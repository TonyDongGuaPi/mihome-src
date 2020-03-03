package com.h6ah4i.android.widget.advrecyclerview.swipeable;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;

public interface BaseSwipeableItemAdapter<T extends RecyclerView.ViewHolder> {
    void a(T t, int i, int i2);

    int b(T t, int i, int i2, int i3);
}
