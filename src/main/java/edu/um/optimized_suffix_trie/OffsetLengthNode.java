package edu.um.optimized_suffix_trie;

import edu.um.suffix_trie.Node;
import java.util.Map;

/**
 * The Node contains a key {@link OffsetLengthKey OffsetLengthKey} which is a pair that contains
 * two integers (offset, length). The value is another OffsetLengthNode.
 */
public class OffsetLengthNode extends Node<OffsetLengthKey, OffsetLengthNode> {

    /**
     * Using the Optimisation {@link OptimizedNode OptimizedNode}, this method will transfer
     * every edge to (offset, length) key using {@link OffsetLengthKey OffsetLengthKey}. It
     * will transfer to the new key using String.indexOf on the Suffix word for the offset. This is
     * done for every edge in the Trie.
     * @param head The current node.
     * @param word The word the Trie is built on.
     */
    protected void buildOptimizedSuffixTrie(final OptimizedNode head, final String word) {
        // Loop through all nodes and turn them to offset, length
        for (final Map.Entry<String, OptimizedNode> entry : head.getNodeEdges().entrySet()) {
            final String oldKeyString = entry.getKey();
            final OffsetLengthNode newNode = new OffsetLengthNode();
            getNodeEdges().put(new OffsetLengthKey(word.indexOf(oldKeyString), oldKeyString.length()), newNode);
            // Do the same for every child's edge
            newNode.buildOptimizedSuffixTrie(entry.getValue(), word);
        }
    }

    /**
     * Transfer from {@link OffsetLengthNode OffsetLengthKey} to {@link String String}.
     * @param word The word that the Suffix Trie is built on.
     * @param offsetLengthKey The key to be transferred.
     * @return The String for a key.
     */
    protected static String offsetLengthToString(final String word, final OffsetLengthKey offsetLengthKey) {
        return word.substring(offsetLengthKey.getOffset(), offsetLengthKey.getOffset() + offsetLengthKey.getLength());
    }

    @Override
    protected void printTrie(final String line, final boolean isLast, final OffsetLengthKey key) {
        int counter = 0;

        System.out.println(line + (isLast ? "└── " : "├── ") + key.toString());
        for (final Map.Entry<OffsetLengthKey, OffsetLengthNode> entry : getNodeEdges().entrySet()) {
            if (counter == getNodeEdges().size() - 1) {
                entry.getValue().printTrie(line + (isLast ? "    " : "│   "), true, entry.getKey());
            } else {
                entry.getValue().printTrie(line + (isLast ? "    " : "│   "), false, entry.getKey());
            }
            counter++;
        }
    }
}

