package com.h6ah4i.android.widget.advrecyclerview.swipeable;

import android.view.animation.Interpolator;

class RubberBandInterpolator implements Interpolator {

    /* renamed from: a  reason: collision with root package name */
    private final float f5727a;

    public RubberBandInterpolator(float f) {
        this.f5727a = f;
    }

    public float getInterpolation(float f) {
        float f2 = 1.0f - f;
        return this.f5727a * (1.0f - (f2 * f2));
    }
}
