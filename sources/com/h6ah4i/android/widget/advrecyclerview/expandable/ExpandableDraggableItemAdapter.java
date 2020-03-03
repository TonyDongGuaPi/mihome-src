package com.h6ah4i.android.widget.advrecyclerview.expandable;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;

public interface ExpandableDraggableItemAdapter<GVH extends RecyclerView.ViewHolder, CVH extends RecyclerView.ViewHolder> {
    ItemDraggableRange a(GVH gvh, int i);

    ItemDraggableRange a(CVH cvh, int i, int i2);

    void a(int i, int i2);

    void a(int i, int i2, int i3, int i4);

    boolean a(GVH gvh, int i, int i2, int i3);

    boolean a(CVH cvh, int i, int i2, int i3, int i4);

    boolean b(int i, int i2);

    boolean b(int i, int i2, int i3, int i4);
}
