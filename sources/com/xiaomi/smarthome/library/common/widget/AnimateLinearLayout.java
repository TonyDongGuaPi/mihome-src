package com.xiaomi.smarthome.library.common.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.xiaomi.smarthome.R;

public class AnimateLinearLayout extends LinearLayout {
    protected static final int ANIM_DURATION = 180;
    protected static final int BASE_DELAY = 150;
    protected static final float FADEIN_DISTANCE_X = (Resources.getSystem().getDisplayMetrics().density * 200.0f);
    protected static final float FADEIN_DISTANCE_Y = (Resources.getSystem().getDisplayMetrics().density * -60.0f);
    static int FLING_MIN = 1000;
    protected static final int MAX_ANIM_DEVICES = 10;
    protected static final int MIN_ANIM_DEVICES = 3;
    static float SPEED_BASE_LEFT = 2.0f;
    static float SPEED_DECAY = 0.9f;
    static final String TAG = "AnimateLinearLayout";
    float mDownX;
    FlingLeftListener mFlingListener = null;
    GestureDetector mGestureDetector = null;
    public volatile boolean mIsAnimating = false;
    boolean mIsScrolling = false;
    int mLastDragDistance = 0;
    LinearInterpolator mLinearInterpolator = new LinearInterpolator();
    Animator.AnimatorListener mMoveOutAnimListener = null;
    int mTouchSlop = (ViewConfiguration.get(getContext()).getScaledTouchSlop() * 2);

    public interface FlingLeftListener {
        void a();
    }

    /* access modifiers changed from: protected */
    public AnimateLinearLayout getSubAnimateLayout(View view) {
        return null;
    }

    protected static class CircEaseOutInterpolator implements Interpolator {

        /* renamed from: a  reason: collision with root package name */
        protected static final CircEaseOutInterpolator f18770a = new CircEaseOutInterpolator();

