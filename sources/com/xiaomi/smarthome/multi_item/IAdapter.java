package com.xiaomi.smarthome.multi_item;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import com.xiaomi.smarthome.newui.dragsort.SimpleItemDelegateTouchHelperCallback;

public abstract class IAdapter extends RecyclerView.Adapter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20162a = "IAdapter";
    /* access modifiers changed from: private */
    public DelegateAdapter b;
    private final RecyclerView.AdapterDataObserver c = new RecyclerView.AdapterDataObserver() {
        public void onChanged() {
            IAdapter.this.b.c(IAdapter.this);
        }

        public void onItemRangeChanged(int i, int i2) {
            IAdapter.this.b.a(IAdapter.this, i, i2);
        }

        public void onItemRangeChanged(int i, int i2, @Nullable Object obj) {
            IAdapter.this.b.a(IAdapter.this, i, i2, obj);
        }

        public void onItemRangeInserted(int i, int i2) {
            IAdapter.this.b.b(IAdapter.this, i, i2);
        }

        public void onItemRangeRemoved(int i, int i2) {
            IAdapter.this.b.c(IAdapter.this, i, i2);
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
            IAdapter.this.b.a(IAdapter.this, i, i2, i3);
        }
    };

    /* access modifiers changed from: protected */
    public abstract int a();

    /* access modifiers changed from: protected */
    public int a(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void a(Canvas canvas, RecyclerView recyclerView, View view, int i, RecyclerView.State state) {
    }

    /* access modifiers changed from: protected */
    public void a(Rect rect, View view, int i, RecyclerView recyclerView, RecyclerView.State state) {
    }

    /* access modifiers changed from: protected */
    public void a(RecyclerView.ViewHolder viewHolder, int i, int i2) {
    }

    /* access modifiers changed from: protected */
    public void a(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i) {
    }

    /* access modifiers changed from: protected */
    public void a(SimpleItemDelegateTouchHelperCallback simpleItemDelegateTouchHelperCallback, ItemTouchHelper itemTouchHelper) {
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, int i2) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void b(Canvas canvas, RecyclerView recyclerView, View view, int i, RecyclerView.State state) {
    }

    /* access modifiers changed from: protected */
    public void e() {
    }

    public int d() {
        return this.b.d(this);
    }

    /* access modifiers changed from: package-private */
    public final void a(DelegateAdapter delegateAdapter) {
        this.b = delegateAdapter;
    }

    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        registerAdapterDataObserver(this.c);
    }

    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        unregisterAdapterDataObserver(this.c);
    }

    /* access modifiers changed from: protected */
    public void a(RecyclerView.ViewHolder viewHolder, int i) {
        Log.e(f20162a, "onStartDrag: ");
    }
}
