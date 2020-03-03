package com.daimajia.androidanimations.library.specials;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.facebook.react.uimanager.ViewProps;

public class RollOutAnimator extends BaseViewAnimator {
    public void prepare(View view) {
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0f, 0.0f}), ObjectAnimator.ofFloat(view, "translationX", new float[]{0.0f, (float) view.getWidth()}), ObjectAnimator.ofFloat(view, ViewProps.ROTATION, new float[]{0.0f, 120.0f})});
    }
}
