package com.mi.global.shop.widget.ptr;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;
import com.mi.global.shop.R;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.ptr.util.PtrLocalDisplay;
import in.srain.cube.util.CLog;

public class PtrFrameLayout extends ViewGroup {
    public static boolean DEBUG = false;
    public static final byte PTR_STATUS_COMPLETE = 4;
    public static final byte PTR_STATUS_INIT = 1;
    public static final byte PTR_STATUS_LOADING = 3;
    public static final byte PTR_STATUS_PREPARE = 2;

    /* renamed from: a  reason: collision with root package name */
    private static final int f7248a = 0;
    private static final boolean b = false;
    private static int c = 1;
    private static byte d = 1;
    private static byte e = 2;
    private boolean A;
    private boolean B;
    private MotionEvent C;
    private MotionEvent D;
    private PtrUIHandlerHook E;
    private int F;
    private long G;
    protected final String LOG_TAG;
    private int f;
    private int g;
    private float h;
    private int i;
    private int j;
    private float k;
    private boolean l;
    private boolean m;
    protected View mContent;
    protected int mOffsetToRefresh;
    private View n;
    private PtrUIHandlerHolder o;
    private PtrHandler p;
    private ScrollChecker q;
    private PointF r;
    /* access modifiers changed from: private */
    public int s;
    private int t;
    private int u;
    private int v;
    private byte w;
    private boolean x;
    private boolean y;
    private int z;

    /* access modifiers changed from: protected */
    public void onPositionChange(boolean z2, byte b2, int i2, int i3, float f2, float f3) {
    }

