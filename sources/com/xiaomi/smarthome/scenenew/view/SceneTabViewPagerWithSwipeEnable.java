package com.xiaomi.smarthome.scenenew.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class SceneTabViewPagerWithSwipeEnable extends ViewPager {

    /* renamed from: a  reason: collision with root package name */
    private boolean f22035a = true;

    public SceneTabViewPagerWithSwipeEnable(Context context) {
        super(context);
    }

    public SceneTabViewPagerWithSwipeEnable(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setSwipeEnabled(boolean z) {
        this.f22035a = z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f22035a) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.f22035a) {
            return false;
        }
        try {
            return super.onInterceptTouchEvent(motionEvent);
        } catch (Exception unused) {
            return true;
        }
    }
}
