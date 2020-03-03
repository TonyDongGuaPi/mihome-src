package com.xiaomi.smarthome.lite;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class LiteDeviceGroupFrameLayoutView extends FrameLayout {
    public LiteDeviceGroupFrameLayoutView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (isInChild(motionEvent)) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (isInChild(motionEvent)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isInChild(MotionEvent motionEvent) {
        int childCount = getChildCount();
        Rect rect = new Rect();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).getHitRect(rect);
            if (rect.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return true;
            }
        }
        return false;
    }
}
