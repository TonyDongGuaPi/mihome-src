package com.daimajia.androidanimations.library.sliders;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class SlideOutDownAnimator extends BaseViewAnimator {
    public void prepare(View view) {
        int height = ((ViewGroup) view.getParent()).getHeight() - view.getTop();
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0f, 0.0f}), ObjectAnimator.ofFloat(view, "translationY", new float[]{0.0f, (float) height})});
    }
}
