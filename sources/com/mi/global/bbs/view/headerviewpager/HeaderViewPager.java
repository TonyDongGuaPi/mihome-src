package com.mi.global.bbs.view.headerviewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.headerviewpager.HeaderScrollHelper;

public class HeaderViewPager extends LinearLayout {
    private static final int DIRECTION_DOWN = 2;
    private static final int DIRECTION_UP = 1;
    private boolean isClickHead;
    private int mCurY;
    private int mDirection;
    private boolean mDisallowIntercept;
    private float mDownX;
    private float mDownY;
    private int mHeadHeight;
    private View mHeadView;
    private int mLastScrollerY;
    private float mLastX;
    private int mLastXIntercept;
    private float mLastY;
    private int mLastYIntercept;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private HeaderScrollHelper mScrollable;
    private Scroller mScroller;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private int maxY;
    private int minY;
    private OnScrollListener onScrollListener;
    private int sysVersion;
    private int topOffset;
    private boolean verticalScrollFlag;

    public interface OnScrollListener {
        void onScroll(int i, int i2);
    }

    private int calcDuration(int i, int i2) {
        return i - i2;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener2) {
        this.onScrollListener = onScrollListener2;
    }

    public HeaderViewPager(Context context) {
        this(context, (AttributeSet) null);
    }

