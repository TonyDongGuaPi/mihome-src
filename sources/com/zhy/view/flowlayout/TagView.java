package com.zhy.view.flowlayout;

import android.content.Context;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;

public class TagView extends FrameLayout implements Checkable {
    private static final int[] b = {16842912};

    /* renamed from: a  reason: collision with root package name */
    private boolean f2572a;

    public TagView(Context context) {
        super(context);
    }

    public View getTagView() {
        return getChildAt(0);
    }

    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, b);
        }
        return onCreateDrawableState;
    }

    public void setChecked(boolean z) {
        if (this.f2572a != z) {
            this.f2572a = z;
            refreshDrawableState();
        }
    }

    public boolean isChecked() {
        return this.f2572a;
    }

    public void toggle() {
        setChecked(!this.f2572a);
    }
}
