package edu.um.OptimizedSuffixTrie;

import edu.um.SuffixTrie.Node;
import java.util.Map;

/**
 *
 */
public class OptimizedNode extends Node<String, OptimizedNode> {

    protected OptimizedNode() {
    }

    /**
     *
     * @param word The word to build Suffix Trie of.
     */
    public void buildPartOfTheSuffixTrie(String word) {
        Map<String, OptimizedNode> leaves = getNodeEdges();
        if (leaves.size() == 0) {
            leaves.put(word, new OptimizedNode());
        } else {
            for (Map.Entry<String, OptimizedNode> edge : leaves.entrySet()) {
                String oldKey = edge.getKey();
                OptimizedNode oldValue = edge.getValue();
                int index = getIndexOfSameLetters(oldKey, word);
                if (index > 0) {
                    String newKey = oldKey.substring(0, index);
                    // Remove old key and put the similar substring as new key
                    if (!newKey.equals(oldKey)) {
                        leaves.remove(oldKey);
                        leaves.put(newKey, new OptimizedNode());
                        // Part of the old key was removed, thus it needs to be set again in the Trie
                        addNewKeyWithChildrenNode(leaves.get(newKey), oldKey.substring(index), oldValue);
                    }
                    leaves.get(newKey).buildPartOfTheSuffixTrie(word.substring(index));
                    return;
                }
            }
            // If it did not find part of the String in a current node, put it.
            leaves.put(word, new OptimizedNode());
        }
    }

    private int getIndexOfSameLetters(final String firstWord, final String secondWord) {
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

    private void addNewKeyWithChildrenNode(OptimizedNode newNode, String key, OptimizedNode children) {
        if (children.getNodeEdges().size() == 0) {
            newNode.getNodeEdges().put(key, new OptimizedNode());
        } else {
            newNode.getNodeEdges().put(key, children);
        }
    }
}
