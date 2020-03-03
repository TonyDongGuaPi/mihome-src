package com.xiaomi.zxing.datamatrix.detector;

import com.xiaomi.smarthome.fastvideo.IOUtils;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.common.DetectorResult;
import com.xiaomi.zxing.common.GridSampler;
import com.xiaomi.zxing.common.detector.MathUtils;
import com.xiaomi.zxing.common.detector.WhiteRectangleDetector;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public final class Detector {

    /* renamed from: a  reason: collision with root package name */
    private final BitMatrix f1670a;
    private final WhiteRectangleDetector b;

    public Detector(BitMatrix bitMatrix) throws NotFoundException {
        this.f1670a = bitMatrix;
        this.b = new WhiteRectangleDetector(bitMatrix);
    }

    public DetectorResult a() throws NotFoundException {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        BitMatrix bitMatrix;
        ResultPoint resultPoint3;
        ResultPoint[] a2 = this.b.a();
        ResultPoint resultPoint4 = a2[0];
        ResultPoint resultPoint5 = a2[1];
        ResultPoint resultPoint6 = a2[2];
        ResultPoint resultPoint7 = a2[3];
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(b(resultPoint4, resultPoint5));
        arrayList.add(b(resultPoint4, resultPoint6));
        arrayList.add(b(resultPoint5, resultPoint7));
        arrayList.add(b(resultPoint6, resultPoint7));
        ResultPoint resultPoint8 = null;
        Collections.sort(arrayList, new ResultPointsAndTransitionsComparator());
        ResultPointsAndTransitions resultPointsAndTransitions = (ResultPointsAndTransitions) arrayList.get(0);
        ResultPointsAndTransitions resultPointsAndTransitions2 = (ResultPointsAndTransitions) arrayList.get(1);
        HashMap hashMap = new HashMap();
        a((Map<ResultPoint, Integer>) hashMap, resultPointsAndTransitions.a());
        a((Map<ResultPoint, Integer>) hashMap, resultPointsAndTransitions.b());
        a((Map<ResultPoint, Integer>) hashMap, resultPointsAndTransitions2.a());
        a((Map<ResultPoint, Integer>) hashMap, resultPointsAndTransitions2.b());
        ResultPoint resultPoint9 = null;
        ResultPoint resultPoint10 = null;
        for (Map.Entry entry : hashMap.entrySet()) {
            ResultPoint resultPoint11 = (ResultPoint) entry.getKey();
            if (((Integer) entry.getValue()).intValue() == 2) {
                resultPoint9 = resultPoint11;
            } else if (resultPoint8 == null) {
                resultPoint8 = resultPoint11;
            } else {
                resultPoint10 = resultPoint11;
            }
        }
        if (resultPoint8 == null || resultPoint9 == null || resultPoint10 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint[] resultPointArr = {resultPoint8, resultPoint9, resultPoint10};
        ResultPoint.a(resultPointArr);
        ResultPoint resultPoint12 = resultPointArr[0];
        ResultPoint resultPoint13 = resultPointArr[1];
        ResultPoint resultPoint14 = resultPointArr[2];
        if (!hashMap.containsKey(resultPoint4)) {
            resultPoint = resultPoint4;
        } else if (!hashMap.containsKey(resultPoint5)) {
            resultPoint = resultPoint5;
        } else {
            resultPoint = !hashMap.containsKey(resultPoint6) ? resultPoint6 : resultPoint7;
        }
        int c = b(resultPoint14, resultPoint).c();
        int c2 = b(resultPoint12, resultPoint).c();
        if ((c & 1) == 1) {
            c++;
        }
        int i = c + 2;
        if ((c2 & 1) == 1) {
            c2++;
        }
        int i2 = c2 + 2;
        if (i * 4 >= i2 * 7 || i2 * 4 >= i * 7) {
            resultPoint2 = resultPoint14;
            ResultPoint a3 = a(resultPoint13, resultPoint12, resultPoint14, resultPoint, i, i2);
            if (a3 == null) {
                a3 = resultPoint;
            }
            int c3 = b(resultPoint2, resultPoint3).c();
            int c4 = b(resultPoint12, resultPoint3).c();
            if ((c3 & 1) == 1) {
                c3++;
            }
            int i3 = c3;
            if ((c4 & 1) == 1) {
                c4++;
            }
            bitMatrix = a(this.f1670a, resultPoint2, resultPoint13, resultPoint12, resultPoint3, i3, c4);
        } else {
            resultPoint3 = a(resultPoint13, resultPoint12, resultPoint14, resultPoint, Math.min(i2, i));
            if (resultPoint3 == null) {
                resultPoint3 = resultPoint;
            }
            int max = Math.max(b(resultPoint14, resultPoint3).c(), b(resultPoint12, resultPoint3).c()) + 1;
            if ((max & 1) == 1) {
                max++;
            }
            int i4 = max;
            bitMatrix = a(this.f1670a, resultPoint14, resultPoint13, resultPoint12, resultPoint3, i4, i4);
            resultPoint2 = resultPoint14;
        }
        return new DetectorResult(bitMatrix, new ResultPoint[]{resultPoint2, resultPoint13, resultPoint12, resultPoint3});
    }

    private ResultPoint a(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i, int i2) {
        float a2 = ((float) a(resultPoint, resultPoint2)) / ((float) i);
        float a3 = (float) a(resultPoint3, resultPoint4);
        ResultPoint resultPoint5 = new ResultPoint(resultPoint4.a() + (((resultPoint4.a() - resultPoint3.a()) / a3) * a2), resultPoint4.b() + (a2 * ((resultPoint4.b() - resultPoint3.b()) / a3)));
        float a4 = ((float) a(resultPoint, resultPoint3)) / ((float) i2);
        float a5 = (float) a(resultPoint2, resultPoint4);
        ResultPoint resultPoint6 = new ResultPoint(resultPoint4.a() + (((resultPoint4.a() - resultPoint2.a()) / a5) * a4), resultPoint4.b() + (a4 * ((resultPoint4.b() - resultPoint2.b()) / a5)));
        if (a(resultPoint5)) {
            return (a(resultPoint6) && Math.abs(i - b(resultPoint3, resultPoint5).c()) + Math.abs(i2 - b(resultPoint2, resultPoint5).c()) > Math.abs(i - b(resultPoint3, resultPoint6).c()) + Math.abs(i2 - b(resultPoint2, resultPoint6).c())) ? resultPoint6 : resultPoint5;
        }
        if (a(resultPoint6)) {
            return resultPoint6;
        }
        return null;
    }

    private ResultPoint a(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i) {
        float f = (float) i;
        float a2 = ((float) a(resultPoint, resultPoint2)) / f;
        float a3 = (float) a(resultPoint3, resultPoint4);
        ResultPoint resultPoint5 = new ResultPoint(resultPoint4.a() + (((resultPoint4.a() - resultPoint3.a()) / a3) * a2), resultPoint4.b() + (a2 * ((resultPoint4.b() - resultPoint3.b()) / a3)));
        float a4 = ((float) a(resultPoint, resultPoint3)) / f;
        float a5 = (float) a(resultPoint2, resultPoint4);
        ResultPoint resultPoint6 = new ResultPoint(resultPoint4.a() + (((resultPoint4.a() - resultPoint2.a()) / a5) * a4), resultPoint4.b() + (a4 * ((resultPoint4.b() - resultPoint2.b()) / a5)));
        if (!a(resultPoint5)) {
            if (a(resultPoint6)) {
                return resultPoint6;
            }
            return null;
        } else if (!a(resultPoint6)) {
            return resultPoint5;
        } else {
            return Math.abs(b(resultPoint3, resultPoint5).c() - b(resultPoint2, resultPoint5).c()) <= Math.abs(b(resultPoint3, resultPoint6).c() - b(resultPoint2, resultPoint6).c()) ? resultPoint5 : resultPoint6;
        }
    }

    private boolean a(ResultPoint resultPoint) {
        return resultPoint.a() >= 0.0f && resultPoint.a() < ((float) this.f1670a.f()) && resultPoint.b() > 0.0f && resultPoint.b() < ((float) this.f1670a.g());
    }

    private static int a(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.a(ResultPoint.a(resultPoint, resultPoint2));
    }

    private static void a(Map<ResultPoint, Integer> map, ResultPoint resultPoint) {
        Integer num = map.get(resultPoint);
        int i = 1;
        if (num != null) {
            i = 1 + num.intValue();
        }
        map.put(resultPoint, Integer.valueOf(i));
    }

    private static BitMatrix a(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i, int i2) throws NotFoundException {
        float f = ((float) i) - 0.5f;
        float f2 = ((float) i2) - 0.5f;
        return GridSampler.a().a(bitMatrix, i, i2, 0.5f, 0.5f, f, 0.5f, f, f2, 0.5f, f2, resultPoint.a(), resultPoint.b(), resultPoint4.a(), resultPoint4.b(), resultPoint3.a(), resultPoint3.b(), resultPoint2.a(), resultPoint2.b());
    }

    private ResultPointsAndTransitions b(ResultPoint resultPoint, ResultPoint resultPoint2) {
        int a2 = (int) resultPoint.a();
        int b2 = (int) resultPoint.b();
        int a3 = (int) resultPoint2.a();
        int b3 = (int) resultPoint2.b();
        int i = 0;
        int i2 = 1;
        boolean z = Math.abs(b3 - b2) > Math.abs(a3 - a2);
        if (z) {
            int i3 = b2;
            b2 = a2;
            a2 = i3;
            int i4 = b3;
            b3 = a3;
            a3 = i4;
        }
        int abs = Math.abs(a3 - a2);
        int abs2 = Math.abs(b3 - b2);
        int i5 = (-abs) / 2;
        int i6 = b2 < b3 ? 1 : -1;
        if (a2 >= a3) {
            i2 = -1;
        }
        boolean a4 = this.f1670a.a(z ? b2 : a2, z ? a2 : b2);
        while (a2 != a3) {
            boolean a5 = this.f1670a.a(z ? b2 : a2, z ? a2 : b2);
            if (a5 != a4) {
                i++;
                a4 = a5;
            }
            i5 += abs2;
            if (i5 > 0) {
                if (b2 == b3) {
                    break;
                }
                b2 += i6;
                i5 -= abs;
            }
            a2 += i2;
        }
        return new ResultPointsAndTransitions(resultPoint, resultPoint2, i);
    }

    private static final class ResultPointsAndTransitions {

        /* renamed from: a  reason: collision with root package name */
        private final ResultPoint f1671a;
        private final ResultPoint b;
        private final int c;

        private ResultPointsAndTransitions(ResultPoint resultPoint, ResultPoint resultPoint2, int i) {
            this.f1671a = resultPoint;
            this.b = resultPoint2;
            this.c = i;
        }

        /* access modifiers changed from: package-private */
        public ResultPoint a() {
            return this.f1671a;
        }

        /* access modifiers changed from: package-private */
        public ResultPoint b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public String toString() {
            return this.f1671a + "/" + this.b + IOUtils.f15883a + this.c;
        }
    }

    private static final class ResultPointsAndTransitionsComparator implements Serializable, Comparator<ResultPointsAndTransitions> {
        private ResultPointsAndTransitionsComparator() {
        }

        public int compare(ResultPointsAndTransitions resultPointsAndTransitions, ResultPointsAndTransitions resultPointsAndTransitions2) {
            return resultPointsAndTransitions.c() - resultPointsAndTransitions2.c();
        }
    }
}
