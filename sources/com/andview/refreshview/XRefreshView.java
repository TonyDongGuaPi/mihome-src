package com.andview.refreshview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import com.andview.refreshview.callback.IFooterCallBack;
import com.andview.refreshview.callback.IHeaderCallBack;
import com.andview.refreshview.listener.OnBottomLoadMoreTime;
import com.andview.refreshview.listener.OnTopRefreshTime;
import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.andview.refreshview.utils.LogUtils;
import com.andview.refreshview.utils.Utils;
import java.util.Calendar;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class XRefreshView extends LinearLayout {
    private boolean A;
    private IHeaderCallBack B;
    private IFooterCallBack C;
    private int D;
    private XRefreshViewState E;
    private boolean F;
    private boolean G;
    /* access modifiers changed from: private */
    public boolean H;
    private boolean I;
    private boolean J;
    /* access modifiers changed from: private */
    public boolean K;
    /* access modifiers changed from: private */
    public boolean L;
    private int M;
    private boolean N;
    /* access modifiers changed from: private */
    public int O;
    private final CopyOnWriteArrayList<TouchLifeCycle> P;
    /* access modifiers changed from: private */
    public boolean Q;
    private boolean R;
    /* access modifiers changed from: private */
    public boolean S;
    /* access modifiers changed from: private */
    public long T;
    private int U;
    private ScrollRunner V;
    private View W;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public View f4769a;
    private View aa;
    /* access modifiers changed from: private */
    public int ab;
    private int b;
    private int c;
    private int d;
    private boolean e;
    private float f;
    private XRefreshViewListener g;
    private View h;
    private boolean i;
    /* access modifiers changed from: private */
    public boolean j;
    private boolean k;
    private int l;
    /* access modifiers changed from: private */
    public XRefreshContentView m;
    protected int mInitScrollY;
    public boolean mPullLoading;
    public boolean mPullRefreshing;
    private boolean n;
    private boolean o;
    private int p;
    private int q;
    /* access modifiers changed from: private */
    public XRefreshHolder r;
    private MotionEvent s;
    private boolean t;
    private boolean u;
    /* access modifiers changed from: private */
    public Scroller v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    public static class SimpleXRefreshListener implements XRefreshViewListener {
        @Deprecated
        public void a() {
        }

        public void a(double d, int i) {
        }

        public void a(float f) {
        }

        public void a(boolean z) {
        }

        public void b(boolean z) {
        }
    }

    interface TouchLifeCycle {
        void a(MotionEvent motionEvent);
    }

    public interface XRefreshViewListener {
        @Deprecated
        void a();

        void a(double d, int i);

        void a(float f);

        void a(boolean z);

        void b(boolean z);
    }

    public XRefreshView(Context context) {
        this(context, (AttributeSet) null);
    }

    public XRefreshView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mInitScrollY = 0;
        this.c = -1;
        this.d = -1;
        this.e = true;
        this.mPullRefreshing = false;
        this.f = 1.8f;
        this.j = false;
        this.k = true;
        this.n = true;
        this.o = true;
        this.t = false;
        this.u = false;
        this.w = false;
        this.x = false;
        this.y = true;
        this.z = true;
        this.A = false;
        this.E = null;
        this.F = false;
        this.G = false;
        this.H = true;
        this.I = true;
        this.J = true;
        this.K = false;
        this.L = false;
        this.N = false;
        this.P = new CopyOnWriteArrayList<>();
        this.Q = false;
        this.R = true;
        this.S = false;
        this.T = -1;
        this.U = 300;
        this.V = new ScrollRunner() {
            public void run() {
                if (XRefreshView.this.v.computeScrollOffset()) {
                    int i = XRefreshView.this.r.f4768a;
                    int currY = XRefreshView.this.v.getCurrY();
                    int i2 = currY - i;
                    XRefreshView.this.moveView(i2);
                    XRefreshView.this.f4769a.getLocationInWindow(new int[2]);
                    LogUtils.a("currentY=" + currY + ";mHolder.mOffsetY=" + XRefreshView.this.r.f4768a);
                    if (XRefreshView.this.H && XRefreshView.this.r.f4768a == 0 && XRefreshView.this.Q && XRefreshView.this.m != null && XRefreshView.this.m.k()) {
                        boolean unused = XRefreshView.this.Q = false;
                        XRefreshView.this.m.a(false, (BaseRecyclerAdapter) null, (RecyclerView.LayoutManager) null);
                    }
                    XRefreshView.this.post(this);
                    if (this.f4760a) {
                        XRefreshView.this.b(i2);
                        return;
                    }
                    return;
                }
                int currY2 = XRefreshView.this.v.getCurrY();
                if (XRefreshView.this.r.f4768a == 0) {
                    XRefreshView.this.enablePullUp(true);
                    boolean unused2 = XRefreshView.this.S = false;
                    this.f4760a = false;
                } else if (XRefreshView.this.S && !XRefreshView.this.mPullLoading && !XRefreshView.this.mPullRefreshing) {
                    XRefreshView.this.startScroll(-currY2, Utils.a(currY2, XRefreshView.this.getHeight()));
                }
            }
        };
        this.ab = 0;
        setClickable(true);
        setLongClickable(true);
        this.m = new XRefreshContentView();
        this.r = new XRefreshHolder();
        this.v = new Scroller(getContext(), new LinearInterpolator());
        a(context, attributeSet);
        setOrientation(1);
    }

    public void setOnTopRefreshTime(OnTopRefreshTime onTopRefreshTime) {
        this.m.a(onTopRefreshTime);
    }

    public void setOnBottomLoadMoreTime(OnBottomLoadMoreTime onBottomLoadMoreTime) {
        this.m.a(onBottomLoadMoreTime);
    }

    public boolean needAddFooterView() {
        return !this.m.r();
    }

    public void setMoveForHorizontal(boolean z2) {
        this.x = z2;
    }

    @Deprecated
    public void setSilenceLoadMore() {
        this.m.a(true);
        setPullLoadEnable(false);
    }

    public void setSilenceLoadMore(boolean z2) {
        if (z2) {
            this.m.a(true);
            setPullLoadEnable(false);
            return;
        }
        this.m.a(false);
    }

    public void notifyLayoutManagerChanged() {
        this.m.c();
        this.m.e();
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.XRefreshView, 0, 0);
            try {
                this.n = obtainStyledAttributes.getBoolean(R.styleable.XRefreshView_isHeightMatchParent, true);
                this.o = obtainStyledAttributes.getBoolean(R.styleable.XRefreshView_isHeightMatchParent, true);
                this.j = obtainStyledAttributes.getBoolean(R.styleable.XRefreshView_autoRefresh, false);
                this.k = obtainStyledAttributes.getBoolean(R.styleable.XRefreshView_autoLoadMore, true);
            } catch (Exception e2) {
                e2.printStackTrace();
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
            obtainStyledAttributes.recycle();
        }
        a();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                boolean unused = XRefreshView.this.K = true;
                if (XRefreshView.this.j || XRefreshView.this.L) {
                    XRefreshView.this.startRefresh();
                }
                XRefreshView.this.setHeadMoveLargestDistence(XRefreshView.this.O);
                XRefreshView.this.d();
                XRefreshView.this.e();
                if (XRefreshView.this.ab == 1) {
                    XRefreshView.this.enableEmptyView(true);
                    int unused2 = XRefreshView.this.ab = 0;
                }
                XRefreshView.this.removeViewTreeObserver(this);
            }
        });
        this.q = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    private void a() {
        if (this.f4769a == null) {
            this.f4769a = new XRefreshViewHeader(getContext());
        }
        b();
    }

    private void b() {
        if (indexOfChild(this.f4769a) == -1) {
            Utils.a(this.f4769a);
            addView(this.f4769a, 0);
            this.B = (IHeaderCallBack) this.f4769a;
            n();
            i();
        }
    }

    private void c() {
        if (indexOfChild(this.h) == -1) {
            if (needAddFooterView()) {
                Utils.a(this.h);
                try {
                    addView(this.h, 2);
                } catch (IndexOutOfBoundsException unused) {
                    new RuntimeException("XRefreshView is allowed to have one and only one child");
                }
            }
            this.C = (IFooterCallBack) this.h;
            j();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        this.m.a(getChildAt(1));
        this.m.b(this.k ? this : null);
        this.m.a(this.n, this.o);
        this.m.a(this.r);
        this.m.a(this);
        this.m.c();
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.h == null) {
            this.h = new XRefreshViewFooter(getContext());
        }
        c();
    }

    @SuppressLint({"NewApi"})
    public void removeViewTreeObserver(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (Build.VERSION.SDK_INT < 16) {
            getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
        } else {
            getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public void setHeaderGap(int i2) {
        this.M = i2;
    }

    private void getHeaderHeight() {
        if (this.B != null) {
            this.b = this.B.getHeaderHeight();
        }
    }

    private void getFooterHeight() {
        if (this.C != null) {
            this.l = this.C.getFooterHeight();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        View.MeasureSpec.getSize(i3);
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i4 = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            childAt.measure(View.MeasureSpec.makeMeasureSpec((((size - layoutParams.leftMargin) - layoutParams.rightMargin) - paddingLeft) - paddingRight, 1073741824), getChildMeasureSpec(i3, paddingTop + paddingBottom + layoutParams.topMargin + layoutParams.bottomMargin, layoutParams.height));
            i4 += childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
        }
        setMeasuredDimension(size, i4);
        f();
        getHeaderHeight();
        getFooterHeight();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        LogUtils.a("onLayout mHolder.mOffsetY=" + this.r.f4768a);
        int childCount = getChildCount();
        int paddingTop = getPaddingTop() + this.r.f4768a;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            int i8 = layoutParams.topMargin;
            int i9 = layoutParams.bottomMargin;
            int i10 = layoutParams.leftMargin;
            int i11 = layoutParams.rightMargin;
            int paddingLeft = i10 + getPaddingLeft();
            paddingTop += i8;
            int measuredWidth = childAt.getMeasuredWidth();
            if (childAt.getVisibility() != 8) {
                if (i7 == 0) {
                    i6 = childAt.getMeasuredHeight() - this.b;
                    paddingTop += i6;
                    childAt.layout(paddingLeft, paddingTop - this.b, measuredWidth + paddingLeft, paddingTop);
                } else if (i7 == 1) {
                    int measuredHeight = childAt.getMeasuredHeight() - i6;
                    childAt.layout(paddingLeft, paddingTop, measuredWidth + paddingLeft, measuredHeight + paddingTop);
                    paddingTop += measuredHeight + i9;
                } else if (needAddFooterView()) {
                    childAt.layout(paddingLeft, paddingTop, measuredWidth + paddingLeft, childAt.getMeasuredHeight() + paddingTop);
                    paddingTop += childAt.getMeasuredHeight();
                } else {
                    f();
                }
            }
        }
    }

    private void f() {
        if (!needAddFooterView() && this.h != null && this.h.getVisibility() != 8) {
            this.h.setVisibility(8);
        }
    }

    public void addTouchLifeCycle(TouchLifeCycle touchLifeCycle) {
        this.P.add(touchLifeCycle);
    }

    public void removeTouchLifeCycle(TouchLifeCycle touchLifeCycle) {
        if (touchLifeCycle != null && this.P.contains(touchLifeCycle)) {
            this.P.remove(touchLifeCycle);
        }
    }

    private void a(MotionEvent motionEvent) {
        Iterator<TouchLifeCycle> it = this.P.iterator();
        while (it.hasNext()) {
            TouchLifeCycle next = it.next();
            if (next != null) {
                next.a(motionEvent);
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        a(motionEvent);
        switch (action) {
            case 0:
                this.t = false;
                this.u = false;
                this.c = (int) motionEvent.getRawY();
                this.d = (int) motionEvent.getRawX();
                this.p = this.c;
                break;
            case 1:
            case 3:
                if (this.r.a()) {
                    if (this.e && !this.S && !this.mPullRefreshing && this.r.f4768a > this.b) {
                        this.mPullRefreshing = true;
                        this.B.onStateRefreshing();
                        this.E = XRefreshViewState.STATE_REFRESHING;
                        if (this.g != null) {
                            this.g.a();
                            this.g.a(true);
                        }
                    }
                    m();
                } else if (this.r.b() && !this.S) {
                    if (!this.i || isEmptyViewShowing() || !needAddFooterView() || this.F) {
                        int i2 = 0 - this.r.f4768a;
                        startScroll(i2, Utils.a(i2, getHeight()));
                    } else {
                        invokeLoadMore();
                    }
                }
                this.c = -1;
                this.d = -1;
                this.p = 0;
                this.N = false;
                this.w = false;
                break;
            case 2:
                this.s = motionEvent;
                if (!this.S && isEnabled() && !this.A) {
                    if ((!this.mPullLoading && !this.mPullRefreshing) || !this.G) {
                        int rawY = (int) motionEvent.getRawY();
                        int rawX = (int) motionEvent.getRawX();
                        int i3 = rawY - this.c;
                        int i4 = rawX - this.d;
                        this.c = rawY;
                        this.d = rawX;
                        if (!this.N) {
                            if (Math.abs(rawY - this.p) < this.q) {
                                return super.dispatchTouchEvent(motionEvent);
                            }
                            this.N = true;
                        }
                        if (this.x && !this.w && Math.abs(i4) > this.q && Math.abs(i4) > Math.abs(i3) && this.r.f4768a == 0) {
                            this.w = true;
                        }
                        if (!this.w) {
                            LogUtils.a("isTop=" + this.m.j() + ";isBottom=" + this.m.k());
                            if ((i3 > 0 && this.r.f4768a <= this.O) || i3 < 0) {
                                int i5 = (int) (((float) i3) / this.f);
                                if (this.mPullLoading || this.Q || !this.m.j() || ((i5 <= 0 || this.r.b()) && (i5 >= 0 || !this.r.a()))) {
                                    if (this.mPullRefreshing || !this.m.k() || (i5 >= 0 && (i5 <= 0 || !this.r.b()))) {
                                        if (i5 != 0 && ((this.m.j() && !this.r.a()) || (this.m.k() && !this.r.b()))) {
                                            if (this.Q) {
                                                a(false);
                                            }
                                            if (Math.abs(i5) > 0) {
                                                h();
                                                break;
                                            }
                                        }
                                    } else {
                                        g();
                                        a(i5);
                                        break;
                                    }
                                } else {
                                    g();
                                    a(rawY, i5, new int[0]);
                                    break;
                                }
                            } else {
                                return super.dispatchTouchEvent(motionEvent);
                            }
                        } else {
                            return super.dispatchTouchEvent(motionEvent);
                        }
                    } else {
                        g();
                        return true;
                    }
                } else {
                    return super.dispatchTouchEvent(motionEvent);
                }
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public XRefreshContentView getContentView() {
        return this.m;
    }

    public boolean invokeLoadMore() {
        if (!this.i || isEmptyViewShowing() || this.mPullRefreshing || this.S || this.F) {
            return false;
        }
        int i2 = (0 - this.r.f4768a) - this.l;
        if (i2 != 0) {
            startScroll(i2, Utils.a(i2, getHeight()));
        }
        k();
        return true;
    }

    public void notifyLoadMore() {
        if (needAddFooterView()) {
            k();
        } else {
            this.m.d();
        }
    }

    public void disallowInterceptTouchEvent(boolean z2) {
        this.A = z2;
    }

    private void g() {
        if (!this.t) {
            LogUtils.a("sendCancelEvent");
            n();
            this.t = true;
            this.u = false;
            MotionEvent motionEvent = this.s;
            dispatchTouchEventSupper(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime() + ((long) ViewConfiguration.getLongPressTimeout()), 3, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState()));
        }
    }

    public void setHeadMoveLargestDistence(int i2) {
        if (i2 <= 0) {
            this.O = Utils.a(getContext()).y / 3;
        } else {
            this.O = i2;
        }
        this.O = this.O <= this.b ? this.b + 1 : this.O;
    }

    private void h() {
        if (!this.u) {
            LogUtils.a("sendDownEvent");
            this.t = false;
            this.u = true;
            this.N = false;
            MotionEvent motionEvent = this.s;
            if (motionEvent != null) {
                dispatchTouchEventSupper(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), 0, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState()));
            }
        }
    }

    public boolean dispatchTouchEventSupper(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setPullLoadEnable(boolean z2) {
        this.i = z2;
        if (needAddFooterView()) {
            j();
        } else {
            this.m.f(z2);
        }
    }

    public boolean getPullLoadEnable() {
        return this.i;
    }

    public boolean getPullRefreshEnable() {
        return this.e;
    }

    public void setPullRefreshEnable(boolean z2) {
        this.e = z2;
        i();
    }

    private void i() {
        if (this.B != null) {
            if (!this.e) {
                this.B.hide();
            } else {
                this.B.show();
            }
        }
    }

    private void j() {
        if (this.C != null) {
            if (!this.i) {
                this.C.show(false);
                return;
            }
            this.mPullLoading = false;
            this.C.show(true);
            this.C.onStateRefreshing();
        }
    }

    private void k() {
        if (!this.mPullLoading) {
            this.C.onStateRefreshing();
            this.mPullLoading = true;
            if (this.g != null) {
                this.g.b(false);
            }
        }
    }

    private void a(int i2, int i3, int... iArr) {
        if (iArr != null && iArr.length > 0) {
            this.B.onStateRefreshing();
            startScroll(i3, iArr[0]);
            return;
        }
        if (this.r.b(i3)) {
            i3 = -this.r.f4768a;
        }
        if (this.e || this.y) {
            moveView(i3);
        }
        if (this.e && !this.mPullRefreshing) {
            if (this.r.f4768a > this.b) {
                if (this.E != XRefreshViewState.STATE_READY) {
                    this.B.onStateReady();
                    this.E = XRefreshViewState.STATE_READY;
                }
            } else if (this.E != XRefreshViewState.STATE_NORMAL) {
                this.B.onStateNormal();
                this.E = XRefreshViewState.STATE_NORMAL;
            }
        }
    }

    public void setMoveHeadWhenDisablePullRefresh(boolean z2) {
        this.y = z2;
    }

    public void setMoveFootWhenDisablePullLoadMore(boolean z2) {
        this.z = z2;
    }

    private boolean l() {
        return this.H && this.i && this.m != null && !this.m.i() && !this.m.o();
    }

    private void a(boolean z2) {
        this.Q = z2;
        this.m.b(this.Q);
    }

    private void a(int i2) {
        if (this.i) {
            if (needAddFooterView()) {
                if (isEmptyViewShowing()) {
                    if (this.C.isShowing()) {
                        this.C.show(false);
                    }
                } else if (this.E != XRefreshViewState.STATE_LOADING) {
                    this.C.onStateRefreshing();
                    this.E = XRefreshViewState.STATE_LOADING;
                }
            } else if (l()) {
                a(this.r.f4768a != 0);
            }
        }
        if (!needAddFooterView() && !this.I) {
            return;
        }
        if (this.R || !this.m.i()) {
            if (this.m.i() && needAddFooterView() && this.C != null && this.C.isShowing()) {
                this.C.show(false);
            }
            if (this.i || this.z) {
                moveView(i2);
            }
        }
    }

    public void setAutoRefresh(boolean z2) {
        this.j = z2;
    }

    public void setAutoLoadMore(boolean z2) {
        this.k = z2;
        if (this.m != null) {
            this.m.b(z2 ? this : null);
        }
        if (z2) {
            setPullLoadEnable(true);
        }
    }

    public void startRefresh() {
        if (this.e && this.r.f4768a == 0 && !this.m.o() && !this.mPullRefreshing && isEnabled()) {
            if (this.K) {
                this.L = false;
                a(0, this.b, 0);
                this.mPullRefreshing = true;
                if (this.g != null) {
                    this.g.a();
                    this.g.a(false);
                }
                this.m.b();
                return;
            }
            this.L = true;
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        int i2;
        float f2 = (float) this.r.f4768a;
        if (!this.mPullRefreshing || (f2 > ((float) this.b) && f2 != 0.0f)) {
            if (this.mPullRefreshing) {
                i2 = this.b - this.r.f4768a;
                startScroll(i2, Utils.a(i2, getHeight()));
            } else {
                i2 = 0 - this.r.f4768a;
                startScroll(i2, Utils.a(i2, getHeight()));
            }
            LogUtils.a("resetHeaderHeight offsetY=" + i2);
        }
    }

    public void moveView(int i2) {
        this.r.a(i2);
        this.f4769a.offsetTopAndBottom(i2);
        this.m.c(i2);
        if (needAddFooterView()) {
            this.h.offsetTopAndBottom(i2);
        }
        ViewCompat.postInvalidateOnAnimation(this);
        if (this.g == null) {
            return;
        }
        if (this.m.j() || this.mPullRefreshing) {
            double d2 = (double) this.r.f4768a;
            Double.isNaN(d2);
            double d3 = (double) this.b;
            Double.isNaN(d3);
            double d4 = (d2 * 1.0d) / d3;
            this.g.a(d4, this.r.f4768a);
            this.B.onHeaderMove(d4, this.r.f4768a, i2);
        }
    }

    public void stopRefresh() {
        stopRefresh(true);
    }

    public void stopRefresh(boolean z2) {
        LogUtils.a("stopRefresh mPullRefreshing=" + this.mPullRefreshing);
        if (this.mPullRefreshing) {
            this.S = true;
            this.B.onStateFinish(z2);
            this.E = XRefreshViewState.STATE_COMPLETE;
            postDelayed(new Runnable() {
                public void run() {
                    XRefreshView.this.mPullRefreshing = false;
                    if (XRefreshView.this.S) {
                        XRefreshView.this.m();
                    }
                    long unused = XRefreshView.this.T = Calendar.getInstance().getTimeInMillis();
                }
            }, (long) this.D);
        }
    }

    public void restoreLastRefreshTime(long j2) {
        this.T = j2;
    }

    public long getLastRefreshTime() {
        return this.T;
    }

    private void n() {
        if (this.T > 0) {
            this.B.setRefreshTime(this.T);
        }
    }

    public void stopLoadMore() {
        stopLoadMore(true);
    }

    public void stopLoadMore(boolean z2) {
        a(z2, this.U);
    }

    private void a(final boolean z2, final int i2) {
        if (needAddFooterView() && this.mPullLoading) {
            this.S = true;
            this.E = XRefreshViewState.STATE_COMPLETE;
            this.C.onStateFinish(z2);
            if (this.D >= 1000) {
                postDelayed(new Runnable() {
                    public void run() {
                        XRefreshView.this.b(z2, i2);
                    }
                }, (long) this.D);
            } else {
                b(z2, i2);
            }
        }
        this.m.d(z2);
    }

    /* access modifiers changed from: private */
    public void b(int i2) {
        View a2 = this.m.a();
        if (a2 instanceof AbsListView) {
            ((AbsListView) a2).smoothScrollBy(i2, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void resetLayout() {
        enablePullUp(false);
        if (this.r.f4768a != 0 && !this.S) {
            startScroll(-this.r.f4768a, Utils.a(this.r.f4768a, getHeight()));
        }
    }

    /* access modifiers changed from: protected */
    public void enablePullUp(boolean z2) {
        this.J = z2;
    }

    public void setLoadComplete(boolean z2) {
        this.F = z2;
        if (needAddFooterView()) {
            stopLoadMore(true);
            if (!z2 && this.i && this.C != null) {
                this.C.onStateRefreshing();
            }
        }
        this.m.c(z2);
    }

    public boolean hasLoadCompleted() {
        return this.F;
    }

    public void setScrollBackDuration(int i2) {
        this.U = i2;
    }

    /* access modifiers changed from: private */
    public void b(boolean z2, int i2) {
        this.mPullLoading = false;
        this.V.f4760a = true;
        startScroll(-this.r.f4768a, i2);
        if (this.F && z2) {
            this.C.show(false);
        }
    }

    public void startScroll(int i2, int i3) {
        this.v.startScroll(0, this.r.f4768a, 0, i2, i3);
        post(this.V);
    }

    public boolean isStopLoadMore() {
        return this.V.f4760a;
    }

    public void setOnAbsListViewScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.m.a(onScrollListener);
    }

    public void setEmptyView(View view) {
        Utils.a(view);
        this.W = view;
        o();
    }

    private void o() {
        if (this.W != null) {
            LinearLayout.LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.height = -1;
            generateDefaultLayoutParams.width = -1;
            this.W.setLayoutParams(generateDefaultLayoutParams);
        }
    }

    public void setEmptyView(@LayoutRes int i2) {
        if (getContext().getResources().getResourceTypeName(i2).contains("layout")) {
            setEmptyView(LayoutInflater.from(getContext()).inflate(i2, this, false));
            return;
        }
        throw new RuntimeException(getContext().getResources().getResourceName(i2) + " is a illegal layoutid , please check your layout id first !");
    }

    public void enableEmptyView(boolean z2) {
        int i2 = 1;
        if (!this.K) {
            if (!z2) {
                i2 = 2;
            }
            this.ab = i2;
            return;
        }
        View childAt = getChildAt(1);
        if (z2) {
            if (this.W != null && childAt != this.W) {
                this.aa = getChildAt(1);
                a(this.W);
            }
        } else if (this.aa != null && childAt == this.W) {
            a(this.aa);
        }
    }

    public boolean isEmptyViewShowing() {
        if (this.W == null || getChildCount() < 2 || getChildAt(1) != this.W) {
            return false;
        }
        return true;
    }

    public View getEmptyView() {
        return this.W;
    }

    private void a(View view) {
        removeViewAt(1);
        addView(view, 1);
        this.m.a(view);
        this.m.b();
    }

    public void setOnRecyclerViewScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        this.m.a(onScrollListener);
    }

    public void setPreLoadCount(int i2) {
        this.m.a(i2);
    }

    public void setXRefreshViewListener(XRefreshViewListener xRefreshViewListener) {
        this.g = xRefreshViewListener;
        this.m.a(xRefreshViewListener);
    }

    public void enableReleaseToLoadMore(boolean z2) {
        this.H = z2;
    }

    public void enablePullUpWhenLoadCompleted(boolean z2) {
        this.R = z2;
    }

    public void enableRecyclerViewPullUp(boolean z2) {
        this.I = z2;
    }

    public void setFooterCallBack(IFooterCallBack iFooterCallBack) {
        this.C = iFooterCallBack;
    }

    public void setDampingRatio(float f2) {
        this.f = f2;
    }

    public void setPinnedTime(int i2) {
        this.D = i2;
        this.m.b(i2);
    }

    public void setHideFooterWhenComplete(boolean z2) {
        this.m.e(z2);
    }

    public void setPinnedContent(boolean z2) {
        this.G = z2;
    }

    public void setCustomHeaderView(View view) {
        if (view instanceof IHeaderCallBack) {
            if (this.f4769a != null) {
                removeView(this.f4769a);
            }
            this.f4769a = view;
            b();
            return;
        }
        throw new RuntimeException("headerView must be implementes IHeaderCallBack!");
    }

    public void setCustomFooterView(View view) {
        if (view instanceof IFooterCallBack) {
            if (this.h != null) {
                removeView(this.h);
            }
            this.h = view;
            c();
            return;
        }
        throw new RuntimeException("footerView must be implementes IFooterCallBack!");
    }
}
