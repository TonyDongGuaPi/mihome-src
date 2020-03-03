package com.mi.global.bbs.view.swipe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import com.libra.Color;

@SuppressLint({"ClickableViewAccessibility"})
public class SuperSwipeRefreshLayout extends ViewGroup {
    private static final int ANIMATE_TO_START_DURATION = 200;
    private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    private static final int DEFAULT_CIRCLE_TARGET = 64;
    private static final float DRAG_RATE = 0.5f;
    private static final int HEADER_VIEW_HEIGHT = 84;
    private static final int INVALID_POINTER = -1;
    private static final int[] LAYOUT_ATTRS = {16842766};
    private static final int SCALE_DOWN_DURATION = 150;
    /* access modifiers changed from: private */
    public CircleProgressView defaultProgressView;
    /* access modifiers changed from: private */
    public float density;
    /* access modifiers changed from: private */
    public boolean isProgressEnable;
    private int mActivePointerId;
    private final Animation mAnimateToCorrectPosition;
    private final Animation mAnimateToStartPosition;
    /* access modifiers changed from: private */
    public int mCurrentTargetOffsetTop;
    private final DecelerateInterpolator mDecelerateInterpolator;
    private RelativeLayout mFooterViewContainer;
    private int mFooterViewHeight;
    private int mFooterViewIndex;
    private int mFooterViewWidth;
    protected int mFrom;
    /* access modifiers changed from: private */
    public HeadViewContainer mHeadViewContainer;
    private int mHeaderViewHeight;
    private int mHeaderViewIndex;
    private int mHeaderViewWidth;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    /* access modifiers changed from: private */
    public OnPullRefreshListener mListener;
    private boolean mLoadMore;
    private int mMediumAnimationDuration;
    /* access modifiers changed from: private */
    public boolean mNotify;
    private boolean mOriginalOffsetCalculated;
    protected int mOriginalOffsetTop;
    private Animation.AnimationListener mRefreshListener;
    /* access modifiers changed from: private */
    public boolean mRefreshing;
    private boolean mReturningToStart;
    /* access modifiers changed from: private */
    public boolean mScale;
    private Animation mScaleAnimation;
    private Animation mScaleDownAnimation;
    private Animation mScaleDownToStartAnimation;
    /* access modifiers changed from: private */
    public float mSpinnerFinalOffset;
    /* access modifiers changed from: private */
    public float mStartingScale;
    private View mTarget;
    private float mTotalDragDistance;
    private int mTouchSlop;
    /* access modifiers changed from: private */
    public boolean mUsingCustomStart;
    private int pushDistance;
    private boolean targetScrollWithLayout;
    /* access modifiers changed from: private */
    public boolean usingDefaultHeader;

    public interface OnPullRefreshListener {
        void onPullDistance(int i);

        void onPullEnable(boolean z);

        void onRefresh();
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
    }

    /* access modifiers changed from: private */
    public void updateListenerCallBack() {
        int height = this.mCurrentTargetOffsetTop + this.mHeadViewContainer.getHeight();
        if (this.mListener != null) {
            this.mListener.onPullDistance(height);
        }
        if (this.usingDefaultHeader && this.isProgressEnable) {
            this.defaultProgressView.setPullDistance(height);
        }
    }

