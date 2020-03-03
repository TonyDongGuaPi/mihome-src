package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class DragSlideView extends RelativeLayout {
    DragSlideHorizontalView mDragSlideHorizontalView;

    public DragSlideView(Context context) {
        super(context);
    }

    public DragSlideView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DragSlideView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setDragSlideHorizontalView(DragSlideHorizontalView dragSlideHorizontalView) {
        this.mDragSlideHorizontalView = dragSlideHorizontalView;
    }

    public DragSlideHorizontalView getView() {
        return this.mDragSlideHorizontalView;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setAboveView(View view) {
        if (this.mDragSlideHorizontalView != null) {
            this.mDragSlideHorizontalView.setAboveView(view);
        }
    }

    public void setBehindView(View view) {
        if (this.mDragSlideHorizontalView != null) {
            this.mDragSlideHorizontalView.setBehindView(view);
        }
    }

    public void disableBehindView(boolean z) {
        if (this.mDragSlideHorizontalView != null) {
            this.mDragSlideHorizontalView.disableBehindView(z);
        }
    }

    public boolean isShow() {
        return this.mDragSlideHorizontalView != null && this.mDragSlideHorizontalView.isShow();
    }

    public void scrollToAbove() {
        if (this.mDragSlideHorizontalView != null) {
            this.mDragSlideHorizontalView.scrollToAbove();
        }
    }

    public void reset() {
        if (this.mDragSlideHorizontalView != null) {
            this.mDragSlideHorizontalView.reset();
        }
    }

    public void scrollToBehind() {
        if (this.mDragSlideHorizontalView != null) {
            this.mDragSlideHorizontalView.scrollToBehind();
        }
    }

    public void scroll() {
        if (this.mDragSlideHorizontalView != null) {
            this.mDragSlideHorizontalView.scroll();
        }
    }
}
