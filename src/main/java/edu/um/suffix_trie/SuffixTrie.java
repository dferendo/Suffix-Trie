package edu.um.suffix_trie;

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
     void buildSuffixTrie(final String word);

    /**
     * Checks if the given string is a substring of the Suffix Trie.
     * @param word Given word to compare.
     * @return True if the word is a substring, false otherwise.
     */
     boolean substring(final String word);

    /**
     * A suffix of a string is a substring that occurs at the end of the Suffix Trie.
     * The path for the word is traversed and the leaf is checked to see if it contains
     * the terminal character.
     * @param word Given word to compare.
     * @return True if the word is a suffix, false otherwise.
     */
     boolean suffix(final String word);

    /**
     * Count how many times word appears in the Suffix Trie. This is done by following the
     * path of the given word starting from the head. The leaves under the node are the
     * number of occurrences.
     * @param word Given word to count.
     * @return The number of occurrences.
     */
     int count(final String word);

    /**
     * Find the longest repeated substring in the Suffix Trie by finding the deepest node with
     * more than one child.
     * @return The longest repeated substring. Empty String if there are no repeated substring.
     */
     String longestRepeat();

    /**
     * Finds the longest common substring between the given word and Suffix Trie. To accomplish this
     * first traverse the given list. If the given list is traversed, return it since it is the longest
     * substring. If a dead end is found, the suffix link on the current traverse string is used. The current
     * string is also added to the list. When using the suffix link, the the tail of the current string is
     * taken as the new current String and the Suffix Trie is traversed from the root. If the current String
     * is empty, there is no suffix link and thus the character from the given word is removed. The above
     * steps are repeated until the given word is empty. The longest string is returned.
     * @param givenWord The word to compare with the edu.um.SuffixTrie.SuffixTrie.
     * @return The longest substring.
     */
     String longestSubstring(final String givenWord);

    /**
     * Display Suffix Trie. Style inspired by linux tree folder structure command.
     */
    void show();
}
