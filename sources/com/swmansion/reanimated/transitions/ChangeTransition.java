package com.swmansion.reanimated.transitions;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.transition.TransitionPropagation;
import android.transition.TransitionValues;
import android.view.ViewGroup;

@RequiresApi(api = 21)
final class ChangeTransition extends Transition {

    /* renamed from: a  reason: collision with root package name */
    private final ChangeTransform f8937a = new ChangeTransform();
    private final ChangeBounds b = new ChangeBounds();

    public void captureStartValues(TransitionValues transitionValues) {
        this.f8937a.captureStartValues(transitionValues);
        this.b.captureStartValues(transitionValues);
    }

    public void captureEndValues(TransitionValues transitionValues) {
        this.f8937a.captureEndValues(transitionValues);
        this.b.captureEndValues(transitionValues);
    }

    public Transition setDuration(long j) {
        this.f8937a.setDuration(j);
        this.b.setDuration(j);
        return super.setDuration(j);
    }

    public Transition setStartDelay(long j) {
        this.f8937a.setStartDelay(j);
        this.b.setStartDelay(j);
        return super.setStartDelay(j);
    }

    public Transition setInterpolator(@Nullable TimeInterpolator timeInterpolator) {
        this.f8937a.setInterpolator(timeInterpolator);
        this.b.setInterpolator(timeInterpolator);
        return super.setInterpolator(timeInterpolator);
    }

    public void setPropagation(@Nullable TransitionPropagation transitionPropagation) {
        this.f8937a.setPropagation(transitionPropagation);
        this.b.setPropagation(transitionPropagation);
        super.setPropagation(transitionPropagation);
    }

    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        this.f8937a.setReparent(false);
        Animator createAnimator = this.f8937a.createAnimator(viewGroup, transitionValues, transitionValues2);
        Animator createAnimator2 = this.b.createAnimator(viewGroup, transitionValues, transitionValues2);
        if (createAnimator == null) {
            return createAnimator2;
        }
        if (createAnimator2 == null) {
            return createAnimator;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{createAnimator, createAnimator2});
        return animatorSet;
    }
}
