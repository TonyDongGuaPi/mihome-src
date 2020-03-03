package com.h6ah4i.android.widget.advrecyclerview.expandable;

class ExpandableAdapterHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final long f5713a = -1;
    static final int b = Integer.MIN_VALUE;
    private static final long c = 4294967295L;
    private static final long d = 2147483647L;

    public static int a(long j) {
        return (int) (j >>> 32);
    }

    public static long a(int i) {
        return (((long) i) & 4294967295L) | -4294967296L;
    }

    public static long a(int i, int i2) {
        return (((long) i) & 4294967295L) | (((long) i2) << 32);
    }

    public static long a(long j, long j2) {
        return ((j & d) << 32) | (j2 & 4294967295L);
    }

    public static int b(long j) {
        return (int) (j & 4294967295L);
    }

    public static boolean b(int i) {
        return (i & Integer.MIN_VALUE) != 0;
    }

    public static int c(int i) {
        return i & Integer.MAX_VALUE;
    }

    public static long c(long j) {
        return ((j & d) << 32) | 4294967295L;
    }

    public static int d(int i) {
        return i & Integer.MAX_VALUE;
    }

    private ExpandableAdapterHelper() {
    }
}
