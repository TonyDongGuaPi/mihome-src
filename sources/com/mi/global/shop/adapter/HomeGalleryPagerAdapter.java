package com.mi.global.shop.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.shop.R;
import com.mi.global.shop.model.HomeThemeItem;
import com.mi.global.shop.util.HomeThemeItemClick;
import com.mi.global.shop.util.UIAdapter;
import com.mi.global.shop.widget.ResponsiveRelativeLayout;
import com.mi.log.LogUtil;

@Deprecated
public class HomeGalleryPagerAdapter extends BasePageIndicatorAdapter<HomeThemeItem> {
    private ImageView d;
    /* access modifiers changed from: private */
    public Context e;

    public HomeGalleryPagerAdapter(Context context) {
        super(context);
        this.e = context;
    }

    public View a(Context context, HomeThemeItem homeThemeItem, ViewGroup viewGroup) {
        AnonymousClass1 r5 = new ResponsiveRelativeLayout(context) {
            /* access modifiers changed from: protected */
            public Drawable getForegroundDrawable() {
                return getResources().getDrawable(R.drawable.shop_selector_home_staritem);
            }
        };
        int a2 = UIAdapter.a().a(19);
        int a3 = UIAdapter.a().a(20);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.height = a3;
        layoutParams.width = a2;
        r5.setLayoutParams(layoutParams);
        r5.setMinimumHeight(a3);
        this.d = new ImageView(context);
        this.d.setScaleType(ImageView.ScaleType.FIT_XY);
        this.d.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        r5.addView(this.d);
        return r5;
    }

    public void a(View view, int i, final HomeThemeItem homeThemeItem) {
        if (homeThemeItem != null) {
            view.setClickable(true);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    HomeThemeItemClick.a(HomeGalleryPagerAdapter.this.e, homeThemeItem);
                }
            });
            String str = homeThemeItem.mImageUrl;
            if (str != null && !str.startsWith(ConnectionHelper.HTTP_PREFIX)) {
                str = "http:" + str;
            }
            LogUtil.b("HomeGalleryPagerAdapter", "imageUrl:" + str);
        }
    }
}
