package com.daimajia.androidanimations.library.attention;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.facebook.react.uimanager.ViewProps;

public class WaveAnimator extends BaseViewAnimator {
    public void prepare(View view) {
        float width = (float) ((((view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight()) / 2) + view.getPaddingLeft());
        float height = (float) (view.getHeight() - view.getPaddingBottom());
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, ViewProps.ROTATION, new float[]{12.0f, -12.0f, 3.0f, -3.0f, 0.0f}), ObjectAnimator.ofFloat(view, "pivotX", new float[]{width, width, width, width, width}), ObjectAnimator.ofFloat(view, "pivotY", new float[]{height, height, height, height, height})});
    }
}
