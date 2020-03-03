package com.xiaomi.jr.common.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.jr.common.os.SystemProperties;

public class MiuiClient {

    /* renamed from: a  reason: collision with root package name */
    static final String f10367a = "88daa889de21a80bca64464243c9ede6";
    private static Boolean b;
    private static String c;
    private static String d;
    private static String e;

    public static boolean a() {
        if (b == null) {
            f();
        }
        return b.booleanValue();
    }

    public static String b() {
        if (TextUtils.isEmpty(d)) {
            f();
        }
        return d;
    }

    public static String c() {
        if (TextUtils.isEmpty(c)) {
            f();
        }
        return c;
    }

    private static void f() {
        d = SystemProperties.a("ro.miui.ui.version.code");
        c = SystemProperties.a("ro.miui.ui.version.name");
        b = Boolean.valueOf(!TextUtils.isEmpty(c) && !TextUtils.isEmpty(d));
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String d() {
        /*
            java.lang.String r0 = e
            if (r0 != 0) goto L_0x005a
            r0 = 0
            java.lang.String r1 = "miui.os.Build"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x004f }
            java.lang.String r2 = "IS_ALPHA_BUILD"
            java.lang.Object r2 = com.xiaomi.jr.common.utils.ReflectUtil.a((java.lang.Class<?>) r1, (java.lang.String) r2, (java.lang.Object) r0)     // Catch:{ ClassNotFoundException -> 0x004f }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ ClassNotFoundException -> 0x004f }
            if (r2 == 0) goto L_0x001e
            boolean r2 = r2.booleanValue()     // Catch:{ ClassNotFoundException -> 0x004f }
            if (r2 == 0) goto L_0x001e
            java.lang.String r2 = "alpha"
            goto L_0x001f
        L_0x001e:
            r2 = r0
        L_0x001f:
            if (r2 != 0) goto L_0x0038
            java.lang.String r3 = "IS_DEVELOPMENT_VERSION"
            java.lang.Object r3 = com.xiaomi.jr.common.utils.ReflectUtil.a((java.lang.Class<?>) r1, (java.lang.String) r3, (java.lang.Object) r0)     // Catch:{ ClassNotFoundException -> 0x0035 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ ClassNotFoundException -> 0x0035 }
            if (r3 == 0) goto L_0x0038
            boolean r3 = r3.booleanValue()     // Catch:{ ClassNotFoundException -> 0x0035 }
            if (r3 == 0) goto L_0x0038
            java.lang.String r3 = "dev"
            r2 = r3
            goto L_0x0038
        L_0x0035:
            r1 = move-exception
            r0 = r2
            goto L_0x0050
        L_0x0038:
            if (r2 != 0) goto L_0x004d
            java.lang.String r3 = "IS_STABLE_VERSION"
            java.lang.Object r0 = com.xiaomi.jr.common.utils.ReflectUtil.a((java.lang.Class<?>) r1, (java.lang.String) r3, (java.lang.Object) r0)     // Catch:{ ClassNotFoundException -> 0x0035 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ ClassNotFoundException -> 0x0035 }
            if (r0 == 0) goto L_0x004d
            boolean r0 = r0.booleanValue()     // Catch:{ ClassNotFoundException -> 0x0035 }
            if (r0 == 0) goto L_0x004d
            java.lang.String r0 = "stable"
            goto L_0x0053
        L_0x004d:
            r0 = r2
            goto L_0x0053
        L_0x004f:
            r1 = move-exception
        L_0x0050:
            r1.printStackTrace()
        L_0x0053:
            if (r0 == 0) goto L_0x0056
            goto L_0x0058
        L_0x0056:
            java.lang.String r0 = ""
        L_0x0058:
            e = r0
        L_0x005a:
            java.lang.String r0 = e
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.common.utils.MiuiClient.d():java.lang.String");
    }

    public static String e() {
        String d2 = d();
        String a2 = SystemProperties.a("ro.build.version.incremental");
        StringBuilder sb = new StringBuilder();
        sb.append(d2);
        sb.append("-");
        if (a2 == null) {
            a2 = "";
        }
        sb.append(a2);
        return sb.toString();
    }

    public static boolean a(Context context) {
        try {
            Class<?> cls = Class.forName("miui.telephony.TelephonyManager");
            return ((Boolean) cls.getMethod("hasIccCard", new Class[0]).invoke(cls.getMethod("getDefault", new Class[0]).invoke((Object) null, new Object[0]), new Object[0])).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return b(context);
        }
    }

    private static boolean b(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).hasIccCard();
    }
}
