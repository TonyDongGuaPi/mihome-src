package com.xiaomi.smarthome.miio.camera.cloudstorage.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;
import com.libra.Color;

public class RecyclerViewRefreshLayout extends ViewGroup {
    private static final int ANIMATE_TO_START_DURATION = 200;
    private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
    public static final int BOTH = 3;
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    private static final int DEFAULT_CIRCLE_TARGET = 64;
    public static final int DISABLED = 0;
    private static final float DRAG_RATE = 0.5f;
    private static final int HEADER_VIEW_HEIGHT = 60;
    private static final int INVALID_POINTER = -1;
    private static final int[] LAYOUT_ATTRS = {16842766};
    public static final int PULL_FROM_END = 2;
    public static final int PULL_FROM_START = 1;
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
    private RelativeLayout mFooterRefreshViewContainer;
    private int mFooterViewHeight;
    private int mFooterViewIndex;
    private int mFooterViewWidth;
    protected int mFrom;
    private int mHeaderViewHeight;
    private int mHeaderViewIndex;
    private int mHeaderViewWidth;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    /* access modifiers changed from: private */
    public OnPullRefreshListener mListener;
    /* access modifiers changed from: private */
    public boolean mLoadMore;
    private int mMediumAnimationDuration;
    private int mMode;
    /* access modifiers changed from: private */
    public boolean mNotify;
    /* access modifiers changed from: private */
    public OnPushLoadMoreListener mOnPushLoadMoreListener;
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
    /* access modifiers changed from: private */
    public HeadViewContainer mTopRefreshViewContainer;
    private float mTotalDragDistance;
    private int mTouchSlop;
    /* access modifiers changed from: private */
    public boolean mUsingCustomStart;
    /* access modifiers changed from: private */
    public int pushDistance;
    private boolean targetScrollWithLayout;
    /* access modifiers changed from: private */
    public boolean usingDefaultHeader;

    public interface OnPullRefreshListener {
        void onPullDistance(int i);

        void onPullEnable(boolean z);

        void onRefresh();
    }

    public interface OnPushLoadMoreListener {
        void onLoadMore();

        void onPushDistance(int i);

        void onPushEnable(boolean z);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
    }

    /* access modifiers changed from: private */
    public void updateListenerCallBack() {
        int height = this.mCurrentTargetOffsetTop + this.mTopRefreshViewContainer.getHeight();
        if (this.mListener != null) {
            this.mListener.onPullDistance(height);
        }
        if (this.usingDefaultHeader && this.isProgressEnable) {
            this.defaultProgressView.setPullDistance(height);
        }
    }

