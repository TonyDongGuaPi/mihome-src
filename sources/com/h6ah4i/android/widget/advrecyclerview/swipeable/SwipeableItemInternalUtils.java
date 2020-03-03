package com.h6ah4i.android.widget.advrecyclerview.swipeable;

import android.support.v7.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;

public class SwipeableItemInternalUtils {
    private SwipeableItemInternalUtils() {
    }

    public static SwipeResultAction a(BaseSwipeableItemAdapter<?> baseSwipeableItemAdapter, RecyclerView.ViewHolder viewHolder, int i, int i2) {
        return ((SwipeableItemAdapter) baseSwipeableItemAdapter).b(viewHolder, i, i2);
    }
}
