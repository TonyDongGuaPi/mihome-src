package com.mi.global.shop.base.request;

import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.sys.a;
import com.mi.global.shop.base.ApplicationAgent;
import com.mi.global.shop.base.utils.Utils;
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
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int GPRS_READ_TIMEOUT = 30000;
    protected static final String HTTPS_PROTOCAL = "https";
    protected static final String HTTP_PROTOCAL = "http";
    private static final String TAG = "ServiceConnection";
    private static final int WIFI_READ_TIMEOUT = 10000;
    private HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    };
    private String mContentType;
    protected Parameter mParameter;
    private byte[] mPostData;
    protected JSONObject mResponse;
    protected String mString;
    protected URL mUrl;
    protected boolean mUseGet;
    private TrustManager[] xtmArray = {new MytmArray()};

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
    public HttpURLConnection onConnectionCreated(HttpURLConnection httpURLConnection) throws ConnectionException {
        return httpURLConnection;
    }

    /* access modifiers changed from: protected */
    public Parameter onQueryCreated(Parameter parameter) throws ConnectionException {
        return parameter;
    }

    /* access modifiers changed from: protected */
    public String onURLCreated(String str, Parameter parameter) throws ConnectionException {
        return str;
    }

    public ServiceConnection(String str) {
        URL url;
        try {
            url = new URL(str);
        } catch (MalformedURLException e) {
            if (ApplicationAgent.b) {
                String str2 = TAG;
                Log.e(str2, "URL error: " + e);
            }
            url = null;
        }
        init(url);
    }

    private void init(URL url) {
        this.mUseGet = false;
        if (checkURL(url)) {
            this.mUrl = url;
        }
    }

    public JSONObject getResponse() {
        return this.mResponse;
    }

    public String getStringResponse() {
        return this.mString;
    }

    public Parameter getParameter() {
        return this.mParameter;
    }

    public void setUseGet(boolean z) {
        this.mUseGet = z;
    }

    public void setPostData(byte[] bArr) {
        this.mPostData = bArr;
    }

    public NetworkError requestJSON() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        NetworkError request = request(new MemoryResetableOutputStream(byteArrayOutputStream));
        try {
            if (request == NetworkError.OK) {
                String str = TAG;
                LogUtil.b(str, "baos.toString():" + byteArrayOutputStream.toString());
                this.mResponse = new JSONObject(byteArrayOutputStream.toString());
            } else if (ApplicationAgent.b) {
                String str2 = TAG;
                LogUtil.b(str2, "ServiceConnection failed : " + request);
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused) {
            }
            return request;
        } catch (JSONException e) {
            if (ApplicationAgent.b) {
                String str3 = TAG;
                LogUtil.b(str3, "JSON error: " + e);
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

    public NetworkError requestString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        NetworkError request = request(new MemoryResetableOutputStream(byteArrayOutputStream));
        if (request == NetworkError.OK) {
            this.mString = byteArrayOutputStream.toString();
            if (ApplicationAgent.b) {
                Log.d(TAG, this.mString);
            }
        } else if (ApplicationAgent.b) {
            String str = TAG;
            Log.e(str, "ServiceConnection failed : " + request);
        }
        try {
            byteArrayOutputStream.close();
        } catch (IOException unused) {
        }
        return request;
    }

    public NetworkError requestFile(File file) throws FileNotFoundException {
        if (file != null) {
            LogUtil.b(TAG, String.format("outFile:%s", new Object[]{file.getAbsolutePath()}));
            try {
                FileResetableOutputStream fileResetableOutputStream = new FileResetableOutputStream(file);
                NetworkError request = request(fileResetableOutputStream);
                try {
                    fileResetableOutputStream.close();
                    if (request != NetworkError.OK) {
                        if (ApplicationAgent.b) {
                            String str = TAG;
                            Log.e(str, "ServiceConnection failed : " + request);
                        }
                        file.delete();
                    }
                } catch (IOException unused) {
                }
                return request;
            } catch (FileNotFoundException e) {
                if (ApplicationAgent.b) {
                    String str2 = TAG;
                    Log.e(str2, "File not found: " + e);
                }
                throw e;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    /* access modifiers changed from: protected */
    public NetworkError request(ResetableOutputStream resetableOutputStream) {
        LogUtil.b(TAG, "ServiceConnection.request mUrl:" + this.mUrl);
        if (this.mUrl == null) {
            return NetworkError.URL_ERROR;
        }
        if (!Utils.Network.isNetWorkConnected(ApplicationAgent.f5586a)) {
            return NetworkError.NETWORK_ERROR;
        }
        if (this.mParameter == null) {
            this.mParameter = new Parameter(this);
        }
        try {
            Parameter onQueryCreated = onQueryCreated(this.mParameter);
            String url = this.mUrl.toString();
            if (this.mUseGet && !onQueryCreated.isEmpty()) {
                String query = this.mUrl.getQuery();
                String url2 = this.mUrl.toString();
                if (TextUtils.isEmpty(query)) {
                    url = url2 + "?" + onQueryCreated.toEncodedString();
                } else {
                    url = url2 + a.b + onQueryCreated.toEncodedString();
                }
            }
            try {
                String onURLCreated = onURLCreated(url, onQueryCreated);
                if (ApplicationAgent.b) {
                    Log.d(TAG, "connection url: " + onURLCreated);
                }
                if (!this.mUseGet) {
                    if (this.mPostData != null && this.mPostData.length > 0) {
                        this.mContentType = "application/octet-stream";
                    } else if (!onQueryCreated.isEmpty()) {
                        this.mPostData = onQueryCreated.toEncodedString().getBytes();
                        if (ApplicationAgent.b) {
                            Log.d(TAG, "[post]" + onQueryCreated);
                        }
                    }
                    if (this.mPostData == null || this.mPostData.length == 0) {
                        return NetworkError.CLIENT_ERROR;
                    }
                }
                long currentTimeMillis = System.currentTimeMillis();
                NetworkError innerRequest = innerRequest(onURLCreated, this.mPostData, this.mUseGet, resetableOutputStream);
                if (ApplicationAgent.b) {
                    long currentTimeMillis2 = System.currentTimeMillis();
                    if (ApplicationAgent.b) {
                        Log.d(TAG, "Time(ms) spent in request: " + (currentTimeMillis2 - currentTimeMillis) + ", " + onURLCreated);
                    }
                }
                return innerRequest;
            } catch (ConnectionException e) {
                return e.mError;
            }
        } catch (ConnectionException e2) {
            return e2.mError;
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

    private void trustAllHosts() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init((KeyManager[]) null, this.xtmArray, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: type inference failed for: r0v1 */
    /* JADX WARNING: type inference failed for: r0v2, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v5, types: [java.io.BufferedInputStream] */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0184  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0107 A[Catch:{ all -> 0x0100 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x012e A[SYNTHETIC, Splitter:B:75:0x012e] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0134 A[Catch:{ Exception -> 0x0141, all -> 0x013e }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x013a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.mi.global.shop.base.request.ServiceConnection.NetworkError innerRequest(java.lang.String r6, byte[] r7, boolean r8, com.mi.global.shop.base.request.ServiceConnection.ResetableOutputStream r9) {
        /*
            r5 = this;
            boolean r0 = com.mi.global.shop.base.ApplicationAgent.b
            if (r0 == 0) goto L_0x001a
            java.lang.String r0 = TAG
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
            boolean r1 = com.mi.global.shop.base.ApplicationAgent.b
            if (r1 == 0) goto L_0x003c
            java.lang.String r1 = TAG
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
            java.lang.String r6 = r1.getProtocol()     // Catch:{ Exception -> 0x0150 }
            java.lang.String r2 = "https"
            boolean r6 = r6.equalsIgnoreCase(r2)     // Catch:{ Exception -> 0x0150 }
            if (r6 == 0) goto L_0x0062
            r5.trustAllHosts()     // Catch:{ Exception -> 0x0150 }
            java.net.URLConnection r6 = r1.openConnection()     // Catch:{ Exception -> 0x0150 }
            javax.net.ssl.HttpsURLConnection r6 = (javax.net.ssl.HttpsURLConnection) r6     // Catch:{ Exception -> 0x0150 }
            r2 = r6
            javax.net.ssl.HttpsURLConnection r2 = (javax.net.ssl.HttpsURLConnection) r2     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            javax.net.ssl.HostnameVerifier r3 = r5.DO_NOT_VERIFY     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            r2.setHostnameVerifier(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            goto L_0x0068
        L_0x005b:
            r7 = move-exception
            goto L_0x0182
        L_0x005e:
            r7 = move-exception
            r0 = r6
            goto L_0x0151
        L_0x0062:
            java.net.URLConnection r6 = r1.openConnection()     // Catch:{ Exception -> 0x0150 }
            java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6     // Catch:{ Exception -> 0x0150 }
        L_0x0068:
            r2 = 10000(0x2710, float:1.4013E-41)
            r6.setConnectTimeout(r2)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            android.app.Application r3 = com.mi.global.shop.base.ApplicationAgent.f5586a     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            boolean r3 = com.mi.global.shop.base.utils.Utils.Network.isWifiConnected(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            if (r3 == 0) goto L_0x0079
            r6.setReadTimeout(r2)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            goto L_0x007e
        L_0x0079:
            r2 = 30000(0x7530, float:4.2039E-41)
            r6.setReadTimeout(r2)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
        L_0x007e:
            r2 = 0
            if (r8 == 0) goto L_0x008a
            java.lang.String r3 = "GET"
            r6.setRequestMethod(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            r6.setDoOutput(r2)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            goto L_0x00b4
        L_0x008a:
            java.lang.String r3 = "POST"
            r6.setRequestMethod(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            r3 = 1
            r6.setDoOutput(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            r6.setUseCaches(r2)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            if (r7 == 0) goto L_0x00a5
            int r3 = r7.length     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            if (r3 <= 0) goto L_0x00a5
            java.lang.String r3 = "Content-Length"
            int r4 = r7.length     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            r6.setRequestProperty(r3, r4)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
        L_0x00a5:
            java.lang.String r3 = r5.mContentType     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            if (r3 != 0) goto L_0x00b4
            java.lang.String r3 = "Content-Type"
            java.lang.String r4 = r5.mContentType     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            r6.setRequestProperty(r3, r4)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
        L_0x00b4:
            java.net.HttpURLConnection r3 = r5.onConnectionCreated(r6)     // Catch:{ ConnectionException -> 0x0144 }
            r3.connect()     // Catch:{ Exception -> 0x0141, all -> 0x013e }
            if (r8 != 0) goto L_0x00cc
            if (r7 == 0) goto L_0x00cc
            int r6 = r7.length     // Catch:{ Exception -> 0x0141, all -> 0x013e }
            if (r6 <= 0) goto L_0x00cc
            java.io.OutputStream r6 = r3.getOutputStream()     // Catch:{ Exception -> 0x0141, all -> 0x013e }
            r6.write(r7)     // Catch:{ Exception -> 0x0141, all -> 0x013e }
            r6.close()     // Catch:{ Exception -> 0x0141, all -> 0x013e }
        L_0x00cc:
            int r6 = r3.getResponseCode()     // Catch:{ Exception -> 0x0141, all -> 0x013e }
            com.mi.global.shop.base.request.ServiceConnection$NetworkError r6 = r5.handleResponseCode(r6)     // Catch:{ Exception -> 0x0141, all -> 0x013e }
            com.mi.global.shop.base.request.ServiceConnection$NetworkError r7 = com.mi.global.shop.base.request.ServiceConnection.NetworkError.OK     // Catch:{ Exception -> 0x0141, all -> 0x013e }
            if (r6 != r7) goto L_0x0138
            if (r9 == 0) goto L_0x0138
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0102 }
            java.io.InputStream r8 = r3.getInputStream()     // Catch:{ Exception -> 0x0102 }
            r4 = 8192(0x2000, float:1.14794E-41)
            r7.<init>(r8, r4)     // Catch:{ Exception -> 0x0102 }
            r8 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r8]     // Catch:{ Exception -> 0x00fd, all -> 0x00fa }
        L_0x00e9:
            int r4 = r7.read(r0, r2, r8)     // Catch:{ Exception -> 0x00fd, all -> 0x00fa }
            if (r4 <= 0) goto L_0x00f3
            r9.write(r0, r2, r4)     // Catch:{ Exception -> 0x00fd, all -> 0x00fa }
            goto L_0x00e9
        L_0x00f3:
            r9.flush()     // Catch:{ Exception -> 0x00fd, all -> 0x00fa }
            r7.close()     // Catch:{ Exception -> 0x0141, all -> 0x013e }
            goto L_0x0138
        L_0x00fa:
            r6 = move-exception
            r0 = r7
            goto L_0x0132
        L_0x00fd:
            r8 = move-exception
            r0 = r7
            goto L_0x0103
        L_0x0100:
            r6 = move-exception
            goto L_0x0132
        L_0x0102:
            r8 = move-exception
        L_0x0103:
            boolean r7 = com.mi.global.shop.base.ApplicationAgent.b     // Catch:{ all -> 0x0100 }
            if (r7 == 0) goto L_0x0129
            java.lang.String r7 = TAG     // Catch:{ all -> 0x0100 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0100 }
            r2.<init>()     // Catch:{ all -> 0x0100 }
            java.lang.String r4 = "ServiceConnection Exception for "
            r2.append(r4)     // Catch:{ all -> 0x0100 }
            java.lang.String r4 = r1.getHost()     // Catch:{ all -> 0x0100 }
            r2.append(r4)     // Catch:{ all -> 0x0100 }
            java.lang.String r4 = " : read file stream error "
            r2.append(r4)     // Catch:{ all -> 0x0100 }
            r2.append(r8)     // Catch:{ all -> 0x0100 }
            java.lang.String r8 = r2.toString()     // Catch:{ all -> 0x0100 }
            android.util.Log.e(r7, r8)     // Catch:{ all -> 0x0100 }
        L_0x0129:
            r9.reset()     // Catch:{ all -> 0x0100 }
            if (r0 == 0) goto L_0x0138
            r0.close()     // Catch:{ Exception -> 0x0141, all -> 0x013e }
            goto L_0x0138
        L_0x0132:
            if (r0 == 0) goto L_0x0137
            r0.close()     // Catch:{ Exception -> 0x0141, all -> 0x013e }
        L_0x0137:
            throw r6     // Catch:{ Exception -> 0x0141, all -> 0x013e }
        L_0x0138:
            if (r3 == 0) goto L_0x013d
            r3.disconnect()
        L_0x013d:
            return r6
        L_0x013e:
            r7 = move-exception
            r6 = r3
            goto L_0x0182
        L_0x0141:
            r7 = move-exception
            r0 = r3
            goto L_0x0151
        L_0x0144:
            r7 = move-exception
            com.mi.global.shop.base.request.ServiceConnection$NetworkError r7 = r7.mError     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            if (r6 == 0) goto L_0x014c
            r6.disconnect()
        L_0x014c:
            return r7
        L_0x014d:
            r7 = move-exception
            r6 = r0
            goto L_0x0182
        L_0x0150:
            r7 = move-exception
        L_0x0151:
            boolean r6 = com.mi.global.shop.base.ApplicationAgent.b     // Catch:{ all -> 0x014d }
            if (r6 == 0) goto L_0x0177
            java.lang.String r6 = TAG     // Catch:{ all -> 0x014d }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x014d }
            r8.<init>()     // Catch:{ all -> 0x014d }
            java.lang.String r9 = "ServiceConnection Exception for "
            r8.append(r9)     // Catch:{ all -> 0x014d }
            java.lang.String r9 = r1.getHost()     // Catch:{ all -> 0x014d }
            r8.append(r9)     // Catch:{ all -> 0x014d }
            java.lang.String r9 = " :"
            r8.append(r9)     // Catch:{ all -> 0x014d }
            r8.append(r7)     // Catch:{ all -> 0x014d }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x014d }
            android.util.Log.e(r6, r8)     // Catch:{ all -> 0x014d }
        L_0x0177:
            r7.printStackTrace()     // Catch:{ all -> 0x014d }
            if (r0 == 0) goto L_0x017f
            r0.disconnect()
        L_0x017f:
            com.mi.global.shop.base.request.ServiceConnection$NetworkError r6 = com.mi.global.shop.base.request.ServiceConnection.NetworkError.NETWORK_ERROR
            return r6
        L_0x0182:
            if (r6 == 0) goto L_0x0187
            r6.disconnect()
        L_0x0187:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.base.request.ServiceConnection.innerRequest(java.lang.String, byte[], boolean, com.mi.global.shop.base.request.ServiceConnection$ResetableOutputStream):com.mi.global.shop.base.request.ServiceConnection$NetworkError");
    }

    /* access modifiers changed from: protected */
    public boolean checkURL(URL url) {
        if (url == null) {
            return false;
        }
        if (TextUtils.equals(url.getProtocol(), "http") || TextUtils.equals(url.getProtocol(), "https")) {
            return true;
        }
        return false;
    }

    private NetworkError handleResponseCode(int i) {
        if (i == 200) {
            return NetworkError.OK;
        }
        if (ApplicationAgent.b) {
            String str = TAG;
            Log.e(str, "Network Error : " + i);
        }
        return NetworkError.SERVER_ERROR;
    }

    /* access modifiers changed from: private */
    public static StringBuilder appendParameter(StringBuilder sb, String str, String str2, String str3) {
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
    public static StringBuilder appendParameter(StringBuilder sb, String str, String str2) {
        if (sb.length() > 0) {
            sb.append(a.b);
        }
        sb.append(str);
        sb.append("=");
        sb.append(str2);
        return sb;
    }

    public class Parameter {
        private boolean disallowEmptyValue;
        private TreeMap<String, String> params;

        public Parameter(ServiceConnection serviceConnection) {
            this(true);
        }

        public Parameter(boolean z) {
            this.disallowEmptyValue = false;
            this.params = new TreeMap<>();
            this.disallowEmptyValue = false;
            if (z) {
                ServiceConnection.this.mParameter = this;
            }
        }

        public void setDisallowEmptyValue(boolean z) {
            this.disallowEmptyValue = z;
        }

        public Parameter add(String str, String str2) {
            if (TextUtils.isEmpty(str2)) {
                if (this.disallowEmptyValue) {
                    return this;
                }
                str2 = "";
            }
            this.params.put(str, str2);
            return this;
        }

        public Parameter add(String str, Object obj) {
            if (obj == null) {
                if (this.disallowEmptyValue) {
                    return this;
                }
                obj = "";
            }
            this.params.put(str, String.valueOf(obj));
            return this;
        }

        public Parameter add(String str, boolean z) {
            this.params.put(str, String.valueOf(z));
            return this;
        }

        public Parameter add(String str, int i) {
            this.params.put(str, String.valueOf(i));
            return this;
        }

        public String get(String str) {
            return this.params.get(str);
        }

        public boolean isEmpty() {
            return this.params.isEmpty();
        }

        public String toString() {
            if (this.params.isEmpty()) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (String next : this.params.keySet()) {
                sb = ServiceConnection.appendParameter(sb, next, this.params.get(next));
            }
            return sb.toString();
        }

        public String toEncodedString() {
            return toEncodedString("UTF-8");
        }

        public String toEncodedString(String str) {
            if (this.params.isEmpty()) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (String next : this.params.keySet()) {
                sb = ServiceConnection.appendParameter(sb, next, this.params.get(next), str);
            }
            return sb.toString();
        }

        public TreeMap<String, String> getParams() {
            return this.params;
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
        protected OutputStream mOutputStream;

        public abstract void reset();

        public ResetableOutputStream(OutputStream outputStream) {
            if (outputStream != null) {
                this.mOutputStream = outputStream;
                return;
            }
            throw new IllegalArgumentException("outputstream is null");
        }

        public void close() throws IOException {
            this.mOutputStream.close();
        }

        public void flush() throws IOException {
            this.mOutputStream.flush();
        }

        public void write(byte[] bArr) throws IOException {
            this.mOutputStream.write(bArr);
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.mOutputStream.write(bArr, i, i2);
        }

        public void write(int i) throws IOException {
            this.mOutputStream.write(i);
        }
    }

    protected class MemoryResetableOutputStream extends ResetableOutputStream {
        public MemoryResetableOutputStream(ByteArrayOutputStream byteArrayOutputStream) {
            super(byteArrayOutputStream);
        }

        public void reset() {
            ((ByteArrayOutputStream) this.mOutputStream).reset();
        }
    }

    protected class FileResetableOutputStream extends ResetableOutputStream {
        private File mFile;

        public FileResetableOutputStream(File file) throws FileNotFoundException {
            super(new FileOutputStream(file));
            this.mFile = file;
        }

        public void reset() {
            try {
                this.mOutputStream.close();
            } catch (IOException unused) {
            }
            this.mFile.delete();
            try {
                this.mOutputStream = new FileOutputStream(this.mFile);
            } catch (FileNotFoundException unused2) {
            }
        }
    }
}
