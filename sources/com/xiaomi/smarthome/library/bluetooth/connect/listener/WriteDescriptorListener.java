package com.xiaomi.smarthome.library.bluetooth.connect.listener;

import android.bluetooth.BluetoothGattDescriptor;

public interface WriteDescriptorListener extends GattResponseListener {
    void a(BluetoothGattDescriptor bluetoothGattDescriptor, int i);
}
