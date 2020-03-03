package com.xiaomi.smarthome.newui;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class MyScaleAnimation extends Animation {

    /* renamed from: a  reason: collision with root package name */
    private final Resources f20342a = null;
    private float b = 1.0f;
    private float c;
    private float d;
    private float e;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private int o = 0;
    private float p = 0.0f;
    private float q = 0.0f;
    private float r;
    private float s;

    public MyScaleAnimation(float f2, int i2, float f3, int i3, float f4) {
        this.c = f2;
        this.d = 1.0f;
        this.e = f2;
        this.p = f3;
        this.n = i2;
        this.q = f4;
        this.o = i3;
        a();
    }

    private void a() {
        if (this.n == 0) {
            this.r = this.p;
        }
        if (this.o == 0) {
            this.s = this.q;
        }
    }

    /* access modifiers changed from: protected */
    public void applyTransformation(float f2, Transformation transformation) {
        float f3;
        float scaleFactor = getScaleFactor();
        float f4 = 1.0f;
        if (this.b == 1.0f && this.c == 1.0f) {
            f3 = 1.0f;
        } else {
            f3 = b(f2);
        }
        if (!(this.d == 1.0f && this.e == 1.0f)) {
            f4 = a(f2);
        }
        if (this.r == 0.0f && this.s == 0.0f) {
            transformation.getMatrix().setScale(f3, f4);
        } else {
            transformation.getMatrix().setScale(f3, f4, this.r * scaleFactor, scaleFactor * this.s);
        }
    }

    private float a(float f2) {
        if (f2 < 0.0f || f2 > 0.5f) {
            f2 = 1.0f - f2;
        }
        return this.d + ((this.e - this.d) * f2);
    }

    private float b(float f2) {
        if (f2 < 0.0f || f2 > 0.5f) {
            f2 = 1.0f - f2;
        }
        return this.b + ((this.c - this.b) * f2);
    }

    /* access modifiers changed from: package-private */
    public float a(float f2, int i2, int i3, int i4, int i5) {
        float f3;
        if (i2 == 6) {
            f3 = TypedValue.complexToFraction(i3, (float) i4, (float) i5);
        } else if (i2 != 5) {
            return f2;
        } else {
            f3 = TypedValue.complexToDimension(i3, this.f20342a.getDisplayMetrics());
        }
        if (i4 == 0) {
            return 1.0f;
        }
        return f3 / ((float) i4);
    }

    public void initialize(int i2, int i3, int i4, int i5) {
        super.initialize(i2, i3, i4, i5);
        this.b = a(this.b, this.f, this.j, i2, i4);
        this.c = a(this.c, this.g, this.k, i2, i4);
        int i6 = i3;
        int i7 = i5;
        this.d = a(this.d, this.h, this.l, i6, i7);
        this.e = a(this.e, this.i, this.m, i6, i7);
        this.r = resolveSize(this.n, this.p, i2, i4);
        this.s = resolveSize(this.o, this.q, i3, i5);
    }
}
