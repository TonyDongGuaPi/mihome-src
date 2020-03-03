package com.xiaomi.smarthome.homeroom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiTVDevice;
import com.xiaomi.smarthome.device.RouterDevice;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.WatchBandDevice;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.miio.device.GeneralAPDevice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiHomeDeviceManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f18169a = "action_multi_home_device_changed";
    private static final String b = "MultiHomeDeviceManager";
    private static volatile MultiHomeDeviceManager c;
    private Map<String, List<Device>> d = new HashMap();
    private List<Device> e = new ArrayList();
    private List<Device> f = new ArrayList();
    private BroadcastReceiver g = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.equals(action, HomeManager.t) || TextUtils.equals(action, HomeManager.u)) {
                MultiHomeDeviceManager.this.c();
                MultiHomeDeviceManager.this.i();
                MultiHomeDeviceManager.this.j();
                MultiHomeDeviceManager.this.k();
            }
        }
    };
    private SmartHomeDeviceManager.IClientDeviceListener h = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void b(int i) {
        }

        public void a(int i) {
            if (i == 3) {
                MultiHomeDeviceManager.this.c();
                MultiHomeDeviceManager.this.i();
                MultiHomeDeviceManager.this.j();
                MultiHomeDeviceManager.this.k();
            }
        }
    };

    private MultiHomeDeviceManager() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(HomeManager.t);
        intentFilter.addAction(HomeManager.u);
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.g, intentFilter);
        SmartHomeDeviceManager.a().a(this.h);
    }

    public static MultiHomeDeviceManager a() {
        if (c == null) {
            synchronized (MultiHomeDeviceManager.class) {
                if (c == null) {
                    c = new MultiHomeDeviceManager();
                }
            }
        }
        return c;
    }

    public void b() {
        this.d.clear();
        this.e.clear();
        this.f.clear();
    }

    public void c() {
        HashMap hashMap = new HashMap();
        List<Home> f2 = HomeManager.a().f();
        if (f2 != null && f2.size() > 0) {
            for (int i = 0; i < f2.size(); i++) {
                Home home = f2.get(i);
                if (home != null) {
                    hashMap.put(home.j(), e(home.j()));
                }
            }
        }
        this.d = hashMap;
    }

    /* access modifiers changed from: private */
    public void i() {
        ArrayList arrayList = new ArrayList();
        for (Device next : SmartHomeDeviceManager.a().d()) {
            if ((next instanceof MiTVDevice) && !next.isOwner()) {
                arrayList.add(next);
            }
            if ((next instanceof RouterDevice) && !next.isOwner()) {
                arrayList.add(next);
            }
            if (next instanceof GeneralAPDevice) {
                arrayList.add(next);
            }
            if ((next instanceof BleDevice) && ((BleDevice) next).k()) {
                arrayList.add(next);
            }
            if (next instanceof WatchBandDevice) {
                arrayList.add(next);
            }
        }
        this.e = arrayList;
    }

    public static boolean a(Device device) {
        if (device == null) {
            return false;
        }
        if ((device instanceof MiTVDevice) && !device.isOwner()) {
            return true;
        }
        if ((!(device instanceof RouterDevice) || device.isOwner()) && !(device instanceof GeneralAPDevice)) {
            return ((device instanceof BleDevice) && ((BleDevice) device).k()) || (device instanceof WatchBandDevice);
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void j() {
        this.f = HomeVirtualDeviceHelper.a();
    }

    public List<Device> d() {
        return a(HomeManager.a().l());
    }

    public List<Device> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return SmartHomeDeviceManager.a().d();
        }
        if (this.d.get(str) != null) {
            return this.d.get(str);
        }
        return new ArrayList();
    }

    public List<Device> e() {
        ArrayList<Device> arrayList = new ArrayList<>();
        List<Device> d2 = SmartHomeDeviceManager.a().d();
        if (d2 != null && !d2.isEmpty()) {
            arrayList.addAll(d2);
        }
        ArrayList<Device> arrayList2 = new ArrayList<>();
        for (Device device : arrayList) {
            if (SmartHomeDeviceManager.e(device)) {
                arrayList2.add(device);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (Device device2 : arrayList2) {
            arrayList3.add(device2.did);
        }
        Log.d(b, "getShareDeviceList: " + Arrays.deepToString(arrayList3.toArray()));
        String l = HomeManager.a().l();
        if (!TextUtils.isEmpty(l)) {
            HomeManager.HomeRoomSortUtil.a(arrayList2, (ArrayList<Device>) HomeManager.HomeRoomSortUtil.a(l, HomeManager.e));
        }
        return arrayList2;
    }

    public List<String> f() {
        List<Device> d2 = SmartHomeDeviceManager.a().d();
        ArrayList<Device> arrayList = new ArrayList<>();
        for (int i = 0; i < d2.size(); i++) {
            Device device = d2.get(i);
            if (device != null && SmartHomeDeviceManager.e(device)) {
                arrayList.add(device);
            }
        }
        String l = HomeManager.a().l();
        if (!TextUtils.isEmpty(l)) {
            HomeManager.HomeRoomSortUtil.a(arrayList, (ArrayList<Device>) HomeManager.HomeRoomSortUtil.a(l, HomeManager.e));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Device device2 : arrayList) {
            arrayList2.add(device2.did);
        }
        return arrayList2;
    }

    public List<Device> g() {
        return this.e;
    }

    public List<Device> h() {
        return this.f;
    }

    public Device b(String str) {
        List<Device> list;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String l = HomeManager.a().l();
        if (TextUtils.isEmpty(l)) {
            list = SmartHomeDeviceManager.a().d();
        } else {
            list = this.d.get(l);
        }
        if (list != null) {
            for (Device next : list) {
                if (TextUtils.equals(next.did, str)) {
                    return next;
                }
            }
        }
        return null;
    }

    public Device c(String str) {
        List<Device> list;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String l = HomeManager.a().l();
        if (TextUtils.isEmpty(l)) {
            list = SmartHomeDeviceManager.a().d();
        } else {
            list = this.d.get(l);
        }
        if (list != null) {
            for (Device next : list) {
                if (TextUtils.equals(next.mac, str)) {
                    return next;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void k() {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(f18169a));
    }

    public List<Device> d(String str) {
        if (TextUtils.isEmpty(str) || this.d.get(str) == null) {
            return new ArrayList();
        }
        return this.d.get(str);
    }

    private List<Device> e(String str) {
        Home j;
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str) || (j = HomeManager.a().j(str)) == null) {
            return arrayList;
        }
        try {
            List<Room> d2 = j.d();
            for (int i = 0; i < d2.size(); i++) {
                Room room = d2.get(i);
                if (room != null) {
                    List<String> h2 = room.h();
                    if (h2 != null && !h2.isEmpty()) {
                        int i2 = 0;
                        while (i2 < h2.size()) {
                            try {
                                Device b2 = SmartHomeDeviceManager.a().b(h2.get(i2));
                                if (b2 != null) {
                                    arrayList.add(b2);
                                }
                                i2++;
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                }
            }
            List<String> m = j.m();
            if (m != null && !m.isEmpty()) {
                for (int i3 = 0; i3 < m.size(); i3++) {
                    Device b3 = SmartHomeDeviceManager.a().b(m.get(i3));
                    if (b3 != null) {
                        arrayList.add(b3);
                    }
                }
            }
        } catch (Exception e3) {
            e3.getMessage();
        }
        return arrayList;
    }
}
