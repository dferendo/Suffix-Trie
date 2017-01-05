package edu.um.optimized_suffix_trie;

import com.sun.istack.internal.Nullable;
import edu.um.exception.WordContainsTerminalCharacterException;
import edu.um.suffix_trie.Node;
import edu.um.suffix_trie.SuffixTrie;
import javafx.util.Pair;

import java.util.*;

import static edu.um.optimized_suffix_trie.OffsetLengthNode.offsetLengthToString;

/**
 * Optimized Suffix Trie using offset, length.
 */
public class OptimizedSuffixTrieImpl implements SuffixTrie {

    private OffsetLengthNode head;
    private String word;

    public OptimizedSuffixTrieImpl(final String word) throws WordContainsTerminalCharacterException {
        if (word.contains(Character.toString(Node.TERMINAL_CHARACTER))) {
            throw new WordContainsTerminalCharacterException();
        }
        this.word = word + Node.TERMINAL_CHARACTER;
        buildSuffixTrie(this.word);
    }

    /**
     * The Suffix Trie is built by first building {@link OptimizedNode OptimizedNode} and then
     * using it to transfer to {@link OffsetLengthNode OffsetLengthNode}.
     * @param word The given word
     */
    @Override
    public void buildSuffixTrie(final String word) {
        final OptimizedNode stringHead = buildSuffixTrieWithString(word);
        head = new OffsetLengthNode();
        head.buildOptimizedSuffixTrie(stringHead, word);
    }

    private OptimizedNode buildSuffixTrieWithString(String word) {
        final OptimizedNode head = new OptimizedNode();
        word += Node.TERMINAL_CHARACTER;

        while (!word.isEmpty()) {
            head.buildPartOfTheSuffixTrie(word);
            word = tail(word);
        }
        return head;
    }

    @Override
    public boolean substring(final String word) {
        return substringImpl(head, word);
    }

