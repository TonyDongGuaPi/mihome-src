package com.xiaomi.zxing.aztec.detector;

import com.tencent.smtt.utils.TbsLog;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.aztec.AztecDetectorResult;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.common.GridSampler;
import com.xiaomi.zxing.common.detector.MathUtils;
import com.xiaomi.zxing.common.detector.WhiteRectangleDetector;
import com.xiaomi.zxing.common.reedsolomon.GenericGF;
import com.xiaomi.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.xiaomi.zxing.common.reedsolomon.ReedSolomonException;
import kotlin.text.Typography;

public final class Detector {
    private static final int[] g = {3808, 476, 2107, 1799};

    /* renamed from: a  reason: collision with root package name */
    private final BitMatrix f1637a;
    private boolean b;
    private int c;
    private int d;
    private int e;
    private int f;

    public Detector(BitMatrix bitMatrix) {
        this.f1637a = bitMatrix;
    }

    public AztecDetectorResult a() throws NotFoundException {
        return a(false);
    }

    public AztecDetectorResult a(boolean z) throws NotFoundException {
        ResultPoint[] a2 = a(b());
        if (z) {
            ResultPoint resultPoint = a2[0];
            a2[0] = a2[2];
            a2[2] = resultPoint;
        }
        a(a2);
        return new AztecDetectorResult(a(this.f1637a, a2[this.f % 4], a2[(this.f + 1) % 4], a2[(this.f + 2) % 4], a2[(this.f + 3) % 4]), b(a2), this.b, this.d, this.c);
    }

