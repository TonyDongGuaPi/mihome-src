package com.reactnativecommunity.viewpager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

class PageSelectedEvent extends Event<PageSelectedEvent> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8706a = "topPageSelected";
    private final int b;

    public String getEventName() {
        return "topPageSelected";
    }

    protected PageSelectedEvent(int i, int i2) {
        super(i);
        this.b = i2;
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("position", this.b);
        return createMap;
    }
}
