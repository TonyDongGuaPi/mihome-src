package com.xiaomi.smarthome.library.bluetooth.connect;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;

public interface IBluetoothGattResponse {
    void a(int i);

    void a(int i, int i2);

    void a(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, byte[] bArr);

    void a(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr);

    void a(BluetoothGattDescriptor bluetoothGattDescriptor, int i);

    void b(int i, int i2);

    void b(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, byte[] bArr);

    void c(int i, int i2);
}
