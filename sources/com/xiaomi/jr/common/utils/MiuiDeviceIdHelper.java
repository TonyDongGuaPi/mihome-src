package com.xiaomi.jr.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.List;

public class MiuiDeviceIdHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10368a = "MiuiDeviceIdHelper";
    private static final int b = 15;
    private static final int c = 14;
    private static String d = "1";
    private static String e = "2";
    private static String f = "3";
    private static String g = "-1";
    private static Method h;
    private static Method i;
    private static Object j;
    private static String k;
    private static String l;
    private static Method m;

    static {
        try {
            h = Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class});
        } catch (Exception e2) {
            Log.e(f10368a, "Exception: " + e2);
        }
        try {
            Class<?> cls = Class.forName("miui.telephony.TelephonyManagerEx");
            j = cls.getMethod("getDefault", new Class[0]).invoke((Object) null, new Object[0]);
            i = cls.getMethod("getImeiList", new Class[0]);
        } catch (Exception e3) {
            Log.e(f10368a, "Exception: " + e3);
        }
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                m = Class.forName("android.telephony.TelephonyManager").getMethod("getImei", new Class[]{Integer.TYPE});
            }
        } catch (Exception e4) {
            Log.e(f10368a, "Exception: " + e4);
        }
    }

    @SuppressLint({"MissingPermission"})
    public static String[] a(Context context) {
        String a2 = a("ro.ril.miui.imei0");
        if (!a()) {
            if (TextUtils.isEmpty(a2) && (a2 = a("ro.ril.miui.imei")) != null && a2.contains(",")) {
                a2 = a2.split(",")[0];
            }
            if (TextUtils.isEmpty(a2)) {
                a2 = a("ro.ril.oem.imei");
            }
            if (TextUtils.isEmpty(a2)) {
                a2 = a("persist.radio.imei");
            }
            if (b(a2)) {
                k = a2;
                return new String[]{a2, d};
            }
        } else {
            String a3 = a("ro.ril.miui.imei1");
            String a4 = a("ro.ril.miui.imei");
            if (!TextUtils.isEmpty(a4)) {
                String[] split = a4.split(",");
                if (split.length == 2) {
                    if (TextUtils.isEmpty(a2)) {
                        a2 = split[0];
                    }
                    if (TextUtils.isEmpty(a3)) {
                        a3 = split[1];
                    }
                }
            }
            if (TextUtils.isEmpty(a2)) {
                a2 = a("ro.ril.oem.imei1");
            }
            if (TextUtils.isEmpty(a2)) {
                a2 = a("persist.radio.imei1");
            }
            if (TextUtils.isEmpty(a3)) {
                a3 = a("ro.ril.oem.imei2");
            }
            if (TextUtils.isEmpty(a3)) {
                a3 = a("persist.radio.imei2");
            }
            if (Build.VERSION.SDK_INT < 21) {
                String a5 = a("ro.product.mod_device");
                if (a5 == null || !a5.contains("_global")) {
                    if (b(a2)) {
                        k = a2;
                        return new String[]{a2, d};
                    } else if (b(a3)) {
                        k = a3;
                        return new String[]{a3, d};
                    }
                }
                if (b(a4)) {
                    k = a4;
                    return new String[]{a4, d};
                }
            }
            if (b(a2) && b(a3)) {
                String str = a2.compareTo(a3) <= 0 ? a2 : a3;
                k = str;
                if (k == a2) {
                    a2 = a3;
                }
                l = a2;
                return new String[]{str, d};
            }
        }
        List<String> b2 = b(context);
        if (b2 == null || b2.size() <= 0) {
            String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            String str2 = g;
            if (deviceId != null && deviceId.matches("^0*$")) {
                deviceId = null;
            }
            if (c(deviceId)) {
                str2 = f;
            }
            return new String[]{deviceId, str2};
        }
        return new String[]{b2.get(0), d};
    }

    /* JADX WARNING: Removed duplicated region for block: B:87:0x01c5 A[RETURN] */
    @android.annotation.SuppressLint({"MissingPermission"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<java.lang.String> b(android.content.Context r8) {
        /*
            java.lang.reflect.Method r0 = i
            r1 = 0
            if (r0 == 0) goto L_0x0040
            boolean r0 = b()
            if (r0 != 0) goto L_0x0040
            java.lang.reflect.Method r0 = i     // Catch:{ Exception -> 0x0029 }
            java.lang.Object r2 = j     // Catch:{ Exception -> 0x0029 }
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x0029 }
            java.lang.Object r0 = r0.invoke(r2, r3)     // Catch:{ Exception -> 0x0029 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ Exception -> 0x0029 }
            if (r0 == 0) goto L_0x0040
            int r2 = r0.size()     // Catch:{ Exception -> 0x0029 }
            if (r2 <= 0) goto L_0x0040
            boolean r2 = a((java.util.List<java.lang.String>) r0)     // Catch:{ Exception -> 0x0029 }
            if (r2 != 0) goto L_0x0040
            java.util.Collections.sort(r0)     // Catch:{ Exception -> 0x0029 }
            return r0
        L_0x0029:
            r0 = move-exception
            java.lang.String r2 = "MiuiDeviceIdHelper"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Exception: "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            android.util.Log.e(r2, r0)
        L_0x0040:
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 21
            r3 = 0
            r4 = 1
            if (r0 < r2) goto L_0x00e7
            java.lang.reflect.Method r0 = m
            if (r0 == 0) goto L_0x00ba
            java.lang.String r0 = "phone"
            java.lang.Object r8 = r8.getSystemService(r0)     // Catch:{ Exception -> 0x00a3 }
            android.telephony.TelephonyManager r8 = (android.telephony.TelephonyManager) r8     // Catch:{ Exception -> 0x00a3 }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Exception -> 0x00a3 }
            r0.<init>()     // Catch:{ Exception -> 0x00a3 }
            java.lang.reflect.Method r2 = m     // Catch:{ Exception -> 0x00a3 }
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x00a3 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x00a3 }
            r5[r1] = r6     // Catch:{ Exception -> 0x00a3 }
            java.lang.Object r2 = r2.invoke(r8, r5)     // Catch:{ Exception -> 0x00a3 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x00a3 }
            boolean r5 = b((java.lang.String) r2)     // Catch:{ Exception -> 0x00a3 }
            if (r5 == 0) goto L_0x00ba
            boolean r5 = a()     // Catch:{ Exception -> 0x00a3 }
            if (r5 != 0) goto L_0x0079
            r0.add(r2)     // Catch:{ Exception -> 0x00a3 }
            return r0
        L_0x0079:
            java.lang.reflect.Method r5 = m     // Catch:{ Exception -> 0x00a3 }
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x00a3 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x00a3 }
            r6[r1] = r4     // Catch:{ Exception -> 0x00a3 }
            java.lang.Object r8 = r5.invoke(r8, r6)     // Catch:{ Exception -> 0x00a3 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ Exception -> 0x00a3 }
            boolean r1 = b((java.lang.String) r8)     // Catch:{ Exception -> 0x00a3 }
            if (r1 == 0) goto L_0x00ba
            int r1 = r2.compareTo(r8)     // Catch:{ Exception -> 0x00a3 }
            if (r1 <= 0) goto L_0x009c
            r0.add(r8)     // Catch:{ Exception -> 0x00a3 }
            r0.add(r2)     // Catch:{ Exception -> 0x00a3 }
            goto L_0x00a2
        L_0x009c:
            r0.add(r2)     // Catch:{ Exception -> 0x00a3 }
            r0.add(r8)     // Catch:{ Exception -> 0x00a3 }
        L_0x00a2:
            return r0
        L_0x00a3:
            r8 = move-exception
            java.lang.String r0 = "MiuiDeviceIdHelper"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Exception: "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            android.util.Log.e(r0, r8)
        L_0x00ba:
            boolean r8 = a()
            if (r8 != 0) goto L_0x00cf
            java.lang.String r8 = k
            if (r8 == 0) goto L_0x00cf
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.lang.String r0 = k
            r8.add(r0)
            return r8
        L_0x00cf:
            java.lang.String r8 = k
            if (r8 == 0) goto L_0x01dd
            java.lang.String r8 = l
            if (r8 == 0) goto L_0x01dd
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.lang.String r0 = k
            r8.add(r0)
            java.lang.String r0 = l
            r8.add(r0)
            return r8
        L_0x00e7:
            int r8 = android.os.Build.VERSION.SDK_INT
            if (r8 >= r2) goto L_0x01dd
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ Exception -> 0x01c6 }
            r8.<init>()     // Catch:{ Exception -> 0x01c6 }
            java.lang.String r0 = "android.telephony.TelephonyManager"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ Exception -> 0x01c6 }
            boolean r2 = a()     // Catch:{ Exception -> 0x01c6 }
            if (r2 != 0) goto L_0x0124
            java.lang.String r2 = "getDefault"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x01c6 }
            java.lang.reflect.Method r0 = r0.getMethod(r2, r4)     // Catch:{ Exception -> 0x01c6 }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x01c6 }
            java.lang.Object r0 = r0.invoke(r3, r1)     // Catch:{ Exception -> 0x01c6 }
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch:{ Exception -> 0x01c6 }
            java.lang.String r0 = r0.getDeviceId()     // Catch:{ Exception -> 0x01c6 }
            boolean r1 = b((java.lang.String) r0)     // Catch:{ Exception -> 0x01c6 }
            if (r1 == 0) goto L_0x011a
            r8.add(r0)     // Catch:{ Exception -> 0x01c6 }
            return r8
        L_0x011a:
            java.lang.String r0 = k     // Catch:{ Exception -> 0x01c6 }
            if (r0 == 0) goto L_0x01dd
            java.lang.String r0 = k     // Catch:{ Exception -> 0x01c6 }
            r8.add(r0)     // Catch:{ Exception -> 0x01c6 }
            return r8
        L_0x0124:
            java.lang.String r2 = "getDefault"
            java.lang.Class[] r5 = new java.lang.Class[r4]     // Catch:{ Exception -> 0x01c6 }
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x01c6 }
            r5[r1] = r6     // Catch:{ Exception -> 0x01c6 }
            java.lang.reflect.Method r2 = r0.getMethod(r2, r5)     // Catch:{ Exception -> 0x01c6 }
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x01c6 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x01c6 }
            r5[r1] = r6     // Catch:{ Exception -> 0x01c6 }
            java.lang.Object r2 = r2.invoke(r3, r5)     // Catch:{ Exception -> 0x01c6 }
            android.telephony.TelephonyManager r2 = (android.telephony.TelephonyManager) r2     // Catch:{ Exception -> 0x01c6 }
            java.lang.String r2 = r2.getDeviceId()     // Catch:{ Exception -> 0x01c6 }
            java.lang.String r5 = "getDefault"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ Exception -> 0x01c6 }
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x01c6 }
            r6[r1] = r7     // Catch:{ Exception -> 0x01c6 }
            java.lang.reflect.Method r0 = r0.getMethod(r5, r6)     // Catch:{ Exception -> 0x01c6 }
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x01c6 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x01c6 }
            r5[r1] = r4     // Catch:{ Exception -> 0x01c6 }
            java.lang.Object r0 = r0.invoke(r3, r5)     // Catch:{ Exception -> 0x01c6 }
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch:{ Exception -> 0x01c6 }
            java.lang.String r0 = r0.getDeviceId()     // Catch:{ Exception -> 0x01c6 }
            java.lang.String r1 = "ro.product.mod_device"
            java.lang.String r1 = a((java.lang.String) r1)     // Catch:{ Exception -> 0x01c6 }
            if (r1 == 0) goto L_0x0197
            java.lang.String r4 = "_global"
            boolean r1 = r1.contains(r4)     // Catch:{ Exception -> 0x01c6 }
            if (r1 != 0) goto L_0x0171
            goto L_0x0197
        L_0x0171:
            boolean r1 = b((java.lang.String) r2)     // Catch:{ Exception -> 0x01c6 }
            if (r1 == 0) goto L_0x0184
            boolean r1 = b((java.lang.String) r0)     // Catch:{ Exception -> 0x01c6 }
            if (r1 == 0) goto L_0x0184
            r8.add(r2)     // Catch:{ Exception -> 0x01c6 }
            r8.add(r0)     // Catch:{ Exception -> 0x01c6 }
            goto L_0x01bf
        L_0x0184:
            java.lang.String r0 = k     // Catch:{ Exception -> 0x01c6 }
            if (r0 == 0) goto L_0x01bf
            java.lang.String r0 = l     // Catch:{ Exception -> 0x01c6 }
            if (r0 == 0) goto L_0x01bf
            java.lang.String r0 = k     // Catch:{ Exception -> 0x01c6 }
            r8.add(r0)     // Catch:{ Exception -> 0x01c6 }
            java.lang.String r0 = l     // Catch:{ Exception -> 0x01c6 }
            r8.add(r0)     // Catch:{ Exception -> 0x01c6 }
            goto L_0x01bf
        L_0x0197:
            boolean r1 = b((java.lang.String) r2)     // Catch:{ Exception -> 0x01c6 }
            if (r1 == 0) goto L_0x01a4
            r8.add(r2)     // Catch:{ Exception -> 0x01c6 }
            r8.add(r2)     // Catch:{ Exception -> 0x01c6 }
            goto L_0x01bf
        L_0x01a4:
            boolean r1 = b((java.lang.String) r0)     // Catch:{ Exception -> 0x01c6 }
            if (r1 == 0) goto L_0x01b1
            r8.add(r0)     // Catch:{ Exception -> 0x01c6 }
            r8.add(r0)     // Catch:{ Exception -> 0x01c6 }
            goto L_0x01bf
        L_0x01b1:
            java.lang.String r0 = k     // Catch:{ Exception -> 0x01c6 }
            if (r0 == 0) goto L_0x01bf
            java.lang.String r0 = k     // Catch:{ Exception -> 0x01c6 }
            r8.add(r0)     // Catch:{ Exception -> 0x01c6 }
            java.lang.String r0 = k     // Catch:{ Exception -> 0x01c6 }
            r8.add(r0)     // Catch:{ Exception -> 0x01c6 }
        L_0x01bf:
            int r0 = r8.size()     // Catch:{ Exception -> 0x01c6 }
            if (r0 <= 0) goto L_0x01dd
            return r8
        L_0x01c6:
            r8 = move-exception
            java.lang.String r0 = "MiuiDeviceIdHelper"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Exception: "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            android.util.Log.e(r0, r8)
        L_0x01dd:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.common.utils.MiuiDeviceIdHelper.b(android.content.Context):java.util.List");
    }

    private static boolean a() {
        if ("dsds".equals(a("persist.radio.multisim.config"))) {
            return true;
        }
        String str = Build.DEVICE;
        if ("lcsh92_wet_jb9".equals(str) || "lcsh92_wet_tdd".equals(str) || "HM2013022".equals(str) || "HM2013023".equals(str) || "armani".equals(str) || "HM2014011".equals(str) || "HM2014012".equals(str)) {
            return true;
        }
        return false;
    }

    private static String a(String str) {
        try {
            if (h != null) {
                return String.valueOf(h.invoke((Object) null, new Object[]{str}));
            }
        } catch (Exception e2) {
            Log.e(f10368a, "Exception: " + e2);
        }
        return null;
    }

    private static boolean b() {
        if (Build.VERSION.SDK_INT >= 21) {
            return false;
        }
        String str = Build.DEVICE;
        String a2 = a("persist.radio.modem");
        if ("HM2014812".equals(str) || "HM2014821".equals(str)) {
            return true;
        }
        if ((!"gucci".equals(str) || !"ct".equals(a("persist.sys.modem"))) && !"CDMA".equals(a2) && !"HM1AC".equals(a2) && !"LTE-X5-ALL".equals(a2) && !"LTE-CT".equals(a2) && !"MI 3C".equals(Build.MODEL)) {
            return false;
        }
        return true;
    }

    private static boolean a(List<String> list) {
        for (String b2 : list) {
            if (!b(b2)) {
                return true;
            }
        }
        return false;
    }

    private static boolean b(String str) {
        return str != null && str.length() == 15 && !str.matches("^0*$");
    }

    private static boolean c(String str) {
        return str != null && str.length() == 14 && !str.matches("^0*$");
    }
}
