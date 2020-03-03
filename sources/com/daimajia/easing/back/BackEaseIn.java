package com.daimajia.easing.back;

import com.daimajia.easing.BaseEasingMethod;

public class BackEaseIn extends BaseEasingMethod {
    private float s;

    public BackEaseIn(float f) {
        super(f);
        this.s = 1.70158f;
    }

    public BackEaseIn(float f, float f2) {
        this(f);
        this.s = f2;
    }

    public Float calculate(float f, float f2, float f3, float f4) {
        float f5 = f / f4;
        return Float.valueOf((f3 * f5 * f5 * (((this.s + 1.0f) * f5) - this.s)) + f2);
    }
}