package com.wx.wheelview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class NestedScrollView extends ScrollView {
    public NestedScrollView(Context context) {
        super(context);
        a();
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                WheelView wheelView = (WheelView) NestedScrollView.this.findViewWithTag("com.wx.wheelview");
                if (wheelView != null) {
                    wheelView.getParent().requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }
        });
    }
}
