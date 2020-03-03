package com.xiaomi.zxing.common;

import java.util.Arrays;

public final class BitMatrix implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    private final int f1646a;
    private final int b;
    private final int c;
    private final int[] d;

    public BitMatrix(int i) {
        this(i, i);
    }

    public BitMatrix(int i, int i2) {
        if (i < 1 || i2 < 1) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.f1646a = i;
        this.b = i2;
        this.c = (i + 31) / 32;
        this.d = new int[(this.c * i2)];
    }

    private BitMatrix(int i, int i2, int i3, int[] iArr) {
        this.f1646a = i;
        this.b = i2;
        this.c = i3;
        this.d = iArr;
    }

    public static BitMatrix a(String str, String str2, String str3) {
        if (str != null) {
            boolean[] zArr = new boolean[str.length()];
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = -1;
            int i5 = 0;
            while (i < str.length()) {
                if (str.charAt(i) == 10 || str.charAt(i) == 13) {
                    if (i2 > i3) {
                        if (i4 == -1) {
                            i4 = i2 - i3;
                        } else if (i2 - i3 != i4) {
                            throw new IllegalArgumentException("row lengths do not match");
                        }
                        i5++;
                        i3 = i2;
                    }
                    i++;
                } else if (str.substring(i, str2.length() + i).equals(str2)) {
                    i += str2.length();
                    zArr[i2] = true;
                    i2++;
                } else if (str.substring(i, str3.length() + i).equals(str3)) {
                    i += str3.length();
                    zArr[i2] = false;
                    i2++;
                } else {
                    throw new IllegalArgumentException("illegal character encountered: " + str.substring(i));
                }
            }
            if (i2 > i3) {
                if (i4 == -1) {
                    i4 = i2 - i3;
                } else if (i2 - i3 != i4) {
                    throw new IllegalArgumentException("row lengths do not match");
                }
                i5++;
            }
            BitMatrix bitMatrix = new BitMatrix(i4, i5);
            for (int i6 = 0; i6 < i2; i6++) {
                if (zArr[i6]) {
                    bitMatrix.b(i6 % i4, i6 / i4);
                }
            }
            return bitMatrix;
        }
        throw new IllegalArgumentException();
    }

    public boolean a(int i, int i2) {
        return ((this.d[(i2 * this.c) + (i / 32)] >>> (i & 31)) & 1) != 0;
    }

    public void b(int i, int i2) {
        int i3 = (i2 * this.c) + (i / 32);
        int[] iArr = this.d;
        iArr[i3] = (1 << (i & 31)) | iArr[i3];
    }

    public void c(int i, int i2) {
        int i3 = (i2 * this.c) + (i / 32);
        int[] iArr = this.d;
        iArr[i3] = ((1 << (i & 31)) ^ -1) & iArr[i3];
    }

    public void d(int i, int i2) {
        int i3 = (i2 * this.c) + (i / 32);
        int[] iArr = this.d;
        iArr[i3] = (1 << (i & 31)) ^ iArr[i3];
    }

    public void a(BitMatrix bitMatrix) {
        if (this.f1646a == bitMatrix.f() && this.b == bitMatrix.g() && this.c == bitMatrix.h()) {
            BitArray bitArray = new BitArray((this.f1646a / 32) + 1);
            for (int i = 0; i < this.b; i++) {
                int i2 = this.c * i;
                int[] d2 = bitMatrix.a(i, bitArray).d();
                for (int i3 = 0; i3 < this.c; i3++) {
                    int[] iArr = this.d;
                    int i4 = i2 + i3;
                    iArr[i4] = iArr[i4] ^ d2[i3];
                }
            }
            return;
        }
        throw new IllegalArgumentException("input matrix dimensions do not match");
    }

    public void a() {
        int length = this.d.length;
        for (int i = 0; i < length; i++) {
            this.d[i] = 0;
        }
    }

    public void a(int i, int i2, int i3, int i4) {
        if (i2 < 0 || i < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        } else if (i4 < 1 || i3 < 1) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        } else {
            int i5 = i3 + i;
            int i6 = i4 + i2;
            if (i6 > this.b || i5 > this.f1646a) {
                throw new IllegalArgumentException("The region must fit inside the matrix");
            }
            while (i2 < i6) {
                int i7 = this.c * i2;
                for (int i8 = i; i8 < i5; i8++) {
                    int[] iArr = this.d;
                    int i9 = (i8 / 32) + i7;
                    iArr[i9] = iArr[i9] | (1 << (i8 & 31));
                }
                i2++;
            }
        }
    }

    public BitArray a(int i, BitArray bitArray) {
        if (bitArray == null || bitArray.a() < this.f1646a) {
            bitArray = new BitArray(this.f1646a);
        } else {
            bitArray.c();
        }
        int i2 = i * this.c;
        for (int i3 = 0; i3 < this.c; i3++) {
            bitArray.a(i3 * 32, this.d[i2 + i3]);
        }
        return bitArray;
    }

    public void b(int i, BitArray bitArray) {
        System.arraycopy(bitArray.d(), 0, this.d, i * this.c, this.c);
    }

    public void b() {
        int f = f();
        int g = g();
        BitArray bitArray = new BitArray(f);
        BitArray bitArray2 = new BitArray(f);
        for (int i = 0; i < (g + 1) / 2; i++) {
            bitArray = a(i, bitArray);
            int i2 = (g - 1) - i;
            bitArray2 = a(i2, bitArray2);
            bitArray.e();
            bitArray2.e();
            b(i, bitArray2);
            b(i2, bitArray);
        }
    }

    public int[] c() {
        int i = this.f1646a;
        int i2 = -1;
        int i3 = this.b;
        int i4 = -1;
        int i5 = i;
        int i6 = 0;
        while (i6 < this.b) {
            int i7 = i4;
            int i8 = i2;
            int i9 = i5;
            for (int i10 = 0; i10 < this.c; i10++) {
                int i11 = this.d[(this.c * i6) + i10];
                if (i11 != 0) {
                    if (i6 < i3) {
                        i3 = i6;
                    }
                    if (i6 > i7) {
                        i7 = i6;
                    }
                    int i12 = i10 * 32;
                    int i13 = 31;
                    if (i12 < i9) {
                        int i14 = 0;
                        while ((i11 << (31 - i14)) == 0) {
                            i14++;
                        }
                        int i15 = i14 + i12;
                        if (i15 < i9) {
                            i9 = i15;
                        }
                    }
                    if (i12 + 31 > i8) {
                        while ((i11 >>> i13) == 0) {
                            i13--;
                        }
                        int i16 = i12 + i13;
                        if (i16 > i8) {
                            i8 = i16;
                        }
                    }
                }
            }
            i6++;
            i5 = i9;
            i2 = i8;
            i4 = i7;
        }
        int i17 = i2 - i5;
        int i18 = i4 - i3;
        if (i17 < 0 || i18 < 0) {
            return null;
        }
        return new int[]{i5, i3, i17, i18};
    }

    public int[] d() {
        int i = 0;
        while (i < this.d.length && this.d[i] == 0) {
            i++;
        }
        if (i == this.d.length) {
            return null;
        }
        int i2 = i / this.c;
        int i3 = (i % this.c) * 32;
        int i4 = 0;
        while ((this.d[i] << (31 - i4)) == 0) {
            i4++;
        }
        return new int[]{i3 + i4, i2};
    }

    public int[] e() {
        int length = this.d.length - 1;
        while (length >= 0 && this.d[length] == 0) {
            length--;
        }
        if (length < 0) {
            return null;
        }
        int i = length / this.c;
        int i2 = (length % this.c) * 32;
        int i3 = 31;
        while ((this.d[length] >>> i3) == 0) {
            i3--;
        }
        return new int[]{i2 + i3, i};
    }

    public int f() {
        return this.f1646a;
    }

    public int g() {
        return this.b;
    }

    public int h() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BitMatrix)) {
            return false;
        }
        BitMatrix bitMatrix = (BitMatrix) obj;
        if (this.f1646a == bitMatrix.f1646a && this.b == bitMatrix.b && this.c == bitMatrix.c && Arrays.equals(this.d, bitMatrix.d)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.f1646a * 31) + this.f1646a) * 31) + this.b) * 31) + this.c) * 31) + Arrays.hashCode(this.d);
    }

    public String toString() {
        return a("X ", "  ");
    }

    public String a(String str, String str2) {
        return b(str, str2, "\n");
    }

    @Deprecated
    public String b(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(this.b * (this.f1646a + 1));
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.f1646a; i2++) {
                sb.append(a(i2, i) ? str : str2);
            }
            sb.append(str3);
        }
        return sb.toString();
    }

    /* renamed from: i */
    public BitMatrix clone() {
        return new BitMatrix(this.f1646a, this.b, this.c, (int[]) this.d.clone());
    }
}
