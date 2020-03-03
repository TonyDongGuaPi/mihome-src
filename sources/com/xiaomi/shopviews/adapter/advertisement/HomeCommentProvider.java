package com.xiaomi.shopviews.adapter.advertisement;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.adapter.countdown.CommentViewAdapter;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;

public class HomeCommentProvider extends HomeItemProvider<PageDataBean, BaseViewHolder> {
    private ProviderClickListener c;

    public int a() {
        return 7;
    }

    public HomeCommentProvider(ProviderClickListener providerClickListener) {
        this.c = providerClickListener;
    }

    public int b() {
        return R.layout.comment_list_item;
    }

    public void a(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        View view = baseViewHolder.itemView;
        CustomTextView customTextView = (CustomTextView) view.findViewById(R.id.hot_reviews_title);
        if (!TextUtils.isEmpty(pageDataBean.d)) {
            customTextView.setVisibility(0);
            customTextView.setText(pageDataBean.d);
        } else {
            customTextView.setVisibility(8);
        }
        if (!TextUtils.isEmpty(pageDataBean.i)) {
            customTextView.setTextColor(Color.parseColor(pageDataBean.i));
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        CommentViewAdapter commentViewAdapter = new CommentViewAdapter(view.getContext(), pageDataBean, this.c);
        recyclerView.setAdapter(commentViewAdapter);
        commentViewAdapter.a(pageDataBean.v);
    }

    public void onClick(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        super.onClick(baseViewHolder, pageDataBean, i);
    }
}
