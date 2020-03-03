package com.xiaomi.shopviews.adapter.countdown;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.shopviews.model.item.HomeItemContentCrowdfundingMultiple;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import java.util.ArrayList;
import java.util.List;

public class CrowdfundingMultipleAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    private Context f13095a;
    private List<HomeItemContentCrowdfundingMultiple.Item> b = new ArrayList();

    public CrowdfundingMultipleAdapter(Context context) {
        this.f13095a = context;
    }

    public void a(List<HomeItemContentCrowdfundingMultiple.Item> list) {
        if (list != null) {
            this.b.clear();
            this.b.addAll(list);
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void onBindViewHolder(ProductViewHolder productViewHolder, int i) {
        HomeItemContentCrowdfundingMultiple.Item item = this.b.get(i);
        productViewHolder.f13097a.setText(item.c);
        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        ImageLoader.a().a(item.f13183a, productViewHolder.b);
    }

    /* renamed from: a */
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ProductViewHolder(LayoutInflater.from(this.f13095a).inflate(R.layout.crowdfunding_multiple_adapter_list_item, viewGroup, false));
    }

    public int getItemCount() {
        return this.b.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        CustomTextView f13097a;
        ImageView b;

        ProductViewHolder(View view) {
            super(view);
            this.f13097a = (CustomTextView) view.findViewById(R.id.product_name);
            this.b = (ImageView) view.findViewById(R.id.product_img);
        }
    }
}
