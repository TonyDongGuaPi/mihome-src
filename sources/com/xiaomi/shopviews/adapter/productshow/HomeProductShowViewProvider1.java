package com.xiaomi.shopviews.adapter.productshow;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.widget.R;

public class HomeProductShowViewProvider1 extends HomeItemProvider<PageDataBean, BaseViewHolder> {
    private ProviderClickListener c;

    public int a() {
        return 4;
    }

    public HomeProductShowViewProvider1(ProviderClickListener providerClickListener) {
        this.c = providerClickListener;
    }

    public int b() {
        return R.layout.product_show_list_item_1;
    }

    public void a(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        View view = baseViewHolder.itemView;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        ProductShowListAdapter1 productShowListAdapter1 = new ProductShowListAdapter1(view.getContext(), pageDataBean, this.c);
        recyclerView.setAdapter(productShowListAdapter1);
        productShowListAdapter1.a(pageDataBean.v);
    }

    public void onClick(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        super.onClick(baseViewHolder, pageDataBean, i);
    }
}
