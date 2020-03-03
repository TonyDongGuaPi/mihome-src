package com.xiaomi.smarthome.library.bluetooth.connect.request;

import android.bluetooth.BluetoothGattDescriptor;
import com.xiaomi.smarthome.library.bluetooth.connect.BleResponser;
import com.xiaomi.smarthome.library.bluetooth.connect.listener.WriteDescriptorListener;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.util.UUID;

public class BleNotifyRequest extends BleRequest implements WriteDescriptorListener {
    public BleNotifyRequest(UUID uuid, UUID uuid2, BleResponser bleResponser) {
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
        BluetoothLog.c("openNotify...ServiceUUID is " + this.k + "  CharacterUUID is " + this.l);
        if (!a(this.k, this.l, true)) {
            e(-1);
        } else {
            q();
        }
    }

    public void a(BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        r();
        if (i == 0) {
            e(0);
        } else {
            e(-1);
        }
    }
}
