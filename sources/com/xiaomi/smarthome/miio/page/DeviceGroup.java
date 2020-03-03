package com.xiaomi.smarthome.miio.page;

import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthomedevice.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeviceGroup implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    public String f19530a;
    public DeviceGroupType b;
    public List<Device> c;

    public enum DeviceGroupType {
        Router,
        Bluetooth,
        VirtualGroup,
        OtherGroup
    }

    public DeviceGroup(DeviceGroupType deviceGroupType) {
        this.f19530a = deviceGroupType.name();
        this.b = deviceGroupType;
        this.c = Collections.synchronizedList(new ArrayList());
    }

    public DeviceGroup(String str, DeviceGroupType deviceGroupType, String str2) {
        this.f19530a = str;
        this.b = deviceGroupType;
        this.c = new ArrayList();
    }

    public static String a(DeviceGroup deviceGroup) {
        if (deviceGroup == null) {
            return CommonApplication.getAppContext().getString(R.string.device_group_title_others);
        }
        if (deviceGroup.b == DeviceGroupType.Router) {
            String b2 = SmartHomeDeviceHelper.a().b().b(deviceGroup.f19530a, SmartHomeDeviceHelper.a().b().b(deviceGroup.f19530a));
            return b2 == null ? deviceGroup.f19530a : b2;
        }
        switch (deviceGroup.b) {
            case Bluetooth:
                return CommonApplication.getAppContext().getString(R.string.device_group_title_bluetooth);
            case VirtualGroup:
                return CommonApplication.getAppContext().getString(R.string.device_group_title_virtualgroup);
            case OtherGroup:
                return CommonApplication.getAppContext().getString(R.string.device_group_title_others);
            default:
                return CommonApplication.getAppContext().getString(R.string.device_group_title_others);
        }
    }

    public void a(Device device) {
        this.c.add(device);
    }

    public Device a(int i) {
        if (this.c != null && i < this.c.size()) {
            return this.c.get(i);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public Object clone() throws CloneNotSupportedException {
        DeviceGroup deviceGroup = (DeviceGroup) super.clone();
        deviceGroup.c = Collections.synchronizedList(new ArrayList(this.c.size()));
        for (Device add : this.c) {
            deviceGroup.c.add(add);
        }
        return deviceGroup;
    }

    public boolean a(int i, int i2) {
        if (i == i2 || i < 0 || i2 < 0 || i >= this.c.size() || i2 >= this.c.size()) {
            return false;
        }
        this.c.add(i2, this.c.remove(i));
        return true;
    }

    public int b(Device device) {
        if (device == null) {
            return -1;
        }
        for (int i = 0; i < this.c.size(); i++) {
            Device device2 = this.c.get(i);
            if (device2 != null && device2.did.equals(device.did)) {
                return i;
            }
        }
        return -1;
    }
}
