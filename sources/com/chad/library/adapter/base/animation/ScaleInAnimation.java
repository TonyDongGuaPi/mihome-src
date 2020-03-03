package com.chad.library.adapter.base.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

public class ScaleInAnimation implements BaseAnimation {

    /* renamed from: a  reason: collision with root package name */
    private static final float f5136a = 0.5f;
    private final float b;

    public ScaleInAnimation() {
        this(0.5f);
    }

    public ScaleInAnimation(float f) {
        this.b = f;
    }

    public Animator[] a(View view) {
        return new ObjectAnimator[]{ObjectAnimator.ofFloat(view, "scaleX", new float[]{this.b, 1.0f}), ObjectAnimator.ofFloat(view, "scaleY", new float[]{this.b, 1.0f})};
    }
}
