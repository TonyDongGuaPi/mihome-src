package com.reactnativecommunity.viewpager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

class PageScrollStateChangedEvent extends Event<PageScrollStateChangedEvent> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8705a = "topPageScrollStateChanged";
    private final String b;

    public String getEventName() {
        return "topPageScrollStateChanged";
    }

    protected PageScrollStateChangedEvent(int i, String str) {
        super(i);
        this.b = str;
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("pageScrollState", this.b);
        return createMap;
    }
}
