package com.xiaomi.account.diagnosis.util;

public class ProcessUtils {
    private ProcessUtils() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0039 A[SYNTHETIC, Splitter:B:15:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0040 A[SYNTHETIC, Splitter:B:23:0x0040] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getProcessName(int r5) {
        /*
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            r3.<init>()     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            java.lang.String r4 = "/proc/"
            r3.append(r4)     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            r3.append(r5)     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            java.lang.String r5 = "/cmdline"
            r3.append(r5)     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            java.lang.String r5 = r3.toString()     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            r2.<init>(r5)     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            java.lang.String r5 = r1.readLine()     // Catch:{ Exception -> 0x003e, all -> 0x0033 }
            boolean r2 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception -> 0x003e, all -> 0x0033 }
            if (r2 != 0) goto L_0x002f
            java.lang.String r5 = r5.trim()     // Catch:{ Exception -> 0x003e, all -> 0x0033 }
        L_0x002f:
            r1.close()     // Catch:{ IOException -> 0x0032 }
        L_0x0032:
            return r5
        L_0x0033:
            r5 = move-exception
            r0 = r1
            goto L_0x0037
        L_0x0036:
            r5 = move-exception
        L_0x0037:
            if (r0 == 0) goto L_0x003c
            r0.close()     // Catch:{ IOException -> 0x003c }
        L_0x003c:
            throw r5
        L_0x003d:
            r1 = r0
        L_0x003e:
            if (r1 == 0) goto L_0x0043
            r1.close()     // Catch:{ IOException -> 0x0043 }
        L_0x0043:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.account.diagnosis.util.ProcessUtils.getProcessName(int):java.lang.String");
    }
}
