package edu.um.NotOptimizedSuffixTrie;

import edu.um.SuffixTrie.Node;
import java.util.Map;

/**
 * Created by dylan on 02/12/2016.
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
    public void buildPartOfTheSuffixTrie(String word) {
        if (word.isEmpty()) {
            getNodeEdges().put(TERMINAL_CHARACTER, new SuffixNode());
        } else {
            Character currentLetter = head(word);
            SuffixNode nextNode = getNodeEdges().get(currentLetter);
            if (nextNode != null) {
                nextNode.buildPartOfTheSuffixTrie(tail(word));
            } else {
                getNodeEdges().put(currentLetter, new SuffixNode());
                getNodeEdges().get(currentLetter).buildPartOfTheSuffixTrie(tail(word));
            }
        }
    }

    public void printTrie(String line, boolean isTail, Character name) {
        int counter = 0;

        System.out.println(line + (isTail ? "└── " : "├── ") + name);
        for (Map.Entry<Character, SuffixNode> entry : getNodeEdges().entrySet()) {
            if (counter == getNodeEdges().size() - 1) {
                entry.getValue().printTrie(line + (isTail ? "    " : "│   "), true, entry.getKey());;
            } else {
                entry.getValue().printTrie(line + (isTail ? "    " : "│   "), false, entry.getKey());
            }
            counter++;
        }
    }

    private Character head(String list) {
        return list.charAt(0);
    }

    private String tail(String list) {
        return list.substring(1);
    }

}
