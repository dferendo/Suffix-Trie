package edu.um.NotOptimizedSuffixTrie;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing for SuffixTrieImpl.
 */

public class SuffixTrieImplTest {

    private SuffixTrieImpl suffixTrie;
    private final String WORD_TEST = "hahaa";

    @Before
    public void setUp() {
        suffixTrie = new SuffixTrieImpl(WORD_TEST);
    }

    @Test
    public void substring_correctSubstringFirstPart() {
        final String substring = "ha";
        assertTrue(substring + " is a substring of " + WORD_TEST,
                suffixTrie.substring(substring));
    }

    @Test
    public void substring_correctSubstringLastPart() {
        final String substring = "aa";
        assertTrue(substring + " is a substring of " + WORD_TEST,
                suffixTrie.substring(substring));
    }

    @Test
    public void substring_correctSubstringMiddlePart() {
        final String substring = "aha";
        assertTrue(substring + " is a substring of " + WORD_TEST,
                suffixTrie.substring(substring));
    }

    @Test
    public void substring_incorrectSubstringPattern() {
        final String substring = "aah";
        assertFalse(substring + " is not a substring of " + WORD_TEST,
                suffixTrie.substring(substring));
    }

    @Test
    public void substring_incorrectSubstring_givenStringIsLonger() {
        final String substring = "hahaaa";
        assertFalse(substring + " is not a substring of " + WORD_TEST,
                suffixTrie.substring(substring));
    }

    @Test
    public void substring_incorrectSubstring_letterNotInAlphabet() {
        final String substring = "hacaa";
        assertFalse(substring + " is not a substring of " + WORD_TEST,
                suffixTrie.substring(substring));
    }

    @Test
    public void suffix_correctSuffix_removeFirstLetter() {
        final String suffix = "ahaa";
        assertTrue(suffix + " is a suffix of " + WORD_TEST,
                suffixTrie.suffix(suffix));
    }

    @Test
    public void suffix_correctSuffix_lastTwoLetters() {
        final String suffix = "aa";
        assertTrue(suffix + " is a suffix of " + WORD_TEST,
                suffixTrie.suffix(suffix));
    }

    @Test
    public void suffix_incorrectSuffix_letterNotInAlphabet() {
        final String suffix = "aca";
        assertFalse(suffix + " is not a suffix of " + WORD_TEST,
                suffixTrie.suffix(suffix));
    }

    @Test
    public void suffix_incorrectSuffix_firstFourLetters() {
        final String suffix = "haha";
        assertFalse(suffix + " is not a suffix of " + WORD_TEST,
                suffixTrie.suffix(suffix));
    }

    @Test
    public void count_substringAppearsTwice() {
        final String substring = "ha";
        assertTrue(suffixTrie.count(substring) == 2);
    }

    @Test
    public void count_substringAppearsOne() {
        final String substring = "ah";
        assertTrue(suffixTrie.count(substring) == 1);
    }

    @Test
    public void count_substringAppearNoWhere() {
        final String substring = "aah";
        assertTrue(suffixTrie.count(substring) == 0);
    }

    @Test
    public void longestSubstring_startingFromBeginningOfList() {
        final String substring = "hac";
        final String longestSubstring = "ha";
        assertTrue(suffixTrie.longestSubstring(substring).equals(longestSubstring));
    }

    @Test
    public void longestSubstring_startingFromEndingOfList() {
        final String substring = "chdhaa";
        final String longestSubstring = "haa";
        assertTrue(suffixTrie.longestSubstring(substring).equals(longestSubstring));
    }

    @Test
    public void longestSubstring_startingFromMiddleOfList() {
        final String substring = "nmndahanbch";
        final String longestSubstring = "aha";
        assertTrue(suffixTrie.longestSubstring(substring).equals(longestSubstring));
    }
}