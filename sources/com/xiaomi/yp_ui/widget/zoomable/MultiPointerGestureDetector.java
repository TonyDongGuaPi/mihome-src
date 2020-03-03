package com.xiaomi.yp_ui.widget.zoomable;

import android.view.MotionEvent;

public class MultiPointerGestureDetector {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1617a = 2;
    private boolean b;
    private int c;
    private int d;
    private final int[] e = new int[2];
    private final float[] f = new float[2];
    private final float[] g = new float[2];
    private final float[] h = new float[2];
    private final float[] i = new float[2];
    private Listener j = null;

    public interface Listener {
        void a(MultiPointerGestureDetector multiPointerGestureDetector);

        void b(MultiPointerGestureDetector multiPointerGestureDetector);

        void c(MultiPointerGestureDetector multiPointerGestureDetector);
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        return true;
    }

    public MultiPointerGestureDetector() {
        b();
    }

    public static MultiPointerGestureDetector a() {
        return new MultiPointerGestureDetector();
    }

    public void a(Listener listener) {
        this.j = listener;
    }

    public void b() {
        this.b = false;
        this.c = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            this.e[i2] = -1;
        }
    }

    private void l() {
        if (!this.b) {
            if (this.j != null) {
                this.j.a(this);
            }
            this.b = true;
        }
    }

    private void m() {
        if (this.b) {
            this.b = false;
            if (this.j != null) {
                this.j.c(this);
            }
        }
    }

    private int a(MotionEvent motionEvent, int i2) {
        int pointerCount = motionEvent.getPointerCount();
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if ((actionMasked == 1 || actionMasked == 6) && i2 >= actionIndex) {
            i2++;
        }
        if (i2 < pointerCount) {
            return i2;
        }
        return -1;
    }

    private static int b(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        int actionMasked = motionEvent.getActionMasked();
        return (actionMasked == 1 || actionMasked == 6) ? pointerCount - 1 : pointerCount;
    }

    private void c(MotionEvent motionEvent) {
        this.c = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            int a2 = a(motionEvent, i2);
            if (a2 == -1) {
                this.e[i2] = -1;
            } else {
                this.e[i2] = motionEvent.getPointerId(a2);
                float[] fArr = this.h;
                float[] fArr2 = this.f;
                float x = motionEvent.getX(a2);
                fArr2[i2] = x;
                fArr[i2] = x;
                float[] fArr3 = this.i;
                float[] fArr4 = this.g;
                float y = motionEvent.getY(a2);
                fArr4[i2] = y;
                fArr3[i2] = y;
                this.c++;
            }
        }
    }

    private void d(MotionEvent motionEvent) {
        for (int i2 = 0; i2 < 2; i2++) {
            int findPointerIndex = motionEvent.findPointerIndex(this.e[i2]);
            if (findPointerIndex != -1) {
                this.h[i2] = motionEvent.getX(findPointerIndex);
                this.i[i2] = motionEvent.getY(findPointerIndex);
            }
        }
    }

    public boolean a(MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 0:
            case 1:
            case 5:
            case 6:
                this.d = b(motionEvent);
                m();
                c(motionEvent);
                if (this.c <= 0 || !c()) {
                    return true;
                }
                l();
                return true;
            case 2:
                d(motionEvent);
                if (!this.b && this.c > 0 && c()) {
                    l();
                }
                if (!this.b || this.j == null) {
                    return true;
                }
                this.j.b(this);
                return true;
            case 3:
                this.d = 0;
                m();
                b();
                return true;
            default:
                return true;
        }
    }

    public void d() {
        if (this.b) {
            m();
            for (int i2 = 0; i2 < 2; i2++) {
                this.f[i2] = this.h[i2];
                this.g[i2] = this.i[i2];
            }
            l();
        }
    }

    public boolean e() {
        return this.b;
    }

    public int f() {
        return this.d;
    }

    public int g() {
        return this.c;
    }

    public float[] h() {
        return this.f;
    }

    public float[] i() {
        return this.g;
    }

    public float[] j() {
        return this.h;
    }

    public float[] k() {
        return this.i;
    }
}
