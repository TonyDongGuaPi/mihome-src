package com.xiaomi.smarthome.device.bluetooth.receiver;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.smarthome.bluetooth.XmBluetoothDevice;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;

public class BondStateReceiver extends AbsBluetoothReceiver {
    private static final String[] d = {"android.bluetooth.device.action.ACL_CONNECTED", "android.bluetooth.device.action.ACL_DISCONNECTED", "android.bluetooth.device.action.BOND_STATE_CHANGED"};
    private static BondStateReceiver e;

    private BondStateReceiver() {
        a(d);
    }

    public static BondStateReceiver b() {
        if (e == null) {
            e = new BondStateReceiver();
        }
        return e;
    }

    public boolean a(Context context, Intent intent) {
        String action = intent.getAction();
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (bluetoothDevice == null) {
            return false;
        }
        XmBluetoothDevice xmBluetoothDevice = new XmBluetoothDevice();
        xmBluetoothDevice.device = bluetoothDevice;
        String j = BleCacheUtils.j(bluetoothDevice.getAddress());
        if (TextUtils.isEmpty(j)) {
            return false;
        }
        if ("android.bluetooth.device.action.ACL_CONNECTED".equalsIgnoreCase(action)) {
            PluginRecord d2 = CoreApi.a().d(j);
            if (d2 != null && d2.l()) {
                PluginApi.getInstance().sendMessage(context, d2, 6, intent, (DeviceStat) null, (RunningProcess) null, true, (PluginApi.SendMessageCallback) null);
            }
            return true;
        } else if ("android.bluetooth.device.action.ACL_DISCONNECTED".equalsIgnoreCase(action)) {
            PluginRecord d3 = CoreApi.a().d(j);
            if (d3 != null && d3.l()) {
                PluginApi.getInstance().sendMessage(context, d3, 7, intent, (DeviceStat) null, (RunningProcess) null, true, (PluginApi.SendMessageCallback) null);
            }
            return true;
        } else {
            if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equalsIgnoreCase(action)) {
                int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", 10);
                BleDevice a2 = BleDevice.a(xmBluetoothDevice);
                a2.model = j;
                if (intExtra == 10) {
                    BLEDeviceManager.a(a2);
                    return true;
                } else if (intExtra == 12) {
                    if (a2.i()) {
                        SmartHomeDeviceManager.a().p(a2.did);
                        BLEDeviceManager.b(true);
                    }
                    return true;
                }
            }
            return false;
        }
    }
}
