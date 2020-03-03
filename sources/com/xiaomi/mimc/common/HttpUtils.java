package com.xiaomi.mimc.common;

import java.util.Map;

public class HttpUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f11170a = 8000;

    public interface CallBack {
        void a(Exception exc);

        void a(String str);
    }

    public static void a(final String str, final Map<String, String> map, final CallBack callBack) {
        new Thread() {
            public void run() {
                try {
                    String a2 = HttpUtils.a(str, map);
                    if (callBack != null) {
                        callBack.a(a2);
                    }
                } catch (Exception e) {
                    if (callBack != null) {
                        callBack.a(e);
                    }
                }
            }
        }.start();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: java.io.InputStream} */
    /* JADX WARNING: type inference failed for: r5v1, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r5v3 */
    /* JADX WARNING: type inference failed for: r5v5 */
    /* JADX WARNING: type inference failed for: r5v11 */
    /* JADX WARNING: type inference failed for: r5v12 */
    /* JADX WARNING: type inference failed for: r5v13 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00d7  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r5, java.util.Map<java.lang.String, java.lang.String> r6) throws java.lang.Exception {
        /*
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
            r1.<init>(r5)     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
            java.net.URLConnection r5 = r1.openConnection()     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
            java.lang.String r1 = "accept"
            java.lang.String r2 = "*/*"
            r5.setRequestProperty(r1, r2)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.lang.String r1 = "connection"
            java.lang.String r2 = "Keep-Alive"
            r5.setRequestProperty(r1, r2)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            if (r6 == 0) goto L_0x0040
            java.util.Set r6 = r6.entrySet()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
        L_0x0024:
            boolean r1 = r6.hasNext()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            if (r1 == 0) goto L_0x0040
            java.lang.Object r1 = r6.next()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.lang.Object r2 = r1.getKey()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r5.setRequestProperty(r2, r1)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            goto L_0x0024
        L_0x0040:
            java.lang.String r6 = "GET"
            r5.setRequestMethod(r6)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r6 = 8000(0x1f40, float:1.121E-41)
            r5.setConnectTimeout(r6)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r5.setReadTimeout(r6)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            int r6 = r5.getResponseCode()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r1 = 200(0xc8, float:2.8E-43)
            if (r6 != r1) goto L_0x009c
            java.io.InputStream r6 = r5.getInputStream()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0095, all -> 0x008f }
            r1.<init>()     // Catch:{ Exception -> 0x0095, all -> 0x008f }
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x0089, all -> 0x0083 }
        L_0x0062:
            int r2 = r6.read(r0)     // Catch:{ Exception -> 0x0089, all -> 0x0083 }
            r3 = -1
            if (r2 == r3) goto L_0x006e
            r3 = 0
            r1.write(r0, r3, r2)     // Catch:{ Exception -> 0x0089, all -> 0x0083 }
            goto L_0x0062
        L_0x006e:
            r1.flush()     // Catch:{ Exception -> 0x0089, all -> 0x0083 }
            java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x0089, all -> 0x0083 }
            if (r6 == 0) goto L_0x007a
            r6.close()
        L_0x007a:
            r1.close()
            if (r5 == 0) goto L_0x0082
            r5.disconnect()
        L_0x0082:
            return r0
        L_0x0083:
            r0 = move-exception
            r4 = r0
            r0 = r6
            r6 = r4
            goto L_0x00cb
        L_0x0089:
            r0 = move-exception
            r4 = r0
            r0 = r5
            r5 = r6
            r6 = r4
            goto L_0x00c6
        L_0x008f:
            r1 = move-exception
            r4 = r0
            r0 = r6
            r6 = r1
            r1 = r4
            goto L_0x00cb
        L_0x0095:
            r1 = move-exception
            r4 = r0
            r0 = r5
            r5 = r6
            r6 = r1
            r1 = r4
            goto L_0x00c6
        L_0x009c:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r1.<init>()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.lang.String r2 = "Get response code is "
            r1.append(r2)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            int r2 = r5.getResponseCode()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r1.append(r2)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r6.<init>(r1)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            throw r6     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
        L_0x00b7:
            r6 = move-exception
            r1 = r0
            goto L_0x00cb
        L_0x00ba:
            r6 = move-exception
            r1 = r0
            r0 = r5
            r5 = r1
            goto L_0x00c6
        L_0x00bf:
            r6 = move-exception
            r5 = r0
            r1 = r5
            goto L_0x00cb
        L_0x00c3:
            r6 = move-exception
            r5 = r0
            r1 = r5
        L_0x00c6:
            throw r6     // Catch:{ all -> 0x00c7 }
        L_0x00c7:
            r6 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
        L_0x00cb:
            if (r0 == 0) goto L_0x00d0
            r0.close()
        L_0x00d0:
            if (r1 == 0) goto L_0x00d5
            r1.close()
        L_0x00d5:
            if (r5 == 0) goto L_0x00da
            r5.disconnect()
        L_0x00da:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.common.HttpUtils.a(java.lang.String, java.util.Map):java.lang.String");
    }

    public static void a(final String str, final Map<String, String> map, final String str2, final CallBack callBack) {
        new Thread() {
            public void run() {
                try {
                    String a2 = HttpUtils.b(str, (Map<String, String>) map, str2);
                    if (callBack != null) {
                        callBack.a(a2);
                    }
                } catch (Exception e) {
                    if (callBack != null) {
                        callBack.a(e);
                    }
                }
            }
        }.start();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: java.io.BufferedReader} */
    /* JADX WARNING: type inference failed for: r7v1, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r7v3 */
    /* JADX WARNING: type inference failed for: r7v5 */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: type inference failed for: r7v9 */
    /* JADX WARNING: type inference failed for: r7v13 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00eb  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(java.lang.String r5, java.util.Map<java.lang.String, java.lang.String> r6, java.lang.String r7) throws java.lang.Exception {
        /*
            java.lang.String r0 = ""
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x00d7, all -> 0x00d3 }
            r2.<init>(r5)     // Catch:{ Exception -> 0x00d7, all -> 0x00d3 }
            java.net.URLConnection r5 = r2.openConnection()     // Catch:{ Exception -> 0x00d7, all -> 0x00d3 }
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ Exception -> 0x00d7, all -> 0x00d3 }
            java.lang.String r2 = "accept"
            java.lang.String r3 = "*/*"
            r5.setRequestProperty(r2, r3)     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            java.lang.String r2 = "connection"
            java.lang.String r3 = "Keep-Alive"
            r5.setRequestProperty(r2, r3)     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            java.lang.String r2 = "charset"
            java.lang.String r3 = "utf-8"
            r5.setRequestProperty(r2, r3)     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            if (r6 == 0) goto L_0x0049
            java.util.Set r6 = r6.entrySet()     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
        L_0x002d:
            boolean r2 = r6.hasNext()     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            if (r2 == 0) goto L_0x0049
            java.lang.Object r2 = r6.next()     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            java.lang.Object r3 = r2.getKey()     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            java.lang.Object r2 = r2.getValue()     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            r5.setRequestProperty(r3, r2)     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            goto L_0x002d
        L_0x0049:
            java.lang.String r6 = "POST"
            r5.setRequestMethod(r6)     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            r6 = 0
            r5.setUseCaches(r6)     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            r6 = 1
            r5.setDoOutput(r6)     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            r5.setDoInput(r6)     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            r6 = 8000(0x1f40, float:1.121E-41)
            r5.setConnectTimeout(r6)     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            r5.setReadTimeout(r6)     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            if (r7 == 0) goto L_0x007f
            java.lang.String r6 = r7.trim()     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            java.lang.String r2 = ""
            boolean r6 = r6.equals(r2)     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            if (r6 != 0) goto L_0x007f
            java.io.PrintWriter r6 = new java.io.PrintWriter     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            java.io.OutputStream r2 = r5.getOutputStream()     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            r6.<init>(r2)     // Catch:{ Exception -> 0x00ce, all -> 0x00ca }
            r6.print(r7)     // Catch:{ Exception -> 0x00c3, all -> 0x00bd }
            r6.flush()     // Catch:{ Exception -> 0x00c3, all -> 0x00bd }
            goto L_0x0080
        L_0x007f:
            r6 = r1
        L_0x0080:
            java.io.BufferedReader r7 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00c3, all -> 0x00bd }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00c3, all -> 0x00bd }
            java.io.InputStream r3 = r5.getInputStream()     // Catch:{ Exception -> 0x00c3, all -> 0x00bd }
            r2.<init>(r3)     // Catch:{ Exception -> 0x00c3, all -> 0x00bd }
            r7.<init>(r2)     // Catch:{ Exception -> 0x00c3, all -> 0x00bd }
        L_0x008e:
            java.lang.String r1 = r7.readLine()     // Catch:{ Exception -> 0x00b8, all -> 0x00b2 }
            if (r1 == 0) goto L_0x00a4
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b8, all -> 0x00b2 }
            r2.<init>()     // Catch:{ Exception -> 0x00b8, all -> 0x00b2 }
            r2.append(r0)     // Catch:{ Exception -> 0x00b8, all -> 0x00b2 }
            r2.append(r1)     // Catch:{ Exception -> 0x00b8, all -> 0x00b2 }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x00b8, all -> 0x00b2 }
            goto L_0x008e
        L_0x00a4:
            r7.close()
            if (r6 == 0) goto L_0x00ac
            r6.close()
        L_0x00ac:
            if (r5 == 0) goto L_0x00b1
            r5.disconnect()
        L_0x00b1:
            return r0
        L_0x00b2:
            r0 = move-exception
            r1 = r7
            r7 = r5
            r5 = r6
            r6 = r0
            goto L_0x00df
        L_0x00b8:
            r0 = move-exception
            r1 = r5
            r5 = r6
            r6 = r0
            goto L_0x00da
        L_0x00bd:
            r7 = move-exception
            r4 = r7
            r7 = r5
            r5 = r6
            r6 = r4
            goto L_0x00df
        L_0x00c3:
            r7 = move-exception
            r4 = r1
            r1 = r5
            r5 = r6
            r6 = r7
            r7 = r4
            goto L_0x00da
        L_0x00ca:
            r6 = move-exception
            r7 = r5
            r5 = r1
            goto L_0x00df
        L_0x00ce:
            r6 = move-exception
            r7 = r1
            r1 = r5
            r5 = r7
            goto L_0x00da
        L_0x00d3:
            r6 = move-exception
            r5 = r1
            r7 = r5
            goto L_0x00df
        L_0x00d7:
            r6 = move-exception
            r5 = r1
            r7 = r5
        L_0x00da:
            throw r6     // Catch:{ all -> 0x00db }
        L_0x00db:
            r6 = move-exception
            r4 = r1
            r1 = r7
            r7 = r4
        L_0x00df:
            if (r1 == 0) goto L_0x00e4
            r1.close()
        L_0x00e4:
            if (r5 == 0) goto L_0x00e9
            r5.close()
        L_0x00e9:
            if (r7 == 0) goto L_0x00ee
            r7.disconnect()
        L_0x00ee:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.common.HttpUtils.b(java.lang.String, java.util.Map, java.lang.String):java.lang.String");
    }

    public static void b(final String str, final Map<String, String> map, final CallBack callBack) {
        new Thread() {
            public void run() {
                try {
                    String b2 = HttpUtils.c(str, map);
                    if (callBack != null) {
                        callBack.a(b2);
                    }
                } catch (Exception e) {
                    if (callBack != null) {
                        callBack.a(e);
                    }
                }
            }
        }.start();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: java.io.InputStream} */
    /* JADX WARNING: type inference failed for: r5v1, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r5v3 */
    /* JADX WARNING: type inference failed for: r5v5 */
    /* JADX WARNING: type inference failed for: r5v11 */
    /* JADX WARNING: type inference failed for: r5v12 */
    /* JADX WARNING: type inference failed for: r5v13 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00d7  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String c(java.lang.String r5, java.util.Map<java.lang.String, java.lang.String> r6) throws java.lang.Exception {
        /*
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
            r1.<init>(r5)     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
            java.net.URLConnection r5 = r1.openConnection()     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
            java.lang.String r1 = "accept"
            java.lang.String r2 = "*/*"
            r5.setRequestProperty(r1, r2)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.lang.String r1 = "connection"
            java.lang.String r2 = "Keep-Alive"
            r5.setRequestProperty(r1, r2)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            if (r6 == 0) goto L_0x0040
            java.util.Set r6 = r6.entrySet()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
        L_0x0024:
            boolean r1 = r6.hasNext()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            if (r1 == 0) goto L_0x0040
            java.lang.Object r1 = r6.next()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.lang.Object r2 = r1.getKey()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r5.setRequestProperty(r2, r1)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            goto L_0x0024
        L_0x0040:
            java.lang.String r6 = "DELETE"
            r5.setRequestMethod(r6)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r6 = 8000(0x1f40, float:1.121E-41)
            r5.setConnectTimeout(r6)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r5.setReadTimeout(r6)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            int r6 = r5.getResponseCode()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r1 = 200(0xc8, float:2.8E-43)
            if (r6 != r1) goto L_0x009c
            java.io.InputStream r6 = r5.getInputStream()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0095, all -> 0x008f }
            r1.<init>()     // Catch:{ Exception -> 0x0095, all -> 0x008f }
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x0089, all -> 0x0083 }
        L_0x0062:
            int r2 = r6.read(r0)     // Catch:{ Exception -> 0x0089, all -> 0x0083 }
            r3 = -1
            if (r2 == r3) goto L_0x006e
            r3 = 0
            r1.write(r0, r3, r2)     // Catch:{ Exception -> 0x0089, all -> 0x0083 }
            goto L_0x0062
        L_0x006e:
            r1.flush()     // Catch:{ Exception -> 0x0089, all -> 0x0083 }
            java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x0089, all -> 0x0083 }
            if (r6 == 0) goto L_0x007a
            r6.close()
        L_0x007a:
            r1.close()
            if (r5 == 0) goto L_0x0082
            r5.disconnect()
        L_0x0082:
            return r0
        L_0x0083:
            r0 = move-exception
            r4 = r0
            r0 = r6
            r6 = r4
            goto L_0x00cb
        L_0x0089:
            r0 = move-exception
            r4 = r0
            r0 = r5
            r5 = r6
            r6 = r4
            goto L_0x00c6
        L_0x008f:
            r1 = move-exception
            r4 = r0
            r0 = r6
            r6 = r1
            r1 = r4
            goto L_0x00cb
        L_0x0095:
            r1 = move-exception
            r4 = r0
            r0 = r5
            r5 = r6
            r6 = r1
            r1 = r4
            goto L_0x00c6
        L_0x009c:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r1.<init>()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.lang.String r2 = "Delete response code is "
            r1.append(r2)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            int r2 = r5.getResponseCode()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r1.append(r2)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r6.<init>(r1)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            throw r6     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
        L_0x00b7:
            r6 = move-exception
            r1 = r0
            goto L_0x00cb
        L_0x00ba:
            r6 = move-exception
            r1 = r0
            r0 = r5
            r5 = r1
            goto L_0x00c6
        L_0x00bf:
            r6 = move-exception
            r5 = r0
            r1 = r5
            goto L_0x00cb
        L_0x00c3:
            r6 = move-exception
            r5 = r0
            r1 = r5
        L_0x00c6:
            throw r6     // Catch:{ all -> 0x00c7 }
        L_0x00c7:
            r6 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
        L_0x00cb:
            if (r0 == 0) goto L_0x00d0
            r0.close()
        L_0x00d0:
            if (r1 == 0) goto L_0x00d5
            r1.close()
        L_0x00d5:
            if (r5 == 0) goto L_0x00da
            r5.disconnect()
        L_0x00da:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.common.HttpUtils.c(java.lang.String, java.util.Map):java.lang.String");
    }
}
