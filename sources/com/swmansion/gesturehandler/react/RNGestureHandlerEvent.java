package com.swmansion.gesturehandler.react;

import android.support.annotation.Nullable;
import android.support.v4.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.swmansion.gesturehandler.GestureHandler;

public class RNGestureHandlerEvent extends Event<RNGestureHandlerEvent> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8887a = "onGestureHandlerEvent";
    private static final int b = 7;
    private static final Pools.SynchronizedPool<RNGestureHandlerEvent> c = new Pools.SynchronizedPool<>(7);
    private WritableMap d;

    public boolean canCoalesce() {
        return false;
    }

    public short getCoalescingKey() {
        return 0;
    }

    public String getEventName() {
        return f8887a;
    }

    public static RNGestureHandlerEvent a(GestureHandler gestureHandler, @Nullable RNGestureHandlerEventDataExtractor rNGestureHandlerEventDataExtractor) {
        RNGestureHandlerEvent acquire = c.acquire();
        if (acquire == null) {
            acquire = new RNGestureHandlerEvent();
        }
        acquire.b(gestureHandler, rNGestureHandlerEventDataExtractor);
        return acquire;
    }

    private RNGestureHandlerEvent() {
    }

    private void b(GestureHandler gestureHandler, @Nullable RNGestureHandlerEventDataExtractor rNGestureHandlerEventDataExtractor) {
        super.init(gestureHandler.e().getId());
        this.d = Arguments.createMap();
        if (rNGestureHandlerEventDataExtractor != null) {
            rNGestureHandlerEventDataExtractor.a(gestureHandler, this.d);
        }
        this.d.putInt("handlerTag", gestureHandler.d());
        this.d.putInt("state", gestureHandler.k());
    }

    public void onDispose() {
        this.d = null;
        c.release(this);
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), f8887a, this.d);
    }
}
