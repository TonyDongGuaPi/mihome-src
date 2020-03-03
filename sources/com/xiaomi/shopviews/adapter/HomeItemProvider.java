package com.xiaomi.shopviews.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;

public abstract class HomeItemProvider<T, V extends BaseViewHolder> extends BaseItemProvider<T, V> {
    private HomeItemListener<T> c;

    public void a(HomeItemListener<T> homeItemListener) {
        this.c = homeItemListener;
    }

    public void onClick(V v, T t, int i) {
        if (this.c != null) {
            this.c.onClick(v, t, i);
        }
    }

    public boolean b(V v, T t, int i) {
        if (this.c != null) {
            return this.c.a(v, t, i);
        }
        return super.b(v, t, i);
    }
}
