package com.xiaomi.zxing.common.detector;

import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.common.BitMatrix;

public final class WhiteRectangleDetector {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1656a = 10;
    private static final int b = 1;
    private final BitMatrix c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    private final int h;
    private final int i;

    public WhiteRectangleDetector(BitMatrix bitMatrix) throws NotFoundException {
        this(bitMatrix, 10, bitMatrix.f() / 2, bitMatrix.g() / 2);
    }

    public WhiteRectangleDetector(BitMatrix bitMatrix, int i2, int i3, int i4) throws NotFoundException {
        this.c = bitMatrix;
        this.d = bitMatrix.g();
        this.e = bitMatrix.f();
        int i5 = i2 / 2;
        this.f = i3 - i5;
        this.g = i3 + i5;
        this.i = i4 - i5;
        this.h = i4 + i5;
        if (this.i < 0 || this.f < 0 || this.h >= this.d || this.g >= this.e) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    public ResultPoint[] a() throws NotFoundException {
        int i2 = this.f;
        int i3 = this.g;
        int i4 = this.i;
        int i5 = this.h;
        boolean z = false;
        int i6 = i2;
        boolean z2 = true;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        while (true) {
            if (!z2) {
                break;
            }
            boolean z8 = true;
            boolean z9 = false;
            while (true) {
                if ((z8 || !z3) && i3 < this.e) {
                    z8 = a(i4, i5, i3, false);
                    if (z8) {
                        i3++;
                        z3 = true;
                        z9 = true;
                    } else if (!z3) {
                        i3++;
                    }
                }
            }
            if (i3 >= this.e) {
                break;
            }
            boolean z10 = true;
            while (true) {
                if ((z10 || !z4) && i5 < this.d) {
                    z10 = a(i6, i3, i5, true);
                    if (z10) {
                        i5++;
                        z4 = true;
                        z9 = true;
                    } else if (!z4) {
                        i5++;
                    }
                }
            }
            if (i5 >= this.d) {
                break;
            }
            boolean z11 = true;
            while (true) {
                if ((z11 || !z5) && i6 >= 0) {
                    z11 = a(i4, i5, i6, false);
                    if (z11) {
                        i6--;
                        z5 = true;
                        z9 = true;
                    } else if (!z5) {
                        i6--;
                    }
                }
            }
            if (i6 < 0) {
                break;
            }
            boolean z12 = true;
            while (true) {
                if ((z12 || !z7) && i4 >= 0) {
                    z12 = a(i6, i3, i4, true);
                    if (z12) {
                        i4--;
                        z7 = true;
                        z9 = true;
                    } else if (!z7) {
                        i4--;
                    }
                }
            }
            if (i4 < 0) {
                break;
            }
            if (z9) {
                z6 = true;
            }
            z2 = z9;
        }
        z = true;
        if (z || !z6) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i7 = i3 - i6;
        ResultPoint resultPoint = null;
        ResultPoint resultPoint2 = null;
        for (int i8 = 1; i8 < i7; i8++) {
            resultPoint2 = a((float) i6, (float) (i5 - i8), (float) (i6 + i8), (float) i5);
            if (resultPoint2 != null) {
                break;
            }
        }
        if (resultPoint2 != null) {
            ResultPoint resultPoint3 = null;
            for (int i9 = 1; i9 < i7; i9++) {
                resultPoint3 = a((float) i6, (float) (i4 + i9), (float) (i6 + i9), (float) i4);
                if (resultPoint3 != null) {
                    break;
                }
            }
            if (resultPoint3 != null) {
                ResultPoint resultPoint4 = null;
                for (int i10 = 1; i10 < i7; i10++) {
                    resultPoint4 = a((float) i3, (float) (i4 + i10), (float) (i3 - i10), (float) i4);
                    if (resultPoint4 != null) {
                        break;
                    }
                }
                if (resultPoint4 != null) {
                    for (int i11 = 1; i11 < i7; i11++) {
                        resultPoint = a((float) i3, (float) (i5 - i11), (float) (i3 - i11), (float) i5);
                        if (resultPoint != null) {
                            break;
                        }
                    }
                    if (resultPoint != null) {
                        return a(resultPoint, resultPoint2, resultPoint4, resultPoint3);
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
                throw NotFoundException.getNotFoundInstance();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private ResultPoint a(float f2, float f3, float f4, float f5) {
        int a2 = MathUtils.a(MathUtils.a(f2, f3, f4, f5));
        float f6 = (float) a2;
        float f7 = (f4 - f2) / f6;
        float f8 = (f5 - f3) / f6;
        for (int i2 = 0; i2 < a2; i2++) {
            float f9 = (float) i2;
            int a3 = MathUtils.a((f9 * f7) + f2);
            int a4 = MathUtils.a((f9 * f8) + f3);
            if (this.c.a(a3, a4)) {
                return new ResultPoint((float) a3, (float) a4);
            }
        }
        return null;
    }

    private ResultPoint[] a(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) {
        float a2 = resultPoint.a();
        float b2 = resultPoint.b();
        float a3 = resultPoint2.a();
        float b3 = resultPoint2.b();
        float a4 = resultPoint3.a();
        float b4 = resultPoint3.b();
        float a5 = resultPoint4.a();
        float b5 = resultPoint4.b();
        if (a2 < ((float) this.e) / 2.0f) {
            return new ResultPoint[]{new ResultPoint(a5 - 1.0f, b5 + 1.0f), new ResultPoint(a3 + 1.0f, b3 + 1.0f), new ResultPoint(a4 - 1.0f, b4 - 1.0f), new ResultPoint(a2 + 1.0f, b2 - 1.0f)};
        }
        return new ResultPoint[]{new ResultPoint(a5 + 1.0f, b5 + 1.0f), new ResultPoint(a3 + 1.0f, b3 - 1.0f), new ResultPoint(a4 - 1.0f, b4 + 1.0f), new ResultPoint(a2 - 1.0f, b2 - 1.0f)};
    }

    private boolean a(int i2, int i3, int i4, boolean z) {
        if (z) {
            while (i2 <= i3) {
                if (this.c.a(i2, i4)) {
                    return true;
                }
                i2++;
            }
            return false;
        }
        while (i2 <= i3) {
            if (this.c.a(i4, i2)) {
                return true;
            }
            i2++;
        }
        return false;
    }
}
