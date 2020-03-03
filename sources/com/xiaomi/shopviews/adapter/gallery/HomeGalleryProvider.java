package com.xiaomi.shopviews.adapter.gallery;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.shopviews.WidgetApplication;
import com.xiaomi.shopviews.adapter.HomeItemProvider;
import com.xiaomi.shopviews.adapter.ProviderClickListener;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.model.item.PageDataBeanExtend;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.customfont.CustomTextView;
import com.xiaomi.shopviews.widget.view.BGABanner;
import java.util.List;

public class HomeGalleryProvider extends HomeItemProvider<PageDataBean, BaseViewHolder> {
    private BGABanner.Delegate<ImageView, PageDataBean.AssemblyInfoBean> c;
    /* access modifiers changed from: private */
    public ProviderClickListener d;

    public int a() {
        return 1;
    }

    public void a(BGABanner.Delegate<ImageView, PageDataBean.AssemblyInfoBean> delegate) {
        this.c = delegate;
    }

    public HomeGalleryProvider(ProviderClickListener providerClickListener) {
        this.d = providerClickListener;
    }

    /* access modifiers changed from: private */
    public void onClick(String str, PageDataBean.AssemblyInfoBean assemblyInfoBean) {
        if (this.d != null) {
            this.d.a(str, assemblyInfoBean, "");
        }
    }

