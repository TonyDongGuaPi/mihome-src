package com.xiaomi.shopviews.widget.homehorizontaltab;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.IHomeItemView;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.homehorizontaltab.HorizontalViewPagerSlideTab;
import java.util.ArrayList;

public class HomeHorizontalTabViewPager extends LinearLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private boolean f13259a = false;
    /* access modifiers changed from: private */
    public HomeTabPagerAdapter b;
    private ArrayList<HomeSectionItem> c;
    /* access modifiers changed from: private */
    public HorizontalViewPagerSlideTab d;
    private RelativeLayout e;
    /* access modifiers changed from: private */
    public CustViewPager f;

    public void draw(HomeSection homeSection) {
    }

    public HomeHorizontalTabViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public HomeHorizontalTabViewPager(Context context, boolean z) {
        super(context);
        a(context, (AttributeSet) null, 0);
        this.f13259a = z;
        if (z) {
            setMinimumHeight(Coder.a(context, 192.0f));
        } else {
            setMinimumHeight(Coder.a(context, 254.0f));
        }
    }

    private void a() {
        if (this.f != null) {
            this.f.setAdapter((PagerAdapter) null);
            this.f.removeAllViews();
        }
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        inflate(context, R.layout.listitem_home_horizontal_viewpager, this);
        this.e = (RelativeLayout) findViewById(R.id.tab_container);
        CustViewPager custViewPager = (CustViewPager) findViewById(R.id.horizontal_viewpager);
        this.f = custViewPager;
        custViewPager.setOffscreenPageLimit(0);
        this.f.setPageMargin(Coder.a(context, 2.0f));
        HorizontalViewPagerSlideTab horizontalViewPagerSlideTab = new HorizontalViewPagerSlideTab(context);
        this.d = horizontalViewPagerSlideTab;
        horizontalViewPagerSlideTab.setOnSlideTabItemSelecteListener(new HorizontalViewPagerSlideTab.OnSlideTabItemSelecteListener() {
            public void a(int i) {
                HomeHorizontalTabViewPager.this.f.setCurrentItem(i + (250 - (250 % HomeHorizontalTabViewPager.this.b.b())));
            }
        });
        this.e.addView(this.d, new RelativeLayout.LayoutParams(-1, Coder.a(context, 40.0f)));
        this.b = new HomeTabPagerAdapter(context, this.f13259a);
        this.f.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int b = 0;

            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                HomeSectionItem b2 = HomeHorizontalTabViewPager.this.b.a(i);
                int c = HomeHorizontalTabViewPager.this.b.c(i);
                if (c == 0 || c == HomeHorizontalTabViewPager.this.b.f13068a.size() - 1 || c % 4 == 0) {
                    HomeHorizontalTabViewPager.this.d.setDoAnim(true);
                } else if (this.b > i) {
                    HomeHorizontalTabViewPager.this.d.setDoAnim(true);
                } else {
                    HomeHorizontalTabViewPager.this.d.setDoAnim(false);
                }
                HomeHorizontalTabViewPager.this.d.setSelectedItemByTitle(b2.mTitle);
                this.b = i;
            }
        });
    }

    public void bind(HomeSection homeSection, int i, int i2) {
        if (homeSection != null && homeSection.mBody != null && homeSection.mBody.mItems != null && homeSection.mBody.mItems.size() > 0) {
            this.c = homeSection.mBody.mItems;
            this.d.bind(this.c);
            if (this.b == null) {
                this.b = new HomeTabPagerAdapter(getContext(), this.f13259a);
            }
            this.f.setAdapter(this.b);
            this.b.a(this.c);
            this.f.setCurrentItem(250 - (250 % this.b.b()), false);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        a();
    }
}
