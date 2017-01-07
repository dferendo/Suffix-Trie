package edu.um.not_optimized_suffix_trie;

import edu.um.suffix_trie.Node;
import java.util.Map;

/**
 * Node holds 1 character for every edge.
 */
public class SuffixNode extends Node<Character, SuffixNode> {

    /**
     * Build the path required for a given string. This method is defined recursively. If the String is empty,
     * a terminal character is added and the method is returned. Otherwise, the head of the String is taken
     * and is checked to see if there is already a {@link SuffixNode SuffixNode} with the same letter. If there is,
     * nothing is added to nodeEdges and the method is called again while getting the {@link SuffixNode SuffixNode}
     * containing the letter needed, and passing the tail of the word given. If there is not a
     * {@link SuffixNode SuffixNode} with the same letter, one is initialised and added to the {@link SuffixNode SuffixNode} nodeEdges.
     * The new node is passed to the method and the tail of the word is given again.
     * @param word The word needed to be build.
     */
    void buildPartOfTheSuffixTrie(final String word) {
        if (word.isEmpty()) {
            getNodeEdges().put(TERMINAL_CHARACTER, new SuffixNode());
        } else {
            final Character currentLetter = head(word);
            final SuffixNode nextNode = getNodeEdges().get(currentLetter);
            if (nextNode != null) {
                nextNode.buildPartOfTheSuffixTrie(tail(word));
            } else {
                getNodeEdges().put(currentLetter, new SuffixNode());
                getNodeEdges().get(currentLetter).buildPartOfTheSuffixTrie(tail(word));
            }
        }
    }

    /**
     * Print part of the Suffix Trie.
     * @param line Represents a whole line.
     * @param isLast Checks if it is the last key.
     * @param key Key to be printed.
     */
     void printTrie(final String line, final boolean isLast, final Character key) {
        int counter = 0;

        System.out.println(line + (isLast ? "└── " : "├── ") + key);
        for (final Map.Entry<Character, SuffixNode> entry : getNodeEdges().entrySet()) {
            if (counter == getNodeEdges().size() - 1) {
                entry.getValue().printTrie(line + (isLast ? "    " : "│   "), true, entry.getKey());;
            } else {
                entry.getValue().printTrie(line + (isLast ? "    " : "│   "), false, entry.getKey());
            }
            counter++;
        }
    }

    private static Character head(final String list) {
        return list.charAt(0);
    }

    private static String tail(final String list) {
        return list.substring(1);
    }
}
