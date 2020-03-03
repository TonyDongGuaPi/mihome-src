package com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.NavigatorHelper;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.abs.IPagerNavigator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.model.PositionData;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import java.util.ArrayList;
import java.util.List;

public class CommonNavigator extends FrameLayout implements NavigatorHelper.OnNavigatorScrollListener, IPagerNavigator {
    private static final String x = "CommonNavigator";

    /* renamed from: a  reason: collision with root package name */
    private final Scroller f20928a;
    private HorizontalScrollView b;
    private LinearLayout c;
    private LinearLayout d;
    private IPagerIndicator e;
    /* access modifiers changed from: private */
    public CommonNavigatorAdapter f;
    /* access modifiers changed from: private */
    public NavigatorHelper g = new NavigatorHelper();
    private boolean h;
    private boolean i;
    private boolean j;
    private float k = 0.5f;
    private boolean l = true;
    private boolean m = true;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private boolean r = true;
    private List<PositionData> s = new ArrayList();
    private DataSetObserver t = new DataSetObserver() {
        public void onInvalidated() {
        }

        public void onChanged() {
            CommonNavigator.this.g.c(CommonNavigator.this.f.a());
            CommonNavigator.this.a();
        }
    };
    private int u = 0;
    private SpringAnimation v;
    private boolean w = false;

    public void onDetachFromMagicIndicator() {
    }

    public int getCurrentSelected() {
        return this.u;
    }

    public CommonNavigator(Context context) {
        super(context);
        this.g.a((NavigatorHelper.OnNavigatorScrollListener) this);
        this.f20928a = new Scroller(context);
    }

    public void notifyDataSetChanged() {
        if (this.f != null) {
            this.f.b();
        }
    }

    public boolean isAdjustMode() {
        return this.h;
    }

    public void setAdjustMode(boolean z) {
        this.h = z;
    }

    public boolean isCompactMode() {
        return this.i;
    }

    public void setCompactMode(boolean z) {
        this.i = z;
    }

    public CommonNavigatorAdapter getAdapter() {
        return this.f;
    }

