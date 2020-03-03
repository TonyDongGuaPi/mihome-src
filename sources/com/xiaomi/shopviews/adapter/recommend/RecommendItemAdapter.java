package com.xiaomi.shopviews.adapter.recommend;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.shopviews.adapter.HomeRvAdapter;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.utils.CommonUtils;
import com.xiaomi.shopviews.utils.EventRecordConstants;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import java.util.ArrayList;
import java.util.List;

public class RecommendItemAdapter extends RecyclerView.Adapter<RecommendViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f13153a = "RecommendItemAdapter";
    private Context b;
    /* access modifiers changed from: private */
    public PageDataBean c;
    private List<PageDataBean.AssemblyInfoBean> d = new ArrayList();
    private ProviderClickListener e;

    public RecommendItemAdapter(Context context, PageDataBean pageDataBean, ProviderClickListener providerClickListener) {
        this.b = context;
        this.c = pageDataBean;
        this.e = providerClickListener;
    }

    /* access modifiers changed from: private */
    public void onClick(String str, PageDataBean.AssemblyInfoBean assemblyInfoBean) {
        if (this.e != null) {
            this.e.a(str, assemblyInfoBean, "");
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2) {
        if (this.e != null) {
            this.e.a(str, str2);
        }
    }

    private void a(String str, PageDataBean.AssemblyInfoBean assemblyInfoBean) {
        if (this.e != null) {
            this.e.a(str, assemblyInfoBean);
        }
    }

    public void a(List<PageDataBean.AssemblyInfoBean> list) {
        if (list != null) {
            this.d.clear();
            this.d.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void a(ArrayList<PageDataBean.AssemblyInfoBean> arrayList) {
        if (arrayList != null) {
            this.d.addAll(arrayList);
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void onBindViewHolder(RecommendViewHolder recommendViewHolder, final int i) {
        final PageDataBean.AssemblyInfoBean assemblyInfoBean = this.d.get(i);
        if (!TextUtils.isEmpty(assemblyInfoBean.t)) {
            ((GradientDrawable) recommendViewHolder.g.getBackground()).setColor(Color.parseColor(assemblyInfoBean.t));
        }
        if (!TextUtils.isEmpty(assemblyInfoBean.g)) {
            recommendViewHolder.e.setVisibility(0);
            ImageLoader.a().a(assemblyInfoBean.g, recommendViewHolder.e);
            recommendViewHolder.f.setVisibility(4);
        } else {
            recommendViewHolder.e.setVisibility(4);
        }
        if (!TextUtils.isEmpty(assemblyInfoBean.A)) {
            recommendViewHolder.f.setVisibility(0);
            recommendViewHolder.f.setText(assemblyInfoBean.A);
            recommendViewHolder.e.setVisibility(4);
        } else {
            recommendViewHolder.f.setVisibility(4);
        }
        if (!TextUtils.isEmpty(assemblyInfoBean.b)) {
            ImageLoader.a().a(CommonUtils.a(assemblyInfoBean.b), recommendViewHolder.f13155a, new Option().b(R.drawable.default_pic_small_inverse));
        }
        recommendViewHolder.b.setText(assemblyInfoBean.d);
        if (!TextUtils.isEmpty(assemblyInfoBean.e)) {
            CustomTextView customTextView = recommendViewHolder.c;
            StringBuilder sb = new StringBuilder();
            sb.append(assemblyInfoBean.E ? "" : HomeRvAdapter.c());
            sb.append(assemblyInfoBean.e);
            customTextView.setText(sb.toString());
        }
        if (!TextUtils.isEmpty(assemblyInfoBean.f) && !assemblyInfoBean.e.equals(assemblyInfoBean.f)) {
            CustomTextView customTextView2 = recommendViewHolder.d;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(assemblyInfoBean.E ? "" : HomeRvAdapter.c());
            sb2.append(assemblyInfoBean.f);
            customTextView2.setText(sb2.toString());
            recommendViewHolder.d.getPaint().setAntiAlias(true);
            recommendViewHolder.d.getPaint().setFlags(16);
        }
        recommendViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RecommendItemAdapter.this.onClick(RecommendItemAdapter.this.c.b, assemblyInfoBean);
                RecommendItemAdapter.this.a(String.format(EventRecordConstants.EventID.b, new Object[]{Integer.valueOf(i + 1)}), "audio");
            }
        });
        a(this.c.b, assemblyInfoBean);
    }

    /* renamed from: a */
    public RecommendViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RecommendViewHolder(LayoutInflater.from(this.b).inflate(R.layout.recommend_item, viewGroup, false));
    }

    public int getItemCount() {
        return this.d.size();
    }

    static class RecommendViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        ImageView f13155a;
        CustomTextView b;
        CustomTextView c;
        CustomTextView d;
        ImageView e;
        CustomTextView f;
        RelativeLayout g;

        RecommendViewHolder(View view) {
            super(view);
            this.g = (RelativeLayout) view.findViewById(R.id.layout_container);
            this.e = (ImageView) view.findViewById(R.id.corner_mark_img);
            this.f = (CustomTextView) view.findViewById(R.id.corner_mark_txt);
            this.f13155a = (ImageView) view.findViewById(R.id.item_image);
            this.b = (CustomTextView) view.findViewById(R.id.item_title);
            this.c = (CustomTextView) view.findViewById(R.id.item_price);
            this.d = (CustomTextView) view.findViewById(R.id.item_price_origin);
        }
    }
}
