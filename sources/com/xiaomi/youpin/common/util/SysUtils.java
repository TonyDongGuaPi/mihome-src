package com.xiaomi.youpin.common.util;

import android.os.Build;
import android.text.TextUtils;

public class SysUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23281a = "MIUI";
    public static final String b = "EMUI";
    public static final String c = "FLYME";
    public static final String d = "OPPO";
    public static final String e = "VIVO";
    public static final String f = "QIKU";
    public static final String g = "SMARTISAN";
    private static final String h = "Rom";
    private static final String i = "ro.miui.ui.version.name";
    private static final String j = "ro.build.version.emui";
    private static final String k = "ro.build.version.opporom";
    private static final String l = "ro.vivo.os.version";
    private static final String m = "ro.smartisan.version";
    private static String n;
    private static String o;

    public static boolean a() {
        return a("EMUI");
    }

    public static boolean b() {
        return a("MIUI");
    }

    public static boolean c() {
        return a("VIVO");
    }

    public static boolean d() {
        return a("OPPO");
    }

    public static boolean e() {
        return a("FLYME");
    }

    public static boolean f() {
        return a("QIKU") || a("360");
    }

    public static boolean g() {
        return a("SMARTISAN");
    }

    public static String h() {
        if (n == null) {
            a("");
        }
        return n;
    }

    public static String i() {
        if (o == null) {
            a("");
        }
        return o;
    }

    private static boolean a(String str) {
        if (n != null) {
            return n.equals(str);
        }
        String b2 = b(i);
        o = b2;
        if (!TextUtils.isEmpty(b2)) {
            n = "MIUI";
        } else {
            String b3 = b(j);
            o = b3;
            if (!TextUtils.isEmpty(b3)) {
                n = "EMUI";
            } else {
                String b4 = b(k);
                o = b4;
                if (!TextUtils.isEmpty(b4)) {
                    n = "OPPO";
                } else {
                    String b5 = b(l);
                    o = b5;
                    if (!TextUtils.isEmpty(b5)) {
                        n = "VIVO";
                    } else {
                        String b6 = b(m);
                        o = b6;
                        if (!TextUtils.isEmpty(b6)) {
                            n = "SMARTISAN";
                        } else {
                            o = Build.DISPLAY;
                            if (o.toUpperCase().contains("FLYME")) {
                                n = "FLYME";
                            } else {
                                o = "unknown";
                                n = Build.MANUFACTURER.toUpperCase();
                            }
                        }
                    }
                }
            }
        }
        return n.equals(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0058 A[SYNTHETIC, Splitter:B:18:0x0058] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0065 A[SYNTHETIC, Splitter:B:26:0x0065] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String b(java.lang.String r6) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x003e, all -> 0x003c }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x003e, all -> 0x003c }
            r2.<init>()     // Catch:{ IOException -> 0x003e, all -> 0x003c }
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch:{ IOException -> 0x003e, all -> 0x003c }
            r2.append(r6)     // Catch:{ IOException -> 0x003e, all -> 0x003c }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x003e, all -> 0x003c }
            java.lang.Process r1 = r1.exec(r2)     // Catch:{ IOException -> 0x003e, all -> 0x003c }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x003e, all -> 0x003c }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x003e, all -> 0x003c }
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ IOException -> 0x003e, all -> 0x003c }
            r3.<init>(r1)     // Catch:{ IOException -> 0x003e, all -> 0x003c }
            r1 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3, r1)     // Catch:{ IOException -> 0x003e, all -> 0x003c }
            java.lang.String r1 = r2.readLine()     // Catch:{ IOException -> 0x003a }
            r2.close()     // Catch:{ IOException -> 0x003a }
            r2.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0039:
            return r1
        L_0x003a:
            r1 = move-exception
            goto L_0x0040
        L_0x003c:
            r6 = move-exception
            goto L_0x0063
        L_0x003e:
            r1 = move-exception
            r2 = r0
        L_0x0040:
            java.lang.String r3 = "Rom"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0061 }
            r4.<init>()     // Catch:{ all -> 0x0061 }
            java.lang.String r5 = "Unable to read prop "
            r4.append(r5)     // Catch:{ all -> 0x0061 }
            r4.append(r6)     // Catch:{ all -> 0x0061 }
            java.lang.String r6 = r4.toString()     // Catch:{ all -> 0x0061 }
            com.xiaomi.youpin.log.LogUtils.e(r3, r6, r1)     // Catch:{ all -> 0x0061 }
            if (r2 == 0) goto L_0x0060
            r2.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x0060
        L_0x005c:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0060:
            return r0
        L_0x0061:
            r6 = move-exception
            r0 = r2
        L_0x0063:
            if (r0 == 0) goto L_0x006d
            r0.close()     // Catch:{ IOException -> 0x0069 }
            goto L_0x006d
        L_0x0069:
            r0 = move-exception
            r0.printStackTrace()
        L_0x006d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.SysUtils.b(java.lang.String):java.lang.String");
    }
}
