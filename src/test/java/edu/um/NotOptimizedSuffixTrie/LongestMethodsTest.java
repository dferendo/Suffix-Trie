package edu.um.NotOptimizedSuffixTrie;

import edu.um.SuffixTrie.SuffixTrie;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by dylan on 03/12/2016.
 * Examples taken from
 * @see <a href="http://www.geeksforgeeks.org/suffix-tree-application-3-longest-repeated-substring/">
 *     Examples of longestRepeat</a>
 * and also from
 * @see <a href="http://www.geeksforgeeks.org/suffix-tree-application-5-longest-common-substring-2/">
 *     Examples of longestSubstring</a>
 * Taken on 4th December 2016.
 */

public class LongestMethodsTest {

    @Test
    public void longestRepeat_geeksForGeeks() {
        final String givenString = "geeksforgeeks";
        final String longestSubstring = "geeks";
        final SuffixTrie suffixTrie = new SuffixTrieImpl(givenString);
        assertTrue("Longest repeat substring for " + givenString + " is " +longestSubstring,
                suffixTrie.longestRepeat().equals(longestSubstring));
    }

    @Test
    public void longestRepeat_multipleSameLetterWord() {
        final String givenString = "aaaaaaaaaa";
        final String longestSubstring = "aaaaaaaaa";
        final SuffixTrie suffixTrie = new SuffixTrieImpl(givenString);
        assertTrue("Longest repeat substring for " + givenString + " is" +longestSubstring,
                suffixTrie.longestRepeat().equals(longestSubstring));
    }

    @Test
    public void longestRepeat_noRepeatedWord() {
        final String givenString = "abcdefg";
        final String longestSubstring = "";
        final SuffixTrie suffixTrie = new SuffixTrieImpl(givenString);
        assertTrue("Longest repeat substring for " + givenString + " is" +longestSubstring,
                suffixTrie.longestRepeat().equals(longestSubstring));
    }

    @Test
    public void longestRepeat_banana() {
        final String givenString = "banana";
        final String longestSubstring = "ana";
        final SuffixTrie suffixTrie = new SuffixTrieImpl(givenString);
        assertTrue("Longest repeat substring for " + givenString + " is" +longestSubstring,
                suffixTrie.longestRepeat().equals(longestSubstring));
    }

    @Test
    public void longestRepeat_multipleRepeatedWords() {
        final String givenString = "abcpqrabpqpq";
        final String firstLongestSubstring = "ab";
        final String secondLongestSubstring = "pq";
        final SuffixTrie suffixTrie = new SuffixTrieImpl(givenString);
        assertTrue("Longest repeat substring for " + givenString + " is" + firstLongestSubstring + " or "
                + secondLongestSubstring, suffixTrie.longestRepeat().equals(firstLongestSubstring)
                || suffixTrie.longestRepeat().equals(secondLongestSubstring));
    }

    @Test
    public void longestCommonSubstring_middleCommon() {
        final String givenString = "xabxac";
        final String findCommonSubstring = "abcabxabcd";
        final SuffixTrie suffixTrie = new SuffixTrieImpl(givenString);
        assertTrue(suffixTrie.longestSubstring(findCommonSubstring).equals("abxa"));
    }

    @Test
    public void longestCommonSubstring_beginningCommon() {
        final String givenString = "GeeksforGeeks";
        final String findCommonSubstring = "GeeksQuiz";
        final SuffixTrie suffixTrie = new SuffixTrieImpl(givenString);
        assertTrue(suffixTrie.longestSubstring(findCommonSubstring).equals("Geeks"));
    }

    @Test
    public void longestCommonSubstring_lastCommon() {
        final String givenString = "OldSite:GeeksforGeeks.org";
        final String findCommonSubstring = "NewSite:NotGeeks.org";
        final SuffixTrie suffixTrie = new SuffixTrieImpl(givenString);
        assertTrue(suffixTrie.longestSubstring(findCommonSubstring).equals("Geeks.org"));
    }

    @Test
    public void longestCommonSubstring_oneLetterCommon() {
        final String givenString = "abcde";
        final String findCommonSubstring = "fghie";
        final SuffixTrie suffixTrie = new SuffixTrieImpl(givenString);
        assertTrue(suffixTrie.longestSubstring(findCommonSubstring).equals("e"));
    }

    @Test
    public void longestCommonSubstring_noCommon() {
        final String givenString = "pqrst";
        final String findCommonSubstring = "uvwxyz";
        final SuffixTrie suffixTrie = new SuffixTrieImpl(givenString);
        assertTrue(suffixTrie.longestSubstring(findCommonSubstring).equals(""));
    }
}
