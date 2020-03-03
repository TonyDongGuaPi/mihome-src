package com.daimajia.androidanimations.library.attention;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.facebook.react.uimanager.ViewProps;

public class WobbleAnimator extends BaseViewAnimator {
    public void prepare(View view) {
        double width = (double) ((float) view.getWidth());
        Double.isNaN(width);
        float f = (float) (width / 100.0d);
        float f2 = f * 0.0f;
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "translationX", new float[]{f2, -25.0f * f, 20.0f * f, -15.0f * f, 10.0f * f, f * -5.0f, f2, 0.0f}), ObjectAnimator.ofFloat(view, ViewProps.ROTATION, new float[]{0.0f, -5.0f, 3.0f, -3.0f, 2.0f, -1.0f, 0.0f})});
    }
}
