package org.reactnative.camera.events;

import android.support.v4.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import org.reactnative.camera.CameraViewManager;
import org.reactnative.facedetector.RNFaceDetector;

public class FaceDetectionErrorEvent extends Event<FaceDetectionErrorEvent> {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.SynchronizedPool<FaceDetectionErrorEvent> f4151a = new Pools.SynchronizedPool<>(3);
    private RNFaceDetector b;

    public short getCoalescingKey() {
        return 0;
    }

    private FaceDetectionErrorEvent() {
    }

    public static FaceDetectionErrorEvent a(int i, RNFaceDetector rNFaceDetector) {
        FaceDetectionErrorEvent acquire = f4151a.acquire();
        if (acquire == null) {
            acquire = new FaceDetectionErrorEvent();
        }
        acquire.b(i, rNFaceDetector);
        return acquire;
    }

    private void b(int i, RNFaceDetector rNFaceDetector) {
        super.init(i);
        this.b = rNFaceDetector;
    }

    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_FACE_DETECTION_ERROR.toString();
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
