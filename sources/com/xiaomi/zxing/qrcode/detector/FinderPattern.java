package com.xiaomi.zxing.qrcode.detector;

import com.xiaomi.zxing.ResultPoint;

public final class FinderPattern extends ResultPoint {

    /* renamed from: a  reason: collision with root package name */
    private final float f1769a;
    private final int b;

    FinderPattern(float f, float f2, float f3) {
        this(f, f2, f3, 1);
    }

    private FinderPattern(float f, float f2, float f3, int i) {
        super(f, f2);
        this.f1769a = f3;
        this.b = i;
    }

    public float c() {
        return this.f1769a;
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public boolean a(float f, float f2, float f3) {
        if (Math.abs(f2 - b()) > f || Math.abs(f3 - a()) > f) {
            return false;
        }
        float abs = Math.abs(f - this.f1769a);
        if (abs <= 1.0f || abs <= this.f1769a) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public FinderPattern b(float f, float f2, float f3) {
        int i = this.b + 1;
        float a2 = (((float) this.b) * a()) + f2;
        float f4 = (float) i;
        return new FinderPattern(a2 / f4, ((((float) this.b) * b()) + f) / f4, ((((float) this.b) * this.f1769a) + f3) / f4, i);
    }
}
