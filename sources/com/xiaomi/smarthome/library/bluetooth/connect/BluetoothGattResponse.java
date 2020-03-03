package com.xiaomi.smarthome.library.bluetooth.connect;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;

public class BluetoothGattResponse extends BluetoothGattCallback {

    /* renamed from: a  reason: collision with root package name */
    private IBluetoothGattResponse f18516a;

    public BluetoothGattResponse(IBluetoothGattResponse iBluetoothGattResponse) {
        this.f18516a = iBluetoothGattResponse;
    }

    public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        this.f18516a.a(i, i2);
    }

    public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        this.f18516a.a(i);
    }

    public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        this.f18516a.a(bluetoothGattCharacteristic, i, bluetoothGattCharacteristic.getValue());
    }

    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        this.f18516a.b(bluetoothGattCharacteristic, i, bluetoothGattCharacteristic.getValue());
    }

    public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.f18516a.a(bluetoothGattCharacteristic, bluetoothGattCharacteristic.getValue());
    }

    public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        this.f18516a.a(bluetoothGattDescriptor, i);
    }

    public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
        this.f18516a.b(i, i2);
    }

    public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
        this.f18516a.c(i, i2);
    }
}
