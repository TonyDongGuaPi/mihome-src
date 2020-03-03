package com.daimajia.easing.back;

import com.daimajia.easing.BaseEasingMethod;

public class BackEaseInOut extends BaseEasingMethod {
    private float s;

    public BackEaseInOut(float f) {
        super(f);
        this.s = 1.70158f;
    }

    public BackEaseInOut(float f, float f2) {
        this(f);
        this.s = f2;
    }

    public Float calculate(float f, float f2, float f3, float f4) {
        float f5 = f / (f4 / 2.0f);
        if (f5 < 1.0f) {
            double d = (double) this.s;
            Double.isNaN(d);
            float f6 = (float) (d * 1.525d);
            this.s = f6;
            return Float.valueOf(((f3 / 2.0f) * f5 * f5 * (((f6 + 1.0f) * f5) - this.s)) + f2);
        }
        float f7 = f5 - 2.0f;
        double d2 = (double) this.s;
        Double.isNaN(d2);
        float f8 = (float) (d2 * 1.525d);
        this.s = f8;
        return Float.valueOf(((f3 / 2.0f) * ((f7 * f7 * (((f8 + 1.0f) * f7) + this.s)) + 2.0f)) + f2);
    }
}
