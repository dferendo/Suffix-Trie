package edu.um.OptimizedSuffixTrie;

import edu.um.SuffixTrie.Node;
import java.util.Map;

/**
 * Created by dylan on 03/01/2017.
 */
public class OffsetLengthNode extends Node<OffsetLengthKey, OffsetLengthNode> {

    void buildOptimizedSuffixTrie(final OptimizedNode head, final String word, final String currentWord) {
        // Loop through all nodes and turn them to offset, length
        for (Map.Entry<String, OptimizedNode> entry : head.getNodeEdges().entrySet()) {
            final String arranged = entry.getKey();
            // Does contain terminal character therefore no length needed
            if (arranged.contains(Character.toString(TERMINAL_CHARACTER))) {
                int offset = currentWord.length() + arranged.length() - 1;
                getNodeEdges().put(new OffsetLengthKey(word.length() -  offset, null), new OffsetLengthNode());
            } else {
                OffsetLengthNode newNode = new OffsetLengthNode();
                getNodeEdges().put(new OffsetLengthKey(word.indexOf(arranged), arranged.length()), newNode);
                newNode.buildOptimizedSuffixTrie(entry.getValue(), word, currentWord + arranged);
            }
        }
    }

    protected String offsetLengthToString(String word, OffsetLengthKey offsetLength) {
        // If length is null, it indicates that the node is a leaf
        if (offsetLength.getLength() == null) {
            return word.substring(offsetLength.getOffset());
        } else {
            return word.substring(offsetLength.getOffset(), offsetLength.getOffset() + offsetLength.getLength());
        }
    }
}

