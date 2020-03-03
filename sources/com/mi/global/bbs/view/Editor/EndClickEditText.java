package com.mi.global.bbs.view.Editor;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class EndClickEditText extends FontEditText {
    private Drawable mClearDrawable;
    OnEndClickListener onEndClickListener;

    public interface OnEndClickListener {
        void onEndClick(View view);
    }

    public EndClickEditText(Context context) {
        this(context, (AttributeSet) null);
    }

    public EndClickEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.editTextStyle);
    }

    public EndClickEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mClearDrawable = getCompoundDrawablesRelative()[2];
        if (this.mClearDrawable == null) {
            this.mClearDrawable = new ColorDrawable(0);
        }
        this.mClearDrawable.setBounds(0, 0, this.mClearDrawable.getIntrinsicWidth(), this.mClearDrawable.getIntrinsicHeight());
        setClearIconVisible(true);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 && getCompoundDrawablesRelative()[2] != null) {
            if (motionEvent.getX() > ((float) (getWidth() - getTotalPaddingEnd())) && motionEvent.getX() < ((float) (getWidth() - getPaddingEnd()))) {
                setText("");
                if (this.onEndClickListener != null) {
                    this.onEndClickListener.onEndClick(this);
                }
                return true;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void setClearIconVisible(boolean z) {
        setCompoundDrawablesRelative(getCompoundDrawablesRelative()[0], getCompoundDrawablesRelative()[1], z ? this.mClearDrawable : null, getCompoundDrawablesRelative()[3]);
    }

    public void setOnEndClickListener(OnEndClickListener onEndClickListener2) {
        this.onEndClickListener = onEndClickListener2;
    }
}
