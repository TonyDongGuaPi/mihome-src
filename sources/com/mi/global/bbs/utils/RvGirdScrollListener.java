package com.mi.global.bbs.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class RvGirdScrollListener extends RecyclerView.OnScrollListener {
    public abstract void onLoadMore();

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        View childAt = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount() - 1);
        if (childAt != null) {
            int bottom = childAt.getBottom();
            int bottom2 = recyclerView.getBottom() - recyclerView.getPaddingBottom();
            int position = recyclerView.getLayoutManager().getPosition(childAt);
            if (bottom == bottom2 && position == recyclerView.getLayoutManager().getItemCount() - 1) {
                onLoadMore();
            }
        }
    }
}
