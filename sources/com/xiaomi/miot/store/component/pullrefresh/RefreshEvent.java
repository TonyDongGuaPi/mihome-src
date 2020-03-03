package com.xiaomi.miot.store.component.pullrefresh;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.xiaomi.youpin.log.LogUtils;

public class RefreshEvent extends Event<RefreshEvent> {
    public static final String TAG = "NativePtr";

    public String getEventName() {
        return "topRefresh";
    }

    protected RefreshEvent(int i) {
        super(i);
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        LogUtils.d("NativePtr", " dispatch   *********    ");
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), (WritableMap) null);
    }
}
