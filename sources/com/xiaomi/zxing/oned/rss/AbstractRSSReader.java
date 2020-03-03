package com.xiaomi.zxing.oned.rss;

import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.common.detector.MathUtils;
import com.xiaomi.zxing.oned.OneDReader;

public abstract class AbstractRSSReader extends OneDReader {

    /* renamed from: a  reason: collision with root package name */
    private static final float f1711a = 0.2f;
    private static final float b = 0.45f;
    private static final float c = 0.7916667f;
    private static final float d = 0.89285713f;
    private final int[] e = new int[4];
    private final int[] f = new int[8];
    private final float[] g = new float[4];
    private final float[] h = new float[4];
    private final int[] i = new int[(this.f.length / 2)];
    private final int[] j = new int[(this.f.length / 2)];

    protected AbstractRSSReader() {
    }

    /* access modifiers changed from: protected */
    public final int[] b() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public final int[] c() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public final float[] d() {
        return this.g;
    }

    /* access modifiers changed from: protected */
    public final float[] e() {
        return this.h;
    }

    /* access modifiers changed from: protected */
    public final int[] f() {
        return this.i;
    }

    /* access modifiers changed from: protected */
    public final int[] g() {
        return this.j;
    }

    protected static int a(int[] iArr, int[][] iArr2) throws NotFoundException {
        for (int i2 = 0; i2 < iArr2.length; i2++) {
            if (a(iArr, iArr2[i2], (float) b) < 0.2f) {
                return i2;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Deprecated
    protected static int a(int[] iArr) {
        return MathUtils.a(iArr);
    }

    protected static void a(int[] iArr, float[] fArr) {
        float f2 = fArr[0];
        int i2 = 0;
        for (int i3 = 1; i3 < iArr.length; i3++) {
            if (fArr[i3] > f2) {
                f2 = fArr[i3];
                i2 = i3;
            }
        }
        iArr[i2] = iArr[i2] + 1;
    }

    protected static void b(int[] iArr, float[] fArr) {
        float f2 = fArr[0];
        int i2 = 0;
        for (int i3 = 1; i3 < iArr.length; i3++) {
            if (fArr[i3] < f2) {
                f2 = fArr[i3];
                i2 = i3;
            }
        }
        iArr[i2] = iArr[i2] - 1;
    }

    protected static boolean b(int[] iArr) {
        int i2 = iArr[0] + iArr[1];
        float f2 = ((float) i2) / ((float) ((iArr[2] + i2) + iArr[3]));
        if (f2 < c || f2 > d) {
            return false;
        }
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        for (int i5 : iArr) {
            if (i5 > i3) {
                i3 = i5;
            }
            if (i5 < i4) {
                i4 = i5;
            }
        }
        if (i3 < i4 * 10) {
            return true;
        }
        return false;
    }
}
