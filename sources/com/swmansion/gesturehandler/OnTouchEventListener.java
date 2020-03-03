package com.swmansion.gesturehandler;

import android.view.MotionEvent;
import com.swmansion.gesturehandler.GestureHandler;

public interface OnTouchEventListener<T extends GestureHandler> {
    void a(T t, int i, int i2);

    void a(T t, MotionEvent motionEvent);
}
