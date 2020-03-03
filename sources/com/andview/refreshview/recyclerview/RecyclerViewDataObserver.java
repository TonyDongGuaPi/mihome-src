package com.andview.refreshview.recyclerview;

import android.support.v7.widget.RecyclerView;
import com.andview.refreshview.XRefreshView;

public class RecyclerViewDataObserver extends RecyclerView.AdapterDataObserver {

    /* renamed from: a  reason: collision with root package name */
    private BaseRecyclerAdapter f4782a;
    private XRefreshView b;
    private boolean c;
    private boolean d = true;

    public void a(BaseRecyclerAdapter baseRecyclerAdapter, XRefreshView xRefreshView) {
        this.f4782a = baseRecyclerAdapter;
        this.b = xRefreshView;
    }

    private void a(boolean z) {
        if (this.b != null) {
            this.b.enableEmptyView(z);
        }
    }

    public void onChanged() {
        if (this.f4782a != null) {
            if (this.f4782a.d()) {
                if (this.d) {
                    a(true);
                    this.d = false;
                }
            } else if (!this.d) {
                a(false);
                this.d = true;
            }
        }
    }

    public void onItemRangeChanged(int i, int i2, Object obj) {
        onChanged();
    }

    public void onItemRangeChanged(int i, int i2) {
        onChanged();
    }

    public void onItemRangeInserted(int i, int i2) {
        onChanged();
    }

    public void onItemRangeRemoved(int i, int i2) {
        onChanged();
    }

    public void onItemRangeMoved(int i, int i2, int i3) {
        onChanged();
    }

    public void a() {
        this.c = true;
    }

    public boolean b() {
        return this.c;
    }
}
