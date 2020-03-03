package com.xiaomi.smarthome.newui.widget.topnavi.layoutmanager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    private int f20944a;
    private int b;
    private int c;
    private int d;

    public SpaceItemDecoration(int i, int i2, int i3, int i4) {
        this.f20944a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public SpaceItemDecoration(int i) {
        this(i, i, i, i);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        FlowLayoutManager flowLayoutManager = (FlowLayoutManager) recyclerView.getLayoutManager();
        recyclerView.getChildAdapterPosition(view);
        rect.set(this.f20944a, this.b, this.c, this.d);
    }
}
