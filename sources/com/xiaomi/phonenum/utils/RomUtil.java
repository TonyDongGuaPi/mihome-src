package com.xiaomi.phonenum.utils;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;

public class RomUtil {
    public static boolean a() {
        return !TextUtils.isEmpty(a("ro.miui.ui.version.name"));
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003b A[SYNTHETIC, Splitter:B:13:0x003b] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0042 A[SYNTHETIC, Splitter:B:21:0x0042] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.lang.String r4) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x003f, all -> 0x0038 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x003f, all -> 0x0038 }
            r2.<init>()     // Catch:{ IOException -> 0x003f, all -> 0x0038 }
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch:{ IOException -> 0x003f, all -> 0x0038 }
            r2.append(r4)     // Catch:{ IOException -> 0x003f, all -> 0x0038 }
            java.lang.String r4 = r2.toString()     // Catch:{ IOException -> 0x003f, all -> 0x0038 }
            java.lang.Process r4 = r1.exec(r4)     // Catch:{ IOException -> 0x003f, all -> 0x0038 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ IOException -> 0x003f, all -> 0x0038 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x003f, all -> 0x0038 }
            java.io.InputStream r4 = r4.getInputStream()     // Catch:{ IOException -> 0x003f, all -> 0x0038 }
            r2.<init>(r4)     // Catch:{ IOException -> 0x003f, all -> 0x0038 }
            r4 = 1024(0x400, float:1.435E-42)
            r1.<init>(r2, r4)     // Catch:{ IOException -> 0x003f, all -> 0x0038 }
            java.lang.String r4 = r1.readLine()     // Catch:{ IOException -> 0x0040, all -> 0x0035 }
            r1.close()     // Catch:{ IOException -> 0x0040, all -> 0x0035 }
            r1.close()     // Catch:{ IOException -> 0x0034 }
        L_0x0034:
            return r4
        L_0x0035:
            r4 = move-exception
            r0 = r1
            goto L_0x0039
        L_0x0038:
            r4 = move-exception
        L_0x0039:
            if (r0 == 0) goto L_0x003e
            r0.close()     // Catch:{ IOException -> 0x003e }
        L_0x003e:
            throw r4
        L_0x003f:
            r1 = r0
        L_0x0040:
            if (r1 == 0) goto L_0x0045
            r1.close()     // Catch:{ IOException -> 0x0045 }
        L_0x0045:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.phonenum.utils.RomUtil.a(java.lang.String):java.lang.String");
    }

    public static boolean a(Context context, String str) {
        return context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }
}
