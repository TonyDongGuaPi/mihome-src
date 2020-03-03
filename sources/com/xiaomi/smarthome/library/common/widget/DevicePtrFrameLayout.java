package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class DevicePtrFrameLayout extends PtrFrameLayout {
    boolean touchEnabled = true;

    public DevicePtrFrameLayout(Context context) {
        super(context);
    }

    public DevicePtrFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DevicePtrFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void enableTouch() {
        this.touchEnabled = true;
    }

    public void disableTouch() {
        this.touchEnabled = false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.touchEnabled) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.touchEnabled) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
