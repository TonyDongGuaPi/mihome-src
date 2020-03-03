package com.xiaomi.push;

public final class jt extends ju {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f12834a;
    private int b;
    private int c;

    public int a(byte[] bArr, int i, int i2) {
        int c2 = c();
        if (i2 > c2) {
            i2 = c2;
        }
        if (i2 > 0) {
            System.arraycopy(this.f12834a, this.b, bArr, i, i2);
            a(i2);
        }
        return i2;
    }

    public void a(int i) {
        this.b += i;
    }

    public void a(byte[] bArr) {
        c(bArr, 0, bArr.length);
    }

    public byte[] a() {
        return this.f12834a;
    }

    public int b() {
        return this.b;
    }

    public void b(byte[] bArr, int i, int i2) {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    public int c() {
        return this.c - this.b;
    }

    public void c(byte[] bArr, int i, int i2) {
        this.f12834a = bArr;
        this.b = i;
        this.c = i + i2;
    }
}
