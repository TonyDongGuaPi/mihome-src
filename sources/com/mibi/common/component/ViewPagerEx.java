package com.mibi.common.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import miuipub.view.ViewPager;

public class ViewPagerEx extends ViewPager {

    /* renamed from: a  reason: collision with root package name */
    private float f7497a;
    private float b;

    public ViewPagerEx(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ViewPagerEx(Context context) {
        super(context);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        ViewParent parent = getParent();
        if (parent == null) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (getAdapter() != null) {
            boolean z = true;
            if (getAdapter().a() > 1) {
                switch (motionEvent.getAction()) {
                    case 0:
                        this.f7497a = motionEvent.getX();
                        this.b = motionEvent.getY();
                        break;
                    case 1:
                    case 3:
                        parent.requestDisallowInterceptTouchEvent(false);
                        break;
                    case 2:
                        break;
                }
                if (((int) Math.abs(motionEvent.getX() - this.f7497a)) <= ((int) Math.abs(motionEvent.getY() - this.b))) {
                    z = false;
                }
                parent.requestDisallowInterceptTouchEvent(z);
                return super.onInterceptTouchEvent(motionEvent);
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
