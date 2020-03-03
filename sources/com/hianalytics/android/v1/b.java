package com.hianalytics.android.v1;

public final class b {
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007b A[SYNTHETIC, Splitter:B:30:0x007b] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008f A[SYNTHETIC, Splitter:B:39:0x008f] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r5, byte[] r6) {
        /*
            r0 = 0
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x0063, all -> 0x0060 }
            r2.<init>(r5)     // Catch:{ IOException -> 0x0063, all -> 0x0060 }
            java.net.URLConnection r5 = r2.openConnection()     // Catch:{ IOException -> 0x0063, all -> 0x0060 }
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ IOException -> 0x0063, all -> 0x0060 }
            com.hianalytics.android.b.a.a.h()     // Catch:{ IOException -> 0x005e }
            java.lang.String r2 = "POST"
            r5.setRequestMethod(r2)     // Catch:{ IOException -> 0x005e }
            r2 = 5000(0x1388, float:7.006E-42)
            r5.setConnectTimeout(r2)     // Catch:{ IOException -> 0x005e }
            r2 = 1
            r5.setDoOutput(r2)     // Catch:{ IOException -> 0x005e }
            java.lang.String r3 = "Content-Type"
            java.lang.String r4 = "application/x-www-form-urlencoded; charset=UTF-8"
            r5.setRequestProperty(r3, r4)     // Catch:{ IOException -> 0x005e }
            java.lang.String r3 = "Content-Length"
            int r4 = r6.length     // Catch:{ IOException -> 0x005e }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x005e }
            r5.setRequestProperty(r3, r4)     // Catch:{ IOException -> 0x005e }
            java.io.OutputStream r3 = r5.getOutputStream()     // Catch:{ IOException -> 0x005e }
            r3.write(r6)     // Catch:{ IOException -> 0x005b, all -> 0x0058 }
            r3.flush()     // Catch:{ IOException -> 0x005b, all -> 0x0058 }
            int r6 = r5.getResponseCode()     // Catch:{ IOException -> 0x005b, all -> 0x0058 }
            com.hianalytics.android.b.a.a.h()     // Catch:{ IOException -> 0x005b, all -> 0x0058 }
            r1 = 200(0xc8, float:2.8E-43)
            if (r6 != r1) goto L_0x0045
            r0 = 1
        L_0x0045:
            if (r3 == 0) goto L_0x004f
            r3.close()     // Catch:{ IOException -> 0x004b }
            goto L_0x004f
        L_0x004b:
            r6 = move-exception
            r6.printStackTrace()
        L_0x004f:
            if (r5 == 0) goto L_0x0057
            r5.disconnect()
            com.hianalytics.android.b.a.a.h()
        L_0x0057:
            return r0
        L_0x0058:
            r6 = move-exception
            r1 = r3
            goto L_0x008d
        L_0x005b:
            r6 = move-exception
            r1 = r3
            goto L_0x0065
        L_0x005e:
            r6 = move-exception
            goto L_0x0065
        L_0x0060:
            r6 = move-exception
            r5 = r1
            goto L_0x008d
        L_0x0063:
            r6 = move-exception
            r5 = r1
        L_0x0065:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x008c }
            java.lang.String r3 = "IOException:"
            r2.<init>(r3)     // Catch:{ all -> 0x008c }
            java.lang.String r3 = r6.getMessage()     // Catch:{ all -> 0x008c }
            r2.append(r3)     // Catch:{ all -> 0x008c }
            com.hianalytics.android.b.a.a.h()     // Catch:{ all -> 0x008c }
            r6.printStackTrace()     // Catch:{ all -> 0x008c }
            if (r1 == 0) goto L_0x0083
            r1.close()     // Catch:{ IOException -> 0x007f }
            goto L_0x0083
        L_0x007f:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0083:
            if (r5 == 0) goto L_0x008b
            r5.disconnect()
            com.hianalytics.android.b.a.a.h()
        L_0x008b:
            return r0
        L_0x008c:
            r6 = move-exception
        L_0x008d:
            if (r1 == 0) goto L_0x0097
            r1.close()     // Catch:{ IOException -> 0x0093 }
            goto L_0x0097
        L_0x0093:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0097:
            if (r5 == 0) goto L_0x009f
            r5.disconnect()
            com.hianalytics.android.b.a.a.h()
        L_0x009f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hianalytics.android.v1.b.a(java.lang.String, byte[]):boolean");
    }
}
