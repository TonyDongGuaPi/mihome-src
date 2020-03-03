package com.xiaomi.shopviews.adapter.productshow;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.model.item.HomeItemContentHotAccessories;
import com.xiaomi.shopviews.widget.R;

public class HomeHotAccessoriesViewProvider extends HomeItemProvider<HomeItemContentHotAccessories, BaseViewHolder> {
    public int a() {
        return 21;
    }

    public int b() {
        return R.layout.hot_accessories_list_item;
    }

    public void a(BaseViewHolder baseViewHolder, HomeItemContentHotAccessories homeItemContentHotAccessories, int i) {
        View view = baseViewHolder.itemView;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        HomeHotAccessoriesListAdapter homeHotAccessoriesListAdapter = new HomeHotAccessoriesListAdapter(view.getContext());
        recyclerView.setAdapter(homeHotAccessoriesListAdapter);
        homeHotAccessoriesListAdapter.a(homeItemContentHotAccessories.f13191a);
    }

    public void onClick(BaseViewHolder baseViewHolder, HomeItemContentHotAccessories homeItemContentHotAccessories, int i) {
        super.onClick(baseViewHolder, homeItemContentHotAccessories, i);
    }
}
