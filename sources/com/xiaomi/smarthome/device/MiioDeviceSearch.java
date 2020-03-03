package com.xiaomi.smarthome.device;

import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceSearch;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.miio.Miio;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiioDeviceSearch extends MiioDeviceSearchBase {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14897a = 0;

    public int g() {
        return 0;
    }

    public void f() {
        this.d = null;
    }

    public void a(Collection<? extends Device> collection, DeviceSearch.SearchDeviceListener searchDeviceListener) {
        if (CoreApi.a().q()) {
            ArrayList<Device> arrayList = new ArrayList<>();
            for (Device device : collection) {
                if (device instanceof MiioDeviceV2) {
                    arrayList.add((MiioDeviceV2) device);
                }
            }
            if (this.d == null) {
                this.d = new HashMap();
            }
            for (Map.Entry value : this.d.entrySet()) {
                Device device2 = (Device) value.getValue();
            }
            for (Device device3 : arrayList) {
                if ((device3 instanceof MiioDeviceV2) && device3.resetFlag == 1) {
                    this.d.remove(device3.did);
                }
            }
            List<MiioDeviceV2> a2 = a(this.d, arrayList);
            if (a2.size() > 0) {
                for (MiioDeviceV2 miioDeviceV2 : a2) {
                    Miio.g("result addDevices" + miioDeviceV2.did + miioDeviceV2.token + miioDeviceV2.ip);
                    miioDeviceV2.location = Device.Location.LOCAL;
                    DeviceFactory.a((Device) miioDeviceV2);
                    miioDeviceV2.isNew = true;
                }
                searchDeviceListener.a(a2);
                this.d = b(arrayList);
            }
        }
    }
}
