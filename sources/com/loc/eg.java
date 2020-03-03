package com.loc;

import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import com.payu.custombrowser.util.CBConstant;
import com.xiaomi.smarthome.download.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

public final class eg {
    static long c;
    static long d;
    static long e;
    static long f;
    static long g;
    public static HashMap<String, Long> q = new HashMap<>(36);
    public static long r = 0;
    static int s = 0;

    /* renamed from: a  reason: collision with root package name */
    WifiManager f6583a;
    ArrayList<ScanResult> b = new ArrayList<>();
    Context h;
    boolean i = false;
    StringBuilder j = null;
    boolean k = true;
    boolean l = true;
    boolean m = true;
    String n = null;
    TreeMap<Integer, ScanResult> o = null;
    public boolean p = true;
    ConnectivityManager t = null;
    volatile boolean u = false;
    private volatile WifiInfo v = null;
    private long w = 30000;

    public eg(Context context, WifiManager wifiManager) {
        this.f6583a = wifiManager;
        this.h = context;
    }

    public static long a() {
        return ((fa.c() - r) / 1000) + 1;
    }

    private static boolean a(int i2) {
        int i3;
        try {
            i3 = WifiManager.calculateSignalLevel(i2, 20);
        } catch (ArithmeticException e2) {
            es.a(e2, "Aps", "wifiSigFine");
            i3 = 20;
        }
        return i3 > 0;
    }

    public static boolean a(WifiInfo wifiInfo) {
        return wifiInfo != null && !TextUtils.isEmpty(wifiInfo.getSSID()) && fa.a(wifiInfo.getBSSID());
    }

    public static String k() {
        return String.valueOf(fa.c() - f);
    }

    private List<ScanResult> l() {
        long c2;
        if (this.f6583a != null) {
            try {
                List<ScanResult> scanResults = this.f6583a.getScanResults();
                if (Build.VERSION.SDK_INT >= 17) {
                    HashMap<String, Long> hashMap = new HashMap<>(36);
                    for (ScanResult next : scanResults) {
                        hashMap.put(next.BSSID, Long.valueOf(next.timestamp));
                    }
                    if (q.isEmpty() || !q.equals(hashMap)) {
                        q = hashMap;
                        c2 = fa.c();
                    }
                    this.n = null;
                    return scanResults;
                }
                c2 = fa.c();
                r = c2;
                this.n = null;
                return scanResults;
            } catch (SecurityException e2) {
                this.n = e2.getMessage();
            } catch (Throwable th) {
                this.n = null;
                es.a(th, "WifiManagerWrapper", "getScanResults");
            }
        }
        return null;
    }

