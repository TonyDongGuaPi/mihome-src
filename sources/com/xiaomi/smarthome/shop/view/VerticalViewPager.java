package com.xiaomi.smarthome.shop.view;

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
    private static final int T = -1;

    /* renamed from: a  reason: collision with root package name */
    private static final String f22209a = "ViewPager";
    private static final int ac = 2;
    private static final int aq = 0;
    private static final int ar = 1;
    private static final int as = 2;
    private static final ViewPositionComparator av = new ViewPositionComparator();
    private static final boolean b = false;
    private static final boolean c = false;
    private static final int d = 1;
    private static final int e = 600;
    private static final int f = 25;
    private static final int g = 16;
    private static final int h = 400;
    /* access modifiers changed from: private */
    public static final int[] i = {16842931};
    private static final Comparator<ItemInfo> k = new Comparator<ItemInfo>() {
        /* renamed from: a */
        public int compare(ItemInfo itemInfo, ItemInfo itemInfo2) {
            return itemInfo.b - itemInfo2.b;
        }
    };
    private static final Interpolator l = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };
    private float A = -3.4028235E38f;
    private float B = Float.MAX_VALUE;
    private int C;
    private int D;
    private boolean E;
    private boolean F;
    private boolean G;
    private int H = 1;
    private boolean I;
    private boolean J;
    private boolean K;
    private int L;
    private int M;
    private int N;
    private float O;
    private float P;
    private float Q;
    private float R;
    private int S = -1;
    private VelocityTracker U;
    private int V;
    private int W;
    private int aa;
    private int ab;
    private boolean ad;
    private long ae;
    private EdgeEffectCompat af;
    private EdgeEffectCompat ag;
    private boolean ah = true;
    private boolean ai = false;
    private boolean aj;
    private int ak;
    private ViewPager.OnPageChangeListener al;
    private ViewPager.OnPageChangeListener am;
    private OnAdapterChangeListener an;
    private ViewPager.PageTransformer ao;
    private Method ap;
    private int at;
    private ArrayList<View> au;
    private final Runnable aw = new Runnable() {
        public void run() {
            VerticalViewPager.this.setScrollState(0);
            VerticalViewPager.this.populate();
        }
    };
    private int ax = 0;
    private int j;
    private final ArrayList<ItemInfo> m = new ArrayList<>();
    private final ItemInfo n = new ItemInfo();
    private final Rect o = new Rect();
    /* access modifiers changed from: private */
    public PagerAdapter p;
    /* access modifiers changed from: private */
    public int q;
    private int r = -1;
    private Parcelable s = null;
    private ClassLoader t = null;
    private Scroller u;
    private PagerObserver v;
    private int w;
    private Drawable x;
    private int y;
    private int z;

    interface Decor {
    }

    interface OnAdapterChangeListener {
        void a(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2);
    }

    static class ItemInfo {

        /* renamed from: a  reason: collision with root package name */
        Object f22211a;
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
        this.u = new Scroller(context, l);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.N = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
        this.V = (int) (400.0f * f2);
        this.W = viewConfiguration.getScaledMaximumFlingVelocity();
        this.af = new EdgeEffectCompat(context);
        this.ag = new EdgeEffectCompat(context);
        this.aa = (int) (25.0f * f2);
        this.ab = (int) (2.0f * f2);
        this.L = (int) (f2 * 16.0f);
        ViewCompat.setAccessibilityDelegate(this, new MyAccessibilityDelegate());
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        removeCallbacks(this.aw);
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: private */
    public void setScrollState(int i2) {
        if (this.ax != i2) {
            this.ax = i2;
            if (this.ao != null) {
                b(i2 != 0);
            }
            if (this.al != null) {
                this.al.onPageScrollStateChanged(i2);
            }
        }
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        if (this.p != null) {
            this.p.unregisterDataSetObserver(this.v);
            this.p.startUpdate((ViewGroup) this);
            for (int i2 = 0; i2 < this.m.size(); i2++) {
                ItemInfo itemInfo = this.m.get(i2);
                this.p.destroyItem((ViewGroup) this, itemInfo.b, itemInfo.f22211a);
            }
            this.p.finishUpdate((ViewGroup) this);
            this.m.clear();
            a();
            this.q = 0;
            scrollTo(0, 0);
        }
        PagerAdapter pagerAdapter2 = this.p;
        this.p = pagerAdapter;
        this.j = 0;
        if (this.p != null) {
            if (this.v == null) {
                this.v = new PagerObserver();
            }
            this.p.registerDataSetObserver(this.v);
            this.G = false;
            boolean z2 = this.ah;
            this.ah = true;
            this.j = this.p.getCount();
            if (this.r >= 0) {
                this.p.restoreState(this.s, this.t);
                setCurrentItemInternal(this.r, false, true);
                this.r = -1;
                this.s = null;
                this.t = null;
            } else if (!z2) {
                populate();
            } else {
                requestLayout();
            }
        }
        if (this.an != null && pagerAdapter2 != pagerAdapter) {
            this.an.a(pagerAdapter2, pagerAdapter);
        }
    }

    private void a() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((LayoutParams) getChildAt(i2).getLayoutParams()).f22212a) {
                removeViewAt(i2);
                i2--;
            }
            i2++;
        }
    }

    public PagerAdapter getAdapter() {
        return this.p;
    }

    /* access modifiers changed from: package-private */
    public void setOnAdapterChangeListener(OnAdapterChangeListener onAdapterChangeListener) {
        this.an = onAdapterChangeListener;
    }

    private int getClientHeight() {
        return (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
    }

    public void setCurrentItem(int i2) {
        this.G = false;
        setCurrentItemInternal(i2, !this.ah, false);
    }

    public void setCurrentItem(int i2, boolean z2) {
        this.G = false;
        setCurrentItemInternal(i2, z2, false);
    }

    public int getCurrentItem() {
        return this.q;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentItemInternal(int i2, boolean z2, boolean z3) {
        setCurrentItemInternal(i2, z2, z3, 0);
    }

    /* access modifiers changed from: package-private */
    public void setCurrentItemInternal(int i2, boolean z2, boolean z3, int i3) {
        if (this.p == null || this.p.getCount() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (z3 || this.q != i2 || this.m.size() == 0) {
            boolean z4 = true;
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 >= this.p.getCount()) {
                i2 = this.p.getCount() - 1;
            }
            int i4 = this.H;
            if (i2 > this.q + i4 || i2 < this.q - i4) {
                for (int i5 = 0; i5 < this.m.size(); i5++) {
                    this.m.get(i5).c = true;
                }
            }
            if (this.q == i2) {
                z4 = false;
            }
            if (this.ah) {
                this.q = i2;
                if (z4 && this.al != null) {
                    this.al.onPageSelected(i2);
                }
                if (z4 && this.am != null) {
                    this.am.onPageSelected(i2);
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
        int clientHeight = infoForPosition != null ? (int) (((float) getClientHeight()) * Math.max(this.A, Math.min(infoForPosition.e, this.B))) : 0;
        if (z2) {
            smoothScrollTo(0, clientHeight, i3);
            if (z3 && this.al != null) {
                this.al.onPageSelected(i2);
            }
            if (z3 && this.am != null) {
                this.am.onPageSelected(i2);
                return;
            }
            return;
        }
        if (z3 && this.al != null) {
            this.al.onPageSelected(i2);
        }
        if (z3 && this.am != null) {
            this.am.onPageSelected(i2);
        }
        a(false);
        scrollTo(0, clientHeight);
        a(clientHeight);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.al = onPageChangeListener;
    }

    public void setPageTransformer(boolean z2, ViewPager.PageTransformer pageTransformer) {
        if (Build.VERSION.SDK_INT >= 11) {
            int i2 = 1;
            boolean z3 = pageTransformer != null;
            boolean z4 = z3 != (this.ao != null);
            this.ao = pageTransformer;
            setChildrenDrawingOrderEnabledCompat(z3);
            if (z3) {
                if (z2) {
                    i2 = 2;
                }
                this.at = i2;
            } else {
                this.at = 0;
            }
            if (z4) {
                populate();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setChildrenDrawingOrderEnabledCompat(boolean z2) {
        if (Build.VERSION.SDK_INT >= 7) {
            if (this.ap == null) {
                Class<ViewGroup> cls = ViewGroup.class;
                try {
                    this.ap = cls.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
                } catch (NoSuchMethodException e2) {
                    Log.e(f22209a, "Can't find setChildrenDrawingOrderEnabled", e2);
                }
            }
            try {
                this.ap.invoke(this, new Object[]{Boolean.valueOf(z2)});
            } catch (Exception e3) {
                Log.e(f22209a, "Error changing children drawing order", e3);
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i2, int i3) {
        if (this.at == 2) {
            i3 = (i2 - 1) - i3;
        }
        return ((LayoutParams) this.au.get(i3).getLayoutParams()).f;
    }

    /* access modifiers changed from: package-private */
    public ViewPager.OnPageChangeListener setInternalPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        ViewPager.OnPageChangeListener onPageChangeListener2 = this.am;
        this.am = onPageChangeListener;
        return onPageChangeListener2;
    }

    public int getOffscreenPageLimit() {
        return this.H;
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            Log.w(f22209a, "Requested offscreen page limit " + i2 + " too small; defaulting to " + 1);
            i2 = 1;
        }
        if (i2 != this.H) {
            this.H = i2;
            populate();
        }
    }

    public void setPageMargin(int i2) {
        int i3 = this.w;
        this.w = i2;
        int height = getHeight();
        a(height, height, i2, i3);
        requestLayout();
    }

    public int getPageMargin() {
        return this.w;
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.x = drawable;
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
        return super.verifyDrawable(drawable) || drawable == this.x;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.x;
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
            i5 = (int) (((((float) Math.abs(i6)) / ((f2 * this.p.getPageWidth(this.q)) + ((float) this.w))) + 1.0f) * 100.0f);
        }
        this.u.startScroll(scrollX, scrollY, i6, i7, Math.min(i5, 600));
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /* access modifiers changed from: package-private */
    public ItemInfo addNewItem(int i2, int i3) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.b = i2;
        itemInfo.f22211a = this.p.instantiateItem((ViewGroup) this, i2);
        itemInfo.d = this.p.getPageWidth(i2);
        if (i3 < 0 || i3 >= this.m.size()) {
            this.m.add(itemInfo);
        } else {
            this.m.add(i3, itemInfo);
        }
        return itemInfo;
    }

    /* access modifiers changed from: package-private */
    public void dataSetChanged() {
        int count = this.p.getCount();
        this.j = count;
        boolean z2 = this.m.size() < (this.H * 2) + 1 && this.m.size() < count;
        int i2 = this.q;
        int i3 = 0;
        boolean z3 = false;
        while (i3 < this.m.size()) {
            ItemInfo itemInfo = this.m.get(i3);
            int itemPosition = this.p.getItemPosition(itemInfo.f22211a);
            if (itemPosition != -1) {
                if (itemPosition == -2) {
                    this.m.remove(i3);
                    i3--;
                    if (!z3) {
                        this.p.startUpdate((ViewGroup) this);
                        z3 = true;
                    }
                    this.p.destroyItem((ViewGroup) this, itemInfo.b, itemInfo.f22211a);
                    if (this.q == itemInfo.b) {
                        i2 = Math.max(0, Math.min(this.q, count - 1));
                    }
                } else if (itemInfo.b != itemPosition) {
                    if (itemInfo.b == this.q) {
                        i2 = itemPosition;
                    }
                    itemInfo.b = itemPosition;
                }
                z2 = true;
            }
            i3++;
        }
        if (z3) {
            this.p.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.m, k);
        if (z2) {
            int childCount = getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i4).getLayoutParams();
                if (!layoutParams.f22212a) {
                    layoutParams.c = 0.0f;
                }
            }
            setCurrentItemInternal(i2, false, true);
            requestLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void populate() {
        populate(this.q);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0070, code lost:
        if (r9.b == r0.q) goto L_0x0077;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void populate(int r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            int r2 = r0.q
            if (r2 == r1) goto L_0x001a
            int r2 = r0.q
            if (r2 >= r1) goto L_0x000f
            r2 = 130(0x82, float:1.82E-43)
            goto L_0x0011
        L_0x000f:
            r2 = 33
        L_0x0011:
            int r4 = r0.q
            com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo r4 = r0.infoForPosition(r4)
            r0.q = r1
            goto L_0x001c
        L_0x001a:
            r2 = 2
            r4 = 0
        L_0x001c:
            android.support.v4.view.PagerAdapter r1 = r0.p
            if (r1 != 0) goto L_0x0024
            r17.b()
            return
        L_0x0024:
            boolean r1 = r0.G
            if (r1 == 0) goto L_0x002c
            r17.b()
            return
        L_0x002c:
            android.os.IBinder r1 = r17.getWindowToken()
            if (r1 != 0) goto L_0x0033
            return
        L_0x0033:
            android.support.v4.view.PagerAdapter r1 = r0.p
            r1.startUpdate((android.view.ViewGroup) r0)
            int r1 = r0.H
            int r5 = r0.q
            int r5 = r5 - r1
            r6 = 0
            int r5 = java.lang.Math.max(r6, r5)
            android.support.v4.view.PagerAdapter r7 = r0.p
            int r7 = r7.getCount()
            int r8 = r7 + -1
            int r9 = r0.q
            int r9 = r9 + r1
            int r1 = java.lang.Math.min(r8, r9)
            int r8 = r0.j
            if (r7 != r8) goto L_0x0224
            r8 = 0
        L_0x0056:
            java.util.ArrayList<com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo> r9 = r0.m
            int r9 = r9.size()
            if (r8 >= r9) goto L_0x0076
            java.util.ArrayList<com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo> r9 = r0.m
            java.lang.Object r9 = r9.get(r8)
            com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo r9 = (com.xiaomi.smarthome.shop.view.VerticalViewPager.ItemInfo) r9
            int r10 = r9.b
            int r11 = r0.q
            if (r10 < r11) goto L_0x0073
            int r10 = r9.b
            int r11 = r0.q
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
            int r9 = r0.q
            com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo r9 = r0.addNewItem(r9, r8)
        L_0x0081:
            if (r9 == 0) goto L_0x01a2
            int r11 = r8 + -1
            if (r11 < 0) goto L_0x0090
            java.util.ArrayList<com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo> r12 = r0.m
            java.lang.Object r12 = r12.get(r11)
            com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo r12 = (com.xiaomi.smarthome.shop.view.VerticalViewPager.ItemInfo) r12
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
            int r6 = r0.q
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
            java.util.ArrayList<com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo> r10 = r0.m
            r10.remove(r11)
            android.support.v4.view.PagerAdapter r10 = r0.p
            java.lang.Object r12 = r12.f22211a
            r10.destroyItem((android.view.ViewGroup) r0, (int) r6, (java.lang.Object) r12)
            int r11 = r11 + -1
            int r15 = r15 + -1
            if (r11 < 0) goto L_0x0107
            java.util.ArrayList<com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo> r10 = r0.m
            java.lang.Object r10 = r10.get(r11)
            com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo r10 = (com.xiaomi.smarthome.shop.view.VerticalViewPager.ItemInfo) r10
            goto L_0x0108
        L_0x00db:
            if (r12 == 0) goto L_0x00f1
            int r10 = r12.b
            if (r6 != r10) goto L_0x00f1
            float r10 = r12.d
            float r8 = r8 + r10
            int r11 = r11 + -1
            if (r11 < 0) goto L_0x0107
            java.util.ArrayList<com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo> r10 = r0.m
            java.lang.Object r10 = r10.get(r11)
            com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo r10 = (com.xiaomi.smarthome.shop.view.VerticalViewPager.ItemInfo) r10
            goto L_0x0108
        L_0x00f1:
            int r10 = r11 + 1
            com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo r10 = r0.addNewItem(r6, r10)
            float r10 = r10.d
            float r8 = r8 + r10
            int r15 = r15 + 1
            if (r11 < 0) goto L_0x0107
            java.util.ArrayList<com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo> r10 = r0.m
            java.lang.Object r10 = r10.get(r11)
            com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo r10 = (com.xiaomi.smarthome.shop.view.VerticalViewPager.ItemInfo) r10
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
            java.util.ArrayList<com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo> r6 = r0.m
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x0125
            java.util.ArrayList<com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo> r6 = r0.m
            java.lang.Object r6 = r6.get(r5)
            com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo r6 = (com.xiaomi.smarthome.shop.view.VerticalViewPager.ItemInfo) r6
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
            int r8 = r0.q
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
            java.util.ArrayList<com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo> r11 = r0.m
            r11.remove(r5)
            android.support.v4.view.PagerAdapter r11 = r0.p
            java.lang.Object r6 = r6.f22211a
            r11.destroyItem((android.view.ViewGroup) r0, (int) r8, (java.lang.Object) r6)
            java.util.ArrayList<com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo> r6 = r0.m
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x0167
            java.util.ArrayList<com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo> r6 = r0.m
            java.lang.Object r6 = r6.get(r5)
            com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo r6 = (com.xiaomi.smarthome.shop.view.VerticalViewPager.ItemInfo) r6
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
            java.util.ArrayList<com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo> r6 = r0.m
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x0167
            java.util.ArrayList<com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo> r6 = r0.m
            java.lang.Object r6 = r6.get(r5)
            com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo r6 = (com.xiaomi.smarthome.shop.view.VerticalViewPager.ItemInfo) r6
            goto L_0x019e
        L_0x0185:
            com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo r6 = r0.addNewItem(r8, r5)
            int r5 = r5 + 1
            float r6 = r6.d
            float r3 = r3 + r6
            java.util.ArrayList<com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo> r6 = r0.m
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x0167
            java.util.ArrayList<com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo> r6 = r0.m
            java.lang.Object r6 = r6.get(r5)
            com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo r6 = (com.xiaomi.smarthome.shop.view.VerticalViewPager.ItemInfo) r6
        L_0x019e:
            goto L_0x0135
        L_0x019f:
            r0.a(r9, r15, r4)
        L_0x01a2:
            android.support.v4.view.PagerAdapter r1 = r0.p
            int r3 = r0.q
            if (r9 == 0) goto L_0x01ab
            java.lang.Object r4 = r9.f22211a
            goto L_0x01ac
        L_0x01ab:
            r4 = 0
        L_0x01ac:
            r1.setPrimaryItem((android.view.ViewGroup) r0, (int) r3, (java.lang.Object) r4)
            android.support.v4.view.PagerAdapter r1 = r0.p
            r1.finishUpdate((android.view.ViewGroup) r0)
            int r1 = r17.getChildCount()
            r3 = 0
        L_0x01b9:
            if (r3 >= r1) goto L_0x01e5
            android.view.View r4 = r0.getChildAt(r3)
            android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
            com.xiaomi.smarthome.shop.view.VerticalViewPager$LayoutParams r5 = (com.xiaomi.smarthome.shop.view.VerticalViewPager.LayoutParams) r5
            r5.f = r3
            boolean r6 = r5.f22212a
            if (r6 != 0) goto L_0x01e1
            float r6 = r5.c
            r7 = 0
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 != 0) goto L_0x01e2
            com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo r4 = r0.infoForChild(r4)
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
            com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo r3 = r0.infoForAnyChild(r1)
            goto L_0x01fa
        L_0x01f9:
            r3 = 0
        L_0x01fa:
            if (r3 == 0) goto L_0x0202
            int r1 = r3.b
            int r3 = r0.q
            if (r1 == r3) goto L_0x0223
        L_0x0202:
            r1 = 0
        L_0x0203:
            int r3 = r17.getChildCount()
            if (r1 >= r3) goto L_0x0223
            android.view.View r3 = r0.getChildAt(r1)
            com.xiaomi.smarthome.shop.view.VerticalViewPager$ItemInfo r4 = r0.infoForChild(r3)
            if (r4 == 0) goto L_0x0220
            int r4 = r4.b
            int r5 = r0.q
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
            int r4 = r0.j
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
            android.support.v4.view.PagerAdapter r1 = r0.p
            java.lang.Class r1 = r1.getClass()
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.shop.view.VerticalViewPager.populate(int):void");
    }

    private void b() {
        if (this.at != 0) {
            if (this.au == null) {
                this.au = new ArrayList<>();
            } else {
                this.au.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.au.add(getChildAt(i2));
            }
            Collections.sort(this.au, av);
        }
    }

    private void a(ItemInfo itemInfo, int i2, ItemInfo itemInfo2) {
        ItemInfo itemInfo3;
        ItemInfo itemInfo4;
        int count = this.p.getCount();
        int clientHeight = getClientHeight();
        float f2 = clientHeight > 0 ? ((float) this.w) / ((float) clientHeight) : 0.0f;
        if (itemInfo2 != null) {
            int i3 = itemInfo2.b;
            if (i3 < itemInfo.b) {
                float f3 = itemInfo2.e + itemInfo2.d + f2;
                int i4 = i3 + 1;
                int i5 = 0;
                while (i4 <= itemInfo.b && i5 < this.m.size()) {
                    Object obj = this.m.get(i5);
                    while (true) {
                        itemInfo4 = (ItemInfo) obj;
                        if (i4 > itemInfo4.b && i5 < this.m.size() - 1) {
                            i5++;
                            obj = this.m.get(i5);
                        }
                    }
                    while (i4 < itemInfo4.b) {
                        f3 += this.p.getPageWidth(i4) + f2;
                        i4++;
                    }
                    itemInfo4.e = f3;
                    f3 += itemInfo4.d + f2;
                    i4++;
                }
            } else if (i3 > itemInfo.b) {
                int size = this.m.size() - 1;
                float f4 = itemInfo2.e;
                while (true) {
                    i3--;
                    if (i3 < itemInfo.b || size < 0) {
                        break;
                    }
                    Object obj2 = this.m.get(size);
                    while (true) {
                        itemInfo3 = (ItemInfo) obj2;
                        if (i3 < itemInfo3.b && size > 0) {
                            size--;
                            obj2 = this.m.get(size);
                        }
                    }
                    while (i3 > itemInfo3.b) {
                        f4 -= this.p.getPageWidth(i3) + f2;
                        i3--;
                    }
                    f4 -= itemInfo3.d + f2;
                    itemInfo3.e = f4;
                }
            }
        }
        int size2 = this.m.size();
        float f5 = itemInfo.e;
        int i6 = itemInfo.b - 1;
        this.A = itemInfo.b == 0 ? itemInfo.e : -3.4028235E38f;
        int i7 = count - 1;
        this.B = itemInfo.b == i7 ? (itemInfo.e + itemInfo.d) - 1.0f : Float.MAX_VALUE;
        int i8 = i2 - 1;
        while (i8 >= 0) {
            ItemInfo itemInfo5 = this.m.get(i8);
            while (i6 > itemInfo5.b) {
                f5 -= this.p.getPageWidth(i6) + f2;
                i6--;
            }
            f5 -= itemInfo5.d + f2;
            itemInfo5.e = f5;
            if (itemInfo5.b == 0) {
                this.A = f5;
            }
            i8--;
            i6--;
        }
        float f6 = itemInfo.e + itemInfo.d + f2;
        int i9 = itemInfo.b + 1;
        int i10 = i2 + 1;
        while (i10 < size2) {
            ItemInfo itemInfo6 = this.m.get(i10);
            while (i9 < itemInfo6.b) {
                f6 += this.p.getPageWidth(i9) + f2;
                i9++;
            }
            if (itemInfo6.b == i7) {
                this.B = (itemInfo6.d + f6) - 1.0f;
            }
            itemInfo6.e = f6;
            f6 += itemInfo6.d + f2;
            i10++;
            i9++;
        }
        this.ai = false;
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
        int f22215a;
        Parcelable b;
        ClassLoader c;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f22215a);
            parcel.writeParcelable(this.b, i);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f22215a + "}";
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.f22215a = parcel.readInt();
            this.b = parcel.readParcelable(classLoader);
            this.c = classLoader;
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f22215a = this.q;
        if (this.p != null) {
            savedState.b = this.p.saveState();
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
        if (this.p != null) {
            this.p.restoreState(savedState.b, savedState.c);
            setCurrentItemInternal(savedState.f22215a, false, true);
            return;
        }
        this.r = savedState.f22215a;
        this.s = savedState.b;
        this.t = savedState.c;
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        layoutParams2.f22212a |= view instanceof Decor;
        if (!this.E) {
            super.addView(view, i2, layoutParams);
        } else if (layoutParams2 == null || !layoutParams2.f22212a) {
            layoutParams2.d = true;
            addViewInLayout(view, i2, layoutParams);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    public void removeView(View view) {
        if (this.E) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    /* access modifiers changed from: package-private */
    public ItemInfo infoForChild(View view) {
        for (int i2 = 0; i2 < this.m.size(); i2++) {
            ItemInfo itemInfo = this.m.get(i2);
            if (this.p.isViewFromObject(view, itemInfo.f22211a)) {
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
        for (int i3 = 0; i3 < this.m.size(); i3++) {
            ItemInfo itemInfo = this.m.get(i3);
            if (itemInfo.b == i2) {
                return itemInfo;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.ah = true;
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
            int r4 = r0.L
            int r3 = java.lang.Math.min(r3, r4)
            r0.M = r3
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
            com.xiaomi.smarthome.shop.view.VerticalViewPager$LayoutParams r6 = (com.xiaomi.smarthome.shop.view.VerticalViewPager.LayoutParams) r6
            if (r6 == 0) goto L_0x00c0
            boolean r10 = r6.f22212a
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
            r0.C = r1
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r8)
            r0.D = r1
            r0.E = r7
            r16.populate()
            r1 = 0
            r0.E = r1
            int r2 = r16.getChildCount()
        L_0x00dd:
            if (r1 >= r2) goto L_0x0107
            android.view.View r3 = r0.getChildAt(r1)
            int r4 = r3.getVisibility()
            if (r4 == r6) goto L_0x0104
            android.view.ViewGroup$LayoutParams r4 = r3.getLayoutParams()
            com.xiaomi.smarthome.shop.view.VerticalViewPager$LayoutParams r4 = (com.xiaomi.smarthome.shop.view.VerticalViewPager.LayoutParams) r4
            if (r4 == 0) goto L_0x00f5
            boolean r7 = r4.f22212a
            if (r7 != 0) goto L_0x0104
        L_0x00f5:
            float r7 = (float) r5
            float r4 = r4.c
            float r7 = r7 * r4
            int r4 = (int) r7
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r8)
            int r7 = r0.C
            r3.measure(r7, r4)
        L_0x0104:
            int r1 = r1 + 1
            goto L_0x00dd
        L_0x0107:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.shop.view.VerticalViewPager.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i3 != i5) {
            a(i3, i5, this.w, this.w);
        }
    }

    private void a(int i2, int i3, int i4, int i5) {
        if (i3 <= 0 || this.m.isEmpty()) {
            ItemInfo infoForPosition = infoForPosition(this.q);
            int min = (int) ((infoForPosition != null ? Math.min(infoForPosition.e, this.B) : 0.0f) * ((float) ((i2 - getPaddingTop()) - getPaddingBottom())));
            if (min != getScrollY()) {
                a(false);
                scrollTo(getScrollX(), min);
                return;
            }
            return;
        }
        int scrollY = (int) ((((float) getScrollY()) / ((float) (((i3 - getPaddingTop()) - getPaddingBottom()) + i5))) * ((float) (((i2 - getPaddingTop()) - getPaddingBottom()) + i4)));
        scrollTo(getScrollX(), scrollY);
        if (!this.u.isFinished()) {
            this.u.startScroll(0, scrollY, 0, (int) (infoForPosition(this.q).e * ((float) i2)), this.u.getDuration() - this.u.timePassed());
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
                if (layoutParams.f22212a) {
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
                if (!layoutParams2.f22212a && (infoForChild = infoForChild(childAt2)) != null) {
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
        this.y = i12;
        this.z = i8 - i10;
        this.ak = i11;
        if (this.ah) {
            z3 = false;
            a(this.q, false, 0, false);
        } else {
            z3 = false;
        }
        this.ah = z3;
    }

    public void computeScroll() {
        if (this.u.isFinished() || !this.u.computeScrollOffset()) {
            a(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.u.getCurrX();
        int currY = this.u.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            if (!a(currY)) {
                this.u.abortAnimation();
                scrollTo(currX, 0);
            }
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private boolean a(int i2) {
        if (this.m.size() == 0) {
            this.aj = false;
            onPageScrolled(0, 0.0f, 0);
            if (this.aj) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        ItemInfo c2 = c();
        int clientHeight = getClientHeight();
        int i3 = this.w + clientHeight;
        float f2 = (float) clientHeight;
        float f3 = ((float) this.w) / f2;
        int i4 = c2.b;
        float f4 = ((((float) i2) / f2) - c2.e) / (c2.d + f3);
        this.aj = false;
        onPageScrolled(i4, f4, (int) (((float) i3) * f4));
        if (this.aj) {
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
            int r0 = r11.ak
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
            com.xiaomi.smarthome.shop.view.VerticalViewPager$LayoutParams r8 = (com.xiaomi.smarthome.shop.view.VerticalViewPager.LayoutParams) r8
            boolean r9 = r8.f22212a
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
            android.support.v4.view.ViewPager$OnPageChangeListener r0 = r11.al
            if (r0 == 0) goto L_0x0079
            android.support.v4.view.ViewPager$OnPageChangeListener r0 = r11.al
            r0.onPageScrolled(r12, r13, r14)
        L_0x0079:
            android.support.v4.view.ViewPager$OnPageChangeListener r0 = r11.am
            if (r0 == 0) goto L_0x0082
            android.support.v4.view.ViewPager$OnPageChangeListener r0 = r11.am
            r0.onPageScrolled(r12, r13, r14)
        L_0x0082:
            android.support.v4.view.ViewPager$PageTransformer r12 = r11.ao
            if (r12 == 0) goto L_0x00b3
            int r12 = r11.getScrollY()
            int r13 = r11.getChildCount()
        L_0x008e:
            if (r1 >= r13) goto L_0x00b3
            android.view.View r14 = r11.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r0 = r14.getLayoutParams()
            com.xiaomi.smarthome.shop.view.VerticalViewPager$LayoutParams r0 = (com.xiaomi.smarthome.shop.view.VerticalViewPager.LayoutParams) r0
            boolean r0 = r0.f22212a
            if (r0 == 0) goto L_0x009f
            goto L_0x00b0
        L_0x009f:
            int r0 = r14.getTop()
            int r0 = r0 - r12
            float r0 = (float) r0
            int r2 = r11.getClientHeight()
            float r2 = (float) r2
            float r0 = r0 / r2
            android.support.v4.view.ViewPager$PageTransformer r2 = r11.ao
            r2.transformPage(r14, r0)
        L_0x00b0:
            int r1 = r1 + 1
            goto L_0x008e
        L_0x00b3:
            r12 = 1
            r11.aj = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.shop.view.VerticalViewPager.onPageScrolled(int, float, int):void");
    }

    private void a(boolean z2) {
        boolean z3 = this.ax == 2;
        if (z3) {
            setScrollingCacheEnabled(false);
            this.u.abortAnimation();
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.u.getCurrX();
            int currY = this.u.getCurrY();
            if (!(scrollX == currX && scrollY == currY)) {
                scrollTo(currX, currY);
            }
        }
        this.G = false;
        boolean z4 = z3;
        for (int i2 = 0; i2 < this.m.size(); i2++) {
            ItemInfo itemInfo = this.m.get(i2);
            if (itemInfo.c) {
                itemInfo.c = false;
                z4 = true;
            }
        }
        if (!z4) {
            return;
        }
        if (z2) {
            ViewCompat.postOnAnimation(this, this.aw);
        } else {
            this.aw.run();
        }
    }

    private boolean a(float f2, float f3) {
        return (f2 < ((float) this.M) && f3 > 0.0f) || (f2 > ((float) (getHeight() - this.M)) && f3 < 0.0f);
    }

    private void b(boolean z2) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            ViewCompat.setLayerType(getChildAt(i2), z2 ? 2 : 0, (Paint) null);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            this.I = false;
            this.J = false;
            this.S = -1;
            if (this.U != null) {
                this.U.recycle();
                this.U = null;
            }
            return false;
        }
        if (action != 0) {
            if (this.I) {
                return true;
            }
            if (this.J) {
                return false;
            }
        }
        if (action == 0) {
            float x2 = motionEvent.getX();
            this.Q = x2;
            this.O = x2;
            float y2 = motionEvent.getY();
            this.R = y2;
            this.P = y2;
            this.S = MotionEventCompat.getPointerId(motionEvent2, 0);
            this.J = false;
            this.u.computeScrollOffset();
            if (this.ax != 2 || Math.abs(this.u.getFinalY() - this.u.getCurrY()) <= this.ab) {
                a(false);
                this.I = false;
            } else {
                this.u.abortAnimation();
                this.G = false;
                populate();
                this.I = true;
                c(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i2 = this.S;
            if (i2 != -1) {
                int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent2, i2);
                float y3 = MotionEventCompat.getY(motionEvent2, findPointerIndex);
                float f2 = y3 - this.P;
                float abs = Math.abs(f2);
                float x3 = MotionEventCompat.getX(motionEvent2, findPointerIndex);
                float abs2 = Math.abs(x3 - this.Q);
                if (f2 != 0.0f && !a(this.P, f2)) {
                    if (canScroll(this, false, (int) f2, (int) x3, (int) y3)) {
                        this.O = x3;
                        this.P = y3;
                        this.J = true;
                        return false;
                    }
                }
                if (abs > ((float) this.N) && abs * 0.5f > abs2) {
                    this.I = true;
                    c(true);
                    setScrollState(1);
                    this.P = f2 > 0.0f ? this.R + ((float) this.N) : this.R - ((float) this.N);
                    this.O = x3;
                    setScrollingCacheEnabled(true);
                } else if (abs2 > ((float) this.N)) {
                    this.J = true;
                }
                if (this.I && a(y3)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
            }
        } else if (action == 6) {
            a(motionEvent);
        }
        if (this.U == null) {
            this.U = VelocityTracker.obtain();
        }
        this.U.addMovement(motionEvent2);
        return this.I;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.ad) {
            return true;
        }
        boolean z2 = false;
        if ((motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) || this.p == null || this.p.getCount() == 0) {
            return false;
        }
        if (this.U == null) {
            this.U = VelocityTracker.obtain();
        }
        this.U.addMovement(motionEvent);
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.u.abortAnimation();
                this.G = false;
                populate();
                float x2 = motionEvent.getX();
                this.Q = x2;
                this.O = x2;
                float y2 = motionEvent.getY();
                this.R = y2;
                this.P = y2;
                this.S = MotionEventCompat.getPointerId(motionEvent, 0);
                break;
            case 1:
                if (this.I) {
                    VelocityTracker velocityTracker = this.U;
                    velocityTracker.computeCurrentVelocity(1000, (float) this.W);
                    int yVelocity = (int) VelocityTrackerCompat.getYVelocity(velocityTracker, this.S);
                    this.G = true;
                    int clientHeight = getClientHeight();
                    int scrollY = getScrollY();
                    ItemInfo c2 = c();
                    setCurrentItemInternal(a(c2.b, ((((float) scrollY) / ((float) clientHeight)) - c2.e) / c2.d, yVelocity, (int) (MotionEventCompat.getY(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.S)) - this.R)), true, true, yVelocity);
                    this.S = -1;
                    d();
                    z2 = this.af.onRelease() | this.ag.onRelease();
                    break;
                }
                break;
            case 2:
                if (!this.I) {
                    int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.S);
                    float y3 = MotionEventCompat.getY(motionEvent, findPointerIndex);
                    float abs = Math.abs(y3 - this.P);
                    float x3 = MotionEventCompat.getX(motionEvent, findPointerIndex);
                    float abs2 = Math.abs(x3 - this.O);
                    if (abs > ((float) this.N) && abs > abs2) {
                        this.I = true;
                        c(true);
                        this.P = y3 - this.R > 0.0f ? this.R + ((float) this.N) : this.R - ((float) this.N);
                        this.O = x3;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                if (this.I) {
                    z2 = false | a(MotionEventCompat.getY(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.S)));
                    break;
                }
                break;
            case 3:
                if (this.I) {
                    a(this.q, true, 0, false);
                    this.S = -1;
                    d();
                    z2 = this.af.onRelease() | this.ag.onRelease();
                    break;
                }
                break;
            case 5:
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                this.P = MotionEventCompat.getY(motionEvent, actionIndex);
                this.S = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                break;
            case 6:
                a(motionEvent);
                this.P = MotionEventCompat.getY(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.S));
                break;
        }
        if (z2) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        return true;
    }

    private void c(boolean z2) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z2);
        }
    }

    private boolean a(float f2) {
        boolean z2;
        float f3 = this.P - f2;
        this.P = f2;
        float scrollY = ((float) getScrollY()) + f3;
        float clientHeight = (float) getClientHeight();
        float f4 = this.A * clientHeight;
        float f5 = this.B * clientHeight;
        boolean z3 = false;
        ItemInfo itemInfo = this.m.get(0);
        boolean z4 = true;
        ItemInfo itemInfo2 = this.m.get(this.m.size() - 1);
        if (itemInfo.b != 0) {
            f4 = itemInfo.e * clientHeight;
            z2 = false;
        } else {
            z2 = true;
        }
        if (itemInfo2.b != this.p.getCount() - 1) {
            f5 = itemInfo2.e * clientHeight;
            z4 = false;
        }
        if (scrollY < f4) {
            if (z2) {
                z3 = this.af.onPull(Math.abs(f4 - scrollY) / clientHeight);
            }
            scrollY = f4;
        } else if (scrollY > f5) {
            if (z4) {
                z3 = this.ag.onPull(Math.abs(scrollY - f5) / clientHeight);
            }
            scrollY = f5;
        }
        int i2 = (int) scrollY;
        this.O += scrollY - ((float) i2);
        scrollTo(getScrollX(), i2);
        a(i2);
        return z3;
    }

    private ItemInfo c() {
        int i2;
        int clientHeight = getClientHeight();
        float scrollY = clientHeight > 0 ? ((float) getScrollY()) / ((float) clientHeight) : 0.0f;
        float f2 = clientHeight > 0 ? ((float) this.w) / ((float) clientHeight) : 0.0f;
        ItemInfo itemInfo = null;
        int i3 = 0;
        boolean z2 = true;
        int i4 = -1;
        float f3 = 0.0f;
        float f4 = 0.0f;
        while (i3 < this.m.size()) {
            ItemInfo itemInfo2 = this.m.get(i3);
            if (!z2 && itemInfo2.b != (i2 = i4 + 1)) {
                itemInfo2 = this.n;
                itemInfo2.e = f3 + f4 + f2;
                itemInfo2.b = i2;
                itemInfo2.d = this.p.getPageWidth(itemInfo2.b);
                i3--;
            }
            f3 = itemInfo2.e;
            float f5 = itemInfo2.d + f3 + f2;
            if (!z2 && scrollY < f3) {
                return itemInfo;
            }
            if (scrollY < f5 || i3 == this.m.size() - 1) {
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
        if (Math.abs(i4) <= this.aa || Math.abs(i3) <= this.V) {
            i2 = (int) (((float) i2) + f2 + (i2 >= this.q ? 0.4f : 0.6f));
        } else if (i3 <= 0) {
            i2++;
        }
        return this.m.size() > 0 ? Math.max(this.m.get(0).b, Math.min(i2, this.m.get(this.m.size() - 1).b)) : i2;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int overScrollMode = ViewCompat.getOverScrollMode(this);
        boolean z2 = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && this.p != null && this.p.getCount() > 1)) {
            if (!this.af.isFinished()) {
                int save = canvas.save();
                int height = getHeight();
                int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.translate((float) getPaddingLeft(), this.A * ((float) height));
                this.af.setSize(width, height);
                z2 = false | this.af.draw(canvas);
                canvas.restoreToCount(save);
            }
            if (!this.ag.isFinished()) {
                int save2 = canvas.save();
                int height2 = getHeight();
                int width2 = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.rotate(180.0f);
                canvas.translate((float) ((-width2) - getPaddingLeft()), (-(this.B + 1.0f)) * ((float) height2));
                this.ag.setSize(width2, height2);
                z2 |= this.ag.draw(canvas);
                canvas.restoreToCount(save2);
            }
        } else {
            this.af.finish();
            this.ag.finish();
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
        if (this.w > 0 && this.x != null && this.m.size() > 0 && this.p != null) {
            int scrollY = getScrollY();
            int height = getHeight();
            float f5 = (float) height;
            float f6 = ((float) this.w) / f5;
            int i2 = 0;
            ItemInfo itemInfo = this.m.get(0);
            float f7 = itemInfo.e;
            int size = this.m.size();
            int i3 = itemInfo.b;
            int i4 = this.m.get(size - 1).b;
            while (i3 < i4) {
                while (i3 > itemInfo.b && i2 < size) {
                    i2++;
                    itemInfo = this.m.get(i2);
                }
                if (i3 == itemInfo.b) {
                    f3 = (itemInfo.e + itemInfo.d) * f5;
                    f2 = itemInfo.e + itemInfo.d + f6;
                } else {
                    float pageWidth = this.p.getPageWidth(i3);
                    f2 = f7 + pageWidth + f6;
                    f3 = (f7 + pageWidth) * f5;
                }
                if (((float) this.w) + f3 > ((float) scrollY)) {
                    f4 = f6;
                    this.x.setBounds(this.y, (int) f3, this.z, (int) (((float) this.w) + f3 + 0.5f));
                    this.x.draw(canvas);
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
        if (this.I) {
            return false;
        }
        this.ad = true;
        setScrollState(1);
        this.P = 0.0f;
        this.R = 0.0f;
        if (this.U == null) {
            this.U = VelocityTracker.obtain();
        } else {
            this.U.clear();
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, 0.0f, 0.0f, 0);
        this.U.addMovement(obtain);
        obtain.recycle();
        this.ae = uptimeMillis;
        return true;
    }

    public void endFakeDrag() {
        if (this.ad) {
            VelocityTracker velocityTracker = this.U;
            velocityTracker.computeCurrentVelocity(1000, (float) this.W);
            int yVelocity = (int) VelocityTrackerCompat.getYVelocity(velocityTracker, this.S);
            this.G = true;
            int clientHeight = getClientHeight();
            int scrollY = getScrollY();
            ItemInfo c2 = c();
            setCurrentItemInternal(a(c2.b, ((((float) scrollY) / ((float) clientHeight)) - c2.e) / c2.d, yVelocity, (int) (this.P - this.R)), true, true, yVelocity);
            d();
            this.ad = false;
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public void fakeDragBy(float f2) {
        if (this.ad) {
            this.P += f2;
            float scrollY = ((float) getScrollY()) - f2;
            float clientHeight = (float) getClientHeight();
            float f3 = this.A * clientHeight;
            float f4 = this.B * clientHeight;
            ItemInfo itemInfo = this.m.get(0);
            ItemInfo itemInfo2 = this.m.get(this.m.size() - 1);
            if (itemInfo.b != 0) {
                f3 = itemInfo.e * clientHeight;
            }
            if (itemInfo2.b != this.p.getCount() - 1) {
                f4 = itemInfo2.e * clientHeight;
            }
            if (scrollY < f3) {
                scrollY = f3;
            } else if (scrollY > f4) {
                scrollY = f4;
            }
            int i2 = (int) scrollY;
            this.P += scrollY - ((float) i2);
            scrollTo(getScrollX(), i2);
            a(i2);
            MotionEvent obtain = MotionEvent.obtain(this.ae, SystemClock.uptimeMillis(), 2, 0.0f, this.P, 0);
            this.U.addMovement(obtain);
            obtain.recycle();
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public boolean isFakeDragging() {
        return this.ad;
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.S) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.P = MotionEventCompat.getY(motionEvent, i2);
            this.S = MotionEventCompat.getPointerId(motionEvent, i2);
            if (this.U != null) {
                this.U.clear();
            }
        }
    }

    private void d() {
        this.I = false;
        this.J = false;
        if (this.U != null) {
            this.U.recycle();
            this.U = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z2) {
        if (this.F != z2) {
            this.F = z2;
        }
    }

    public boolean internalCanScrollVertically(int i2) {
        if (this.p == null) {
            return false;
        }
        int clientHeight = getClientHeight();
        int scrollY = getScrollY();
        if (i2 < 0) {
            if (scrollY > ((int) (((float) clientHeight) * this.A))) {
                return true;
            }
            return false;
        } else if (i2 <= 0 || scrollY >= ((int) (((float) clientHeight) * this.B))) {
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
                    Log.e(f22209a, "arrowScroll tried to find focus based on non-child current focused view " + sb.toString());
                }
            }
            view = findFocus;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, view, i2);
        if (findNextFocus != null && findNextFocus != view) {
            if (i2 == 33) {
                int i3 = a(this.o, findNextFocus).top;
                int i4 = a(this.o, view).top;
                if (view == null || i3 < i4) {
                    requestFocus = findNextFocus.requestFocus();
                } else {
                    requestFocus = pageUp();
                }
            } else if (i2 == 130) {
                int i5 = a(this.o, findNextFocus).bottom;
                int i6 = a(this.o, view).bottom;
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
        if (this.q <= 0) {
            return false;
        }
        setCurrentItem(this.q - 1, true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean pageDown() {
        if (this.p == null || this.q >= this.p.getCount() - 1) {
            return false;
        }
        setCurrentItem(this.q + 1, true);
        return true;
    }

    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        ItemInfo infoForChild;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.q) {
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
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.q) {
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
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.q && childAt.requestFocus(i2, rect)) {
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
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.q && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
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
            if (accessibilityEvent.getEventType() == 4096 && VerticalViewPager.this.p != null) {
                obtain.setItemCount(VerticalViewPager.this.p.getCount());
                obtain.setFromIndex(VerticalViewPager.this.q);
                obtain.setToIndex(VerticalViewPager.this.q);
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
                VerticalViewPager.this.setCurrentItem(VerticalViewPager.this.q - 1);
                return true;
            } else if (!VerticalViewPager.this.internalCanScrollVertically(1)) {
                return false;
            } else {
                VerticalViewPager.this.setCurrentItem(VerticalViewPager.this.q + 1);
                return true;
            }
        }

        private boolean a() {
            return VerticalViewPager.this.p != null && VerticalViewPager.this.p.getCount() > 1;
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
        public boolean f22212a;
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
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, VerticalViewPager.i);
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
            if (layoutParams.f22212a != layoutParams2.f22212a) {
                return layoutParams.f22212a ? 1 : -1;
            }
            return layoutParams.e - layoutParams2.e;
        }
    }
}
