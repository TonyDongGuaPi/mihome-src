package com.xiaomi.smarthome.core.server.internal.bluetooth.decorator;

import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.device.BtDevice;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;

public class BluetoothDeviceDidDecorator implements Decorator {

    /* renamed from: a  reason: collision with root package name */
    private static BluetoothDeviceDidDecorator f14178a;

    public static BluetoothDeviceDidDecorator a() {
        if (f14178a == null) {
            synchronized (BluetoothDeviceDidDecorator.class) {
                if (f14178a == null) {
                    f14178a = new BluetoothDeviceDidDecorator();
                }
            }
        }
        return f14178a;
    }

    public void a(BtDevice btDevice) {
        String f = BluetoothCache.f(btDevice.n());
        if (!TextUtils.isEmpty(f)) {
            btDevice.a(f);
        }
        if (TextUtils.isEmpty(btDevice.k())) {
            btDevice.a(btDevice.n());
        }
    }
}
