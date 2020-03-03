package com.xiaomi.miot.store.component.swiperefresh;

import android.view.MotionEvent;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.views.swiperefresh.ReactSwipeRefreshLayout;

public class MiotReactSwipeRefreshLayout extends ReactSwipeRefreshLayout {
    private static final boolean DEBUG = false;
    private static final String TAG = "MiotReactSwipeRefreshLayout";
    private float mLastX;
    private float mLastY;
    private float mOffsetX;
    private float mOffsetY;

    public MiotReactSwipeRefreshLayout(ReactContext reactContext) {
        super(reactContext);
        setOverScrollMode(2);
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
            this.mLastX = x;
            this.mLastY = y;
            if (this.mOffsetX > this.mOffsetY) {
                return false;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
