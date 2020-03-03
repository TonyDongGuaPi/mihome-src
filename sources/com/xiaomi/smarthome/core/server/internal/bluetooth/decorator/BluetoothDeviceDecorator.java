package com.xiaomi.smarthome.core.server.internal.bluetooth.decorator;

import com.xiaomi.smarthome.core.entity.device.BtDevice;

public class BluetoothDeviceDecorator implements Decorator {

    /* renamed from: a  reason: collision with root package name */
    private static Decorator[] f14177a = {BluetoothDeviceModelDecorator.a(), BluetoothDeviceNameDecorator.a(), BluetoothDeviceDidDecorator.a(), BluetoothOwnerDecorator.a()};
    private static BluetoothDeviceDecorator b;

    public static BluetoothDeviceDecorator a() {
        if (b == null) {
            synchronized (BluetoothDeviceDecorator.class) {
                if (b == null) {
                    b = new BluetoothDeviceDecorator();
                }
            }
        }
        return b;
    }

    public void a(BtDevice btDevice) {
        for (Decorator a2 : f14177a) {
            a2.a(btDevice);
        }
    }
}
