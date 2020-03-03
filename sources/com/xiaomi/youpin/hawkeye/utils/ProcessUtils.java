package com.xiaomi.youpin.hawkeye.utils;

import android.text.TextUtils;

public class ProcessUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23391a = "ProcessUtils";
    private static String b;

    public static String a() {
        if (TextUtils.isEmpty(b)) {
            b = b();
        }
        return b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0020 A[Catch:{ Throwable -> 0x0067 }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0047 A[SYNTHETIC, Splitter:B:18:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x008b A[SYNTHETIC, Splitter:B:32:0x008b] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x009c A[SYNTHETIC, Splitter:B:39:0x009c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String b() {
        /*
            r0 = 0
            java.lang.String r1 = "/proc/self/cmdline"
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x006d, all -> 0x0069 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x006d, all -> 0x0069 }
            r1 = 256(0x100, float:3.59E-43)
            byte[] r1 = new byte[r1]     // Catch:{ Throwable -> 0x0067 }
            r3 = 0
            r4 = 0
        L_0x000e:
            int r5 = r2.read()     // Catch:{ Throwable -> 0x0067 }
            if (r5 <= 0) goto L_0x001e
            int r6 = r1.length     // Catch:{ Throwable -> 0x0067 }
            if (r4 >= r6) goto L_0x001e
            int r6 = r4 + 1
            byte r5 = (byte) r5     // Catch:{ Throwable -> 0x0067 }
            r1[r4] = r5     // Catch:{ Throwable -> 0x0067 }
            r4 = r6
            goto L_0x000e
        L_0x001e:
            if (r4 <= 0) goto L_0x0047
            java.lang.String r5 = new java.lang.String     // Catch:{ Throwable -> 0x0067 }
            java.lang.String r6 = "UTF-8"
            r5.<init>(r1, r3, r4, r6)     // Catch:{ Throwable -> 0x0067 }
            r2.close()     // Catch:{ Throwable -> 0x002b }
            goto L_0x0046
        L_0x002b:
            r0 = move-exception
            java.lang.String r1 = "ProcessUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "getCurrentProcessName: got exception: "
            r2.append(r3)
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.xiaomi.youpin.hawkeye.utils.HLog.b(r1, r0)
        L_0x0046:
            return r5
        L_0x0047:
            r2.close()     // Catch:{ Throwable -> 0x004b }
            goto L_0x0098
        L_0x004b:
            r1 = move-exception
            java.lang.String r2 = "ProcessUtils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
        L_0x0053:
            java.lang.String r4 = "getCurrentProcessName: got exception: "
            r3.append(r4)
            java.lang.String r1 = android.util.Log.getStackTraceString(r1)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            com.xiaomi.youpin.hawkeye.utils.HLog.b(r2, r1)
            goto L_0x0098
        L_0x0067:
            r1 = move-exception
            goto L_0x006f
        L_0x0069:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L_0x009a
        L_0x006d:
            r1 = move-exception
            r2 = r0
        L_0x006f:
            java.lang.String r3 = "ProcessUtils"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0099 }
            r4.<init>()     // Catch:{ all -> 0x0099 }
            java.lang.String r5 = "getCurrentProcessName: got exception: "
            r4.append(r5)     // Catch:{ all -> 0x0099 }
            java.lang.String r1 = android.util.Log.getStackTraceString(r1)     // Catch:{ all -> 0x0099 }
            r4.append(r1)     // Catch:{ all -> 0x0099 }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x0099 }
            com.xiaomi.youpin.hawkeye.utils.HLog.b(r3, r1)     // Catch:{ all -> 0x0099 }
            if (r2 == 0) goto L_0x0098
            r2.close()     // Catch:{ Throwable -> 0x008f }
            goto L_0x0098
        L_0x008f:
            r1 = move-exception
            java.lang.String r2 = "ProcessUtils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            goto L_0x0053
        L_0x0098:
            return r0
        L_0x0099:
            r0 = move-exception
        L_0x009a:
            if (r2 == 0) goto L_0x00bb
            r2.close()     // Catch:{ Throwable -> 0x00a0 }
            goto L_0x00bb
        L_0x00a0:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "getCurrentProcessName: got exception: "
            r2.append(r3)
            java.lang.String r1 = android.util.Log.getStackTraceString(r1)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r2 = "ProcessUtils"
            com.xiaomi.youpin.hawkeye.utils.HLog.b(r2, r1)
        L_0x00bb:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.hawkeye.utils.ProcessUtils.b():java.lang.String");
    }
}
