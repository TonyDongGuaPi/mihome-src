package com.xiaomi.smarthome.core.server.internal.device;

import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.device.ScanType;
import com.xiaomi.smarthome.core.server.internal.device.DeviceSearch;
import java.util.List;

public class MiTVMiWIFIDeviceSearch extends MiioDeviceSearchBase {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14540a = 2;

    public int b() {
        return 2;
    }

    public void a(ScanType scanType, DeviceSearch.DeviceSearchCallback deviceSearchCallback) {
        if (scanType.ordinal() == ScanType.ALL.ordinal()) {
            List<Device> c = MitvLocalDeviceManager.a().c();
            if (deviceSearchCallback != null) {
                deviceSearchCallback.a(b(), b(c));
                this.b.addAll(c);
            }
        } else if (deviceSearchCallback != null) {
            deviceSearchCallback.a(b(), b((List<Device>) null));
        }
    }
}
