package com.daimajia.androidanimations.library.rotating_exits;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.facebook.react.uimanager.ViewProps;

public class RotateOutDownLeftAnimator extends BaseViewAnimator {
    public void prepare(View view) {
        float paddingLeft = (float) view.getPaddingLeft();
        float height = (float) (view.getHeight() - view.getPaddingBottom());
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0f, 0.0f}), ObjectAnimator.ofFloat(view, ViewProps.ROTATION, new float[]{0.0f, 90.0f}), ObjectAnimator.ofFloat(view, "pivotX", new float[]{paddingLeft, paddingLeft}), ObjectAnimator.ofFloat(view, "pivotY", new float[]{height, height})});
    }
}
