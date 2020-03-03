package com.daimajia.androidanimations.library.flippers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class FlipInXAnimator extends BaseViewAnimator {
    public void prepare(View view) {
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "rotationX", new float[]{90.0f, -15.0f, 15.0f, 0.0f}), ObjectAnimator.ofFloat(view, "alpha", new float[]{0.25f, 0.5f, 0.75f, 1.0f})});
    }
}
