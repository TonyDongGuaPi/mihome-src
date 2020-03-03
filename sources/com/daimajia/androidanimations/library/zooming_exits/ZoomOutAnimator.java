package com.daimajia.androidanimations.library.zooming_exits;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class ZoomOutAnimator extends BaseViewAnimator {
    /* access modifiers changed from: protected */
    public void prepare(View view) {
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0f, 0.0f, 0.0f}), ObjectAnimator.ofFloat(view, "scaleX", new float[]{1.0f, 0.3f, 0.0f}), ObjectAnimator.ofFloat(view, "scaleY", new float[]{1.0f, 0.3f, 0.0f})});
    }
}
