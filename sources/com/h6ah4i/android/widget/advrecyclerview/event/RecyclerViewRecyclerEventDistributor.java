package com.h6ah4i.android.widget.advrecyclerview.event;

import android.support.v7.widget.RecyclerView;
import java.lang.ref.WeakReference;

public class RecyclerViewRecyclerEventDistributor extends BaseRecyclerViewEventDistributor<RecyclerView.RecyclerListener> {
    private InternalRecyclerListener e = new InternalRecyclerListener(this);

    /* access modifiers changed from: protected */
    public void b(RecyclerView recyclerView) {
        super.b(recyclerView);
        recyclerView.setRecyclerListener(this.e);
    }

    /* access modifiers changed from: protected */
    public void f() {
        super.f();
        if (this.e != null) {
            this.e.a();
            this.e = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(RecyclerView.ViewHolder viewHolder) {
        if (this.c != null) {
            for (RecyclerView.RecyclerListener onViewRecycled : this.c) {
                onViewRecycled.onViewRecycled(viewHolder);
            }
        }
    }

    private static class InternalRecyclerListener implements RecyclerView.RecyclerListener {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<RecyclerViewRecyclerEventDistributor> f5712a;

        public InternalRecyclerListener(RecyclerViewRecyclerEventDistributor recyclerViewRecyclerEventDistributor) {
            this.f5712a = new WeakReference<>(recyclerViewRecyclerEventDistributor);
        }

        public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
            RecyclerViewRecyclerEventDistributor recyclerViewRecyclerEventDistributor = (RecyclerViewRecyclerEventDistributor) this.f5712a.get();
            if (recyclerViewRecyclerEventDistributor != null) {
                recyclerViewRecyclerEventDistributor.a(viewHolder);
            }
        }

        public void a() {
            this.f5712a.clear();
        }
    }
}