    public void setHeaderView(View view) {
        if (view != null && this.mHeadViewContainer != null) {
            this.usingDefaultHeader = false;
            this.mHeadViewContainer.removeAllViews();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.mHeaderViewWidth, this.mHeaderViewHeight);
            layoutParams.addRule(12);
            this.mHeadViewContainer.addView(view, layoutParams);
        }
    }

    public SuperSwipeRefreshLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public SuperSwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRefreshing = false;
        this.mLoadMore = false;
        this.mTotalDragDistance = -1.0f;
        this.mOriginalOffsetCalculated = false;
        this.mActivePointerId = -1;
        this.mHeaderViewIndex = -1;
        this.mFooterViewIndex = -1;
        this.targetScrollWithLayout = true;
        this.pushDistance = 0;
        this.defaultProgressView = null;
        this.usingDefaultHeader = true;
        this.density = 1.0f;
        this.isProgressEnable = true;
        this.mRefreshListener = new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                boolean unused = SuperSwipeRefreshLayout.this.isProgressEnable = false;
            }

            public void onAnimationEnd(Animation animation) {
                boolean unused = SuperSwipeRefreshLayout.this.isProgressEnable = true;
                if (!SuperSwipeRefreshLayout.this.mRefreshing) {
                    SuperSwipeRefreshLayout.this.mHeadViewContainer.setVisibility(8);
                    if (SuperSwipeRefreshLayout.this.mScale) {
                        SuperSwipeRefreshLayout.this.setAnimationProgress(0.0f);
                    } else {
                        SuperSwipeRefreshLayout.this.setTargetOffsetTopAndBottom(SuperSwipeRefreshLayout.this.mOriginalOffsetTop - SuperSwipeRefreshLayout.this.mCurrentTargetOffsetTop, true);
                    }
                } else if (SuperSwipeRefreshLayout.this.mNotify) {
                    if (SuperSwipeRefreshLayout.this.usingDefaultHeader) {
                        ViewCompat.setAlpha(SuperSwipeRefreshLayout.this.defaultProgressView, 1.0f);
                        SuperSwipeRefreshLayout.this.defaultProgressView.setOnDraw(true);
                        new Thread(SuperSwipeRefreshLayout.this.defaultProgressView).start();
                    }
                    if (SuperSwipeRefreshLayout.this.mListener != null) {
                        SuperSwipeRefreshLayout.this.mListener.onRefresh();
                    }
                }
                int unused2 = SuperSwipeRefreshLayout.this.mCurrentTargetOffsetTop = SuperSwipeRefreshLayout.this.mHeadViewContainer.getTop();
                SuperSwipeRefreshLayout.this.updateListenerCallBack();
            }
        };
        this.mAnimateToCorrectPosition = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                int i;
                if (!SuperSwipeRefreshLayout.this.mUsingCustomStart) {
                    i = (int) (SuperSwipeRefreshLayout.this.mSpinnerFinalOffset - ((float) Math.abs(SuperSwipeRefreshLayout.this.mOriginalOffsetTop)));
                } else {
                    i = (int) SuperSwipeRefreshLayout.this.mSpinnerFinalOffset;
                }
                SuperSwipeRefreshLayout.this.setTargetOffsetTopAndBottom((SuperSwipeRefreshLayout.this.mFrom + ((int) (((float) (i - SuperSwipeRefreshLayout.this.mFrom)) * f))) - SuperSwipeRefreshLayout.this.mHeadViewContainer.getTop(), false);
            }

            public void setAnimationListener(Animation.AnimationListener animationListener) {
                super.setAnimationListener(animationListener);
            }
        };
        this.mAnimateToStartPosition = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                SuperSwipeRefreshLayout.this.moveToStart(f);
            }
        };
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMediumAnimationDuration = getResources().getInteger(17694721);
        setWillNotDraw(false);
        this.mDecelerateInterpolator = new DecelerateInterpolator(DECELERATE_INTERPOLATION_FACTOR);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, LAYOUT_ATTRS);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.mHeaderViewWidth = defaultDisplay.getWidth();
        this.mHeaderViewHeight = (int) (displayMetrics.density * 84.0f);
        this.defaultProgressView = new CircleProgressView(getContext());
        createHeaderViewContainer();
        createFooterViewContainer();
        ViewCompat.setChildrenDrawingOrderEnabled(this, true);
        this.mSpinnerFinalOffset = displayMetrics.density * 64.0f;
        this.density = displayMetrics.density;
        this.mTotalDragDistance = this.mSpinnerFinalOffset;
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i, int i2) {
        if (this.mHeaderViewIndex < 0 && this.mFooterViewIndex < 0) {
            return i2;
        }
        if (i2 == i - 2) {
            return this.mHeaderViewIndex;
        }
        if (i2 == i - 1) {
            return this.mFooterViewIndex;
        }
        int i3 = this.mFooterViewIndex > this.mHeaderViewIndex ? this.mFooterViewIndex : this.mHeaderViewIndex;
        if (i2 < (this.mFooterViewIndex < this.mHeaderViewIndex ? this.mFooterViewIndex : this.mHeaderViewIndex) || i2 >= i3 - 1) {
            return (i2 >= i3 || i2 == i3 + -1) ? i2 + 2 : i2;
        }
        return i2 + 1;
    }

    private void createHeaderViewContainer() {
        double d = (double) this.mHeaderViewHeight;
        Double.isNaN(d);
        double d2 = (double) this.mHeaderViewHeight;
        Double.isNaN(d2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (d * 0.8d), (int) (d2 * 0.8d));
        layoutParams.addRule(14);
        layoutParams.addRule(12);
        this.mHeadViewContainer = new HeadViewContainer(getContext());
        this.mHeadViewContainer.setVisibility(8);
        this.defaultProgressView.setVisibility(0);
        this.defaultProgressView.setOnDraw(false);
        this.mHeadViewContainer.addView(this.defaultProgressView, layoutParams);
        addView(this.mHeadViewContainer);
    }

    private void createFooterViewContainer() {
        this.mFooterViewContainer = new RelativeLayout(getContext());
        this.mFooterViewContainer.setVisibility(8);
        addView(this.mFooterViewContainer);
    }

    public void setOnPullRefreshListener(OnPullRefreshListener onPullRefreshListener) {
        this.mListener = onPullRefreshListener;
    }

    public void setHeaderViewBackgroundColor(int i) {
        this.mHeadViewContainer.setBackgroundColor(i);
    }

    public void setRefreshing(boolean z) {
        int i;
        if (!z || this.mRefreshing == z) {
            setRefreshing(z, false);
            if (this.usingDefaultHeader) {
                this.defaultProgressView.setOnDraw(false);
                return;
            }
            return;
        }
        this.mRefreshing = z;
        if (!this.mUsingCustomStart) {
            i = (int) (this.mSpinnerFinalOffset + ((float) this.mOriginalOffsetTop));
        } else {
            i = (int) this.mSpinnerFinalOffset;
        }
        setTargetOffsetTopAndBottom(i - this.mCurrentTargetOffsetTop, true);
        this.mNotify = false;
        startScaleUpAnimation(this.mRefreshListener);
    }

    private void startScaleUpAnimation(Animation.AnimationListener animationListener) {
        this.mHeadViewContainer.setVisibility(0);
        this.mScaleAnimation = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                SuperSwipeRefreshLayout.this.setAnimationProgress(f);
            }
        };
        this.mScaleAnimation.setDuration((long) this.mMediumAnimationDuration);
        if (animationListener != null) {
            this.mHeadViewContainer.setAnimationListener(animationListener);
        }
        this.mHeadViewContainer.clearAnimation();
        this.mHeadViewContainer.startAnimation(this.mScaleAnimation);
    }

    /* access modifiers changed from: private */
    public void setAnimationProgress(float f) {
        if (!this.usingDefaultHeader) {
            f = 1.0f;
        }
        ViewCompat.setScaleX(this.mHeadViewContainer, f);
        ViewCompat.setScaleY(this.mHeadViewContainer, f);
    }

    private void setRefreshing(boolean z, boolean z2) {
        if (this.mRefreshing != z) {
            this.mNotify = z2;
            ensureTarget();
            this.mRefreshing = z;
            if (this.mRefreshing) {
                animateOffsetToCorrectPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
            } else {
                animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
            }
        }
    }

    /* access modifiers changed from: private */
    public void startScaleDownAnimation(Animation.AnimationListener animationListener) {
        this.mScaleDownAnimation = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                SuperSwipeRefreshLayout.this.setAnimationProgress(1.0f - f);
            }
        };
        this.mScaleDownAnimation.setDuration(150);
        this.mHeadViewContainer.setAnimationListener(animationListener);
        this.mHeadViewContainer.clearAnimation();
        this.mHeadViewContainer.startAnimation(this.mScaleDownAnimation);
    }

    public boolean isRefreshing() {
        return this.mRefreshing;
    }

    private void ensureTarget() {
        if (this.mTarget == null) {
            int i = 0;
            while (i < getChildCount()) {
                View childAt = getChildAt(i);
                if (childAt.equals(this.mHeadViewContainer) || childAt.equals(this.mFooterViewContainer)) {
                    i++;
                } else {
                    this.mTarget = childAt;
                    return;
                }
            }
        }
    }

    public void setDistanceToTriggerSync(int i) {
        this.mTotalDragDistance = (float) i;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() != 0) {
            if (this.mTarget == null) {
                ensureTarget();
            }
            if (this.mTarget != null) {
                int measuredHeight2 = this.mCurrentTargetOffsetTop + this.mHeadViewContainer.getMeasuredHeight();
                if (!this.targetScrollWithLayout) {
                    measuredHeight2 = 0;
                }
                View view = this.mTarget;
                int paddingLeft = getPaddingLeft();
                int paddingTop = (getPaddingTop() + measuredHeight2) - this.pushDistance;
                view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
                int measuredWidth2 = this.mHeadViewContainer.getMeasuredWidth();
                int i5 = measuredWidth / 2;
                int i6 = measuredWidth2 / 2;
                this.mHeadViewContainer.layout(i5 - i6, this.mCurrentTargetOffsetTop, i6 + i5, this.mCurrentTargetOffsetTop + this.mHeadViewContainer.getMeasuredHeight());
                int measuredWidth3 = this.mFooterViewContainer.getMeasuredWidth();
                int i7 = measuredWidth3 / 2;
                this.mFooterViewContainer.layout(i5 - i7, measuredHeight - this.pushDistance, i5 + i7, (measuredHeight + this.mFooterViewContainer.getMeasuredHeight()) - this.pushDistance);
            }
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mTarget == null) {
            ensureTarget();
        }
        if (this.mTarget != null) {
            this.mTarget.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
            this.mHeadViewContainer.measure(View.MeasureSpec.makeMeasureSpec(this.mHeaderViewWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mHeaderViewHeight * 3, 1073741824));
            this.mFooterViewContainer.measure(View.MeasureSpec.makeMeasureSpec(this.mFooterViewWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mFooterViewHeight, 1073741824));
            if (!this.mUsingCustomStart && !this.mOriginalOffsetCalculated) {
                this.mOriginalOffsetCalculated = true;
                int i3 = -this.mHeadViewContainer.getMeasuredHeight();
                this.mOriginalOffsetTop = i3;
                this.mCurrentTargetOffsetTop = i3;
                updateListenerCallBack();
            }
            this.mHeaderViewIndex = -1;
            int i4 = 0;
            while (true) {
                if (i4 >= getChildCount()) {
                    break;
                } else if (getChildAt(i4) == this.mHeadViewContainer) {
                    this.mHeaderViewIndex = i4;
                    break;
                } else {
                    i4++;
                }
            }
            this.mFooterViewIndex = -1;
            for (int i5 = 0; i5 < getChildCount(); i5++) {
                if (getChildAt(i5) == this.mFooterViewContainer) {
                    this.mFooterViewIndex = i5;
                    return;
                }
            }
        }
    }

    public boolean isChildScrollToTop() {
        if (Build.VERSION.SDK_INT >= 14) {
            return !ViewCompat.canScrollVertically(this.mTarget, -1);
        }
        if (this.mTarget instanceof AbsListView) {
            AbsListView absListView = (AbsListView) this.mTarget;
            if (absListView.getChildCount() <= 0) {
                return true;
            }
            if (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop()) {
                return false;
            }
            return true;
        } else if (this.mTarget.getScrollY() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        ensureTarget();
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (this.mReturningToStart && actionMasked == 0) {
            this.mReturningToStart = false;
        }
        if (!isEnabled() || this.mReturningToStart || !isChildScrollToTop()) {
            return false;
        }
        if (this.mRefreshing) {
            return true;
        }
        if (actionMasked != 6) {
            switch (actionMasked) {
                case 0:
                    setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mHeadViewContainer.getTop(), true);
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                    this.mIsBeingDragged = false;
                    float motionEventY = getMotionEventY(motionEvent, this.mActivePointerId);
                    if (motionEventY != -1.0f) {
                        this.mInitialMotionY = motionEventY;
                        break;
                    } else {
                        return false;
                    }
                case 1:
                case 3:
                    this.mIsBeingDragged = false;
                    this.mActivePointerId = -1;
                    break;
                case 2:
                    break;
            }
            if (this.mActivePointerId == -1) {
                return false;
            }
            float motionEventY2 = getMotionEventY(motionEvent, this.mActivePointerId);
            if (motionEventY2 == -1.0f) {
                return false;
            }
            if (isChildScrollToTop() && motionEventY2 - this.mInitialMotionY > ((float) this.mTouchSlop) && !this.mIsBeingDragged) {
                this.mIsBeingDragged = true;
            }
        } else {
            onSecondaryPointerUp(motionEvent);
        }
        return this.mIsBeingDragged;
    }

    private float getMotionEventY(MotionEvent motionEvent, int i) {
        int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i);
        if (findPointerIndex < 0) {
            return -1.0f;
        }
        return MotionEventCompat.getY(motionEvent, findPointerIndex);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (this.mReturningToStart && actionMasked == 0) {
            this.mReturningToStart = false;
        }
        if (!isEnabled() || this.mReturningToStart || !isChildScrollToTop()) {
            return false;
        }
        if (isChildScrollToTop()) {
            return handlerPullTouchEvent(motionEvent, actionMasked);
        }
        return super.onTouchEvent(motionEvent);
    }

    private boolean handlerPullTouchEvent(MotionEvent motionEvent, int i) {
        switch (i) {
            case 0:
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                this.mIsBeingDragged = false;
                break;
            case 1:
            case 3:
                if (this.mActivePointerId == -1) {
                    return false;
                }
                this.mIsBeingDragged = false;
                if ((MotionEventCompat.getY(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId)) - this.mInitialMotionY) * 0.5f > this.mTotalDragDistance) {
                    setRefreshing(true, true);
                } else {
                    this.mRefreshing = false;
                    AnonymousClass4 r12 = null;
                    if (!this.mScale) {
                        r12 = new Animation.AnimationListener() {
                            public void onAnimationRepeat(Animation animation) {
                            }

                            public void onAnimationStart(Animation animation) {
                            }

                            public void onAnimationEnd(Animation animation) {
                                if (!SuperSwipeRefreshLayout.this.mScale) {
                                    SuperSwipeRefreshLayout.this.startScaleDownAnimation((Animation.AnimationListener) null);
                                }
                            }
                        };
                    }
                    animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, r12);
                }
                this.mActivePointerId = -1;
                return false;
            case 2:
                int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                if (findPointerIndex >= 0) {
                    float y = (MotionEventCompat.getY(motionEvent, findPointerIndex) - this.mInitialMotionY) * 0.5f;
                    if (this.mIsBeingDragged) {
                        float f = y / this.mTotalDragDistance;
                        if (f >= 0.0f) {
                            float min = Math.min(1.0f, Math.abs(f));
                            float abs = Math.abs(y) - this.mTotalDragDistance;
                            float f2 = this.mUsingCustomStart ? this.mSpinnerFinalOffset - ((float) this.mOriginalOffsetTop) : this.mSpinnerFinalOffset;
                            double max = (double) (Math.max(0.0f, Math.min(abs, f2 * DECELERATE_INTERPOLATION_FACTOR) / f2) / 4.0f);
                            double pow = Math.pow(max, 2.0d);
                            Double.isNaN(max);
                            int i2 = this.mOriginalOffsetTop + ((int) ((f2 * min) + (((float) (max - pow)) * DECELERATE_INTERPOLATION_FACTOR * f2 * DECELERATE_INTERPOLATION_FACTOR)));
                            if (this.mHeadViewContainer.getVisibility() != 0) {
                                this.mHeadViewContainer.setVisibility(0);
                            }
                            if (!this.mScale) {
                                ViewCompat.setScaleX(this.mHeadViewContainer, 1.0f);
                                ViewCompat.setScaleY(this.mHeadViewContainer, 1.0f);
                            }
                            if (this.usingDefaultHeader) {
                                float f3 = y / this.mTotalDragDistance;
                                if (f3 >= 1.0f) {
                                    f3 = 1.0f;
                                }
                                ViewCompat.setScaleX(this.defaultProgressView, f3);
                                ViewCompat.setScaleY(this.defaultProgressView, f3);
                                ViewCompat.setAlpha(this.defaultProgressView, f3);
                            }
                            if (y < this.mTotalDragDistance) {
                                if (this.mScale) {
                                    setAnimationProgress(y / this.mTotalDragDistance);
                                }
                                if (this.mListener != null) {
                                    this.mListener.onPullEnable(false);
                                }
                            } else if (this.mListener != null) {
                                this.mListener.onPullEnable(true);
                            }
                            setTargetOffsetTopAndBottom(i2 - this.mCurrentTargetOffsetTop, true);
                            break;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
                break;
            case 5:
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, MotionEventCompat.getActionIndex(motionEvent));
                break;
            case 6:
                onSecondaryPointerUp(motionEvent);
                break;
        }
        return true;
    }

    private void animateOffsetToCorrectPosition(int i, Animation.AnimationListener animationListener) {
        this.mFrom = i;
        this.mAnimateToCorrectPosition.reset();
        this.mAnimateToCorrectPosition.setDuration(200);
        this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
        if (animationListener != null) {
            this.mHeadViewContainer.setAnimationListener(animationListener);
        }
        this.mHeadViewContainer.clearAnimation();
        this.mHeadViewContainer.startAnimation(this.mAnimateToCorrectPosition);
    }

    private void animateOffsetToStartPosition(int i, Animation.AnimationListener animationListener) {
        if (this.mScale) {
            startScaleDownReturnToStartAnimation(i, animationListener);
        } else {
            this.mFrom = i;
            this.mAnimateToStartPosition.reset();
            this.mAnimateToStartPosition.setDuration(200);
            this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
            if (animationListener != null) {
                this.mHeadViewContainer.setAnimationListener(animationListener);
            }
            this.mHeadViewContainer.clearAnimation();
            this.mHeadViewContainer.startAnimation(this.mAnimateToStartPosition);
        }
        resetTargetLayoutDelay(200);
    }

    public void resetTargetLayoutDelay(int i) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SuperSwipeRefreshLayout.this.resetTargetLayout();
            }
        }, (long) i);
    }

    public void resetTargetLayout() {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        View view = this.mTarget;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        view.layout(paddingLeft, paddingTop, ((view.getWidth() - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((view.getHeight() - getPaddingTop()) - getPaddingBottom()) + paddingTop);
        int measuredWidth2 = this.mHeadViewContainer.getMeasuredWidth();
        int i = measuredWidth / 2;
        int i2 = measuredWidth2 / 2;
        this.mHeadViewContainer.layout(i - i2, -this.mHeadViewContainer.getMeasuredHeight(), i2 + i, 0);
        int measuredWidth3 = this.mFooterViewContainer.getMeasuredWidth();
        int i3 = measuredWidth3 / 2;
        this.mFooterViewContainer.layout(i - i3, measuredHeight, i + i3, this.mFooterViewContainer.getMeasuredHeight() + measuredHeight);
    }

    /* access modifiers changed from: private */
    public void moveToStart(float f) {
        setTargetOffsetTopAndBottom((this.mFrom + ((int) (((float) (this.mOriginalOffsetTop - this.mFrom)) * f))) - this.mHeadViewContainer.getTop(), false);
    }

    private void startScaleDownReturnToStartAnimation(int i, Animation.AnimationListener animationListener) {
        this.mFrom = i;
        this.mStartingScale = ViewCompat.getScaleX(this.mHeadViewContainer);
        this.mScaleDownToStartAnimation = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                SuperSwipeRefreshLayout.this.setAnimationProgress(SuperSwipeRefreshLayout.this.mStartingScale + ((-SuperSwipeRefreshLayout.this.mStartingScale) * f));
                SuperSwipeRefreshLayout.this.moveToStart(f);
            }
        };
        this.mScaleDownToStartAnimation.setDuration(150);
        if (animationListener != null) {
            this.mHeadViewContainer.setAnimationListener(animationListener);
        }
        this.mHeadViewContainer.clearAnimation();
        this.mHeadViewContainer.startAnimation(this.mScaleDownToStartAnimation);
    }

    /* access modifiers changed from: private */
    public void setTargetOffsetTopAndBottom(int i, boolean z) {
        this.mHeadViewContainer.bringToFront();
        this.mHeadViewContainer.offsetTopAndBottom(i);
        this.mCurrentTargetOffsetTop = this.mHeadViewContainer.getTop();
        if (z && Build.VERSION.SDK_INT < 11) {
            invalidate();
        }
        updateListenerCallBack();
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.mActivePointerId) {
            this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex == 0 ? 1 : 0);
        }
    }

    private class HeadViewContainer extends RelativeLayout {
        private Animation.AnimationListener mListener;

        public HeadViewContainer(Context context) {
            super(context);
        }

        public void setAnimationListener(Animation.AnimationListener animationListener) {
            this.mListener = animationListener;
        }

        public void onAnimationStart() {
            super.onAnimationStart();
            if (this.mListener != null) {
                this.mListener.onAnimationStart(getAnimation());
            }
        }

        public void onAnimationEnd() {
            super.onAnimationEnd();
            if (this.mListener != null) {
                this.mListener.onAnimationEnd(getAnimation());
            }
        }
    }

    public boolean isTargetScrollWithLayout() {
        return this.targetScrollWithLayout;
    }

    public void setTargetScrollWithLayout(boolean z) {
        this.targetScrollWithLayout = z;
    }

    public class OnPullRefreshListenerAdapter implements OnPullRefreshListener {
        public void onPullDistance(int i) {
        }

        public void onPullEnable(boolean z) {
        }

        public void onRefresh() {
        }

        public OnPullRefreshListenerAdapter() {
        }
    }

    public void setDefaultCircleProgressColor(int i) {
        if (this.usingDefaultHeader) {
            this.defaultProgressView.setProgressColor(i);
        }
    }

    public void setDefaultCircleBackgroundColor(int i) {
        if (this.usingDefaultHeader) {
            this.defaultProgressView.setCircleBackgroundColor(i);
        }
    }

    public void setDefaultCircleShadowColor(int i) {
        if (this.usingDefaultHeader) {
            this.defaultProgressView.setShadowColor(i);
        }
    }

    public class CircleProgressView extends View implements Runnable {
        private static final int PEROID = 16;
        private Paint bgPaint;
        private RectF bgRect = null;
        private int circleBackgroundColor = -1;
        private int height;
        private boolean isOnDraw = false;
        private boolean isRunning = false;
        private RectF ovalRect = null;
        private int progressColor = Color.d;
        private Paint progressPaint;
        private int shadowColor = -6710887;
        private int speed = 8;
        private int startAngle = 0;
        private int swipeAngle;
        private int width;

        public CircleProgressView(Context context) {
            super(context);
        }

        public CircleProgressView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public CircleProgressView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }

        /* access modifiers changed from: protected */
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawArc(getBgRect(), 0.0f, 360.0f, false, createBgPaint());
            if ((this.startAngle / 360) % 2 == 0) {
                this.swipeAngle = (this.startAngle % 720) / 2;
            } else {
                this.swipeAngle = 360 - ((this.startAngle % 720) / 2);
            }
            canvas.drawArc(getOvalRect(), (float) this.startAngle, (float) this.swipeAngle, false, createPaint());
        }

        private RectF getBgRect() {
            this.width = getWidth();
            this.height = getHeight();
            if (this.bgRect == null) {
                int access$1700 = (int) (SuperSwipeRefreshLayout.this.density * SuperSwipeRefreshLayout.DECELERATE_INTERPOLATION_FACTOR);
                float f = (float) access$1700;
                this.bgRect = new RectF(f, f, (float) (this.width - access$1700), (float) (this.height - access$1700));
            }
            return this.bgRect;
        }

        private RectF getOvalRect() {
            this.width = getWidth();
            this.height = getHeight();
            if (this.ovalRect == null) {
                int access$1700 = (int) (SuperSwipeRefreshLayout.this.density * 8.0f);
                float f = (float) access$1700;
                this.ovalRect = new RectF(f, f, (float) (this.width - access$1700), (float) (this.height - access$1700));
            }
            return this.ovalRect;
        }

        public void setProgressColor(int i) {
            this.progressColor = i;
        }

        public void setCircleBackgroundColor(int i) {
            this.circleBackgroundColor = i;
        }

        public void setShadowColor(int i) {
            this.shadowColor = i;
        }

        private Paint createPaint() {
            if (this.progressPaint == null) {
                this.progressPaint = new Paint();
                this.progressPaint.setStrokeWidth((float) ((int) (SuperSwipeRefreshLayout.this.density * 3.0f)));
                this.progressPaint.setStyle(Paint.Style.STROKE);
                this.progressPaint.setAntiAlias(true);
            }
            this.progressPaint.setColor(this.progressColor);
            return this.progressPaint;
        }

        private Paint createBgPaint() {
            if (this.bgPaint == null) {
                this.bgPaint = new Paint();
                this.bgPaint.setColor(this.circleBackgroundColor);
                this.bgPaint.setStyle(Paint.Style.FILL);
                this.bgPaint.setAntiAlias(true);
                if (Build.VERSION.SDK_INT >= 11) {
                    setLayerType(1, this.bgPaint);
                }
                this.bgPaint.setShadowLayer(4.0f, 0.0f, SuperSwipeRefreshLayout.DECELERATE_INTERPOLATION_FACTOR, this.shadowColor);
            }
            return this.bgPaint;
        }

        public void setPullDistance(int i) {
            this.startAngle = i * 2;
            postInvalidate();
        }

        public void run() {
            while (this.isOnDraw) {
                this.isRunning = true;
                long currentTimeMillis = System.currentTimeMillis();
                this.startAngle += this.speed;
                postInvalidate();
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                if (currentTimeMillis2 < 16) {
                    try {
                        Thread.sleep(16 - currentTimeMillis2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void setOnDraw(boolean z) {
            this.isOnDraw = z;
        }

        public void setSpeed(int i) {
            this.speed = i;
        }

        public boolean isRunning() {
            return this.isRunning;
        }

        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
        }

        /* access modifiers changed from: protected */
        public void onDetachedFromWindow() {
            this.isOnDraw = false;
            super.onDetachedFromWindow();
        }
    }
}
