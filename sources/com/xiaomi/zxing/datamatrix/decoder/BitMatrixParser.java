package com.xiaomi.zxing.datamatrix.decoder;

import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.common.BitMatrix;

final class BitMatrixParser {

    /* renamed from: a  reason: collision with root package name */
    private final BitMatrix f1662a;
    private final BitMatrix b;
    private final Version c;

    BitMatrixParser(BitMatrix bitMatrix) throws FormatException {
        int g = bitMatrix.g();
        if (g < 8 || g > 144 || (g & 1) != 0) {
            throw FormatException.getFormatInstance();
        }
        this.c = b(bitMatrix);
        this.f1662a = a(bitMatrix);
        this.b = new BitMatrix(this.f1662a.f(), this.f1662a.g());
    }

    /* access modifiers changed from: package-private */
    public Version a() {
        return this.c;
    }

    private static Version b(BitMatrix bitMatrix) throws FormatException {
        return Version.a(bitMatrix.g(), bitMatrix.f());
    }

    /* access modifiers changed from: package-private */
    public byte[] b() throws FormatException {
        byte[] bArr = new byte[this.c.f()];
        int g = this.f1662a.g();
        int f = this.f1662a.f();
        int i = 4;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (true) {
            if (i == g && i2 == 0 && !z) {
                bArr[i3] = (byte) a(g, f);
                i -= 2;
                i2 += 2;
                i3++;
                z = true;
            } else {
                int i4 = g - 2;
                if (i == i4 && i2 == 0 && (f & 3) != 0 && !z2) {
                    bArr[i3] = (byte) b(g, f);
                    i -= 2;
                    i2 += 2;
                    i3++;
                    z2 = true;
                } else if (i == g + 4 && i2 == 2 && (f & 7) == 0 && !z3) {
                    bArr[i3] = (byte) c(g, f);
                    i -= 2;
                    i2 += 2;
                    i3++;
                    z3 = true;
                } else if (i == i4 && i2 == 0 && (f & 7) == 4 && !z4) {
                    bArr[i3] = (byte) d(g, f);
                    i -= 2;
                    i2 += 2;
                    i3++;
                    z4 = true;
                } else {
                    do {
                        if (i < g && i2 >= 0 && !this.b.a(i2, i)) {
                            bArr[i3] = (byte) b(i, i2, g, f);
                            i3++;
                        }
                        i -= 2;
                        i2 += 2;
                        if (i < 0) {
                            break;
                        }
                    } while (i2 < f);
                    int i5 = i + 1;
                    int i6 = i2 + 3;
                    do {
                        if (i5 >= 0 && i6 < f && !this.b.a(i6, i5)) {
                            bArr[i3] = (byte) b(i5, i6, g, f);
                            i3++;
                        }
                        i5 += 2;
                        i6 -= 2;
                        if (i5 >= g) {
                            break;
                        }
                    } while (i6 >= 0);
                    i = i5 + 3;
                    i2 = i6 + 1;
                }
            }
            if (i >= g && i2 >= f) {
                break;
            }
        }
        if (i3 == this.c.f()) {
            return bArr;
        }
        throw FormatException.getFormatInstance();
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i, int i2, int i3, int i4) {
        if (i < 0) {
            i += i3;
            i2 += 4 - ((i3 + 4) & 7);
        }
        if (i2 < 0) {
            i2 += i4;
            i += 4 - ((i4 + 4) & 7);
        }
        this.b.b(i2, i);
        return this.f1662a.a(i2, i);
    }

    /* access modifiers changed from: package-private */
    public int b(int i, int i2, int i3, int i4) {
        int i5 = i - 2;
        int i6 = i2 - 2;
        int i7 = (a(i5, i6, i3, i4) ? 1 : 0) << 1;
        int i8 = i2 - 1;
        if (a(i5, i8, i3, i4)) {
            i7 |= 1;
        }
        int i9 = i7 << 1;
        int i10 = i - 1;
        if (a(i10, i6, i3, i4)) {
            i9 |= 1;
        }
        int i11 = i9 << 1;
        if (a(i10, i8, i3, i4)) {
            i11 |= 1;
        }
        int i12 = i11 << 1;
        if (a(i10, i2, i3, i4)) {
            i12 |= 1;
        }
        int i13 = i12 << 1;
        if (a(i, i6, i3, i4)) {
            i13 |= 1;
        }
        int i14 = i13 << 1;
        if (a(i, i8, i3, i4)) {
            i14 |= 1;
        }
        int i15 = i14 << 1;
        return a(i, i2, i3, i4) ? i15 | 1 : i15;
    }

