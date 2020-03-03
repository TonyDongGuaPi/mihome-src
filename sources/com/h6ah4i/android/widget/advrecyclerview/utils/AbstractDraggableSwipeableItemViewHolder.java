package com.h6ah4i.android.widget.advrecyclerview.utils;

import android.view.View;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder;

public abstract class AbstractDraggableSwipeableItemViewHolder extends AbstractSwipeableItemViewHolder implements DraggableItemViewHolder {

    /* renamed from: a  reason: collision with root package name */
    private int f5734a;

    public AbstractDraggableSwipeableItemViewHolder(View view) {
        super(view);
    }

    public void a(int i) {
        this.f5734a = i;
    }

    public int a() {
        return this.f5734a;
    }
}
