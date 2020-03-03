package com.mi.global.shop.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    private int f7170a;
    private int b;
    private int c;

    public SpaceItemDecoration(int i, int i2, int i3) {
        this.f7170a = i;
        this.b = i2;
        this.c = i3;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition == 0) {
            rect.left = this.b;
        } else {
            rect.left = this.f7170a;
        }
        if (childAdapterPosition == recyclerView.getAdapter().getItemCount() - 1) {
            rect.right = this.c;
        }
    }
}
