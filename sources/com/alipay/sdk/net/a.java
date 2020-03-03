package com.alipay.sdk.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.Map;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1112a = "msp";
    private static final String b = "application/octet-stream;binary/octet-stream";
    private static final CookieManager c = new CookieManager();

    /* renamed from: com.alipay.sdk.net.a$a  reason: collision with other inner class name */
    public static final class C0030a {

        /* renamed from: a  reason: collision with root package name */
        public final String f1113a;
        public final byte[] b;
        public final Map<String, String> c;

        public C0030a(String str, Map<String, String> map, byte[] bArr) {
            this.f1113a = str;
            this.b = bArr;
            this.c = map;
        }

        public String toString() {
            return String.format("<UrlConnectionConfigure url=%s requestBody=%s headers=%s>", new Object[]{this.f1113a, this.b, this.c});
        }
    }

    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public final Map<String, List<String>> f1114a;
        public final String b;
        public final byte[] c;

        public b(Map<String, List<String>> map, String str, byte[] bArr) {
            this.f1114a = map;
            this.b = str;
            this.c = bArr;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v27, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v29, resolved type: java.io.BufferedOutputStream} */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:64:0x0192 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01d1 A[SYNTHETIC, Splitter:B:106:0x01d1] */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x01d6 A[SYNTHETIC, Splitter:B:110:0x01d6] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01db A[SYNTHETIC, Splitter:B:114:0x01db] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0197 A[SYNTHETIC, Splitter:B:68:0x0197] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01bf A[SYNTHETIC, Splitter:B:91:0x01bf] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01c4 A[SYNTHETIC, Splitter:B:95:0x01c4] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01c9 A[SYNTHETIC, Splitter:B:99:0x01c9] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.alipay.sdk.net.a.b a(android.content.Context r12, com.alipay.sdk.net.a.C0030a r13) {
        /*
            r0 = 0
            if (r12 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = "msp"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            r2.<init>()     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            java.lang.String r3 = "config : "
            r2.append(r3)     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            r2.append(r13)     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            com.alipay.sdk.util.c.c(r1, r2)     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            java.net.URL r1 = new java.net.URL     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            java.lang.String r2 = r13.f1113a     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            java.net.Proxy r12 = a((android.content.Context) r12)     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            java.lang.String r2 = "msp"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            r3.<init>()     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            java.lang.String r4 = "proxy: "
            r3.append(r4)     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            r3.append(r12)     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            com.alipay.sdk.util.c.c(r2, r3)     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            if (r12 == 0) goto L_0x0044
            java.net.URLConnection r12 = r1.openConnection(r12)     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            java.net.HttpURLConnection r12 = (java.net.HttpURLConnection) r12     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            goto L_0x004a
        L_0x0044:
            java.net.URLConnection r12 = r1.openConnection()     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
            java.net.HttpURLConnection r12 = (java.net.HttpURLConnection) r12     // Catch:{ Throwable -> 0x01b5, all -> 0x01b0 }
        L_0x004a:
            java.lang.String r2 = "http.keepAlive"
            java.lang.String r3 = "false"
            java.lang.System.setProperty(r2, r3)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            boolean r2 = r12 instanceof javax.net.ssl.HttpsURLConnection     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            if (r2 == 0) goto L_0x0058
            r2 = r12
            javax.net.ssl.HttpsURLConnection r2 = (javax.net.ssl.HttpsURLConnection) r2     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
        L_0x0058:
            java.net.CookieManager r2 = c     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.net.CookieStore r2 = r2.getCookieStore()     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.util.List r2 = r2.getCookies()     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            int r2 = r2.size()     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            if (r2 <= 0) goto L_0x007d
            java.lang.String r2 = "Cookie"
            java.lang.String r3 = ";"
            java.net.CookieManager r4 = c     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.net.CookieStore r4 = r4.getCookieStore()     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.util.List r4 = r4.getCookies()     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.lang.String r3 = android.text.TextUtils.join(r3, r4)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            r12.setRequestProperty(r2, r3)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
        L_0x007d:
            r2 = 20000(0x4e20, float:2.8026E-41)
            r12.setConnectTimeout(r2)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            r2 = 30000(0x7530, float:4.2039E-41)
            r12.setReadTimeout(r2)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            r2 = 1
            r12.setInstanceFollowRedirects(r2)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.lang.String r3 = "User-Agent"
            java.lang.String r4 = "msp"
            r12.setRequestProperty(r3, r4)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            byte[] r3 = r13.b     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            if (r3 == 0) goto L_0x00be
            byte[] r3 = r13.b     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            int r3 = r3.length     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            if (r3 <= 0) goto L_0x00be
            java.lang.String r3 = "POST"
            r12.setRequestMethod(r3)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.lang.String r3 = "Content-Type"
            java.lang.String r4 = "application/octet-stream;binary/octet-stream"
            r12.setRequestProperty(r3, r4)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.lang.String r3 = "Accept-Charset"
            java.lang.String r4 = "UTF-8"
            r12.setRequestProperty(r3, r4)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.lang.String r3 = "Connection"
            java.lang.String r4 = "Keep-Alive"
            r12.setRequestProperty(r3, r4)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.lang.String r3 = "Keep-Alive"
            java.lang.String r4 = "timeout=180, max=100"
            r12.setRequestProperty(r3, r4)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            goto L_0x00c3
        L_0x00be:
            java.lang.String r3 = "GET"
            r12.setRequestMethod(r3)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
        L_0x00c3:
            java.util.Map<java.lang.String, java.lang.String> r3 = r13.c     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            if (r3 == 0) goto L_0x00f4
            java.util.Map<java.lang.String, java.lang.String> r3 = r13.c     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.util.Set r3 = r3.entrySet()     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
        L_0x00d1:
            boolean r4 = r3.hasNext()     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            if (r4 == 0) goto L_0x00f4
            java.lang.Object r4 = r3.next()     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.lang.Object r5 = r4.getKey()     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            if (r5 != 0) goto L_0x00e4
            goto L_0x00d1
        L_0x00e4:
            java.lang.Object r5 = r4.getKey()     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.lang.Object r4 = r4.getValue()     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            r12.setRequestProperty(r5, r4)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            goto L_0x00d1
        L_0x00f4:
            r12.setDoInput(r2)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.lang.String r3 = "POST"
            java.lang.String r4 = r12.getRequestMethod()     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            boolean r3 = r3.equals(r4)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            if (r3 == 0) goto L_0x0106
            r12.setDoOutput(r2)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
        L_0x0106:
            java.lang.String r2 = "POST"
            java.lang.String r3 = r12.getRequestMethod()     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            boolean r2 = r2.equals(r3)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            if (r2 == 0) goto L_0x0124
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            java.io.OutputStream r3 = r12.getOutputStream()     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x01ad, all -> 0x01aa }
            byte[] r13 = r13.b     // Catch:{ Throwable -> 0x01a7, all -> 0x01a5 }
            r2.write(r13)     // Catch:{ Throwable -> 0x01a7, all -> 0x01a5 }
            r2.flush()     // Catch:{ Throwable -> 0x01a7, all -> 0x01a5 }
            goto L_0x0125
        L_0x0124:
            r2 = r0
        L_0x0125:
            java.io.BufferedInputStream r13 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x01a7, all -> 0x01a5 }
            java.io.InputStream r3 = r12.getInputStream()     // Catch:{ Throwable -> 0x01a7, all -> 0x01a5 }
            r13.<init>(r3)     // Catch:{ Throwable -> 0x01a7, all -> 0x01a5 }
            byte[] r3 = a((java.io.InputStream) r13)     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            java.util.Map r4 = r12.getHeaderFields()     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            if (r4 == 0) goto L_0x014b
            java.lang.Object r5 = r4.get(r0)     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            if (r5 == 0) goto L_0x014b
            java.lang.String r5 = ","
            java.lang.Object r6 = r4.get(r0)     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            java.lang.Iterable r6 = (java.lang.Iterable) r6     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            java.lang.String r5 = android.text.TextUtils.join(r5, r6)     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            goto L_0x014c
        L_0x014b:
            r5 = r0
        L_0x014c:
            java.lang.String r6 = "Set-Cookie"
            java.lang.Object r6 = r4.get(r6)     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            java.util.List r6 = (java.util.List) r6     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            if (r6 == 0) goto L_0x0188
            java.util.Iterator r6 = r6.iterator()     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
        L_0x015a:
            boolean r7 = r6.hasNext()     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            if (r7 == 0) goto L_0x0188
            java.lang.Object r7 = r6.next()     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            java.util.List r7 = java.net.HttpCookie.parse(r7)     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            if (r7 == 0) goto L_0x015a
            boolean r8 = r7.isEmpty()     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            if (r8 == 0) goto L_0x0173
            goto L_0x015a
        L_0x0173:
            java.net.CookieManager r8 = c     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            java.net.CookieStore r8 = r8.getCookieStore()     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            java.net.URI r9 = r1.toURI()     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            r10 = 0
            java.lang.Object r7 = r7.get(r10)     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            java.net.HttpCookie r7 = (java.net.HttpCookie) r7     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            r8.add(r9, r7)     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            goto L_0x015a
        L_0x0188:
            com.alipay.sdk.net.a$b r1 = new com.alipay.sdk.net.a$b     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            r1.<init>(r4, r5, r3)     // Catch:{ Throwable -> 0x01a0, all -> 0x019b }
            if (r12 == 0) goto L_0x0192
            r12.disconnect()     // Catch:{ Throwable -> 0x0192 }
        L_0x0192:
            r13.close()     // Catch:{ Throwable -> 0x0195 }
        L_0x0195:
            if (r2 == 0) goto L_0x019a
            r2.close()     // Catch:{ Throwable -> 0x019a }
        L_0x019a:
            return r1
        L_0x019b:
            r0 = move-exception
            r11 = r0
            r0 = r13
            r13 = r11
            goto L_0x01cf
        L_0x01a0:
            r1 = move-exception
            r11 = r1
            r1 = r13
            r13 = r11
            goto L_0x01ba
        L_0x01a5:
            r13 = move-exception
            goto L_0x01cf
        L_0x01a7:
            r13 = move-exception
            r1 = r0
            goto L_0x01ba
        L_0x01aa:
            r13 = move-exception
            r2 = r0
            goto L_0x01cf
        L_0x01ad:
            r13 = move-exception
            r1 = r0
            goto L_0x01b9
        L_0x01b0:
            r12 = move-exception
            r13 = r12
            r12 = r0
            r2 = r12
            goto L_0x01cf
        L_0x01b5:
            r12 = move-exception
            r13 = r12
            r12 = r0
            r1 = r12
        L_0x01b9:
            r2 = r1
        L_0x01ba:
            com.alipay.sdk.util.c.a(r13)     // Catch:{ all -> 0x01cd }
            if (r12 == 0) goto L_0x01c2
            r12.disconnect()     // Catch:{ Throwable -> 0x01c2 }
        L_0x01c2:
            if (r1 == 0) goto L_0x01c7
            r1.close()     // Catch:{ Throwable -> 0x01c7 }
        L_0x01c7:
            if (r2 == 0) goto L_0x01cc
            r2.close()     // Catch:{ Throwable -> 0x01cc }
        L_0x01cc:
            return r0
        L_0x01cd:
            r13 = move-exception
            r0 = r1
        L_0x01cf:
            if (r12 == 0) goto L_0x01d4
            r12.disconnect()     // Catch:{ Throwable -> 0x01d4 }
        L_0x01d4:
            if (r0 == 0) goto L_0x01d9
            r0.close()     // Catch:{ Throwable -> 0x01d9 }
        L_0x01d9:
            if (r2 == 0) goto L_0x01de
            r2.close()     // Catch:{ Throwable -> 0x01de }
        L_0x01de:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.net.a.a(android.content.Context, com.alipay.sdk.net.a$a):com.alipay.sdk.net.a$b");
    }

    private static Proxy a(Context context) {
        String c2 = c(context);
        if (c2 != null && !c2.contains("wap")) {
            return null;
        }
        try {
            String property = System.getProperty("https.proxyHost");
            String property2 = System.getProperty("https.proxyPort");
            if (!TextUtils.isEmpty(property)) {
                return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(property, Integer.parseInt(property2)));
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static NetworkInfo b(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
            return null;
        }
    }

    private static String c(Context context) {
        try {
            NetworkInfo b2 = b(context);
            if (b2 == null || !b2.isAvailable()) {
                return "none";
            }
            if (b2.getType() == 1) {
                return "wifi";
            }
            return b2.getExtraInfo().toLowerCase();
        } catch (Exception unused) {
            return "none";
        }
    }

    private static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
