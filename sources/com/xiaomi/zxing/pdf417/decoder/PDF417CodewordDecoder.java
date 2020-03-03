package com.xiaomi.zxing.pdf417.decoder;

import com.xiaomi.zxing.common.detector.MathUtils;
import com.xiaomi.zxing.pdf417.PDF417Common;
import java.lang.reflect.Array;

final class PDF417CodewordDecoder {

    /* renamed from: a  reason: collision with root package name */
    private static final float[][] f1742a = ((float[][]) Array.newInstance(float.class, new int[]{PDF417Common.h.length, 8}));

    static {
        int i;
        for (int i2 = 0; i2 < PDF417Common.h.length; i2++) {
            int i3 = PDF417Common.h[i2];
            int i4 = i3 & 1;
            int i5 = i3;
            int i6 = 0;
            while (i6 < 8) {
                float f = 0.0f;
                while (true) {
                    i = i5 & 1;
                    if (i != i4) {
                        break;
                    }
                    f += 1.0f;
                    i5 >>= 1;
                }
                f1742a[i2][(8 - i6) - 1] = f / 17.0f;
                i6++;
                i4 = i;
            }
        }
    }

    private PDF417CodewordDecoder() {
    }

    static int a(int[] iArr) {
        int c = c(b(iArr));
        if (c != -1) {
            return c;
        }
        return e(iArr);
    }

    private static int[] b(int[] iArr) {
        float a2 = (float) MathUtils.a(iArr);
        int[] iArr2 = new int[8];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 17; i3++) {
            if (((float) (iArr[i2] + i)) <= (a2 / 34.0f) + ((((float) i3) * a2) / 17.0f)) {
                i += iArr[i2];
                i2++;
            }
            iArr2[i2] = iArr2[i2] + 1;
        }
        return iArr2;
    }

    private static int c(int[] iArr) {
        int d = d(iArr);
        if (PDF417Common.a(d) == -1) {
            return -1;
        }
        return d;
    }

    private static int d(int[] iArr) {
        long j = 0;
        int i = 0;
        while (i < iArr.length) {
            long j2 = j;
            for (int i2 = 0; i2 < iArr[i]; i2++) {
                int i3 = 1;
                long j3 = j2 << 1;
                if (i % 2 != 0) {
                    i3 = 0;
                }
                j2 = j3 | ((long) i3);
            }
            i++;
            j = j2;
        }
        return (int) j;
    }

    private static int e(int[] iArr) {
        int a2 = MathUtils.a(iArr);
        float[] fArr = new float[8];
        for (int i = 0; i < fArr.length; i++) {
            fArr[i] = ((float) iArr[i]) / ((float) a2);
        }
        int i2 = -1;
        float f = Float.MAX_VALUE;
        for (int i3 = 0; i3 < f1742a.length; i3++) {
            float[] fArr2 = f1742a[i3];
            float f2 = 0.0f;
            for (int i4 = 0; i4 < 8; i4++) {
                float f3 = fArr2[i4] - fArr[i4];
                f2 += f3 * f3;
                if (f2 >= f) {
                    break;
                }
            }
            if (f2 < f) {
                i2 = PDF417Common.h[i3];
                f = f2;
            }
        }
        return i2;
    }
}
