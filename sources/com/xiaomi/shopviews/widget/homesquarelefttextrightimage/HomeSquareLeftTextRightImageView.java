package com.xiaomi.shopviews.widget.homesquarelefttextrightimage;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.base.utils.PlainUtils;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.HomeThemeConstant;
import com.xiaomi.shopviews.model.IHomeItemView;
import com.xiaomi.shopviews.utils.HomeProductTagImageHelper;
import com.xiaomi.shopviews.utils.PriceRegUtils;
import com.xiaomi.shopviews.widget.R;

public class HomeSquareLeftTextRightImageView extends LinearLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private TextView f13329a;
    private TextView b;
    private HomeProductTagImageHelper c;
    /* access modifiers changed from: private */
    public ImageView d;
    private TextView e;
    private TextView f;
    private TextView g;

    /* access modifiers changed from: private */
    public void a(HomeSectionItem homeSectionItem) {
    }

    public void draw(HomeSection homeSection) {
    }

    public HomeSquareLeftTextRightImageView(Context context) {
        super(context);
        a(context);
    }

    private boolean a(HomeSection homeSection) {
        return homeSection == null || homeSection.mBody == null || homeSection.mBody.mItems == null || homeSection.mBody.mItems.size() == 0;
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.listitem_left_text_right_image, this);
        ImageView imageView = (ImageView) findViewById(R.id.top_image);
        this.d = imageView;
        imageView.setMinimumHeight(ScreenInfo.a().e() / 2);
        this.b = (TextView) findViewById(R.id.bottom_title);
        View findViewById = findViewById(R.id.layout_viewhelper_price);
        this.f = (TextView) findViewById.findViewById(R.id.tv_viewhelper_price);
        this.g = (TextView) findViewById.findViewById(R.id.tv_viewhelper_price_qi);
        this.e = (TextView) findViewById.findViewById(R.id.tv_viewhelper_marketprice);
        this.f13329a = (TextView) findViewById(R.id.bottom_sub_title);
        this.c = new HomeProductTagImageHelper(this);
    }

    public void bind(HomeSection homeSection, int i, int i2) {
        if (a(homeSection)) {
            Option option = new Option();
            option.b(R.drawable.default_pic_large);
            ImageLoader.a().a("", this.d, option);
            return;
        }
        final HomeSectionItem homeSectionItem = homeSection.mBody.mItems.get(0);
        ColorDrawable colorDrawable = new ColorDrawable(HomeThemeConstant.aH);
        Option option2 = new Option();
        option2.a((Drawable) colorDrawable).b((Drawable) colorDrawable);
        ImageLoader.a().a(homeSectionItem.mImageUrl, this.d, option2);
        this.b.setText(homeSectionItem.mProductName);
        if (TextUtils.isEmpty(homeSectionItem.mProductBrief)) {
            this.f13329a.setVisibility(8);
        } else {
            this.f13329a.setVisibility(0);
            this.f13329a.setText(homeSectionItem.mProductBrief);
        }
        PriceRegUtils.b(this.f, this.e, this.g, homeSectionItem);
        this.c.a(homeSectionItem);
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PlainUtils.a(homeSectionItem.mAction.mPath, (Activity) HomeSquareLeftTextRightImageView.this.getContext(), HomeSquareLeftTextRightImageView.this.d, HomeSquareLeftTextRightImageView.this.d.getDrawable(), homeSectionItem.mImageUrl);
                HomeSquareLeftTextRightImageView.this.a(homeSectionItem);
            }
        });
    }
}
