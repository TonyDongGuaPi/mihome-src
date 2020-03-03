package com.mi.global.bbs.view.animator;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class ScaleXShadeAnimator extends BaseViewAnimator {
    /* access modifiers changed from: protected */
    public void prepare(View view) {
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "scaleX", new float[]{0.6f, 1.2f, 0.7f, 1.0f}), ObjectAnimator.ofFloat(view, "scaleY", new float[]{0.6f, 1.2f, 0.7f, 1.0f})});
    }
}
