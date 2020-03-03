package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.scrollview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.OverScroller;
import android.widget.ScrollView;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import com.facebook.react.views.scroll.FpsListener;
import com.facebook.react.views.scroll.OnScrollDispatchHelper;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.facebook.react.views.scroll.VelocityHelper;
import com.facebook.react.views.view.ReactViewBackgroundManager;
import java.lang.reflect.Field;
import javax.annotation.Nullable;

public class MIOTScrollView extends ScrollView implements View.OnLayoutChangeListener, ViewGroup.OnHierarchyChangeListener, ReactClippingViewGroup {
    public static final String HIDDEN = "hidden";
    public static final String VISIBLE = "visible";
    private static boolean b = false;
    @Nullable
    private static Field c;
    private ValueAnimator A;
    /* access modifiers changed from: private */
    public boolean B;
    /* access modifiers changed from: private */
    public Runnable C;
    private float D;

    /* renamed from: a  reason: collision with root package name */
    private final ReactViewBackgroundManager f17626a;
    private final OnScrollDispatchHelper d;
    @Nullable
    private final OverScroller e;
    private final VelocityHelper f;
    private final Rect g;
    @Nullable
    private Rect h;
    @Nullable
    private String i;
    private boolean j;
    private boolean k;
    private boolean l;
    /* access modifiers changed from: private */
    public boolean m;
    @Nullable
    private String n;
    @Nullable
    private FpsListener o;
    @Nullable
    private Drawable p;
    private int q;
    private float r;
    /* access modifiers changed from: private */
    public boolean s;
    private View t;
    private OnScrollListener u;
    private int v;
    private int w;
    /* access modifiers changed from: private */
    public int x;
    private float y;
    private boolean z;

    public MIOTScrollView(ReactContext reactContext) {
        this(reactContext, (FpsListener) null);
    }

    public MIOTScrollView(ReactContext reactContext, @Nullable FpsListener fpsListener) {
        super(reactContext);
        this.d = new OnScrollDispatchHelper();
        this.f = new VelocityHelper();
        this.g = new Rect();
        this.i = "hidden";
        this.l = true;
        this.q = 0;
        this.r = 0.985f;
        this.v = 0;
        this.w = 250;
        this.x = 0;
        this.B = false;
        this.D = 0.2f;
        this.o = fpsListener;
        this.f17626a = new ReactViewBackgroundManager(this);
        this.e = getOverScrollerFromParent();
        setOnHierarchyChangeListener(this);
        setScrollBarStyle(33554432);
    }

    @Nullable
    private OverScroller getOverScrollerFromParent() {
        if (!b) {
            b = true;
            try {
                c = ScrollView.class.getDeclaredField("mScroller");
                c.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.w(ReactConstants.TAG, "Failed to get mScroller field for ScrollView! This app will exhibit the bounce-back scrolling bug :(");
            }
        }
        if (c == null) {
            return null;
        }
        try {
            Object obj = c.get(this);
            if (obj instanceof OverScroller) {
                return (OverScroller) obj;
            }
            Log.w(ReactConstants.TAG, "Failed to cast mScroller field in ScrollView (probably due to OEM changes to AOSP)! This app will exhibit the bounce-back scrolling bug :(");
            return null;
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Failed to get mScroller from ScrollView!", e2);
        }
    }

    public void setSendMomentumEvents(boolean z2) {
        this.m = z2;
    }

    public void setScrollPerfTag(@Nullable String str) {
        this.n = str;
    }

    public void setScrollEnabled(boolean z2) {
        this.l = z2;
    }

    public void setPagingEnabled(boolean z2) {
        this.s = z2;
    }

    public void setDecelerationRate(float f2) {
        this.r = f2;
        if (this.e != null) {
            this.e.setFriction(1.0f - this.r);
        }
    }

    public void setScrollListener(OnScrollListener onScrollListener) {
        this.u = onScrollListener;
    }

    public boolean isScrolledHeader() {
        return getSnapHeight() == getScrollY();
    }

    public boolean isScrollSnap() {
        return getSnapHeight() <= getScrollY();
    }

    public void snapToHeader(boolean z2) {
        int snapHeight = getSnapHeight();
        float f2 = (float) snapHeight;
        int i2 = (int) (this.D * f2);
        int i3 = (int) ((1.0f - this.D) * f2);
        int min = Math.min(Math.max(0, getScrollY()), getMaxScrollY());
        if (getScrollY() >= snapHeight ? min < snapHeight : min > snapHeight) {
            min = snapHeight;
        }
        if ((min >= i3 && min < snapHeight) || (min > i2 && min < i3 && z2)) {
            a(snapHeight);
        } else if ((min > 0 && min <= i2) || (min > i2 && min < i3 && !z2)) {
            a(0);
        }
    }

    public void flashScrollIndicators() {
        awakenScrollBars();
    }

