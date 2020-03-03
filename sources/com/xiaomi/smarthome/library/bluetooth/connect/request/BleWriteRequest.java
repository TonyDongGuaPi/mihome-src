package com.xiaomi.smarthome.library.bluetooth.connect.request;

import android.bluetooth.BluetoothGattCharacteristic;
import com.xiaomi.smarthome.library.bluetooth.connect.BleResponser;
import com.xiaomi.smarthome.library.bluetooth.connect.listener.WriteCharacterListener;
import java.util.UUID;

public class BleWriteRequest extends BleRequest implements WriteCharacterListener {
    public BleWriteRequest(UUID uuid, UUID uuid2, byte[] bArr, BleResponser bleResponser) {
        super(bleResponser);
        this.k = uuid;
        this.l = uuid2;
        this.m = bArr;
    }

    public void i() {
        int e = e();
        if (e == 0) {
            e(-1);
        } else if (e == 2) {
            s();
        } else if (e != 19) {
            e(-1);
        } else {
            s();
        }
    }

    private void s() {
        if (!a(this.k, this.l, this.m)) {
            e(-1);
        } else {
            q();
        }
    }

    public void a(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, byte[] bArr) {
        r();
        if (i == 0) {
            e(0);
        } else {
            e(-1);
        }
    }
}
