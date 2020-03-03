package com.xiaomi.mishopsdk.widget.pulltorefresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.TextView;
import com.xiaomi.mishopsdk.R;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.util.DensityUtil;
import com.xiaomi.mishopsdk.util.PreferenceUtil;

public class SimplePullToRefreshLayout extends ViewGroup implements IPullToRefresh<ViewGroup> {
    private static final boolean DEBUG = false;
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    private static final int DEFAULT_MISHOP_TRANSPARENT_VIEW_HEIGHT = 0;
    private static final State DEFAULT_STATE = State.RESET;
    private static final float FRICTION = 2.2222223f;
    private static final String TAG = "SimplePullToRefreshLayout";
    private final Animation mAnimateToHeaderPosition = new Animation() {
        public void applyTransformation(float f, Transformation transformation) {
            int paddingTop = SimplePullToRefreshLayout.this.getPaddingTop();
            if (SimplePullToRefreshLayout.this.mFrom != SimplePullToRefreshLayout.this.mOriginalOffsetTop || SimplePullToRefreshLayout.this.getState() == State.MANUAL_REFRESHING) {
                paddingTop = SimplePullToRefreshLayout.this.mFrom + ((int) (((float) ((SimplePullToRefreshLayout.this.mOriginalOffsetTop - SimplePullToRefreshLayout.this.mFrom) + SimplePullToRefreshLayout.this.mLoadingHeaderHeight)) * f));
            }
            int top = paddingTop - SimplePullToRefreshLayout.this.mTargetView.getTop();
            int top2 = SimplePullToRefreshLayout.this.mTargetView.getTop();
            if (top + top2 < 0) {
                top = 0 - top2;
            }
            SimplePullToRefreshLayout.this.setTargetOffsetTopAndBottom(top);
        }
    };
    private final Animation mAnimateToStartPosition = new Animation() {
        public void applyTransformation(float f, Transformation transformation) {
            int paddingTop = SimplePullToRefreshLayout.this.getPaddingTop();
            if (SimplePullToRefreshLayout.this.mFrom != SimplePullToRefreshLayout.this.mOriginalOffsetTop) {
                paddingTop = SimplePullToRefreshLayout.this.mFrom + ((int) (((float) (SimplePullToRefreshLayout.this.mOriginalOffsetTop - SimplePullToRefreshLayout.this.mFrom)) * f));
            }
            int top = paddingTop - SimplePullToRefreshLayout.this.mTargetView.getTop();
            int top2 = SimplePullToRefreshLayout.this.mTargetView.getTop();
            if (top + top2 < 0) {
                top = 0 - top2;
            }
            SimplePullToRefreshLayout.this.setTargetOffsetTopAndBottom(top);
        }
    };
    private long mAnimationDuration;
    /* access modifiers changed from: private */
    public int mCurrentTargetOffsetTop;
    /* access modifiers changed from: private */
    public int mCurrentTipOffsetTop;
    private DecelerateInterpolator mDecelerateInterpolator;
    /* access modifiers changed from: private */
    public int mFrom;
    private LoadingLayout mHeaderLoadingLayout;
    private float mInitialMotionY;
    /* access modifiers changed from: private */
    public boolean mIsDoingAnimate;
    private float mLastMotionY;
    /* access modifiers changed from: private */
    public int mLoadingHeaderHeight;
    private OnStateChangedListner mOnStateChangedListener;
    /* access modifiers changed from: private */
    public int mOriginalOffsetTop;
    private boolean mPullToRefreshEnable;
    /* access modifiers changed from: private */
    public OnRefreshListener mRefreshListener;
    /* access modifiers changed from: private */
    public final Animation.AnimationListener mReturnToHeaderPositionListener = new BaseAnimationListener() {
        public void onAnimationEnd(Animation animation) {
            int unused = SimplePullToRefreshLayout.this.mCurrentTargetOffsetTop = SimplePullToRefreshLayout.this.mLoadingHeaderHeight;
            if (SimplePullToRefreshLayout.this.mRefreshListener != null) {
                SimplePullToRefreshLayout.this.mRefreshListener.onRefresh();
                SimplePullToRefreshLayout.this.mTipView.setTextColor(SimplePullToRefreshLayout.this.mTipView.getTextColors().withAlpha(0));
            }
        }
    };
    private final Runnable mReturnToHeaderPositionTask = new Runnable() {
        public void run() {
            boolean unused = SimplePullToRefreshLayout.this.mIsDoingAnimate = true;
            SimplePullToRefreshLayout.this.animateOffsetToHeaderPosition(SimplePullToRefreshLayout.this.mCurrentTargetOffsetTop + SimplePullToRefreshLayout.this.getPaddingTop(), SimplePullToRefreshLayout.this.mReturnToHeaderPositionListener);
        }
    };
    /* access modifiers changed from: private */
    public final Animation.AnimationListener mReturnToStartPositionListener = new BaseAnimationListener() {
        public void onAnimationEnd(Animation animation) {
            int unused = SimplePullToRefreshLayout.this.mCurrentTipOffsetTop = 0;
            int unused2 = SimplePullToRefreshLayout.this.mCurrentTargetOffsetTop = 0;
        }
    };
    private final Runnable mReturnToStartPositionTask = new Runnable() {
        public void run() {
            boolean unused = SimplePullToRefreshLayout.this.mIsDoingAnimate = true;
            SimplePullToRefreshLayout.this.animateOffsetToStartPosition(SimplePullToRefreshLayout.this.mCurrentTargetOffsetTop + SimplePullToRefreshLayout.this.getPaddingTop(), SimplePullToRefreshLayout.this.mReturnToStartPositionListener);
        }
    };
    private State mState = DEFAULT_STATE;
    /* access modifiers changed from: private */
    public View mTargetView;
    private int mTipHeaderHeight;
    private boolean mTipOffsetEnable = true;
    /* access modifiers changed from: private */
    public TextView mTipView;
    private float mTouchSlop;
    private int mTransparentViewHeight;

