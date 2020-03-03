package com.xiaomi.zxing.common;

import com.xiaomi.zxing.Binarizer;
import com.xiaomi.zxing.LuminanceSource;
import com.xiaomi.zxing.NotFoundException;

public class GlobalHistogramBinarizer extends Binarizer {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1650a = 5;
    private static final int b = 3;
    private static final int c = 32;
    private static final byte[] d = new byte[0];
    private byte[] e = d;
    private final int[] f = new int[32];

    public GlobalHistogramBinarizer(LuminanceSource luminanceSource) {
        super(luminanceSource);
    }

    public BitArray a(int i, BitArray bitArray) throws NotFoundException {
        LuminanceSource a2 = a();
        int g = a2.g();
        if (bitArray == null || bitArray.a() < g) {
            bitArray = new BitArray(g);
        } else {
            bitArray.c();
        }
        a(g);
        byte[] a3 = a2.a(i, this.e);
        int[] iArr = this.f;
        for (int i2 = 0; i2 < g; i2++) {
            int i3 = (a3[i2] & 255) >> 3;
            iArr[i3] = iArr[i3] + 1;
        }
        int a4 = a(iArr);
        if (g < 3) {
            for (int i4 = 0; i4 < g; i4++) {
                if ((a3[i4] & 255) < a4) {
                    bitArray.b(i4);
                }
            }
        } else {
            byte b2 = a3[1] & 255;
            byte b3 = a3[0] & 255;
            int i5 = 1;
            while (i5 < g - 1) {
                int i6 = i5 + 1;
                byte b4 = a3[i6] & 255;
                if ((((b2 * 4) - b3) - b4) / 2 < a4) {
                    bitArray.b(i5);
                }
                b3 = b2;
                i5 = i6;
                b2 = b4;
            }
        }
        return bitArray;
    }

    public BitMatrix b() throws NotFoundException {
        LuminanceSource a2 = a();
        int g = a2.g();
        int h = a2.h();
        BitMatrix bitMatrix = new BitMatrix(g, h);
        a(g);
        int[] iArr = this.f;
        for (int i = 1; i < 5; i++) {
            byte[] a3 = a2.a((h * i) / 5, this.e);
            int i2 = (g * 4) / 5;
            for (int i3 = g / 5; i3 < i2; i3++) {
                int i4 = (a3[i3] & 255) >> 3;
                iArr[i4] = iArr[i4] + 1;
            }
        }
        int a4 = a(iArr);
        byte[] a5 = a2.a();
        for (int i5 = 0; i5 < h; i5++) {
            int i6 = i5 * g;
            for (int i7 = 0; i7 < g; i7++) {
                if ((a5[i6 + i7] & 255) < a4) {
                    bitMatrix.b(i7, i5);
                }
            }
        }
        return bitMatrix;
    }

    public Binarizer a(LuminanceSource luminanceSource) {
        return new GlobalHistogramBinarizer(luminanceSource);
    }

    private void a(int i) {
        if (this.e.length < i) {
            this.e = new byte[i];
        }
        for (int i2 = 0; i2 < 32; i2++) {
            this.f[i2] = 0;
        }
    }

    private static int a(int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            if (iArr[i4] > i) {
                i = iArr[i4];
                i3 = i4;
            }
            if (iArr[i4] > i2) {
                i2 = iArr[i4];
            }
        }
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            int i8 = i7 - i3;
            int i9 = iArr[i7] * i8 * i8;
            if (i9 > i5) {
                i6 = i7;
                i5 = i9;
            }
        }
        if (i3 > i6) {
            int i10 = i3;
            i3 = i6;
            i6 = i10;
        }
        if (i6 - i3 > length / 16) {
            int i11 = i6 - 1;
            int i12 = -1;
            int i13 = i11;
            while (i11 > i3) {
                int i14 = i11 - i3;
                int i15 = i14 * i14 * (i6 - i11) * (i2 - iArr[i11]);
                if (i15 > i12) {
                    i13 = i11;
                    i12 = i15;
                }
                i11--;
            }
            return i13 << 3;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
