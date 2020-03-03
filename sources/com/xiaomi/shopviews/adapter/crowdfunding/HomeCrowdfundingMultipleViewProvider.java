package com.xiaomi.shopviews.adapter.crowdfunding;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.adapter.countdown.CrowdfundingMultipleAdapter;
import com.xiaomi.shopviews.model.item.HomeItemContentCrowdfundingMultiple;
import com.xiaomi.shopviews.widget.R;

public class HomeCrowdfundingMultipleViewProvider extends HomeItemProvider<HomeItemContentCrowdfundingMultiple, BaseViewHolder> {
    public int a() {
        return 23;
    }

    public int b() {
        return R.layout.crowdfunding_multiple_list_item;
    }

    public void a(BaseViewHolder baseViewHolder, HomeItemContentCrowdfundingMultiple homeItemContentCrowdfundingMultiple, int i) {
        View view = baseViewHolder.itemView;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(1);
        recyclerView.setLayoutManager(linearLayoutManager);
        CrowdfundingMultipleAdapter crowdfundingMultipleAdapter = new CrowdfundingMultipleAdapter(view.getContext());
        recyclerView.setAdapter(crowdfundingMultipleAdapter);
        crowdfundingMultipleAdapter.a(homeItemContentCrowdfundingMultiple.f13182a);
    }

    public void onClick(BaseViewHolder baseViewHolder, HomeItemContentCrowdfundingMultiple homeItemContentCrowdfundingMultiple, int i) {
        super.onClick(baseViewHolder, homeItemContentCrowdfundingMultiple, i);
    }
}
