package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ViewPagerSystem extends ViewPager {

    /* renamed from: a  reason: collision with root package name */
    private float f18976a;
    private float b;
    private boolean c = true;

    public ViewPagerSystem(Context context) {
        super(context);
    }

    public ViewPagerSystem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.c) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setPagingEnabled(boolean z) {
        this.c = z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.c) {
            return false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
