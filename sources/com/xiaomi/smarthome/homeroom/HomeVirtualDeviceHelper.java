package com.xiaomi.smarthome.homeroom;

import android.text.TextUtils;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import java.util.ArrayList;
import java.util.List;

public class HomeVirtualDeviceHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final List<String> f18123a = new ArrayList();
    public static final boolean b = true;

    static {
        f18123a.add("yeelink.light.virtual");
        f18123a.add("philips.light.virtual");
    }

    public static boolean a(String str) {
        if (!TextUtils.isEmpty(str) && f18123a.contains(str)) {
            return true;
        }
        return false;
    }

    public static List<Device> a() {
        ArrayList arrayList = new ArrayList();
        List<Device> d = SmartHomeDeviceManager.a().d();
        ArrayList<Device> arrayList2 = new ArrayList<>();
        for (int i = 0; i < d.size(); i++) {
            Device device = d.get(i);
            if (device.isVirtualDevice()) {
                arrayList2.add(device);
            }
        }
        if (arrayList2.isEmpty()) {
            return arrayList;
        }
        ArrayList arrayList3 = new ArrayList();
        for (Device device2 : arrayList2) {
            if (f18123a.contains(device2.model)) {
                arrayList3.add(device2);
            }
        }
        return arrayList3;
    }
}
