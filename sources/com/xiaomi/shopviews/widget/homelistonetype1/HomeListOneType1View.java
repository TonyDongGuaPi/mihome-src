package com.xiaomi.shopviews.widget.homelistonetype1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.IHomeItemView;
import com.xiaomi.shopviews.utils.HomeProductTagImageHelper;
import com.xiaomi.shopviews.utils.ViewHelperTCPBigPic;
import com.xiaomi.shopviews.widget.R;

public class HomeListOneType1View extends RelativeLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private int f13267a = 1;
    private int b = 1;
    /* access modifiers changed from: private */
    public ImageView c;
    private HomeProductTagImageHelper d;
    private ViewHelperTCPBigPic e;

    /* access modifiers changed from: private */
    public void a(HomeSectionItem homeSectionItem) {
    }

    public void draw(HomeSection homeSection) {
    }

    public HomeListOneType1View(Context context, int i, int i2) {
        super(context);
        if (i > 0) {
            this.b = i;
        }
        if (i2 > 0) {
            this.f13267a = i2;
        }
        a(context, this.b, this.f13267a);
    }

    private void a(Context context, int i, int i2) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.listitem_one_type1, this);
        this.c = (ImageView) findViewById(R.id.iv_list_one_type1_product);
        int e2 = ScreenInfo.a().e();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.c.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(-1, (e2 * i2) / i);
        }
        layoutParams.width = -1;
        layoutParams.height = (e2 * i2) / i;
        this.e = new ViewHelperTCPBigPic(inflate, context);
        this.d = new HomeProductTagImageHelper(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r3 = r3.mBody.mItems.get(0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void bind(com.xiaomi.shopviews.model.HomeSection r3, int r4, int r5) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x003f
            com.xiaomi.shopviews.model.HomeSectionBody r3 = r3.mBody
            java.util.ArrayList<com.xiaomi.shopviews.model.HomeSectionItem> r3 = r3.mItems
            r4 = 0
            java.lang.Object r3 = r3.get(r4)
            com.xiaomi.shopviews.model.HomeSectionItem r3 = (com.xiaomi.shopviews.model.HomeSectionItem) r3
            if (r3 == 0) goto L_0x003f
            android.graphics.drawable.ColorDrawable r4 = new android.graphics.drawable.ColorDrawable
            int r5 = com.xiaomi.shopviews.model.HomeThemeConstant.aH
            r4.<init>(r5)
            com.xiaomi.base.imageloader.Option r5 = new com.xiaomi.base.imageloader.Option
            r5.<init>()
            com.xiaomi.base.imageloader.Option r0 = r5.a((android.graphics.drawable.Drawable) r4)
            r0.b((android.graphics.drawable.Drawable) r4)
            com.xiaomi.base.imageloader.ImageLoaderInterface r4 = com.xiaomi.base.imageloader.ImageLoader.a()
            java.lang.String r0 = r3.mImageUrl
            android.widget.ImageView r1 = r2.c
            r4.a((java.lang.String) r0, (android.widget.ImageView) r1, (com.xiaomi.base.imageloader.Option) r5)
            com.xiaomi.shopviews.widget.homelistonetype1.HomeListOneType1View$1 r4 = new com.xiaomi.shopviews.widget.homelistonetype1.HomeListOneType1View$1
            r4.<init>(r3)
            r2.setOnClickListener(r4)
            com.xiaomi.shopviews.utils.ViewHelperTCPBigPic r4 = r2.e
            r4.a((com.xiaomi.shopviews.model.HomeSectionItem) r3)
            com.xiaomi.shopviews.utils.HomeProductTagImageHelper r4 = r2.d
            r4.a((com.xiaomi.shopviews.model.HomeSectionItem) r3)
        L_0x003f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shopviews.widget.homelistonetype1.HomeListOneType1View.bind(com.xiaomi.shopviews.model.HomeSection, int, int):void");
    }
}
