package com.xiaomi.miot.store.component.scrollView;

import android.content.Context;
import android.view.MotionEvent;
import com.facebook.react.views.scroll.ReactHorizontalScrollView;

public class MiotReactHorizontalScrollView extends ReactHorizontalScrollView {
    private static final boolean DEBUG = false;
    private static final String TAG = "MiotReactHorizontalScrollView";
    private float mLastX;
    private float mLastY;
    private float mOffsetX;
    private float mOffsetY;

    public MiotReactHorizontalScrollView(Context context) {
        super(context);
        setOverScrollMode(2);
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
            if (this.mOffsetX >= this.mOffsetY && (getScrollX() + getWidth() < computeHorizontalScrollRange() || x - this.mLastX >= 0.0f)) {
                z = false;
            }
            this.mLastX = x;
            this.mLastY = y;
            if (z) {
                return false;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
