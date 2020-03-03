package com.xiaomi.shopviews.adapter.advertisement;

import android.widget.ImageView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.model.item.HomeItemContentAdvertiseHeadImage;
import com.xiaomi.shopviews.widget.R;

public class HomeAdvertiseHeadImageViewProvider extends HomeItemProvider<HomeItemContentAdvertiseHeadImage, BaseViewHolder> {
    public int a() {
        return 24;
    }

    public int b() {
        return R.layout.advertise_head_image_list_item;
    }

    public void a(BaseViewHolder baseViewHolder, HomeItemContentAdvertiseHeadImage homeItemContentAdvertiseHeadImage, int i) {
        ImageLoader.a().a(homeItemContentAdvertiseHeadImage.f13172a.f13173a, (ImageView) baseViewHolder.itemView.findViewById(R.id.iv_head));
    }

    public void onClick(BaseViewHolder baseViewHolder, HomeItemContentAdvertiseHeadImage homeItemContentAdvertiseHeadImage, int i) {
        super.onClick(baseViewHolder, homeItemContentAdvertiseHeadImage, i);
    }
}
