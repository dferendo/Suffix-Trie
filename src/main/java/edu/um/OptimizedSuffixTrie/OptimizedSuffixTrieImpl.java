package edu.um.OptimizedSuffixTrie;

import edu.um.NotOptimizedSuffixTrie.SuffixNode;
import edu.um.SuffixTrie.Node;
import edu.um.SuffixTrie.SuffixTrie;
import javafx.util.Pair;

import java.util.*;

/**
 * Created by dylan on 25/12/2016.
 */
public class OptimizedSuffixTrieImpl implements SuffixTrie {

    //    private OffsetLengthNode head;
    private OptimizedNode head;
    private String word;

    public OptimizedSuffixTrieImpl(final String word) {
        this.word = word;
        buildSuffixTrie(word);
    }

    public void buildSuffixTrie(String word) {
        head = buildSuffixTrieWithString(word);
//        OptimizedNode stringHead = buildSuffixTrieWithString(word);
//        head = new OffsetLengthNode();
//        head.buildOptimizedSuffixTrie(stringHead, word, "");
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

    public boolean substring(String word) {
        return substringImpl(head, this.word, word);
    }

    //    private boolean substringImpl(OffsetLengthNode currentSuffixNode, String word, String subStringWord) {
    private boolean substringImpl(OptimizedNode currentSuffixNode, String word, String subStringWord) {
        if (subStringWord.isEmpty()) {
            return true;
        } else {
            Pair<OptimizedNode, String> nextNode = nextTraverse(currentSuffixNode, word, subStringWord);
            if (nextNode != null) {
                return substringImpl(nextNode.getKey(), word, nextNode.getValue());
            } else {
                return false;
            }
        }
    }

    public boolean suffix(String word) {
        return suffixImpl(head, this.word, word + Node.TERMINAL_CHARACTER);
    }

    private boolean suffixImpl(OptimizedNode currentSuffixNode, String word, String subStringWord) {
        if (subStringWord.isEmpty()) {
            return true;
        } else {
            Pair<OptimizedNode, String> nextNode = nextTraverse(currentSuffixNode, word, subStringWord);
            if (nextNode != null) {
                return suffixImpl(nextNode.getKey(), word, nextNode.getValue());
            } else {
                return false;
            }
        }
    }

    public int count(String word) {
        return countImpl(head, this.word, word);
    }

    private int countImpl(OptimizedNode currentSuffixNode, String word, String subStringWord) {
        // Traverse until the given word is empty.
        if (subStringWord.isEmpty()) {
            // If the program is here, there is at least 1 count.
            if (currentSuffixNode.getNodeEdges().size() == 0) {
                // The terminal character was in the node, so it will have no other children.
                return 1;
            }
            return getNumberOfLeafNodes(currentSuffixNode);
        } else {
            Pair<OptimizedNode, String> nextNode = nextTraverse(currentSuffixNode, word, subStringWord);
            if (nextNode != null) {
                return countImpl(nextNode.getKey(), word, nextNode.getValue());
            } else {
                // Not found returns 0
                return 0;
            }
        }
    }

    /**
     * Find the longest repeated substring by finding the deepest internal node
     * in the tree. The depth is measured by characters traversed through the trie from the
     * root to the node. The string spelled by the edges from the root to the deepest node
     * is the longest repeated substring.
     * @return Longest repeated substring.
     */
    public String longestRepeat() {
        String deepestNodeWithMultipleChild = "";
        int deepestNodeLength = 0;
        // With every level, the traversed string is kept in the Pair followed.
        // This is done to take the string immediately instead of traverse to the SuffixNode again.
        Queue<Pair<String, OptimizedNode>> allPossible = new LinkedList<>();
        allPossible.add(new Pair<>(deepestNodeWithMultipleChild, head));

        while (!allPossible.isEmpty()) {
            Pair<String, OptimizedNode> childNode = allPossible.remove();
            // The node must have at least 2 children
            if (childNode.getValue().getNodeEdges().size() > 1) {
                // The deepness from the root to the node must be greater than the previous deepest node
                if (childNode.getKey().length() > deepestNodeLength) {
                    deepestNodeWithMultipleChild = childNode.getKey();
                    deepestNodeLength = childNode.getKey().length();
                }
            }
            for (Map.Entry<String, OptimizedNode> entry : childNode.getValue().getNodeEdges().entrySet()) {
                allPossible.add(new Pair<>(childNode.getKey() + entry.getKey(), entry.getValue()));
            }
        }
        return deepestNodeWithMultipleChild;
    }

    public String longestSubstring(String givenWord) {
        OptimizedNode currentSuffixNode = head;
        List<String> longestString = new ArrayList<>();
        String currentLongestString = "";

        while (!givenWord.isEmpty()) {
            Pair<OptimizedNode, String> nextNode = nextTraverse(currentSuffixNode, word, givenWord);
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

    private OptimizedNode getSuffixLink(OptimizedNode currentNode, String word) {
        if (word.isEmpty()) {
            return currentNode;
        }

        Pair<OptimizedNode, String> nextNode = nextTraverse(currentNode, this.word, word);
        if (nextNode == null) {
            return currentNode;
        }
        return getSuffixLink(nextNode.getKey(), nextNode.getValue());
    }

    public void show() {
        Queue<OptimizedNode> allPossible = new LinkedList<>();
        allPossible.add(head);
        allPossible.add(null);

        while (!allPossible.isEmpty()) {
            OptimizedNode childNodes = allPossible.remove();
            if (childNodes == null) {
                System.out.println();
                continue;
            }
            for (Map.Entry<String , OptimizedNode> entry : childNodes.getNodeEdges().entrySet()) {
                System.out.print(entry.getKey() + " ");
                allPossible.add(entry.getValue());
            }
            allPossible.add(null);
        }
    }

//    private Pair<OffsetLengthNode, String> nextTraverse(OffsetLengthNode currentSuffixNode, String word, String inputWord) {
//        for(Map.Entry<OffsetLengthKey, OffsetLengthNode> children : currentSuffixNode.getNodeEdges().entrySet()) {
//            String childString = children.getValue().offsetLengthToString(word, children.getKey());
//            if (childString.charAt(0) == inputWord.charAt(0)) {
//                return new Pair<>(children.getValue(), determineNewWordAfterTraverse(childString, inputWord));
//            }
//        }
//        // No Traverse found
//        return null;
//    }

    private Pair<OptimizedNode, String> nextTraverse(OptimizedNode currentSuffixNode, String word, String inputWord) {
        for(Map.Entry<String, OptimizedNode> children : currentSuffixNode.getNodeEdges().entrySet()) {
            String childString = children.getKey();
            if (childString.charAt(0) == inputWord.charAt(0)) {
                return new Pair<>(children.getValue(), determineNewWordAfterTraverse(childString, inputWord));
            }
        }
        // No Traverse found
        return null;
    }

    private String determineNewWordAfterTraverse(String traverseString, String inputWord) {
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

    private int getNumberOfLeafNodes(OptimizedNode optimizedNode) {
        int counter = 0;
        Stack<OptimizedNode> allPossible = new Stack<>();
        allPossible.push(optimizedNode);

        while (!allPossible.isEmpty()) {
            OptimizedNode childNodes = allPossible.pop();
            for (Map.Entry<String, OptimizedNode> entry : childNodes.getNodeEdges().entrySet()) {
                allPossible.push(entry.getValue());
                if (entry.getKey().contains(Character.toString(Node.TERMINAL_CHARACTER))) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private Character head(String list) {
        return list.charAt(0);
    }

    private String tail(String list) {
        return list.substring(1);
    }

}
