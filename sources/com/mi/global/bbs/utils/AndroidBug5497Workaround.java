package com.mi.global.bbs.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

public class AndroidBug5497Workaround {
    private FrameLayout content;
    private FrameLayout.LayoutParams frameLayoutParams;
    private boolean isFullScreen = false;
    private View mChildOfContent;
    private KeyboardChangeListener mKeyboardChangeListener;
    private int usableHeightPrevious;

    public static AndroidBug5497Workaround assistActivity(Activity activity, boolean z) {
        return new AndroidBug5497Workaround(activity, z);
    }

    private AndroidBug5497Workaround(Activity activity, boolean z) {
        this.isFullScreen = z;
        this.content = (FrameLayout) activity.findViewById(16908290);
        this.mChildOfContent = this.content.getChildAt(0);
        this.mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                AndroidBug5497Workaround.this.possiblyResizeChildOfContent();
            }
        });
        this.frameLayoutParams = (FrameLayout.LayoutParams) this.mChildOfContent.getLayoutParams();
    }

    /* access modifiers changed from: private */
    public void possiblyResizeChildOfContent() {
        int computeUsableHeight = computeUsableHeight();
        if (computeUsableHeight != this.usableHeightPrevious && this.mChildOfContent.getParent() != null) {
            int height = ((ViewGroup) this.mChildOfContent.getParent()).getHeight();
            int i = height - computeUsableHeight;
            if (i > height / 4) {
                this.frameLayoutParams.height = height - i;
                if (this.mKeyboardChangeListener != null) {
                    this.mKeyboardChangeListener.onKeyboardChange(true);
                }
            } else {
                this.frameLayoutParams.height = height;
                if (this.mKeyboardChangeListener != null) {
                    this.mKeyboardChangeListener.onKeyboardChange(false);
                }
            }
            this.mChildOfContent.requestLayout();
            this.usableHeightPrevious = computeUsableHeight;
        }
    }

    private int computeUsableHeight() {
        Rect rect = new Rect();
        this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return this.isFullScreen ? rect.bottom : rect.bottom - rect.top;
    }

    public void setKeyboardChangeListener(KeyboardChangeListener keyboardChangeListener) {
        this.mKeyboardChangeListener = keyboardChangeListener;
    }
}
