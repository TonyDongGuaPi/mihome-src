package com.mi.global.shop.adapter.home;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.BasePageIndicatorAdapter;
import com.mi.global.shop.newmodel.home.NewHomeBlockInfoItem;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.home.HomeThemeItemClick;

public class HomeGalleryPagerAdapter extends BasePageIndicatorAdapter<NewHomeBlockInfoItem> {
    /* access modifiers changed from: private */
    public Context d;

    public HomeGalleryPagerAdapter(Context context) {
        super(context);
        this.d = context;
    }

    public View a(Context context, NewHomeBlockInfoItem newHomeBlockInfoItem, ViewGroup viewGroup) {
        return LayoutInflater.from(this.d).inflate(R.layout.shop_gallery_image_view, viewGroup, false);
    }

    public void a(View view, int i, final NewHomeBlockInfoItem newHomeBlockInfoItem) {
        if (newHomeBlockInfoItem != null) {
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    HomeThemeItemClick.a(HomeGalleryPagerAdapter.this.d, newHomeBlockInfoItem);
                }
            });
            String imageUrl = newHomeBlockInfoItem.getImageUrl();
            if (!TextUtils.isEmpty(imageUrl)) {
                NewHomeBlockInfoItem newHomeBlockInfoItem2 = (NewHomeBlockInfoItem) view.getTag();
                if (newHomeBlockInfoItem2 == null || !imageUrl.equals(newHomeBlockInfoItem2.getImageUrl())) {
                    if (newHomeBlockInfoItem2 == null) {
                        newHomeBlockInfoItem2 = new NewHomeBlockInfoItem();
                    }
                    newHomeBlockInfoItem2.mImageUrl = imageUrl;
                    view.setTag(newHomeBlockInfoItem2);
                    FrescoUtils.a(imageUrl, (SimpleDraweeView) view);
                }
            }
        }
    }
}
