package org.reactnative.camera.events;

import android.support.v4.util.Pools;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import javax.jmdns.impl.constants.DNSRecordClass;
import org.reactnative.camera.CameraViewManager;

public class PictureSavedEvent extends Event<PictureSavedEvent> {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.SynchronizedPool<PictureSavedEvent> f4153a = new Pools.SynchronizedPool<>(5);
    private WritableMap b;

    private PictureSavedEvent() {
    }

    public static PictureSavedEvent a(int i, WritableMap writableMap) {
        PictureSavedEvent acquire = f4153a.acquire();
        if (acquire == null) {
            acquire = new PictureSavedEvent();
        }
        acquire.b(i, writableMap);
        return acquire;
    }

    private void b(int i, WritableMap writableMap) {
        super.init(i);
        this.b = writableMap;
    }

    public short getCoalescingKey() {
        return (short) (this.b.getMap("data").getString("uri").hashCode() % DNSRecordClass.CLASS_MASK);
    }

    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_PICTURE_SAVED.toString();
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), this.b);
    }
}
