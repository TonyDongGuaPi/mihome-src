package com.xiaomi.push;

import android.text.TextUtils;
import com.taobao.weex.common.Constants;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class l {

    /* renamed from: a  reason: collision with root package name */
    private static int f12836a = 0;
    private static int b = -1;
    private static Map<String, o> c;

    public static String a(String str) {
        try {
            return (String) ba.a("android.os.SystemProperties", "get", str, "");
        } catch (Exception e) {
            b.a((Throwable) e);
        } catch (Throwable unused) {
        }
        return null;
    }

    public static synchronized boolean a() {
        boolean z;
        synchronized (l.class) {
            z = true;
            if (c() != 1) {
                z = false;
            }
        }
        return z;
    }

    public static o b(String str) {
        o c2 = c(str);
        return c2 == null ? o.Global : c2;
    }

    public static synchronized boolean b() {
        boolean z;
        synchronized (l.class) {
            z = c() == 2;
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0027 A[Catch:{ Throwable -> 0x002c }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0028 A[Catch:{ Throwable -> 0x002c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized int c() {
        /*
            java.lang.Class<com.xiaomi.push.l> r0 = com.xiaomi.push.l.class
            monitor-enter(r0)
            int r1 = f12836a     // Catch:{ all -> 0x004e }
            if (r1 != 0) goto L_0x004a
            r1 = 0
            java.lang.String r2 = "ro.miui.ui.version.code"
            java.lang.String r2 = a(r2)     // Catch:{ Throwable -> 0x002c }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x002c }
            r3 = 1
            if (r2 == 0) goto L_0x0024
            java.lang.String r2 = "ro.miui.ui.version.name"
            java.lang.String r2 = a(r2)     // Catch:{ Throwable -> 0x002c }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x002c }
            if (r2 != 0) goto L_0x0022
            goto L_0x0024
        L_0x0022:
            r2 = 0
            goto L_0x0025
        L_0x0024:
            r2 = 1
        L_0x0025:
            if (r2 == 0) goto L_0x0028
            goto L_0x0029
        L_0x0028:
            r3 = 2
        L_0x0029:
            f12836a = r3     // Catch:{ Throwable -> 0x002c }
            goto L_0x0034
        L_0x002c:
            r2 = move-exception
            java.lang.String r3 = "get isMIUI failed"
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r3, (java.lang.Throwable) r2)     // Catch:{ all -> 0x004e }
            f12836a = r1     // Catch:{ all -> 0x004e }
        L_0x0034:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x004e }
            r1.<init>()     // Catch:{ all -> 0x004e }
            java.lang.String r2 = "isMIUI's value is: "
            r1.append(r2)     // Catch:{ all -> 0x004e }
            int r2 = f12836a     // Catch:{ all -> 0x004e }
            r1.append(r2)     // Catch:{ all -> 0x004e }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x004e }
            com.xiaomi.channel.commonutils.logger.b.b(r1)     // Catch:{ all -> 0x004e }
        L_0x004a:
            int r1 = f12836a     // Catch:{ all -> 0x004e }
            monitor-exit(r0)
            return r1
        L_0x004e:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.l.c():int");
    }

    private static o c(String str) {
        h();
        return c.get(str.toUpperCase());
    }

    public static synchronized String d() {
        synchronized (l.class) {
            int b2 = t.b();
            return (!a() || b2 <= 0) ? "" : b2 < 2 ? "alpha" : b2 < 3 ? "development" : Constants.Name.STABLE;
        }
    }

    public static boolean e() {
        if (b < 0) {
            Object a2 = ba.a("miui.external.SdkHelper", "isMiuiSystem", new Object[0]);
            b = 0;
            if (a2 != null && (a2 instanceof Boolean) && !Boolean.class.cast(a2).booleanValue()) {
                b = 1;
            }
        }
        return b > 0;
    }

    public static String f() {
        String a2 = s.a("ro.miui.region", "");
        if (TextUtils.isEmpty(a2)) {
            a2 = s.a("persist.sys.oppo.region", "");
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = s.a("ro.oppo.regionmark", "");
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = s.a("ro.hw.country", "");
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = s.a("ro.csc.countryiso_code", "");
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = s.a("ro.product.country.region", "");
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = s.a("gsm.vivo.countrycode", "");
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = s.a("persist.sys.oem.region", "");
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = s.a("ro.product.locale.region", "");
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = s.a("persist.sys.country", "");
        }
        if (!TextUtils.isEmpty(a2)) {
            b.a("get region from system, region = " + a2);
        }
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        String country = Locale.getDefault().getCountry();
        b.a("locale.default.country = " + country);
        return country;
    }

    public static boolean g() {
        return !o.China.name().equalsIgnoreCase(b(f()).name());
    }

    private static void h() {
        if (c == null) {
            c = new HashMap();
            c.put("CN", o.China);
            c.put("FI", o.Europe);
            c.put("SE", o.Europe);
            c.put(Constants.ae, o.Europe);
            c.put("FO", o.Europe);
            c.put("EE", o.Europe);
            c.put("LV", o.Europe);
            c.put("LT", o.Europe);
            c.put("BY", o.Europe);
            c.put("MD", o.Europe);
            c.put("UA", o.Europe);
            c.put("PL", o.Europe);
            c.put("CZ", o.Europe);
            c.put("SK", o.Europe);
            c.put("HU", o.Europe);
            c.put("DE", o.Europe);
            c.put("AT", o.Europe);
            c.put("CH", o.Europe);
            c.put("LI", o.Europe);
            c.put(ServerCompact.i, o.Europe);
            c.put("IE", o.Europe);
            c.put("NL", o.Europe);
            c.put("BE", o.Europe);
            c.put("LU", o.Europe);
            c.put(ServerCompact.e, o.Europe);
            c.put("RO", o.Europe);
            c.put("BG", o.Europe);
            c.put("RS", o.Europe);
            c.put("MK", o.Europe);
            c.put("AL", o.Europe);
            c.put("GR", o.Europe);
            c.put("SI", o.Europe);
            c.put("HR", o.Europe);
            c.put(ServerCompact.h, o.Europe);
            c.put("SM", o.Europe);
            c.put("MT", o.Europe);
            c.put(ServerCompact.f, o.Europe);
            c.put("PT", o.Europe);
            c.put("AD", o.Europe);
            c.put("CY", o.Europe);
            c.put("DK", o.Europe);
            c.put(ServerCompact.d, o.Russia);
            c.put(ServerCompact.c, o.India);
        }
    }
}
