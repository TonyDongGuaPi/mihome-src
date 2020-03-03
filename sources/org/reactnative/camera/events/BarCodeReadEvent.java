package org.reactnative.camera.events;

import android.support.v4.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.taobao.weex.common.Constants;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.ResultPoint;
import java.util.Formatter;
import javax.jmdns.impl.constants.DNSRecordClass;
import org.reactnative.camera.CameraViewManager;

public class BarCodeReadEvent extends Event<BarCodeReadEvent> {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.SynchronizedPool<BarCodeReadEvent> f4146a = new Pools.SynchronizedPool<>(3);
    private Result b;
    private int c;
    private int d;

    private BarCodeReadEvent() {
    }

    public static BarCodeReadEvent a(int i, Result result, int i2, int i3) {
        BarCodeReadEvent acquire = f4146a.acquire();
        if (acquire == null) {
            acquire = new BarCodeReadEvent();
        }
        acquire.b(i, result, i2, i3);
        return acquire;
    }

    private void b(int i, Result result, int i2, int i3) {
        super.init(i);
        this.b = result;
        this.c = i2;
        this.d = i3;
    }

    public short getCoalescingKey() {
        return (short) (this.b.a().hashCode() % DNSRecordClass.CLASS_MASK);
    }

    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_BAR_CODE_READ.toString();
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        createMap.putInt(TouchesHelper.TARGET_KEY, getViewTag());
        createMap.putString("data", this.b.a());
        byte[] b2 = this.b.b();
        if (b2 != null && b2.length > 0) {
            Formatter formatter = new Formatter();
            int length = b2.length;
            for (int i = 0; i < length; i++) {
                formatter.format("%02x", new Object[]{Byte.valueOf(b2[i])});
            }
            createMap.putString("rawData", formatter.toString());
            formatter.close();
        }
        createMap.putString("type", this.b.d().toString());
        WritableArray createArray = Arguments.createArray();
        for (ResultPoint resultPoint : this.b.c()) {
            if (resultPoint != null) {
                WritableMap createMap3 = Arguments.createMap();
                createMap3.putString("x", String.valueOf(resultPoint.a()));
                createMap3.putString(Constants.Name.Y, String.valueOf(resultPoint.b()));
                createArray.pushMap(createMap3);
            }
        }
        createMap2.putArray("origin", createArray);
        createMap2.putInt("height", this.d);
        createMap2.putInt("width", this.c);
        createMap.putMap("bounds", createMap2);
        return createMap;
    }
}
