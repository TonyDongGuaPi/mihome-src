package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.filter;

import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;

public abstract class BluetoothDeviceFilter implements IBluetoothFilter {
    public boolean c() {
        return GlobalDynamicSettingManager.a().e();
    }
}
