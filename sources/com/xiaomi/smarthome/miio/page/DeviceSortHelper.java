package com.xiaomi.smarthome.miio.page;

import android.text.TextUtils;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DevicePrefManager;
import com.xiaomi.smarthome.device.DeviceSearch;
import com.xiaomi.smarthome.device.MiTVDevice;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.DeviceSortUtil;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.miio.device.PhoneIRDevice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class DeviceSortHelper {

    /* renamed from: a  reason: collision with root package name */
    private static Comparator<Device> f19543a = new Comparator<Device>() {
        /* renamed from: a */
        public int compare(Device device, Device device2) {
            if (device == device2) {
                return 0;
            }
            if (device == null) {
                return 1;
            }
            if (device2 == null) {
                return -1;
            }
            if (device.isOnline != device2.isOnline) {
                return device.isOnline ? -1 : 1;
            }
            long a2 = DevicePrefManager.a(device.did);
            long a3 = DevicePrefManager.a(device2.did);
            if (a2 == a3) {
                return 0;
            }
            return a2 > a3 ? -1 : 1;
        }
    };

    public static class SortDeviceData {

        /* renamed from: a  reason: collision with root package name */
        public List<Device> f19544a = new ArrayList();
    }

    public static SortDeviceData a() {
        List<Device> list;
        SortDeviceData sortDeviceData = new SortDeviceData();
        ArrayList arrayList = new ArrayList();
        for (DeviceSearch<?> d : SmartHomeDeviceManager.a().b()) {
            List d2 = d.d();
            if (d2 != null) {
                synchronized (d2) {
                    if (d2.size() > 0) {
                        arrayList.addAll(d2);
                    }
                }
            }
        }
        DeviceSortUtil.b();
        if (DeviceSortUtil.c()) {
            list = a(arrayList);
        } else {
            list = b(arrayList);
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        ArrayList<Device> arrayList2 = new ArrayList<>();
        for (Device next : list) {
            if (next.isSubDevice() && !next.isShowMainList()) {
                arrayList2.add(next);
            }
        }
        for (Device remove : arrayList2) {
            list.remove(remove);
        }
        sortDeviceData.f19544a = list;
        return sortDeviceData;
    }

    private static List<Device> a(List<Device> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        ArrayList arrayList2 = new ArrayList();
        int d = DeviceSortUtil.d();
        for (int i = 0; i < d; i++) {
            String a2 = DeviceSortUtil.a(i);
            if (!TextUtils.isEmpty(a2) && !a2.startsWith("bssid_") && !IRDeviceUtil.a(a2)) {
                Iterator it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Device device = (Device) it.next();
                    if (device != null && a2.equals(device.did)) {
                        arrayList.remove(device);
                        arrayList2.add(device);
                        break;
                    }
                }
            }
        }
        if (arrayList.size() <= 0) {
            return arrayList2;
        }
        return a((List<Device>) arrayList2, (List<Device>) arrayList);
    }

    private static List<Device> b(List<Device> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<Device> c = c(list);
        Collections.sort(list, f19543a);
        return a(list, c);
    }

    private static List<Device> a(List<Device> list, List<Device> list2) {
        if (list == null) {
            list = new ArrayList<>();
        }
        if (list2 == null) {
            return list;
        }
        ArrayList<Device> arrayList = new ArrayList<>();
        ArrayList<Device> arrayList2 = new ArrayList<>();
        for (Device next : list2) {
            if (next != null && (next instanceof MiTVDevice) && next.isOnline && ((MiTVDevice) next).d() && !next.isOwner()) {
                arrayList.add(next);
            }
        }
        for (Device remove : arrayList) {
            list2.remove(remove);
        }
        for (Device next2 : list2) {
            if (next2 != null && ((next2 instanceof PhoneIRDevice) || IRDeviceUtil.a(next2.did))) {
                arrayList2.add(next2);
            }
        }
        for (Device remove2 : arrayList2) {
            list2.remove(remove2);
        }
        for (Device a2 : list2) {
            a(list, a2);
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(arrayList);
        arrayList3.addAll(list);
        arrayList3.addAll(arrayList2);
        return arrayList3;
    }

    private static List<Device> c(List<Device> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList<Device> arrayList = new ArrayList<>();
        for (Device next : list) {
            if (next != null && (next.isNew || IRDeviceUtil.a(next.did) || (next instanceof PhoneIRDevice) || (next instanceof MiTVDevice))) {
                arrayList.add(next);
            }
        }
        for (Device remove : arrayList) {
            list.remove(remove);
        }
        return arrayList;
    }

    private static void a(List<Device> list, Device device) {
        if (list != null && device != null) {
            if (list.size() > 0) {
                int size = list.size() - 1;
                while (size >= 0) {
                    Device device2 = list.get(size);
                    if (device2 == null || device2.model == null || !device2.model.equals(device.model)) {
                        size--;
                    } else {
                        list.add(size + 1, device);
                        return;
                    }
                }
            }
            list.add(device);
        }
    }
}
