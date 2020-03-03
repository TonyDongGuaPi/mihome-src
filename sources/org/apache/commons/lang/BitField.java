package org.apache.commons.lang;

public class BitField {

    /* renamed from: a  reason: collision with root package name */
    private final int f3355a;
    private final int b;

    public BitField(int i) {
        this.f3355a = i;
        int i2 = 0;
        if (i != 0) {
            while ((i & 1) == 0) {
                i2++;
                i >>= 1;
            }
        }
        this.b = i2;
    }

    public int a(int i) {
        return b(i) >> this.b;
    }

    public short a(short s) {
        return (short) a((int) s);
    }

    public int b(int i) {
        return i & this.f3355a;
    }

    public short b(short s) {
        return (short) b((int) s);
    }

    public boolean c(int i) {
        return (i & this.f3355a) != 0;
    }

    public boolean d(int i) {
        return (i & this.f3355a) == this.f3355a;
    }

    public int a(int i, int i2) {
        return (i & (this.f3355a ^ -1)) | ((i2 << this.b) & this.f3355a);
    }

    public short a(short s, short s2) {
        return (short) a((int) s, (int) s2);
    }

    public int e(int i) {
        return i & (this.f3355a ^ -1);
    }

    public short c(short s) {
        return (short) e(s);
    }

    public byte a(byte b2) {
        return (byte) e(b2);
    }

    public int f(int i) {
        return i | this.f3355a;
    }

    public short d(short s) {
        return (short) f(s);
    }

    public byte b(byte b2) {
        return (byte) f(b2);
    }

    public int a(int i, boolean z) {
        return z ? f(i) : e(i);
    }

    public short a(short s, boolean z) {
        return z ? d(s) : c(s);
    }

    public byte a(byte b2, boolean z) {
        return z ? b(b2) : a(b2);
    }
}
