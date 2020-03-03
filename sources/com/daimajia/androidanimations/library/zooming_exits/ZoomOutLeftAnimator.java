package com.daimajia.androidanimations.library.zooming_exits;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class ZoomOutLeftAnimator extends BaseViewAnimator {
    /* access modifiers changed from: protected */
    public void prepare(View view) {
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0f, 1.0f, 0.0f}), ObjectAnimator.ofFloat(view, "scaleX", new float[]{1.0f, 0.475f, 0.1f}), ObjectAnimator.ofFloat(view, "scaleY", new float[]{1.0f, 0.475f, 0.1f}), ObjectAnimator.ofFloat(view, "translationX", new float[]{0.0f, 42.0f, (float) (-view.getRight())})});
    }
}
