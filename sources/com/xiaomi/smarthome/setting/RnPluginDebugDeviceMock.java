package com.xiaomi.smarthome.setting;

import com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.api.DeviceStat;

public class RnPluginDebugDeviceMock {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22091a = "t.t.t";
    public static final String b = "00-00-00-00-00-00";
    public static final String c = "00-00-00-00-00-00";
    public static final long d = 2100000000;
    public static final long e = 2100000000;
    public static final long f = 2100000000;
    public static final long g = 2100000000;
    public static final String h = "00-00-00-00-00-00";

    public static PluginRecord a() {
        PluginRecord pluginRecord = new PluginRecord();
        PluginDeviceInfo pluginDeviceInfo = new PluginDeviceInfo();
        pluginDeviceInfo.a(f22091a);
        pluginDeviceInfo.b(f22091a);
        pluginRecord.a(pluginDeviceInfo);
        PluginPackageInfo pluginPackageInfo = new PluginPackageInfo();
        pluginPackageInfo.b(2100000000);
        pluginPackageInfo.c("rn");
        PluginRecord pluginRecord2 = pluginRecord;
        PluginPackageInfo pluginPackageInfo2 = pluginPackageInfo;
        pluginRecord2.b(2100000000, 2100000000, pluginPackageInfo2, (PluginDeveloperInfo) null);
        pluginRecord2.a(2100000000, 2100000000, pluginPackageInfo2, (PluginDeveloperInfo) null);
        return pluginRecord;
    }

    public static DeviceStat b() {
        DeviceStat deviceStat = new DeviceStat();
        deviceStat.pid = 0;
        deviceStat.did = "00-00-00-00-00-00";
        deviceStat.model = f22091a;
        deviceStat.isOnline = true;
        deviceStat.name = "mock_name";
        return deviceStat;
    }
}