    private boolean substringImpl(final OffsetLengthNode currentSuffixNode, final String subStringWord) {
        if (subStringWord.isEmpty()) {
            return true;
        } else {
            final Pair<OffsetLengthNode, String> nextNode = nextTraverse(currentSuffixNode, subStringWord);
            if (nextNode != null) {
                return substringImpl(nextNode.getKey(), nextNode.getValue());
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

    private int countImpl(final OffsetLengthNode currentSuffixNode,final String subStringWord) {
        // Traverse until the given word is empty.
        if (subStringWord.isEmpty()) {
            // If the program is here, there is at least 1 count even though the Node
            // has no children. This is because the Node containing the Terminal Character will
            // be already traversed.
            if (currentSuffixNode.getNodeEdges().size() == 0) {
                // The terminal character was in the node, so it will have no other children.
                return 1;
            }
            return getNumberOfLeafNodes(currentSuffixNode);
        } else {
            final Pair<OffsetLengthNode, String> nextNode = nextTraverse(currentSuffixNode, subStringWord);
            if (nextNode != null) {
                return countImpl(nextNode.getKey(), nextNode.getValue());
            } else {
                // Not found returns 0
                return 0;
            }
        }
    }

    /**
     * Find the longest repeated substring by finding the deepest node from the root
     * to a node containing multiple children. The depth is measured by characters
     * traversed through the trie from the root to the node. The string spelled
     * by the edges from the root to the deepest node is the longest repeated substring.
     * @return Longest repeated substring.
     */
    @Override
    public String longestRepeat() {
        String deepestNodeWithMultipleChild = "";
        int deepestNodeLength = 0;
        // With every level, the traversed string is kept in the Pair followed.
        // This is done to take the string immediately instead of traverse to the SuffixNode again.
        final Queue<Pair<String, OffsetLengthNode>> allPossible = new LinkedList<>();
        allPossible.add(new Pair<>(deepestNodeWithMultipleChild, head));

        while (!allPossible.isEmpty()) {
            final Pair<String, OffsetLengthNode> childNode = allPossible.remove();
            // The node must have at least 2 children
            if (childNode.getValue().getNodeEdges().size() > 1) {
                // The deepness from the root to the node must be greater than the previous deepest node
                if (childNode.getKey().length() > deepestNodeLength) {
                    deepestNodeWithMultipleChild = childNode.getKey();
                    deepestNodeLength = childNode.getKey().length();
                }
            }
            // Add the children of the current node.
            for (final Map.Entry<OffsetLengthKey, OffsetLengthNode> entry : childNode.getValue().getNodeEdges().entrySet()) {
                allPossible.add(new Pair<>(childNode.getKey() + offsetLengthToString(word, entry.getKey()), entry.getValue()));
            }
        }
        return deepestNodeWithMultipleChild;
    }

    @Override
    public String longestSubstring(String givenWord) {
        OffsetLengthNode currentSuffixNode = head;
        final List<String> longestString = new ArrayList<>();
        String currentLongestString = "";

        while (!givenWord.isEmpty()) {
            final Pair<OffsetLengthNode, String> nextNode = nextTraverse(currentSuffixNode, givenWord);
            if (nextNode != null) {
                currentSuffixNode = nextNode.getKey();
                currentLongestString += givenWord.substring(0, givenWord.length() - nextNode.getValue().length());
                givenWord = nextNode.getValue();
            } else {
                if (!currentLongestString.isEmpty()) {
                    // Get suffix link, add the currentLongestString to the list and set the
                    // currentLongestString to the tail of the currentLongestString.
                    currentSuffixNode = getSuffixLink(head, tail(currentLongestString));
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
     * Get the suffix Link of Node. This is done by passing the tail of the word you want
     * the suffix link of.
     * @param currentNode The current Node.
     * @param word The tail of the word you want the suffix link of.
     * @return The Node of the suffix link.
     */
    private OffsetLengthNode getSuffixLink(final OffsetLengthNode currentNode, final String word) {
        if (word.isEmpty()) {
            return currentNode;
        }

        final Pair<OffsetLengthNode, String> nextNode = nextTraverse(currentNode, word);
        if (nextNode == null) {
            return currentNode;
        }
        return getSuffixLink(nextNode.getKey(), nextNode.getValue());
    }

    @Override
    public void show() {
        int counter = 1;

        System.out.println("All the characters show the edge between 2 nodes");
        printWordInABox(word);
        for (final Map.Entry<OffsetLengthKey, OffsetLengthNode> entry : head.getNodeEdges().entrySet()) {
            if (counter == head.getNodeEdges().size()) {
                entry.getValue().printTrie("", true, entry.getKey());
            } else {
                entry.getValue().printTrie("", false, entry.getKey());
            }
            counter++;
        }
    }

    private void printWordInABox(final String word) {
        final StringBuilder letters = new StringBuilder("|");
        final StringBuilder numbers = new StringBuilder("|");

        for (int i = 0; i < word.length(); i++) {
            letters.append(word.charAt(i)).append("|");
            numbers.append(i).append("|");
        }
        System.out.println(letters);
        System.out.println(numbers);
        System.out.println();
    }

    /**
     * Move from one node to another node given the word to traverse. If it is possible to traverse it
     * will return the new Node and the remaining string. Otherwise it will return null. The return
     * value can be present or missing, therefore no exception will be thrown since that result
     * is excepted (It is not an error).
     * @param currentSuffixNode The Node you want to traverse from.
     * @param inputWord The word inputted that want to traverse.
     * @return null if traversing to another node is not possible otherwise {@link Pair Pair} containing
     * the next Node and the remaining String from the input word.
     */
    @Nullable
    private Pair<OffsetLengthNode, String> nextTraverse(final OffsetLengthNode currentSuffixNode,
                                                        final String inputWord) {
        for(final Map.Entry<OffsetLengthKey, OffsetLengthNode> children : currentSuffixNode.getNodeEdges().entrySet()) {
            final String childString = offsetLengthToString(word, children.getKey());
            if (childString.charAt(0) == inputWord.charAt(0)) {
                return new Pair<>(children.getValue(), determineNewWordAfterTraverse(childString, inputWord));
            }
        }
        // No Traverse found
        return null;
    }

    /**
     * Returns the new string after traversing. Multiple characters can be traversed at once.
     * @param traverseString The traverse String.
     * @param inputWord The input word.
     * @return The string after removing the traversed characters from the input word.
     */
    private String determineNewWordAfterTraverse(final String traverseString, final String inputWord) {
        int counter = 0;

        while (traverseString.length() > counter && inputWord.length() > counter) {
            if (traverseString.charAt(counter) == inputWord.charAt(counter)) {
                counter++;
            } else {
                break;
            }
        }
        return inputWord.substring(counter);
    }

    /**
     * Count the number of leaves from a Node.
     * @param optimizedNode The current Node.
     * @return Number of Leaves nodes. 0 if none found.
     */
    private int getNumberOfLeafNodes(final OffsetLengthNode optimizedNode) {
        int counter = 0;
        final Stack<OffsetLengthNode> allPossible = new Stack<>();
        allPossible.push(optimizedNode);

        while (!allPossible.isEmpty()) {
            final OffsetLengthNode childNodes = allPossible.pop();
            for (final Map.Entry<OffsetLengthKey, OffsetLengthNode> entry : childNodes.getNodeEdges().entrySet()) {
                allPossible.push(entry.getValue());
                if (offsetLengthToString(word, entry.getKey()).contains(Character.toString(Node.TERMINAL_CHARACTER))) {
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
     * Get the tail of a String.
     * @param list The word.
     * @return Tail of the word.
     */
    private String tail(final String list) {
        return list.substring(1);
    }

}
