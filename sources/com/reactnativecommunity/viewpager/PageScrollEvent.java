package com.reactnativecommunity.viewpager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

class PageScrollEvent extends Event<PageScrollEvent> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8704a = "topPageScroll";
    private final int b;
    private final float c;

    public String getEventName() {
        return "topPageScroll";
    }

    protected PageScrollEvent(int i, int i2, float f) {
        super(i);
        this.b = i2;
        this.c = (Float.isInfinite(f) || Float.isNaN(f)) ? 0.0f : f;
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("position", this.b);
        createMap.putDouble("offset", (double) this.c);
        return createMap;
    }
}
