package com.mi.multi_image_selector.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class CheckableTextView extends TextView {
    private int[] checkedAttr;
    private boolean mChecked;
    private OnCheckedChangedListener onCheckedChangedListener;

    public interface OnCheckedChangedListener {
        void a(CheckableTextView checkableTextView, boolean z);
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    public CheckableTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CheckableTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mChecked = false;
        this.checkedAttr = new int[]{16842912};
        setClickable(true);
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        return this.mChecked ? mergeDrawableStates(onCreateDrawableState, this.checkedAttr) : onCreateDrawableState;
    }

    public boolean performClick() {
        toggle();
        if (this.onCheckedChangedListener != null) {
            this.onCheckedChangedListener.a(this, this.mChecked);
        }
        return super.performClick();
    }

    private void toggle() {
        setChecked(!this.mChecked);
    }

    public void setOnCheckedChangedListener(OnCheckedChangedListener onCheckedChangedListener2) {
        this.onCheckedChangedListener = onCheckedChangedListener2;
    }

    public void setChecked(boolean z) {
        this.mChecked = z;
        if (z) {
            this.mChecked = z;
            refreshDrawableState();
        }
    }
}
