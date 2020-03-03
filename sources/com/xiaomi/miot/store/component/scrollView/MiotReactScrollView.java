package com.xiaomi.miot.store.component.scrollView;

import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import android.widget.ScrollView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.views.scroll.ReactScrollView;
import com.xiaomi.youpin.log.LogUtils;
import java.lang.reflect.Field;

public class MiotReactScrollView extends ReactScrollView {
    private static final boolean DEBUG = false;
    private static final String TAG = "MiotReactScrollView";
    private float mLastX;
    private float mLastY;
    private int mMaxVelocity;
    private boolean mMoveByTouch;
    private float mOffsetX;
    private float mOffsetY;
    private OverScroller mScroller;
    private Field mScrollerField;
    private boolean mScrollingDown;
    private boolean mTouching;
    private boolean mVelocityRestrictionEnable;

    public MiotReactScrollView(ReactContext reactContext) {
        super(reactContext);
        setOverScrollMode(2);
        try {
            this.mScrollerField = ScrollView.class.getDeclaredField("mScroller");
            this.mScrollerField.setAccessible(true);
            this.mScroller = (OverScroller) this.mScrollerField.get(this);
        } catch (Exception e) {
            LogUtils.e(TAG, "cannot to get the mScroller field from ScrollView! ", e);
        }
        this.mVelocityRestrictionEnable = true;
        this.mMaxVelocity = ViewConfiguration.get(reactContext).getScaledMaximumFlingVelocity() / 2;
        setSaveEnabled(false);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mOffsetX = 0.0f;
            this.mOffsetY = 0.0f;
            this.mLastX = motionEvent.getX();
            this.mLastY = motionEvent.getY();
        } else if (action == 2) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.mOffsetX += Math.abs(x - this.mLastX);
            this.mOffsetY += Math.abs(y - this.mLastY);
            boolean z = true;
            if (this.mOffsetX <= this.mOffsetY && (getScrollY() + getHeight() < computeVerticalScrollRange() || motionEvent.getY() - this.mLastY >= 0.0f)) {
                z = false;
            }
            this.mLastX = x;
            this.mLastY = y;
            if (z) {
                this.mTouching = false;
                return false;
            }
        }
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        this.mTouching = onInterceptTouchEvent;
        return onInterceptTouchEvent;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1 || action == 3) {
            this.mTouching = false;
        }
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        if (this.mTouching) {
            this.mScrollingDown = i2 - i4 > 0;
            this.mMoveByTouch = true;
        }
        if (this.mMoveByTouch && this.mScroller != null && !this.mTouching && !this.mScroller.isFinished() && ((this.mScrollingDown && i2 < i4) || (!this.mScrollingDown && i2 > i4))) {
            this.mScroller.abortAnimation();
        }
        super.onScrollChanged(i, i2, i3, i4);
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.mScroller != null && this.mScroller.isFinished() && !this.mTouching) {
            this.mMoveByTouch = false;
        }
    }

    public void fling(int i) {
        if (this.mVelocityRestrictionEnable && i > this.mMaxVelocity) {
            i = this.mMaxVelocity;
        }
        super.fling(i);
    }

    public void setMaxVelocity(int i) {
        this.mMaxVelocity = i;
    }

    public void setVelocityRestrictionEnabled(boolean z) {
        this.mVelocityRestrictionEnable = z;
    }
}
