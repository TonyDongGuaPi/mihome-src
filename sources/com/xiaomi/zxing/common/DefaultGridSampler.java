package com.xiaomi.zxing.common;

import com.xiaomi.zxing.NotFoundException;

public final class DefaultGridSampler extends GridSampler {
    public BitMatrix a(BitMatrix bitMatrix, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) throws NotFoundException {
        BitMatrix bitMatrix2 = bitMatrix;
        int i3 = i;
        int i4 = i2;
        return a(bitMatrix, i, i2, PerspectiveTransform.a(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16));
    }

    public BitMatrix a(BitMatrix bitMatrix, int i, int i2, PerspectiveTransform perspectiveTransform) throws NotFoundException {
        if (i <= 0 || i2 <= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        BitMatrix bitMatrix2 = new BitMatrix(i, i2);
        float[] fArr = new float[(i * 2)];
        for (int i3 = 0; i3 < i2; i3++) {
            int length = fArr.length;
            float f = ((float) i3) + 0.5f;
            for (int i4 = 0; i4 < length; i4 += 2) {
                fArr[i4] = ((float) (i4 / 2)) + 0.5f;
                fArr[i4 + 1] = f;
            }
            perspectiveTransform.a(fArr);
            a(bitMatrix, fArr);
            int i5 = 0;
            while (i5 < length) {
                try {
                    if (bitMatrix.a((int) fArr[i5], (int) fArr[i5 + 1])) {
                        bitMatrix2.b(i5 / 2, i3);
                    }
                    i5 += 2;
                } catch (ArrayIndexOutOfBoundsException unused) {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
        }
        return bitMatrix2;
    }
}
