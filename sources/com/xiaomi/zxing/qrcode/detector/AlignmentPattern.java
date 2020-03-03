package com.xiaomi.zxing.qrcode.detector;

import com.xiaomi.zxing.ResultPoint;

public final class AlignmentPattern extends ResultPoint {

    /* renamed from: a  reason: collision with root package name */
    private final float f1766a;

    AlignmentPattern(float f, float f2, float f3) {
        super(f, f2);
        this.f1766a = f3;
    }

    /* access modifiers changed from: package-private */
    public boolean a(float f, float f2, float f3) {
        if (Math.abs(f2 - b()) > f || Math.abs(f3 - a()) > f) {
            return false;
        }
        float abs = Math.abs(f - this.f1766a);
        if (abs <= 1.0f || abs <= this.f1766a) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public AlignmentPattern b(float f, float f2, float f3) {
        return new AlignmentPattern((a() + f2) / 2.0f, (b() + f) / 2.0f, (this.f1766a + f3) / 2.0f);
    }
}
