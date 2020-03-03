package com.xiaomi.smarthome.wificonfig;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.coloros.mcssdk.PushManager;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.SHConfig;
import com.xiaomi.smarthome.device.ApDeviceManager;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.service.DeviceObserveService;
import com.xiaomi.smarthome.wificonfig.WifiScanServices;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;

public class WifiDeviceFinder implements WifiScanServices.WIFIScanMonitor {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22920a = "WifiDeviceFinder";
    public static final String b = "wifi_scan_result_broadcast";
    public static final String c = "wifi_scan_result";
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 10000;
    public static final int g = 10000;
    public static final int h = 20000;
    public static ArrayList<ScanResult> j = new ArrayList<>();
    public static ArrayList<ScanResult> k = new ArrayList<>();
    public static HashMap<String, Integer> l = new HashMap<>();
    public static Map<String, CacheScanResult> m = new ConcurrentHashMap();
    public static Map<String, CacheScanResult> n = new ConcurrentHashMap();
    private static WifiDeviceFinder t = null;
    NotificationManager i;
    Handler o = new Handler();
    HandlerThread p;
    Handler q;
    private WifiManager r;
    /* access modifiers changed from: private */
    public Context s;

    public boolean c() {
        return true;
    }

    public static WifiDeviceFinder d() {
        if (t == null) {
            t = new WifiDeviceFinder();
        }
        return t;
    }

    public static class CacheScanResult {

        /* renamed from: a  reason: collision with root package name */
        long f22925a;
        long b;
        ScanResult c;

        public String toString() {
            return "findTime - " + this.b + ", " + this.c.toString();
        }
    }

