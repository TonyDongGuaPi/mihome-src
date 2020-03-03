package com.xiaomi.zxing.common;

public final class PerspectiveTransform {

    /* renamed from: a  reason: collision with root package name */
    private final float f1653a;
    private final float b;
    private final float c;
    private final float d;
    private final float e;
    private final float f;
    private final float g;
    private final float h;
    private final float i;

    private PerspectiveTransform(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        this.f1653a = f2;
        this.b = f5;
        this.c = f8;
        this.d = f3;
        this.e = f6;
        this.f = f9;
        this.g = f4;
        this.h = f7;
        this.i = f10;
    }

    public static PerspectiveTransform a(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17) {
        return a(f10, f11, f12, f13, f14, f15, f16, f17).a(b(f2, f3, f4, f5, f6, f7, f8, f9));
    }

    public void a(float[] fArr) {
        float[] fArr2 = fArr;
        int length = fArr2.length;
        float f2 = this.f1653a;
        float f3 = this.b;
        float f4 = this.c;
        float f5 = this.d;
        float f6 = this.e;
        float f7 = this.f;
        float f8 = this.g;
        float f9 = this.h;
        float f10 = this.i;
        for (int i2 = 0; i2 < length; i2 += 2) {
            float f11 = fArr2[i2];
            int i3 = i2 + 1;
            float f12 = fArr2[i3];
            float f13 = (f4 * f11) + (f7 * f12) + f10;
            fArr2[i2] = (((f2 * f11) + (f5 * f12)) + f8) / f13;
            fArr2[i3] = (((f11 * f3) + (f12 * f6)) + f9) / f13;
        }
    }

    public void a(float[] fArr, float[] fArr2) {
        int length = fArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            float f2 = fArr[i2];
            float f3 = fArr2[i2];
            float f4 = (this.c * f2) + (this.f * f3) + this.i;
            fArr[i2] = (((this.f1653a * f2) + (this.d * f3)) + this.g) / f4;
            fArr2[i2] = (((this.b * f2) + (this.e * f3)) + this.h) / f4;
        }
    }

    public static PerspectiveTransform a(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        float f10 = ((f2 - f4) + f6) - f8;
        float f11 = ((f3 - f5) + f7) - f9;
        if (f10 == 0.0f && f11 == 0.0f) {
            return new PerspectiveTransform(f4 - f2, f6 - f4, f2, f5 - f3, f7 - f5, f3, 0.0f, 0.0f, 1.0f);
        }
        float f12 = f4 - f6;
        float f13 = f8 - f6;
        float f14 = f5 - f7;
        float f15 = f9 - f7;
        float f16 = (f12 * f15) - (f13 * f14);
        float f17 = ((f15 * f10) - (f13 * f11)) / f16;
        float f18 = ((f12 * f11) - (f10 * f14)) / f16;
        return new PerspectiveTransform((f4 - f2) + (f17 * f4), (f18 * f8) + (f8 - f2), f2, (f5 - f3) + (f17 * f5), (f9 - f3) + (f18 * f9), f3, f17, f18, 1.0f);
    }

    public static PerspectiveTransform b(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        return a(f2, f3, f4, f5, f6, f7, f8, f9).a();
    }

    /* access modifiers changed from: package-private */
    public PerspectiveTransform a() {
        return new PerspectiveTransform((this.e * this.i) - (this.f * this.h), (this.f * this.g) - (this.d * this.i), (this.d * this.h) - (this.e * this.g), (this.c * this.h) - (this.b * this.i), (this.f1653a * this.i) - (this.c * this.g), (this.b * this.g) - (this.f1653a * this.h), (this.b * this.f) - (this.c * this.e), (this.c * this.d) - (this.f1653a * this.f), (this.f1653a * this.e) - (this.b * this.d));
    }

    /* access modifiers changed from: package-private */
    public PerspectiveTransform a(PerspectiveTransform perspectiveTransform) {
        return new PerspectiveTransform((this.g * perspectiveTransform.c) + (this.f1653a * perspectiveTransform.f1653a) + (this.d * perspectiveTransform.b), (this.g * perspectiveTransform.f) + (this.f1653a * perspectiveTransform.d) + (this.d * perspectiveTransform.e), (this.g * perspectiveTransform.i) + (this.f1653a * perspectiveTransform.g) + (this.d * perspectiveTransform.h), (this.h * perspectiveTransform.c) + (this.b * perspectiveTransform.f1653a) + (this.e * perspectiveTransform.b), (this.h * perspectiveTransform.f) + (this.b * perspectiveTransform.d) + (this.e * perspectiveTransform.e), (this.h * perspectiveTransform.i) + (this.b * perspectiveTransform.g) + (this.e * perspectiveTransform.h), (this.i * perspectiveTransform.c) + (this.c * perspectiveTransform.f1653a) + (this.f * perspectiveTransform.b), (this.i * perspectiveTransform.f) + (this.c * perspectiveTransform.d) + (this.f * perspectiveTransform.e), (this.i * perspectiveTransform.i) + (this.c * perspectiveTransform.g) + (this.f * perspectiveTransform.h));
    }
}
