package com.amap.location.common.model;

import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.SystemClock;
import com.amap.location.common.util.f;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class WifiStatus {

    /* renamed from: a  reason: collision with root package name */
    public long f4590a;
    public WiFi b;
    private List<WiFi> c = Collections.emptyList();

    public WifiStatus() {
    }

    public WifiStatus(long j) {
        this.f4590a = j;
    }

    public WifiStatus(long j, List<ScanResult> list) {
        this.f4590a = j;
        this.c = b(list);
    }

    public WifiStatus(long j, List<WiFi> list, int i) {
        this.f4590a = j;
        this.c = list;
    }

    private String a(boolean z) {
        String str;
        String str2;
        StringBuilder sb;
        String str3;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("wifiStatus:[");
        sb2.append("updateTime=" + this.f4590a + ",");
        if (this.b != null) {
            str = "mainWifi:[" + this.b.toString() + "],";
        } else {
            str = "mainWifi:[null],";
        }
        sb2.append(str);
        if (this.c != null) {
            ArrayList arrayList = new ArrayList();
            if (this.c.size() <= 5) {
                arrayList.addAll(this.c);
                sb = new StringBuilder("wifiList=");
            } else if (z) {
                arrayList.addAll(this.c.subList(0, 5));
                sb = new StringBuilder("wifiList=");
                str3 = arrayList.toString();
                sb.append(str3);
                str2 = sb.toString();
            } else {
                arrayList.addAll(this.c);
                sb = new StringBuilder("wifiList=");
            }
            str3 = this.c.toString();
            sb.append(str3);
            str2 = sb.toString();
        } else {
            str2 = "wifiList=0";
        }
        sb2.append(str2);
        sb2.append(Operators.ARRAY_END_STR);
        return sb2.toString();
    }

    public final int a() {
        return this.c.size();
    }

    public final WiFi a(int i) {
        return this.c.get(i);
    }

    public void a(List<WiFi> list) {
        this.c = list;
    }

    public List<WiFi> b() {
        return this.c;
    }

    public List<WiFi> b(List<ScanResult> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<ScanResult> it = list.iterator();
        if (Build.VERSION.SDK_INT >= 17) {
            while (it.hasNext()) {
                ScanResult next = it.next();
                if (next != null) {
                    arrayList.add(new WiFi(f.a(next.BSSID), next.SSID, next.level, next.frequency, next.timestamp / 1000));
                }
            }
        } else {
            while (it.hasNext()) {
                ScanResult next2 = it.next();
                if (next2 != null) {
                    arrayList.add(new WiFi(f.a(next2.BSSID), next2.SSID, next2.level, next2.frequency, SystemClock.elapsedRealtime()));
                }
            }
        }
        return arrayList;
    }

    /* renamed from: c */
    public WifiStatus clone() {
        WifiStatus wifiStatus = new WifiStatus(this.f4590a);
        if (this.b != null) {
            wifiStatus.b = this.b.clone();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.c);
        wifiStatus.c = arrayList;
        return wifiStatus;
    }

    public String d() {
        return a(true);
    }

    public String toString() {
        return a(false);
    }
}
