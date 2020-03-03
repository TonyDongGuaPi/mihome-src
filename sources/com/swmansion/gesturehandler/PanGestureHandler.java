package com.swmansion.gesturehandler;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public class PanGestureHandler extends GestureHandler<PanGestureHandler> {
    private static float o = Float.MAX_VALUE;
    private static float p = Float.MIN_VALUE;
    private static int q = 1;
    private static int r = 10;
    private float A = o;
    private float B = o;
    private float C = o;
    private float D = o;
    private int E = q;
    private int F = r;
    private float G;
    private float H;
    private float I;
    private float J;
    private float K;
    private float L;
    private float M;
    private float N;
    private VelocityTracker O;
    private boolean P;
    private float s = p;
    private float t = o;
    private float u = p;
    private float v = p;
    private float w = o;
    private float x = o;
    private float y = p;
    private float z = p;

    public PanGestureHandler(Context context) {
        int scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.s = (float) (scaledTouchSlop * scaledTouchSlop);
    }

    public PanGestureHandler b(float f) {
        this.t = f;
        return this;
    }

    public PanGestureHandler c(float f) {
        this.u = f;
        return this;
    }

    public PanGestureHandler d(float f) {
        this.v = f;
        return this;
    }

    public PanGestureHandler e(float f) {
        this.w = f;
        return this;
    }

    public PanGestureHandler f(float f) {
        this.x = f;
        return this;
    }

    public PanGestureHandler g(float f) {
        this.y = f;
        return this;
    }

    public PanGestureHandler h(float f) {
        this.z = f;
        return this;
    }

    public PanGestureHandler i(float f) {
        this.A = f;
        return this;
    }

    public PanGestureHandler j(float f) {
        this.s = f * f;
        return this;
    }

    public PanGestureHandler a(int i) {
        this.E = i;
        return this;
    }

    public PanGestureHandler b(int i) {
        this.F = i;
        return this;
    }

    public PanGestureHandler c(boolean z2) {
        this.P = z2;
        return this;
    }

    public PanGestureHandler k(float f) {
        this.D = f * f;
        return this;
    }

    public PanGestureHandler l(float f) {
        this.B = f;
        return this;
    }

    public PanGestureHandler m(float f) {
        this.C = f;
        return this;
    }

    private boolean z() {
        float f = (this.K - this.G) + this.I;
        if (this.t != o && f < this.t) {
            return true;
        }
        if (this.u != p && f > this.u) {
            return true;
        }
        float f2 = (this.L - this.H) + this.J;
        if (this.x != o && f2 < this.x) {
            return true;
        }
        if (this.y != p && f2 > this.y) {
            return true;
        }
        float f3 = (f * f) + (f2 * f2);
        if (this.s != o && f3 >= this.s) {
            return true;
        }
        float f4 = this.M;
        if (this.B != o && ((this.B < 0.0f && f4 <= this.B) || (this.B >= 0.0f && f4 >= this.B))) {
            return true;
        }
        float f5 = this.N;
        if (this.C != o && ((this.C < 0.0f && f4 <= this.C) || (this.C >= 0.0f && f4 >= this.C))) {
            return true;
        }
        float f6 = (f4 * f4) + (f5 * f5);
        if (this.D == o || f6 < this.D) {
            return false;
        }
        return true;
    }

    private boolean A() {
        float f = (this.K - this.G) + this.I;
        if (this.v != p && f < this.v) {
            return true;
        }
        if (this.w != o && f > this.w) {
            return true;
        }
        float f2 = (this.L - this.H) + this.J;
        if (this.z != p && f2 < this.z) {
            return true;
        }
        if (this.A == o || f2 <= this.A) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void a(MotionEvent motionEvent) {
        int k = k();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 6 || actionMasked == 5) {
            this.I += this.K - this.G;
            this.J += this.L - this.H;
            this.K = GestureUtils.a(motionEvent, this.P);
            this.L = GestureUtils.b(motionEvent, this.P);
            this.G = this.K;
            this.H = this.L;
        } else {
            this.K = GestureUtils.a(motionEvent, this.P);
            this.L = GestureUtils.b(motionEvent, this.P);
        }
        if (k == 0 && motionEvent.getPointerCount() >= this.E) {
            this.G = this.K;
            this.H = this.L;
            this.I = 0.0f;
            this.J = 0.0f;
            this.O = VelocityTracker.obtain();
            a(this.O, motionEvent);
            o();
        } else if (this.O != null) {
            a(this.O, motionEvent);
            this.O.computeCurrentVelocity(1000);
            this.M = this.O.getXVelocity();
            this.N = this.O.getYVelocity();
        }
        if (actionMasked == 1) {
            if (k == 4 || k == 2) {
                p();
            } else {
                m();
            }
        } else if (actionMasked != 5 || motionEvent.getPointerCount() <= this.F) {
            if (actionMasked == 6 && k == 4 && motionEvent.getPointerCount() < this.E) {
                m();
            } else if (k != 2) {
            } else {
                if (A()) {
                    m();
                } else if (z()) {
                    this.G = this.K;
                    this.H = this.L;
                    n();
                }
            }
        } else if (k == 4) {
            l();
        } else {
            m();
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (this.O != null) {
            this.O.recycle();
            this.O = null;
        }
    }

    public float v() {
        return (this.K - this.G) + this.I;
    }

    public float w() {
        return (this.L - this.H) + this.J;
    }

    public float x() {
        return this.M;
    }

    public float y() {
        return this.N;
    }

    private static void a(VelocityTracker velocityTracker, MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        motionEvent.offsetLocation(rawX, rawY);
        velocityTracker.addMovement(motionEvent);
        motionEvent.offsetLocation(-rawX, -rawY);
    }
}
