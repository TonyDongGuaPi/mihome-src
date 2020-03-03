package com.xiaomi.shopviews.adapter;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.tmall.wireless.vaf.virtualview.event.EventData;
import com.tmall.wireless.vaf.virtualview.event.IEventProcessor;
import com.xiaomi.shopviews.WidgetApplication;
import com.xiaomi.shopviews.adapter.advertisement.HomeAdvertiseGalleryProvider;
import com.xiaomi.shopviews.adapter.advertisement.HomeAdvertiseHeadImageViewProvider;
import com.xiaomi.shopviews.adapter.advertisement.HomeAdvertiseThreeImageViewProvider;
import com.xiaomi.shopviews.adapter.advertisement.HomeCommentProvider;
import com.xiaomi.shopviews.adapter.bigvision.HomeBigVisionViewProvider;
import com.xiaomi.shopviews.adapter.contentshow.HomeContentShowViewProvider1;
import com.xiaomi.shopviews.adapter.contentshow.HomeContentShowViewProvider2;
import com.xiaomi.shopviews.adapter.countdown.HomeCountDownViewCfgProvider;
import com.xiaomi.shopviews.adapter.countdown.HomeCountDownViewProvider;
import com.xiaomi.shopviews.adapter.crowdfunding.HomeCrowdfundingMultipleViewProvider;
import com.xiaomi.shopviews.adapter.crowdfunding.HomeCrowdfundingSingleViewProvider;
import com.xiaomi.shopviews.adapter.discover.provider.DiscoverArticleProvider;
import com.xiaomi.shopviews.adapter.discover.provider.DiscoverEntranceProvider;
import com.xiaomi.shopviews.adapter.discover.provider.DiscoverGalleryProvider;
import com.xiaomi.shopviews.adapter.discover.provider.DiscoverImagesProvider;
import com.xiaomi.shopviews.adapter.discover.provider.DiscoverVideoProvider;
import com.xiaomi.shopviews.adapter.empty.HomeEmptyProvider;
import com.xiaomi.shopviews.adapter.gallery.HomeGalleryProvider;
import com.xiaomi.shopviews.adapter.productshow.HomeDiscoverProvider;
import com.xiaomi.shopviews.adapter.productshow.HomeGoldenOilViewProvider;
import com.xiaomi.shopviews.adapter.productshow.HomeHotAccessoriesViewProvider;
import com.xiaomi.shopviews.adapter.productshow.HomeProductShowViewProvider1;
import com.xiaomi.shopviews.adapter.productshow.HomeProductShowViewProvider3;
import com.xiaomi.shopviews.adapter.quickenter.HomeQuickEnterViewProvider;
import com.xiaomi.shopviews.adapter.recommend.HomeRecommendProvider;
import com.xiaomi.shopviews.adapter.title.HomeTitleProvider;
import com.xiaomi.shopviews.adapter.virtualview.VirtualViewProvider;
import com.xiaomi.shopviews.model.item.HomeItemContentFactory;
import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.widget.view.SimpleLoadMoreView;
import java.util.List;

public class HomeRvAdapter extends MultipleItemRvAdapter<PageDataBean, BaseViewHolder> {
    private static String g = "₹";
    public long b = (System.currentTimeMillis() / 1000);
    public long c = 0;
    private HomeGalleryProvider d;
    private HomeRecommendProvider e;
    /* access modifiers changed from: private */
    public ProviderClickListener f;
    /* access modifiers changed from: private */
    public CountDownTimer h;

    public HomeRvAdapter(List<PageDataBean> list) {
        super(list);
        a();
        M();
        a((LoadMoreView) new SimpleLoadMoreView());
    }

    public HomeRvAdapter(List<PageDataBean> list, ProviderClickListener providerClickListener) {
        super(list);
        this.f = providerClickListener;
        a();
        M();
        e();
        a((LoadMoreView) new SimpleLoadMoreView());
    }

