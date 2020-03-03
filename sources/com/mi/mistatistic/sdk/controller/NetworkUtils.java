package com.mi.mistatistic.sdk.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.mi.mistatistic.sdk.BuildSetting;
import com.mi.mistatistic.sdk.CustomSettings;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public abstract class NetworkUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7335a = "10.0.0.172";
    public static final int b = 80;
    public static final String c = "X-Online-Host";
    public static final int d = 10000;
    public static final int e = 15000;
    private static final String f = "mistats_sdkconfig_jafej!@#)(*e@!#";

    public interface IUploadCallback {
        void a(String str);
    }

    public static boolean a(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && 1 == activeNetworkInfo.getType()) {
                    return true;
                }
                return false;
            } catch (Exception unused) {
                return false;
            }
        } catch (Exception unused2) {
            return false;
        }
    }

    public static String b(Context context) {
        if (a(context)) {
            return "WIFI";
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return "";
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    return "";
                }
                return (activeNetworkInfo.getTypeName() + "-" + activeNetworkInfo.getSubtypeName() + "-" + activeNetworkInfo.getExtraInfo()).toLowerCase();
            } catch (Exception unused) {
                return "";
            }
        } catch (Exception unused2) {
            return "";
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: com.mi.mistatistic.sdk.model.Stat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: com.mi.mistatistic.sdk.model.Stat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: com.mi.mistatistic.sdk.model.Stat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v16, resolved type: com.mi.mistatistic.sdk.model.Stat} */
    /* JADX WARNING: type inference failed for: r5v2, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r5v7 */
    /* JADX WARNING: type inference failed for: r5v11 */
    /* JADX WARNING: type inference failed for: r5v14 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009c A[SYNTHETIC, Splitter:B:36:0x009c] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a1 A[Catch:{ IOException -> 0x00a4 }] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String r4, com.mi.mistatistic.sdk.model.Stat r5, com.mi.mistatistic.sdk.controller.NetworkUtils.IUploadCallback r6) throws java.io.IOException {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 != 0) goto L_0x00a5
            r0 = 0
            android.content.Context r1 = com.mi.mistatistic.sdk.controller.ApplicationContextHolder.a()     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            r2.<init>(r4)     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            java.net.HttpURLConnection r4 = a(r1, r2)     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            r1 = 10000(0x2710, float:1.4013E-41)
            r4.setConnectTimeout(r1)     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            r1 = 15000(0x3a98, float:2.102E-41)
            r4.setReadTimeout(r1)     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            java.lang.String r1 = "POST"
            r4.setRequestMethod(r1)     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            r1 = 1
            r4.setDoOutput(r1)     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            byte[] r5 = r5.encode()     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            java.io.OutputStream r1 = r4.getOutputStream()     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            r2 = 0
            int r3 = r5.length     // Catch:{ IOException -> 0x0083, Throwable -> 0x007f, all -> 0x007b }
            r1.write(r5, r2, r3)     // Catch:{ IOException -> 0x0083, Throwable -> 0x007f, all -> 0x007b }
            r1.flush()     // Catch:{ IOException -> 0x0083, Throwable -> 0x007f, all -> 0x007b }
            r1.close()     // Catch:{ IOException -> 0x0083, Throwable -> 0x007f, all -> 0x007b }
            r4.getResponseCode()     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            com.mi.mistatistic.sdk.controller.NetworkUtils$DoneHandlerInputStream r2 = new com.mi.mistatistic.sdk.controller.NetworkUtils$DoneHandlerInputStream     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            java.io.InputStream r4 = r4.getInputStream()     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            r2.<init>(r4)     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            r5.<init>(r1)     // Catch:{ IOException -> 0x0096, Throwable -> 0x008a, all -> 0x0087 }
            java.lang.String r4 = r5.readLine()     // Catch:{ IOException -> 0x0079, Throwable -> 0x0077 }
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ IOException -> 0x0079, Throwable -> 0x0077 }
            r1.<init>()     // Catch:{ IOException -> 0x0079, Throwable -> 0x0077 }
            java.lang.String r2 = "line.separator"
            java.lang.String r2 = java.lang.System.getProperty(r2)     // Catch:{ IOException -> 0x0079, Throwable -> 0x0077 }
        L_0x005f:
            if (r4 == 0) goto L_0x006c
            r1.append(r4)     // Catch:{ IOException -> 0x0079, Throwable -> 0x0077 }
            r1.append(r2)     // Catch:{ IOException -> 0x0079, Throwable -> 0x0077 }
            java.lang.String r4 = r5.readLine()     // Catch:{ IOException -> 0x0079, Throwable -> 0x0077 }
            goto L_0x005f
        L_0x006c:
            java.lang.String r4 = r1.toString()     // Catch:{ IOException -> 0x0079, Throwable -> 0x0077 }
            r6.a(r4)     // Catch:{ IOException -> 0x0079, Throwable -> 0x0077 }
            r5.close()     // Catch:{ IOException -> 0x0079, Throwable -> 0x0077 }
            return
        L_0x0077:
            r4 = move-exception
            goto L_0x008c
        L_0x0079:
            r4 = move-exception
            goto L_0x0098
        L_0x007b:
            r4 = move-exception
            r5 = r0
            r0 = r1
            goto L_0x009a
        L_0x007f:
            r4 = move-exception
            r5 = r0
            r0 = r1
            goto L_0x008c
        L_0x0083:
            r4 = move-exception
            r5 = r0
            r0 = r1
            goto L_0x0098
        L_0x0087:
            r4 = move-exception
            r5 = r0
            goto L_0x009a
        L_0x008a:
            r4 = move-exception
            r5 = r0
        L_0x008c:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x0099 }
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x0099 }
            r6.<init>(r4)     // Catch:{ all -> 0x0099 }
            throw r6     // Catch:{ all -> 0x0099 }
        L_0x0096:
            r4 = move-exception
            r5 = r0
        L_0x0098:
            throw r4     // Catch:{ all -> 0x0099 }
        L_0x0099:
            r4 = move-exception
        L_0x009a:
            if (r0 == 0) goto L_0x009f
            r0.close()     // Catch:{ IOException -> 0x00a4 }
        L_0x009f:
            if (r5 == 0) goto L_0x00a4
            r5.close()     // Catch:{ IOException -> 0x00a4 }
        L_0x00a4:
            throw r4
        L_0x00a5:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "url"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.mistatistic.sdk.controller.NetworkUtils.a(java.lang.String, com.mi.mistatistic.sdk.model.Stat, com.mi.mistatistic.sdk.controller.NetworkUtils$IUploadCallback):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v15, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v19, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r5v2, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r5v7 */
    /* JADX WARNING: type inference failed for: r5v14 */
    /* JADX WARNING: type inference failed for: r5v17 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a8 A[SYNTHETIC, Splitter:B:38:0x00a8] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ad A[Catch:{ IOException -> 0x00b0 }] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r4, java.lang.String r5, java.util.Map<java.lang.String, java.lang.String> r6, com.mi.mistatistic.sdk.controller.NetworkUtils.IUploadCallback r7) throws java.io.IOException {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 != 0) goto L_0x00b1
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ IOException -> 0x00a2, Throwable -> 0x0096, all -> 0x0093 }
            r1.<init>(r5)     // Catch:{ IOException -> 0x00a2, Throwable -> 0x0096, all -> 0x0093 }
            java.net.HttpURLConnection r4 = a(r4, r1)     // Catch:{ IOException -> 0x00a2, Throwable -> 0x0096, all -> 0x0093 }
            r5 = 10000(0x2710, float:1.4013E-41)
            r4.setConnectTimeout(r5)     // Catch:{ IOException -> 0x00a2, Throwable -> 0x0096, all -> 0x0093 }
            r5 = 15000(0x3a98, float:2.102E-41)
            r4.setReadTimeout(r5)     // Catch:{ IOException -> 0x00a2, Throwable -> 0x0096, all -> 0x0093 }
            java.lang.String r5 = "POST"
            r4.setRequestMethod(r5)     // Catch:{ IOException -> 0x00a2, Throwable -> 0x0096, all -> 0x0093 }
            a((java.util.Map<java.lang.String, java.lang.String>) r6)     // Catch:{ IOException -> 0x00a2, Throwable -> 0x0096, all -> 0x0093 }
            java.io.OutputStream r5 = r4.getOutputStream()     // Catch:{ IOException -> 0x00a2, Throwable -> 0x0096, all -> 0x0093 }
            java.lang.String r6 = b((java.util.Map<java.lang.String, java.lang.String>) r6)     // Catch:{ IOException -> 0x008e, Throwable -> 0x0089, all -> 0x0084 }
            boolean r1 = android.text.TextUtils.isEmpty(r6)     // Catch:{ IOException -> 0x008e, Throwable -> 0x0089, all -> 0x0084 }
            if (r1 != 0) goto L_0x003d
            r1 = 1
            r4.setDoOutput(r1)     // Catch:{ IOException -> 0x008e, Throwable -> 0x0089, all -> 0x0084 }
            byte[] r6 = r6.getBytes()     // Catch:{ IOException -> 0x008e, Throwable -> 0x0089, all -> 0x0084 }
            r1 = 0
            int r2 = r6.length     // Catch:{ IOException -> 0x008e, Throwable -> 0x0089, all -> 0x0084 }
            r5.write(r6, r1, r2)     // Catch:{ IOException -> 0x008e, Throwable -> 0x0089, all -> 0x0084 }
        L_0x003d:
            r5.flush()     // Catch:{ IOException -> 0x008e, Throwable -> 0x0089, all -> 0x0084 }
            r5.close()     // Catch:{ IOException -> 0x008e, Throwable -> 0x0089, all -> 0x0084 }
            r4.getResponseCode()     // Catch:{ IOException -> 0x00a2, Throwable -> 0x0096, all -> 0x0093 }
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ IOException -> 0x00a2, Throwable -> 0x0096, all -> 0x0093 }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x00a2, Throwable -> 0x0096, all -> 0x0093 }
            com.mi.mistatistic.sdk.controller.NetworkUtils$DoneHandlerInputStream r1 = new com.mi.mistatistic.sdk.controller.NetworkUtils$DoneHandlerInputStream     // Catch:{ IOException -> 0x00a2, Throwable -> 0x0096, all -> 0x0093 }
            java.io.InputStream r4 = r4.getInputStream()     // Catch:{ IOException -> 0x00a2, Throwable -> 0x0096, all -> 0x0093 }
            r1.<init>(r4)     // Catch:{ IOException -> 0x00a2, Throwable -> 0x0096, all -> 0x0093 }
            r6.<init>(r1)     // Catch:{ IOException -> 0x00a2, Throwable -> 0x0096, all -> 0x0093 }
            r5.<init>(r6)     // Catch:{ IOException -> 0x00a2, Throwable -> 0x0096, all -> 0x0093 }
            java.lang.String r4 = r5.readLine()     // Catch:{ IOException -> 0x0082, Throwable -> 0x0080 }
            java.lang.StringBuffer r6 = new java.lang.StringBuffer     // Catch:{ IOException -> 0x0082, Throwable -> 0x0080 }
            r6.<init>()     // Catch:{ IOException -> 0x0082, Throwable -> 0x0080 }
            java.lang.String r1 = "line.separator"
            java.lang.String r1 = java.lang.System.getProperty(r1)     // Catch:{ IOException -> 0x0082, Throwable -> 0x0080 }
        L_0x0068:
            if (r4 == 0) goto L_0x0075
            r6.append(r4)     // Catch:{ IOException -> 0x0082, Throwable -> 0x0080 }
            r6.append(r1)     // Catch:{ IOException -> 0x0082, Throwable -> 0x0080 }
            java.lang.String r4 = r5.readLine()     // Catch:{ IOException -> 0x0082, Throwable -> 0x0080 }
            goto L_0x0068
        L_0x0075:
            java.lang.String r4 = r6.toString()     // Catch:{ IOException -> 0x0082, Throwable -> 0x0080 }
            r7.a(r4)     // Catch:{ IOException -> 0x0082, Throwable -> 0x0080 }
            r5.close()     // Catch:{ IOException -> 0x0082, Throwable -> 0x0080 }
            return
        L_0x0080:
            r4 = move-exception
            goto L_0x0098
        L_0x0082:
            r4 = move-exception
            goto L_0x00a4
        L_0x0084:
            r4 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
            goto L_0x00a6
        L_0x0089:
            r4 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
            goto L_0x0098
        L_0x008e:
            r4 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
            goto L_0x00a4
        L_0x0093:
            r4 = move-exception
            r5 = r0
            goto L_0x00a6
        L_0x0096:
            r4 = move-exception
            r5 = r0
        L_0x0098:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x00a5 }
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x00a5 }
            r6.<init>(r4)     // Catch:{ all -> 0x00a5 }
            throw r6     // Catch:{ all -> 0x00a5 }
        L_0x00a2:
            r4 = move-exception
            r5 = r0
        L_0x00a4:
            throw r4     // Catch:{ all -> 0x00a5 }
        L_0x00a5:
            r4 = move-exception
        L_0x00a6:
            if (r0 == 0) goto L_0x00ab
            r0.close()     // Catch:{ IOException -> 0x00b0 }
        L_0x00ab:
            if (r5 == 0) goto L_0x00b0
            r5.close()     // Catch:{ IOException -> 0x00b0 }
        L_0x00b0:
            throw r4
        L_0x00b1:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "url"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.mistatistic.sdk.controller.NetworkUtils.a(android.content.Context, java.lang.String, java.util.Map, com.mi.mistatistic.sdk.controller.NetworkUtils$IUploadCallback):void");
    }

    protected static void a(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        new HashMap();
        if (map != null) {
            ArrayList<String> arrayList = new ArrayList<>(map.keySet());
            Logger.b("mistats_NetworkUtils_keys:" + arrayList.toString());
            Collections.sort(arrayList);
            for (String str : arrayList) {
                sb.append(str + map.get(str));
            }
        }
        sb.append(f);
    }

    public static String b(Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry next : map.entrySet()) {
            if (!(next.getKey() == null || next.getValue() == null)) {
                try {
                    stringBuffer.append(URLEncoder.encode((String) next.getKey(), "UTF-8"));
                    stringBuffer.append("=");
                    stringBuffer.append(URLEncoder.encode((String) next.getValue(), "UTF-8"));
                    stringBuffer.append(a.b);
                } catch (UnsupportedEncodingException unused) {
                    return null;
                }
            }
        }
        if (stringBuffer.length() > 0) {
            stringBuffer = stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    public static final class DoneHandlerInputStream extends FilterInputStream {

        /* renamed from: a  reason: collision with root package name */
        private boolean f7336a;

        public DoneHandlerInputStream(InputStream inputStream) {
            super(inputStream);
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read;
            if (!this.f7336a && (read = super.read(bArr, i, i2)) != -1) {
                return read;
            }
            this.f7336a = true;
            return -1;
        }
    }

    public static HttpURLConnection a(Context context, URL url) throws IOException {
        if (!"http".equals(url.getProtocol())) {
            return (HttpURLConnection) url.openConnection();
        }
        if (d(context)) {
            return (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.200", 80)));
        }
        if (!c(context)) {
            return (HttpURLConnection) url.openConnection();
        }
        String host = url.getHost();
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(a(url)).openConnection();
        httpURLConnection.addRequestProperty("X-Online-Host", host);
        return httpURLConnection;
    }

    public static String a(URL url) {
        StringBuilder sb = new StringBuilder();
        sb.append(url.getProtocol());
        sb.append("://");
        sb.append("10.0.0.172");
        sb.append(url.getPath());
        if (!TextUtils.isEmpty(url.getQuery())) {
            sb.append("?");
            sb.append(url.getQuery());
        }
        return sb.toString();
    }

    public static boolean c(Context context) {
        if (!"CN".equalsIgnoreCase(((TelephonyManager) context.getSystemService("phone")).getSimCountryIso())) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    return false;
                }
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (TextUtils.isEmpty(extraInfo) || extraInfo.length() < 3 || extraInfo.contains("ctwap")) {
                    return false;
                }
                return extraInfo.regionMatches(true, extraInfo.length() - 3, "wap", 0, 3);
            } catch (Exception unused) {
                return false;
            }
        } catch (Exception unused2) {
            return false;
        }
    }

    public static boolean d(Context context) {
        if (!"CN".equalsIgnoreCase(((TelephonyManager) context.getSystemService("phone")).getSimCountryIso())) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    return false;
                }
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (TextUtils.isEmpty(extraInfo) || extraInfo.length() < 3 || !extraInfo.contains("ctwap")) {
                    return false;
                }
                return true;
            } catch (Exception unused) {
                return false;
            }
        } catch (Exception unused2) {
            return false;
        }
    }

    public static void a(String str, Map<String, String> map, IUploadCallback iUploadCallback) throws IOException {
        if (!CustomSettings.d()) {
            Logger.a("upload is disabled.", (Throwable) null);
            return;
        }
        Logger.a("Uploading to: " + str);
        if (map != null) {
            map.put("bc", BuildSetting.e());
        }
        Logger.a("Uploading under app network");
        a(ApplicationContextHolder.a(), str, map, iUploadCallback);
    }

    @SuppressLint({"NewApi"})
    public static boolean a() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) ApplicationContextHolder.a().getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 1) {
                    return true;
                }
                if (Build.VERSION.SDK_INT >= 16) {
                    return !connectivityManager.isActiveNetworkMetered();
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String a(String str, File file, String str2) throws IOException {
        return a(str, (Map<String, String>) null, file, str2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: java.util.Map<java.lang.String, java.lang.String>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: java.util.Map<java.lang.String, java.lang.String>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v12, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v19, resolved type: java.util.Map<java.lang.String, java.lang.String>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v21, resolved type: java.util.Map<java.lang.String, java.lang.String>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v28, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r6v1, types: [java.io.DataOutputStream] */
    /* JADX WARNING: type inference failed for: r0v2, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r6v7 */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: type inference failed for: r0v14 */
    /* JADX WARNING: type inference failed for: r6v17 */
    /* JADX WARNING: type inference failed for: r0v24 */
    /* JADX WARNING: type inference failed for: r6v20 */
    /* JADX WARNING: type inference failed for: r6v22 */
    /* JADX WARNING: type inference failed for: r0v27 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0153 A[SYNTHETIC, Splitter:B:62:0x0153] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x015b A[Catch:{ IOException -> 0x0157 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0160 A[Catch:{ IOException -> 0x0157 }] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r5, java.util.Map<java.lang.String, java.lang.String> r6, java.io.File r7, java.lang.String r8) throws java.io.IOException {
        /*
            boolean r0 = r7.exists()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.String r0 = r7.getName()
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            r2.<init>(r5)     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            java.net.URLConnection r5 = r2.openConnection()     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            r2 = 15000(0x3a98, float:2.102E-41)
            r5.setReadTimeout(r2)     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            r2 = 10000(0x2710, float:1.4013E-41)
            r5.setConnectTimeout(r2)     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            r2 = 1
            r5.setDoInput(r2)     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            r5.setDoOutput(r2)     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            r2 = 0
            r5.setUseCaches(r2)     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            java.lang.String r3 = "POST"
            r5.setRequestMethod(r3)     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            java.lang.String r3 = "Connection"
            java.lang.String r4 = "Keep-Alive"
            r5.setRequestProperty(r3, r4)     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            java.lang.String r3 = "Content-Type"
            java.lang.String r4 = "multipart/form-data;boundary=*****"
            r5.setRequestProperty(r3, r4)     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            if (r6 == 0) goto L_0x0065
            java.util.Set r6 = r6.entrySet()     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
        L_0x0049:
            boolean r3 = r6.hasNext()     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            if (r3 == 0) goto L_0x0065
            java.lang.Object r3 = r6.next()     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            java.lang.Object r4 = r3.getKey()     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            java.lang.Object r3 = r3.getValue()     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            r5.setRequestProperty(r4, r3)     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            goto L_0x0049
        L_0x0065:
            int r6 = r0.length()     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            int r6 = r6 + 77
            long r3 = r7.length()     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            int r0 = (int) r3     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            int r6 = r6 + r0
            int r0 = r8.length()     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            int r6 = r6 + r0
            r5.setFixedLengthStreamingMode(r6)     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            java.io.DataOutputStream r6 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            java.io.OutputStream r0 = r5.getOutputStream()     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            r6.<init>(r0)     // Catch:{ IOException -> 0x014c, Throwable -> 0x013f, all -> 0x013b }
            java.lang.String r0 = "--*****\r\n"
            r6.writeBytes(r0)     // Catch:{ IOException -> 0x0138, Throwable -> 0x0135, all -> 0x0132 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0138, Throwable -> 0x0135, all -> 0x0132 }
            r0.<init>()     // Catch:{ IOException -> 0x0138, Throwable -> 0x0135, all -> 0x0132 }
            java.lang.String r3 = "Content-Disposition: form-data; name=\""
            r0.append(r3)     // Catch:{ IOException -> 0x0138, Throwable -> 0x0135, all -> 0x0132 }
            r0.append(r8)     // Catch:{ IOException -> 0x0138, Throwable -> 0x0135, all -> 0x0132 }
            java.lang.String r8 = "\";filename=\""
            r0.append(r8)     // Catch:{ IOException -> 0x0138, Throwable -> 0x0135, all -> 0x0132 }
            java.lang.String r8 = r7.getName()     // Catch:{ IOException -> 0x0138, Throwable -> 0x0135, all -> 0x0132 }
            r0.append(r8)     // Catch:{ IOException -> 0x0138, Throwable -> 0x0135, all -> 0x0132 }
            java.lang.String r8 = "\""
            r0.append(r8)     // Catch:{ IOException -> 0x0138, Throwable -> 0x0135, all -> 0x0132 }
            java.lang.String r8 = "\r\n"
            r0.append(r8)     // Catch:{ IOException -> 0x0138, Throwable -> 0x0135, all -> 0x0132 }
            java.lang.String r8 = r0.toString()     // Catch:{ IOException -> 0x0138, Throwable -> 0x0135, all -> 0x0132 }
            r6.writeBytes(r8)     // Catch:{ IOException -> 0x0138, Throwable -> 0x0135, all -> 0x0132 }
            java.lang.String r8 = "\r\n"
            r6.writeBytes(r8)     // Catch:{ IOException -> 0x0138, Throwable -> 0x0135, all -> 0x0132 }
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0138, Throwable -> 0x0135, all -> 0x0132 }
            r8.<init>(r7)     // Catch:{ IOException -> 0x0138, Throwable -> 0x0135, all -> 0x0132 }
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
        L_0x00bf:
            int r0 = r8.read(r7)     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
            r3 = -1
            if (r0 == r3) goto L_0x00cd
            r6.write(r7, r2, r0)     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
            r6.flush()     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
            goto L_0x00bf
        L_0x00cd:
            java.lang.String r7 = "\r\n"
            r6.writeBytes(r7)     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
            java.lang.String r7 = "--"
            r6.writeBytes(r7)     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
            java.lang.String r7 = "*****"
            r6.writeBytes(r7)     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
            java.lang.String r7 = "--"
            r6.writeBytes(r7)     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
            java.lang.String r7 = "\r\n"
            r6.writeBytes(r7)     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
            r6.flush()     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
            java.lang.StringBuffer r7 = new java.lang.StringBuffer     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
            r7.<init>()     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
            com.mi.mistatistic.sdk.controller.NetworkUtils$DoneHandlerInputStream r3 = new com.mi.mistatistic.sdk.controller.NetworkUtils$DoneHandlerInputStream     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
            java.io.InputStream r5 = r5.getInputStream()     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
            r3.<init>(r5)     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
            r0.<init>(r2)     // Catch:{ IOException -> 0x012e, Throwable -> 0x012a, all -> 0x0126 }
        L_0x0101:
            java.lang.String r5 = r0.readLine()     // Catch:{ IOException -> 0x0124, Throwable -> 0x0122, all -> 0x0120 }
            if (r5 == 0) goto L_0x010b
            r7.append(r5)     // Catch:{ IOException -> 0x0124, Throwable -> 0x0122, all -> 0x0120 }
            goto L_0x0101
        L_0x010b:
            java.lang.String r5 = r7.toString()     // Catch:{ IOException -> 0x0124, Throwable -> 0x0122, all -> 0x0120 }
            r8.close()     // Catch:{ IOException -> 0x0119 }
            r6.close()     // Catch:{ IOException -> 0x0119 }
            r0.close()     // Catch:{ IOException -> 0x0119 }
            goto L_0x011f
        L_0x0119:
            r6 = move-exception
            java.lang.String r7 = ""
            com.mi.mistatistic.sdk.controller.Logger.a((java.lang.String) r7, (java.lang.Throwable) r6)
        L_0x011f:
            return r5
        L_0x0120:
            r5 = move-exception
            goto L_0x0128
        L_0x0122:
            r5 = move-exception
            goto L_0x012c
        L_0x0124:
            r5 = move-exception
            goto L_0x0130
        L_0x0126:
            r5 = move-exception
            r0 = r1
        L_0x0128:
            r1 = r8
            goto L_0x0151
        L_0x012a:
            r5 = move-exception
            r0 = r1
        L_0x012c:
            r1 = r8
            goto L_0x0142
        L_0x012e:
            r5 = move-exception
            r0 = r1
        L_0x0130:
            r1 = r8
            goto L_0x014f
        L_0x0132:
            r5 = move-exception
            r0 = r1
            goto L_0x0151
        L_0x0135:
            r5 = move-exception
            r0 = r1
            goto L_0x0142
        L_0x0138:
            r5 = move-exception
            r0 = r1
            goto L_0x014f
        L_0x013b:
            r5 = move-exception
            r6 = r1
            r0 = r6
            goto L_0x0151
        L_0x013f:
            r5 = move-exception
            r6 = r1
            r0 = r6
        L_0x0142:
            java.io.IOException r7 = new java.io.IOException     // Catch:{ all -> 0x0150 }
            java.lang.String r5 = r5.getMessage()     // Catch:{ all -> 0x0150 }
            r7.<init>(r5)     // Catch:{ all -> 0x0150 }
            throw r7     // Catch:{ all -> 0x0150 }
        L_0x014c:
            r5 = move-exception
            r6 = r1
            r0 = r6
        L_0x014f:
            throw r5     // Catch:{ all -> 0x0150 }
        L_0x0150:
            r5 = move-exception
        L_0x0151:
            if (r1 == 0) goto L_0x0159
            r1.close()     // Catch:{ IOException -> 0x0157 }
            goto L_0x0159
        L_0x0157:
            r6 = move-exception
            goto L_0x0164
        L_0x0159:
            if (r6 == 0) goto L_0x015e
            r6.close()     // Catch:{ IOException -> 0x0157 }
        L_0x015e:
            if (r0 == 0) goto L_0x0169
            r0.close()     // Catch:{ IOException -> 0x0157 }
            goto L_0x0169
        L_0x0164:
            java.lang.String r7 = ""
            com.mi.mistatistic.sdk.controller.Logger.a((java.lang.String) r7, (java.lang.Throwable) r6)
        L_0x0169:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.mistatistic.sdk.controller.NetworkUtils.a(java.lang.String, java.util.Map, java.io.File, java.lang.String):java.lang.String");
    }

    private static String b() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                byte[] hardwareAddress = nextElement.getHardwareAddress();
                if (hardwareAddress != null) {
                    if (hardwareAddress.length != 0) {
                        StringBuilder sb = new StringBuilder();
                        int length = hardwareAddress.length;
                        for (int i = 0; i < length; i++) {
                            sb.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                        }
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        String sb2 = sb.toString();
                        if ("wlan0".equals(nextElement.getName())) {
                            return sb2;
                        }
                    }
                }
            }
            return "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0034 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String e(android.content.Context r3) {
        /*
            java.lang.String r0 = ""
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 23
            if (r1 < r2) goto L_0x000c
            java.lang.String r0 = b()
        L_0x000c:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x0028
            java.lang.String r1 = "wifi"
            java.lang.Object r3 = r3.getSystemService(r1)     // Catch:{ Exception -> 0x0024 }
            android.net.wifi.WifiManager r3 = (android.net.wifi.WifiManager) r3     // Catch:{ Exception -> 0x0024 }
            android.net.wifi.WifiInfo r3 = r3.getConnectionInfo()     // Catch:{ Exception -> 0x0024 }
            java.lang.String r3 = r3.getMacAddress()     // Catch:{ Exception -> 0x0024 }
            goto L_0x0029
        L_0x0024:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0028:
            r3 = r0
        L_0x0029:
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L_0x0034
            java.lang.String r3 = b((java.lang.String) r3)
            return r3
        L_0x0034:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.mistatistic.sdk.controller.NetworkUtils.e(android.content.Context):java.lang.String");
    }

    public static byte[] a(String str) {
        if (str == null) {
            return new byte[0];
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(a(str));
            return String.format("%1$032X", new Object[]{new BigInteger(1, instance.digest())});
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }
}
