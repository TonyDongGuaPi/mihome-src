package com.xiaomi.shopviews.widget.homeproductonerow;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeTags;
import com.xiaomi.shopviews.model.IHomeItemView;
import com.xiaomi.shopviews.utils.PriceRegUtils;
import com.xiaomi.shopviews.widget.R;

public class HomeProductOneRowView extends RelativeLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private LinearLayout.LayoutParams f13317a;
    private TextView b;
    private ImageView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private LinearLayout g;

    /* access modifiers changed from: private */
    public void a(HomeSection homeSection) {
    }

    public void draw(HomeSection homeSection) {
    }

    public HomeProductOneRowView(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.f13317a = new LinearLayout.LayoutParams(-2, Coder.a(context, 14.0f));
        inflate(context, R.layout.listitem_home_list_one_row, this);
        this.e = (TextView) findViewById(R.id.listitem_home_listonerow_name);
        this.b = (TextView) findViewById(R.id.listitem_home_listonerow_desc);
        this.f = (TextView) findViewById(R.id.listitem_home_listonerow_price);
        this.d = (TextView) findViewById(R.id.listitem_home_listonerow_marketprice);
        this.c = (ImageView) findViewById(R.id.listitem_home_listonerow_image);
        this.g = (LinearLayout) findViewById(R.id.listitem_home_listonerow_taglayout);
    }

    public void bind(final HomeSection homeSection, int i, int i2) {
        this.e.setText(homeSection.mBody.mProductName);
        this.b.setText(homeSection.mBody.mProductBrief);
        ImageLoader.a().a(homeSection.mBody.mImageUrl, this.c);
        if (PriceRegUtils.a(homeSection.mBody.mProductPrice, homeSection.mBody.mMarketPrice)) {
            this.d.setVisibility(0);
            this.d.setPaintFlags(this.d.getPaintFlags() | 16);
            this.d.setText(homeSection.mBody.mMarketPrice);
        } else {
            this.d.setVisibility(8);
        }
        PriceRegUtils.a(this.f, this.d, homeSection.mBody.mProductPrice);
        if (homeSection.mBody.mTags == null || homeSection.mBody.mTags.size() <= 0) {
            this.g.setVisibility(8);
        } else {
            this.g.setVisibility(0);
            this.g.removeAllViews();
            for (int i3 = 0; i3 < homeSection.mBody.mTags.size(); i3++) {
                HomeTags homeTags = homeSection.mBody.mTags.get(i3);
                TextView textView = new TextView(getContext());
                textView.setText(homeTags.b);
                textView.setGravity(17);
                textView.setTextSize(8.5f);
                textView.setTextColor(-1);
                textView.setBackgroundColor(Color.parseColor(homeTags.f13162a));
                textView.setPadding(20, 0, 20, 0);
                this.f13317a.setMargins(Coder.a(getContext(), 5.0f), 0, 0, 0);
                this.g.addView(textView, this.f13317a);
            }
        }
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeProductOneRowView.this.a(homeSection);
            }
        });
    }
}
