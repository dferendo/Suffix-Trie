package edu.um.OptimizedSuffixTrie;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * {@link edu.um.NotOptimizedSuffixTrie.LongestMethodsTest LongestMethodsTest} tests still apply for
 * the optimized OptimizedSuffixTrieImpl, so they will be recycled.
 */
public class OptimizedLongestMethodsTest {

    @Test
    public void longestRepeat_geeksForGeeks() {
        final String givenString = "geeksforgeeks";
        final String longestSubstring = "geeks";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue("Longest repeat substring for " + givenString + " is " +longestSubstring,
                suffixTrie.longestRepeat().equals(longestSubstring));
    }

    @Test
    public void longestRepeat_multipleSameLetterWord() {
        final String givenString = "aaaaaaaaaa";
        final String longestSubstring = "aaaaaaaaa";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue("Longest repeat substring for " + givenString + " is " +longestSubstring,
                suffixTrie.longestRepeat().equals(longestSubstring));
    }

    @Test
    public void longestRepeat_noRepeatedWord() {
        final String givenString = "abcdefg";
        final String longestSubstring = "";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue("Longest repeat substring for " + givenString + " is " +longestSubstring,
                suffixTrie.longestRepeat().equals(longestSubstring));
    }

    @Test
    public void longestRepeat_banana() {
        final String givenString = "banana";
        final String longestSubstring = "ana";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue("Longest repeat substring for " + givenString + " is " +longestSubstring,
                suffixTrie.longestRepeat().equals(longestSubstring));
    }

    @Test
    public void longestRepeat_multipleRepeatedWords() {
        final String givenString = "abcpqrabpqpq";
        final String firstLongestSubstring = "ab";
        final String secondLongestSubstring = "pq";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue("Longest repeat substring for " + givenString + " is " + firstLongestSubstring + " or "
                + secondLongestSubstring, suffixTrie.longestRepeat().equals(firstLongestSubstring)
                || suffixTrie.longestRepeat().equals(secondLongestSubstring));
    }

    @Test
    public void longestCommonSubstring_middleCommon() {
        final String givenString = "xabxac";
        final String findCommonSubstring = "abcabxabcd";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue(suffixTrie.longestSubstring(findCommonSubstring).equals("abxa"));
    }

    @Test
    public void longestCommonSubstring_beginningCommon() {
        final String givenString = "GeeksforGeeks";
        final String findCommonSubstring = "GeeksQuiz";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue(suffixTrie.longestSubstring(findCommonSubstring).equals("Geeks"));
    }

    @Test
    public void longestCommonSubstring_lastCommon() {
        final String givenString = "OldSite:GeeksforGeeks.org";
        final String findCommonSubstring = "NewSite:NotGeeks.org";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue(suffixTrie.longestSubstring(findCommonSubstring).equals("Geeks.org"));
    }

    @Test
    public void longestCommonSubstring_oneLetterCommon() {
        final String givenString = "abcde";
        final String findCommonSubstring = "fghie";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue(suffixTrie.longestSubstring(findCommonSubstring).equals("e"));
    }

    @Test
    public void longestCommonSubstring_noCommon() {
        final String givenString = "pqrst";
        final String findCommonSubstring = "uvwxyz";
        final OptimizedSuffixTrieImpl suffixTrie = new OptimizedSuffixTrieImpl(givenString);
        assertTrue(suffixTrie.longestSubstring(findCommonSubstring).equals(""));
    }
}
