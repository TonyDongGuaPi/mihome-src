package com.xiaomi.shopviews.widget.smarttab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nineoldandroids.animation.ValueAnimator;
import com.xiaomi.shopviews.widget.R;

public class SmartTabLayout extends HorizontalScrollView {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f13346a = false;
    private static final boolean b = true;
    private static final int c = 16;
    private static final boolean d = true;
    private static final int e = -67108864;
    private static final int f = 0;
    private static final int g = 13;
    private static final int h = -1;
    private static final int i = 24;
    ValueAnimator animator;
    private boolean j;
    private boolean k;
    private InternalTabClickListener l;
    /* access modifiers changed from: private */
    public ISmartTabMediator m;
    private OnScrollChangeListener n;
    /* access modifiers changed from: private */
    public OnTabClickListener o;
    private TabProvider p;
    private int q;
    private boolean r;
    private ColorStateList s;
    private int t;
    protected final SmartTabStrip tabStrip;
    private int u;
    private float v;
    private int w;
    /* access modifiers changed from: private */
    public ViewPager.OnPageChangeListener x;

    public interface OnScrollChangeListener {
        void a(int i, int i2);
    }

    public interface OnTabClickListener {
        void a(int i);
    }

    public interface TabColorizer {
        int a(int i);

        int b(int i);
    }

    public interface TabProvider {
        View a(ViewGroup viewGroup, int i, ISmartTabMediator iSmartTabMediator);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
    }

