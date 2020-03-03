package com.xiaomi.smarthome.newui.widget;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayoutSpringBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.newui.DeviceMainPage;
import com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManagerNew;
import com.xiaomi.smarthome.newui.topwidget.TopWidgetDataNew;
import com.xiaomi.smarthome.newui.widget.SimplePushToRefreshHeader;
import com.xiaomi.smarthome.newui.widget.banner.CustomBannerHeaderView;
import com.xiaomi.smarthome.stat.STAT;
import java.lang.ref.WeakReference;
import java.util.List;

public class MainPageAppBarLayout extends AppBarLayout {
    private static final float n = 0.9f;
    private static final float o = 0.3f;
    private static final int p = 200;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public CustomBannerHeaderView f20872a;
    /* access modifiers changed from: private */
    public SimplePushToRefreshHeader b;
    private ViewStub c;
    /* access modifiers changed from: private */
    public View d;
    private View e;
    /* access modifiers changed from: private */
    public AppBarLayoutSpringBehavior f;
    /* access modifiers changed from: private */
    public AppBarLayout.OnOffsetChangedListener g;
    private SimplePushToRefreshHeader.OnRefreshListener h;
    boolean haveSetMargin = false;
    /* access modifiers changed from: private */
    public boolean i = true;
    private int j = 0;
    private AppBarLayout.OnOffsetChangedListener k = new AppBarLayout.OnOffsetChangedListener() {
        private int b = Integer.MAX_VALUE;

        public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            if (this.b == Integer.MAX_VALUE) {
                this.b = i;
            } else if (this.b == i) {
                return;
            }
            this.b = i;
            float totalScrollRange = (float) appBarLayout.getTotalScrollRange();
            float abs = ((float) Math.abs(i)) / totalScrollRange;
            MainPageAppBarLayout.this.a(abs);
            MainPageAppBarLayout.this.b(abs);
            float abs2 = ((float) Math.abs(Math.abs(i))) / totalScrollRange;
            if (MainPageAppBarLayout.this.f20872a != null) {
                MainPageAppBarLayout.this.f20872a.setAlpha(1.0f - abs2);
            }
            if (((double) abs2) > 0.8d) {
                float f = 1.0f - ((abs2 - 0.8f) * 5.0f);
                MainPageAppBarLayout.this.findViewById(R.id.module_a_3_return_title).setAlpha(f);
                MainPageAppBarLayout.this.findViewById(R.id.module_a_3_right_iv_setting_btn).setAlpha(f);
            } else {
                MainPageAppBarLayout.this.findViewById(R.id.module_a_3_return_title).setAlpha(1.0f);
                MainPageAppBarLayout.this.findViewById(R.id.module_a_3_right_iv_setting_btn).setAlpha(1.0f);
            }
            if (MainPageAppBarLayout.this.d == null) {
                View unused = MainPageAppBarLayout.this.d = MainPageAppBarLayout.this.findViewById(R.id.title_bar_content);
            }
            if (abs2 >= 1.0f) {
                boolean unused2 = MainPageAppBarLayout.this.l = true;
                if (MainPageAppBarLayout.this.d != null) {
                    MainPageAppBarLayout.this.d.setBackgroundColor(0);
                    MainPageAppBarLayout.this.d.setAlpha(1.0f);
                    ((TextView) MainPageAppBarLayout.this.d.findViewById(R.id.module_a_3_return_title)).setVisibility(8);
                }
                MainPageAppBarLayout.this.a();
                if (!MainPageAppBarLayout.this.i) {
                    boolean unused3 = MainPageAppBarLayout.this.i = true;
                }
            } else {
                boolean unused4 = MainPageAppBarLayout.this.l = false;
                if (MainPageAppBarLayout.this.i) {
                    boolean unused5 = MainPageAppBarLayout.this.i = false;
                    MainPageAppBarLayout.this.a();
                    if (MainPageAppBarLayout.this.d != null) {
                        MainPageAppBarLayout.this.d.setBackgroundColor(0);
                        MainPageAppBarLayout.this.d.setAlpha(1.0f);
                        ((TextView) MainPageAppBarLayout.this.d.findViewById(R.id.module_a_3_return_title)).setVisibility(0);
                    }
                }
            }
            if (MainPageAppBarLayout.this.g != null) {
                MainPageAppBarLayout.this.g.onOffsetChanged(appBarLayout, i);
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean l = false;
    private WeakReference<DeviceMainPage> m;
    private boolean q = false;
    private boolean r = true;
    /* access modifiers changed from: private */
    public boolean s = true;

    public MainPageAppBarLayout(Context context) {
        super(context);
    }

    public MainPageAppBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    public int getContentHeight() {
        return this.j;
    }

    public void setContentHeight(int i2) {
        this.j = i2;
        getLayoutParams().height = i2;
        if (this.f != null) {
            this.f.setContentHeight(i2);
        }
    }

    public void init() {
        this.f20872a = (CustomBannerHeaderView) findViewById(R.id.custom_header_view);
        this.b = (SimplePushToRefreshHeader) findViewById(R.id.pull_to_refresh_indicator);
        if (this.h != null) {
            this.b.setOnRefreshListener(this.h);
        }
        this.e = findViewById(R.id.loading_container);
        post(new Runnable() {
            public void run() {
                AppBarLayoutSpringBehavior unused = MainPageAppBarLayout.this.f = (AppBarLayoutSpringBehavior) ((CoordinatorLayout.LayoutParams) MainPageAppBarLayout.this.getLayoutParams()).getBehavior();
                MainPageAppBarLayout.this.f.addSpringOffsetCallback(new AppBarLayoutSpringBehavior.SpringOffsetCallback() {
                    public void springCallback(int i) {
                        if (MainPageAppBarLayout.this.f20872a != null) {
                            MainPageAppBarLayout.this.f20872a.offset(i);
                        }
                    }
                });
                MainPageAppBarLayout.this.f.setPullToRefreshCallback(MainPageAppBarLayout.this.b);
                MainPageAppBarLayout.this.f.setContentHeight(MainPageAppBarLayout.this.getHeight());
                MainPageAppBarLayout.this.f.setNormalScrollEnable(MainPageAppBarLayout.this.s, (CoordinatorLayout) MainPageAppBarLayout.this.getParent(), MainPageAppBarLayout.this);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.e != null && !this.haveSetMargin) {
            this.haveSetMargin = true;
            ((ViewGroup.MarginLayoutParams) this.e.getLayoutParams()).topMargin = (i3 - getResources().getDimensionPixelSize(R.dimen.mainpage_titlebar_height)) / 2;
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        addOnOffsetChangedListener(this.k);
    }

    /* access modifiers changed from: private */
    public void a() {
        DeviceMainPage deviceMainPage;
        if (this.m != null && (deviceMainPage = (DeviceMainPage) this.m.get()) != null) {
            deviceMainPage.refreshTitleBar();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeOnOffsetChangedListener(this.k);
    }

    public boolean isCollapsingMode() {
        return this.l;
    }

    public void setOnRefreshListener(SimplePushToRefreshHeader.OnRefreshListener onRefreshListener) {
        if (this.b != null) {
            this.b.setOnRefreshListener(onRefreshListener);
        } else {
            this.h = onRefreshListener;
        }
    }

    public void refreshComplete(CoordinatorLayout coordinatorLayout) {
        if (this.f != null) {
            this.f.refreshComplete(coordinatorLayout, this);
        }
    }

    public void setDeviceMainPage(DeviceMainPage deviceMainPage) {
        this.m = new WeakReference<>(deviceMainPage);
    }

    public void refreshUI() {
        List<TopWidgetDataNew.Detail> a2;
        if (this.f20872a != null && (a2 = TopWidgetDataManagerNew.b().a(HomeManager.a().l())) != null && !a2.isEmpty()) {
            STAT.e.b();
        }
    }

    /* access modifiers changed from: private */
    public void a(float f2) {
        if (f2 >= o) {
            if (this.r) {
                this.r = false;
            }
        } else if (!this.r) {
            this.r = true;
        }
    }

    /* access modifiers changed from: private */
    public void b(float f2) {
        if (f2 >= n) {
            if (!this.q) {
                this.q = true;
            }
        } else if (this.q) {
            this.q = false;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 0:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case 1:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void refreshTitleBar() {
        if (this.l) {
            TitleBarUtil.a((Activity) getContext());
        } else {
            TitleBarUtil.b((Activity) getContext());
        }
    }

    public void setNormalScrollEnable(boolean z, CoordinatorLayout coordinatorLayout) {
        if (z || !SHApplication.isInSplitScreenMode()) {
            this.s = z;
            if (this.f != null) {
                this.f.setNormalScrollEnable(z, coordinatorLayout, this);
            }
        }
    }

    public void collapse(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        if (this.f != null) {
            this.f.collapse(coordinatorLayout, appBarLayout);
        }
    }

    public void expand(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        if (this.f != null) {
            this.f.expand(coordinatorLayout, appBarLayout);
        }
    }

    public void setCameraVisibility(int i2) {
        if (i2 <= 0) {
            setTag("camera gone");
        } else {
            setTag("camera visible");
        }
    }
}
