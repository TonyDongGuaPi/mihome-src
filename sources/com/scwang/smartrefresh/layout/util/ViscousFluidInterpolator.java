package com.scwang.smartrefresh.layout.util;

import android.view.animation.Interpolator;

public class ViscousFluidInterpolator implements Interpolator {

    /* renamed from: a  reason: collision with root package name */
    private static final float f8803a = 8.0f;
    private static final float b = (1.0f / a(1.0f));
    private static final float c = (1.0f - (b * a(1.0f)));

    private static float a(float f) {
        float f2 = f * 8.0f;
        if (f2 < 1.0f) {
            return f2 - (1.0f - ((float) Math.exp((double) (-f2))));
        }
        return ((1.0f - ((float) Math.exp((double) (1.0f - f2)))) * 0.63212055f) + 0.36787945f;
    }

    public float getInterpolation(float f) {
        float a2 = b * a(f);
        return a2 > 0.0f ? a2 + c : a2;
    }
}
