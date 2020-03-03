package com.xiaomi.shopviews.adapter.crowdfunding;

import android.widget.ImageView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.model.item.HomeItemContentCrowdfundingSingle;
import com.xiaomi.shopviews.widget.R;

public class HomeCrowdfundingSingleViewProvider extends HomeItemProvider<HomeItemContentCrowdfundingSingle, BaseViewHolder> {
    public int a() {
        return 22;
    }

    public int b() {
        return R.layout.crowdfunding_single_list_item;
    }

    public void a(BaseViewHolder baseViewHolder, HomeItemContentCrowdfundingSingle homeItemContentCrowdfundingSingle, int i) {
        ImageView imageView = (ImageView) baseViewHolder.itemView.findViewById(R.id.imageView);
        Option option = new Option();
        option.a(Coder.a(imageView.getContext(), 10.0f));
        ImageLoader.a().a(homeItemContentCrowdfundingSingle.f13184a.get(0).f13185a, imageView, option);
    }

    public void onClick(BaseViewHolder baseViewHolder, HomeItemContentCrowdfundingSingle homeItemContentCrowdfundingSingle, int i) {
        super.onClick(baseViewHolder, homeItemContentCrowdfundingSingle, i);
    }
}
