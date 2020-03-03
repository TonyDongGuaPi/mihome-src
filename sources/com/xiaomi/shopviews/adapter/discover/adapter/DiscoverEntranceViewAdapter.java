package com.xiaomi.shopviews.adapter.discover.adapter;

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
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.utils.CommonUtils;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import java.util.List;

public class DiscoverEntranceViewAdapter extends RecyclerView.Adapter<EntranceItemHolder> {

    /* renamed from: a  reason: collision with root package name */
    private Context f13112a;
    /* access modifiers changed from: private */
    public PageDataBean b;
    /* access modifiers changed from: private */
    public ProviderClickListener c;

    public DiscoverEntranceViewAdapter(Context context, PageDataBean pageDataBean, ProviderClickListener providerClickListener) {
        this.f13112a = context;
        this.b = pageDataBean;
        this.c = providerClickListener;
    }

    public void a(List<PageDataBean.AssemblyInfoBean> list) {
        if (list != null) {
            this.b.v.clear();
            this.b.v.addAll(list);
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public EntranceItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(this.f13112a).inflate(R.layout.discover_quick_ntrance_list_item, viewGroup, false);
        inflate.getLayoutParams().width = (int) (((float) Resources.getSystem().getDisplayMetrics().widthPixels) / 4.0f);
        return new EntranceItemHolder(inflate);
    }

    /* renamed from: a */
    public void onBindViewHolder(EntranceItemHolder entranceItemHolder, int i) {
        final PageDataBean.AssemblyInfoBean assemblyInfoBean = this.b.v.get(i);
        if (assemblyInfoBean.d != null && !TextUtils.isEmpty(assemblyInfoBean.d)) {
            CustomTextView customTextView = entranceItemHolder.f13114a;
            customTextView.setText(assemblyInfoBean.d + "");
        }
        entranceItemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String str = assemblyInfoBean.c;
                if (DiscoverEntranceViewAdapter.this.c != null && str != null && !TextUtils.isEmpty(str)) {
                    DiscoverEntranceViewAdapter.this.c.a(DiscoverEntranceViewAdapter.this.b.b, assemblyInfoBean, "");
                }
            }
        });
        String str = assemblyInfoBean.b;
        if (!TextUtils.isEmpty(str)) {
            ImageLoader.a().a(CommonUtils.a(str), entranceItemHolder.b, new Option().b(R.drawable.default_pic_small_inverse));
            if (this.c != null) {
                this.c.a(this.b.b, assemblyInfoBean);
            }
        }
    }

    public int getItemCount() {
        return this.b.v.size();
    }

    static class EntranceItemHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        CustomTextView f13114a;
        ImageView b;
        LinearLayout c;

        EntranceItemHolder(View view) {
            super(view);
            this.b = (ImageView) view.findViewById(R.id.model_entry_image);
            this.f13114a = (CustomTextView) view.findViewById(R.id.model_entry_name);
            this.c = (LinearLayout) view.findViewById(R.id.id_root_ll);
        }
    }
}
