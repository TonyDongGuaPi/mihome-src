package com.h6ah4i.android.widget.advrecyclerview.utils;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.taobao.weex.el.parse.Operators;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

public class BaseWrapperAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    /* renamed from: a  reason: collision with root package name */
    protected static final List<Object> f5737a = Collections.emptyList();
    private static final String b = "ARVBaseWrapperAdapter";
    private static final boolean c = false;
    private RecyclerView.Adapter<VH> d;
    private BridgeObserver e = new BridgeObserver(this);

    /* access modifiers changed from: protected */
    public void a() {
    }

    public BaseWrapperAdapter(RecyclerView.Adapter<VH> adapter) {
        this.d = adapter;
        this.d.registerAdapterDataObserver(this.e);
        super.setHasStableIds(this.d.hasStableIds());
    }

    public boolean k() {
        return this.d != null;
    }

    public void l() {
        a();
        if (!(this.d == null || this.e == null)) {
            this.d.unregisterAdapterDataObserver(this.e);
        }
        this.d = null;
        this.e = null;
    }

    public RecyclerView.Adapter<VH> m() {
        return this.d;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        if (k()) {
            this.d.onAttachedToRecyclerView(recyclerView);
        }
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        if (k()) {
            this.d.onDetachedFromRecyclerView(recyclerView);
        }
    }

    public void onViewAttachedToWindow(VH vh) {
        if (k()) {
            this.d.onViewAttachedToWindow(vh);
        }
    }

    public void onViewDetachedFromWindow(VH vh) {
        if (k()) {
            this.d.onViewDetachedFromWindow(vh);
        }
    }

    public void onViewRecycled(VH vh) {
        if (k()) {
            this.d.onViewRecycled(vh);
        }
    }

    public void setHasStableIds(boolean z) {
        super.setHasStableIds(z);
        if (k()) {
            this.d.setHasStableIds(z);
        }
    }

    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.d.onCreateViewHolder(viewGroup, i);
    }

    public void onBindViewHolder(VH vh, int i) {
        onBindViewHolder(vh, i, f5737a);
    }

    public void onBindViewHolder(VH vh, int i, List<Object> list) {
        if (k()) {
            this.d.onBindViewHolder(vh, i, list);
        }
    }

    public int getItemCount() {
        if (k()) {
            return this.d.getItemCount();
        }
        return 0;
    }

    public long getItemId(int i) {
        return this.d.getItemId(i);
    }

    public int getItemViewType(int i) {
        return this.d.getItemViewType(i);
    }

    /* access modifiers changed from: protected */
    public void b() {
        notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2) {
        notifyItemRangeChanged(i, i2);
    }

    /* access modifiers changed from: protected */
    public void b(int i, int i2, Object obj) {
        notifyItemRangeChanged(i, i2, obj);
    }

    /* access modifiers changed from: protected */
    public void a_(int i, int i2) {
        notifyItemRangeInserted(i, i2);
    }

    /* access modifiers changed from: protected */
    public void c(int i, int i2) {
        notifyItemRangeRemoved(i, i2);
    }

    /* access modifiers changed from: protected */
    public void b(int i, int i2, int i3) {
        if (i3 == 1) {
            notifyItemMoved(i, i2);
            return;
        }
        throw new IllegalStateException("itemCount should be always 1  (actual: " + i3 + Operators.BRACKET_END_STR);
    }

    /* access modifiers changed from: package-private */
    public final void n() {
        b();
    }

    /* access modifiers changed from: package-private */
    public final void g(int i, int i2) {
        a(i, i2);
    }

    /* access modifiers changed from: package-private */
    public final void c(int i, int i2, Object obj) {
        b(i, i2, obj);
    }

    /* access modifiers changed from: package-private */
    public final void h(int i, int i2) {
        a_(i, i2);
    }

    /* access modifiers changed from: package-private */
    public final void i(int i, int i2) {
        c(i, i2);
    }

    /* access modifiers changed from: package-private */
    public final void d(int i, int i2, int i3) {
        b(i, i2, i3);
    }

    private static final class BridgeObserver<VH extends RecyclerView.ViewHolder> extends RecyclerView.AdapterDataObserver {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<BaseWrapperAdapter<VH>> f5738a;

        public BridgeObserver(BaseWrapperAdapter<VH> baseWrapperAdapter) {
            this.f5738a = new WeakReference<>(baseWrapperAdapter);
        }

        public void onChanged() {
            BaseWrapperAdapter baseWrapperAdapter = (BaseWrapperAdapter) this.f5738a.get();
            if (baseWrapperAdapter != null) {
                baseWrapperAdapter.n();
            }
        }

        public void onItemRangeChanged(int i, int i2) {
            BaseWrapperAdapter baseWrapperAdapter = (BaseWrapperAdapter) this.f5738a.get();
            if (baseWrapperAdapter != null) {
                baseWrapperAdapter.g(i, i2);
            }
        }

        public void onItemRangeChanged(int i, int i2, Object obj) {
            BaseWrapperAdapter baseWrapperAdapter = (BaseWrapperAdapter) this.f5738a.get();
            if (baseWrapperAdapter != null) {
                baseWrapperAdapter.c(i, i2, obj);
            }
        }

        public void onItemRangeInserted(int i, int i2) {
            BaseWrapperAdapter baseWrapperAdapter = (BaseWrapperAdapter) this.f5738a.get();
            if (baseWrapperAdapter != null) {
                baseWrapperAdapter.h(i, i2);
            }
        }

        public void onItemRangeRemoved(int i, int i2) {
            BaseWrapperAdapter baseWrapperAdapter = (BaseWrapperAdapter) this.f5738a.get();
            if (baseWrapperAdapter != null) {
                baseWrapperAdapter.i(i, i2);
            }
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
            BaseWrapperAdapter baseWrapperAdapter = (BaseWrapperAdapter) this.f5738a.get();
            if (baseWrapperAdapter != null) {
                baseWrapperAdapter.d(i, i2, i3);
            }
        }
    }
}
