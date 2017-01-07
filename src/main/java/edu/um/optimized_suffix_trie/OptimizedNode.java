package edu.um.optimized_suffix_trie;

import edu.um.suffix_trie.Node;
import java.util.Map;

/**
 * Holds multiple character in a single edge. To accommodate this, the Key is
 * set to String and the the Value is another OptimizedNode.
 */
public class OptimizedNode extends Node<String, OptimizedNode> {

    /**
     * Build part of a Suffix Trie. Used by giving a word and which each call removing the head of the string
     * until the string is empty. It checks if their already is part of given word as a key. If there is not
     * all of the word is appended as a key. Otherwise, the similar letters of the key and the given will
     * be set as the new key. The remaining letters of both the old key and the given word will be set
     * as edges of the new key. The edges of the old key, will be added to the the corresponding parent.
     * @param word The word to build part of the Suffix Trie of.
     */
    void buildPartOfTheSuffixTrie(final String word) {
        final Map<String, OptimizedNode> leaves = getNodeEdges();
        if (leaves.size() == 0) {
            leaves.put(word, new OptimizedNode());
        } else {
            for (final Map.Entry<String, OptimizedNode> edge : leaves.entrySet()) {
                final String oldKey = edge.getKey();
                final OptimizedNode oldValue = edge.getValue();
                final int index = getIndexOfSameLetters(oldKey, word);
                // Part of an existing key is common,
                if (index > 0) {
                    final String newKey = oldKey.substring(0, index);
                    // If the two keys are equal, do not remove the old key
                    if (!newKey.equals(oldKey)) {
                        // Remove old key and put the similar substring as new key
                        leaves.remove(oldKey);
                        final OptimizedNode newKeyNode = new OptimizedNode();
                        leaves.put(newKey, newKeyNode);
                        // Part of the old key was removed, thus it needs to be set again in the Trie
                        addNewKeyWithChildrenNode(newKeyNode, oldValue, oldKey.substring(index));
                    }
                    leaves.get(newKey).buildPartOfTheSuffixTrie(word.substring(index));
                    return;
                }
            }
            // No matching keys.
            leaves.put(word, new OptimizedNode());
        }
    }

    /**
     * Get the index where the two words have the same letters.
     * @param firstWord The first String.
     * @param secondWord The second String.
     * @return The index where the two words contains the same letters. 0 no same letter at the beginning.
     */
    private static int getIndexOfSameLetters(final String firstWord, final String secondWord) {
        int counter = 0;

        while (counter < firstWord.length() && counter < secondWord.length()) {
            if (firstWord.charAt(counter) == secondWord.charAt(counter)) {
                counter++;
            } else {
                return counter;
            }
        }
        return counter;
    }

    /**
     * Takes the new key Node and the old key Node and put the old key to one of the edge
     * to the new Key Node.
     * @param newNode The new Key Node.
     * @param oldKey The old Key Node.
     * @param key The remaining letters of the old Key.
     */
    private void addNewKeyWithChildrenNode(final OptimizedNode newNode, final OptimizedNode oldKey, final String key) {
        if (oldKey.getNodeEdges().size() == 0) {
            newNode.getNodeEdges().put(key, new OptimizedNode());
        } else {
            newNode.getNodeEdges().put(key, oldKey);
        }
    }
}
