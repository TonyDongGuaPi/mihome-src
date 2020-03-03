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
import android.support.annotation.DrawableRes;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
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
import java.util.List;

public class ViewPager extends ViewGroup {
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final int V = -1;

    /* renamed from: a  reason: collision with root package name */
    private static final String f7178a = "ViewPager";
    private static final int ae = 2;
    private static final int at = 0;
    private static final int au = 1;
    private static final int av = 2;
    private static final ViewPositionComparator ay = new ViewPositionComparator();
    private static final boolean b = false;
    private static final boolean c = false;
    private static final float d = 0.1f;
    private static final float e = 0.9f;
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
    private Drawable A;
    private int B;
    private int C;
    private float D = -3.4028235E38f;
    private float E = Float.MAX_VALUE;
    private int F;
    private int G;
    private boolean H;
    private boolean I;
    private boolean J;
    private int K = 1;
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
    private int aA = 0;
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
    private List<OnPageChangeListener> an;
    private OnPageChangeListener ao;
    private OnPageChangeListener ap;
    private OnAdapterChangeListener aq;
    private PageTransformer ar;
    private Method as;
    private int aw;
    private ArrayList<View> ax;
    private final Runnable az = new Runnable() {
        public void run() {
            ViewPager.this.setScrollState(0);
            ViewPager.this.populate();
        }
    };
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
    private boolean x;
    private PagerObserver y;
    private int z;

    interface Decor {
    }

    interface OnAdapterChangeListener {
        void a(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2);
    }

    public interface OnPageChangeListener {
        void a(int i);

        void a(int i, float f, int i2);

        void b(int i);
    }

    public interface PageTransformer {
        void a(View view, float f);
    }

    public static class SimpleOnPageChangeListener implements OnPageChangeListener {
        public void a(int i) {
        }

        public void a(int i, float f, int i2) {
        }

        public void b(int i) {
        }
    }

    static class ItemInfo {

        /* renamed from: a  reason: collision with root package name */
        Object f7181a;
        int b;
        boolean c;
        float d;
        float e;

        ItemInfo() {
        }
    }

