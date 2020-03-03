package com.swmansion.gesturehandler;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewConfiguration;

public class PinchGestureHandler extends GestureHandler<PinchGestureHandler> {
    private ScaleGestureDetector o;
    /* access modifiers changed from: private */
    public double p;
    /* access modifiers changed from: private */
    public double q;
    /* access modifiers changed from: private */
    public float r;
    /* access modifiers changed from: private */
    public float s;
    private ScaleGestureDetector.OnScaleGestureListener t = new ScaleGestureDetector.OnScaleGestureListener() {
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            double a2 = PinchGestureHandler.this.p;
            PinchGestureHandler pinchGestureHandler = PinchGestureHandler.this;
            double a3 = PinchGestureHandler.this.p;
            double scaleFactor = (double) scaleGestureDetector.getScaleFactor();
            Double.isNaN(scaleFactor);
            double unused = pinchGestureHandler.p = a3 * scaleFactor;
            long timeDelta = scaleGestureDetector.getTimeDelta();
            if (timeDelta > 0) {
                PinchGestureHandler pinchGestureHandler2 = PinchGestureHandler.this;
                double a4 = PinchGestureHandler.this.p - a2;
                double d = (double) timeDelta;
                Double.isNaN(d);
                double unused2 = pinchGestureHandler2.q = a4 / d;
            }
            if (Math.abs(PinchGestureHandler.this.r - scaleGestureDetector.getCurrentSpan()) < PinchGestureHandler.this.s || PinchGestureHandler.this.k() != 2) {
                return true;
            }
            PinchGestureHandler.this.n();
            return true;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            float unused = PinchGestureHandler.this.r = scaleGestureDetector.getCurrentSpan();
            return true;
        }
    };

    public PinchGestureHandler() {
        a(false);
    }

    /* access modifiers changed from: protected */
    public void a(MotionEvent motionEvent) {
        if (k() == 0) {
            Context context = e().getContext();
            this.q = 0.0d;
            this.p = 1.0d;
            this.o = new ScaleGestureDetector(context, this.t);
            this.s = (float) ViewConfiguration.get(context).getScaledTouchSlop();
            o();
        }
        if (this.o != null) {
            this.o.onTouchEvent(motionEvent);
        }
        int pointerCount = motionEvent.getPointerCount();
        if (motionEvent.getActionMasked() == 6) {
            pointerCount--;
        }
        if (k() == 4 && pointerCount < 2) {
            p();
        } else if (motionEvent.getActionMasked() == 1) {
            m();
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.o = null;
        this.q = 0.0d;
        this.p = 1.0d;
    }

    public double v() {
        return this.p;
    }

    public double w() {
        return this.q;
    }

    public float x() {
        if (this.o == null) {
            return Float.NaN;
        }
        return this.o.getFocusX();
    }

    public float y() {
        if (this.o == null) {
            return Float.NaN;
        }
        return this.o.getFocusY();
    }
}
