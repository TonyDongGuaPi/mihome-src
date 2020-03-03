package com.xiaomi.smarthome.core.server.internal.bluetooth.decorator;

import android.os.Bundle;
import com.xiaomi.smarthome.core.entity.device.BtDevice;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothService;

public class BluetoothDevicePermitLevelDecorator implements Decorator {

    /* renamed from: a  reason: collision with root package name */
    private static BluetoothDevicePermitLevelDecorator f14181a;

    public static BluetoothDevicePermitLevelDecorator a() {
        if (f14181a == null) {
            synchronized (BluetoothDevicePermitLevelDecorator.class) {
                if (f14181a == null) {
                    f14181a = new BluetoothDevicePermitLevelDecorator();
                }
            }
        }
        return f14181a;
    }

    public void a(BtDevice btDevice) {
        Bundle bundle = new Bundle();
        BluetoothService.a().a(btDevice.n(), 15, bundle);
        btDevice.d(bundle.getInt("extra.result", 0));
        if (btDevice.p() == 0) {
            btDevice.d(true);
        }
    }
}
