package com.xiaomi.smarthome.lite;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class HomeSceneGridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    private int f19351a;
    private int b;
    private int c;
    private boolean d;

    public HomeSceneGridSpacingItemDecoration(int i, int i2, int i3, boolean z) {
        this.f19351a = i;
        this.b = i2;
        this.c = i3;
        this.d = z;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i = childAdapterPosition % this.f19351a;
        if (this.d) {
            rect.left = this.b;
            rect.right = this.b;
            if (childAdapterPosition < this.f19351a) {
                rect.top = this.c;
            }
            rect.bottom = this.c;
            return;
        }
        rect.left = (this.b * i) / this.f19351a;
        rect.right = this.b - (((i + 1) * this.b) / this.f19351a);
        if (childAdapterPosition >= this.f19351a) {
            rect.top = this.c;
        }
    }
}
