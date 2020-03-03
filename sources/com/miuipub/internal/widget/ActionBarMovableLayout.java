package com.miuipub.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.OverScroller;
import miuipub.v6.R;

public class ActionBarMovableLayout extends ActionBarOverlayLayout {
    public static final int DEFAULT_SPRING_BACK_DURATION = 800;
    public static final int STATE_DOWN = 1;
    public static final int STATE_UNKNOWN = -1;
    public static final int STATE_UP = 0;

    /* renamed from: a  reason: collision with root package name */
    private static final String f8325a = "ActionBarMovableLayout";
    private static final boolean b = false;
    private View c;
    private OverScroller d;
    private int e;
    private boolean f;
    private float g;
    private float h;
    private int i;
    private int j = -1;
    private final int k;
    private final int l;
    private final int m;
    private int n = -1;
    private int o;
    private int p = -1;
    private int q;
    private int r = 8;
    private boolean s;
    private boolean t = true;
    private boolean u;
    private VelocityTracker v;
    private OnScrollListener w;

    public interface OnScrollListener {
        void a();

        void a(float f, int i);

        void a(int i, float f);

        void b();

        boolean c();
    }

    /* access modifiers changed from: protected */
    public int computeVerticalScrollExtent() {
        return 0;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
    }

    public ActionBarMovableLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.V6_ActionBarMovableLayout, R.attr.v6_actionBarMovableLayoutStyle, 0);
        this.o = obtainStyledAttributes.getDimensionPixelSize(R.styleable.V6_ActionBarMovableLayout_v6_overScrollRange, 0);
        this.n = obtainStyledAttributes.getDimensionPixelSize(R.styleable.V6_ActionBarMovableLayout_v6_scrollRange, -1);
        this.p = obtainStyledAttributes.getDimensionPixelSize(R.styleable.V6_ActionBarMovableLayout_v6_scrollStart, -1);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.k = viewConfiguration.getScaledTouchSlop();
        this.d = new OverScroller(context);
        this.l = viewConfiguration.getScaledMinimumFlingVelocity();
        this.m = viewConfiguration.getScaledMaximumFlingVelocity();
        setOverScrollMode(0);
        obtainStyledAttributes.recycle();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        View findViewById = findViewById(R.id.content_mask);
        if (findViewById != null && findViewById.getVisibility() == 0) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 2 && this.f) {
            return true;
        }
        int i2 = action & 255;
        if (i2 != 6) {
            switch (i2) {
                case 0:
                    this.g = motionEvent.getY();
                    this.h = motionEvent.getX();
                    this.e = motionEvent.getPointerId(0);
                    a();
                    this.v.addMovement(motionEvent);
                    this.d.forceFinished(true);
                    break;
                case 1:
                case 3:
                    this.f = false;
                    this.e = -1;
                    c();
                    onScrollEnd();
                    break;
                case 2:
                    if (shouldStartScroll(motionEvent)) {
                        this.f = true;
                        b();
                        this.v.addMovement(motionEvent);
                        onScrollBegin();
                        break;
                    }
                    break;
            }
        } else {
            a(motionEvent);
        }
        return this.f;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        b();
        this.v.addMovement(motionEvent2);
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.g = motionEvent.getY();
                this.e = motionEvent2.getPointerId(0);
                break;
            case 1:
            case 3:
                if (this.f) {
                    this.f = false;
                    this.e = -1;
                    int computeVerticalVelocity = computeVerticalVelocity();
                    if (Math.abs(computeVerticalVelocity) <= this.l) {
                        if (!this.d.springBack(0, this.i, 0, 0, 0, getScrollRange())) {
                            springBack();
                            break;
                        } else {
                            invalidate();
                            break;
                        }
                    } else {
                        fling(computeVerticalVelocity);
                        break;
                    }
                }
                break;
            case 2:
                if (!this.f) {
                    if (shouldStartScroll(motionEvent)) {
                        this.f = true;
                        b();
                        this.v.addMovement(motionEvent2);
                        onScrollBegin();
                        break;
                    }
                } else {
                    int findPointerIndex = motionEvent2.findPointerIndex(this.e);
                    if (findPointerIndex != -1) {
                        float y = motionEvent2.getY(findPointerIndex);
                        boolean overScrollBy = overScrollBy(0, (int) (y - this.g), 0, this.i, 0, getScrollRange(), 0, getOverScrollDistance(), true);
                        this.g = y;
                        if (overScrollBy) {
                            if (this.i == 0) {
                                this.f = false;
                                this.e = -1;
                                motionEvent2.setAction(0);
                                dispatchTouchEvent(motionEvent);
                            }
                            this.v.clear();
                            break;
                        }
                    } else {
                        return false;
                    }
                }
                break;
            case 5:
                int actionIndex = motionEvent.getActionIndex();
                this.g = (float) ((int) motionEvent2.getY(actionIndex));
                this.e = motionEvent2.getPointerId(actionIndex);
                break;
            case 6:
                a(motionEvent);
                this.g = (float) ((int) motionEvent2.getY(motionEvent2.findPointerIndex(this.e)));
                break;
        }
        return true;
    }

    public void computeScroll() {
        if (this.d.computeScrollOffset()) {
            int i2 = this.i;
            int currY = this.d.getCurrY();
            if (i2 != currY) {
                overScrollBy(0, currY - i2, 0, this.i, 0, getScrollRange(), 0, getOverScrollDistance(), true);
            }
            postInvalidateOnAnimation();
        } else if (this.u) {
            springBack();
            this.u = false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean overScrollBy(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z) {
        int overScrollMode = getOverScrollMode();
        boolean z2 = true;
        int i10 = i3 + i5;
        if (!(overScrollMode == 0 || (overScrollMode == 1 && (computeVerticalScrollRange() > computeVerticalScrollExtent())))) {
            i9 = 0;
        }
        int i11 = i9 + i7;
        if (i10 <= i11) {
            if (i10 < 0) {
                i11 = 0;
            } else {
                i11 = i10;
                z2 = false;
            }
        }
        onOverScrolled(0, i11, false, z2);
        return z2;
    }

    /* access modifiers changed from: protected */
    public void onOverScrolled(int i2, int i3, boolean z, boolean z2) {
        onScroll((float) i3);
        this.i = i3;
        if (this.i == 0 && z2) {
            int computeVerticalVelocity = computeVerticalVelocity();
            if (Math.abs(computeVerticalVelocity) > this.l * 2 && this.w != null) {
                this.w.a(((float) (-computeVerticalVelocity)) * 0.2f, 500);
            }
        }
    }

    public void setInitialMotionY(int i2) {
        this.p = i2;
    }

    public void setOverScrollDistance(int i2) {
        this.o = i2;
    }

    public int getOverScrollDistance() {
        return this.o;
    }

    public void setScrollRange(int i2) {
        this.n = i2;
    }

    public int getScrollRange() {
        return this.n;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.w = onScrollListener;
    }

    public void setScrollStart(int i2) {
        this.q = i2;
    }

    public int getScrollStart() {
        return this.q;
    }

    public void setSpringBackEnabled(boolean z) {
        this.t = z;
    }

    private boolean a(View view, int i2, int i3) {
        if (view == null) {
            return false;
        }
        int y = (int) view.getY();
        int x = (int) view.getX();
        int y2 = (int) (view.getY() + ((float) view.getHeight()));
        int x2 = (int) (view.getX() + ((float) view.getWidth()));
        if (view == this.c) {
            int top = this.mActionBarTop.getTop();
            y += top;
            y2 += top;
        }
        if (i3 < y || i3 >= y2 || i2 < x || i2 >= x2) {
            return false;
        }
        return true;
    }

    private void a() {
        if (this.v == null) {
            this.v = VelocityTracker.obtain();
        } else {
            this.v.clear();
        }
    }

    private void b() {
        if (this.v == null) {
            this.v = VelocityTracker.obtain();
        }
    }

    private void c() {
        if (this.v != null) {
            this.v.recycle();
            this.v = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureTabScrollView() {
        this.c = this.mActionBarTop.getTabContainer();
    }

    /* access modifiers changed from: protected */
    public void measureChildWithMargins(View view, int i2, int i3, int i4, int i5) {
        if (view != this.mContentView) {
            super.measureChildWithMargins(view, i2, i3, i4, i5);
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, marginLayoutParams.width), getChildMeasureSpec(i4, ((((((getPaddingTop() + getPaddingBottom()) + marginLayoutParams.bottomMargin) + this.mActionBarView.getMeasuredHeight()) + ((ViewGroup.MarginLayoutParams) this.mActionBarView.getLayoutParams()).topMargin) - getScrollRange()) - getOverScrollDistance()) - this.q, marginLayoutParams.height));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        boolean z2 = !this.s || d();
        if (!this.s) {
            if (this.p < 0) {
                this.p = this.n;
            }
            this.i = this.p;
            this.s = true;
        }
        if (z2) {
            applyTranslationY((float) this.i);
        }
    }

    /* access modifiers changed from: protected */
    public int computeVerticalScrollRange() {
        return getScrollRange();
    }

    /* access modifiers changed from: protected */
    public int computeVerticalVelocity() {
        VelocityTracker velocityTracker = this.v;
        velocityTracker.computeCurrentVelocity(1000, (float) this.m);
        return (int) velocityTracker.getYVelocity(this.e);
    }

    /* access modifiers changed from: protected */
    public void fling(int i2) {
        int overScrollDistance = getOverScrollDistance();
        this.d.fling(0, this.i, 0, i2, 0, 0, 0, getScrollRange(), 0, overScrollDistance);
        this.u = true;
        postInvalidate();
    }

    /* access modifiers changed from: protected */
    public boolean shouldStartScroll(MotionEvent motionEvent) {
        int i2 = this.e;
        int i3 = 0;
        if (i2 == -1) {
            return false;
        }
        int findPointerIndex = motionEvent.findPointerIndex(i2);
        if (findPointerIndex == -1) {
            Log.w(f8325a, "invalid pointer index");
            return false;
        }
        float x = motionEvent.getX(findPointerIndex);
        float y = motionEvent.getY(findPointerIndex);
        int i4 = (int) (y - this.g);
        int abs = Math.abs(i4);
        int i5 = (int) x;
        int i6 = (int) y;
        boolean z = (a(this.mContentView, i5, i6) || a(this.c, i5, i6)) && abs > this.k && abs > ((int) Math.abs(x - this.h)) && (this.i != 0 ? i4 <= 0 || this.i < getOverScrollDistance() || this.w == null || !this.w.c() : i4 >= 0 && (this.w == null || !this.w.c()));
        if (z) {
            this.g = y;
            this.h = x;
            if (i4 > 0) {
                i3 = 1;
            }
            this.j = i3;
            ViewParent parent = getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public void onScrollBegin() {
        if (this.w != null) {
            this.w.a();
        }
    }

    /* access modifiers changed from: protected */
    public void onScroll(float f2) {
        applyTranslationY(f2);
        if (this.w != null) {
            this.w.a(this.j, f2 / ((float) this.n));
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollEnd() {
        this.j = -1;
        if (this.w != null) {
            this.w.b();
        }
    }

    /* access modifiers changed from: protected */
    public float motionToTranslation(float f2) {
        float f3 = ((((float) (-this.o)) + f2) - ((float) this.n)) - ((float) this.q);
        ensureTabScrollView();
        return (this.c == null || this.c.getVisibility() != 0) ? f3 : f3 - ((float) this.c.getHeight());
    }

    /* access modifiers changed from: protected */
    public void applyTranslationY(float f2) {
        float motionToTranslation = motionToTranslation(f2);
        this.mContentView.setTranslationY(motionToTranslation);
        ensureTabScrollView();
        if (this.c != null) {
            this.c.setTranslationY(motionToTranslation);
        }
    }

    /* access modifiers changed from: protected */
    public void springBack() {
        if (this.t) {
            int scrollRange = getScrollRange();
            this.d.startScroll(0, this.i, 0, this.i > scrollRange / 2 ? scrollRange - this.i : -this.i, 800);
            postInvalidateOnAnimation();
        }
    }

    private void a(MotionEvent motionEvent) {
        int action = (motionEvent.getAction() & 65280) >> 8;
        if (motionEvent.getPointerId(action) == this.e) {
            int i2 = action == 0 ? 1 : 0;
            this.g = (float) ((int) motionEvent.getY(i2));
            this.e = motionEvent.getPointerId(i2);
            if (this.v != null) {
                this.v.clear();
            }
        }
    }

    private boolean d() {
        int visibility;
        ensureTabScrollView();
        if (this.c == null || (visibility = this.c.getVisibility()) == this.r) {
            return false;
        }
        this.r = visibility;
        return true;
    }

    public void setMotionY(int i2) {
        this.i = i2;
        onScroll((float) i2);
    }
}
