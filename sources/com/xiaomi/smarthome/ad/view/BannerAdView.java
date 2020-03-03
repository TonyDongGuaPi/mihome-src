package com.xiaomi.smarthome.ad.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.xiaomi.router.miio.miioplugin.IPluginCallback3;
import com.xiaomi.router.miio.miioplugin.PluginServiceManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.ad.api.AdPosition;
import com.xiaomi.smarthome.ad.api.Advertisement;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import java.util.ArrayList;
import java.util.List;

public class BannerAdView extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private ViewPager f13642a;
    private ImageView b;
    private ImageView c;
    private ViewGroup d;
    /* access modifiers changed from: private */
    public List<ImageView> e;
    private List<ImageView> f;
    /* access modifiers changed from: private */
    public List<Advertisement> g;
    private PagerAdapter h = new PagerAdapter() {
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public int getCount() {
            return BannerAdView.this.g.size();
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) BannerAdView.this.e.get(i % BannerAdView.this.e.size()));
        }

        public Object instantiateItem(ViewGroup viewGroup, final int i) {
            int size = i % BannerAdView.this.e.size();
            if (((ImageView) BannerAdView.this.e.get(size)).getParent() != null) {
                ((ViewGroup) ((ImageView) BannerAdView.this.e.get(size)).getParent()).removeView((View) BannerAdView.this.e.get(size));
            }
            viewGroup.addView((View) BannerAdView.this.e.get(size));
            BannerAdView.this.a(size, (ImageView) BannerAdView.this.e.get(size));
            ((ImageView) BannerAdView.this.e.get(size)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PluginAdUtil.a();
                    FrameManager.b().k().loadWebView(((Advertisement) BannerAdView.this.g.get(i)).f(), "");
                }
            });
            return BannerAdView.this.e.get(size);
        }
    };

    public BannerAdView(Context context) {
        super(context);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.view_ad_banner, this, true);
        setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        this.f13642a = (ViewPager) findViewById(R.id.view_pager);
        this.b = (ImageView) findViewById(R.id.single_image);
        this.c = (ImageView) findViewById(R.id.ad_banner_close);
        this.d = (ViewGroup) findViewById(R.id.dots_parent);
    }

    public void setBannerAd(AdPosition adPosition) {
        this.g = adPosition.b();
        if (this.g.size() == 1) {
            this.f13642a.setVisibility(8);
            a(0, this.b);
            this.c.setVisibility(0);
            this.b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FrameManager.b().k().loadWebView(((Advertisement) BannerAdView.this.g.get(0)).f(), "");
                    PluginAdUtil.a();
                }
            });
        } else {
            this.b.setVisibility(8);
            this.c.setVisibility(0);
            a(this.g.size());
            b(this.g.size());
            this.f13642a.setAdapter(this.h);
            setDot(0);
            this.f13642a.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                public void onPageScrollStateChanged(int i) {
                }

                public void onPageScrolled(int i, float f, int i2) {
                }

                public void onPageSelected(int i) {
                    BannerAdView.this.setDot(i % BannerAdView.this.e.size());
                }
            });
        }
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ViewGroup viewGroup = (ViewGroup) BannerAdView.this.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(BannerAdView.this);
                }
                for (Advertisement b : BannerAdView.this.g) {
                    PluginAdUtil.b(b);
                }
            }
        });
    }

    private void a(int i) {
        this.e = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            this.e.add(imageView);
        }
    }

    private void b(int i) {
        this.f = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageDrawable(getContext().getApplicationContext().getResources().getDrawable(R.drawable.banner_bottom_dot_unselected));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins((int) CommonUtils.a(getContext(), 2.0f), 0, (int) CommonUtils.a(getContext(), 2.0f), 0);
            imageView.setLayoutParams(layoutParams);
            this.f.add(imageView);
            this.d.addView(imageView);
        }
    }

    /* access modifiers changed from: private */
    public void setDot(int i) {
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            if (i2 == i) {
                this.f.get(i2).setImageDrawable(getContext().getApplicationContext().getResources().getDrawable(R.drawable.banner_bottom_dot_focused));
            } else {
                this.f.get(i2).setImageDrawable(getContext().getApplicationContext().getResources().getDrawable(R.drawable.banner_bottom_dot_unselected));
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(int i, final ImageView imageView) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().loadBitmap(this.g.get(i).e(), new IPluginCallback3.Stub() {
                    public void onFailed() throws RemoteException {
                    }

                    public void onSuccess(final Bitmap bitmap) throws RemoteException {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                    }
                });
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
    }
}
