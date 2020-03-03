package com.mics.widget.SpringView.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;
import android.widget.OverScroller;
import com.mics.R;

public class SpringView extends ViewGroup {
    private float A;
    private float B;
    private float C;
    private float D;
    private boolean E = false;
    private Rect F = new Rect();
    private View G;
    private View H;
    private View I;
    private boolean J = false;
    private int K;
    private int L;
    private float M;
    private float N;
    private boolean O;
    private int P = -1;
    private int Q;
    private boolean R = true;
    /* access modifiers changed from: private */
    public int S = 0;
    private boolean T;
    private boolean U = false;
    private boolean V = false;
    private boolean W = false;

    /* renamed from: a  reason: collision with root package name */
    private Context f7820a;
    private boolean aa = false;
    private DragHandler ab;
    private DragHandler ac;
    /* access modifiers changed from: private */
    public DragHandler ad;
    private DragHandler ae;
    private LayoutInflater b;
    private OverScroller c;
    /* access modifiers changed from: private */
    public OnFreshListener d;
    /* access modifiers changed from: private */
    public OnRefreshListener e;
    private boolean f = false;
    private boolean g = false;
    private boolean h = true;
    private boolean i = false;
    /* access modifiers changed from: private */
    public boolean j = false;
    private boolean k = false;
    private boolean l = false;
    private long m;
    private boolean n = true;
    private int o = 400;
    private int p = 200;
    private Give q = Give.BOTH;
    private Type r = Type.OVERLAP;
    private Type s;
    private final double t = 2.0d;
    private int u = 600;
    private int v = 600;
    private int w;
    private int x;
    private int y;
    private int z;

    public interface DragHandler {
        int a(View view);

        View a(LayoutInflater layoutInflater, ViewGroup viewGroup);

        void a();

        void a(View view, int i);

        void a(View view, boolean z);

        int b(View view);

        void b();

        int c(View view);

        void d(View view);
    }

    public enum Give {
        BOTH,
        TOP,
        BOTTOM,
        NONE
    }

    public interface OnFreshListener {
        void a();

        void b();
    }

    public interface OnRefreshListener {
        void onLoadMore(View view);

        void onRefresh(View view);
    }

    public enum Type {
        OVERLAP,
        FOLLOW
    }

