package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ViewPagerWithSwipeEnable extends ViewPager {

    /* renamed from: a  reason: collision with root package name */
    private boolean f18977a = true;

    public ViewPagerWithSwipeEnable(Context context) {
        super(context);
    }

    public ViewPagerWithSwipeEnable(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setSwipeEnabled(boolean z) {
        this.f18977a = z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f18977a) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f18977a) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }
}
