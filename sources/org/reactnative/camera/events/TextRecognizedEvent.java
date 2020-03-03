package org.reactnative.camera.events;

import android.support.v4.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.TouchesHelper;
import org.reactnative.camera.CameraViewManager;

public class TextRecognizedEvent extends Event<TextRecognizedEvent> {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.SynchronizedPool<TextRecognizedEvent> f4155a = new Pools.SynchronizedPool<>(3);
    private WritableArray b;

    private TextRecognizedEvent() {
    }

    public static TextRecognizedEvent a(int i, WritableArray writableArray) {
        TextRecognizedEvent acquire = f4155a.acquire();
        if (acquire == null) {
            acquire = new TextRecognizedEvent();
        }
        acquire.b(i, writableArray);
        return acquire;
    }

    private void b(int i, WritableArray writableArray) {
        super.init(i);
        this.b = writableArray;
    }

    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_TEXT_RECOGNIZED.toString();
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("type", "textBlock");
        createMap.putArray("textBlocks", this.b);
        createMap.putInt(TouchesHelper.TARGET_KEY, getViewTag());
        return createMap;
    }
}
