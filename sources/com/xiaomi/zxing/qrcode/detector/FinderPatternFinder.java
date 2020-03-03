package com.xiaomi.zxing.qrcode.detector;

import com.tencent.smtt.sdk.TbsListener;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.ResultPointCallback;
import com.xiaomi.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class FinderPatternFinder {

    /* renamed from: a  reason: collision with root package name */
    protected static final int f1770a = 3;
    protected static final int b = 57;
    private static final int c = 2;
    private final BitMatrix d;
    private final List<FinderPattern> e;
    private boolean f;
    private final int[] g;
    private final ResultPointCallback h;

    public FinderPatternFinder(BitMatrix bitMatrix) {
        this(bitMatrix, (ResultPointCallback) null);
    }

    public FinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        this.d = bitMatrix;
        this.e = new ArrayList();
        this.g = new int[5];
        this.h = resultPointCallback;
    }

    /* access modifiers changed from: protected */
    public final BitMatrix a() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public final List<FinderPattern> b() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public final FinderPatternInfo b(Map<DecodeHintType, ?> map) throws NotFoundException {
        Map<DecodeHintType, ?> map2 = map;
        boolean z = map2 != null && map2.containsKey(DecodeHintType.TRY_HARDER);
        boolean z2 = map2 != null && map2.containsKey(DecodeHintType.PURE_BARCODE);
        int g2 = this.d.g();
        int f2 = this.d.f();
        int i = (g2 * 3) / TbsListener.ErrorCode.INCR_ERROR_DETAIL;
        if (i < 3 || z) {
            i = 3;
        }
        int[] iArr = new int[5];
        int i2 = i - 1;
        int i3 = i;
        boolean z3 = false;
        while (i2 < g2 && !z3) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            boolean z4 = z3;
            int i4 = i3;
            int i5 = 0;
            int i6 = 0;
            while (i5 < f2) {
                if (this.d.a(i5, i2)) {
                    if ((i6 & 1) == 1) {
                        i6++;
                    }
                    iArr[i6] = iArr[i6] + 1;
                } else if ((i6 & 1) != 0) {
                    iArr[i6] = iArr[i6] + 1;
                } else if (i6 == 4) {
                    if (!a(iArr)) {
                        iArr[0] = iArr[2];
                        iArr[1] = iArr[3];
                        iArr[2] = iArr[4];
                        iArr[3] = 1;
                        iArr[4] = 0;
                    } else if (a(iArr, i2, i5, z2)) {
                        if (this.f) {
                            z4 = e();
                        } else {
                            int d2 = d();
                            if (d2 > iArr[2]) {
                                i2 += (d2 - iArr[2]) - 2;
                                i5 = f2 - 1;
                            }
                        }
                        iArr[0] = 0;
                        iArr[1] = 0;
                        iArr[2] = 0;
                        iArr[3] = 0;
                        iArr[4] = 0;
                        i6 = 0;
                        i4 = 2;
                    } else {
                        iArr[0] = iArr[2];
                        iArr[1] = iArr[3];
                        iArr[2] = iArr[4];
                        iArr[3] = 1;
                        iArr[4] = 0;
                    }
                    i6 = 3;
                } else {
                    i6++;
                    iArr[i6] = iArr[i6] + 1;
                }
                i5++;
            }
            if (!a(iArr) || !a(iArr, i2, f2, z2)) {
                i3 = i4;
            } else {
                int i7 = iArr[0];
                if (this.f) {
                    i3 = i7;
                    z3 = e();
                    i2 += i3;
                } else {
                    i3 = i7;
                }
            }
            z3 = z4;
            i2 += i3;
        }
        FinderPattern[] f3 = f();
        ResultPoint.a(f3);
        return new FinderPatternInfo(f3);
    }

    private static float a(int[] iArr, int i) {
        return ((float) ((i - iArr[4]) - iArr[3])) - (((float) iArr[2]) / 2.0f);
    }

    protected static boolean a(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 7) {
            return false;
        }
        float f2 = ((float) i) / 7.0f;
        float f3 = f2 / 2.0f;
        if (Math.abs(f2 - ((float) iArr[0])) >= f3 || Math.abs(f2 - ((float) iArr[1])) >= f3 || Math.abs((f2 * 3.0f) - ((float) iArr[2])) >= 3.0f * f3 || Math.abs(f2 - ((float) iArr[3])) >= f3 || Math.abs(f2 - ((float) iArr[4])) >= f3) {
            return false;
        }
        return true;
    }

    private int[] c() {
        this.g[0] = 0;
        this.g[1] = 0;
        this.g[2] = 0;
        this.g[3] = 0;
        this.g[4] = 0;
        return this.g;
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a1 A[LOOP:4: B:41:0x00a1->B:49:0x00b6, LOOP_START, PHI: r10 
      PHI: (r10v2 int) = (r10v1 int), (r10v5 int) binds: [B:40:0x009d, B:49:0x00b6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00c9 A[LOOP:5: B:55:0x00c9->B:63:0x00de, LOOP_START, PHI: r10 
      PHI: (r10v3 int) = (r10v2 int), (r10v4 int) binds: [B:54:0x00c6, B:63:0x00de] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x010d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x010e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(int r16, int r17, int r18, int r19) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r18
            int[] r4 = r15.c()
            r5 = 0
            r6 = 0
        L_0x000d:
            r7 = 2
            r8 = 1
            if (r1 < r6) goto L_0x0027
            if (r2 < r6) goto L_0x0027
            com.xiaomi.zxing.common.BitMatrix r9 = r0.d
            int r10 = r2 - r6
            int r11 = r1 - r6
            boolean r9 = r9.a((int) r10, (int) r11)
            if (r9 == 0) goto L_0x0027
            r9 = r4[r7]
            int r9 = r9 + r8
            r4[r7] = r9
            int r6 = r6 + 1
            goto L_0x000d
        L_0x0027:
            if (r1 < r6) goto L_0x0110
            if (r2 >= r6) goto L_0x002d
            goto L_0x0110
        L_0x002d:
            if (r1 < r6) goto L_0x0049
            if (r2 < r6) goto L_0x0049
            com.xiaomi.zxing.common.BitMatrix r9 = r0.d
            int r10 = r2 - r6
            int r11 = r1 - r6
            boolean r9 = r9.a((int) r10, (int) r11)
            if (r9 != 0) goto L_0x0049
            r9 = r4[r8]
            if (r9 > r3) goto L_0x0049
            r9 = r4[r8]
            int r9 = r9 + r8
            r4[r8] = r9
            int r6 = r6 + 1
            goto L_0x002d
        L_0x0049:
            if (r1 < r6) goto L_0x010f
            if (r2 < r6) goto L_0x010f
            r9 = r4[r8]
            if (r9 <= r3) goto L_0x0053
            goto L_0x010f
        L_0x0053:
            if (r1 < r6) goto L_0x006f
            if (r2 < r6) goto L_0x006f
            com.xiaomi.zxing.common.BitMatrix r9 = r0.d
            int r10 = r2 - r6
            int r11 = r1 - r6
            boolean r9 = r9.a((int) r10, (int) r11)
            if (r9 == 0) goto L_0x006f
            r9 = r4[r5]
            if (r9 > r3) goto L_0x006f
            r9 = r4[r5]
            int r9 = r9 + r8
            r4[r5] = r9
            int r6 = r6 + 1
            goto L_0x0053
        L_0x006f:
            r6 = r4[r5]
            if (r6 <= r3) goto L_0x0074
            return r5
        L_0x0074:
            com.xiaomi.zxing.common.BitMatrix r6 = r0.d
            int r6 = r6.g()
            com.xiaomi.zxing.common.BitMatrix r9 = r0.d
            int r9 = r9.f()
            r10 = 1
        L_0x0081:
            int r11 = r1 + r10
            if (r11 >= r6) goto L_0x0099
            int r12 = r2 + r10
            if (r12 >= r9) goto L_0x0099
            com.xiaomi.zxing.common.BitMatrix r13 = r0.d
            boolean r12 = r13.a((int) r12, (int) r11)
            if (r12 == 0) goto L_0x0099
            r11 = r4[r7]
            int r11 = r11 + r8
            r4[r7] = r11
            int r10 = r10 + 1
            goto L_0x0081
        L_0x0099:
            if (r11 >= r6) goto L_0x010e
            int r11 = r2 + r10
            if (r11 < r9) goto L_0x00a1
            goto L_0x010e
        L_0x00a1:
            int r11 = r1 + r10
            r12 = 3
            if (r11 >= r6) goto L_0x00be
            int r13 = r2 + r10
            if (r13 >= r9) goto L_0x00be
            com.xiaomi.zxing.common.BitMatrix r14 = r0.d
            boolean r13 = r14.a((int) r13, (int) r11)
            if (r13 != 0) goto L_0x00be
            r13 = r4[r12]
            if (r13 >= r3) goto L_0x00be
            r11 = r4[r12]
            int r11 = r11 + r8
            r4[r12] = r11
            int r10 = r10 + 1
            goto L_0x00a1
        L_0x00be:
            if (r11 >= r6) goto L_0x010d
            int r11 = r2 + r10
            if (r11 >= r9) goto L_0x010d
            r11 = r4[r12]
            if (r11 < r3) goto L_0x00c9
            goto L_0x010d
        L_0x00c9:
            int r11 = r1 + r10
            r13 = 4
            if (r11 >= r6) goto L_0x00e7
            int r14 = r2 + r10
            if (r14 >= r9) goto L_0x00e7
            com.xiaomi.zxing.common.BitMatrix r12 = r0.d
            boolean r11 = r12.a((int) r14, (int) r11)
            if (r11 == 0) goto L_0x00e7
            r11 = r4[r13]
            if (r11 >= r3) goto L_0x00e7
            r11 = r4[r13]
            int r11 = r11 + r8
            r4[r13] = r11
            int r10 = r10 + 1
            r12 = 3
            goto L_0x00c9
        L_0x00e7:
            r1 = r4[r13]
            if (r1 < r3) goto L_0x00ec
            return r5
        L_0x00ec:
            r1 = r4[r5]
            r2 = r4[r8]
            int r1 = r1 + r2
            r2 = r4[r7]
            int r1 = r1 + r2
            r2 = 3
            r2 = r4[r2]
            int r1 = r1 + r2
            r2 = r4[r13]
            int r1 = r1 + r2
            int r1 = r1 - r19
            int r1 = java.lang.Math.abs(r1)
            int r2 = r19 * 2
            if (r1 >= r2) goto L_0x010c
            boolean r1 = a(r4)
            if (r1 == 0) goto L_0x010c
            r5 = 1
        L_0x010c:
            return r5
        L_0x010d:
            return r5
        L_0x010e:
            return r5
        L_0x010f:
            return r5
        L_0x0110:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.qrcode.detector.FinderPatternFinder.a(int, int, int, int):boolean");
    }

    private float b(int i, int i2, int i3, int i4) {
        BitMatrix bitMatrix = this.d;
        int g2 = bitMatrix.g();
        int[] c2 = c();
        int i5 = i;
        while (i5 >= 0 && bitMatrix.a(i2, i5)) {
            c2[2] = c2[2] + 1;
            i5--;
        }
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !bitMatrix.a(i2, i5) && c2[1] <= i3) {
            c2[1] = c2[1] + 1;
            i5--;
        }
        if (i5 < 0 || c2[1] > i3) {
            return Float.NaN;
        }
        while (i5 >= 0 && bitMatrix.a(i2, i5) && c2[0] <= i3) {
            c2[0] = c2[0] + 1;
            i5--;
        }
        if (c2[0] > i3) {
            return Float.NaN;
        }
        int i6 = i + 1;
        while (i6 < g2 && bitMatrix.a(i2, i6)) {
            c2[2] = c2[2] + 1;
            i6++;
        }
        if (i6 == g2) {
            return Float.NaN;
        }
        while (i6 < g2 && !bitMatrix.a(i2, i6) && c2[3] < i3) {
            c2[3] = c2[3] + 1;
            i6++;
        }
        if (i6 == g2 || c2[3] >= i3) {
            return Float.NaN;
        }
        while (i6 < g2 && bitMatrix.a(i2, i6) && c2[4] < i3) {
            c2[4] = c2[4] + 1;
            i6++;
        }
        if (c2[4] < i3 && Math.abs(((((c2[0] + c2[1]) + c2[2]) + c2[3]) + c2[4]) - i4) * 5 < i4 * 2 && a(c2)) {
            return a(c2, i6);
        }
        return Float.NaN;
    }

    private float c(int i, int i2, int i3, int i4) {
        BitMatrix bitMatrix = this.d;
        int f2 = bitMatrix.f();
        int[] c2 = c();
        int i5 = i;
        while (i5 >= 0 && bitMatrix.a(i5, i2)) {
            c2[2] = c2[2] + 1;
            i5--;
        }
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !bitMatrix.a(i5, i2) && c2[1] <= i3) {
            c2[1] = c2[1] + 1;
            i5--;
        }
        if (i5 < 0 || c2[1] > i3) {
            return Float.NaN;
        }
        while (i5 >= 0 && bitMatrix.a(i5, i2) && c2[0] <= i3) {
            c2[0] = c2[0] + 1;
            i5--;
        }
        if (c2[0] > i3) {
            return Float.NaN;
        }
        int i6 = i + 1;
        while (i6 < f2 && bitMatrix.a(i6, i2)) {
            c2[2] = c2[2] + 1;
            i6++;
        }
        if (i6 == f2) {
            return Float.NaN;
        }
        while (i6 < f2 && !bitMatrix.a(i6, i2) && c2[3] < i3) {
            c2[3] = c2[3] + 1;
            i6++;
        }
        if (i6 == f2 || c2[3] >= i3) {
            return Float.NaN;
        }
        while (i6 < f2 && bitMatrix.a(i6, i2) && c2[4] < i3) {
            c2[4] = c2[4] + 1;
            i6++;
        }
        if (c2[4] < i3 && Math.abs(((((c2[0] + c2[1]) + c2[2]) + c2[3]) + c2[4]) - i4) * 5 < i4 && a(c2)) {
            return a(c2, i6);
        }
        return Float.NaN;
    }

    /* access modifiers changed from: protected */
    public final boolean a(int[] iArr, int i, int i2, boolean z) {
        boolean z2 = false;
        int i3 = iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4];
        int a2 = (int) a(iArr, i2);
        float b2 = b(i, a2, iArr[2], i3);
        if (!Float.isNaN(b2)) {
            int i4 = (int) b2;
            float c2 = c(a2, i4, iArr[2], i3);
            if (!Float.isNaN(c2) && (!z || a(i4, (int) c2, iArr[2], i3))) {
                float f2 = ((float) i3) / 7.0f;
                int i5 = 0;
                while (true) {
                    if (i5 >= this.e.size()) {
                        break;
                    }
                    FinderPattern finderPattern = this.e.get(i5);
                    if (finderPattern.a(f2, b2, c2)) {
                        this.e.set(i5, finderPattern.b(b2, c2, f2));
                        z2 = true;
                        break;
                    }
                    i5++;
                }
                if (!z2) {
                    FinderPattern finderPattern2 = new FinderPattern(c2, b2, f2);
                    this.e.add(finderPattern2);
                    if (this.h != null) {
                        this.h.a(finderPattern2);
                    }
                }
                return true;
            }
        }
        return false;
    }

    private int d() {
        if (this.e.size() <= 1) {
            return 0;
        }
        FinderPattern finderPattern = null;
        for (FinderPattern next : this.e) {
            if (next.d() >= 2) {
                if (finderPattern == null) {
                    finderPattern = next;
                } else {
                    this.f = true;
                    return ((int) (Math.abs(finderPattern.a() - next.a()) - Math.abs(finderPattern.b() - next.b()))) / 2;
                }
            }
        }
        return 0;
    }

    private boolean e() {
        int size = this.e.size();
        float f2 = 0.0f;
        int i = 0;
        float f3 = 0.0f;
        for (FinderPattern next : this.e) {
            if (next.d() >= 2) {
                i++;
                f3 += next.c();
            }
        }
        if (i < 3) {
            return false;
        }
        float f4 = f3 / ((float) size);
        for (FinderPattern c2 : this.e) {
            f2 += Math.abs(c2.c() - f4);
        }
        if (f2 <= f3 * 0.05f) {
            return true;
        }
        return false;
    }

    private FinderPattern[] f() throws NotFoundException {
        int size = this.e.size();
        if (size >= 3) {
            float f2 = 0.0f;
            if (size > 3) {
                float f3 = 0.0f;
                float f4 = 0.0f;
                for (FinderPattern c2 : this.e) {
                    float c3 = c2.c();
                    f3 += c3;
                    f4 += c3 * c3;
                }
                float f5 = (float) size;
                float f6 = f3 / f5;
                float sqrt = (float) Math.sqrt((double) ((f4 / f5) - (f6 * f6)));
                Collections.sort(this.e, new FurthestFromAverageComparator(f6));
                float max = Math.max(0.2f * f6, sqrt);
                int i = 0;
                while (i < this.e.size() && this.e.size() > 3) {
                    if (Math.abs(this.e.get(i).c() - f6) > max) {
                        this.e.remove(i);
                        i--;
                    }
                    i++;
                }
            }
            if (this.e.size() > 3) {
                for (FinderPattern c4 : this.e) {
                    f2 += c4.c();
                }
                Collections.sort(this.e, new CenterComparator(f2 / ((float) this.e.size())));
                this.e.subList(3, this.e.size()).clear();
            }
            return new FinderPattern[]{this.e.get(0), this.e.get(1), this.e.get(2)};
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static final class FurthestFromAverageComparator implements Serializable, Comparator<FinderPattern> {
        private final float average;

        private FurthestFromAverageComparator(float f) {
            this.average = f;
        }

        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            float abs = Math.abs(finderPattern2.c() - this.average);
            float abs2 = Math.abs(finderPattern.c() - this.average);
            if (abs < abs2) {
                return -1;
            }
            return abs == abs2 ? 0 : 1;
        }
    }

    private static final class CenterComparator implements Serializable, Comparator<FinderPattern> {
        private final float average;

        private CenterComparator(float f) {
            this.average = f;
        }

        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            if (finderPattern2.d() != finderPattern.d()) {
                return finderPattern2.d() - finderPattern.d();
            }
            float abs = Math.abs(finderPattern2.c() - this.average);
            float abs2 = Math.abs(finderPattern.c() - this.average);
            if (abs < abs2) {
                return 1;
            }
            return abs == abs2 ? 0 : -1;
        }
    }
}
