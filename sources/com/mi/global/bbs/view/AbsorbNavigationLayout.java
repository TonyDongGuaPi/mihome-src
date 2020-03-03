package com.mi.global.bbs.view;

import android.content.Context;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.OverScroller;

public class AbsorbNavigationLayout extends LinearLayout implements NestedScrollingParent {
    private int mMoveHeight;
    private View mNavigationView;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private int mRetainHeight;
    private OverScroller mScroller;
    private View mTopView;
    private int mTopViewHeight;
    private ViewPager mViewPager;
    private OnScrollProgressListener onScrollProgressListener;
    private boolean up;

    public interface OnScrollProgressListener {
        void onScrollProgress(@FloatRange(from = 0.0d, to = 1.0d) float f);
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        return (i & 2) != 0;
    }

    public AbsorbNavigationLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public AbsorbNavigationLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        this.mRetainHeight = 0;
        this.mMoveHeight = 0;
        this.up = false;
        setOrientation(1);
        this.mScroller = new OverScroller(context);
    }

    public AbsorbNavigationLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public AbsorbNavigationLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount == 3) {
            this.mTopView = getChildAt(0);
            this.mNavigationView = getChildAt(1);
            View childAt = getChildAt(2);
            if (childAt instanceof ViewPager) {
                this.mViewPager = (ViewPager) childAt;
                return;
            }
            throw new RuntimeException("the third child should be ViewPager!");
        }
        throw new RuntimeException(String.format("only support 3 three children,current children count is ", new Object[]{Integer.valueOf(childCount)}));
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(view, view2, i);
    }

    public void onStopNestedScroll(View view) {
        this.mNestedScrollingParentHelper.onStopNestedScroll(view);
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        dispatchScrollProgress();
        if (i2 > 0) {
            this.up = true;
            if (getScrollY() < this.mMoveHeight) {
                iArr[1] = i2;
                scrollBy(0, i2);
                return;
            }
            return;
        }
        this.up = false;
        if (getScrollY() <= this.mMoveHeight && getScrollY() > 0) {
            boolean canScrollVertically = ViewCompat.canScrollVertically(view, -1);
            if (view instanceof SwipeRefreshLayout) {
                SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
                if (swipeRefreshLayout.getChildCount() > 0) {
                    canScrollVertically = ViewCompat.canScrollVertically(swipeRefreshLayout.getChildAt(0), -1);
                }
            }
            if (!canScrollVertically) {
                iArr[1] = i2;
                scrollBy(0, i2);
            }
        }
    }

    public boolean getUpState() {
        return this.up;
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (z) {
            return false;
        }
        fling((int) f2);
        return true;
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        if (getScrollY() >= this.mMoveHeight) {
            return false;
        }
        fling((int) f2);
        return true;
    }

    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        getChildAt(0).measure(i, View.MeasureSpec.makeMeasureSpec(0, 0));
        this.mViewPager.getLayoutParams().height = getMeasuredHeight() - this.mNavigationView.getMeasuredHeight();
        setMeasuredDimension(getMeasuredWidth(), this.mTopView.getMeasuredHeight() + this.mNavigationView.getMeasuredHeight() + this.mViewPager.getMeasuredHeight());
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mTopViewHeight = this.mTopView.getMeasuredHeight();
        this.mMoveHeight = this.mTopViewHeight - this.mRetainHeight;
    }

    public void fling(int i) {
        this.mScroller.fling(0, getScrollY(), 0, i, 0, 0, 0, this.mMoveHeight);
        dispatchScrollProgress();
        invalidate();
    }

    public void scrollTo(int i, int i2) {
        int amendY = amendY(i2);
        dispatchScrollProgress();
        if (amendY != getScrollY()) {
            super.scrollTo(0, amendY);
        }
    }

    private int amendY(int i) {
        if (i < 0) {
            i = 0;
        }
        return i > this.mMoveHeight ? this.mMoveHeight : i;
    }

    private void dispatchScrollProgress() {
        if (this.onScrollProgressListener != null) {
            this.onScrollProgressListener.onScrollProgress(((float) getScrollY()) / ((float) this.mMoveHeight));
        }
    }

    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            scrollTo(0, this.mScroller.getCurrY());
            invalidate();
        }
    }

    public int getRetainHeight() {
        return this.mRetainHeight;
    }

    public void setRetainHeight(int i) {
        this.mRetainHeight = i;
        this.mMoveHeight = this.mTopViewHeight - i;
    }

    public int getMoveHeight() {
        return this.mMoveHeight;
    }

    public void setOnScrollProgressListener(OnScrollProgressListener onScrollProgressListener2) {
        this.onScrollProgressListener = onScrollProgressListener2;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        if (this.mScroller != null && !this.mScroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
        super.onDetachedFromWindow();
    }
}
