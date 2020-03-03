package com.swmansion.gesturehandler.react;

import android.support.annotation.Nullable;
import android.support.v4.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.swmansion.gesturehandler.GestureHandler;

public class RNGestureHandlerStateChangeEvent extends Event<RNGestureHandlerStateChangeEvent> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8895a = "onGestureHandlerStateChange";
    private static final int b = 7;
    private static final Pools.SynchronizedPool<RNGestureHandlerStateChangeEvent> c = new Pools.SynchronizedPool<>(7);
    private WritableMap d;

    public boolean canCoalesce() {
        return false;
    }

    public short getCoalescingKey() {
        return 0;
    }

    public String getEventName() {
        return f8895a;
    }

    public static RNGestureHandlerStateChangeEvent a(GestureHandler gestureHandler, int i, int i2, @Nullable RNGestureHandlerEventDataExtractor rNGestureHandlerEventDataExtractor) {
        RNGestureHandlerStateChangeEvent acquire = c.acquire();
        if (acquire == null) {
            acquire = new RNGestureHandlerStateChangeEvent();
        }
        acquire.b(gestureHandler, i, i2, rNGestureHandlerEventDataExtractor);
        return acquire;
    }

    private RNGestureHandlerStateChangeEvent() {
    }

    private void b(GestureHandler gestureHandler, int i, int i2, @Nullable RNGestureHandlerEventDataExtractor rNGestureHandlerEventDataExtractor) {
        super.init(gestureHandler.e().getId());
        this.d = Arguments.createMap();
        if (rNGestureHandlerEventDataExtractor != null) {
            rNGestureHandlerEventDataExtractor.a(gestureHandler, this.d);
        }
        this.d.putInt("handlerTag", gestureHandler.d());
        this.d.putInt("state", i);
        this.d.putInt("oldState", i2);
    }

    public void onDispose() {
        this.d = null;
        c.release(this);
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), f8895a, this.d);
    }
}
