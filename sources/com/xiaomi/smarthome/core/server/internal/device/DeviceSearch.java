package com.xiaomi.smarthome.core.server.internal.device;

import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.device.ScanType;
import java.util.List;

public abstract class DeviceSearch {

    public static abstract class DeviceSearchCallback {
        public abstract void a(int i, Object obj);

        public abstract void a(int i, List<Device> list);
    }

    public void a(ScanType scanType, DeviceSearchCallback deviceSearchCallback) {
    }

    public abstract int b();

    public List<Device> e() {
        return null;
    }
}