    private WifiInfo m() {
        try {
            if (this.f6583a != null) {
                return this.f6583a.getConnectionInfo();
            }
            return null;
        } catch (Throwable th) {
            es.a(th, "WifiManagerWrapper", "getConnectionInfo");
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0058, code lost:
        if (r0 < r6) goto L_0x0075;
     */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0078 A[Catch:{ Throwable -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void n() {
        /*
            r10 = this;
            boolean r0 = r10.o()
            if (r0 == 0) goto L_0x0088
            long r0 = com.loc.fa.c()     // Catch:{ Throwable -> 0x007f }
            long r2 = c     // Catch:{ Throwable -> 0x007f }
            r4 = 0
            long r0 = r0 - r2
            r2 = 4900(0x1324, double:2.421E-320)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x0075
            android.net.ConnectivityManager r2 = r10.t     // Catch:{ Throwable -> 0x007f }
            if (r2 != 0) goto L_0x0024
            android.content.Context r2 = r10.h     // Catch:{ Throwable -> 0x007f }
            java.lang.String r3 = "connectivity"
            java.lang.Object r2 = com.loc.fa.a((android.content.Context) r2, (java.lang.String) r3)     // Catch:{ Throwable -> 0x007f }
            android.net.ConnectivityManager r2 = (android.net.ConnectivityManager) r2     // Catch:{ Throwable -> 0x007f }
            r10.t = r2     // Catch:{ Throwable -> 0x007f }
        L_0x0024:
            android.net.ConnectivityManager r2 = r10.t     // Catch:{ Throwable -> 0x007f }
            boolean r2 = r10.a((android.net.ConnectivityManager) r2)     // Catch:{ Throwable -> 0x007f }
            if (r2 == 0) goto L_0x0032
            r2 = 9900(0x26ac, double:4.8912E-320)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x0075
        L_0x0032:
            int r2 = s     // Catch:{ Throwable -> 0x007f }
            r3 = 1
            if (r2 <= r3) goto L_0x005a
            long r4 = r10.w     // Catch:{ Throwable -> 0x007f }
            r6 = 30000(0x7530, double:1.4822E-319)
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x0042
            long r6 = r10.w     // Catch:{ Throwable -> 0x007f }
            goto L_0x0050
        L_0x0042:
            long r4 = com.loc.er.B()     // Catch:{ Throwable -> 0x007f }
            r8 = -1
            int r2 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r2 == 0) goto L_0x0050
            long r6 = com.loc.er.B()     // Catch:{ Throwable -> 0x007f }
        L_0x0050:
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x007f }
            r4 = 28
            if (r2 < r4) goto L_0x005a
            int r2 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r2 < 0) goto L_0x0075
        L_0x005a:
            android.net.wifi.WifiManager r0 = r10.f6583a     // Catch:{ Throwable -> 0x007f }
            if (r0 == 0) goto L_0x0075
            long r0 = com.loc.fa.c()     // Catch:{ Throwable -> 0x007f }
            c = r0     // Catch:{ Throwable -> 0x007f }
            int r0 = s     // Catch:{ Throwable -> 0x007f }
            r1 = 2
            if (r0 >= r1) goto L_0x006e
            int r0 = s     // Catch:{ Throwable -> 0x007f }
            int r0 = r0 + r3
            s = r0     // Catch:{ Throwable -> 0x007f }
        L_0x006e:
            android.net.wifi.WifiManager r0 = r10.f6583a     // Catch:{ Throwable -> 0x007f }
            boolean r0 = r0.startScan()     // Catch:{ Throwable -> 0x007f }
            goto L_0x0076
        L_0x0075:
            r0 = 0
        L_0x0076:
            if (r0 == 0) goto L_0x007e
            long r0 = com.loc.fa.c()     // Catch:{ Throwable -> 0x007f }
            e = r0     // Catch:{ Throwable -> 0x007f }
        L_0x007e:
            return
        L_0x007f:
            r0 = move-exception
            java.lang.String r1 = "WifiManager"
            java.lang.String r2 = "wifiScan"
            com.loc.es.a(r0, r1, r2)
        L_0x0088:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.eg.n():void");
    }

    private boolean o() {
        this.p = this.f6583a == null ? false : fa.h(this.h);
        if (!this.p || !this.k) {
            return false;
        }
        if (e != 0) {
            if (fa.c() - e < 4900 || fa.c() - f < Constants.x) {
                return false;
            }
            int i2 = ((fa.c() - f) > 4900 ? 1 : ((fa.c() - f) == 4900 ? 0 : -1));
        }
        return true;
    }

    public final void a(boolean z) {
        Context context = this.h;
        if (er.A() && this.m && this.f6583a != null && context != null && z && fa.d() > 17) {
            ContentResolver contentResolver = context.getContentResolver();
            try {
                if (((Integer) ew.a("android.provider.Settings$Global", "getInt", new Object[]{contentResolver, "wifi_scan_always_enabled"}, (Class<?>[]) new Class[]{ContentResolver.class, String.class})).intValue() == 0) {
                    ew.a("android.provider.Settings$Global", "putInt", new Object[]{contentResolver, "wifi_scan_always_enabled", 1}, (Class<?>[]) new Class[]{ContentResolver.class, String.class, Integer.TYPE});
                }
            } catch (Throwable th) {
                es.a(th, "WifiManagerWrapper", "enableWifiAlwaysScan");
            }
        }
    }

    public final void a(boolean z, boolean z2, boolean z3, long j2) {
        this.k = z;
        this.l = z2;
        this.m = z3;
        if (j2 < 10000) {
            this.w = 10000;
        } else {
            this.w = j2;
        }
    }

    public final boolean a(ConnectivityManager connectivityManager) {
        WifiManager wifiManager = this.f6583a;
        if (wifiManager == null) {
            return false;
        }
        try {
            return fa.a(connectivityManager.getActiveNetworkInfo()) == 1 && a(wifiManager.getConnectionInfo());
        } catch (Throwable th) {
            es.a(th, "WifiManagerWrapper", "wifiAccess");
            return false;
        }
    }

    public final String b() {
        return this.n;
    }

    public final void b(boolean z) {
        String str;
        if (!z) {
            n();
        } else if (o()) {
            long c2 = fa.c();
            if (c2 - d >= 10000) {
                this.b.clear();
                g = f;
            }
            n();
            if (c2 - d >= 10000) {
                for (int i2 = 20; i2 > 0 && f == g; i2--) {
                    try {
                        Thread.sleep(150);
                    } catch (Throwable unused) {
                    }
                }
            }
        }
        if (this.u) {
            this.u = false;
            d();
        }
        if (g != f) {
            List<ScanResult> list = null;
            try {
                list = l();
            } catch (Throwable th) {
                es.a(th, "WifiManager", "updateScanResult");
            }
            g = f;
            if (list != null) {
                this.b.clear();
                this.b.addAll(list);
            } else {
                this.b.clear();
            }
        }
        if (fa.c() - f > 20000) {
            this.b.clear();
        }
        d = fa.c();
        if (this.b.isEmpty()) {
            f = fa.c();
            List<ScanResult> l2 = l();
            if (l2 != null) {
                this.b.addAll(l2);
            }
        }
        if (this.b != null && !this.b.isEmpty()) {
            if (fa.c() - f > 3600000) {
                d();
            }
            if (this.o == null) {
                this.o = new TreeMap<>(Collections.reverseOrder());
            }
            this.o.clear();
            int size = this.b.size();
            for (int i3 = 0; i3 < size; i3++) {
                ScanResult scanResult = this.b.get(i3);
                if (fa.a(scanResult != null ? scanResult.BSSID : "") && (size <= 20 || a(scanResult.level))) {
                    if (!TextUtils.isEmpty(scanResult.SSID)) {
                        if (!"<unknown ssid>".equals(scanResult.SSID)) {
                            str = String.valueOf(i3);
                        }
                        this.o.put(Integer.valueOf((scanResult.level * 25) + i3), scanResult);
                    } else {
                        str = "unkwn";
                    }
                    scanResult.SSID = str;
                    this.o.put(Integer.valueOf((scanResult.level * 25) + i3), scanResult);
                }
            }
            this.b.clear();
            for (ScanResult add : this.o.values()) {
                this.b.add(add);
            }
            this.o.clear();
        }
    }

    public final ArrayList<ScanResult> c() {
        if (this.b == null) {
            return null;
        }
        ArrayList<ScanResult> arrayList = new ArrayList<>();
        if (!this.b.isEmpty()) {
            arrayList.addAll(this.b);
        }
        return arrayList;
    }

    public final void d() {
        this.v = null;
        this.b.clear();
    }

    public final void e() {
        if (this.f6583a != null && fa.c() - f > 4900) {
            f = fa.c();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void f() {
        /*
            r4 = this;
            android.net.wifi.WifiManager r0 = r4.f6583a
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            r0 = 4
            android.net.wifi.WifiManager r1 = r4.f6583a     // Catch:{ Throwable -> 0x0011 }
            if (r1 == 0) goto L_0x0019
            android.net.wifi.WifiManager r1 = r4.f6583a     // Catch:{ Throwable -> 0x0011 }
            int r1 = r1.getWifiState()     // Catch:{ Throwable -> 0x0011 }
            goto L_0x001a
        L_0x0011:
            r1 = move-exception
            java.lang.String r2 = "Aps"
            java.lang.String r3 = "onReceive part"
            com.loc.es.a(r1, r2, r3)
        L_0x0019:
            r1 = 4
        L_0x001a:
            java.util.ArrayList<android.net.wifi.ScanResult> r2 = r4.b
            if (r2 != 0) goto L_0x0025
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r4.b = r2
        L_0x0025:
            if (r1 == r0) goto L_0x002b
            switch(r1) {
                case 0: goto L_0x002b;
                case 1: goto L_0x002b;
                default: goto L_0x002a;
            }
        L_0x002a:
            goto L_0x002e
        L_0x002b:
            r0 = 1
            r4.u = r0
        L_0x002e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.eg.f():void");
    }

    public final WifiInfo g() {
        this.v = m();
        return this.v;
    }

    public final boolean h() {
        return this.i;
    }

    public final String i() {
        if (this.j == null) {
            this.j = new StringBuilder(700);
        } else {
            this.j.delete(0, this.j.length());
        }
        this.i = false;
        String str = "";
        this.v = g();
        if (a(this.v)) {
            str = this.v.getBSSID();
        }
        int size = this.b.size();
        boolean z = false;
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            String str2 = this.b.get(i2).BSSID;
            if (!this.l && !"<unknown ssid>".equals(this.b.get(i2).SSID)) {
                z = true;
            }
            String str3 = CBConstant.NB;
            if (str.equals(str2)) {
                str3 = "access";
                z2 = true;
            }
            this.j.append(String.format(Locale.US, "#%s,%s", new Object[]{str2, str3}));
        }
        if (this.b.size() == 0) {
            z = true;
        }
        if (!this.l && !z) {
            this.i = true;
        }
        if (!z2 && !TextUtils.isEmpty(str)) {
            StringBuilder sb = this.j;
            sb.append("#");
            sb.append(str);
            this.j.append(",access");
        }
        return this.j.toString();
    }

    public final void j() {
        d();
        this.b.clear();
    }
}
