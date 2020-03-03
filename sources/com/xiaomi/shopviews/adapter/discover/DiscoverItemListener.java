package com.xiaomi.shopviews.adapter.discover;

import com.chad.library.adapter.base.BaseViewHolder;

public interface DiscoverItemListener<T> {
    boolean a(BaseViewHolder baseViewHolder, T t, int i);

    void onClick(BaseViewHolder baseViewHolder, T t, int i);
}
