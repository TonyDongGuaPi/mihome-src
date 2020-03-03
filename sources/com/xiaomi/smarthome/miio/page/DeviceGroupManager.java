package com.xiaomi.smarthome.miio.page;

import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.DeviceSortUtil;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.miio.page.DeviceGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeviceGroupManager {

    /* renamed from: a  reason: collision with root package name */
    static DeviceGroupManager f19532a;
    DeviceTagInterface b = SmartHomeDeviceHelper.a().b();
    List<DeviceGroup> c = Collections.synchronizedList(new ArrayList());
    DeviceGroup d = new DeviceGroup(DeviceGroup.DeviceGroupType.Bluetooth);
    DeviceGroup e = new DeviceGroup(DeviceGroup.DeviceGroupType.VirtualGroup);
    DeviceGroup f = new DeviceGroup(DeviceGroup.DeviceGroupType.OtherGroup);
    BssidComparator g = new BssidComparator();
    List<Device> h = new ArrayList();

    public static DeviceGroupManager a() {
        if (f19532a == null) {
            f19532a = new DeviceGroupManager();
        }
        return f19532a;
    }

    public List<Device> b() {
        return this.h;
    }

    public List<DeviceGroup> c() {
        return this.c;
    }

    public int d() {
        List<DeviceGroup> list = this.c;
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            i += list.get(i2).c.size();
        }
        return i;
    }

    public int e() {
        return this.c.size();
    }

    public int f() {
        return d() + e();
    }

    public Device a(int i) {
        Object c2 = c(i);
        if (c2 != null && !(c2 instanceof DeviceGroup)) {
            return (Device) c2;
        }
        return null;
    }

    public DeviceGroup b(int i) {
        Object c2 = c(i);
        if (c2 != null && (c2 instanceof DeviceGroup)) {
            return (DeviceGroup) c2;
        }
        return null;
    }

    public Object c(int i) {
        if (i < 0 || i >= f()) {
            return null;
        }
        int e2 = e();
        int i2 = -1;
        for (int i3 = 0; i3 < e2; i3++) {
            DeviceGroup deviceGroup = this.c.get(i3);
            int i4 = i2 + 1;
            if (i == i4) {
                return deviceGroup;
            }
            int size = deviceGroup.c.size();
            int i5 = (i - i4) - 1;
            if (i5 < 0) {
                Log.d("device-group", "position=" + i + ",currPos=" + i4);
            }
            if (i5 < size) {
                return deviceGroup.c.get(i5);
            }
            i2 = i4 + size;
        }
        return null;
    }

    public void a(List<Device> list) {
        c(b(list));
    }

    public List<DeviceGroup> b(List<Device> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        this.h = arrayList;
        return d(list);
    }

    public void c(List<DeviceGroup> list) {
        if (list != null) {
            this.c = list;
        } else {
            this.c.clear();
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public List<DeviceGroup> a(List<Device> list, List<DeviceGroup> list2, DeviceGroup deviceGroup, DeviceGroup deviceGroup2, DeviceGroup deviceGroup3) {
        if (list2 == null) {
            list2 = Collections.synchronizedList(new ArrayList());
        } else {
            list2.clear();
        }
        if (deviceGroup == null) {
            deviceGroup = new DeviceGroup(DeviceGroup.DeviceGroupType.Bluetooth);
        } else {
            deviceGroup.c.clear();
        }
        if (deviceGroup2 == null) {
            deviceGroup2 = new DeviceGroup(DeviceGroup.DeviceGroupType.VirtualGroup);
        } else {
            deviceGroup2.c.clear();
        }
        if (deviceGroup3 == null) {
            deviceGroup3 = new DeviceGroup(DeviceGroup.DeviceGroupType.OtherGroup);
        } else {
            deviceGroup3.c.clear();
        }
        if (list == null || list.size() <= 0) {
            return list2;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            Device device = list.get(i);
            if (device != null && !IRDeviceUtil.a(device.did)) {
                if (device.pid == Device.PID_BLUETOOTH) {
                    deviceGroup.a(device);
                } else if (device.pid == Device.PID_VIRTUAL_DEVICE) {
                    deviceGroup2.a(device);
                } else if (TextUtils.isEmpty(device.bssid)) {
                    deviceGroup3.a(device);
                } else {
                    DeviceGroup deviceGroup4 = (DeviceGroup) hashMap.get(device.bssid);
                    if (deviceGroup4 == null) {
                        deviceGroup4 = new DeviceGroup(device.bssid, DeviceGroup.DeviceGroupType.Router, this.b.b(device.bssid, this.b.b(device.bssid)));
                        hashMap.put(device.bssid, deviceGroup4);
                    }
                    deviceGroup4.a(device);
                }
            }
        }
        if (deviceGroup.c.size() > 0) {
            hashMap.put(deviceGroup.f19530a, deviceGroup);
        }
        if (deviceGroup2.c.size() > 0) {
            hashMap.put(deviceGroup2.f19530a, deviceGroup2);
        }
        if (deviceGroup3.c.size() > 0) {
            hashMap.put(deviceGroup3.f19530a, deviceGroup3);
        }
        ArrayList arrayList = new ArrayList(hashMap.values());
        Collections.sort(arrayList, this.g);
        list2.addAll(arrayList);
        return list2;
    }

    public static class BssidComparator implements Comparator<DeviceGroup> {
        /* renamed from: a */
        public int compare(DeviceGroup deviceGroup, DeviceGroup deviceGroup2) {
            int c = DeviceSortUtil.c(deviceGroup.f19530a);
            int c2 = DeviceSortUtil.c(deviceGroup2.f19530a);
            if (c >= 0 && c2 >= 0) {
                return c - c2;
            }
            if (c >= 0 || c2 >= 0) {
                return c < 0 ? 1 : -1;
            }
            if (deviceGroup.b == deviceGroup2.b) {
                return deviceGroup2.c.size() - deviceGroup.c.size();
            }
            return deviceGroup.b.ordinal() - deviceGroup2.b.ordinal();
        }
    }

    private List<DeviceGroup> d(List<Device> list) {
        List<DeviceGroup> synchronizedList = Collections.synchronizedList(new ArrayList());
        if (list == null || list.size() <= 0) {
            return synchronizedList;
        }
        HashMap hashMap = new HashMap();
        DeviceGroup deviceGroup = new DeviceGroup(DeviceGroup.DeviceGroupType.Bluetooth);
        DeviceGroup deviceGroup2 = new DeviceGroup(DeviceGroup.DeviceGroupType.VirtualGroup);
        DeviceGroup deviceGroup3 = new DeviceGroup(DeviceGroup.DeviceGroupType.OtherGroup);
        if (this.b != null) {
            Map<String, Set<String>> a2 = this.b.a(2);
            HashMap hashMap2 = new HashMap();
            for (String next : a2.keySet()) {
                if (!TextUtils.isEmpty(next)) {
                    DeviceGroup deviceGroup4 = new DeviceGroup(next, DeviceGroup.DeviceGroupType.Router, this.b.b(next, this.b.b(next)));
                    Set<String> set = a2.get(next);
                    if (set != null && !set.isEmpty()) {
                        for (String str : set) {
                            if (!hashMap2.containsKey(str)) {
                                hashMap2.put(str, new HashSet());
                            }
                            ((Set) hashMap2.get(str)).add(next);
                        }
                    }
                    hashMap.put(next, deviceGroup4);
                }
            }
            for (int i = 0; i < list.size(); i++) {
                Device device = list.get(i);
                if (device != null && !IRDeviceUtil.a(device.did)) {
                    if (device.pid == Device.PID_BLUETOOTH) {
                        deviceGroup.a(device);
                    } else if (device.pid == Device.PID_VIRTUAL_DEVICE) {
                        deviceGroup2.a(device);
                    } else if (!hashMap2.containsKey(device.did)) {
                        deviceGroup3.a(device);
                    } else {
                        for (String str2 : (Set) hashMap2.get(device.did)) {
                            ((DeviceGroup) hashMap.get(str2)).a(device);
                        }
                    }
                }
            }
        }
        if (deviceGroup.c.size() > 0) {
            hashMap.put(deviceGroup.f19530a, deviceGroup);
        }
        if (deviceGroup2.c.size() > 0) {
            hashMap.put(deviceGroup2.f19530a, deviceGroup2);
        }
        if (deviceGroup3.c.size() > 0) {
            hashMap.put(deviceGroup3.f19530a, deviceGroup3);
        }
        a((HashMap<String, DeviceGroup>) hashMap);
        ArrayList arrayList = new ArrayList(hashMap.values());
        Collections.sort(arrayList, this.g);
        synchronizedList.addAll(arrayList);
        return synchronizedList;
    }

    private void a(HashMap<String, DeviceGroup> hashMap) {
        boolean z;
        if (hashMap != null && !hashMap.isEmpty()) {
            ArrayList<String> arrayList = new ArrayList<>();
            for (String next : hashMap.keySet()) {
                DeviceGroup deviceGroup = hashMap.get(next);
                if (deviceGroup == null || deviceGroup.c == null || deviceGroup.c.size() == 0) {
                    arrayList.add(next);
                } else {
                    int i = 0;
                    while (true) {
                        z = true;
                        if (i >= deviceGroup.c.size()) {
                            z = false;
                            break;
                        }
                        Device device = deviceGroup.c.get(i);
                        if (device != null && (TextUtils.isEmpty(device.parentId) || SmartHomeDeviceManager.a().b(device.parentId) != null)) {
                            break;
                        }
                        i++;
                    }
                    if (!z) {
                        arrayList.add(next);
                    }
                }
            }
            for (String remove : arrayList) {
                hashMap.remove(remove);
            }
        }
    }
}
