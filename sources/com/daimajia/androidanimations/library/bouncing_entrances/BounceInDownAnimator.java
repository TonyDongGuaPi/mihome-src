package com.daimajia.androidanimations.library.bouncing_entrances;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class BounceInDownAnimator extends BaseViewAnimator {
    public void prepare(View view) {
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f, 1.0f, 1.0f, 1.0f}), ObjectAnimator.ofFloat(view, "translationY", new float[]{(float) (-view.getHeight()), 30.0f, -10.0f, 0.0f})});
    }
}