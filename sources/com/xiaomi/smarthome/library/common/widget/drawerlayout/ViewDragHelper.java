package com.xiaomi.smarthome.library.common.widget.drawerlayout;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;

public class ViewDragHelper {
    private static final Interpolator L = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f19020a = -1;
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 4;
    public static final int h = 8;
    public static final int i = 15;
    public static final int j = 1;
    public static final int k = 2;
    public static final int l = 3;
    private static final String m = "ViewDragHelper";
    private static final int n = 20;
    private static final int o = 256;
    private static final int p = 600;
    private int A;
    private VelocityTracker B;
    private float C;
    private float D;
    private int E;
    private int F;
    private ScrollerCompat G;
    private final Callback H;
    private View I;
    private boolean J;
    private final ViewGroup K;
    private final Runnable M = new Runnable() {
        public void run() {
            ViewDragHelper.this.c(0);
        }
    };
    private int q;
    private int r;
    private int s = -1;
    private float[] t;
    private float[] u;
    private float[] v;
    private float[] w;
    private int[] x;
    private int[] y;
    private int[] z;

    public static abstract class Callback {
        public int a(View view) {
            return 0;
        }

        public int a(View view, int i, int i2) {
            return 0;
        }

        public void a(int i) {
        }

        public void a(int i, int i2) {
        }

        public void a(View view, float f, float f2) {
        }

        public void a(View view, int i, int i2, int i3, int i4) {
        }

        public abstract boolean a(View view, int i);

        public int b(View view) {
            return 0;
        }

        public int b(View view, int i, int i2) {
            return 0;
        }

        public void b(int i, int i2) {
        }

        public void b(View view, int i) {
        }

        public boolean b(int i) {
            return false;
        }

        public int c(int i) {
            return i;
        }
    }

    public static ViewDragHelper a(ViewGroup viewGroup, Callback callback) {
        return new ViewDragHelper(viewGroup.getContext(), viewGroup, callback);
    }

    public static ViewDragHelper a(ViewGroup viewGroup, float f2, Callback callback) {
        ViewDragHelper a2 = a(viewGroup, callback);
        a2.r = (int) (((float) a2.r) * (1.0f / f2));
        a2.r *= 8;
        return a2;
    }

