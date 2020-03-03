package com.mibi.common.data;

import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
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

public class ConnectionDefault implements Connection {
    private static final String h = "ConnectionDefault";
    private static final int i = 10000;
    private static final int j = 10000;

    /* renamed from: a  reason: collision with root package name */
    protected URL f7512a;
    protected Parameter b;
    protected boolean c;
    protected boolean d;
    protected String e;
    protected JSONObject f;
    protected int g;
    private volatile Status k;

    public enum NetworkError {
        OK,
        NETWORK_ERROR,
        ACCOUNT_CHANGED_ERROR,
        AUTH_ERROR,
        SERVER_ERROR,
        RESULT_ERROR,
        UNKNOWN_ERROR
    }

    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    /* access modifiers changed from: protected */
    public Parameter a(Parameter parameter) {
        return parameter;
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection a(HttpURLConnection httpURLConnection) {
        return httpURLConnection;
    }

    /* access modifiers changed from: protected */
    public URL a(URL url, Parameter parameter) {
        return url;
    }

    /* access modifiers changed from: protected */
    public boolean a(URL url) {
        return url != null;
    }

    ConnectionDefault(String str) {
        this.k = Status.PENDING;
        try {
            b(new URL(str));
        } catch (MalformedURLException unused) {
            throw new IllegalArgumentException("URL error: " + null);
        }
    }

    ConnectionDefault(String str, String str2) {
        this(a(str, str2));
    }

    public static String a(String str, String str2) {
        return Utils.a((CharSequence) str, str2);
    }

    private void b(URL url) {
        this.c = false;
        this.d = false;
        if (a(url)) {
            this.f7512a = url;
            return;
        }
        throw new IllegalArgumentException("URL error: " + url);
    }

    public URL g() {
        return this.f7512a;
    }

    public JSONObject a() {
        return this.f;
    }

    public String b() {
        return this.e;
    }

    public int c() {
        return this.g;
    }

    /* renamed from: h */
    public Parameter d() {
        if (this.b == null) {
            this.b = new Parameter();
        }
        return this.b;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public JSONObject e() throws PaymentException {
        f();
        try {
            this.f = new JSONObject(this.e);
            return this.f;
        } catch (Exception e2) {
            throw new ResultException((Throwable) e2);
        }
    }

    public String f() throws PaymentException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a((OutputStream) byteArrayOutputStream);
        this.e = byteArrayOutputStream.toString();
        try {
            byteArrayOutputStream.close();
        } catch (IOException unused) {
        }
        return this.e;
    }

    /* access modifiers changed from: protected */
    public void a(OutputStream outputStream) throws PaymentException {
        String str;
        if (this.k != Status.PENDING) {
            switch (this.k) {
                case RUNNING:
                    throw new IllegalStateException("Cannot start: the connection is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot start: the connection has already finished.");
            }
        }
        this.k = Status.RUNNING;
        Parameter a2 = a(d());
        String url = this.f7512a.toString();
        if (this.c && !a2.b()) {
            if (TextUtils.isEmpty(this.f7512a.getQuery())) {
                url = url + "?" + a2.toString();
            } else {
                url = url + a.b + a2.toString();
            }
        }
        try {
            URL a3 = a(new URL(url), a2);
            if (!this.c) {
                str = a2.toString();
            } else {
                str = "";
            }
            a(a3, str, this.c, this.d, outputStream);
            this.k = Status.FINISHED;
        } catch (MalformedURLException e2) {
            throw new IllegalStateException(e2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x00a9 A[SYNTHETIC, Splitter:B:51:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x00f3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.net.URL r6, java.lang.String r7, boolean r8, boolean r9, java.io.OutputStream r10) throws com.mibi.common.exception.PaymentException {
        /*
            r5 = this;
            r0 = 0
            java.net.URLConnection r1 = r6.openConnection()     // Catch:{ PaymentException -> 0x00ef, IOException -> 0x00e8, Exception -> 0x00e1 }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ PaymentException -> 0x00ef, IOException -> 0x00e8, Exception -> 0x00e1 }
            r2 = 10000(0x2710, float:1.4013E-41)
            r1.setConnectTimeout(r2)     // Catch:{ PaymentException -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
            r1.setReadTimeout(r2)     // Catch:{ PaymentException -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
            r2 = 0
            if (r8 == 0) goto L_0x001b
            java.lang.String r3 = "GET"
            r1.setRequestMethod(r3)     // Catch:{ PaymentException -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
            r1.setDoOutput(r2)     // Catch:{ PaymentException -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
            goto L_0x0024
        L_0x001b:
            java.lang.String r3 = "POST"
            r1.setRequestMethod(r3)     // Catch:{ PaymentException -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
            r3 = 1
            r1.setDoOutput(r3)     // Catch:{ PaymentException -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
        L_0x0024:
            if (r9 == 0) goto L_0x0034
            java.lang.String r3 = "Content-Type"
            java.lang.String r4 = "application/gzip"
            r1.setRequestProperty(r3, r4)     // Catch:{ PaymentException -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
            java.lang.String r3 = "Content-Encoding"
            java.lang.String r4 = "gzip"
            r1.setRequestProperty(r3, r4)     // Catch:{ PaymentException -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
        L_0x0034:
            java.net.HttpURLConnection r3 = r5.a((java.net.HttpURLConnection) r1)     // Catch:{ PaymentException -> 0x00db, IOException -> 0x00d8, Exception -> 0x00d5, all -> 0x00d2 }
            r3.connect()     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            if (r8 != 0) goto L_0x005c
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            if (r8 != 0) goto L_0x005c
            java.io.OutputStream r8 = r3.getOutputStream()     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            if (r9 == 0) goto L_0x004f
            java.util.zip.GZIPOutputStream r9 = new java.util.zip.GZIPOutputStream     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r9.<init>(r8)     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r8 = r9
        L_0x004f:
            byte[] r7 = r7.getBytes()     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r8.write(r7)     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r8.flush()     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r8.close()     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
        L_0x005c:
            int r7 = r3.getResponseCode()     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r5.g = r7     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            int r7 = r5.g     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
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
            r7.close()     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
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
            com.mibi.common.exception.ConnectionException r7 = new com.mibi.common.exception.ConnectionException     // Catch:{ all -> 0x0093 }
            java.lang.String r9 = "error read/write data"
            r7.<init>(r6, r9, r8)     // Catch:{ all -> 0x0093 }
            throw r7     // Catch:{ all -> 0x0093 }
        L_0x009e:
            r8 = move-exception
        L_0x009f:
            com.mibi.common.exception.ConnectionException r7 = new com.mibi.common.exception.ConnectionException     // Catch:{ all -> 0x0093 }
            java.lang.String r9 = "read file stream error "
            r7.<init>(r6, r9, r8)     // Catch:{ all -> 0x0093 }
            throw r7     // Catch:{ all -> 0x0093 }
        L_0x00a7:
            if (r0 == 0) goto L_0x00ac
            r0.close()     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
        L_0x00ac:
            throw r8     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
        L_0x00ad:
            if (r3 == 0) goto L_0x00b2
            r3.disconnect()
        L_0x00b2:
            return
        L_0x00b3:
            int r7 = r5.g     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r8 = 401(0x191, float:5.62E-43)
            if (r7 != r8) goto L_0x00bf
            com.mibi.common.exception.ServiceTokenExpiredException r7 = new com.mibi.common.exception.ServiceTokenExpiredException     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r7.<init>()     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            throw r7     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
        L_0x00bf:
            com.mibi.common.exception.ServerException r7 = new com.mibi.common.exception.ServerException     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            int r8 = r5.g     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            r7.<init>(r8, r6)     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
            throw r7     // Catch:{ PaymentException -> 0x00cf, IOException -> 0x00cc, Exception -> 0x00c9, all -> 0x00c7 }
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
            com.mibi.common.exception.ConnectionException r8 = new com.mibi.common.exception.ConnectionException     // Catch:{ all -> 0x00de }
            r8.<init>(r6, r7)     // Catch:{ all -> 0x00de }
            throw r8     // Catch:{ all -> 0x00de }
        L_0x00e8:
            r7 = move-exception
        L_0x00e9:
            com.mibi.common.exception.ConnectionException r8 = new com.mibi.common.exception.ConnectionException     // Catch:{ all -> 0x00de }
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
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.ConnectionDefault.a(java.net.URL, java.lang.String, boolean, boolean, java.io.OutputStream):void");
    }

    public class Parameter extends SortedParameter {
        Parameter() {
        }

        public String toString() {
            if (this.c.isEmpty()) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (String str : this.c.keySet()) {
                sb.append(str);
                sb.append("=");
                try {
                    sb.append(URLEncoder.encode(this.c.get(str).toString(), "UTF-8"));
                } catch (UnsupportedEncodingException unused) {
                }
                sb.append(a.b);
            }
            return sb.deleteCharAt(sb.length() - 1).toString();
        }

        public TreeMap<String, Object> a() {
            return this.c;
        }
    }
}
