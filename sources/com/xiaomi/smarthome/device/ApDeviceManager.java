package com.xiaomi.smarthome.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.support.v4.content.LocalBroadcastManager;
import com.xiaomi.smarthome.application.CommonApplication;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApDeviceManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14746a = "wifi_scan_result_broadcast";
    public static final String b = "wifi_scan_device";
    public static final String c = "wifi_scan_device_result";
    public static final String d = "ap_device_msg";
    public static final String e = "refresh_xiaofang_dialog";
    private static ApDeviceManager f;
    private List<ScanResult> g = new ArrayList();
    /* access modifiers changed from: private */
    public List<ScanResult> h = new ArrayList();
    /* access modifiers changed from: private */
    public ScanResult i = null;
    private BroadcastReceiver j = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ApDeviceManager.b)) {
                ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra(ApDeviceManager.c);
                if (parcelableArrayListExtra != null && parcelableArrayListExtra.size() != 0) {
                    ApDeviceManager.this.a((List<ScanResult>) parcelableArrayListExtra);
                }
            } else if (!intent.getAction().equals("wifi_scan_result_broadcast") && intent.getAction().equals("android.net.wifi.STATE_CHANGE")) {
                ZhilianCameraSearch.a().a(intent);
            }
        }
    };

    public static ApDeviceManager a() {
        if (f == null) {
            f = new ApDeviceManager();
        }
        return f;
    }

    ApDeviceManager() {
        b();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        ZhilianCameraSearch.a().i();
    }

    public List<ScanResult> c() {
        return this.g;
    }

    public synchronized ScanResult a(String str) {
        if (this.i == null || !str.equalsIgnoreCase(this.i.BSSID)) {
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                ScanResult scanResult = this.h.get(i2);
                if (scanResult.BSSID.equalsIgnoreCase(str)) {
                    return scanResult;
                }
            }
            return null;
        }
        return this.i;
    }

    /* access modifiers changed from: private */
    public void a(final List<ScanResult> list) {
        CommonApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList<Device> arrayList3 = new ArrayList<>();
                List<Device> d = SmartHomeDeviceManager.a().d();
                ScanResult unused = ApDeviceManager.this.i = null;
                for (Device next : d) {
                    if (next.pid == 7) {
                        arrayList3.add(next);
                    }
                }
                Iterator it = list.iterator();
                while (true) {
                    boolean z = false;
                    if (!it.hasNext()) {
                        break;
                    }
                    ScanResult scanResult = (ScanResult) it.next();
                    for (Device device : arrayList3) {
                        if (device.mac.equalsIgnoreCase(scanResult.BSSID)) {
                            arrayList.add(scanResult);
                            z = true;
                        }
                    }
                    if (!z) {
                        arrayList2.add(scanResult);
                    }
                }
                ApDeviceManager.this.h.clear();
                ApDeviceManager.this.h.addAll(arrayList);
                Iterator it2 = arrayList3.iterator();
                boolean z2 = false;
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Device device2 = (Device) it2.next();
                    Iterator it3 = ApDeviceManager.this.h.iterator();
                    boolean z3 = false;
                    while (true) {
                        if (!it3.hasNext()) {
                            break;
                        } else if (((ScanResult) it3.next()).BSSID.equalsIgnoreCase(device2.mac)) {
                            if (!device2.isOnline) {
                                z2 = true;
                                break;
                            }
                            z3 = true;
                        }
                    }
                    if (!z2) {
                        if (!z3) {
                            z2 = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (z2) {
                    SmartHomeDeviceManager.a().c();
                }
                ApDeviceManager.this.b(arrayList2);
            }
        });
    }

    public synchronized void a(ScanResult scanResult) {
        this.i = scanResult;
    }

    /* access modifiers changed from: private */
    public void b(List<ScanResult> list) {
        Intent intent = new Intent();
        intent.setAction(d);
        LocalBroadcastManager.getInstance(CommonApplication.getAppContext()).sendBroadcast(intent);
        this.g.clear();
        this.g.addAll(list);
    }

    public void d() {
        LocalBroadcastManager.getInstance(CommonApplication.getAppContext()).registerReceiver(this.j, new IntentFilter(b));
        CommonApplication.getAppContext().registerReceiver(this.j, new IntentFilter("android.net.wifi.STATE_CHANGE"));
    }

    public void e() {
        try {
            LocalBroadcastManager.getInstance(CommonApplication.getAppContext()).unregisterReceiver(this.j);
            CommonApplication.getAppContext().unregisterReceiver(this.j);
        } catch (Exception unused) {
        }
    }
}
