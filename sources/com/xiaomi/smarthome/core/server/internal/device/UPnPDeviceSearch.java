package com.xiaomi.smarthome.core.server.internal.device;

import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.device.ScanType;
import com.xiaomi.smarthome.core.server.internal.device.DeviceSearch;
import java.util.ArrayList;
import java.util.List;

public class UPnPDeviceSearch extends MiioDeviceSearchBase {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14548a = 8;

    public int b() {
        return 8;
    }

    public void a(ScanType scanType, DeviceSearch.DeviceSearchCallback deviceSearchCallback) {
        deviceSearchCallback.a(b(), (List<Device>) new ArrayList());
    }
}
