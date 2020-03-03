package org.reactnative.camera.events;

import android.support.v4.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.xiaomi.qrcode2.BarcodeGenActivity;
import kotlin.jvm.internal.ShortCompanionObject;
import org.reactnative.camera.CameraViewManager;

public class BarcodesDetectedEvent extends Event<BarcodesDetectedEvent> {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.SynchronizedPool<BarcodesDetectedEvent> f4148a = new Pools.SynchronizedPool<>(3);
    private WritableArray b;

    private BarcodesDetectedEvent() {
    }

    public static BarcodesDetectedEvent a(int i, WritableArray writableArray) {
        BarcodesDetectedEvent acquire = f4148a.acquire();
        if (acquire == null) {
            acquire = new BarcodesDetectedEvent();
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
        return CameraViewManager.Events.EVENT_ON_BARCODES_DETECTED.toString();
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("type", BarcodeGenActivity.BARCODE);
        createMap.putArray("barcodes", this.b);
        createMap.putInt(TouchesHelper.TARGET_KEY, getViewTag());
        return createMap;
    }
}
