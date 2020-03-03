package com.mi.global.bbs.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ImageCheckView extends ImageView {
    private int[] additionalState;
    private boolean mChecked;
    private OnCheckedChangedListener onCheckedChangedListener;

    public interface OnCheckedChangedListener {
        void onCheckedChanged(ImageCheckView imageCheckView, boolean z);
    }

    public ImageCheckView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ImageCheckView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mChecked = false;
        this.additionalState = new int[]{16842912};
        setClickable(true);
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    public void setChecked(boolean z) {
        if (this.mChecked != z) {
            this.mChecked = z;
            refreshDrawableState();
        }
    }

    public void toggle() {
        setChecked(!this.mChecked);
    }

    public boolean performClick() {
        toggle();
        boolean performClick = super.performClick();
        if (!performClick) {
            playSoundEffect(0);
        }
        if (this.onCheckedChangedListener != null) {
            this.onCheckedChangedListener.onCheckedChanged(this, this.mChecked);
        }
        return performClick;
    }

    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.mChecked) {
            mergeDrawableStates(onCreateDrawableState, this.additionalState);
        }
        return onCreateDrawableState;
    }

    public void setOnCheckedChangedListener(OnCheckedChangedListener onCheckedChangedListener2) {
        this.onCheckedChangedListener = onCheckedChangedListener2;
    }
}
