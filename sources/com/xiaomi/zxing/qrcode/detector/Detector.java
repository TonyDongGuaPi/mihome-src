package com.xiaomi.zxing.qrcode.detector;

import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.ResultPointCallback;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.common.DetectorResult;
import com.xiaomi.zxing.common.GridSampler;
import com.xiaomi.zxing.common.PerspectiveTransform;
import com.xiaomi.zxing.common.detector.MathUtils;
import com.xiaomi.zxing.qrcode.decoder.Version;
import java.util.Map;

public class Detector {

    /* renamed from: a  reason: collision with root package name */
    private final BitMatrix f1768a;
    private ResultPointCallback b;

    public Detector(BitMatrix bitMatrix) {
        this.f1768a = bitMatrix;
    }

    /* access modifiers changed from: protected */
    public final BitMatrix a() {
        return this.f1768a;
    }

    /* access modifiers changed from: protected */
    public final ResultPointCallback b() {
        return this.b;
    }

    public DetectorResult c() throws NotFoundException, FormatException {
        return b((Map<DecodeHintType, ?>) null);
    }

    public final DetectorResult b(Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        ResultPointCallback resultPointCallback;
        if (map == null) {
            resultPointCallback = null;
        } else {
            resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        }
        this.b = resultPointCallback;
        return a(new FinderPatternFinder(this.f1768a, this.b).b(map));
    }

