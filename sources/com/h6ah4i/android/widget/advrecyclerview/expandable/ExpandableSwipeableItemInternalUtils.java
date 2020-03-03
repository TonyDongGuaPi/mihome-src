package com.h6ah4i.android.widget.advrecyclerview.expandable;

import android.support.v7.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;

class ExpandableSwipeableItemInternalUtils {
    private ExpandableSwipeableItemInternalUtils() {
    }

    public static SwipeResultAction a(BaseExpandableSwipeableItemAdapter<?, ?> baseExpandableSwipeableItemAdapter, RecyclerView.ViewHolder viewHolder, int i, int i2, int i3) {
        if (i2 == -1) {
            return ((ExpandableSwipeableItemAdapter) baseExpandableSwipeableItemAdapter).b(viewHolder, i, i3);
        }
        return ((ExpandableSwipeableItemAdapter) baseExpandableSwipeableItemAdapter).c(viewHolder, i, i2, i3);
    }
}