    public PtrFrameLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public PtrFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PtrFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        StringBuilder sb = new StringBuilder();
        sb.append("ptr-frame-");
        int i3 = c + 1;
        c = i3;
        sb.append(i3);
        this.LOG_TAG = sb.toString();
        this.mOffsetToRefresh = 0;
        this.f = 0;
        this.g = 0;
        this.h = 1.7f;
        this.i = 200;
        this.j = 1000;
        this.k = 1.2f;
        this.l = true;
        this.m = false;
        this.o = PtrUIHandlerHolder.b();
        this.r = new PointF();
        this.s = 0;
        this.t = 0;
        this.w = 1;
        this.x = false;
        this.y = false;
        this.z = 0;
        this.A = false;
        this.B = false;
        this.F = 500;
        this.G = 0;
        PtrLocalDisplay.a(getContext());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PtrFrameLayout, 0, 0);
        if (obtainStyledAttributes != null) {
            this.f = obtainStyledAttributes.getResourceId(R.styleable.PtrFrameLayout_ptr_header, this.f);
            this.g = obtainStyledAttributes.getResourceId(R.styleable.PtrFrameLayout_ptr_content, this.g);
            this.h = obtainStyledAttributes.getFloat(R.styleable.PtrFrameLayout_ptr_resistance, this.h);
            this.i = obtainStyledAttributes.getInt(R.styleable.PtrFrameLayout_ptr_duration_to_close, this.i);
            this.j = obtainStyledAttributes.getInt(R.styleable.PtrFrameLayout_ptr_duration_to_close_header, this.j);
            this.k = obtainStyledAttributes.getFloat(R.styleable.PtrFrameLayout_ptr_ratio_of_header_height_to_refresh, this.k);
            this.l = obtainStyledAttributes.getBoolean(R.styleable.PtrFrameLayout_ptr_keep_header_when_refresh, this.l);
            this.m = obtainStyledAttributes.getBoolean(R.styleable.PtrFrameLayout_ptr_pull_to_fresh, this.m);
            obtainStyledAttributes.recycle();
        }
        this.q = new ScrollChecker();
        this.u = ViewConfiguration.get(getContext()).getScaledTouchSlop() * 2;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        int childCount = getChildCount();
        if (childCount <= 2) {
            if (childCount == 2) {
                if (this.f != 0 && this.n == null) {
                    this.n = findViewById(this.f);
                }
                if (this.g != 0 && this.mContent == null) {
                    this.mContent = findViewById(this.g);
                }
                if (this.mContent == null || this.n == null) {
                    View childAt = getChildAt(0);
                    View childAt2 = getChildAt(1);
                    if (childAt instanceof PtrUIHandler) {
                        this.n = childAt;
                        this.mContent = childAt2;
                    } else if (childAt2 instanceof PtrUIHandler) {
                        this.n = childAt2;
                        this.mContent = childAt;
                    } else if (this.mContent == null && this.n == null) {
                        this.n = childAt;
                        this.mContent = childAt2;
                    } else if (this.n == null) {
                        if (this.mContent == childAt) {
                            childAt = childAt2;
                        }
                        this.n = childAt;
                    } else {
                        if (this.n == childAt) {
                            childAt = childAt2;
                        }
                        this.mContent = childAt;
                    }
                }
            } else if (childCount == 1) {
                this.mContent = getChildAt(0);
            } else {
                CustomTextView customTextView = new CustomTextView(getContext());
                customTextView.setClickable(true);
                customTextView.setTextColor(-39424);
                customTextView.setGravity(17);
                customTextView.setTextSize(20.0f);
                customTextView.setText("The content view in PtrFrameLayout is empty. Do you forget to specify its id in xml layout file?");
                this.mContent = customTextView;
                addView(this.mContent);
            }
            super.onFinishInflate();
            return;
        }
        throw new IllegalStateException("PtrFrameLayout only can host 2 elements");
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        boolean z2 = DEBUG;
        if (this.n != null) {
            measureChildWithMargins(this.n, i2, 0, i3, 0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.n.getLayoutParams();
            this.v = this.n.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            this.mOffsetToRefresh = (int) (((float) this.v) * this.k);
            boolean z3 = DEBUG;
        }
        if (this.mContent != null) {
            a(this.mContent, i2, i3);
            boolean z4 = DEBUG;
        }
    }

    private void a(View view, int i2, int i3) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, marginLayoutParams.width), getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin, marginLayoutParams.height));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        a();
    }

    private void a() {
        int i2 = this.s;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        if (this.n != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.n.getLayoutParams();
            int i3 = marginLayoutParams.leftMargin + paddingLeft;
            int i4 = ((marginLayoutParams.topMargin + paddingTop) + i2) - this.v;
            this.n.layout(i3, i4, this.n.getMeasuredWidth() + i3, this.n.getMeasuredHeight() + i4);
            boolean z2 = DEBUG;
        }
        if (this.mContent != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mContent.getLayoutParams();
            int i5 = paddingLeft + marginLayoutParams2.leftMargin;
            int i6 = paddingTop + marginLayoutParams2.topMargin + i2;
            boolean z3 = DEBUG;
            this.mContent.layout(i5, i6, this.mContent.getMeasuredWidth() + i5, this.mContent.getMeasuredHeight() + i6);
        }
    }

    public boolean dispatchTouchEventSupper(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || this.mContent == null || this.n == null) {
            return dispatchTouchEventSupper(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.C = motionEvent;
                this.r.set(motionEvent.getX(), motionEvent.getY());
                this.q.a();
                this.x = true;
                this.A = false;
                if (!this.B || this.s <= 0) {
                    dispatchTouchEventSupper(motionEvent);
                }
                return true;
            case 1:
            case 3:
                this.x = false;
                if (this.s <= 0) {
                    return dispatchTouchEventSupper(motionEvent);
                }
                if (DEBUG) {
                    CLog.b(this.LOG_TAG, "call onRelease when user release");
                }
                a(false);
                return true;
            case 2:
                this.D = motionEvent;
                float x2 = motionEvent.getX() - this.r.x;
                float y2 = (float) ((int) (motionEvent.getY() - this.r.y));
                this.r.set(motionEvent.getX(), motionEvent.getY());
                if (this.y && !this.A && ((Math.abs(x2) > ((float) this.u) || Math.abs(x2) > Math.abs(y2) * 3.0f) && j())) {
                    this.A = true;
                }
                if (this.A) {
                    return dispatchTouchEventSupper(motionEvent);
                }
                boolean z2 = y2 > 0.0f;
                boolean z3 = !z2;
                boolean z4 = this.s > 0;
                if (DEBUG) {
                    CLog.a(this.LOG_TAG, "ACTION_MOVE: offsetY:%s, mCurrentPos: %s, moveUp: %s, canMoveUp: %s, moveDown: %s: canMoveDown: %s", Float.valueOf(y2), Integer.valueOf(this.s), Boolean.valueOf(z3), Boolean.valueOf(z4), Boolean.valueOf(z2), Boolean.valueOf(this.p != null && this.p.a(this, this.mContent, this.n)));
                }
                if (z2 && this.p != null && !this.p.a(this, this.mContent, this.n)) {
                    return dispatchTouchEventSupper(motionEvent);
                }
                if ((z3 && z4) || z2) {
                    double d2 = (double) y2;
                    double d3 = (double) this.h;
                    Double.isNaN(d2);
                    Double.isNaN(d3);
                    a((float) (d2 / d3));
                    return true;
                }
                break;
        }
        return dispatchTouchEventSupper(motionEvent);
    }

    /* access modifiers changed from: private */
    public void a(float f2) {
        if (f2 >= 0.0f || this.s != 0) {
            int i2 = ((int) f2) + this.s;
            if (i2 < 0) {
                if (DEBUG) {
                    CLog.e(this.LOG_TAG, String.format("over top", new Object[0]));
                }
                i2 = 0;
            }
            this.s = i2;
            b();
            this.t = this.s;
        } else if (DEBUG) {
            CLog.e(this.LOG_TAG, String.format("has reached the top", new Object[0]));
        }
    }

    private void b() {
        int i2 = this.s - this.t;
        if (i2 != 0) {
            if (this.t == 0 && this.s != 0 && this.o.a()) {
                if (this.w == 1) {
                    this.w = 2;
                    this.o.onUIRefreshPrepare(this);
                    if (DEBUG) {
                        CLog.c(this.LOG_TAG, "PtrUIHandler: onUIRefreshPrepare, mAutoScrollRefreshTag %s", Integer.valueOf(this.z));
                    }
                }
                if (this.x && this.B) {
                    l();
                }
            }
            if (this.t != 0 && this.s == 0) {
                i();
                if (this.x && this.B) {
                    m();
                }
            }
            if (this.w == 2) {
                if (this.x && this.z == 0 && this.m && this.t < this.mOffsetToRefresh && this.s >= this.mOffsetToRefresh) {
                    g();
                }
                if (this.z == e && this.t < this.v && this.s >= this.v) {
                    g();
                }
            }
            if (DEBUG) {
                CLog.a(this.LOG_TAG, "updatePos: change: %s, current: %s last: %s, top: %s, headerHeight: %s", Integer.valueOf(i2), Integer.valueOf(this.s), Integer.valueOf(this.t), Integer.valueOf(this.mContent.getTop()), Integer.valueOf(this.v));
            }
            this.n.offsetTopAndBottom(i2);
            this.mContent.offsetTopAndBottom(i2);
            invalidate();
            float f2 = 0.0f;
            float f3 = this.v == 0 ? 0.0f : (((float) this.t) * 1.0f) / ((float) this.v);
            if (this.v != 0) {
                f2 = (((float) this.s) * 1.0f) / ((float) this.v);
            }
            if (this.o.a()) {
                this.o.onUIPositionChange(this, this.x, this.w, this.t, this.s, f3, f2);
            }
            onPositionChange(this.x, this.w, this.t, this.s, f3, f2);
        }
    }

    public int getHeaderHeight() {
        return this.v;
    }

    private void a(boolean z2) {
        g();
        if (this.w == 3) {
            if (!this.l) {
                d();
            } else if (this.s > this.v && !z2) {
                this.q.a(this.v, this.i);
            }
        } else if (this.w == 4) {
            b(false);
        } else {
            f();
        }
    }

    public void setRefreshCompleteHook(PtrUIHandlerHook ptrUIHandlerHook) {
        this.E = ptrUIHandlerHook;
        ptrUIHandlerHook.b(new Runnable() {
            public void run() {
                if (PtrFrameLayout.DEBUG) {
                    CLog.b(PtrFrameLayout.this.LOG_TAG, "mRefreshCompleteHook resume.");
                }
                PtrFrameLayout.this.b(true);
            }
        });
    }

    private void c() {
        if (!this.x) {
            this.q.a(0, this.j);
        }
    }

    private void d() {
        c();
    }

    private void e() {
        c();
    }

    private void f() {
        c();
    }

    private boolean g() {
        if (this.w != 2) {
            return false;
        }
        if ((this.s >= this.v && this.z > 0) || this.s >= this.mOffsetToRefresh) {
            this.w = 3;
            h();
        }
        return false;
    }

    private void h() {
        this.G = System.currentTimeMillis();
        if (this.o.a()) {
            this.o.onUIRefreshBegin(this);
            if (DEBUG) {
                CLog.c(this.LOG_TAG, "PtrUIHandler: onUIRefreshBegin");
            }
        }
        if (this.p != null) {
            this.p.a(this);
        }
    }

    private boolean i() {
        if ((this.w != 4 && this.w != 2) || this.s != 0) {
            return false;
        }
        if (this.o.a()) {
            this.o.onUIReset(this);
            if (DEBUG) {
                CLog.c(this.LOG_TAG, "PtrUIHandler: onUIReset");
            }
        }
        this.w = 1;
        this.z = 0;
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPtrScrollAbort() {
        if (this.s > 0 && this.z > 0) {
            if (DEBUG) {
                CLog.b(this.LOG_TAG, "call onRelease after scroll abort");
            }
            a(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onPtrScrollFinish() {
        if (this.s > 0 && this.z > 0) {
            if (DEBUG) {
                CLog.b(this.LOG_TAG, "call onRelease after scroll finish");
            }
            a(true);
        }
    }

    private boolean j() {
        return this.s == 0;
    }

    public final void refreshComplete() {
        if (DEBUG) {
            CLog.c(this.LOG_TAG, "refreshComplete");
        }
        if (this.E != null) {
            this.E.b();
        }
        int currentTimeMillis = (int) (((long) this.F) - (System.currentTimeMillis() - this.G));
        if (currentTimeMillis <= 0) {
            if (DEBUG) {
                CLog.b(this.LOG_TAG, "performRefreshComplete at once");
            }
            k();
            return;
        }
        postDelayed(new Runnable() {
            public void run() {
                PtrFrameLayout.this.k();
            }
        }, (long) currentTimeMillis);
        if (DEBUG) {
            CLog.b(this.LOG_TAG, "performRefreshComplete after delay: %s", Integer.valueOf(currentTimeMillis));
        }
    }

    /* access modifiers changed from: private */
    public void k() {
        this.w = 4;
        if (!this.q.d || this.z <= 0) {
            b(false);
        } else if (DEBUG) {
            CLog.b(this.LOG_TAG, "performRefreshComplete do nothing, scrolling: %s, auto refresh: %s", Boolean.valueOf(this.q.d), Integer.valueOf(this.z));
        }
    }

    /* access modifiers changed from: private */
    public void b(boolean z2) {
        if (this.s == 0 || z2 || this.E == null) {
            if (this.o.a()) {
                if (DEBUG) {
                    CLog.c(this.LOG_TAG, "PtrUIHandler: onUIRefreshComplete");
                }
                this.o.onUIRefreshComplete(this);
            }
            e();
            i();
            return;
        }
        if (DEBUG) {
            CLog.b(this.LOG_TAG, "notifyUIRefreshComplete mRefreshCompleteHook run.");
        }
        this.E.a();
    }

    public void autoRefresh() {
        autoRefresh(true, this.j);
    }

    public void autoRefresh(boolean z2) {
        autoRefresh(z2, this.j);
    }

    public void autoRefresh(boolean z2, int i2) {
        if (this.w == 1) {
            this.z = z2 ? d : e;
            this.w = 2;
            if (this.o.a()) {
                this.o.onUIRefreshPrepare(this);
                if (DEBUG) {
                    CLog.c(this.LOG_TAG, "PtrUIHandler: onUIRefreshPrepare, mAutoScrollRefreshTag %s", Integer.valueOf(this.z));
                }
            }
            this.q.a(this.mOffsetToRefresh, i2);
            if (z2) {
                this.w = 3;
                h();
            }
        }
    }

    public void disableWhenHorizontalMove(boolean z2) {
        this.y = z2;
    }

    public void setLoadingMinTime(int i2) {
        this.F = i2;
    }

    public void setInterceptEventWhileWorking(boolean z2) {
        this.B = z2;
    }

    public View getContentView() {
        return this.mContent;
    }

    public void setPtrHandler(PtrHandler ptrHandler) {
        this.p = ptrHandler;
    }

    public void addPtrUIHandler(PtrUIHandler ptrUIHandler) {
        PtrUIHandlerHolder.a(this.o, ptrUIHandler);
    }

    public void removePtrUIHandler(PtrUIHandler ptrUIHandler) {
        this.o = PtrUIHandlerHolder.b(this.o, ptrUIHandler);
    }

    public float getResistance() {
        return this.h;
    }

    public void setResistance(float f2) {
        this.h = f2;
    }

    public float getDurationToClose() {
        return (float) this.i;
    }

    public void setDurationToClose(int i2) {
        this.i = i2;
    }

    public long getDurationToCloseHeader() {
        return (long) this.j;
    }

    public void setDurationToCloseHeader(int i2) {
        this.j = i2;
    }

    public void setRatioOfHeaderHeightToRefresh(float f2) {
        this.k = f2;
        this.mOffsetToRefresh = (int) (((float) this.v) * this.k);
    }

    public int getOffsetToRefresh() {
        return this.mOffsetToRefresh;
    }

    public void setOffsetToRefresh(int i2) {
        this.mOffsetToRefresh = i2;
    }

    public float getRatioOfHeaderToHeightRefresh() {
        return this.k;
    }

    public boolean isKeepHeaderWhenRefresh() {
        return this.l;
    }

    public void setKeepHeaderWhenRefresh(boolean z2) {
        this.l = z2;
    }

    public boolean isPullToRefresh() {
        return this.m;
    }

    public void setPullToRefresh(boolean z2) {
        this.m = z2;
    }

    public View getHeaderView() {
        return this.n;
    }

    public void setHeaderView(View view) {
        if (!(this.n == null || view == null || this.n == view)) {
            removeView(this.n);
        }
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(new LayoutParams(-1, -2));
        }
        this.n = view;
        addView(view);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    private void l() {
        if (DEBUG) {
            CLog.b(this.LOG_TAG, "send cancel event");
        }
        dispatchTouchEventSupper(MotionEvent.obtain(this.C.getDownTime(), this.C.getEventTime() + ((long) ViewConfiguration.getLongPressTimeout()), 3, this.C.getX(), this.C.getY(), this.C.getMetaState()));
    }

    private void m() {
        if (DEBUG) {
            CLog.b(this.LOG_TAG, "send down event");
        }
        MotionEvent motionEvent = this.D;
        dispatchTouchEventSupper(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), 0, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState()));
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    class ScrollChecker implements Runnable {
        private int b;
        private Scroller c;
        /* access modifiers changed from: private */
        public boolean d = false;
        private int e;
        private int f;

        public ScrollChecker() {
            this.c = new Scroller(PtrFrameLayout.this.getContext());
        }

        public void run() {
            boolean z = !this.c.computeScrollOffset() || this.c.isFinished();
            int currY = this.c.getCurrY();
            int i = currY - this.b;
            if (PtrFrameLayout.DEBUG && i != 0) {
                CLog.a(PtrFrameLayout.this.LOG_TAG, "scroll: %s, start: %s, to: %s, mCurrentPos: %s, current :%s, last: %s, delta: %s", Boolean.valueOf(z), Integer.valueOf(this.e), Integer.valueOf(this.f), Integer.valueOf(PtrFrameLayout.this.s), Integer.valueOf(currY), Integer.valueOf(this.b), Integer.valueOf(i));
            }
            if (!z) {
                this.b = currY;
                PtrFrameLayout.this.a((float) i);
                PtrFrameLayout.this.post(this);
                return;
            }
            b();
        }

        private void b() {
            if (PtrFrameLayout.DEBUG) {
                CLog.a(PtrFrameLayout.this.LOG_TAG, "finish, mCurrentPos:%s", Integer.valueOf(PtrFrameLayout.this.s));
            }
            c();
            PtrFrameLayout.this.onPtrScrollFinish();
        }

        private void c() {
            this.d = false;
            this.b = 0;
            PtrFrameLayout.this.removeCallbacks(this);
        }

        public void a() {
            if (this.d) {
                if (!this.c.isFinished()) {
                    this.c.forceFinished(true);
                }
                PtrFrameLayout.this.onPtrScrollAbort();
                c();
            }
        }

        public void a(int i, int i2) {
            if (PtrFrameLayout.this.s != i) {
                this.e = PtrFrameLayout.this.s;
                this.f = i;
                int i3 = i - this.e;
                if (PtrFrameLayout.DEBUG) {
                    CLog.b(PtrFrameLayout.this.LOG_TAG, "tryToScrollTo: start: %s, distance:%s, to:%s", Integer.valueOf(this.e), Integer.valueOf(i3), Integer.valueOf(i));
                }
                PtrFrameLayout.this.removeCallbacks(this);
                this.b = 0;
                this.c = new Scroller(PtrFrameLayout.this.getContext());
                this.c.startScroll(0, 0, 0, i3, i2);
                PtrFrameLayout.this.post(this);
                this.d = true;
            }
        }
    }
}
