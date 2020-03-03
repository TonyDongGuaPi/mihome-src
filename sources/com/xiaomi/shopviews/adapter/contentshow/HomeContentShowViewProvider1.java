package com.xiaomi.shopviews.adapter.contentshow;

import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.model.item.HomeItemContentShow1;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;

public class HomeContentShowViewProvider1 extends HomeItemProvider<HomeItemContentShow1, BaseViewHolder> {
    public int a() {
        return 25;
    }

    public int b() {
        return R.layout.content_show_1_list_item;
    }

    public void a(BaseViewHolder baseViewHolder, HomeItemContentShow1 homeItemContentShow1, int i) {
        View view = baseViewHolder.itemView;
        CustomTextView customTextView = (CustomTextView) view.findViewById(R.id.product_name);
        ImageLoader.a().a(homeItemContentShow1.f13203a.get(0).f13204a, (ImageView) view.findViewById(R.id.product_img));
    }

    public void onClick(BaseViewHolder baseViewHolder, HomeItemContentShow1 homeItemContentShow1, int i) {
        super.onClick(baseViewHolder, homeItemContentShow1, i);
    }
}
