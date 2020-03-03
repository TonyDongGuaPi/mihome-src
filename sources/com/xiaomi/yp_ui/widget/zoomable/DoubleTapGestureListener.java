package com.xiaomi.yp_ui.widget.zoomable;

import android.app.Activity;
import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class DoubleTapGestureListener extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1615a = 300;
    private static final int b = 20;
    private final ZoomableDraweeView c;
    private final PointF d = new PointF();
    private final PointF e = new PointF();
    private float f = 1.0f;
    private boolean g = false;
    private Activity h;

    public DoubleTapGestureListener(ZoomableDraweeView zoomableDraweeView) {
        this.c = zoomableDraweeView;
    }

    /* access modifiers changed from: protected */
    public ZoomableDraweeView a() {
        return this.c;
    }

    public void a(Activity activity) {
        this.h = activity;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        if (this.h == null) {
            return super.onSingleTapConfirmed(motionEvent);
        }
        this.h.finish();
        return true;
    }

    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        AbstractAnimatedZoomableController abstractAnimatedZoomableController = (AbstractAnimatedZoomableController) this.c.getZoomableController();
        PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
        PointF a2 = abstractAnimatedZoomableController.a(pointF);
        boolean z = false;
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.d.set(pointF);
                this.e.set(a2);
                this.f = abstractAnimatedZoomableController.q();
                break;
            case 1:
                if (this.g) {
                    abstractAnimatedZoomableController.a(b(pointF), this.e, this.d);
                } else {
                    float p = abstractAnimatedZoomableController.p();
                    float o = abstractAnimatedZoomableController.o();
                    if (abstractAnimatedZoomableController.q() < (p + o) / 2.0f) {
                        abstractAnimatedZoomableController.a(p, a2, pointF, 7, 300, (Runnable) null);
                    } else {
                        abstractAnimatedZoomableController.a(o, a2, pointF, 7, 300, (Runnable) null);
                    }
                }
                this.g = false;
                break;
            case 2:
                if (this.g || a(pointF)) {
                    z = true;
                }
                this.g = z;
                if (this.g) {
                    abstractAnimatedZoomableController.a(b(pointF), this.e, this.d);
                    break;
                }
                break;
        }
        return true;
    }

    private boolean a(PointF pointF) {
        return Math.hypot((double) (pointF.x - this.d.x), (double) (pointF.y - this.d.y)) > 20.0d;
    }

    private float b(PointF pointF) {
        float f2 = pointF.y - this.d.y;
        float abs = (Math.abs(f2) * 0.001f) + 1.0f;
        return f2 < 0.0f ? this.f / abs : this.f * abs;
    }
}
