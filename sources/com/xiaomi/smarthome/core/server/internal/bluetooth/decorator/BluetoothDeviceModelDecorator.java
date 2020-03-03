package com.xiaomi.smarthome.core.server.internal.bluetooth.decorator;

import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.device.BtDevice;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;

public class BluetoothDeviceModelDecorator implements Decorator {

    /* renamed from: a  reason: collision with root package name */
    private static BluetoothDeviceModelDecorator f14179a;

    public static BluetoothDeviceModelDecorator a() {
        if (f14179a == null) {
            synchronized (BluetoothDeviceModelDecorator.class) {
                if (f14179a == null) {
                    f14179a = new BluetoothDeviceModelDecorator();
                }
            }
        }
        return f14179a;
    }

    public void a(BtDevice btDevice) {
        if (TextUtils.isEmpty(btDevice.l())) {
            String j = BluetoothCache.j(btDevice.n());
            if (!TextUtils.isEmpty(j)) {
                btDevice.b(j);
            }
        }
    }
}
