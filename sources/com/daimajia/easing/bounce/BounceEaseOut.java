package com.daimajia.easing.bounce;

import com.daimajia.easing.BaseEasingMethod;

public class BounceEaseOut extends BaseEasingMethod {
    public BounceEaseOut(float f) {
        super(f);
    }

    public Float calculate(float f, float f2, float f3, float f4) {
        float f5 = f / f4;
        if (f5 < 0.36363637f) {
            return Float.valueOf((f3 * 7.5625f * f5 * f5) + f2);
        }
        if (f5 < 0.72727275f) {
            float f6 = f5 - 0.54545456f;
            return Float.valueOf((f3 * ((7.5625f * f6 * f6) + 0.75f)) + f2);
        } else if (((double) f5) < 0.9090909090909091d) {
            float f7 = f5 - 0.8181818f;
            return Float.valueOf((f3 * ((7.5625f * f7 * f7) + 0.9375f)) + f2);
        } else {
            float f8 = f5 - 0.95454544f;
            return Float.valueOf((f3 * ((7.5625f * f8 * f8) + 0.984375f)) + f2);
        }
    }
}
