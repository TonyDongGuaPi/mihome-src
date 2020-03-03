package com.xiaomi.shopviews.widget.homelisttwotype7;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.HomeThemeConstant;
import com.xiaomi.shopviews.widget.R;

public class HomePicContentProgressView extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private ImageView f13270a;
    private RelativeLayout b;
    private ProgressBar c;
    private TextView d;
    private TextView e;

    /* access modifiers changed from: private */
    public void a(HomeSectionItem homeSectionItem) {
    }

    public HomePicContentProgressView(Context context, int i, int i2) {
        super(context);
        a(context, i, i2);
    }

    private void a(Context context, int i, int i2) {
        LayoutInflater.from(context).inflate(R.layout.listitem_home_pic_content_progress, this);
        this.b = (RelativeLayout) findViewById(R.id.layout_home_list_two_type7_img);
        this.f13270a = (ImageView) findViewById(R.id.iv_home_list_two_type7);
        this.e = (TextView) findViewById(R.id.tv_home_list_two_type7_title);
        this.d = (TextView) findViewById(R.id.tv_home_list_two_type7_subTitle);
        this.c = (ProgressBar) findViewById(R.id.pb_home_list_two_type7);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.b.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(-1, i);
        }
        layoutParams.height = i2;
        this.b.setLayoutParams(layoutParams);
    }

    public void bind(final HomeSectionItem homeSectionItem) {
        if (homeSectionItem != null) {
            ColorDrawable colorDrawable = new ColorDrawable(HomeThemeConstant.aH);
            Option option = new Option();
            option.a((Drawable) colorDrawable).b((Drawable) colorDrawable);
            ImageLoader.a().a(homeSectionItem.mImageUrl, this.f13270a, option);
            this.e.setText(!TextUtils.isEmpty(homeSectionItem.mTitle) ? homeSectionItem.mTitle : "");
            this.d.setText(homeSectionItem.mSubTitle);
            this.c.setProgress(homeSectionItem.mProgress);
        }
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomePicContentProgressView.this.a(homeSectionItem);
            }
        });
    }
}
