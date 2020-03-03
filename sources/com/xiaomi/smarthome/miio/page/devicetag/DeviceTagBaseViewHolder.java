package com.xiaomi.smarthome.miio.page.devicetag;

import android.view.View;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableSwipeableItemViewHolder;

public class DeviceTagBaseViewHolder extends AbstractDraggableSwipeableItemViewHolder implements ExpandableItemViewHolder {

    /* renamed from: a  reason: collision with root package name */
    private int f19800a;
    public View b;

    public DeviceTagBaseViewHolder(View view) {
        super(view);
        this.b = view;
    }

    public int K_() {
        return this.f19800a;
    }

    public void c_(int i) {
        this.f19800a = i;
    }

    public View k() {
        return this.b;
    }
}
