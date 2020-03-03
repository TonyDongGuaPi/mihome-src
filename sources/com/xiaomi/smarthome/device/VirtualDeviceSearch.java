package com.xiaomi.smarthome.device;

import com.xiaomi.smarthome.device.DeviceSearch;
import java.util.Collection;

public class VirtualDeviceSearch extends MiioDeviceSearchBase {
    public void a(Collection<? extends Device> collection, DeviceSearch.SearchDeviceListener searchDeviceListener) {
    }

    public int g() {
        return Device.PID_VIRTUAL_DEVICE;
    }
}
