package com.h6ah4i.android.widget.advrecyclerview.draggable;

import android.view.animation.Interpolator;

public class BasicSwapTargetTranslationInterpolator implements Interpolator {

    /* renamed from: a  reason: collision with root package name */
    private final float f5701a;
    private final float b;
    private final float c;

    public BasicSwapTargetTranslationInterpolator() {
        this(0.3f);
    }

    public BasicSwapTargetTranslationInterpolator(float f) {
        if (f < 0.0f || f >= 0.5f) {
            throw new IllegalArgumentException("Invalid threshold range: " + f);
        }
        float f2 = 1.0f - (2.0f * f);
        this.f5701a = f;
        this.b = 0.5f * f2;
        this.c = 1.0f / f2;
    }

    public float getInterpolation(float f) {
        if (Math.abs(f - 0.5f) < this.b) {
            return (f - this.f5701a) * this.c;
        }
        return f < 0.5f ? 0.0f : 1.0f;
    }
}
