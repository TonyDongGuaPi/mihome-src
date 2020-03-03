package com.mipay.common.data;

import android.text.TextUtils;
import com.mipay.common.exception.f;
import com.mipay.common.exception.g;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.TreeMap;
import org.json.JSONObject;

public class e {
    private static final String h = "Connection";
    private static final int i = 10000;
    private static final int j = 10000;

    /* renamed from: a  reason: collision with root package name */
    protected URL f8128a;
    protected b b;
    protected boolean c;
    protected boolean d;
    protected String e;
    protected JSONObject f;
    protected int g;
    private volatile c k;

    public enum a {
        OK,
        NETWORK_ERROR,
        ACCOUNT_CHANGED_ERROR,
        AUTH_ERROR,
        SERVER_ERROR,
        RESULT_ERROR,
        UNKNOWN_ERROR
    }

    public class b extends h {
        b() {
        }

        public TreeMap<String, Object> a() {
            return this.b;
        }

        public String toString() {
            if (this.b.isEmpty()) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (String str : this.b.keySet()) {
                sb.append(str);
                sb.append("=");
                try {
                    sb.append(URLEncoder.encode(this.b.get(str).toString(), "UTF-8"));
                } catch (UnsupportedEncodingException unused) {
                }
                sb.append(com.alipay.sdk.sys.a.b);
            }
            return sb.deleteCharAt(sb.length() - 1).toString();
        }
    }

    public enum c {
        PENDING,
        RUNNING,
        FINISHED
    }

    e(String str) {
        this.k = c.PENDING;
        try {
            b(new URL(str));
        } catch (MalformedURLException unused) {
            throw new IllegalArgumentException("URL error: " + null);
        }
    }

