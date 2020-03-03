package com.daimajia.androidanimations.library.specials;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;
import com.facebook.react.uimanager.ViewProps;

public class HingeAnimator extends BaseViewAnimator {
    public void prepare(View view) {
        float paddingLeft = (float) view.getPaddingLeft();
        float paddingTop = (float) view.getPaddingTop();
        getAnimatorAgent().playTogether(new Animator[]{Glider.glide(Skill.SineEaseInOut, 1300.0f, (ValueAnimator) ObjectAnimator.ofFloat(view, ViewProps.ROTATION, new float[]{0.0f, 80.0f, 60.0f, 80.0f, 60.0f, 60.0f})), ObjectAnimator.ofFloat(view, "translationY", new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 700.0f}), ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f}), ObjectAnimator.ofFloat(view, "pivotX", new float[]{paddingLeft, paddingLeft, paddingLeft, paddingLeft, paddingLeft, paddingLeft}), ObjectAnimator.ofFloat(view, "pivotY", new float[]{paddingTop, paddingTop, paddingTop, paddingTop, paddingTop, paddingTop})});
        setDuration(1300);
    }
}
