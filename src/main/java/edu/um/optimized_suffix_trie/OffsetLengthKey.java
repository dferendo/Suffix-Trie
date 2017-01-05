package edu.um.optimized_suffix_trie;

/**
 * Holds the offset and length as a Pair. Since TreeMap is being used, Comparable is needed
 * to sort any key.
 */
public class OffsetLengthKey implements Comparable<OffsetLengthKey> {
    private final Integer offset;
    private final Integer length;

    public OffsetLengthKey(Integer offset, Integer length) {
        this.offset = offset;
        this.length = length;
    }

    /**
     * CompareTo is override to be able to compare paired keys in a Treesort.
     * @param o Other Key
     * @return 1 if this is greater, -1 otherwise
     */
    @Override
    public int compareTo(OffsetLengthKey o) {
        if (this.offset > o.offset) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OffsetLengthKey)) return false;
        OffsetLengthKey key = (OffsetLengthKey) o;
        return offset.equals(key.offset) && length.equals(key.length);
    }

    @Override
    public int hashCode() {
        int result = offset;
        // Multiple by a prime number to make sure there is a difference
        result = 23 * result + length;
        return result;
    }

    /**
     * Used to print key. Will be mostly used for show function.
     * @return String containing the key elements.
     */
    @Override
    public String toString() {
        return "[" + offset + ", " + length + "]";
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLength() {
        return length;
    }
}
