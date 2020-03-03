package com.reactnative.camera.events;

import android.support.v4.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.google.zxing.Result;
import com.reactnative.camera.CameraViewManager;
import javax.jmdns.impl.constants.DNSRecordClass;

public class BarCodeReadEvent extends Event<BarCodeReadEvent> {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.SynchronizedPool<BarCodeReadEvent> f8670a = new Pools.SynchronizedPool<>(3);
    private Result b;

    private BarCodeReadEvent() {
    }

    public static BarCodeReadEvent a(int i, Result result) {
        BarCodeReadEvent acquire = f8670a.acquire();
        if (acquire == null) {
            acquire = new BarCodeReadEvent();
        }
        acquire.b(i, result);
        return acquire;
    }

    private void b(int i, Result result) {
        super.init(i);
        this.b = result;
    }

    public short getCoalescingKey() {
        return (short) (this.b.getText().hashCode() % DNSRecordClass.CLASS_MASK);
    }

    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_BAR_CODE_READ.toString();
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt(TouchesHelper.TARGET_KEY, getViewTag());
        createMap.putString("data", this.b.getText());
        createMap.putString("type", this.b.getBarcodeFormat().toString());
        return createMap;
    }
}
