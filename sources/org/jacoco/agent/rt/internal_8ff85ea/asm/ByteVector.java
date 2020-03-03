package org.jacoco.agent.rt.internal_8ff85ea.asm;

import com.taobao.weex.el.parse.Operators;

public class ByteVector {

    /* renamed from: a  reason: collision with root package name */
    byte[] f3586a;
    int b;

    public ByteVector() {
        this.f3586a = new byte[64];
    }

    public ByteVector(int i) {
        this.f3586a = new byte[i];
    }

    public ByteVector a(int i) {
        int i2 = this.b;
        int i3 = i2 + 1;
        if (i3 > this.f3586a.length) {
            d(1);
        }
        this.f3586a[i2] = (byte) i;
        this.b = i3;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ByteVector a(int i, int i2) {
        int i3 = this.b;
        if (i3 + 2 > this.f3586a.length) {
            d(2);
        }
        byte[] bArr = this.f3586a;
        int i4 = i3 + 1;
        bArr[i3] = (byte) i;
        bArr[i4] = (byte) i2;
        this.b = i4 + 1;
        return this;
    }

    public ByteVector b(int i) {
        int i2 = this.b;
        if (i2 + 2 > this.f3586a.length) {
            d(2);
        }
        byte[] bArr = this.f3586a;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 8);
        bArr[i3] = (byte) i;
        this.b = i3 + 1;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ByteVector b(int i, int i2) {
        int i3 = this.b;
        if (i3 + 3 > this.f3586a.length) {
            d(3);
        }
        byte[] bArr = this.f3586a;
        int i4 = i3 + 1;
        bArr[i3] = (byte) i;
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i2 >>> 8);
        bArr[i5] = (byte) i2;
        this.b = i5 + 1;
        return this;
    }

    public ByteVector c(int i) {
        int i2 = this.b;
        if (i2 + 4 > this.f3586a.length) {
            d(4);
        }
        byte[] bArr = this.f3586a;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i >>> 16);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i >>> 8);
        bArr[i5] = (byte) i;
        this.b = i5 + 1;
        return this;
    }

    public ByteVector a(long j) {
        int i = this.b;
        if (i + 8 > this.f3586a.length) {
            d(8);
        }
        byte[] bArr = this.f3586a;
        int i2 = (int) (j >>> 32);
        int i3 = i + 1;
        bArr[i] = (byte) (i2 >>> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i2 >>> 16);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i2 >>> 8);
        int i6 = i5 + 1;
        bArr[i5] = (byte) i2;
        int i7 = (int) j;
        int i8 = i6 + 1;
        bArr[i6] = (byte) (i7 >>> 24);
        int i9 = i8 + 1;
        bArr[i8] = (byte) (i7 >>> 16);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i7 >>> 8);
        bArr[i10] = (byte) i7;
        this.b = i10 + 1;
        return this;
    }

    public ByteVector a(String str) {
        int length = str.length();
        if (length <= 65535) {
            int i = this.b;
            if (i + 2 + length > this.f3586a.length) {
                d(length + 2);
            }
            byte[] bArr = this.f3586a;
            int i2 = i + 1;
            bArr[i] = (byte) (length >>> 8);
            int i3 = i2 + 1;
            bArr[i2] = (byte) length;
            int i4 = 0;
            while (i4 < length) {
                char charAt = str.charAt(i4);
                if (charAt < 1 || charAt > 127) {
                    this.b = i3;
                    return a(str, i4, 65535);
                }
                bArr[i3] = (byte) charAt;
                i4++;
                i3++;
            }
            this.b = i3;
            return this;
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public ByteVector a(String str, int i, int i2) {
        int i3;
        int length = str.length();
        int i4 = i;
        int i5 = i4;
        while (i4 < length) {
            char charAt = str.charAt(i4);
            i5 = (charAt < 1 || charAt > 127) ? charAt > 2047 ? i5 + 3 : i5 + 2 : i5 + 1;
            i4++;
        }
        if (i5 <= i2) {
            int i6 = (this.b - i) - 2;
            if (i6 >= 0) {
                this.f3586a[i6] = (byte) (i5 >>> 8);
                this.f3586a[i6 + 1] = (byte) i5;
            }
            if ((this.b + i5) - i > this.f3586a.length) {
                d(i5 - i);
            }
            int i7 = this.b;
            while (i < length) {
                char charAt2 = str.charAt(i);
                if (charAt2 >= 1 && charAt2 <= 127) {
                    i3 = i7 + 1;
                    this.f3586a[i7] = (byte) charAt2;
                } else if (charAt2 > 2047) {
                    int i8 = i7 + 1;
                    this.f3586a[i7] = (byte) (((charAt2 >> 12) & 15) | 224);
                    int i9 = i8 + 1;
                    this.f3586a[i8] = (byte) (((charAt2 >> 6) & 63) | 128);
                    i3 = i9 + 1;
                    this.f3586a[i9] = (byte) ((charAt2 & Operators.CONDITION_IF) | 128);
                } else {
                    int i10 = i7 + 1;
                    this.f3586a[i7] = (byte) (((charAt2 >> 6) & 31) | 192);
                    this.f3586a[i10] = (byte) ((charAt2 & Operators.CONDITION_IF) | 128);
                    i7 = i10 + 1;
                    i++;
                }
                i7 = i3;
                i++;
            }
            this.b = i7;
            return this;
        }
        throw new IllegalArgumentException();
    }

    public ByteVector a(byte[] bArr, int i, int i2) {
        if (this.b + i2 > this.f3586a.length) {
            d(i2);
        }
        if (bArr != null) {
            System.arraycopy(bArr, i, this.f3586a, this.b, i2);
        }
        this.b += i2;
        return this;
    }

    private void d(int i) {
        int length = this.f3586a.length * 2;
        int i2 = i + this.b;
        if (length > i2) {
            i2 = length;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.f3586a, 0, bArr, 0, this.b);
        this.f3586a = bArr;
    }
}
