package com.xiaomi.zxing.common;

import com.xiaomi.zxing.Binarizer;
import com.xiaomi.zxing.LuminanceSource;
import com.xiaomi.zxing.NotFoundException;
import java.lang.reflect.Array;

public final class HybridBinarizer extends GlobalHistogramBinarizer {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1652a = 3;
    private static final int b = 8;
    private static final int c = 7;
    private static final int d = 40;
    private static final int e = 24;
    private BitMatrix f;

    private static int a(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    public HybridBinarizer(LuminanceSource luminanceSource) {
        super(luminanceSource);
    }

    public BitMatrix b() throws NotFoundException {
        if (this.f != null) {
            return this.f;
        }
        LuminanceSource a2 = a();
        int g = a2.g();
        int h = a2.h();
        if (g < 40 || h < 40) {
            this.f = super.b();
        } else {
            byte[] a3 = a2.a();
            int i = g >> 3;
            if ((g & 7) != 0) {
                i++;
            }
            int i2 = i;
            int i3 = h >> 3;
            if ((h & 7) != 0) {
                i3++;
            }
            int i4 = i3;
            int[][] a4 = a(a3, i2, i4, g, h);
            BitMatrix bitMatrix = new BitMatrix(g, h);
            a(a3, i2, i4, g, h, a4, bitMatrix);
            this.f = bitMatrix;
        }
        return this.f;
    }

    public Binarizer a(LuminanceSource luminanceSource) {
        return new HybridBinarizer(luminanceSource);
    }

    private static void a(byte[] bArr, int i, int i2, int i3, int i4, int[][] iArr, BitMatrix bitMatrix) {
        int i5 = i;
        int i6 = i2;
        for (int i7 = 0; i7 < i6; i7++) {
            int i8 = i7 << 3;
            int i9 = i4 - 8;
            if (i8 > i9) {
                i8 = i9;
            }
            for (int i10 = 0; i10 < i5; i10++) {
                int i11 = i10 << 3;
                int i12 = i3 - 8;
                if (i11 <= i12) {
                    i12 = i11;
                }
                int a2 = a(i10, 2, i5 - 3);
                int a3 = a(i7, 2, i6 - 3);
                int i13 = 0;
                for (int i14 = -2; i14 <= 2; i14++) {
                    int[] iArr2 = iArr[a3 + i14];
                    i13 += iArr2[a2 - 2] + iArr2[a2 - 1] + iArr2[a2] + iArr2[a2 + 1] + iArr2[a2 + 2];
                }
                a(bArr, i12, i8, i13 / 25, i3, bitMatrix);
            }
        }
    }

    private static void a(byte[] bArr, int i, int i2, int i3, int i4, BitMatrix bitMatrix) {
        int i5 = (i2 * i4) + i;
        int i6 = 0;
        while (i6 < 8) {
            for (int i7 = 0; i7 < 8; i7++) {
                if ((bArr[i5 + i7] & 255) <= i3) {
                    bitMatrix.b(i + i7, i2 + i6);
                }
            }
            i6++;
            i5 += i4;
        }
    }

    private static int[][] a(byte[] bArr, int i, int i2, int i3, int i4) {
        int i5 = i;
        int i6 = i2;
        int[][] iArr = (int[][]) Array.newInstance(int.class, new int[]{i6, i5});
        for (int i7 = 0; i7 < i6; i7++) {
            int i8 = i7 << 3;
            int i9 = 8;
            int i10 = i4 - 8;
            if (i8 > i10) {
                i8 = i10;
            }
            int i11 = 0;
            while (i11 < i5) {
                int i12 = i11 << 3;
                int i13 = i3 - 8;
                if (i12 > i13) {
                    i12 = i13;
                }
                int i14 = (i8 * i3) + i12;
                int i15 = 0;
                int i16 = 0;
                byte b2 = 0;
                byte b3 = 255;
                while (i15 < i9) {
                    byte b4 = b2;
                    int i17 = i16;
                    int i18 = 0;
                    while (i18 < i9) {
                        byte b5 = bArr[i14 + i18] & 255;
                        i17 += b5;
                        if (b5 < b3) {
                            b3 = b5;
                        }
                        if (b5 > b4) {
                            b4 = b5;
                        }
                        i18++;
                        i9 = 8;
                    }
                    if (b4 - b3 <= 24) {
                        i16 = i17;
                        i15++;
                        i14 += i3;
                        b2 = b4;
                        i9 = 8;
                    }
                    while (true) {
                        i15++;
                        i14 += i3;
                        if (i15 >= 8) {
                            break;
                        }
                        int i19 = 0;
                        for (int i20 = 8; i19 < i20; i20 = 8) {
                            i17 += bArr[i14 + i19] & 255;
                            i19++;
                        }
                    }
                    i16 = i17;
                    i15++;
                    i14 += i3;
                    b2 = b4;
                    i9 = 8;
                }
                int i21 = i16 >> 6;
                if (b2 - b3 <= 24) {
                    i21 = b3 / 2;
                    if (i7 > 0 && i11 > 0) {
                        int i22 = i7 - 1;
                        int i23 = i11 - 1;
                        int i24 = ((iArr[i22][i11] + (iArr[i7][i23] * 2)) + iArr[i22][i23]) / 4;
                        if (b3 < i24) {
                            i21 = i24;
                        }
                    }
                }
                iArr[i7][i11] = i21;
                i11++;
                i9 = 8;
            }
        }
        return iArr;
    }
}
