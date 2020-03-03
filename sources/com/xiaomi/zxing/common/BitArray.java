package com.xiaomi.zxing.common;

import java.util.Arrays;

public final class BitArray implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    private int[] f1645a;
    private int b;

    public BitArray() {
        this.b = 0;
        this.f1645a = new int[1];
    }

    public BitArray(int i) {
        this.b = i;
        this.f1645a = g(i);
    }

    BitArray(int[] iArr, int i) {
        this.f1645a = iArr;
        this.b = i;
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return (this.b + 7) / 8;
    }

    private void f(int i) {
        if (i > this.f1645a.length * 32) {
            int[] g = g(i);
            System.arraycopy(this.f1645a, 0, g, 0, this.f1645a.length);
            this.f1645a = g;
        }
    }

    public boolean a(int i) {
        return ((1 << (i & 31)) & this.f1645a[i / 32]) != 0;
    }

    public void b(int i) {
        int[] iArr = this.f1645a;
        int i2 = i / 32;
        iArr[i2] = (1 << (i & 31)) | iArr[i2];
    }

    public void c(int i) {
        int[] iArr = this.f1645a;
        int i2 = i / 32;
        iArr[i2] = (1 << (i & 31)) ^ iArr[i2];
    }

    public int d(int i) {
        if (i >= this.b) {
            return this.b;
        }
        int i2 = i / 32;
        int i3 = (((1 << (i & 31)) - 1) ^ -1) & this.f1645a[i2];
        while (i3 == 0) {
            i2++;
            if (i2 == this.f1645a.length) {
                return this.b;
            }
            i3 = this.f1645a[i2];
        }
        int numberOfTrailingZeros = (i2 * 32) + Integer.numberOfTrailingZeros(i3);
        return numberOfTrailingZeros > this.b ? this.b : numberOfTrailingZeros;
    }

    public int e(int i) {
        if (i >= this.b) {
            return this.b;
        }
        int i2 = i / 32;
        int i3 = (((1 << (i & 31)) - 1) ^ -1) & (this.f1645a[i2] ^ -1);
        while (i3 == 0) {
            i2++;
            if (i2 == this.f1645a.length) {
                return this.b;
            }
            i3 = this.f1645a[i2] ^ -1;
        }
        int numberOfTrailingZeros = (i2 * 32) + Integer.numberOfTrailingZeros(i3);
        return numberOfTrailingZeros > this.b ? this.b : numberOfTrailingZeros;
    }

    public void a(int i, int i2) {
        this.f1645a[i / 32] = i2;
    }

    public void b(int i, int i2) {
        if (i2 < i || i < 0 || i2 > this.b) {
            throw new IllegalArgumentException();
        } else if (i2 != i) {
            int i3 = i2 - 1;
            int i4 = i / 32;
            int i5 = i3 / 32;
            int i6 = i4;
            while (i6 <= i5) {
                int i7 = 31;
                int i8 = i6 > i4 ? 0 : i & 31;
                if (i6 >= i5) {
                    i7 = 31 & i3;
                }
                int i9 = (2 << i7) - (1 << i8);
                int[] iArr = this.f1645a;
                iArr[i6] = i9 | iArr[i6];
                i6++;
            }
        }
    }

    public void c() {
        int length = this.f1645a.length;
        for (int i = 0; i < length; i++) {
            this.f1645a[i] = 0;
        }
    }

    public boolean a(int i, int i2, boolean z) {
        if (i2 < i || i < 0 || i2 > this.b) {
            throw new IllegalArgumentException();
        } else if (i2 == i) {
            return true;
        } else {
            int i3 = i2 - 1;
            int i4 = i / 32;
            int i5 = i3 / 32;
            int i6 = i4;
            while (i6 <= i5) {
                int i7 = 31;
                int i8 = i6 > i4 ? 0 : i & 31;
                if (i6 >= i5) {
                    i7 = 31 & i3;
                }
                int i9 = (2 << i7) - (1 << i8);
                int i10 = this.f1645a[i6] & i9;
                if (!z) {
                    i9 = 0;
                }
                if (i10 != i9) {
                    return false;
                }
                i6++;
            }
            return true;
        }
    }

    public void a(boolean z) {
        f(this.b + 1);
        if (z) {
            int[] iArr = this.f1645a;
            int i = this.b / 32;
            iArr[i] = iArr[i] | (1 << (this.b & 31));
        }
        this.b++;
    }

    public void c(int i, int i2) {
        if (i2 < 0 || i2 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        f(this.b + i2);
        while (i2 > 0) {
            boolean z = true;
            if (((i >> (i2 - 1)) & 1) != 1) {
                z = false;
            }
            a(z);
            i2--;
        }
    }

    public void a(BitArray bitArray) {
        int i = bitArray.b;
        f(this.b + i);
        for (int i2 = 0; i2 < i; i2++) {
            a(bitArray.a(i2));
        }
    }

    public void b(BitArray bitArray) {
        if (this.b == bitArray.b) {
            for (int i = 0; i < this.f1645a.length; i++) {
                int[] iArr = this.f1645a;
                iArr[i] = iArr[i] ^ bitArray.f1645a[i];
            }
            return;
        }
        throw new IllegalArgumentException("Sizes don't match");
    }

    public void a(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        int i5 = 0;
        while (i5 < i3) {
            int i6 = i4;
            int i7 = 0;
            for (int i8 = 0; i8 < 8; i8++) {
                if (a(i6)) {
                    i7 |= 1 << (7 - i8);
                }
                i6++;
            }
            bArr[i2 + i5] = (byte) i7;
            i5++;
            i4 = i6;
        }
    }

    public int[] d() {
        return this.f1645a;
    }

    public void e() {
        int[] iArr = new int[this.f1645a.length];
        int i = (this.b - 1) / 32;
        int i2 = i + 1;
        for (int i3 = 0; i3 < i2; i3++) {
            long j = (long) this.f1645a[i3];
            long j2 = ((j & 1431655765) << 1) | ((j >> 1) & 1431655765);
            long j3 = ((j2 & 858993459) << 2) | ((j2 >> 2) & 858993459);
            long j4 = ((j3 & 252645135) << 4) | ((j3 >> 4) & 252645135);
            long j5 = ((j4 & 16711935) << 8) | ((j4 >> 8) & 16711935);
            iArr[i - i3] = (int) (((j5 & 65535) << 16) | ((j5 >> 16) & 65535));
        }
        int i4 = i2 * 32;
        if (this.b != i4) {
            int i5 = i4 - this.b;
            int i6 = iArr[0] >>> i5;
            for (int i7 = 1; i7 < i2; i7++) {
                int i8 = iArr[i7];
                iArr[i7 - 1] = i6 | (i8 << (32 - i5));
                i6 = i8 >>> i5;
            }
            iArr[i2 - 1] = i6;
        }
        this.f1645a = iArr;
    }

    private static int[] g(int i) {
        return new int[((i + 31) / 32)];
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BitArray)) {
            return false;
        }
        BitArray bitArray = (BitArray) obj;
        if (this.b != bitArray.b || !Arrays.equals(this.f1645a, bitArray.f1645a)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.b * 31) + Arrays.hashCode(this.f1645a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.b);
        for (int i = 0; i < this.b; i++) {
            if ((i & 7) == 0) {
                sb.append(' ');
            }
            sb.append(a(i) ? 'X' : '.');
        }
        return sb.toString();
    }

    /* renamed from: f */
    public BitArray clone() {
        return new BitArray((int[]) this.f1645a.clone(), this.b);
    }
}
