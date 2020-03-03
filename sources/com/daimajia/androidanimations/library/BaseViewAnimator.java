package com.daimajia.androidanimations.library;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.Interpolator;

public abstract class BaseViewAnimator {
    public static final long DURATION = 1000;
    private AnimatorSet mAnimatorSet = new AnimatorSet();
    private long mDuration = 1000;

    /* access modifiers changed from: protected */
    public abstract void prepare(View view);

    public BaseViewAnimator setTarget(View view) {
        reset(view);
        prepare(view);
        return this;
    }

    public void animate() {
        start();
    }

    public void restart() {
        this.mAnimatorSet = this.mAnimatorSet.clone();
        start();
    }

    public void reset(View view) {
        ViewCompat.setAlpha(view, 1.0f);
        ViewCompat.setScaleX(view, 1.0f);
        ViewCompat.setScaleY(view, 1.0f);
        ViewCompat.setTranslationX(view, 0.0f);
        ViewCompat.setTranslationY(view, 0.0f);
        ViewCompat.setRotation(view, 0.0f);
        ViewCompat.setRotationY(view, 0.0f);
        ViewCompat.setRotationX(view, 0.0f);
    }

    public void start() {
        this.mAnimatorSet.setDuration(this.mDuration);
        this.mAnimatorSet.start();
    }

    public BaseViewAnimator setDuration(long j) {
        this.mDuration = j;
        return this;
    }

    public BaseViewAnimator setStartDelay(long j) {
        getAnimatorAgent().setStartDelay(j);
        return this;
    }

    public long getStartDelay() {
        return this.mAnimatorSet.getStartDelay();
    }

    public BaseViewAnimator addAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.mAnimatorSet.addListener(animatorListener);
        return this;
    }

    public void cancel() {
        this.mAnimatorSet.cancel();
    }

    public boolean isRunning() {
        return this.mAnimatorSet.isRunning();
    }

    public boolean isStarted() {
        return this.mAnimatorSet.isStarted();
    }

    public void removeAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.mAnimatorSet.removeListener(animatorListener);
    }

    public void removeAllListener() {
        this.mAnimatorSet.removeAllListeners();
    }

    public BaseViewAnimator setInterpolator(Interpolator interpolator) {
        this.mAnimatorSet.setInterpolator(interpolator);
        return this;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public AnimatorSet getAnimatorAgent() {
        return this.mAnimatorSet;
    }
}
