package org.reactnative.camera.events;

import android.support.v4.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import org.reactnative.camera.CameraViewManager;

public class PictureTakenEvent extends Event<PictureTakenEvent> {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.SynchronizedPool<PictureTakenEvent> f4154a = new Pools.SynchronizedPool<>(3);

    public short getCoalescingKey() {
        return 0;
    }

    private PictureTakenEvent() {
    }

    public static PictureTakenEvent a(int i) {
        PictureTakenEvent acquire = f4154a.acquire();
        if (acquire == null) {
            acquire = new PictureTakenEvent();
        }
        acquire.init(i);
        return acquire;
    }

    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_PICTURE_TAKEN.toString();
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        return Arguments.createMap();
    }
}
