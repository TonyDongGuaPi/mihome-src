package com.xiaomi.smarthome.core.server.internal.device;

import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.device.Location;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class MiioDeviceSearchBase extends DeviceSearch {
    List<Device> b = Collections.synchronizedList(new ArrayList());

    /* access modifiers changed from: protected */
    public List<Device> b(List<Device> list) {
        ArrayList<Device> arrayList = new ArrayList<>();
        for (Device next : this.b) {
            next.a(Location.REMOTE);
            arrayList.add(next);
        }
        if (list == null) {
            this.b.clear();
            return arrayList;
        }
        for (Device next2 : list) {
            boolean z = false;
            for (Device device : arrayList) {
                if (!(next2.k() == null || device.k() == null || !next2.k().equalsIgnoreCase(device.k()))) {
                    device.a(Location.LOCAL);
                    z = true;
                }
            }
            if (!z) {
                arrayList.add(next2);
            }
        }
        this.b.clear();
        this.b.addAll(list);
        return arrayList;
    }

    public List<Device> e() {
        return new ArrayList(this.b);
    }
}
