package com.daimajia.androidanimations.library.rotating_entrances;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.facebook.react.uimanager.ViewProps;

public class RotateInAnimator extends BaseViewAnimator {
    public void prepare(View view) {
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, ViewProps.ROTATION, new float[]{-200.0f, 0.0f}), ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f, 1.0f})});
    }
}
