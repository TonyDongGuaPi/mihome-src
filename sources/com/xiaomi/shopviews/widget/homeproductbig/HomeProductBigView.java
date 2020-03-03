package com.xiaomi.shopviews.widget.homeproductbig;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.utils.ImageAdapUtil;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.IHomeItemView;
import com.xiaomi.shopviews.utils.PriceRegUtils;
import com.xiaomi.shopviews.widget.R;

public class HomeProductBigView extends LinearLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private TextView f13315a = ((TextView) findViewById(R.id.listitem_home_productbigview_desc));
    private ImageView b = ((ImageView) findViewById(R.id.listitem_home_productbigview_image));
    private TextView c = ((TextView) findViewById(R.id.listitem_home_productbigview_price));
    private TextView d = ((TextView) findViewById(R.id.listitem_home_productbigview_title));

    /* access modifiers changed from: private */
    public void a(HomeSection homeSection) {
    }

    public HomeProductBigView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.listitem_home_product_big, this);
        setOrientation(1);
    }

    public void bind(final HomeSection homeSection, int i, int i2) {
        this.d.setText(homeSection.mBody.mProductName);
        PriceRegUtils.a(this.c, homeSection.mBody.mProductPrice);
        this.f13315a.setText(homeSection.mBody.mProductBrief);
        ImageLoader.a().a(homeSection.mBody.mImageUrl, this.b);
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeProductBigView.this.a(homeSection);
            }
        });
    }

    public void draw(HomeSection homeSection) {
        ImageAdapUtil.ImageInfo a2 = ImageAdapUtil.a(ScreenInfo.a().e(), 1080, 648, 1080);
        int i = a2.b;
        int i2 = a2.f10029a;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.b.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LinearLayout.LayoutParams(i, i2);
            layoutParams.gravity = 1;
        }
        layoutParams.width = i;
        layoutParams.height = i2;
        this.b.setLayoutParams(layoutParams);
    }
}
