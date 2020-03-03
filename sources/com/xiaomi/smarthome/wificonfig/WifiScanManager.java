package com.xiaomi.smarthome.wificonfig;

import android.os.Handler;
import android.os.Looper;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;

public class WifiScanManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f22942a = 10000;
    public static final int b = 30000;
    public static final int c = 2000;
    private static final String d = "com.xiaomi.smarthome.wifiscanservice.SCAN_RESULTS";
    private static final String e = "com.xiaomi.smarthome.wifiscanservice.STATE_CHANGE";
    private static final String f = "com.xiaomi.smarthome.wifiscanservice.USER_PRESENT";
    private static final String g = "com.xiaomi.smarthome.wifiscanservice.CONNECTIVITY_CHANGE";
    private static WifiScanManager h;
    /* access modifiers changed from: private */
    public Handler i = new Handler(Looper.getMainLooper());

    private void e() {
    }

    private void f() {
    }

    public static WifiScanManager a() {
        if (h == null) {
            synchronized (WifiScanManager.class) {
                if (h == null) {
                    h = new WifiScanManager();
                }
            }
        }
        return h;
    }

    private WifiScanManager() {
    }

    public void b() {
        CoreApi.a().a((CoreApi.WifiScanCallback) new CoreApi.WifiScanCallback() {
            public void a(final String str) {
                WifiScanManager.this.i.post(new Runnable() {
                    public void run() {
                        WifiScanManager.this.a(str);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r3) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            r1 = -837326635(0xffffffffce1768d5, float:-6.3505747E8)
            if (r0 == r1) goto L_0x0037
            r1 = -659219007(0xffffffffd8b51dc1, float:-1.59311517E15)
            if (r0 == r1) goto L_0x002d
            r1 = 1384661419(0x528841ab, float:2.92608639E11)
            if (r0 == r1) goto L_0x0023
            r1 = 2114614718(0x7e0a75be, float:4.6011204E37)
            if (r0 == r1) goto L_0x0019
            goto L_0x0041
        L_0x0019:
            java.lang.String r0 = "com.xiaomi.smarthome.wifiscanservice.USER_PRESENT"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0041
            r3 = 2
            goto L_0x0042
        L_0x0023:
            java.lang.String r0 = "com.xiaomi.smarthome.wifiscanservice.SCAN_RESULTS"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0041
            r3 = 0
            goto L_0x0042
        L_0x002d:
            java.lang.String r0 = "com.xiaomi.smarthome.wifiscanservice.CONNECTIVITY_CHANGE"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0041
            r3 = 3
            goto L_0x0042
        L_0x0037:
            java.lang.String r0 = "com.xiaomi.smarthome.wifiscanservice.STATE_CHANGE"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0041
            r3 = 1
            goto L_0x0042
        L_0x0041:
            r3 = -1
        L_0x0042:
            switch(r3) {
                case 0: goto L_0x005b;
                case 1: goto L_0x0054;
                case 2: goto L_0x004d;
                case 3: goto L_0x0046;
                default: goto L_0x0045;
            }
        L_0x0045:
            goto L_0x0061
        L_0x0046:
            r2.c()
            r2.g()
            goto L_0x0061
        L_0x004d:
            r2.c()
            r2.f()
            goto L_0x0061
        L_0x0054:
            r2.c()
            r2.e()
            goto L_0x0061
        L_0x005b:
            r2.c()
            r2.d()
        L_0x0061:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.wificonfig.WifiScanManager.a(java.lang.String):void");
    }

    public void c() {
        if (WifiDeviceFinder.d().e() == null) {
            WifiDeviceFinder.d().a(SHApplication.getAppContext());
            WifiDeviceFinder.d().a();
        }
        if (WifiScanHomelog.d().h() == null) {
            WifiScanHomelog.d().a(SHApplication.getAppContext());
            WifiScanHomelog.d().a();
        }
    }

    private void d() {
        WifiDeviceFinder.d().h();
        WifiScanHomelog.d().k();
    }

    private void g() {
        WifiScanHomelog.d().l();
    }
}
