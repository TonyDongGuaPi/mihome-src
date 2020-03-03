package com.loc;

import java.util.concurrent.atomic.AtomicBoolean;

final class dl {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f6563a = false;
    private static AtomicBoolean b = new AtomicBoolean(false);

    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v7, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00fb A[Catch:{ Throwable -> 0x018f, all -> 0x018c }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x015e A[SYNTHETIC, Splitter:B:38:0x015e] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0172  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0177 A[SYNTHETIC, Splitter:B:44:0x0177] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x017f A[Catch:{ IOException -> 0x017b }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01ab  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01b0 A[SYNTHETIC, Splitter:B:72:0x01b0] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01b8 A[Catch:{ IOException -> 0x01b4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01c1  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01c6 A[SYNTHETIC, Splitter:B:84:0x01c6] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01ce A[Catch:{ IOException -> 0x01ca }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void a(android.content.Context r8) {
        /*
            java.util.concurrent.atomic.AtomicBoolean r0 = b
            r1 = 1
            r2 = 0
            boolean r0 = r0.compareAndSet(r2, r1)
            if (r0 == 0) goto L_0x01db
            r0 = 0
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            r4 = 14
            if (r3 < r4) goto L_0x0017
            r3 = 40965(0xa005, float:5.7404E-41)
            android.net.TrafficStats.setThreadStatsTag(r3)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
        L_0x0017:
            com.loc.cx r8 = com.loc.cy.a(r8)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            if (r8 == 0) goto L_0x002d
            java.lang.String r3 = r8.e()     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            boolean r3 = com.loc.dw.a(r3)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            if (r3 == 0) goto L_0x0028
            goto L_0x002d
        L_0x0028:
            java.lang.String r8 = r8.e()     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            goto L_0x002f
        L_0x002d:
            java.lang.String r8 = "ffffffffffffffffffffffff"
        L_0x002f:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.String r4 = "stat: "
            r3.<init>(r4)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            r3.append(r8)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            com.loc.dk.a((java.lang.String) r3)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.String r4 = "HTTPDNS-"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            r3.append(r8)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.String r8 = r3.toString()     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.String r4 = "23356390Raw"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.String r4 = com.loc.dn.c(r8)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            r3.append(r4)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.String r5 = "16594f72217bece5a457b4803a48f2da"
            r4.<init>(r5)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.String r3 = com.loc.dn.c(r3)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            r4.append(r3)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.String r3 = "16594f72217bece5a457b4803a48f2da"
            r4.append(r3)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.String r3 = r4.toString()     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.String r3 = com.loc.dn.d(r3)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.String r5 = "http://adash.man.aliyuncs.com:80/man/api?ak=23356390&s="
            r4.<init>(r5)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            r4.append(r3)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.lang.String r3 = r4.toString()     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.net.URL r4 = new java.net.URL     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            r4.<init>(r3)     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.net.URLConnection r3 = r4.openConnection()     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ Throwable -> 0x019e, all -> 0x019a }
            r3.setDoOutput(r1)     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            r3.setUseCaches(r2)     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            java.lang.String r5 = "==="
            r4.<init>(r5)     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            r4.append(r5)     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            java.lang.String r5 = "==="
            r4.append(r5)     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            java.lang.String r5 = "Content-Type"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            java.lang.String r7 = "multipart/form-data; boundary="
            r6.<init>(r7)     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            r6.append(r4)     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            r3.setRequestProperty(r5, r6)     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            java.lang.String r6 = "--"
            r5.<init>(r6)     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            r5.append(r4)     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            java.lang.String r6 = "\r\nContent-Disposition: form-data; name=\"Raw\"\r\nContent-Type: text/plain; charset=UTF-8\r\n\r\n"
            r5.append(r6)     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            r5.append(r8)     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            java.lang.String r8 = "\r\n--"
            r5.append(r8)     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            r5.append(r4)     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            java.lang.String r8 = "--\r\n"
            r5.append(r8)     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            java.lang.String r8 = r5.toString()     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            java.io.OutputStream r4 = r3.getOutputStream()     // Catch:{ Throwable -> 0x0195, all -> 0x0192 }
            byte[] r8 = r8.getBytes()     // Catch:{ Throwable -> 0x018f, all -> 0x018c }
            r4.write(r8)     // Catch:{ Throwable -> 0x018f, all -> 0x018c }
            int r8 = r3.getResponseCode()     // Catch:{ Throwable -> 0x018f, all -> 0x018c }
            r5 = 200(0xc8, float:2.8E-43)
            if (r8 != r5) goto L_0x015e
            java.io.DataInputStream r8 = new java.io.DataInputStream     // Catch:{ Throwable -> 0x018f, all -> 0x018c }
            java.io.InputStream r5 = r3.getInputStream()     // Catch:{ Throwable -> 0x018f, all -> 0x018c }
            r8.<init>(r5)     // Catch:{ Throwable -> 0x018f, all -> 0x018c }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x015a, all -> 0x0155 }
            r0.<init>()     // Catch:{ Throwable -> 0x015a, all -> 0x0155 }
            r5 = 1024(0x400, float:1.435E-42)
            byte[] r5 = new byte[r5]     // Catch:{ Throwable -> 0x015a, all -> 0x0155 }
        L_0x010d:
            int r6 = r8.read(r5)     // Catch:{ Throwable -> 0x015a, all -> 0x0155 }
            r7 = -1
            if (r6 == r7) goto L_0x011d
            java.lang.String r7 = new java.lang.String     // Catch:{ Throwable -> 0x015a, all -> 0x0155 }
            r7.<init>(r5, r2, r6)     // Catch:{ Throwable -> 0x015a, all -> 0x0155 }
            r0.append(r7)     // Catch:{ Throwable -> 0x015a, all -> 0x0155 }
            goto L_0x010d
        L_0x011d:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x015a, all -> 0x0155 }
            java.lang.String r6 = "get MAN response: "
            r5.<init>(r6)     // Catch:{ Throwable -> 0x015a, all -> 0x0155 }
            java.lang.String r6 = r0.toString()     // Catch:{ Throwable -> 0x015a, all -> 0x0155 }
            r5.append(r6)     // Catch:{ Throwable -> 0x015a, all -> 0x0155 }
            java.lang.String r5 = r5.toString()     // Catch:{ Throwable -> 0x015a, all -> 0x0155 }
            com.loc.dk.a((java.lang.String) r5)     // Catch:{ Throwable -> 0x015a, all -> 0x0155 }
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0150 }
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x0150 }
            r5.<init>(r0)     // Catch:{ JSONException -> 0x0150 }
            java.lang.String r0 = "success"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ JSONException -> 0x0150 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ JSONException -> 0x0150 }
            java.lang.String r5 = "success"
            boolean r0 = r0.equals(r5)     // Catch:{ JSONException -> 0x0150 }
            if (r0 == 0) goto L_0x0170
            f6563a = r1     // Catch:{ JSONException -> 0x0150 }
            goto L_0x0170
        L_0x0150:
            r0 = move-exception
            com.loc.dk.a((java.lang.Throwable) r0)     // Catch:{ Throwable -> 0x015a, all -> 0x0155 }
            goto L_0x0170
        L_0x0155:
            r0 = move-exception
            r1 = r8
            r8 = r0
            goto L_0x01be
        L_0x015a:
            r0 = move-exception
            r1 = r8
            r8 = r0
            goto L_0x0198
        L_0x015e:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x018f, all -> 0x018c }
            java.lang.String r5 = "MAN API error: "
            r1.<init>(r5)     // Catch:{ Throwable -> 0x018f, all -> 0x018c }
            r1.append(r8)     // Catch:{ Throwable -> 0x018f, all -> 0x018c }
            java.lang.String r8 = r1.toString()     // Catch:{ Throwable -> 0x018f, all -> 0x018c }
            com.loc.dk.a((java.lang.String) r8)     // Catch:{ Throwable -> 0x018f, all -> 0x018c }
            r8 = r0
        L_0x0170:
            if (r3 == 0) goto L_0x0175
            r3.disconnect()
        L_0x0175:
            if (r4 == 0) goto L_0x017d
            r4.close()     // Catch:{ IOException -> 0x017b }
            goto L_0x017d
        L_0x017b:
            r8 = move-exception
            goto L_0x0183
        L_0x017d:
            if (r8 == 0) goto L_0x0186
            r8.close()     // Catch:{ IOException -> 0x017b }
            goto L_0x0186
        L_0x0183:
            com.loc.dk.a((java.lang.Throwable) r8)
        L_0x0186:
            java.util.concurrent.atomic.AtomicBoolean r8 = b
            r8.set(r2)
            return
        L_0x018c:
            r8 = move-exception
            r1 = r0
            goto L_0x01be
        L_0x018f:
            r8 = move-exception
            r1 = r0
            goto L_0x0198
        L_0x0192:
            r8 = move-exception
            r1 = r0
            goto L_0x01bf
        L_0x0195:
            r8 = move-exception
            r1 = r0
            r4 = r1
        L_0x0198:
            r0 = r3
            goto L_0x01a1
        L_0x019a:
            r8 = move-exception
            r1 = r0
            r3 = r1
            goto L_0x01bf
        L_0x019e:
            r8 = move-exception
            r1 = r0
            r4 = r1
        L_0x01a1:
            java.lang.String r3 = "MAN report error"
            com.loc.dk.a((java.lang.String) r3)     // Catch:{ all -> 0x01bc }
            com.loc.dk.a((java.lang.Throwable) r8)     // Catch:{ all -> 0x01bc }
            if (r0 == 0) goto L_0x01ae
            r0.disconnect()
        L_0x01ae:
            if (r4 == 0) goto L_0x01b6
            r4.close()     // Catch:{ IOException -> 0x01b4 }
            goto L_0x01b6
        L_0x01b4:
            r8 = move-exception
            goto L_0x0183
        L_0x01b6:
            if (r1 == 0) goto L_0x0186
            r1.close()     // Catch:{ IOException -> 0x01b4 }
            goto L_0x0186
        L_0x01bc:
            r8 = move-exception
            r3 = r0
        L_0x01be:
            r0 = r4
        L_0x01bf:
            if (r3 == 0) goto L_0x01c4
            r3.disconnect()
        L_0x01c4:
            if (r0 == 0) goto L_0x01cc
            r0.close()     // Catch:{ IOException -> 0x01ca }
            goto L_0x01cc
        L_0x01ca:
            r0 = move-exception
            goto L_0x01d2
        L_0x01cc:
            if (r1 == 0) goto L_0x01d5
            r1.close()     // Catch:{ IOException -> 0x01ca }
            goto L_0x01d5
        L_0x01d2:
            com.loc.dk.a((java.lang.Throwable) r0)
        L_0x01d5:
            java.util.concurrent.atomic.AtomicBoolean r0 = b
            r0.set(r2)
            throw r8
        L_0x01db:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dl.a(android.content.Context):void");
    }

    static boolean a() {
        return f6563a;
    }
}
