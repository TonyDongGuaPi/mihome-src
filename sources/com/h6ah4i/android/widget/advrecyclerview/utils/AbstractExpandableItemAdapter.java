package com.h6ah4i.android.widget.advrecyclerview.utils;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter;
import java.util.List;

public abstract class AbstractExpandableItemAdapter<GVH extends RecyclerView.ViewHolder, CVH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ExpandableItemAdapter<GVH, CVH> {
    public boolean a(int i, boolean z) {
        return true;
    }

    public boolean b(int i, boolean z) {
        return true;
    }

    public int c(int i) {
        return 0;
    }

    public int d(int i, int i2) {
        return 0;
    }

    public final int getItemCount() {
        return 0;
    }

    public final long getItemId(int i) {
        return -1;
    }

    public final int getItemViewType(int i) {
        return 0;
    }

    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
    }

    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    public void a(GVH gvh, int i, int i2, List<Object> list) {
        b(gvh, i, i2);
    }

    public void a(CVH cvh, int i, int i2, int i3, List<Object> list) {
        b(cvh, i, i2, i3);
    }
}
