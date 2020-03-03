package com.xiaomi.shopviews.widget.hdhorizontalrecyclergoods;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.xiaomi.base.utils.Coder;

public class CustRecylerView extends RecyclerView {

    /* renamed from: a  reason: collision with root package name */
    private boolean f13232a = false;
    private float b;
    private float c;
    private float d;
    private float e;

    public CustRecylerView(Context context) {
        super(context);
    }

    public CustRecylerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustRecylerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
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
                this.f13232a = false;
                break;
            case 2:
                this.c = motionEvent.getRawX();
                this.e = motionEvent.getRawY();
                if (!this.f13232a) {
                    float abs = Math.abs(this.e - this.d);
                    float abs2 = Math.abs(this.c - this.b);
                    if (abs2 >= abs && abs2 >= ((float) Coder.a(getContext(), 3.0f))) {
                        this.f13232a = true;
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
