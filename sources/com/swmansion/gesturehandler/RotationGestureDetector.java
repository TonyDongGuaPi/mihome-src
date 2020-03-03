package com.swmansion.gesturehandler;

import android.view.MotionEvent;

public class RotationGestureDetector {

    /* renamed from: a  reason: collision with root package name */
    private long f8881a;
    private long b;
    private double c;
    private double d;
    private float e;
    private float f;
    private boolean g;
    private int[] h = new int[2];
    private OnRotationGestureListener i;

    public interface OnRotationGestureListener {
        boolean a(RotationGestureDetector rotationGestureDetector);

        boolean b(RotationGestureDetector rotationGestureDetector);

        void c(RotationGestureDetector rotationGestureDetector);
    }

    public RotationGestureDetector(OnRotationGestureListener onRotationGestureListener) {
        this.i = onRotationGestureListener;
    }

    private void b(MotionEvent motionEvent) {
        this.b = this.f8881a;
        this.f8881a = motionEvent.getEventTime();
        int findPointerIndex = motionEvent.findPointerIndex(this.h[0]);
        int findPointerIndex2 = motionEvent.findPointerIndex(this.h[1]);
        float x = motionEvent.getX(findPointerIndex);
        float y = motionEvent.getY(findPointerIndex);
        float x2 = motionEvent.getX(findPointerIndex2);
        float y2 = motionEvent.getY(findPointerIndex2);
        this.e = (x + x2) * 0.5f;
        this.f = (y + y2) * 0.5f;
        double d2 = -Math.atan2((double) (y2 - y), (double) (x2 - x));
        if (Double.isNaN(this.c)) {
            this.d = 0.0d;
        } else {
            this.d = this.c - d2;
        }
        this.c = d2;
        if (this.d > 3.141592653589793d) {
            this.d -= 3.141592653589793d;
        } else if (this.d < -3.141592653589793d) {
            this.d += 3.141592653589793d;
        }
        if (this.d > 1.5707963267948966d) {
            this.d -= 3.141592653589793d;
        } else if (this.d < -1.5707963267948966d) {
            this.d += 3.141592653589793d;
        }
    }

    private void e() {
        if (this.g) {
            this.g = false;
            if (this.i != null) {
                this.i.c(this);
            }
        }
    }

    public boolean a(MotionEvent motionEvent) {
        int pointerId;
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.g = false;
                this.h[0] = motionEvent.getPointerId(motionEvent.getActionIndex());
                this.h[1] = -1;
                break;
            case 1:
                e();
                break;
            case 2:
                if (this.g) {
                    b(motionEvent);
                    if (this.i != null) {
                        this.i.a(this);
                        break;
                    }
                }
                break;
            case 5:
                if (!this.g) {
                    this.h[1] = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.g = true;
                    this.b = motionEvent.getEventTime();
                    this.c = Double.NaN;
                    b(motionEvent);
                    if (this.i != null) {
                        this.i.b(this);
                        break;
                    }
                }
                break;
            case 6:
                if (this.g && ((pointerId = motionEvent.getPointerId(motionEvent.getActionIndex())) == this.h[0] || pointerId == this.h[1])) {
                    e();
                    break;
                }
        }
        return true;
    }

    public double a() {
        return this.d;
    }

    public long b() {
        return this.f8881a - this.b;
    }

    public float c() {
        return this.e;
    }

    public float d() {
        return this.f;
    }
}
