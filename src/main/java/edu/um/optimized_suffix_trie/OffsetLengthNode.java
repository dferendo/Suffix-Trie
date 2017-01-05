package edu.um.optimized_suffix_trie;

import edu.um.suffix_trie.Node;
import java.util.Map;

/**
 * Created by dylan on 03/01/2017.
 */
public class OffsetLengthNode extends Node<OffsetLengthKey, OffsetLengthNode> {

    void buildOptimizedSuffixTrie(final OptimizedNode head, final String word, final String currentWord) {
        // Loop through all nodes and turn them to offset, length
        for (Map.Entry<String, OptimizedNode> entry : head.getNodeEdges().entrySet()) {
            final String oldKeyString = entry.getKey();
            // Does contain terminal character, end till terminal character
            OffsetLengthNode newNode = new OffsetLengthNode();
            getNodeEdges().put(new OffsetLengthKey(word.indexOf(oldKeyString), oldKeyString.length()), newNode);
            newNode.buildOptimizedSuffixTrie(entry.getValue(), word, currentWord + oldKeyString);
        }
    }

    protected String offsetLengthToString(String word, OffsetLengthKey offsetLength) {
        return word.substring(offsetLength.getOffset(), offsetLength.getOffset() + offsetLength.getLength());
    }

    protected void printTrie(String line, boolean isTail, OffsetLengthKey key) {
        int counter = 0;

        System.out.println(line + (isTail ? "└── " : "├── ") + key.toString());
        for (Map.Entry<OffsetLengthKey, OffsetLengthNode> entry : getNodeEdges().entrySet()) {
            if (counter == getNodeEdges().size() - 1) {
                entry.getValue().printTrie(line + (isTail ? "    " : "│   "), true, entry.getKey());
            } else {
                entry.getValue().printTrie(line + (isTail ? "    " : "│   "), false, entry.getKey());
            }
            counter++;
        }
    }
}

