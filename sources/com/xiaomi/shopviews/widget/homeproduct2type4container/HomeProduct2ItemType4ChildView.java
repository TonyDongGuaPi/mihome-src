package com.xiaomi.shopviews.widget.homeproduct2type4container;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.base.utils.ImageAdapUtil;
import com.xiaomi.base.utils.PlainUtils;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.HomeThemeConstant;
import com.xiaomi.shopviews.utils.ViewHelperTPSmallPic;
import com.xiaomi.shopviews.widget.R;

public class HomeProduct2ItemType4ChildView extends RelativeLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ImageView f13312a;
    private ViewHelperTPSmallPic b;

    /* access modifiers changed from: private */
    public void a(HomeSectionItem homeSectionItem) {
    }

    public HomeProduct2ItemType4ChildView(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.listitem_home_product2view_type5, this);
        this.f13312a = (ImageView) findViewById(R.id.listitem_home_product2view_type5_image);
        ImageAdapUtil.ImageInfo a2 = ImageAdapUtil.a(ScreenInfo.a().e(), 537, 303, 1080);
        int i = a2.b;
        int i2 = a2.f10029a;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f13312a.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(i, i2);
            layoutParams.addRule(14);
        }
        layoutParams.width = i;
        layoutParams.height = i2;
        this.f13312a.setLayoutParams(layoutParams);
        this.b = new ViewHelperTPSmallPic(this);
    }

    public void bind(final HomeSectionItem homeSectionItem) {
        ColorDrawable colorDrawable = new ColorDrawable(HomeThemeConstant.aH);
        new Option().a((Drawable) colorDrawable).b((Drawable) colorDrawable);
        ImageLoader.a().a(homeSectionItem.mImageUrl, this.f13312a);
        if (this.b != null) {
            this.b.a(homeSectionItem);
        }
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PlainUtils.a(homeSectionItem.mAction.mPath, (Activity) HomeProduct2ItemType4ChildView.this.getContext(), HomeProduct2ItemType4ChildView.this.f13312a, HomeProduct2ItemType4ChildView.this.f13312a.getDrawable(), homeSectionItem.mImageUrl);
                HomeProduct2ItemType4ChildView.this.a(homeSectionItem);
            }
        });
    }
}
