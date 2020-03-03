package com.xiaomi.smarthome.library.common.widget;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.miio.TitleBarUtil;

public class CoordinatorTabLayout extends CoordinatorLayout {

    /* renamed from: a  reason: collision with root package name */
    private int[] f18791a;
    private View[] b;
    private Context c;
    private Toolbar d;
    private TabLayout e;
    private FrameLayout f;
    /* access modifiers changed from: private */
    public AppBarLayout g;
    private CollapsingToolbarLayout h;
    private LoadHeaderImagesListener i;
    /* access modifiers changed from: private */
    public ArgbEvaluator j = new ArgbEvaluator();
    /* access modifiers changed from: private */
    public int k = 0;
    /* access modifiers changed from: private */
    public int l = 0;
    /* access modifiers changed from: private */
    public AppBarLayout.OnOffsetChangedListener m;
    /* access modifiers changed from: private */
    public float n = 0.0f;
    /* access modifiers changed from: private */
    public TabLayout.OnTabSelectedListener o;

    public interface LoadHeaderImagesListener {
        void a(ImageView imageView, TabLayout.Tab tab);
    }

    public CoordinatorTabLayout setBackEnable(Boolean bool) {
        return this;
    }

    public CoordinatorTabLayout setTitle(String str) {
        return this;
    }

    public CoordinatorTabLayout(Context context) {
        super(context);
        this.c = context;
    }

    public CoordinatorTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = context;
        if (Build.VERSION.SDK_INT >= 3 && !isInEditMode()) {
            a(context);
            a(context, attributeSet);
        }
    }

    public CoordinatorTabLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.c = context;
        if (Build.VERSION.SDK_INT >= 3 && !isInEditMode()) {
            a(context);
            a(context, attributeSet);
        }
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_coordinatortablayout, this, true);
        a();
        this.h = (CollapsingToolbarLayout) findViewById(R.id.collapsingtoolbarlayout);
        this.e = (TabLayout) findViewById(R.id.tabLayout);
        this.f = (FrameLayout) findViewById(R.id.top_content_container);
        this.g = (AppBarLayout) findViewById(R.id.app_bar_layout);
        this.k = getResources().getColor(R.color.class_Y);
        this.l = getResources().getColor(R.color.white);
        this.g.addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) new AppBarLayout.OnOffsetChangedListener() {
            private int b = 0;
            private boolean c = true;

            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (this.b == 0) {
                    this.b = appBarLayout.getTotalScrollRange();
                }
                if (this.b != 0) {
                    if (CoordinatorTabLayout.this.m != null) {
                        CoordinatorTabLayout.this.m.onOffsetChanged(appBarLayout, i);
                    }
                    float unused = CoordinatorTabLayout.this.n = ((float) Math.abs(i)) / ((float) this.b);
                    CoordinatorTabLayout.this.g.setBackgroundColor(((Integer) CoordinatorTabLayout.this.j.evaluate(CoordinatorTabLayout.this.n, Integer.valueOf(CoordinatorTabLayout.this.k), Integer.valueOf(CoordinatorTabLayout.this.l))).intValue());
                    if (CoordinatorTabLayout.this.n >= 1.0f) {
                        if (!this.c) {
                            this.c = true;
                            TitleBarUtil.a((Activity) CoordinatorTabLayout.this.getContext());
                        }
                    } else if (this.c) {
                        this.c = false;
                        TitleBarUtil.b((Activity) CoordinatorTabLayout.this.getContext());
                    }
                }
            }
        });
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 1) {
            if (((double) this.n) > 0.5d) {
                this.g.setExpanded(false, true);
            } else {
                this.g.setExpanded(true, true);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CoordinatorTabLayout);
        this.e.setSelectedTabIndicatorColor(obtainStyledAttributes.getColor(1, getResources().getColor(R.color.class_text_17)));
        this.e.setSelectedTabIndicatorHeight(0);
        this.e.setTabTextColors(getResources().getColor(R.color.class_D), getResources().getColor(R.color.class_text_17));
        obtainStyledAttributes.recycle();
    }

    private void a() {
        this.d = (Toolbar) findViewById(R.id.title_bar);
    }

    public CoordinatorTabLayout setImageArray(@Nullable View[] viewArr) {
        this.b = viewArr;
        b();
        return this;
    }

    public CoordinatorTabLayout setImageArray(@Nullable View[] viewArr, @Nullable int[] iArr) {
        this.b = viewArr;
        this.f18791a = iArr;
        b();
        return this;
    }

    public CoordinatorTabLayout setContentScrimColorArray(@NonNull int[] iArr) {
        this.f18791a = iArr;
        return this;
    }

    public void setOnTabSelectedListener(TabLayout.OnTabSelectedListener onTabSelectedListener) {
        this.o = onTabSelectedListener;
    }

    private void b() {
        this.e.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            public void onTabSelected(TabLayout.Tab tab) {
                if (CoordinatorTabLayout.this.o != null) {
                    CoordinatorTabLayout.this.o.onTabSelected(tab);
                }
            }

            public void onTabUnselected(TabLayout.Tab tab) {
                if (CoordinatorTabLayout.this.o != null) {
                    CoordinatorTabLayout.this.o.onTabUnselected(tab);
                }
            }

            public void onTabReselected(TabLayout.Tab tab) {
                if (CoordinatorTabLayout.this.o != null) {
                    CoordinatorTabLayout.this.o.onTabReselected(tab);
                }
            }
        });
    }

    public CoordinatorTabLayout setupWithViewPager(ViewPager viewPager) {
        this.e.setupWithViewPager(viewPager);
        return this;
    }

    public TabLayout getTabLayout() {
        return this.e;
    }

    public FrameLayout getTopContent() {
        return this.f;
    }

    public void setTopContentView(View view) {
        this.f.removeAllViews();
        this.f.addView(view);
    }

    public void setHeaderView(View view) {
        this.d.removeAllViews();
        this.d.addView(view);
    }

    public CoordinatorTabLayout setLoadHeaderImagesListener(LoadHeaderImagesListener loadHeaderImagesListener) {
        this.i = loadHeaderImagesListener;
        b();
        return this;
    }

    public void setOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener onOffsetChangedListener) {
        this.m = onOffsetChangedListener;
    }
}
