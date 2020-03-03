package com.xiaomi.smarthome.newui.adapter.main_grid;

import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.stat.STAT;

public class CardLog {
    public static void a(Device device, String str, int i, int i2, int i3) {
        if (device != null) {
            STAT.d.a(device.model, str, SmartHomeDeviceManager.e(device), i, i2, i3, device.did);
        }
    }

    public static void b(Device device, String str, int i, int i2, int i3) {
        if (device != null) {
            STAT.d.b(device.model, str, SmartHomeDeviceManager.e(device), i, i2, i3, device.did);
        }
    }

    public static void a(Device device, String str) {
        if (device != null) {
            STAT.d.b(device.model, str, SmartHomeDeviceManager.e(device));
        }
    }
}
