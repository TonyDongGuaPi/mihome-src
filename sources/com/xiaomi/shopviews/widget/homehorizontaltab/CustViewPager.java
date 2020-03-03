package com.xiaomi.shopviews.widget.homehorizontaltab;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.xiaomi.base.utils.Coder;

public class CustViewPager extends ViewPager {

    /* renamed from: a  reason: collision with root package name */
    private boolean f13258a = false;
    private float b;
    private float c;
    private float d;
    private float e;

    public CustViewPager(Context context) {
        super(context);
    }

    public CustViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.b = motionEvent.getRawX();
                this.d = motionEvent.getRawY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case 1:
            case 3:
                getParent().requestDisallowInterceptTouchEvent(false);
                this.f13258a = false;
                break;
            case 2:
                this.c = motionEvent.getRawX();
                this.e = motionEvent.getRawY();
                if (!this.f13258a) {
                    float abs = Math.abs(this.e - this.d);
                    float abs2 = Math.abs(this.c - this.b);
                    if (abs2 >= abs && abs2 >= ((float) Coder.a(getContext(), 3.0f))) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        this.f13258a = true;
                        break;
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(false);
                        return false;
                    }
                } else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                }
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
