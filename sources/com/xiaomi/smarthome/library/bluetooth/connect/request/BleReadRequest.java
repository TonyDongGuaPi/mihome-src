package com.xiaomi.smarthome.library.bluetooth.connect.request;

import android.bluetooth.BluetoothGattCharacteristic;
import com.xiaomi.smarthome.library.bluetooth.connect.BleResponser;
import com.xiaomi.smarthome.library.bluetooth.connect.listener.ReadCharacterListener;
import java.util.UUID;

public class BleReadRequest extends BleRequest implements ReadCharacterListener {
    public BleReadRequest(UUID uuid, UUID uuid2, BleResponser bleResponser) {
        super(bleResponser);
        this.k = uuid;
        this.l = uuid2;
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
        if (!a(this.k, this.l)) {
            e(-1);
        } else {
            q();
        }
    }

    public void a(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, byte[] bArr) {
        r();
        if (i == 0) {
            a("key_bytes", bArr);
            e(0);
            return;
        }
        e(-1);
    }
}
