package com.amap.api.services.a;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.amap.api.services.a.db;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

public class dd {

    /* renamed from: a  reason: collision with root package name */
    private int f4399a;
    private int b;
    private boolean c;
    private SSLContext d;
    private Proxy e;
    private volatile boolean f;
    private long g;
    private long h;
    private String i;
    private a j;
    private db.a k;

    private void a() {
        try {
            this.i = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        } catch (Throwable th) {
            ci.a(th, "ht", "ic");
        }
    }

    dd(int i2, int i3, Proxy proxy, boolean z) {
        this(i2, i3, proxy, z, (db.a) null);
    }

    dd(int i2, int i3, Proxy proxy, boolean z, db.a aVar) {
        this.f = false;
        this.g = -1;
        this.h = 0;
        this.f4399a = i2;
        this.b = i3;
        this.e = proxy;
        this.c = bv.a().b(z);
        if (bv.c()) {
            this.c = false;
        }
        this.k = aVar;
        a();
        if (this.c) {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
                this.d = instance;
            } catch (Throwable th) {
                ci.a(th, "ht", "ne");
            }
        }
        this.j = new a();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00a0 A[SYNTHETIC, Splitter:B:63:0x00a0] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.amap.api.services.a.dh a(java.lang.String r8, boolean r9, java.lang.String r10, java.util.Map<java.lang.String, java.lang.String> r11, java.util.Map<java.lang.String, java.lang.String> r12, boolean r13) throws com.amap.api.services.a.bo {
        /*
            r7 = this;
            r0 = 0
            java.lang.String r12 = a(r12)     // Catch:{ ConnectException -> 0x0096, MalformedURLException -> 0x008d, UnknownHostException -> 0x0084, SocketException -> 0x007c, SocketTimeoutException -> 0x0074, InterruptedIOException -> 0x006b, IOException -> 0x0063, bo -> 0x0061, Throwable -> 0x0054 }
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ ConnectException -> 0x0096, MalformedURLException -> 0x008d, UnknownHostException -> 0x0084, SocketException -> 0x007c, SocketTimeoutException -> 0x0074, InterruptedIOException -> 0x006b, IOException -> 0x0063, bo -> 0x0061, Throwable -> 0x0054 }
            r1.<init>()     // Catch:{ ConnectException -> 0x0096, MalformedURLException -> 0x008d, UnknownHostException -> 0x0084, SocketException -> 0x007c, SocketTimeoutException -> 0x0074, InterruptedIOException -> 0x006b, IOException -> 0x0063, bo -> 0x0061, Throwable -> 0x0054 }
            r1.append(r8)     // Catch:{ ConnectException -> 0x0096, MalformedURLException -> 0x008d, UnknownHostException -> 0x0084, SocketException -> 0x007c, SocketTimeoutException -> 0x0074, InterruptedIOException -> 0x006b, IOException -> 0x0063, bo -> 0x0061, Throwable -> 0x0054 }
            if (r12 == 0) goto L_0x0017
            java.lang.String r8 = "?"
            r1.append(r8)     // Catch:{ ConnectException -> 0x0096, MalformedURLException -> 0x008d, UnknownHostException -> 0x0084, SocketException -> 0x007c, SocketTimeoutException -> 0x0074, InterruptedIOException -> 0x006b, IOException -> 0x0063, bo -> 0x0061, Throwable -> 0x0054 }
            r1.append(r12)     // Catch:{ ConnectException -> 0x0096, MalformedURLException -> 0x008d, UnknownHostException -> 0x0084, SocketException -> 0x007c, SocketTimeoutException -> 0x0074, InterruptedIOException -> 0x006b, IOException -> 0x0063, bo -> 0x0061, Throwable -> 0x0054 }
        L_0x0017:
            java.lang.String r2 = r1.toString()     // Catch:{ ConnectException -> 0x0096, MalformedURLException -> 0x008d, UnknownHostException -> 0x0084, SocketException -> 0x007c, SocketTimeoutException -> 0x0074, InterruptedIOException -> 0x006b, IOException -> 0x0063, bo -> 0x0061, Throwable -> 0x0054 }
            r6 = 0
            r1 = r7
            r3 = r9
            r4 = r10
            r5 = r11
            java.net.HttpURLConnection r8 = r1.a(r2, r3, r4, r5, r6)     // Catch:{ ConnectException -> 0x0096, MalformedURLException -> 0x008d, UnknownHostException -> 0x0084, SocketException -> 0x007c, SocketTimeoutException -> 0x0074, InterruptedIOException -> 0x006b, IOException -> 0x0063, bo -> 0x0061, Throwable -> 0x0054 }
            com.amap.api.services.a.dh r9 = r7.a((java.net.HttpURLConnection) r8, (boolean) r13)     // Catch:{ ConnectException -> 0x0050, MalformedURLException -> 0x004e, UnknownHostException -> 0x004c, SocketException -> 0x004a, SocketTimeoutException -> 0x0048, InterruptedIOException -> 0x0046, IOException -> 0x0044, bo -> 0x0040, Throwable -> 0x003c, all -> 0x0037 }
            if (r8 == 0) goto L_0x0036
            r8.disconnect()     // Catch:{ Throwable -> 0x002e }
            goto L_0x0036
        L_0x002e:
            r8 = move-exception
            java.lang.String r10 = "ht"
            java.lang.String r11 = "mgr"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r8, (java.lang.String) r10, (java.lang.String) r11)
        L_0x0036:
            return r9
        L_0x0037:
            r9 = move-exception
            r0 = r8
            r8 = r9
            goto L_0x009e
        L_0x003c:
            r9 = move-exception
            r0 = r8
            r8 = r9
            goto L_0x0055
        L_0x0040:
            r9 = move-exception
            r0 = r8
            r8 = r9
            goto L_0x0062
        L_0x0044:
            r0 = r8
            goto L_0x0063
        L_0x0046:
            r0 = r8
            goto L_0x006b
        L_0x0048:
            r0 = r8
            goto L_0x0074
        L_0x004a:
            r0 = r8
            goto L_0x007c
        L_0x004c:
            r0 = r8
            goto L_0x0084
        L_0x004e:
            r0 = r8
            goto L_0x008d
        L_0x0050:
            r0 = r8
            goto L_0x0096
        L_0x0052:
            r8 = move-exception
            goto L_0x009e
        L_0x0054:
            r8 = move-exception
        L_0x0055:
            r8.printStackTrace()     // Catch:{ all -> 0x0052 }
            com.amap.api.services.a.bo r8 = new com.amap.api.services.a.bo     // Catch:{ all -> 0x0052 }
            java.lang.String r9 = "未知的错误"
            r8.<init>(r9)     // Catch:{ all -> 0x0052 }
            throw r8     // Catch:{ all -> 0x0052 }
        L_0x0061:
            r8 = move-exception
        L_0x0062:
            throw r8     // Catch:{ all -> 0x0052 }
        L_0x0063:
            com.amap.api.services.a.bo r8 = new com.amap.api.services.a.bo     // Catch:{ all -> 0x0052 }
            java.lang.String r9 = "IO 操作异常 - IOException"
            r8.<init>(r9)     // Catch:{ all -> 0x0052 }
            throw r8     // Catch:{ all -> 0x0052 }
        L_0x006b:
            com.amap.api.services.a.bo r8 = new com.amap.api.services.a.bo     // Catch:{ all -> 0x0052 }
            java.lang.String r9 = "未知的错误"
            r8.<init>(r9)     // Catch:{ all -> 0x0052 }
            throw r8     // Catch:{ all -> 0x0052 }
        L_0x0074:
            com.amap.api.services.a.bo r8 = new com.amap.api.services.a.bo     // Catch:{ all -> 0x0052 }
            java.lang.String r9 = "socket 连接超时 - SocketTimeoutException"
            r8.<init>(r9)     // Catch:{ all -> 0x0052 }
            throw r8     // Catch:{ all -> 0x0052 }
        L_0x007c:
            com.amap.api.services.a.bo r8 = new com.amap.api.services.a.bo     // Catch:{ all -> 0x0052 }
            java.lang.String r9 = "socket 连接异常 - SocketException"
            r8.<init>(r9)     // Catch:{ all -> 0x0052 }
            throw r8     // Catch:{ all -> 0x0052 }
        L_0x0084:
            com.amap.api.services.a.bo r8 = new com.amap.api.services.a.bo     // Catch:{ all -> 0x0052 }
            java.lang.String r9 = "未知主机 - UnKnowHostException"
            r8.<init>(r9)     // Catch:{ all -> 0x0052 }
            throw r8     // Catch:{ all -> 0x0052 }
        L_0x008d:
            com.amap.api.services.a.bo r8 = new com.amap.api.services.a.bo     // Catch:{ all -> 0x0052 }
            java.lang.String r9 = "url异常 - MalformedURLException"
            r8.<init>(r9)     // Catch:{ all -> 0x0052 }
            throw r8     // Catch:{ all -> 0x0052 }
        L_0x0096:
            com.amap.api.services.a.bo r8 = new com.amap.api.services.a.bo     // Catch:{ all -> 0x0052 }
            java.lang.String r9 = "http连接失败 - ConnectionException"
            r8.<init>(r9)     // Catch:{ all -> 0x0052 }
            throw r8     // Catch:{ all -> 0x0052 }
        L_0x009e:
            if (r0 == 0) goto L_0x00ac
            r0.disconnect()     // Catch:{ Throwable -> 0x00a4 }
            goto L_0x00ac
        L_0x00a4:
            r9 = move-exception
            java.lang.String r10 = "ht"
            java.lang.String r11 = "mgr"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r9, (java.lang.String) r10, (java.lang.String) r11)
        L_0x00ac:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.dd.a(java.lang.String, boolean, java.lang.String, java.util.Map, java.util.Map, boolean):com.amap.api.services.a.dh");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00c2 A[SYNTHETIC, Splitter:B:66:0x00c2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.amap.api.services.a.dh a(java.lang.String r8, boolean r9, java.lang.String r10, java.util.Map<java.lang.String, java.lang.String> r11, byte[] r12, boolean r13) throws com.amap.api.services.a.bo {
        /*
            r7 = this;
            r5 = 1
            r6 = 0
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            java.net.HttpURLConnection r8 = r0.a(r1, r2, r3, r4, r5)     // Catch:{ ConnectException -> 0x00b4, MalformedURLException -> 0x00a7, UnknownHostException -> 0x009a, SocketException -> 0x008e, SocketTimeoutException -> 0x0082, InterruptedIOException -> 0x0079, IOException -> 0x006d, bo -> 0x0064, Throwable -> 0x0053 }
            if (r12 == 0) goto L_0x003e
            int r9 = r12.length     // Catch:{ ConnectException -> 0x003b, MalformedURLException -> 0x0038, UnknownHostException -> 0x0035, SocketException -> 0x0032, SocketTimeoutException -> 0x002f, InterruptedIOException -> 0x002d, IOException -> 0x002a, bo -> 0x0027, Throwable -> 0x0024, all -> 0x0020 }
            if (r9 <= 0) goto L_0x003e
            java.io.DataOutputStream r9 = new java.io.DataOutputStream     // Catch:{ ConnectException -> 0x003b, MalformedURLException -> 0x0038, UnknownHostException -> 0x0035, SocketException -> 0x0032, SocketTimeoutException -> 0x002f, InterruptedIOException -> 0x002d, IOException -> 0x002a, bo -> 0x0027, Throwable -> 0x0024, all -> 0x0020 }
            java.io.OutputStream r10 = r8.getOutputStream()     // Catch:{ ConnectException -> 0x003b, MalformedURLException -> 0x0038, UnknownHostException -> 0x0035, SocketException -> 0x0032, SocketTimeoutException -> 0x002f, InterruptedIOException -> 0x002d, IOException -> 0x002a, bo -> 0x0027, Throwable -> 0x0024, all -> 0x0020 }
            r9.<init>(r10)     // Catch:{ ConnectException -> 0x003b, MalformedURLException -> 0x0038, UnknownHostException -> 0x0035, SocketException -> 0x0032, SocketTimeoutException -> 0x002f, InterruptedIOException -> 0x002d, IOException -> 0x002a, bo -> 0x0027, Throwable -> 0x0024, all -> 0x0020 }
            r9.write(r12)     // Catch:{ ConnectException -> 0x003b, MalformedURLException -> 0x0038, UnknownHostException -> 0x0035, SocketException -> 0x0032, SocketTimeoutException -> 0x002f, InterruptedIOException -> 0x002d, IOException -> 0x002a, bo -> 0x0027, Throwable -> 0x0024, all -> 0x0020 }
            r9.close()     // Catch:{ ConnectException -> 0x003b, MalformedURLException -> 0x0038, UnknownHostException -> 0x0035, SocketException -> 0x0032, SocketTimeoutException -> 0x002f, InterruptedIOException -> 0x002d, IOException -> 0x002a, bo -> 0x0027, Throwable -> 0x0024, all -> 0x0020 }
            goto L_0x003e
        L_0x0020:
            r9 = move-exception
            r6 = r8
            goto L_0x00c0
        L_0x0024:
            r9 = move-exception
            r6 = r8
            goto L_0x0054
        L_0x0027:
            r9 = move-exception
            r6 = r8
            goto L_0x0065
        L_0x002a:
            r9 = move-exception
            r6 = r8
            goto L_0x006e
        L_0x002d:
            r6 = r8
            goto L_0x0079
        L_0x002f:
            r9 = move-exception
            r6 = r8
            goto L_0x0083
        L_0x0032:
            r9 = move-exception
            r6 = r8
            goto L_0x008f
        L_0x0035:
            r9 = move-exception
            r6 = r8
            goto L_0x009b
        L_0x0038:
            r9 = move-exception
            r6 = r8
            goto L_0x00a8
        L_0x003b:
            r9 = move-exception
            r6 = r8
            goto L_0x00b5
        L_0x003e:
            com.amap.api.services.a.dh r9 = r7.a((java.net.HttpURLConnection) r8, (boolean) r13)     // Catch:{ ConnectException -> 0x003b, MalformedURLException -> 0x0038, UnknownHostException -> 0x0035, SocketException -> 0x0032, SocketTimeoutException -> 0x002f, InterruptedIOException -> 0x002d, IOException -> 0x002a, bo -> 0x0027, Throwable -> 0x0024, all -> 0x0020 }
            if (r8 == 0) goto L_0x0050
            r8.disconnect()     // Catch:{ Throwable -> 0x0048 }
            goto L_0x0050
        L_0x0048:
            r8 = move-exception
            java.lang.String r10 = "ht"
            java.lang.String r11 = "mPt"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r8, (java.lang.String) r10, (java.lang.String) r11)
        L_0x0050:
            return r9
        L_0x0051:
            r9 = move-exception
            goto L_0x00c0
        L_0x0053:
            r9 = move-exception
        L_0x0054:
            java.lang.String r8 = "ht"
            java.lang.String r10 = "mPt"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r9, (java.lang.String) r8, (java.lang.String) r10)     // Catch:{ all -> 0x0051 }
            com.amap.api.services.a.bo r8 = new com.amap.api.services.a.bo     // Catch:{ all -> 0x0051 }
            java.lang.String r9 = "未知的错误"
            r8.<init>(r9)     // Catch:{ all -> 0x0051 }
            throw r8     // Catch:{ all -> 0x0051 }
        L_0x0064:
            r9 = move-exception
        L_0x0065:
            java.lang.String r8 = "ht"
            java.lang.String r10 = "mPt"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r9, (java.lang.String) r8, (java.lang.String) r10)     // Catch:{ all -> 0x0051 }
            throw r9     // Catch:{ all -> 0x0051 }
        L_0x006d:
            r9 = move-exception
        L_0x006e:
            r9.printStackTrace()     // Catch:{ all -> 0x0051 }
            com.amap.api.services.a.bo r8 = new com.amap.api.services.a.bo     // Catch:{ all -> 0x0051 }
            java.lang.String r9 = "IO 操作异常 - IOException"
            r8.<init>(r9)     // Catch:{ all -> 0x0051 }
            throw r8     // Catch:{ all -> 0x0051 }
        L_0x0079:
            com.amap.api.services.a.bo r8 = new com.amap.api.services.a.bo     // Catch:{ all -> 0x0051 }
            java.lang.String r9 = "未知的错误"
            r8.<init>(r9)     // Catch:{ all -> 0x0051 }
            throw r8     // Catch:{ all -> 0x0051 }
        L_0x0082:
            r9 = move-exception
        L_0x0083:
            r9.printStackTrace()     // Catch:{ all -> 0x0051 }
            com.amap.api.services.a.bo r8 = new com.amap.api.services.a.bo     // Catch:{ all -> 0x0051 }
            java.lang.String r9 = "socket 连接超时 - SocketTimeoutException"
            r8.<init>(r9)     // Catch:{ all -> 0x0051 }
            throw r8     // Catch:{ all -> 0x0051 }
        L_0x008e:
            r9 = move-exception
        L_0x008f:
            r9.printStackTrace()     // Catch:{ all -> 0x0051 }
            com.amap.api.services.a.bo r8 = new com.amap.api.services.a.bo     // Catch:{ all -> 0x0051 }
            java.lang.String r9 = "socket 连接异常 - SocketException"
            r8.<init>(r9)     // Catch:{ all -> 0x0051 }
            throw r8     // Catch:{ all -> 0x0051 }
        L_0x009a:
            r9 = move-exception
        L_0x009b:
            r9.printStackTrace()     // Catch:{ all -> 0x0051 }
            com.amap.api.services.a.bo r8 = new com.amap.api.services.a.bo     // Catch:{ all -> 0x0051 }
            java.lang.String r9 = "未知主机 - UnKnowHostException"
            r8.<init>(r9)     // Catch:{ all -> 0x0051 }
            throw r8     // Catch:{ all -> 0x0051 }
        L_0x00a7:
            r9 = move-exception
        L_0x00a8:
            r9.printStackTrace()     // Catch:{ all -> 0x0051 }
            com.amap.api.services.a.bo r8 = new com.amap.api.services.a.bo     // Catch:{ all -> 0x0051 }
            java.lang.String r9 = "url异常 - MalformedURLException"
            r8.<init>(r9)     // Catch:{ all -> 0x0051 }
            throw r8     // Catch:{ all -> 0x0051 }
        L_0x00b4:
            r9 = move-exception
        L_0x00b5:
            r9.printStackTrace()     // Catch:{ all -> 0x0051 }
            com.amap.api.services.a.bo r8 = new com.amap.api.services.a.bo     // Catch:{ all -> 0x0051 }
            java.lang.String r9 = "http连接失败 - ConnectionException"
            r8.<init>(r9)     // Catch:{ all -> 0x0051 }
            throw r8     // Catch:{ all -> 0x0051 }
        L_0x00c0:
            if (r6 == 0) goto L_0x00ce
            r6.disconnect()     // Catch:{ Throwable -> 0x00c6 }
            goto L_0x00ce
        L_0x00c6:
            r8 = move-exception
            java.lang.String r10 = "ht"
            java.lang.String r11 = "mPt"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r8, (java.lang.String) r10, (java.lang.String) r11)
        L_0x00ce:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.dd.a(java.lang.String, boolean, java.lang.String, java.util.Map, byte[], boolean):com.amap.api.services.a.dh");
    }

    private String a(int i2, String str, Map<String, String> map) {
        String str2 = "";
        if (i2 == 1) {
            str2 = db.b;
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        Uri parse = Uri.parse(str);
        String host = parse.getHost();
        String uri = parse.buildUpon().encodedAuthority(str2).build().toString();
        if (map != null) {
            map.put("targetHost", host);
        }
        if (this.c) {
            this.j.b(str2);
        }
        return uri;
    }

    /* access modifiers changed from: package-private */
    public HttpURLConnection a(String str, boolean z, String str2, Map<String, String> map, boolean z2) throws IOException {
        HttpURLConnection httpURLConnection;
        bt.b();
        if (map == null) {
            map = new HashMap<>();
        }
        b a2 = this.j.a();
        if (z && !TextUtils.isEmpty(str2)) {
            a2 = this.j.a(str2);
        }
        String a3 = a(db.f4397a, str, map);
        if (this.c) {
            a3 = bv.a(a3);
        }
        URL url = new URL(a3);
        URLConnection a4 = this.k != null ? this.k.a(this.e, url) : null;
        if (a4 == null) {
            if (this.e != null) {
                a4 = url.openConnection(this.e);
            } else {
                a4 = url.openConnection();
            }
        }
        if (this.c) {
            httpURLConnection = (HttpsURLConnection) a4;
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            httpsURLConnection.setSSLSocketFactory(this.d.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(a2);
        } else {
            httpURLConnection = (HttpURLConnection) a4;
        }
        if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
            httpURLConnection.setRequestProperty("Connection", "close");
        }
        a(map, httpURLConnection);
        if (z2) {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
        } else {
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
        }
        return httpURLConnection;
    }

    /* JADX WARNING: Removed duplicated region for block: B:75:0x011d A[SYNTHETIC, Splitter:B:75:0x011d] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x012b A[SYNTHETIC, Splitter:B:80:0x012b] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0139 A[SYNTHETIC, Splitter:B:85:0x0139] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0147 A[SYNTHETIC, Splitter:B:90:0x0147] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.amap.api.services.a.dh a(java.net.HttpURLConnection r11, boolean r12) throws com.amap.api.services.a.bo, java.io.IOException {
        /*
            r10 = this;
            java.lang.String r0 = ""
            r1 = 0
            r11.connect()     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            java.util.Map r2 = r11.getHeaderFields()     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            int r3 = r11.getResponseCode()     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            r4 = 0
            if (r2 == 0) goto L_0x0028
            java.lang.String r5 = "gsid"
            java.lang.Object r5 = r2.get(r5)     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            java.util.List r5 = (java.util.List) r5     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            if (r5 == 0) goto L_0x0028
            int r6 = r5.size()     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            if (r6 <= 0) goto L_0x0028
            java.lang.Object r5 = r5.get(r4)     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            r0 = r5
        L_0x0028:
            r5 = 200(0xc8, float:2.8E-43)
            if (r3 != r5) goto L_0x00cd
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            r3.<init>()     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            java.io.InputStream r11 = r11.getInputStream()     // Catch:{ IOException -> 0x00c8, all -> 0x00c2 }
            java.io.PushbackInputStream r5 = new java.io.PushbackInputStream     // Catch:{ IOException -> 0x00c0, all -> 0x00b7 }
            r6 = 2
            r5.<init>(r11, r6)     // Catch:{ IOException -> 0x00c0, all -> 0x00b7 }
            byte[] r6 = new byte[r6]     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            r5.read(r6)     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            r5.unread(r6)     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            byte r7 = r6[r4]     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            r8 = 31
            if (r7 != r8) goto L_0x0059
            r7 = 1
            byte r6 = r6[r7]     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            r7 = -117(0xffffffffffffff8b, float:NaN)
            if (r6 != r7) goto L_0x0059
            if (r12 != 0) goto L_0x0059
            java.util.zip.GZIPInputStream r12 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            r12.<init>(r5)     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            r1 = r12
            goto L_0x005a
        L_0x0059:
            r1 = r5
        L_0x005a:
            r12 = 1024(0x400, float:1.435E-42)
            byte[] r12 = new byte[r12]     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
        L_0x005e:
            int r6 = r1.read(r12)     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            r7 = -1
            if (r6 == r7) goto L_0x0069
            r3.write(r12, r4, r6)     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            goto L_0x005e
        L_0x0069:
            com.amap.api.services.a.cl.b()     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            com.amap.api.services.a.dh r12 = new com.amap.api.services.a.dh     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            r12.<init>()     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            byte[] r4 = r3.toByteArray()     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            r12.f4403a = r4     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            r12.b = r2     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            java.lang.String r2 = r10.i     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            r12.c = r2     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            r12.d = r0     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            r3.close()     // Catch:{ Throwable -> 0x0083 }
            goto L_0x008b
        L_0x0083:
            r0 = move-exception
            java.lang.String r2 = "ht"
            java.lang.String r3 = "par"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r0, (java.lang.String) r2, (java.lang.String) r3)
        L_0x008b:
            if (r11 == 0) goto L_0x0099
            r11.close()     // Catch:{ Throwable -> 0x0091 }
            goto L_0x0099
        L_0x0091:
            r11 = move-exception
            java.lang.String r0 = "ht"
            java.lang.String r2 = "par"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r11, (java.lang.String) r0, (java.lang.String) r2)
        L_0x0099:
            r5.close()     // Catch:{ Throwable -> 0x009d }
            goto L_0x00a5
        L_0x009d:
            r11 = move-exception
            java.lang.String r0 = "ht"
            java.lang.String r2 = "par"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r11, (java.lang.String) r0, (java.lang.String) r2)
        L_0x00a5:
            r1.close()     // Catch:{ Throwable -> 0x00a9 }
            goto L_0x00b1
        L_0x00a9:
            r11 = move-exception
            java.lang.String r0 = "ht"
            java.lang.String r1 = "par"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r11, (java.lang.String) r0, (java.lang.String) r1)
        L_0x00b1:
            return r12
        L_0x00b2:
            r12 = move-exception
            r0 = r1
            goto L_0x00ba
        L_0x00b5:
            r12 = r1
            goto L_0x00cb
        L_0x00b7:
            r12 = move-exception
            r0 = r1
            r5 = r0
        L_0x00ba:
            r1 = r3
            r9 = r12
            r12 = r11
            r11 = r9
            goto L_0x011b
        L_0x00c0:
            r12 = r1
            goto L_0x00ca
        L_0x00c2:
            r11 = move-exception
            r12 = r1
            r0 = r12
            r5 = r0
            r1 = r3
            goto L_0x011b
        L_0x00c8:
            r11 = r1
            r12 = r11
        L_0x00ca:
            r5 = r12
        L_0x00cb:
            r1 = r3
            goto L_0x010e
        L_0x00cd:
            com.amap.api.services.a.bo r12 = new com.amap.api.services.a.bo     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            r2.<init>()     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            java.lang.String r4 = "网络异常原因："
            r2.append(r4)     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            java.lang.String r11 = r11.getResponseMessage()     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            r2.append(r11)     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            java.lang.String r11 = " 网络异常状态码："
            r2.append(r11)     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            r2.append(r3)     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            java.lang.String r11 = "  "
            r2.append(r11)     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            r2.append(r0)     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            java.lang.String r11 = " "
            r2.append(r11)     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            java.lang.String r11 = r10.i     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            r2.append(r11)     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            java.lang.String r11 = r2.toString()     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            r12.<init>(r11, r0)     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            r12.a((int) r3)     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
            throw r12     // Catch:{ IOException -> 0x010b, all -> 0x0106 }
        L_0x0106:
            r11 = move-exception
            r12 = r1
            r0 = r12
            r5 = r0
            goto L_0x011b
        L_0x010b:
            r11 = r1
            r12 = r11
            r5 = r12
        L_0x010e:
            com.amap.api.services.a.bo r2 = new com.amap.api.services.a.bo     // Catch:{ all -> 0x0116 }
            java.lang.String r3 = "IO 操作异常 - IOException"
            r2.<init>(r3, r0)     // Catch:{ all -> 0x0116 }
            throw r2     // Catch:{ all -> 0x0116 }
        L_0x0116:
            r0 = move-exception
            r9 = r12
            r12 = r11
            r11 = r0
            r0 = r9
        L_0x011b:
            if (r1 == 0) goto L_0x0129
            r1.close()     // Catch:{ Throwable -> 0x0121 }
            goto L_0x0129
        L_0x0121:
            r1 = move-exception
            java.lang.String r2 = "ht"
            java.lang.String r3 = "par"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r1, (java.lang.String) r2, (java.lang.String) r3)
        L_0x0129:
            if (r12 == 0) goto L_0x0137
            r12.close()     // Catch:{ Throwable -> 0x012f }
            goto L_0x0137
        L_0x012f:
            r12 = move-exception
            java.lang.String r1 = "ht"
            java.lang.String r2 = "par"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r12, (java.lang.String) r1, (java.lang.String) r2)
        L_0x0137:
            if (r5 == 0) goto L_0x0145
            r5.close()     // Catch:{ Throwable -> 0x013d }
            goto L_0x0145
        L_0x013d:
            r12 = move-exception
            java.lang.String r1 = "ht"
            java.lang.String r2 = "par"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r12, (java.lang.String) r1, (java.lang.String) r2)
        L_0x0145:
            if (r0 == 0) goto L_0x0153
            r0.close()     // Catch:{ Throwable -> 0x014b }
            goto L_0x0153
        L_0x014b:
            r12 = move-exception
            java.lang.String r0 = "ht"
            java.lang.String r1 = "par"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r12, (java.lang.String) r0, (java.lang.String) r1)
        L_0x0153:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.dd.a(java.net.HttpURLConnection, boolean):com.amap.api.services.a.dh");
    }

    private void a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null) {
            for (String next : map.keySet()) {
                httpURLConnection.addRequestProperty(next, map.get(next));
            }
        }
        try {
            httpURLConnection.addRequestProperty("csid", this.i);
        } catch (Throwable th) {
            ci.a(th, "ht", "adh");
        }
        httpURLConnection.setConnectTimeout(this.f4399a);
        httpURLConnection.setReadTimeout(this.b);
    }

    private static class b implements HostnameVerifier {

        /* renamed from: a  reason: collision with root package name */
        private String f4401a;
        private String b;

        private b() {
        }

        public void a(String str) {
            this.f4401a = str;
        }

        public void b(String str) {
            this.b = str;
        }

        public String a() {
            return this.b;
        }

        public boolean verify(String str, SSLSession sSLSession) {
            HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
            if (!TextUtils.isEmpty(this.f4401a)) {
                return this.f4401a.equals(str);
            }
            if (!TextUtils.isEmpty(this.b)) {
                return defaultHostnameVerifier.verify(this.b, sSLSession);
            }
            return defaultHostnameVerifier.verify(str, sSLSession);
        }
    }

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private Vector<b> f4400a;
        private volatile b b;

        private a() {
            this.f4400a = new Vector<>();
            this.b = new b();
        }

        public b a() {
            return this.b;
        }

        public b a(String str) {
            if (TextUtils.isEmpty(str)) {
                return this.b;
            }
            for (int i = 0; i < this.f4400a.size(); i++) {
                b bVar = this.f4400a.get(i);
                if (bVar != null && bVar.a().equals(str)) {
                    return bVar;
                }
            }
            b bVar2 = new b();
            bVar2.b(str);
            this.f4400a.add(bVar2);
            return bVar2;
        }

        public void b(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.b.a(str);
            }
        }
    }

    static String a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            String str2 = (String) next.getValue();
            if (str2 == null) {
                str2 = "";
            }
            if (sb.length() > 0) {
                sb.append(com.alipay.sdk.sys.a.b);
            }
            sb.append(URLEncoder.encode(str));
            sb.append("=");
            sb.append(URLEncoder.encode(str2));
        }
        return sb.toString();
    }
}
