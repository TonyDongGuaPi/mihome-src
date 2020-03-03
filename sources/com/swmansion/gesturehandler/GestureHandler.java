package com.swmansion.gesturehandler;

import android.view.MotionEvent;
import android.view.View;
import com.swmansion.gesturehandler.GestureHandler;
import java.util.Arrays;

public class GestureHandler<T extends GestureHandler> {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8876a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final float g = Float.NaN;
    public static final int h = 1;
    public static final int i = 2;
    public static final int j = 4;
    public static final int k = 8;
    private static final int o = 0;
    private static final int p = 1;
    private static final int q = 2;
    private static final int r = 3;
    private static final int s = 4;
    private static final int t = 5;
    private static int u = 11;
    private static MotionEvent.PointerProperties[] v;
    private static MotionEvent.PointerCoords[] w;
    private View A;
    private int B = 0;
    private float C;
    private float D;
    private boolean E;
    private boolean F = true;
    private float[] G;
    private float H;
    private float I;
    private float J;
    private float K;
    private boolean L;
    private int M = 0;
    private GestureHandlerOrchestrator N;
    private OnTouchEventListener<T> O;
    private GestureHandlerInteractionController P;
    int l;
    boolean m;
    boolean n;
    private final int[] x = new int[u];
    private int y = 0;
    private int z;

