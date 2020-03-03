package com.daimajia.androidanimations.library.zooming_entrances;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class ZoomInDownAnimator extends BaseViewAnimator {
    public void prepare(View view) {
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "scaleX", new float[]{0.1f, 0.475f, 1.0f}), ObjectAnimator.ofFloat(view, "scaleY", new float[]{0.1f, 0.475f, 1.0f}), ObjectAnimator.ofFloat(view, "translationY", new float[]{(float) (-view.getBottom()), 60.0f, 0.0f}), ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f, 1.0f, 1.0f})});
    }
}
