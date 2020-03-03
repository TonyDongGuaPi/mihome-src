package com.xiaomi.zxing.common;

public final class BitSource {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f1647a;
    private int b;
    private int c;

    public BitSource(byte[] bArr) {
        this.f1647a = bArr;
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.b;
    }

    public int a(int i) {
        byte b2;
        if (i < 1 || i > 32 || i > c()) {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        if (this.c > 0) {
            int i2 = 8 - this.c;
            int i3 = i < i2 ? i : i2;
            int i4 = i2 - i3;
            b2 = (((255 >> (8 - i3)) << i4) & this.f1647a[this.b]) >> i4;
            i -= i3;
            this.c += i3;
            if (this.c == 8) {
                this.c = 0;
                this.b++;
            }
        } else {
            b2 = 0;
        }
        if (i <= 0) {
            return b2;
        }
        while (i >= 8) {
            b2 = (b2 << 8) | (this.f1647a[this.b] & 255);
            this.b++;
            i -= 8;
        }
        if (i <= 0) {
            return b2;
        }
        int i5 = 8 - i;
        int i6 = (b2 << i) | ((((255 >> i5) << i5) & this.f1647a[this.b]) >> i5);
        this.c += i;
        return i6;
    }

    public int c() {
        return ((this.f1647a.length - this.b) * 8) - this.c;
    }
}
