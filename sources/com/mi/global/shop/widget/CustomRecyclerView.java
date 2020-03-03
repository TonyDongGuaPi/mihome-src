package com.mi.global.shop.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class CustomRecyclerView extends RecyclerView {

    /* renamed from: a  reason: collision with root package name */
    private int f7156a;
    private int b;
    private ViewGroup c;

    public void setParent(ViewGroup viewGroup) {
        this.c = viewGroup;
    }

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f7156a = (int) (motionEvent.getX() + 0.5f);
            this.b = (int) (motionEvent.getY() + 0.5f);
            this.c.requestDisallowInterceptTouchEvent(true);
        } else if (action == 2) {
            int x = (int) (motionEvent.getX() + 0.5f);
            int y = (int) (motionEvent.getY() + 0.5f);
            int i = x - this.f7156a;
            this.f7156a = x;
            this.b = y;
            if (Math.abs(y - this.b) > Math.abs(i)) {
                this.c.requestDisallowInterceptTouchEvent(false);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }
}
