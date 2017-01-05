package edu.um.optimized_suffix_trie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by dylan on 03/01/2017.
 */
public class OffsetLengthNodeTest {

    private OffsetLengthNode offsetLengthNode;

    @Before
    public void setUp() throws Exception {
        offsetLengthNode = new OffsetLengthNode();
    }

    @Test
    public void offsetLengthNodeTest() {
        final String word = "test";
        final int offset = 1, length = 2;
        final String wordReturned = "es";
        final OffsetLengthKey offsetLengthKey = new OffsetLengthKey(offset, length);
        Assert.assertTrue(wordReturned.equals(offsetLengthNode.offsetLengthToString(word, offsetLengthKey)));
    }
}