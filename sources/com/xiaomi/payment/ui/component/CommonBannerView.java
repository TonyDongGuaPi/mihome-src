package com.xiaomi.payment.ui.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import com.mibi.common.component.ViewPagerEx;
import com.mibi.common.data.Image;
import com.xiaomi.payment.platform.R;
import java.util.ArrayList;
import miuipub.view.PagerAdapter;
import miuipub.view.ViewPager;

public abstract class CommonBannerView<Type> extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int f12452a = 3000;
    /* access modifiers changed from: private */
    public ViewPagerEx b;
    /* access modifiers changed from: private */
    public ViewPagerIndicatorBar c;
    /* access modifiers changed from: private */
    public Drawable d;
    private int e = 3000;
    /* access modifiers changed from: private */
    public CommonBannerView<Type>.CommonBannerPagerAdapter f;
    private Handler g = new Handler();
    private Runnable h = new Runnable() {
        public void run() {
            CommonBannerView.this.b.setCurrentItem((CommonBannerView.this.b.getCurrentItem() + 1) % CommonBannerView.this.f.a());
        }
    };
    private ViewPager.OnPageChangeListener i = new ViewPager.OnPageChangeListener() {
        public void a(int i, float f, int i2) {
        }

        public void a(int i) {
            CommonBannerView.this.c.setSelected(i);
        }

        public void b(int i) {
            if (i == 1) {
                CommonBannerView.this.b();
            } else if (i == 0) {
                CommonBannerView.this.a();
            }
        }
    };
    protected ArrayList<Type> mBannerInfoList;

    /* access modifiers changed from: protected */
    public abstract String getBannerItemImageUrl(Type type);

    /* access modifiers changed from: protected */
    public abstract void onBannerItemClick(View view, Type type);

    public CommonBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = context.getResources().getDrawable(R.drawable.mibi_banner_image_default);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.b = (ViewPagerEx) findViewById(R.id.viewpager);
        this.c = (ViewPagerIndicatorBar) findViewById(R.id.indicatorBar);
    }

    public void bindBannerInfo(ArrayList<Type> arrayList) {
        this.mBannerInfoList = arrayList;
        if (arrayList == null || arrayList.size() <= 0) {
            setVisibility(8);
            return;
        }
        if (arrayList.size() == 1) {
            this.c.setVisibility(8);
        } else {
            this.c.setVisibility(0);
            this.c.setIndicatorNum(arrayList.size());
        }
        this.b.setOnPageChangeListener(this.i);
        if (this.f == null) {
            this.f = new CommonBannerPagerAdapter();
        }
        this.b.setAdapter(this.f);
        this.f.a(arrayList);
        b();
        a();
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.f.a() > 1) {
            this.g.postDelayed(this.h, (long) this.e);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.f.a() > 1) {
            this.g.removeCallbacks(this.h);
        }
    }

    protected class CommonBannerPagerAdapter extends PagerAdapter {

        /* renamed from: a  reason: collision with root package name */
        protected ArrayList<Type> f12455a;
        private SparseArray<ImageSwitcher> e = new SparseArray<>();
        private SparseArray<ImageView> f = new SparseArray<>();

        public boolean a(int i) {
            return false;
        }

        public boolean a(View view, Object obj) {
            return view == obj;
        }

        protected CommonBannerPagerAdapter() {
        }

        public void a(ArrayList<Type> arrayList) {
            this.f12455a = arrayList;
            if (this.f12455a != null) {
                d();
            }
        }

        public int a() {
            if (this.f12455a != null) {
                return this.f12455a.size();
            }
            return 0;
        }

        public Object a(ViewGroup viewGroup, final int i) {
            final ImageSwitcher imageSwitcher = this.e.get(i);
            if (imageSwitcher == null) {
                imageSwitcher = (ImageSwitcher) LayoutInflater.from(CommonBannerView.this.getContext()).inflate(R.layout.mibi_home_grid_banner_image, viewGroup, false);
            }
            ((ImageView) imageSwitcher.getCurrentView()).setImageDrawable(CommonBannerView.this.d);
            final String bannerItemImageUrl = CommonBannerView.this.getBannerItemImageUrl(this.f12455a.get(i));
            imageSwitcher.post(new Runnable() {
                public void run() {
                    CommonBannerPagerAdapter.this.a(bannerItemImageUrl, imageSwitcher);
                }
            });
            imageSwitcher.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (CommonBannerPagerAdapter.this.f12455a != null && i < CommonBannerPagerAdapter.this.f12455a.size()) {
                        CommonBannerView.this.onBannerItemClick(view, CommonBannerPagerAdapter.this.f12455a.get(i));
                    }
                }
            });
            this.e.put(i, imageSwitcher);
            viewGroup.addView(imageSwitcher);
            return imageSwitcher;
        }

        public void a(ViewGroup viewGroup, int i, Object obj) {
            if (obj instanceof View) {
                this.f.remove(i);
                viewGroup.removeView((View) obj);
            }
        }

        /* access modifiers changed from: private */
        public void a(String str, final ImageSwitcher imageSwitcher) {
            int width = CommonBannerView.this.getWidth();
            int height = CommonBannerView.this.getHeight();
            if (!TextUtils.isEmpty(str)) {
                Image.a(CommonBannerView.this.getContext()).a(str, Image.ThumbnailFormat.a(width, height, 2)).a((Image.LoadProcessCallBack) new Image.LoadProcessCallBack() {
                    public void a(String str) {
                    }

                    public void a() {
                        imageSwitcher.setImageDrawable(CommonBannerView.this.d);
                    }

                    public void a(Drawable drawable) {
                        imageSwitcher.setImageDrawable(drawable);
                    }
                });
            }
        }
    }
}
