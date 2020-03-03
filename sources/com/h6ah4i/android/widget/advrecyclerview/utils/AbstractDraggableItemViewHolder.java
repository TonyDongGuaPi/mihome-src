package com.h6ah4i.android.widget.advrecyclerview.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder;

public abstract class AbstractDraggableItemViewHolder extends RecyclerView.ViewHolder implements DraggableItemViewHolder {

    /* renamed from: a  reason: collision with root package name */
    private int f5733a;

    public AbstractDraggableItemViewHolder(View view) {
        super(view);
    }

    public void a(int i) {
        this.f5733a = i;
    }

    public int a() {
        return this.f5733a;
    }
}
