package com.swmansion.gesturehandler;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class NativeViewGestureHandler extends GestureHandler<NativeViewGestureHandler> {
    private boolean o;
    private boolean p;

    public NativeViewGestureHandler() {
        a(true);
    }

    public NativeViewGestureHandler c(boolean z) {
        this.o = z;
        return this;
    }

    public NativeViewGestureHandler d(boolean z) {
        this.p = z;
        return this;
    }

    public boolean b(GestureHandler gestureHandler) {
        return super.b(gestureHandler);
    }

    public boolean d(GestureHandler gestureHandler) {
        if (gestureHandler instanceof NativeViewGestureHandler) {
            NativeViewGestureHandler nativeViewGestureHandler = (NativeViewGestureHandler) gestureHandler;
            if (nativeViewGestureHandler.k() == 4 && nativeViewGestureHandler.p) {
                return false;
            }
        }
        boolean z = !this.p;
        int k = k();
        int k2 = gestureHandler.k();
        if ((k != 4 || k2 != 4 || !z) && k == 4 && z) {
            return true;
        }
        return false;
    }

    public boolean e(GestureHandler gestureHandler) {
        return !this.p;
    }

    /* access modifiers changed from: protected */
    public void a(MotionEvent motionEvent) {
        View e = e();
        int k = k();
        if (motionEvent.getActionMasked() == 1) {
            e.onTouchEvent(motionEvent);
            if ((k == 0 || k == 2) && e.isPressed()) {
                n();
            }
            p();
        } else if (k == 0 || k == 2) {
            if (this.o) {
                a(e, motionEvent);
                e.onTouchEvent(motionEvent);
                n();
            } else if (a(e, motionEvent)) {
                e.onTouchEvent(motionEvent);
                n();
            } else if (k != 2) {
                o();
            }
        } else if (k == 4) {
            e.onTouchEvent(motionEvent);
        }
    }

    private static boolean a(View view, MotionEvent motionEvent) {
        return (view instanceof ViewGroup) && ((ViewGroup) view).onInterceptTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void a() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        obtain.setAction(3);
        e().onTouchEvent(obtain);
    }
}
