package com.xiaomi.mimc.common;

public class ResolverClient {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11179a = "ResolverClient";

    /* JADX WARNING: Removed duplicated region for block: B:64:0x019e A[SYNTHETIC, Splitter:B:64:0x019e] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01b2 A[SYNTHETIC, Splitter:B:73:0x01b2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.HashMap<java.lang.String, com.xiaomi.mimc.json.JSONArray> a(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.String r1 = "ver"
            java.lang.String r2 = "4.0"
            r0.put(r1, r2)
            java.lang.String r1 = "type"
            java.lang.String r2 = "wifi"
            r0.put(r1, r2)
            java.lang.String r1 = "uuid"
            java.lang.String r2 = "0"
            r0.put(r1, r2)
            java.lang.String r1 = "list"
            r0.put(r1, r14)
            java.lang.String r1 = "sdkver"
            java.lang.String r2 = "35"
            r0.put(r1, r2)
            java.lang.String r1 = "osver"
            java.lang.String r2 = "23"
            r0.put(r1, r2)
            java.lang.String r1 = "os"
            java.lang.String r2 = "MI%204LTE%3A1.1.1"
            r0.put(r1, r2)
            java.lang.String r1 = "mi"
            java.lang.String r2 = "2"
            r0.put(r1, r2)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            r3.<init>()     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            r3.append(r13)     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            java.lang.String r13 = "?"
            r3.append(r13)     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            java.lang.String r13 = r3.toString()     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            java.util.Set r3 = r0.keySet()     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Exception -> 0x0191, all -> 0x018d }
        L_0x005a:
            boolean r4 = r3.hasNext()     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            if (r4 == 0) goto L_0x0089
            java.lang.Object r4 = r3.next()     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            r5.<init>()     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            r5.append(r13)     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            r5.append(r4)     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            java.lang.String r13 = "="
            r5.append(r13)     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            java.lang.Object r13 = r0.get(r4)     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            java.lang.String r13 = (java.lang.String) r13     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            r5.append(r13)     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            java.lang.String r13 = "&"
            r5.append(r13)     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            java.lang.String r13 = r5.toString()     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            goto L_0x005a
        L_0x0089:
            java.lang.String r0 = "ResolverClient"
            java.lang.String r3 = "path:%s"
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            r6 = 0
            r5[r6] = r13     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            java.lang.String r3 = java.lang.String.format(r3, r5)     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            com.xiaomi.msg.logger.MIMCLog.b(r0, r3)     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            java.net.URL r0 = new java.net.URL     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            r0.<init>(r13)     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            java.net.URLConnection r13 = r0.openConnection()     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            java.net.HttpURLConnection r13 = (java.net.HttpURLConnection) r13     // Catch:{ Exception -> 0x0191, all -> 0x018d }
            java.lang.String r0 = "GET"
            r13.setRequestMethod(r0)     // Catch:{ Exception -> 0x018a, all -> 0x0188 }
            r0 = 15000(0x3a98, float:2.102E-41)
            r13.setConnectTimeout(r0)     // Catch:{ Exception -> 0x018a, all -> 0x0188 }
            r0 = 60000(0xea60, float:8.4078E-41)
            r13.setReadTimeout(r0)     // Catch:{ Exception -> 0x018a, all -> 0x0188 }
            java.lang.String r0 = "Content-Type"
            java.lang.String r3 = "application/x-www-form-urlencoded"
            r13.setRequestProperty(r0, r3)     // Catch:{ Exception -> 0x018a, all -> 0x0188 }
            r13.setDoOutput(r6)     // Catch:{ Exception -> 0x018a, all -> 0x0188 }
            r13.setDoInput(r4)     // Catch:{ Exception -> 0x018a, all -> 0x0188 }
            r13.connect()     // Catch:{ Exception -> 0x018a, all -> 0x0188 }
            int r0 = r13.getResponseCode()     // Catch:{ Exception -> 0x018a, all -> 0x0188 }
            r3 = 200(0xc8, float:2.8E-43)
            if (r0 != r3) goto L_0x00e7
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Exception -> 0x018a, all -> 0x0188 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x018a, all -> 0x0188 }
            java.io.InputStream r5 = r13.getInputStream()     // Catch:{ Exception -> 0x018a, all -> 0x0188 }
            java.lang.String r7 = "UTF-8"
            r3.<init>(r5, r7)     // Catch:{ Exception -> 0x018a, all -> 0x0188 }
            r0.<init>(r3)     // Catch:{ Exception -> 0x018a, all -> 0x0188 }
        L_0x00dd:
            java.lang.String r3 = r0.readLine()     // Catch:{ Exception -> 0x0186 }
            if (r3 == 0) goto L_0x00e8
            r1.append(r3)     // Catch:{ Exception -> 0x0186 }
            goto L_0x00dd
        L_0x00e7:
            r0 = r2
        L_0x00e8:
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0186 }
            boolean r3 = com.xiaomi.mimc.common.MIMCUtils.c((java.lang.String) r1)     // Catch:{ Exception -> 0x0186 }
            if (r3 == 0) goto L_0x010b
            java.lang.String r14 = "ResolverClient"
            java.lang.String r1 = "get relayAddress from resolver is failed"
            com.xiaomi.msg.logger.MIMCLog.c(r14, r1)     // Catch:{ Exception -> 0x0186 }
            if (r0 == 0) goto L_0x0107
            r0.close()     // Catch:{ IOException -> 0x00ff }
            goto L_0x0107
        L_0x00ff:
            r14 = move-exception
            java.lang.String r0 = "ResolverClient"
            java.lang.String r1 = "Close BufferedReader Exception:"
            com.xiaomi.msg.logger.MIMCLog.c(r0, r1, r14)
        L_0x0107:
            r13.disconnect()
            return r2
        L_0x010b:
            com.xiaomi.mimc.json.JSONObject r3 = new com.xiaomi.mimc.json.JSONObject     // Catch:{ Exception -> 0x0186 }
            r3.<init>((java.lang.String) r1)     // Catch:{ Exception -> 0x0186 }
            java.lang.String r1 = "S"
            java.lang.String r1 = r3.l(r1)     // Catch:{ Exception -> 0x0186 }
            java.lang.String r5 = "OK"
            boolean r1 = r1.equalsIgnoreCase(r5)     // Catch:{ Exception -> 0x0186 }
            if (r1 != 0) goto L_0x0137
            java.lang.String r14 = "ResolverClient"
            java.lang.String r1 = "HTTP_REQUEST_FAIL, HTTP_RETURN_CODE!=OK"
            com.xiaomi.msg.logger.MIMCLog.c(r14, r1)     // Catch:{ Exception -> 0x0186 }
            if (r0 == 0) goto L_0x0133
            r0.close()     // Catch:{ IOException -> 0x012b }
            goto L_0x0133
        L_0x012b:
            r14 = move-exception
            java.lang.String r0 = "ResolverClient"
            java.lang.String r1 = "Close BufferedReader Exception:"
            com.xiaomi.msg.logger.MIMCLog.c(r0, r1, r14)
        L_0x0133:
            r13.disconnect()
            return r2
        L_0x0137:
            java.lang.String r1 = ","
            java.lang.String[] r14 = r14.split(r1)     // Catch:{ Exception -> 0x0186 }
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ Exception -> 0x0186 }
            r1.<init>()     // Catch:{ Exception -> 0x0186 }
            r5 = 0
        L_0x0143:
            int r7 = r14.length     // Catch:{ Exception -> 0x0186 }
            if (r5 >= r7) goto L_0x0174
            java.lang.String r7 = "R"
            com.xiaomi.mimc.json.JSONObject r7 = r3.j(r7)     // Catch:{ Exception -> 0x0186 }
            java.lang.String r8 = "wifi"
            com.xiaomi.mimc.json.JSONObject r7 = r7.j(r8)     // Catch:{ Exception -> 0x0186 }
            r8 = r14[r5]     // Catch:{ Exception -> 0x0186 }
            com.xiaomi.mimc.json.JSONArray r7 = r7.i(r8)     // Catch:{ Exception -> 0x0186 }
            r8 = r14[r5]     // Catch:{ Exception -> 0x0186 }
            r1.put(r8, r7)     // Catch:{ Exception -> 0x0186 }
            java.lang.String r8 = "ResolverClient"
            java.lang.String r9 = "domain:%s address:%s"
            r10 = 2
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ Exception -> 0x0186 }
            r11 = r14[r5]     // Catch:{ Exception -> 0x0186 }
            r10[r6] = r11     // Catch:{ Exception -> 0x0186 }
            r10[r4] = r7     // Catch:{ Exception -> 0x0186 }
            java.lang.String r7 = java.lang.String.format(r9, r10)     // Catch:{ Exception -> 0x0186 }
            com.xiaomi.msg.logger.MIMCLog.b(r8, r7)     // Catch:{ Exception -> 0x0186 }
            int r5 = r5 + 1
            goto L_0x0143
        L_0x0174:
            if (r0 == 0) goto L_0x0182
            r0.close()     // Catch:{ IOException -> 0x017a }
            goto L_0x0182
        L_0x017a:
            r14 = move-exception
            java.lang.String r0 = "ResolverClient"
            java.lang.String r2 = "Close BufferedReader Exception:"
            com.xiaomi.msg.logger.MIMCLog.c(r0, r2, r14)
        L_0x0182:
            r13.disconnect()
            return r1
        L_0x0186:
            r14 = move-exception
            goto L_0x0195
        L_0x0188:
            r14 = move-exception
            goto L_0x01b0
        L_0x018a:
            r14 = move-exception
            r0 = r2
            goto L_0x0195
        L_0x018d:
            r13 = move-exception
            r14 = r13
            r13 = r2
            goto L_0x01b0
        L_0x0191:
            r13 = move-exception
            r14 = r13
            r13 = r2
            r0 = r13
        L_0x0195:
            java.lang.String r1 = "ResolverClient"
            java.lang.String r3 = "ResolverClient Exception:"
            com.xiaomi.msg.logger.MIMCLog.c(r1, r3, r14)     // Catch:{ all -> 0x01ae }
            if (r0 == 0) goto L_0x01aa
            r0.close()     // Catch:{ IOException -> 0x01a2 }
            goto L_0x01aa
        L_0x01a2:
            r14 = move-exception
            java.lang.String r0 = "ResolverClient"
            java.lang.String r1 = "Close BufferedReader Exception:"
            com.xiaomi.msg.logger.MIMCLog.c(r0, r1, r14)
        L_0x01aa:
            r13.disconnect()
            return r2
        L_0x01ae:
            r14 = move-exception
            r2 = r0
        L_0x01b0:
            if (r2 == 0) goto L_0x01be
            r2.close()     // Catch:{ IOException -> 0x01b6 }
            goto L_0x01be
        L_0x01b6:
            r0 = move-exception
            java.lang.String r1 = "ResolverClient"
            java.lang.String r2 = "Close BufferedReader Exception:"
            com.xiaomi.msg.logger.MIMCLog.c(r1, r2, r0)
        L_0x01be:
            r13.disconnect()
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.common.ResolverClient.a(java.lang.String, java.lang.String):java.util.HashMap");
    }
}
