package org.reactnative.camera.events;

import android.support.v4.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.TouchesHelper;
import kotlin.jvm.internal.ShortCompanionObject;
import org.reactnative.camera.CameraViewManager;

public class FacesDetectedEvent extends Event<FacesDetectedEvent> {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.SynchronizedPool<FacesDetectedEvent> f4152a = new Pools.SynchronizedPool<>(3);
    private WritableArray b;

    private FacesDetectedEvent() {
    }

    public static FacesDetectedEvent a(int i, WritableArray writableArray) {
        FacesDetectedEvent acquire = f4152a.acquire();
        if (acquire == null) {
            acquire = new FacesDetectedEvent();
        }
        acquire.b(i, writableArray);
        return acquire;
    }

    private void b(int i, WritableArray writableArray) {
        super.init(i);
        this.b = writableArray;
    }

    public short getCoalescingKey() {
        if (this.b.size() > 32767) {
            return ShortCompanionObject.b;
        }
        return (short) this.b.size();
    }

    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_FACES_DETECTED.toString();
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("type", "face");
        createMap.putArray("faces", this.b);
        createMap.putInt(TouchesHelper.TARGET_KEY, getViewTag());
        return createMap;
    }
}
