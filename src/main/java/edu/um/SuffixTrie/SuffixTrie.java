package edu.um.SuffixTrie;

/**
 * Created by dylan on 02/12/2016.
 * A edu.um.SuffixTrie.SuffixTrie is an ordered rooted tree where each edge is labelled with
 * a unique letter from an alphabet. A {@link Node Node} has at most one receiving
 * edge labelled. Keys (collection of words) are spelled out along some path starting
 * from the root and ending with a terminal character(~).
 */
public interface SuffixTrie {

     /**
      * Build the Suffix Trie.
      * @param word The given word
      */
     void buildSuffixTrie(String word);

    /**
     * Checks if the given string is a substring of the edu.um.SuffixTrie.SuffixTrie.
     * @param word Given word to compare.
     * @return True if the word is a substring, false otherwise.
     */
     boolean substring(String word);

    /**
     * A suffix of a string is a substring that occurs at the end of the edu.um.SuffixTrie.SuffixTrie.
     * The path for the word is traversed and the leaf is checked to see if it contains
     * the terminal character.
     * @param word Given word to compare.
     * @return True if the word is a suffix, false otherwise.
     */
     boolean suffix(String word);

    /**
     * Count how many times word appears in the edu.um.SuffixTrie.SuffixTrie. This is done by following the
     * path of the given word starting from the head. The leaves under the node are the
     * number of occurrences.
     * @param word Given word to count.
     * @return The number of occurrences.
     */
     int count(String word);

    /**
     * Find the longest repeated substring in the edu.um.SuffixTrie.SuffixTrie by finding the deepest node with
     * more than one child. To find the longest repeated substring, Breadth-first search algorithm is used.
     * The search algorithm was preferred since it search the edu.um.SuffixTrie.SuffixTrie level by level.
     * Thus if a new SuffixNode with multiple edges is encountered, it is accepted as the new
     * deepest SuffixNode with more than one child.
     * @return The longest repeated substring. Empty String if there are no repeated substring.
     */
     String longestRepeat();

    /**
     * Finds the longest common substring between the given word and the edu.um.SuffixTrie.SuffixTrie. To accomplish this
     * first traverse the given list. If the given list is traversed, return it since it is the longest
     * substring. If a dead end is found, the suffix link on the current traverse string is used. The current
     * string is also added to the list. When using the suffix link, the the tail of the current string is
     * taken as the new current String. If the current String is empty, there is no suffix link and thus
     * the character from the given word is removed. The above steps are repeated until the given word is empty.
     * The longest string is returned.
     * @param givenWord The word to compare with the edu.um.SuffixTrie.SuffixTrie.
     * @return The longest substring.
     */
     String longestSubstring(String givenWord);

    /**
     * Display the edu.um.SuffixTrie.SuffixTrie using breath-first search algorithm.
     */
    void show();
}
