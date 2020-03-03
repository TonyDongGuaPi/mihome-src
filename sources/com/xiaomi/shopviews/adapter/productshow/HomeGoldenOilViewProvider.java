package com.xiaomi.shopviews.adapter.productshow;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.shopviews.WidgetApplication;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.adapter.HomeRvAdapter;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.utils.CommonUtils;
import com.xiaomi.shopviews.utils.EventRecordConstants;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import java.util.List;

public class HomeGoldenOilViewProvider extends HomeItemProvider<PageDataBean, BaseViewHolder> {
    private ProviderClickListener c;

    public int a() {
        return 9;
    }

    public HomeGoldenOilViewProvider(ProviderClickListener providerClickListener) {
        this.c = providerClickListener;
    }

    /* access modifiers changed from: private */
    public void onClick(String str, PageDataBean.AssemblyInfoBean assemblyInfoBean) {
        if (this.c != null) {
            this.c.a(str, assemblyInfoBean, "");
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2) {
        if (this.c != null) {
            this.c.a(str, str2);
        }
    }

    private void a(String str, PageDataBean.AssemblyInfoBean assemblyInfoBean) {
        if (this.c != null) {
            this.c.a(str, assemblyInfoBean);
        }
    }

    public int b() {
        return R.layout.product_show_view_item_5;
    }

    public void a(BaseViewHolder baseViewHolder, final PageDataBean pageDataBean, int i) {
        View view = baseViewHolder.itemView;
        CustomTextView customTextView = (CustomTextView) view.findViewById(R.id.titleText);
        if (!TextUtils.isEmpty(pageDataBean.d)) {
            customTextView.setVisibility(0);
            customTextView.setText(pageDataBean.d);
        } else {
            customTextView.setVisibility(8);
        }
        if (!TextUtils.isEmpty(pageDataBean.i)) {
            customTextView.setTextColor(Color.parseColor(pageDataBean.i));
        }
        List<PageDataBean.AssemblyInfoBean> list = pageDataBean.v;
        if (list != null && list.size() > 0) {
            PageDataBean.AssemblyInfoBean assemblyInfoBean = list.get(0);
            ImageView imageView = (ImageView) view.findViewById(R.id.item_image);
            int i2 = Resources.getSystem().getDisplayMetrics().widthPixels;
            if (i2 != 0) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                layoutParams.width = -1;
                layoutParams.height = ((i2 - Coder.a((Context) WidgetApplication.f13060a, 30.0f)) * 2) / 3;
                imageView.setLayoutParams(layoutParams);
            }
            if (!TextUtils.isEmpty(assemblyInfoBean.b)) {
                Option option = new Option();
                option.a(Coder.a(imageView.getContext(), 10.0f));
                option.b(R.drawable.default_pic_small_inverse);
                option.d = true;
                option.e = true;
                ImageLoader.a().a(CommonUtils.a(assemblyInfoBean.b), imageView, option);
            }
            CustomTextView customTextView2 = (CustomTextView) view.findViewById(R.id.item_title);
            customTextView2.setText(assemblyInfoBean.d);
            if (!TextUtils.isEmpty(assemblyInfoBean.v)) {
                customTextView2.setTextColor(Color.parseColor(assemblyInfoBean.v));
            }
            CustomTextView customTextView3 = (CustomTextView) view.findViewById(R.id.item_desc);
            customTextView3.setText(assemblyInfoBean.i);
            if (!TextUtils.isEmpty(assemblyInfoBean.z)) {
                customTextView3.setTextColor(Color.parseColor(assemblyInfoBean.z));
            }
            CustomTextView customTextView4 = (CustomTextView) view.findViewById(R.id.item_price);
            TextUtils.isEmpty(assemblyInfoBean.e);
            StringBuilder sb = new StringBuilder();
            sb.append(assemblyInfoBean.E ? "" : HomeRvAdapter.c());
            sb.append(assemblyInfoBean.e);
            customTextView4.setText(sb.toString());
            CustomTextView customTextView5 = (CustomTextView) view.findViewById(R.id.item_price_origin);
            if (TextUtils.isEmpty(assemblyInfoBean.f) || assemblyInfoBean.e.equals(assemblyInfoBean.f)) {
                customTextView5.setVisibility(4);
            } else {
                customTextView5.setVisibility(0);
                StringBuilder sb2 = new StringBuilder();
                sb2.append(assemblyInfoBean.E ? "" : HomeRvAdapter.c());
                sb2.append(assemblyInfoBean.f);
                customTextView5.setText(sb2.toString());
                customTextView5.getPaint().setAntiAlias(true);
                customTextView5.getPaint().setFlags(16);
            }
            CustomTextView customTextView6 = (CustomTextView) view.findViewById(R.id.buy_btn);
            baseViewHolder.b(R.id.buy_btn);
            customTextView6.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (pageDataBean.v != null && pageDataBean.v.size() > 0) {
                        HomeGoldenOilViewProvider.this.onClick(pageDataBean.b, pageDataBean.v.get(0));
                        HomeGoldenOilViewProvider.this.a(EventRecordConstants.EventID.e, EventRecordConstants.PageID.j);
                    }
                }
            });
            if (!TextUtils.isEmpty(assemblyInfoBean.x)) {
                customTextView6.setText(assemblyInfoBean.x);
            }
            if (!TextUtils.isEmpty(assemblyInfoBean.y)) {
                customTextView6.setTextColor(Color.parseColor(assemblyInfoBean.y));
                ((GradientDrawable) customTextView6.getBackground()).setStroke(Coder.a(customTextView6.getContext(), 1.0f), Color.parseColor(assemblyInfoBean.y));
            }
            a(pageDataBean.b, assemblyInfoBean);
        }
    }

    public void onClick(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        super.onClick(baseViewHolder, pageDataBean, i);
        if (pageDataBean.v != null && pageDataBean.v.size() > 0) {
            onClick(pageDataBean.b, pageDataBean.v.get(0));
            a(EventRecordConstants.EventID.d, EventRecordConstants.PageID.j);
        }
    }
}