    private void a(ResultPoint[] resultPointArr) throws NotFoundException {
        long j;
        long j2;
        if (!a(resultPointArr[0]) || !a(resultPointArr[1]) || !a(resultPointArr[2]) || !a(resultPointArr[3])) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i = this.e * 2;
        int[] iArr = {a(resultPointArr[0], resultPointArr[1], i), a(resultPointArr[1], resultPointArr[2], i), a(resultPointArr[2], resultPointArr[3], i), a(resultPointArr[3], resultPointArr[0], i)};
        this.f = a(iArr, i);
        long j3 = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = iArr[(this.f + i2) % 4];
            if (this.b) {
                j2 = j3 << 7;
                j = (long) ((i3 >> 1) & 127);
            } else {
                j2 = j3 << 10;
                j = (long) (((i3 >> 2) & TbsLog.TBSLOG_CODE_SDK_UNAVAIL_X5CORE) + ((i3 >> 1) & 31));
            }
            j3 = j2 + j;
        }
        int a2 = a(j3, this.b);
        if (this.b) {
            this.c = (a2 >> 6) + 1;
            this.d = (a2 & 63) + 1;
            return;
        }
        this.c = (a2 >> 11) + 1;
        this.d = (a2 & 2047) + 1;
    }

    private static int a(int[] iArr, int i) throws NotFoundException {
        int i2 = 0;
        for (int i3 : iArr) {
            i2 = (i2 << 3) + ((i3 >> (i - 2)) << 1) + (i3 & 1);
        }
        int i4 = ((i2 & 1) << 11) + (i2 >> 1);
        for (int i5 = 0; i5 < 4; i5++) {
            if (Integer.bitCount(g[i5] ^ i4) <= 2) {
                return i5;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int a(long j, boolean z) throws NotFoundException {
        int i;
        int i2;
        if (z) {
            i = 7;
            i2 = 2;
        } else {
            i = 10;
            i2 = 4;
        }
        int i3 = i - i2;
        int[] iArr = new int[i];
        for (int i4 = i - 1; i4 >= 0; i4--) {
            iArr[i4] = ((int) j) & 15;
            j >>= 4;
        }
        try {
            new ReedSolomonDecoder(GenericGF.d).a(iArr, i3);
            int i5 = 0;
            for (int i6 = 0; i6 < i2; i6++) {
                i5 = (i5 << 4) + iArr[i6];
            }
            return i5;
        } catch (ReedSolomonException unused) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private ResultPoint[] a(Point point) throws NotFoundException {
        this.e = 1;
        Point point2 = point;
        Point point3 = point2;
        Point point4 = point3;
        Point point5 = point4;
        boolean z = true;
        while (this.e < 9) {
            Point a2 = a(point2, z, 1, -1);
            Point a3 = a(point3, z, 1, 1);
            Point a4 = a(point4, z, -1, 1);
            Point a5 = a(point5, z, -1, -1);
            if (this.e > 2) {
                double b2 = (double) ((b(a5, a2) * ((float) this.e)) / (b(point5, point2) * ((float) (this.e + 2))));
                if (b2 < 0.75d || b2 > 1.25d || !a(a2, a3, a4, a5)) {
                    break;
                }
            }
            z = !z;
            this.e++;
            point5 = a5;
            point2 = a2;
            point3 = a3;
            point4 = a4;
        }
        if (this.e == 5 || this.e == 7) {
            this.b = this.e == 5;
            return a(new ResultPoint[]{new ResultPoint(((float) point2.b()) + 0.5f, ((float) point2.c()) - 0.5f), new ResultPoint(((float) point3.b()) + 0.5f, ((float) point3.c()) + 0.5f), new ResultPoint(((float) point4.b()) - 0.5f, ((float) point4.c()) + 0.5f), new ResultPoint(((float) point5.b()) - 0.5f, ((float) point5.c()) - 0.5f)}, (float) ((this.e * 2) - 3), (float) (this.e * 2));
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private Point b() {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        ResultPoint resultPoint3;
        ResultPoint resultPoint4;
        ResultPoint resultPoint5;
        ResultPoint resultPoint6;
        ResultPoint resultPoint7;
        ResultPoint resultPoint8;
        try {
            ResultPoint[] a2 = new WhiteRectangleDetector(this.f1637a).a();
            resultPoint3 = a2[0];
            resultPoint2 = a2[1];
            resultPoint = a2[2];
            resultPoint4 = a2[3];
        } catch (NotFoundException unused) {
            int f2 = this.f1637a.f() / 2;
            int g2 = this.f1637a.g() / 2;
            int i = f2 + 7;
            int i2 = g2 - 7;
            ResultPoint a3 = a(new Point(i, i2), false, 1, -1).a();
            int i3 = g2 + 7;
            ResultPoint a4 = a(new Point(i, i3), false, 1, 1).a();
            int i4 = f2 - 7;
            ResultPoint a5 = a(new Point(i4, i3), false, -1, 1).a();
            resultPoint4 = a(new Point(i4, i2), false, -1, -1).a();
            ResultPoint resultPoint9 = a4;
            resultPoint = a5;
            resultPoint3 = a3;
            resultPoint2 = resultPoint9;
        }
        int a6 = MathUtils.a((((resultPoint3.a() + resultPoint4.a()) + resultPoint2.a()) + resultPoint.a()) / 4.0f);
        int a7 = MathUtils.a((((resultPoint3.b() + resultPoint4.b()) + resultPoint2.b()) + resultPoint.b()) / 4.0f);
        try {
            ResultPoint[] a8 = new WhiteRectangleDetector(this.f1637a, 15, a6, a7).a();
            resultPoint6 = a8[0];
            resultPoint5 = a8[1];
            resultPoint7 = a8[2];
            resultPoint8 = a8[3];
        } catch (NotFoundException unused2) {
            int i5 = a6 + 7;
            int i6 = a7 - 7;
            resultPoint6 = a(new Point(i5, i6), false, 1, -1).a();
            int i7 = a7 + 7;
            resultPoint5 = a(new Point(i5, i7), false, 1, 1).a();
            int i8 = a6 - 7;
            resultPoint7 = a(new Point(i8, i7), false, -1, 1).a();
            resultPoint8 = a(new Point(i8, i6), false, -1, -1).a();
        }
        return new Point(MathUtils.a((((resultPoint6.a() + resultPoint8.a()) + resultPoint5.a()) + resultPoint7.a()) / 4.0f), MathUtils.a((((resultPoint6.b() + resultPoint8.b()) + resultPoint5.b()) + resultPoint7.b()) / 4.0f));
    }

    private ResultPoint[] b(ResultPoint[] resultPointArr) {
        return a(resultPointArr, (float) (this.e * 2), (float) c());
    }

    private BitMatrix a(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) throws NotFoundException {
        GridSampler a2 = GridSampler.a();
        int c2 = c();
        int i = c2;
        int i2 = c2;
        float f2 = ((float) c2) / 2.0f;
        float f3 = f2 - ((float) this.e);
        float f4 = f3;
        float f5 = f2 + ((float) this.e);
        return a2.a(bitMatrix, i2, i, f4, f3, f5, f3, f5, f5, f3, f5, resultPoint.a(), resultPoint.b(), resultPoint2.a(), resultPoint2.b(), resultPoint3.a(), resultPoint3.b(), resultPoint4.a(), resultPoint4.b());
    }

    private int a(ResultPoint resultPoint, ResultPoint resultPoint2, int i) {
        float a2 = a(resultPoint, resultPoint2);
        float f2 = a2 / ((float) i);
        float a3 = resultPoint.a();
        float b2 = resultPoint.b();
        float a4 = ((resultPoint2.a() - resultPoint.a()) * f2) / a2;
        float b3 = (f2 * (resultPoint2.b() - resultPoint.b())) / a2;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            float f3 = (float) i3;
            if (this.f1637a.a(MathUtils.a((f3 * a4) + a3), MathUtils.a((f3 * b3) + b2))) {
                i2 |= 1 << ((i - i3) - 1);
            }
        }
        return i2;
    }

    private boolean a(Point point, Point point2, Point point3, Point point4) {
        Point point5 = new Point(point.b() - 3, point.c() + 3);
        Point point6 = new Point(point2.b() - 3, point2.c() - 3);
        Point point7 = new Point(point3.b() + 3, point3.c() - 3);
        Point point8 = new Point(point4.b() + 3, point4.c() + 3);
        int a2 = a(point8, point5);
        if (a2 != 0 && a(point5, point6) == a2 && a(point6, point7) == a2 && a(point7, point8) == a2) {
            return true;
        }
        return false;
    }

    private int a(Point point, Point point2) {
        float b2 = b(point, point2);
        float b3 = ((float) (point2.b() - point.b())) / b2;
        float c2 = ((float) (point2.c() - point.c())) / b2;
        boolean a2 = this.f1637a.a(point.b(), point.c());
        boolean z = false;
        float c3 = (float) point.c();
        int i = 0;
        float b4 = (float) point.b();
        for (int i2 = 0; ((float) i2) < b2; i2++) {
            b4 += b3;
            c3 += c2;
            if (this.f1637a.a(MathUtils.a(b4), MathUtils.a(c3)) != a2) {
                i++;
            }
        }
        float f2 = ((float) i) / b2;
        if (f2 > 0.1f && f2 < 0.9f) {
            return 0;
        }
        if (f2 <= 0.1f) {
            z = true;
        }
        return z == a2 ? 1 : -1;
    }

    private Point a(Point point, boolean z, int i, int i2) {
        int b2 = point.b() + i;
        int c2 = point.c();
        while (true) {
            c2 += i2;
            if (!a(b2, c2) || this.f1637a.a(b2, c2) != z) {
                int i3 = b2 - i;
                int i4 = c2 - i2;
            } else {
                b2 += i;
            }
        }
        int i32 = b2 - i;
        int i42 = c2 - i2;
        while (a(i32, i42) && this.f1637a.a(i32, i42) == z) {
            i32 += i;
        }
        int i5 = i32 - i;
        while (a(i5, i42) && this.f1637a.a(i5, i42) == z) {
            i42 += i2;
        }
        return new Point(i5, i42 - i2);
    }

    private static ResultPoint[] a(ResultPoint[] resultPointArr, float f2, float f3) {
        float f4 = f3 / (f2 * 2.0f);
        float a2 = resultPointArr[0].a() - resultPointArr[2].a();
        float b2 = resultPointArr[0].b() - resultPointArr[2].b();
        float a3 = (resultPointArr[0].a() + resultPointArr[2].a()) / 2.0f;
        float b3 = (resultPointArr[0].b() + resultPointArr[2].b()) / 2.0f;
        float f5 = a2 * f4;
        float f6 = b2 * f4;
        ResultPoint resultPoint = new ResultPoint(a3 + f5, b3 + f6);
        ResultPoint resultPoint2 = new ResultPoint(a3 - f5, b3 - f6);
        float a4 = resultPointArr[1].a() - resultPointArr[3].a();
        float b4 = resultPointArr[1].b() - resultPointArr[3].b();
        float a5 = (resultPointArr[1].a() + resultPointArr[3].a()) / 2.0f;
        float b5 = (resultPointArr[1].b() + resultPointArr[3].b()) / 2.0f;
        float f7 = a4 * f4;
        float f8 = f4 * b4;
        return new ResultPoint[]{resultPoint, new ResultPoint(a5 + f7, b5 + f8), resultPoint2, new ResultPoint(a5 - f7, b5 - f8)};
    }

    private boolean a(int i, int i2) {
        return i >= 0 && i < this.f1637a.f() && i2 > 0 && i2 < this.f1637a.g();
    }

    private boolean a(ResultPoint resultPoint) {
        return a(MathUtils.a(resultPoint.a()), MathUtils.a(resultPoint.b()));
    }

    private static float b(Point point, Point point2) {
        return MathUtils.a(point.b(), point.c(), point2.b(), point2.c());
    }

    private static float a(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.a(resultPoint.a(), resultPoint.b(), resultPoint2.a(), resultPoint2.b());
    }

    private int c() {
        if (this.b) {
            return (this.c * 4) + 11;
        }
        if (this.c <= 4) {
            return (this.c * 4) + 15;
        }
        return (this.c * 4) + ((((this.c - 4) / 8) + 1) * 2) + 15;
    }

    static final class Point {

        /* renamed from: a  reason: collision with root package name */
        private final int f1638a;
        private final int b;

        /* access modifiers changed from: package-private */
        public ResultPoint a() {
            return new ResultPoint((float) b(), (float) c());
        }

        Point(int i, int i2) {
            this.f1638a = i;
            this.b = i2;
        }

        /* access modifiers changed from: package-private */
        public int b() {
            return this.f1638a;
        }

        /* access modifiers changed from: package-private */
        public int c() {
            return this.b;
        }

        public String toString() {
            return "<" + this.f1638a + ' ' + this.b + Typography.e;
        }
    }
}
