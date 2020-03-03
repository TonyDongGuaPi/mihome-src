package com.mi.global.shop.request;

import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.sys.a;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.util.Utils;
import com.mi.log.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.TreeMap;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONException;
import org.json.JSONObject;

public class ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    protected static final String f6949a = "http";
    protected static final String b = "https";
    private static final String h = "ServiceConnection";
    private static final int i = 10000;
    private static final int j = 10000;
    private static final int k = 30000;
    protected JSONObject c;
    protected URL d;
    protected Parameter e;
    protected String f;
    protected boolean g;
    private byte[] l;
    private String m;
    private TrustManager[] n = {new MytmArray()};
    private HostnameVerifier o = new HostnameVerifier() {
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    };

    public enum NetworkError {
        OK,
        URL_ERROR,
        NETWORK_ERROR,
        CLIENT_ERROR,
        SERVER_ERROR,
        RESULT_ERROR,
        UNKNOWN_ERROR
    }

    /* access modifiers changed from: protected */
    public Parameter a(Parameter parameter) throws ConnectionException {
        return parameter;
    }

    /* access modifiers changed from: protected */
    public String a(String str, Parameter parameter) throws ConnectionException {
        return str;
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection a(HttpURLConnection httpURLConnection) throws ConnectionException {
        return httpURLConnection;
    }

    public ServiceConnection(String str) {
        URL url;
        try {
            url = new URL(str);
        } catch (MalformedURLException e2) {
            if (ShopApp.f1346a) {
                String str2 = h;
                Log.e(str2, "URL error: " + e2);
            }
            url = null;
        }
        b(url);
    }

    private void b(URL url) {
        this.g = false;
        if (a(url)) {
            this.d = url;
        }
    }

    public JSONObject a() {
        return this.c;
    }

    public String b() {
        return this.f;
    }

    public Parameter c() {
        return this.e;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public void a(byte[] bArr) {
        this.l = bArr;
    }

    public NetworkError d() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        NetworkError a2 = a((ResetableOutputStream) new MemoryResetableOutputStream(byteArrayOutputStream));
        try {
            if (a2 == NetworkError.OK) {
                String str = h;
                LogUtil.b(str, "baos.toString():" + byteArrayOutputStream.toString());
                this.c = new JSONObject(byteArrayOutputStream.toString());
            } else if (ShopApp.f1346a) {
                String str2 = h;
                LogUtil.b(str2, "ServiceConnection failed : " + a2);
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused) {
            }
            return a2;
        } catch (JSONException e2) {
            if (ShopApp.f1346a) {
                String str3 = h;
                LogUtil.b(str3, "JSON error: " + e2);
            }
            NetworkError networkError = NetworkError.RESULT_ERROR;
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused2) {
            }
            return networkError;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused3) {
            }
            throw th;
        }
    }

    public NetworkError e() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        NetworkError a2 = a((ResetableOutputStream) new MemoryResetableOutputStream(byteArrayOutputStream));
        if (a2 == NetworkError.OK) {
            this.f = byteArrayOutputStream.toString();
            if (ShopApp.f1346a) {
                Log.d(h, this.f);
            }
        } else if (ShopApp.f1346a) {
            String str = h;
            Log.e(str, "ServiceConnection failed : " + a2);
        }
        try {
            byteArrayOutputStream.close();
        } catch (IOException unused) {
        }
        return a2;
    }

    public NetworkError a(File file) throws FileNotFoundException {
        if (file != null) {
            LogUtil.b(h, String.format("outFile:%s", new Object[]{file.getAbsolutePath()}));
            try {
                FileResetableOutputStream fileResetableOutputStream = new FileResetableOutputStream(file);
                NetworkError a2 = a((ResetableOutputStream) fileResetableOutputStream);
                try {
                    fileResetableOutputStream.close();
                    if (a2 != NetworkError.OK) {
                        if (ShopApp.f1346a) {
                            String str = h;
                            Log.e(str, "ServiceConnection failed : " + a2);
                        }
                        file.delete();
                    }
                } catch (IOException unused) {
                }
                return a2;
            } catch (FileNotFoundException e2) {
                if (ShopApp.f1346a) {
                    String str2 = h;
                    Log.e(str2, "File not found: " + e2);
                }
                throw e2;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    /* access modifiers changed from: protected */
    public NetworkError a(ResetableOutputStream resetableOutputStream) {
        LogUtil.b(h, "ServiceConnection.request mUrl:" + this.d);
        if (this.d == null) {
            return NetworkError.URL_ERROR;
        }
        if (!Utils.Network.isNetWorkConnected(ShopApp.g())) {
            return NetworkError.NETWORK_ERROR;
        }
        if (this.e == null) {
            this.e = new Parameter(this);
        }
        try {
            Parameter a2 = a(this.e);
            String url = this.d.toString();
            if (this.g && !a2.a()) {
                String query = this.d.getQuery();
                String url2 = this.d.toString();
                if (TextUtils.isEmpty(query)) {
                    url = url2 + "?" + a2.b();
                } else {
                    url = url2 + a.b + a2.b();
                }
            }
            try {
                String a3 = a(url, a2);
                if (ShopApp.f1346a) {
                    Log.d(h, "connection url: " + a3);
                }
                if (!this.g) {
                    if (this.l != null && this.l.length > 0) {
                        this.m = "application/octet-stream";
                    } else if (!a2.a()) {
                        this.l = a2.b().getBytes();
                        if (ShopApp.f1346a) {
                            Log.d(h, "[post]" + a2);
                        }
                    }
                    if (this.l == null || this.l.length == 0) {
                        return NetworkError.CLIENT_ERROR;
                    }
                }
                long currentTimeMillis = System.currentTimeMillis();
                NetworkError a4 = a(a3, this.l, this.g, resetableOutputStream);
                if (ShopApp.f1346a) {
                    long currentTimeMillis2 = System.currentTimeMillis();
                    if (ShopApp.f1346a) {
                        Log.d(h, "Time(ms) spent in request: " + (currentTimeMillis2 - currentTimeMillis) + ", " + a3);
                    }
                }
                return a4;
            } catch (ConnectionException e2) {
                return e2.mError;
            }
        } catch (ConnectionException e3) {
            return e3.mError;
        }
    }

    class MytmArray implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        MytmArray() {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private void f() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init((KeyManager[]) null, this.n, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARNING: type inference failed for: r0v1 */
    /* JADX WARNING: type inference failed for: r0v2, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v5, types: [java.io.BufferedInputStream] */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0109 A[Catch:{ all -> 0x0102 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0130 A[SYNTHETIC, Splitter:B:75:0x0130] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0136 A[Catch:{ Exception -> 0x0143, all -> 0x0140 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x013c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.mi.global.shop.request.ServiceConnection.NetworkError a(java.lang.String r6, byte[] r7, boolean r8, com.mi.global.shop.request.ServiceConnection.ResetableOutputStream r9) {
        /*
            r5 = this;
            boolean r0 = com.mi.global.shop.ShopApp.f1346a
            if (r0 == 0) goto L_0x001a
            java.lang.String r0 = h
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "hosted connection url: "
            r1.append(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r0, r1)
        L_0x001a:
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0021 }
            r1.<init>(r6)     // Catch:{ MalformedURLException -> 0x0021 }
            goto L_0x003d
        L_0x0021:
            r6 = move-exception
            boolean r1 = com.mi.global.shop.ShopApp.f1346a
            if (r1 == 0) goto L_0x003c
            java.lang.String r1 = h
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = " URL error :"
            r2.append(r3)
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            android.util.Log.e(r1, r6)
        L_0x003c:
            r1 = r0
        L_0x003d:
            java.lang.String r6 = r1.getProtocol()     // Catch:{ Exception -> 0x0152 }
            java.lang.String r2 = "https"
            boolean r6 = r6.equalsIgnoreCase(r2)     // Catch:{ Exception -> 0x0152 }
            if (r6 == 0) goto L_0x0062
            r5.f()     // Catch:{ Exception -> 0x0152 }
            java.net.URLConnection r6 = r1.openConnection()     // Catch:{ Exception -> 0x0152 }
            javax.net.ssl.HttpsURLConnection r6 = (javax.net.ssl.HttpsURLConnection) r6     // Catch:{ Exception -> 0x0152 }
            r2 = r6
            javax.net.ssl.HttpsURLConnection r2 = (javax.net.ssl.HttpsURLConnection) r2     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            javax.net.ssl.HostnameVerifier r3 = r5.o     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            r2.setHostnameVerifier(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            goto L_0x0068
        L_0x005b:
            r7 = move-exception
            goto L_0x0184
        L_0x005e:
            r7 = move-exception
            r0 = r6
            goto L_0x0153
        L_0x0062:
            java.net.URLConnection r6 = r1.openConnection()     // Catch:{ Exception -> 0x0152 }
            java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6     // Catch:{ Exception -> 0x0152 }
        L_0x0068:
            r2 = 10000(0x2710, float:1.4013E-41)
            r6.setConnectTimeout(r2)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            android.app.Application r3 = com.mi.global.shop.ShopApp.g()     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            boolean r3 = com.mi.global.shop.util.Utils.Network.isWifiConnected(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            if (r3 == 0) goto L_0x007b
            r6.setReadTimeout(r2)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            goto L_0x0080
        L_0x007b:
            r2 = 30000(0x7530, float:4.2039E-41)
            r6.setReadTimeout(r2)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
        L_0x0080:
            r2 = 0
            if (r8 == 0) goto L_0x008c
            java.lang.String r3 = "GET"
            r6.setRequestMethod(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            r6.setDoOutput(r2)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            goto L_0x00b6
        L_0x008c:
            java.lang.String r3 = "POST"
            r6.setRequestMethod(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            r3 = 1
            r6.setDoOutput(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            r6.setUseCaches(r2)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            if (r7 == 0) goto L_0x00a7
            int r3 = r7.length     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            if (r3 <= 0) goto L_0x00a7
            java.lang.String r3 = "Content-Length"
            int r4 = r7.length     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            r6.setRequestProperty(r3, r4)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
        L_0x00a7:
            java.lang.String r3 = r5.m     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            if (r3 != 0) goto L_0x00b6
            java.lang.String r3 = "Content-Type"
            java.lang.String r4 = r5.m     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            r6.setRequestProperty(r3, r4)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
        L_0x00b6:
            java.net.HttpURLConnection r3 = r5.a((java.net.HttpURLConnection) r6)     // Catch:{ ConnectionException -> 0x0146 }
            r3.connect()     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            if (r8 != 0) goto L_0x00ce
            if (r7 == 0) goto L_0x00ce
            int r6 = r7.length     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            if (r6 <= 0) goto L_0x00ce
            java.io.OutputStream r6 = r3.getOutputStream()     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            r6.write(r7)     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            r6.close()     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
        L_0x00ce:
            int r6 = r3.getResponseCode()     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            com.mi.global.shop.request.ServiceConnection$NetworkError r6 = r5.a((int) r6)     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            com.mi.global.shop.request.ServiceConnection$NetworkError r7 = com.mi.global.shop.request.ServiceConnection.NetworkError.OK     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            if (r6 != r7) goto L_0x013a
            if (r9 == 0) goto L_0x013a
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0104 }
            java.io.InputStream r8 = r3.getInputStream()     // Catch:{ Exception -> 0x0104 }
            r4 = 8192(0x2000, float:1.14794E-41)
            r7.<init>(r8, r4)     // Catch:{ Exception -> 0x0104 }
            r8 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r8]     // Catch:{ Exception -> 0x00ff, all -> 0x00fc }
        L_0x00eb:
            int r4 = r7.read(r0, r2, r8)     // Catch:{ Exception -> 0x00ff, all -> 0x00fc }
            if (r4 <= 0) goto L_0x00f5
            r9.write(r0, r2, r4)     // Catch:{ Exception -> 0x00ff, all -> 0x00fc }
            goto L_0x00eb
        L_0x00f5:
            r9.flush()     // Catch:{ Exception -> 0x00ff, all -> 0x00fc }
            r7.close()     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            goto L_0x013a
        L_0x00fc:
            r6 = move-exception
            r0 = r7
            goto L_0x0134
        L_0x00ff:
            r8 = move-exception
            r0 = r7
            goto L_0x0105
        L_0x0102:
            r6 = move-exception
            goto L_0x0134
        L_0x0104:
            r8 = move-exception
        L_0x0105:
            boolean r7 = com.mi.global.shop.ShopApp.f1346a     // Catch:{ all -> 0x0102 }
            if (r7 == 0) goto L_0x012b
            java.lang.String r7 = h     // Catch:{ all -> 0x0102 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0102 }
            r2.<init>()     // Catch:{ all -> 0x0102 }
            java.lang.String r4 = "ServiceConnection Exception for "
            r2.append(r4)     // Catch:{ all -> 0x0102 }
            java.lang.String r4 = r1.getHost()     // Catch:{ all -> 0x0102 }
            r2.append(r4)     // Catch:{ all -> 0x0102 }
            java.lang.String r4 = " : read file stream error "
            r2.append(r4)     // Catch:{ all -> 0x0102 }
            r2.append(r8)     // Catch:{ all -> 0x0102 }
            java.lang.String r8 = r2.toString()     // Catch:{ all -> 0x0102 }
            android.util.Log.e(r7, r8)     // Catch:{ all -> 0x0102 }
        L_0x012b:
            r9.a()     // Catch:{ all -> 0x0102 }
            if (r0 == 0) goto L_0x013a
            r0.close()     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            goto L_0x013a
        L_0x0134:
            if (r0 == 0) goto L_0x0139
            r0.close()     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
        L_0x0139:
            throw r6     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
        L_0x013a:
            if (r3 == 0) goto L_0x013f
            r3.disconnect()
        L_0x013f:
            return r6
        L_0x0140:
            r7 = move-exception
            r6 = r3
            goto L_0x0184
        L_0x0143:
            r7 = move-exception
            r0 = r3
            goto L_0x0153
        L_0x0146:
            r7 = move-exception
            com.mi.global.shop.request.ServiceConnection$NetworkError r7 = r7.mError     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            if (r6 == 0) goto L_0x014e
            r6.disconnect()
        L_0x014e:
            return r7
        L_0x014f:
            r7 = move-exception
            r6 = r0
            goto L_0x0184
        L_0x0152:
            r7 = move-exception
        L_0x0153:
            boolean r6 = com.mi.global.shop.ShopApp.f1346a     // Catch:{ all -> 0x014f }
            if (r6 == 0) goto L_0x0179
            java.lang.String r6 = h     // Catch:{ all -> 0x014f }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x014f }
            r8.<init>()     // Catch:{ all -> 0x014f }
            java.lang.String r9 = "ServiceConnection Exception for "
            r8.append(r9)     // Catch:{ all -> 0x014f }
            java.lang.String r9 = r1.getHost()     // Catch:{ all -> 0x014f }
            r8.append(r9)     // Catch:{ all -> 0x014f }
            java.lang.String r9 = " :"
            r8.append(r9)     // Catch:{ all -> 0x014f }
            r8.append(r7)     // Catch:{ all -> 0x014f }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x014f }
            android.util.Log.e(r6, r8)     // Catch:{ all -> 0x014f }
        L_0x0179:
            r7.printStackTrace()     // Catch:{ all -> 0x014f }
            if (r0 == 0) goto L_0x0181
            r0.disconnect()
        L_0x0181:
            com.mi.global.shop.request.ServiceConnection$NetworkError r6 = com.mi.global.shop.request.ServiceConnection.NetworkError.NETWORK_ERROR
            return r6
        L_0x0184:
            if (r6 == 0) goto L_0x0189
            r6.disconnect()
        L_0x0189:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.request.ServiceConnection.a(java.lang.String, byte[], boolean, com.mi.global.shop.request.ServiceConnection$ResetableOutputStream):com.mi.global.shop.request.ServiceConnection$NetworkError");
    }

    /* access modifiers changed from: protected */
    public boolean a(URL url) {
        if (url == null) {
            return false;
        }
        if (TextUtils.equals(url.getProtocol(), "http") || TextUtils.equals(url.getProtocol(), "https")) {
            return true;
        }
        return false;
    }

    private NetworkError a(int i2) {
        if (i2 == 200) {
            return NetworkError.OK;
        }
        if (ShopApp.f1346a) {
            String str = h;
            Log.e(str, "Network Error : " + i2);
        }
        return NetworkError.SERVER_ERROR;
    }

    /* access modifiers changed from: private */
    public static StringBuilder b(StringBuilder sb, String str, String str2, String str3) {
        if (sb.length() > 0) {
            sb.append(a.b);
        }
        sb.append(str);
        sb.append("=");
        try {
            sb.append(URLEncoder.encode(str2, str3));
        } catch (UnsupportedEncodingException unused) {
        }
        return sb;
    }

    /* access modifiers changed from: private */
    public static StringBuilder b(StringBuilder sb, String str, String str2) {
        if (sb.length() > 0) {
            sb.append(a.b);
        }
        sb.append(str);
        sb.append("=");
        sb.append(str2);
        return sb;
    }

    public class Parameter {
        private TreeMap<String, String> b;
        private boolean c;

        public Parameter(ServiceConnection serviceConnection) {
            this(true);
        }

        public Parameter(boolean z) {
            this.c = false;
            this.b = new TreeMap<>();
            this.c = false;
            if (z) {
                ServiceConnection.this.e = this;
            }
        }

        public void a(boolean z) {
            this.c = z;
        }

        public Parameter a(String str, String str2) {
            if (TextUtils.isEmpty(str2)) {
                if (this.c) {
                    return this;
                }
                str2 = "";
            }
            this.b.put(str, str2);
            return this;
        }

        public Parameter a(String str, Object obj) {
            if (obj == null) {
                if (this.c) {
                    return this;
                }
                obj = "";
            }
            this.b.put(str, String.valueOf(obj));
            return this;
        }

        public Parameter a(String str, boolean z) {
            this.b.put(str, String.valueOf(z));
            return this;
        }

        public Parameter a(String str, int i) {
            this.b.put(str, String.valueOf(i));
            return this;
        }

        public String a(String str) {
            return this.b.get(str);
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public String toString() {
            if (this.b.isEmpty()) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (String next : this.b.keySet()) {
                sb = ServiceConnection.b(sb, next, this.b.get(next));
            }
            return sb.toString();
        }

        public String b() {
            return b("UTF-8");
        }

        public String b(String str) {
            if (this.b.isEmpty()) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (String next : this.b.keySet()) {
                sb = ServiceConnection.b(sb, next, this.b.get(next), str);
            }
            return sb.toString();
        }

        public TreeMap<String, String> c() {
            return this.b;
        }
    }

    protected class ConnectionException extends Exception {
        private static final long serialVersionUID = 1;
        protected NetworkError mError;

        public ConnectionException(NetworkError networkError) {
            this.mError = networkError;
        }
    }

    protected abstract class ResetableOutputStream extends OutputStream {
        protected OutputStream b;

        public abstract void a();

        public ResetableOutputStream(OutputStream outputStream) {
            if (outputStream != null) {
                this.b = outputStream;
                return;
            }
            throw new IllegalArgumentException("outputstream is null");
        }

        public void close() throws IOException {
            this.b.close();
        }

        public void flush() throws IOException {
            this.b.flush();
        }

        public void write(byte[] bArr) throws IOException {
            this.b.write(bArr);
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.b.write(bArr, i, i2);
        }

        public void write(int i) throws IOException {
            this.b.write(i);
        }
    }

    protected class MemoryResetableOutputStream extends ResetableOutputStream {
        public MemoryResetableOutputStream(ByteArrayOutputStream byteArrayOutputStream) {
            super(byteArrayOutputStream);
        }

        public void a() {
            ((ByteArrayOutputStream) this.b).reset();
        }
    }

    protected class FileResetableOutputStream extends ResetableOutputStream {
        private File d;

        public FileResetableOutputStream(File file) throws FileNotFoundException {
            super(new FileOutputStream(file));
            this.d = file;
        }

        public void a() {
            try {
                this.b.close();
            } catch (IOException unused) {
            }
            this.d.delete();
            try {
                this.b = new FileOutputStream(this.d);
            } catch (FileNotFoundException unused2) {
            }
        }
    }
}
