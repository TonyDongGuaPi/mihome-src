package com.h6ah4i.android.widget.advrecyclerview.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemViewHolder;

public abstract class AbstractExpandableItemViewHolder extends RecyclerView.ViewHolder implements ExpandableItemViewHolder {

    /* renamed from: a  reason: collision with root package name */
    private int f5735a;

    public AbstractExpandableItemViewHolder(View view) {
        super(view);
    }

    public void c_(int i) {
        this.f5735a = i;
    }

    public int K_() {
        return this.f5735a;
    }
}
