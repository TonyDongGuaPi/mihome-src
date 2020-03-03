package com.facebook.react.common;

public class LongArray {
    private static final double INNER_ARRAY_GROWTH_FACTOR = 1.8d;
    private long[] mArray;
    private int mLength = 0;

    public static LongArray createWithInitialCapacity(int i) {
        return new LongArray(i);
    }

    private LongArray(int i) {
        this.mArray = new long[i];
    }

    public void add(long j) {
        growArrayIfNeeded();
        long[] jArr = this.mArray;
        int i = this.mLength;
        this.mLength = i + 1;
        jArr[i] = j;
    }

    public long get(int i) {
        if (i < this.mLength) {
            return this.mArray[i];
        }
        throw new IndexOutOfBoundsException("" + i + " >= " + this.mLength);
    }

    public void set(int i, long j) {
        if (i < this.mLength) {
            this.mArray[i] = j;
            return;
        }
        throw new IndexOutOfBoundsException("" + i + " >= " + this.mLength);
    }

    public int size() {
        return this.mLength;
    }

    public boolean isEmpty() {
        return this.mLength == 0;
    }

    public void dropTail(int i) {
        if (i <= this.mLength) {
            this.mLength -= i;
            return;
        }
        throw new IndexOutOfBoundsException("Trying to drop " + i + " items from array of length " + this.mLength);
    }

    private void growArrayIfNeeded() {
        if (this.mLength == this.mArray.length) {
            double d = (double) this.mLength;
            Double.isNaN(d);
            long[] jArr = new long[Math.max(this.mLength + 1, (int) (d * INNER_ARRAY_GROWTH_FACTOR))];
            System.arraycopy(this.mArray, 0, jArr, 0, this.mLength);
            this.mArray = jArr;
        }
    }
}
