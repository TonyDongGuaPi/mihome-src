package org.reactnative.camera.events;

import android.support.v4.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import org.reactnative.camera.CameraViewManager;

public class CameraMountErrorEvent extends Event<CameraMountErrorEvent> {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.SynchronizedPool<CameraMountErrorEvent> f4149a = new Pools.SynchronizedPool<>(3);
    private String b;

    public short getCoalescingKey() {
        return 0;
    }

    private CameraMountErrorEvent() {
    }

    public static CameraMountErrorEvent a(int i, String str) {
        CameraMountErrorEvent acquire = f4149a.acquire();
        if (acquire == null) {
            acquire = new CameraMountErrorEvent();
        }
        acquire.b(i, str);
        return acquire;
    }

    private void b(int i, String str) {
        super.init(i);
        this.b = str;
    }

    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_MOUNT_ERROR.toString();
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("message", this.b);
        return createMap;
    }
}
