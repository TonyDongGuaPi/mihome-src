package com.amap.openapi;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.amap.location.common.util.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class be {
    public static List<ScanResult> a(@NonNull List<ScanResult> list) {
        Collections.sort(list, new Comparator<ScanResult>() {
            /* renamed from: a */
            public final int compare(ScanResult scanResult, ScanResult scanResult2) {
                return scanResult2.level - scanResult.level;
            }
        });
        return list;
    }

    public static void a(@NonNull List<aa> list, @Nullable List<ScanResult> list2) {
        list.clear();
        if (list2 != null) {
            List<ScanResult> a2 = a(b(list2));
            int size = a2.size();
            if (size > 40) {
                size = 40;
            }
            for (int i = 0; i < size; i++) {
                ScanResult scanResult = a2.get(i);
                if (scanResult != null) {
                    aa aaVar = new aa();
                    aaVar.f4607a = f.a(scanResult.BSSID);
                    aaVar.b = (short) scanResult.level;
                    aaVar.c = scanResult.SSID != null ? scanResult.SSID.substring(0, Math.min(32, scanResult.SSID.length())) : "";
                    aaVar.f = (short) scanResult.frequency;
                    if (Build.VERSION.SDK_INT >= 17) {
                        aaVar.e = scanResult.timestamp / 1000;
                        aaVar.d = (short) ((int) ((SystemClock.elapsedRealtime() - aaVar.e) / 1000));
                        if (aaVar.d < 0) {
                            aaVar.d = 0;
                        }
                    }
                    list.add(aaVar);
                }
            }
        }
    }

    public static boolean a(@NonNull Context context) {
        return f.a(context) == 1;
    }

    public static boolean a(@Nullable WifiManager wifiManager) {
        if (wifiManager == null) {
            return false;
        }
        try {
            if (wifiManager.isWifiEnabled()) {
                return true;
            }
            return Build.VERSION.SDK_INT >= 18 && wifiManager.isScanAlwaysAvailable();
        } catch (Exception | SecurityException unused) {
            return false;
        }
    }

    public static boolean a(@Nullable List<ScanResult> list, @Nullable List<ScanResult> list2, double d) {
        if (!(list == null || list2 == null)) {
            int size = list.size();
            int size2 = list2.size();
            int i = size + size2;
            if (size <= size2) {
                List<ScanResult> list3 = list2;
                list2 = list;
                list = list3;
            }
            HashMap hashMap = new HashMap(list.size());
            for (ScanResult next : list) {
                if (next.BSSID != null) {
                    hashMap.put(next.BSSID, 1);
                }
            }
            int i2 = 0;
            for (ScanResult next2 : list2) {
                if (!(next2.BSSID == null || ((Integer) hashMap.get(next2.BSSID)) == null)) {
                    i2++;
                }
            }
            double d2 = (double) i2;
            Double.isNaN(d2);
            double d3 = (double) i;
            Double.isNaN(d3);
            if (d2 * 2.0d >= d3 * d) {
                return true;
            }
        }
        return false;
    }

    private static List<ScanResult> b(@NonNull List<ScanResult> list) {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            ScanResult scanResult = list.get(i);
            hashMap.put(Integer.valueOf(scanResult.level), scanResult);
        }
        arrayList.addAll(hashMap.values());
        return arrayList;
    }
}
