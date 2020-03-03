package com.mi.global.shop.widget.gallery;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class ScaleGestureDetector {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7239a = "ScaleGestureDetector";
    private static final float b = 0.67f;
    private final Context c;
    private final OnScaleGestureListener d;
    private boolean e;
    private MotionEvent f;
    private MotionEvent g;
    private float h;
    private float i;
    private float j;
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private float q;
    private float r;
    private long s;
    private final float t;
    private float u;
    private float v;
    private boolean w;

    public interface OnScaleGestureListener {
        boolean a(ScaleGestureDetector scaleGestureDetector);

        boolean a(ScaleGestureDetector scaleGestureDetector, float f, float f2);

        void b(ScaleGestureDetector scaleGestureDetector);
    }

    public static class SimpleOnScaleGestureListener implements OnScaleGestureListener {
        public boolean a(ScaleGestureDetector scaleGestureDetector) {
            return true;
        }

        public boolean a(ScaleGestureDetector scaleGestureDetector, float f, float f2) {
            return false;
        }

        public void b(ScaleGestureDetector scaleGestureDetector) {
        }
    }

    public ScaleGestureDetector(Context context, OnScaleGestureListener onScaleGestureListener) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.c = context;
        this.d = onScaleGestureListener;
        this.t = (float) viewConfiguration.getScaledEdgeSlop();
    }

    public boolean a(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        boolean z = false;
        if (!this.e) {
            int i2 = action & 255;
            if (i2 != 2) {
                switch (i2) {
                    case 5:
                        DisplayMetrics displayMetrics = this.c.getResources().getDisplayMetrics();
                        this.u = ((float) displayMetrics.widthPixels) - this.t;
                        this.v = ((float) displayMetrics.heightPixels) - this.t;
                        i();
                        this.f = MotionEvent.obtain(motionEvent);
                        this.s = 0;
                        b(motionEvent);
                        float f2 = this.t;
                        float f3 = this.u;
                        float f4 = this.v;
                        float rawX = motionEvent.getRawX();
                        float rawY = motionEvent.getRawY();
                        float a2 = a(motionEvent, 1);
                        float b2 = b(motionEvent, 1);
                        boolean z2 = rawX < f2 || rawY < f2 || rawX > f3 || rawY > f4;
                        if (a2 < f2 || b2 < f2 || a2 > f3 || b2 > f4) {
                            z = true;
                        }
                        if (!z2 || !z) {
                            if (!z2) {
                                if (!z) {
                                    this.e = this.d.a(this);
                                    break;
                                } else {
                                    this.w = true;
                                    break;
                                }
                            } else {
                                this.w = true;
                                break;
                            }
                        } else {
                            this.w = true;
                            break;
                        }
                    case 6:
                        boolean z3 = this.w;
                        break;
                }
            } else if (this.w) {
                float f5 = this.t;
                float f6 = this.u;
                float f7 = this.v;
                float rawX2 = motionEvent.getRawX();
                float rawY2 = motionEvent.getRawY();
                float a3 = a(motionEvent, 1);
                float b3 = b(motionEvent, 1);
                boolean z4 = rawX2 < f5 || rawY2 < f5 || rawX2 > f6 || rawY2 > f7;
                boolean z5 = a3 < f5 || b3 < f5 || a3 > f6 || b3 > f7;
                if ((!z4 || !z5) && !z4 && !z5) {
                    this.w = false;
                    this.e = this.d.a(this);
                }
            }
        } else {
            int i3 = action & 255;
            if (i3 != 6) {
                switch (i3) {
                    case 2:
                        b(motionEvent);
                        this.h = (motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f;
                        this.i = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
                        if (this.q / this.r > b && this.d.a(this, this.h, this.i)) {
                            this.f.recycle();
                            this.f = MotionEvent.obtain(motionEvent);
                            break;
                        }
                    case 3:
                        if (!this.w) {
                            this.d.b(this);
                        }
                        i();
                        break;
                }
            } else {
                b(motionEvent);
                if (!this.w) {
                    this.d.b(this);
                }
                i();
            }
        }
        return true;
    }

    private static float a(MotionEvent motionEvent, int i2) {
        return motionEvent.getX(Math.min(i2, motionEvent.getPointerCount() - 1)) + (motionEvent.getRawX() - motionEvent.getX());
    }

    private static float b(MotionEvent motionEvent, int i2) {
        return motionEvent.getY(Math.min(i2, motionEvent.getPointerCount() - 1)) + (motionEvent.getRawY() - motionEvent.getY());
    }

    private void b(MotionEvent motionEvent) {
        if (this.g != null) {
            this.g.recycle();
        }
        this.g = MotionEvent.obtain(motionEvent);
        this.n = -1.0f;
        this.o = -1.0f;
        this.p = -1.0f;
        MotionEvent motionEvent2 = this.f;
        float x = motionEvent2.getX(0);
        float y = motionEvent2.getY(0);
        float x2 = motionEvent2.getX(1);
        float y2 = motionEvent2.getY(1);
        float x3 = motionEvent.getX(0);
        float y3 = motionEvent.getY(0);
        float x4 = motionEvent.getX(1);
        this.j = x2 - x;
        this.k = y2 - y;
        this.l = x4 - x3;
        this.m = motionEvent.getY(1) - y3;
        this.s = motionEvent.getEventTime() - motionEvent2.getEventTime();
        this.q = motionEvent.getPressure(0) + motionEvent.getPressure(1);
        this.r = motionEvent2.getPressure(0) + motionEvent2.getPressure(1);
    }

    private void i() {
        if (this.f != null) {
            this.f.recycle();
            this.f = null;
        }
        if (this.g != null) {
            this.g.recycle();
            this.g = null;
        }
        this.w = false;
        this.e = false;
    }

    public boolean a() {
        return this.e;
    }

    public float b() {
        return this.h;
    }

    public float c() {
        return this.i;
    }

    public float d() {
        if (this.n == -1.0f) {
            float f2 = this.l;
            float f3 = this.m;
            this.n = (float) Math.sqrt((double) ((f2 * f2) + (f3 * f3)));
        }
        return this.n;
    }

    public float e() {
        if (this.o == -1.0f) {
            float f2 = this.j;
            float f3 = this.k;
            this.o = (float) Math.sqrt((double) ((f2 * f2) + (f3 * f3)));
        }
        return this.o;
    }

    public float f() {
        if (this.p == -1.0f) {
            this.p = d() / e();
        }
        return this.p;
    }

    public long g() {
        return this.s;
    }

    public long h() {
        return this.g.getEventTime();
    }
}