    private class InternalHandler extends Handler {
        public InternalHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Long valueOf = Long.valueOf(System.currentTimeMillis());
                    WifiDeviceFinder.this.g();
                    Log.e(WifiDeviceFinder.f22920a, "start process end time - " + (System.currentTimeMillis() - valueOf.longValue()));
                    return;
                case 2:
                    try {
                        ScanResult scanResult = (ScanResult) message.obj;
                        if (scanResult != null) {
                            Iterator<ScanResult> it = WifiDeviceFinder.j.iterator();
                            while (it.hasNext()) {
                                if (it.next().SSID.equals(scanResult.SSID)) {
                                    return;
                                }
                            }
                            Iterator<ScanResult> it2 = WifiDeviceFinder.k.iterator();
                            while (it2.hasNext()) {
                                if (it2.next().SSID.equals(scanResult.SSID)) {
                                    return;
                                }
                            }
                            if (DeviceFactory.f(scanResult)) {
                                WifiDeviceFinder.j.add(scanResult);
                                Intent intent = new Intent("wifi_scan_result_broadcast");
                                ArrayList arrayList = new ArrayList();
                                arrayList.addAll(WifiDeviceFinder.j);
                                intent.putParcelableArrayListExtra(WifiDeviceFinder.c, arrayList);
                                WifiDeviceFinder.this.s.sendBroadcast(intent);
                                LocalBroadcastManager.getInstance(WifiDeviceFinder.this.s).sendBroadcast(intent);
                                return;
                            } else if (DeviceFactory.e(scanResult)) {
                                int i = 0;
                                while (true) {
                                    if (i < WifiDeviceFinder.k.size()) {
                                        if (WifiDeviceFinder.k.get(i).SSID.compareTo(scanResult.SSID) > 0) {
                                            WifiDeviceFinder.k.add(i, scanResult);
                                        } else {
                                            i++;
                                        }
                                    }
                                }
                                WifiDeviceFinder.k.add(scanResult);
                                return;
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    } catch (Exception unused) {
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public void a() {
        t = this;
        this.i = (NotificationManager) this.s.getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        this.r = (WifiManager) this.s.getSystemService("wifi");
        f();
    }

    public void b() {
        if (this.p != null) {
            this.p.quit();
            this.p = null;
        }
        t = null;
    }

    public void a(Context context) {
        this.s = context;
    }

    public Context e() {
        return this.s;
    }

    public static void a(ScanResult scanResult) {
        if (scanResult != null) {
            Iterator<ScanResult> it = j.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ScanResult next = it.next();
                if (next.BSSID.equals(scanResult.BSSID)) {
                    j.remove(next);
                    break;
                }
            }
            for (Map.Entry next2 : m.entrySet()) {
                if (((CacheScanResult) next2.getValue()).c.BSSID.equals(scanResult.BSSID)) {
                    m.remove(next2.getKey());
                }
            }
            if (t != null) {
                Intent intent = new Intent("wifi_scan_result_broadcast");
                intent.putParcelableArrayListExtra(c, j);
                SHApplication.getAppContext().sendBroadcast(intent);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void f() {
        String c2 = SHConfig.a().c("wifi_ignore_list");
        if (c2 != null) {
            try {
                JSONArray jSONArray = new JSONArray(c2);
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    l.put(jSONArray.optString(i2), Integer.valueOf(i2));
                }
            } catch (JSONException unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void g() {
        List<ScanResult> list;
        boolean z;
        boolean z2;
        boolean z3 = true;
        try {
            list = this.r.getScanResults();
        } catch (Exception e2) {
            LogUtilGrey.a("scanResult", Log.getStackTraceString(e2), true);
            list = null;
        }
        if (list == null || list.size() == 0) {
            LogUtilGrey.a("scanResult", "WifiDeviceFinder scanResult is" + list, true);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        boolean z4 = true;
        for (int i2 = 0; i2 < list.size(); i2++) {
            ScanResult scanResult = list.get(i2);
            LogUtilGrey.a("scanResult", "WifiDeviceFinder " + scanResult.level + " " + scanResult.SSID, true);
            if (DeviceFactory.f(scanResult) && !DeviceFactory.e(list.get(i2)) && scanResult.level > -60) {
                if (DeviceFactory.d(scanResult) == DeviceFactory.AP_TYPE.AP_MIDEA) {
                    z4 = false;
                }
                CacheScanResult cacheScanResult = m.get(list.get(i2).SSID);
                if (cacheScanResult == null) {
                    cacheScanResult = new CacheScanResult();
                    m.put(list.get(i2).SSID, cacheScanResult);
                    cacheScanResult.b = currentTimeMillis;
                    cacheScanResult.f22925a = currentTimeMillis;
                }
                cacheScanResult.c = list.get(i2);
                cacheScanResult.f22925a = currentTimeMillis;
            }
            if (DeviceFactory.e(list.get(i2)) && list.get(i2).level > -70) {
                CacheScanResult cacheScanResult2 = n.get(list.get(i2).SSID);
                if (cacheScanResult2 == null) {
                    cacheScanResult2 = new CacheScanResult();
                    n.put(list.get(i2).SSID, cacheScanResult2);
                    cacheScanResult2.b = currentTimeMillis;
                    cacheScanResult2.f22925a = currentTimeMillis;
                }
                cacheScanResult2.c = list.get(i2);
                cacheScanResult2.f22925a = currentTimeMillis;
            }
        }
        Set<String> keySet = m.keySet();
        ArrayList arrayList = new ArrayList();
        for (String next : keySet) {
            CacheScanResult cacheScanResult3 = m.get(next);
            if (currentTimeMillis - cacheScanResult3.f22925a < 10000) {
                arrayList.add(cacheScanResult3.c);
            } else {
                m.remove(next);
            }
        }
        Collections.sort(arrayList, new Comparator<ScanResult>() {
            /* renamed from: a */
            public int compare(ScanResult scanResult, ScanResult scanResult2) {
                CacheScanResult cacheScanResult = WifiDeviceFinder.m.get(scanResult.SSID);
                CacheScanResult cacheScanResult2 = WifiDeviceFinder.m.get(scanResult2.SSID);
                if (cacheScanResult.f22925a != cacheScanResult2.f22925a) {
                    return (int) (cacheScanResult.f22925a - cacheScanResult2.f22925a);
                }
                return scanResult.SSID.compareTo(scanResult2.SSID);
            }
        });
        if (j.size() != arrayList.size()) {
            j.clear();
            j.addAll(arrayList);
        } else {
            int i3 = 0;
            while (true) {
                if (i3 >= j.size()) {
                    z2 = false;
                    break;
                } else if (!j.get(i3).SSID.equals(((ScanResult) arrayList.get(i3)).SSID)) {
                    z2 = true;
                    break;
                } else {
                    i3++;
                }
            }
            if (z2) {
                j.clear();
                j.addAll(arrayList);
            } else {
                z4 = false;
            }
        }
        Set<String> keySet2 = n.keySet();
        ArrayList arrayList2 = new ArrayList();
        for (String next2 : keySet2) {
            CacheScanResult cacheScanResult4 = n.get(next2);
            if (currentTimeMillis - cacheScanResult4.f22925a < 10000) {
                arrayList2.add(cacheScanResult4.c);
            } else {
                n.remove(next2);
            }
        }
        Collections.sort(arrayList2, new Comparator<ScanResult>() {
            /* renamed from: a */
            public int compare(ScanResult scanResult, ScanResult scanResult2) {
                CacheScanResult cacheScanResult = WifiDeviceFinder.n.get(scanResult.SSID);
                CacheScanResult cacheScanResult2 = WifiDeviceFinder.n.get(scanResult2.SSID);
                if (cacheScanResult.f22925a != cacheScanResult2.f22925a) {
                    return (int) (cacheScanResult.f22925a - cacheScanResult2.f22925a);
                }
                return scanResult.SSID.compareTo(scanResult2.SSID);
            }
        });
        if (k.size() != arrayList2.size()) {
            k.clear();
            k.addAll(arrayList2);
        } else {
            int i4 = 0;
            while (true) {
                if (i4 >= k.size()) {
                    z = false;
                    break;
                } else if (!k.get(i4).SSID.equals(((ScanResult) arrayList2.get(i4)).SSID)) {
                    z = true;
                    break;
                } else {
                    i4++;
                }
            }
            if (z) {
                k.clear();
                k.addAll(arrayList2);
            } else {
                z3 = false;
            }
        }
        if (z4) {
            this.o.post(new Runnable() {
                public void run() {
                    Intent intent = new Intent("wifi_scan_result_broadcast");
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll(WifiDeviceFinder.j);
                    intent.putParcelableArrayListExtra(WifiDeviceFinder.c, arrayList);
                    WifiDeviceFinder.this.s.sendBroadcast(intent);
                    LocalBroadcastManager.getInstance(WifiDeviceFinder.this.s).sendBroadcast(intent);
                    try {
                        DeviceObserveService.getInstance().onStartCommand();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        if (z3) {
            this.o.post(new Runnable() {
                public void run() {
                    Intent intent = new Intent(ApDeviceManager.b);
                    intent.putParcelableArrayListExtra(ApDeviceManager.c, WifiDeviceFinder.k);
                    LocalBroadcastManager.getInstance(WifiDeviceFinder.this.s).sendBroadcast(intent);
                    DeviceObserveService.getInstance().onStartCommand();
                }
            });
        }
    }

    public void h() {
        if (this.p == null) {
            this.p = new MessageHandlerThread(f22920a);
            this.p.start();
            this.q = new InternalHandler(this.p.getLooper());
        }
        this.q.removeMessages(1);
        this.q.sendEmptyMessage(1);
    }

    public void b(ScanResult scanResult) {
        if (this.p == null) {
            this.p = new MessageHandlerThread(f22920a);
            this.p.start();
            this.q = new InternalHandler(this.p.getLooper());
        }
        this.q.sendMessage(Message.obtain(this.q, 2, scanResult));
    }
}
