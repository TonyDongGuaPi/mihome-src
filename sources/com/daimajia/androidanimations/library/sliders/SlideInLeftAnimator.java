package com.daimajia.androidanimations.library.sliders;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class SlideInLeftAnimator extends BaseViewAnimator {
    public void prepare(View view) {
        int width = ((ViewGroup) view.getParent()).getWidth() - view.getLeft();
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(view, "translationX", new float[]{(float) (-width), 0.0f})});
    }
}
