package com.xiaomi.shopviews.adapter.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.shopviews.WidgetApplication;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.utils.EventRecordConstants;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import com.xiaomi.shopviews.widget.recycleview.FullyGridLayoutManager;
import com.xiaomi.shopviews.widget.recycleview.GridSpacingItemDecoration;

public class HomeRecommendProvider extends HomeItemProvider<PageDataBean, BaseViewHolder> {
    private RecommendItemAdapter c;
    /* access modifiers changed from: private */
    public MultipleItemRvAdapter d;
    private RecyclerView e;
    /* access modifiers changed from: private */
    public ProviderClickListener f;
    private GridSpacingItemDecoration g = new GridSpacingItemDecoration(2, Coder.a((Context) WidgetApplication.f13060a, 10.0f));

    public int a() {
        return 10;
    }

    public HomeRecommendProvider(ProviderClickListener providerClickListener, MultipleItemRvAdapter multipleItemRvAdapter) {
        this.f = providerClickListener;
        this.d = multipleItemRvAdapter;
    }

    public int b() {
        return R.layout.recommend_view;
    }

    public void a(BaseViewHolder baseViewHolder, final PageDataBean pageDataBean, final int i) {
        View view = baseViewHolder.itemView;
        CustomTextView customTextView = (CustomTextView) view.findViewById(R.id.customTextView);
        if (!TextUtils.isEmpty(pageDataBean.d)) {
            customTextView.setVisibility(0);
            customTextView.setText(pageDataBean.d);
        } else {
            customTextView.setVisibility(8);
        }
        this.e = (RecyclerView) view.findViewById(R.id.rycRecommend);
        this.e.setLayoutManager(new FullyGridLayoutManager(this.f5143a, 2));
        if (this.e.getItemDecorationCount() == 0) {
            this.e.addItemDecoration(this.g);
        }
        this.c = new RecommendItemAdapter(this.f5143a, pageDataBean, this.f);
        this.e.setAdapter(this.c);
        this.e.setNestedScrollingEnabled(false);
        if (pageDataBean.v == null || pageDataBean.v.size() <= 4 || pageDataBean.w) {
            this.c.a(pageDataBean.v);
        } else {
            this.c.a(pageDataBean.v.subList(0, 4));
        }
        CustomTextView customTextView2 = (CustomTextView) view.findViewById(R.id.tv_view_more);
        if (pageDataBean.v == null || pageDataBean.v.size() <= 4 || pageDataBean.w) {
            customTextView2.setVisibility(8);
        } else {
            customTextView2.setVisibility(0);
        }
        customTextView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pageDataBean.w = true;
                HomeRecommendProvider.this.d.notifyItemChanged(i);
                if (HomeRecommendProvider.this.f != null) {
                    HomeRecommendProvider.this.f.a(EventRecordConstants.EventID.c, "audio");
                }
            }
        });
    }

    public void onClick(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        super.onClick(baseViewHolder, pageDataBean, i);
    }
}
