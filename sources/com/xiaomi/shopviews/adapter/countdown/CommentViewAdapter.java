package com.xiaomi.shopviews.adapter.countdown;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.utils.CommonUtils;
import com.xiaomi.shopviews.utils.EventRecordConstants;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import java.util.ArrayList;
import java.util.List;

public class CommentViewAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    private Context f13079a;
    private List<PageDataBean.AssemblyInfoBean> b;
    private ProviderClickListener c;
    /* access modifiers changed from: private */
    public PageDataBean d;

    public CommentViewAdapter(Context context) {
        this.b = new ArrayList();
        this.f13079a = context;
    }

    public CommentViewAdapter(Context context, PageDataBean pageDataBean, ProviderClickListener providerClickListener) {
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

    public void a(List<PageDataBean.AssemblyInfoBean> list) {
        if (list != null) {
            this.b.clear();
            this.b.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void a(ArrayList<PageDataBean.AssemblyInfoBean> arrayList) {
        if (arrayList != null) {
            this.b.addAll(arrayList);
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void onBindViewHolder(ProductViewHolder productViewHolder, final int i) {
        final PageDataBean.AssemblyInfoBean assemblyInfoBean = this.b.get(i);
        int a2 = i == 0 ? Coder.a(this.f13079a, 8.0f) : 0;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) productViewHolder.g.getLayoutParams();
        layoutParams.leftMargin = a2;
        productViewHolder.g.setLayoutParams(layoutParams);
        Option option = new Option();
        option.a(Coder.a(productViewHolder.f13081a.getContext(), 10.0f));
        option.b(R.drawable.default_pic_small_inverse);
        ImageLoader.a().a(CommonUtils.a(assemblyInfoBean.b), productViewHolder.f13081a, option);
        Option option2 = new Option();
        option2.b(true);
        option2.b(R.drawable.icon_user_default_head);
        option2.c(R.drawable.icon_user_default_head);
        ImageLoader.a().a(CommonUtils.a(assemblyInfoBean.B), productViewHolder.c, option2);
        if (!TextUtils.isEmpty(assemblyInfoBean.h)) {
            productViewHolder.d.setText(assemblyInfoBean.h);
        } else {
            productViewHolder.d.setVisibility(4);
        }
        productViewHolder.e.setNumStars(5);
        if (!TextUtils.isEmpty(assemblyInfoBean.k)) {
            productViewHolder.e.setRating(Float.parseFloat(assemblyInfoBean.k));
        } else {
            productViewHolder.e.setRating(5.0f);
        }
        if (!TextUtils.isEmpty(assemblyInfoBean.i)) {
            productViewHolder.f.setText(assemblyInfoBean.i);
        } else {
            productViewHolder.f.setVisibility(4);
        }
        if (!TextUtils.isEmpty(assemblyInfoBean.z)) {
            productViewHolder.f.setTextColor(Color.parseColor(assemblyInfoBean.z));
        }
        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommentViewAdapter.this.onClick(CommentViewAdapter.this.d.b, assemblyInfoBean);
                CommentViewAdapter.this.a(String.format(EventRecordConstants.EventID.b, new Object[]{Integer.valueOf(i + 1)}), EventRecordConstants.PageID.i);
            }
        });
        a(this.d.b, assemblyInfoBean);
    }

    /* renamed from: a */
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ProductViewHolder(LayoutInflater.from(this.f13079a).inflate(R.layout.comment_adapter_list_item, viewGroup, false));
    }

    public int getItemCount() {
        return this.b.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        ImageView f13081a;
        CustomTextView b;
        ImageView c;
        CustomTextView d;
        RatingBar e;
        CustomTextView f;
        RelativeLayout g;

        ProductViewHolder(View view) {
            super(view);
            this.f13081a = (ImageView) view.findViewById(R.id.product_image);
            this.b = (CustomTextView) view.findViewById(R.id.product_name);
            this.c = (ImageView) view.findViewById(R.id.user_header);
            this.d = (CustomTextView) view.findViewById(R.id.user_name);
            this.e = (RatingBar) view.findViewById(R.id.rating_bar);
            this.f = (CustomTextView) view.findViewById(R.id.tv_description);
            this.g = (RelativeLayout) view.findViewById(R.id.layout_root);
        }
    }
}