    private ViewDragHelper(Context context, ViewGroup viewGroup, Callback callback) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        } else if (callback != null) {
            this.K = viewGroup;
            this.H = callback;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.E = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
            this.r = viewConfiguration.getScaledTouchSlop();
            this.C = (float) viewConfiguration.getScaledMaximumFlingVelocity();
            this.D = (float) viewConfiguration.getScaledMinimumFlingVelocity();
            this.G = ScrollerCompat.create(context, L);
        } else {
            throw new IllegalArgumentException("Callback may not be null");
        }
    }

    public void a(float f2) {
        this.D = f2;
    }

    public float a() {
        return this.D;
    }

    public int b() {
        return this.q;
    }

    public void a(int i2) {
        this.F = i2;
    }

    public int c() {
        return this.E;
    }

    public void a(View view, int i2) {
        if (view.getParent() == this.K) {
            this.I = view;
            this.s = i2;
            this.H.b(view, i2);
            c(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.K + Operators.BRACKET_END_STR);
    }

    public View d() {
        return this.I;
    }

    public int e() {
        return this.s;
    }

    public int f() {
        return this.r;
    }

    public void g() {
        this.s = -1;
        i();
        if (this.B != null) {
            this.B.recycle();
            this.B = null;
        }
    }

    public void h() {
        g();
        if (this.q == 2) {
            int currX = this.G.getCurrX();
            int currY = this.G.getCurrY();
            this.G.abortAnimation();
            int currX2 = this.G.getCurrX();
            int currY2 = this.G.getCurrY();
            this.H.a(this.I, currX2, currY2, currX2 - currX, currY2 - currY);
        }
        c(0);
    }

    public boolean a(View view, int i2, int i3) {
        this.I = view;
        this.s = -1;
        boolean b2 = b(i2, i3, 0, 0);
        if (!b2 && this.q == 0 && this.I != null) {
            this.I = null;
        }
        return b2;
    }

    public boolean b(View view, int i2, int i3) {
        this.I = view;
        this.s = -1;
        boolean c2 = c(i2, i3, 0, 0);
        if (!c2 && this.q == 0 && this.I != null) {
            this.I = null;
        }
        return c2;
    }

    public boolean a(int i2, int i3) {
        if (this.J) {
            return b(i2, i3, (int) VelocityTrackerCompat.getXVelocity(this.B, this.s), (int) VelocityTrackerCompat.getYVelocity(this.B, this.s));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    private boolean b(int i2, int i3, int i4, int i5) {
        int left = this.I.getLeft();
        int top = this.I.getTop();
        int i6 = i2 - left;
        int i7 = i3 - top;
        if (i6 == 0 && i7 == 0) {
            this.G.abortAnimation();
            c(0);
            return false;
        }
        this.G.startScroll(left, top, i6, i7, a(this.I, i6, i7, i4, i5));
        c(2);
        return true;
    }

    private boolean c(int i2, int i3, int i4, int i5) {
        int left = this.I.getLeft();
        int top = this.I.getTop();
        int i6 = i2 - left;
        int i7 = i3 - top;
        if (i6 == 0 && i7 == 0) {
            this.G.abortAnimation();
            c(0);
            return false;
        }
        int a2 = a(this.I, i6, i7, i4, i5);
        this.G.startScroll(left, top, i6, i7, a2 > 200 ? 200 : a2);
        c(2);
        return true;
    }

    private int a(View view, int i2, int i3, int i4, int i5) {
        float f2;
        float f3;
        float f4;
        float f5;
        int b2 = b(i4, (int) this.D, (int) this.C);
        int b3 = b(i5, (int) this.D, (int) this.C);
        int abs = Math.abs(i2);
        int abs2 = Math.abs(i3);
        int abs3 = Math.abs(b2);
        int abs4 = Math.abs(b3);
        int i6 = abs3 + abs4;
        int i7 = abs + abs2;
        if (b2 != 0) {
            f2 = (float) abs3;
            f3 = (float) i6;
        } else {
            f2 = (float) abs;
            f3 = (float) i7;
        }
        float f6 = f2 / f3;
        if (b3 != 0) {
            f4 = (float) abs4;
            f5 = (float) i6;
        } else {
            f4 = (float) abs2;
            f5 = (float) i7;
        }
        float f7 = f4 / f5;
        return (int) ((((float) a(i2, b2, this.H.a(view))) * f6) + (((float) a(i3, b3, this.H.b(view))) * f7));
    }

    private int a(int i2, int i3, int i4) {
        int i5;
        if (i2 == 0) {
            return 0;
        }
        int width = this.K.getWidth();
        float f2 = (float) (width / 2);
        float b2 = f2 + (b(Math.min(1.0f, ((float) Math.abs(i2)) / ((float) width))) * f2);
        int abs = Math.abs(i3);
        if (abs > 0) {
            i5 = Math.round(Math.abs(b2 / ((float) abs)) * 1000.0f) * 4;
        } else {
            i5 = (int) (((((float) Math.abs(i2)) / ((float) i4)) + 1.0f) * 256.0f);
        }
        return Math.min(i5, 600);
    }

    private int b(int i2, int i3, int i4) {
        int abs = Math.abs(i2);
        if (abs < i3) {
            return 0;
        }
        if (abs > i4) {
            return i2 > 0 ? i4 : -i4;
        }
        return i2;
    }

    private float a(float f2, float f3, float f4) {
        float abs = Math.abs(f2);
        if (abs < f3) {
            return 0.0f;
        }
        if (abs > f4) {
            return f2 > 0.0f ? f4 : -f4;
        }
        return f2;
    }

    private float b(float f2) {
        double d2 = (double) (f2 - 0.5f);
        Double.isNaN(d2);
        return (float) Math.sin((double) ((float) (d2 * 0.4712389167638204d)));
    }

    public void a(int i2, int i3, int i4, int i5) {
        if (this.J) {
            this.G.fling(this.I.getLeft(), this.I.getTop(), (int) VelocityTrackerCompat.getXVelocity(this.B, this.s), (int) VelocityTrackerCompat.getYVelocity(this.B, this.s), i2, i4, i3, i5);
            c(2);
            return;
        }
        throw new IllegalStateException("Cannot flingCapturedView outside of a call to Callback#onViewReleased");
    }

    public boolean a(boolean z2) {
        if (this.q == 2) {
            boolean computeScrollOffset = this.G.computeScrollOffset();
            int currX = this.G.getCurrX();
            int currY = this.G.getCurrY();
            int left = currX - this.I.getLeft();
            int top = currY - this.I.getTop();
            if (left != 0) {
                this.I.offsetLeftAndRight(left);
            }
            if (top != 0) {
                this.I.offsetTopAndBottom(top);
            }
            if (!(left == 0 && top == 0)) {
                this.H.a(this.I, currX, currY, left, top);
            }
            if (computeScrollOffset && currX == this.G.getFinalX() && currY == this.G.getFinalY()) {
                this.G.abortAnimation();
                computeScrollOffset = false;
            }
            if (!computeScrollOffset) {
                if (z2) {
                    this.K.post(this.M);
                } else {
                    c(0);
                }
            }
        }
        if (this.q == 2) {
            return true;
        }
        return false;
    }

    private void a(float f2, float f3) {
        this.J = true;
        this.H.a(this.I, f2, f3);
        this.J = false;
        if (this.q == 1) {
            c(0);
        }
    }

    private void i() {
        if (this.t != null) {
            Arrays.fill(this.t, 0.0f);
            Arrays.fill(this.u, 0.0f);
            Arrays.fill(this.v, 0.0f);
            Arrays.fill(this.w, 0.0f);
            Arrays.fill(this.x, 0);
            Arrays.fill(this.y, 0);
            Arrays.fill(this.z, 0);
            this.A = 0;
        }
    }

    private void f(int i2) {
        if (this.t != null) {
            this.t[i2] = 0.0f;
            this.u[i2] = 0.0f;
            this.v[i2] = 0.0f;
            this.w[i2] = 0.0f;
            this.x[i2] = 0;
            this.y[i2] = 0;
            this.z[i2] = 0;
            this.A = ((1 << i2) ^ -1) & this.A;
        }
    }

    private void g(int i2) {
        if (this.t == null || this.t.length <= i2) {
            int i3 = i2 + 1;
            float[] fArr = new float[i3];
            float[] fArr2 = new float[i3];
            float[] fArr3 = new float[i3];
            float[] fArr4 = new float[i3];
            int[] iArr = new int[i3];
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            if (this.t != null) {
                System.arraycopy(this.t, 0, fArr, 0, this.t.length);
                System.arraycopy(this.u, 0, fArr2, 0, this.u.length);
                System.arraycopy(this.v, 0, fArr3, 0, this.v.length);
                System.arraycopy(this.w, 0, fArr4, 0, this.w.length);
                System.arraycopy(this.x, 0, iArr, 0, this.x.length);
                System.arraycopy(this.y, 0, iArr2, 0, this.y.length);
                System.arraycopy(this.z, 0, iArr3, 0, this.z.length);
            }
            this.t = fArr;
            this.u = fArr2;
            this.v = fArr3;
            this.w = fArr4;
            this.x = iArr;
            this.y = iArr2;
            this.z = iArr3;
        }
    }

    private void a(float f2, float f3, int i2) {
        g(i2);
        float[] fArr = this.t;
        this.v[i2] = f2;
        fArr[i2] = f2;
        float[] fArr2 = this.u;
        this.w[i2] = f3;
        fArr2[i2] = f3;
        this.x[i2] = f((int) f2, (int) f3);
        this.A |= 1 << i2;
    }

    private void c(MotionEvent motionEvent) {
        int pointerCount = MotionEventCompat.getPointerCount(motionEvent);
        for (int i2 = 0; i2 < pointerCount; i2++) {
            int pointerId = MotionEventCompat.getPointerId(motionEvent, i2);
            float x2 = MotionEventCompat.getX(motionEvent, i2);
            float y2 = MotionEventCompat.getY(motionEvent, i2);
            this.v[pointerId] = x2;
            this.w[pointerId] = y2;
        }
    }

    public boolean b(int i2) {
        return ((1 << i2) & this.A) != 0;
    }

    /* access modifiers changed from: package-private */
    public void c(int i2) {
        this.K.removeCallbacks(this.M);
        if (this.q != i2) {
            this.q = i2;
            this.H.a(i2);
            if (this.q == 0) {
                this.I = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b(View view, int i2) {
        if (view == this.I && this.s == i2) {
            return true;
        }
        if (view == null || !this.H.a(view, i2)) {
            return false;
        }
        this.s = i2;
        a(view, i2);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean a(View view, boolean z2, int i2, int i3, int i4, int i5) {
        int i6;
        View view2 = view;
        if (view2 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i7 = i4 + scrollX;
                if (i7 >= childAt.getLeft() && i7 < childAt.getRight() && (i6 = i5 + scrollY) >= childAt.getTop() && i6 < childAt.getBottom()) {
                    if (a(childAt, true, i2, i3, i7 - childAt.getLeft(), i6 - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (!z2 || (!ViewCompat.canScrollHorizontally(view, -i2) && !ViewCompat.canScrollVertically(view, -i3))) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00d3, code lost:
        if (r9 != r8) goto L_0x00dc;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(android.view.MotionEvent r15) {
        /*
            r14 = this;
            int r0 = android.support.v4.view.MotionEventCompat.getActionMasked(r15)
            int r1 = android.support.v4.view.MotionEventCompat.getActionIndex(r15)
            if (r0 != 0) goto L_0x000d
            r14.g()
        L_0x000d:
            android.view.VelocityTracker r2 = r14.B
            if (r2 != 0) goto L_0x0017
            android.view.VelocityTracker r2 = android.view.VelocityTracker.obtain()
            r14.B = r2
        L_0x0017:
            android.view.VelocityTracker r2 = r14.B
            r2.addMovement(r15)
            r2 = 2
            r3 = 0
            r4 = 1
            switch(r0) {
                case 0: goto L_0x00f8;
                case 1: goto L_0x00f4;
                case 2: goto L_0x0066;
                case 3: goto L_0x00f4;
                case 4: goto L_0x0022;
                case 5: goto L_0x002d;
                case 6: goto L_0x0024;
                default: goto L_0x0022;
            }
        L_0x0022:
            goto L_0x0129
        L_0x0024:
            int r15 = android.support.v4.view.MotionEventCompat.getPointerId(r15, r1)
            r14.f(r15)
            goto L_0x0129
        L_0x002d:
            int r0 = android.support.v4.view.MotionEventCompat.getPointerId(r15, r1)
            float r5 = android.support.v4.view.MotionEventCompat.getX(r15, r1)
            float r15 = android.support.v4.view.MotionEventCompat.getY(r15, r1)
            r14.a((float) r5, (float) r15, (int) r0)
            int r1 = r14.q
            if (r1 != 0) goto L_0x0053
            int[] r15 = r14.x
            r15 = r15[r0]
            int r1 = r14.F
            r1 = r1 & r15
            if (r1 == 0) goto L_0x0129
            com.xiaomi.smarthome.library.common.widget.drawerlayout.ViewDragHelper$Callback r1 = r14.H
            int r2 = r14.F
            r15 = r15 & r2
            r1.a((int) r15, (int) r0)
            goto L_0x0129
        L_0x0053:
            int r1 = r14.q
            if (r1 != r2) goto L_0x0129
            int r1 = (int) r5
            int r15 = (int) r15
            android.view.View r15 = r14.e(r1, r15)
            android.view.View r1 = r14.I
            if (r15 != r1) goto L_0x0129
            r14.b((android.view.View) r15, (int) r0)
            goto L_0x0129
        L_0x0066:
            float[] r0 = r14.t
            if (r0 == 0) goto L_0x0129
            float[] r0 = r14.u
            if (r0 != 0) goto L_0x0070
            goto L_0x0129
        L_0x0070:
            r0 = 0
        L_0x0071:
            int r1 = android.support.v4.view.MotionEventCompat.getPointerCount(r15)
            if (r0 >= r1) goto L_0x00f0
            int r1 = android.support.v4.view.MotionEventCompat.getPointerId(r15, r0)
            float[] r2 = r14.t
            int r2 = r2.length
            if (r1 < r2) goto L_0x0082
            goto L_0x00ed
        L_0x0082:
            float r2 = android.support.v4.view.MotionEventCompat.getX(r15, r0)
            float r5 = android.support.v4.view.MotionEventCompat.getY(r15, r0)
            float[] r6 = r14.t
            r6 = r6[r1]
            float r6 = r2 - r6
            float[] r7 = r14.u
            r7 = r7[r1]
            float r7 = r5 - r7
            int r2 = (int) r2
            int r5 = (int) r5
            android.view.View r2 = r14.e(r2, r5)
            if (r2 == 0) goto L_0x00a6
            boolean r5 = r14.a((android.view.View) r2, (float) r6, (float) r7)
            if (r5 == 0) goto L_0x00a6
            r5 = 1
            goto L_0x00a7
        L_0x00a6:
            r5 = 0
        L_0x00a7:
            if (r5 == 0) goto L_0x00dc
            int r8 = r2.getLeft()
            int r9 = (int) r6
            int r10 = r8 + r9
            com.xiaomi.smarthome.library.common.widget.drawerlayout.ViewDragHelper$Callback r11 = r14.H
            int r9 = r11.a((android.view.View) r2, (int) r10, (int) r9)
            int r10 = r2.getTop()
            int r11 = (int) r7
            int r12 = r10 + r11
            com.xiaomi.smarthome.library.common.widget.drawerlayout.ViewDragHelper$Callback r13 = r14.H
            int r11 = r13.b(r2, r12, r11)
            com.xiaomi.smarthome.library.common.widget.drawerlayout.ViewDragHelper$Callback r12 = r14.H
            int r12 = r12.a((android.view.View) r2)
            com.xiaomi.smarthome.library.common.widget.drawerlayout.ViewDragHelper$Callback r13 = r14.H
            int r13 = r13.b((android.view.View) r2)
            if (r12 == 0) goto L_0x00d5
            if (r12 <= 0) goto L_0x00dc
            if (r9 != r8) goto L_0x00dc
        L_0x00d5:
            if (r13 == 0) goto L_0x00f0
            if (r13 <= 0) goto L_0x00dc
            if (r11 != r10) goto L_0x00dc
            goto L_0x00f0
        L_0x00dc:
            r14.b((float) r6, (float) r7, (int) r1)
            int r6 = r14.q
            if (r6 != r4) goto L_0x00e4
            goto L_0x00f0
        L_0x00e4:
            if (r5 == 0) goto L_0x00ed
            boolean r1 = r14.b((android.view.View) r2, (int) r1)
            if (r1 == 0) goto L_0x00ed
            goto L_0x00f0
        L_0x00ed:
            int r0 = r0 + 1
            goto L_0x0071
        L_0x00f0:
            r14.c((android.view.MotionEvent) r15)
            goto L_0x0129
        L_0x00f4:
            r14.g()
            goto L_0x0129
        L_0x00f8:
            float r0 = r15.getX()
            float r1 = r15.getY()
            int r15 = android.support.v4.view.MotionEventCompat.getPointerId(r15, r3)
            r14.a((float) r0, (float) r1, (int) r15)
            int r0 = (int) r0
            int r1 = (int) r1
            android.view.View r0 = r14.e(r0, r1)
            android.view.View r1 = r14.I
            if (r0 != r1) goto L_0x0118
            int r1 = r14.q
            if (r1 != r2) goto L_0x0118
            r14.b((android.view.View) r0, (int) r15)
        L_0x0118:
            int[] r0 = r14.x
            r0 = r0[r15]
            int r1 = r14.F
            r1 = r1 & r0
            if (r1 == 0) goto L_0x0129
            com.xiaomi.smarthome.library.common.widget.drawerlayout.ViewDragHelper$Callback r1 = r14.H
            int r2 = r14.F
            r0 = r0 & r2
            r1.a((int) r0, (int) r15)
        L_0x0129:
            int r15 = r14.q
            if (r15 != r4) goto L_0x012e
            r3 = 1
        L_0x012e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.widget.drawerlayout.ViewDragHelper.a(android.view.MotionEvent):boolean");
    }

    public void b(MotionEvent motionEvent) {
        int i2;
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (actionMasked == 0) {
            g();
        }
        if (this.B == null) {
            this.B = VelocityTracker.obtain();
        }
        this.B.addMovement(motionEvent);
        int i3 = 0;
        switch (actionMasked) {
            case 0:
                float x2 = motionEvent.getX();
                float y2 = motionEvent.getY();
                int pointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                View e2 = e((int) x2, (int) y2);
                a(x2, y2, pointerId);
                b(e2, pointerId);
                int i4 = this.x[pointerId];
                if ((this.F & i4) != 0) {
                    this.H.a(i4 & this.F, pointerId);
                    return;
                }
                return;
            case 1:
                if (this.q == 1) {
                    j();
                }
                g();
                return;
            case 2:
                if (this.q == 1) {
                    int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.s);
                    float x3 = MotionEventCompat.getX(motionEvent, findPointerIndex);
                    float y3 = MotionEventCompat.getY(motionEvent, findPointerIndex);
                    int i5 = (int) (x3 - this.v[this.s]);
                    int i6 = (int) (y3 - this.w[this.s]);
                    d(this.I.getLeft() + i5, this.I.getTop() + i6, i5, i6);
                    c(motionEvent);
                    return;
                }
                int pointerCount = MotionEventCompat.getPointerCount(motionEvent);
                while (i3 < pointerCount) {
                    int pointerId2 = MotionEventCompat.getPointerId(motionEvent, i3);
                    float x4 = MotionEventCompat.getX(motionEvent, i3);
                    float y4 = MotionEventCompat.getY(motionEvent, i3);
                    float f2 = x4 - this.t[pointerId2];
                    float f3 = y4 - this.u[pointerId2];
                    b(f2, f3, pointerId2);
                    if (this.q != 1) {
                        View e3 = e((int) x4, (int) y4);
                        if (!a(e3, f2, f3) || !b(e3, pointerId2)) {
                            i3++;
                        }
                    }
                    c(motionEvent);
                    return;
                }
                c(motionEvent);
                return;
            case 3:
                if (this.q == 1) {
                    a(0.0f, 0.0f);
                }
                g();
                return;
            case 5:
                int pointerId3 = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                float x5 = MotionEventCompat.getX(motionEvent, actionIndex);
                float y5 = MotionEventCompat.getY(motionEvent, actionIndex);
                a(x5, y5, pointerId3);
                if (this.q == 0) {
                    b(e((int) x5, (int) y5), pointerId3);
                    int i7 = this.x[pointerId3];
                    if ((this.F & i7) != 0) {
                        this.H.a(i7 & this.F, pointerId3);
                        return;
                    }
                    return;
                } else if (d((int) x5, (int) y5)) {
                    b(this.I, pointerId3);
                    return;
                } else {
                    return;
                }
            case 6:
                int pointerId4 = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                if (this.q == 1 && pointerId4 == this.s) {
                    int pointerCount2 = MotionEventCompat.getPointerCount(motionEvent);
                    while (true) {
                        if (i3 < pointerCount2) {
                            int pointerId5 = MotionEventCompat.getPointerId(motionEvent, i3);
                            if (pointerId5 != this.s) {
                                if (e((int) MotionEventCompat.getX(motionEvent, i3), (int) MotionEventCompat.getY(motionEvent, i3)) == this.I && b(this.I, pointerId5)) {
                                    i2 = this.s;
                                }
                            }
                            i3++;
                        } else {
                            i2 = -1;
                        }
                    }
                    if (i2 == -1) {
                        j();
                    }
                }
                f(pointerId4);
                return;
            default:
                return;
        }
    }

    private void b(float f2, float f3, int i2) {
        int i3 = 1;
        if (!a(f2, f3, i2, 1)) {
            i3 = 0;
        }
        if (a(f3, f2, i2, 4)) {
            i3 |= 4;
        }
        if (a(f2, f3, i2, 2)) {
            i3 |= 2;
        }
        if (a(f3, f2, i2, 8)) {
            i3 |= 8;
        }
        if (i3 != 0) {
            int[] iArr = this.y;
            iArr[i2] = iArr[i2] | i3;
            this.H.b(i3, i2);
        }
    }

    private boolean a(float f2, float f3, int i2, int i3) {
        float abs = Math.abs(f2);
        float abs2 = Math.abs(f3);
        if ((this.x[i2] & i3) != i3 || (this.F & i3) == 0 || (this.z[i2] & i3) == i3 || (this.y[i2] & i3) == i3 || (abs <= ((float) this.r) && abs2 <= ((float) this.r))) {
            return false;
        }
        if (abs < abs2 * 0.5f && this.H.b(i3)) {
            int[] iArr = this.z;
            iArr[i2] = iArr[i2] | i3;
            return false;
        } else if ((this.y[i2] & i3) != 0 || abs <= ((float) this.r)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean a(View view, float f2, float f3) {
        if (view == null) {
            return false;
        }
        boolean z2 = this.H.a(view) > 0;
        boolean z3 = this.H.b(view) > 0;
        if (!z2 || !z3) {
            if (z2) {
                if (Math.abs(f2) > ((float) this.r)) {
                    return true;
                }
                return false;
            } else if (!z3 || Math.abs(f3) <= ((float) this.r)) {
                return false;
            } else {
                return true;
            }
        } else if ((f2 * f2) + (f3 * f3) > ((float) (this.r * this.r))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean d(int i2) {
        int length = this.t.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (b(i2, i3)) {
                return true;
            }
        }
        return false;
    }

    public boolean b(int i2, int i3) {
        if (!b(i3)) {
            return false;
        }
        boolean z2 = (i2 & 1) == 1;
        boolean z3 = (i2 & 2) == 2;
        float f2 = this.v[i3] - this.t[i3];
        float f3 = this.w[i3] - this.u[i3];
        if (!z2 || !z3) {
            if (z2) {
                if (Math.abs(f2) > ((float) this.r)) {
                    return true;
                }
                return false;
            } else if (!z3 || Math.abs(f3) <= ((float) this.r)) {
                return false;
            } else {
                return true;
            }
        } else if ((f2 * f2) + (f3 * f3) > ((float) (this.r * this.r))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean e(int i2) {
        int length = this.x.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (c(i2, i3)) {
                return true;
            }
        }
        return false;
    }

    public boolean c(int i2, int i3) {
        return b(i3) && (i2 & this.x[i3]) != 0;
    }

    private void j() {
        this.B.computeCurrentVelocity(1000, this.C);
        a(a(VelocityTrackerCompat.getXVelocity(this.B, this.s), this.D, this.C), a(VelocityTrackerCompat.getYVelocity(this.B, this.s), this.D, this.C));
    }

    private void d(int i2, int i3, int i4, int i5) {
        int left = this.I.getLeft();
        int top = this.I.getTop();
        if (i4 != 0) {
            i2 = this.H.a(this.I, i2, i4);
            this.I.offsetLeftAndRight(i2 - left);
        }
        int i6 = i2;
        if (i5 != 0) {
            i3 = this.H.b(this.I, i3, i5);
            this.I.offsetTopAndBottom(i3 - top);
        }
        int i7 = i3;
        if (i4 != 0 || i5 != 0) {
            this.H.a(this.I, i6, i7, i6 - left, i7 - top);
        }
    }

    public boolean d(int i2, int i3) {
        return c(this.I, i2, i3);
    }

    public boolean c(View view, int i2, int i3) {
        if (view != null && i2 >= view.getLeft() && i2 < view.getRight() && i3 >= view.getTop() && i3 < view.getBottom()) {
            return true;
        }
        return false;
    }

    public View e(int i2, int i3) {
        for (int childCount = this.K.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.K.getChildAt(this.H.c(childCount));
            if (i2 >= childAt.getLeft() && i2 < childAt.getRight() && i3 >= childAt.getTop() && i3 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    private int f(int i2, int i3) {
        int i4 = i2 < this.K.getLeft() + this.E ? 1 : 0;
        if (i3 < this.K.getTop() + this.E) {
            i4 |= 4;
        }
        if (i2 > this.K.getRight() - this.E) {
            i4 |= 2;
        }
        return i3 > this.K.getBottom() - this.E ? i4 | 8 : i4;
    }
}