    private void e() {
        WidgetApplication.a().e().a(0, (IEventProcessor) new IEventProcessor() {
            public boolean a(EventData eventData) {
                Log.d("ClickProcessorImpl", "event " + eventData.b);
                if (HomeRvAdapter.this.f == null || eventData.b == null) {
                    return true;
                }
                HomeRvAdapter.this.f.a(eventData.b.i(), HomeItemContentFactory.J, "", "");
                return true;
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int d(PageDataBean pageDataBean) {
        return HomeItemContentFactory.a(pageDataBean);
    }

    public void b() {
        this.d = new HomeGalleryProvider(this.f);
        this.e = new HomeRecommendProvider(this.f, this);
        this.f5131a.a(this.d);
        this.f5131a.a(this.e);
        this.f5131a.a(new HomeQuickEnterViewProvider());
        this.f5131a.a(new HomeBigVisionViewProvider());
        this.f5131a.a(new HomeCountDownViewProvider(this.f, this));
        this.f5131a.a(new HomeCountDownViewCfgProvider(this.f, this));
        this.f5131a.a(new HomeTitleProvider());
        this.f5131a.a(new HomeCrowdfundingSingleViewProvider());
        this.f5131a.a(new HomeCrowdfundingMultipleViewProvider());
        this.f5131a.a(new HomeAdvertiseThreeImageViewProvider(this.f));
        this.f5131a.a(new HomeAdvertiseGalleryProvider(this.f));
        this.f5131a.a(new HomeAdvertiseHeadImageViewProvider());
        this.f5131a.a(new HomeCommentProvider(this.f));
        this.f5131a.a(new HomeContentShowViewProvider1());
        this.f5131a.a(new HomeContentShowViewProvider2());
        this.f5131a.a(new HomeProductShowViewProvider1(this.f));
        this.f5131a.a(new HomeProductShowViewProvider3());
        this.f5131a.a(new HomeGoldenOilViewProvider(this.f));
        this.f5131a.a(new HomeHotAccessoriesViewProvider());
        this.f5131a.a(new HomeDiscoverProvider(this.f));
        this.f5131a.a(new HomeEmptyProvider());
        this.f5131a.a(new DiscoverGalleryProvider(this.f));
        this.f5131a.a(new DiscoverEntranceProvider(this.f));
        this.f5131a.a(new DiscoverArticleProvider(this.f));
        this.f5131a.a(new DiscoverImagesProvider(this.f));
        this.f5131a.a(new DiscoverVideoProvider(this.f));
        this.f5131a.a(new VirtualViewProvider());
    }

    public static String c() {
        return g;
    }

    public static void a(String str) {
        g = str;
    }

    public HomeGalleryProvider d() {
        return this.d;
    }

    private void M() {
        if (this.s != null) {
            for (PageDataBean pageDataBean : this.s) {
                if (HomeItemContentFactory.n.equals(pageDataBean.b) || HomeItemContentFactory.p.equals(pageDataBean.b)) {
                    b(pageDataBean);
                }
            }
        }
    }

    private void b(PageDataBean pageDataBean) {
        if (pageDataBean.n != 0) {
            this.b = pageDataBean.n;
        }
        if (pageDataBean.y > 0) {
            if (pageDataBean.y > this.c) {
                this.c = pageDataBean.y;
            }
        } else if (!TextUtils.isEmpty(pageDataBean.e) && TextUtils.isDigitsOnly(pageDataBean.e)) {
            long parseLong = Long.parseLong(pageDataBean.e);
            if (parseLong > this.c) {
                this.c = parseLong;
            }
        }
        if (this.h != null) {
            this.h.cancel();
            this.h = null;
        }
        this.h = new CountDownTimer(2147483647L, 1000) {
            public void onFinish() {
            }

            public void onTick(long j) {
                HomeRvAdapter.this.b++;
                if (HomeRvAdapter.this.h != null && HomeRvAdapter.this.b > HomeRvAdapter.this.c) {
                    HomeRvAdapter.this.h.cancel();
                    CountDownTimer unused = HomeRvAdapter.this.h = null;
                }
            }
        }.start();
    }
}
