package com.swmansion.gesturehandler;

import android.os.Handler;
import android.view.MotionEvent;

public class FlingGestureHandler extends GestureHandler<FlingGestureHandler> {
    private static final long o = 800;
    private static final long p = 160;
    private static final int q = 1;
    private static final int r = 1;
    private final Runnable A = new Runnable() {
        public void run() {
            FlingGestureHandler.this.m();
        }
    };
    private long s = o;
    private long t = p;
    private int u = 1;
    private int v = 1;
    private float w;
    private float x;
    private Handler y;
    private int z;

    public void a(int i) {
        this.v = i;
    }

    public void b(int i) {
        this.u = i;
    }

    private void d(MotionEvent motionEvent) {
        this.w = motionEvent.getRawX();
        this.x = motionEvent.getRawY();
        o();
        this.z = 1;
        if (this.y == null) {
            this.y = new Handler();
        } else {
            this.y.removeCallbacksAndMessages((Object) null);
        }
        this.y.postDelayed(this.A, this.s);
    }

    private boolean e(MotionEvent motionEvent) {
        if (this.z != this.v) {
            return false;
        }
        if (((this.u & 1) == 0 || motionEvent.getRawX() - this.w <= ((float) this.t)) && (((this.u & 2) == 0 || this.w - motionEvent.getRawX() <= ((float) this.t)) && (((this.u & 4) == 0 || this.x - motionEvent.getRawY() <= ((float) this.t)) && ((this.u & 8) == 0 || motionEvent.getRawY() - this.x <= ((float) this.t))))) {
            return false;
        }
        this.y.removeCallbacksAndMessages((Object) null);
        n();
        p();
        return true;
    }

    private void f(MotionEvent motionEvent) {
        if (!e(motionEvent)) {
            m();
        }
    }

    /* access modifiers changed from: protected */
    public void a(MotionEvent motionEvent) {
        int k = k();
        if (k == 0) {
            d(motionEvent);
        }
        if (k == 2) {
            e(motionEvent);
            if (motionEvent.getPointerCount() > this.z) {
                this.z = motionEvent.getPointerCount();
            }
            if (motionEvent.getActionMasked() == 1) {
                f(motionEvent);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (this.y != null) {
            this.y.removeCallbacksAndMessages((Object) null);
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (this.y != null) {
            this.y.removeCallbacksAndMessages((Object) null);
        }
    }
}