    public HeaderViewPager(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HeaderViewPager(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.topOffset = 0;
        this.maxY = 0;
        this.minY = 0;
        this.verticalScrollFlag = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.HeaderViewPager);
        this.topOffset = obtainStyledAttributes.getDimensionPixelSize(obtainStyledAttributes.getIndex(R.styleable.HeaderViewPager_hvp_topOffset), this.topOffset);
        obtainStyledAttributes.recycle();
        this.mScroller = new Scroller(context);
        this.mScrollable = new HeaderScrollHelper();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.sysVersion = Build.VERSION.SDK_INT;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        if (this.mHeadView != null && !this.mHeadView.isClickable()) {
            this.mHeadView.setClickable(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        this.mHeadView = getChildAt(0);
        measureChildWithMargins(this.mHeadView, i, 0, 0, 0);
        this.mHeadHeight = this.mHeadView.getMeasuredHeight();
        this.maxY = this.mHeadHeight - this.topOffset;
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2) + this.maxY, 1073741824));
    }

    public void requestHeaderViewPagerDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        this.mDisallowIntercept = z;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        float abs = Math.abs(x - this.mDownX);
        float abs2 = Math.abs(y - this.mDownY);
        obtainVelocityTracker(motionEvent);
        switch (motionEvent.getAction()) {
            case 0:
                this.mDisallowIntercept = false;
                this.verticalScrollFlag = false;
                this.mDownX = x;
                this.mDownY = y;
                this.mLastY = y;
                this.mLastX = x;
                checkIsClickHead((int) y, this.mHeadHeight, getScrollY());
                this.mScroller.abortAnimation();
                break;
            case 1:
                if (this.verticalScrollFlag) {
                    this.mVelocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
                    float yVelocity = this.mVelocityTracker.getYVelocity();
                    this.mDirection = yVelocity > 0.0f ? 2 : 1;
                    this.mScroller.fling(0, getScrollY(), 0, -((int) yVelocity), 0, 0, -2147483647, Integer.MAX_VALUE);
                    this.mLastScrollerY = getScrollY();
                    invalidate();
                    if ((abs > ((float) this.mTouchSlop) || abs2 > ((float) this.mTouchSlop)) && (this.isClickHead || !isStickied())) {
                        int action = motionEvent.getAction();
                        motionEvent2.setAction(3);
                        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
                        motionEvent2.setAction(action);
                        return dispatchTouchEvent;
                    }
                }
                recycleVelocityTracker();
                break;
            case 2:
                if (!this.mDisallowIntercept) {
                    float f = this.mLastY - y;
                    this.mLastY = y;
                    if (abs > ((float) this.mTouchSlop) && abs > abs2) {
                        this.verticalScrollFlag = false;
                    } else if (abs2 > ((float) this.mTouchSlop) && abs2 > abs) {
                        this.verticalScrollFlag = true;
                    }
                    if (this.verticalScrollFlag && (!isStickied() || this.mScrollable.isTop() || this.isClickHead)) {
                        double d = (double) f;
                        Double.isNaN(d);
                        scrollBy(0, (int) (d + 0.5d));
                        invalidate();
                        break;
                    }
                }
                break;
            case 3:
                recycleVelocityTracker();
                break;
        }
        super.dispatchTouchEvent(motionEvent);
        return true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            float r0 = r5.getX()
            int r0 = (int) r0
            float r1 = r5.getY()
            int r1 = (int) r1
            int r5 = r5.getAction()
            r2 = 0
            switch(r5) {
                case 0: goto L_0x0012;
                case 1: goto L_0x0012;
                case 2: goto L_0x0014;
                default: goto L_0x0012;
            }
        L_0x0012:
            r5 = 0
            goto L_0x0027
        L_0x0014:
            int r5 = r4.mLastXIntercept
            int r5 = r0 - r5
            int r3 = r4.mLastYIntercept
            int r3 = r1 - r3
            int r3 = java.lang.Math.abs(r3)
            int r5 = java.lang.Math.abs(r5)
            if (r3 <= r5) goto L_0x0012
            r5 = 1
        L_0x0027:
            r4.mLastXIntercept = r0
            r4.mLastYIntercept = r1
            boolean r0 = r4.isStickied()
            if (r0 == 0) goto L_0x0032
            r5 = 0
        L_0x0032:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.bbs.view.headerviewpager.HeaderViewPager.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    private void checkIsClickHead(int i, int i2, int i3) {
        this.isClickHead = i + i3 <= i2;
    }

    private void obtainVelocityTracker(MotionEvent motionEvent) {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
    }

    private void recycleVelocityTracker() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            int currY = this.mScroller.getCurrY();
            if (this.mDirection != 1) {
                if (this.mScrollable.isTop() || this.isClickHead) {
                    scrollTo(0, getScrollY() + (currY - this.mLastScrollerY));
                    if (this.mCurY <= this.minY) {
                        this.mScroller.abortAnimation();
                        return;
                    }
                }
                invalidate();
            } else if (isStickied()) {
                int finalY = this.mScroller.getFinalY() - currY;
                int calcDuration = calcDuration(this.mScroller.getDuration(), this.mScroller.timePassed());
                this.mScrollable.smoothScrollBy(getScrollerVelocity(finalY, calcDuration), finalY, calcDuration);
                this.mScroller.abortAnimation();
                return;
            } else {
                scrollTo(0, currY);
                invalidate();
            }
            this.mLastScrollerY = currY;
        }
    }

    @SuppressLint({"NewApi"})
    private int getScrollerVelocity(int i, int i2) {
        if (this.mScroller == null) {
            return 0;
        }
        if (this.sysVersion >= 14) {
            return (int) this.mScroller.getCurrVelocity();
        }
        return i / i2;
    }

    public void scrollBy(int i, int i2) {
        int scrollY = getScrollY();
        int i3 = i2 + scrollY;
        if (i3 >= this.maxY) {
            i3 = this.maxY;
        } else if (i3 <= this.minY) {
            i3 = this.minY;
        }
        super.scrollBy(i, i3 - scrollY);
    }

    public void scrollTo(int i, int i2) {
        if (i2 >= this.maxY) {
            i2 = this.maxY;
        } else if (i2 <= this.minY) {
            i2 = this.minY;
        }
        this.mCurY = i2;
        if (this.onScrollListener != null) {
            this.onScrollListener.onScroll(i2, this.maxY);
        }
        super.scrollTo(i, i2);
    }

    public boolean isStickied() {
        return this.mCurY == this.maxY;
    }

    public int getMaxY() {
        return this.maxY;
    }

    public boolean isHeadTop() {
        return this.mCurY == this.minY;
    }

    public boolean canPtr() {
        return this.verticalScrollFlag && this.mCurY == this.minY && this.mScrollable.isTop();
    }

    public void setTopOffset(int i) {
        this.topOffset = i;
    }

    public void setCurrentScrollableContainer(HeaderScrollHelper.ScrollableContainer scrollableContainer) {
        this.mScrollable.setCurrentScrollableContainer(scrollableContainer);
    }
}
