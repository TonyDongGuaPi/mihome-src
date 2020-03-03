package com.chad.library.adapter.base.listener;

import android.support.v7.widget.RecyclerView;

public interface OnItemDragListener {
    void a(RecyclerView.ViewHolder viewHolder, int i);

    void a(RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder2, int i2);

    void b(RecyclerView.ViewHolder viewHolder, int i);
}
