package com.mi.global.bbs.view.Editor;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ClearEditText extends FontEditText implements TextWatcher, View.OnFocusChangeListener {
    private boolean hasFocus;
    private Drawable mClearDrawable;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public ClearEditText(Context context) {
        this(context, (AttributeSet) null);
    }

    public ClearEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842862);
    }

    public ClearEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mClearDrawable = getCompoundDrawablesRelative()[2];
        if (this.mClearDrawable == null) {
            this.mClearDrawable = new ColorDrawable(0);
        }
        this.mClearDrawable.setBounds(0, 0, this.mClearDrawable.getIntrinsicWidth(), this.mClearDrawable.getIntrinsicHeight());
        setClearIconVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        if (motionEvent.getAction() == 1 && getCompoundDrawablesRelative()[2] != null) {
            if (motionEvent.getX() <= ((float) (getWidth() - getTotalPaddingEnd())) || motionEvent.getX() >= ((float) (getWidth() - getPaddingEnd()))) {
                z = false;
            }
            if (z) {
                setText("");
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void onFocusChange(View view, boolean z) {
        this.hasFocus = z;
        boolean z2 = false;
        if (z) {
            if (getText().length() > 0) {
                z2 = true;
            }
            setClearIconVisible(z2);
            return;
        }
        setClearIconVisible(false);
    }

    /* access modifiers changed from: protected */
    public void setClearIconVisible(boolean z) {
        setCompoundDrawablesRelative(getCompoundDrawablesRelative()[0], getCompoundDrawablesRelative()[1], z ? this.mClearDrawable : null, getCompoundDrawablesRelative()[3]);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.hasFocus) {
            setClearIconVisible(charSequence.length() > 0);
        }
    }
}
