package com.xiaomi.zxing.pdf417.detector;

import com.xiaomi.zxing.BinaryBitmap;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.common.BitMatrix;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class Detector {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f1747a = {0, 4, 1, 5};
    private static final int[] b = {6, 2, 7, 3};
    private static final float c = 0.42f;
    private static final float d = 0.8f;
    private static final int[] e = {8, 1, 1, 1, 1, 1, 1, 3};
    private static final int[] f = {7, 1, 1, 3, 1, 1, 1, 2, 1};
    private static final int g = 3;
    private static final int h = 5;
    private static final int i = 25;
    private static final int j = 5;
    private static final int k = 10;

    private Detector() {
    }

    public static PDF417DetectorResult a(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map, boolean z) throws NotFoundException {
        BitMatrix c2 = binaryBitmap.c();
        List<ResultPoint[]> a2 = a(z, c2);
        if (a2.isEmpty()) {
            c2 = c2.clone();
            c2.b();
            a2 = a(z, c2);
        }
        return new PDF417DetectorResult(c2, a2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0028, code lost:
        if (r4.hasNext() == false) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        r5 = (com.xiaomi.zxing.ResultPoint[]) r4.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        if (r5[1] == null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0034, code lost:
        r3 = (int) java.lang.Math.max((float) r3, r5[1].b());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        if (r5[3] == null) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3 = java.lang.Math.max(r3, (int) r5[3].b());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        if (r5 != false) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        r4 = r0.iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<com.xiaomi.zxing.ResultPoint[]> a(boolean r8, com.xiaomi.zxing.common.BitMatrix r9) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 1
            r2 = 0
            r3 = 0
        L_0x0008:
            r4 = 0
            r5 = 0
        L_0x000a:
            int r6 = r9.g()
            if (r3 >= r6) goto L_0x0080
            com.xiaomi.zxing.ResultPoint[] r4 = a((com.xiaomi.zxing.common.BitMatrix) r9, (int) r3, (int) r4)
            r6 = r4[r2]
            if (r6 != 0) goto L_0x0053
            r6 = 3
            r7 = r4[r6]
            if (r7 != 0) goto L_0x0053
            if (r5 != 0) goto L_0x0020
            goto L_0x0080
        L_0x0020:
            java.util.Iterator r4 = r0.iterator()
        L_0x0024:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0050
            java.lang.Object r5 = r4.next()
            com.xiaomi.zxing.ResultPoint[] r5 = (com.xiaomi.zxing.ResultPoint[]) r5
            r7 = r5[r1]
            if (r7 == 0) goto L_0x0040
            float r3 = (float) r3
            r7 = r5[r1]
            float r7 = r7.b()
            float r3 = java.lang.Math.max(r3, r7)
            int r3 = (int) r3
        L_0x0040:
            r7 = r5[r6]
            if (r7 == 0) goto L_0x0024
            r5 = r5[r6]
            float r5 = r5.b()
            int r5 = (int) r5
            int r3 = java.lang.Math.max(r3, r5)
            goto L_0x0024
        L_0x0050:
            int r3 = r3 + 5
            goto L_0x0008
        L_0x0053:
            r0.add(r4)
            if (r8 != 0) goto L_0x0059
            goto L_0x0080
        L_0x0059:
            r3 = 2
            r5 = r4[r3]
            if (r5 == 0) goto L_0x006e
            r5 = r4[r3]
            float r5 = r5.a()
            int r5 = (int) r5
            r3 = r4[r3]
            float r3 = r3.b()
            int r3 = (int) r3
        L_0x006c:
            r4 = r5
            goto L_0x007e
        L_0x006e:
            r3 = 4
            r5 = r4[r3]
            float r5 = r5.a()
            int r5 = (int) r5
            r3 = r4[r3]
            float r3 = r3.b()
            int r3 = (int) r3
            goto L_0x006c
        L_0x007e:
            r5 = 1
            goto L_0x000a
        L_0x0080:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.pdf417.detector.Detector.a(boolean, com.xiaomi.zxing.common.BitMatrix):java.util.List");
    }

    private static ResultPoint[] a(BitMatrix bitMatrix, int i2, int i3) {
        int g2 = bitMatrix.g();
        int f2 = bitMatrix.f();
        ResultPoint[] resultPointArr = new ResultPoint[8];
        a(resultPointArr, a(bitMatrix, g2, f2, i2, i3, e), f1747a);
        if (resultPointArr[4] != null) {
            i3 = (int) resultPointArr[4].a();
            i2 = (int) resultPointArr[4].b();
        }
        a(resultPointArr, a(bitMatrix, g2, f2, i2, i3, f), b);
        return resultPointArr;
    }

    private static void a(ResultPoint[] resultPointArr, ResultPoint[] resultPointArr2, int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            resultPointArr[iArr[i2]] = resultPointArr2[i2];
        }
    }

    private static ResultPoint[] a(BitMatrix bitMatrix, int i2, int i3, int i4, int i5, int[] iArr) {
        int i6;
        boolean z;
        int i7;
        int[] iArr2;
        int i8 = i2;
        ResultPoint[] resultPointArr = new ResultPoint[4];
        int[] iArr3 = new int[iArr.length];
        int i9 = i4;
        while (true) {
            if (i9 >= i8) {
                z = false;
                break;
            }
            int[] a2 = a(bitMatrix, i5, i9, i3, false, iArr, iArr3);
            if (a2 != null) {
                while (true) {
                    iArr2 = a2;
                    if (i9 <= 0) {
                        break;
                    }
                    i9--;
                    a2 = a(bitMatrix, i5, i9, i3, false, iArr, iArr3);
                    if (a2 == null) {
                        i9++;
                        break;
                    }
                }
                float f2 = (float) i9;
                resultPointArr[0] = new ResultPoint((float) iArr2[0], f2);
                resultPointArr[1] = new ResultPoint((float) iArr2[1], f2);
                z = true;
            } else {
                i9 += 5;
            }
        }
        int i10 = i9 + 1;
        if (z) {
            int[] iArr4 = {(int) resultPointArr[0].a(), (int) resultPointArr[1].a()};
            int i11 = i10;
            int i12 = 0;
            while (true) {
                if (i11 >= i8) {
                    i7 = i12;
                    break;
                }
                i7 = i12;
                int[] a3 = a(bitMatrix, iArr4[0], i11, i3, false, iArr, iArr3);
                if (a3 != null && Math.abs(iArr4[0] - a3[0]) < 5 && Math.abs(iArr4[1] - a3[1]) < 5) {
                    iArr4 = a3;
                    i12 = 0;
                } else if (i7 > 25) {
                    break;
                } else {
                    i12 = i7 + 1;
                }
                i11++;
            }
            i10 = i11 - (i7 + 1);
            float f3 = (float) i10;
            resultPointArr[2] = new ResultPoint((float) iArr4[0], f3);
            resultPointArr[3] = new ResultPoint((float) iArr4[1], f3);
        }
        if (i10 - i9 < 10) {
            for (i6 = 0; i6 < resultPointArr.length; i6++) {
                resultPointArr[i6] = null;
            }
        }
        return resultPointArr;
    }

    private static int[] a(BitMatrix bitMatrix, int i2, int i3, int i4, boolean z, int[] iArr, int[] iArr2) {
        Arrays.fill(iArr2, 0, iArr2.length, 0);
        int length = iArr.length;
        int i5 = 0;
        while (bitMatrix.a(i2, i3) && i2 > 0) {
            int i6 = i5 + 1;
            if (i5 >= 3) {
                break;
            }
            i2--;
            i5 = i6;
        }
        int i7 = i2;
        int i8 = 0;
        while (true) {
            boolean z2 = true;
            if (i2 < i4) {
                if (bitMatrix.a(i2, i3) ^ z) {
                    iArr2[i8] = iArr2[i8] + 1;
                } else {
                    int i9 = length - 1;
                    if (i8 != i9) {
                        i8++;
                    } else if (a(iArr2, iArr, 0.8f) < c) {
                        return new int[]{i7, i2};
                    } else {
                        i7 += iArr2[0] + iArr2[1];
                        int i10 = length - 2;
                        System.arraycopy(iArr2, 2, iArr2, 0, i10);
                        iArr2[i10] = 0;
                        iArr2[i9] = 0;
                        i8--;
                    }
                    iArr2[i8] = 1;
                    if (z) {
                        z2 = false;
                    }
                    z = z2;
                }
                i2++;
            } else if (i8 != length - 1 || a(iArr2, iArr, 0.8f) >= c) {
                return null;
            } else {
                return new int[]{i7, i2 - 1};
            }
        }
    }

    private static float a(int[] iArr, int[] iArr2, float f2) {
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            i2 += iArr[i4];
            i3 += iArr2[i4];
        }
        if (i2 < i3) {
            return Float.POSITIVE_INFINITY;
        }
        float f3 = (float) i2;
        float f4 = f3 / ((float) i3);
        float f5 = f2 * f4;
        float f6 = 0.0f;
        for (int i5 = 0; i5 < length; i5++) {
            int i6 = iArr[i5];
            float f7 = ((float) iArr2[i5]) * f4;
            float f8 = (float) i6;
            float f9 = f8 > f7 ? f8 - f7 : f7 - f8;
            if (f9 > f5) {
                return Float.POSITIVE_INFINITY;
            }
            f6 += f9;
        }
        return f6 / f3;
    }
}
