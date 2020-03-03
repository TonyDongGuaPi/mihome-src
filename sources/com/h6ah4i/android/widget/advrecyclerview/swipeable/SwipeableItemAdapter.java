package com.h6ah4i.android.widget.advrecyclerview.swipeable;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;

public interface SwipeableItemAdapter<T extends RecyclerView.ViewHolder> extends BaseSwipeableItemAdapter<T> {
    SwipeResultAction b(T t, int i, int i2);
}
