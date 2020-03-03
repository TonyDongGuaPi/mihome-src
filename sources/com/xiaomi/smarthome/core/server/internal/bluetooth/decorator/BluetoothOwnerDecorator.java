package com.xiaomi.smarthome.core.server.internal.bluetooth.decorator;

import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.device.BtDevice;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;

public class BluetoothOwnerDecorator implements Decorator {

    /* renamed from: a  reason: collision with root package name */
    private static BluetoothOwnerDecorator f14182a;

    public static BluetoothOwnerDecorator a() {
        if (f14182a == null) {
            synchronized (BluetoothOwnerDecorator.class) {
                if (f14182a == null) {
                    f14182a = new BluetoothOwnerDecorator();
                }
            }
        }
        return f14182a;
    }

    public void a(BtDevice btDevice) {
        String d = BluetoothCache.d(btDevice.n());
        if (!TextUtils.isEmpty(d)) {
            btDevice.m(d);
        }
        String b = BluetoothCache.b(btDevice.n());
        if (!TextUtils.isEmpty(b)) {
            btDevice.l(b);
        }
        btDevice.d(BluetoothCache.e(btDevice.n()));
    }
}
