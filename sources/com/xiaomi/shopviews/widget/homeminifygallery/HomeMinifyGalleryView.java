package com.xiaomi.shopviews.widget.homeminifygallery;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.shopviews.adapter.adapter.UIAdapter;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.IHomeItemView;
import com.xiaomi.shopviews.widget.R;

public class HomeMinifyGalleryView extends LinearLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private HomeMinifyGalleryPagerAdapter f13289a;
    private ViewPager b;

    public void draw(HomeSection homeSection) {
    }

    public HomeMinifyGalleryView(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        inflate(context, R.layout.listitem_home_minify_galleryview, this);
        this.b = (ViewPager) findViewById(R.id.listitem_home_minify_gallery_viewpager);
        this.f13289a = new HomeMinifyGalleryPagerAdapter(context);
        this.b.setAdapter(this.f13289a);
        this.b.setPageMargin(Coder.a(getContext(), 8.0f));
        setMinimumHeight(UIAdapter.a().a(UIAdapter.k));
    }

    public void bind(HomeSection homeSection, int i, int i2) {
        if (homeSection != null && homeSection.mBody != null && homeSection.mBody.mItems != null && homeSection.mBody.mItems.size() > 0) {
            this.f13289a = new HomeMinifyGalleryPagerAdapter(getContext());
            this.b.setAdapter(this.f13289a);
            this.f13289a.a(homeSection.mBody.mItems);
        }
    }
}