    public interface OnRefreshListener {
        void onRefresh();
    }

    public interface OnStateChangedListner {
        void onStateChaned(State state);
    }

    public SimplePullToRefreshLayout(Context context) {
        super(context);
        init(context, (AttributeSet) null, 0);
    }

    public SimplePullToRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public SimplePullToRefreshLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        float f = context.getResources().getDisplayMetrics().density;
        this.mTouchSlop = (float) ViewConfiguration.get(context).getScaledTouchSlop();
        this.mHeaderLoadingLayout = createLoadingLayout(context, (TypedArray) null);
        this.mTipView = new TextView(context);
        String stringPref = PreferenceUtil.getStringPref(ShopApp.instance, "app_motto", "");
        if (!TextUtils.isEmpty(stringPref)) {
            this.mTipView.setText(stringPref);
        } else {
            this.mTipView.setText("和米粉 交朋友");
        }
        this.mTipView.setTextColor(context.getResources().getColor(R.color.mishopsdk_t_grey_desc));
        this.mTipView.setGravity(17);
        this.mDecelerateInterpolator = new DecelerateInterpolator(DECELERATE_INTERPOLATION_FACTOR);
        this.mAnimationDuration = 600;
        this.mTransparentViewHeight = (int) (f * 0.0f);
        this.mPullToRefreshEnable = true;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        ensureTarget();
        removeAllViews();
        addView(this.mTipView, new ViewGroup.LayoutParams(-1, -2));
        addView(this.mHeaderLoadingLayout);
        addView(this.mTargetView);
        this.mTipView.setTextColor(this.mTipView.getTextColors().withAlpha(0));
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getChildCount() > 3 && !isInEditMode()) {
            throw new IllegalStateException("SimplePullToRefreshLayout can host only one direct child");
        } else if (getChildCount() > 0) {
            getChildAt(0).measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), Integer.MIN_VALUE));
            getChildAt(1).measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), Integer.MIN_VALUE));
            getChildAt(2).measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() != 0) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = this.mCurrentTargetOffsetTop + getPaddingTop();
            int paddingLeft2 = (measuredWidth - getPaddingLeft()) - getPaddingRight();
            int paddingTop2 = (measuredHeight - getPaddingTop()) - getPaddingBottom();
            int measuredHeight2 = this.mHeaderLoadingLayout.getMeasuredHeight();
            int measuredHeight3 = this.mTipView.getMeasuredHeight();
            this.mLoadingHeaderHeight = measuredHeight2;
            this.mTipHeaderHeight = measuredHeight3;
            if (getState() == State.RESET) {
                this.mHeaderLoadingLayout.layout(paddingLeft, (-measuredHeight2) + paddingTop, paddingLeft2, paddingTop);
                this.mTipView.layout(paddingLeft, measuredHeight2, paddingLeft2, measuredHeight3 + measuredHeight2);
            } else {
                this.mTipView.layout(paddingLeft, measuredHeight2, paddingLeft2, measuredHeight3 + measuredHeight2);
                this.mHeaderLoadingLayout.layout(paddingLeft, (-measuredHeight2) + paddingTop + this.mTransparentViewHeight, paddingLeft2, this.mTransparentViewHeight + paddingTop);
            }
            View childAt = getChildAt(2);
            if (childAt != null) {
                childAt.layout(paddingLeft, paddingTop, paddingLeft2 + paddingLeft, paddingTop2 + paddingTop);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isPullToRefreshEnabled()) {
            return false;
        }
        if (isRefreshing()) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                float y = motionEvent.getY();
                this.mInitialMotionY = y;
                this.mLastMotionY = y;
                return false;
            case 1:
            case 3:
                if (this.mState == State.RELEASE_TO_REFRESH && this.mRefreshListener != null) {
                    setState(State.REFRESHING);
                    return true;
                } else if (isRefreshing()) {
                    return true;
                } else {
                    setState(State.RESET);
                    return false;
                }
            case 2:
                if (this.mIsDoingAnimate) {
                    return false;
                }
                float y2 = motionEvent.getY() - this.mInitialMotionY;
                if (y2 <= ((float) DensityUtil.dip2px(165.0f))) {
                    this.mTipView.setTextColor(this.mTipView.getTextColors().withAlpha(0));
                } else if ((y2 - ((float) DensityUtil.dip2px(165.0f))) / 255.0f < 1.0f) {
                    this.mTipView.setTextColor(this.mTipView.getTextColors().withAlpha(((int) (y2 - ((float) DensityUtil.dip2px(165.0f)))) % 255));
                }
                if (y2 <= this.mTouchSlop) {
                    return false;
                }
                updateContentOffsetTop(Math.round(y2 / FRICTION));
                this.mLastMotionY = motionEvent.getY();
                pullEvent();
                return true;
            default:
                return false;
        }
    }

    private void pullEvent() {
        int round = Math.round(Math.min(this.mInitialMotionY - this.mLastMotionY, 0.0f) / FRICTION);
        int measuredHeight = this.mHeaderLoadingLayout.getMeasuredHeight();
        if (this.mState != State.PULL_TO_REFRESH && measuredHeight >= Math.abs(round)) {
            setState(State.PULL_TO_REFRESH);
        } else if (this.mState == State.PULL_TO_REFRESH && measuredHeight < Math.abs(round)) {
            setState(State.RELEASE_TO_REFRESH);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        boolean z = false;
        if (this.mIsDoingAnimate && action == 0) {
            float y = motionEvent.getY();
            this.mInitialMotionY = y;
            this.mLastMotionY = y;
            this.mIsDoingAnimate = false;
        }
        if (isEnabled() && !this.mIsDoingAnimate && !canChildScrollUp()) {
            z = onTouchEvent(motionEvent);
        }
        return !z ? super.onInterceptTouchEvent(motionEvent) : z;
    }

    public boolean canChildScrollUp() {
        if (Build.VERSION.SDK_INT >= 14) {
            return ViewCompat.canScrollVertically(this.mTargetView, -1);
        }
        if (this.mTargetView instanceof AbsListView) {
            AbsListView absListView = (AbsListView) this.mTargetView;
            if (absListView.getChildCount() <= 0 || (absListView.getFirstVisiblePosition() <= 0 && absListView.getChildAt(0).getTop() >= absListView.getPaddingTop())) {
                return false;
            }
            return true;
        } else if (this.mTargetView.getScrollY() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void ensureTarget() {
        if (this.mTargetView != null) {
            return;
        }
        if (getChildCount() <= 1 || isInEditMode()) {
            this.mTargetView = getChildAt(0);
            this.mOriginalOffsetTop = this.mTargetView.getTop() + getPaddingTop();
            return;
        }
        throw new IllegalStateException("SimplePullToRefreshLayout can host only one direct child");
    }

    public enum State {
        RESET(0),
        PULL_TO_REFRESH(1),
        RELEASE_TO_REFRESH(2),
        REFRESHING(8),
        MANUAL_REFRESHING(9);
        
        private int mIntValue;

        static State mapIntToValue(int i) {
            for (State state : values()) {
                if (i == state.getIntValue()) {
                    return state;
                }
            }
            return RESET;
        }

        private State(int i) {
            this.mIntValue = i;
        }

        /* access modifiers changed from: package-private */
        public int getIntValue() {
            return this.mIntValue;
        }
    }

    private LoadingLayout createLoadingLayout(Context context, TypedArray typedArray) {
        return new FlipLoadingLayout(context, typedArray);
    }

    private void updateContentOffsetTop(int i) {
        int top = this.mTargetView.getTop();
        if (i < 0) {
            i = 0;
        }
        setTargetOffsetTopAndBottom((i - top) + getPaddingTop());
    }

    /* access modifiers changed from: private */
    public void setTargetOffsetTopAndBottom(int i) {
        this.mTargetView.offsetTopAndBottom(i);
        this.mCurrentTargetOffsetTop = this.mTargetView.getTop() - getPaddingTop();
        this.mHeaderLoadingLayout.offsetTopAndBottom(i);
        if (Build.VERSION.SDK_INT < 11) {
            invalidate();
        }
    }

    /* access modifiers changed from: package-private */
    public final void setState(State state) {
        this.mState = state;
        switch (this.mState) {
            case RESET:
                this.mHeaderLoadingLayout.reset();
                post(this.mReturnToStartPositionTask);
                break;
            case PULL_TO_REFRESH:
                this.mHeaderLoadingLayout.pullToRefresh();
                break;
            case RELEASE_TO_REFRESH:
                this.mHeaderLoadingLayout.releaseToRefresh();
                break;
            case REFRESHING:
            case MANUAL_REFRESHING:
                this.mHeaderLoadingLayout.refreshing();
                post(this.mReturnToHeaderPositionTask);
                break;
        }
        if (this.mOnStateChangedListener != null) {
            this.mOnStateChangedListener.onStateChaned(state);
        }
    }

    public State getState() {
        return this.mState;
    }

    public boolean isPullToRefreshEnabled() {
        return this.mPullToRefreshEnable;
    }

    public boolean isRefreshing() {
        return this.mState == State.REFRESHING || this.mState == State.MANUAL_REFRESHING;
    }

    public void onRefreshComplete() {
        if (isRefreshing()) {
            setState(State.RESET);
        }
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.mRefreshListener = onRefreshListener;
    }

    public void setRefreshing() {
        if (this.mRefreshListener == null) {
            throw new IllegalStateException("mRefreshListener can not be null");
        } else if (!isRefreshing()) {
            setState(State.MANUAL_REFRESHING);
        }
    }

    public void setPullToRefreshEnable(boolean z) {
        this.mPullToRefreshEnable = z;
    }

    public LoadingLayout getHeaderLoadingLayout() {
        return this.mHeaderLoadingLayout;
    }

    /* access modifiers changed from: private */
    public void animateOffsetToStartPosition(int i, Animation.AnimationListener animationListener) {
        this.mFrom = i;
        this.mAnimateToStartPosition.reset();
        this.mAnimateToStartPosition.setDuration(this.mAnimationDuration);
        this.mAnimateToStartPosition.setAnimationListener(animationListener);
        this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
        this.mTargetView.startAnimation(this.mAnimateToStartPosition);
    }

    /* access modifiers changed from: private */
    public void animateOffsetToHeaderPosition(int i, Animation.AnimationListener animationListener) {
        this.mFrom = i;
        this.mAnimateToHeaderPosition.reset();
        this.mAnimateToHeaderPosition.setDuration(this.mAnimationDuration);
        this.mAnimateToHeaderPosition.setAnimationListener(animationListener);
        this.mAnimateToHeaderPosition.setInterpolator(this.mDecelerateInterpolator);
        this.mTargetView.startAnimation(this.mAnimateToHeaderPosition);
    }

    private class BaseAnimationListener implements Animation.AnimationListener {
        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }

        private BaseAnimationListener() {
        }
    }

    public void setTransparentViewHeight(int i) {
        this.mTransparentViewHeight = (int) (((float) i) * getContext().getResources().getDisplayMetrics().density);
    }

    public void setOnStateChangedListener(OnStateChangedListner onStateChangedListner) {
        this.mOnStateChangedListener = onStateChangedListner;
    }
}
