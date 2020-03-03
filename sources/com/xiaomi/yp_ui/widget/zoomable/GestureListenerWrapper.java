package com.xiaomi.yp_ui.widget.zoomable;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class GestureListenerWrapper extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a  reason: collision with root package name */
    private GestureDetector.SimpleOnGestureListener f1616a = new GestureDetector.SimpleOnGestureListener();

    public void a(GestureDetector.SimpleOnGestureListener simpleOnGestureListener) {
        this.f1616a = simpleOnGestureListener;
    }

    public void onLongPress(MotionEvent motionEvent) {
        this.f1616a.onLongPress(motionEvent);
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return this.f1616a.onScroll(motionEvent, motionEvent2, f, f2);
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return this.f1616a.onFling(motionEvent, motionEvent2, f, f2);
    }

    public void onShowPress(MotionEvent motionEvent) {
        this.f1616a.onShowPress(motionEvent);
    }

    public boolean onDown(MotionEvent motionEvent) {
        return this.f1616a.onDown(motionEvent);
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        return this.f1616a.onDoubleTap(motionEvent);
    }

    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return this.f1616a.onDoubleTapEvent(motionEvent);
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return this.f1616a.onSingleTapConfirmed(motionEvent);
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return this.f1616a.onSingleTapUp(motionEvent);
    }
}
