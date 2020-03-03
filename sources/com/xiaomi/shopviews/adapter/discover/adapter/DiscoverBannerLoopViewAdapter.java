package com.xiaomi.shopviews.adapter.discover.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.widget.R;

public class DiscoverBannerLoopViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    private Context f13109a;
    /* access modifiers changed from: private */
    public PageDataBean b;
    /* access modifiers changed from: private */
    public ProviderClickListener c;

    public DiscoverBannerLoopViewAdapter(Context context, PageDataBean pageDataBean, ProviderClickListener providerClickListener) {
        this.f13109a = context;
        this.b = pageDataBean;
        this.c = providerClickListener;
    }

    /* renamed from: a */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_discover_gallery, viewGroup, false));
    }

    /* renamed from: a */
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if (this.b != null && !this.b.v.isEmpty()) {
            String str = this.b.v.get(i % this.b.v.size()).b;
            ImageView imageView = viewHolder.f13111a;
            Option b2 = new Option().b(R.drawable.default_pic_small_inverse);
            b2.a(Coder.a(imageView.getContext(), 10.0f));
            ImageLoader.a().a(str, imageView, b2);
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String str = DiscoverBannerLoopViewAdapter.this.b.v.get(i).c;
                    if (DiscoverBannerLoopViewAdapter.this.c != null && str != null && !TextUtils.isEmpty(str)) {
                        DiscoverBannerLoopViewAdapter.this.c.a(DiscoverBannerLoopViewAdapter.this.b.b, DiscoverBannerLoopViewAdapter.this.b.v.get(i), "");
                    }
                }
            });
            if (this.c != null) {
                this.c.a(this.b.b, this.b.v.get(i));
            }
        }
    }

    public int getItemCount() {
        if (this.b != null) {
            return this.b.v.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        ImageView f13111a;

        ViewHolder(View view) {
            super(view);
            this.f13111a = (ImageView) view.findViewById(R.id.image);
        }
    }
}
