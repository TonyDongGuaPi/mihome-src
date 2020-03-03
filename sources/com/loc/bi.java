package com.loc;

import android.text.TextUtils;
import com.loc.bg;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

public final class bi {

    /* renamed from: a  reason: collision with root package name */
    private int f6510a;
    private int b;
    private boolean c;
    private SSLContext d;
    private Proxy e;
    private volatile boolean f;
    private long g;
    private long h;
    private String i;
    private a j;
    private bg.a k;

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private Vector<b> f6511a;
        private volatile b b;

        private a() {
            this.f6511a = new Vector<>();
            this.b = new b((byte) 0);
        }

        /* synthetic */ a(byte b2) {
            this();
        }

        public final b a() {
            return this.b;
        }

        public final b a(String str) {
            if (TextUtils.isEmpty(str)) {
                return this.b;
            }
            for (int i = 0; i < this.f6511a.size(); i++) {
                b bVar = this.f6511a.get(i);
                if (bVar != null && bVar.a().equals(str)) {
                    return bVar;
                }
            }
            b bVar2 = new b((byte) 0);
            bVar2.b(str);
            this.f6511a.add(bVar2);
            return bVar2;
        }

        public final void b(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.b.a(str);
            }
        }
    }

    private static class b implements HostnameVerifier {

        /* renamed from: a  reason: collision with root package name */
        private String f6512a;
        private String b;

        private b() {
        }

        /* synthetic */ b(byte b2) {
            this();
        }

        public final String a() {
            return this.b;
        }

        public final void a(String str) {
            this.f6512a = str;
        }

        public final void b(String str) {
            this.b = str;
        }

        public final boolean verify(String str, SSLSession sSLSession) {
            HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
            return !TextUtils.isEmpty(this.f6512a) ? this.f6512a.equals(str) : !TextUtils.isEmpty(this.b) ? defaultHostnameVerifier.verify(this.b, sSLSession) : defaultHostnameVerifier.verify(str, sSLSession);
        }
    }

    bi(int i2, int i3, Proxy proxy, boolean z) {
        this(i2, i3, proxy, z, (byte) 0);
    }

    private bi(int i2, int i3, Proxy proxy, boolean z, byte b2) {
        this.f = false;
        this.g = -1;
        this.h = 0;
        this.f6510a = i2;
        this.b = i3;
        this.e = proxy;
        this.c = z.a().b(z);
        if (z.b()) {
            this.c = false;
        }
        this.k = null;
        try {
            this.i = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        } catch (Throwable th) {
            an.a(th, "ht", "ic");
        }
        if (this.c) {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
                this.d = instance;
            } catch (Throwable th2) {
                an.a(th2, "ht", "ne");
            }
        }
        this.j = new a((byte) 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:75:0x011a A[SYNTHETIC, Splitter:B:75:0x011a] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0128 A[SYNTHETIC, Splitter:B:80:0x0128] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0136 A[SYNTHETIC, Splitter:B:85:0x0136] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0144 A[SYNTHETIC, Splitter:B:90:0x0144] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.loc.bk a(java.net.HttpURLConnection r11, boolean r12) throws com.loc.t, java.io.IOException {
        /*
            r10 = this;
            java.lang.String r0 = ""
            r1 = 0
            r11.connect()     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            java.util.Map r2 = r11.getHeaderFields()     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            int r3 = r11.getResponseCode()     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            r4 = 0
            if (r2 == 0) goto L_0x0028
            java.lang.String r5 = "gsid"
            java.lang.Object r5 = r2.get(r5)     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            java.util.List r5 = (java.util.List) r5     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            if (r5 == 0) goto L_0x0028
            int r6 = r5.size()     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            if (r6 <= 0) goto L_0x0028
            java.lang.Object r5 = r5.get(r4)     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            r0 = r5
        L_0x0028:
            r5 = 200(0xc8, float:2.8E-43)
            if (r3 != r5) goto L_0x00cd
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            r3.<init>()     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
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
            com.loc.aq.c()     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            com.loc.bk r12 = new com.loc.bk     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            r12.<init>()     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            byte[] r4 = r3.toByteArray()     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
            r12.f6513a = r4     // Catch:{ IOException -> 0x00b5, all -> 0x00b2 }
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
            com.loc.an.a((java.lang.Throwable) r0, (java.lang.String) r2, (java.lang.String) r3)
        L_0x008b:
            if (r11 == 0) goto L_0x0099
            r11.close()     // Catch:{ Throwable -> 0x0091 }
            goto L_0x0099
        L_0x0091:
            r11 = move-exception
            java.lang.String r0 = "ht"
            java.lang.String r2 = "par"
            com.loc.an.a((java.lang.Throwable) r11, (java.lang.String) r0, (java.lang.String) r2)
        L_0x0099:
            r5.close()     // Catch:{ Throwable -> 0x009d }
            goto L_0x00a5
        L_0x009d:
            r11 = move-exception
            java.lang.String r0 = "ht"
            java.lang.String r2 = "par"
            com.loc.an.a((java.lang.Throwable) r11, (java.lang.String) r0, (java.lang.String) r2)
        L_0x00a5:
            r1.close()     // Catch:{ Throwable -> 0x00a9 }
            goto L_0x00b1
        L_0x00a9:
            r11 = move-exception
            java.lang.String r0 = "ht"
            java.lang.String r1 = "par"
            com.loc.an.a((java.lang.Throwable) r11, (java.lang.String) r0, (java.lang.String) r1)
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
            goto L_0x0118
        L_0x00c0:
            r12 = r1
            goto L_0x00ca
        L_0x00c2:
            r11 = move-exception
            r12 = r1
            r0 = r12
            r5 = r0
            r1 = r3
            goto L_0x0118
        L_0x00c8:
            r11 = r1
            r12 = r11
        L_0x00ca:
            r5 = r12
        L_0x00cb:
            r1 = r3
            goto L_0x010b
        L_0x00cd:
            com.loc.t r12 = new com.loc.t     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            java.lang.String r4 = "网络异常原因："
            r2.<init>(r4)     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            java.lang.String r11 = r11.getResponseMessage()     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            r2.append(r11)     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            java.lang.String r11 = " 网络异常状态码："
            r2.append(r11)     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            r2.append(r3)     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            java.lang.String r11 = "  "
            r2.append(r11)     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            r2.append(r0)     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            java.lang.String r11 = " "
            r2.append(r11)     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            java.lang.String r11 = r10.i     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            r2.append(r11)     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            java.lang.String r11 = r2.toString()     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            r12.<init>(r11, r0)     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            r12.a(r3)     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            throw r12     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
        L_0x0103:
            r11 = move-exception
            r12 = r1
            r0 = r12
            r5 = r0
            goto L_0x0118
        L_0x0108:
            r11 = r1
            r12 = r11
            r5 = r12
        L_0x010b:
            com.loc.t r2 = new com.loc.t     // Catch:{ all -> 0x0113 }
            java.lang.String r3 = "IO 操作异常 - IOException"
            r2.<init>(r3, r0)     // Catch:{ all -> 0x0113 }
            throw r2     // Catch:{ all -> 0x0113 }
        L_0x0113:
            r0 = move-exception
            r9 = r12
            r12 = r11
            r11 = r0
            r0 = r9
        L_0x0118:
            if (r1 == 0) goto L_0x0126
            r1.close()     // Catch:{ Throwable -> 0x011e }
            goto L_0x0126
        L_0x011e:
            r1 = move-exception
            java.lang.String r2 = "ht"
            java.lang.String r3 = "par"
            com.loc.an.a((java.lang.Throwable) r1, (java.lang.String) r2, (java.lang.String) r3)
        L_0x0126:
            if (r12 == 0) goto L_0x0134
            r12.close()     // Catch:{ Throwable -> 0x012c }
            goto L_0x0134
        L_0x012c:
            r12 = move-exception
            java.lang.String r1 = "ht"
            java.lang.String r2 = "par"
            com.loc.an.a((java.lang.Throwable) r12, (java.lang.String) r1, (java.lang.String) r2)
        L_0x0134:
            if (r5 == 0) goto L_0x0142
            r5.close()     // Catch:{ Throwable -> 0x013a }
            goto L_0x0142
        L_0x013a:
            r12 = move-exception
            java.lang.String r1 = "ht"
            java.lang.String r2 = "par"
            com.loc.an.a((java.lang.Throwable) r12, (java.lang.String) r1, (java.lang.String) r2)
        L_0x0142:
            if (r0 == 0) goto L_0x0150
            r0.close()     // Catch:{ Throwable -> 0x0148 }
            goto L_0x0150
        L_0x0148:
            r12 = move-exception
            java.lang.String r0 = "ht"
            java.lang.String r1 = "par"
            com.loc.an.a((java.lang.Throwable) r12, (java.lang.String) r0, (java.lang.String) r1)
        L_0x0150:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bi.a(java.net.HttpURLConnection, boolean):com.loc.bk");
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

    private void a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null) {
            for (String next : map.keySet()) {
                httpURLConnection.addRequestProperty(next, map.get(next));
            }
        }
        try {
            httpURLConnection.addRequestProperty("csid", this.i);
        } catch (Throwable th) {
            an.a(th, "ht", "adh");
        }
        httpURLConnection.setConnectTimeout(this.f6510a);
        httpURLConnection.setReadTimeout(this.b);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0180 A[SYNTHETIC, Splitter:B:107:0x0180] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.loc.bk a(java.lang.String r5, boolean r6, java.lang.String r7, java.util.Map<java.lang.String, java.lang.String> r8, byte[] r9, boolean r10) throws com.loc.t {
        /*
            r4 = this;
            r0 = 0
            com.loc.x.c()     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            if (r8 != 0) goto L_0x000b
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            r8.<init>()     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
        L_0x000b:
            com.loc.bi$a r1 = r4.j     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            com.loc.bi$b r1 = r1.a()     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            if (r6 == 0) goto L_0x001f
            boolean r6 = android.text.TextUtils.isEmpty(r7)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            if (r6 != 0) goto L_0x001f
            com.loc.bi$a r6 = r4.j     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            com.loc.bi$b r1 = r6.a(r7)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
        L_0x001f:
            int r6 = com.loc.bg.f6508a     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            java.lang.String r7 = ""
            r2 = 1
            if (r6 == r2) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            java.lang.String r7 = com.loc.bg.b     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
        L_0x0029:
            boolean r6 = android.text.TextUtils.isEmpty(r7)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            if (r6 != 0) goto L_0x0057
            android.net.Uri r5 = android.net.Uri.parse(r5)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            java.lang.String r6 = r5.getHost()     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            android.net.Uri$Builder r5 = r5.buildUpon()     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            android.net.Uri$Builder r5 = r5.encodedAuthority(r7)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            android.net.Uri r5 = r5.build()     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            java.lang.String r5 = r5.toString()     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            if (r8 == 0) goto L_0x004e
            java.lang.String r3 = "targetHost"
            r8.put(r3, r6)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
        L_0x004e:
            boolean r6 = r4.c     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            if (r6 == 0) goto L_0x0057
            com.loc.bi$a r6 = r4.j     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            r6.b(r7)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
        L_0x0057:
            boolean r6 = r4.c     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            if (r6 == 0) goto L_0x005f
            java.lang.String r5 = com.loc.z.a((java.lang.String) r5)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
        L_0x005f:
            java.net.URL r6 = new java.net.URL     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            r6.<init>(r5)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            com.loc.bg$a r5 = r4.k     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            if (r5 == 0) goto L_0x006f
            com.loc.bg$a r5 = r4.k     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            java.net.URLConnection r5 = r5.a()     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            goto L_0x0070
        L_0x006f:
            r5 = r0
        L_0x0070:
            if (r5 != 0) goto L_0x0081
            java.net.Proxy r5 = r4.e     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            if (r5 == 0) goto L_0x007d
            java.net.Proxy r5 = r4.e     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            java.net.URLConnection r5 = r6.openConnection(r5)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            goto L_0x0081
        L_0x007d:
            java.net.URLConnection r5 = r6.openConnection()     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
        L_0x0081:
            boolean r6 = r4.c     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            if (r6 == 0) goto L_0x009a
            javax.net.ssl.HttpsURLConnection r5 = (javax.net.ssl.HttpsURLConnection) r5     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            r6 = r5
            javax.net.ssl.HttpsURLConnection r6 = (javax.net.ssl.HttpsURLConnection) r6     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            javax.net.ssl.SSLContext r7 = r4.d     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            javax.net.ssl.SSLSocketFactory r7 = r7.getSocketFactory()     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            r6.setSSLSocketFactory(r7)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            r6 = r5
            javax.net.ssl.HttpsURLConnection r6 = (javax.net.ssl.HttpsURLConnection) r6     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            r6.setHostnameVerifier(r1)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            goto L_0x009c
        L_0x009a:
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
        L_0x009c:
            java.lang.String r6 = android.os.Build.VERSION.SDK     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            if (r6 == 0) goto L_0x00ad
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            r7 = 13
            if (r6 <= r7) goto L_0x00ad
            java.lang.String r6 = "Connection"
            java.lang.String r7 = "close"
            r5.setRequestProperty(r6, r7)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
        L_0x00ad:
            r4.a((java.util.Map<java.lang.String, java.lang.String>) r8, (java.net.HttpURLConnection) r5)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            java.lang.String r6 = "POST"
            r5.setRequestMethod(r6)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            r6 = 0
            r5.setUseCaches(r6)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            r5.setDoInput(r2)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            r5.setDoOutput(r2)     // Catch:{ ConnectException -> 0x0172, MalformedURLException -> 0x0165, UnknownHostException -> 0x0158, SocketException -> 0x014c, SocketTimeoutException -> 0x0140, InterruptedIOException -> 0x0137, IOException -> 0x012b, t -> 0x0122, Throwable -> 0x0111 }
            if (r9 == 0) goto L_0x00fa
            int r6 = r9.length     // Catch:{ ConnectException -> 0x00f5, MalformedURLException -> 0x00f1, UnknownHostException -> 0x00ed, SocketException -> 0x00e9, SocketTimeoutException -> 0x00e5, InterruptedIOException -> 0x010d, IOException -> 0x00e1, t -> 0x00dd, Throwable -> 0x00d9, all -> 0x00d4 }
            if (r6 <= 0) goto L_0x00fa
            java.io.DataOutputStream r6 = new java.io.DataOutputStream     // Catch:{ ConnectException -> 0x00f5, MalformedURLException -> 0x00f1, UnknownHostException -> 0x00ed, SocketException -> 0x00e9, SocketTimeoutException -> 0x00e5, InterruptedIOException -> 0x010d, IOException -> 0x00e1, t -> 0x00dd, Throwable -> 0x00d9, all -> 0x00d4 }
            java.io.OutputStream r7 = r5.getOutputStream()     // Catch:{ ConnectException -> 0x00f5, MalformedURLException -> 0x00f1, UnknownHostException -> 0x00ed, SocketException -> 0x00e9, SocketTimeoutException -> 0x00e5, InterruptedIOException -> 0x010d, IOException -> 0x00e1, t -> 0x00dd, Throwable -> 0x00d9, all -> 0x00d4 }
            r6.<init>(r7)     // Catch:{ ConnectException -> 0x00f5, MalformedURLException -> 0x00f1, UnknownHostException -> 0x00ed, SocketException -> 0x00e9, SocketTimeoutException -> 0x00e5, InterruptedIOException -> 0x010d, IOException -> 0x00e1, t -> 0x00dd, Throwable -> 0x00d9, all -> 0x00d4 }
            r6.write(r9)     // Catch:{ ConnectException -> 0x00f5, MalformedURLException -> 0x00f1, UnknownHostException -> 0x00ed, SocketException -> 0x00e9, SocketTimeoutException -> 0x00e5, InterruptedIOException -> 0x010d, IOException -> 0x00e1, t -> 0x00dd, Throwable -> 0x00d9, all -> 0x00d4 }
            r6.close()     // Catch:{ ConnectException -> 0x00f5, MalformedURLException -> 0x00f1, UnknownHostException -> 0x00ed, SocketException -> 0x00e9, SocketTimeoutException -> 0x00e5, InterruptedIOException -> 0x010d, IOException -> 0x00e1, t -> 0x00dd, Throwable -> 0x00d9, all -> 0x00d4 }
            goto L_0x00fa
        L_0x00d4:
            r6 = move-exception
            r0 = r5
            r5 = r6
            goto L_0x017e
        L_0x00d9:
            r6 = move-exception
            r0 = r5
            r5 = r6
            goto L_0x0112
        L_0x00dd:
            r6 = move-exception
            r0 = r5
            r5 = r6
            goto L_0x0123
        L_0x00e1:
            r6 = move-exception
            r0 = r5
            r5 = r6
            goto L_0x012c
        L_0x00e5:
            r6 = move-exception
            r0 = r5
            r5 = r6
            goto L_0x0141
        L_0x00e9:
            r6 = move-exception
            r0 = r5
            r5 = r6
            goto L_0x014d
        L_0x00ed:
            r6 = move-exception
            r0 = r5
            r5 = r6
            goto L_0x0159
        L_0x00f1:
            r6 = move-exception
            r0 = r5
            r5 = r6
            goto L_0x0166
        L_0x00f5:
            r6 = move-exception
            r0 = r5
            r5 = r6
            goto L_0x0173
        L_0x00fa:
            com.loc.bk r6 = r4.a((java.net.HttpURLConnection) r5, (boolean) r10)     // Catch:{ ConnectException -> 0x00f5, MalformedURLException -> 0x00f1, UnknownHostException -> 0x00ed, SocketException -> 0x00e9, SocketTimeoutException -> 0x00e5, InterruptedIOException -> 0x010d, IOException -> 0x00e1, t -> 0x00dd, Throwable -> 0x00d9, all -> 0x00d4 }
            if (r5 == 0) goto L_0x010c
            r5.disconnect()     // Catch:{ Throwable -> 0x0104 }
            goto L_0x010c
        L_0x0104:
            r5 = move-exception
            java.lang.String r7 = "ht"
            java.lang.String r8 = "mPt"
            com.loc.an.a((java.lang.Throwable) r5, (java.lang.String) r7, (java.lang.String) r8)
        L_0x010c:
            return r6
        L_0x010d:
            r0 = r5
            goto L_0x0137
        L_0x010f:
            r5 = move-exception
            goto L_0x017e
        L_0x0111:
            r5 = move-exception
        L_0x0112:
            java.lang.String r6 = "ht"
            java.lang.String r7 = "mPt"
            com.loc.an.a((java.lang.Throwable) r5, (java.lang.String) r6, (java.lang.String) r7)     // Catch:{ all -> 0x010f }
            com.loc.t r5 = new com.loc.t     // Catch:{ all -> 0x010f }
            java.lang.String r6 = "未知的错误"
            r5.<init>(r6)     // Catch:{ all -> 0x010f }
            throw r5     // Catch:{ all -> 0x010f }
        L_0x0122:
            r5 = move-exception
        L_0x0123:
            java.lang.String r6 = "ht"
            java.lang.String r7 = "mPt"
            com.loc.an.a((java.lang.Throwable) r5, (java.lang.String) r6, (java.lang.String) r7)     // Catch:{ all -> 0x010f }
            throw r5     // Catch:{ all -> 0x010f }
        L_0x012b:
            r5 = move-exception
        L_0x012c:
            r5.printStackTrace()     // Catch:{ all -> 0x010f }
            com.loc.t r5 = new com.loc.t     // Catch:{ all -> 0x010f }
            java.lang.String r6 = "IO 操作异常 - IOException"
            r5.<init>(r6)     // Catch:{ all -> 0x010f }
            throw r5     // Catch:{ all -> 0x010f }
        L_0x0137:
            com.loc.t r5 = new com.loc.t     // Catch:{ all -> 0x010f }
            java.lang.String r6 = "未知的错误"
            r5.<init>(r6)     // Catch:{ all -> 0x010f }
            throw r5     // Catch:{ all -> 0x010f }
        L_0x0140:
            r5 = move-exception
        L_0x0141:
            r5.printStackTrace()     // Catch:{ all -> 0x010f }
            com.loc.t r5 = new com.loc.t     // Catch:{ all -> 0x010f }
            java.lang.String r6 = "socket 连接超时 - SocketTimeoutException"
            r5.<init>(r6)     // Catch:{ all -> 0x010f }
            throw r5     // Catch:{ all -> 0x010f }
        L_0x014c:
            r5 = move-exception
        L_0x014d:
            r5.printStackTrace()     // Catch:{ all -> 0x010f }
            com.loc.t r5 = new com.loc.t     // Catch:{ all -> 0x010f }
            java.lang.String r6 = "socket 连接异常 - SocketException"
            r5.<init>(r6)     // Catch:{ all -> 0x010f }
            throw r5     // Catch:{ all -> 0x010f }
        L_0x0158:
            r5 = move-exception
        L_0x0159:
            r5.printStackTrace()     // Catch:{ all -> 0x010f }
            com.loc.t r5 = new com.loc.t     // Catch:{ all -> 0x010f }
            java.lang.String r6 = "未知主机 - UnKnowHostException"
            r5.<init>(r6)     // Catch:{ all -> 0x010f }
            throw r5     // Catch:{ all -> 0x010f }
        L_0x0165:
            r5 = move-exception
        L_0x0166:
            r5.printStackTrace()     // Catch:{ all -> 0x010f }
            com.loc.t r5 = new com.loc.t     // Catch:{ all -> 0x010f }
            java.lang.String r6 = "url异常 - MalformedURLException"
            r5.<init>(r6)     // Catch:{ all -> 0x010f }
            throw r5     // Catch:{ all -> 0x010f }
        L_0x0172:
            r5 = move-exception
        L_0x0173:
            r5.printStackTrace()     // Catch:{ all -> 0x010f }
            com.loc.t r5 = new com.loc.t     // Catch:{ all -> 0x010f }
            java.lang.String r6 = "http连接失败 - ConnectionException"
            r5.<init>(r6)     // Catch:{ all -> 0x010f }
            throw r5     // Catch:{ all -> 0x010f }
        L_0x017e:
            if (r0 == 0) goto L_0x018c
            r0.disconnect()     // Catch:{ Throwable -> 0x0184 }
            goto L_0x018c
        L_0x0184:
            r6 = move-exception
            java.lang.String r7 = "ht"
            java.lang.String r8 = "mPt"
            com.loc.an.a((java.lang.Throwable) r6, (java.lang.String) r7, (java.lang.String) r8)
        L_0x018c:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bi.a(java.lang.String, boolean, java.lang.String, java.util.Map, byte[], boolean):com.loc.bk");
    }
}
