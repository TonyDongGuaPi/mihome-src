package com.xiaomi.smarthome.smartconfig;

import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CameraFastConnectConfigs {
    public static int a(String str) {
        return 2;
    }

    public static List<Integer> b(String str) {
        ArrayList arrayList = new ArrayList();
        PluginRecord d = CoreApi.a().d(str);
        if (d == null || d.c() == null) {
            return c(str);
        }
        List<Integer> i = d.c().i();
        if (i == null || i.size() == 0) {
            return c(str);
        }
        if (i.contains(0)) {
            arrayList.add(3);
        }
        if (i.contains(1)) {
            arrayList.add(1);
        }
        if (i.contains(2)) {
            arrayList.add(2);
        }
        return arrayList.size() == 0 ? c(str) : arrayList;
    }

    public static List<Integer> c(String str) {
        if (DeviceFactory.a(str)) {
            return Arrays.asList(new Integer[]{1, 2});
        } else if ("mijia.camera.v3".equals(str) || "mijia.camera.v4".equals(str)) {
            return Arrays.asList(new Integer[]{3, 2});
        } else if ("isa.camera.isc5".equals(str) || "isa.camera.df3".equals(str)) {
            return Arrays.asList(new Integer[]{2});
        } else if ("chuangmi.camera.v2".equals(str) || "chuangmi.camera.xiaobai".equals(str) || "chuangmi.camera.v3".equals(str) || "chuangmi.camera.v4".equals(str) || "chuangmi.camera.v5".equals(str)) {
            return Arrays.asList(new Integer[]{2, 3});
        } else if ("yunyi.camera.mj1".equals(str)) {
            return Arrays.asList(new Integer[]{2});
        } else if ("chuangmi.camera.v6".equals(str)) {
            return Arrays.asList(new Integer[]{2, 3, 1});
        } else {
            return Arrays.asList(new Integer[]{2});
        }
    }
}
