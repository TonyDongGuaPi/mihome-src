package com.daimajia.androidanimations.library.attention;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class StandUpAnimator extends BaseViewAnimator {
    public void prepare(View view) {
        float width = (float) ((((view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight()) / 2) + view.getPaddingLeft());
        float height = (float) (view.getHeight() - view.getPaddingBottom());
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "pivotX", new float[]{width, width, width, width, width}), ObjectAnimator.ofFloat(view, "pivotY", new float[]{height, height, height, height, height}), ObjectAnimator.ofFloat(view, "rotationX", new float[]{55.0f, -30.0f, 15.0f, -15.0f, 0.0f})});
    }
}
