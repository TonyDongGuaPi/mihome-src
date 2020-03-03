package com.daimajia.androidanimations.library.bouncing_entrances;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class BounceInAnimator extends BaseViewAnimator {
    public void prepare(View view) {
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f, 1.0f, 1.0f, 1.0f}), ObjectAnimator.ofFloat(view, "scaleX", new float[]{0.3f, 1.05f, 0.9f, 1.0f}), ObjectAnimator.ofFloat(view, "scaleY", new float[]{0.3f, 1.05f, 0.9f, 1.0f})});
    }
}
