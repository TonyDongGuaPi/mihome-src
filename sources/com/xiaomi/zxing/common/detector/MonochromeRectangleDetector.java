package com.xiaomi.zxing.common.detector;

import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.common.BitMatrix;

public final class MonochromeRectangleDetector {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1655a = 32;
    private final BitMatrix b;

    public MonochromeRectangleDetector(BitMatrix bitMatrix) {
        this.b = bitMatrix;
    }

    public ResultPoint[] a() throws NotFoundException {
        int g = this.b.g();
        int f = this.b.f();
        int i = g / 2;
        int i2 = f / 2;
        int max = Math.max(1, g / 256);
        int i3 = -max;
        int i4 = i2 / 2;
        int i5 = i2;
        int i6 = f;
        int i7 = i;
        int i8 = i3;
        int i9 = g;
        int i10 = max;
        int max2 = Math.max(1, f / 256);
        int i11 = -max2;
        int b2 = ((int) a(i5, 0, 0, i6, i7, i3, 0, i9, i4).b()) - 1;
        int i12 = max2;
        int i13 = i / 2;
        ResultPoint a2 = a(i5, i11, 0, i6, i7, 0, b2, i9, i13);
        int a3 = ((int) a2.a()) - 1;
        ResultPoint a4 = a(i5, i12, a3, i6, i7, 0, b2, i9, i13);
        int a5 = ((int) a4.a()) + 1;
        ResultPoint a6 = a(i5, 0, a3, a5, i7, i10, b2, i9, i4);
        return new ResultPoint[]{a(i5, 0, a3, a5, i7, i8, b2, ((int) a6.b()) + 1, i2 / 4), a2, a4, a6};
    }

    private ResultPoint a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) throws NotFoundException {
        int[] iArr;
        int i10 = i;
        int i11 = i5;
        int i12 = i10;
        int i13 = i11;
        int[] iArr2 = null;
        int i14 = i8;
        while (i13 < i14 && i13 >= i7 && i12 < i4 && i12 >= i3) {
            if (i2 == 0) {
                iArr = a(i13, i9, i3, i4, true);
            } else {
                iArr = a(i12, i9, i7, i8, false);
            }
            if (iArr != null) {
                i13 += i6;
                i12 += i2;
                iArr2 = iArr;
            } else if (iArr2 != null) {
                char c = 1;
                if (i2 == 0) {
                    int i15 = i13 - i6;
                    if (iArr2[0] >= i10) {
                        return new ResultPoint((float) iArr2[1], (float) i15);
                    }
                    if (iArr2[1] <= i10) {
                        return new ResultPoint((float) iArr2[0], (float) i15);
                    }
                    if (i6 > 0) {
                        c = 0;
                    }
                    return new ResultPoint((float) iArr2[c], (float) i15);
                }
                int i16 = i12 - i2;
                if (iArr2[0] >= i11) {
                    return new ResultPoint((float) i16, (float) iArr2[1]);
                }
                if (iArr2[1] <= i11) {
                    return new ResultPoint((float) i16, (float) iArr2[0]);
                }
                float f = (float) i16;
                if (i2 < 0) {
                    c = 0;
                }
                return new ResultPoint(f, (float) iArr2[c]);
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private int[] a(int i, int i2, int i3, int i4, boolean z) {
        int i5 = (i3 + i4) / 2;
        int i6 = i5;
        while (i6 >= i3) {
            if (!z ? !this.b.a(i, i6) : !this.b.a(i6, i)) {
                int i7 = i6;
                while (true) {
                    i7--;
                    if (i7 < i3) {
                        break;
                    } else if (z) {
                        if (this.b.a(i7, i)) {
                            break;
                        }
                    } else if (this.b.a(i, i7)) {
                        break;
                    }
                }
                int i8 = i6 - i7;
                if (i7 < i3 || i8 > i2) {
                    break;
                }
                i6 = i7;
            } else {
                i6--;
            }
        }
        int i9 = i6 + 1;
        while (i5 < i4) {
            if (!z ? !this.b.a(i, i5) : !this.b.a(i5, i)) {
                int i10 = i5;
                while (true) {
                    i10++;
                    if (i10 >= i4) {
                        break;
                    } else if (z) {
                        if (this.b.a(i10, i)) {
                            break;
                        }
                    } else if (this.b.a(i, i10)) {
                        break;
                    }
                }
                int i11 = i10 - i5;
                if (i10 >= i4 || i11 > i2) {
                    break;
                }
                i5 = i10;
            } else {
                i5++;
            }
        }
        int i12 = i5 - 1;
        if (i12 <= i9) {
            return null;
        }
        return new int[]{i9, i12};
    }
}
