package com.xiaomi.mishopsdk.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RatingBar;

public class BaseRatingBar extends RatingBar {
    public BaseRatingBar(Context context) {
        super(context);
    }

    public BaseRatingBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public BaseRatingBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getX() < 0.0f) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }
}
