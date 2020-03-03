package com.xiaomi.smarthome.library.bluetooth.connect.listener;

import android.bluetooth.BluetoothGattCharacteristic;

public interface ReadCharacterListener extends GattResponseListener {
    void a(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, byte[] bArr);
}
