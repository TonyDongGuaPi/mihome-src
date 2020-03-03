package com.mi.global.bbs.utils;

public class TestSpeedUtil {
    private static final String TAG = "TestSpeedUtil";

    public static void download(final String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            new Thread(new Runnable() {
                public void run() {
                    for (String access$000 : strArr) {
                        TestSpeedUtil.download(access$000);
                    }
                }
            }).start();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0092 A[SYNTHETIC, Splitter:B:35:0x0092] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x009e A[SYNTHETIC, Splitter:B:42:0x009e] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void download(java.lang.String r7) {
        /*
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            r1.<init>(r7)     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            java.net.URLConnection r7 = r1.openConnection()     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            r7.connect()     // Catch:{ Exception -> 0x0080 }
            int r1 = r7.getResponseCode()     // Catch:{ Exception -> 0x0080 }
            r2 = 200(0xc8, float:2.8E-43)
            if (r1 == r2) goto L_0x0043
            java.lang.String r1 = TAG     // Catch:{ Exception -> 0x0080 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0080 }
            r2.<init>()     // Catch:{ Exception -> 0x0080 }
            java.lang.String r3 = "Server returned HTTP "
            r2.append(r3)     // Catch:{ Exception -> 0x0080 }
            int r3 = r7.getResponseCode()     // Catch:{ Exception -> 0x0080 }
            r2.append(r3)     // Catch:{ Exception -> 0x0080 }
            java.lang.String r3 = " "
            r2.append(r3)     // Catch:{ Exception -> 0x0080 }
            java.lang.String r3 = r7.getResponseMessage()     // Catch:{ Exception -> 0x0080 }
            r2.append(r3)     // Catch:{ Exception -> 0x0080 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0080 }
            com.mi.log.LogUtil.a(r1, r2)     // Catch:{ Exception -> 0x0080 }
            if (r7 == 0) goto L_0x0042
            r7.disconnect()
        L_0x0042:
            return
        L_0x0043:
            java.io.InputStream r1 = r7.getInputStream()     // Catch:{ Exception -> 0x0080 }
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x007b, all -> 0x0076 }
            r2 = 0
        L_0x004d:
            int r4 = r1.read(r0)     // Catch:{ Exception -> 0x007b, all -> 0x0076 }
            r5 = -1
            if (r4 == r5) goto L_0x0057
            long r4 = (long) r4     // Catch:{ Exception -> 0x007b, all -> 0x0076 }
            long r2 = r2 + r4
            goto L_0x004d
        L_0x0057:
            java.lang.String r0 = TAG     // Catch:{ Exception -> 0x007b, all -> 0x0076 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x007b, all -> 0x0076 }
            r4.<init>()     // Catch:{ Exception -> 0x007b, all -> 0x0076 }
            java.lang.String r5 = "total="
            r4.append(r5)     // Catch:{ Exception -> 0x007b, all -> 0x0076 }
            r4.append(r2)     // Catch:{ Exception -> 0x007b, all -> 0x0076 }
            java.lang.String r2 = r4.toString()     // Catch:{ Exception -> 0x007b, all -> 0x0076 }
            com.mi.log.LogUtil.b(r0, r2)     // Catch:{ Exception -> 0x007b, all -> 0x0076 }
            if (r1 == 0) goto L_0x0073
            r1.close()     // Catch:{ IOException -> 0x0073 }
        L_0x0073:
            if (r7 == 0) goto L_0x009a
            goto L_0x0097
        L_0x0076:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x009c
        L_0x007b:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0087
        L_0x0080:
            r1 = move-exception
            goto L_0x0087
        L_0x0082:
            r1 = move-exception
            r7 = r0
            goto L_0x009c
        L_0x0085:
            r1 = move-exception
            r7 = r0
        L_0x0087:
            java.lang.String r2 = TAG     // Catch:{ all -> 0x009b }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x009b }
            com.mi.log.LogUtil.a(r2, r1)     // Catch:{ all -> 0x009b }
            if (r0 == 0) goto L_0x0095
            r0.close()     // Catch:{ IOException -> 0x0095 }
        L_0x0095:
            if (r7 == 0) goto L_0x009a
        L_0x0097:
            r7.disconnect()
        L_0x009a:
            return
        L_0x009b:
            r1 = move-exception
        L_0x009c:
            if (r0 == 0) goto L_0x00a1
            r0.close()     // Catch:{ IOException -> 0x00a1 }
        L_0x00a1:
            if (r7 == 0) goto L_0x00a6
            r7.disconnect()
        L_0x00a6:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.bbs.utils.TestSpeedUtil.download(java.lang.String):void");
    }
}
