package com.xiaomi.dragdrop;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class AbstractDraggableItemViewHolder extends RecyclerView.ViewHolder implements DraggableItemViewHolder {

    /* renamed from: a  reason: collision with root package name */
    private int f10090a;

    public AbstractDraggableItemViewHolder(View view) {
        super(view);
    }

    public void a(int i) {
        this.f10090a = i;
    }

    public int a() {
        return this.f10090a;
    }
}
