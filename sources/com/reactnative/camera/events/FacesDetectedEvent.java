package com.reactnative.camera.events;

import android.support.v4.util.Pools;
import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.google.android.gms.vision.face.Face;
import com.reactnative.camera.CameraViewManager;
import com.reactnative.camera.facedetector.FaceDetectorUtils;
import com.reactnative.camera.utils.ImageDimensions;
import kotlin.jvm.internal.ShortCompanionObject;

public class FacesDetectedEvent extends Event<FacesDetectedEvent> {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.SynchronizedPool<FacesDetectedEvent> f8674a = new Pools.SynchronizedPool<>(3);
    private double b;
    private double c;
    private SparseArray<Face> d;
    private ImageDimensions e;

    private FacesDetectedEvent() {
    }

    public static FacesDetectedEvent a(int i, SparseArray<Face> sparseArray, ImageDimensions imageDimensions, double d2, double d3) {
        FacesDetectedEvent acquire = f8674a.acquire();
        if (acquire == null) {
            acquire = new FacesDetectedEvent();
        }
        acquire.b(i, sparseArray, imageDimensions, d2, d3);
        return acquire;
    }

    private void b(int i, SparseArray<Face> sparseArray, ImageDimensions imageDimensions, double d2, double d3) {
        super.init(i);
        this.d = sparseArray;
        this.e = imageDimensions;
        this.b = d2;
        this.c = d3;
    }

    public short getCoalescingKey() {
        if (this.d.size() > 32767) {
            return ShortCompanionObject.b;
        }
        return (short) this.d.size();
    }

    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_FACES_DETECTED.toString();
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        WritableMap writableMap;
        WritableArray createArray = Arguments.createArray();
        for (int i = 0; i < this.d.size(); i++) {
            WritableMap a2 = FaceDetectorUtils.a(this.d.valueAt(i), this.b, this.c);
            if (this.e.e() == 1) {
                writableMap = FaceDetectorUtils.a(a2, this.e.b(), this.b);
            } else {
                writableMap = FaceDetectorUtils.a(a2);
            }
            createArray.pushMap(writableMap);
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putString("type", "face");
        createMap.putArray("faces", createArray);
        createMap.putInt(TouchesHelper.TARGET_KEY, getViewTag());
        return createMap;
    }
}
