package com.reactnative.camera.events;

import android.support.v4.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.reactnative.camera.CameraViewManager;

public class CameraMountErrorEvent extends Event<CameraMountErrorEvent> {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.SynchronizedPool<CameraMountErrorEvent> f8671a = new Pools.SynchronizedPool<>(3);

    public short getCoalescingKey() {
        return 0;
    }

    private CameraMountErrorEvent() {
    }

    public static CameraMountErrorEvent a(int i) {
        CameraMountErrorEvent acquire = f8671a.acquire();
        if (acquire == null) {
            acquire = new CameraMountErrorEvent();
        }
        acquire.init(i);
        return acquire;
    }

    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_MOUNT_ERROR.toString();
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        return Arguments.createMap();
    }
}
