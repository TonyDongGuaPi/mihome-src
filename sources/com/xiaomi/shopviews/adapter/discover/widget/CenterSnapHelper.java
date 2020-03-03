package com.xiaomi.shopviews.adapter.discover.widget;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import com.xiaomi.shopviews.adapter.discover.widget.BannerLayoutManager;

public class CenterSnapHelper extends RecyclerView.OnFlingListener {

    /* renamed from: a  reason: collision with root package name */
    RecyclerView f13125a;
    Scroller b;
    /* access modifiers changed from: private */
    public boolean c = false;
    private final RecyclerView.OnScrollListener d = new RecyclerView.OnScrollListener() {

        /* renamed from: a  reason: collision with root package name */
        boolean f13126a = false;

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            BannerLayoutManager bannerLayoutManager = (BannerLayoutManager) recyclerView.getLayoutManager();
            BannerLayoutManager.OnPageChangeListener onPageChangeListener = bannerLayoutManager.m;
            if (onPageChangeListener != null) {
                onPageChangeListener.b(i);
            }
            if (i == 0 && this.f13126a) {
                this.f13126a = false;
                if (!CenterSnapHelper.this.c) {
                    boolean unused = CenterSnapHelper.this.c = true;
                    CenterSnapHelper.this.a(bannerLayoutManager, onPageChangeListener);
                    return;
                }
                boolean unused2 = CenterSnapHelper.this.c = false;
            }
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            if (i != 0 || i2 != 0) {
                this.f13126a = true;
            }
        }
    };

    public boolean onFling(int i, int i2) {
        BannerLayoutManager bannerLayoutManager = (BannerLayoutManager) this.f13125a.getLayoutManager();
        if (bannerLayoutManager == null || this.f13125a.getAdapter() == null) {
            return false;
        }
        if (!bannerLayoutManager.p() && (bannerLayoutManager.j == bannerLayoutManager.j() || bannerLayoutManager.j == bannerLayoutManager.k())) {
            return false;
        }
        int minFlingVelocity = this.f13125a.getMinFlingVelocity();
        this.b.fling(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (bannerLayoutManager.g == 1 && Math.abs(i2) > minFlingVelocity) {
            int n = bannerLayoutManager.n();
            int finalY = (int) ((((float) this.b.getFinalY()) / bannerLayoutManager.l) / bannerLayoutManager.a());
            this.f13125a.smoothScrollToPosition(bannerLayoutManager.f() ? n - finalY : n + finalY);
            return true;
        } else if (bannerLayoutManager.g != 0 || Math.abs(i) <= minFlingVelocity) {
            return true;
        } else {
            int n2 = bannerLayoutManager.n();
            int finalX = (int) ((((float) this.b.getFinalX()) / bannerLayoutManager.l) / bannerLayoutManager.a());
            this.f13125a.smoothScrollToPosition(bannerLayoutManager.f() ? n2 - finalX : n2 + finalX);
            return true;
        }
    }

    public void a(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        if (this.f13125a != recyclerView) {
            if (this.f13125a != null) {
                b();
            }
            this.f13125a = recyclerView;
            if (this.f13125a != null) {
                RecyclerView.LayoutManager layoutManager = this.f13125a.getLayoutManager();
                if (layoutManager instanceof BannerLayoutManager) {
                    a();
                    this.b = new Scroller(this.f13125a.getContext(), new DecelerateInterpolator());
                    BannerLayoutManager bannerLayoutManager = (BannerLayoutManager) layoutManager;
                    a(bannerLayoutManager, bannerLayoutManager.m);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(BannerLayoutManager bannerLayoutManager, BannerLayoutManager.OnPageChangeListener onPageChangeListener) {
        int o = bannerLayoutManager.o();
        if (o == 0) {
            this.c = false;
        } else if (bannerLayoutManager.d() == 1) {
            this.f13125a.smoothScrollBy(0, o);
        } else {
            this.f13125a.smoothScrollBy(o, 0);
        }
        if (onPageChangeListener != null) {
            onPageChangeListener.a(bannerLayoutManager.n());
        }
    }

    /* access modifiers changed from: package-private */
    public void a() throws IllegalStateException {
        if (this.f13125a.getOnFlingListener() == null) {
            this.f13125a.addOnScrollListener(this.d);
            this.f13125a.setOnFlingListener(this);
            return;
        }
        throw new IllegalStateException("An instance of OnFlingListener already set.");
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.f13125a.removeOnScrollListener(this.d);
        this.f13125a.setOnFlingListener((RecyclerView.OnFlingListener) null);
    }
}
