package com.xiaomi.shopviews.adapter.productshow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.shopviews.model.item.HomeItemContentHotAccessories;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import java.util.ArrayList;
import java.util.List;

public class HomeHotAccessoriesListAdapter extends RecyclerView.Adapter<HotAccessoriesViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    private Context f13144a;
    private List<HomeItemContentHotAccessories.Item> b = new ArrayList();

    public HomeHotAccessoriesListAdapter(Context context) {
        this.f13144a = context;
    }

    public void a(List<HomeItemContentHotAccessories.Item> list) {
        if (list != null) {
            this.b.clear();
            this.b.addAll(list);
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void onBindViewHolder(HotAccessoriesViewHolder hotAccessoriesViewHolder, int i) {
        HomeItemContentHotAccessories.Item item = this.b.get(i);
        if (i <= 2 || i != this.b.size() - 1) {
            hotAccessoriesViewHolder.e.setVisibility(8);
        } else {
            hotAccessoriesViewHolder.e.setVisibility(0);
        }
        hotAccessoriesViewHolder.f13146a.setText(item.f13192a);
        hotAccessoriesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        hotAccessoriesViewHolder.c.setText(item.d);
        hotAccessoriesViewHolder.d.setText(item.d);
        hotAccessoriesViewHolder.d.getPaint().setAntiAlias(true);
        hotAccessoriesViewHolder.d.getPaint().setFlags(16);
        ImageLoader.a().a(item.c, hotAccessoriesViewHolder.b);
    }

    /* renamed from: a */
    public HotAccessoriesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HotAccessoriesViewHolder(LayoutInflater.from(this.f13144a).inflate(R.layout.hot_accessories_adapter_list_item, viewGroup, false));
    }

    public int getItemCount() {
        return this.b.size();
    }

    static class HotAccessoriesViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        CustomTextView f13146a;
        ImageView b;
        CustomTextView c;
        CustomTextView d;
        CustomTextView e;

        HotAccessoriesViewHolder(View view) {
            super(view);
            this.f13146a = (CustomTextView) view.findViewById(R.id.product_name);
            this.b = (ImageView) view.findViewById(R.id.product_image);
            this.c = (CustomTextView) view.findViewById(R.id.tv_price_new);
            this.d = (CustomTextView) view.findViewById(R.id.tv_price_origin);
            this.e = (CustomTextView) view.findViewById(R.id.tv_more);
        }
    }
}
