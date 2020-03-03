package com.xiaomi.shopviews.adapter.productshow;

import android.support.v7.widget.RecyclerView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.model.item.HomeItemContentProductShow3;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.recycleview.FullyGridLayoutManager;

public class HomeProductShowViewProvider3 extends HomeItemProvider<HomeItemContentProductShow3, BaseViewHolder> {
    private ProductShowListAdapter3 c;
    private RecyclerView d;

    public int a() {
        return 27;
    }

    public int b() {
        return R.layout.product_show_view3;
    }

    public void a(BaseViewHolder baseViewHolder, HomeItemContentProductShow3 homeItemContentProductShow3, int i) {
        this.d = (RecyclerView) baseViewHolder.itemView.findViewById(R.id.rycRecommend);
        this.d.setLayoutManager(new FullyGridLayoutManager(this.f5143a, 2));
        this.c = new ProductShowListAdapter3(this.f5143a);
        this.d.setAdapter(this.c);
        this.d.setNestedScrollingEnabled(false);
        this.c.a(homeItemContentProductShow3.f13195a);
    }

    public void onClick(BaseViewHolder baseViewHolder, HomeItemContentProductShow3 homeItemContentProductShow3, int i) {
        super.onClick(baseViewHolder, homeItemContentProductShow3, i);
    }
}
