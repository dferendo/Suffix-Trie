package edu.um.optimized_suffix_trie;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * {@link edu.um.not_optimized_suffix_trie.LongestMethodsTest LongestMethodsTest} tests still apply for
 * the optimized OptimizedSuffixTrieImpl, so they will be recycled.
 */
public class OptimizedLongestMethodsTest {

    @Test
    public void longestRepeat_geeksForGeeks() throws Exception {
        final String givenString = "geeksforgeeks";
        final String longestSubstring = "geeks";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue("Longest repeat substring for " + givenString + " is " +longestSubstring,
                suffixTrie.longestRepeat().equals(longestSubstring));
    }

    @Test
    public void longestRepeat_multipleSameLetterWord() throws Exception {
        final String givenString = "aaaaaaaaaa";
        final String longestSubstring = "aaaaaaaaa";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue("Longest repeat substring for " + givenString + " is " +longestSubstring,
                suffixTrie.longestRepeat().equals(longestSubstring));
    }

    @Test
    public void longestRepeat_noRepeatedWord() throws Exception {
        final String givenString = "abcdefg";
        final String longestSubstring = "";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue("Longest repeat substring for " + givenString + " is " +longestSubstring,
                suffixTrie.longestRepeat().equals(longestSubstring));
    }

    @Test
    public void longestRepeat_banana() throws Exception {
        final String givenString = "banana";
        final String longestSubstring = "ana";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue("Longest repeat substring for " + givenString + " is " +longestSubstring,
                suffixTrie.longestRepeat().equals(longestSubstring));
    }

    @Test
    public void longestRepeat_multipleRepeatedWords() throws Exception {
        final String givenString = "abcpqrabpqpq";
        final String firstLongestSubstring = "ab";
        final String secondLongestSubstring = "pq";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue("Longest repeat substring for " + givenString + " is " + firstLongestSubstring + " or "
                + secondLongestSubstring, suffixTrie.longestRepeat().equals(firstLongestSubstring)
                || suffixTrie.longestRepeat().equals(secondLongestSubstring));
    }

    @Test
    public void longestCommonSubstring_middleCommon() throws Exception {
        final String givenString = "xabxac";
        final String findCommonSubstring = "abcabxabcd";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue(suffixTrie.longestSubstring(findCommonSubstring).equals("abxa"));
    }

    @Test
    public void longestCommonSubstring_beginningCommon() throws Exception {
        final String givenString = "GeeksforGeeks";
        final String findCommonSubstring = "GeeksQuiz";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue(suffixTrie.longestSubstring(findCommonSubstring).equals("Geeks"));
    }

    @Test
    public void longestCommonSubstring_lastCommon() throws Exception {
        final String givenString = "OldSite:GeeksforGeeks.org";
        final String findCommonSubstring = "NewSite:NotGeeks.org";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue(suffixTrie.longestSubstring(findCommonSubstring).equals("Geeks.org"));
    }

    @Test
    public void longestCommonSubstring_oneLetterCommon() throws Exception {
        final String givenString = "abcde";
        final String findCommonSubstring = "fghie";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue(suffixTrie.longestSubstring(findCommonSubstring).equals("e"));
    }

    @Test
    public void longestCommonSubstring_noCommon() throws Exception {
        final String givenString = "pqrst";
        final String findCommonSubstring = "uvwxyz";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue(suffixTrie.longestSubstring(findCommonSubstring).equals(""));
    }
}
