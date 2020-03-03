package com.mi.global.bbs.view;

import android.content.Context;
import android.util.AttributeSet;
import com.mi.global.bbs.view.Editor.FontTextView;

public class CheckedTextView extends FontTextView {
    private int[] addAttr;
    private boolean checked;
    private OnCheckedChangeListener onCheckedChangeListener;
    private boolean toggleAble;

    public interface OnCheckedChangeListener {
        void onChecked(CheckedTextView checkedTextView, boolean z);
    }

    public CheckedTextView(Context context) {
        super(context);
        this.addAttr = new int[]{16842912};
        this.toggleAble = false;
    }

    public CheckedTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.addAttr = new int[]{16842912};
        this.toggleAble = false;
        setChecked(false);
        setClickable(true);
    }

    public CheckedTextView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public boolean isToggleAble() {
        return this.toggleAble;
    }

    public void setToggleAble(boolean z) {
        this.toggleAble = z;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean z) {
        if (this.checked != z) {
            this.checked = z;
            refreshDrawableState();
        }
    }

    public void toggle() {
        setChecked(!this.checked);
        if (this.onCheckedChangeListener != null) {
            this.onCheckedChangeListener.onChecked(this, isChecked());
        }
    }

    public boolean performClick() {
        if (this.toggleAble) {
            toggle();
        } else if (!this.checked) {
            setChecked(!this.checked);
            if (this.onCheckedChangeListener != null) {
                this.onCheckedChangeListener.onChecked(this, isChecked());
            }
        }
        return super.performClick();
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, this.addAttr);
        }
        return onCreateDrawableState;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener2) {
        this.onCheckedChangeListener = onCheckedChangeListener2;
    }
}
