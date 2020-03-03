package com.xiaomi.shopviews.adapter.discover.provider;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.adapter.discover.adapter.DiscoverEntranceViewAdapter;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.widget.R;

public class DiscoverEntranceProvider extends BaseItemProvider<PageDataBean, BaseViewHolder> {
    private ProviderClickListener c;

    public int a() {
        return 12;
    }

    public DiscoverEntranceProvider() {
    }

    public DiscoverEntranceProvider(ProviderClickListener providerClickListener) {
        this.c = providerClickListener;
    }

    public int b() {
        return R.layout.comment_list_item;
    }

    public void a(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        View view = baseViewHolder.itemView;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 4));
            recyclerView.setAdapter(new DiscoverEntranceViewAdapter(baseViewHolder.itemView.getContext(), pageDataBean, this.c));
        }
    }
}
