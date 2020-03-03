package com.daimajia.easing.sine;

import com.daimajia.easing.BaseEasingMethod;

public class SineEaseIn extends BaseEasingMethod {
    public SineEaseIn(float f) {
        super(f);
    }

    public Float calculate(float f, float f2, float f3, float f4) {
        double d = (double) (f / f4);
        Double.isNaN(d);
        return Float.valueOf(((-f3) * ((float) Math.cos(d * 1.5707963267948966d))) + f3 + f2);
    }
}
