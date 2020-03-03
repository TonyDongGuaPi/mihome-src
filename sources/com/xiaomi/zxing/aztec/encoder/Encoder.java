package com.xiaomi.zxing.aztec.encoder;

import com.xiaomi.zxing.common.BitArray;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.common.reedsolomon.GenericGF;
import com.xiaomi.zxing.common.reedsolomon.ReedSolomonEncoder;

public final class Encoder {

    /* renamed from: a  reason: collision with root package name */
    public static final int f1640a = 33;
    public static final int b = 0;
    private static final int c = 32;
    private static final int d = 4;
    private static final int[] e = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private static int a(int i, boolean z) {
        return ((z ? 88 : 112) + (i * 16)) * i;
    }

    private Encoder() {
    }

    public static AztecCode a(byte[] bArr) {
        return a(bArr, 33, 0);
    }

    public static AztecCode a(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        boolean z;
        BitArray bitArray;
        int i6;
        BitArray a2 = new HighLevelEncoder(bArr).a();
        int a3 = ((a2.a() * i) / 100) + 11;
        int a4 = a2.a() + a3;
        int i7 = 32;
        int i8 = 0;
        int i9 = 1;
        if (i2 != 0) {
            z = i2 < 0;
            i4 = Math.abs(i2);
            if (z) {
                i7 = 4;
            }
            if (i4 <= i7) {
                int a5 = a(i4, z);
                i5 = e[i4];
                int i10 = a5 - (a5 % i5);
                bitArray = a(a2, i5);
                if (bitArray.a() + a3 > i10) {
                    throw new IllegalArgumentException("Data to large for user specified layer");
                } else if (!z || bitArray.a() <= i5 * 64) {
                    i3 = a5;
                } else {
                    throw new IllegalArgumentException("Data to large for user specified layer");
                }
            } else {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", new Object[]{Integer.valueOf(i2)}));
            }
        } else {
            BitArray bitArray2 = null;
            int i11 = 0;
            int i12 = 0;
            while (i11 <= 32) {
                boolean z2 = i11 <= 3;
                int i13 = z2 ? i11 + 1 : i11;
                i3 = a(i13, z2);
                if (a4 <= i3) {
                    if (i12 != e[i13]) {
                        i12 = e[i13];
                        bitArray2 = a(a2, i12);
                    }
                    int i14 = i3 - (i3 % i12);
                    if ((!z2 || bitArray2.a() <= i12 * 64) && bitArray2.a() + a3 <= i14) {
                        i5 = i12;
                        bitArray = bitArray2;
                        z = z2;
                        i4 = i13;
                    }
                }
                i11++;
                i8 = 0;
                i9 = 1;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        BitArray a6 = a(bitArray, i3, i5);
        int a7 = bitArray.a() / i5;
        BitArray a8 = a(z, i4, a7);
        int i15 = (z ? 11 : 14) + (i4 * 4);
        int[] iArr = new int[i15];
        int i16 = 2;
        if (z) {
            for (int i17 = 0; i17 < iArr.length; i17++) {
                iArr[i17] = i17;
            }
            i6 = i15;
        } else {
            int i18 = i15 / 2;
            i6 = i15 + 1 + (((i18 - 1) / 15) * 2);
            int i19 = i6 / 2;
            for (int i20 = 0; i20 < i18; i20++) {
                int i21 = (i20 / 15) + i20;
                iArr[(i18 - i20) - 1] = (i19 - i21) - 1;
                iArr[i18 + i20] = i21 + i19 + i9;
            }
        }
        BitMatrix bitMatrix = new BitMatrix(i6);
        int i22 = 0;
        int i23 = 0;
        while (i22 < i4) {
            int i24 = ((i4 - i22) * 4) + (z ? 9 : 12);
            int i25 = 0;
            while (i25 < i24) {
                int i26 = i25 * 2;
                while (i8 < i16) {
                    if (a6.a(i23 + i26 + i8)) {
                        int i27 = i22 * 2;
                        bitMatrix.b(iArr[i27 + i8], iArr[i27 + i25]);
                    }
                    if (a6.a((i24 * 2) + i23 + i26 + i8)) {
                        int i28 = i22 * 2;
                        bitMatrix.b(iArr[i28 + i25], iArr[((i15 - 1) - i28) - i8]);
                    }
                    if (a6.a((i24 * 4) + i23 + i26 + i8)) {
                        int i29 = (i15 - 1) - (i22 * 2);
                        bitMatrix.b(iArr[i29 - i8], iArr[i29 - i25]);
                    }
                    if (a6.a((i24 * 6) + i23 + i26 + i8)) {
                        int i30 = i22 * 2;
                        bitMatrix.b(iArr[((i15 - 1) - i30) - i25], iArr[i30 + i8]);
                    }
                    i8++;
                    i16 = 2;
                }
                i25++;
                i8 = 0;
                i16 = 2;
            }
            i23 += i24 * 8;
            i22++;
            i8 = 0;
            i16 = 2;
        }
        a(bitMatrix, z, i6, a8);
        if (z) {
            a(bitMatrix, i6 / 2, 5);
        } else {
            int i31 = i6 / 2;
            a(bitMatrix, i31, 7);
            int i32 = 0;
            int i33 = 0;
            while (i32 < (i15 / 2) - 1) {
                for (int i34 = i31 & 1; i34 < i6; i34 += 2) {
                    int i35 = i31 - i33;
                    bitMatrix.b(i35, i34);
                    int i36 = i31 + i33;
                    bitMatrix.b(i36, i34);
                    bitMatrix.b(i34, i35);
                    bitMatrix.b(i34, i36);
                }
                i32 += 15;
                i33 += 16;
            }
        }
        AztecCode aztecCode = new AztecCode();
        aztecCode.a(z);
        aztecCode.a(i6);
        aztecCode.b(i4);
        aztecCode.c(a7);
        aztecCode.a(bitMatrix);
        return aztecCode;
    }

    private static void a(BitMatrix bitMatrix, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3 += 2) {
            int i4 = i - i3;
            int i5 = i4;
            while (true) {
                int i6 = i + i3;
                if (i5 > i6) {
                    break;
                }
                bitMatrix.b(i5, i4);
                bitMatrix.b(i5, i6);
                bitMatrix.b(i4, i5);
                bitMatrix.b(i6, i5);
                i5++;
            }
        }
        int i7 = i - i2;
        bitMatrix.b(i7, i7);
        int i8 = i7 + 1;
        bitMatrix.b(i8, i7);
        bitMatrix.b(i7, i8);
        int i9 = i + i2;
        bitMatrix.b(i9, i7);
        bitMatrix.b(i9, i8);
        bitMatrix.b(i9, i9 - 1);
    }

    static BitArray a(boolean z, int i, int i2) {
        BitArray bitArray = new BitArray();
        if (z) {
            bitArray.c(i - 1, 2);
            bitArray.c(i2 - 1, 6);
            return a(bitArray, 28, 4);
        }
        bitArray.c(i - 1, 5);
        bitArray.c(i2 - 1, 11);
        return a(bitArray, 40, 4);
    }

    private static void a(BitMatrix bitMatrix, boolean z, int i, BitArray bitArray) {
        int i2 = i / 2;
        int i3 = 0;
        if (z) {
            while (i3 < 7) {
                int i4 = (i2 - 3) + i3;
                if (bitArray.a(i3)) {
                    bitMatrix.b(i4, i2 - 5);
                }
                if (bitArray.a(i3 + 7)) {
                    bitMatrix.b(i2 + 5, i4);
                }
                if (bitArray.a(20 - i3)) {
                    bitMatrix.b(i4, i2 + 5);
                }
                if (bitArray.a(27 - i3)) {
                    bitMatrix.b(i2 - 5, i4);
                }
                i3++;
            }
            return;
        }
        while (i3 < 10) {
            int i5 = (i2 - 5) + i3 + (i3 / 5);
            if (bitArray.a(i3)) {
                bitMatrix.b(i5, i2 - 7);
            }
            if (bitArray.a(i3 + 10)) {
                bitMatrix.b(i2 + 7, i5);
            }
            if (bitArray.a(29 - i3)) {
                bitMatrix.b(i5, i2 + 7);
            }
            if (bitArray.a(39 - i3)) {
                bitMatrix.b(i2 - 7, i5);
            }
            i3++;
        }
    }

    private static BitArray a(BitArray bitArray, int i, int i2) {
        ReedSolomonEncoder reedSolomonEncoder = new ReedSolomonEncoder(a(i2));
        int i3 = i / i2;
        int[] b2 = b(bitArray, i2, i3);
        reedSolomonEncoder.a(b2, i3 - (bitArray.a() / i2));
        BitArray bitArray2 = new BitArray();
        bitArray2.c(0, i % i2);
        for (int c2 : b2) {
            bitArray2.c(c2, i2);
        }
        return bitArray2;
    }

    private static int[] b(BitArray bitArray, int i, int i2) {
        int[] iArr = new int[i2];
        int a2 = bitArray.a() / i;
        for (int i3 = 0; i3 < a2; i3++) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                i4 |= bitArray.a((i3 * i) + i5) ? 1 << ((i - i5) - 1) : 0;
            }
            iArr[i3] = i4;
        }
        return iArr;
    }

    private static GenericGF a(int i) {
        if (i == 4) {
            return GenericGF.d;
        }
        if (i == 6) {
            return GenericGF.c;
        }
        if (i == 8) {
            return GenericGF.g;
        }
        if (i == 10) {
            return GenericGF.b;
        }
        if (i == 12) {
            return GenericGF.f1657a;
        }
        throw new IllegalArgumentException("Unsupported word size " + i);
    }

    static BitArray a(BitArray bitArray, int i) {
        BitArray bitArray2 = new BitArray();
        int a2 = bitArray.a();
        int i2 = (1 << i) - 2;
        int i3 = 0;
        while (i3 < a2) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                int i6 = i3 + i5;
                if (i6 >= a2 || bitArray.a(i6)) {
                    i4 |= 1 << ((i - 1) - i5);
                }
            }
            int i7 = i4 & i2;
            if (i7 == i2) {
                bitArray2.c(i7, i);
                i3--;
            } else if (i7 == 0) {
                bitArray2.c(i4 | 1, i);
                i3--;
            } else {
                bitArray2.c(i4, i);
            }
            i3 += i;
        }
        return bitArray2;
    }
}
