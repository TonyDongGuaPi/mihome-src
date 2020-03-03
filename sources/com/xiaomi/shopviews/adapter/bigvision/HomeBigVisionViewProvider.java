package com.xiaomi.shopviews.adapter.bigvision;

import android.view.View;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.model.item.HomeItemContentBigVision;
import com.xiaomi.shopviews.widget.R;

public class HomeBigVisionViewProvider extends HomeItemProvider<HomeItemContentBigVision, BaseViewHolder> {
    GyroscopeObserver c = new GyroscopeObserver();

    public int a() {
        return 18;
    }

    public int b() {
        return R.layout.big_vision_list_item;
    }

    public void a(BaseViewHolder baseViewHolder, HomeItemContentBigVision homeItemContentBigVision, int i) {
        View view = baseViewHolder.itemView;
        ((GravityImageView) view.findViewById(R.id.panorama_image_view1)).setGyroscopeObserver(this.c);
        ((GravityImageView) view.findViewById(R.id.panorama_image_view2)).setGyroscopeObserver(this.c);
        ((GravityImageView) view.findViewById(R.id.panorama_image_view3)).setGyroscopeObserver(this.c);
        this.c.a(view.getContext());
    }

    public void onClick(BaseViewHolder baseViewHolder, HomeItemContentBigVision homeItemContentBigVision, int i) {
        super.onClick(baseViewHolder, homeItemContentBigVision, i);
    }
}
