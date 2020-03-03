package com.xiaomi.shopviews.widget.homebanner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.IHomeItemView;
import com.xiaomi.shopviews.widget.R;

public class HomeBannerView extends RelativeLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private int f13240a = 0;
    private ImageView b = null;

    /* access modifiers changed from: private */
    public void b(HomeSection homeSection) {
    }

    public HomeBannerView(Context context) {
        super(context);
        init(context, (AttributeSet) null, 0);
    }

    public HomeBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public HomeBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void a(HomeSection homeSection) {
        if (homeSection.mBody != null && homeSection.mBody.mHeight > 0) {
            if (homeSection.mBody.mHeight > 0) {
                int e = ScreenInfo.a().e();
                int i = (homeSection.mBody.mHeight * e) / 1080;
                if (this.f13240a != i) {
                    AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) getLayoutParams();
                    if (layoutParams == null) {
                        layoutParams = new AbsListView.LayoutParams(e, 0);
                    }
                    layoutParams.height = i;
                    setLayoutParams(layoutParams);
                    setMinimumHeight(i);
                    this.f13240a = i;
                    return;
                }
                return;
            }
            setMinimumHeight(100);
        }
    }

    public void bind(final HomeSection homeSection, int i, int i2) {
        a(homeSection);
        String str = "";
        if (homeSection.mBody != null) {
            str = homeSection.mBody.mImageUrl;
        }
        ImageLoader.a().a(str, this.b);
        setClickable(true);
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeBannerView.this.b(homeSection);
            }
        });
    }

    public void draw(HomeSection homeSection) {
        a(homeSection);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, AttributeSet attributeSet, int i) {
        inflate(context, R.layout.listitem_home_bannerview2, this);
        this.b = (ImageView) findViewById(R.id.listitem_home_banner_image);
    }
}
