package com.xiaomi.shopviews.widget.homelisttwotypetcp;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nineoldandroids.view.ViewHelper;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.base.utils.PlainUtils;
import com.xiaomi.base.utils.SimpleSpringListener;
import com.xiaomi.base.utils.Spring;
import com.xiaomi.base.utils.SpringListener;
import com.xiaomi.base.utils.SpringUtil;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.HomeThemeConstant;
import com.xiaomi.shopviews.utils.HomeProductTagImageHelper;
import com.xiaomi.shopviews.utils.ViewHelperTCPSmallPic;
import com.xiaomi.shopviews.widget.R;

public class HomePicContentDescVerticalView extends LinearLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ImageView f13272a;
    private RelativeLayout b;
    private Spring c;
    private final ExampleSpringListener d = new ExampleSpringListener();
    private HomeProductTagImageHelper e;
    private TextView f;
    private ViewHelperTCPSmallPic g;

    /* access modifiers changed from: private */
    public void a(HomeSectionItem homeSectionItem) {
    }

    public HomePicContentDescVerticalView(Context context, int i, int i2) {
        super(context);
        a(context, i, i2);
    }

    private void a(Context context, int i, int i2) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.listitem_home_pic_content_desc_vetical, this);
        this.b = (RelativeLayout) findViewById(R.id.layout_home_pic_content_vertical_img);
        this.f13272a = (ImageView) findViewById(R.id.iv_home_pic_content_vertical);
        this.f = (TextView) findViewById(R.id.listitem_home_tag_favordesc);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.b.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LinearLayout.LayoutParams(-1, i2);
        }
        layoutParams.height = i2;
        this.b.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f13272a.getLayoutParams();
        if (layoutParams2 == null) {
            layoutParams2 = new RelativeLayout.LayoutParams(i, i2);
            layoutParams2.addRule(14);
        }
        layoutParams2.width = -1;
        layoutParams2.height = i2;
        this.f13272a.setLayoutParams(layoutParams2);
        this.g = new ViewHelperTCPSmallPic(inflate, context);
        this.e = new HomeProductTagImageHelper(this);
    }

    public void bind(String str, final HomeSectionItem homeSectionItem) {
        if (homeSectionItem != null) {
            ColorDrawable colorDrawable = new ColorDrawable(HomeThemeConstant.aH);
            Option option = new Option();
            option.a((Drawable) colorDrawable).b((Drawable) colorDrawable);
            ImageLoader.a().a(homeSectionItem.mImageUrl, this.f13272a, option);
            if (this.g != null) {
                this.g.a(homeSectionItem);
            }
            this.e.a(homeSectionItem);
            if (!TextUtils.isEmpty(homeSectionItem.mFavorDesc)) {
                if (this.f != null) {
                    this.f.setVisibility(0);
                    this.f.setText(homeSectionItem.mFavorDesc);
                }
            } else if (this.f != null) {
                this.f.setVisibility(4);
            }
        }
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PlainUtils.a(homeSectionItem.mAction.mPath, (Activity) HomePicContentDescVerticalView.this.getContext(), HomePicContentDescVerticalView.this.f13272a, HomePicContentDescVerticalView.this.f13272a.getDrawable(), homeSectionItem.mImageUrl);
                HomePicContentDescVerticalView.this.a(homeSectionItem);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.c != null) {
            this.c.b((SpringListener) this.d);
        }
    }

    private class ExampleSpringListener extends SimpleSpringListener {
        private ExampleSpringListener() {
        }

        public void d(Spring spring) {
            float a2 = (float) SpringUtil.a(spring.c(), 0.0d, 1.0d, 1.0d, 0.88d);
            ViewHelper.setScaleX(HomePicContentDescVerticalView.this.f13272a, a2);
            ViewHelper.setScaleY(HomePicContentDescVerticalView.this.f13272a, a2);
        }
    }
}
