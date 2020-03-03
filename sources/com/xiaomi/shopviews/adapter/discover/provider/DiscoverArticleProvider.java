package com.xiaomi.shopviews.adapter.discover.provider;

import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.google.gson.Gson;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.shopviews.WidgetApplication;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.model.item.DiscoverExtendedBean;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.utils.TimeFormater;
import com.xiaomi.shopviews.widget.R;

public class DiscoverArticleProvider extends BaseItemProvider<PageDataBean, BaseViewHolder> {
    private ProviderClickListener c;

    public int a() {
        return 13;
    }

    public DiscoverArticleProvider() {
    }

    public DiscoverArticleProvider(ProviderClickListener providerClickListener) {
        this.c = providerClickListener;
    }

    public int b() {
        return R.layout.discover_article_list_item;
    }

    public void a(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        DiscoverExtendedBean discoverExtendedBean;
        String a2;
        View view = baseViewHolder.itemView;
        TextView textView = (TextView) view.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_content);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_addtime);
        TextView textView4 = (TextView) view.findViewById(R.id.tv_viewCount);
        ImageView imageView = (ImageView) view.findViewById(R.id.id_discover_article_iv);
        ((ImageView) view.findViewById(R.id.id_video_player_btn)).setVisibility(8);
        ((ImageView) view.findViewById(R.id.id_discover_gallery_1)).setVisibility(4);
        imageView.setVisibility(0);
        PageDataBean.AssemblyInfoBean assemblyInfoBean = pageDataBean.v.get(0);
        if (!(assemblyInfoBean.r == null || TextUtils.isEmpty(assemblyInfoBean.r) || (discoverExtendedBean = (DiscoverExtendedBean) new Gson().fromJson(assemblyInfoBean.r, DiscoverExtendedBean.class)) == null)) {
            String f = discoverExtendedBean.f();
            if (f != null && !TextUtils.isEmpty(f)) {
                textView4.setText(discoverExtendedBean.f());
            }
            if (!(discoverExtendedBean.a() == null || (a2 = TimeFormater.a(discoverExtendedBean.a().longValue())) == null || TextUtils.isEmpty(a2))) {
                textView3.setText(a2);
            }
        }
        if (assemblyInfoBean.d != null && !TextUtils.isEmpty(assemblyInfoBean.d)) {
            textView.setText(assemblyInfoBean.d);
        }
        if (assemblyInfoBean.i != null && !TextUtils.isEmpty(assemblyInfoBean.i)) {
            textView2.setText(assemblyInfoBean.i);
        }
        int i2 = Resources.getSystem().getDisplayMetrics().widthPixels;
        if (i2 != 0) {
            double a3 = (double) (i2 - Coder.a((Context) WidgetApplication.f13060a, 30.0f));
            Double.isNaN(a3);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = (int) (a3 / 2.1d);
            imageView.setLayoutParams(layoutParams);
        }
        Option b = new Option().b(R.drawable.default_pic_small_inverse);
        b.a(Coder.a(imageView.getContext(), 10.0f));
        b.e = true;
        b.d = true;
        ImageLoader.a().a(assemblyInfoBean.b, imageView, b);
        if (this.c != null) {
            this.c.a(pageDataBean.b, assemblyInfoBean);
        }
    }

    public void onClick(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        DiscoverExtendedBean discoverExtendedBean;
        String str = pageDataBean.v.get(0).c;
        if (this.c != null && str != null && !TextUtils.isEmpty(str)) {
            String str2 = "";
            if (!(pageDataBean.v.get(0).r == null || TextUtils.isEmpty(pageDataBean.v.get(0).r) || (discoverExtendedBean = (DiscoverExtendedBean) new Gson().fromJson(pageDataBean.v.get(0).r, DiscoverExtendedBean.class)) == null)) {
                str2 = discoverExtendedBean.e();
            }
            this.c.a(pageDataBean.b, pageDataBean.v.get(0), str2);
        }
    }
}
