package com.xiaomi.smarthome.miio.page.devicetag;

import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.DeviceTagManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeviceTagGroupManager {

    /* renamed from: a  reason: collision with root package name */
    private static DeviceTagGroupManager f19817a;

    private DeviceTagGroupManager() {
    }

    public static DeviceTagGroupManager a() {
        if (f19817a == null) {
            f19817a = new DeviceTagGroupManager();
        }
        return f19817a;
    }

    public List<DeviceTagGroup> b() {
        ArrayList arrayList = new ArrayList();
        DeviceTagManager deviceTagManager = (DeviceTagManager) SmartHomeDeviceHelper.a().b();
        List<Integer> m = deviceTagManager.m();
        if (m != null && !m.isEmpty()) {
            Iterator<Integer> it = m.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Integer next = it.next();
                if (next.intValue() == 4) {
                    arrayList.add(a(next.intValue(), deviceTagManager, 0, deviceTagManager.g()));
                    break;
                }
            }
        }
        DeviceTagGroup deviceTagGroup = new DeviceTagGroup();
        deviceTagGroup.r = 1;
        arrayList.add(deviceTagGroup);
        return arrayList;
    }

    public List<DeviceTagGroup> c() {
        ArrayList arrayList = new ArrayList();
        DeviceTagManager deviceTagManager = (DeviceTagManager) SmartHomeDeviceHelper.a().b();
        List<Integer> l = deviceTagManager.l();
        if (l != null) {
            for (Integer next : l) {
                if (next.intValue() == 4) {
                    arrayList.add(a(next.intValue(), deviceTagManager, 2, (String) null));
                }
            }
        }
        return arrayList;
    }

    public List<DeviceTagGroup> d() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(i());
        DeviceTagManager deviceTagManager = (DeviceTagManager) SmartHomeDeviceHelper.a().b();
        List<Integer> m = deviceTagManager.m();
        if (m != null && !m.isEmpty()) {
            for (Integer intValue : m) {
                arrayList.add(a(intValue.intValue(), deviceTagManager, 0, deviceTagManager.g()));
            }
        }
        DeviceTagGroup deviceTagGroup = new DeviceTagGroup();
        deviceTagGroup.r = 1;
        arrayList.add(deviceTagGroup);
        return arrayList;
    }

    public List<DeviceTagGroup> e() {
        ArrayList arrayList = new ArrayList();
        DeviceTagManager deviceTagManager = (DeviceTagManager) SmartHomeDeviceHelper.a().b();
        List<Integer> m = deviceTagManager.m();
        if (m != null && !m.isEmpty()) {
            for (Integer next : m) {
                if (next.intValue() == 2) {
                    arrayList.add(a(next.intValue(), deviceTagManager, 0, deviceTagManager.g()));
                }
            }
        }
        return arrayList;
    }

    public List<DeviceTagGroup> f() {
        ArrayList arrayList = new ArrayList();
        DeviceTagManager deviceTagManager = (DeviceTagManager) SmartHomeDeviceHelper.a().b();
        arrayList.add(a(deviceTagManager));
        List<Integer> l = deviceTagManager.l();
        if (l != null) {
            for (Integer next : l) {
                int i = 3;
                if (next.intValue() == 4) {
                    i = 2;
                }
                arrayList.add(a(next.intValue(), deviceTagManager, i, (String) null));
            }
        }
        return arrayList;
    }

    public List<DeviceTagGroup> a(String str) {
        ArrayList arrayList = new ArrayList();
        final Set<String> b = SmartHomeDeviceHelper.a().b().b(4, str);
        List<Device> a2 = SmartHomeDeviceHelper.a().a(SmartHomeDeviceManager.a().d());
        if (a2 == null || a2.isEmpty()) {
            DeviceTagGroup deviceTagGroup = new DeviceTagGroup();
            deviceTagGroup.r = 6;
            deviceTagGroup.s = 4;
            deviceTagGroup.w = new ArrayList();
            DeviceTagChild deviceTagChild = new DeviceTagChild();
            deviceTagChild.d = SHApplication.getAppContext().getString(R.string.tag_no_device_to_add);
            deviceTagGroup.w.add(deviceTagChild);
            deviceTagGroup.u = SHApplication.getAppContext().getString(R.string.tag_no_device_to_add);
            arrayList.add(deviceTagGroup);
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (Device next : a2) {
                if (HomeManager.a(next)) {
                    arrayList2.add(next);
                }
            }
            DeviceTagGroup deviceTagGroup2 = new DeviceTagGroup();
            deviceTagGroup2.r = 6;
            deviceTagGroup2.s = 9;
            deviceTagGroup2.w = new ArrayList();
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                deviceTagGroup2.w.add(a((Device) it.next(), b));
            }
            Collections.sort(deviceTagGroup2.w, new Comparator<DeviceTagChild>() {
                /* renamed from: a */
                public int compare(DeviceTagChild deviceTagChild, DeviceTagChild deviceTagChild2) {
                    if (b != null) {
                        if (deviceTagChild.l == 1 && deviceTagChild2.l == 1) {
                            return 0;
                        }
                        if (deviceTagChild.l == 1) {
                            return -1;
                        }
                        if (deviceTagChild2.l == 1) {
                            return 1;
                        }
                        if (deviceTagChild.l < deviceTagChild2.l) {
                            return -1;
                        }
                        return deviceTagChild.l > deviceTagChild2.l ? 1 : 0;
                    } else if (deviceTagChild.l < deviceTagChild2.l) {
                        return -1;
                    } else {
                        return deviceTagChild.l > deviceTagChild2.l ? 1 : 0;
                    }
                }
            });
            int i = 0;
            for (DeviceTagChild deviceTagChild2 : deviceTagGroup2.w) {
                if (deviceTagChild2.h) {
                    i++;
                }
            }
            deviceTagGroup2.u = SHApplication.getAppContext().getResources().getQuantityString(R.plurals.tag_selected_title, i);
            arrayList.add(deviceTagGroup2);
        }
        return arrayList;
    }

    public List<DeviceTagGroup> a(Set<String> set) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(g());
        DeviceTagManager deviceTagManager = (DeviceTagManager) SmartHomeDeviceHelper.a().b();
        a(0, set, deviceTagManager, (List<DeviceTagGroup>) arrayList);
        arrayList.add(h());
        a(2, set, deviceTagManager, (List<DeviceTagGroup>) arrayList);
        return arrayList;
    }

    public List<DeviceTagGroup> b(String str) {
        ArrayList arrayList = new ArrayList();
        DeviceTagManager deviceTagManager = (DeviceTagManager) SmartHomeDeviceHelper.a().b();
        List<String> c = deviceTagManager.c(0, str);
        if (c != null && c.size() > 0) {
            DeviceTagGroup deviceTagGroup = new DeviceTagGroup();
            deviceTagGroup.r = 5;
            deviceTagGroup.u = c.get(0);
            deviceTagGroup.v = SHApplication.getAppContext().getString(R.string.tag_category_title);
            arrayList.add(deviceTagGroup);
        }
        List<String> c2 = deviceTagManager.c(2, str);
        if (c2 != null && c2.size() > 0) {
            DeviceTagGroup deviceTagGroup2 = new DeviceTagGroup();
            deviceTagGroup2.r = 5;
            deviceTagGroup2.u = deviceTagManager.b(c2.get(0), deviceTagManager.e(c2.get(0)));
            deviceTagGroup2.v = SHApplication.getAppContext().getString(R.string.tag_router_title);
            arrayList.add(deviceTagGroup2);
        }
        List<String> c3 = deviceTagManager.c(4, str);
        DeviceTagGroup a2 = a(4, deviceTagManager, 8, (String) null);
        if (!(a2 == null || a2.w == null || a2.w.size() <= 0)) {
            a2.t = 4;
            if (c3 == null || c3.size() <= 0) {
                for (DeviceTagChild deviceTagChild : a2.w) {
                    deviceTagChild.h = false;
                }
            } else {
                for (DeviceTagChild next : a2.w) {
                    int indexOf = c3.indexOf(next.d);
                    if (indexOf < 0 || indexOf >= c3.size()) {
                        next.h = false;
                    } else {
                        next.h = true;
                    }
                }
            }
            arrayList.add(a2);
        }
        return arrayList;
    }

    private void a(int i, Set<String> set, DeviceTagManager deviceTagManager, List<DeviceTagGroup> list) {
        Map<String, Set<String>> a2 = deviceTagManager.a(i);
        if (a2 != null && !a2.isEmpty()) {
            List<String> b = deviceTagManager.b(i);
            if (b != null && !b.isEmpty()) {
                for (String next : b) {
                    if (a2.containsKey(next)) {
                        DeviceTagGroup a3 = a(i, next, a2.get(next), set, deviceTagManager);
                        if (a3 != null) {
                            list.add(a3);
                        }
                        a2.remove(next);
                    }
                }
            }
            if (!a2.isEmpty()) {
                for (String next2 : a2.keySet()) {
                    DeviceTagGroup a4 = a(i, next2, a2.get(next2), set, deviceTagManager);
                    if (a4 != null) {
                        list.add(a4);
                    }
                }
            }
        }
    }

    private DeviceTagGroup a(int i, String str, Set<String> set, Set<String> set2, DeviceTagManager deviceTagManager) {
        if (set == null || set.isEmpty()) {
            return null;
        }
        DeviceTagGroup deviceTagGroup = new DeviceTagGroup();
        deviceTagGroup.t = i;
        deviceTagGroup.r = 4;
        deviceTagGroup.s = 6;
        if (i != 2) {
            deviceTagGroup.u = str;
        } else {
            deviceTagGroup.u = deviceTagManager.e(str);
            deviceTagGroup.v = str;
        }
        deviceTagGroup.w = new ArrayList();
        for (String next : set) {
            Device b = SmartHomeDeviceManager.a().b(next);
            if (b != null) {
                DeviceTagChild deviceTagChild = new DeviceTagChild();
                deviceTagChild.d = b.name;
                deviceTagChild.g = new HashSet();
                deviceTagChild.g.add(next);
                if (set2.contains(next)) {
                    deviceTagChild.h = true;
                    deviceTagGroup.x = true;
                } else {
                    deviceTagChild.h = false;
                }
                deviceTagGroup.w.add(deviceTagChild);
            }
        }
        return deviceTagGroup;
    }

    private DeviceTagGroup g() {
        DeviceTagGroup deviceTagGroup = new DeviceTagGroup();
        deviceTagGroup.t = 0;
        deviceTagGroup.r = 3;
        deviceTagGroup.u = SHApplication.getAppContext().getString(R.string.tag_category_title);
        return deviceTagGroup;
    }

    private DeviceTagGroup h() {
        DeviceTagGroup deviceTagGroup = new DeviceTagGroup();
        deviceTagGroup.t = 2;
        deviceTagGroup.r = 3;
        deviceTagGroup.u = SHApplication.getAppContext().getString(R.string.tag_router_title);
        return deviceTagGroup;
    }

    private DeviceTagChild a(Device device, Set<String> set) {
        DeviceTagChild deviceTagChild = new DeviceTagChild();
        deviceTagChild.d = device.name;
        deviceTagChild.g = new HashSet();
        deviceTagChild.g.add(device.did);
        deviceTagChild.k = SmartHomeDeviceHelper.a().b().f(device.did);
        if (TextUtils.isEmpty(deviceTagChild.k)) {
            deviceTagChild.l = 0;
            deviceTagChild.h = false;
        } else if (set == null || !set.contains(device.did)) {
            deviceTagChild.l = 2;
            deviceTagChild.h = false;
        } else {
            deviceTagChild.l = 1;
            deviceTagChild.h = true;
        }
        return deviceTagChild;
    }

    private DeviceTagGroup a(DeviceTagManager deviceTagManager) {
        DeviceTagGroup deviceTagGroup = new DeviceTagGroup();
        deviceTagGroup.r = 6;
        deviceTagGroup.s = 1;
        deviceTagGroup.t = -1;
        deviceTagGroup.u = SHApplication.getAppContext().getString(R.string.tag_select_title);
        deviceTagGroup.w = new ArrayList();
        List<Integer> l = deviceTagManager.l();
        Set<Integer> y = deviceTagManager.y();
        if (l != null) {
            for (Integer next : l) {
                DeviceTagChild deviceTagChild = new DeviceTagChild();
                deviceTagChild.d = b(next.intValue());
                deviceTagChild.f = next.intValue();
                deviceTagChild.h = y.contains(next);
                deviceTagGroup.w.add(deviceTagChild);
            }
        }
        return deviceTagGroup;
    }

    private DeviceTagGroup i() {
        DeviceTagGroup deviceTagGroup = new DeviceTagGroup();
        deviceTagGroup.r = 0;
        deviceTagGroup.t = -1;
        deviceTagGroup.w = new ArrayList();
        deviceTagGroup.u = SHApplication.getAppContext().getString(R.string.tag_all_devices);
        return deviceTagGroup;
    }

    private DeviceTagGroup a(int i, DeviceTagManager deviceTagManager, int i2, String str) {
        DeviceTagGroup deviceTagGroup = new DeviceTagGroup();
        deviceTagGroup.r = 6;
        deviceTagGroup.s = i2;
        deviceTagGroup.t = i;
        a(deviceTagGroup);
        deviceTagGroup.w = new ArrayList();
        Map<String, Set<String>> a2 = deviceTagManager.a(i);
        if (a2 != null && !a2.isEmpty()) {
            List<String> b = deviceTagManager.b(i);
            if (b != null && !b.isEmpty()) {
                for (String next : b) {
                    if (a2.containsKey(next)) {
                        DeviceTagChild a3 = a(i, a2, next, deviceTagManager, str);
                        if (a3 != null) {
                            deviceTagGroup.w.add(a3);
                        }
                        a2.remove(next);
                    }
                }
            }
            if (!a2.isEmpty()) {
                for (String a4 : a2.keySet()) {
                    DeviceTagChild a5 = a(i, a2, a4, deviceTagManager, str);
                    if (a5 != null) {
                        deviceTagGroup.w.add(a5);
                    }
                }
            }
        }
        if (i == 4 && deviceTagGroup.w.isEmpty()) {
            deviceTagGroup.s = 7;
            deviceTagGroup.w.add(a(i));
        }
        return deviceTagGroup;
    }

    private DeviceTagChild a(int i) {
        DeviceTagChild deviceTagChild = new DeviceTagChild();
        deviceTagChild.d = SHApplication.getAppContext().getString(R.string.tag_custom_empty);
        return deviceTagChild;
    }

    private DeviceTagChild a(int i, Map<String, Set<String>> map, String str, DeviceTagManager deviceTagManager, String str2) {
        DeviceTagChild deviceTagChild = new DeviceTagChild();
        deviceTagChild.i = new ArrayList();
        deviceTagChild.h = false;
        deviceTagChild.d = str;
        if (i == 4) {
            deviceTagChild.j = TextUtils.equals(str, str2);
        } else {
            deviceTagChild.j = false;
        }
        if (i == 2) {
            deviceTagChild.d = deviceTagManager.b(str, deviceTagManager.e(str));
            deviceTagChild.e = str;
        }
        Set<String> set = map.get(str);
        if (set != null && set.size() > 0) {
            deviceTagChild.g = new HashSet();
            for (String str3 : set) {
                Device b = SmartHomeDeviceManager.a().b(str3);
                if (b != null) {
                    deviceTagChild.g.add(str3);
                    if (b.isOnline && DeviceUtils.a(b)) {
                        deviceTagChild.i.add(b);
                        if (!deviceTagChild.h && b.isOnline && b.isOpen()) {
                            deviceTagChild.h = true;
                        }
                    }
                }
            }
        }
        if (i == 4 || (deviceTagChild.g != null && !deviceTagChild.g.isEmpty())) {
            return deviceTagChild;
        }
        return null;
    }

    private void a(DeviceTagGroup deviceTagGroup) {
        if (deviceTagGroup.t == 0) {
            deviceTagGroup.u = SHApplication.getAppContext().getString(R.string.tag_category_title);
        } else if (deviceTagGroup.t == 2) {
            deviceTagGroup.u = SHApplication.getAppContext().getString(R.string.tag_router_title);
        } else if (deviceTagGroup.t == 4) {
            deviceTagGroup.u = SHApplication.getAppContext().getString(R.string.tag_custom_title);
        }
    }

    private String b(int i) {
        if (i == 0) {
            return SHApplication.getAppContext().getString(R.string.tag_category_title);
        }
        if (i == 2) {
            return SHApplication.getAppContext().getString(R.string.tag_router_title);
        }
        return i == 4 ? SHApplication.getAppContext().getString(R.string.tag_custom_title) : "";
    }
}
