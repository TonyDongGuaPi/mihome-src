package com.daimajia.androidanimations.library.specials.in;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;

public class LandingAnimator extends BaseViewAnimator {
    /* access modifiers changed from: protected */
    public void prepare(View view) {
        getAnimatorAgent().playTogether(new Animator[]{Glider.glide(Skill.QuintEaseOut, (float) getDuration(), (ValueAnimator) ObjectAnimator.ofFloat(view, "scaleX", new float[]{1.5f, 1.0f})), Glider.glide(Skill.QuintEaseOut, (float) getDuration(), (ValueAnimator) ObjectAnimator.ofFloat(view, "scaleY", new float[]{1.5f, 1.0f})), Glider.glide(Skill.QuintEaseOut, (float) getDuration(), (ValueAnimator) ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f, 1.0f}))});
    }
}