    public void setAdapter(CommonNavigatorAdapter commonNavigatorAdapter) {
        if (this.f != commonNavigatorAdapter) {
            if (this.f != null) {
                this.f.b(this.t);
            }
            this.f = commonNavigatorAdapter;
            if (this.f != null) {
                this.f.a(this.t);
                this.g.c(this.f.a());
                if (this.c != null) {
                    this.f.b();
                    return;
                }
                return;
            }
            this.g.c(0);
            a();
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        View view;
        removeAllViews();
        if (isCompactMode()) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.pager_navigator_layout_compact, this);
        } else if (this.h) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.pager_navigator_layout_no_scroll, this);
        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.pager_navigator_layout, this);
        }
        this.b = (HorizontalScrollView) view.findViewById(R.id.scroll_view);
        this.c = (LinearLayout) view.findViewById(R.id.title_container);
        this.c.setPadding(this.o, 0, this.n, 0);
        this.d = (LinearLayout) view.findViewById(R.id.indicator_container);
        if (this.p) {
            this.d.getParent().bringChildToFront(this.d);
        }
        b();
    }

    private void b() {
        LinearLayout.LayoutParams layoutParams;
        int a2 = this.g.a();
        for (int i2 = 0; i2 < a2; i2++) {
            IPagerTitleView a3 = this.f.a(getContext(), i2);
            if (a3 instanceof View) {
                View view = (View) a3;
                if (isCompactMode()) {
                    layoutParams = new LinearLayout.LayoutParams(-2, -2);
                } else if (this.h) {
                    layoutParams = new LinearLayout.LayoutParams(0, -1);
                    layoutParams.weight = this.f.b(getContext(), i2);
                } else {
                    layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                    if (layoutParams == null) {
                        layoutParams = new LinearLayout.LayoutParams(-2, -1);
                    }
                }
                this.c.addView(view, layoutParams);
            }
        }
        if (this.f != null) {
            this.e = this.f.a(getContext());
            if (this.e instanceof View) {
                this.d.addView((View) this.e, new FrameLayout.LayoutParams(-1, -1));
            }
        }
    }

    public void refreshUI(int i2, int i3) {
        if (this.f != null) {
            int a2 = this.g.a();
            for (int i4 = 0; i4 < a2; i4++) {
                View childAt = this.c.getChildAt(i4);
                if (childAt instanceof SimplePagerTitleView) {
                    SimplePagerTitleView simplePagerTitleView = (SimplePagerTitleView) childAt;
                    simplePagerTitleView.setNormalColor(i3);
                    simplePagerTitleView.setSelectedColor(i2);
                    if (i4 == getCurrentSelected()) {
                        simplePagerTitleView.setTextColor(i2);
                    } else {
                        simplePagerTitleView.setTextColor(i3);
                    }
                }
            }
        }
        View childAt2 = this.d.getChildAt(0);
        if (childAt2 != null && (childAt2 instanceof LinePagerIndicator)) {
            LinePagerIndicator linePagerIndicator = (LinePagerIndicator) childAt2;
            linePagerIndicator.setColors(Integer.valueOf(i2));
            linePagerIndicator.invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (this.f != null) {
            c();
            if (this.e != null) {
                this.e.onPositionDataProvide(this.s);
            }
            if (this.r && this.g.c() == 0) {
                onPageSelected(this.g.b());
                onPageScrolled(this.g.b(), 0.0f, 0);
            }
        }
    }

    private void c() {
        this.s.clear();
        int a2 = this.g.a();
        for (int i2 = 0; i2 < a2; i2++) {
            PositionData positionData = new PositionData();
            View childAt = this.c.getChildAt(i2);
            if (childAt != null) {
                positionData.f20936a = childAt.getLeft();
                positionData.b = childAt.getTop();
                positionData.c = childAt.getRight();
                positionData.d = childAt.getBottom();
                if (childAt instanceof IMeasurablePagerTitleView) {
                    IMeasurablePagerTitleView iMeasurablePagerTitleView = (IMeasurablePagerTitleView) childAt;
                    positionData.e = iMeasurablePagerTitleView.getContentLeft();
                    positionData.f = iMeasurablePagerTitleView.getContentTop();
                    positionData.g = iMeasurablePagerTitleView.getContentRight();
                    positionData.h = iMeasurablePagerTitleView.getContentBottom();
                } else {
                    positionData.e = positionData.f20936a;
                    positionData.f = positionData.b;
                    positionData.g = positionData.c;
                    positionData.h = positionData.d;
                }
            }
            this.s.add(positionData);
        }
    }

    public void onPageScrolled(int i2, float f2, int i3) {
        if (this.f != null) {
            this.g.a(i2, f2, i3);
            if (this.e != null) {
                this.e.onPageScrolled(i2, f2, i3);
            }
            if (this.b != null && this.s.size() > 0 && i2 >= 0 && i2 < this.s.size()) {
                if (this.m) {
                    int min = Math.min(this.s.size() - 1, i2);
                    int min2 = Math.min(this.s.size() - 1, i2 + 1);
                    float e2 = ((float) this.s.get(min).e()) - (((float) this.b.getWidth()) * this.k);
                    float e3 = ((float) this.s.get(min2).e()) - (((float) this.b.getWidth()) * this.k);
                    if (!this.w) {
                        this.b.scrollTo((int) (e2 + ((e3 - e2) * f2)), 0);
                        return;
                    }
                    return;
                }
                boolean z = this.j;
            }
        }
    }

    public float getScrollPivotX() {
        return this.k;
    }

    public void setScrollPivotX(float f2) {
        this.k = f2;
    }

    public void onPageSelected(int i2) {
        if (this.f != null) {
            this.g.a(i2);
            if (this.e != null) {
                this.e.onPageSelected(i2);
            }
        }
    }

    public void onPageScrollStateChanged(int i2) {
        if (this.f != null) {
            this.g.b(i2);
            if (this.e != null) {
                this.e.onPageScrollStateChanged(i2);
            }
        }
    }

    public void onAttachToMagicIndicator() {
        a();
    }

    public IPagerIndicator getPagerIndicator() {
        return this.e;
    }

    public boolean isEnablePivotScroll() {
        return this.j;
    }

    public void setEnablePivotScroll(boolean z) {
        this.j = z;
    }

    public void onEnter(int i2, int i3, float f2, boolean z) {
        if (this.c != null) {
            View childAt = this.c.getChildAt(i2);
            if (childAt instanceof IPagerTitleView) {
                ((IPagerTitleView) childAt).onEnter(i2, i3, f2, z);
            }
        }
    }

    public void onLeave(int i2, int i3, float f2, boolean z) {
        if (this.c != null) {
            View childAt = this.c.getChildAt(i2);
            if (childAt instanceof IPagerTitleView) {
                ((IPagerTitleView) childAt).onLeave(i2, i3, f2, z);
            }
        }
    }

    public boolean isSmoothScroll() {
        return this.l;
    }

    public void setSmoothScroll(boolean z) {
        this.l = z;
    }

    public boolean isFollowTouch() {
        return this.m;
    }

    public void setFollowTouch(boolean z) {
        this.m = z;
    }

    public boolean isSkimOver() {
        return this.q;
    }

    public void setSkimOver(boolean z) {
        this.q = z;
        this.g.a(z);
    }

    public void onSelected(int i2, int i3) {
        this.u = i2;
        if (this.c != null) {
            View childAt = this.c.getChildAt(i2);
            if (childAt instanceof IPagerTitleView) {
                ((IPagerTitleView) childAt).onSelected(i2, i3);
                childAt.performAccessibilityAction(64, new Bundle());
            }
            if (!this.h && !this.m && this.b != null && this.s.size() > 0) {
                PositionData positionData = this.s.get(Math.min(this.s.size() - 1, i2));
                if (this.j) {
                    float e2 = ((float) positionData.e()) - (((float) this.b.getWidth()) * this.k);
                    if (this.l) {
                        this.b.smoothScrollTo((int) e2, 0);
                    } else {
                        this.b.scrollTo((int) e2, 0);
                    }
                } else if (this.b.getScrollX() > positionData.f20936a) {
                    if (this.l) {
                        this.b.smoothScrollTo(positionData.f20936a, 0);
                    } else {
                        this.b.scrollTo(positionData.f20936a, 0);
                    }
                } else if (this.b.getScrollX() + getWidth() < positionData.c) {
                    if (this.l) {
                        this.b.smoothScrollTo(positionData.c - getWidth(), 0);
                    } else {
                        this.b.scrollTo(positionData.c - getWidth(), 0);
                    }
                }
            }
            if (this.w) {
                float e3 = ((float) this.s.get(Math.min(this.s.size() - 1, i2)).e()) - (((float) this.b.getWidth()) * this.k);
                if (this.v != null && this.v.isRunning()) {
                    this.v.skipToEnd();
                }
                this.v = new SpringAnimation(this.b, SpringAnimation.SCROLL_X, e3);
                this.v.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                    public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
                        CommonNavigator.this.a(dynamicAnimation, z, f, f2);
                    }
                });
                this.v.getSpring().setStiffness(50.0f).setDampingRatio(1.0f);
                this.b.postDelayed(new Runnable() {
                    public final void run() {
                        CommonNavigator.this.d();
                    }
                }, 50);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DynamicAnimation dynamicAnimation, boolean z, float f2, float f3) {
        this.w = false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d() {
        this.v.start();
    }

    public void computeScroll() {
        super.computeScroll();
    }

    public void onDeselected(int i2, int i3) {
        if (this.c != null) {
            View childAt = this.c.getChildAt(i2);
            if (childAt instanceof IPagerTitleView) {
                ((IPagerTitleView) childAt).onDeselected(i2, i3);
            }
        }
    }

    public IPagerTitleView getPagerTitleView(int i2) {
        if (this.c == null) {
            return null;
        }
        return (IPagerTitleView) this.c.getChildAt(i2);
    }

    public LinearLayout getTitleContainer() {
        return this.c;
    }

    public int getRightPadding() {
        return this.n;
    }

    public void setRightPadding(int i2) {
        this.n = i2;
    }

    public int getLeftPadding() {
        return this.o;
    }

    public void setLeftPadding(int i2) {
        this.o = i2;
    }

    public boolean isIndicatorOnTop() {
        return this.p;
    }

    public void setIndicatorOnTop(boolean z) {
        this.p = z;
    }

    public boolean isReselectWhenLayout() {
        return this.r;
    }

    public void setReselectWhenLayout(boolean z) {
        this.r = z;
    }

    public void setChangeFromClickTab() {
        this.w = true;
    }
}
