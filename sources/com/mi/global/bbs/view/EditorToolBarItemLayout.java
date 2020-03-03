package com.mi.global.bbs.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class EditorToolBarItemLayout extends RelativeLayout {
    private boolean itemFocus;
    private boolean selected;
    private int[] selectedAttr;

    public EditorToolBarItemLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public boolean isItemFocus() {
        return this.itemFocus;
    }

    public void setItemFocus(boolean z) {
        this.itemFocus = z;
    }

    public EditorToolBarItemLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.selectedAttr = new int[]{16842913};
        if (Build.VERSION.SDK_INT > 16) {
            setBackground(getMyStateListDrawable());
        } else {
            setBackgroundDrawable(getMyStateListDrawable());
        }
    }

    private StateListDrawable getMyStateListDrawable() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, new ColorDrawable(Color.parseColor("#dddddd")));
        stateListDrawable.addState(this.selectedAttr, new ColorDrawable(Color.parseColor("#dddddd")));
        stateListDrawable.addState(new int[0], new ColorDrawable(Color.parseColor("#f1f1f1")));
        return stateListDrawable;
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        if (this.selected) {
            return mergeDrawableStates(super.onCreateDrawableState(i + 1), this.selectedAttr);
        }
        return super.onCreateDrawableState(i);
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        if (this.selected != z) {
            this.selected = z;
            refreshDrawableState();
        }
    }
}
