package com.xiaomi.shopviews.adapter.productshow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.utils.EventRecordConstants;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import java.util.ArrayList;
import java.util.List;

public class ProductShowListAdapter1 extends RecyclerView.Adapter<ProductViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    private Context f13147a;
    private List<PageDataBean.AssemblyInfoBean> b;
    private ProviderClickListener c;
    /* access modifiers changed from: private */
    public PageDataBean d;

    public ProductShowListAdapter1(Context context, PageDataBean pageDataBean, ProviderClickListener providerClickListener) {
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

    public ProductShowListAdapter1(Context context) {
        this.b = new ArrayList();
        this.f13147a = context;
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
        if (i == 0) {
            productViewHolder.f13149a.setVisibility(0);
        } else {
            productViewHolder.f13149a.setVisibility(8);
        }
        final PageDataBean.AssemblyInfoBean assemblyInfoBean = this.b.get(i);
        productViewHolder.d.setVisibility(8);
        productViewHolder.b.setText(assemblyInfoBean.d);
        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ProductShowListAdapter1.this.onClick(ProductShowListAdapter1.this.d.b, assemblyInfoBean);
                ProductShowListAdapter1.this.a(String.format(EventRecordConstants.EventID.b, new Object[]{Integer.valueOf(i + 1)}), EventRecordConstants.PageID.e);
            }
        });
        ImageLoader.a().a(assemblyInfoBean.b, productViewHolder.c, new Option().b(R.drawable.default_pic_small_inverse));
        a(this.d.b, assemblyInfoBean);
    }

    /* renamed from: a */
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ProductViewHolder(LayoutInflater.from(this.f13147a).inflate(R.layout.product_show_adapter_list_item_1, viewGroup, false));
    }

    public int getItemCount() {
        return this.b.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f13149a;
        CustomTextView b;
        ImageView c;
        CustomTextView d;

        ProductViewHolder(View view) {
            super(view);
            this.f13149a = view.findViewById(R.id.left_view);
            this.b = (CustomTextView) view.findViewById(R.id.product_name);
            this.c = (ImageView) view.findViewById(R.id.product_image);
            this.d = (CustomTextView) view.findViewById(R.id.tv_more);
        }
    }
}