    e(String str, String str2) {
        this(a(str, str2));
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        if (str.charAt(str.length() - 1) == '/') {
            str = str.substring(0, str.length() - 1);
        }
        if (str2.charAt(0) == '/') {
            str2 = str2.substring(1);
        }
        return str + "/" + str2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x00a9 A[SYNTHETIC, Splitter:B:51:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x00f3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.net.URL r6, java.lang.String r7, boolean r8, boolean r9, java.io.OutputStream r10) throws com.mipay.common.exception.f {
        /*
            r5 = this;
            r0 = 0
            java.net.URLConnection r1 = r6.openConnection()     // Catch:{ f -> 0x00ef, IOException -> 0x00e8, Exception -> 0x00e1 }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ f -> 0x00ef, IOException -> 0x00e8, Exception -> 0x00e1 }
            r2 = 10000(0x2710, float:1.4013E-41)
            r1.setConnectTimeout(r2)     // Catch:{ f -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
            r1.setReadTimeout(r2)     // Catch:{ f -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
            r2 = 0
            if (r8 == 0) goto L_0x001b
            java.lang.String r3 = "GET"
            r1.setRequestMethod(r3)     // Catch:{ f -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
            r1.setDoOutput(r2)     // Catch:{ f -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
            goto L_0x0024
        L_0x001b:
            java.lang.String r3 = "POST"
            r1.setRequestMethod(r3)     // Catch:{ f -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
            r3 = 1
            r1.setDoOutput(r3)     // Catch:{ f -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
        L_0x0024:
            if (r9 == 0) goto L_0x0034
            java.lang.String r3 = "Content-Type"
            java.lang.String r4 = "application/gzip"
            r1.setRequestProperty(r3, r4)     // Catch:{ f -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
            java.lang.String r3 = "Content-Encoding"
            java.lang.String r4 = "gzip"
            r1.setRequestProperty(r3, r4)     // Catch:{ f -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
        L_0x0034:
            java.net.HttpURLConnection r3 = r5.a((java.net.HttpURLConnection) r1)     // Catch:{ f -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
            r3.connect()     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            if (r8 != 0) goto L_0x005c
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            if (r8 != 0) goto L_0x005c
            java.io.OutputStream r8 = r3.getOutputStream()     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            if (r9 == 0) goto L_0x004f
            java.util.zip.GZIPOutputStream r9 = new java.util.zip.GZIPOutputStream     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r9.<init>(r8)     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r8 = r9
        L_0x004f:
            byte[] r7 = r7.getBytes()     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r8.write(r7)     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r8.flush()     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r8.close()     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
        L_0x005c:
            int r7 = r3.getResponseCode()     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r5.g = r7     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            int r7 = r5.g     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r8 = 200(0xc8, float:2.8E-43)
            if (r7 != r8) goto L_0x00b3
            if (r10 == 0) goto L_0x00ad
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x009e, Exception -> 0x0095 }
            java.io.InputStream r8 = r3.getInputStream()     // Catch:{ IOException -> 0x009e, Exception -> 0x0095 }
            r9 = 8192(0x2000, float:1.14794E-41)
            r7.<init>(r8, r9)     // Catch:{ IOException -> 0x009e, Exception -> 0x0095 }
            r8 = 1024(0x400, float:1.435E-42)
            byte[] r9 = new byte[r8]     // Catch:{ IOException -> 0x0090, Exception -> 0x008d, all -> 0x008a }
        L_0x0079:
            int r0 = r7.read(r9, r2, r8)     // Catch:{ IOException -> 0x0090, Exception -> 0x008d, all -> 0x008a }
            if (r0 <= 0) goto L_0x0083
            r10.write(r9, r2, r0)     // Catch:{ IOException -> 0x0090, Exception -> 0x008d, all -> 0x008a }
            goto L_0x0079
        L_0x0083:
            r10.flush()     // Catch:{ IOException -> 0x0090, Exception -> 0x008d, all -> 0x008a }
            r7.close()     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            goto L_0x00ad
        L_0x008a:
            r8 = move-exception
            r0 = r7
            goto L_0x00a7
        L_0x008d:
            r8 = move-exception
            r0 = r7
            goto L_0x0096
        L_0x0090:
            r8 = move-exception
            r0 = r7
            goto L_0x009f
        L_0x0093:
            r8 = move-exception
            goto L_0x00a7
        L_0x0095:
            r8 = move-exception
        L_0x0096:
            com.mipay.common.exception.c r7 = new com.mipay.common.exception.c     // Catch:{ all -> 0x0093 }
            java.lang.String r9 = "error read/write data"
            r7.<init>(r6, r9, r8)     // Catch:{ all -> 0x0093 }
            throw r7     // Catch:{ all -> 0x0093 }
        L_0x009e:
            r8 = move-exception
        L_0x009f:
            com.mipay.common.exception.c r7 = new com.mipay.common.exception.c     // Catch:{ all -> 0x0093 }
            java.lang.String r9 = "read file stream error "
            r7.<init>(r6, r9, r8)     // Catch:{ all -> 0x0093 }
            throw r7     // Catch:{ all -> 0x0093 }
        L_0x00a7:
            if (r0 == 0) goto L_0x00ac
            r0.close()     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
        L_0x00ac:
            throw r8     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
        L_0x00ad:
            if (r3 == 0) goto L_0x00b2
            r3.disconnect()
        L_0x00b2:
            return
        L_0x00b3:
            int r7 = r5.g     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r8 = 401(0x191, float:5.62E-43)
            if (r7 != r8) goto L_0x00bf
            com.mipay.common.exception.i r7 = new com.mipay.common.exception.i     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r7.<init>()     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            throw r7     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
        L_0x00bf:
            com.mipay.common.exception.h r7 = new com.mipay.common.exception.h     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            int r8 = r5.g     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r7.<init>(r8, r6)     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            throw r7     // Catch:{ f -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
        L_0x00c7:
            r6 = move-exception
            goto L_0x00f1
        L_0x00c9:
            r7 = move-exception
            r0 = r3
            goto L_0x00e2
        L_0x00cc:
            r7 = move-exception
            r0 = r3
            goto L_0x00e9
        L_0x00cf:
            r6 = move-exception
            r0 = r3
            goto L_0x00f0
        L_0x00d2:
            r6 = move-exception
            r3 = r1
            goto L_0x00f1
        L_0x00d5:
            r7 = move-exception
            r0 = r1
            goto L_0x00e2
        L_0x00d8:
            r7 = move-exception
            r0 = r1
            goto L_0x00e9
        L_0x00db:
            r6 = move-exception
            r0 = r1
            goto L_0x00f0
        L_0x00de:
            r6 = move-exception
            r3 = r0
            goto L_0x00f1
        L_0x00e1:
            r7 = move-exception
        L_0x00e2:
            com.mipay.common.exception.c r8 = new com.mipay.common.exception.c     // Catch:{ all -> 0x00de }
            r8.<init>(r6, r7)     // Catch:{ all -> 0x00de }
            throw r8     // Catch:{ all -> 0x00de }
        L_0x00e8:
            r7 = move-exception
        L_0x00e9:
            com.mipay.common.exception.c r8 = new com.mipay.common.exception.c     // Catch:{ all -> 0x00de }
            r8.<init>(r6, r7)     // Catch:{ all -> 0x00de }
            throw r8     // Catch:{ all -> 0x00de }
        L_0x00ef:
            r6 = move-exception
        L_0x00f0:
            throw r6     // Catch:{ all -> 0x00de }
        L_0x00f1:
            if (r3 == 0) goto L_0x00f6
            r3.disconnect()
        L_0x00f6:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mipay.common.data.e.a(java.net.URL, java.lang.String, boolean, boolean, java.io.OutputStream):void");
    }

    private void b(URL url) {
        this.c = false;
        this.d = false;
        if (a(url)) {
            this.f8128a = url;
            return;
        }
        throw new IllegalArgumentException("URL error: " + url);
    }

    /* access modifiers changed from: protected */
    public b a(b bVar) {
        return bVar;
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection a(HttpURLConnection httpURLConnection) {
        return httpURLConnection;
    }

    /* access modifiers changed from: protected */
    public URL a(URL url, b bVar) {
        return url;
    }

    public JSONObject a() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public void a(OutputStream outputStream) throws f {
        StringBuilder sb;
        String str;
        if (this.k != c.PENDING) {
            switch (this.k) {
                case RUNNING:
                    throw new IllegalStateException("Cannot start: the connection is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot start: the connection has already finished.");
            }
        }
        this.k = c.RUNNING;
        if (this.b == null) {
            this.b = new b();
        }
        b a2 = a(this.b);
        String url = this.f8128a.toString();
        if (this.c && !a2.b()) {
            if (TextUtils.isEmpty(this.f8128a.getQuery())) {
                sb = new StringBuilder();
                sb.append(url);
                str = "?";
            } else {
                sb = new StringBuilder();
                sb.append(url);
                str = com.alipay.sdk.sys.a.b;
            }
            sb.append(str);
            sb.append(a2.toString());
            url = sb.toString();
        }
        try {
            a(a(new URL(url), a2), !this.c ? a2.toString() : "", this.c, this.d, outputStream);
            this.k = c.FINISHED;
        } catch (MalformedURLException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public void a(boolean z) {
        this.c = z;
    }

    /* access modifiers changed from: protected */
    public boolean a(URL url) {
        return url != null;
    }

    public String b() {
        return this.e;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public int c() {
        return this.g;
    }

    public b d() {
        if (this.b == null) {
            this.b = new b();
        }
        return this.b;
    }

    public JSONObject e() throws f {
        f();
        try {
            this.f = new JSONObject(this.e);
            return this.f;
        } catch (Exception e2) {
            throw new g((Throwable) e2);
        }
    }

    public String f() throws f {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a((OutputStream) byteArrayOutputStream);
        this.e = byteArrayOutputStream.toString();
        try {
            byteArrayOutputStream.close();
        } catch (IOException unused) {
        }
        return this.e;
    }
}
