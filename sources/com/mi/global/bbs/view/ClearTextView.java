package com.mi.global.bbs.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.mi.global.bbs.view.Editor.FontTextView;

public class ClearTextView extends FontTextView {
    onRightClickListener onRightClickListener;

    public interface onRightClickListener {
        void onRightClick(ClearTextView clearTextView);
    }

    public ClearTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ClearTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public ClearTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 && getCompoundDrawables()[2] != null) {
            if ((motionEvent.getX() > ((float) (getWidth() - getTotalPaddingRight())) && motionEvent.getX() < ((float) (getWidth() - getPaddingRight()))) && this.onRightClickListener != null) {
                this.onRightClickListener.onRightClick(this);
                return true;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setOnRightClickListener(onRightClickListener onrightclicklistener) {
        this.onRightClickListener = onrightclicklistener;
    }
}
