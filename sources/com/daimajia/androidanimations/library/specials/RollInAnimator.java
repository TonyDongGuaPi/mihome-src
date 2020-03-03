package com.daimajia.androidanimations.library.specials;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.facebook.react.uimanager.ViewProps;

public class RollInAnimator extends BaseViewAnimator {
    public void prepare(View view) {
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(view, "translationX", new float[]{(float) (-((view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight())), 0.0f}), ObjectAnimator.ofFloat(view, ViewProps.ROTATION, new float[]{-120.0f, 0.0f})});
    }
}
