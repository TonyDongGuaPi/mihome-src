package org.reactnative.camera.events;

import android.support.v4.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import org.reactnative.camera.CameraViewManager;

public class CameraReadyEvent extends Event<CameraReadyEvent> {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.SynchronizedPool<CameraReadyEvent> f4150a = new Pools.SynchronizedPool<>(3);

    public short getCoalescingKey() {
        return 0;
    }

    private CameraReadyEvent() {
    }

    public static CameraReadyEvent a(int i) {
        CameraReadyEvent acquire = f4150a.acquire();
        if (acquire == null) {
            acquire = new CameraReadyEvent();
        }
        acquire.init(i);
        return acquire;
    }

    public String getEventName() {
        return CameraViewManager.Events.EVENT_CAMERA_READY.toString();
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        return Arguments.createMap();
    }
}
