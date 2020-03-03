package com.xiaomi.shopviews.widget.recycleview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    private int f13342a;
    private int b;

    public GridSpacingItemDecoration(int i, int i2) {
        this.f13342a = i;
        this.b = i2;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view) % this.f13342a;
        rect.left = (this.b * childAdapterPosition) / this.f13342a;
        rect.right = this.b - (((childAdapterPosition + 1) * this.b) / this.f13342a);
    }
}
