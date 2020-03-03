package com.xiaomi.shopviews.adapter.countdown;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.shopviews.WidgetApplication;
import com.xiaomi.shopviews.adapter.HomeRvAdapter;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.utils.EventRecordConstants;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import java.util.ArrayList;
import java.util.List;

public class CountDownListCfgAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    private Context f13087a;
    /* access modifiers changed from: private */
    public List<PageDataBean.AssemblyInfoBean> b;
    private ProviderClickListener c;
    /* access modifiers changed from: private */
    public PageDataBean d;

    public CountDownListCfgAdapter(Context context, PageDataBean pageDataBean, ProviderClickListener providerClickListener) {
        this(context);
        this.d = pageDataBean;
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

    public CountDownListCfgAdapter(Context context) {
        this.b = new ArrayList();
        this.f13087a = context;
    }

    public void a(List<PageDataBean.AssemblyInfoBean> list) {
        if (list != null) {
            this.b.clear();
            this.b.addAll(list);
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void onBindViewHolder(ProductViewHolder productViewHolder, final int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = -1;
        int i7 = 0;
        if (this.b.size() == 1) {
            int i8 = R.drawable.border_shadow_white;
            int a2 = Coder.a(this.f13087a, 220.0f);
            int i9 = Resources.getSystem().getDisplayMetrics().widthPixels;
            if (i9 != 0) {
                a2 = ((i9 - Coder.a((Context) WidgetApplication.f13060a, 30.0f)) * 2) / 3;
            }
            i2 = a2;
            i3 = -1;
            i4 = i8;
            i5 = 0;
        } else {
            i6 = -2;
            int i10 = R.drawable.border_shadow_white_half;
            int a3 = Coder.a(this.f13087a, 7.0f);
            if (i == 0) {
                i7 = Coder.a(this.f13087a, 8.0f);
            }
            i3 = Coder.a(this.f13087a, 285.0f);
            i2 = Coder.a(this.f13087a, 190.0f);
            int i11 = a3;
            i4 = i10;
            i5 = i7;
            i7 = i11;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) productViewHolder.e.getLayoutParams();
        layoutParams.width = i6;
        layoutParams.topMargin = i7;
        layoutParams.leftMargin = i5;
        productViewHolder.e.setLayoutParams(layoutParams);
        productViewHolder.e.setBackgroundResource(i4);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) productViewHolder.b.getLayoutParams();
        layoutParams2.width = i3;
        layoutParams2.height = i2;
        productViewHolder.b.setLayoutParams(layoutParams2);
        PageDataBean.AssemblyInfoBean assemblyInfoBean = this.b.get(i);
        productViewHolder.f13090a.setText(assemblyInfoBean.d);
        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        String str = assemblyInfoBean.b;
        Option option = new Option();
        option.a(Coder.a(productViewHolder.b.getContext(), 10.0f));
        option.b(R.drawable.default_pic_small_inverse);
        option.d = true;
        option.e = true;
        ImageLoader.a().a(str, productViewHolder.b, option);
        if (!TextUtils.isEmpty(this.b.get(i).e)) {
            CustomTextView customTextView = productViewHolder.c;
            StringBuilder sb = new StringBuilder();
            sb.append(this.b.get(i).E ? "" : HomeRvAdapter.c());
            sb.append(this.b.get(i).e);
            customTextView.setText(sb.toString());
        }
        if (!TextUtils.isEmpty(this.b.get(i).f)) {
            CustomTextView customTextView2 = productViewHolder.d;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.b.get(i).E ? "" : HomeRvAdapter.c());
            sb2.append(this.b.get(i).f);
            customTextView2.setText(sb2.toString());
            productViewHolder.d.getPaint().setAntiAlias(true);
            productViewHolder.d.getPaint().setFlags(16);
        } else {
            productViewHolder.d.setText("");
        }
        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CountDownListCfgAdapter.this.onClick(CountDownListCfgAdapter.this.d.b, (PageDataBean.AssemblyInfoBean) CountDownListCfgAdapter.this.b.get(i));
                CountDownListCfgAdapter.this.a(String.format(EventRecordConstants.EventID.b, new Object[]{Integer.valueOf(i + 1)}), EventRecordConstants.PageID.f);
            }
        });
        a(this.d.b, this.b.get(i));
    }

    /* renamed from: a */
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ProductViewHolder(LayoutInflater.from(this.f13087a).inflate(R.layout.countdown_list_item, viewGroup, false));
    }

    public int getItemCount() {
        return this.b.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        CustomTextView f13090a;
        ImageView b;
        CustomTextView c;
        CustomTextView d;
        LinearLayout e;
        CustomTextView f;

        ProductViewHolder(View view) {
            super(view);
            this.e = (LinearLayout) view.findViewById(R.id.layout_root);
            this.f13090a = (CustomTextView) view.findViewById(R.id.product_name);
            this.b = (ImageView) view.findViewById(R.id.product_image);
            this.c = (CustomTextView) view.findViewById(R.id.product_price);
            this.d = (CustomTextView) view.findViewById(R.id.product_old_price);
            this.f = (CustomTextView) view.findViewById(R.id.btn_remind_me);
        }
    }
}
