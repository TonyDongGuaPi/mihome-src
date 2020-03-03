package com.xiaomi.shopviews.adapter.contentshow;

import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.model.item.HomeItemContentShow2;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;

public class HomeContentShowViewProvider2 extends HomeItemProvider<HomeItemContentShow2, BaseViewHolder> {
    public int a() {
        return 26;
    }

    public int b() {
        return R.layout.content_show_2_list_item;
    }

    public void a(BaseViewHolder baseViewHolder, HomeItemContentShow2 homeItemContentShow2, int i) {
        View view = baseViewHolder.itemView;
        CustomTextView customTextView = (CustomTextView) view.findViewById(R.id.product_name);
        ImageLoader.a().a(homeItemContentShow2.f13205a.get(0).f13206a, (ImageView) view.findViewById(R.id.product_img));
    }

    public void onClick(BaseViewHolder baseViewHolder, HomeItemContentShow2 homeItemContentShow2, int i) {
        super.onClick(baseViewHolder, homeItemContentShow2, i);
    }
}
