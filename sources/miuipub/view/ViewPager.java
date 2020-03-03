package miuipub.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
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
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.miuipub.internal.widget.ActionBarOverlayLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ViewPager extends ViewGroup {
    static final float CURRENT_PAGE_DETERMIN_FACTOR = 0.05f;
    private static final int S = -1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;

    /* renamed from: a  reason: collision with root package name */
    private static final String f3059a = "ViewPager";
    private static final int ab = 2;
    private static final boolean b = true;
    private static final boolean c = false;
    private static final int d = 3;
    private static final int e = 800;
    private static final int f = 25;
    private static final int g = 250;
    private static final int h = 16;
    /* access modifiers changed from: private */
    public static final int[] i = {16842931};
    private static final Comparator<ItemInfo> j = new Comparator<ItemInfo>() {
        /* renamed from: a */
        public int compare(ItemInfo itemInfo, ItemInfo itemInfo2) {
            return itemInfo.b - itemInfo2.b;
        }
    };
    private static final Interpolator k = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };
    private int A;
    private int B;
    private boolean C;
    private boolean D;
    private boolean E;
    private int F = 3;
    private boolean G;
    private boolean H;
    private boolean I;
    private int J;
    private int K;
    private int L;
    private float M;
    private float N;
    private boolean O;
    private float P;
    private float Q;
    private int R = -1;
    private VelocityTracker T;
    private int U;
    private int V;
    private int W;
    private int aa;
    private boolean ac;
    private long ad;
    private EdgeEffectCompat ae;
    private EdgeEffectCompat af;
    private boolean ag = true;
    private boolean ah = false;
    private boolean ai;
    private int aj;
    private OnPageChangeListener ak;
    private OnPageChangeListener al;
    private OnAdapterChangeListener am;
    private int an = 0;
    private final ArrayList<ItemInfo> l = new ArrayList<>();
    private final ItemInfo m = new ItemInfo();
    boolean mDragEnabled = true;
    float mLastPageOffset = 0.0f;
    private final Rect n = new Rect();
    private PagerAdapter o;
    private int p;
    private int q = -1;
    private Parcelable r = null;
    private Scroller s;
    private PagerObserver t;
    private int u;
    private Drawable v;
    private int w;
    private int x;
    private float y = -3.4028235E38f;
    private float z = Float.MAX_VALUE;

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
        Object f3060a;
        int b;
        boolean c;
        float d;
        float e;
        boolean f;

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
        this.s = new Scroller(context, k);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.L = viewConfiguration.getScaledPagingTouchSlop();
        this.U = viewConfiguration.getScaledMinimumFlingVelocity();
        this.V = viewConfiguration.getScaledMaximumFlingVelocity();
        this.ae = new EdgeEffectCompat(context);
        this.af = new EdgeEffectCompat(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.W = (int) (25.0f * f2);
        this.aa = (int) (2.0f * f2);
        this.J = (int) (f2 * 16.0f);
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
    }

    private void setScrollState(int i2) {
        if (this.an != i2) {
            this.an = i2;
            if (this.ak != null) {
                this.ak.b(i2);
            }
            if (this.al != null) {
                this.al.b(i2);
            }
        }
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        if (this.o != null) {
            this.o.b((DataSetObserver) this.t);
            this.o.a((ViewGroup) this);
            Iterator<ItemInfo> it = this.l.iterator();
            while (it.hasNext()) {
                ItemInfo next = it.next();
                this.o.a((ViewGroup) this, next.b, next.f3060a);
            }
            this.o.b((ViewGroup) this);
            this.l.clear();
            a();
            this.p = 0;
            scrollTo(0, 0);
        }
        PagerAdapter pagerAdapter2 = this.o;
        this.o = pagerAdapter;
        if (this.o != null) {
            if (this.t == null) {
                this.t = new PagerObserver();
            }
            this.o.a((DataSetObserver) this.t);
            this.E = false;
            this.ag = true;
            if (this.q >= 0) {
                this.o.a(this.r, (ClassLoader) null);
                setCurrentItemInternal(this.q, false, true);
                this.q = -1;
                this.r = null;
            } else {
                populate();
            }
        }
        if (this.am != null && pagerAdapter2 != pagerAdapter) {
            this.am.a(pagerAdapter2, pagerAdapter);
        }
    }

    private void a() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((LayoutParams) getChildAt(i2).getLayoutParams()).f3061a) {
                removeViewAt(i2);
                i2--;
            }
            i2++;
        }
    }

    public PagerAdapter getAdapter() {
        return this.o;
    }

    /* access modifiers changed from: package-private */
    public void setOnAdapterChangeListener(OnAdapterChangeListener onAdapterChangeListener) {
        this.am = onAdapterChangeListener;
    }

    public void setCurrentItem(int i2) {
        this.E = false;
        setCurrentItemInternal(i2, !this.ag, false);
    }

    public void setCurrentItem(int i2, boolean z2) {
        this.E = false;
        setCurrentItemInternal(i2, z2, false);
    }

    public int getCurrentItem() {
        return this.p;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentItemInternal(int i2, boolean z2, boolean z3) {
        setCurrentItemInternal(i2, z2, z3, 0);
    }

    /* access modifiers changed from: package-private */
    public void setCurrentItemInternal(int i2, boolean z2, boolean z3, int i3) {
        if (this.o == null || this.o.a() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (z3 || this.p != i2 || this.l.size() == 0) {
            boolean z4 = true;
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 >= this.o.a()) {
                i2 = this.o.a() - 1;
            }
            int i4 = this.F;
            if (i2 > this.p + i4 || i2 < this.p - i4) {
                Iterator<ItemInfo> it = this.l.iterator();
                while (it.hasNext()) {
                    it.next().c = true;
                }
            }
            if (this.p == i2) {
                z4 = false;
            }
            populate(i2);
            ItemInfo infoForPosition = infoForPosition(i2);
            int width = infoForPosition != null ? (int) (((float) getWidth()) * Math.max(this.y, Math.min(infoForPosition.e, this.z))) : 0;
            if (z2) {
                smoothScrollTo(width, 0, i3);
                if (z4 && this.ak != null) {
                    this.ak.a(i2);
                }
                if (z4 && this.al != null) {
                    this.al.a(i2);
                    return;
                }
                return;
            }
            if (z4 && this.ak != null) {
                this.ak.a(i2);
            }
            if (z4 && this.al != null) {
                this.al.a(i2);
            }
            b();
            scrollTo(width, 0);
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.ak = onPageChangeListener;
    }

    public OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener onPageChangeListener) {
        OnPageChangeListener onPageChangeListener2 = this.al;
        this.al = onPageChangeListener;
        return onPageChangeListener2;
    }

    public int getOffscreenPageLimit() {
        return this.F;
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 3) {
            Log.w(f3059a, "Requested offscreen page limit " + i2 + " too small; defaulting to " + 3);
            i2 = 3;
        }
        if (i2 != this.F) {
            this.F = i2;
            populate();
        }
    }

    public void setPageMargin(int i2) {
        int i3 = this.u;
        this.u = i2;
        int width = getWidth();
        a(width, width, i2, i3);
        requestLayout();
    }

    public int getPageMargin() {
        return this.u;
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.v = drawable;
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
        return super.verifyDrawable(drawable) || drawable == this.v;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.v;
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
            b();
            populate();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int width = getWidth();
        int i8 = width / 2;
        float f2 = (float) width;
        float f3 = (float) i8;
        float distanceInfluenceForSnapDuration = f3 + (distanceInfluenceForSnapDuration(Math.min(1.0f, (((float) Math.abs(i6)) * 1.0f) / f2)) * f3);
        int abs = Math.abs(i4);
        if (abs > 0) {
            i5 = Math.round(Math.abs(distanceInfluenceForSnapDuration / ((float) abs)) * 1000.0f) * 4;
        } else {
            i5 = (int) (((((float) Math.abs(i6)) / ((f2 * this.o.e(this.p)) + ((float) this.u))) + 1.0f) * 250.0f);
        }
        this.s.startScroll(scrollX, scrollY, i6, i7, Math.min(i5, 800));
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /* access modifiers changed from: package-private */
    public ItemInfo addNewItem(int i2, int i3) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.b = i2;
        itemInfo.f3060a = this.o.a((ViewGroup) this, i2);
        itemInfo.d = this.o.e(i2);
        itemInfo.f = this.o.a(i2);
        if (i3 < 0 || i3 >= this.l.size()) {
            this.l.add(itemInfo);
        } else {
            this.l.add(i3, itemInfo);
        }
        return itemInfo;
    }

    /* access modifiers changed from: package-private */
    public void dataSetChanged() {
        boolean z2 = false;
        boolean z3 = this.l.size() < (this.F * 2) + 1 && this.l.size() < this.o.a();
        int i2 = this.p;
        int i3 = 0;
        boolean z4 = false;
        boolean z5 = false;
        while (i3 < this.l.size()) {
            ItemInfo itemInfo = this.l.get(i3);
            int a2 = this.o.a(itemInfo.f3060a);
            if (a2 != -1) {
                if (a2 == -2) {
                    this.l.remove(i3);
                    i3--;
                    if (!z4) {
                        this.o.a((ViewGroup) this);
                        z4 = true;
                    }
                    this.o.a((ViewGroup) this, itemInfo.b, itemInfo.f3060a);
                    if (this.p == itemInfo.b) {
                        i2 = Math.max(0, Math.min(this.p, this.o.a() - 1));
                    }
                    z3 = true;
                } else {
                    if (itemInfo.b != a2) {
                        if (itemInfo.b == this.p) {
                            i2 = a2;
                        }
                        itemInfo.b = a2;
                        z3 = true;
                    }
                    if (itemInfo.f != this.o.a(itemInfo.b)) {
                        itemInfo.f = !itemInfo.f;
                    }
                }
                i3++;
            } else if (itemInfo.f != this.o.a(itemInfo.b)) {
                itemInfo.f = !itemInfo.f;
            } else {
                i3++;
            }
            z5 = true;
            i3++;
        }
        if (z4) {
            this.o.b((ViewGroup) this);
        }
        Collections.sort(this.l, j);
        if (z3) {
            int childCount = getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i4).getLayoutParams();
                if (!layoutParams.f3061a) {
                    layoutParams.c = 0.0f;
                }
            }
            setCurrentItemInternal(i2, false, true);
            requestLayout();
        } else {
            z2 = z5;
        }
        if (z2) {
            requestLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void populate() {
        populate(this.p);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0063, code lost:
        if (r8.b == r0.p) goto L_0x006a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void populate(int r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            int r2 = r0.p
            if (r2 == r1) goto L_0x0011
            int r2 = r0.p
            miuipub.view.ViewPager$ItemInfo r2 = r0.infoForPosition(r2)
            r0.p = r1
            goto L_0x0012
        L_0x0011:
            r2 = 0
        L_0x0012:
            miuipub.view.PagerAdapter r1 = r0.o
            if (r1 != 0) goto L_0x0017
            return
        L_0x0017:
            boolean r1 = r0.E
            if (r1 == 0) goto L_0x0023
            java.lang.String r1 = "ViewPager"
            java.lang.String r2 = "populate is pending, skipping for now..."
            android.util.Log.i(r1, r2)
            return
        L_0x0023:
            android.os.IBinder r1 = r17.getWindowToken()
            if (r1 != 0) goto L_0x002a
            return
        L_0x002a:
            miuipub.view.PagerAdapter r1 = r0.o
            r1.a((android.view.ViewGroup) r0)
            int r1 = r0.F
            int r4 = r0.p
            int r4 = r4 - r1
            r5 = 0
            int r4 = java.lang.Math.max(r5, r4)
            miuipub.view.PagerAdapter r6 = r0.o
            int r6 = r6.a()
            int r7 = r6 + -1
            int r8 = r0.p
            int r8 = r8 + r1
            int r1 = java.lang.Math.min(r7, r8)
            r7 = 0
        L_0x0049:
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r8 = r0.l
            int r8 = r8.size()
            if (r7 >= r8) goto L_0x0069
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r8 = r0.l
            java.lang.Object r8 = r8.get(r7)
            miuipub.view.ViewPager$ItemInfo r8 = (miuipub.view.ViewPager.ItemInfo) r8
            int r9 = r8.b
            int r10 = r0.p
            if (r9 < r10) goto L_0x0066
            int r9 = r8.b
            int r10 = r0.p
            if (r9 != r10) goto L_0x0069
            goto L_0x006a
        L_0x0066:
            int r7 = r7 + 1
            goto L_0x0049
        L_0x0069:
            r8 = 0
        L_0x006a:
            if (r8 != 0) goto L_0x0074
            if (r6 <= 0) goto L_0x0074
            int r8 = r0.p
            miuipub.view.ViewPager$ItemInfo r8 = r0.addNewItem(r8, r7)
        L_0x0074:
            r9 = 0
            if (r8 == 0) goto L_0x0179
            int r10 = r7 + -1
            if (r10 < 0) goto L_0x0084
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r11 = r0.l
            java.lang.Object r11 = r11.get(r10)
            miuipub.view.ViewPager$ItemInfo r11 = (miuipub.view.ViewPager.ItemInfo) r11
            goto L_0x0085
        L_0x0084:
            r11 = 0
        L_0x0085:
            float r12 = r8.d
            r13 = 1073741824(0x40000000, float:2.0)
            float r12 = r13 - r12
            int r14 = r0.p
            int r14 = r14 + -1
            r15 = r7
            r7 = 0
        L_0x0091:
            if (r14 < 0) goto L_0x00f0
            int r16 = (r7 > r12 ? 1 : (r7 == r12 ? 0 : -1))
            if (r16 < 0) goto L_0x00bf
            if (r14 >= r4) goto L_0x00bf
            if (r11 != 0) goto L_0x009c
            goto L_0x00f0
        L_0x009c:
            int r3 = r11.b
            if (r14 != r3) goto L_0x00ed
            boolean r3 = r11.c
            if (r3 != 0) goto L_0x00ed
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r3 = r0.l
            r3.remove(r10)
            miuipub.view.PagerAdapter r3 = r0.o
            java.lang.Object r11 = r11.f3060a
            r3.a((android.view.ViewGroup) r0, (int) r14, (java.lang.Object) r11)
            int r10 = r10 + -1
            int r15 = r15 + -1
            if (r10 < 0) goto L_0x00eb
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r3 = r0.l
            java.lang.Object r3 = r3.get(r10)
            miuipub.view.ViewPager$ItemInfo r3 = (miuipub.view.ViewPager.ItemInfo) r3
            goto L_0x00ec
        L_0x00bf:
            if (r11 == 0) goto L_0x00d5
            int r3 = r11.b
            if (r14 != r3) goto L_0x00d5
            float r3 = r11.d
            float r7 = r7 + r3
            int r10 = r10 + -1
            if (r10 < 0) goto L_0x00eb
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r3 = r0.l
            java.lang.Object r3 = r3.get(r10)
            miuipub.view.ViewPager$ItemInfo r3 = (miuipub.view.ViewPager.ItemInfo) r3
            goto L_0x00ec
        L_0x00d5:
            int r3 = r10 + 1
            miuipub.view.ViewPager$ItemInfo r3 = r0.addNewItem(r14, r3)
            float r3 = r3.d
            float r7 = r7 + r3
            int r15 = r15 + 1
            if (r10 < 0) goto L_0x00eb
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r3 = r0.l
            java.lang.Object r3 = r3.get(r10)
            miuipub.view.ViewPager$ItemInfo r3 = (miuipub.view.ViewPager.ItemInfo) r3
            goto L_0x00ec
        L_0x00eb:
            r3 = 0
        L_0x00ec:
            r11 = r3
        L_0x00ed:
            int r14 = r14 + -1
            goto L_0x0091
        L_0x00f0:
            float r3 = r8.d
            int r4 = r15 + 1
            int r7 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r7 >= 0) goto L_0x0176
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r7 = r0.l
            int r7 = r7.size()
            if (r4 >= r7) goto L_0x0109
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r7 = r0.l
            java.lang.Object r7 = r7.get(r4)
            miuipub.view.ViewPager$ItemInfo r7 = (miuipub.view.ViewPager.ItemInfo) r7
            goto L_0x010a
        L_0x0109:
            r7 = 0
        L_0x010a:
            int r10 = r0.p
        L_0x010c:
            int r10 = r10 + 1
            if (r10 >= r6) goto L_0x0176
            int r11 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r11 < 0) goto L_0x0140
            if (r10 <= r1) goto L_0x0140
            if (r7 != 0) goto L_0x0119
            goto L_0x0176
        L_0x0119:
            int r11 = r7.b
            if (r10 != r11) goto L_0x0175
            boolean r11 = r7.c
            if (r11 != 0) goto L_0x0175
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r11 = r0.l
            r11.remove(r4)
            miuipub.view.PagerAdapter r11 = r0.o
            java.lang.Object r7 = r7.f3060a
            r11.a((android.view.ViewGroup) r0, (int) r10, (java.lang.Object) r7)
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r7 = r0.l
            int r7 = r7.size()
            if (r4 >= r7) goto L_0x013e
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r7 = r0.l
            java.lang.Object r7 = r7.get(r4)
            miuipub.view.ViewPager$ItemInfo r7 = (miuipub.view.ViewPager.ItemInfo) r7
            goto L_0x0175
        L_0x013e:
            r7 = 0
            goto L_0x0175
        L_0x0140:
            if (r7 == 0) goto L_0x015c
            int r11 = r7.b
            if (r10 != r11) goto L_0x015c
            float r7 = r7.d
            float r3 = r3 + r7
            int r4 = r4 + 1
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r7 = r0.l
            int r7 = r7.size()
            if (r4 >= r7) goto L_0x013e
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r7 = r0.l
            java.lang.Object r7 = r7.get(r4)
            miuipub.view.ViewPager$ItemInfo r7 = (miuipub.view.ViewPager.ItemInfo) r7
            goto L_0x0175
        L_0x015c:
            miuipub.view.ViewPager$ItemInfo r7 = r0.addNewItem(r10, r4)
            int r4 = r4 + 1
            float r7 = r7.d
            float r3 = r3 + r7
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r7 = r0.l
            int r7 = r7.size()
            if (r4 >= r7) goto L_0x013e
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r7 = r0.l
            java.lang.Object r7 = r7.get(r4)
            miuipub.view.ViewPager$ItemInfo r7 = (miuipub.view.ViewPager.ItemInfo) r7
        L_0x0175:
            goto L_0x010c
        L_0x0176:
            r0.a(r8, r15, r2)
        L_0x0179:
            java.lang.String r1 = "ViewPager"
            java.lang.String r2 = "Current page list:"
            android.util.Log.i(r1, r2)
            r1 = 0
        L_0x0181:
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r2 = r0.l
            int r2 = r2.size()
            if (r1 >= r2) goto L_0x01b4
            java.lang.String r2 = "ViewPager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "#"
            r3.append(r4)
            r3.append(r1)
            java.lang.String r4 = ": page "
            r3.append(r4)
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r4 = r0.l
            java.lang.Object r4 = r4.get(r1)
            miuipub.view.ViewPager$ItemInfo r4 = (miuipub.view.ViewPager.ItemInfo) r4
            int r4 = r4.b
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.i(r2, r3)
            int r1 = r1 + 1
            goto L_0x0181
        L_0x01b4:
            miuipub.view.PagerAdapter r1 = r0.o
            int r2 = r0.p
            if (r8 == 0) goto L_0x01bd
            java.lang.Object r3 = r8.f3060a
            goto L_0x01be
        L_0x01bd:
            r3 = 0
        L_0x01be:
            r1.b((android.view.ViewGroup) r0, (int) r2, (java.lang.Object) r3)
            miuipub.view.PagerAdapter r1 = r0.o
            r1.b((android.view.ViewGroup) r0)
            int r1 = r17.getChildCount()
            r2 = 0
        L_0x01cb:
            if (r2 >= r1) goto L_0x01ee
            android.view.View r3 = r0.getChildAt(r2)
            android.view.ViewGroup$LayoutParams r4 = r3.getLayoutParams()
            miuipub.view.ViewPager$LayoutParams r4 = (miuipub.view.ViewPager.LayoutParams) r4
            boolean r6 = r4.f3061a
            if (r6 != 0) goto L_0x01eb
            float r6 = r4.c
            int r6 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r6 != 0) goto L_0x01eb
            miuipub.view.ViewPager$ItemInfo r3 = r0.infoForChild(r3)
            if (r3 == 0) goto L_0x01eb
            float r3 = r3.d
            r4.c = r3
        L_0x01eb:
            int r2 = r2 + 1
            goto L_0x01cb
        L_0x01ee:
            boolean r1 = r17.hasFocus()
            if (r1 == 0) goto L_0x0229
            android.view.View r1 = r17.findFocus()
            if (r1 == 0) goto L_0x01ff
            miuipub.view.ViewPager$ItemInfo r3 = r0.infoForAnyChild(r1)
            goto L_0x0200
        L_0x01ff:
            r3 = 0
        L_0x0200:
            if (r3 == 0) goto L_0x0208
            int r1 = r3.b
            int r2 = r0.p
            if (r1 == r2) goto L_0x0229
        L_0x0208:
            int r1 = r17.getChildCount()
            if (r5 >= r1) goto L_0x0229
            android.view.View r1 = r0.getChildAt(r5)
            miuipub.view.ViewPager$ItemInfo r2 = r0.infoForChild(r1)
            if (r2 == 0) goto L_0x0226
            int r2 = r2.b
            int r3 = r0.p
            if (r2 != r3) goto L_0x0226
            r2 = 2
            boolean r1 = r1.requestFocus(r2)
            if (r1 == 0) goto L_0x0226
            goto L_0x0229
        L_0x0226:
            int r5 = r5 + 1
            goto L_0x0208
        L_0x0229:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.view.ViewPager.populate(int):void");
    }

    private void a(ItemInfo itemInfo, int i2, ItemInfo itemInfo2) {
        ItemInfo itemInfo3;
        ItemInfo itemInfo4;
        int a2 = this.o.a();
        int width = getWidth();
        float f2 = width > 0 ? ((float) this.u) / ((float) width) : 0.0f;
        if (itemInfo2 != null) {
            int i3 = itemInfo2.b;
            if (i3 < itemInfo.b) {
                float f3 = itemInfo2.e + itemInfo2.d + f2;
                int i4 = i3 + 1;
                int i5 = 0;
                while (i4 <= itemInfo.b && i5 < this.l.size()) {
                    Object obj = this.l.get(i5);
                    while (true) {
                        itemInfo4 = (ItemInfo) obj;
                        if (i4 > itemInfo4.b && i5 < this.l.size() - 1) {
                            i5++;
                            obj = this.l.get(i5);
                        }
                    }
                    while (i4 < itemInfo4.b) {
                        f3 += this.o.e(i4) + f2;
                        i4++;
                    }
                    itemInfo4.e = f3;
                    f3 += itemInfo4.d + f2;
                    i4++;
                }
            } else if (i3 > itemInfo.b) {
                int size = this.l.size() - 1;
                float f4 = itemInfo2.e;
                while (true) {
                    i3--;
                    if (i3 < itemInfo.b || size < 0) {
                        break;
                    }
                    Object obj2 = this.l.get(size);
                    while (true) {
                        itemInfo3 = (ItemInfo) obj2;
                        if (i3 < itemInfo3.b && size > 0) {
                            size--;
                            obj2 = this.l.get(size);
                        }
                    }
                    while (i3 > itemInfo3.b) {
                        f4 -= this.o.e(i3) + f2;
                        i3--;
                    }
                    f4 -= itemInfo3.d + f2;
                    itemInfo3.e = f4;
                }
            }
        }
        int size2 = this.l.size();
        float f5 = itemInfo.e;
        int i6 = itemInfo.b - 1;
        this.y = itemInfo.b == 0 ? itemInfo.e : -3.4028235E38f;
        int i7 = a2 - 1;
        this.z = itemInfo.b == i7 ? (itemInfo.e + itemInfo.d) - 1.0f : Float.MAX_VALUE;
        int i8 = i2 - 1;
        while (i8 >= 0) {
            ItemInfo itemInfo5 = this.l.get(i8);
            while (i6 > itemInfo5.b) {
                f5 -= this.o.e(i6) + f2;
                i6--;
            }
            f5 -= itemInfo5.d + f2;
            itemInfo5.e = f5;
            if (itemInfo5.b == 0) {
                this.y = f5;
            }
            i8--;
            i6--;
        }
        float f6 = itemInfo.e + itemInfo.d + f2;
        int i9 = itemInfo.b + 1;
        int i10 = i2 + 1;
        while (i10 < size2) {
            ItemInfo itemInfo6 = this.l.get(i10);
            while (i9 < itemInfo6.b) {
                f6 += this.o.e(i9) + f2;
                i9++;
            }
            if (itemInfo6.b == i7) {
                this.z = (itemInfo6.d + f6) - 1.0f;
            }
            itemInfo6.e = f6;
            f6 += itemInfo6.d + f2;
            i10++;
            i9++;
        }
        this.ah = false;
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        int f3063a;
        Parcelable b;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f3063a);
            parcel.writeParcelable(this.b, i);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f3063a + "}";
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.f3063a = parcel.readInt();
            this.b = parcel.readParcelable((ClassLoader) null);
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f3063a = this.p;
        if (this.o != null) {
            savedState.b = this.o.c();
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
        if (this.o != null) {
            this.o.a(savedState.b, (ClassLoader) null);
            setCurrentItemInternal(savedState.f3063a, false, true);
            return;
        }
        this.q = savedState.f3063a;
        this.r = savedState.b;
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        layoutParams2.f3061a |= view instanceof Decor;
        if (!this.C) {
            super.addView(view, i2, layoutParams);
        } else if (!layoutParams2.f3061a) {
            layoutParams2.d = true;
            addViewInLayout(view, i2, layoutParams);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    /* access modifiers changed from: package-private */
    public ItemInfo infoForChild(View view) {
        Iterator<ItemInfo> it = this.l.iterator();
        while (it.hasNext()) {
            ItemInfo next = it.next();
            if (this.o.a(view, next.f3060a)) {
                return next;
            }
        }
        return null;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.view.ViewParent] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public miuipub.view.ViewPager.ItemInfo infoForAnyChild(android.view.View r2) {
        /*
            r1 = this;
        L_0x0000:
            android.view.ViewParent r0 = r2.getParent()
            if (r0 == r1) goto L_0x0010
            boolean r2 = r0 instanceof android.view.View
            if (r2 != 0) goto L_0x000c
            r2 = 0
            return r2
        L_0x000c:
            r2 = r0
            android.view.View r2 = (android.view.View) r2
            goto L_0x0000
        L_0x0010:
            miuipub.view.ViewPager$ItemInfo r2 = r1.infoForChild(r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.view.ViewPager.infoForAnyChild(android.view.View):miuipub.view.ViewPager$ItemInfo");
    }

    /* access modifiers changed from: package-private */
    public ItemInfo infoForPosition(int i2) {
        Iterator<ItemInfo> it = this.l.iterator();
        while (it.hasNext()) {
            ItemInfo next = it.next();
            if (next.b == i2) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.ag = true;
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
            int r4 = r0.J
            int r3 = java.lang.Math.min(r3, r4)
            r0.K = r3
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
            miuipub.view.ViewPager$LayoutParams r6 = (miuipub.view.ViewPager.LayoutParams) r6
            if (r6 == 0) goto L_0x00c1
            boolean r10 = r6.f3061a
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
            r0.A = r1
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r8)
            r0.B = r1
            r0.C = r7
            r16.populate()
            r1 = 0
            r0.C = r1
            int r2 = r16.getChildCount()
        L_0x00de:
            if (r1 >= r2) goto L_0x014b
            android.view.View r4 = r0.getChildAt(r1)
            int r7 = r4.getVisibility()
            if (r7 == r6) goto L_0x0148
            java.lang.String r7 = "ViewPager"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Measuring #"
            r9.append(r10)
            r9.append(r1)
            java.lang.String r10 = " "
            r9.append(r10)
            r9.append(r4)
            java.lang.String r10 = ": "
            r9.append(r10)
            int r10 = r0.A
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            android.util.Log.v(r7, r9)
            android.view.ViewGroup$LayoutParams r7 = r4.getLayoutParams()
            miuipub.view.ViewPager$LayoutParams r7 = (miuipub.view.ViewPager.LayoutParams) r7
            boolean r9 = r7.f3061a
            if (r9 != 0) goto L_0x0148
            float r9 = (float) r3
            float r7 = r7.c
            float r9 = r9 * r7
            int r7 = (int) r9
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r8)
            miuipub.view.ViewPager$ItemInfo r9 = r0.infoForChild(r4)
            if (r9 == 0) goto L_0x0140
            boolean r9 = r9.f
            if (r9 == 0) goto L_0x0140
            int r9 = r16.getSplitActionBarHeight()
            float r9 = (float) r9
            r10 = 1065353216(0x3f800000, float:1.0)
            float r11 = r0.N
            float r10 = r10 - r11
            float r9 = r9 * r10
            int r9 = (int) r9
            int r9 = r5 - r9
            goto L_0x0141
        L_0x0140:
            r9 = r5
        L_0x0141:
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r8)
            r4.measure(r7, r9)
        L_0x0148:
            int r1 = r1 + 1
            goto L_0x00de
        L_0x014b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.view.ViewPager.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            a(i2, i4, this.u, this.u);
        }
    }

    private void a(int i2, int i3, int i4, int i5) {
        if (i3 <= 0 || this.l.isEmpty()) {
            ItemInfo infoForPosition = infoForPosition(this.p);
            int min = (int) ((infoForPosition != null ? Math.min(infoForPosition.e, this.z) : 0.0f) * ((float) i2));
            if (min != getScrollX()) {
                b();
                scrollTo(min, getScrollY());
                return;
            }
            return;
        }
        int scrollX = (int) ((((float) getScrollX()) / ((float) (i3 + i5))) * ((float) (i4 + i2)));
        scrollTo(scrollX, getScrollY());
        if (!this.s.isFinished()) {
            this.s.startScroll(scrollX, 0, (int) (infoForPosition(this.p).e * ((float) i2)), 0, this.s.getDuration() - this.s.timePassed());
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        ItemInfo infoForChild;
        int i8;
        int i9;
        int i10 = 1;
        this.C = true;
        populate();
        this.C = false;
        int childCount = getChildCount();
        int i11 = i4 - i2;
        int i12 = i5 - i3;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int i13 = paddingBottom;
        int i14 = 0;
        int i15 = paddingTop;
        int i16 = paddingLeft;
        int i17 = 0;
        while (true) {
            i6 = 8;
            if (i17 >= childCount) {
                break;
            }
            View childAt = getChildAt(i17);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.f3061a) {
                    int i18 = layoutParams.b & 7;
                    int i19 = layoutParams.b & 112;
                    if (i18 == i10) {
                        i8 = Math.max((i11 - childAt.getMeasuredWidth()) / 2, i16);
                    } else if (i18 == 3) {
                        int i20 = i16;
                        i16 = childAt.getMeasuredWidth() + i16;
                        i8 = i20;
                    } else if (i18 != 5) {
                        i8 = i16;
                    } else {
                        i8 = (i11 - paddingRight) - childAt.getMeasuredWidth();
                        paddingRight += childAt.getMeasuredWidth();
                    }
                    if (i19 == 16) {
                        i9 = Math.max((i12 - childAt.getMeasuredHeight()) / 2, i15);
                    } else if (i19 == 48) {
                        i9 = i15;
                        i15 = childAt.getMeasuredHeight() + i15;
                    } else if (i19 != 80) {
                        i9 = i15;
                    } else {
                        i9 = (i12 - i13) - childAt.getMeasuredHeight();
                        i13 += childAt.getMeasuredHeight();
                    }
                    int i21 = i8 + scrollX;
                    childAt.layout(i21, i9, childAt.getMeasuredWidth() + i21, i9 + childAt.getMeasuredHeight());
                    i14++;
                }
            }
            i17++;
            i10 = 1;
        }
        int i22 = 0;
        while (i22 < childCount) {
            View childAt2 = getChildAt(i22);
            if (childAt2.getVisibility() != i6) {
                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                if (!layoutParams2.f3061a && (infoForChild = infoForChild(childAt2)) != null) {
                    int i23 = ((int) (((float) i11) * infoForChild.e)) + i16;
                    if (this.O || layoutParams2.d) {
                        layoutParams2.d = false;
                        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (((float) ((i11 - i16) - paddingRight)) * layoutParams2.c), 1073741824);
                        this.O = false;
                        i7 = childCount;
                        childAt2.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec((int) (((float) ((i12 - i15) - i13)) - (((float) (infoForChild.f ? getSplitActionBarHeight() : 0)) * (1.0f - this.N))), 1073741824));
                    } else {
                        i7 = childCount;
                    }
                    Log.v(f3059a, "Positioning #" + i22 + " " + childAt2 + " f=" + infoForChild.f3060a + ":" + i23 + "," + i15 + " " + childAt2.getMeasuredWidth() + "x" + childAt2.getMeasuredHeight());
                    childAt2.layout(i23, i15, childAt2.getMeasuredWidth() + i23, childAt2.getMeasuredHeight() + i15);
                    i22++;
                    childCount = i7;
                    i6 = 8;
                }
            }
            i7 = childCount;
            i22++;
            childCount = i7;
            i6 = 8;
        }
        this.w = i15;
        this.x = i12 - i13;
        this.aj = i14;
        this.ag = false;
    }

    public void computeScroll() {
        if (this.s.isFinished() || !this.s.computeScrollOffset()) {
            b();
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.s.getCurrX();
        int currY = this.s.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            if (!a(currX, false)) {
                this.s.abortAnimation();
                scrollTo(0, currY);
            }
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0065  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(int r7, boolean r8) {
        /*
            r6 = this;
            java.util.ArrayList<miuipub.view.ViewPager$ItemInfo> r0 = r6.l
            int r0 = r0.size()
            r1 = 0
            if (r0 != 0) goto L_0x001c
            r6.ai = r1
            r7 = 0
            r6.onPageScrolled(r1, r7, r1)
            boolean r7 = r6.ai
            if (r7 == 0) goto L_0x0014
            return r1
        L_0x0014:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "onPageScrolled did not call superclass implementation"
            r7.<init>(r8)
            throw r7
        L_0x001c:
            miuipub.view.ViewPager$ItemInfo r0 = r6.c()
            int r2 = r6.getWidth()
            int r3 = r6.u
            int r3 = r3 + r2
            int r4 = r6.u
            float r4 = (float) r4
            float r2 = (float) r2
            float r4 = r4 / r2
            int r5 = r0.b
            float r7 = (float) r7
            float r7 = r7 / r2
            float r2 = r0.e
            float r7 = r7 - r2
            float r0 = r0.d
            float r0 = r0 + r4
            float r7 = r7 / r0
            float r0 = (float) r3
            float r0 = r0 * r7
            int r0 = (int) r0
            r2 = 1
            if (r8 == 0) goto L_0x0093
            float r8 = r6.mLastPageOffset
            int r8 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r8 >= 0) goto L_0x0050
            r8 = 1057803469(0x3f0ccccd, float:0.55)
            int r8 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r8 <= 0) goto L_0x0050
            int r8 = r5 + 1
            r3 = r8
        L_0x004e:
            r8 = 1
            goto L_0x0061
        L_0x0050:
            float r8 = r6.mLastPageOffset
            int r8 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r8 <= 0) goto L_0x005f
            r8 = 1055286886(0x3ee66666, float:0.45)
            int r8 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r8 >= 0) goto L_0x005f
            r3 = r5
            goto L_0x004e
        L_0x005f:
            r3 = r5
            r8 = 0
        L_0x0061:
            r6.mLastPageOffset = r7
            if (r8 == 0) goto L_0x0093
            if (r3 >= 0) goto L_0x0069
            r8 = 0
            goto L_0x007a
        L_0x0069:
            miuipub.view.PagerAdapter r8 = r6.o
            int r8 = r8.a()
            if (r3 < r8) goto L_0x0079
            miuipub.view.PagerAdapter r8 = r6.o
            int r8 = r8.a()
            int r8 = r8 - r2
            goto L_0x007a
        L_0x0079:
            r8 = r3
        L_0x007a:
            int r3 = r6.p
            if (r8 == r3) goto L_0x0093
            r6.populate(r8)
            miuipub.view.ViewPager$OnPageChangeListener r3 = r6.ak
            if (r3 == 0) goto L_0x008a
            miuipub.view.ViewPager$OnPageChangeListener r3 = r6.ak
            r3.a(r8)
        L_0x008a:
            miuipub.view.ViewPager$OnPageChangeListener r3 = r6.al
            if (r3 == 0) goto L_0x0093
            miuipub.view.ViewPager$OnPageChangeListener r3 = r6.al
            r3.a(r8)
        L_0x0093:
            r6.ai = r1
            r6.onPageScrolled(r5, r7, r0)
            boolean r7 = r6.ai
            if (r7 == 0) goto L_0x009d
            return r2
        L_0x009d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "onPageScrolled did not call superclass implementation"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.view.ViewPager.a(int, boolean):boolean");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0063  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPageScrolled(int r12, float r13, int r14) {
        /*
            r11 = this;
            int r0 = r11.aj
            r1 = 1
            if (r0 <= 0) goto L_0x006a
            int r0 = r11.getScrollX()
            int r2 = r11.getPaddingLeft()
            int r3 = r11.getPaddingRight()
            int r4 = r11.getWidth()
            int r5 = r11.getChildCount()
            r6 = 0
        L_0x001a:
            if (r6 >= r5) goto L_0x006a
            android.view.View r7 = r11.getChildAt(r6)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            miuipub.view.ViewPager$LayoutParams r8 = (miuipub.view.ViewPager.LayoutParams) r8
            boolean r9 = r8.f3061a
            if (r9 != 0) goto L_0x002b
            goto L_0x0067
        L_0x002b:
            int r8 = r8.b
            r8 = r8 & 7
            if (r8 == r1) goto L_0x004c
            r9 = 3
            if (r8 == r9) goto L_0x0046
            r9 = 5
            if (r8 == r9) goto L_0x0039
            r8 = r2
            goto L_0x005b
        L_0x0039:
            int r8 = r4 - r3
            int r9 = r7.getMeasuredWidth()
            int r8 = r8 - r9
            int r9 = r7.getMeasuredWidth()
            int r3 = r3 + r9
            goto L_0x0058
        L_0x0046:
            int r8 = r7.getWidth()
            int r8 = r8 + r2
            goto L_0x005b
        L_0x004c:
            int r8 = r7.getMeasuredWidth()
            int r8 = r4 - r8
            int r8 = r8 / 2
            int r8 = java.lang.Math.max(r8, r2)
        L_0x0058:
            r10 = r8
            r8 = r2
            r2 = r10
        L_0x005b:
            int r2 = r2 + r0
            int r9 = r7.getLeft()
            int r2 = r2 - r9
            if (r2 == 0) goto L_0x0066
            r7.offsetLeftAndRight(r2)
        L_0x0066:
            r2 = r8
        L_0x0067:
            int r6 = r6 + 1
            goto L_0x001a
        L_0x006a:
            miuipub.view.ViewPager$OnPageChangeListener r0 = r11.ak
            if (r0 == 0) goto L_0x0073
            miuipub.view.ViewPager$OnPageChangeListener r0 = r11.ak
            r0.a(r12, r13, r14)
        L_0x0073:
            miuipub.view.ViewPager$OnPageChangeListener r0 = r11.al
            if (r0 == 0) goto L_0x007c
            miuipub.view.ViewPager$OnPageChangeListener r0 = r11.al
            r0.a(r12, r13, r14)
        L_0x007c:
            r11.ai = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.view.ViewPager.onPageScrolled(int, float, int):void");
    }

    private void b() {
        boolean z2 = this.an == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            this.s.abortAnimation();
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.s.getCurrX();
            int currY = this.s.getCurrY();
            if (!(scrollX == currX && scrollY == currY)) {
                scrollTo(currX, currY);
            }
            setScrollState(0);
        }
        this.E = false;
        Iterator<ItemInfo> it = this.l.iterator();
        while (it.hasNext()) {
            ItemInfo next = it.next();
            if (next.c) {
                next.c = false;
                z2 = true;
            }
        }
        if (z2) {
            populate();
        }
    }

    private boolean a(float f2, float f3) {
        return (f2 < ((float) this.K) && f3 > 0.0f) || (f2 > ((float) (getWidth() - this.K)) && f3 < 0.0f);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int findPointerIndex;
        MotionEvent motionEvent2 = motionEvent;
        if (!this.mDragEnabled) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            Log.v(f3059a, "Intercept done!");
            this.G = false;
            this.H = false;
            this.R = -1;
            if (this.T != null) {
                this.T.recycle();
                this.T = null;
            }
            return false;
        }
        if (action != 0) {
            if (this.G) {
                Log.v(f3059a, "Intercept returning true!");
                return true;
            } else if (this.H) {
                Log.v(f3059a, "Intercept returning false!");
                return false;
            }
        }
        if (action == 0) {
            float x2 = motionEvent.getX();
            this.M = x2;
            this.P = x2;
            this.Q = motionEvent.getY();
            this.R = motionEvent2.getPointerId(0);
            this.H = false;
            this.s.computeScrollOffset();
            if (this.an != 2 || Math.abs(this.s.getFinalX() - this.s.getCurrX()) <= this.aa) {
                b();
                this.G = false;
            } else {
                this.s.abortAnimation();
                this.E = false;
                populate();
                this.G = true;
                setScrollState(1);
            }
            Log.v(f3059a, "Down at " + this.P + "," + this.Q + " mIsBeingDragged=" + this.G + "mIsUnableToDrag=" + this.H);
        } else if (action == 2) {
            int i2 = this.R;
            if (i2 != -1 && (findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent2, i2)) >= 0) {
                float x3 = motionEvent2.getX(findPointerIndex);
                float f2 = x3 - this.P;
                float abs = Math.abs(f2);
                float y2 = motionEvent2.getY(findPointerIndex);
                float abs2 = Math.abs(y2 - this.Q);
                Log.v(f3059a, "Moved x to " + x3 + "," + y2 + " diff=" + abs + "," + abs2);
                if (f2 != 0.0f && !a(this.P, f2)) {
                    if (canScroll(this, false, (int) f2, (int) x3, (int) y2)) {
                        this.P = x3;
                        this.M = x3;
                        this.Q = y2;
                        this.H = true;
                        return false;
                    }
                }
                if (abs > ((float) this.L) && abs > abs2) {
                    Log.v(f3059a, "Starting drag!");
                    this.G = true;
                    setScrollState(1);
                    this.P = f2 > 0.0f ? this.M + ((float) this.L) : this.M - ((float) this.L);
                    setScrollingCacheEnabled(true);
                } else if (abs2 > ((float) this.L)) {
                    Log.v(f3059a, "Starting unable to drag!");
                    this.H = true;
                }
                if (this.G && a(x3)) {
                    postInvalidateOnAnimation();
                }
            }
        } else if (action == 6) {
            a(motionEvent);
        }
        if (this.T == null) {
            this.T = VelocityTracker.obtain();
        }
        this.T.addMovement(motionEvent2);
        return this.G;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.ac) {
            return true;
        }
        boolean z2 = false;
        if ((motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) || this.o == null || this.o.a() == 0) {
            return false;
        }
        if (this.T == null) {
            this.T = VelocityTracker.obtain();
        }
        this.T.addMovement(motionEvent);
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.s.abortAnimation();
                this.E = false;
                populate();
                this.G = true;
                setScrollState(1);
                float x2 = motionEvent.getX();
                this.M = x2;
                this.P = x2;
                this.R = motionEvent.getPointerId(0);
                break;
            case 1:
                if (this.G) {
                    VelocityTracker velocityTracker = this.T;
                    velocityTracker.computeCurrentVelocity(1000, (float) this.V);
                    int xVelocity = (int) velocityTracker.getXVelocity(this.R);
                    this.E = true;
                    int width = getWidth();
                    int scrollX = getScrollX();
                    ItemInfo c2 = c();
                    setCurrentItemInternal(a(c2.b, ((((float) scrollX) / ((float) width)) - c2.e) / c2.d, xVelocity, (int) (motionEvent.getX(motionEvent.findPointerIndex(this.R)) - this.M)), true, true, xVelocity);
                    this.R = -1;
                    d();
                    this.ae.onRelease();
                    this.af.onRelease();
                    z2 = this.ae.isFinished() | this.af.isFinished();
                    break;
                }
                break;
            case 2:
                if (!this.G) {
                    int findPointerIndex = motionEvent.findPointerIndex(this.R);
                    float x3 = motionEvent.getX(findPointerIndex);
                    float abs = Math.abs(x3 - this.P);
                    float y2 = motionEvent.getY(findPointerIndex);
                    float abs2 = Math.abs(y2 - this.Q);
                    Log.v(f3059a, "Moved x to " + x3 + "," + y2 + " diff=" + abs + "," + abs2);
                    if (abs > ((float) this.L) && abs > abs2) {
                        Log.v(f3059a, "Starting drag!");
                        this.G = true;
                        this.P = x3 - this.M > 0.0f ? this.M + ((float) this.L) : this.M - ((float) this.L);
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                    }
                }
                if (this.G) {
                    z2 = a(motionEvent.getX(motionEvent.findPointerIndex(this.R)));
                    break;
                }
                break;
            case 3:
                if (this.G) {
                    setCurrentItemInternal(this.p, true, true);
                    this.R = -1;
                    d();
                    this.ae.onRelease();
                    this.af.onRelease();
                    z2 = this.ae.isFinished() | this.af.isFinished();
                    break;
                }
                break;
            case 5:
                int actionIndex = motionEvent.getActionIndex();
                this.P = motionEvent.getX(actionIndex);
                this.R = motionEvent.getPointerId(actionIndex);
                break;
            case 6:
                a(motionEvent);
                this.P = motionEvent.getX(motionEvent.findPointerIndex(this.R));
                break;
        }
        if (z2) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        return true;
    }

    private boolean a(float f2) {
        boolean z2;
        boolean z3;
        float f3 = this.P - f2;
        this.P = f2;
        float scrollX = ((float) getScrollX()) + f3;
        float width = (float) getWidth();
        float f4 = this.y * width;
        float f5 = this.z * width;
        boolean z4 = false;
        ItemInfo itemInfo = this.l.get(0);
        ItemInfo itemInfo2 = this.l.get(this.l.size() - 1);
        if (itemInfo.b != 0) {
            f4 = itemInfo.e * width;
            z2 = false;
        } else {
            z2 = true;
        }
        if (itemInfo2.b != this.o.a() - 1) {
            f5 = itemInfo2.e * width;
            z3 = false;
        } else {
            z3 = true;
        }
        if (scrollX < f4) {
            if (z2) {
                this.ae.onPull(Math.abs(f4 - scrollX) / width);
                z4 = true;
            }
            scrollX = f4;
        } else if (scrollX > f5) {
            if (z3) {
                this.af.onPull(Math.abs(scrollX - f5) / width);
                z4 = true;
            }
            scrollX = f5;
        }
        int i2 = (int) scrollX;
        this.P += scrollX - ((float) i2);
        scrollTo(i2, getScrollY());
        a(i2, true);
        return z4;
    }

    private ItemInfo c() {
        int i2;
        int width = getWidth();
        float scrollX = width > 0 ? ((float) getScrollX()) / ((float) width) : 0.0f;
        float f2 = width > 0 ? ((float) this.u) / ((float) width) : 0.0f;
        ItemInfo itemInfo = null;
        int i3 = 0;
        boolean z2 = true;
        int i4 = -1;
        float f3 = 0.0f;
        float f4 = 0.0f;
        while (i3 < this.l.size()) {
            ItemInfo itemInfo2 = this.l.get(i3);
            if (!z2 && itemInfo2.b != (i2 = i4 + 1)) {
                itemInfo2 = this.m;
                itemInfo2.e = f3 + f4 + f2;
                itemInfo2.b = i2;
                itemInfo2.d = this.o.e(itemInfo2.b);
                i3--;
            }
            f3 = itemInfo2.e;
            float f5 = itemInfo2.d + f3 + f2;
            if (!z2 && scrollX < f3) {
                return itemInfo;
            }
            if (scrollX < f5 || i3 == this.l.size() - 1) {
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
        if (Math.abs(i4) <= this.W || Math.abs(i3) <= this.U) {
            i2 = (int) (((float) i2) + f2 + 0.5f);
        } else if (i3 <= 0) {
            i2++;
        }
        return this.l.size() > 0 ? Math.max(this.l.get(0).b, Math.min(i2, this.l.get(this.l.size() - 1).b)) : i2;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean z2 = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && this.o != null && this.o.a() > 1)) {
            if (!this.ae.isFinished()) {
                int save = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((float) ((-height) + getPaddingTop()), this.y * ((float) width));
                this.ae.setSize(height, width);
                z2 = this.ae.draw(canvas);
                canvas.restoreToCount(save);
            }
            if (!this.af.isFinished()) {
                int save2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate((float) (-getPaddingTop()), (-(this.z + 1.0f)) * ((float) width2));
                this.af.setSize(height2, width2);
                z2 |= this.af.draw(canvas);
                canvas.restoreToCount(save2);
            }
        } else {
            this.ae.finish();
            this.af.finish();
        }
        if (z2) {
            postInvalidateOnAnimation();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f2;
        float f3;
        float f4;
        super.onDraw(canvas);
        if (this.u > 0 && this.v != null && this.l.size() > 0 && this.o != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            float f5 = (float) width;
            float f6 = ((float) this.u) / f5;
            int i2 = 0;
            ItemInfo itemInfo = this.l.get(0);
            float f7 = itemInfo.e;
            int size = this.l.size();
            int i3 = itemInfo.b;
            int i4 = this.l.get(size - 1).b;
            while (i3 < i4) {
                while (i3 > itemInfo.b && i2 < size) {
                    i2++;
                    itemInfo = this.l.get(i2);
                }
                if (i3 == itemInfo.b) {
                    f3 = (itemInfo.e + itemInfo.d) * f5;
                    f2 = itemInfo.e + itemInfo.d + f6;
                } else {
                    float e2 = this.o.e(i3);
                    f2 = f7 + e2 + f6;
                    f3 = (f7 + e2) * f5;
                }
                if (((float) this.u) + f3 > ((float) scrollX)) {
                    f4 = f6;
                    this.v.setBounds((int) f3, this.w, (int) (((float) this.u) + f3 + 0.5f), this.x);
                    this.v.draw(canvas);
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
        if (this.G) {
            return false;
        }
        this.ac = true;
        setScrollState(1);
        this.P = 0.0f;
        this.M = 0.0f;
        if (this.T == null) {
            this.T = VelocityTracker.obtain();
        } else {
            this.T.clear();
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, 0.0f, 0.0f, 0);
        this.T.addMovement(obtain);
        obtain.recycle();
        this.ad = uptimeMillis;
        return true;
    }

    public void endFakeDrag() {
        if (this.ac) {
            VelocityTracker velocityTracker = this.T;
            velocityTracker.computeCurrentVelocity(1000, (float) this.V);
            int xVelocity = (int) velocityTracker.getXVelocity(this.R);
            this.E = true;
            int width = getWidth();
            int scrollX = getScrollX();
            ItemInfo c2 = c();
            setCurrentItemInternal(a(c2.b, ((((float) scrollX) / ((float) width)) - c2.e) / c2.d, xVelocity, (int) (this.P - this.M)), true, true, xVelocity);
            d();
            this.ac = false;
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public void fakeDragBy(float f2) {
        if (this.ac) {
            this.P += f2;
            float scrollX = ((float) getScrollX()) - f2;
            float width = (float) getWidth();
            float f3 = this.y * width;
            float f4 = this.z * width;
            ItemInfo itemInfo = this.l.get(0);
            ItemInfo itemInfo2 = this.l.get(this.l.size() - 1);
            if (itemInfo.b != 0) {
                f3 = itemInfo.e * width;
            }
            if (itemInfo2.b != this.o.a() - 1) {
                f4 = itemInfo2.e * width;
            }
            if (scrollX < f3) {
                scrollX = f3;
            } else if (scrollX > f4) {
                scrollX = f4;
            }
            int i2 = (int) scrollX;
            this.P += scrollX - ((float) i2);
            scrollTo(i2, getScrollY());
            a(i2, true);
            MotionEvent obtain = MotionEvent.obtain(this.ad, SystemClock.uptimeMillis(), 2, this.P, 0.0f, 0);
            this.T.addMovement(obtain);
            obtain.recycle();
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public boolean isFakeDragging() {
        return this.ac;
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.R) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.P = motionEvent.getX(i2);
            this.R = motionEvent.getPointerId(i2);
            if (this.T != null) {
                this.T.clear();
            }
        }
    }

    private void d() {
        this.G = false;
        this.H = false;
        if (this.T != null) {
            this.T.recycle();
            this.T = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z2) {
        if (this.D != z2) {
            this.D = z2;
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
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        boolean z2 = false;
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i2);
        if (findNextFocus != null && findNextFocus != findFocus) {
            if (i2 == 17) {
                int i3 = a(this.n, findNextFocus).left;
                int i4 = a(this.n, findFocus).left;
                if (findFocus == null || i3 < i4) {
                    requestFocus = findNextFocus.requestFocus();
                } else {
                    requestFocus = pageLeft();
                }
            } else if (i2 == 66) {
                int i5 = a(this.n, findNextFocus).left;
                int i6 = a(this.n, findFocus).left;
                if (findFocus == null || i5 > i6) {
                    requestFocus = findNextFocus.requestFocus();
                } else {
                    requestFocus = pageRight();
                }
            }
            z2 = requestFocus;
        } else if (i2 == 17 || i2 == 1) {
            z2 = pageLeft();
        } else if (i2 == 66 || i2 == 2) {
            z2 = pageRight();
        }
        if (z2) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i2));
        }
        return z2;
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
        if (this.p <= 0) {
            return false;
        }
        setCurrentItem(this.p - 1, true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean pageRight() {
        if (this.o == null || this.p >= this.o.a() - 1) {
            return false;
        }
        setCurrentItem(this.p + 1, true);
        return true;
    }

    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        ItemInfo infoForChild;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.p) {
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
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.p) {
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
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.p && childAt.requestFocus(i2, rect)) {
                return true;
            }
            i4 += i3;
        }
        return false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        ItemInfo infoForChild;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.p && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
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

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(ViewPager.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ViewPager.class.getName());
        accessibilityNodeInfo.setScrollable(this.o != null && this.o.a() > 1);
        if (this.o != null && this.p >= 0 && this.p < this.o.a() - 1) {
            accessibilityNodeInfo.addAction(4096);
        }
        if (this.o != null && this.p > 0 && this.p < this.o.a()) {
            accessibilityNodeInfo.addAction(8192);
        }
    }

    public boolean performAccessibilityAction(int i2, Bundle bundle) {
        if (super.performAccessibilityAction(i2, bundle)) {
            return true;
        }
        if (i2 != 4096) {
            if (i2 != 8192 || this.o == null || this.p <= 0 || this.p >= this.o.a()) {
                return false;
            }
            setCurrentItem(this.p - 1);
            return true;
        } else if (this.o == null || this.p < 0 || this.p >= this.o.a() - 1) {
            return false;
        } else {
            setCurrentItem(this.p + 1);
            return true;
        }
    }

    public void setDraggable(boolean z2) {
        this.mDragEnabled = z2;
    }

    /* access modifiers changed from: package-private */
    public int getSplitActionBarHeight() {
        ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.getActionBarOverlayLayout(this);
        if (actionBarOverlayLayout == null || actionBarOverlayLayout.getActionBarView() == null) {
            return 0;
        }
        return actionBarOverlayLayout.getActionBarView().getSplitActionBarHeight(true);
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

    public void setBottomMarginProgress(float f2) {
        this.N = f2;
        this.O = true;
        requestLayout();
        invalidate();
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3061a;
        public int b;
        public float c = 0.0f;
        public boolean d;

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.i);
            this.b = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }
}
