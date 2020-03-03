package com.xiaomi.shopviews.widget.homehorizontaltab;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.adapter.adapter.BasePageAdapter;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.HomeThemeConstant;
import com.xiaomi.shopviews.utils.HomeProductTagImageHelper;
import com.xiaomi.shopviews.utils.ViewHelperTCPBigPic;
import com.xiaomi.shopviews.widget.R;

public class HomeTabPagerAdapter extends BasePageAdapter<HomeSectionItem> {
    public static final int b = 500;
    private boolean c = false;
    private int d = 1;
    private int e = 2;
    private ImageView f;
    private Context g;
    private HomeProductTagImageHelper h;
    private ViewHelperTCPBigPic i;

    /* access modifiers changed from: private */
    public void a(HomeSectionItem homeSectionItem) {
    }

    public HomeTabPagerAdapter(Context context, boolean z) {
        super(context);
        this.g = context;
        this.c = z;
    }

    public void a(View view, int i2, HomeSectionItem homeSectionItem) {
        if (homeSectionItem != null) {
            ColorDrawable colorDrawable = new ColorDrawable(HomeThemeConstant.aH);
            ImageLoader.a().a(homeSectionItem.mImageUrl, this.f, new Option().a((Drawable) colorDrawable).b((Drawable) colorDrawable));
            if (this.c) {
                this.i.a().setVisibility(8);
                return;
            }
            this.i.a().setVisibility(0);
            this.i.a(homeSectionItem);
            this.h.a(homeSectionItem);
        }
    }

    public int getCount() {
        if (this.f13068a.size() != 1) {
            return this.f13068a.size() == 0 ? 0 : 500;
        }
        return 1;
    }

    /* renamed from: b */
    public HomeSectionItem a(int i2) {
        return (HomeSectionItem) super.a(c(i2));
    }

    public int getItemPosition(Object obj) {
        if (getCount() > 0) {
            return this.f13068a.contains(((View) obj).getTag()) ? -1 : -2;
        }
        return super.getItemPosition(obj);
    }

    public CharSequence getPageTitle(int i2) {
        return ((HomeSectionItem) this.f13068a.get(i2)).mTitle;
    }

    public int c(int i2) {
        return i2 % this.f13068a.size();
    }

    public int b() {
        if (this.f13068a != null) {
            return this.f13068a.size();
        }
        return 0;
    }

    public View a(Context context, final HomeSectionItem homeSectionItem, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.listitem_one_type1, (ViewGroup) null);
        this.f = (ImageView) inflate.findViewById(R.id.iv_list_one_type1_product);
        ScreenInfo.a().e();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
        layoutParams.width = Coder.a(context, 300.0f);
        layoutParams.height = (Coder.a(context, 300.0f) * this.d) / this.e;
        this.i = new ViewHelperTCPBigPic(inflate, context);
        this.h = new HomeProductTagImageHelper(inflate);
        inflate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeTabPagerAdapter.this.a(homeSectionItem);
            }
        });
        if (this.c) {
            this.i.a().setVisibility(8);
        }
        return inflate;
    }
}
