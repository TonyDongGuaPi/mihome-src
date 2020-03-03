package com.tencent.smtt.utils;

import android.os.Build;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

public class m {

    public interface a {
        void a(int i);
    }

    public static String a(String str, Map<String, String> map, byte[] bArr, a aVar, boolean z) {
        HttpURLConnection a2;
        if (bArr == null || (a2 = a(str, map)) == null) {
            return null;
        }
        if (z) {
            a(a2, bArr);
        } else {
            b(a2, bArr);
        }
        return a(a2, aVar, false);
    }

    public static String a(String str, byte[] bArr, a aVar, boolean z) {
        String str2;
        byte[] a2;
        if (z) {
            try {
                str2 = o.a().c();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            str2 = n.a().b();
        }
        String str3 = str + str2;
        if (z) {
            try {
                a2 = o.a().a(bArr);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            a2 = n.a().a(bArr);
        }
        bArr = a2;
        if (bArr == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        hashMap.put("Content-Length", String.valueOf(bArr.length));
        HttpURLConnection a3 = a(str3, (Map<String, String>) hashMap);
        if (a3 == null) {
            return null;
        }
        b(a3, bArr);
        return a(a3, aVar, z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.net.HttpURLConnection r5, com.tencent.smtt.utils.m.a r6, boolean r7) {
        /*
            r0 = 0
            int r1 = r5.getResponseCode()     // Catch:{ Throwable -> 0x0095, all -> 0x0092 }
            if (r6 == 0) goto L_0x000a
            r6.a(r1)     // Catch:{ Throwable -> 0x0095, all -> 0x0092 }
        L_0x000a:
            r6 = 200(0xc8, float:2.8E-43)
            if (r1 != r6) goto L_0x0089
            java.io.InputStream r6 = r5.getInputStream()     // Catch:{ Throwable -> 0x0095, all -> 0x0092 }
            java.lang.String r5 = r5.getContentEncoding()     // Catch:{ Throwable -> 0x0095, all -> 0x0092 }
            if (r5 == 0) goto L_0x0026
            java.lang.String r1 = "gzip"
            boolean r1 = r5.equalsIgnoreCase(r1)     // Catch:{ Throwable -> 0x0095, all -> 0x0092 }
            if (r1 == 0) goto L_0x0026
            java.util.zip.GZIPInputStream r5 = new java.util.zip.GZIPInputStream     // Catch:{ Throwable -> 0x0095, all -> 0x0092 }
            r5.<init>(r6)     // Catch:{ Throwable -> 0x0095, all -> 0x0092 }
            goto L_0x003d
        L_0x0026:
            if (r5 == 0) goto L_0x003c
            java.lang.String r1 = "deflate"
            boolean r5 = r5.equalsIgnoreCase(r1)     // Catch:{ Throwable -> 0x0095, all -> 0x0092 }
            if (r5 == 0) goto L_0x003c
            java.util.zip.InflaterInputStream r5 = new java.util.zip.InflaterInputStream     // Catch:{ Throwable -> 0x0095, all -> 0x0092 }
            java.util.zip.Inflater r1 = new java.util.zip.Inflater     // Catch:{ Throwable -> 0x0095, all -> 0x0092 }
            r2 = 1
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0095, all -> 0x0092 }
            r5.<init>(r6, r1)     // Catch:{ Throwable -> 0x0095, all -> 0x0092 }
            goto L_0x003d
        L_0x003c:
            r5 = r6
        L_0x003d:
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x0083, all -> 0x007d }
            r6.<init>()     // Catch:{ Throwable -> 0x0083, all -> 0x007d }
            r1 = 128(0x80, float:1.794E-43)
            byte[] r1 = new byte[r1]     // Catch:{ Throwable -> 0x0077, all -> 0x0073 }
        L_0x0046:
            int r2 = r5.read(r1)     // Catch:{ Throwable -> 0x0077, all -> 0x0073 }
            r3 = -1
            if (r2 == r3) goto L_0x0052
            r3 = 0
            r6.write(r1, r3, r2)     // Catch:{ Throwable -> 0x0077, all -> 0x0073 }
            goto L_0x0046
        L_0x0052:
            if (r7 == 0) goto L_0x0061
            java.lang.String r7 = new java.lang.String     // Catch:{ Throwable -> 0x0077, all -> 0x0073 }
            byte[] r1 = r6.toByteArray()     // Catch:{ Throwable -> 0x0077, all -> 0x0073 }
            java.lang.String r2 = "utf-8"
            r7.<init>(r1, r2)     // Catch:{ Throwable -> 0x0077, all -> 0x0073 }
        L_0x005f:
            r0 = r7
            goto L_0x008b
        L_0x0061:
            java.lang.String r7 = new java.lang.String     // Catch:{ Throwable -> 0x0077, all -> 0x0073 }
            com.tencent.smtt.utils.n r1 = com.tencent.smtt.utils.n.a()     // Catch:{ Throwable -> 0x0077, all -> 0x0073 }
            byte[] r2 = r6.toByteArray()     // Catch:{ Throwable -> 0x0077, all -> 0x0073 }
            byte[] r1 = r1.c(r2)     // Catch:{ Throwable -> 0x0077, all -> 0x0073 }
            r7.<init>(r1)     // Catch:{ Throwable -> 0x0077, all -> 0x0073 }
            goto L_0x005f
        L_0x0073:
            r7 = move-exception
            r0 = r5
            r5 = r7
            goto L_0x00a5
        L_0x0077:
            r7 = move-exception
            r4 = r6
            r6 = r5
            r5 = r7
            r7 = r4
            goto L_0x0098
        L_0x007d:
            r6 = move-exception
            r4 = r0
            r0 = r5
            r5 = r6
            r6 = r4
            goto L_0x00a5
        L_0x0083:
            r6 = move-exception
            r7 = r0
            r4 = r6
            r6 = r5
            r5 = r4
            goto L_0x0098
        L_0x0089:
            r5 = r0
            r6 = r5
        L_0x008b:
            a(r5)
            a(r6)
            goto L_0x00a1
        L_0x0092:
            r5 = move-exception
            r6 = r0
            goto L_0x00a5
        L_0x0095:
            r5 = move-exception
            r6 = r0
            r7 = r6
        L_0x0098:
            r5.printStackTrace()     // Catch:{ all -> 0x00a2 }
            a(r6)
            a(r7)
        L_0x00a1:
            return r0
        L_0x00a2:
            r5 = move-exception
            r0 = r6
            r6 = r7
        L_0x00a5:
            a(r0)
            a(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.m.a(java.net.HttpURLConnection, com.tencent.smtt.utils.m$a, boolean):java.lang.String");
    }

    private static HttpURLConnection a(String str, Map<String, String> map) {
        String str2;
        String str3;
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.setRequestMethod("POST");
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setDoInput(true);
                httpURLConnection2.setUseCaches(false);
                httpURLConnection2.setConnectTimeout(20000);
                if (Build.VERSION.SDK_INT > 13) {
                    str2 = "Connection";
                    str3 = "close";
                } else {
                    str2 = "http.keepAlive";
                    str3 = "false";
                }
                httpURLConnection2.setRequestProperty(str2, str3);
                for (Map.Entry next : map.entrySet()) {
                    httpURLConnection2.setRequestProperty((String) next.getKey(), (String) next.getValue());
                }
                return httpURLConnection2;
            } catch (Exception e) {
                e = e;
                httpURLConnection = httpURLConnection2;
                e.printStackTrace();
                return httpURLConnection;
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return httpURLConnection;
        }
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    private static void a(HttpURLConnection httpURLConnection, byte[] bArr) {
        GZIPOutputStream gZIPOutputStream;
        try {
            gZIPOutputStream = new GZIPOutputStream(new BufferedOutputStream(httpURLConnection.getOutputStream(), 204800));
            try {
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.flush();
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            gZIPOutputStream = null;
            try {
                e.printStackTrace();
                a((Closeable) null);
                a(gZIPOutputStream);
            } catch (Throwable th) {
                th = th;
                a((Closeable) null);
                a(gZIPOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            gZIPOutputStream = null;
            a((Closeable) null);
            a(gZIPOutputStream);
            throw th;
        }
        a((Closeable) null);
        a(gZIPOutputStream);
    }

    private static void b(HttpURLConnection httpURLConnection, byte[] bArr) {
        try {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(bArr);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
