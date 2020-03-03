package com.xiaomi.infrared.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    private int f10263a;

    public GridItemDecoration(int i) {
        this.f10263a = i;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.left = this.f10263a;
        rect.right = this.f10263a;
        rect.bottom = this.f10263a;
        rect.top = this.f10263a;
    }
}