    public SmartTabLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public SmartTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SmartTabLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        this.k = true;
        setHorizontalScrollBarEnabled(false);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float f2 = displayMetrics.density;
        float applyDimension = TypedValue.applyDimension(2, 13.0f, displayMetrics);
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet2, R.styleable.mi_stl_SmartTabLayout, i2, 0);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.mi_stl_SmartTabLayout_mi_stl_defaultTabBackground, -1);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.mi_stl_SmartTabLayout_mi_stl_defaultTabTextAllCaps, true);
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.mi_stl_SmartTabLayout_mi_stl_defaultTabTextColor);
        float dimension = obtainStyledAttributes.getDimension(R.styleable.mi_stl_SmartTabLayout_mi_stl_defaultTabTextSize, applyDimension);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.mi_stl_SmartTabLayout_mi_stl_defaultTabTextHorizontalPadding, (int) (16.0f * f2));
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.mi_stl_SmartTabLayout_mi_stl_defaultTabTextMinWidth, (int) (0.0f * f2));
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.mi_stl_SmartTabLayout_mi_stl_customTabTextLayoutId, -1);
        int resourceId3 = obtainStyledAttributes.getResourceId(R.styleable.mi_stl_SmartTabLayout_mi_stl_customTabTextViewId, -1);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.mi_stl_SmartTabLayout_mi_stl_distributeEvenly, false);
        boolean z3 = obtainStyledAttributes.getBoolean(R.styleable.mi_stl_SmartTabLayout_mi_stl_clickable, true);
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(R.styleable.mi_stl_SmartTabLayout_mi_stl_titleOffset, (int) (f2 * 24.0f));
        obtainStyledAttributes.recycle();
        this.w = layoutDimension;
        this.q = resourceId;
        this.r = z;
        this.s = colorStateList == null ? ColorStateList.valueOf(e) : colorStateList;
        this.v = dimension;
        this.t = dimensionPixelSize;
        this.u = dimensionPixelSize2;
        this.l = z3 ? new InternalTabClickListener() : null;
        this.j = z2;
        if (resourceId2 != -1) {
            setCustomTabView(resourceId2, resourceId3);
        }
        this.tabStrip = new SmartTabStrip(context2, attributeSet2);
        if (!z2 || !this.tabStrip.b()) {
            setFillViewport(!this.tabStrip.b());
            addView(this.tabStrip, -1, -1);
            return;
        }
        throw new UnsupportedOperationException("'distributeEvenly' and 'indicatorAlwaysInCenter' both use does not support");
    }

    private void a() {
        View view;
        int i2 = 0;
        while (i2 < this.m.a()) {
            if (this.p == null) {
                view = createDefaultTabView(this.m.a(i2));
            } else {
                view = this.p.a(this.tabStrip, i2, this.m);
            }
            if (view != null) {
                if (this.j) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.width = 0;
                    layoutParams.weight = 1.0f;
                }
                if (this.l != null) {
                    view.setOnClickListener(this.l);
                }
                View view2 = view;
                this.tabStrip.addView(view2);
                if (i2 == this.m.b()) {
                    view2.setSelected(true);
                }
                i2++;
            } else {
                throw new IllegalStateException("tabView is null.");
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2, float f2) {
        int i3;
        int childCount = this.tabStrip.getChildCount();
        if (childCount != 0 && i2 >= 0 && i2 < childCount) {
            startScrollAnimation(false, 0);
            boolean l2 = SmartTablayoutUtils.l(this);
            View childAt = this.tabStrip.getChildAt(i2);
            int j2 = (int) (((float) (SmartTablayoutUtils.j(childAt) + SmartTablayoutUtils.c(childAt))) * f2);
            if (this.tabStrip.b()) {
                if (0.0f < f2 && f2 < 1.0f) {
                    View childAt2 = this.tabStrip.getChildAt(i2 + 1);
                    j2 = Math.round(f2 * ((float) ((SmartTablayoutUtils.j(childAt) / 2) + SmartTablayoutUtils.b(childAt) + (SmartTablayoutUtils.j(childAt2) / 2) + SmartTablayoutUtils.d(childAt2))));
                }
                View childAt3 = this.tabStrip.getChildAt(0);
                if (l2) {
                    i3 = ((SmartTablayoutUtils.a(childAt) - SmartTablayoutUtils.b(childAt)) - j2) - (((SmartTablayoutUtils.j(childAt3) + SmartTablayoutUtils.b(childAt3)) - (SmartTablayoutUtils.j(childAt) + SmartTablayoutUtils.b(childAt))) / 2);
                } else {
                    i3 = (j2 + (SmartTablayoutUtils.i(childAt) - SmartTablayoutUtils.d(childAt))) - (((SmartTablayoutUtils.j(childAt3) + SmartTablayoutUtils.d(childAt3)) - (SmartTablayoutUtils.j(childAt) + SmartTablayoutUtils.d(childAt))) / 2);
                }
                startScrollAnimation(true, i3);
                return;
            }
            if (this.w == -1) {
                if (0.0f < f2 && f2 < 1.0f) {
                    View childAt4 = this.tabStrip.getChildAt(i2 + 1);
                    Math.round(f2 * ((float) ((SmartTablayoutUtils.j(childAt) / 2) + SmartTablayoutUtils.b(childAt) + (SmartTablayoutUtils.j(childAt4) / 2) + SmartTablayoutUtils.d(childAt4))));
                }
                if (l2) {
                    int i4 = (-SmartTablayoutUtils.k(childAt)) / 2;
                    int width = getWidth() / 2;
                    SmartTablayoutUtils.h(this);
                } else {
                    int k2 = SmartTablayoutUtils.k(childAt) / 2;
                    int width2 = getWidth() / 2;
                    SmartTablayoutUtils.h(this);
                }
            } else if (l2) {
                if (i2 > 0 || f2 > 0.0f) {
                    int i5 = this.w;
                }
            } else if (i2 > 0 || f2 > 0.0f) {
                int i6 = this.w;
            }
            int i7 = SmartTablayoutUtils.i(childAt);
            SmartTablayoutUtils.d(childAt);
            if (l2) {
                getWidth();
                SmartTablayoutUtils.g(this);
            }
            startScrollAnimation(this.k, i7);
        }
    }

    /* access modifiers changed from: protected */
    public TextView createDefaultTabView(CharSequence charSequence) {
        TextView textView = new TextView(getContext());
        textView.setGravity(17);
        textView.setText(charSequence);
        textView.setTextColor(this.s);
        textView.setTextSize(0, this.v);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        if (this.q != -1) {
            textView.setBackgroundResource(this.q);
        }
        if (Build.VERSION.SDK_INT >= 14) {
            textView.setAllCaps(this.r);
        }
        textView.setPadding(this.t, 0, this.t, 0);
        if (this.u > 0) {
            textView.setMinWidth(this.u);
        }
        return textView;
    }

    public View getTabAt(int i2) {
        return this.tabStrip.getChildAt(i2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z && this.m != null) {
            a(this.m.b(), 0.0f);
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        if (this.n != null) {
            this.n.a(i2, i4);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.tabStrip.b() && this.tabStrip.getChildCount() > 0) {
            View childAt = this.tabStrip.getChildAt(0);
            View childAt2 = this.tabStrip.getChildAt(this.tabStrip.getChildCount() - 1);
            int e2 = ((i2 - SmartTablayoutUtils.e(childAt)) / 2) - SmartTablayoutUtils.d(childAt);
            int e3 = ((i2 - SmartTablayoutUtils.e(childAt2)) / 2) - SmartTablayoutUtils.b(childAt2);
            this.tabStrip.setMinimumWidth(this.tabStrip.getMeasuredWidth());
            setPadding(e2, getPaddingTop(), e3, getPaddingBottom());
            setClipToPadding(false);
        }
    }

    public void setCustomTabColorizer(TabColorizer tabColorizer) {
        this.tabStrip.a(tabColorizer);
    }

    public void setCustomTabView(int i2, int i3) {
        this.p = new SimpleTabProvider(getContext(), i2, i3);
    }

    public void setCustomTabView(TabProvider tabProvider) {
        this.p = tabProvider;
    }

    public void setDefaultTabTextColor(int i2) {
        this.s = ColorStateList.valueOf(i2);
    }

    public void setDefaultTabTextColor(ColorStateList colorStateList) {
        this.s = colorStateList;
    }

    public void setDefaultTextMinWidth(int i2) {
        this.u = i2;
    }

    public void setDistributeEvenly(boolean z) {
        this.j = z;
    }

    public void setDividerColors(int... iArr) {
        this.tabStrip.a(iArr);
    }

    public void setDoStartAnim(boolean z) {
        this.k = z;
    }

    public void setIndicationInterpolator(SmartTabIndicationInterpolator smartTabIndicationInterpolator) {
        this.tabStrip.a(smartTabIndicationInterpolator);
    }

    public void setMediator(ISmartTabMediator iSmartTabMediator) {
        this.tabStrip.removeAllViews();
        this.m = iSmartTabMediator;
        if (iSmartTabMediator != null && !iSmartTabMediator.c()) {
            iSmartTabMediator.a((ViewPager.OnPageChangeListener) new InternalViewPagerListener());
            a();
        }
    }

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        this.n = onScrollChangeListener;
    }

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.o = onTabClickListener;
    }

    public void setSelectedIndicatorColors(int... iArr) {
        this.tabStrip.b(iArr);
    }

    public void startScrollAnimation(boolean z, int i2) {
        if (this.animator == null) {
            this.animator = new ValueAnimator();
        }
        if (z) {
            this.animator.setIntValues(getScrollX(), i2);
            this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SmartTabLayout.this.setScrollX(((Integer) valueAnimator.getAnimatedValue()).intValue());
                }
            });
            this.animator.setDuration(200);
            this.animator.start();
        } else if (this.animator.isRunning()) {
            this.animator.end();
        }
    }

    private class InternalTabClickListener implements View.OnClickListener {
        private InternalTabClickListener() {
        }

        public void onClick(View view) {
            view.getId();
            for (int i = 0; i < SmartTabLayout.this.tabStrip.getChildCount(); i++) {
                if (view == SmartTabLayout.this.tabStrip.getChildAt(i)) {
                    if (SmartTabLayout.this.o != null) {
                        SmartTabLayout.this.o.a(i);
                    }
                    SmartTabLayout.this.m.b(i);
                    return;
                }
            }
        }
    }

    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        private int b;

        private InternalViewPagerListener() {
            this.b = 0;
        }

        public void onPageScrollStateChanged(int i) {
            this.b = i;
            if (SmartTabLayout.this.x != null) {
                SmartTabLayout.this.x.onPageScrollStateChanged(i);
            }
        }

        public void onPageScrolled(int i, float f, int i2) {
            int childCount = SmartTabLayout.this.tabStrip.getChildCount();
            if (childCount != 0 && i >= 0 && i < childCount) {
                SmartTabLayout.this.tabStrip.a(i, f);
                SmartTabLayout.this.a(i, f);
                if (SmartTabLayout.this.x != null) {
                    SmartTabLayout.this.x.onPageScrolled(i, f, i2);
                }
            }
        }

        public void onPageSelected(int i) {
            if (this.b == 0) {
                SmartTabLayout.this.tabStrip.a(i, 0.0f);
                SmartTabLayout.this.a(i, 0.0f);
            }
            int i2 = 0;
            while (i2 < SmartTabLayout.this.tabStrip.getChildCount()) {
                SmartTabLayout.this.tabStrip.getChildAt(i2).setSelected(i == i2);
                i2++;
            }
            if (SmartTabLayout.this.x != null) {
                SmartTabLayout.this.x.onPageSelected(i);
            }
        }
    }

    private static class SimpleTabProvider implements TabProvider {

        /* renamed from: a  reason: collision with root package name */
        private final LayoutInflater f13350a;
        private final int b;
        private final int c;

        private SimpleTabProvider(Context context, int i, int i2) {
            this.f13350a = LayoutInflater.from(context);
            this.b = i;
            this.c = i2;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: android.widget.TextView} */
        /* JADX WARNING: type inference failed for: r0v6, types: [android.view.View] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.view.View a(android.view.ViewGroup r6, int r7, com.xiaomi.shopviews.widget.smarttab.ISmartTabMediator r8) {
            /*
                r5 = this;
                int r0 = r5.b
                r1 = -1
                r2 = 0
                if (r0 == r1) goto L_0x0010
                android.view.LayoutInflater r0 = r5.f13350a
                int r3 = r5.b
                r4 = 0
                android.view.View r6 = r0.inflate(r3, r6, r4)
                goto L_0x0011
            L_0x0010:
                r6 = r2
            L_0x0011:
                int r0 = r5.c
                if (r0 == r1) goto L_0x0023
                if (r6 == 0) goto L_0x0023
                r0 = r6
                android.view.View r0 = (android.view.View) r0
                int r1 = r5.c
                android.view.View r0 = r0.findViewById(r1)
                r2 = r0
                android.widget.TextView r2 = (android.widget.TextView) r2
            L_0x0023:
                if (r2 != 0) goto L_0x0030
                java.lang.Class<android.widget.TextView> r0 = android.widget.TextView.class
                boolean r0 = r0.isInstance(r6)
                if (r0 == 0) goto L_0x0030
                r2 = r6
                android.widget.TextView r2 = (android.widget.TextView) r2
            L_0x0030:
                if (r2 == 0) goto L_0x0039
                java.lang.CharSequence r7 = r8.a((int) r7)
                r2.setText(r7)
            L_0x0039:
                android.view.View r6 = (android.view.View) r6
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shopviews.widget.smarttab.SmartTabLayout.SimpleTabProvider.a(android.view.ViewGroup, int, com.xiaomi.shopviews.widget.smarttab.ISmartTabMediator):android.view.View");
        }
    }
}