    public ViewPager(Context context) {
        super(context);
        initViewPager();
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
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
        ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener() {
            private final Rect b = new Rect();

            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat onApplyWindowInsets = ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
                if (onApplyWindowInsets.isConsumed()) {
                    return onApplyWindowInsets;
                }
                Rect rect = this.b;
                rect.left = onApplyWindowInsets.getSystemWindowInsetLeft();
                rect.top = onApplyWindowInsets.getSystemWindowInsetTop();
                rect.right = onApplyWindowInsets.getSystemWindowInsetRight();
                rect.bottom = onApplyWindowInsets.getSystemWindowInsetBottom();
                int childCount = ViewPager.this.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    WindowInsetsCompat dispatchApplyWindowInsets = ViewCompat.dispatchApplyWindowInsets(ViewPager.this.getChildAt(i), onApplyWindowInsets);
                    rect.left = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetLeft(), rect.left);
                    rect.top = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetTop(), rect.top);
                    rect.right = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetRight(), rect.right);
                    rect.bottom = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetBottom(), rect.bottom);
                }
                return onApplyWindowInsets.replaceSystemWindowInsets(rect.left, rect.top, rect.right, rect.bottom);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        removeCallbacks(this.az);
        if (this.w != null && !this.w.isFinished()) {
            this.w.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: private */
    public void setScrollState(int i2) {
        if (this.aA != i2) {
            this.aA = i2;
            if (this.ar != null) {
                b(i2 != 0);
            }
            c(i2);
        }
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        if (this.r != null) {
            this.r.unregisterDataSetObserver(this.y);
            this.r.startUpdate((ViewGroup) this);
            for (int i2 = 0; i2 < this.o.size(); i2++) {
                ItemInfo itemInfo = this.o.get(i2);
                this.r.destroyItem((ViewGroup) this, itemInfo.b, itemInfo.f7181a);
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
            if (this.y == null) {
                this.y = new PagerObserver();
            }
            this.r.registerDataSetObserver(this.y);
            this.J = false;
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
        if (this.aq != null && pagerAdapter2 != pagerAdapter) {
            this.aq.a(pagerAdapter2, pagerAdapter);
        }
    }

    private void a() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((LayoutParams) getChildAt(i2).getLayoutParams()).f7182a) {
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
        this.aq = onAdapterChangeListener;
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public void setCurrentItem(int i2) {
        this.J = false;
        setCurrentItemInternal(i2, !this.aj, false);
    }

    public void setCurrentItem(int i2, boolean z2) {
        this.J = false;
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
            int i4 = this.K;
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
                if (z4) {
                    b(i2);
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
        int clientWidth = infoForPosition != null ? (int) (((float) getClientWidth()) * Math.max(this.D, Math.min(infoForPosition.e, this.E))) : 0;
        if (z2) {
            smoothScrollTo(clientWidth, 0, i3);
            if (z3) {
                b(i2);
                return;
            }
            return;
        }
        if (z3) {
            b(i2);
        }
        a(false);
        scrollTo(clientWidth, 0);
        a(clientWidth);
    }

    @Deprecated
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.ao = onPageChangeListener;
    }

    public void addOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        if (this.an == null) {
            this.an = new ArrayList();
        }
        this.an.add(onPageChangeListener);
    }

    public void removeOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        if (this.an != null) {
            this.an.remove(onPageChangeListener);
        }
    }

    public void clearOnPageChangeListeners() {
        if (this.an != null) {
            this.an.clear();
        }
    }

    public void setPageTransformer(boolean z2, PageTransformer pageTransformer) {
        if (Build.VERSION.SDK_INT >= 11) {
            int i2 = 1;
            boolean z3 = pageTransformer != null;
            boolean z4 = z3 != (this.ar != null);
            this.ar = pageTransformer;
            setChildrenDrawingOrderEnabledCompat(z3);
            if (z3) {
                if (z2) {
                    i2 = 2;
                }
                this.aw = i2;
            } else {
                this.aw = 0;
            }
            if (z4) {
                populate();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setChildrenDrawingOrderEnabledCompat(boolean z2) {
        if (Build.VERSION.SDK_INT >= 7) {
            if (this.as == null) {
                Class<ViewGroup> cls = ViewGroup.class;
                try {
                    this.as = cls.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
                } catch (NoSuchMethodException e2) {
                    Log.e(f7178a, "Can't find setChildrenDrawingOrderEnabled", e2);
                }
            }
            try {
                this.as.invoke(this, new Object[]{Boolean.valueOf(z2)});
            } catch (Exception e3) {
                Log.e(f7178a, "Error changing children drawing order", e3);
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i2, int i3) {
        if (this.aw == 2) {
            i3 = (i2 - 1) - i3;
        }
        return ((LayoutParams) this.ax.get(i3).getLayoutParams()).f;
    }

    /* access modifiers changed from: package-private */
    public OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener onPageChangeListener) {
        OnPageChangeListener onPageChangeListener2 = this.ap;
        this.ap = onPageChangeListener;
        return onPageChangeListener2;
    }

    public int getOffscreenPageLimit() {
        return this.K;
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            Log.w(f7178a, "Requested offscreen page limit " + i2 + " too small; defaulting to " + 1);
            i2 = 1;
        }
        if (i2 != this.K) {
            this.K = i2;
            populate();
        }
    }

    public void setPageMargin(int i2) {
        int i3 = this.z;
        this.z = i2;
        int width = getWidth();
        a(width, width, i2, i3);
        requestLayout();
    }

    public int getPageMargin() {
        return this.z;
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.A = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageMarginDrawable(@DrawableRes int i2) {
        setPageMarginDrawable(getContext().getResources().getDrawable(i2));
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.A;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.A;
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
        int i6;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (this.w != null && !this.w.isFinished()) {
            i5 = this.x ? this.w.getCurrX() : this.w.getStartX();
            this.w.abortAnimation();
            setScrollingCacheEnabled(false);
        } else {
            i5 = getScrollX();
        }
        int i7 = i5;
        int scrollY = getScrollY();
        int i8 = i2 - i7;
        int i9 = i3 - scrollY;
        if (i8 == 0 && i9 == 0) {
            a(false);
            populate();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i10 = clientWidth / 2;
        float f2 = (float) clientWidth;
        float f3 = (float) i10;
        float distanceInfluenceForSnapDuration = f3 + (distanceInfluenceForSnapDuration(Math.min(1.0f, (((float) Math.abs(i8)) * 1.0f) / f2)) * f3);
        int abs = Math.abs(i4);
        if (abs > 0) {
            i6 = Math.round(Math.abs(distanceInfluenceForSnapDuration / ((float) abs)) * 1000.0f) * 4;
        } else {
            i6 = (int) (((((float) Math.abs(i8)) / ((f2 * this.r.getPageWidth(this.s)) + ((float) this.z))) + 1.0f) * 100.0f);
        }
        int min = Math.min(i6, 600);
        this.x = false;
        this.w.startScroll(i7, scrollY, i8, i9, min);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /* access modifiers changed from: package-private */
    public ItemInfo addNewItem(int i2, int i3) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.b = i2;
        itemInfo.f7181a = this.r.instantiateItem((ViewGroup) this, i2);
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
        boolean z2 = this.o.size() < (this.K * 2) + 1 && this.o.size() < count;
        int i2 = this.s;
        int i3 = 0;
        boolean z3 = false;
        while (i3 < this.o.size()) {
            ItemInfo itemInfo = this.o.get(i3);
            int itemPosition = this.r.getItemPosition(itemInfo.f7181a);
            if (itemPosition != -1) {
                if (itemPosition == -2) {
                    this.o.remove(i3);
                    i3--;
                    if (!z3) {
                        this.r.startUpdate((ViewGroup) this);
                        z3 = true;
                    }
                    this.r.destroyItem((ViewGroup) this, itemInfo.b, itemInfo.f7181a);
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
                if (!layoutParams.f7182a) {
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
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0066, code lost:
        if (r8.b == r0.s) goto L_0x006d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void populate(int r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            int r2 = r0.s
            if (r2 == r1) goto L_0x0011
            int r2 = r0.s
            com.mi.global.shop.widget.ViewPager$ItemInfo r2 = r0.infoForPosition(r2)
            r0.s = r1
            goto L_0x0012
        L_0x0011:
            r2 = 0
        L_0x0012:
            android.support.v4.view.PagerAdapter r1 = r0.r
            if (r1 != 0) goto L_0x001a
            r17.b()
            return
        L_0x001a:
            boolean r1 = r0.J
            if (r1 == 0) goto L_0x0022
            r17.b()
            return
        L_0x0022:
            android.os.IBinder r1 = r17.getWindowToken()
            if (r1 != 0) goto L_0x0029
            return
        L_0x0029:
            android.support.v4.view.PagerAdapter r1 = r0.r
            r1.startUpdate((android.view.ViewGroup) r0)
            int r1 = r0.K
            int r4 = r0.s
            int r4 = r4 - r1
            r5 = 0
            int r4 = java.lang.Math.max(r5, r4)
            android.support.v4.view.PagerAdapter r6 = r0.r
            int r6 = r6.getCount()
            int r7 = r6 + -1
            int r8 = r0.s
            int r8 = r8 + r1
            int r1 = java.lang.Math.min(r7, r8)
            int r7 = r0.l
            if (r6 != r7) goto L_0x021a
            r7 = 0
        L_0x004c:
            java.util.ArrayList<com.mi.global.shop.widget.ViewPager$ItemInfo> r8 = r0.o
            int r8 = r8.size()
            if (r7 >= r8) goto L_0x006c
            java.util.ArrayList<com.mi.global.shop.widget.ViewPager$ItemInfo> r8 = r0.o
            java.lang.Object r8 = r8.get(r7)
            com.mi.global.shop.widget.ViewPager$ItemInfo r8 = (com.mi.global.shop.widget.ViewPager.ItemInfo) r8
            int r9 = r8.b
            int r10 = r0.s
            if (r9 < r10) goto L_0x0069
            int r9 = r8.b
            int r10 = r0.s
            if (r9 != r10) goto L_0x006c
            goto L_0x006d
        L_0x0069:
            int r7 = r7 + 1
            goto L_0x004c
        L_0x006c:
            r8 = 0
        L_0x006d:
            if (r8 != 0) goto L_0x0077
            if (r6 <= 0) goto L_0x0077
            int r8 = r0.s
            com.mi.global.shop.widget.ViewPager$ItemInfo r8 = r0.addNewItem(r8, r7)
        L_0x0077:
            r9 = 0
            if (r8 == 0) goto L_0x019a
            int r10 = r7 + -1
            if (r10 < 0) goto L_0x0087
            java.util.ArrayList<com.mi.global.shop.widget.ViewPager$ItemInfo> r11 = r0.o
            java.lang.Object r11 = r11.get(r10)
            com.mi.global.shop.widget.ViewPager$ItemInfo r11 = (com.mi.global.shop.widget.ViewPager.ItemInfo) r11
            goto L_0x0088
        L_0x0087:
            r11 = 0
        L_0x0088:
            int r12 = r17.getClientWidth()
            r13 = 1073741824(0x40000000, float:2.0)
            if (r12 > 0) goto L_0x0092
            r3 = 0
            goto L_0x009f
        L_0x0092:
            float r14 = r8.d
            float r14 = r13 - r14
            int r15 = r17.getPaddingLeft()
            float r15 = (float) r15
            float r3 = (float) r12
            float r15 = r15 / r3
            float r3 = r14 + r15
        L_0x009f:
            int r14 = r0.s
            int r14 = r14 + -1
            r15 = r7
            r7 = 0
        L_0x00a5:
            if (r14 < 0) goto L_0x0105
            int r16 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r16 < 0) goto L_0x00d3
            if (r14 >= r4) goto L_0x00d3
            if (r11 != 0) goto L_0x00b0
            goto L_0x0105
        L_0x00b0:
            int r5 = r11.b
            if (r14 != r5) goto L_0x0101
            boolean r5 = r11.c
            if (r5 != 0) goto L_0x0101
            java.util.ArrayList<com.mi.global.shop.widget.ViewPager$ItemInfo> r5 = r0.o
            r5.remove(r10)
            android.support.v4.view.PagerAdapter r5 = r0.r
            java.lang.Object r11 = r11.f7181a
            r5.destroyItem((android.view.ViewGroup) r0, (int) r14, (java.lang.Object) r11)
            int r10 = r10 + -1
            int r15 = r15 + -1
            if (r10 < 0) goto L_0x00ff
            java.util.ArrayList<com.mi.global.shop.widget.ViewPager$ItemInfo> r5 = r0.o
            java.lang.Object r5 = r5.get(r10)
            com.mi.global.shop.widget.ViewPager$ItemInfo r5 = (com.mi.global.shop.widget.ViewPager.ItemInfo) r5
            goto L_0x0100
        L_0x00d3:
            if (r11 == 0) goto L_0x00e9
            int r5 = r11.b
            if (r14 != r5) goto L_0x00e9
            float r5 = r11.d
            float r7 = r7 + r5
            int r10 = r10 + -1
            if (r10 < 0) goto L_0x00ff
            java.util.ArrayList<com.mi.global.shop.widget.ViewPager$ItemInfo> r5 = r0.o
            java.lang.Object r5 = r5.get(r10)
            com.mi.global.shop.widget.ViewPager$ItemInfo r5 = (com.mi.global.shop.widget.ViewPager.ItemInfo) r5
            goto L_0x0100
        L_0x00e9:
            int r5 = r10 + 1
            com.mi.global.shop.widget.ViewPager$ItemInfo r5 = r0.addNewItem(r14, r5)
            float r5 = r5.d
            float r7 = r7 + r5
            int r15 = r15 + 1
            if (r10 < 0) goto L_0x00ff
            java.util.ArrayList<com.mi.global.shop.widget.ViewPager$ItemInfo> r5 = r0.o
            java.lang.Object r5 = r5.get(r10)
            com.mi.global.shop.widget.ViewPager$ItemInfo r5 = (com.mi.global.shop.widget.ViewPager.ItemInfo) r5
            goto L_0x0100
        L_0x00ff:
            r5 = 0
        L_0x0100:
            r11 = r5
        L_0x0101:
            int r14 = r14 + -1
            r5 = 0
            goto L_0x00a5
        L_0x0105:
            float r3 = r8.d
            int r4 = r15 + 1
            int r5 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r5 >= 0) goto L_0x0197
            java.util.ArrayList<com.mi.global.shop.widget.ViewPager$ItemInfo> r5 = r0.o
            int r5 = r5.size()
            if (r4 >= r5) goto L_0x011e
            java.util.ArrayList<com.mi.global.shop.widget.ViewPager$ItemInfo> r5 = r0.o
            java.lang.Object r5 = r5.get(r4)
            com.mi.global.shop.widget.ViewPager$ItemInfo r5 = (com.mi.global.shop.widget.ViewPager.ItemInfo) r5
            goto L_0x011f
        L_0x011e:
            r5 = 0
        L_0x011f:
            if (r12 > 0) goto L_0x0123
            r7 = 0
            goto L_0x012b
        L_0x0123:
            int r7 = r17.getPaddingRight()
            float r7 = (float) r7
            float r10 = (float) r12
            float r7 = r7 / r10
            float r7 = r7 + r13
        L_0x012b:
            int r10 = r0.s
        L_0x012d:
            int r10 = r10 + 1
            if (r10 >= r6) goto L_0x0197
            int r11 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r11 < 0) goto L_0x0161
            if (r10 <= r1) goto L_0x0161
            if (r5 != 0) goto L_0x013a
            goto L_0x0197
        L_0x013a:
            int r11 = r5.b
            if (r10 != r11) goto L_0x0196
            boolean r11 = r5.c
            if (r11 != 0) goto L_0x0196
            java.util.ArrayList<com.mi.global.shop.widget.ViewPager$ItemInfo> r11 = r0.o
            r11.remove(r4)
            android.support.v4.view.PagerAdapter r11 = r0.r
            java.lang.Object r5 = r5.f7181a
            r11.destroyItem((android.view.ViewGroup) r0, (int) r10, (java.lang.Object) r5)
            java.util.ArrayList<com.mi.global.shop.widget.ViewPager$ItemInfo> r5 = r0.o
            int r5 = r5.size()
            if (r4 >= r5) goto L_0x015f
            java.util.ArrayList<com.mi.global.shop.widget.ViewPager$ItemInfo> r5 = r0.o
            java.lang.Object r5 = r5.get(r4)
            com.mi.global.shop.widget.ViewPager$ItemInfo r5 = (com.mi.global.shop.widget.ViewPager.ItemInfo) r5
            goto L_0x0196
        L_0x015f:
            r5 = 0
            goto L_0x0196
        L_0x0161:
            if (r5 == 0) goto L_0x017d
            int r11 = r5.b
            if (r10 != r11) goto L_0x017d
            float r5 = r5.d
            float r3 = r3 + r5
            int r4 = r4 + 1
            java.util.ArrayList<com.mi.global.shop.widget.ViewPager$ItemInfo> r5 = r0.o
            int r5 = r5.size()
            if (r4 >= r5) goto L_0x015f
            java.util.ArrayList<com.mi.global.shop.widget.ViewPager$ItemInfo> r5 = r0.o
            java.lang.Object r5 = r5.get(r4)
            com.mi.global.shop.widget.ViewPager$ItemInfo r5 = (com.mi.global.shop.widget.ViewPager.ItemInfo) r5
            goto L_0x0196
        L_0x017d:
            com.mi.global.shop.widget.ViewPager$ItemInfo r5 = r0.addNewItem(r10, r4)
            int r4 = r4 + 1
            float r5 = r5.d
            float r3 = r3 + r5
            java.util.ArrayList<com.mi.global.shop.widget.ViewPager$ItemInfo> r5 = r0.o
            int r5 = r5.size()
            if (r4 >= r5) goto L_0x015f
            java.util.ArrayList<com.mi.global.shop.widget.ViewPager$ItemInfo> r5 = r0.o
            java.lang.Object r5 = r5.get(r4)
            com.mi.global.shop.widget.ViewPager$ItemInfo r5 = (com.mi.global.shop.widget.ViewPager.ItemInfo) r5
        L_0x0196:
            goto L_0x012d
        L_0x0197:
            r0.a((com.mi.global.shop.widget.ViewPager.ItemInfo) r8, (int) r15, (com.mi.global.shop.widget.ViewPager.ItemInfo) r2)
        L_0x019a:
            android.support.v4.view.PagerAdapter r1 = r0.r
            int r2 = r0.s
            if (r8 == 0) goto L_0x01a3
            java.lang.Object r3 = r8.f7181a
            goto L_0x01a4
        L_0x01a3:
            r3 = 0
        L_0x01a4:
            r1.setPrimaryItem((android.view.ViewGroup) r0, (int) r2, (java.lang.Object) r3)
            android.support.v4.view.PagerAdapter r1 = r0.r
            r1.finishUpdate((android.view.ViewGroup) r0)
            int r1 = r17.getChildCount()
            r2 = 0
        L_0x01b1:
            if (r2 >= r1) goto L_0x01da
            android.view.View r3 = r0.getChildAt(r2)
            android.view.ViewGroup$LayoutParams r4 = r3.getLayoutParams()
            com.mi.global.shop.widget.ViewPager$LayoutParams r4 = (com.mi.global.shop.widget.ViewPager.LayoutParams) r4
            r4.f = r2
            boolean r5 = r4.f7182a
            if (r5 != 0) goto L_0x01d7
            float r5 = r4.c
            int r5 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r5 != 0) goto L_0x01d7
            com.mi.global.shop.widget.ViewPager$ItemInfo r3 = r0.infoForChild(r3)
            if (r3 == 0) goto L_0x01d7
            float r5 = r3.d
            r4.c = r5
            int r3 = r3.b
            r4.e = r3
        L_0x01d7:
            int r2 = r2 + 1
            goto L_0x01b1
        L_0x01da:
            r17.b()
            boolean r1 = r17.hasFocus()
            if (r1 == 0) goto L_0x0219
            android.view.View r1 = r17.findFocus()
            if (r1 == 0) goto L_0x01ee
            com.mi.global.shop.widget.ViewPager$ItemInfo r3 = r0.infoForAnyChild(r1)
            goto L_0x01ef
        L_0x01ee:
            r3 = 0
        L_0x01ef:
            if (r3 == 0) goto L_0x01f7
            int r1 = r3.b
            int r2 = r0.s
            if (r1 == r2) goto L_0x0219
        L_0x01f7:
            r1 = 0
        L_0x01f8:
            int r2 = r17.getChildCount()
            if (r1 >= r2) goto L_0x0219
            android.view.View r2 = r0.getChildAt(r1)
            com.mi.global.shop.widget.ViewPager$ItemInfo r3 = r0.infoForChild(r2)
            if (r3 == 0) goto L_0x0216
            int r3 = r3.b
            int r4 = r0.s
            if (r3 != r4) goto L_0x0216
            r3 = 2
            boolean r2 = r2.requestFocus(r3)
            if (r2 == 0) goto L_0x0216
            goto L_0x0219
        L_0x0216:
            int r1 = r1 + 1
            goto L_0x01f8
        L_0x0219:
            return
        L_0x021a:
            android.content.res.Resources r1 = r17.getResources()     // Catch:{ NotFoundException -> 0x0227 }
            int r2 = r17.getId()     // Catch:{ NotFoundException -> 0x0227 }
            java.lang.String r1 = r1.getResourceName(r2)     // Catch:{ NotFoundException -> 0x0227 }
            goto L_0x022f
        L_0x0227:
            int r1 = r17.getId()
            java.lang.String r1 = java.lang.Integer.toHexString(r1)
        L_0x022f:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: "
            r3.append(r4)
            int r4 = r0.l
            r3.append(r4)
            java.lang.String r4 = ", found: "
            r3.append(r4)
            r3.append(r6)
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
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.widget.ViewPager.populate(int):void");
    }

    private void b() {
        if (this.aw != 0) {
            if (this.ax == null) {
                this.ax = new ArrayList<>();
            } else {
                this.ax.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.ax.add(getChildAt(i2));
            }
            Collections.sort(this.ax, ay);
        }
    }

    private void a(ItemInfo itemInfo, int i2, ItemInfo itemInfo2) {
        ItemInfo itemInfo3;
        ItemInfo itemInfo4;
        int count = this.r.getCount();
        int clientWidth = getClientWidth();
        float f2 = clientWidth > 0 ? ((float) this.z) / ((float) clientWidth) : 0.0f;
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
        this.D = itemInfo.b == 0 ? itemInfo.e : -3.4028235E38f;
        int i7 = count - 1;
        this.E = itemInfo.b == i7 ? (itemInfo.e + itemInfo.d) - 1.0f : Float.MAX_VALUE;
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
                this.D = f5;
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
                this.E = (itemInfo6.d + f6) - 1.0f;
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
        int f7185a;
        Parcelable b;
        ClassLoader c;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f7185a);
            parcel.writeParcelable(this.b, i);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f7185a + "}";
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.f7185a = parcel.readInt();
            this.b = parcel.readParcelable(classLoader);
            this.c = classLoader;
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f7185a = this.s;
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
            setCurrentItemInternal(savedState.f7185a, false, true);
            return;
        }
        this.t = savedState.f7185a;
        this.u = savedState.b;
        this.v = savedState.c;
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        layoutParams2.f7182a |= view instanceof Decor;
        if (!this.H) {
            super.addView(view, i2, layoutParams);
        } else if (layoutParams2 == null || !layoutParams2.f7182a) {
            layoutParams2.d = true;
            addViewInLayout(view, i2, layoutParams);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    public void removeView(View view) {
        if (this.H) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    /* access modifiers changed from: package-private */
    public ItemInfo infoForChild(View view) {
        for (int i2 = 0; i2 < this.o.size(); i2++) {
            ItemInfo itemInfo = this.o.get(i2);
            if (this.r.isViewFromObject(view, itemInfo.f7181a)) {
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
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ba  */
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
            int r2 = r16.getMeasuredWidth()
            int r3 = r2 / 10
            int r4 = r0.N
            int r3 = java.lang.Math.min(r3, r4)
            r0.O = r3
            int r3 = r16.getPaddingLeft()
            int r2 = r2 - r3
            int r3 = r16.getPaddingRight()
            int r2 = r2 - r3
            int r3 = r16.getMeasuredHeight()
            int r4 = r16.getPaddingTop()
            int r3 = r3 - r4
            int r4 = r16.getPaddingBottom()
            int r3 = r3 - r4
            int r4 = r16.getChildCount()
            r5 = r3
            r3 = r2
            r2 = 0
        L_0x003f:
            r6 = 8
            r7 = 1
            r8 = 1073741824(0x40000000, float:2.0)
            if (r2 >= r4) goto L_0x00c6
            android.view.View r9 = r0.getChildAt(r2)
            int r10 = r9.getVisibility()
            if (r10 == r6) goto L_0x00c1
            android.view.ViewGroup$LayoutParams r6 = r9.getLayoutParams()
            com.mi.global.shop.widget.ViewPager$LayoutParams r6 = (com.mi.global.shop.widget.ViewPager.LayoutParams) r6
            if (r6 == 0) goto L_0x00c1
            boolean r10 = r6.f7182a
            if (r10 == 0) goto L_0x00c1
            int r10 = r6.b
            r10 = r10 & 7
            int r11 = r6.b
            r11 = r11 & 112(0x70, float:1.57E-43)
            r12 = 48
            if (r11 == r12) goto L_0x006f
            r12 = 80
            if (r11 != r12) goto L_0x006d
            goto L_0x006f
        L_0x006d:
            r11 = 0
            goto L_0x0070
        L_0x006f:
            r11 = 1
        L_0x0070:
            r12 = 3
            if (r10 == r12) goto L_0x0078
            r12 = 5
            if (r10 != r12) goto L_0x0077
            goto L_0x0078
        L_0x0077:
            r7 = 0
        L_0x0078:
            r10 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r11 == 0) goto L_0x0081
            r10 = 1073741824(0x40000000, float:2.0)
        L_0x007e:
            r12 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x0085
        L_0x0081:
            if (r7 == 0) goto L_0x007e
            r12 = 1073741824(0x40000000, float:2.0)
        L_0x0085:
            int r13 = r6.width
            r14 = -1
            r15 = -2
            if (r13 == r15) goto L_0x0097
            int r10 = r6.width
            if (r10 == r14) goto L_0x0093
            int r10 = r6.width
            r13 = r10
            goto L_0x0094
        L_0x0093:
            r13 = r3
        L_0x0094:
            r10 = 1073741824(0x40000000, float:2.0)
            goto L_0x0098
        L_0x0097:
            r13 = r3
        L_0x0098:
            int r1 = r6.height
            if (r1 == r15) goto L_0x00a5
            int r1 = r6.height
            if (r1 == r14) goto L_0x00a3
            int r1 = r6.height
            goto L_0x00a7
        L_0x00a3:
            r1 = r5
            goto L_0x00a7
        L_0x00a5:
            r1 = r5
            r8 = r12
        L_0x00a7:
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r13, r10)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r8)
            r9.measure(r6, r1)
            if (r11 == 0) goto L_0x00ba
            int r1 = r9.getMeasuredHeight()
            int r5 = r5 - r1
            goto L_0x00c1
        L_0x00ba:
            if (r7 == 0) goto L_0x00c1
            int r1 = r9.getMeasuredWidth()
            int r3 = r3 - r1
        L_0x00c1:
            int r2 = r2 + 1
            r1 = 0
            goto L_0x003f
        L_0x00c6:
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r8)
            r0.F = r1
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r8)
            r0.G = r1
            r0.H = r7
            r16.populate()
            r1 = 0
            r0.H = r1
            int r2 = r16.getChildCount()
        L_0x00de:
            if (r1 >= r2) goto L_0x0108
            android.view.View r4 = r0.getChildAt(r1)
            int r5 = r4.getVisibility()
            if (r5 == r6) goto L_0x0105
            android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
            com.mi.global.shop.widget.ViewPager$LayoutParams r5 = (com.mi.global.shop.widget.ViewPager.LayoutParams) r5
            if (r5 == 0) goto L_0x00f6
            boolean r7 = r5.f7182a
            if (r7 != 0) goto L_0x0105
        L_0x00f6:
            float r7 = (float) r3
            float r5 = r5.c
            float r7 = r7 * r5
            int r5 = (int) r7
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r8)
            int r7 = r0.G
            r4.measure(r5, r7)
        L_0x0105:
            int r1 = r1 + 1
            goto L_0x00de
        L_0x0108:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.widget.ViewPager.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            a(i2, i4, this.z, this.z);
        }
    }

    private void a(int i2, int i3, int i4, int i5) {
        if (i3 <= 0 || this.o.isEmpty()) {
            ItemInfo infoForPosition = infoForPosition(this.s);
            int min = (int) ((infoForPosition != null ? Math.min(infoForPosition.e, this.E) : 0.0f) * ((float) ((i2 - getPaddingLeft()) - getPaddingRight())));
            if (min != getScrollX()) {
                a(false);
                scrollTo(min, getScrollY());
            }
        } else if (!this.w.isFinished()) {
            this.w.setFinalX(getCurrentItem() * getClientWidth());
        } else {
            scrollTo((int) ((((float) getScrollX()) / ((float) (((i3 - getPaddingLeft()) - getPaddingRight()) + i5))) * ((float) (((i2 - getPaddingLeft()) - getPaddingRight()) + i4))), getScrollY());
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
        int scrollX = getScrollX();
        int i10 = paddingBottom;
        int i11 = 0;
        int i12 = paddingTop;
        int i13 = paddingLeft;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.f7182a) {
                    int i15 = layoutParams.b & 7;
                    int i16 = layoutParams.b & 112;
                    if (i15 == 1) {
                        i6 = Math.max((i8 - childAt.getMeasuredWidth()) / 2, i13);
                    } else if (i15 == 3) {
                        i6 = i13;
                        i13 = childAt.getMeasuredWidth() + i13;
                    } else if (i15 != 5) {
                        i6 = i13;
                    } else {
                        i6 = (i8 - paddingRight) - childAt.getMeasuredWidth();
                        paddingRight += childAt.getMeasuredWidth();
                    }
                    if (i16 == 16) {
                        i7 = Math.max((i9 - childAt.getMeasuredHeight()) / 2, i12);
                    } else if (i16 == 48) {
                        i7 = i12;
                        i12 = childAt.getMeasuredHeight() + i12;
                    } else if (i16 != 80) {
                        i7 = i12;
                    } else {
                        i7 = (i9 - i10) - childAt.getMeasuredHeight();
                        i10 += childAt.getMeasuredHeight();
                    }
                    int i17 = i6 + scrollX;
                    childAt.layout(i17, i7, childAt.getMeasuredWidth() + i17, i7 + childAt.getMeasuredHeight());
                    i11++;
                }
            }
        }
        int i18 = (i8 - i13) - paddingRight;
        for (int i19 = 0; i19 < childCount; i19++) {
            View childAt2 = getChildAt(i19);
            if (childAt2.getVisibility() != 8) {
                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                if (!layoutParams2.f7182a && (infoForChild = infoForChild(childAt2)) != null) {
                    float f2 = (float) i18;
                    int i20 = ((int) (infoForChild.e * f2)) + i13;
                    if (layoutParams2.d) {
                        layoutParams2.d = false;
                        childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (f2 * layoutParams2.c), 1073741824), View.MeasureSpec.makeMeasureSpec((i9 - i12) - i10, 1073741824));
                    }
                    childAt2.layout(i20, i12, childAt2.getMeasuredWidth() + i20, childAt2.getMeasuredHeight() + i12);
                }
            }
        }
        this.B = i12;
        this.C = i9 - i10;
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
        this.x = true;
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
            if (!a(currX)) {
                this.w.abortAnimation();
                scrollTo(0, currY);
            }
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private boolean a(int i2) {
        if (this.o.size() != 0) {
            ItemInfo d2 = d();
            int clientWidth = getClientWidth();
            int i3 = this.z + clientWidth;
            float f2 = (float) clientWidth;
            float f3 = ((float) this.z) / f2;
            int i4 = d2.b;
            float f4 = ((((float) i2) / f2) - d2.e) / (d2.d + f3);
            this.al = false;
            onPageScrolled(i4, f4, (int) (((float) i3) * f4));
            if (this.al) {
                return true;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        } else if (this.aj) {
            return false;
        } else {
            this.al = false;
            onPageScrolled(0, 0.0f, 0);
            if (this.al) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0066  */
    @android.support.annotation.CallSuper
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPageScrolled(int r13, float r14, int r15) {
        /*
            r12 = this;
            int r0 = r12.am
            r1 = 0
            r2 = 1
            if (r0 <= 0) goto L_0x006d
            int r0 = r12.getScrollX()
            int r3 = r12.getPaddingLeft()
            int r4 = r12.getPaddingRight()
            int r5 = r12.getWidth()
            int r6 = r12.getChildCount()
            r7 = r4
            r4 = r3
            r3 = 0
        L_0x001d:
            if (r3 >= r6) goto L_0x006d
            android.view.View r8 = r12.getChildAt(r3)
            android.view.ViewGroup$LayoutParams r9 = r8.getLayoutParams()
            com.mi.global.shop.widget.ViewPager$LayoutParams r9 = (com.mi.global.shop.widget.ViewPager.LayoutParams) r9
            boolean r10 = r9.f7182a
            if (r10 != 0) goto L_0x002e
            goto L_0x006a
        L_0x002e:
            int r9 = r9.b
            r9 = r9 & 7
            if (r9 == r2) goto L_0x004f
            r10 = 3
            if (r9 == r10) goto L_0x0049
            r10 = 5
            if (r9 == r10) goto L_0x003c
            r9 = r4
            goto L_0x005e
        L_0x003c:
            int r9 = r5 - r7
            int r10 = r8.getMeasuredWidth()
            int r9 = r9 - r10
            int r10 = r8.getMeasuredWidth()
            int r7 = r7 + r10
            goto L_0x005b
        L_0x0049:
            int r9 = r8.getWidth()
            int r9 = r9 + r4
            goto L_0x005e
        L_0x004f:
            int r9 = r8.getMeasuredWidth()
            int r9 = r5 - r9
            int r9 = r9 / 2
            int r9 = java.lang.Math.max(r9, r4)
        L_0x005b:
            r11 = r9
            r9 = r4
            r4 = r11
        L_0x005e:
            int r4 = r4 + r0
            int r10 = r8.getLeft()
            int r4 = r4 - r10
            if (r4 == 0) goto L_0x0069
            r8.offsetLeftAndRight(r4)
        L_0x0069:
            r4 = r9
        L_0x006a:
            int r3 = r3 + 1
            goto L_0x001d
        L_0x006d:
            r12.a((int) r13, (float) r14, (int) r15)
            com.mi.global.shop.widget.ViewPager$PageTransformer r13 = r12.ar
            if (r13 == 0) goto L_0x00a1
            int r13 = r12.getScrollX()
            int r14 = r12.getChildCount()
        L_0x007c:
            if (r1 >= r14) goto L_0x00a1
            android.view.View r15 = r12.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r0 = r15.getLayoutParams()
            com.mi.global.shop.widget.ViewPager$LayoutParams r0 = (com.mi.global.shop.widget.ViewPager.LayoutParams) r0
            boolean r0 = r0.f7182a
            if (r0 == 0) goto L_0x008d
            goto L_0x009e
        L_0x008d:
            int r0 = r15.getLeft()
            int r0 = r0 - r13
            float r0 = (float) r0
            int r3 = r12.getClientWidth()
            float r3 = (float) r3
            float r0 = r0 / r3
            com.mi.global.shop.widget.ViewPager$PageTransformer r3 = r12.ar
            r3.a(r15, r0)
        L_0x009e:
            int r1 = r1 + 1
            goto L_0x007c
        L_0x00a1:
            r12.al = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.widget.ViewPager.onPageScrolled(int, float, int):void");
    }

    private void a(int i2, float f2, int i3) {
        if (this.ao != null) {
            this.ao.a(i2, f2, i3);
        }
        if (this.an != null) {
            int size = this.an.size();
            for (int i4 = 0; i4 < size; i4++) {
                OnPageChangeListener onPageChangeListener = this.an.get(i4);
                if (onPageChangeListener != null) {
                    onPageChangeListener.a(i2, f2, i3);
                }
            }
        }
        if (this.ap != null) {
            this.ap.a(i2, f2, i3);
        }
    }

    private void b(int i2) {
        if (this.ao != null) {
            this.ao.a(i2);
        }
        if (this.an != null) {
            int size = this.an.size();
            for (int i3 = 0; i3 < size; i3++) {
                OnPageChangeListener onPageChangeListener = this.an.get(i3);
                if (onPageChangeListener != null) {
                    onPageChangeListener.a(i2);
                }
            }
        }
        if (this.ap != null) {
            this.ap.a(i2);
        }
    }

    private void c(int i2) {
        if (this.ao != null) {
            this.ao.b(i2);
        }
        if (this.an != null) {
            int size = this.an.size();
            for (int i3 = 0; i3 < size; i3++) {
                OnPageChangeListener onPageChangeListener = this.an.get(i3);
                if (onPageChangeListener != null) {
                    onPageChangeListener.b(i2);
                }
            }
        }
        if (this.ap != null) {
            this.ap.b(i2);
        }
    }

    private void a(boolean z2) {
        boolean z3 = this.aA == 2;
        if (z3) {
            setScrollingCacheEnabled(false);
            if (!this.w.isFinished()) {
                this.w.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.w.getCurrX();
                int currY = this.w.getCurrY();
                if (!(scrollX == currX && scrollY == currY)) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        a(currX);
                    }
                }
            }
        }
        this.J = false;
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
            ViewCompat.postOnAnimation(this, this.az);
        } else {
            this.az.run();
        }
    }

    private boolean a(float f2, float f3) {
        return (f2 < ((float) this.O) && f3 > 0.0f) || (f2 > ((float) (getWidth() - this.O)) && f3 < 0.0f);
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
                        if (this.L) {
                            return true;
                        }
                        if (this.M) {
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
                        this.M = false;
                        this.x = true;
                        this.w.computeScrollOffset();
                        if (this.aA != 2 || Math.abs(this.w.getFinalX() - this.w.getCurrX()) <= this.ad) {
                            a(false);
                            this.L = false;
                        } else {
                            this.w.abortAnimation();
                            this.J = false;
                            populate();
                            this.L = true;
                            c(true);
                            setScrollState(1);
                        }
                    } else if (action == 2) {
                        int i2 = this.U;
                        if (i2 != -1) {
                            int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent2, i2);
                            float x3 = MotionEventCompat.getX(motionEvent2, findPointerIndex);
                            float f2 = x3 - this.Q;
                            float abs = Math.abs(f2);
                            float y3 = MotionEventCompat.getY(motionEvent2, findPointerIndex);
                            float abs2 = Math.abs(y3 - this.T);
                            if (f2 != 0.0f && !a(this.Q, f2)) {
                                if (canScroll(this, false, (int) f2, (int) x3, (int) y3)) {
                                    this.Q = x3;
                                    this.R = y3;
                                    this.M = true;
                                    return false;
                                }
                            }
                            if (abs > ((float) this.P) && abs * 0.5f > abs2) {
                                this.L = true;
                                c(true);
                                setScrollState(1);
                                this.Q = f2 > 0.0f ? this.S + ((float) this.P) : this.S - ((float) this.P);
                                this.R = y3;
                                setScrollingCacheEnabled(true);
                            } else if (abs2 > ((float) this.P)) {
                                this.M = true;
                            }
                            if (this.L && a(x3)) {
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
                    return this.L;
                }
            }
            c();
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
            boolean r1 = r8.af     // Catch:{ IllegalArgumentException -> 0x015a }
            r2 = 1
            if (r1 == 0) goto L_0x0007
            return r2
        L_0x0007:
            int r1 = r9.getAction()     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r1 != 0) goto L_0x0014
            int r1 = r9.getEdgeFlags()     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r1 == 0) goto L_0x0014
            return r0
        L_0x0014:
            android.support.v4.view.PagerAdapter r1 = r8.r     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r1 == 0) goto L_0x0159
            android.support.v4.view.PagerAdapter r1 = r8.r     // Catch:{ IllegalArgumentException -> 0x015a }
            int r1 = r1.getCount()     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r1 != 0) goto L_0x0022
            goto L_0x0159
        L_0x0022:
            android.view.VelocityTracker r1 = r8.W     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r1 != 0) goto L_0x002c
            android.view.VelocityTracker r1 = android.view.VelocityTracker.obtain()     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.W = r1     // Catch:{ IllegalArgumentException -> 0x015a }
        L_0x002c:
            android.view.VelocityTracker r1 = r8.W     // Catch:{ IllegalArgumentException -> 0x015a }
            r1.addMovement(r9)     // Catch:{ IllegalArgumentException -> 0x015a }
            int r1 = r9.getAction()     // Catch:{ IllegalArgumentException -> 0x015a }
            r1 = r1 & 255(0xff, float:3.57E-43)
            switch(r1) {
                case 0: goto L_0x0132;
                case 1: goto L_0x00e5;
                case 2: goto L_0x006e;
                case 3: goto L_0x005f;
                case 4: goto L_0x003a;
                case 5: goto L_0x004d;
                case 6: goto L_0x003c;
                default: goto L_0x003a;
            }     // Catch:{ IllegalArgumentException -> 0x015a }
        L_0x003a:
            goto L_0x0152
        L_0x003c:
            r8.a((android.view.MotionEvent) r9)     // Catch:{ IllegalArgumentException -> 0x015a }
            int r1 = r8.U     // Catch:{ IllegalArgumentException -> 0x015a }
            int r1 = android.support.v4.view.MotionEventCompat.findPointerIndex(r9, r1)     // Catch:{ IllegalArgumentException -> 0x015a }
            float r9 = android.support.v4.view.MotionEventCompat.getX(r9, r1)     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.Q = r9     // Catch:{ IllegalArgumentException -> 0x015a }
            goto L_0x0152
        L_0x004d:
            int r1 = android.support.v4.view.MotionEventCompat.getActionIndex(r9)     // Catch:{ IllegalArgumentException -> 0x015a }
            float r3 = android.support.v4.view.MotionEventCompat.getX(r9, r1)     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.Q = r3     // Catch:{ IllegalArgumentException -> 0x015a }
            int r9 = android.support.v4.view.MotionEventCompat.getPointerId(r9, r1)     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.U = r9     // Catch:{ IllegalArgumentException -> 0x015a }
            goto L_0x0152
        L_0x005f:
            boolean r9 = r8.L     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r9 == 0) goto L_0x0152
            int r9 = r8.s     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.a((int) r9, (boolean) r2, (int) r0, (boolean) r0)     // Catch:{ IllegalArgumentException -> 0x015a }
            boolean r9 = r8.c()     // Catch:{ IllegalArgumentException -> 0x015a }
            goto L_0x0153
        L_0x006e:
            boolean r1 = r8.L     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r1 != 0) goto L_0x00d1
            int r1 = r8.U     // Catch:{ IllegalArgumentException -> 0x015a }
            int r1 = android.support.v4.view.MotionEventCompat.findPointerIndex(r9, r1)     // Catch:{ IllegalArgumentException -> 0x015a }
            r3 = -1
            if (r1 != r3) goto L_0x0081
            boolean r9 = r8.c()     // Catch:{ IllegalArgumentException -> 0x015a }
            goto L_0x0153
        L_0x0081:
            float r3 = android.support.v4.view.MotionEventCompat.getX(r9, r1)     // Catch:{ IllegalArgumentException -> 0x015a }
            float r4 = r8.Q     // Catch:{ IllegalArgumentException -> 0x015a }
            float r4 = r3 - r4
            float r4 = java.lang.Math.abs(r4)     // Catch:{ IllegalArgumentException -> 0x015a }
            float r1 = android.support.v4.view.MotionEventCompat.getY(r9, r1)     // Catch:{ IllegalArgumentException -> 0x015a }
            float r5 = r8.R     // Catch:{ IllegalArgumentException -> 0x015a }
            float r5 = r1 - r5
            float r5 = java.lang.Math.abs(r5)     // Catch:{ IllegalArgumentException -> 0x015a }
            int r6 = r8.P     // Catch:{ IllegalArgumentException -> 0x015a }
            float r6 = (float) r6     // Catch:{ IllegalArgumentException -> 0x015a }
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x00d1
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L_0x00d1
            r8.L = r2     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.c((boolean) r2)     // Catch:{ IllegalArgumentException -> 0x015a }
            float r4 = r8.S     // Catch:{ IllegalArgumentException -> 0x015a }
            float r3 = r3 - r4
            r4 = 0
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 <= 0) goto L_0x00b8
            float r3 = r8.S     // Catch:{ IllegalArgumentException -> 0x015a }
            int r4 = r8.P     // Catch:{ IllegalArgumentException -> 0x015a }
            float r4 = (float) r4     // Catch:{ IllegalArgumentException -> 0x015a }
            float r3 = r3 + r4
            goto L_0x00be
        L_0x00b8:
            float r3 = r8.S     // Catch:{ IllegalArgumentException -> 0x015a }
            int r4 = r8.P     // Catch:{ IllegalArgumentException -> 0x015a }
            float r4 = (float) r4     // Catch:{ IllegalArgumentException -> 0x015a }
            float r3 = r3 - r4
        L_0x00be:
            r8.Q = r3     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.R = r1     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.setScrollState(r2)     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.setScrollingCacheEnabled(r2)     // Catch:{ IllegalArgumentException -> 0x015a }
            android.view.ViewParent r1 = r8.getParent()     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r1 == 0) goto L_0x00d1
            r1.requestDisallowInterceptTouchEvent(r2)     // Catch:{ IllegalArgumentException -> 0x015a }
        L_0x00d1:
            boolean r1 = r8.L     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r1 == 0) goto L_0x0152
            int r1 = r8.U     // Catch:{ IllegalArgumentException -> 0x015a }
            int r1 = android.support.v4.view.MotionEventCompat.findPointerIndex(r9, r1)     // Catch:{ IllegalArgumentException -> 0x015a }
            float r9 = android.support.v4.view.MotionEventCompat.getX(r9, r1)     // Catch:{ IllegalArgumentException -> 0x015a }
            boolean r9 = r8.a((float) r9)     // Catch:{ IllegalArgumentException -> 0x015a }
            r9 = r9 | r0
            goto L_0x0153
        L_0x00e5:
            boolean r1 = r8.L     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r1 == 0) goto L_0x0152
            android.view.VelocityTracker r1 = r8.W     // Catch:{ IllegalArgumentException -> 0x015a }
            r3 = 1000(0x3e8, float:1.401E-42)
            int r4 = r8.ab     // Catch:{ IllegalArgumentException -> 0x015a }
            float r4 = (float) r4     // Catch:{ IllegalArgumentException -> 0x015a }
            r1.computeCurrentVelocity(r3, r4)     // Catch:{ IllegalArgumentException -> 0x015a }
            int r3 = r8.U     // Catch:{ IllegalArgumentException -> 0x015a }
            float r1 = android.support.v4.view.VelocityTrackerCompat.getXVelocity(r1, r3)     // Catch:{ IllegalArgumentException -> 0x015a }
            int r1 = (int) r1     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.J = r2     // Catch:{ IllegalArgumentException -> 0x015a }
            int r3 = r8.getClientWidth()     // Catch:{ IllegalArgumentException -> 0x015a }
            int r4 = r8.getScrollX()     // Catch:{ IllegalArgumentException -> 0x015a }
            com.mi.global.shop.widget.ViewPager$ItemInfo r5 = r8.d()     // Catch:{ IllegalArgumentException -> 0x015a }
            int r6 = r8.z     // Catch:{ IllegalArgumentException -> 0x015a }
            float r6 = (float) r6     // Catch:{ IllegalArgumentException -> 0x015a }
            float r3 = (float) r3     // Catch:{ IllegalArgumentException -> 0x015a }
            float r6 = r6 / r3
            int r7 = r5.b     // Catch:{ IllegalArgumentException -> 0x015a }
            float r4 = (float) r4     // Catch:{ IllegalArgumentException -> 0x015a }
            float r4 = r4 / r3
            float r3 = r5.e     // Catch:{ IllegalArgumentException -> 0x015a }
            float r4 = r4 - r3
            float r3 = r5.d     // Catch:{ IllegalArgumentException -> 0x015a }
            float r3 = r3 + r6
            float r4 = r4 / r3
            int r3 = r8.U     // Catch:{ IllegalArgumentException -> 0x015a }
            int r3 = android.support.v4.view.MotionEventCompat.findPointerIndex(r9, r3)     // Catch:{ IllegalArgumentException -> 0x015a }
            float r9 = android.support.v4.view.MotionEventCompat.getX(r9, r3)     // Catch:{ IllegalArgumentException -> 0x015a }
            float r3 = r8.S     // Catch:{ IllegalArgumentException -> 0x015a }
            float r9 = r9 - r3
            int r9 = (int) r9     // Catch:{ IllegalArgumentException -> 0x015a }
            int r9 = r8.a((int) r7, (float) r4, (int) r1, (int) r9)     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.setCurrentItemInternal(r9, r2, r2, r1)     // Catch:{ IllegalArgumentException -> 0x015a }
            boolean r9 = r8.c()     // Catch:{ IllegalArgumentException -> 0x015a }
            goto L_0x0153
        L_0x0132:
            android.widget.Scroller r1 = r8.w     // Catch:{ IllegalArgumentException -> 0x015a }
            r1.abortAnimation()     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.J = r0     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.populate()     // Catch:{ IllegalArgumentException -> 0x015a }
            float r1 = r9.getX()     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.S = r1     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.Q = r1     // Catch:{ IllegalArgumentException -> 0x015a }
            float r1 = r9.getY()     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.T = r1     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.R = r1     // Catch:{ IllegalArgumentException -> 0x015a }
            int r9 = android.support.v4.view.MotionEventCompat.getPointerId(r9, r0)     // Catch:{ IllegalArgumentException -> 0x015a }
            r8.U = r9     // Catch:{ IllegalArgumentException -> 0x015a }
        L_0x0152:
            r9 = 0
        L_0x0153:
            if (r9 == 0) goto L_0x0158
            android.support.v4.view.ViewCompat.postInvalidateOnAnimation(r8)     // Catch:{ IllegalArgumentException -> 0x015a }
        L_0x0158:
            return r2
        L_0x0159:
            return r0
        L_0x015a:
            r9 = move-exception
            r9.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.widget.ViewPager.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private boolean c() {
        this.U = -1;
        e();
        return this.ah.onRelease() | this.ai.onRelease();
    }

    private void c(boolean z2) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z2);
        }
    }

    private boolean a(float f2) {
        boolean z2;
        float f3 = this.Q - f2;
        this.Q = f2;
        float scrollX = ((float) getScrollX()) + f3;
        float clientWidth = (float) getClientWidth();
        float f4 = this.D * clientWidth;
        float f5 = this.E * clientWidth;
        boolean z3 = false;
        ItemInfo itemInfo = this.o.get(0);
        boolean z4 = true;
        ItemInfo itemInfo2 = this.o.get(this.o.size() - 1);
        if (itemInfo.b != 0) {
            f4 = itemInfo.e * clientWidth;
            z2 = false;
        } else {
            z2 = true;
        }
        if (itemInfo2.b != this.r.getCount() - 1) {
            f5 = itemInfo2.e * clientWidth;
            z4 = false;
        }
        if (scrollX < f4) {
            if (z2) {
                z3 = this.ah.onPull(Math.abs(f4 - scrollX) / clientWidth);
            }
            scrollX = f4;
        } else if (scrollX > f5) {
            if (z4) {
                z3 = this.ai.onPull(Math.abs(scrollX - f5) / clientWidth);
            }
            scrollX = f5;
        }
        int i2 = (int) scrollX;
        this.Q += scrollX - ((float) i2);
        scrollTo(i2, getScrollY());
        a(i2);
        return z3;
    }

    private ItemInfo d() {
        int i2;
        int clientWidth = getClientWidth();
        float scrollX = clientWidth > 0 ? ((float) getScrollX()) / ((float) clientWidth) : 0.0f;
        float f2 = clientWidth > 0 ? ((float) this.z) / ((float) clientWidth) : 0.0f;
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
            if (!z2 && scrollX < f3) {
                return itemInfo;
            }
            if (scrollX < f5 || i3 == this.o.size() - 1) {
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
            i2 = (int) (((float) i2) + f2 + (i2 >= this.s ? e : 0.1f));
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
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((float) ((-height) + getPaddingTop()), this.D * ((float) width));
                this.ah.setSize(height, width);
                z2 = false | this.ah.draw(canvas);
                canvas.restoreToCount(save);
            }
            if (!this.ai.isFinished()) {
                int save2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate((float) (-getPaddingTop()), (-(this.E + 1.0f)) * ((float) width2));
                this.ai.setSize(height2, width2);
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
        if (this.z > 0 && this.A != null && this.o.size() > 0 && this.r != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            float f5 = (float) width;
            float f6 = ((float) this.z) / f5;
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
                if (((float) this.z) + f3 > ((float) scrollX)) {
                    f4 = f6;
                    this.A.setBounds(Math.round(f3), this.B, Math.round(((float) this.z) + f3), this.C);
                    this.A.draw(canvas);
                } else {
                    Canvas canvas2 = canvas;
                    f4 = f6;
                }
                if (f3 <= ((float) (scrollX + width))) {
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
        if (this.L) {
            return false;
        }
        this.af = true;
        setScrollState(1);
        this.Q = 0.0f;
        this.S = 0.0f;
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
            if (this.r != null) {
                VelocityTracker velocityTracker = this.W;
                velocityTracker.computeCurrentVelocity(1000, (float) this.ab);
                int xVelocity = (int) VelocityTrackerCompat.getXVelocity(velocityTracker, this.U);
                this.J = true;
                int clientWidth = getClientWidth();
                int scrollX = getScrollX();
                ItemInfo d2 = d();
                setCurrentItemInternal(a(d2.b, ((((float) scrollX) / ((float) clientWidth)) - d2.e) / d2.d, xVelocity, (int) (this.Q - this.S)), true, true, xVelocity);
            }
            e();
            this.af = false;
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public void fakeDragBy(float f2) {
        if (!this.af) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        } else if (this.r != null) {
            this.Q += f2;
            float scrollX = ((float) getScrollX()) - f2;
            float clientWidth = (float) getClientWidth();
            float f3 = this.D * clientWidth;
            float f4 = this.E * clientWidth;
            ItemInfo itemInfo = this.o.get(0);
            ItemInfo itemInfo2 = this.o.get(this.o.size() - 1);
            if (itemInfo.b != 0) {
                f3 = itemInfo.e * clientWidth;
            }
            if (itemInfo2.b != this.r.getCount() - 1) {
                f4 = itemInfo2.e * clientWidth;
            }
            if (scrollX < f3) {
                scrollX = f3;
            } else if (scrollX > f4) {
                scrollX = f4;
            }
            int i2 = (int) scrollX;
            this.Q += scrollX - ((float) i2);
            scrollTo(i2, getScrollY());
            a(i2);
            MotionEvent obtain = MotionEvent.obtain(this.ag, SystemClock.uptimeMillis(), 2, this.Q, 0.0f, 0);
            this.W.addMovement(obtain);
            obtain.recycle();
        }
    }

    public boolean isFakeDragging() {
        return this.af;
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.U) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.Q = MotionEventCompat.getX(motionEvent, i2);
            this.U = MotionEventCompat.getPointerId(motionEvent, i2);
            if (this.W != null) {
                this.W.clear();
            }
        }
    }

    private void e() {
        this.L = false;
        this.M = false;
        if (this.W != null) {
            this.W.recycle();
            this.W = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z2) {
        if (this.I != z2) {
            this.I = z2;
        }
    }

    public boolean canScrollHorizontally(int i2) {
        if (this.r == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        if (i2 < 0) {
            if (scrollX > ((int) (((float) clientWidth) * this.D))) {
                return true;
            }
            return false;
        } else if (i2 <= 0 || scrollX >= ((int) (((float) clientWidth) * this.E))) {
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
                int i6 = i3 + scrollX;
                if (i6 >= childAt.getLeft() && i6 < childAt.getRight() && (i5 = i4 + scrollY) >= childAt.getTop() && i5 < childAt.getBottom()) {
                    if (canScroll(childAt, true, i2, i6 - childAt.getLeft(), i5 - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (!z2 || !ViewCompat.canScrollHorizontally(view, -i2)) {
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
                    Log.e(f7178a, "arrowScroll tried to find focus based on non-child current focused view " + sb.toString());
                }
            }
            view = findFocus;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, view, i2);
        if (findNextFocus != null && findNextFocus != view) {
            if (i2 == 17) {
                int i3 = a(this.q, findNextFocus).left;
                int i4 = a(this.q, view).left;
                if (view == null || i3 < i4) {
                    requestFocus = findNextFocus.requestFocus();
                } else {
                    requestFocus = pageLeft();
                }
            } else if (i2 == 66) {
                int i5 = a(this.q, findNextFocus).left;
                int i6 = a(this.q, view).left;
                if (view == null || i5 > i6) {
                    requestFocus = findNextFocus.requestFocus();
                } else {
                    requestFocus = pageRight();
                }
            }
            z3 = requestFocus;
        } else if (i2 == 17 || i2 == 1) {
            z3 = pageLeft();
        } else if (i2 == 66 || i2 == 2) {
            z3 = pageRight();
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
    public boolean pageLeft() {
        if (this.s <= 0) {
            return false;
        }
        setCurrentItem(this.s - 1, true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean pageRight() {
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
            accessibilityEvent.setClassName(ItemInfo.class.getName());
            AccessibilityRecordCompat asRecord = AccessibilityEventCompat.asRecord(accessibilityEvent);
            asRecord.setScrollable(a());
            if (accessibilityEvent.getEventType() == 4096 && ViewPager.this.r != null) {
                asRecord.setItemCount(ViewPager.this.r.getCount());
                asRecord.setFromIndex(ViewPager.this.s);
                asRecord.setToIndex(ViewPager.this.s);
            }
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setClassName(ItemInfo.class.getName());
            accessibilityNodeInfoCompat.setScrollable(a());
            if (ViewPager.this.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.addAction(8192);
            }
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (super.performAccessibilityAction(view, i, bundle)) {
                return true;
            }
            if (i != 4096) {
                if (i != 8192 || !ViewPager.this.canScrollHorizontally(-1)) {
                    return false;
                }
                ViewPager.this.setCurrentItem(ViewPager.this.s - 1);
                return true;
            } else if (!ViewPager.this.canScrollHorizontally(1)) {
                return false;
            } else {
                ViewPager.this.setCurrentItem(ViewPager.this.s + 1);
                return true;
            }
        }

        private boolean a() {
            return ViewPager.this.r != null && ViewPager.this.r.getCount() > 1;
        }
    }

    private class PagerObserver extends DataSetObserver {
        private PagerObserver() {
        }

        public void onChanged() {
            ViewPager.this.dataSetChanged();
        }

        public void onInvalidated() {
            ViewPager.this.dataSetChanged();
        }
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {

        /* renamed from: a  reason: collision with root package name */
        public boolean f7182a;
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
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.k);
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
            if (layoutParams.f7182a != layoutParams2.f7182a) {
                return layoutParams.f7182a ? 1 : -1;
            }
            return layoutParams.e - layoutParams2.e;
        }
    }
}
