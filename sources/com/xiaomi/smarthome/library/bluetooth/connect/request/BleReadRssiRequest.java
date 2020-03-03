package com.xiaomi.smarthome.library.bluetooth.connect.request;

import com.xiaomi.smarthome.library.bluetooth.connect.BleResponser;
import com.xiaomi.smarthome.library.bluetooth.connect.listener.ReadRssiListener;

public class BleReadRssiRequest extends BleRequest implements ReadRssiListener {
    public BleReadRssiRequest(BleResponser bleResponser) {
        super(bleResponser);
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
        if (!g()) {
            e(-1);
        } else {
            q();
        }
    }

    public void a(int i, int i2) {
        r();
        if (i2 == 0) {
            a("key_rssi", i);
            e(0);
            return;
        }
        e(-1);
    }
}
