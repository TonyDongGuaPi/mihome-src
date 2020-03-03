package com.daimajia.easing.sine;

import com.daimajia.easing.BaseEasingMethod;

public class SineEaseInOut extends BaseEasingMethod {
    public SineEaseInOut(float f) {
        super(f);
    }

    public Float calculate(float f, float f2, float f3, float f4) {
        double d = (double) f;
        Double.isNaN(d);
        double d2 = (double) f4;
        Double.isNaN(d2);
        return Float.valueOf((((-f3) / 2.0f) * (((float) Math.cos((d * 3.141592653589793d) / d2)) - 1.0f)) + f2);
    }
}
