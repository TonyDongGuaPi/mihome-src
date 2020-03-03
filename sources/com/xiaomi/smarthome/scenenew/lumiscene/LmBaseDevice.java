package com.xiaomi.smarthome.scenenew.lumiscene;

import com.xiaomi.smarthome.device.api.BaseDevice;
import com.xiaomi.smarthome.device.api.DeviceStat;
import java.util.ArrayList;
import java.util.List;

public class LmBaseDevice extends BaseDevice {

    /* renamed from: a  reason: collision with root package name */
    public static final int f21950a = 0;
    public static final int b = 1;
    public static final int c = 2;
    protected List<String> d = new ArrayList();
    protected List<String> e = new ArrayList();

    public LmBaseDevice(DeviceStat deviceStat) {
        super(deviceStat);
    }

    public int hashCode() {
        if (this.mDeviceStat == null) {
            return 0;
        }
        return this.mDeviceStat.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if ((obj instanceof LmBaseDevice) && ((LmBaseDevice) obj).hashCode() == hashCode()) {
            return true;
        }
        return false;
    }
}
