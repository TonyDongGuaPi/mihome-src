package com.mi.global.shop.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class VerticalViewPager extends ViewGroup {
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final int V = -1;

    /* renamed from: a  reason: collision with root package name */
    private static final String f7171a = "ViewPager";
    private static final int ae = 2;
    private static final int as = 0;
    private static final int at = 1;
    private static final int au = 2;
    private static final ViewPositionComparator ax = new ViewPositionComparator();
    private static final boolean b = false;
    private static final float c = 0.1f;
    private static final float d = 0.9f;
    private static final boolean e = false;
    private static final int f = 1;
    private static final int g = 600;
    private static final int h = 25;
    private static final int i = 16;
    private static final int j = 400;
    /* access modifiers changed from: private */
    public static final int[] k = {16842931};
    private static final Comparator<ItemInfo> m = new Comparator<ItemInfo>() {
        /* renamed from: a */
        public int compare(ItemInfo itemInfo, ItemInfo itemInfo2) {
            return itemInfo.b - itemInfo2.b;
        }
    };
    private static final Interpolator n = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };
    private int A;
    private int B;
    private float C = -3.4028235E38f;
    private float D = Float.MAX_VALUE;
    private int E;
    private int F;
    private boolean G;
    private boolean H;
    private boolean I;
    private int J = 1;
    private boolean K;
    private boolean L;
    private boolean M;
    private int N;
    private int O;
    private int P;
    private float Q;
    private float R;
    private float S;
    private float T;
    private int U = -1;
    private VelocityTracker W;
    private int aa;
    private int ab;
    private int ac;
    private int ad;
    private boolean af;
    private long ag;
    private EdgeEffectCompat ah;
    private EdgeEffectCompat ai;
    private boolean aj = true;
    private boolean ak = false;
    private boolean al;
    private int am;
    private ViewPager.OnPageChangeListener an;
    private ViewPager.OnPageChangeListener ao;
    private OnAdapterChangeListener ap;
    private ViewPager.PageTransformer aq;
    private Method ar;
    private int av;
    private ArrayList<View> aw;
    private final Runnable ay = new Runnable() {
        public void run() {
            VerticalViewPager.this.setScrollState(0);
            VerticalViewPager.this.populate();
        }
    };
    private int az = 0;
    private int l;
    private final ArrayList<ItemInfo> o = new ArrayList<>();
    private final ItemInfo p = new ItemInfo();
    private final Rect q = new Rect();
    /* access modifiers changed from: private */
    public PagerAdapter r;
    /* access modifiers changed from: private */
    public int s;
    private int t = -1;
    private Parcelable u = null;
    private ClassLoader v = null;
    private Scroller w;
    private PagerObserver x;
    private int y;
    private Drawable z;

    interface Decor {
    }

    interface OnAdapterChangeListener {
        void a(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2);
    }

    static class ItemInfo {

        /* renamed from: a  reason: collision with root package name */
        Object f7173a;
        int b;
        boolean c;
        float d;
        float e;

        ItemInfo() {
        }
    }

    public VerticalViewPager(Context context) {
        super(context);
        initViewPager();
    }

    public VerticalViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViewPager();
    }

    /* access modifiers changed from: package-private */
    public void initViewPager() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.w = new Scroller(context, n);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.P = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
        this.aa = (int) (400.0f * f2);
        this.ab = viewConfiguration.getScaledMaximumFlingVelocity();
        this.ah = new EdgeEffectCompat(context);
        this.ai = new EdgeEffectCompat(context);
        this.ac = (int) (25.0f * f2);
        this.ad = (int) (2.0f * f2);
        this.N = (int) (f2 * 16.0f);
        ViewCompat.setAccessibilityDelegate(this, new MyAccessibilityDelegate());
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        removeCallbacks(this.ay);
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: private */
    public void setScrollState(int i2) {
        if (this.az != i2) {
            this.az = i2;
            if (this.aq != null) {
                b(i2 != 0);
            }
            if (this.an != null) {
                this.an.onPageScrollStateChanged(i2);
            }
        }
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        if (this.r != null) {
            this.r.unregisterDataSetObserver(this.x);
            this.r.startUpdate((ViewGroup) this);
            for (int i2 = 0; i2 < this.o.size(); i2++) {
                ItemInfo itemInfo = this.o.get(i2);
                this.r.destroyItem((ViewGroup) this, itemInfo.b, itemInfo.f7173a);
            }
            this.r.finishUpdate((ViewGroup) this);
            this.o.clear();
            a();
            this.s = 0;
            scrollTo(0, 0);
        }
        PagerAdapter pagerAdapter2 = this.r;
        this.r = pagerAdapter;
        this.l = 0;
        if (this.r != null) {
            if (this.x == null) {
                this.x = new PagerObserver();
            }
            this.r.registerDataSetObserver(this.x);
            this.I = false;
            boolean z2 = this.aj;
            this.aj = true;
            this.l = this.r.getCount();
            if (this.t >= 0) {
                this.r.restoreState(this.u, this.v);
                setCurrentItemInternal(this.t, false, true);
                this.t = -1;
                this.u = null;
                this.v = null;
            } else if (!z2) {
                populate();
            } else {
                requestLayout();
            }
        }
        if (this.ap != null && pagerAdapter2 != pagerAdapter) {
            this.ap.a(pagerAdapter2, pagerAdapter);
        }
    }

    private void a() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((LayoutParams) getChildAt(i2).getLayoutParams()).f7174a) {
                removeViewAt(i2);
                i2--;
            }
            i2++;
        }
    }

    public PagerAdapter getAdapter() {
        return this.r;
    }

    /* access modifiers changed from: package-private */
    public void setOnAdapterChangeListener(OnAdapterChangeListener onAdapterChangeListener) {
        this.ap = onAdapterChangeListener;
    }

    private int getClientHeight() {
        return (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
    }

    public void setCurrentItem(int i2) {
        this.I = false;
        setCurrentItemInternal(i2, !this.aj, false);
    }

    public void setCurrentItem(int i2, boolean z2) {
        this.I = false;
        setCurrentItemInternal(i2, z2, false);
    }

    public int getCurrentItem() {
        return this.s;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentItemInternal(int i2, boolean z2, boolean z3) {
        setCurrentItemInternal(i2, z2, z3, 0);
    }

    /* access modifiers changed from: package-private */
    public void setCurrentItemInternal(int i2, boolean z2, boolean z3, int i3) {
        if (this.r == null || this.r.getCount() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (z3 || this.s != i2 || this.o.size() == 0) {
            boolean z4 = true;
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 >= this.r.getCount()) {
                i2 = this.r.getCount() - 1;
            }
            int i4 = this.J;
            if (i2 > this.s + i4 || i2 < this.s - i4) {
                for (int i5 = 0; i5 < this.o.size(); i5++) {
                    this.o.get(i5).c = true;
                }
            }
            if (this.s == i2) {
                z4 = false;
            }
            if (this.aj) {
                this.s = i2;
                if (z4 && this.an != null) {
                    this.an.onPageSelected(i2);
                }
                if (z4 && this.ao != null) {
                    this.ao.onPageSelected(i2);
                }
                requestLayout();
                return;
            }
            populate(i2);
            a(i2, z2, i3, z4);
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    private void a(int i2, boolean z2, int i3, boolean z3) {
        ItemInfo infoForPosition = infoForPosition(i2);
        int clientHeight = infoForPosition != null ? (int) (((float) getClientHeight()) * Math.max(this.C, Math.min(infoForPosition.e, this.D))) : 0;
        if (z2) {
            smoothScrollTo(0, clientHeight, i3);
            if (z3 && this.an != null) {
                this.an.onPageSelected(i2);
            }
            if (z3 && this.ao != null) {
                this.ao.onPageSelected(i2);
                return;
            }
            return;
        }
        if (z3 && this.an != null) {
            this.an.onPageSelected(i2);
        }
        if (z3 && this.ao != null) {
            this.ao.onPageSelected(i2);
        }
        a(false);
        scrollTo(0, clientHeight);
        a(clientHeight);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.an = onPageChangeListener;
    }

    public void setPageTransformer(boolean z2, ViewPager.PageTransformer pageTransformer) {
        if (Build.VERSION.SDK_INT >= 11) {
            int i2 = 1;
            boolean z3 = pageTransformer != null;
            boolean z4 = z3 != (this.aq != null);
            this.aq = pageTransformer;
            setChildrenDrawingOrderEnabledCompat(z3);
            if (z3) {
                if (z2) {
                    i2 = 2;
                }
                this.av = i2;
            } else {
                this.av = 0;
            }
            if (z4) {
                populate();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setChildrenDrawingOrderEnabledCompat(boolean z2) {
        if (Build.VERSION.SDK_INT >= 7) {
            if (this.ar == null) {
                Class<ViewGroup> cls = ViewGroup.class;
                try {
                    this.ar = cls.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
                } catch (NoSuchMethodException e2) {
                    Log.e(f7171a, "Can't find setChildrenDrawingOrderEnabled", e2);
                }
            }
            try {
                this.ar.invoke(this, new Object[]{Boolean.valueOf(z2)});
            } catch (Exception e3) {
                Log.e(f7171a, "Error changing children drawing order", e3);
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i2, int i3) {
        if (this.av == 2) {
            i3 = (i2 - 1) - i3;
        }
        return ((LayoutParams) this.aw.get(i3).getLayoutParams()).f;
    }

    /* access modifiers changed from: package-private */
    public ViewPager.OnPageChangeListener setInternalPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        ViewPager.OnPageChangeListener onPageChangeListener2 = this.ao;
        this.ao = onPageChangeListener;
        return onPageChangeListener2;
    }

    public int getOffscreenPageLimit() {
        return this.J;
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            Log.w(f7171a, "Requested offscreen page limit " + i2 + " too small; defaulting to " + 1);
            i2 = 1;
        }
        if (i2 != this.J) {
            this.J = i2;
            populate();
        }
    }

    public void setPageMargin(int i2) {
        int i3 = this.y;
        this.y = i2;
        int height = getHeight();
        a(height, height, i2, i3);
        requestLayout();
    }

    public int getPageMargin() {
        return this.y;
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.z = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageMarginDrawable(int i2) {
        setPageMarginDrawable(getContext().getResources().getDrawable(i2));
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.z;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.z;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    /* access modifiers changed from: package-private */
    public float distanceInfluenceForSnapDuration(float f2) {
        double d2 = (double) (f2 - 0.5f);
        Double.isNaN(d2);
        return (float) Math.sin((double) ((float) (d2 * 0.4712389167638204d)));
    }

    /* access modifiers changed from: package-private */
    public void smoothScrollTo(int i2, int i3) {
        smoothScrollTo(i2, i3, 0);
    }

    /* access modifiers changed from: package-private */
    public void smoothScrollTo(int i2, int i3, int i4) {
        int i5;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i6 = i2 - scrollX;
        int i7 = i3 - scrollY;
        if (i6 == 0 && i7 == 0) {
            a(false);
            populate();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientHeight = getClientHeight();
        int i8 = clientHeight / 2;
        float f2 = (float) clientHeight;
        float f3 = (float) i8;
        float distanceInfluenceForSnapDuration = f3 + (distanceInfluenceForSnapDuration(Math.min(1.0f, (((float) Math.abs(i6)) * 1.0f) / f2)) * f3);
        int abs = Math.abs(i4);
        if (abs > 0) {
            i5 = Math.round(Math.abs(distanceInfluenceForSnapDuration / ((float) abs)) * 1000.0f) * 4;
        } else {
            i5 = (int) (((((float) Math.abs(i6)) / ((f2 * this.r.getPageWidth(this.s)) + ((float) this.y))) + 1.0f) * 100.0f);
        }
        this.w.startScroll(scrollX, scrollY, i6, i7, Math.min(i5, 600));
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /* access modifiers changed from: package-private */
    public ItemInfo addNewItem(int i2, int i3) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.b = i2;
        itemInfo.f7173a = this.r.instantiateItem((ViewGroup) this, i2);
        itemInfo.d = this.r.getPageWidth(i2);
        if (i3 < 0 || i3 >= this.o.size()) {
            this.o.add(itemInfo);
        } else {
            this.o.add(i3, itemInfo);
        }
        return itemInfo;
    }

    /* access modifiers changed from: package-private */
    public void dataSetChanged() {
        int count = this.r.getCount();
        this.l = count;
        boolean z2 = this.o.size() < (this.J * 2) + 1 && this.o.size() < count;
        int i2 = this.s;
        int i3 = 0;
        boolean z3 = false;
        while (i3 < this.o.size()) {
            ItemInfo itemInfo = this.o.get(i3);
            int itemPosition = this.r.getItemPosition(itemInfo.f7173a);
            if (itemPosition != -1) {
                if (itemPosition == -2) {
                    this.o.remove(i3);
                    i3--;
                    if (!z3) {
                        this.r.startUpdate((ViewGroup) this);
                        z3 = true;
                    }
                    this.r.destroyItem((ViewGroup) this, itemInfo.b, itemInfo.f7173a);
                    if (this.s == itemInfo.b) {
                        i2 = Math.max(0, Math.min(this.s, count - 1));
                    }
                } else if (itemInfo.b != itemPosition) {
                    if (itemInfo.b == this.s) {
                        i2 = itemPosition;
                    }
                    itemInfo.b = itemPosition;
                }
                z2 = true;
            }
            i3++;
        }
        if (z3) {
            this.r.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.o, m);
        if (z2) {
            int childCount = getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i4).getLayoutParams();
                if (!layoutParams.f7174a) {
                    layoutParams.c = 0.0f;
                }
            }
            setCurrentItemInternal(i2, false, true);
            requestLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void populate() {
        populate(this.s);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0070, code lost:
        if (r9.b == r0.s) goto L_0x0077;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void populate(int r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            int r2 = r0.s
            if (r2 == r1) goto L_0x001a
            int r2 = r0.s
            if (r2 >= r1) goto L_0x000f
            r2 = 130(0x82, float:1.82E-43)
            goto L_0x0011
        L_0x000f:
            r2 = 33
        L_0x0011:
            int r4 = r0.s
            com.mi.global.shop.widget.VerticalViewPager$ItemInfo r4 = r0.infoForPosition(r4)
            r0.s = r1
            goto L_0x001c
        L_0x001a:
            r2 = 2
            r4 = 0
        L_0x001c:
            android.support.v4.view.PagerAdapter r1 = r0.r
            if (r1 != 0) goto L_0x0024
            r17.b()
            return
        L_0x0024:
            boolean r1 = r0.I
            if (r1 == 0) goto L_0x002c
            r17.b()
            return
        L_0x002c:
            android.os.IBinder r1 = r17.getWindowToken()
            if (r1 != 0) goto L_0x0033
            return
        L_0x0033:
            android.support.v4.view.PagerAdapter r1 = r0.r
            r1.startUpdate((android.view.ViewGroup) r0)
            int r1 = r0.J
            int r5 = r0.s
            int r5 = r5 - r1
            r6 = 0
            int r5 = java.lang.Math.max(r6, r5)
            android.support.v4.view.PagerAdapter r7 = r0.r
            int r7 = r7.getCount()
            int r8 = r7 + -1
            int r9 = r0.s
            int r9 = r9 + r1
            int r1 = java.lang.Math.min(r8, r9)
            int r8 = r0.l
            if (r7 != r8) goto L_0x0224
            r8 = 0
        L_0x0056:
            java.util.ArrayList<com.mi.global.shop.widget.VerticalViewPager$ItemInfo> r9 = r0.o
            int r9 = r9.size()
            if (r8 >= r9) goto L_0x0076
            java.util.ArrayList<com.mi.global.shop.widget.VerticalViewPager$ItemInfo> r9 = r0.o
            java.lang.Object r9 = r9.get(r8)
            com.mi.global.shop.widget.VerticalViewPager$ItemInfo r9 = (com.mi.global.shop.widget.VerticalViewPager.ItemInfo) r9
            int r10 = r9.b
            int r11 = r0.s
            if (r10 < r11) goto L_0x0073
            int r10 = r9.b
            int r11 = r0.s
            if (r10 != r11) goto L_0x0076
            goto L_0x0077
        L_0x0073:
            int r8 = r8 + 1
            goto L_0x0056
        L_0x0076:
            r9 = 0
        L_0x0077:
            if (r9 != 0) goto L_0x0081
            if (r7 <= 0) goto L_0x0081
            int r9 = r0.s
            com.mi.global.shop.widget.VerticalViewPager$ItemInfo r9 = r0.addNewItem(r9, r8)
        L_0x0081:
            if (r9 == 0) goto L_0x01a2
            int r11 = r8 + -1
            if (r11 < 0) goto L_0x0090
            java.util.ArrayList<com.mi.global.shop.widget.VerticalViewPager$ItemInfo> r12 = r0.o
            java.lang.Object r12 = r12.get(r11)
            com.mi.global.shop.widget.VerticalViewPager$ItemInfo r12 = (com.mi.global.shop.widget.VerticalViewPager.ItemInfo) r12
            goto L_0x0091
        L_0x0090:
            r12 = 0
        L_0x0091:
            int r13 = r17.getClientHeight()
            r14 = 1073741824(0x40000000, float:2.0)
            if (r13 > 0) goto L_0x009b
            r3 = 0
            goto L_0x00a7
        L_0x009b:
            float r15 = r9.d
            float r15 = r14 - r15
            int r3 = r17.getPaddingLeft()
            float r3 = (float) r3
            float r6 = (float) r13
            float r3 = r3 / r6
            float r3 = r3 + r15
        L_0x00a7:
            int r6 = r0.s
            int r6 = r6 + -1
            r15 = r8
            r8 = 0
        L_0x00ad:
            if (r6 < 0) goto L_0x010c
            int r16 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r16 < 0) goto L_0x00db
            if (r6 >= r5) goto L_0x00db
            if (r12 != 0) goto L_0x00b8
            goto L_0x010c
        L_0x00b8:
            int r10 = r12.b
            if (r6 != r10) goto L_0x0109
            boolean r10 = r12.c
            if (r10 != 0) goto L_0x0109
            java.util.ArrayList<com.mi.global.shop.widget.VerticalViewPager$ItemInfo> r10 = r0.o
            r10.remove(r11)
            android.support.v4.view.PagerAdapter r10 = r0.r
            java.lang.Object r12 = r12.f7173a
            r10.destroyItem((android.view.ViewGroup) r0, (int) r6, (java.lang.Object) r12)
            int r11 = r11 + -1
            int r15 = r15 + -1
            if (r11 < 0) goto L_0x0107
            java.util.ArrayList<com.mi.global.shop.widget.VerticalViewPager$ItemInfo> r10 = r0.o
            java.lang.Object r10 = r10.get(r11)
            com.mi.global.shop.widget.VerticalViewPager$ItemInfo r10 = (com.mi.global.shop.widget.VerticalViewPager.ItemInfo) r10
            goto L_0x0108
        L_0x00db:
            if (r12 == 0) goto L_0x00f1
            int r10 = r12.b
            if (r6 != r10) goto L_0x00f1
            float r10 = r12.d
            float r8 = r8 + r10
            int r11 = r11 + -1
            if (r11 < 0) goto L_0x0107
            java.util.ArrayList<com.mi.global.shop.widget.VerticalViewPager$ItemInfo> r10 = r0.o
            java.lang.Object r10 = r10.get(r11)
            com.mi.global.shop.widget.VerticalViewPager$ItemInfo r10 = (com.mi.global.shop.widget.VerticalViewPager.ItemInfo) r10
            goto L_0x0108
        L_0x00f1:
            int r10 = r11 + 1
            com.mi.global.shop.widget.VerticalViewPager$ItemInfo r10 = r0.addNewItem(r6, r10)
            float r10 = r10.d
            float r8 = r8 + r10
            int r15 = r15 + 1
            if (r11 < 0) goto L_0x0107
            java.util.ArrayList<com.mi.global.shop.widget.VerticalViewPager$ItemInfo> r10 = r0.o
            java.lang.Object r10 = r10.get(r11)
            com.mi.global.shop.widget.VerticalViewPager$ItemInfo r10 = (com.mi.global.shop.widget.VerticalViewPager.ItemInfo) r10
            goto L_0x0108
        L_0x0107:
            r10 = 0
        L_0x0108:
            r12 = r10
        L_0x0109:
            int r6 = r6 + -1
            goto L_0x00ad
        L_0x010c:
            float r3 = r9.d
            int r5 = r15 + 1
            int r6 = (r3 > r14 ? 1 : (r3 == r14 ? 0 : -1))
            if (r6 >= 0) goto L_0x019f
            java.util.ArrayList<com.mi.global.shop.widget.VerticalViewPager$ItemInfo> r6 = r0.o
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x0125
            java.util.ArrayList<com.mi.global.shop.widget.VerticalViewPager$ItemInfo> r6 = r0.o
            java.lang.Object r6 = r6.get(r5)
            com.mi.global.shop.widget.VerticalViewPager$ItemInfo r6 = (com.mi.global.shop.widget.VerticalViewPager.ItemInfo) r6
            goto L_0x0126
        L_0x0125:
            r6 = 0
        L_0x0126:
            if (r13 > 0) goto L_0x012a
            r10 = 0
            goto L_0x0133
        L_0x012a:
            int r8 = r17.getPaddingRight()
            float r8 = (float) r8
            float r10 = (float) r13
            float r8 = r8 / r10
            float r10 = r8 + r14
        L_0x0133:
            int r8 = r0.s
        L_0x0135:
            int r8 = r8 + 1
            if (r8 >= r7) goto L_0x019f
            int r11 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r11 < 0) goto L_0x0169
            if (r8 <= r1) goto L_0x0169
            if (r6 != 0) goto L_0x0142
            goto L_0x019f
        L_0x0142:
            int r11 = r6.b
            if (r8 != r11) goto L_0x019e
            boolean r11 = r6.c
            if (r11 != 0) goto L_0x019e
            java.util.ArrayList<com.mi.global.shop.widget.VerticalViewPager$ItemInfo> r11 = r0.o
            r11.remove(r5)
            android.support.v4.view.PagerAdapter r11 = r0.r
            java.lang.Object r6 = r6.f7173a
            r11.destroyItem((android.view.ViewGroup) r0, (int) r8, (java.lang.Object) r6)
            java.util.ArrayList<com.mi.global.shop.widget.VerticalViewPager$ItemInfo> r6 = r0.o
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x0167
            java.util.ArrayList<com.mi.global.shop.widget.VerticalViewPager$ItemInfo> r6 = r0.o
            java.lang.Object r6 = r6.get(r5)
            com.mi.global.shop.widget.VerticalViewPager$ItemInfo r6 = (com.mi.global.shop.widget.VerticalViewPager.ItemInfo) r6
            goto L_0x019e
        L_0x0167:
            r6 = 0
            goto L_0x019e
        L_0x0169:
            if (r6 == 0) goto L_0x0185
            int r11 = r6.b
            if (r8 != r11) goto L_0x0185
            float r6 = r6.d
            float r3 = r3 + r6
            int r5 = r5 + 1
            java.util.ArrayList<com.mi.global.shop.widget.VerticalViewPager$ItemInfo> r6 = r0.o
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x0167
            java.util.ArrayList<com.mi.global.shop.widget.VerticalViewPager$ItemInfo> r6 = r0.o
            java.lang.Object r6 = r6.get(r5)
            com.mi.global.shop.widget.VerticalViewPager$ItemInfo r6 = (com.mi.global.shop.widget.VerticalViewPager.ItemInfo) r6
            goto L_0x019e
        L_0x0185:
            com.mi.global.shop.widget.VerticalViewPager$ItemInfo r6 = r0.addNewItem(r8, r5)
            int r5 = r5 + 1
            float r6 = r6.d
            float r3 = r3 + r6
            java.util.ArrayList<com.mi.global.shop.widget.VerticalViewPager$ItemInfo> r6 = r0.o
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x0167
            java.util.ArrayList<com.mi.global.shop.widget.VerticalViewPager$ItemInfo> r6 = r0.o
            java.lang.Object r6 = r6.get(r5)
            com.mi.global.shop.widget.VerticalViewPager$ItemInfo r6 = (com.mi.global.shop.widget.VerticalViewPager.ItemInfo) r6
        L_0x019e:
            goto L_0x0135
        L_0x019f:
            r0.a(r9, r15, r4)
        L_0x01a2:
            android.support.v4.view.PagerAdapter r1 = r0.r
            int r3 = r0.s
            if (r9 == 0) goto L_0x01ab
            java.lang.Object r4 = r9.f7173a
            goto L_0x01ac
        L_0x01ab:
            r4 = 0
        L_0x01ac:
            r1.setPrimaryItem((android.view.ViewGroup) r0, (int) r3, (java.lang.Object) r4)
            android.support.v4.view.PagerAdapter r1 = r0.r
            r1.finishUpdate((android.view.ViewGroup) r0)
            int r1 = r17.getChildCount()
            r3 = 0
        L_0x01b9:
            if (r3 >= r1) goto L_0x01e5
            android.view.View r4 = r0.getChildAt(r3)
            android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
            com.mi.global.shop.widget.VerticalViewPager$LayoutParams r5 = (com.mi.global.shop.widget.VerticalViewPager.LayoutParams) r5
            r5.f = r3
            boolean r6 = r5.f7174a
            if (r6 != 0) goto L_0x01e1
            float r6 = r5.c
            r7 = 0
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 != 0) goto L_0x01e2
            com.mi.global.shop.widget.VerticalViewPager$ItemInfo r4 = r0.infoForChild(r4)
            if (r4 == 0) goto L_0x01e2
            float r6 = r4.d
            r5.c = r6
            int r4 = r4.b
            r5.e = r4
            goto L_0x01e2
        L_0x01e1:
            r7 = 0
        L_0x01e2:
            int r3 = r3 + 1
            goto L_0x01b9
        L_0x01e5:
            r17.b()
            boolean r1 = r17.hasFocus()
            if (r1 == 0) goto L_0x0223
            android.view.View r1 = r17.findFocus()
            if (r1 == 0) goto L_0x01f9
            com.mi.global.shop.widget.VerticalViewPager$ItemInfo r3 = r0.infoForAnyChild(r1)
            goto L_0x01fa
        L_0x01f9:
            r3 = 0
        L_0x01fa:
            if (r3 == 0) goto L_0x0202
            int r1 = r3.b
            int r3 = r0.s
            if (r1 == r3) goto L_0x0223
        L_0x0202:
            r1 = 0
        L_0x0203:
            int r3 = r17.getChildCount()
            if (r1 >= r3) goto L_0x0223
            android.view.View r3 = r0.getChildAt(r1)
            com.mi.global.shop.widget.VerticalViewPager$ItemInfo r4 = r0.infoForChild(r3)
            if (r4 == 0) goto L_0x0220
            int r4 = r4.b
            int r5 = r0.s
            if (r4 != r5) goto L_0x0220
            boolean r3 = r3.requestFocus(r2)
            if (r3 == 0) goto L_0x0220
            goto L_0x0223
        L_0x0220:
            int r1 = r1 + 1
            goto L_0x0203
        L_0x0223:
            return
        L_0x0224:
            android.content.res.Resources r1 = r17.getResources()     // Catch:{ NotFoundException -> 0x0231 }
            int r2 = r17.getId()     // Catch:{ NotFoundException -> 0x0231 }
            java.lang.String r1 = r1.getResourceName(r2)     // Catch:{ NotFoundException -> 0x0231 }
            goto L_0x0239
        L_0x0231:
            int r1 = r17.getId()
            java.lang.String r1 = java.lang.Integer.toHexString(r1)
        L_0x0239:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: "
            r3.append(r4)
            int r4 = r0.l
            r3.append(r4)
            java.lang.String r4 = ", found: "
            r3.append(r4)
            r3.append(r7)
            java.lang.String r4 = " Pager id: "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = " Pager class: "
            r3.append(r1)
            java.lang.Class r1 = r17.getClass()
            r3.append(r1)
            java.lang.String r1 = " Problematic adapter: "
            r3.append(r1)
            android.support.v4.view.PagerAdapter r1 = r0.r
            java.lang.Class r1 = r1.getClass()
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.widget.VerticalViewPager.populate(int):void");
    }

    private void b() {
        if (this.av != 0) {
            if (this.aw == null) {
                this.aw = new ArrayList<>();
            } else {
                this.aw.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.aw.add(getChildAt(i2));
            }
            Collections.sort(this.aw, ax);
        }
    }

    private void a(ItemInfo itemInfo, int i2, ItemInfo itemInfo2) {
        ItemInfo itemInfo3;
        ItemInfo itemInfo4;
        int count = this.r.getCount();
        int clientHeight = getClientHeight();
        float f2 = clientHeight > 0 ? ((float) this.y) / ((float) clientHeight) : 0.0f;
        if (itemInfo2 != null) {
            int i3 = itemInfo2.b;
            if (i3 < itemInfo.b) {
                float f3 = itemInfo2.e + itemInfo2.d + f2;
                int i4 = i3 + 1;
                int i5 = 0;
                while (i4 <= itemInfo.b && i5 < this.o.size()) {
                    Object obj = this.o.get(i5);
                    while (true) {
                        itemInfo4 = (ItemInfo) obj;
                        if (i4 > itemInfo4.b && i5 < this.o.size() - 1) {
                            i5++;
                            obj = this.o.get(i5);
                        }
                    }
                    while (i4 < itemInfo4.b) {
                        f3 += this.r.getPageWidth(i4) + f2;
                        i4++;
                    }
                    itemInfo4.e = f3;
                    f3 += itemInfo4.d + f2;
                    i4++;
                }
            } else if (i3 > itemInfo.b) {
                int size = this.o.size() - 1;
                float f4 = itemInfo2.e;
                while (true) {
                    i3--;
                    if (i3 < itemInfo.b || size < 0) {
                        break;
                    }
                    Object obj2 = this.o.get(size);
                    while (true) {
                        itemInfo3 = (ItemInfo) obj2;
                        if (i3 < itemInfo3.b && size > 0) {
                            size--;
                            obj2 = this.o.get(size);
                        }
                    }
                    while (i3 > itemInfo3.b) {
                        f4 -= this.r.getPageWidth(i3) + f2;
                        i3--;
                    }
                    f4 -= itemInfo3.d + f2;
                    itemInfo3.e = f4;
                }
            }
        }
        int size2 = this.o.size();
        float f5 = itemInfo.e;
        int i6 = itemInfo.b - 1;
        this.C = itemInfo.b == 0 ? itemInfo.e : -3.4028235E38f;
        int i7 = count - 1;
        this.D = itemInfo.b == i7 ? (itemInfo.e + itemInfo.d) - 1.0f : Float.MAX_VALUE;
        int i8 = i2 - 1;
        while (i8 >= 0) {
            ItemInfo itemInfo5 = this.o.get(i8);
            while (i6 > itemInfo5.b) {
                f5 -= this.r.getPageWidth(i6) + f2;
                i6--;
            }
            f5 -= itemInfo5.d + f2;
            itemInfo5.e = f5;
            if (itemInfo5.b == 0) {
                this.C = f5;
            }
            i8--;
            i6--;
        }
        float f6 = itemInfo.e + itemInfo.d + f2;
        int i9 = itemInfo.b + 1;
        int i10 = i2 + 1;
        while (i10 < size2) {
            ItemInfo itemInfo6 = this.o.get(i10);
            while (i9 < itemInfo6.b) {
                f6 += this.r.getPageWidth(i9) + f2;
                i9++;
            }
            if (itemInfo6.b == i7) {
                this.D = (itemInfo6.d + f6) - 1.0f;
            }
            itemInfo6.e = f6;
            f6 += itemInfo6.d + f2;
            i10++;
            i9++;
        }
        this.ak = false;
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        });

        /* renamed from: a  reason: collision with root package name */
        int f7177a;
        Parcelable b;
        ClassLoader c;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f7177a);
            parcel.writeParcelable(this.b, i);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f7177a + "}";
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.f7177a = parcel.readInt();
            this.b = parcel.readParcelable(classLoader);
            this.c = classLoader;
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f7177a = this.s;
        if (this.r != null) {
            savedState.b = this.r.saveState();
        }
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (this.r != null) {
            this.r.restoreState(savedState.b, savedState.c);
            setCurrentItemInternal(savedState.f7177a, false, true);
            return;
        }
        this.t = savedState.f7177a;
        this.u = savedState.b;
        this.v = savedState.c;
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        layoutParams2.f7174a |= view instanceof Decor;
        if (!this.G) {
            super.addView(view, i2, layoutParams);
        } else if (layoutParams2 == null || !layoutParams2.f7174a) {
            layoutParams2.d = true;
            addViewInLayout(view, i2, layoutParams);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    public void removeView(View view) {
        if (this.G) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    /* access modifiers changed from: package-private */
    public ItemInfo infoForChild(View view) {
        for (int i2 = 0; i2 < this.o.size(); i2++) {
            ItemInfo itemInfo = this.o.get(i2);
            if (this.r.isViewFromObject(view, itemInfo.f7173a)) {
                return itemInfo;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ItemInfo infoForAnyChild(View view) {
        while (true) {
            ViewParent parent = view.getParent();
            if (parent == this) {
                return infoForChild(view);
            }
            if (parent == null || !(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
    }

    /* access modifiers changed from: package-private */
    public ItemInfo infoForPosition(int i2) {
        for (int i3 = 0; i3 < this.o.size(); i3++) {
            ItemInfo itemInfo = this.o.get(i3);
            if (itemInfo.b == i2) {
                return itemInfo;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.aj = true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00b9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r17, int r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = 0
            r2 = r17
            int r2 = getDefaultSize(r1, r2)
            r3 = r18
            int r3 = getDefaultSize(r1, r3)
            r0.setMeasuredDimension(r2, r3)
            int r2 = r16.getMeasuredHeight()
            int r3 = r2 / 10
            int r4 = r0.N
            int r3 = java.lang.Math.min(r3, r4)
            r0.O = r3
            int r3 = r16.getMeasuredWidth()
            int r4 = r16.getPaddingLeft()
            int r3 = r3 - r4
            int r4 = r16.getPaddingRight()
            int r3 = r3 - r4
            int r4 = r16.getPaddingTop()
            int r2 = r2 - r4
            int r4 = r16.getPaddingBottom()
            int r2 = r2 - r4
            int r4 = r16.getChildCount()
            r5 = r2
            r2 = 0
        L_0x003e:
            r6 = 8
            r7 = 1
            r8 = 1073741824(0x40000000, float:2.0)
            if (r2 >= r4) goto L_0x00c5
            android.view.View r9 = r0.getChildAt(r2)
            int r10 = r9.getVisibility()
            if (r10 == r6) goto L_0x00c0
            android.view.ViewGroup$LayoutParams r6 = r9.getLayoutParams()
            com.mi.global.shop.widget.VerticalViewPager$LayoutParams r6 = (com.mi.global.shop.widget.VerticalViewPager.LayoutParams) r6
            if (r6 == 0) goto L_0x00c0
            boolean r10 = r6.f7174a
            if (r10 == 0) goto L_0x00c0
            int r10 = r6.b
            r10 = r10 & 7
            int r11 = r6.b
            r11 = r11 & 112(0x70, float:1.57E-43)
            r12 = 48
            if (r11 == r12) goto L_0x006e
            r12 = 80
            if (r11 != r12) goto L_0x006c
            goto L_0x006e
        L_0x006c:
            r11 = 0
            goto L_0x006f
        L_0x006e:
            r11 = 1
        L_0x006f:
            r12 = 3
            if (r10 == r12) goto L_0x0077
            r12 = 5
            if (r10 != r12) goto L_0x0076
            goto L_0x0077
        L_0x0076:
            r7 = 0
        L_0x0077:
            r10 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r11 == 0) goto L_0x0080
            r10 = 1073741824(0x40000000, float:2.0)
        L_0x007d:
            r12 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x0084
        L_0x0080:
            if (r7 == 0) goto L_0x007d
            r12 = 1073741824(0x40000000, float:2.0)
        L_0x0084:
            int r13 = r6.width
            r14 = -1
            r15 = -2
            if (r13 == r15) goto L_0x0096
            int r10 = r6.width
            if (r10 == r14) goto L_0x0092
            int r10 = r6.width
            r13 = r10
            goto L_0x0093
        L_0x0092:
            r13 = r3
        L_0x0093:
            r10 = 1073741824(0x40000000, float:2.0)
            goto L_0x0097
        L_0x0096:
            r13 = r3
        L_0x0097:
            int r1 = r6.height
            if (r1 == r15) goto L_0x00a4
            int r1 = r6.height
            if (r1 == r14) goto L_0x00a2
            int r1 = r6.height
            goto L_0x00a6
        L_0x00a2:
            r1 = r5
            goto L_0x00a6
        L_0x00a4:
            r1 = r5
            r8 = r12
        L_0x00a6:
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r13, r10)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r8)
            r9.measure(r6, r1)
            if (r11 == 0) goto L_0x00b9
            int r1 = r9.getMeasuredHeight()
            int r5 = r5 - r1
            goto L_0x00c0
        L_0x00b9:
            if (r7 == 0) goto L_0x00c0
            int r1 = r9.getMeasuredWidth()
            int r3 = r3 - r1
        L_0x00c0:
            int r2 = r2 + 1
            r1 = 0
            goto L_0x003e
        L_0x00c5:
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r8)
            r0.E = r1
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r8)
            r0.F = r1
            r0.G = r7
            r16.populate()
            r1 = 0
            r0.G = r1
            int r2 = r16.getChildCount()
        L_0x00dd:
            if (r1 >= r2) goto L_0x0107
            android.view.View r3 = r0.getChildAt(r1)
            int r4 = r3.getVisibility()
            if (r4 == r6) goto L_0x0104
            android.view.ViewGroup$LayoutParams r4 = r3.getLayoutParams()
            com.mi.global.shop.widget.VerticalViewPager$LayoutParams r4 = (com.mi.global.shop.widget.VerticalViewPager.LayoutParams) r4
            if (r4 == 0) goto L_0x00f5
            boolean r7 = r4.f7174a
            if (r7 != 0) goto L_0x0104
        L_0x00f5:
            float r7 = (float) r5
            float r4 = r4.c
            float r7 = r7 * r4
            int r4 = (int) r7
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r8)
            int r7 = r0.E
            r3.measure(r7, r4)
        L_0x0104:
            int r1 = r1 + 1
            goto L_0x00dd
        L_0x0107:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.widget.VerticalViewPager.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i3 != i5) {
            a(i3, i5, this.y, this.y);
        }
    }

    private void a(int i2, int i3, int i4, int i5) {
        if (i3 <= 0 || this.o.isEmpty()) {
            ItemInfo infoForPosition = infoForPosition(this.s);
            int min = (int) ((infoForPosition != null ? Math.min(infoForPosition.e, this.D) : 0.0f) * ((float) ((i2 - getPaddingTop()) - getPaddingBottom())));
            if (min != getScrollY()) {
                a(false);
                scrollTo(getScrollX(), min);
                return;
            }
            return;
        }
        int scrollY = (int) ((((float) getScrollY()) / ((float) (((i3 - getPaddingTop()) - getPaddingBottom()) + i5))) * ((float) (((i2 - getPaddingTop()) - getPaddingBottom()) + i4)));
        scrollTo(getScrollX(), scrollY);
        if (!this.w.isFinished()) {
            this.w.startScroll(0, scrollY, 0, (int) (infoForPosition(this.s).e * ((float) i2)), this.w.getDuration() - this.w.timePassed());
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        boolean z3;
        ItemInfo infoForChild;
        int i6;
        int i7;
        int childCount = getChildCount();
        int i8 = i4 - i2;
        int i9 = i5 - i3;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollY = getScrollY();
        int i10 = paddingRight;
        int i11 = 0;
        int i12 = paddingLeft;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.f7174a) {
                    int i14 = layoutParams.b & 7;
                    int i15 = layoutParams.b & 112;
                    if (i14 == 1) {
                        i6 = Math.max((i8 - childAt.getMeasuredWidth()) / 2, i12);
                    } else if (i14 == 3) {
                        i6 = i12;
                        i12 = childAt.getMeasuredWidth() + i12;
                    } else if (i14 != 5) {
                        i6 = i12;
                    } else {
                        i6 = (i8 - i10) - childAt.getMeasuredWidth();
                        i10 += childAt.getMeasuredWidth();
                    }
                    if (i15 == 16) {
                        i7 = Math.max((i9 - childAt.getMeasuredHeight()) / 2, paddingTop);
                    } else if (i15 == 48) {
                        i7 = paddingTop;
                        paddingTop = childAt.getMeasuredHeight() + paddingTop;
                    } else if (i15 != 80) {
                        i7 = paddingTop;
                    } else {
                        i7 = (i9 - paddingBottom) - childAt.getMeasuredHeight();
                        paddingBottom += childAt.getMeasuredHeight();
                    }
                    int i16 = i7 + scrollY;
                    childAt.layout(i6, i16, childAt.getMeasuredWidth() + i6, i16 + childAt.getMeasuredHeight());
                    i11++;
                }
            }
        }
        int i17 = (i9 - paddingTop) - paddingBottom;
        for (int i18 = 0; i18 < childCount; i18++) {
            View childAt2 = getChildAt(i18);
            if (childAt2.getVisibility() != 8) {
                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                if (!layoutParams2.f7174a && (infoForChild = infoForChild(childAt2)) != null) {
                    float f2 = (float) i17;
                    int i19 = ((int) (infoForChild.e * f2)) + paddingTop;
                    if (layoutParams2.d) {
                        layoutParams2.d = false;
                        childAt2.measure(View.MeasureSpec.makeMeasureSpec((i8 - i12) - i10, 1073741824), View.MeasureSpec.makeMeasureSpec((int) (f2 * layoutParams2.c), 1073741824));
                    }
                    childAt2.layout(i12, i19, childAt2.getMeasuredWidth() + i12, childAt2.getMeasuredHeight() + i19);
                }
            }
        }
        this.A = i12;
        this.B = i8 - i10;
        this.am = i11;
        if (this.aj) {
            z3 = false;
            a(this.s, false, 0, false);
        } else {
            z3 = false;
        }
        this.aj = z3;
    }

    public void computeScroll() {
        if (this.w.isFinished() || !this.w.computeScrollOffset()) {
            a(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.w.getCurrX();
        int currY = this.w.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            if (!a(currY)) {
                this.w.abortAnimation();
                scrollTo(currX, 0);
            }
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private boolean a(int i2) {
        if (this.o.size() == 0) {
            this.al = false;
            onPageScrolled(0, 0.0f, 0);
            if (this.al) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        ItemInfo c2 = c();
        int clientHeight = getClientHeight();
        int i3 = this.y + clientHeight;
        float f2 = (float) clientHeight;
        float f3 = ((float) this.y) / f2;
        int i4 = c2.b;
        float f4 = ((((float) i2) / f2) - c2.e) / (c2.d + f3);
        this.al = false;
        onPageScrolled(i4, f4, (int) (((float) i3) * f4));
        if (this.al) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0069  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPageScrolled(int r12, float r13, int r14) {
        /*
            r11 = this;
            int r0 = r11.am
            r1 = 0
            if (r0 <= 0) goto L_0x0070
            int r0 = r11.getScrollY()
            int r2 = r11.getPaddingTop()
            int r3 = r11.getPaddingBottom()
            int r4 = r11.getHeight()
            int r5 = r11.getChildCount()
            r6 = r3
            r3 = r2
            r2 = 0
        L_0x001c:
            if (r2 >= r5) goto L_0x0070
            android.view.View r7 = r11.getChildAt(r2)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            com.mi.global.shop.widget.VerticalViewPager$LayoutParams r8 = (com.mi.global.shop.widget.VerticalViewPager.LayoutParams) r8
            boolean r9 = r8.f7174a
            if (r9 != 0) goto L_0x002d
            goto L_0x006d
        L_0x002d:
            int r8 = r8.b
            r8 = r8 & 112(0x70, float:1.57E-43)
            r9 = 16
            if (r8 == r9) goto L_0x0052
            r9 = 48
            if (r8 == r9) goto L_0x004c
            r9 = 80
            if (r8 == r9) goto L_0x003f
            r8 = r3
            goto L_0x0061
        L_0x003f:
            int r8 = r4 - r6
            int r9 = r7.getMeasuredHeight()
            int r8 = r8 - r9
            int r9 = r7.getMeasuredHeight()
            int r6 = r6 + r9
            goto L_0x005e
        L_0x004c:
            int r8 = r7.getHeight()
            int r8 = r8 + r3
            goto L_0x0061
        L_0x0052:
            int r8 = r7.getMeasuredHeight()
            int r8 = r4 - r8
            int r8 = r8 / 2
            int r8 = java.lang.Math.max(r8, r3)
        L_0x005e:
            r10 = r8
            r8 = r3
            r3 = r10
        L_0x0061:
            int r3 = r3 + r0
            int r9 = r7.getTop()
            int r3 = r3 - r9
            if (r3 == 0) goto L_0x006c
            r7.offsetTopAndBottom(r3)
        L_0x006c:
            r3 = r8
        L_0x006d:
            int r2 = r2 + 1
            goto L_0x001c
        L_0x0070:
            android.support.v4.view.ViewPager$OnPageChangeListener r0 = r11.an
            if (r0 == 0) goto L_0x0079
            android.support.v4.view.ViewPager$OnPageChangeListener r0 = r11.an
            r0.onPageScrolled(r12, r13, r14)
        L_0x0079:
            android.support.v4.view.ViewPager$OnPageChangeListener r0 = r11.ao
            if (r0 == 0) goto L_0x0082
            android.support.v4.view.ViewPager$OnPageChangeListener r0 = r11.ao
            r0.onPageScrolled(r12, r13, r14)
        L_0x0082:
            android.support.v4.view.ViewPager$PageTransformer r12 = r11.aq
            if (r12 == 0) goto L_0x00b3
            int r12 = r11.getScrollY()
            int r13 = r11.getChildCount()
        L_0x008e:
            if (r1 >= r13) goto L_0x00b3
            android.view.View r14 = r11.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r0 = r14.getLayoutParams()
            com.mi.global.shop.widget.VerticalViewPager$LayoutParams r0 = (com.mi.global.shop.widget.VerticalViewPager.LayoutParams) r0
            boolean r0 = r0.f7174a
            if (r0 == 0) goto L_0x009f
            goto L_0x00b0
        L_0x009f:
            int r0 = r14.getTop()
            int r0 = r0 - r12
            float r0 = (float) r0
            int r2 = r11.getClientHeight()
            float r2 = (float) r2
            float r0 = r0 / r2
            android.support.v4.view.ViewPager$PageTransformer r2 = r11.aq
            r2.transformPage(r14, r0)
        L_0x00b0:
            int r1 = r1 + 1
            goto L_0x008e
        L_0x00b3:
            r12 = 1
            r11.al = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.widget.VerticalViewPager.onPageScrolled(int, float, int):void");
    }

    private void a(boolean z2) {
        boolean z3 = this.az == 2;
        if (z3) {
            setScrollingCacheEnabled(false);
            this.w.abortAnimation();
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.w.getCurrX();
            int currY = this.w.getCurrY();
            if (!(scrollX == currX && scrollY == currY)) {
                scrollTo(currX, currY);
            }
        }
        this.I = false;
        boolean z4 = z3;
        for (int i2 = 0; i2 < this.o.size(); i2++) {
            ItemInfo itemInfo = this.o.get(i2);
            if (itemInfo.c) {
                itemInfo.c = false;
                z4 = true;
            }
        }
        if (!z4) {
            return;
        }
        if (z2) {
            ViewCompat.postOnAnimation(this, this.ay);
        } else {
            this.ay.run();
        }
    }

    private boolean a(float f2, float f3) {
        return (f2 < ((float) this.O) && f3 > 0.0f) || (f2 > ((float) (getHeight() - this.O)) && f3 < 0.0f);
    }

    private void b(boolean z2) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            ViewCompat.setLayerType(getChildAt(i2), z2 ? 2 : 0, (Paint) null);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        try {
            int action = motionEvent.getAction() & 255;
            if (action != 3) {
                if (action != 1) {
                    if (action != 0) {
                        if (this.K) {
                            return true;
                        }
                        if (this.L) {
                            return false;
                        }
                    }
                    if (action == 0) {
                        float x2 = motionEvent.getX();
                        this.S = x2;
                        this.Q = x2;
                        float y2 = motionEvent.getY();
                        this.T = y2;
                        this.R = y2;
                        this.U = MotionEventCompat.getPointerId(motionEvent2, 0);
                        this.L = false;
                        this.w.computeScrollOffset();
                        if (this.az != 2 || Math.abs(this.w.getFinalY() - this.w.getCurrY()) <= this.ad) {
                            a(false);
                            this.K = false;
                        } else {
                            this.w.abortAnimation();
                            this.I = false;
                            populate();
                            this.K = true;
                            c(true);
                            setScrollState(1);
                        }
                    } else if (action == 2) {
                        int i2 = this.U;
                        if (i2 != -1) {
                            int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent2, i2);
                            float y3 = MotionEventCompat.getY(motionEvent2, findPointerIndex);
                            float f2 = y3 - this.R;
                            float abs = Math.abs(f2);
                            float x3 = MotionEventCompat.getX(motionEvent2, findPointerIndex);
                            float abs2 = Math.abs(x3 - this.S);
                            if (f2 != 0.0f && !a(this.R, f2)) {
                                if (canScroll(this, false, (int) f2, (int) x3, (int) y3)) {
                                    this.Q = x3;
                                    this.R = y3;
                                    this.L = true;
                                    return false;
                                }
                            }
                            if (abs > ((float) this.P) && abs * 0.5f > abs2) {
                                this.K = true;
                                c(true);
                                setScrollState(1);
                                this.R = f2 > 0.0f ? this.T + ((float) this.P) : this.T - ((float) this.P);
                                this.Q = x3;
                                setScrollingCacheEnabled(true);
                            } else if (abs2 > ((float) this.P)) {
                                this.L = true;
                            }
                            if (this.K && a(y3)) {
                                ViewCompat.postInvalidateOnAnimation(this);
                            }
                        }
                    } else if (action == 6) {
                        a(motionEvent);
                    }
                    if (this.W == null) {
                        this.W = VelocityTracker.obtain();
                    }
                    this.W.addMovement(motionEvent2);
                    return this.K;
                }
            }
            this.K = false;
            this.L = false;
            this.U = -1;
            if (this.W != null) {
                this.W.recycle();
                this.W = null;
            }
            return false;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r9) {
        /*
            r8 = this;
            r0 = 0
            boolean r1 = r8.af     // Catch:{ IllegalArgumentException -> 0x0169 }
            r2 = 1
            if (r1 == 0) goto L_0x0007
            return r2
        L_0x0007:
            int r1 = r9.getAction()     // Catch:{ IllegalArgumentException -> 0x0169 }
            if (r1 != 0) goto L_0x0014
            int r1 = r9.getEdgeFlags()     // Catch:{ IllegalArgumentException -> 0x0169 }
            if (r1 == 0) goto L_0x0014
            return r0
        L_0x0014:
            android.support.v4.view.PagerAdapter r1 = r8.r     // Catch:{ IllegalArgumentException -> 0x0169 }
            if (r1 == 0) goto L_0x0168
            android.support.v4.view.PagerAdapter r1 = r8.r     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r1 = r1.getCount()     // Catch:{ IllegalArgumentException -> 0x0169 }
            if (r1 != 0) goto L_0x0022
            goto L_0x0168
        L_0x0022:
            android.view.VelocityTracker r1 = r8.W     // Catch:{ IllegalArgumentException -> 0x0169 }
            if (r1 != 0) goto L_0x002c
            android.view.VelocityTracker r1 = android.view.VelocityTracker.obtain()     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.W = r1     // Catch:{ IllegalArgumentException -> 0x0169 }
        L_0x002c:
            android.view.VelocityTracker r1 = r8.W     // Catch:{ IllegalArgumentException -> 0x0169 }
            r1.addMovement(r9)     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r1 = r9.getAction()     // Catch:{ IllegalArgumentException -> 0x0169 }
            r1 = r1 & 255(0xff, float:3.57E-43)
            r3 = -1
            switch(r1) {
                case 0: goto L_0x0141;
                case 1: goto L_0x00eb;
                case 2: goto L_0x007d;
                case 3: goto L_0x0060;
                case 4: goto L_0x003b;
                case 5: goto L_0x004e;
                case 6: goto L_0x003d;
                default: goto L_0x003b;
            }     // Catch:{ IllegalArgumentException -> 0x0169 }
        L_0x003b:
            goto L_0x0161
        L_0x003d:
            r8.a((android.view.MotionEvent) r9)     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r1 = r8.U     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r1 = android.support.v4.view.MotionEventCompat.findPointerIndex(r9, r1)     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r9 = android.support.v4.view.MotionEventCompat.getY(r9, r1)     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.R = r9     // Catch:{ IllegalArgumentException -> 0x0169 }
            goto L_0x0161
        L_0x004e:
            int r1 = android.support.v4.view.MotionEventCompat.getActionIndex(r9)     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r3 = android.support.v4.view.MotionEventCompat.getY(r9, r1)     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.R = r3     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r9 = android.support.v4.view.MotionEventCompat.getPointerId(r9, r1)     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.U = r9     // Catch:{ IllegalArgumentException -> 0x0169 }
            goto L_0x0161
        L_0x0060:
            boolean r9 = r8.K     // Catch:{ IllegalArgumentException -> 0x0169 }
            if (r9 == 0) goto L_0x0161
            int r9 = r8.s     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.a((int) r9, (boolean) r2, (int) r0, (boolean) r0)     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.U = r3     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.d()     // Catch:{ IllegalArgumentException -> 0x0169 }
            android.support.v4.widget.EdgeEffectCompat r9 = r8.ah     // Catch:{ IllegalArgumentException -> 0x0169 }
            boolean r9 = r9.onRelease()     // Catch:{ IllegalArgumentException -> 0x0169 }
            android.support.v4.widget.EdgeEffectCompat r1 = r8.ai     // Catch:{ IllegalArgumentException -> 0x0169 }
            boolean r1 = r1.onRelease()     // Catch:{ IllegalArgumentException -> 0x0169 }
            r9 = r9 | r1
            goto L_0x0162
        L_0x007d:
            boolean r1 = r8.K     // Catch:{ IllegalArgumentException -> 0x0169 }
            if (r1 != 0) goto L_0x00d7
            int r1 = r8.U     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r1 = android.support.v4.view.MotionEventCompat.findPointerIndex(r9, r1)     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r3 = android.support.v4.view.MotionEventCompat.getY(r9, r1)     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r4 = r8.R     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r4 = r3 - r4
            float r4 = java.lang.Math.abs(r4)     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r1 = android.support.v4.view.MotionEventCompat.getX(r9, r1)     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r5 = r8.Q     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r5 = r1 - r5
            float r5 = java.lang.Math.abs(r5)     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r6 = r8.P     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r6 = (float) r6     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x00d7
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L_0x00d7
            r8.K = r2     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.c(r2)     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r4 = r8.T     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r3 = r3 - r4
            r4 = 0
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 <= 0) goto L_0x00be
            float r3 = r8.T     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r4 = r8.P     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r4 = (float) r4     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r3 = r3 + r4
            goto L_0x00c4
        L_0x00be:
            float r3 = r8.T     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r4 = r8.P     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r4 = (float) r4     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r3 = r3 - r4
        L_0x00c4:
            r8.R = r3     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.Q = r1     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.setScrollState(r2)     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.setScrollingCacheEnabled(r2)     // Catch:{ IllegalArgumentException -> 0x0169 }
            android.view.ViewParent r1 = r8.getParent()     // Catch:{ IllegalArgumentException -> 0x0169 }
            if (r1 == 0) goto L_0x00d7
            r1.requestDisallowInterceptTouchEvent(r2)     // Catch:{ IllegalArgumentException -> 0x0169 }
        L_0x00d7:
            boolean r1 = r8.K     // Catch:{ IllegalArgumentException -> 0x0169 }
            if (r1 == 0) goto L_0x0161
            int r1 = r8.U     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r1 = android.support.v4.view.MotionEventCompat.findPointerIndex(r9, r1)     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r9 = android.support.v4.view.MotionEventCompat.getY(r9, r1)     // Catch:{ IllegalArgumentException -> 0x0169 }
            boolean r9 = r8.a((float) r9)     // Catch:{ IllegalArgumentException -> 0x0169 }
            r9 = r9 | r0
            goto L_0x0162
        L_0x00eb:
            boolean r1 = r8.K     // Catch:{ IllegalArgumentException -> 0x0169 }
            if (r1 == 0) goto L_0x0161
            android.view.VelocityTracker r1 = r8.W     // Catch:{ IllegalArgumentException -> 0x0169 }
            r4 = 1000(0x3e8, float:1.401E-42)
            int r5 = r8.ab     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r5 = (float) r5     // Catch:{ IllegalArgumentException -> 0x0169 }
            r1.computeCurrentVelocity(r4, r5)     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r4 = r8.U     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r1 = android.support.v4.view.VelocityTrackerCompat.getYVelocity(r1, r4)     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r1 = (int) r1     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.I = r2     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r4 = r8.getClientHeight()     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r5 = r8.getScrollY()     // Catch:{ IllegalArgumentException -> 0x0169 }
            com.mi.global.shop.widget.VerticalViewPager$ItemInfo r6 = r8.c()     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r7 = r6.b     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r5 = (float) r5     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r4 = (float) r4     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r5 = r5 / r4
            float r4 = r6.e     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r5 = r5 - r4
            float r4 = r6.d     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r5 = r5 / r4
            int r4 = r8.U     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r4 = android.support.v4.view.MotionEventCompat.findPointerIndex(r9, r4)     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r9 = android.support.v4.view.MotionEventCompat.getY(r9, r4)     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r4 = r8.T     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r9 = r9 - r4
            int r9 = (int) r9     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r9 = r8.a((int) r7, (float) r5, (int) r1, (int) r9)     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.setCurrentItemInternal(r9, r2, r2, r1)     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.U = r3     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.d()     // Catch:{ IllegalArgumentException -> 0x0169 }
            android.support.v4.widget.EdgeEffectCompat r9 = r8.ah     // Catch:{ IllegalArgumentException -> 0x0169 }
            boolean r9 = r9.onRelease()     // Catch:{ IllegalArgumentException -> 0x0169 }
            android.support.v4.widget.EdgeEffectCompat r1 = r8.ai     // Catch:{ IllegalArgumentException -> 0x0169 }
            boolean r1 = r1.onRelease()     // Catch:{ IllegalArgumentException -> 0x0169 }
            r9 = r9 | r1
            goto L_0x0162
        L_0x0141:
            android.widget.Scroller r1 = r8.w     // Catch:{ IllegalArgumentException -> 0x0169 }
            r1.abortAnimation()     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.I = r0     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.populate()     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r1 = r9.getX()     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.S = r1     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.Q = r1     // Catch:{ IllegalArgumentException -> 0x0169 }
            float r1 = r9.getY()     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.T = r1     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.R = r1     // Catch:{ IllegalArgumentException -> 0x0169 }
            int r9 = android.support.v4.view.MotionEventCompat.getPointerId(r9, r0)     // Catch:{ IllegalArgumentException -> 0x0169 }
            r8.U = r9     // Catch:{ IllegalArgumentException -> 0x0169 }
        L_0x0161:
            r9 = 0
        L_0x0162:
            if (r9 == 0) goto L_0x0167
            android.support.v4.view.ViewCompat.postInvalidateOnAnimation(r8)     // Catch:{ IllegalArgumentException -> 0x0169 }
        L_0x0167:
            return r2
        L_0x0168:
            return r0
        L_0x0169:
            r9 = move-exception
            r9.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.widget.VerticalViewPager.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void c(boolean z2) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z2);
        }
    }

    private boolean a(float f2) {
        boolean z2;
        float f3 = this.R - f2;
        this.R = f2;
        float scrollY = ((float) getScrollY()) + f3;
        float clientHeight = (float) getClientHeight();
        float f4 = this.C * clientHeight;
        float f5 = this.D * clientHeight;
        boolean z3 = false;
        ItemInfo itemInfo = this.o.get(0);
        boolean z4 = true;
        ItemInfo itemInfo2 = this.o.get(this.o.size() - 1);
        if (itemInfo.b != 0) {
            f4 = itemInfo.e * clientHeight;
            z2 = false;
        } else {
            z2 = true;
        }
        if (itemInfo2.b != this.r.getCount() - 1) {
            f5 = itemInfo2.e * clientHeight;
            z4 = false;
        }
        if (scrollY < f4) {
            if (z2) {
                z3 = this.ah.onPull(Math.abs(f4 - scrollY) / clientHeight);
            }
            scrollY = f4;
        } else if (scrollY > f5) {
            if (z4) {
                z3 = this.ai.onPull(Math.abs(scrollY - f5) / clientHeight);
            }
            scrollY = f5;
        }
        int i2 = (int) scrollY;
        this.Q += scrollY - ((float) i2);
        scrollTo(getScrollX(), i2);
        a(i2);
        return z3;
    }

    private ItemInfo c() {
        int i2;
        int clientHeight = getClientHeight();
        float scrollY = clientHeight > 0 ? ((float) getScrollY()) / ((float) clientHeight) : 0.0f;
        float f2 = clientHeight > 0 ? ((float) this.y) / ((float) clientHeight) : 0.0f;
        ItemInfo itemInfo = null;
        int i3 = 0;
        boolean z2 = true;
        int i4 = -1;
        float f3 = 0.0f;
        float f4 = 0.0f;
        while (i3 < this.o.size()) {
            ItemInfo itemInfo2 = this.o.get(i3);
            if (!z2 && itemInfo2.b != (i2 = i4 + 1)) {
                itemInfo2 = this.p;
                itemInfo2.e = f3 + f4 + f2;
                itemInfo2.b = i2;
                itemInfo2.d = this.r.getPageWidth(itemInfo2.b);
                i3--;
            }
            f3 = itemInfo2.e;
            float f5 = itemInfo2.d + f3 + f2;
            if (!z2 && scrollY < f3) {
                return itemInfo;
            }
            if (scrollY < f5 || i3 == this.o.size() - 1) {
                return itemInfo2;
            }
            i4 = itemInfo2.b;
            f4 = itemInfo2.d;
            i3++;
            itemInfo = itemInfo2;
            z2 = false;
        }
        return itemInfo;
    }

    private int a(int i2, float f2, int i3, int i4) {
        if (Math.abs(i4) <= this.ac || Math.abs(i3) <= this.aa) {
            i2 = (int) (((float) i2) + f2 + (i2 >= this.s ? d : 0.1f));
        } else if (i3 <= 0) {
            i2++;
        }
        return this.o.size() > 0 ? Math.max(this.o.get(0).b, Math.min(i2, this.o.get(this.o.size() - 1).b)) : i2;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int overScrollMode = ViewCompat.getOverScrollMode(this);
        boolean z2 = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && this.r != null && this.r.getCount() > 1)) {
            if (!this.ah.isFinished()) {
                int save = canvas.save();
                int height = getHeight();
                int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.translate((float) getPaddingLeft(), this.C * ((float) height));
                this.ah.setSize(width, height);
                z2 = false | this.ah.draw(canvas);
                canvas.restoreToCount(save);
            }
            if (!this.ai.isFinished()) {
                int save2 = canvas.save();
                int height2 = getHeight();
                int width2 = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.rotate(180.0f);
                canvas.translate((float) ((-width2) - getPaddingLeft()), (-(this.D + 1.0f)) * ((float) height2));
                this.ai.setSize(width2, height2);
                z2 |= this.ai.draw(canvas);
                canvas.restoreToCount(save2);
            }
        } else {
            this.ah.finish();
            this.ai.finish();
        }
        if (z2) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f2;
        float f3;
        float f4;
        super.onDraw(canvas);
        if (this.y > 0 && this.z != null && this.o.size() > 0 && this.r != null) {
            int scrollY = getScrollY();
            int height = getHeight();
            float f5 = (float) height;
            float f6 = ((float) this.y) / f5;
            int i2 = 0;
            ItemInfo itemInfo = this.o.get(0);
            float f7 = itemInfo.e;
            int size = this.o.size();
            int i3 = itemInfo.b;
            int i4 = this.o.get(size - 1).b;
            while (i3 < i4) {
                while (i3 > itemInfo.b && i2 < size) {
                    i2++;
                    itemInfo = this.o.get(i2);
                }
                if (i3 == itemInfo.b) {
                    f3 = (itemInfo.e + itemInfo.d) * f5;
                    f2 = itemInfo.e + itemInfo.d + f6;
                } else {
                    float pageWidth = this.r.getPageWidth(i3);
                    f2 = f7 + pageWidth + f6;
                    f3 = (f7 + pageWidth) * f5;
                }
                if (((float) this.y) + f3 > ((float) scrollY)) {
                    f4 = f6;
                    this.z.setBounds(this.A, (int) f3, this.B, (int) (((float) this.y) + f3 + 0.5f));
                    this.z.draw(canvas);
                } else {
                    Canvas canvas2 = canvas;
                    f4 = f6;
                }
                if (f3 <= ((float) (scrollY + height))) {
                    i3++;
                    f7 = f2;
                    f6 = f4;
                } else {
                    return;
                }
            }
        }
    }

    public boolean beginFakeDrag() {
        if (this.K) {
            return false;
        }
        this.af = true;
        setScrollState(1);
        this.R = 0.0f;
        this.T = 0.0f;
        if (this.W == null) {
            this.W = VelocityTracker.obtain();
        } else {
            this.W.clear();
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, 0.0f, 0.0f, 0);
        this.W.addMovement(obtain);
        obtain.recycle();
        this.ag = uptimeMillis;
        return true;
    }

    public void endFakeDrag() {
        if (this.af) {
            VelocityTracker velocityTracker = this.W;
            velocityTracker.computeCurrentVelocity(1000, (float) this.ab);
            int yVelocity = (int) VelocityTrackerCompat.getYVelocity(velocityTracker, this.U);
            this.I = true;
            int clientHeight = getClientHeight();
            int scrollY = getScrollY();
            ItemInfo c2 = c();
            setCurrentItemInternal(a(c2.b, ((((float) scrollY) / ((float) clientHeight)) - c2.e) / c2.d, yVelocity, (int) (this.R - this.T)), true, true, yVelocity);
            d();
            this.af = false;
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public void fakeDragBy(float f2) {
        if (this.af) {
            this.R += f2;
            float scrollY = ((float) getScrollY()) - f2;
            float clientHeight = (float) getClientHeight();
            float f3 = this.C * clientHeight;
            float f4 = this.D * clientHeight;
            ItemInfo itemInfo = this.o.get(0);
            ItemInfo itemInfo2 = this.o.get(this.o.size() - 1);
            if (itemInfo.b != 0) {
                f3 = itemInfo.e * clientHeight;
            }
            if (itemInfo2.b != this.r.getCount() - 1) {
                f4 = itemInfo2.e * clientHeight;
            }
            if (scrollY < f3) {
                scrollY = f3;
            } else if (scrollY > f4) {
                scrollY = f4;
            }
            int i2 = (int) scrollY;
            this.R += scrollY - ((float) i2);
            scrollTo(getScrollX(), i2);
            a(i2);
            MotionEvent obtain = MotionEvent.obtain(this.ag, SystemClock.uptimeMillis(), 2, 0.0f, this.R, 0);
            this.W.addMovement(obtain);
            obtain.recycle();
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public boolean isFakeDragging() {
        return this.af;
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.U) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.R = MotionEventCompat.getY(motionEvent, i2);
            this.U = MotionEventCompat.getPointerId(motionEvent, i2);
            if (this.W != null) {
                this.W.clear();
            }
        }
    }

    private void d() {
        this.K = false;
        this.L = false;
        if (this.W != null) {
            this.W.recycle();
            this.W = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z2) {
        if (this.H != z2) {
            this.H = z2;
        }
    }

    public boolean internalCanScrollVertically(int i2) {
        if (this.r == null) {
            return false;
        }
        int clientHeight = getClientHeight();
        int scrollY = getScrollY();
        if (i2 < 0) {
            if (scrollY > ((int) (((float) clientHeight) * this.C))) {
                return true;
            }
            return false;
        } else if (i2 <= 0 || scrollY >= ((int) (((float) clientHeight) * this.D))) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean canScroll(View view, boolean z2, int i2, int i3, int i4) {
        int i5;
        View view2 = view;
        if (view2 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i6 = i4 + scrollY;
                if (i6 >= childAt.getTop() && i6 < childAt.getBottom() && (i5 = i3 + scrollX) >= childAt.getLeft() && i5 < childAt.getRight()) {
                    if (canScroll(childAt, true, i2, i5 - childAt.getLeft(), i6 - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (!z2 || !ViewCompat.canScrollVertically(view, -i2)) {
            return false;
        }
        return true;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent);
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 61) {
                switch (keyCode) {
                    case 21:
                        return arrowScroll(17);
                    case 22:
                        return arrowScroll(66);
                }
            } else if (Build.VERSION.SDK_INT >= 11) {
                if (keyEvent.hasNoModifiers()) {
                    return arrowScroll(2);
                }
                if (keyEvent.hasModifiers(1)) {
                    return arrowScroll(1);
                }
            }
        }
        return false;
    }

    public boolean arrowScroll(int i2) {
        boolean requestFocus;
        boolean z2;
        View findFocus = findFocus();
        boolean z3 = false;
        View view = null;
        if (findFocus != this) {
            if (findFocus != null) {
                ViewParent parent = findFocus.getParent();
                while (true) {
                    if (!(parent instanceof ViewGroup)) {
                        z2 = false;
                        break;
                    } else if (parent == this) {
                        z2 = true;
                        break;
                    } else {
                        parent = parent.getParent();
                    }
                }
                if (!z2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(findFocus.getClass().getSimpleName());
                    for (ViewParent parent2 = findFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                        sb.append(" => ");
                        sb.append(parent2.getClass().getSimpleName());
                    }
                    Log.e(f7171a, "arrowScroll tried to find focus based on non-child current focused view " + sb.toString());
                }
            }
            view = findFocus;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, view, i2);
        if (findNextFocus != null && findNextFocus != view) {
            if (i2 == 33) {
                int i3 = a(this.q, findNextFocus).top;
                int i4 = a(this.q, view).top;
                if (view == null || i3 < i4) {
                    requestFocus = findNextFocus.requestFocus();
                } else {
                    requestFocus = pageUp();
                }
            } else if (i2 == 130) {
                int i5 = a(this.q, findNextFocus).bottom;
                int i6 = a(this.q, view).bottom;
                if (view == null || i5 > i6) {
                    requestFocus = findNextFocus.requestFocus();
                } else {
                    requestFocus = pageDown();
                }
            }
            z3 = requestFocus;
        } else if (i2 == 33 || i2 == 1) {
            z3 = pageUp();
        } else if (i2 == 130 || i2 == 2) {
            z3 = pageDown();
        }
        if (z3) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i2));
        }
        return z3;
    }

    private Rect a(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left += viewGroup.getLeft();
            rect.right += viewGroup.getRight();
            rect.top += viewGroup.getTop();
            rect.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect;
    }

    /* access modifiers changed from: package-private */
    public boolean pageUp() {
        if (this.s <= 0) {
            return false;
        }
        setCurrentItem(this.s - 1, true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean pageDown() {
        if (this.r == null || this.s >= this.r.getCount() - 1) {
            return false;
        }
        setCurrentItem(this.s + 1, true);
        return true;
    }

    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        ItemInfo infoForChild;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.s) {
                    childAt.addFocusables(arrayList, i2, i3);
                }
            }
        }
        if ((descendantFocusability == 262144 && size != arrayList.size()) || !isFocusable()) {
            return;
        }
        if (((i3 & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) && arrayList != null) {
            arrayList.add(this);
        }
    }

    public void addTouchables(ArrayList<View> arrayList) {
        ItemInfo infoForChild;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.s) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        int i3;
        int i4;
        ItemInfo infoForChild;
        int childCount = getChildCount();
        int i5 = -1;
        if ((i2 & 2) != 0) {
            i5 = childCount;
            i4 = 0;
            i3 = 1;
        } else {
            i4 = childCount - 1;
            i3 = -1;
        }
        while (i4 != i5) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.s && childAt.requestFocus(i2, rect)) {
                return true;
            }
            i4 += i3;
        }
        return false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        ItemInfo infoForChild;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.s && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    class MyAccessibilityDelegate extends AccessibilityDelegateCompat {
        MyAccessibilityDelegate() {
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            AccessibilityRecordCompat obtain = AccessibilityRecordCompat.obtain();
            obtain.setScrollable(a());
            if (accessibilityEvent.getEventType() == 4096 && VerticalViewPager.this.r != null) {
                obtain.setItemCount(VerticalViewPager.this.r.getCount());
                obtain.setFromIndex(VerticalViewPager.this.s);
                obtain.setToIndex(VerticalViewPager.this.s);
            }
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
            accessibilityNodeInfoCompat.setScrollable(a());
            if (VerticalViewPager.this.internalCanScrollVertically(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
            }
            if (VerticalViewPager.this.internalCanScrollVertically(-1)) {
                accessibilityNodeInfoCompat.addAction(8192);
            }
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (super.performAccessibilityAction(view, i, bundle)) {
                return true;
            }
            if (i != 4096) {
                if (i != 8192 || !VerticalViewPager.this.internalCanScrollVertically(-1)) {
                    return false;
                }
                VerticalViewPager.this.setCurrentItem(VerticalViewPager.this.s - 1);
                return true;
            } else if (!VerticalViewPager.this.internalCanScrollVertically(1)) {
                return false;
            } else {
                VerticalViewPager.this.setCurrentItem(VerticalViewPager.this.s + 1);
                return true;
            }
        }

        private boolean a() {
            return VerticalViewPager.this.r != null && VerticalViewPager.this.r.getCount() > 1;
        }
    }

    private class PagerObserver extends DataSetObserver {
        private PagerObserver() {
        }

        public void onChanged() {
            VerticalViewPager.this.dataSetChanged();
        }

        public void onInvalidated() {
            VerticalViewPager.this.dataSetChanged();
        }
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {

        /* renamed from: a  reason: collision with root package name */
        public boolean f7174a;
        public int b;
        float c = 0.0f;
        boolean d;
        int e;
        int f;

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, VerticalViewPager.k);
            this.b = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }

    static class ViewPositionComparator implements Comparator<View> {
        ViewPositionComparator() {
        }

        /* renamed from: a */
        public int compare(View view, View view2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
            if (layoutParams.f7174a != layoutParams2.f7174a) {
                return layoutParams.f7174a ? 1 : -1;
            }
            return layoutParams.e - layoutParams2.e;
        }
    }
}