    public SpringView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7820a = context;
        this.b = LayoutInflater.from(context);
        this.c = new OverScroller(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SpringView);
        if (obtainStyledAttributes.hasValue(R.styleable.SpringView_type)) {
            this.r = Type.values()[obtainStyledAttributes.getInt(R.styleable.SpringView_type, 0)];
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SpringView_give)) {
            this.q = Give.values()[obtainStyledAttributes.getInt(R.styleable.SpringView_give, 0)];
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SpringView_header)) {
            this.K = obtainStyledAttributes.getResourceId(R.styleable.SpringView_header, 0);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SpringView_footer)) {
            this.L = obtainStyledAttributes.getResourceId(R.styleable.SpringView_footer, 0);
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.I = getChildAt(0);
        if (this.I != null) {
            setPadding(0, 0, 0, 0);
            this.I.setPadding(0, 0, 0, 0);
            if (this.K != 0) {
                this.b.inflate(this.K, this, true);
                this.G = getChildAt(getChildCount() - 1);
            }
            if (this.L != 0) {
                this.b.inflate(this.L, this, true);
                this.H = getChildAt(getChildCount() - 1);
                this.H.setVisibility(4);
            }
            this.I.bringToFront();
            super.onFinishInflate();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        if (getChildCount() > 0) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                measureChild(getChildAt(i4), i2, i3);
            }
        }
        if (this.ad != null) {
            int b2 = this.ad.b(this.G);
            if (b2 > 0) {
                this.u = b2;
            }
            int a2 = this.ad.a(this.G);
            if (a2 <= 0) {
                a2 = this.G.getMeasuredHeight();
            }
            this.w = a2;
            int c2 = this.ad.c(this.G);
            if (c2 <= 0) {
                c2 = this.w;
            }
            this.y = c2;
        } else {
            if (this.G != null) {
                this.w = this.G.getMeasuredHeight();
            }
            this.y = this.w;
        }
        if (this.ae != null) {
            int b3 = this.ae.b(this.H);
            if (b3 > 0) {
                this.v = b3;
            }
            int a3 = this.ae.a(this.H);
            if (a3 <= 0) {
                a3 = this.H.getMeasuredHeight();
            }
            this.x = a3;
            int c3 = this.ae.c(this.H);
            if (c3 <= 0) {
                c3 = this.x;
            }
            this.z = c3;
        } else {
            if (this.H != null) {
                this.x = this.H.getMeasuredHeight();
            }
            this.z = this.x;
        }
        setMeasuredDimension(i2, i3);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        if (this.I != null) {
            if (this.r == Type.OVERLAP) {
                if (this.G != null) {
                    this.G.layout(0, 0, getWidth(), this.G.getMeasuredHeight());
                }
                if (this.H != null) {
                    this.H.layout(0, getHeight() - this.H.getMeasuredHeight(), getWidth(), getHeight());
                }
            } else if (this.r == Type.FOLLOW) {
                if (this.G != null) {
                    this.G.layout(0, -this.G.getMeasuredHeight(), getWidth(), 0);
                }
                if (this.H != null) {
                    this.H.layout(0, getHeight(), getWidth(), getHeight() + this.H.getMeasuredHeight());
                }
            }
            this.I.layout(0, 0, this.I.getMeasuredWidth(), this.I.getMeasuredHeight());
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        dealMulTouchEvent(motionEvent);
        switch (motionEvent.getAction()) {
            case 0:
                this.U = false;
                this.V = false;
                this.C = motionEvent.getY();
                boolean m2 = m();
                boolean a2 = a(this.k);
                if (m2 || a2) {
                    this.O = false;
                    break;
                }
            case 1:
            case 3:
                this.l = false;
                this.m = System.currentTimeMillis();
                break;
            case 2:
                this.D += this.M;
                this.l = true;
                this.O = e();
                if (this.O && !this.E) {
                    this.E = true;
                    motionEvent.setAction(3);
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    dispatchTouchEvent(motionEvent);
                    obtain.setAction(0);
                    return dispatchTouchEvent(obtain);
                }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.O && this.n;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.I == null) {
            return false;
        }
        if (this.J) {
            return true;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.h = true;
                break;
            case 1:
            case 3:
                this.J = true;
                this.Q = 0;
                this.j = true;
                this.h = true;
                this.R = true;
                k();
                this.D = 0.0f;
                this.M = 0.0f;
                break;
            case 2:
                if (!this.O) {
                    if (this.M != 0.0f && t()) {
                        h();
                        motionEvent.setAction(0);
                        dispatchTouchEvent(motionEvent);
                        this.E = false;
                        break;
                    }
                } else {
                    this.j = false;
                    a();
                    if (r()) {
                        if (!(this.G == null || this.G.getVisibility() == 0)) {
                            this.G.setVisibility(0);
                        }
                        if (!(this.H == null || this.H.getVisibility() == 4)) {
                            this.H.setVisibility(4);
                        }
                    } else if (s()) {
                        if (!(this.G == null || this.G.getVisibility() == 4)) {
                            this.G.setVisibility(4);
                        }
                        if (!(this.H == null || this.H.getVisibility() == 0)) {
                            this.H.setVisibility(0);
                        }
                    }
                    b();
                    c();
                    d();
                    this.h = false;
                    break;
                }
        }
        return true;
    }

    public void dealMulTouchEvent(MotionEvent motionEvent) {
        int i2 = 0;
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case 0:
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                float x2 = MotionEventCompat.getX(motionEvent, actionIndex);
                float y2 = MotionEventCompat.getY(motionEvent, actionIndex);
                this.B = x2;
                this.A = y2;
                this.P = MotionEventCompat.getPointerId(motionEvent, 0);
                return;
            case 1:
            case 3:
                this.P = -1;
                return;
            case 2:
                int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.P);
                float x3 = MotionEventCompat.getX(motionEvent, findPointerIndex);
                float y3 = MotionEventCompat.getY(motionEvent, findPointerIndex);
                this.N = x3 - this.B;
                this.M = y3 - this.A;
                this.A = y3;
                this.B = x3;
                return;
            case 5:
                int actionIndex2 = MotionEventCompat.getActionIndex(motionEvent);
                if (MotionEventCompat.getPointerId(motionEvent, actionIndex2) != this.P) {
                    this.B = MotionEventCompat.getX(motionEvent, actionIndex2);
                    this.A = MotionEventCompat.getY(motionEvent, actionIndex2);
                    this.P = MotionEventCompat.getPointerId(motionEvent, actionIndex2);
                    return;
                }
                return;
            case 6:
                int actionIndex3 = MotionEventCompat.getActionIndex(motionEvent);
                if (MotionEventCompat.getPointerId(motionEvent, actionIndex3) == this.P) {
                    if (actionIndex3 == 0) {
                        i2 = 1;
                    }
                    this.B = MotionEventCompat.getX(motionEvent, i2);
                    this.A = MotionEventCompat.getY(motionEvent, i2);
                    this.P = MotionEventCompat.getPointerId(motionEvent, i2);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void a() {
        int i2;
        int i3;
        if (this.r == Type.OVERLAP) {
            if (this.F.isEmpty()) {
                this.F.set(this.I.getLeft(), this.I.getTop(), this.I.getRight(), this.I.getBottom());
            }
            if (this.M > 0.0f) {
                double top = (double) ((((float) (this.u - this.I.getTop())) / ((float) this.u)) * this.M);
                Double.isNaN(top);
                i3 = (int) (top / 2.0d);
            } else {
                double realSpacing = (double) ((((float) (this.v - getRealSpacing())) / ((float) this.v)) * this.M);
                Double.isNaN(realSpacing);
                i3 = (int) (realSpacing / 2.0d);
            }
            int top2 = this.I.getTop() + i3;
            this.I.layout(this.I.getLeft(), top2, this.I.getRight(), this.I.getMeasuredHeight() + top2);
        } else if (this.r == Type.FOLLOW) {
            if (this.M > 0.0f) {
                double scrollY = (double) ((((float) (this.u + getScrollY())) / ((float) this.u)) * this.M);
                Double.isNaN(scrollY);
                i2 = (int) (scrollY / 2.0d);
            } else {
                double scrollY2 = (double) ((((float) (this.v - getScrollY())) / ((float) this.v)) * this.M);
                Double.isNaN(scrollY2);
                i2 = (int) (scrollY2 / 2.0d);
            }
            scrollBy(0, -i2);
        }
    }

    private int getRealSpacing() {
        int height = getHeight() - this.I.getBottom();
        return height > this.v ? height - this.v : height;
    }

    private void b() {
        if (this.r == Type.OVERLAP) {
            if (this.I.getTop() > 0 && this.ad != null) {
                this.ad.a(this.G, this.I.getTop());
            }
            if (this.I.getTop() < 0 && this.ae != null) {
                this.ae.a(this.H, this.I.getTop());
            }
        } else if (this.r == Type.FOLLOW) {
            if (getScrollY() < 0 && this.ad != null) {
                this.ad.a(this.G, -getScrollY());
            }
            if (getScrollY() > 0 && this.ae != null) {
                this.ae.a(this.H, -getScrollY());
            }
        }
    }

    private void c() {
        if (!this.R) {
            return;
        }
        if (r()) {
            if (this.ad != null) {
                this.ad.d(this.G);
            }
            this.R = false;
        } else if (s()) {
            if (this.ae != null) {
                this.ae.d(this.H);
            }
            this.R = false;
        }
    }

    private void d() {
        boolean z2;
        if (this.r != Type.OVERLAP ? this.r != Type.FOLLOW || getScrollY() > 0 || !m() : this.I.getTop() < 0 || !m()) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (this.h) {
            if (z2) {
                this.g = true;
                this.f = false;
            } else {
                this.g = false;
                this.f = true;
            }
        }
        if (this.M != 0.0f) {
            boolean z3 = this.M < 0.0f;
            if (z2) {
                if (!z3) {
                    if (p() && !this.f) {
                        this.f = true;
                        if (this.ad != null) {
                            this.ad.a(this.G, z3);
                        }
                        this.g = false;
                    }
                } else if (!p() && !this.g) {
                    this.g = true;
                    if (this.ad != null) {
                        this.ad.a(this.G, z3);
                    }
                    this.f = false;
                }
            } else if (z3) {
                if (q() && !this.g) {
                    this.g = true;
                    if (this.ae != null) {
                        this.ae.a(this.H, z3);
                    }
                    this.f = false;
                }
            } else if (!q() && !this.f) {
                this.f = true;
                if (this.ae != null) {
                    this.ae.a(this.H, z3);
                }
                this.g = false;
            }
        }
    }

    public void computeScroll() {
        this.J = false;
        if (this.c.computeScrollOffset()) {
            scrollTo(0, this.c.getCurrY());
            invalidate();
        }
        if (!this.l && this.r == Type.FOLLOW && this.c.isFinished()) {
            if (this.T) {
                if (!this.U) {
                    this.U = true;
                    f();
                }
            } else if (!this.V) {
                this.V = true;
                g();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x0088 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean e() {
        /*
            r8 = this;
            android.view.View r0 = r8.I
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            float r0 = r8.M
            float r0 = java.lang.Math.abs(r0)
            float r2 = r8.N
            float r2 = java.lang.Math.abs(r2)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x0017
            return r1
        L_0x0017:
            boolean r0 = r8.m()
            boolean r2 = r8.k
            boolean r2 = r8.a((boolean) r2)
            com.mics.widget.SpringView.widget.SpringView$Type r3 = r8.r
            com.mics.widget.SpringView.widget.SpringView$Type r4 = com.mics.widget.SpringView.widget.SpringView.Type.OVERLAP
            r5 = 20
            r6 = 1
            r7 = 0
            if (r3 != r4) goto L_0x005a
            android.view.View r3 = r8.G
            if (r3 == 0) goto L_0x0040
            if (r0 == 0) goto L_0x0037
            float r0 = r8.M
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 > 0) goto L_0x003f
        L_0x0037:
            android.view.View r0 = r8.I
            int r0 = r0.getTop()
            if (r0 <= r5) goto L_0x0040
        L_0x003f:
            return r6
        L_0x0040:
            android.view.View r0 = r8.H
            if (r0 == 0) goto L_0x0088
            if (r2 == 0) goto L_0x004c
            float r0 = r8.M
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 < 0) goto L_0x0059
        L_0x004c:
            android.view.View r0 = r8.I
            int r0 = r0.getBottom()
            android.graphics.Rect r2 = r8.F
            int r2 = r2.bottom
            int r2 = r2 - r5
            if (r0 >= r2) goto L_0x0088
        L_0x0059:
            return r6
        L_0x005a:
            com.mics.widget.SpringView.widget.SpringView$Type r3 = r8.r
            com.mics.widget.SpringView.widget.SpringView$Type r4 = com.mics.widget.SpringView.widget.SpringView.Type.FOLLOW
            if (r3 != r4) goto L_0x0088
            android.view.View r3 = r8.G
            if (r3 == 0) goto L_0x0075
            if (r0 == 0) goto L_0x006c
            float r0 = r8.M
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 > 0) goto L_0x0074
        L_0x006c:
            int r0 = r8.getScrollY()
            r3 = -20
            if (r0 >= r3) goto L_0x0075
        L_0x0074:
            return r6
        L_0x0075:
            android.view.View r0 = r8.H
            if (r0 == 0) goto L_0x0088
            if (r2 == 0) goto L_0x0081
            float r0 = r8.M
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 < 0) goto L_0x0087
        L_0x0081:
            int r0 = r8.getScrollY()
            if (r0 <= r5) goto L_0x0088
        L_0x0087:
            return r6
        L_0x0088:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mics.widget.SpringView.widget.SpringView.e():boolean");
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.S != 0) {
            i();
        }
        if (this.W) {
            this.W = false;
            setHeaderIn(this.ab);
        }
        if (this.aa) {
            this.aa = false;
            setFooterIn(this.ac);
        }
        if (this.G != null) {
            this.G.setVisibility(4);
        }
        if (this.H != null) {
            this.H.setVisibility(4);
        }
        if (this.i) {
            a(this.s);
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.r == Type.FOLLOW) {
            if (r()) {
                if (this.d != null) {
                    this.d.a();
                }
                if (this.e != null) {
                    this.e.onRefresh(this);
                }
            } else if (s()) {
                if (this.d != null) {
                    this.d.b();
                }
                if (this.e != null) {
                    this.e.onLoadMore(this);
                }
            }
        } else if (this.r == Type.OVERLAP && !this.l && System.currentTimeMillis() - this.m >= ((long) this.p)) {
            if (this.S == 1) {
                if (this.d != null) {
                    this.d.a();
                }
                if (this.e != null) {
                    this.e.onRefresh(this);
                }
            }
            if (this.S == 2) {
                if (this.d != null) {
                    this.d.b();
                }
                if (this.e != null) {
                    this.e.onLoadMore(this);
                }
            }
        }
    }

    private void h() {
        this.T = true;
        int i2 = 0;
        this.E = false;
        if (this.r == Type.OVERLAP) {
            if (this.F.bottom != 0 && this.F.right != 0) {
                if (this.I.getHeight() > 0) {
                    i2 = Math.abs((this.I.getTop() * 400) / this.I.getHeight());
                }
                if (i2 < 100) {
                    i2 = 100;
                }
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) this.I.getTop(), (float) this.F.top);
                translateAnimation.setDuration((long) i2);
                translateAnimation.setFillAfter(true);
                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        SpringView.this.f();
                    }
                });
                this.I.startAnimation(translateAnimation);
                this.I.layout(this.F.left, this.F.top, this.F.right, this.F.bottom);
            }
        } else if (this.r == Type.FOLLOW) {
            this.c.startScroll(0, getScrollY(), 0, -getScrollY(), this.o);
            invalidate();
        }
    }

    private void i() {
        if (this.S != 0) {
            if (this.S == 1) {
                if (this.ad != null) {
                    this.ad.b();
                }
                if (this.q == Give.BOTTOM || this.q == Give.NONE) {
                    if (this.d != null) {
                        this.d.a();
                    }
                    if (this.e != null) {
                        this.e.onRefresh(this);
                    }
                }
            } else if (this.S == 2) {
                if (this.ae != null) {
                    this.ae.b();
                }
                if (this.q == Give.TOP || this.q == Give.NONE) {
                    if (this.d != null) {
                        this.d.b();
                    }
                    if (this.e != null) {
                        this.e.onLoadMore(this);
                    }
                }
            }
            this.S = 0;
        }
    }

    private void j() {
        this.T = false;
        this.E = false;
        if (this.r == Type.OVERLAP) {
            if (this.F.bottom != 0 && this.F.right != 0) {
                if (this.I.getTop() > this.F.top) {
                    TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) (this.I.getTop() - this.y), (float) this.F.top);
                    translateAnimation.setDuration((long) this.p);
                    translateAnimation.setFillAfter(true);
                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                        public void onAnimationRepeat(Animation animation) {
                        }

                        public void onAnimationStart(Animation animation) {
                        }

                        public void onAnimationEnd(Animation animation) {
                            SpringView.this.g();
                        }
                    });
                    this.I.startAnimation(translateAnimation);
                    this.I.layout(this.F.left, this.F.top + this.y, this.F.right, this.F.bottom + this.y);
                    return;
                }
                TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, 0.0f, (float) (this.I.getTop() + this.z), (float) this.F.top);
                translateAnimation2.setDuration((long) this.p);
                translateAnimation2.setFillAfter(true);
                translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        SpringView.this.g();
                    }
                });
                this.I.startAnimation(translateAnimation2);
                this.I.layout(this.F.left, this.F.top - this.z, this.F.right, this.F.bottom - this.z);
            }
        } else if (this.r != Type.FOLLOW) {
        } else {
            if (getScrollY() < 0) {
                this.c.startScroll(0, getScrollY(), 0, (-getScrollY()) - this.y, this.o);
                invalidate();
                return;
            }
            this.c.startScroll(0, getScrollY(), 0, (-getScrollY()) + this.z, this.o);
            invalidate();
        }
    }

    public void callFresh() {
        this.G.setVisibility(0);
        if (this.r == Type.OVERLAP) {
            if (this.F.isEmpty()) {
                this.F.set(this.I.getLeft(), this.I.getTop(), this.I.getRight(), this.I.getBottom());
            }
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) (this.I.getTop() - this.y), (float) this.F.top);
            translateAnimation.setDuration((long) this.p);
            translateAnimation.setFillAfter(true);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                    if (SpringView.this.ad != null) {
                        SpringView.this.ad.a();
                    }
                }

                public void onAnimationEnd(Animation animation) {
                    int unused = SpringView.this.S = 1;
                    boolean unused2 = SpringView.this.j = true;
                    if (SpringView.this.d != null) {
                        SpringView.this.d.a();
                    }
                    if (SpringView.this.e != null) {
                        SpringView.this.e.onRefresh(SpringView.this);
                    }
                }
            });
            this.I.startAnimation(translateAnimation);
            this.I.layout(this.F.left, this.F.top + this.y, this.F.right, this.F.bottom + this.y);
        } else if (this.r == Type.FOLLOW) {
            this.T = false;
            this.V = false;
            this.S = 1;
            this.j = true;
            if (this.ad != null) {
                this.ad.a();
            }
            this.c.startScroll(0, getScrollY(), 0, (-getScrollY()) - this.y, this.o);
            invalidate();
        }
    }

    public void addView(View view) {
        super.addView(view);
        onFinishInflate();
    }

    private void k() {
        if (this.d == null && this.e == null) {
            h();
        } else if (p()) {
            l();
            if (this.q == Give.BOTH || this.q == Give.TOP) {
                j();
            } else {
                h();
            }
        } else if (q()) {
            l();
            if (this.q == Give.BOTH || this.q == Give.BOTTOM) {
                j();
            } else {
                h();
            }
        } else {
            h();
        }
    }

    private void l() {
        if (r()) {
            this.S = 1;
            if (this.r == Type.OVERLAP) {
                if ((this.D > 200.0f || this.w >= this.y) && this.ad != null) {
                    this.ad.a();
                }
            } else if (this.r == Type.FOLLOW && this.ad != null) {
                this.ad.a();
            }
        } else if (s()) {
            this.S = 2;
            if (this.r == Type.OVERLAP) {
                if ((this.D < -200.0f || this.x >= this.z) && this.ae != null) {
                    this.ae.a();
                }
            } else if (this.r == Type.FOLLOW && this.ae != null) {
                this.ae.a();
            }
        }
    }

    private boolean m() {
        return !ViewCompat.canScrollVertically(this.I, -1);
    }

    private boolean a(boolean z2) {
        return !ViewCompat.canScrollVertically(this.I, 1);
    }

    private boolean n() {
        return a(true);
    }

    private boolean o() {
        if (a(false)) {
            return a(true);
        }
        return true;
    }

    private boolean p() {
        if (this.r == Type.OVERLAP) {
            if (this.I.getTop() > this.w) {
                return true;
            }
            return false;
        } else if (this.r != Type.FOLLOW || (-getScrollY()) <= this.w) {
            return false;
        } else {
            return true;
        }
    }

    private boolean q() {
        if (this.r == Type.OVERLAP) {
            if (getHeight() - this.I.getBottom() > this.x) {
                return true;
            }
            return false;
        } else if (this.r != Type.FOLLOW || getScrollY() <= this.x) {
            return false;
        } else {
            return true;
        }
    }

    private boolean r() {
        if (this.r == Type.OVERLAP) {
            if (this.I.getTop() > 0) {
                return true;
            }
            return false;
        } else if (this.r != Type.FOLLOW || getScrollY() >= 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean s() {
        if (this.r == Type.OVERLAP) {
            if (this.I.getTop() < 0) {
                return true;
            }
            return false;
        } else if (this.r != Type.FOLLOW || getScrollY() <= 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean t() {
        if (this.r == Type.OVERLAP) {
            if (this.I.getTop() >= 30 || this.I.getTop() <= -30) {
                return false;
            }
            return true;
        } else if (this.r != Type.FOLLOW || getScrollY() <= -30 || getScrollY() >= 30) {
            return false;
        } else {
            return true;
        }
    }

    private void a(Type type) {
        this.r = type;
        if (!(this.G == null || this.G.getVisibility() == 4)) {
            this.G.setVisibility(4);
        }
        if (!(this.H == null || this.H.getVisibility() == 4)) {
            this.H.setVisibility(4);
        }
        requestLayout();
        this.i = false;
    }

    public void onFinishFreshAndLoad() {
        if (!this.l && this.j) {
            boolean z2 = true;
            boolean z3 = r() && (this.q == Give.TOP || this.q == Give.BOTH);
            if (!s() || !(this.q == Give.BOTTOM || this.q == Give.BOTH)) {
                z2 = false;
            }
            if (z3 || z2) {
                boolean z4 = this.I instanceof ListView;
                h();
            }
        }
    }

    public void setMoveTime(int i2) {
        this.o = i2;
    }

    public void setMoveTimeOver(int i2) {
        this.p = i2;
    }

    public void setEnable(boolean z2) {
        this.n = z2;
    }

    public boolean isEnable() {
        return this.n;
    }

    public void setListener(OnFreshListener onFreshListener) {
        this.d = onFreshListener;
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.e = onRefreshListener;
    }

    public void setGive(Give give) {
        this.q = give;
    }

    public void setType(Type type) {
        if (r() || s()) {
            this.i = true;
            this.s = type;
            return;
        }
        a(type);
    }

    public Type getType() {
        return this.r;
    }

    public View getHeaderView() {
        return this.G;
    }

    public View getFooterView() {
        return this.H;
    }

    public DragHandler getHeader() {
        return this.ad;
    }

    public DragHandler getFooter() {
        return this.ae;
    }

    public void setHeader(DragHandler dragHandler) {
        if (this.ad == null || !r()) {
            setHeaderIn(dragHandler);
            return;
        }
        this.W = true;
        this.ab = dragHandler;
        h();
    }

    private void setHeaderIn(DragHandler dragHandler) {
        this.ad = dragHandler;
        if (this.G != null) {
            removeView(this.G);
        }
        dragHandler.a(this.b, (ViewGroup) this);
        this.G = getChildAt(getChildCount() - 1);
        if (this.G != null) {
            this.G.setVisibility(4);
        }
        this.I.bringToFront();
        requestLayout();
    }

    public void setFooter(DragHandler dragHandler) {
        if (this.ae == null || !s()) {
            setFooterIn(dragHandler);
            return;
        }
        this.aa = true;
        this.ac = dragHandler;
        h();
    }

    private void setFooterIn(DragHandler dragHandler) {
        this.ae = dragHandler;
        if (this.H != null) {
            removeView(this.H);
        }
        dragHandler.a(this.b, (ViewGroup) this);
        this.H = getChildAt(getChildCount() - 1);
        if (this.H != null) {
            this.H.setVisibility(4);
        }
        this.I.bringToFront();
        requestLayout();
    }
}
