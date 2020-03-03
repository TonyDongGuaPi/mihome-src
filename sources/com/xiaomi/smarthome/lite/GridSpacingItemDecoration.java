package com.xiaomi.smarthome.lite;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    private int f19350a;
    private int b;
    private boolean c;

    public GridSpacingItemDecoration(int i, int i2, boolean z) {
        this.f19350a = i;
        this.b = i2;
        this.c = z;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i = childAdapterPosition % this.f19350a;
        if (childAdapterPosition == 0) {
            rect.left = 0;
            rect.right = 0;
            rect.top = 0;
            rect.bottom = 0;
        } else if (this.c) {
            rect.left = this.b - ((this.b * i) / this.f19350a);
            rect.right = ((i + 1) * this.b) / this.f19350a;
            if (childAdapterPosition < this.f19350a) {
                rect.top = this.b;
            }
            rect.bottom = this.b;
        } else {
            rect.left = (this.b * i) / this.f19350a;
            rect.right = this.b - (((i + 1) * this.b) / this.f19350a);
            if (childAdapterPosition >= this.f19350a) {
                rect.top = this.b;
            }
        }
    }
}
