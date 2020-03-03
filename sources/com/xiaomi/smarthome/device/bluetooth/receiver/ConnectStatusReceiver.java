package com.xiaomi.smarthome.device.bluetooth.receiver;

import android.content.Context;
import android.content.Intent;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;

public class ConnectStatusReceiver extends AbsBluetoothReceiver {
    private static final String[] d = {"com.xiaomi.smarthome.bluetooth.connect_status_changed"};
    private static ConnectStatusReceiver e;

    private ConnectStatusReceiver() {
        a(d);
    }

    public static ConnectStatusReceiver b() {
        if (e == null) {
            e = new ConnectStatusReceiver();
        }
        return e;
    }

    public boolean a(Context context, Intent intent) {
        if (intent == null || !"com.xiaomi.smarthome.bluetooth.connect_status_changed".equals(intent.getAction())) {
            return false;
        }
        BLEDeviceManager.b(false);
        return true;
    }
}
