package com.xiaomi.smarthome.newui.adapter;

import android.support.v7.util.DiffUtil;
import android.text.TextUtils;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.devicelistswitch.model.DeviceListSwitchManager;
import java.util.List;
import java.util.Map;

public class DevicesGridDiffCallback extends DiffUtil.Callback {

    /* renamed from: a  reason: collision with root package name */
    private List<Device> f20391a;
    private List<Device> b;
    private Map<String, Boolean> c;

    public DevicesGridDiffCallback(List<Device> list, List<Device> list2, Map<String, Boolean> map) {
        this.b = list;
        this.f20391a = list2;
        this.c = map;
    }

    public int getOldListSize() {
        if (this.f20391a == null) {
            return 0;
        }
        return this.f20391a.size();
    }

    public int getNewListSize() {
        if (this.b == null) {
            return 0;
        }
        return this.b.size();
    }

    public boolean areItemsTheSame(int i, int i2) {
        Device device = this.f20391a.get(i);
        Device device2 = this.b.get(i2);
        return (device == null || device2 == null || !TextUtils.equals(device.did, device2.did)) ? false : true;
    }

    public boolean areContentsTheSame(int i, int i2) {
        Device device = this.f20391a.get(i);
        Device device2 = this.b.get(i2);
        if (device == null || device2 == null) {
            return false;
        }
        if ((!IRDeviceUtil.a(device.did) || !IRDeviceUtil.a(device2.did)) && a(device2) && TextUtils.equals(device.name, device2.name) && TextUtils.equals(device.userId, device2.userId) && TextUtils.equals(device.model, device2.model) && device.isOnline == device2.isOnline && device.isNew == device2.isNew && device.isConnected == device2.isConnected && TextUtils.equals(device.property.getString(DeviceListSwitchManager.f15515a, ""), device2.property.getString(DeviceListSwitchManager.f15515a, "")) && TextUtils.equals(device.desc, device2.desc) && TextUtils.equals(device.descNew, device2.descNew) && TextUtils.equals(device.descTimeJString, device2.descTimeJString)) {
            return true;
        }
        return false;
    }

    private boolean a(Device device) {
        return this.c.remove(device.did) == null;
    }
}
