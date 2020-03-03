package com.xiaomi.shopviews.adapter.quickenter;

import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.model.item.HomeItemContentQuickEnter;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;

public class HomeQuickEnterViewProvider extends HomeItemProvider<HomeItemContentQuickEnter, BaseViewHolder> {
    public int a() {
        return 17;
    }

    public int b() {
        return R.layout.quick_enter_list_item;
    }

    public void a(BaseViewHolder baseViewHolder, HomeItemContentQuickEnter homeItemContentQuickEnter, int i) {
        View view = baseViewHolder.itemView;
        if (homeItemContentQuickEnter.f13199a.size() > 0) {
            ImageLoader.a().a(homeItemContentQuickEnter.f13199a.get(0).f13200a, (ImageView) view.findViewById(R.id.product_image_one), new Option().b(R.drawable.default_pic_small_inverse));
            ((CustomTextView) view.findViewById(R.id.product_name_one)).setText(homeItemContentQuickEnter.f13199a.get(0).c);
        }
        if (homeItemContentQuickEnter.f13199a.size() > 1) {
            ImageLoader.a().a(homeItemContentQuickEnter.f13199a.get(1).f13200a, (ImageView) view.findViewById(R.id.product_image_two), new Option().b(R.drawable.default_pic_small_inverse));
            ((CustomTextView) view.findViewById(R.id.product_name_two)).setText(homeItemContentQuickEnter.f13199a.get(1).c);
            baseViewHolder.b(R.id.layout_contain_two, true);
        } else {
            baseViewHolder.b(R.id.layout_contain_two, false);
        }
        if (homeItemContentQuickEnter.f13199a.size() > 2) {
            ImageLoader.a().a(homeItemContentQuickEnter.f13199a.get(2).f13200a, (ImageView) view.findViewById(R.id.product_image_three), new Option().b(R.drawable.default_pic_small_inverse));
            ((CustomTextView) view.findViewById(R.id.product_name_three)).setText(homeItemContentQuickEnter.f13199a.get(2).c);
            baseViewHolder.b(R.id.layout_contain_three, true);
        } else {
            baseViewHolder.b(R.id.layout_contain_three, false);
        }
        if (homeItemContentQuickEnter.f13199a.size() > 3) {
            ImageLoader.a().a(homeItemContentQuickEnter.f13199a.get(3).f13200a, (ImageView) view.findViewById(R.id.product_image_four), new Option().b(R.drawable.default_pic_small_inverse));
            ((CustomTextView) view.findViewById(R.id.product_name_four)).setText(homeItemContentQuickEnter.f13199a.get(3).c);
            baseViewHolder.b(R.id.layout_contain_four, true);
            return;
        }
        baseViewHolder.b(R.id.layout_contain_four, false);
    }

    public void onClick(BaseViewHolder baseViewHolder, HomeItemContentQuickEnter homeItemContentQuickEnter, int i) {
        super.onClick(baseViewHolder, homeItemContentQuickEnter, i);
    }
}
