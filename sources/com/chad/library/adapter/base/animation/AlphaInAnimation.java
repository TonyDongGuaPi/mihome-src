package com.chad.library.adapter.base.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

public class AlphaInAnimation implements BaseAnimation {

    /* renamed from: a  reason: collision with root package name */
    private static final float f5135a = 0.0f;
    private final float b;

    public AlphaInAnimation() {
        this(0.0f);
    }

    public AlphaInAnimation(float f) {
        this.b = f;
    }

    public Animator[] a(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "alpha", new float[]{this.b, 1.0f})};
    }
}
