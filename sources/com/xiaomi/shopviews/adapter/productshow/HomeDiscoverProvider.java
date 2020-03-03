package com.xiaomi.shopviews.adapter.productshow;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.utils.EventRecordConstants;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;

public class HomeDiscoverProvider extends HomeItemProvider<PageDataBean, BaseViewHolder> {
    /* access modifiers changed from: private */
    public ProviderClickListener c;

    public int a() {
        return 8;
    }

    public HomeDiscoverProvider() {
    }

    public HomeDiscoverProvider(ProviderClickListener providerClickListener) {
        this.c = providerClickListener;
    }

    public int b() {
        return R.layout.home_discover_show_list_item;
    }

    public void a(BaseViewHolder baseViewHolder, final PageDataBean pageDataBean, int i) {
        View view = baseViewHolder.itemView;
        if (!TextUtils.isEmpty(pageDataBean.d)) {
            ((CustomTextView) view.findViewById(R.id.tv_discover_title)).setText(pageDataBean.d);
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(1);
        recyclerView.setLayoutManager(linearLayoutManager);
        HomeDiscoverShowListAdapter homeDiscoverShowListAdapter = new HomeDiscoverShowListAdapter(view.getContext(), pageDataBean, this.c);
        recyclerView.setAdapter(homeDiscoverShowListAdapter);
        CustomTextView customTextView = (CustomTextView) view.findViewById(R.id.tv_view_more);
        if (pageDataBean.v == null || pageDataBean.v.size() <= 3) {
            customTextView.setVisibility(8);
            homeDiscoverShowListAdapter.a(pageDataBean.v);
        } else {
            customTextView.setVisibility(0);
            homeDiscoverShowListAdapter.a(pageDataBean.v.subList(0, 3));
        }
        customTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (HomeDiscoverProvider.this.c != null) {
                    HomeDiscoverProvider.this.c.a(pageDataBean, pageDataBean.b, (Object) null);
                    HomeDiscoverProvider.this.c.a(EventRecordConstants.EventID.c, EventRecordConstants.PageID.h);
                }
            }
        });
        customTextView.setVisibility(8);
    }

    public void onClick(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        super.onClick(baseViewHolder, pageDataBean, i);
    }
}
