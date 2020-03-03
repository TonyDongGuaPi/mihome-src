package com.xiaomi.shopviews.adapter;

import com.chad.library.adapter.base.BaseViewHolder;

public interface HomeItemListener<T> {
    boolean a(BaseViewHolder baseViewHolder, T t, int i);

    void onClick(BaseViewHolder baseViewHolder, T t, int i);
}
