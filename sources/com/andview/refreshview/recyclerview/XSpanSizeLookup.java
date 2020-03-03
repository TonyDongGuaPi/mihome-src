package com.andview.refreshview.recyclerview;

import android.support.v7.widget.GridLayoutManager;

public class XSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    /* renamed from: a  reason: collision with root package name */
    private BaseRecyclerAdapter f4783a;
    private int b = 1;

    public XSpanSizeLookup(BaseRecyclerAdapter baseRecyclerAdapter, int i) {
        this.f4783a = baseRecyclerAdapter;
        this.b = i;
    }

    public int getSpanSize(int i) {
        if (this.f4783a.a(i) || this.f4783a.b(i)) {
            return this.b;
        }
        return 1;
    }
}
