package com.swmansion.gesturehandler;

import android.view.MotionEvent;
import com.swmansion.gesturehandler.RotationGestureDetector;

public class RotationGestureHandler extends GestureHandler<RotationGestureHandler> {
    private static final double o = 0.08726646259971647d;
    private RotationGestureDetector p;
    /* access modifiers changed from: private */
    public double q;
    /* access modifiers changed from: private */
    public double r;
    private RotationGestureDetector.OnRotationGestureListener s = new RotationGestureDetector.OnRotationGestureListener() {
        public boolean b(RotationGestureDetector rotationGestureDetector) {
            return true;
        }

        public boolean a(RotationGestureDetector rotationGestureDetector) {
            double a2 = RotationGestureHandler.this.q;
            double unused = RotationGestureHandler.this.q = RotationGestureHandler.this.q + rotationGestureDetector.a();
            long b = rotationGestureDetector.b();
            if (b > 0) {
                RotationGestureHandler rotationGestureHandler = RotationGestureHandler.this;
                double a3 = RotationGestureHandler.this.q - a2;
                double d = (double) b;
                Double.isNaN(d);
                double unused2 = rotationGestureHandler.r = a3 / d;
            }
            if (Math.abs(RotationGestureHandler.this.q) < RotationGestureHandler.o || RotationGestureHandler.this.k() != 2) {
                return true;
            }
            RotationGestureHandler.this.n();
            return true;
        }

        public void c(RotationGestureDetector rotationGestureDetector) {
            RotationGestureHandler.this.p();
        }
    };

    public RotationGestureHandler() {
        a(false);
    }

    /* access modifiers changed from: protected */
    public void a(MotionEvent motionEvent) {
        int k = k();
        if (k == 0) {
            this.r = 0.0d;
            this.q = 0.0d;
            this.p = new RotationGestureDetector(this.s);
            o();
        }
        if (this.p != null) {
            this.p.a(motionEvent);
        }
        if (motionEvent.getActionMasked() != 1) {
            return;
        }
        if (k == 4) {
            p();
        } else {
            m();
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.p = null;
        this.r = 0.0d;
        this.q = 0.0d;
    }

    public double v() {
        return this.q;
    }

    public double w() {
        return this.r;
    }

    public float x() {
        if (this.p == null) {
            return Float.NaN;
        }
        return this.p.c();
    }

    public float y() {
        if (this.p == null) {
            return Float.NaN;
        }
        return this.p.d();
    }
}