    public void setTopRefreshView(View view) {
        if (view != null && this.mTopRefreshViewContainer != null) {
            this.usingDefaultHeader = false;
            this.mTopRefreshViewContainer.removeAllViews();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.mHeaderViewWidth, this.mHeaderViewHeight);
            layoutParams.addRule(12);
            this.mTopRefreshViewContainer.addView(view, layoutParams);
        }
    }

    public void setFooterRefreshView(View view) {
        if (view != null && this.mFooterRefreshViewContainer != null) {
            this.mFooterRefreshViewContainer.removeAllViews();
            this.mFooterRefreshViewContainer.addView(view, new RelativeLayout.LayoutParams(this.mFooterViewWidth, this.mFooterViewHeight));
        }
    }

    public RecyclerViewRefreshLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public RecyclerViewRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMode = 1;
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
        this.usingDefaultHeader = false;
        this.density = 1.0f;
        this.isProgressEnable = true;
        this.mRefreshListener = new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                boolean unused = RecyclerViewRefreshLayout.this.isProgressEnable = false;
            }

            public void onAnimationEnd(Animation animation) {
                boolean unused = RecyclerViewRefreshLayout.this.isProgressEnable = true;
                if (!RecyclerViewRefreshLayout.this.mRefreshing) {
                    RecyclerViewRefreshLayout.this.mTopRefreshViewContainer.setVisibility(8);
                    if (RecyclerViewRefreshLayout.this.mScale) {
                        RecyclerViewRefreshLayout.this.setAnimationProgress(0.0f);
                    } else {
                        RecyclerViewRefreshLayout.this.setTargetOffsetTopAndBottom(RecyclerViewRefreshLayout.this.mOriginalOffsetTop - RecyclerViewRefreshLayout.this.mCurrentTargetOffsetTop, true);
                    }
                } else if (RecyclerViewRefreshLayout.this.mNotify) {
                    if (RecyclerViewRefreshLayout.this.usingDefaultHeader) {
                        ViewCompat.setAlpha(RecyclerViewRefreshLayout.this.defaultProgressView, 1.0f);
                        RecyclerViewRefreshLayout.this.defaultProgressView.setOnDraw(true);
                        new Thread(RecyclerViewRefreshLayout.this.defaultProgressView).start();
                    }
                    if (RecyclerViewRefreshLayout.this.mListener != null) {
                        RecyclerViewRefreshLayout.this.mListener.onRefresh();
                    }
                }
                int unused2 = RecyclerViewRefreshLayout.this.mCurrentTargetOffsetTop = RecyclerViewRefreshLayout.this.mTopRefreshViewContainer.getTop();
                RecyclerViewRefreshLayout.this.updateListenerCallBack();
            }
        };
        this.mAnimateToCorrectPosition = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                int i;
                if (!RecyclerViewRefreshLayout.this.mUsingCustomStart) {
                    i = (int) (RecyclerViewRefreshLayout.this.mSpinnerFinalOffset - ((float) Math.abs(RecyclerViewRefreshLayout.this.mOriginalOffsetTop)));
                } else {
                    i = (int) RecyclerViewRefreshLayout.this.mSpinnerFinalOffset;
                }
                RecyclerViewRefreshLayout.this.setTargetOffsetTopAndBottom((RecyclerViewRefreshLayout.this.mFrom + ((int) (((float) (i - RecyclerViewRefreshLayout.this.mFrom)) * f))) - RecyclerViewRefreshLayout.this.mTopRefreshViewContainer.getTop(), false);
            }

            public void setAnimationListener(Animation.AnimationListener animationListener) {
                super.setAnimationListener(animationListener);
            }
        };
        this.mAnimateToStartPosition = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                RecyclerViewRefreshLayout.this.moveToStart(f);
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
        this.mFooterViewWidth = defaultDisplay.getWidth();
        this.mHeaderViewHeight = (int) (displayMetrics.density * 60.0f);
        this.mFooterViewHeight = (int) (displayMetrics.density * 60.0f);
        this.defaultProgressView = new CircleProgressView(getContext());
        createTopRefreshViewContainer();
        createFooterRefreshViewContainer();
        ViewCompat.setChildrenDrawingOrderEnabled(this, true);
        this.mSpinnerFinalOffset = displayMetrics.density * 64.0f;
        this.density = displayMetrics.density;
        this.mTotalDragDistance = this.mSpinnerFinalOffset;
    }

    public boolean onInterceptHoverEvent(MotionEvent motionEvent) {
        return super.onInterceptHoverEvent(motionEvent);
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

    private void createTopRefreshViewContainer() {
        double d = (double) this.mHeaderViewHeight;
        Double.isNaN(d);
        double d2 = (double) this.mHeaderViewHeight;
        Double.isNaN(d2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (d * 0.8d), (int) (d2 * 0.8d));
        layoutParams.addRule(14);
        layoutParams.addRule(12);
        this.mTopRefreshViewContainer = new HeadViewContainer(getContext());
        this.mTopRefreshViewContainer.setVisibility(8);
        this.defaultProgressView.setVisibility(0);
        this.defaultProgressView.setOnDraw(false);
        this.mTopRefreshViewContainer.addView(this.defaultProgressView, layoutParams);
        addView(this.mTopRefreshViewContainer);
    }

    private void createFooterRefreshViewContainer() {
        this.mFooterRefreshViewContainer = new RelativeLayout(getContext());
        this.mFooterRefreshViewContainer.setVisibility(8);
        addView(this.mFooterRefreshViewContainer);
    }

    public void setOnPullRefreshListener(OnPullRefreshListener onPullRefreshListener) {
        this.mListener = onPullRefreshListener;
    }

    public void setHeaderViewBackgroundColor(int i) {
        this.mTopRefreshViewContainer.setBackgroundColor(i);
    }

    public void setOnPushLoadMoreListener(OnPushLoadMoreListener onPushLoadMoreListener) {
        this.mOnPushLoadMoreListener = onPushLoadMoreListener;
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
        this.mTopRefreshViewContainer.setVisibility(0);
        this.mScaleAnimation = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                RecyclerViewRefreshLayout.this.setAnimationProgress(f);
            }
        };
        this.mScaleAnimation.setDuration((long) this.mMediumAnimationDuration);
        if (animationListener != null) {
            this.mTopRefreshViewContainer.setAnimationListener(animationListener);
        }
        this.mTopRefreshViewContainer.clearAnimation();
        this.mTopRefreshViewContainer.startAnimation(this.mScaleAnimation);
    }

    /* access modifiers changed from: private */
    public void setAnimationProgress(float f) {
        if (!this.usingDefaultHeader) {
            f = 1.0f;
        }
        ViewCompat.setScaleX(this.mTopRefreshViewContainer, f);
        ViewCompat.setScaleY(this.mTopRefreshViewContainer, f);
    }

    private void setRefreshing(boolean z, boolean z2) {
        if (this.mRefreshing != z) {
            this.mNotify = z2;
            ensureTarget();
            this.mRefreshing = z;
            if (this.mRefreshing) {
                animateOffsetToCorrectPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
            } else {
                startScaleDownAnimation(this.mRefreshListener);
            }
        }
    }

    /* access modifiers changed from: private */
    public void startScaleDownAnimation(Animation.AnimationListener animationListener) {
        this.mScaleDownAnimation = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                RecyclerViewRefreshLayout.this.setAnimationProgress(1.0f - f);
            }
        };
        this.mScaleDownAnimation.setDuration(150);
        this.mTopRefreshViewContainer.setAnimationListener(animationListener);
        this.mTopRefreshViewContainer.clearAnimation();
        this.mTopRefreshViewContainer.startAnimation(this.mScaleDownAnimation);
    }

    public boolean isRefreshing() {
        return this.mRefreshing;
    }

    private void ensureTarget() {
        if (this.mTarget == null) {
            int i = 0;
            while (i < getChildCount()) {
                View childAt = getChildAt(i);
                if (childAt.equals(this.mTopRefreshViewContainer) || childAt.equals(this.mFooterRefreshViewContainer) || !(childAt instanceof RecyclerView)) {
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
                int height = this.mCurrentTargetOffsetTop + this.mTopRefreshViewContainer.getHeight();
                if (!this.targetScrollWithLayout) {
                    height = 0;
                }
                if (this.mTopRefreshViewContainer.getHeight() == 0) {
                    height = 0;
                }
                View view = this.mTarget;
                int paddingLeft = getPaddingLeft();
                int paddingTop = (getPaddingTop() + height) - this.pushDistance;
                view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
                int measuredWidth2 = this.mTopRefreshViewContainer.getMeasuredWidth();
                int i5 = measuredWidth / 2;
                int i6 = measuredWidth2 / 2;
                this.mTopRefreshViewContainer.layout(i5 - i6, this.mCurrentTargetOffsetTop, i6 + i5, this.mCurrentTargetOffsetTop + this.mTopRefreshViewContainer.getMeasuredHeight());
                int measuredWidth3 = this.mFooterRefreshViewContainer.getMeasuredWidth();
                int i7 = measuredWidth3 / 2;
                this.mFooterRefreshViewContainer.layout(i5 - i7, measuredHeight - this.pushDistance, i5 + i7, (measuredHeight + this.mFooterRefreshViewContainer.getMeasuredHeight()) - this.pushDistance);
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
            this.mTopRefreshViewContainer.measure(View.MeasureSpec.makeMeasureSpec(this.mHeaderViewWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mHeaderViewHeight, 1073741824));
            this.mFooterRefreshViewContainer.measure(View.MeasureSpec.makeMeasureSpec(this.mFooterViewWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mFooterViewHeight, 1073741824));
            if (!this.mUsingCustomStart && !this.mOriginalOffsetCalculated) {
                this.mOriginalOffsetCalculated = true;
                int i3 = -this.mTopRefreshViewContainer.getMeasuredHeight();
                this.mOriginalOffsetTop = i3;
                this.mCurrentTargetOffsetTop = i3;
                updateListenerCallBack();
            }
            this.mHeaderViewIndex = -1;
            int i4 = 0;
            while (true) {
                if (i4 >= getChildCount()) {
                    break;
                } else if (getChildAt(i4) == this.mTopRefreshViewContainer) {
                    this.mHeaderViewIndex = i4;
                    break;
                } else {
                    i4++;
                }
            }
            this.mFooterViewIndex = -1;
            for (int i5 = 0; i5 < getChildCount(); i5++) {
                if (getChildAt(i5) == this.mFooterRefreshViewContainer) {
                    this.mFooterViewIndex = i5;
                    return;
                }
            }
        }
    }

    public boolean isChildScrollToTop() {
        View view = this.mTarget;
        if (Build.VERSION.SDK_INT >= 14) {
            return !ViewCompat.canScrollVertically(view, -1);
        }
        if (view.getScrollY() <= 0) {
            return true;
        }
        return false;
    }

    public boolean isChildScrollToBottom() {
        if (isChildScrollToTop() || !(this.mTarget instanceof RecyclerView)) {
            return false;
        }
        RecyclerView recyclerView = (RecyclerView) this.mTarget;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int itemCount = recyclerView.getAdapter().getItemCount();
        if (!(layoutManager instanceof LinearLayoutManager) || itemCount <= 0) {
            if (layoutManager instanceof StaggeredGridLayoutManager) {
                int[] iArr = new int[2];
                ((StaggeredGridLayoutManager) layoutManager).findLastCompletelyVisibleItemPositions(iArr);
                if (Math.max(iArr[0], iArr[1]) == itemCount - 1) {
                    return true;
                }
            }
        } else if (((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition() == itemCount - 1) {
            return true;
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        ensureTarget();
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (this.mReturningToStart && actionMasked == 0) {
            this.mReturningToStart = false;
        }
        if (this.mRefreshing) {
            return true;
        }
        if (!isEnabled() || this.mReturningToStart || this.mLoadMore || (!isChildScrollToTop() && !isChildScrollToBottom())) {
            return false;
        }
        if (actionMasked != 6) {
            switch (actionMasked) {
                case 0:
                    setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mTopRefreshViewContainer.getTop(), true);
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
                    if (this.mActivePointerId != -1) {
                        if (!this.mRefreshing) {
                            float motionEventY2 = getMotionEventY(motionEvent, this.mActivePointerId);
                            if (motionEventY2 != -1.0f) {
                                if (!isChildScrollToBottom()) {
                                    if ((this.mMode & 1) != 0) {
                                        if (motionEventY2 - this.mInitialMotionY > ((float) this.mTouchSlop) && !this.mIsBeingDragged) {
                                            this.mIsBeingDragged = true;
                                            break;
                                        }
                                    } else {
                                        this.mActivePointerId = -1;
                                        break;
                                    }
                                } else if ((this.mMode & 2) != 0) {
                                    if (this.mInitialMotionY - motionEventY2 > ((float) this.mTouchSlop) && !this.mIsBeingDragged) {
                                        this.mIsBeingDragged = true;
                                        break;
                                    }
                                } else {
                                    this.mActivePointerId = -1;
                                    break;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return true;
                        }
                    } else {
                        return false;
                    }
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
        if (!isEnabled() || this.mReturningToStart || (!isChildScrollToTop() && !isChildScrollToBottom())) {
            return false;
        }
        if (this.mRefreshing) {
            return true;
        }
        if (isChildScrollToBottom()) {
            return handlerPushTouchEvent(motionEvent, actionMasked);
        }
        return handlerPullTouchEvent(motionEvent, actionMasked);
    }

    public void addOnLayoutChangeListener(View.OnLayoutChangeListener onLayoutChangeListener) {
        super.addOnLayoutChangeListener(onLayoutChangeListener);
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
                    if (i == 1) {
                        Log.e("refresh ", "Got ACTION_UP event but don't have an active pointer id.");
                    }
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
                                if (!RecyclerViewRefreshLayout.this.mScale) {
                                    RecyclerViewRefreshLayout.this.startScaleDownAnimation((Animation.AnimationListener) null);
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
                            if (this.mTopRefreshViewContainer.getVisibility() != 0) {
                                this.mTopRefreshViewContainer.setVisibility(0);
                            }
                            if (!this.mScale) {
                                ViewCompat.setScaleX(this.mTopRefreshViewContainer, 1.0f);
                                ViewCompat.setScaleY(this.mTopRefreshViewContainer, 1.0f);
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

    private boolean handlerPushTouchEvent(MotionEvent motionEvent, int i) {
        boolean z = false;
        switch (i) {
            case 0:
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                this.mIsBeingDragged = false;
                break;
            case 1:
            case 3:
                if (this.mActivePointerId == -1) {
                    if (i == 1) {
                        Log.e("refresh", "Got ACTION_UP event but don't have an active pointer id.");
                    }
                    return false;
                }
                float y = (this.mInitialMotionY - MotionEventCompat.getY(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId))) * 0.5f;
                this.mIsBeingDragged = false;
                this.mActivePointerId = -1;
                if (y < ((float) this.mFooterViewHeight) || this.mOnPushLoadMoreListener == null) {
                    this.pushDistance = 0;
                } else {
                    this.pushDistance = this.mFooterViewHeight;
                }
                if (Build.VERSION.SDK_INT < 11) {
                    updateFooterViewPosition();
                    if (this.pushDistance == this.mFooterViewHeight && this.mOnPushLoadMoreListener != null) {
                        this.mLoadMore = true;
                        this.mOnPushLoadMoreListener.onLoadMore();
                    }
                } else {
                    animatorFooterToBottom((int) y, this.pushDistance);
                }
                return false;
            case 2:
                int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                if (findPointerIndex >= 0) {
                    float y2 = (this.mInitialMotionY - MotionEventCompat.getY(motionEvent, findPointerIndex)) * 0.5f;
                    if (this.mIsBeingDragged) {
                        this.pushDistance = (int) y2;
                        updateFooterViewPosition();
                        if (this.mOnPushLoadMoreListener != null) {
                            OnPushLoadMoreListener onPushLoadMoreListener = this.mOnPushLoadMoreListener;
                            if (this.pushDistance >= this.mFooterViewHeight) {
                                z = true;
                            }
                            onPushLoadMoreListener.onPushEnable(z);
                            break;
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

    @TargetApi(11)
    private void animatorFooterToBottom(int i, final int i2) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i, i2});
        ofInt.setDuration(150);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int unused = RecyclerViewRefreshLayout.this.pushDistance = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                RecyclerViewRefreshLayout.this.updateFooterViewPosition();
            }
        });
        ofInt.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (i2 <= 0 || RecyclerViewRefreshLayout.this.mOnPushLoadMoreListener == null) {
                    RecyclerViewRefreshLayout.this.resetTargetLayout();
                    boolean unused = RecyclerViewRefreshLayout.this.mLoadMore = false;
                    return;
                }
                boolean unused2 = RecyclerViewRefreshLayout.this.mLoadMore = true;
                RecyclerViewRefreshLayout.this.mOnPushLoadMoreListener.onLoadMore();
            }
        });
        ofInt.setInterpolator(this.mDecelerateInterpolator);
        ofInt.start();
    }

    public void setMode(int i) {
        this.mMode = i;
    }

    public void setLoadMore(boolean z) {
        if (!z && this.mLoadMore) {
            if (Build.VERSION.SDK_INT < 11) {
                this.mLoadMore = false;
                this.pushDistance = 0;
                updateFooterViewPosition();
                return;
            }
            animatorFooterToBottom(this.mFooterViewHeight, 0);
        }
    }

    private void animateOffsetToCorrectPosition(int i, Animation.AnimationListener animationListener) {
        this.mFrom = i;
        this.mAnimateToCorrectPosition.reset();
        this.mAnimateToCorrectPosition.setDuration(200);
        this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
        if (animationListener != null) {
            this.mTopRefreshViewContainer.setAnimationListener(animationListener);
        }
        this.mTopRefreshViewContainer.clearAnimation();
        this.mTopRefreshViewContainer.startAnimation(this.mAnimateToCorrectPosition);
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
                this.mTopRefreshViewContainer.setAnimationListener(animationListener);
            }
            this.mTopRefreshViewContainer.clearAnimation();
            this.mTopRefreshViewContainer.startAnimation(this.mAnimateToStartPosition);
        }
        resetTargetLayoutDelay(200);
    }

    public void resetTargetLayoutDelay(int i) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                RecyclerViewRefreshLayout.this.resetTargetLayout();
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
        int measuredWidth2 = this.mTopRefreshViewContainer.getMeasuredWidth();
        int i = measuredWidth / 2;
        int i2 = measuredWidth2 / 2;
        this.mTopRefreshViewContainer.layout(i - i2, -this.mTopRefreshViewContainer.getMeasuredHeight(), i2 + i, 0);
        int measuredWidth3 = this.mFooterRefreshViewContainer.getMeasuredWidth();
        int i3 = measuredWidth3 / 2;
        this.mFooterRefreshViewContainer.layout(i - i3, measuredHeight, i + i3, this.mFooterRefreshViewContainer.getMeasuredHeight() + measuredHeight);
    }

    /* access modifiers changed from: private */
    public void moveToStart(float f) {
        setTargetOffsetTopAndBottom((this.mFrom + ((int) (((float) (this.mOriginalOffsetTop - this.mFrom)) * f))) - this.mTopRefreshViewContainer.getTop(), false);
    }

    private void startScaleDownReturnToStartAnimation(int i, Animation.AnimationListener animationListener) {
        this.mFrom = i;
        this.mStartingScale = ViewCompat.getScaleX(this.mTopRefreshViewContainer);
        this.mScaleDownToStartAnimation = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                RecyclerViewRefreshLayout.this.setAnimationProgress(RecyclerViewRefreshLayout.this.mStartingScale + ((-RecyclerViewRefreshLayout.this.mStartingScale) * f));
                RecyclerViewRefreshLayout.this.moveToStart(f);
            }
        };
        this.mScaleDownToStartAnimation.setDuration(150);
        if (animationListener != null) {
            this.mTopRefreshViewContainer.setAnimationListener(animationListener);
        }
        this.mTopRefreshViewContainer.clearAnimation();
        this.mTopRefreshViewContainer.startAnimation(this.mScaleDownToStartAnimation);
    }

    /* access modifiers changed from: private */
    public void setTargetOffsetTopAndBottom(int i, boolean z) {
        this.mTopRefreshViewContainer.bringToFront();
        this.mTopRefreshViewContainer.offsetTopAndBottom(i);
        this.mCurrentTargetOffsetTop = this.mTopRefreshViewContainer.getTop();
        if (z && Build.VERSION.SDK_INT < 11) {
            invalidate();
        }
        updateListenerCallBack();
    }

    /* access modifiers changed from: private */
    public void updateFooterViewPosition() {
        this.mFooterRefreshViewContainer.setVisibility(0);
        this.mFooterRefreshViewContainer.bringToFront();
        this.mFooterRefreshViewContainer.offsetTopAndBottom(-this.pushDistance);
        updatePushDistanceListener();
    }

    private void updatePushDistanceListener() {
        if (this.mOnPushLoadMoreListener != null) {
            this.mOnPushLoadMoreListener.onPushDistance(this.pushDistance);
        }
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

    public class OnPushLoadMoreListenerAdapter implements OnPushLoadMoreListener {
        public void onLoadMore() {
        }

        public void onPushDistance(int i) {
        }

        public void onPushEnable(boolean z) {
        }

        public OnPushLoadMoreListenerAdapter() {
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
                int access$2100 = (int) (RecyclerViewRefreshLayout.this.density * RecyclerViewRefreshLayout.DECELERATE_INTERPOLATION_FACTOR);
                float f = (float) access$2100;
                this.bgRect = new RectF(f, f, (float) (this.width - access$2100), (float) (this.height - access$2100));
            }
            return this.bgRect;
        }

        private RectF getOvalRect() {
            this.width = getWidth();
            this.height = getHeight();
            if (this.ovalRect == null) {
                int access$2100 = (int) (RecyclerViewRefreshLayout.this.density * 8.0f);
                float f = (float) access$2100;
                this.ovalRect = new RectF(f, f, (float) (this.width - access$2100), (float) (this.height - access$2100));
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
                this.progressPaint.setStrokeWidth((float) ((int) (RecyclerViewRefreshLayout.this.density * 3.0f)));
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
                this.bgPaint.setShadowLayer(4.0f, 0.0f, RecyclerViewRefreshLayout.DECELERATE_INTERPOLATION_FACTOR, this.shadowColor);
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
