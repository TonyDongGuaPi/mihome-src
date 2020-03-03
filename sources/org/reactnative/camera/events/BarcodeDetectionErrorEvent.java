package org.reactnative.camera.events;

import android.support.v4.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import org.reactnative.barcodedetector.RNBarcodeDetector;
import org.reactnative.camera.CameraViewManager;

public class BarcodeDetectionErrorEvent extends Event<BarcodeDetectionErrorEvent> {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.SynchronizedPool<BarcodeDetectionErrorEvent> f4147a = new Pools.SynchronizedPool<>(3);
    private RNBarcodeDetector b;

    public short getCoalescingKey() {
        return 0;
    }

    private BarcodeDetectionErrorEvent() {
    }

    public static BarcodeDetectionErrorEvent a(int i, RNBarcodeDetector rNBarcodeDetector) {
        BarcodeDetectionErrorEvent acquire = f4147a.acquire();
        if (acquire == null) {
            acquire = new BarcodeDetectionErrorEvent();
        }
        acquire.b(i, rNBarcodeDetector);
        return acquire;
    }

    private void b(int i, RNBarcodeDetector rNBarcodeDetector) {
        super.init(i);
        this.b = rNBarcodeDetector;
    }

    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_BARCODE_DETECTION_ERROR.toString();
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("isOperational", this.b != null && this.b.a());
        return createMap;
    }
}