    /* access modifiers changed from: package-private */
    public int a(int i, int i2) {
        int i3 = i - 1;
        int i4 = (a(i3, 0, i, i2) ? 1 : 0) << 1;
        if (a(i3, 1, i, i2)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (a(i3, 2, i, i2)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (a(0, i2 - 2, i, i2)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        int i8 = i2 - 1;
        if (a(0, i8, i, i2)) {
            i7 |= 1;
        }
        int i9 = i7 << 1;
        if (a(1, i8, i, i2)) {
            i9 |= 1;
        }
        int i10 = i9 << 1;
        if (a(2, i8, i, i2)) {
            i10 |= 1;
        }
        int i11 = i10 << 1;
        return a(3, i8, i, i2) ? i11 | 1 : i11;
    }

    /* access modifiers changed from: package-private */
    public int b(int i, int i2) {
        int i3 = (a(i + -3, 0, i, i2) ? 1 : 0) << 1;
        if (a(i - 2, 0, i, i2)) {
            i3 |= 1;
        }
        int i4 = i3 << 1;
        if (a(i - 1, 0, i, i2)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (a(0, i2 - 4, i, i2)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (a(0, i2 - 3, i, i2)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        if (a(0, i2 - 2, i, i2)) {
            i7 |= 1;
        }
        int i8 = i7 << 1;
        int i9 = i2 - 1;
        if (a(0, i9, i, i2)) {
            i8 |= 1;
        }
        int i10 = i8 << 1;
        return a(1, i9, i, i2) ? i10 | 1 : i10;
    }

    /* access modifiers changed from: package-private */
    public int c(int i, int i2) {
        int i3 = i - 1;
        int i4 = (a(i3, 0, i, i2) ? 1 : 0) << 1;
        int i5 = i2 - 1;
        if (a(i3, i5, i, i2)) {
            i4 |= 1;
        }
        int i6 = i4 << 1;
        int i7 = i2 - 3;
        if (a(0, i7, i, i2)) {
            i6 |= 1;
        }
        int i8 = i6 << 1;
        int i9 = i2 - 2;
        if (a(0, i9, i, i2)) {
            i8 |= 1;
        }
        int i10 = i8 << 1;
        if (a(0, i5, i, i2)) {
            i10 |= 1;
        }
        int i11 = i10 << 1;
        if (a(1, i7, i, i2)) {
            i11 |= 1;
        }
        int i12 = i11 << 1;
        if (a(1, i9, i, i2)) {
            i12 |= 1;
        }
        int i13 = i12 << 1;
        return a(1, i5, i, i2) ? i13 | 1 : i13;
    }

    /* access modifiers changed from: package-private */
    public int d(int i, int i2) {
        int i3 = (a(i + -3, 0, i, i2) ? 1 : 0) << 1;
        if (a(i - 2, 0, i, i2)) {
            i3 |= 1;
        }
        int i4 = i3 << 1;
        if (a(i - 1, 0, i, i2)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (a(0, i2 - 2, i, i2)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        int i7 = i2 - 1;
        if (a(0, i7, i, i2)) {
            i6 |= 1;
        }
        int i8 = i6 << 1;
        if (a(1, i7, i, i2)) {
            i8 |= 1;
        }
        int i9 = i8 << 1;
        if (a(2, i7, i, i2)) {
            i9 |= 1;
        }
        int i10 = i9 << 1;
        return a(3, i7, i, i2) ? i10 | 1 : i10;
    }

    /* access modifiers changed from: package-private */
    public BitMatrix a(BitMatrix bitMatrix) {
        int b2 = this.c.b();
        int c2 = this.c.c();
        if (bitMatrix.g() == b2) {
            int d = this.c.d();
            int e = this.c.e();
            int i = b2 / d;
            int i2 = c2 / e;
            BitMatrix bitMatrix2 = new BitMatrix(i2 * e, i * d);
            for (int i3 = 0; i3 < i; i3++) {
                int i4 = i3 * d;
                for (int i5 = 0; i5 < i2; i5++) {
                    int i6 = i5 * e;
                    for (int i7 = 0; i7 < d; i7++) {
                        int i8 = ((d + 2) * i3) + 1 + i7;
                        int i9 = i4 + i7;
                        for (int i10 = 0; i10 < e; i10++) {
                            if (bitMatrix.a(((e + 2) * i5) + 1 + i10, i8)) {
                                bitMatrix2.b(i6 + i10, i9);
                            }
                        }
                        BitMatrix bitMatrix3 = bitMatrix;
                    }
                    BitMatrix bitMatrix4 = bitMatrix;
                }
                BitMatrix bitMatrix5 = bitMatrix;
            }
            return bitMatrix2;
        }
        throw new IllegalArgumentException("Dimension of bitMarix must match the version size");
    }
}
