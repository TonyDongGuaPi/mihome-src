package com.xiaomi.smarthome.core.server.internal.plugin.util;

import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.plugin.entity.DeveloperResult;
import com.xiaomi.smarthome.core.server.internal.plugin.entity.DeviceResult;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class PluginUtils {
    public static void a(DeviceResult deviceResult, PluginDeviceInfo pluginDeviceInfo) {
        pluginDeviceInfo.a(deviceResult.c);
        pluginDeviceInfo.a(deviceResult.d);
        pluginDeviceInfo.d(deviceResult.e);
        pluginDeviceInfo.b(deviceResult.f);
        pluginDeviceInfo.c(deviceResult.g);
        pluginDeviceInfo.d(deviceResult.h);
        pluginDeviceInfo.e(deviceResult.i);
        pluginDeviceInfo.f(deviceResult.j);
        pluginDeviceInfo.g(deviceResult.k);
        boolean z = false;
        pluginDeviceInfo.c(deviceResult.l == 1);
        pluginDeviceInfo.i(deviceResult.n);
        pluginDeviceInfo.e(deviceResult.o);
        if (deviceResult.m != null) {
            pluginDeviceInfo.h(deviceResult.m.f14692a);
            pluginDeviceInfo.j(deviceResult.m.b);
        }
        pluginDeviceInfo.f(deviceResult.p);
        pluginDeviceInfo.k(deviceResult.q);
        pluginDeviceInfo.l(deviceResult.r);
        pluginDeviceInfo.g(deviceResult.s);
        pluginDeviceInfo.h(deviceResult.t);
        pluginDeviceInfo.n(deviceResult.u);
        pluginDeviceInfo.a(deviceResult.v == 1);
        pluginDeviceInfo.i(deviceResult.w);
        pluginDeviceInfo.m(deviceResult.x);
        pluginDeviceInfo.m(deviceResult.y);
        pluginDeviceInfo.j(deviceResult.z);
        pluginDeviceInfo.k(deviceResult.A);
        pluginDeviceInfo.l(deviceResult.B);
        pluginDeviceInfo.b(deviceResult.G);
        pluginDeviceInfo.c(deviceResult.H);
        pluginDeviceInfo.a(deviceResult.I);
        pluginDeviceInfo.o(deviceResult.C);
        pluginDeviceInfo.p(deviceResult.D);
        pluginDeviceInfo.b(deviceResult.J);
        if (deviceResult.K != 0) {
            z = true;
        }
        pluginDeviceInfo.b(z);
        pluginDeviceInfo.q(deviceResult.F);
        pluginDeviceInfo.c(deviceResult.L);
        pluginDeviceInfo.r(deviceResult.M);
        pluginDeviceInfo.s(deviceResult.N);
        pluginDeviceInfo.v(deviceResult.O);
        pluginDeviceInfo.n(deviceResult.P);
        pluginDeviceInfo.u(deviceResult.Q);
    }

    public static void a(DeveloperResult developerResult, PluginDeveloperInfo pluginDeveloperInfo) {
        pluginDeveloperInfo.a(developerResult.f14690a);
        pluginDeveloperInfo.a(developerResult.b);
    }

    public static List<String> a(Map<String, PluginRecord> map, List<DeviceResult> list) {
        HashSet hashSet = new HashSet();
        for (DeviceResult next : list) {
            if (!TextUtils.isEmpty(next.c)) {
                hashSet.add(next.c);
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, PluginRecord> value : map.entrySet()) {
            String o = ((PluginRecord) value.getValue()).o();
            if (!hashSet.contains(o)) {
                arrayList.add(o);
            }
        }
        return arrayList;
    }
}
