package com.h6ah4i.android.widget.advrecyclerview.draggable;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;

public interface DraggableItemAdapter<T extends RecyclerView.ViewHolder> {
    ItemDraggableRange a(T t, int i);

    boolean a(T t, int i, int i2, int i3);

    boolean b(int i, int i2);

    void b_(int i, int i2);
}
