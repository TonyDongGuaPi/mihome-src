package com.alipay.deviceid.module.x;

import java.io.File;

public final class t {
    /* JADX WARNING: Can't wrap try/catch for region: R(5:23|24|(0)|31|32) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0061 */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x005e A[SYNTHETIC, Splitter:B:29:0x005e] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0064 A[SYNTHETIC, Splitter:B:35:0x0064] */
    /* JADX WARNING: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String r3, java.lang.String r4) {
        /*
            boolean r0 = com.alipay.deviceid.module.x.e.a((java.lang.String) r4)     // Catch:{ Throwable -> 0x0009 }
            if (r0 != 0) goto L_0x0009
            java.lang.System.setProperty(r3, r4)     // Catch:{ Throwable -> 0x0009 }
        L_0x0009:
            boolean r0 = com.alipay.deviceid.module.x.u.a()
            if (r0 == 0) goto L_0x0069
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = ".SystemConfig"
            r0.<init>(r1)
            java.lang.String r1 = java.io.File.separator
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            boolean r0 = com.alipay.deviceid.module.x.u.a()     // Catch:{ Exception -> 0x0069 }
            if (r0 == 0) goto L_0x0068
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0069 }
            java.io.File r1 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Exception -> 0x0069 }
            r0.<init>(r1, r3)     // Catch:{ Exception -> 0x0069 }
            boolean r3 = r0.exists()     // Catch:{ Exception -> 0x0069 }
            if (r3 != 0) goto L_0x003e
            java.io.File r3 = r0.getParentFile()     // Catch:{ Exception -> 0x0069 }
            r3.mkdirs()     // Catch:{ Exception -> 0x0069 }
        L_0x003e:
            java.lang.String r3 = r0.getAbsolutePath()     // Catch:{ Exception -> 0x0069 }
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0069 }
            r0.<init>(r3)     // Catch:{ Exception -> 0x0069 }
            r3 = 0
            java.io.FileWriter r1 = new java.io.FileWriter     // Catch:{ Exception -> 0x0062, all -> 0x005b }
            r2 = 0
            r1.<init>(r0, r2)     // Catch:{ Exception -> 0x0062, all -> 0x005b }
            r1.write(r4)     // Catch:{ Exception -> 0x0059, all -> 0x0055 }
            r1.close()     // Catch:{ IOException -> 0x0054 }
        L_0x0054:
            return
        L_0x0055:
            r3 = move-exception
            r4 = r3
            r3 = r1
            goto L_0x005c
        L_0x0059:
            r3 = r1
            goto L_0x0062
        L_0x005b:
            r4 = move-exception
        L_0x005c:
            if (r3 == 0) goto L_0x0061
            r3.close()     // Catch:{ IOException -> 0x0061 }
        L_0x0061:
            throw r4     // Catch:{ Exception -> 0x0069 }
        L_0x0062:
            if (r3 == 0) goto L_0x0068
            r3.close()     // Catch:{ IOException -> 0x0067 }
        L_0x0067:
            return
        L_0x0068:
            return
        L_0x0069:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.t.a(java.lang.String, java.lang.String):void");
    }

    public static String a(String str) {
        String str2 = "";
        try {
            str2 = System.getProperty(str);
        } catch (Throwable unused) {
        }
        if (!e.a(str2)) {
            return str2;
        }
        return u.a(".SystemConfig" + File.separator + str);
    }
}