    public void setOverflow(String str) {
        this.i = str;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        MeasureSpecAssertions.assertExplicitMeasureSpec(i2, i3);
        setMeasuredDimension(View.MeasureSpec.getSize(i2), View.MeasureSpec.getSize(i3));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        scrollTo(getScrollX(), getScrollY());
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.k) {
            updateClippingRect();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.k) {
            updateClippingRect();
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        if (this.u != null) {
            this.u.onScrollChange(i2, Math.min(i3, getSnapHeight()));
        }
        if (this.d.onScrollChanged(i2, i3)) {
            if (this.k) {
                updateClippingRect();
            }
            ReactScrollViewHelper.emitScrollEvent(this, this.d.getXFlingVelocity(), this.d.getYFlingVelocity());
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.l || this.B) {
            return false;
        }
        if ((motionEvent.getAction() & 255) == 0) {
            this.x = 0;
            this.y = motionEvent.getY();
        }
        try {
            if (super.onInterceptTouchEvent(motionEvent)) {
                NativeGestureUtil.notifyNativeGestureStarted(this, motionEvent);
                ReactScrollViewHelper.emitScrollBeginDragEvent(this);
                this.j = true;
                return true;
            }
        } catch (IllegalArgumentException e2) {
            Log.w(ReactConstants.TAG, "Error intercepting touch event.", e2);
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z2 = false;
        if (!this.l || this.B) {
            return false;
        }
        this.f.calculateVelocity(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.x = 0;
            this.y = motionEvent.getY();
        } else if (action == 1 && this.j) {
            float xVelocity = this.f.getXVelocity();
            float yVelocity = this.f.getYVelocity();
            ReactScrollViewHelper.emitScrollEndDragEvent(this, xVelocity, yVelocity);
            this.j = false;
            if (motionEvent.getY() - this.y < 0.0f) {
                z2 = true;
            }
            this.z = z2;
            if (this.C != null) {
                removeCallbacks(this.C);
                this.C = null;
            }
            a(Math.round(xVelocity), Math.round(yVelocity));
        }
        return super.onTouchEvent(motionEvent);
    }

    private void a(int i2) {
        int scrollY = getScrollY();
        a();
        this.A = ValueAnimator.ofInt(new int[]{scrollY, i2}).setDuration((long) this.w);
        this.A.setInterpolator(new EasingOutInterpolator());
        this.A.addListener(new AnimatorListenerAdapter() {
            public void onAnimationCancel(Animator animator) {
                boolean unused = MIOTScrollView.this.B = false;
            }

            public void onAnimationEnd(Animator animator) {
                boolean unused = MIOTScrollView.this.B = false;
            }

            public void onAnimationStart(Animator animator) {
                boolean unused = MIOTScrollView.this.B = true;
            }
        });
        this.A.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                MIOTScrollView.this.a(valueAnimator);
            }
        });
        this.A.start();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(ValueAnimator valueAnimator) {
        scrollTo(0, ((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    private void a() {
        if (this.A != null) {
            if (this.A.isRunning()) {
                this.A.cancel();
            }
            this.A.removeAllUpdateListeners();
            this.A.removeAllListeners();
        }
    }

    private void a(int i2, int i3) {
        if ((this.m || this.s) && this.C == null) {
            if (this.m) {
                b();
                ReactScrollViewHelper.emitScrollMomentumBeginEvent(this, i2, i3);
            }
            this.C = new Runnable() {
                private boolean b = false;

                public void run() {
                    int scrollY = MIOTScrollView.this.getScrollY();
                    if (MIOTScrollView.this.x != scrollY) {
                        int unused = MIOTScrollView.this.x = scrollY;
                        ViewCompat.postOnAnimationDelayed(MIOTScrollView.this, this, 20);
                    } else if (!MIOTScrollView.this.s || this.b) {
                        if (MIOTScrollView.this.m) {
                            ReactScrollViewHelper.emitScrollMomentumEndEvent(MIOTScrollView.this);
                        }
                        Runnable unused2 = MIOTScrollView.this.C = null;
                        MIOTScrollView.this.c();
                    } else {
                        this.b = true;
                        MIOTScrollView.this.b(0);
                        ViewCompat.postOnAnimationDelayed(MIOTScrollView.this, this, 20);
                    }
                }
            };
            ViewCompat.postOnAnimationDelayed(this, this.C, 20);
        }
    }

    public void fling(int i2) {
        int abs = (int) (((float) Math.abs(i2)) * Math.signum(this.d.getYFlingVelocity()));
        if (this.s) {
            b(abs);
        } else if (this.e != null) {
            int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
            this.e.fling(getScrollX(), getScrollY(), 0, abs, 0, 0, 0, Integer.MAX_VALUE, 0, height / 2);
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            super.fling(abs);
        }
        a(0, abs);
    }

    /* access modifiers changed from: private */
    public void b(int i2) {
        int i3;
        if (getChildCount() > 0) {
            int snapHeight = getSnapHeight();
            int c2 = c(i2);
            float f2 = (float) snapHeight;
            int i4 = (int) (this.D * f2);
            int i5 = (int) ((1.0f - this.D) * f2);
            int min = Math.min(Math.max(0, c2), getMaxScrollY());
            int i6 = (getScrollY() >= snapHeight ? min >= snapHeight : min <= snapHeight) ? min : snapHeight;
            if ((i6 >= i5 && i6 < snapHeight) || (i6 > i4 && i6 < i5 && this.z)) {
                a(snapHeight);
            } else if ((i6 > 0 && i6 <= i4) || (i6 > i4 && i6 < i5 && !this.z)) {
                a(0);
            } else if (this.e != null) {
                OverScroller overScroller = this.e;
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                if (i2 != 0) {
                    i3 = i2;
                } else {
                    i3 = i6 - getScrollY();
                }
                overScroller.fling(scrollX, scrollY, 0, i3, 0, 0, i6, i6, 0, 0);
                postInvalidateOnAnimation();
            } else {
                smoothScrollTo(getScrollX(), i6);
            }
        }
    }

    private int c(int i2) {
        OverScroller overScroller = new OverScroller(getContext());
        overScroller.setFriction(1.0f - this.r);
        int maxScrollY = getMaxScrollY();
        int height = ((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2;
        overScroller.fling(getScrollX(), getScrollY(), 0, i2, 0, 0, 0, maxScrollY, 0, height);
        return overScroller.getFinalY();
    }

    private void b() {
        if (d()) {
            Assertions.assertNotNull(this.o);
            Assertions.assertNotNull(this.n);
            this.o.enable(this.n);
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (d()) {
            Assertions.assertNotNull(this.o);
            Assertions.assertNotNull(this.n);
            this.o.disable(this.n);
        }
    }

    private boolean d() {
        return (this.o == null || this.n == null || this.n.isEmpty()) ? false : true;
    }

    public void setRemoveClippedSubviews(boolean z2) {
        if (z2 && this.h == null) {
            this.h = new Rect();
        }
        this.k = z2;
        updateClippingRect();
    }

    public boolean getRemoveClippedSubviews() {
        return this.k;
    }

    public void updateClippingRect() {
        if (this.k) {
            Assertions.assertNotNull(this.h);
            ReactClippingViewGroupHelper.calculateClippingRect(this, this.h);
            View childAt = getChildAt(0);
            if (childAt instanceof ReactClippingViewGroup) {
                ((ReactClippingViewGroup) childAt).updateClippingRect();
            }
        }
    }

    public void getClippingRect(Rect rect) {
        rect.set((Rect) Assertions.assertNotNull(this.h));
    }

    private int getMaxScrollY() {
        return Math.max(0, this.t.getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
    }

    public void draw(Canvas canvas) {
        char c2 = 0;
        if (this.q != 0) {
            View childAt = getChildAt(0);
            if (!(this.p == null || childAt == null || childAt.getBottom() >= getHeight())) {
                this.p.setBounds(0, childAt.getBottom(), getWidth(), getHeight());
                this.p.draw(canvas);
            }
        }
        getDrawingRect(this.g);
        String str = this.i;
        if (str.hashCode() != 466743410 || !str.equals("visible")) {
            c2 = 65535;
        }
        if (c2 != 0) {
            canvas.clipRect(this.g);
        }
        super.draw(canvas);
    }

    public void setEndFillColor(int i2) {
        if (i2 != this.q) {
            this.q = i2;
            this.p = new ColorDrawable(this.q);
        }
    }

    /* access modifiers changed from: protected */
    public void onOverScrolled(int i2, int i3, boolean z2, boolean z3) {
        int maxScrollY;
        if (this.e != null && !this.e.isFinished() && this.e.getCurrY() != this.e.getFinalY() && i3 >= (maxScrollY = getMaxScrollY())) {
            this.e.abortAnimation();
            i3 = maxScrollY;
        }
        super.onOverScrolled(i2, i3, z2, z3);
    }

    public void onChildViewAdded(View view, View view2) {
        this.t = view2;
        this.t.addOnLayoutChangeListener(this);
    }

    public void onChildViewRemoved(View view, View view2) {
        this.t.removeOnLayoutChangeListener(this);
        this.t = null;
    }

    public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        int maxScrollY;
        if (this.t != null && getScrollY() > (maxScrollY = getMaxScrollY())) {
            scrollTo(getScrollX(), maxScrollY);
        }
    }

    private int getSnapHeight() {
        if (this.v != 0 || getChildCount() <= 0) {
            return this.v;
        }
        return getChildAt(0).getHeight();
    }

    public void setOffsetPercent(float f2) {
        this.D = Math.min(Math.max(f2, 0.0f), 0.5f);
    }

    public void setSnapHeight(int i2) {
        this.v = i2;
    }

    public void setScrollYDuration(int i2) {
        this.w = i2;
    }

    public void setBackgroundColor(int i2) {
        this.f17626a.setBackgroundColor(i2);
    }

    public void setBorderWidth(int i2, float f2) {
        this.f17626a.setBorderWidth(i2, f2);
    }

    public void setBorderColor(int i2, float f2, float f3) {
        this.f17626a.setBorderColor(i2, f2, f3);
    }

    public void setBorderRadius(float f2) {
        this.f17626a.setBorderRadius(f2);
    }

    public void setBorderRadius(float f2, int i2) {
        this.f17626a.setBorderRadius(f2, i2);
    }

    public void setBorderStyle(@Nullable String str) {
        this.f17626a.setBorderStyle(str);
    }
}
