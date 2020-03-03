package com.xiaomi.smarthome.device;

import com.xiaomi.smarthome.device.DeviceSearch;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PhoneIRDeviceSearch extends DeviceSearch<Device> {

    /* renamed from: a  reason: collision with root package name */
    List<Device> f14930a = Collections.synchronizedList(new ArrayList(1));

    public void a(Collection<? extends Device> collection, DeviceSearch.SearchDeviceListener searchDeviceListener) {
    }

    public void c() {
        this.f14930a.clear();
        if (IRDeviceUtil.c()) {
            this.f14930a.add(IRDeviceUtil.b());
        }
        this.h = true;
    }

    public List<Device> d() {
        return this.f14930a;
    }

    public void e() {
        this.f14930a.clear();
    }
}
