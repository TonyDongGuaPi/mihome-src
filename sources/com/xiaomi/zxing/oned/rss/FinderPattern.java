package com.xiaomi.zxing.oned.rss;

import com.xiaomi.zxing.ResultPoint;

public final class FinderPattern {

    /* renamed from: a  reason: collision with root package name */
    private final int f1713a;
    private final int[] b;
    private final ResultPoint[] c;

    public FinderPattern(int i, int[] iArr, int i2, int i3, int i4) {
        this.f1713a = i;
        this.b = iArr;
        float f = (float) i4;
        this.c = new ResultPoint[]{new ResultPoint((float) i2, f), new ResultPoint((float) i3, f)};
    }

    public int a() {
        return this.f1713a;
    }

    public int[] b() {
        return this.b;
    }

    public ResultPoint[] c() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if ((obj instanceof FinderPattern) && this.f1713a == ((FinderPattern) obj).f1713a) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.f1713a;
    }
}
