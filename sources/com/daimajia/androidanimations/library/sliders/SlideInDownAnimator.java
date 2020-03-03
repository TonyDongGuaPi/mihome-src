package com.daimajia.androidanimations.library.sliders;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class SlideInDownAnimator extends BaseViewAnimator {
    public void prepare(View view) {
        int top = view.getTop() + view.getHeight();
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(view, "translationY", new float[]{(float) (-top), 0.0f})});
    }
}
