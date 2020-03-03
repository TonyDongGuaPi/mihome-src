package com.mi.global.bbs.view.animator;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;

public class DropDownAnimator extends BaseViewAnimator {
    /* access modifiers changed from: protected */
    public void prepare(View view) {
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f, 1.0f}).setDuration(10), Glider.glide(Skill.BounceEaseOut, (float) getDuration(), (ValueAnimator) ObjectAnimator.ofFloat(view, "translationY", new float[]{(float) -400, 0.0f}))});
    }
}
