package com.bumptech.glide.load.engine.bitmap_recycle;

public final class IntegerArrayAdapter implements ArrayAdapterInterface<int[]> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4894a = "IntegerArrayPool";

    public String a() {
        return f4894a;
    }

    public int b() {
        return 4;
    }

    public int a(int[] iArr) {
        return iArr.length;
    }

    /* renamed from: b */
    public int[] a(int i) {
        return new int[i];
    }
}
