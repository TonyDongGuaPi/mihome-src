package com.xiaomi.shopviews.adapter.discover.provider;

import android.view.View;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.adapter.discover.adapter.DiscoverBannerLoopViewAdapter;
import com.xiaomi.shopviews.adapter.discover.widget.BannerLayout;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.widget.R;

public class DiscoverGalleryProvider extends BaseItemProvider<PageDataBean, BaseViewHolder> {
    private ProviderClickListener c;

    public int a() {
        return 11;
    }

    public DiscoverGalleryProvider() {
    }

    public DiscoverGalleryProvider(ProviderClickListener providerClickListener) {
        this.c = providerClickListener;
    }

    public int b() {
        return R.layout.widget_item_discover_banner;
    }

    public void a(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        View view = baseViewHolder.itemView;
        ((BannerLayout) view.findViewById(R.id.recycler)).setAdapter(new DiscoverBannerLoopViewAdapter(view.getContext(), pageDataBean, this.c));
    }
}
