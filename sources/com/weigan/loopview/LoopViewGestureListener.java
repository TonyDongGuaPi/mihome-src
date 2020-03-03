package com.weigan.loopview;

import android.view.GestureDetector;
import android.view.MotionEvent;

final class LoopViewGestureListener extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a  reason: collision with root package name */
    final LoopView f9867a;

    LoopViewGestureListener(LoopView loopView) {
        this.f9867a = loopView;
    }

    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.f9867a.scrollBy(f2);
        return true;
    }
}
