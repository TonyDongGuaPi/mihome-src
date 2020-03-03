package com.mi.global.bbs.view.swipe;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

public class MySwipeRefreshLayout extends SwipeRefreshLayout {
    private static final String TAG = "MySwipeRefreshLayout";
    private boolean mDragger;
    private float mStartX;
    private float mStartY;
    private int mTouchSlop;
    /* access modifiers changed from: private */
    public ViewGroup viewGroup;

    public MySwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.mStartY = motionEvent.getY();
                this.mStartX = motionEvent.getX();
                this.mDragger = false;
                break;
            case 1:
            case 3:
                this.mDragger = false;
                break;
            case 2:
                if (this.mDragger) {
                    return false;
                }
                float y = motionEvent.getY();
                float abs = Math.abs(motionEvent.getX() - this.mStartX);
                float abs2 = Math.abs(y - this.mStartY);
                if (abs > ((float) this.mTouchSlop) && abs > abs2) {
                    this.mDragger = true;
                    return false;
                }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public ViewGroup getViewGroup() {
        return this.viewGroup;
    }

    public void setViewGroup(ViewGroup viewGroup2) {
        this.viewGroup = viewGroup2;
        this.viewGroup.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MySwipeRefreshLayout.this.viewGroup.getScrollY() <= 1) {
                    MySwipeRefreshLayout.this.setEnabled(true);
                } else {
                    MySwipeRefreshLayout.this.setEnabled(false);
                }
                return false;
            }
        });
    }
}