    /* access modifiers changed from: private */
    public void onClick(String str, PageDataBeanExtend.NavList navList) {
        if (this.d != null) {
            this.d.a(str, navList, "");
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, PageDataBean.AssemblyInfoBean assemblyInfoBean) {
        if (this.d != null) {
            this.d.a(str, assemblyInfoBean);
        }
    }

    private void a(String str, PageDataBeanExtend.NavList navList, String str2) {
        if (this.d != null) {
            this.d.b(str, navList, str2);
        }
    }

    public int b() {
        return R.layout.widget_item_banner;
    }

    public void a(BaseViewHolder baseViewHolder, final PageDataBean pageDataBean, int i) {
        boolean z;
        PageDataBeanExtend pageDataBeanExtend;
        View view = baseViewHolder.itemView;
        final Option option = new Option();
        option.b(R.drawable.default_pic_small_inverse);
        BGABanner bGABanner = (BGABanner) view.findViewById(R.id.banner);
        if (pageDataBean.q != 0) {
            bGABanner.setAutoPlayInterval(pageDataBean.q);
        }
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.layout_quick_enter_container);
        int i2 = Resources.getSystem().getDisplayMetrics().widthPixels;
        if (i2 != 0) {
            int i3 = (i2 * 71) / 72;
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) bGABanner.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = i3;
            bGABanner.setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) frameLayout.getLayoutParams();
            layoutParams2.topMargin = i3 - Coder.a((Context) WidgetApplication.f13060a, 62.5f);
            frameLayout.setLayoutParams(layoutParams2);
        }
        bGABanner.setAdapter(new BGABanner.Adapter<ImageView, PageDataBean.AssemblyInfoBean>() {
            public void a(BGABanner bGABanner, ImageView imageView, @Nullable final PageDataBean.AssemblyInfoBean assemblyInfoBean, final int i) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                ImageLoader.a().a(assemblyInfoBean.b, imageView, option);
                HomeGalleryProvider.this.a(pageDataBean.b, assemblyInfoBean);
                if (TextUtils.isEmpty(Build.MODEL) || !Build.MODEL.toLowerCase().contains("vivo") || !Build.MODEL.toLowerCase().contains("x9")) {
                    Glide.c(HomeGalleryProvider.this.f5143a).j().a(assemblyInfoBean.b).a(new SimpleTarget<Bitmap>() {
                        public void a(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                            if (i == 0 && assemblyInfoBean.F == -1 && HomeGalleryProvider.this.d != null) {
                                assemblyInfoBean.F = HomeGalleryProvider.a(bitmap);
                                HomeGalleryProvider.this.d.a(pageDataBean.v.get(i).F, i);
                            }
                            if (assemblyInfoBean.F == -1) {
                                assemblyInfoBean.F = HomeGalleryProvider.a(bitmap);
                            }
                        }
                    });
                }
            }
        });
        if (pageDataBean.v == null || pageDataBean.v.size() != 1) {
            bGABanner.setAutoPlayAble(true);
        } else {
            bGABanner.setAutoPlayAble(false);
        }
        String str = null;
        bGABanner.setData(pageDataBean.v, (List<String>) null);
        bGABanner.setDelegate(new BGABanner.Delegate() {
            public void a(BGABanner bGABanner, View view, @Nullable Object obj, int i) {
                HomeGalleryProvider.this.onClick(pageDataBean.b, (PageDataBean.AssemblyInfoBean) obj);
            }
        });
        bGABanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (HomeGalleryProvider.this.d != null) {
                    HomeGalleryProvider.this.d.a(pageDataBean.v.get(i).F, i);
                }
            }
        });
        if (pageDataBean.v != null && pageDataBean.v.size() > 0) {
            str = pageDataBean.v.get(0).D;
        }
        if (TextUtils.isEmpty(pageDataBean.f) || (pageDataBeanExtend = (PageDataBeanExtend) new Gson().fromJson(pageDataBean.f, PageDataBeanExtend.class)) == null) {
            z = false;
        } else {
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_bg);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            if (!TextUtils.isEmpty(pageDataBeanExtend.a())) {
                ImageLoader.a().a(pageDataBeanExtend.a(), imageView, new Option());
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(4);
            }
            final List<PageDataBeanExtend.NavList> b = pageDataBeanExtend.b();
            if (b == null || b.size() <= 0) {
                z = false;
            } else {
                LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.layout_contain_one);
                ImageLoader.a().a(b.get(0).b, (ImageView) view.findViewById(R.id.product_image_one), option);
                ((CustomTextView) view.findViewById(R.id.product_name_one)).setText(b.get(0).d);
                if (!TextUtils.isEmpty(b.get(0).e)) {
                    ((CustomTextView) view.findViewById(R.id.product_name_one)).setTextColor(Color.parseColor(b.get(0).e));
                }
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        HomeGalleryProvider.this.onClick(pageDataBean.b, (PageDataBeanExtend.NavList) b.get(0));
                    }
                });
                a(pageDataBean.b, b.get(0), str);
                z = true;
            }
            if (b.size() > 1) {
                LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.layout_contain_two);
                ImageLoader.a().a(b.get(1).b, (ImageView) view.findViewById(R.id.product_image_two), option);
                ((CustomTextView) view.findViewById(R.id.product_name_two)).setText(b.get(1).d);
                if (!TextUtils.isEmpty(b.get(1).e)) {
                    ((CustomTextView) view.findViewById(R.id.product_name_two)).setTextColor(Color.parseColor(b.get(1).e));
                }
                linearLayout2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        HomeGalleryProvider.this.onClick(pageDataBean.b, (PageDataBeanExtend.NavList) b.get(1));
                    }
                });
                a(pageDataBean.b, b.get(1), str);
            }
            if (b.size() > 2) {
                LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.layout_contain_three);
                ImageLoader.a().a(b.get(2).b, (ImageView) view.findViewById(R.id.product_image_three), option);
                ((CustomTextView) view.findViewById(R.id.product_name_three)).setText(b.get(2).d);
                if (!TextUtils.isEmpty(b.get(2).e)) {
                    ((CustomTextView) view.findViewById(R.id.product_name_three)).setTextColor(Color.parseColor(b.get(2).e));
                }
                linearLayout3.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        HomeGalleryProvider.this.onClick(pageDataBean.b, (PageDataBeanExtend.NavList) b.get(2));
                    }
                });
                a(pageDataBean.b, b.get(2), str);
            }
            if (b.size() > 3) {
                LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.layout_contain_four);
                ImageLoader.a().a(b.get(3).b, (ImageView) view.findViewById(R.id.product_image_four), option);
                ((CustomTextView) view.findViewById(R.id.product_name_four)).setText(b.get(3).d);
                if (!TextUtils.isEmpty(b.get(3).e)) {
                    ((CustomTextView) view.findViewById(R.id.product_name_four)).setTextColor(Color.parseColor(b.get(3).e));
                }
                linearLayout4.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        HomeGalleryProvider.this.onClick(pageDataBean.b, (PageDataBeanExtend.NavList) b.get(3));
                    }
                });
                a(pageDataBean.b, b.get(3), str);
            }
        }
        if (z) {
            frameLayout.setVisibility(0);
            return;
        }
        bGABanner.setPointTopBottomMargin(Coder.a(bGABanner.getContext(), 20.0f));
        frameLayout.setVisibility(8);
        ((RelativeLayout.LayoutParams) bGABanner.getLayoutParams()).bottomMargin = 0;
        RecyclerView.LayoutParams layoutParams3 = (RecyclerView.LayoutParams) view.getLayoutParams();
        layoutParams3.bottomMargin = Coder.a(bGABanner.getContext(), 35.0f);
        view.setLayoutParams(layoutParams3);
    }

    public void onClick(BaseViewHolder baseViewHolder, PageDataBean pageDataBean, int i) {
        super.onClick(baseViewHolder, pageDataBean, i);
    }

    public static int a(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap2);
        Canvas canvas = new Canvas(bitmap2);
        bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
        bitmapDrawable.draw(canvas);
        int intrinsicWidth = bitmapDrawable.getIntrinsicWidth();
        int[] iArr = {0, 4, 9, 13, 18, 23, 28, 33, 38, 43, 48, 53, 58, 63, 68, 73, 78, 83, 88, 93, 98, 103};
        int[] iArr2 = {0, intrinsicWidth / 8, (intrinsicWidth * 2) / 8, (intrinsicWidth * 3) / 8, (intrinsicWidth * 4) / 8, (intrinsicWidth * 5) / 8, (intrinsicWidth * 6) / 8, (intrinsicWidth * 7) / 8, intrinsicWidth - 1};
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        double d2 = 0.0d;
        int i = 0;
        int i2 = 0;
        while (i < iArr2.length) {
            double d3 = d2;
            for (int i3 = 0; i3 < iArr.length; i3++) {
                if (iArr2[i] < width && iArr[i3] < height) {
                    i2++;
                    Integer valueOf = Integer.valueOf(bitmap2.getPixel(iArr2[i], iArr[i3]));
                    double intValue = (double) (((valueOf.intValue() | com.libra.Color.j) >> 16) & 255);
                    Double.isNaN(intValue);
                    double intValue2 = (double) (((valueOf.intValue() | com.libra.Color.k) >> 8) & 255);
                    Double.isNaN(intValue2);
                    double d4 = d3 + (intValue * 0.299d) + (intValue2 * 0.587d);
                    double intValue3 = (double) ((valueOf.intValue() | -256) & 255);
                    Double.isNaN(intValue3);
                    d3 = d4 + (intValue3 * 0.114d);
                }
            }
            i++;
            d2 = d3;
        }
        double d5 = (double) i2;
        Double.isNaN(d5);
        return ((double) ((int) (d2 / d5))) >= 128.0d ? 1 : 0;
    }
}
