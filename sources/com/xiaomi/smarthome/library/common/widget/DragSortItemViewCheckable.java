package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.view.View;
import android.widget.Checkable;

public class DragSortItemViewCheckable extends DragSortItemView implements Checkable {
    public DragSortItemViewCheckable(Context context) {
        super(context);
    }

    public boolean isChecked() {
        View childAt = getChildAt(0);
        if (!(childAt instanceof Checkable) || !((Checkable) childAt).isChecked()) {
            return false;
        }
        return true;
    }

    public void setChecked(boolean z) {
        View childAt = getChildAt(0);
        if (childAt instanceof Checkable) {
            ((Checkable) childAt).setChecked(z);
        }
    }

    public void toggle() {
        View childAt = getChildAt(0);
        if (childAt instanceof Checkable) {
            ((Checkable) childAt).toggle();
        }
    }
}
