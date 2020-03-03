package com.xiaomi.shopviews.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.xiaomi.base.utils.PlainUtils;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.widget.R;

public class HomeProductTagImageHelper {

    /* renamed from: a  reason: collision with root package name */
    private ImageView f13222a;

    public HomeProductTagImageHelper(View view) {
        a(view);
    }

    private void a(View view) {
        this.f13222a = (ImageView) view.findViewById(R.id.listitem_home_tag_image);
    }

    public void a(HomeSectionItem homeSectionItem) {
        String str = homeSectionItem.mProductTag;
        if (!TextUtils.isEmpty(str)) {
            PlainUtils.WH a2 = PlainUtils.a(str);
            if (a2 != null) {
                int i = a2.b;
                int i2 = a2.f10034a;
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f13222a.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = new RelativeLayout.LayoutParams(i, i2);
                }
                layoutParams.width = i;
                layoutParams.height = i2;
                this.f13222a.setLayoutParams(layoutParams);
            }
            this.f13222a.setVisibility(0);
            return;
        }
        this.f13222a.setVisibility(8);
    }
}
