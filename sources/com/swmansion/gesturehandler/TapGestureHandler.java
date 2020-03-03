package com.swmansion.gesturehandler;

import android.os.Handler;
import android.view.MotionEvent;

public class TapGestureHandler extends GestureHandler<TapGestureHandler> {
    private static float o = Float.MIN_VALUE;
    private static final long p = 500;
    private static final long q = 500;
    private static final int r = 1;
    private static final int s = 1;
    private int A = 1;
    private float B;
    private float C;
    private float D;
    private float E;
    private float F;
    private float G;
    private Handler H;
    private int I;
    private final Runnable J = new Runnable() {
        public void run() {
            TapGestureHandler.this.m();
        }
    };
    private float t = o;
    private float u = o;
    private float v = o;
    private long w = 500;
    private long x = 500;
    private int y = 1;
    private int z = 1;

    public TapGestureHandler a(int i) {
        this.y = i;
        return this;
    }

    public TapGestureHandler a(long j) {
        this.x = j;
        return this;
    }

    public TapGestureHandler b(long j) {
        this.w = j;
        return this;
    }

    public TapGestureHandler b(float f) {
        this.t = f;
        return this;
    }

    public TapGestureHandler c(float f) {
        this.u = f;
        return this;
    }

    public TapGestureHandler d(float f) {
        this.v = f * f;
        return this;
    }

    public TapGestureHandler b(int i) {
        this.z = i;
        return this;
    }

    public TapGestureHandler() {
        a(true);
    }

    private void v() {
        if (this.H == null) {
            this.H = new Handler();
        } else {
            this.H.removeCallbacksAndMessages((Object) null);
        }
        this.H.postDelayed(this.J, this.w);
    }

    private void w() {
        if (this.H == null) {
            this.H = new Handler();
        } else {
            this.H.removeCallbacksAndMessages((Object) null);
        }
        int i = this.I + 1;
        this.I = i;
        if (i != this.y || this.A < this.z) {
            this.H.postDelayed(this.J, this.x);
            return;
        }
        n();
        p();
    }

    private boolean x() {
        float f = (this.F - this.B) + this.D;
        if (this.t != o && Math.abs(f) > this.t) {
            return true;
        }
        float f2 = (this.G - this.C) + this.E;
        if (this.u != o && Math.abs(f2) > this.u) {
            return true;
        }
        float f3 = (f2 * f2) + (f * f);
        if (this.v == o || f3 <= this.v) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void a(MotionEvent motionEvent) {
        int k = k();
        int actionMasked = motionEvent.getActionMasked();
        if (k == 0) {
            this.D = 0.0f;
            this.E = 0.0f;
            this.B = motionEvent.getRawX();
            this.C = motionEvent.getRawY();
        }
        if (actionMasked == 6 || actionMasked == 5) {
            this.D += this.F - this.B;
            this.E += this.G - this.C;
            this.F = GestureUtils.a(motionEvent, true);
            this.G = GestureUtils.b(motionEvent, true);
            this.B = this.F;
            this.C = this.G;
        } else {
            this.F = GestureUtils.a(motionEvent, true);
            this.G = GestureUtils.b(motionEvent, true);
        }
        if (this.A < motionEvent.getPointerCount()) {
            this.A = motionEvent.getPointerCount();
        }
        if (x()) {
            m();
        } else if (k == 0) {
            if (actionMasked == 0) {
                o();
            }
            v();
        } else if (k != 2) {
        } else {
            if (actionMasked == 1) {
                w();
            } else if (actionMasked == 0) {
                v();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (this.H != null) {
            this.H.removeCallbacksAndMessages((Object) null);
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.I = 0;
        this.A = 0;
        if (this.H != null) {
            this.H.removeCallbacksAndMessages((Object) null);
        }
    }
}