        protected CircEaseOutInterpolator() {
        }

        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (float) Math.sqrt((double) (1.0f - (f2 * f2)));
        }
    }

    public AnimateLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public AnimateFakeList getSubFakeList(View view) {
        View findViewById;
        if (!(view instanceof ScrollView) || (findViewById = view.findViewById(R.id.device_grid_view)) == null) {
            return null;
        }
        return (AnimateFakeList) findViewById;
    }

    public static int calcStepDelay(int i) {
        if (i < 3) {
            i = 3;
        } else if (i > 10) {
            i = 10;
        }
        return 180 / i;
    }

    @SuppressLint({"NewApi"})
    public synchronized void doMoveInAnim(int i, int i2) {
        Log.d(TAG, "doMoveInAnim");
        this.mIsAnimating = true;
        final int childCount = getChildCount();
        final int i3 = 0;
        int i4 = -1;
        while (true) {
            if (i3 >= childCount) {
                break;
            }
            final View childAt = getChildAt(i3);
            if (childAt == null) {
                if (i3 == childCount - 1) {
                    this.mIsAnimating = false;
                }
            } else if (childAt.getVisibility() != 8) {
                i4++;
                AnimateFakeList subFakeList = getSubFakeList(childAt);
                if (subFakeList != null) {
                    if (subFakeList.getChildCount() > 0) {
                        subFakeList.doMoveInAnim(i4 + i, i2);
                        i4 += subFakeList.getChildCount() - 1;
                    }
                    if (i3 == childCount - 1) {
                        this.mIsAnimating = false;
                    }
                } else {
                    AnimateLinearLayout subAnimateLayout = getSubAnimateLayout(childAt);
                    if (subAnimateLayout != null) {
                        if (subAnimateLayout.getChildCount() > 0) {
                            subAnimateLayout.doMoveInAnim(i4 + i, i2);
                        }
                        this.mIsAnimating = false;
                    } else {
                        if (getOrientation() == 1) {
                            childAt.setAlpha(0.0f);
                            childAt.setTranslationX(-FADEIN_DISTANCE_X);
                        } else {
                            childAt.setTranslationY(-FADEIN_DISTANCE_Y);
                        }
                        ViewPropertyAnimator animate = childAt.animate();
                        if (getOrientation() == 1) {
                            animate.translationX(0.0f);
                        } else {
                            animate.translationY(0.0f);
                        }
                        animate.setInterpolator(CircEaseOutInterpolator.f18770a);
                        long j = (long) (((i4 + i) * i2) + 150);
                        animate.setStartDelay(j);
                        if (Build.VERSION.SDK_INT >= 16) {
                            animate.withEndAction(new Runnable() {
                                public void run() {
                                    childAt.setTranslationX(0.0f);
                                    if (i3 == childCount - 1) {
                                        AnimateLinearLayout.this.mIsAnimating = false;
                                    }
                                }
                            });
                        } else {
                            animate.setListener(new Animator.AnimatorListener() {
                                public void onAnimationEnd(Animator animator) {
                                }

                                public void onAnimationRepeat(Animator animator) {
                                }

                                public void onAnimationStart(Animator animator) {
                                }

                                public void onAnimationCancel(Animator animator) {
                                    childAt.setTranslationX(0.0f);
                                    if (i3 == childCount - 1) {
                                        AnimateLinearLayout.this.mIsAnimating = false;
                                    }
                                }
                            });
                        }
                        animate.start();
                        if (getOrientation() == 1) {
                            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(childAt, View.ALPHA, new float[]{1.0f});
                            ofFloat.setInterpolator(this.mLinearInterpolator);
                            ofFloat.setStartDelay(j);
                            ofFloat.start();
                        }
                    }
                }
            } else if (i3 == childCount - 1) {
                this.mIsAnimating = false;
            }
            i3++;
        }
    }

    @SuppressLint({"NewApi"})
    public synchronized void doMoveOutAnim(int i, int i2, Animation.AnimationListener animationListener) {
        float f;
        long j;
        ViewPropertyAnimator viewPropertyAnimator;
        int i3 = i2;
        Animation.AnimationListener animationListener2 = animationListener;
        synchronized (this) {
            int i4 = 1;
            this.mIsAnimating = true;
            int i5 = -1;
            int childCount = getChildCount();
            boolean z = false;
            int i6 = 0;
            while (true) {
                if (i6 >= childCount) {
                    break;
                }
                View childAt = getChildAt(i6);
                if (childAt == null) {
                    if (i6 == childCount - 1) {
                        this.mIsAnimating = z;
                    }
                } else if (childAt.getVisibility() == 0) {
                    int i7 = i5 + 1;
                    AnimateFakeList subFakeList = getSubFakeList(childAt);
                    if (subFakeList != null) {
                        if (subFakeList.getChildCount() > 0) {
                            subFakeList.doMoveOutAnim(i7 + i, i3, animationListener2);
                            i7 += subFakeList.getChildCount() - i4;
                        }
                        if (i6 == childCount - 1) {
                            this.mIsAnimating = z;
                        }
                        i5 = i7;
                    } else {
                        AnimateLinearLayout subAnimateLayout = getSubAnimateLayout(childAt);
                        if (subAnimateLayout != null) {
                            if (subAnimateLayout.getChildCount() > 0) {
                                subAnimateLayout.doMoveOutAnim(i7 + i, i3, animationListener2);
                            }
                            this.mIsAnimating = z;
                        } else {
                            childAt.setAlpha(1.0f);
                            childAt.setTranslationX(0.0f);
                            ViewPropertyAnimator animate = childAt.animate();
                            animate.translationX(-FADEIN_DISTANCE_X);
                            animate.setInterpolator(this.mLinearInterpolator);
                            long j2 = (long) (((i7 + i) * i3) + 150);
                            animate.setStartDelay(j2);
                            if (Build.VERSION.SDK_INT >= 16) {
                                AnonymousClass3 r12 = r1;
                                j = j2;
                                final View view = childAt;
                                final int i8 = i6;
                                viewPropertyAnimator = animate;
                                final int i9 = childCount;
                                f = 0.0f;
                                final Animation.AnimationListener animationListener3 = animationListener;
                                AnonymousClass3 r1 = new Runnable() {
                                    public void run() {
                                        view.setTranslationX(-AnimateLinearLayout.FADEIN_DISTANCE_X);
                                        view.setVisibility(4);
                                        if (i8 == i9 - 1) {
                                            AnimateLinearLayout.this.mIsAnimating = false;
                                            if (animationListener3 != null) {
                                                animationListener3.onAnimationEnd((Animation) null);
                                            }
                                        }
                                    }
                                };
                                viewPropertyAnimator.withEndAction(r12);
                            } else {
                                j = j2;
                                viewPropertyAnimator = animate;
                                f = 0.0f;
                                final View view2 = childAt;
                                final int i10 = i6;
                                final int i11 = childCount;
                                final Animation.AnimationListener animationListener4 = animationListener;
                                viewPropertyAnimator.setListener(new Animator.AnimatorListener() {
                                    public void onAnimationRepeat(Animator animator) {
                                    }

                                    public void onAnimationStart(Animator animator) {
                                    }

                                    public void onAnimationEnd(Animator animator) {
                                        view2.setTranslationX(-AnimateLinearLayout.FADEIN_DISTANCE_X);
                                        view2.setVisibility(4);
                                        if (i10 == i11 - 1) {
                                            AnimateLinearLayout.this.mIsAnimating = false;
                                            if (animationListener4 != null) {
                                                animationListener4.onAnimationEnd((Animation) null);
                                            }
                                        }
                                    }

                                    public void onAnimationCancel(Animator animator) {
                                        view2.setTranslationX(-AnimateLinearLayout.FADEIN_DISTANCE_X);
                                        view2.setVisibility(4);
                                        if (i10 == i11 - 1) {
                                            AnimateLinearLayout.this.mIsAnimating = false;
                                            if (animationListener4 != null) {
                                                animationListener4.onAnimationEnd((Animation) null);
                                            }
                                        }
                                    }
                                });
                            }
                            viewPropertyAnimator.start();
                            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(childAt, View.ALPHA, new float[]{f});
                            ofFloat.setInterpolator(this.mLinearInterpolator);
                            ofFloat.setStartDelay(j);
                            ofFloat.start();
                            i5 = i7;
                            i6++;
                            i4 = 1;
                            z = false;
                        }
                    }
                } else if (i6 == childCount - 1) {
                    this.mIsAnimating = z;
                }
                i6++;
                i4 = 1;
                z = false;
            }
        }
    }

    public void setMoveOutAnimListener(Animator.AnimatorListener animatorListener) {
        this.mMoveOutAnimListener = animatorListener;
    }

    public void setFlingLeftListener(FlingLeftListener flingLeftListener) {
        this.mFlingListener = flingLeftListener;
        if (this.mFlingListener != null && this.mGestureDetector == null) {
            this.mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
                public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                    if ((-f) > ((float) AnimateLinearLayout.FLING_MIN) && Math.abs(f) > Math.abs(f2)) {
                        AnimateLinearLayout.this.mFlingListener.a();
                    }
                    return super.onFling(motionEvent, motionEvent2, f, f2);
                }
            });
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mFlingListener == null) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        String str = TAG;
        Log.d(str, System.currentTimeMillis() + "onInterceptTouchEvent,ev=" + motionEvent.getAction());
        switch (motionEvent.getAction()) {
            case 0:
                this.mIsScrolling = false;
                this.mDownX = motionEvent.getX();
                return super.onInterceptTouchEvent(motionEvent);
            case 1:
            case 3:
                this.mIsScrolling = false;
                return super.onInterceptTouchEvent(motionEvent);
            case 2:
                if (this.mIsScrolling) {
                    return true;
                }
                if (((int) Math.abs(motionEvent.getX() - this.mDownX)) <= this.mTouchSlop) {
                    return super.onInterceptTouchEvent(motionEvent);
                }
                this.mIsScrolling = true;
                return true;
            default:
                return super.onInterceptTouchEvent(motionEvent);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mFlingListener == null) {
            return super.onTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.mIsScrolling = false;
                return true;
            case 1:
            case 3:
                this.mIsScrolling = false;
                if ((-this.mLastDragDistance) > getWidth() / 3) {
                    a(this.mLastDragDistance, this.mMoveOutAnimListener);
                    return true;
                }
                a(this.mLastDragDistance);
                return true;
            case 2:
                if (!this.mIsScrolling) {
                    return true;
                }
                this.mLastDragDistance = (int) (motionEvent.getX() - this.mDownX);
                if (this.mLastDragDistance > 0) {
                    this.mLastDragDistance = 0;
                    return true;
                }
                this.mLastDragDistance = (int) (((float) this.mLastDragDistance) * SPEED_BASE_LEFT);
                stepMoveAnim(this.mLastDragDistance);
                return true;
            default:
                return true;
        }
    }

    @TargetApi(11)
    private void a(int i) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i, 0});
        ofInt.setInterpolator(CircEaseOutInterpolator.f18770a);
        ofInt.setDuration(200);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                AnimateLinearLayout.this.stepMoveAnim(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        ofInt.start();
    }

    @TargetApi(11)
    private void a(int i, Animator.AnimatorListener animatorListener) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i, (i - getWidth()) * 2});
        ofInt.setInterpolator(this.mLinearInterpolator);
        ofInt.setDuration(300);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                AnimateLinearLayout.this.stepMoveAnim(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        if (animatorListener != null) {
            ofInt.addListener(animatorListener);
        }
        ofInt.start();
    }

    /* access modifiers changed from: protected */
    @TargetApi(11)
    public void stepMoveAnim(int i) {
        int childCount;
        View childAt;
        int i2;
        int i3;
        if (Math.abs(i) > 1 && (childCount = getChildCount()) > 0) {
            if (this.mFlingListener != null) {
                int width = getWidth() * 2;
                if (Math.abs(i) > width) {
                    i3 = 0;
                } else {
                    i3 = ((width - Math.abs(i)) * 255) / width;
                }
                Drawable background = getBackground();
                if (background != null) {
                    background.setAlpha(i3);
                }
            }
            int i4 = i;
            int i5 = 0;
            while (i5 < childCount) {
                i4 = (int) (((float) i4) * SPEED_DECAY);
                if (Math.abs(i4) > 0) {
                    if (Math.abs(i4) > 0 && (childAt = getChildAt(i5)) != null && childAt.getVisibility() == 0) {
                        AnimateFakeList subFakeList = getSubFakeList(childAt);
                        if (subFakeList == null) {
                            AnimateLinearLayout subAnimateLayout = getSubAnimateLayout(childAt);
                            if (subAnimateLayout == null) {
                                childAt.setTranslationX((float) i4);
                            } else if (subAnimateLayout.getChildCount() > 0) {
                                subAnimateLayout.stepMoveAnim(i4);
                                i2 = i4;
                                for (int i6 = 0; i6 < subAnimateLayout.getChildCount(); i6++) {
                                    i2 = (int) (((float) i2) * SPEED_DECAY);
                                    if (Math.abs(i2) <= 0) {
                                        break;
                                    }
                                }
                            }
                        } else if (subFakeList.getChildCount() > 0) {
                            subFakeList.stepMoveAnim(i4);
                            i2 = i4;
                            for (int i7 = 0; i7 < subFakeList.getChildCount(); i7++) {
                                i2 = (int) (((float) i2) * SPEED_DECAY);
                                if (Math.abs(i2) <= 0) {
                                    break;
                                }
                            }
                        }
                        i4 = i2;
                    }
                    i5++;
                } else {
                    return;
                }
            }
        }
    }
}
