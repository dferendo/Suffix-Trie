package edu.um.not_optimized_suffix_trie;

import edu.um.exception.WordContainsTerminalCharacterException;
import edu.um.suffix_trie.Node;
import edu.um.suffix_trie.SuffixTrie;
import javafx.util.Pair;

import java.util.*;

/**
 * Implements a Suffix Trie that holds 1 character per edge.
 */
public class SuffixTrieImpl implements SuffixTrie {

    private SuffixNode head;

    public SuffixTrieImpl(final String word) throws WordContainsTerminalCharacterException {
        if (word.contains(Character.toString(Node.TERMINAL_CHARACTER))) {
            throw new WordContainsTerminalCharacterException();
        }
        buildSuffixTrie(word);
    }

    @Override
    public void buildSuffixTrie(String word) {
        head = new SuffixNode();

        while (!word.isEmpty()) {
            head.buildPartOfTheSuffixTrie(word);
            word = tail(word);
        }
        // Add a terminal character since the while loop will stop before inserting the terminal character
        // found in the map of the head.
        head.buildPartOfTheSuffixTrie("");
    }

    @Override
    public boolean substring(final String word) {
        return substringImpl(head, word);
    }

    private boolean substringImpl(final SuffixNode currentSuffixNode, final String word) {
        if (word.isEmpty()) {
            return true;
        } else {
            if (currentSuffixNode.getNodeEdges().containsKey(head(word))) {
                return substringImpl(currentSuffixNode.getNodeEdges().get(head(word)), tail(word));
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean suffix(final String word) {
        return substringImpl(head, word + Node.TERMINAL_CHARACTER);
    }

    @Override
    public int count(final String word) {
        return countImpl(head, word);
    }

    private int countImpl(final SuffixNode currentSuffixNode, final String word) {
        // Traverse until the given word is empty.
        if (word.isEmpty()) {
            return getNumberOfLeafNodes(currentSuffixNode);
        } else {
            if (currentSuffixNode.getNodeEdges().containsKey(head(word))) {
                return countImpl(currentSuffixNode.getNodeEdges().get(head(word)), tail(word));
            } else {
                // Not found returns 0
                return 0;
            }
        }
    }

    /**
     * Count the number of leaves under a SuffixNode. Leaves are indicated by the
     * terminal character. All the children of the SuffixNode are visited.
     * @param node The edges of the SuffixNode.
     * @return The total number of leaf nodes.
     */
    private int getNumberOfLeafNodes(final SuffixNode node) {
        int counter = 0;
        final Stack<SuffixNode> allPossible = new Stack<>();
        allPossible.push(node);

        while (!allPossible.isEmpty()) {
            final SuffixNode childNodes = allPossible.pop();
            for (final Map.Entry<Character, SuffixNode> entry : childNodes.getNodeEdges().entrySet()) {
                allPossible.push(entry.getValue());
                if (entry.getKey() == Node.TERMINAL_CHARACTER) {
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
     * To find the longest repeated substring, Breadth-first search algorithm is used.
     * The search algorithm was preferred since it search the Suffix Trie level by level.
     * Thus if a new SuffixNode with multiple edges is encountered, it is accepted as the new
     * deepest SuffixNode with more than one child.
     * @return The longest repeated substring.
     */
    @Override
    public String longestRepeat() {
        String deepestNodeWithMultipleChild = "";
        // With every level, the traversed string is kept in the Pair followed.
        // This is done to take the string immediately instead of traverse to the SuffixNode again.
        final Queue<Pair<String, SuffixNode>> allPossible = new LinkedList<>();
        allPossible.add(new Pair<>(deepestNodeWithMultipleChild, getHead()));

        while (!allPossible.isEmpty()) {
            final Pair<String, SuffixNode> childNode = allPossible.remove();
            if (childNode.getValue().getNodeEdges().size() > 1) {
                deepestNodeWithMultipleChild = childNode.getKey();
            }
            for (final Map.Entry<Character, SuffixNode> entry : childNode.getValue().getNodeEdges().entrySet()) {
                allPossible.add(new Pair<>(childNode.getKey() + entry.getKey(), entry.getValue()));
            }
        }
        return deepestNodeWithMultipleChild;
    }

    @Override
    public String longestSubstring(String givenWord) {
        SuffixNode currentSuffixNode = getHead();
        String currentLongestString = "";
        final List<String> longestString = new ArrayList<>();

        while (!givenWord.isEmpty()) {
            if (currentSuffixNode.getNodeEdges().containsKey(head(givenWord))) {
                currentSuffixNode = currentSuffixNode.getNodeEdges().get(head(givenWord));
                currentLongestString += head(givenWord);
                givenWord = tail(givenWord);
            } else {
                if (!currentLongestString.isEmpty()) {
                    // Get suffix link, add the currentLongestString to the list and set the
                    // currentLongestString to the tail of the currentLongestString.
                    currentSuffixNode = getSuffixLink(getHead(), tail(currentLongestString));
                    longestString.add(currentLongestString);
                    currentLongestString = tail(currentLongestString);
                } else {
                    // Cannot use suffix link, ignore the letter.
                    givenWord = tail(givenWord);
                }
            }
        }
        // Add the current String at the end of the traverse to compare with the other Strings.
        longestString.add(currentLongestString);
        // Return the longest String from the list of strings.
        return Collections.max(longestString, Comparator.comparing(String::length));
    }

    /**
     * Return the node that is the suffix link of the given tailOfWord. To use this, pass only
     * the tail of the tailOfWord you want the suffix link from.
     * @param currentSuffixNode The current SuffixNode.
     * @param tailOfWord The tail of the word you want the suffix link of.
     * @return The node containing the suffix link.
     */
    private SuffixNode getSuffixLink(final SuffixNode currentSuffixNode, final String tailOfWord) {
        if (tailOfWord.isEmpty()) {
            return currentSuffixNode;
        } else {
            return getSuffixLink(currentSuffixNode.getNodeEdges().get(head(tailOfWord)), tail(tailOfWord));
        }
    }

    @Override
    public void show() {
        int counter = 1;

        System.out.println("All the characters show the edge between 2 nodes");
        for (final Map.Entry<Character, SuffixNode> entry : head.getNodeEdges().entrySet()) {
            if (counter == head.getNodeEdges().size()) {
                entry.getValue().printTrie("", true, entry.getKey());
            } else {
                entry.getValue().printTrie("", false, entry.getKey());
            }
            counter++;
        }
    }

    private SuffixNode getHead() {
        return head;
    }

    private Character head(final String list) {
        return list.charAt(0);
    }

    private String tail(final String list) {
        return list.substring(1);
    }

}
