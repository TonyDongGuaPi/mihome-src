package com.xiaomi.smarthome.device.bluetooth.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.MiioBtSearchResponse;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.util.ArrayList;
import java.util.List;

public class BluetoothStateReceiver extends AbsBluetoothReceiver {
    private static final String[] d = {"android.bluetooth.adapter.action.STATE_CHANGED"};
    private static BluetoothStateReceiver e;
    /* access modifiers changed from: private */
    public List<BluetoothStateChangeListener> f = new ArrayList();

    public interface BluetoothStateChangeListener {
        void a(int i, int i2);
    }

    private String a(int i) {
        switch (i) {
            case 10:
                return "state_off";
            case 11:
                return "state_turning_on";
            case 12:
                return "state_on";
            case 13:
                return "state_turning_off";
            default:
                return "unknown";
        }
    }

    private BluetoothStateReceiver() {
        a(d);
    }

    public void a(final BluetoothStateChangeListener bluetoothStateChangeListener) {
        this.c.post(new Runnable() {
            public void run() {
                if (bluetoothStateChangeListener != null && !BluetoothStateReceiver.this.f.contains(bluetoothStateChangeListener)) {
                    BluetoothStateReceiver.this.f.add(bluetoothStateChangeListener);
                }
            }
        });
    }

    public void b(final BluetoothStateChangeListener bluetoothStateChangeListener) {
        this.c.post(new Runnable() {
            public void run() {
                if (bluetoothStateChangeListener != null) {
                    BluetoothStateReceiver.this.f.remove(bluetoothStateChangeListener);
                }
            }
        });
    }

    public static BluetoothStateReceiver b() {
        if (e == null) {
            e = new BluetoothStateReceiver();
        }
        return e;
    }

    public boolean a(Context context, Intent intent) {
        if (!"android.bluetooth.adapter.action.STATE_CHANGED".equalsIgnoreCase(intent.getAction())) {
            return false;
        }
        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
        int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", 0);
        BluetoothLog.c(String.format("state changed: %s -> %s", new Object[]{a(intExtra2), a(intExtra)}));
        a(intExtra2, intExtra);
        if (intExtra == 10) {
            c();
        } else if (intExtra == 12) {
            d();
        }
        return true;
    }

    private void a(int i, int i2) {
        for (BluetoothStateChangeListener a2 : this.f) {
            a2.a(i, i2);
        }
    }

    private void c() {
        BluetoothLog.c("onBluetoothTurnedOff");
        Log.i("stopScan", "BSR turn off stop");
        BluetoothHelper.b();
        BluetoothHelper.c();
    }

    private void d() {
        BluetoothLog.c("onBluetoothTurnedOn");
        if (CommonApplication.getApplication().isAppForeground()) {
            BLEDeviceManager.a(new SearchRequest.Builder().a(8000, 1).a(), (MiioBtSearchResponse) null);
        } else {
            Log.v("BluetoothStateReceiver", "app is on background, don't search ble device");
        }
    }
}
