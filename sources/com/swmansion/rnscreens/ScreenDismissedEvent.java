package com.swmansion.rnscreens;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class ScreenDismissedEvent extends Event<ScreenDismissedEvent> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8950a = "topDismissed";

    public short getCoalescingKey() {
        return 0;
    }

    public String getEventName() {
        return f8950a;
    }

    public ScreenDismissedEvent(int i) {
        super(i);
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), Arguments.createMap());
    }
}
