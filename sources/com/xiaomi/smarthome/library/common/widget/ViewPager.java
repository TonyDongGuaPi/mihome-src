package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
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
import com.xiaomi.smarthome.library.common.widget.PagerAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ViewPager extends ViewGroup {
    private static final int F = -1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;

    /* renamed from: a  reason: collision with root package name */
    private static final String f18970a = "ViewPager";
    private static final boolean b = false;
    private static final boolean c = false;
    private static final int d = 0;
    private static final int e = 600;
    private static final Comparator<ItemInfo> f = new Comparator<ItemInfo>() {
        /* renamed from: a */
        public int compare(ItemInfo itemInfo, ItemInfo itemInfo2) {
            return itemInfo.b - itemInfo2.b;
        }
    };
    private static final Interpolator g = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2) + 1.0f;
        }
    };
    private int A;
    private float B;
    private float C;
    private float D;
    private int E = -1;
    private VelocityTracker G;
    private int H;
    private int I;
    private float J;
    private float K;
    private boolean L;
    private long M;
    private EdgeEffectCompat N;
    private EdgeEffectCompat O;
    private boolean P = true;
    private OnPageChangeListener Q;
    private int R = 0;
    private boolean S = true;
    private final ArrayList<ItemInfo> h = new ArrayList<>();
    private PagerAdapter i;
    private int j;
    private int k = -1;
    private Parcelable l = null;
    private ClassLoader m = null;
    private Scroller n;
    private PagerAdapter.DataSetObserver o;
    private int p;
    private Drawable q;
    private int r;
    private int s;
    private boolean t;
    boolean touchEnabled = true;
    private boolean u;
    private boolean v;
    private boolean w;
    private int x = 0;
    private boolean y;
    private boolean z;

    public interface OnPageChangeListener {
        void onPageScrollStateChanged(int i);

        void onPageScrolled(int i, float f, int i2);

        void onPageSelected(int i);
    }

    public static class SimpleOnPageChangeListener implements OnPageChangeListener {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
        }
    }

    static class ItemInfo {

        /* renamed from: a  reason: collision with root package name */
        Object f18972a;
        int b;
        boolean c;

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
        this.n = new Scroller(context, g);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.A = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
        this.H = viewConfiguration.getScaledMinimumFlingVelocity();
        this.I = viewConfiguration.getScaledMaximumFlingVelocity();
        this.N = new EdgeEffectCompat(context);
        this.O = new EdgeEffectCompat(context);
        this.J = context.getResources().getDisplayMetrics().density * 2500.0f;
        this.K = 0.8f;
    }

    private void setScrollState(int i2) {
        if (this.R != i2) {
            this.R = i2;
            if (this.Q != null) {
                this.Q.onPageScrollStateChanged(i2);
            }
        }
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        if (this.i != null) {
            this.i.a((PagerAdapter.DataSetObserver) null);
            this.i.a((View) this);
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                ItemInfo itemInfo = this.h.get(i2);
                this.i.a(this, itemInfo.b, itemInfo.f18972a);
            }
            this.i.b((View) this);
            this.h.clear();
            removeAllViews();
            this.j = 0;
            scrollTo(0, 0);
        }
        this.i = pagerAdapter;
        if (this.i != null) {
            if (this.o == null) {
                this.o = new DataSetObserver();
            }
            this.i.a(this.o);
            this.v = false;
            if (this.k >= 0) {
                this.i.a(this.l, this.m);
                setCurrentItemInternal(this.k, false, true);
                this.k = -1;
                this.l = null;
                this.m = null;
                return;
            }
            populate();
        }
    }

    public PagerAdapter getAdapter() {
        return this.i;
    }

    public void setCurrentItem(int i2) {
        this.v = false;
        setCurrentItemInternal(i2, !this.P, false);
    }

    public void setCurrentItem(int i2, boolean z2) {
        this.v = false;
        setCurrentItemInternal(i2, z2, false);
    }

    public int getCurrentItem() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentItemInternal(int i2, boolean z2, boolean z3) {
        setCurrentItemInternal(i2, z2, z3, 0);
    }

    /* access modifiers changed from: package-private */
    public void setCurrentItemInternal(int i2, boolean z2, boolean z3, int i3) {
        if (this.i == null || this.i.a() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (z3 || this.j != i2 || this.h.size() == 0) {
            boolean z4 = true;
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 >= this.i.a()) {
                i2 = this.i.a() - 1;
            }
            int i4 = this.x;
            if (i2 > this.j + i4 || i2 < this.j - i4) {
                for (int i5 = 0; i5 < this.h.size(); i5++) {
                    this.h.get(i5).c = true;
                }
            }
            if (this.j == i2) {
                z4 = false;
            }
            this.j = i2;
            populate();
            int width = (getWidth() + this.p) * i2;
            if (z2) {
                smoothScrollTo(width, 0, i3);
                if (z4 && this.Q != null) {
                    this.Q.onPageSelected(i2);
                    return;
                }
                return;
            }
            if (z4 && this.Q != null) {
                this.Q.onPageSelected(i2);
            }
            a();
            scrollTo(width, 0);
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.Q = onPageChangeListener;
    }

    public int getOffscreenPageLimit() {
        return this.x;
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 0) {
            Log.w(f18970a, "Requested offscreen page limit " + i2 + " too small; defaulting to " + 0);
            i2 = 0;
        }
        if (i2 != this.x) {
            this.x = i2;
            populate();
        }
    }

    public void setPageMargin(int i2) {
        int i3 = this.p;
        this.p = i2;
        int width = getWidth();
        a(width, width, i2, i3);
        requestLayout();
    }

    public int getPageMargin() {
        return this.p;
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.q = drawable;
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
        return super.verifyDrawable(drawable) || drawable == this.q;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.q;
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
            a();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        this.w = true;
        setScrollState(2);
        double abs = (double) ((((float) Math.abs(i6)) / ((float) (getWidth() + this.p))) * 100.0f);
        Double.isNaN(abs);
        int i8 = (int) (abs * 2.0d);
        int abs2 = Math.abs(i4);
        if (abs2 > 0) {
            float f2 = (float) i8;
            i5 = (int) (f2 + ((f2 / (((float) abs2) / this.J)) * this.K));
        } else {
            i5 = i8 + 350;
        }
        this.n.startScroll(scrollX, scrollY, i6, i7, Math.min(i5, 600));
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void addNewItem(int i2, int i3) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.b = i2;
        itemInfo.f18972a = this.i.a((View) this, i2);
        if (i3 < 0) {
            this.h.add(itemInfo);
        } else {
            this.h.add(i3, itemInfo);
        }
    }

    /* access modifiers changed from: package-private */
    public void dataSetChanged() {
        boolean z2 = true;
        boolean z3 = this.h.size() < 3 && this.h.size() < this.i.a();
        int i2 = 0;
        int i3 = -1;
        while (i2 < this.h.size()) {
            ItemInfo itemInfo = this.h.get(i2);
            int a2 = this.i.a(itemInfo.f18972a);
            if (a2 != -1) {
                if (a2 == -2) {
                    this.h.remove(i2);
                    i2--;
                    this.i.a(this, itemInfo.b, itemInfo.f18972a);
                    if (this.j == itemInfo.b) {
                        i3 = Math.max(0, Math.min(this.j, this.i.a() - 1));
                    }
                } else if (itemInfo.b != a2) {
                    if (itemInfo.b == this.j) {
                        i3 = a2;
                    }
                    itemInfo.b = a2;
                }
                z3 = true;
            }
            i2++;
        }
        Collections.sort(this.h, f);
        if (i3 >= 0) {
            setCurrentItemInternal(i3, false, true);
        } else {
            z2 = z3;
        }
        if (z2) {
            populate();
            requestLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void populate() {
        ItemInfo itemInfo;
        ItemInfo itemInfo2;
        if (this.i != null && !this.v && getWindowToken() != null) {
            this.i.a((View) this);
            int i2 = this.x;
            int i3 = 0;
            int max = Math.max(0, this.j - i2);
            int min = Math.min(this.i.a() - 1, this.j + i2);
            int i4 = 0;
            int i5 = -1;
            while (i4 < this.h.size()) {
                ItemInfo itemInfo3 = this.h.get(i4);
                if ((itemInfo3.b < max || itemInfo3.b > min) && !itemInfo3.c) {
                    this.h.remove(i4);
                    i4--;
                    this.i.a(this, itemInfo3.b, itemInfo3.f18972a);
                } else if (i5 < min && itemInfo3.b > max) {
                    int i6 = i5 + 1;
                    if (i6 < max) {
                        i6 = max;
                    }
                    while (i6 <= min && i6 < itemInfo3.b) {
                        addNewItem(i6, i4);
                        i6++;
                        i4++;
                    }
                }
                i5 = itemInfo3.b;
                i4++;
            }
            int i7 = this.h.size() > 0 ? this.h.get(this.h.size() - 1).b : -1;
            if (i7 < min) {
                int i8 = i7 + 1;
                if (i8 > max) {
                    max = i8;
                }
                while (max <= min) {
                    addNewItem(max, -1);
                    max++;
                }
            }
            int i9 = 0;
            while (true) {
                itemInfo = null;
                if (i9 >= this.h.size()) {
                    itemInfo2 = null;
                    break;
                } else if (this.h.get(i9).b == this.j) {
                    itemInfo2 = this.h.get(i9);
                    break;
                } else {
                    i9++;
                }
            }
            this.i.b(this, this.j, itemInfo2 != null ? itemInfo2.f18972a : null);
            this.i.b((View) this);
            if (hasFocus()) {
                View findFocus = findFocus();
                if (findFocus != null) {
                    itemInfo = infoForAnyChild(findFocus);
                }
                if (itemInfo == null || itemInfo.b != this.j) {
                    while (i3 < getChildCount()) {
                        View childAt = getChildAt(i3);
                        ItemInfo infoForChild = infoForChild(childAt);
                        if (infoForChild == null || infoForChild.b != this.j || !childAt.requestFocus(2)) {
                            i3++;
                        } else {
                            return;
                        }
                    }
                }
            }
        }
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
        int f18973a;
        Parcelable b;
        ClassLoader c;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f18973a);
            parcel.writeParcelable(this.b, i);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f18973a + "}";
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.f18973a = parcel.readInt();
            this.b = parcel.readParcelable(classLoader);
            this.c = classLoader;
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f18973a = this.j;
        if (this.i != null) {
            savedState.b = this.i.b();
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
        if (this.i != null) {
            this.i.a(savedState.b, savedState.c);
            setCurrentItemInternal(savedState.f18973a, false, true);
            return;
        }
        this.k = savedState.f18973a;
        this.l = savedState.b;
        this.m = savedState.c;
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (this.t) {
            addViewInLayout(view, i2, layoutParams);
            view.measure(this.r, this.s);
            return;
        }
        super.addView(view, i2, layoutParams);
    }

    /* access modifiers changed from: package-private */
    public ItemInfo infoForChild(View view) {
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            ItemInfo itemInfo = this.h.get(i2);
            if (this.i.a(view, itemInfo.f18972a)) {
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

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.P = true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        setMeasuredDimension(getDefaultSize(0, i2), getDefaultSize(0, i3));
        this.r = View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824);
        this.s = View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824);
        this.t = true;
        populate();
        this.t = false;
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                childAt.measure(this.r, this.s);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            a(i2, i4, this.p, this.p);
        }
    }

    private void a(int i2, int i3, int i4, int i5) {
        int i6 = i2 + i4;
        if (i3 > 0) {
            int scrollX = getScrollX();
            int i7 = i3 + i5;
            int i8 = scrollX / i7;
            int i9 = (int) ((((float) i8) + (((float) (scrollX % i7)) / ((float) i7))) * ((float) i6));
            scrollTo(i9, getScrollY());
            if (!this.n.isFinished()) {
                this.n.startScroll(i9, 0, this.j * i6, 0, this.n.getDuration() - this.n.timePassed());
                return;
            }
            return;
        }
        int i10 = this.j * i6;
        if (i10 != getScrollX()) {
            a();
            scrollTo(i10, getScrollY());
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        ItemInfo infoForChild;
        this.t = true;
        populate();
        this.t = false;
        int childCount = getChildCount();
        int i6 = i4 - i2;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (!(childAt.getVisibility() == 8 || (infoForChild = infoForChild(childAt)) == null)) {
                int paddingLeft = getPaddingLeft() + ((this.p + i6) * infoForChild.b);
                int paddingTop = getPaddingTop();
                childAt.layout(paddingLeft, paddingTop, childAt.getMeasuredWidth() + paddingLeft, childAt.getMeasuredHeight() + paddingTop);
            }
        }
        this.P = false;
    }

    public void computeScroll() {
        if (this.n.isFinished() || !this.n.computeScrollOffset()) {
            a();
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.n.getCurrX();
        int currY = this.n.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
        }
        if (this.Q != null) {
            int width = getWidth() + this.p;
            int i2 = currX / width;
            int i3 = currX % width;
            this.Q.onPageScrolled(i2, ((float) i3) / ((float) width), i3);
        }
        invalidate();
    }

    private void a() {
        boolean z2 = this.w;
        if (z2) {
            setScrollingCacheEnabled(false);
            this.n.abortAnimation();
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.n.getCurrX();
            int currY = this.n.getCurrY();
            if (!(scrollX == currX && scrollY == currY)) {
                scrollTo(currX, currY);
            }
            setScrollState(0);
        }
        this.v = false;
        this.w = false;
        boolean z3 = z2;
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            ItemInfo itemInfo = this.h.get(i2);
            if (itemInfo.c) {
                itemInfo.c = false;
                z3 = true;
            }
        }
        if (z3) {
            populate();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.touchEnabled) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            this.y = false;
            this.z = false;
            this.E = -1;
            return false;
        }
        if (action != 0) {
            if (this.y) {
                return true;
            }
            if (this.z) {
                return false;
            }
        }
        if (action == 0) {
            float x2 = motionEvent.getX();
            this.B = x2;
            this.C = x2;
            this.D = motionEvent.getY();
            this.E = MotionEventCompat.getPointerId(motionEvent, 0);
            if (this.R == 2) {
                this.y = true;
                this.z = false;
                setScrollState(1);
            } else {
                a();
                this.y = false;
                this.z = false;
            }
        } else if (action == 2) {
            int i2 = this.E;
            if (i2 != -1) {
                int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i2);
                float x3 = MotionEventCompat.getX(motionEvent, findPointerIndex);
                float f2 = x3 - this.C;
                float abs = Math.abs(f2);
                float y2 = MotionEventCompat.getY(motionEvent, findPointerIndex);
                float abs2 = Math.abs(y2 - this.D);
                int scrollX = getScrollX();
                if ((f2 <= 0.0f || scrollX != 0) && f2 < 0.0f && this.i != null) {
                    int a2 = ((this.i.a() - 1) * getWidth()) - 1;
                }
                if (canScroll(this, false, (int) f2, (int) x3, (int) y2)) {
                    this.C = x3;
                    this.B = x3;
                    this.D = y2;
                    return false;
                } else if (abs > ((float) this.A) && abs > abs2) {
                    this.y = true;
                    setScrollState(1);
                    this.C = x3;
                    setScrollingCacheEnabled(true);
                } else if (abs2 > ((float) this.A)) {
                    this.z = true;
                }
            }
        } else if (action == 6) {
            a(motionEvent);
        }
        return this.y;
    }

    public void enableScroll(boolean z2) {
        this.touchEnabled = z2;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.touchEnabled) {
            return super.onTouchEvent(motionEvent);
        }
        if (this.L || !this.S) {
            return true;
        }
        boolean z2 = false;
        if ((motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) || this.i == null || this.i.a() == 0) {
            return false;
        }
        if (this.G == null) {
            this.G = VelocityTracker.obtain();
        }
        this.G.addMovement(motionEvent);
        switch (motionEvent.getAction() & 255) {
            case 0:
                a();
                float x2 = motionEvent.getX();
                this.B = x2;
                this.C = x2;
                this.E = MotionEventCompat.getPointerId(motionEvent, 0);
                break;
            case 1:
                if (this.y) {
                    VelocityTracker velocityTracker = this.G;
                    velocityTracker.computeCurrentVelocity(1000, (float) this.I);
                    int xVelocity = (int) VelocityTrackerCompat.getXVelocity(velocityTracker, this.E);
                    this.v = true;
                    int scrollX = getScrollX() / (getWidth() + this.p);
                    if (xVelocity <= 0) {
                        scrollX++;
                    }
                    setCurrentItemInternal(scrollX, true, true, xVelocity);
                    this.E = -1;
                    b();
                    z2 = this.N.onRelease() | this.O.onRelease();
                    break;
                }
                break;
            case 2:
                if (!this.y) {
                    int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.E);
                    float x3 = MotionEventCompat.getX(motionEvent, findPointerIndex);
                    float abs = Math.abs(x3 - this.C);
                    float abs2 = Math.abs(MotionEventCompat.getY(motionEvent, findPointerIndex) - this.D);
                    if (abs > ((float) this.A) && abs > abs2) {
                        this.y = true;
                        this.C = x3;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                    }
                }
                if (this.y) {
                    float x4 = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.E));
                    float f2 = this.C - x4;
                    this.C = x4;
                    float scrollX2 = ((float) getScrollX()) + f2;
                    int width = getWidth();
                    int i2 = this.p + width;
                    int a2 = this.i.a() - 1;
                    float max = (float) Math.max(0, (this.j - 1) * i2);
                    float min = (float) (Math.min(this.j + 1, a2) * i2);
                    if (scrollX2 < max) {
                        if (max == 0.0f) {
                            z2 = this.N.onPull((-scrollX2) / ((float) width));
                        }
                        scrollX2 = max;
                    } else if (scrollX2 > min) {
                        if (min == ((float) (a2 * i2))) {
                            z2 = this.O.onPull((scrollX2 - min) / ((float) width));
                        }
                        scrollX2 = min;
                    }
                    int i3 = (int) scrollX2;
                    this.C += scrollX2 - ((float) i3);
                    scrollTo(i3, getScrollY());
                    if (this.Q != null) {
                        int i4 = i3 / i2;
                        int i5 = i3 % i2;
                        this.Q.onPageScrolled(i4, ((float) i5) / ((float) i2), i5);
                        break;
                    }
                }
                break;
            case 3:
                if (this.y) {
                    setCurrentItemInternal(this.j, true, true);
                    this.E = -1;
                    b();
                    z2 = this.N.onRelease() | this.O.onRelease();
                    break;
                }
                break;
            case 5:
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                this.C = MotionEventCompat.getX(motionEvent, actionIndex);
                this.E = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                break;
            case 6:
                a(motionEvent);
                this.C = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.E));
                break;
        }
        if (z2) {
            invalidate();
        }
        return true;
    }

    public void draw(Canvas canvas) {
        try {
            super.draw(canvas);
        } catch (Exception unused) {
        }
        boolean z2 = false;
        int overScrollMode = ViewCompat.getOverScrollMode(this);
        int i2 = 1;
        if (overScrollMode == 0 || (overScrollMode == 1 && this.i != null && this.i.a() > 1)) {
            if (!this.N.isFinished()) {
                int save = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(270.0f);
                canvas.translate((float) ((-height) + getPaddingTop()), 0.0f);
                this.N.setSize(height, getWidth());
                z2 = false | this.N.draw(canvas);
                canvas.restoreToCount(save);
            }
            if (!this.O.isFinished()) {
                int save2 = canvas.save();
                int width = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                if (this.i != null) {
                    i2 = this.i.a();
                }
                canvas.rotate(90.0f);
                canvas.translate((float) (-getPaddingTop()), (float) (((-i2) * (this.p + width)) + this.p));
                this.O.setSize(height2, width);
                z2 |= this.O.draw(canvas);
                canvas.restoreToCount(save2);
            }
        } else {
            this.N.finish();
            this.O.finish();
        }
        if (z2) {
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
        } catch (Exception unused) {
        }
        if (this.p > 0 && this.q != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            int i2 = scrollX % (this.p + width);
            if (i2 != 0) {
                int i3 = (scrollX - i2) + width;
                this.q.setBounds(i3, 0, this.p + i3, getHeight());
                this.q.draw(canvas);
            }
        }
    }

    public void setPagingEnabled(boolean z2) {
        this.S = z2;
    }

    public boolean beginFakeDrag() {
        if (this.y) {
            return false;
        }
        this.L = true;
        setScrollState(1);
        this.C = 0.0f;
        this.B = 0.0f;
        if (this.G == null) {
            this.G = VelocityTracker.obtain();
        } else {
            this.G.clear();
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, 0.0f, 0.0f, 0);
        this.G.addMovement(obtain);
        obtain.recycle();
        this.M = uptimeMillis;
        return true;
    }

    public void endFakeDrag() {
        if (this.L) {
            VelocityTracker velocityTracker = this.G;
            velocityTracker.computeCurrentVelocity(1000, (float) this.I);
            this.v = true;
            if (Math.abs((int) VelocityTrackerCompat.getYVelocity(velocityTracker, this.E)) <= this.H && Math.abs(this.B - this.C) < ((float) (getWidth() / 3))) {
                setCurrentItemInternal(this.j, true, true);
            } else if (this.C > this.B) {
                setCurrentItemInternal(this.j - 1, true, true);
            } else {
                setCurrentItemInternal(this.j + 1, true, true);
            }
            b();
            this.L = false;
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public void fakeDragBy(float f2) {
        if (this.L) {
            this.C += f2;
            float scrollX = ((float) getScrollX()) - f2;
            int width = getWidth() + this.p;
            float max = (float) Math.max(0, (this.j - 1) * width);
            float min = (float) (Math.min(this.j + 1, this.i.a() - 1) * width);
            if (scrollX < max) {
                scrollX = max;
            } else if (scrollX > min) {
                scrollX = min;
            }
            int i2 = (int) scrollX;
            this.C += scrollX - ((float) i2);
            scrollTo(i2, getScrollY());
            if (this.Q != null) {
                int i3 = i2 / width;
                int i4 = i2 % width;
                this.Q.onPageScrolled(i3, ((float) i4) / ((float) width), i4);
            }
            MotionEvent obtain = MotionEvent.obtain(this.M, SystemClock.uptimeMillis(), 2, this.C, 0.0f, 0);
            this.G.addMovement(obtain);
            obtain.recycle();
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public boolean isFakeDragging() {
        return this.L;
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.E) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.C = MotionEventCompat.getX(motionEvent, i2);
            this.E = MotionEventCompat.getPointerId(motionEvent, i2);
            if (this.G != null) {
                this.G.clear();
            }
        }
    }

    private void b() {
        this.y = false;
        this.z = false;
        if (this.G != null) {
            this.G.recycle();
            this.G = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z2) {
        if (this.u != z2) {
            this.u = z2;
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
            } else if (keyEvent.hasNoModifiers()) {
                return arrowScroll(2);
            } else {
                if (keyEvent.hasModifiers(1)) {
                    return arrowScroll(1);
                }
            }
        }
        return false;
    }

    public boolean arrowScroll(int i2) {
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        boolean z2 = false;
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i2);
        if (findNextFocus == null || findNextFocus == findFocus) {
            if (i2 == 17 || i2 == 1) {
                z2 = pageLeft();
            } else if (i2 == 66 || i2 == 2) {
                z2 = pageRight();
            }
        } else if (i2 == 17) {
            z2 = (findFocus == null || findNextFocus.getLeft() < findFocus.getLeft()) ? findNextFocus.requestFocus() : pageLeft();
        } else if (i2 == 66) {
            z2 = (findFocus == null || findNextFocus.getLeft() > findFocus.getLeft()) ? findNextFocus.requestFocus() : pageRight();
        }
        if (z2) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i2));
        }
        return z2;
    }

    /* access modifiers changed from: package-private */
    public boolean pageLeft() {
        if (this.j <= 0) {
            return false;
        }
        setCurrentItem(this.j - 1, true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean pageRight() {
        if (this.i == null || this.j >= this.i.a() - 1) {
            return false;
        }
        setCurrentItem(this.j + 1, true);
        return true;
    }

    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        ItemInfo infoForChild;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.j) {
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
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.j) {
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
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.j && childAt.requestFocus(i2, rect)) {
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
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.b == this.j && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    private class DataSetObserver implements PagerAdapter.DataSetObserver {
        private DataSetObserver() {
        }

        public void a() {
            ViewPager.this.dataSetChanged();
        }
    }
}
