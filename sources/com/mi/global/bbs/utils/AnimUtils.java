package com.mi.global.bbs.utils;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.ArrayMap;
import android.util.Property;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import java.util.ArrayList;

public class AnimUtils {
    private static Interpolator fastOutLinearIn;
    private static Interpolator fastOutSlowIn;
    private static Interpolator linearOutSlowIn;

    public static float lerp(float f, float f2, float f3) {
        return f + ((f2 - f) * f3);
    }

    private AnimUtils() {
    }

    public static Interpolator getFastOutSlowInInterpolator(Context context) {
        if (fastOutSlowIn == null) {
            fastOutSlowIn = AnimationUtils.loadInterpolator(context, AndroidResources.FAST_OUT_SLOW_IN);
        }
        return fastOutSlowIn;
    }

    public static Interpolator getFastOutLinearInInterpolator(Context context) {
        if (fastOutLinearIn == null) {
            fastOutLinearIn = AnimationUtils.loadInterpolator(context, AndroidResources.FAST_OUT_LINEAR_IN);
        }
        return fastOutLinearIn;
    }

    public static Interpolator getLinearOutSlowInInterpolator(Context context) {
        if (linearOutSlowIn == null) {
            linearOutSlowIn = AnimationUtils.loadInterpolator(context, AndroidResources.LINEAR_OUT_SLOW_IN);
        }
        return linearOutSlowIn;
    }

    public static abstract class FloatProperty<T> extends Property<T, Float> {
        public abstract void setValue(T t, float f);

        public FloatProperty(String str) {
            super(Float.class, str);
        }

        public final void set(T t, Float f) {
            setValue(t, f.floatValue());
        }
    }

    public static abstract class IntProperty<T> extends Property<T, Integer> {
        public abstract void setValue(T t, int i);

        public IntProperty(String str) {
            super(Integer.class, str);
        }

        public final void set(T t, Integer num) {
            setValue(t, num.intValue());
        }
    }

    public static class NoPauseAnimator extends Animator {
        private final Animator mAnimator;
        private final ArrayMap<Animator.AnimatorListener, Animator.AnimatorListener> mListeners = new ArrayMap<>();

        public NoPauseAnimator(Animator animator) {
            this.mAnimator = animator;
        }

        public void addListener(Animator.AnimatorListener animatorListener) {
            AnimatorListenerWrapper animatorListenerWrapper = new AnimatorListenerWrapper(this, animatorListener);
            if (!this.mListeners.containsKey(animatorListener)) {
                this.mListeners.put(animatorListener, animatorListenerWrapper);
                this.mAnimator.addListener(animatorListenerWrapper);
            }
        }

        public void cancel() {
            this.mAnimator.cancel();
        }

        public void end() {
            this.mAnimator.end();
        }

        public long getDuration() {
            return this.mAnimator.getDuration();
        }

        public TimeInterpolator getInterpolator() {
            return this.mAnimator.getInterpolator();
        }

        public void setInterpolator(TimeInterpolator timeInterpolator) {
            this.mAnimator.setInterpolator(timeInterpolator);
        }

        public ArrayList<Animator.AnimatorListener> getListeners() {
            return new ArrayList<>(this.mListeners.keySet());
        }

        public long getStartDelay() {
            return this.mAnimator.getStartDelay();
        }

        public void setStartDelay(long j) {
            this.mAnimator.setStartDelay(j);
        }

        public boolean isPaused() {
            return this.mAnimator.isPaused();
        }

        public boolean isRunning() {
            return this.mAnimator.isRunning();
        }

        public boolean isStarted() {
            return this.mAnimator.isStarted();
        }

        public void removeAllListeners() {
            this.mListeners.clear();
            this.mAnimator.removeAllListeners();
        }

        public void removeListener(Animator.AnimatorListener animatorListener) {
            Animator.AnimatorListener animatorListener2 = this.mListeners.get(animatorListener);
            if (animatorListener2 != null) {
                this.mListeners.remove(animatorListener);
                this.mAnimator.removeListener(animatorListener2);
            }
        }

        public Animator setDuration(long j) {
            this.mAnimator.setDuration(j);
            return this;
        }

        public void setTarget(Object obj) {
            this.mAnimator.setTarget(obj);
        }

        public void setupEndValues() {
            this.mAnimator.setupEndValues();
        }

        public void setupStartValues() {
            this.mAnimator.setupStartValues();
        }

        public void start() {
            this.mAnimator.start();
        }
    }

    static class AnimatorListenerWrapper implements Animator.AnimatorListener {
        private final Animator mAnimator;
        private final Animator.AnimatorListener mListener;

        public AnimatorListenerWrapper(Animator animator, Animator.AnimatorListener animatorListener) {
            this.mAnimator = animator;
            this.mListener = animatorListener;
        }

        public void onAnimationStart(Animator animator) {
            this.mListener.onAnimationStart(this.mAnimator);
        }

        public void onAnimationEnd(Animator animator) {
            this.mListener.onAnimationEnd(this.mAnimator);
        }

        public void onAnimationCancel(Animator animator) {
            this.mListener.onAnimationCancel(this.mAnimator);
        }

        public void onAnimationRepeat(Animator animator) {
            this.mListener.onAnimationRepeat(this.mAnimator);
        }
    }
}
