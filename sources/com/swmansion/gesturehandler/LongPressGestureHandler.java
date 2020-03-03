package com.swmansion.gesturehandler;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;

public class LongPressGestureHandler extends GestureHandler<LongPressGestureHandler> {
    private static final long o = 500;
    private static float p = 10.0f;
    private long q = o;
    private float r;
    private float s;
    private float t;
    private Handler u;

    public LongPressGestureHandler(Context context) {
        a(true);
        this.r = p * context.getResources().getDisplayMetrics().density;
    }

    public void a(long j) {
        this.q = j;
    }

    public LongPressGestureHandler b(float f) {
        this.r = f * f;
        return this;
    }

    /* access modifiers changed from: protected */
    public void a(MotionEvent motionEvent) {
        if (k() == 0) {
            o();
            this.s = motionEvent.getRawX();
            this.t = motionEvent.getRawY();
            this.u = new Handler();
            this.u.postDelayed(new Runnable() {
                public void run() {
                    LongPressGestureHandler.this.n();
                }
            }, this.q);
        }
        if (motionEvent.getActionMasked() == 1) {
            if (this.u != null) {
                this.u.removeCallbacksAndMessages((Object) null);
                this.u = null;
            }
            if (k() == 4) {
                p();
            } else {
                m();
            }
        } else {
            float rawX = motionEvent.getRawX() - this.s;
            float rawY = motionEvent.getRawY() - this.t;
            if ((rawX * rawX) + (rawY * rawY) <= this.r) {
                return;
            }
            if (k() == 4) {
                l();
            } else {
                m();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(int i, int i2) {
        if (this.u != null) {
            this.u.removeCallbacksAndMessages((Object) null);
            this.u = null;
        }
    }
}