    public static String f(int i2) {
        switch (i2) {
            case 0:
                return "UNDETERMINED";
            case 1:
                return "FAILED";
            case 2:
                return "BEGIN";
            case 3:
                return "CANCELLED";
            case 4:
                return "ACTIVE";
            case 5:
                return "END";
            default:
                return null;
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    /* access modifiers changed from: protected */
    public void b(int i2, int i3) {
    }

    private static void a(int i2) {
        if (v == null) {
            v = new MotionEvent.PointerProperties[u];
            w = new MotionEvent.PointerCoords[u];
        }
        while (i2 > 0) {
            int i3 = i2 - 1;
            if (v[i3] == null) {
                v[i3] = new MotionEvent.PointerProperties();
                w[i3] = new MotionEvent.PointerCoords();
                i2--;
            } else {
                return;
            }
        }
    }

    private static boolean b(float f2) {
        return !Float.isNaN(f2);
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3) {
        if (this.O != null) {
            this.O.a(this, i2, i3);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(MotionEvent motionEvent) {
        if (this.O != null) {
            this.O.a(this, motionEvent);
        }
    }

    public boolean a(GestureHandler gestureHandler) {
        for (int i2 = 0; i2 < this.x.length; i2++) {
            if (this.x[i2] != -1 && gestureHandler.x[i2] != -1) {
                return true;
            }
        }
        return false;
    }

    public T a(boolean z2) {
        this.L = z2;
        return this;
    }

    public T b(boolean z2) {
        if (this.A != null) {
            l();
        }
        this.F = z2;
        return this;
    }

    public boolean c() {
        return this.F;
    }

    public T a(float f2, float f3, float f4, float f5, float f6, float f7) {
        if (this.G == null) {
            this.G = new float[6];
        }
        this.G[0] = f2;
        this.G[1] = f3;
        this.G[2] = f4;
        this.G[3] = f5;
        this.G[4] = f6;
        this.G[5] = f7;
        if (b(f6) && b(f2) && b(f4)) {
            throw new IllegalArgumentException("Cannot have all of left, right and width defined");
        } else if (b(f6) && !b(f2) && !b(f4)) {
            throw new IllegalArgumentException("When width is set one of left or right pads need to be defined");
        } else if (b(f7) && b(f5) && b(f3)) {
            throw new IllegalArgumentException("Cannot have all of top, bottom and height defined");
        } else if (!b(f7) || b(f5) || b(f3)) {
            return this;
        } else {
            throw new IllegalArgumentException("When height is set one of top or bottom pads need to be defined");
        }
    }

    public T a(float f2) {
        return a(f2, f2, f2, f2, Float.NaN, Float.NaN);
    }

    public T a(GestureHandlerInteractionController gestureHandlerInteractionController) {
        this.P = gestureHandlerInteractionController;
        return this;
    }

    public void c(int i2) {
        this.z = i2;
    }

    public int d() {
        return this.z;
    }

    public View e() {
        return this.A;
    }

    public float f() {
        return this.C;
    }

    public float g() {
        return this.D;
    }

    public int h() {
        return this.M;
    }

    public boolean i() {
        return this.E;
    }

    public final void a(View view, GestureHandlerOrchestrator gestureHandlerOrchestrator) {
        if (this.A == null && this.N == null) {
            Arrays.fill(this.x, -1);
            this.y = 0;
            this.B = 0;
            this.A = view;
            this.N = gestureHandlerOrchestrator;
            return;
        }
        throw new IllegalStateException("Already prepared or hasn't been reset");
    }

    private int v() {
        int i2 = 0;
        while (i2 < this.y) {
            int i3 = 0;
            while (i3 < this.x.length && this.x[i3] != i2) {
                i3++;
            }
            if (i3 == this.x.length) {
                return i2;
            }
            i2++;
        }
        return i2;
    }

    public void d(int i2) {
        if (this.x[i2] == -1) {
            this.x[i2] = v();
            this.y++;
        }
    }

    public void e(int i2) {
        if (this.x[i2] != -1) {
            this.x[i2] = -1;
            this.y--;
        }
    }

    private boolean d(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() != this.y) {
            return true;
        }
        for (int i2 = 0; i2 < this.x.length; i2++) {
            if (this.x[i2] != -1 && this.x[i2] != i2) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.MotionEvent e(android.view.MotionEvent r26) {
        /*
            r25 = this;
            r0 = r25
            r1 = r26
            boolean r2 = r25.d((android.view.MotionEvent) r26)
            if (r2 != 0) goto L_0x000b
            return r1
        L_0x000b:
            int r2 = r26.getActionMasked()
            r3 = 2
            r4 = 5
            r5 = 0
            r6 = -1
            r7 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r4) goto L_0x0019
            goto L_0x0037
        L_0x0019:
            r4 = 6
            if (r2 == r7) goto L_0x0022
            if (r2 != r4) goto L_0x001f
            goto L_0x0022
        L_0x001f:
            r3 = r2
            r2 = -1
            goto L_0x004b
        L_0x0022:
            int r2 = r26.getActionIndex()
            int r8 = r1.getPointerId(r2)
            int[] r9 = r0.x
            r8 = r9[r8]
            if (r8 == r6) goto L_0x004b
            int r3 = r0.y
            if (r3 != r7) goto L_0x0035
            r4 = 1
        L_0x0035:
            r3 = r4
            goto L_0x004b
        L_0x0037:
            int r2 = r26.getActionIndex()
            int r8 = r1.getPointerId(r2)
            int[] r9 = r0.x
            r8 = r9[r8]
            if (r8 == r6) goto L_0x004b
            int r3 = r0.y
            if (r3 != r7) goto L_0x0035
            r4 = 0
            goto L_0x0035
        L_0x004b:
            int r4 = r0.y
            a((int) r4)
            float r4 = r26.getX()
            float r7 = r26.getY()
            float r8 = r26.getRawX()
            float r9 = r26.getRawY()
            r1.setLocation(r8, r9)
            int r8 = r26.getPointerCount()
            r13 = r3
            r14 = 0
        L_0x0069:
            if (r5 >= r8) goto L_0x0097
            int r3 = r1.getPointerId(r5)
            int[] r9 = r0.x
            r9 = r9[r3]
            if (r9 == r6) goto L_0x0094
            android.view.MotionEvent$PointerProperties[] r9 = v
            r9 = r9[r14]
            r1.getPointerProperties(r5, r9)
            android.view.MotionEvent$PointerProperties[] r9 = v
            r9 = r9[r14]
            int[] r10 = r0.x
            r3 = r10[r3]
            r9.id = r3
            android.view.MotionEvent$PointerCoords[] r3 = w
            r3 = r3[r14]
            r1.getPointerCoords(r5, r3)
            if (r5 != r2) goto L_0x0092
            int r3 = r14 << 8
            r13 = r13 | r3
        L_0x0092:
            int r14 = r14 + 1
        L_0x0094:
            int r5 = r5 + 1
            goto L_0x0069
        L_0x0097:
            long r9 = r26.getDownTime()
            long r11 = r26.getEventTime()
            android.view.MotionEvent$PointerProperties[] r15 = v
            android.view.MotionEvent$PointerCoords[] r16 = w
            int r17 = r26.getMetaState()
            int r18 = r26.getButtonState()
            float r19 = r26.getXPrecision()
            float r20 = r26.getYPrecision()
            int r21 = r26.getDeviceId()
            int r22 = r26.getEdgeFlags()
            int r23 = r26.getSource()
            int r24 = r26.getFlags()
            android.view.MotionEvent r2 = android.view.MotionEvent.obtain(r9, r11, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)
            r1.setLocation(r4, r7)
            r2.setLocation(r4, r7)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.GestureHandler.e(android.view.MotionEvent):android.view.MotionEvent");
    }

    public final void c(MotionEvent motionEvent) {
        if (this.F && this.B != 3 && this.B != 1 && this.B != 5 && this.y >= 1) {
            MotionEvent e2 = e(motionEvent);
            this.C = e2.getX();
            this.D = e2.getY();
            this.M = e2.getPointerCount();
            this.E = a(this.A, this.C, this.D);
            if (!this.L || this.E) {
                this.H = GestureUtils.a(e2, true);
                this.I = GestureUtils.b(e2, true);
                this.J = e2.getRawX() - e2.getX();
                this.K = e2.getRawY() - e2.getY();
                a(e2);
                if (e2 != motionEvent) {
                    e2.recycle();
                }
            } else if (this.B == 4) {
                l();
            } else if (this.B == 2) {
                m();
            }
        }
    }

    private void b(int i2) {
        if (this.B != i2) {
            int i3 = this.B;
            this.B = i2;
            this.N.a(this, i2, i3);
            b(i2, i3);
        }
    }

    public boolean j() {
        return (!this.F || this.B == 1 || this.B == 3 || this.B == 5 || this.y <= 0) ? false : true;
    }

    public int k() {
        return this.B;
    }

    public boolean b(GestureHandler gestureHandler) {
        if (gestureHandler == this || this.P == null) {
            return false;
        }
        return this.P.b(this, gestureHandler);
    }

    public boolean c(GestureHandler gestureHandler) {
        if (gestureHandler == this || this.P == null) {
            return false;
        }
        return this.P.a(this, gestureHandler);
    }

    public boolean d(GestureHandler gestureHandler) {
        if (gestureHandler == this) {
            return true;
        }
        if (this.P != null) {
            return this.P.c(this, gestureHandler);
        }
        return false;
    }

    public boolean e(GestureHandler gestureHandler) {
        if (gestureHandler == this || this.P == null) {
            return false;
        }
        return this.P.d(this, gestureHandler);
    }

    public boolean a(View view, float f2, float f3) {
        float f4;
        float width = (float) view.getWidth();
        float height = (float) view.getHeight();
        float f5 = 0.0f;
        if (this.G != null) {
            float f6 = this.G[0];
            float f7 = this.G[1];
            float f8 = this.G[2];
            float f9 = this.G[3];
            f4 = b(f6) ? 0.0f - f6 : 0.0f;
            if (b(f7)) {
                f5 = 0.0f - f9;
            }
            if (b(f8)) {
                width += f8;
            }
            if (b(f9)) {
                height += f9;
            }
            float f10 = this.G[4];
            float f11 = this.G[5];
            if (b(f10)) {
                if (!b(f6)) {
                    f4 = width - f10;
                } else if (!b(f8)) {
                    width = f10 + f4;
                }
            }
            if (b(f11)) {
                if (!b(f5)) {
                    f5 = height - f11;
                } else if (!b(height)) {
                    height = f5 + f11;
                }
            }
        } else {
            f4 = 0.0f;
        }
        if (f2 < f4 || f2 > width || f3 < f5 || f3 > height) {
            return false;
        }
        return true;
    }

    public final void l() {
        if (this.B == 4 || this.B == 0 || this.B == 2) {
            a();
            b(3);
        }
    }

    public final void m() {
        if (this.B == 4 || this.B == 0 || this.B == 2) {
            b(1);
        }
    }

    public final void n() {
        if (this.B == 0 || this.B == 2) {
            b(4);
        }
    }

    public final void o() {
        if (this.B == 0) {
            b(2);
        }
    }

    public final void p() {
        if (this.B == 2 || this.B == 4) {
            b(5);
        }
    }

    /* access modifiers changed from: protected */
    public void a(MotionEvent motionEvent) {
        b(1);
    }

    public final void q() {
        this.A = null;
        this.N = null;
        Arrays.fill(this.x, -1);
        this.y = 0;
        b();
    }

    public GestureHandler a(OnTouchEventListener<T> onTouchEventListener) {
        this.O = onTouchEventListener;
        return this;
    }

    public String toString() {
        String simpleName = this.A == null ? null : this.A.getClass().getSimpleName();
        return getClass().getSimpleName() + "@[" + this.z + "]:" + simpleName;
    }

    public float r() {
        return this.H;
    }

    public float s() {
        return this.I;
    }

    public float t() {
        return this.H - this.J;
    }

    public float u() {
        return this.I - this.K;
    }
}
