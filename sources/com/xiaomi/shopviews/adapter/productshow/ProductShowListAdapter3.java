package com.xiaomi.shopviews.adapter.productshow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.shopviews.model.item.HomeItemContentProductShow3;
import com.xiaomi.shopviews.utils.CommonUtils;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import java.util.ArrayList;

public class ProductShowListAdapter3 extends RecyclerView.Adapter<RecommendViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f13150a = "RecommendItemAdapter";
    private Context b;
    private ArrayList<HomeItemContentProductShow3.Item> c = new ArrayList<>();

    public ProductShowListAdapter3(Context context) {
        this.b = context;
    }

    public void a(ArrayList<HomeItemContentProductShow3.Item> arrayList) {
        if (arrayList != null) {
            this.c.clear();
            this.c.addAll(arrayList);
            notifyDataSetChanged();
        }
    }

    public void b(ArrayList<HomeItemContentProductShow3.Item> arrayList) {
        if (arrayList != null) {
            this.c.addAll(arrayList);
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void onBindViewHolder(RecommendViewHolder recommendViewHolder, int i) {
        HomeItemContentProductShow3.Item item = this.c.get(i);
        if (!TextUtils.isEmpty(item.b)) {
            ImageLoader.a().a(CommonUtils.a(item.b), recommendViewHolder.f13151a);
        }
        recommendViewHolder.b.setText(item.f13196a);
        recommendViewHolder.c.setText(item.c);
        if (!TextUtils.isEmpty(item.d) && !item.c.equals(item.d)) {
            recommendViewHolder.d.setText(item.d);
            recommendViewHolder.d.getPaint().setAntiAlias(true);
            recommendViewHolder.d.getPaint().setFlags(16);
        }
    }

    /* renamed from: a */
    public RecommendViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RecommendViewHolder(LayoutInflater.from(this.b).inflate(R.layout.product_show_view_adapter_item_3, viewGroup, false));
    }

    public int getItemCount() {
        return this.c.size();
    }

    static class RecommendViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        ImageView f13151a;
        CustomTextView b;
        CustomTextView c;
        CustomTextView d;

        RecommendViewHolder(View view) {
            super(view);
            this.f13151a = (ImageView) view.findViewById(R.id.item_image);
            this.b = (CustomTextView) view.findViewById(R.id.item_title);
            this.c = (CustomTextView) view.findViewById(R.id.item_price);
            this.d = (CustomTextView) view.findViewById(R.id.item_price_origin);
        }
    }
}
