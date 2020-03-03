package com.chad.library.adapter.base;

import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.chad.library.adapter.base.util.ProviderDelegate;
import java.util.List;

public abstract class MultipleItemRvAdapter<T, V extends BaseViewHolder> extends BaseQuickAdapter<T, V> {

    /* renamed from: a  reason: collision with root package name */
    protected ProviderDelegate f5131a;
    private SparseArray<BaseItemProvider> b;

    public abstract void b();

    /* access modifiers changed from: protected */
    public abstract int d(T t);

    public MultipleItemRvAdapter(@Nullable List<T> list) {
        super(list);
    }

    public void a() {
        this.f5131a = new ProviderDelegate();
        a(new MultiTypeDelegate<T>() {
            /* access modifiers changed from: protected */
            public int a(T t) {
                return MultipleItemRvAdapter.this.d(t);
            }
        });
        b();
        this.b = this.f5131a.a();
        for (int i = 0; i < this.b.size(); i++) {
            int keyAt = this.b.keyAt(i);
            BaseItemProvider baseItemProvider = this.b.get(keyAt);
            baseItemProvider.b = this.s;
            z().a(keyAt, baseItemProvider.b());
        }
    }

    /* access modifiers changed from: protected */
    public void a(V v, T t) {
        BaseItemProvider baseItemProvider = this.b.get(v.getItemViewType());
        baseItemProvider.f5143a = v.itemView.getContext();
        int layoutPosition = v.getLayoutPosition() - t();
        baseItemProvider.a(v, t, layoutPosition);
        a(v, t, layoutPosition, baseItemProvider);
    }

    private void a(V v, T t, int i, BaseItemProvider baseItemProvider) {
        BaseQuickAdapter.OnItemClickListener J = J();
        BaseQuickAdapter.OnItemLongClickListener I = I();
        if (J == null || I == null) {
            View view = v.itemView;
            if (J == null) {
                final BaseItemProvider baseItemProvider2 = baseItemProvider;
                final V v2 = v;
                final T t2 = t;
                final int i2 = i;
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        baseItemProvider2.onClick(v2, t2, i2);
                    }
                });
            }
            if (I == null) {
                final BaseItemProvider baseItemProvider3 = baseItemProvider;
                final V v3 = v;
                final T t3 = t;
                final int i3 = i;
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        return baseItemProvider3.b(v3, t3, i3);
                    }
                });
            }
        }
    }
}
