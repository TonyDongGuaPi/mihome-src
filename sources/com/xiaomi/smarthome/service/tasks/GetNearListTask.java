package com.xiaomi.smarthome.service.tasks;

import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.xiaomi.aiot.mibeacon.BeaconManager;
import com.xiaomi.aiot.mibeacon.MiBeacon;
import com.xiaomi.aiot.mibeacon.RangeNotifier;
import com.xiaomi.miot.DeviceInfo;
import com.xiaomi.miot.service.ICallback;
import com.xiaomi.smarthome.MainPageOpManager;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.renderer.SubTitleHelper;
import com.xiaomi.smarthome.devicelistswitch.model.DeviceListSwitchManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory;
import com.xiaomi.smarthome.newui.card.CardItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GetNearListTask implements RangeNotifier {

    /* renamed from: a  reason: collision with root package name */
    private static final int f22075a = 350;
    private ICallback b;
    /* access modifiers changed from: private */
    public final BeaconManager c = BeaconManager.a(SHApplication.getAppContext());
    /* access modifiers changed from: private */
    public final Map<String, MiBeacon> d = new HashMap();
    /* access modifiers changed from: private */
    public final Map<String, MiBeacon> e = new HashMap();
    private boolean f;
    private Map<String, Device> g;
    /* access modifiers changed from: private */
    public final Object h = new Object();
    /* access modifiers changed from: private */
    public Map<String, Device> i = new HashMap();
    /* access modifiers changed from: private */
    public long j;
    /* access modifiers changed from: private */
    public Runnable k = new Runnable() {
        public void run() {
            synchronized (GetNearListTask.this.h) {
                if (GetNearListTask.this.i.size() > 0) {
                    try {
                        long unused = GetNearListTask.this.j = System.currentTimeMillis();
                        GetNearListTask.this.a((ArrayList<Device>) new ArrayList(GetNearListTask.this.i.values()));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public Runnable l = new Runnable() {
        public void run() {
            Log.i("MiuiService", "GetNearListTask stop apsize:" + GetNearListTask.this.e.size() + " btsize:" + GetNearListTask.this.d.size());
            GetNearListTask.this.c.c();
            SHApplication.getGlobalWorkerHandler().removeCallbacks(GetNearListTask.this.k);
            SHApplication.getGlobalWorkerHandler().removeCallbacks(GetNearListTask.this.l);
        }
    };

    public void a(Collection<MiBeacon> collection) {
        if (collection != null && this.b != null && this.g != null) {
            Log.i("MiuiService", "GetNearListTask didRangeBeaconsInScan: collection:" + collection.size());
            boolean z = false;
            try {
                for (MiBeacon next : collection) {
                    String d2 = next.d();
                    String f2 = next.f();
                    Device device = null;
                    if (!TextUtils.isEmpty(d2) && this.d.get(d2) == null) {
                        device = this.g.get(d2);
                    }
                    if (device == null && !TextUtils.isEmpty(f2) && this.e.get(f2) == null) {
                        device = this.g.get(f2);
                    }
                    this.d.put(d2, next);
                    this.e.put(f2, next);
                    if (device != null) {
                        Log.i("MiuiService", "GetNearListTask didRangeBeaconsInScan: collection item: ble:" + d2 + " ap:" + f2 + " did:" + device.did);
                        synchronized (this.h) {
                            if (this.i.get(device.did) == null) {
                                z = true;
                                this.i.put(device.did, device);
                            }
                        }
                    } else {
                        Log.i("MiuiService", "GetNearListTask didRangeBeaconsInScan: collection item: ble:" + d2 + " ap:" + f2 + " not match device");
                    }
                }
                if (z) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (Math.abs(currentTimeMillis - this.j) > 450) {
                        SHApplication.getGlobalWorkerHandler().removeCallbacks(this.k);
                        this.j = currentTimeMillis;
                        a((ArrayList<Device>) new ArrayList(this.i.values()));
                        return;
                    }
                    SHApplication.getGlobalWorkerHandler().removeCallbacks(this.k);
                    SHApplication.getGlobalWorkerHandler().postDelayed(this.k, 350);
                }
            } catch (Throwable th) {
                Log.e("MiuiService", Log.getStackTraceString(th));
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(ArrayList<Device> arrayList) throws RemoteException {
        Bundle bundle = new Bundle();
        ArrayList<DeviceInfo> a2 = a((List<Device>) arrayList, this.f);
        bundle.putString(LoginTask.f22078a, CoreApi.a().s());
        bundle.setClassLoader(DeviceInfo.class.getClassLoader());
        Log.i("MiuiService", "GetNearListTask onSuccess devicesize:" + a2.size() + " apsize:" + this.e.size() + " btsize:" + this.d.size());
        bundle.putParcelableArrayList(GetDeviceTask.c, a2);
        this.b.onSuccess(bundle);
    }

    public void a(boolean z, boolean z2, int i2, ICallback iCallback) {
        Log.d("MiuiService", "scanNearList, onlyOnline = " + z + ", forceRefresh = " + z2 + ", timeout = " + i2);
        try {
            this.b = iCallback;
            this.f = z;
            List<Device> d2 = SmartHomeDeviceManager.a().d();
            this.g = new HashMap();
            for (int i3 = 0; i3 < d2.size(); i3++) {
                Device device = d2.get(i3);
                this.g.put(device.mac, device);
            }
            this.i = new HashMap();
            this.j = System.currentTimeMillis();
            if (z2) {
                this.d.clear();
                this.e.clear();
            } else {
                for (Map.Entry<String, MiBeacon> key : this.d.entrySet()) {
                    Device device2 = this.g.get(key.getKey());
                    if (device2 != null) {
                        this.i.put(device2.did, device2);
                    }
                }
                for (Map.Entry<String, MiBeacon> key2 : this.e.entrySet()) {
                    Device device3 = this.g.get(key2.getKey());
                    if (device3 != null) {
                        this.i.put(device3.did, device3);
                    }
                }
                if (this.i.size() > 0) {
                    a((ArrayList<Device>) new ArrayList(this.i.values()));
                }
            }
            this.c.c(this);
            SHApplication.getGlobalWorkerHandler().postDelayed(this.l, (long) i2);
        } catch (Throwable th) {
            Log.e("MiuiService", Log.getStackTraceString(th));
        }
    }

    public static ArrayList<DeviceInfo> a(List<Device> list, boolean z) {
        Pair pair;
        ArrayList<DeviceInfo> arrayList = new ArrayList<>();
        if (list == null) {
            return arrayList;
        }
        for (Device next : list) {
            if (next == null) {
                Log.e("MiuiService", "GetNearListTask createDeviceInfo data is null");
            } else if (!z || DeviceCategory.fromPid(next.pid) == DeviceCategory.Bluetooth || next.isOnline) {
                DeviceInfo deviceInfo = new DeviceInfo();
                deviceInfo.f11363a = next.did;
                deviceInfo.b = next.name;
                deviceInfo.d = next.model;
                deviceInfo.g = next.isOnlineAdvance();
                PluginRecord d2 = CoreApi.a().d(next.model);
                if (d2 != null) {
                    deviceInfo.c = d2.t();
                    if (next.property == null || TextUtils.isEmpty(next.property.getString(DeviceListSwitchManager.f15515a, ""))) {
                        deviceInfo.e = next.getSubtitleByDesc(SHApplication.getAppContext(), false);
                        if (TextUtils.isEmpty(deviceInfo.e)) {
                            deviceInfo.e = new SubTitleHelper(next).a(SHApplication.getAppContext(), false);
                        }
                    } else {
                        deviceInfo.e = next.property.getString(DeviceListSwitchManager.f15515a, "");
                    }
                    ArrayList<Pair> c2 = MainPageOpManager.a().c(next);
                    if (c2 != null) {
                        Bundle bundle = new Bundle();
                        Iterator<Pair> it = c2.iterator();
                        while (it.hasNext()) {
                            Pair next2 = it.next();
                            if (!(next2 == null || !(next2.first instanceof String) || next2.second == null)) {
                                bundle.putString((String) next2.first, next2.second.toString());
                            }
                        }
                        deviceInfo.f = bundle;
                        if (c2.size() <= 0 || (pair = c2.get(0)) == null || !(pair.first instanceof CardItem.State)) {
                            deviceInfo.h = false;
                        } else if (CardItem.State.NOR == pair.first) {
                            deviceInfo.h = true;
                            deviceInfo.i = false;
                        } else if (CardItem.State.SELECTED == pair.first) {
                            deviceInfo.h = true;
                            deviceInfo.i = true;
                        } else {
                            deviceInfo.h = false;
                        }
                    }
                    arrayList.add(deviceInfo);
                } else {
                    Log.e("MiuiService", "GetNearListTask createDeviceInfo record is null model:" + next.model);
                }
            } else {
                Log.e("MiuiService", "GetNearListTask createDeviceInfo device offline model:" + next.model + " did:" + next.did);
            }
        }
        return arrayList;
    }

    public void a() {
        Log.d("MiuiService", "stopScanNear");
        this.l.run();
    }
}
