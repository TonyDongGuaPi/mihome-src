package com.xiaomi.shopviews.adapter.empty;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.widget.R;

public class HomeEmptyProvider extends HomeItemProvider<PageDataBean, BaseViewHolder> {
    public int a() {
        return 0;
    }

    public void a(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
    }

    public int b() {
        return R.layout.empty_view;
    }

    public void onClick(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        super.onClick(baseViewHolder, pageDataBean, i);
    }
}
