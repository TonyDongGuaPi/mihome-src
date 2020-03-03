package com.xiaomi.smarthome.core.server;

import android.content.Intent;
import android.os.Bundle;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.api.DeviceStat;

public abstract class CoreBridge {

    /* renamed from: a  reason: collision with root package name */
    protected static CoreBridge f1509a;

    public abstract void a(DeviceStat deviceStat);

    public abstract void a(DeviceStat deviceStat, Intent intent);

    public abstract void a(DeviceStat deviceStat, PluginRecord pluginRecord, Bundle bundle);

    public abstract boolean a(String str);

    public abstract void b();

    public abstract void b(DeviceStat deviceStat);

    public abstract byte c();

    public abstract void c(DeviceStat deviceStat);

    public abstract Class d();

    public abstract void e();

    public static CoreBridge a() {
        return f1509a;
    }
}
