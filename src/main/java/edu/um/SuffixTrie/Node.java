package edu.um.SuffixTrie;

import java.util.Map;
import java.util.TreeMap;

/**
 * SuffixTrie are connected with Nodes. Each Node will contain a Map that holds its children.
 * @param <K> The Key of the Map
 * @param <V> The Children of the Map
 */
public abstract class Node<K, V> {
    /**
     * The end nodes will be marked with this Terminal character.
     */
    public final static Character TERMINAL_CHARACTER = '~';

    /**
     * Every SuffixNode will have edges and each label of the edge is unique. Therefore
     * LinkedHashMap is used to maintain alphabetical order.
     */
    private Map<K, V> nodeEdges = new TreeMap<>();

    public Map<K, V> getNodeEdges() {
        return nodeEdges;
    }

    protected void setNodeEdges(Map<K, V> nodeEdges) {
        this.nodeEdges = nodeEdges;
    }
}
