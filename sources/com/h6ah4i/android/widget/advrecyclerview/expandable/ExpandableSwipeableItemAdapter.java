package com.h6ah4i.android.widget.advrecyclerview.expandable;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;

public interface ExpandableSwipeableItemAdapter<GVH extends RecyclerView.ViewHolder, CVH extends RecyclerView.ViewHolder> extends BaseExpandableSwipeableItemAdapter<GVH, CVH> {
    SwipeResultAction b(GVH gvh, int i, int i2);

    SwipeResultAction c(CVH cvh, int i, int i2, int i3);
}
