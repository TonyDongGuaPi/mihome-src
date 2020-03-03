package com.mi.global.bbs.view;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class FloatViewBall extends RelativeLayout {
    private ViewDragHelper mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
        public boolean tryCaptureView(View view, int i) {
            return true;
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            int paddingLeft = FloatViewBall.this.getPaddingLeft();
            return Math.min(Math.max(i, paddingLeft), (FloatViewBall.this.getWidth() - view.getWidth()) - paddingLeft);
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            int paddingTop = FloatViewBall.this.getPaddingTop();
            return Math.min(Math.max(i, paddingTop), FloatViewBall.this.getHeight() - view.getHeight());
        }

        public int getViewHorizontalDragRange(View view) {
            return FloatViewBall.this.getMeasuredWidth() - view.getMeasuredWidth();
        }

        public int getViewVerticalDragRange(View view) {
            return FloatViewBall.this.getMeasuredHeight() - view.getMeasuredHeight();
        }

        public void onViewReleased(View view, float f, float f2) {
            FloatViewBall.this.moveToSide(view);
            FloatViewBall.this.invalidate();
        }
    });

    public FloatViewBall(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mDragger.shouldInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mDragger.processTouchEvent(motionEvent);
        return false;
    }

    public void computeScroll() {
        if (this.mDragger.continueSettling(true)) {
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setClickable(true);
        }
    }

    /* access modifiers changed from: private */
    public void moveToSide(View view) {
        float top = (float) view.getTop();
        float measuredHeight = (float) (getMeasuredHeight() - view.getBottom());
        float measuredWidth = (float) (getMeasuredWidth() - view.getRight());
        float left = (float) view.getLeft();
        int i = 0;
        if ((top < measuredHeight ? top : measuredHeight) / ((float) getMeasuredHeight()) < (measuredWidth < left ? measuredWidth : left) / ((float) getMeasuredWidth())) {
            ViewDragHelper viewDragHelper = this.mDragger;
            int left2 = view.getLeft();
            if (top >= measuredHeight) {
                i = getMeasuredHeight() - view.getMeasuredHeight();
            }
            viewDragHelper.settleCapturedViewAt(left2, i);
            return;
        }
        ViewDragHelper viewDragHelper2 = this.mDragger;
        if (left >= measuredWidth) {
            i = getMeasuredWidth() - view.getMeasuredWidth();
        }
        viewDragHelper2.settleCapturedViewAt(i, view.getTop());
    }
}
