package com.xiaomi.smarthome.homeroom.view;

import android.view.View;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableSwipeableItemViewHolder;

public class BaseViewHolder extends AbstractDraggableSwipeableItemViewHolder implements ExpandableItemViewHolder {

    /* renamed from: a  reason: collision with root package name */
    private int f18356a;
    public View b;

    public BaseViewHolder(View view) {
        super(view);
        this.b = view;
    }

    public int K_() {
        return this.f18356a;
    }

    public void c_(int i) {
        this.f18356a = i;
    }

    public View k() {
        return this.b;
    }
}
