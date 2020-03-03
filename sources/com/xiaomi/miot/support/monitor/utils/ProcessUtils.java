package com.xiaomi.miot.support.monitor.utils;

import android.text.TextUtils;

public class ProcessUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1491a = "ProcessUtils";
    private static String b;

    public static String a() {
        if (TextUtils.isEmpty(b)) {
            b = b();
        }
        return b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x003b, code lost:
        if (r2 != null) goto L_0x002b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0020 A[Catch:{ Throwable -> 0x003b, all -> 0x002f }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0036 A[SYNTHETIC, Splitter:B:23:0x0036] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String b() {
        /*
            r0 = 0
            java.lang.String r1 = "/proc/self/cmdline"
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x003a, all -> 0x0031 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x003a, all -> 0x0031 }
            r1 = 256(0x100, float:3.59E-43)
            byte[] r1 = new byte[r1]     // Catch:{ Throwable -> 0x003b, all -> 0x002f }
            r3 = 0
            r4 = 0
        L_0x000e:
            int r5 = r2.read()     // Catch:{ Throwable -> 0x003b, all -> 0x002f }
            if (r5 <= 0) goto L_0x001e
            int r6 = r1.length     // Catch:{ Throwable -> 0x003b, all -> 0x002f }
            if (r4 >= r6) goto L_0x001e
            int r6 = r4 + 1
            byte r5 = (byte) r5     // Catch:{ Throwable -> 0x003b, all -> 0x002f }
            r1[r4] = r5     // Catch:{ Throwable -> 0x003b, all -> 0x002f }
            r4 = r6
            goto L_0x000e
        L_0x001e:
            if (r4 <= 0) goto L_0x002b
            java.lang.String r5 = new java.lang.String     // Catch:{ Throwable -> 0x003b, all -> 0x002f }
            java.lang.String r6 = "UTF-8"
            r5.<init>(r1, r3, r4, r6)     // Catch:{ Throwable -> 0x003b, all -> 0x002f }
            r2.close()     // Catch:{ Throwable -> 0x002a }
        L_0x002a:
            return r5
        L_0x002b:
            r2.close()     // Catch:{ Throwable -> 0x003e }
            goto L_0x003e
        L_0x002f:
            r0 = move-exception
            goto L_0x0034
        L_0x0031:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x0034:
            if (r2 == 0) goto L_0x0039
            r2.close()     // Catch:{ Throwable -> 0x0039 }
        L_0x0039:
            throw r0
        L_0x003a:
            r2 = r0
        L_0x003b:
            if (r2 == 0) goto L_0x003e
            goto L_0x002b
        L_0x003e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miot.support.monitor.utils.ProcessUtils.b():java.lang.String");
    }
}
