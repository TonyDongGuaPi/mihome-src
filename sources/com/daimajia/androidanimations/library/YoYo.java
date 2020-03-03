package com.daimajia.androidanimations.library;

import android.animation.Animator;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.List;

public class YoYo {
    public static final float CENTER_PIVOT = Float.MAX_VALUE;
    private static final long DURATION = 1000;
    public static final int INFINITE = -1;
    private static final long NO_DELAY = 0;
    /* access modifiers changed from: private */
    public BaseViewAnimator animator;
    private List<Animator.AnimatorListener> callbacks;
    private long delay;
    private long duration;
    private Interpolator interpolator;
    private float pivotX;
    private float pivotY;
    /* access modifiers changed from: private */
    public boolean repeat;
    /* access modifiers changed from: private */
    public long repeatTimes;
    private View target;

    public interface AnimatorCallback {
        void call(Animator animator);
    }

    private YoYo(AnimationComposer animationComposer) {
        this.animator = animationComposer.animator;
        this.duration = animationComposer.duration;
        this.delay = animationComposer.delay;
        this.repeat = animationComposer.repeat;
        this.repeatTimes = animationComposer.repeatTimes;
        this.interpolator = animationComposer.interpolator;
        this.pivotX = animationComposer.pivotX;
        this.pivotY = animationComposer.pivotY;
        this.callbacks = animationComposer.callbacks;
        this.target = animationComposer.target;
    }

    public static AnimationComposer with(Techniques techniques) {
        return new AnimationComposer(techniques);
    }

    public static AnimationComposer with(BaseViewAnimator baseViewAnimator) {
        return new AnimationComposer(baseViewAnimator);
    }

    private static class EmptyAnimatorListener implements Animator.AnimatorListener {
        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }

        private EmptyAnimatorListener() {
        }
    }

    public static final class AnimationComposer {
        /* access modifiers changed from: private */
        public BaseViewAnimator animator;
        /* access modifiers changed from: private */
        public List<Animator.AnimatorListener> callbacks;
        /* access modifiers changed from: private */
        public long delay;
        /* access modifiers changed from: private */
        public long duration;
        /* access modifiers changed from: private */
        public Interpolator interpolator;
        /* access modifiers changed from: private */
        public float pivotX;
        /* access modifiers changed from: private */
        public float pivotY;
        /* access modifiers changed from: private */
        public boolean repeat;
        /* access modifiers changed from: private */
        public long repeatTimes;
        /* access modifiers changed from: private */
        public View target;

        private AnimationComposer(Techniques techniques) {
            this.callbacks = new ArrayList();
            this.duration = 1000;
            this.delay = 0;
            this.repeat = false;
            this.repeatTimes = 0;
            this.pivotX = Float.MAX_VALUE;
            this.pivotY = Float.MAX_VALUE;
            this.animator = techniques.getAnimator();
        }

        private AnimationComposer(BaseViewAnimator baseViewAnimator) {
            this.callbacks = new ArrayList();
            this.duration = 1000;
            this.delay = 0;
            this.repeat = false;
            this.repeatTimes = 0;
            this.pivotX = Float.MAX_VALUE;
            this.pivotY = Float.MAX_VALUE;
            this.animator = baseViewAnimator;
        }

        public AnimationComposer duration(long j) {
            this.duration = j;
            return this;
        }

        public AnimationComposer delay(long j) {
            this.delay = j;
            return this;
        }

        public AnimationComposer interpolate(Interpolator interpolator2) {
            this.interpolator = interpolator2;
            return this;
        }

        public AnimationComposer pivot(float f, float f2) {
            this.pivotX = f;
            this.pivotY = f2;
            return this;
        }

        public AnimationComposer pivotX(float f) {
            this.pivotX = f;
            return this;
        }

        public AnimationComposer pivotY(float f) {
            this.pivotY = f;
            return this;
        }

        public AnimationComposer repeat(int i) {
            if (i >= -1) {
                this.repeat = i != 0;
                this.repeatTimes = (long) i;
                return this;
            }
            throw new RuntimeException("Can not be less than -1, -1 is infinite loop");
        }

        public AnimationComposer withListener(Animator.AnimatorListener animatorListener) {
            this.callbacks.add(animatorListener);
            return this;
        }

        public AnimationComposer onStart(final AnimatorCallback animatorCallback) {
            this.callbacks.add(new EmptyAnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    animatorCallback.call(animator);
                }
            });
            return this;
        }

        public AnimationComposer onEnd(final AnimatorCallback animatorCallback) {
            this.callbacks.add(new EmptyAnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    animatorCallback.call(animator);
                }
            });
            return this;
        }

        public AnimationComposer onCancel(final AnimatorCallback animatorCallback) {
            this.callbacks.add(new EmptyAnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                    animatorCallback.call(animator);
                }
            });
            return this;
        }

        public AnimationComposer onRepeat(final AnimatorCallback animatorCallback) {
            this.callbacks.add(new EmptyAnimatorListener() {
                public void onAnimationRepeat(Animator animator) {
                    animatorCallback.call(animator);
                }
            });
            return this;
        }

        public YoYoString playOn(View view) {
            this.target = view;
            return new YoYoString(new YoYo(this).play(), this.target);
        }
    }

    public static final class YoYoString {
        private BaseViewAnimator animator;
        private View target;

        private YoYoString(BaseViewAnimator baseViewAnimator, View view) {
            this.target = view;
            this.animator = baseViewAnimator;
        }

        public boolean isStarted() {
            return this.animator.isStarted();
        }

        public boolean isRunning() {
            return this.animator.isRunning();
        }

        public void stop() {
            stop(true);
        }

        public void stop(boolean z) {
            this.animator.cancel();
            if (z) {
                this.animator.reset(this.target);
            }
        }
    }

    /* access modifiers changed from: private */
    public BaseViewAnimator play() {
        this.animator.setTarget(this.target);
        if (this.pivotX == Float.MAX_VALUE) {
            ViewCompat.setPivotX(this.target, ((float) this.target.getMeasuredWidth()) / 2.0f);
        } else {
            this.target.setPivotX(this.pivotX);
        }
        if (this.pivotY == Float.MAX_VALUE) {
            ViewCompat.setPivotY(this.target, ((float) this.target.getMeasuredHeight()) / 2.0f);
        } else {
            this.target.setPivotY(this.pivotY);
        }
        this.animator.setDuration(this.duration).setInterpolator(this.interpolator).setStartDelay(this.delay);
        if (this.callbacks.size() > 0) {
            for (Animator.AnimatorListener addAnimatorListener : this.callbacks) {
                this.animator.addAnimatorListener(addAnimatorListener);
            }
        }
        if (this.repeat) {
            this.animator.addAnimatorListener(new Animator.AnimatorListener() {
                private long currentTimes = 0;

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                    this.currentTimes++;
                }

                public void onAnimationEnd(Animator animator) {
                    if (YoYo.this.repeat) {
                        if (YoYo.this.repeatTimes == -1 || this.currentTimes < YoYo.this.repeatTimes) {
                            YoYo.this.animator.restart();
                        }
                    }
                }

                public void onAnimationCancel(Animator animator) {
                    long unused = YoYo.this.repeatTimes = 0;
                    boolean unused2 = YoYo.this.repeat = false;
                }
            });
        }
        this.animator.animate();
        return this.animator;
    }
}
