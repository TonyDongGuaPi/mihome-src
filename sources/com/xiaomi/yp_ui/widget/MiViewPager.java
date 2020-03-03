package com.xiaomi.yp_ui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MiViewPager extends ViewPager {
    public MiViewPager(Context context) {
        super(context);
    }

    public MiViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onInterceptTouchEvent(motionEvent);
        } catch (IllegalArgumentException unused) {
            return false;
        } catch (ArrayIndexOutOfBoundsException unused2) {
            return false;
        }
    }
}
