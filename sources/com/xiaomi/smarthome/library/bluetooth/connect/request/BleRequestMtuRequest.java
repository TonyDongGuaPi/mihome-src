package com.xiaomi.smarthome.library.bluetooth.connect.request;

import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.connect.BleResponser;
import com.xiaomi.smarthome.library.bluetooth.connect.listener.RequestMtuListener;

public class BleRequestMtuRequest extends BleRequest implements RequestMtuListener {
    private int h;

    public BleRequestMtuRequest(int i, BleResponser bleResponser) {
        super(bleResponser);
        this.h = i;
    }

    public void i() {
        int e = e();
        if (e == 0) {
            e(-1);
        } else if (e == 2) {
            f(this.h);
        } else if (e != 19) {
            e(-1);
        } else {
            f(this.h);
        }
    }

    private void f(int i) {
        if (!d(i)) {
            e(-1);
        } else {
            q();
        }
    }

    public void a(int i, int i2) {
        r();
        if (i2 == 0) {
            a(BluetoothConstants.l, i);
            e(0);
            return;
        }
        e(-1);
    }
}
