package com.mibi.common.data;

import android.widget.AbsListView;

public abstract class PageableListener implements AbsListView.OnScrollListener {

    /* renamed from: a  reason: collision with root package name */
    private boolean f7533a;

    public abstract void a();

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 0 && this.f7533a) {
            a();
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.f7533a = i3 == i + i2;
    }
}
