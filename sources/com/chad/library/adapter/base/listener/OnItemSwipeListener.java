package com.chad.library.adapter.base.listener;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;

public interface OnItemSwipeListener {
    void a(Canvas canvas, RecyclerView.ViewHolder viewHolder, float f, float f2, boolean z);

    void a(RecyclerView.ViewHolder viewHolder, int i);

    void b(RecyclerView.ViewHolder viewHolder, int i);

    void c(RecyclerView.ViewHolder viewHolder, int i);
}
