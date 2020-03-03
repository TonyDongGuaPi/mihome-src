package com.transitionseverywhere;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.util.AttributeSet;

public class ArcMotion extends PathMotion {
    private static final float b = 0.0f;
    private static final float c = 70.0f;
    private static final float d = ((float) Math.tan(Math.toRadians(35.0d)));
    private float e = 0.0f;
    private float f = 0.0f;
    private float g = c;
    private float h = 0.0f;
    private float i = 0.0f;
    private float j = d;

    public ArcMotion() {
    }

    public ArcMotion(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ArcMotion);
        b(obtainStyledAttributes.getFloat(R.styleable.ArcMotion_minimumVerticalAngle, 0.0f));
        a(obtainStyledAttributes.getFloat(R.styleable.ArcMotion_minimumHorizontalAngle, 0.0f));
        c(obtainStyledAttributes.getFloat(R.styleable.ArcMotion_maximumAngle, c));
        obtainStyledAttributes.recycle();
    }

    public void a(float f2) {
        this.e = f2;
        this.h = d(f2);
    }

    public float a() {
        return this.e;
    }

    public void b(float f2) {
        this.f = f2;
        this.i = d(f2);
    }

    public float b() {
        return this.f;
    }

    public void c(float f2) {
        this.g = f2;
        this.j = d(f2);
    }

    public float c() {
        return this.g;
    }

    private static float d(float f2) {
        if (f2 >= 0.0f && f2 <= 90.0f) {
            return (float) Math.tan(Math.toRadians((double) (f2 / 2.0f)));
        }
        throw new IllegalArgumentException("Arc must be between 0 and 90 degrees");
    }

    public Path a(float f2, float f3, float f4, float f5) {
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11 = f2;
        float f12 = f3;
        Path path = new Path();
        path.moveTo(f11, f12);
        if (f12 == f5) {
            f7 = (f11 + f4) / 2.0f;
            f6 = ((this.h * Math.abs(f4 - f11)) / 2.0f) + f12;
        } else if (f11 == f4) {
            f7 = ((this.i * Math.abs(f5 - f12)) / 2.0f) + f11;
            f6 = (f12 + f5) / 2.0f;
        } else {
            float f13 = f4 - f11;
            float f14 = f5 - f12;
            float f15 = (f13 * f13) + (f14 * f14);
            float f16 = (f11 + f4) / 2.0f;
            float f17 = (f12 + f5) / 2.0f;
            float f18 = 0.25f * f15;
            boolean z = f13 * f14 > 0.0f;
            if (Math.abs(f13) < Math.abs(f14)) {
                float f19 = f15 / (f14 * 2.0f);
                if (z) {
                    f6 = f12 + f19;
                    f7 = f11;
                } else {
                    f6 = f5 - f19;
                    f7 = f4;
                }
                f8 = this.i * f18 * this.i;
            } else {
                float f20 = f15 / (f13 * 2.0f);
                if (z) {
                    f9 = f4 - f20;
                    f10 = f5;
                } else {
                    f9 = f11 + f20;
                    f10 = f12;
                }
                f8 = this.h * f18 * this.h;
            }
            float f21 = f16 - f7;
            float f22 = f17 - f6;
            float f23 = (f21 * f21) + (f22 * f22);
            float f24 = f18 * this.j * this.j;
            if (f23 < f8) {
                f24 = f8;
            } else if (f23 <= f24) {
                f24 = 0.0f;
            }
            if (f24 != 0.0f) {
                float sqrt = (float) Math.sqrt((double) (f24 / f23));
                f7 = ((f7 - f16) * sqrt) + f16;
                f6 = f17 + (sqrt * (f6 - f17));
            }
        }
        path.cubicTo((f11 + f7) / 2.0f, (f12 + f6) / 2.0f, (f7 + f4) / 2.0f, (f6 + f5) / 2.0f, f4, f5);
        return path;
    }
}
