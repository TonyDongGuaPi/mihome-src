package com.h6ah4i.android.widget.advrecyclerview.expandable;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;

public interface BaseExpandableSwipeableItemAdapter<GVH extends RecyclerView.ViewHolder, CVH extends RecyclerView.ViewHolder> {
    int a(GVH gvh, int i, int i2, int i3);

    int a(CVH cvh, int i, int i2, int i3, int i4);

    void a(GVH gvh, int i, int i2);

    void b(CVH cvh, int i, int i2, int i3);
}
