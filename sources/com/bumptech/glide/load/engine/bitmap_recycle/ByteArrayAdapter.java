package com.bumptech.glide.load.engine.bitmap_recycle;

public final class ByteArrayAdapter implements ArrayAdapterInterface<byte[]> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4891a = "ByteArrayPool";

    public String a() {
        return f4891a;
    }

    public int b() {
        return 1;
    }

    public int a(byte[] bArr) {
        return bArr.length;
    }

    /* renamed from: b */
    public byte[] a(int i) {
        return new byte[i];
    }
}
