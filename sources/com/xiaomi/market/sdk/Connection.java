package com.xiaomi.market.sdk;

import android.text.TextUtils;
import com.alipay.sdk.sys.a;
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
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

public class Connection {

    /* renamed from: a  reason: collision with root package name */
    protected static final String f11096a = "http";
    protected static final String b = "https";
    private static final String m = "MarketConnection";
    private static final int n = 10000;
    private static final int o = 10000;
    private static final int p = 30000;
    protected JSONObject c;
    protected URL d;
    protected Parameter e;
    protected String f;
    protected boolean g;
    protected boolean h;
    protected boolean i;
    protected boolean j;
    protected boolean k;
    protected boolean l;

    public enum NetworkError {
        OK,
        URL_ERROR,
        NETWORK_ERROR,
        AUTH_ERROR,
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

    public Connection(String str) {
        this(str, false);
    }

    public Connection(String str, String str2) {
        this(a(str, str2), false);
    }

    public Connection(String str, boolean z) {
        URL url;
        try {
            url = new URL(str);
        } catch (MalformedURLException e2) {
            Log.b(m, "URL error: " + e2);
            url = null;
        }
        b(url);
        this.l = z;
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

    private void b(URL url) {
        this.g = true;
        this.h = false;
        this.i = true;
        this.j = true;
        this.k = true;
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
        this.h = z;
    }

    public void b(boolean z) {
        this.g = z;
    }

    public void c(boolean z) {
        this.i = z;
    }

    public void d(boolean z) {
        this.j = z;
    }

    public void e(boolean z) {
        this.k = z;
    }

    public NetworkError d() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        NetworkError a2 = a((ResetableOutputStream) new MemoryResetableOutputStream(byteArrayOutputStream));
        try {
            if (a2 == NetworkError.OK) {
                this.c = new JSONObject(byteArrayOutputStream.toString());
            } else {
                Log.b(m, "Connection failed : " + a2);
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused) {
            }
            return a2;
        } catch (JSONException e2) {
            Log.b(m, "JSON error: " + e2);
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
        } else {
            Log.b(m, "Connection failed : " + a2);
        }
        try {
            byteArrayOutputStream.close();
        } catch (IOException unused) {
        }
        return a2;
    }