    /* access modifiers changed from: protected */
    public final DetectorResult a(FinderPatternInfo finderPatternInfo) throws NotFoundException, FormatException {
        ResultPoint[] resultPointArr;
        FinderPattern b2 = finderPatternInfo.b();
        FinderPattern c = finderPatternInfo.c();
        FinderPattern a2 = finderPatternInfo.a();
        float a3 = a((ResultPoint) b2, (ResultPoint) c, (ResultPoint) a2);
        if (a3 >= 1.0f) {
            int a4 = a((ResultPoint) b2, (ResultPoint) c, (ResultPoint) a2, a3);
            Version a5 = Version.a(a4);
            int d = a5.d() - 7;
            AlignmentPattern alignmentPattern = null;
            if (a5.b().length > 0) {
                float a6 = (c.a() - b2.a()) + a2.a();
                float b3 = (c.b() - b2.b()) + a2.b();
                float f = 1.0f - (3.0f / ((float) d));
                int a7 = (int) (b2.a() + ((a6 - b2.a()) * f));
                int b4 = (int) (b2.b() + (f * (b3 - b2.b())));
                int i = 4;
                while (true) {
                    if (i > 16) {
                        break;
                    }
                    try {
                        alignmentPattern = a(a3, a7, b4, (float) i);
                        break;
                    } catch (NotFoundException unused) {
                        i <<= 1;
                    }
                }
            }
            BitMatrix a8 = a(this.f1768a, a(b2, c, a2, alignmentPattern, a4), a4);
            if (alignmentPattern == null) {
                resultPointArr = new ResultPoint[]{a2, b2, c};
            } else {
                resultPointArr = new ResultPoint[]{a2, b2, c, alignmentPattern};
            }
            return new DetectorResult(a8, resultPointArr);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static PerspectiveTransform a(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i) {
        float f;
        float f2;
        float f3;
        float f4 = ((float) i) - 3.5f;
        if (resultPoint4 != null) {
            float a2 = resultPoint4.a();
            f = resultPoint4.b();
            f2 = a2;
            f3 = f4 - 3.0f;
        } else {
            f2 = (resultPoint2.a() - resultPoint.a()) + resultPoint3.a();
            f = (resultPoint2.b() - resultPoint.b()) + resultPoint3.b();
            f3 = f4;
        }
        return PerspectiveTransform.a(3.5f, 3.5f, f4, 3.5f, f3, f3, 3.5f, f4, resultPoint.a(), resultPoint.b(), resultPoint2.a(), resultPoint2.b(), f2, f, resultPoint3.a(), resultPoint3.b());
    }

    private static BitMatrix a(BitMatrix bitMatrix, PerspectiveTransform perspectiveTransform, int i) throws NotFoundException {
        return GridSampler.a().a(bitMatrix, i, i, perspectiveTransform);
    }

    private static int a(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, float f) throws NotFoundException {
        int a2 = ((MathUtils.a(ResultPoint.a(resultPoint, resultPoint2) / f) + MathUtils.a(ResultPoint.a(resultPoint, resultPoint3) / f)) / 2) + 7;
        int i = a2 & 3;
        if (i == 0) {
            return a2 + 1;
        }
        switch (i) {
            case 2:
                return a2 - 1;
            case 3:
                throw NotFoundException.getNotFoundInstance();
            default:
                return a2;
        }
    }

    /* access modifiers changed from: protected */
    public final float a(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        return (a(resultPoint, resultPoint2) + a(resultPoint, resultPoint3)) / 2.0f;
    }

    private float a(ResultPoint resultPoint, ResultPoint resultPoint2) {
        float a2 = a((int) resultPoint.a(), (int) resultPoint.b(), (int) resultPoint2.a(), (int) resultPoint2.b());
        float a3 = a((int) resultPoint2.a(), (int) resultPoint2.b(), (int) resultPoint.a(), (int) resultPoint.b());
        if (Float.isNaN(a2)) {
            return a3 / 7.0f;
        }
        return Float.isNaN(a3) ? a2 / 7.0f : (a2 + a3) / 14.0f;
    }

    private float a(int i, int i2, int i3, int i4) {
        float f;
        int i5;
        float f2;
        float b2 = b(i, i2, i3, i4);
        int i6 = i - (i3 - i);
        int i7 = 0;
        if (i6 < 0) {
            f = ((float) i) / ((float) (i - i6));
            i5 = 0;
        } else if (i6 >= this.f1768a.f()) {
            f = ((float) ((this.f1768a.f() - 1) - i)) / ((float) (i6 - i));
            i5 = this.f1768a.f() - 1;
        } else {
            i5 = i6;
            f = 1.0f;
        }
        float f3 = (float) i2;
        int i8 = (int) (f3 - (((float) (i4 - i2)) * f));
        if (i8 < 0) {
            f2 = f3 / ((float) (i2 - i8));
        } else if (i8 >= this.f1768a.g()) {
            f2 = ((float) ((this.f1768a.g() - 1) - i2)) / ((float) (i8 - i2));
            i7 = this.f1768a.g() - 1;
        } else {
            i7 = i8;
            f2 = 1.0f;
        }
        return (b2 + b(i, i2, (int) (((float) i) + (((float) (i5 - i)) * f2)), i7)) - 1.0f;
    }

    private float b(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean z;
        Detector detector;
        boolean z2;
        int i10 = 1;
        boolean z3 = Math.abs(i4 - i2) > Math.abs(i3 - i);
        if (z3) {
            i6 = i;
            i8 = i2;
            i5 = i3;
            i7 = i4;
        } else {
            i8 = i;
            i6 = i2;
            i7 = i3;
            i5 = i4;
        }
        int abs = Math.abs(i7 - i8);
        int abs2 = Math.abs(i5 - i6);
        int i11 = (-abs) / 2;
        int i12 = -1;
        int i13 = i8 < i7 ? 1 : -1;
        if (i6 < i5) {
            i12 = 1;
        }
        int i14 = i7 + i13;
        int i15 = i6;
        int i16 = i11;
        int i17 = 0;
        int i18 = i8;
        while (true) {
            if (i18 == i14) {
                i9 = i14;
                break;
            }
            int i19 = z3 ? i15 : i18;
            int i20 = z3 ? i18 : i15;
            if (i17 == i10) {
                detector = this;
                i9 = i14;
                z = z3;
                z2 = true;
            } else {
                detector = this;
                i9 = i14;
                z = z3;
                z2 = false;
            }
            if (z2 == detector.f1768a.a(i19, i20)) {
                if (i17 == 2) {
                    return MathUtils.a(i18, i15, i8, i6);
                }
                i17++;
            }
            i16 += abs2;
            if (i16 > 0) {
                if (i15 == i5) {
                    break;
                }
                i15 += i12;
                i16 -= abs;
            }
            i18 += i13;
            z3 = z;
            i14 = i9;
            i10 = 1;
        }
        if (i17 == 2) {
            return MathUtils.a(i9, i5, i8, i6);
        }
        return Float.NaN;
    }

    /* access modifiers changed from: protected */
    public final AlignmentPattern a(float f, int i, int i2, float f2) throws NotFoundException {
        int i3 = (int) (f2 * f);
        int max = Math.max(0, i - i3);
        int min = Math.min(this.f1768a.f() - 1, i + i3) - max;
        float f3 = 3.0f * f;
        if (((float) min) >= f3) {
            int max2 = Math.max(0, i2 - i3);
            int min2 = Math.min(this.f1768a.g() - 1, i2 + i3) - max2;
            if (((float) min2) >= f3) {
                return new AlignmentPatternFinder(this.f1768a, max, max2, min, min2, f, this.b).a();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
