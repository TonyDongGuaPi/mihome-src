package com.h6ah4i.android.widget.advrecyclerview.expandable;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import java.util.List;

public interface ExpandableItemAdapter<GVH extends RecyclerView.ViewHolder, CVH extends RecyclerView.ViewHolder> {
    int a();

    int a(int i);

    GVH a(ViewGroup viewGroup, int i);

    void a(CVH cvh, int i, int i2, int i3, List<Object> list);

    void a(GVH gvh, int i, int i2, List<Object> list);

    boolean a(int i, boolean z);

    boolean a(GVH gvh, int i, int i2, int i3, boolean z);

    long b(int i);

    CVH b(ViewGroup viewGroup, int i);

    void b(GVH gvh, int i, int i2);

    void b(CVH cvh, int i, int i2, int i3);

    boolean b(int i, boolean z);

    int c(int i);

    long c(int i, int i2);

    int d(int i, int i2);
}