    public NetworkError a(File file) throws FileNotFoundException {
        if (file != null) {
            try {
                FileResetableOutputStream fileResetableOutputStream = new FileResetableOutputStream(file);
                NetworkError a2 = a((ResetableOutputStream) fileResetableOutputStream);
                try {
                    fileResetableOutputStream.close();
                    if (a2 != NetworkError.OK) {
                        Log.b(m, "Connection failed : " + a2);
                        file.delete();
                    }
                } catch (IOException unused) {
                }
                return a2;
            } catch (FileNotFoundException e2) {
                Log.b(m, "File not found: " + e2);
                throw e2;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    /* access modifiers changed from: protected */
    public NetworkError a(ResetableOutputStream resetableOutputStream) {
        String str;
        if (this.d == null) {
            return NetworkError.URL_ERROR;
        }
        if (!Utils.b(XiaomiUpdateAgent.f())) {
            return NetworkError.NETWORK_ERROR;
        }
        if (this.e == null) {
            this.e = new Parameter(this);
        }
        Parameter parameter = this.e;
        try {
            Parameter a2 = a(this.e);
            String url = this.d.toString();
            if (this.h && !a2.a()) {
                String query = this.d.getQuery();
                String url2 = this.d.toString();
                if (TextUtils.isEmpty(query)) {
                    url = url2 + "?" + a2.toString();
                } else {
                    url = url2 + a.b + a2.toString();
                }
            }
            try {
                String a3 = a(url, a2);
                if (Utils.b) {
                    Log.a(m, "connection url: " + a3);
                }
                if (!this.h) {
                    str = a2.toString();
                } else {
                    str = "";
                }
                long currentTimeMillis = System.currentTimeMillis();
                NetworkError a4 = a(a3, str, this.h, false, resetableOutputStream);
                if (Utils.b) {
                    long currentTimeMillis2 = System.currentTimeMillis();
                    Log.a(m, "Time(ms) spent in request: " + (currentTimeMillis2 - currentTimeMillis) + ", " + a3);
                }
                return a4;
            } catch (ConnectionException e2) {
                return e2.mError;
            }
        } catch (ConnectionException e3) {
            return e3.mError;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x000c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0100 A[SYNTHETIC, Splitter:B:55:0x0100] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x010d A[SYNTHETIC, Splitter:B:61:0x010d] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0110 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.xiaomi.market.sdk.Connection.NetworkError a(java.lang.String r8, java.lang.String r9, boolean r10, boolean r11, com.xiaomi.market.sdk.Connection.ResetableOutputStream r12) {
        /*
            r7 = this;
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r11.add(r8)
            java.util.Iterator r8 = r11.iterator()
        L_0x000c:
            boolean r11 = r8.hasNext()
            if (r11 == 0) goto L_0x0173
            java.lang.Object r11 = r8.next()
            java.lang.String r11 = (java.lang.String) r11
            boolean r0 = com.xiaomi.market.sdk.Utils.b
            if (r0 == 0) goto L_0x0032
            java.lang.String r0 = "MarketConnection"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "hosted connection url: "
            r1.append(r2)
            r1.append(r11)
            java.lang.String r1 = r1.toString()
            com.xiaomi.market.sdk.Log.a(r0, r1)
        L_0x0032:
            java.net.URL r0 = new java.net.URL     // Catch:{ MalformedURLException -> 0x015a }
            r0.<init>(r11)     // Catch:{ MalformedURLException -> 0x015a }
            r11 = 0
            java.net.URLConnection r1 = r0.openConnection()     // Catch:{ Exception -> 0x012b, all -> 0x0128 }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Exception -> 0x012b, all -> 0x0128 }
            r2 = 10000(0x2710, float:1.4013E-41)
            r1.setConnectTimeout(r2)     // Catch:{ Exception -> 0x0125, all -> 0x0122 }
            android.content.Context r3 = com.xiaomi.market.sdk.XiaomiUpdateAgent.f()     // Catch:{ Exception -> 0x0125, all -> 0x0122 }
            boolean r3 = com.xiaomi.market.sdk.Utils.c(r3)     // Catch:{ Exception -> 0x0125, all -> 0x0122 }
            if (r3 == 0) goto L_0x0051
            r1.setReadTimeout(r2)     // Catch:{ Exception -> 0x0125, all -> 0x0122 }
            goto L_0x0056
        L_0x0051:
            r2 = 30000(0x7530, float:4.2039E-41)
            r1.setReadTimeout(r2)     // Catch:{ Exception -> 0x0125, all -> 0x0122 }
        L_0x0056:
            r2 = 0
            if (r10 == 0) goto L_0x0062
            java.lang.String r3 = "GET"
            r1.setRequestMethod(r3)     // Catch:{ Exception -> 0x0125, all -> 0x0122 }
            r1.setDoOutput(r2)     // Catch:{ Exception -> 0x0125, all -> 0x0122 }
            goto L_0x006b
        L_0x0062:
            java.lang.String r3 = "POST"
            r1.setRequestMethod(r3)     // Catch:{ Exception -> 0x0125, all -> 0x0122 }
            r3 = 1
            r1.setDoOutput(r3)     // Catch:{ Exception -> 0x0125, all -> 0x0122 }
        L_0x006b:
            java.net.HttpURLConnection r3 = r7.a((java.net.HttpURLConnection) r1)     // Catch:{ ConnectionException -> 0x0119 }
            r3.connect()     // Catch:{ Exception -> 0x0117 }
            if (r10 != 0) goto L_0x00a2
            boolean r1 = android.text.TextUtils.isEmpty(r9)     // Catch:{ Exception -> 0x0117 }
            if (r1 != 0) goto L_0x00a2
            java.io.OutputStream r1 = r3.getOutputStream()     // Catch:{ Exception -> 0x0117 }
            byte[] r4 = r9.getBytes()     // Catch:{ Exception -> 0x0117 }
            r1.write(r4)     // Catch:{ Exception -> 0x0117 }
            boolean r4 = com.xiaomi.market.sdk.Utils.b     // Catch:{ Exception -> 0x0117 }
            if (r4 == 0) goto L_0x009f
            java.lang.String r4 = "MarketConnection"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0117 }
            r5.<init>()     // Catch:{ Exception -> 0x0117 }
            java.lang.String r6 = "[post]"
            r5.append(r6)     // Catch:{ Exception -> 0x0117 }
            r5.append(r9)     // Catch:{ Exception -> 0x0117 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0117 }
            com.xiaomi.market.sdk.Log.a(r4, r5)     // Catch:{ Exception -> 0x0117 }
        L_0x009f:
            r1.close()     // Catch:{ Exception -> 0x0117 }
        L_0x00a2:
            int r1 = r3.getResponseCode()     // Catch:{ Exception -> 0x0117 }
            com.xiaomi.market.sdk.Connection$NetworkError r1 = r7.a((int) r1)     // Catch:{ Exception -> 0x0117 }
            com.xiaomi.market.sdk.Connection$NetworkError r4 = com.xiaomi.market.sdk.Connection.NetworkError.OK     // Catch:{ Exception -> 0x0117 }
            if (r1 != r4) goto L_0x0111
            if (r12 == 0) goto L_0x0111
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x00d6, all -> 0x00d2 }
            java.io.InputStream r5 = r3.getInputStream()     // Catch:{ Exception -> 0x00d6, all -> 0x00d2 }
            r6 = 8192(0x2000, float:1.14794E-41)
            r4.<init>(r5, r6)     // Catch:{ Exception -> 0x00d6, all -> 0x00d2 }
            r11 = 1024(0x400, float:1.435E-42)
            byte[] r5 = new byte[r11]     // Catch:{ Exception -> 0x00d0 }
        L_0x00bf:
            int r6 = r4.read(r5, r2, r11)     // Catch:{ Exception -> 0x00d0 }
            if (r6 <= 0) goto L_0x00c9
            r12.write(r5, r2, r6)     // Catch:{ Exception -> 0x00d0 }
            goto L_0x00bf
        L_0x00c9:
            r12.flush()     // Catch:{ Exception -> 0x00d0 }
            r4.close()     // Catch:{ Exception -> 0x0117 }
            goto L_0x0111
        L_0x00d0:
            r11 = move-exception
            goto L_0x00d9
        L_0x00d2:
            r1 = move-exception
            r4 = r11
            r11 = r1
            goto L_0x010b
        L_0x00d6:
            r1 = move-exception
            r4 = r11
            r11 = r1
        L_0x00d9:
            java.lang.String r1 = "MarketConnection"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x010a }
            r2.<init>()     // Catch:{ all -> 0x010a }
            java.lang.String r5 = "Connection Exception for "
            r2.append(r5)     // Catch:{ all -> 0x010a }
            java.lang.String r5 = r0.getHost()     // Catch:{ all -> 0x010a }
            r2.append(r5)     // Catch:{ all -> 0x010a }
            java.lang.String r5 = " : read file stream error "
            r2.append(r5)     // Catch:{ all -> 0x010a }
            r2.append(r11)     // Catch:{ all -> 0x010a }
            java.lang.String r11 = r2.toString()     // Catch:{ all -> 0x010a }
            com.xiaomi.market.sdk.Log.b(r1, r11)     // Catch:{ all -> 0x010a }
            r12.a()     // Catch:{ all -> 0x010a }
            if (r4 == 0) goto L_0x0103
            r4.close()     // Catch:{ Exception -> 0x0117 }
        L_0x0103:
            if (r3 == 0) goto L_0x000c
        L_0x0105:
            r3.disconnect()
            goto L_0x000c
        L_0x010a:
            r11 = move-exception
        L_0x010b:
            if (r4 == 0) goto L_0x0110
            r4.close()     // Catch:{ Exception -> 0x0117 }
        L_0x0110:
            throw r11     // Catch:{ Exception -> 0x0117 }
        L_0x0111:
            if (r3 == 0) goto L_0x0116
            r3.disconnect()
        L_0x0116:
            return r1
        L_0x0117:
            r11 = move-exception
            goto L_0x012e
        L_0x0119:
            r11 = move-exception
            com.xiaomi.market.sdk.Connection$NetworkError r11 = r11.mError     // Catch:{ Exception -> 0x0125, all -> 0x0122 }
            if (r1 == 0) goto L_0x0121
            r1.disconnect()
        L_0x0121:
            return r11
        L_0x0122:
            r8 = move-exception
            r3 = r1
            goto L_0x0154
        L_0x0125:
            r11 = move-exception
            r3 = r1
            goto L_0x012e
        L_0x0128:
            r8 = move-exception
            r3 = r11
            goto L_0x0154
        L_0x012b:
            r1 = move-exception
            r3 = r11
            r11 = r1
        L_0x012e:
            java.lang.String r1 = "MarketConnection"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0153 }
            r2.<init>()     // Catch:{ all -> 0x0153 }
            java.lang.String r4 = "Connection Exception for "
            r2.append(r4)     // Catch:{ all -> 0x0153 }
            java.lang.String r0 = r0.getHost()     // Catch:{ all -> 0x0153 }
            r2.append(r0)     // Catch:{ all -> 0x0153 }
            java.lang.String r0 = " :"
            r2.append(r0)     // Catch:{ all -> 0x0153 }
            r2.append(r11)     // Catch:{ all -> 0x0153 }
            java.lang.String r11 = r2.toString()     // Catch:{ all -> 0x0153 }
            com.xiaomi.market.sdk.Log.b(r1, r11)     // Catch:{ all -> 0x0153 }
            if (r3 == 0) goto L_0x000c
            goto L_0x0105
        L_0x0153:
            r8 = move-exception
        L_0x0154:
            if (r3 == 0) goto L_0x0159
            r3.disconnect()
        L_0x0159:
            throw r8
        L_0x015a:
            r11 = move-exception
            java.lang.String r0 = "MarketConnection"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = " URL error :"
            r1.append(r2)
            r1.append(r11)
            java.lang.String r11 = r1.toString()
            com.xiaomi.market.sdk.Log.b(r0, r11)
            goto L_0x000c
        L_0x0173:
            com.xiaomi.market.sdk.Connection$NetworkError r8 = com.xiaomi.market.sdk.Connection.NetworkError.NETWORK_ERROR
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.market.sdk.Connection.a(java.lang.String, java.lang.String, boolean, boolean, com.xiaomi.market.sdk.Connection$ResetableOutputStream):com.xiaomi.market.sdk.Connection$NetworkError");
    }

    /* access modifiers changed from: protected */
    public boolean a(URL url) {
        if (url == null) {
            return false;
        }
        String protocol = url.getProtocol();
        if (TextUtils.equals(protocol, "http") || TextUtils.equals(protocol, "https")) {
            return true;
        }
        return false;
    }

    private NetworkError a(int i2) {
        if (i2 == 200) {
            return NetworkError.OK;
        }
        Log.b(m, "Network Error : " + i2);
        return NetworkError.SERVER_ERROR;
    }

    public class Parameter {
        private TreeMap<String, String> b;

        public Parameter(Connection connection) {
            this(true);
        }

        public Parameter(boolean z) {
            this.b = new TreeMap<>();
            if (z) {
                Connection.this.e = this;
            }
        }

        public Parameter a(String str, String str2) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return this;
            }
            this.b.put(str, str2);
            return this;
        }

        public Parameter a(String str, boolean z) {
            if (z) {
                this.b.put(str, "true");
            } else {
                this.b.put(str, "false");
            }
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
                sb.append(next);
                sb.append("=");
                try {
                    sb.append(URLEncoder.encode(this.b.get(next), "UTF-8"));
                } catch (UnsupportedEncodingException unused) {
                }
                sb.append(a.b);
            }
            return sb.deleteCharAt(sb.length() - 1).toString();
        }

        public TreeMap<String, String> b() {
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
