package com.daimajia.androidanimations.library.specials.in;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;

public class DropOutAnimator extends BaseViewAnimator {
    /* access modifiers changed from: protected */
    public void prepare(View view) {
        int top = view.getTop() + view.getHeight();
        getAnimatorAgent().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f, 1.0f}), Glider.glide(Skill.BounceEaseOut, (float) getDuration(), (ValueAnimator) ObjectAnimator.ofFloat(view, "translationY", new float[]{(float) (-top), 0.0f}))});
    }
}
