package com.xiaomi.dragdrop;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;

public class DraggingItemInfo {

    /* renamed from: a  reason: collision with root package name */
    public final int f10101a;
    public final int b;
    public final long c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;
    public final Rect h = new Rect();
    public final int i;

    public DraggingItemInfo(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i2, int i3) {
        this.f10101a = viewHolder.itemView.getWidth();
        this.b = viewHolder.itemView.getHeight();
        this.c = viewHolder.getItemId();
        this.d = viewHolder.itemView.getLeft();
        this.e = viewHolder.itemView.getTop();
        this.f = i2 - this.d;
        this.g = i3 - this.e;
        CustomRecyclerViewUtils.a(viewHolder.itemView, this.h);
        this.i = CustomRecyclerViewUtils.e(viewHolder);
    }
}
