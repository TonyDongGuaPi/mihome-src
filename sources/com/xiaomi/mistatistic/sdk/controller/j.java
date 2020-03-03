package com.xiaomi.mistatistic.sdk.controller;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.mistatistic.sdk.BuildSetting;
import com.xiaomi.mistatistic.sdk.CustomSettings;
import com.xiaomi.mistatistic.sdk.controller.d;
import com.xiaomi.stat.c.c;
import com.xiaomi.xmsf.push.service.a;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class j {

    public interface b {
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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v10, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v14, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v18, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r7v2, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: type inference failed for: r7v11 */
    /* JADX WARNING: type inference failed for: r7v16 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ce A[SYNTHETIC, Splitter:B:42:0x00ce] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d3 A[Catch:{ IOException -> 0x00d6 }] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r6, java.lang.String r7, java.util.Map<java.lang.String, java.lang.String> r8, com.xiaomi.mistatistic.sdk.controller.j.b r9) throws java.io.IOException {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            if (r0 != 0) goto L_0x00d7
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            java.lang.String r2 = com.xiaomi.mistatistic.sdk.controller.q.a((android.content.Context) r6, (java.lang.String) r7)     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            r1.<init>(r2)     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            java.net.HttpURLConnection r6 = a(r6, r1)     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            r1 = 10000(0x2710, float:1.4013E-41)
            r6.setConnectTimeout(r1)     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            r1 = 15000(0x3a98, float:2.102E-41)
            r6.setReadTimeout(r1)     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            java.lang.String r1 = "POST"
            r6.setRequestMethod(r1)     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            a((java.util.Map<java.lang.String, java.lang.String>) r8)     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            java.lang.String r8 = b((java.util.Map<java.lang.String, java.lang.String>) r8)     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            if (r8 == 0) goto L_0x00a7
            r1 = 1
            r6.setDoOutput(r1)     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            byte[] r8 = r8.getBytes()     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            java.io.OutputStream r2 = r6.getOutputStream()     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            int r3 = r8.length     // Catch:{ IOException -> 0x00a3, Throwable -> 0x009f, all -> 0x009b }
            r4 = 0
            r2.write(r8, r4, r3)     // Catch:{ IOException -> 0x00a3, Throwable -> 0x009f, all -> 0x009b }
            r2.flush()     // Catch:{ IOException -> 0x00a3, Throwable -> 0x009f, all -> 0x009b }
            r2.close()     // Catch:{ IOException -> 0x00a3, Throwable -> 0x009f, all -> 0x009b }
            int r8 = r6.getResponseCode()     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            java.lang.String r2 = "doHttpPost-"
            java.lang.String r3 = "url:%s,responseCode:%d"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            r5[r4] = r7     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r8)     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            r5[r1] = r7     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            java.lang.String r7 = java.lang.String.format(r3, r5)     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            com.xiaomi.mistatistic.sdk.controller.h.b(r2, r7)     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            java.io.BufferedReader r7 = new java.io.BufferedReader     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            java.io.InputStreamReader r8 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            com.xiaomi.mistatistic.sdk.controller.j$a r1 = new com.xiaomi.mistatistic.sdk.controller.j$a     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            java.io.InputStream r6 = r6.getInputStream()     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            r1.<init>(r6)     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            r8.<init>(r1)     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            r7.<init>(r8)     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            java.lang.String r6 = r7.readLine()     // Catch:{ IOException -> 0x0099, Throwable -> 0x0097 }
            java.lang.StringBuffer r8 = new java.lang.StringBuffer     // Catch:{ IOException -> 0x0099, Throwable -> 0x0097 }
            r8.<init>()     // Catch:{ IOException -> 0x0099, Throwable -> 0x0097 }
            java.lang.String r1 = "line.separator"
            java.lang.String r1 = java.lang.System.getProperty(r1)     // Catch:{ IOException -> 0x0099, Throwable -> 0x0097 }
        L_0x007f:
            if (r6 == 0) goto L_0x008c
            r8.append(r6)     // Catch:{ IOException -> 0x0099, Throwable -> 0x0097 }
            r8.append(r1)     // Catch:{ IOException -> 0x0099, Throwable -> 0x0097 }
            java.lang.String r6 = r7.readLine()     // Catch:{ IOException -> 0x0099, Throwable -> 0x0097 }
            goto L_0x007f
        L_0x008c:
            java.lang.String r6 = r8.toString()     // Catch:{ IOException -> 0x0099, Throwable -> 0x0097 }
            r9.a(r6)     // Catch:{ IOException -> 0x0099, Throwable -> 0x0097 }
            r7.close()     // Catch:{ IOException -> 0x0099, Throwable -> 0x0097 }
            return
        L_0x0097:
            r6 = move-exception
            goto L_0x00b4
        L_0x0099:
            r6 = move-exception
            goto L_0x00c5
        L_0x009b:
            r6 = move-exception
            r7 = r0
            r0 = r2
            goto L_0x00cc
        L_0x009f:
            r6 = move-exception
            r7 = r0
            r0 = r2
            goto L_0x00b4
        L_0x00a3:
            r6 = move-exception
            r7 = r0
            r0 = r2
            goto L_0x00c5
        L_0x00a7:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            java.lang.String r7 = "nameValuePairs"
            r6.<init>(r7)     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
            throw r6     // Catch:{ IOException -> 0x00c3, Throwable -> 0x00b2, all -> 0x00af }
        L_0x00af:
            r6 = move-exception
            r7 = r0
            goto L_0x00cc
        L_0x00b2:
            r6 = move-exception
            r7 = r0
        L_0x00b4:
            java.lang.String r8 = "doHttpPost Throwable:"
            com.xiaomi.mistatistic.sdk.controller.h.a((java.lang.String) r8, (java.lang.Throwable) r6)     // Catch:{ all -> 0x00cb }
            java.io.IOException r8 = new java.io.IOException     // Catch:{ all -> 0x00cb }
            java.lang.String r6 = r6.getMessage()     // Catch:{ all -> 0x00cb }
            r8.<init>(r6)     // Catch:{ all -> 0x00cb }
            throw r8     // Catch:{ all -> 0x00cb }
        L_0x00c3:
            r6 = move-exception
            r7 = r0
        L_0x00c5:
            java.lang.String r8 = "doHttpPost IOException:"
            com.xiaomi.mistatistic.sdk.controller.h.a((java.lang.String) r8, (java.lang.Throwable) r6)     // Catch:{ all -> 0x00cb }
            throw r6     // Catch:{ all -> 0x00cb }
        L_0x00cb:
            r6 = move-exception
        L_0x00cc:
            if (r0 == 0) goto L_0x00d1
            r0.close()     // Catch:{ IOException -> 0x00d6 }
        L_0x00d1:
            if (r7 == 0) goto L_0x00d6
            r7.close()     // Catch:{ IOException -> 0x00d6 }
        L_0x00d6:
            throw r6
        L_0x00d7:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "url"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mistatistic.sdk.controller.j.a(android.content.Context, java.lang.String, java.util.Map, com.xiaomi.mistatistic.sdk.controller.j$b):void");
    }

    protected static void a(Map<String, String> map) {
        try {
            StringBuilder sb = new StringBuilder();
            new HashMap();
            if (map != null) {
                ArrayList<String> arrayList = new ArrayList<>(map.keySet());
                h.b("mistats_NetworkUtils_keys:" + arrayList.toString());
                Collections.sort(arrayList);
                if (!arrayList.isEmpty()) {
                    for (String str : arrayList) {
                        sb.append(str + map.get(str));
                    }
                }
            }
            sb.append("mistats_sdkconfig_jafej!@#)(*e@!#");
            map.put("sign", q.b(sb.toString()).toLowerCase(Locale.getDefault()));
        } catch (Exception e) {
            h.a("sign exception:", (Throwable) e);
        }
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
                    stringBuffer.append(com.alipay.sdk.sys.a.b);
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

    public static final class a extends FilterInputStream {

        /* renamed from: a  reason: collision with root package name */
        private boolean f12036a;

        public a(InputStream inputStream) {
            super(inputStream);
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read;
            if (!this.f12036a && (read = super.read(bArr, i, i2)) != -1) {
                return read;
            }
            this.f12036a = true;
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

    public static void a(String str, Map<String, String> map, b bVar) throws IOException {
        if (!CustomSettings.d()) {
            h.d("upload is disabled.");
            bVar.a("");
            return;
        }
        h.a("Uploading to: " + str);
        if (map != null) {
            map.put("bc", BuildSetting.g());
        }
        if (!CustomSettings.b()) {
            h.a("Uploading under app network");
            a(c.a(), str, map, bVar);
        } else if (a()) {
            try {
                h.a("Uploading via sys service, unmetered network connected");
                b(c.a(), str, map, bVar);
            } catch (Exception e) {
                throw new IOException("exception thrown from IPC." + e.getMessage());
            }
        } else {
            bVar.a((String) null);
            h.a("Uploading via sys service, metered network connected, skip");
        }
    }

    @SuppressLint({"NewApi"})
    public static boolean a() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) c.a().getSystemService("connectivity");
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
    /* JADX WARNING: Removed duplicated region for block: B:62:0x015b A[SYNTHETIC, Splitter:B:62:0x015b] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0163 A[Catch:{ IOException -> 0x015f }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0168 A[Catch:{ IOException -> 0x015f }] */
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
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            android.content.Context r3 = com.xiaomi.mistatistic.sdk.controller.c.a()     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            java.lang.String r5 = com.xiaomi.mistatistic.sdk.controller.q.a((android.content.Context) r3, (java.lang.String) r5)     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            r2.<init>(r5)     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            java.net.URLConnection r5 = r2.openConnection()     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            r2 = 15000(0x3a98, float:2.102E-41)
            r5.setReadTimeout(r2)     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            r2 = 10000(0x2710, float:1.4013E-41)
            r5.setConnectTimeout(r2)     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            r2 = 1
            r5.setDoInput(r2)     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            r5.setDoOutput(r2)     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            r2 = 0
            r5.setUseCaches(r2)     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            java.lang.String r3 = "POST"
            r5.setRequestMethod(r3)     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            java.lang.String r3 = "Connection"
            java.lang.String r4 = "Keep-Alive"
            r5.setRequestProperty(r3, r4)     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            java.lang.String r3 = "Content-Type"
            java.lang.String r4 = "multipart/form-data;boundary=*****"
            r5.setRequestProperty(r3, r4)     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            if (r6 == 0) goto L_0x006d
            java.util.Set r6 = r6.entrySet()     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
        L_0x0051:
            boolean r3 = r6.hasNext()     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            if (r3 == 0) goto L_0x006d
            java.lang.Object r3 = r6.next()     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            java.lang.Object r4 = r3.getKey()     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            java.lang.Object r3 = r3.getValue()     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            r5.setRequestProperty(r4, r3)     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            goto L_0x0051
        L_0x006d:
            int r6 = r0.length()     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            int r6 = r6 + 77
            long r3 = r7.length()     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            int r0 = (int) r3     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            int r6 = r6 + r0
            int r0 = r8.length()     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            int r6 = r6 + r0
            r5.setFixedLengthStreamingMode(r6)     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            java.io.DataOutputStream r6 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            java.io.OutputStream r0 = r5.getOutputStream()     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            r6.<init>(r0)     // Catch:{ IOException -> 0x0154, Throwable -> 0x0147, all -> 0x0143 }
            java.lang.String r0 = "--*****\r\n"
            r6.writeBytes(r0)     // Catch:{ IOException -> 0x0140, Throwable -> 0x013d, all -> 0x013a }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0140, Throwable -> 0x013d, all -> 0x013a }
            r0.<init>()     // Catch:{ IOException -> 0x0140, Throwable -> 0x013d, all -> 0x013a }
            java.lang.String r3 = "Content-Disposition: form-data; name=\""
            r0.append(r3)     // Catch:{ IOException -> 0x0140, Throwable -> 0x013d, all -> 0x013a }
            r0.append(r8)     // Catch:{ IOException -> 0x0140, Throwable -> 0x013d, all -> 0x013a }
            java.lang.String r8 = "\";filename=\""
            r0.append(r8)     // Catch:{ IOException -> 0x0140, Throwable -> 0x013d, all -> 0x013a }
            java.lang.String r8 = r7.getName()     // Catch:{ IOException -> 0x0140, Throwable -> 0x013d, all -> 0x013a }
            r0.append(r8)     // Catch:{ IOException -> 0x0140, Throwable -> 0x013d, all -> 0x013a }
            java.lang.String r8 = "\""
            r0.append(r8)     // Catch:{ IOException -> 0x0140, Throwable -> 0x013d, all -> 0x013a }
            java.lang.String r8 = "\r\n"
            r0.append(r8)     // Catch:{ IOException -> 0x0140, Throwable -> 0x013d, all -> 0x013a }
            java.lang.String r8 = r0.toString()     // Catch:{ IOException -> 0x0140, Throwable -> 0x013d, all -> 0x013a }
            r6.writeBytes(r8)     // Catch:{ IOException -> 0x0140, Throwable -> 0x013d, all -> 0x013a }
            java.lang.String r8 = "\r\n"
            r6.writeBytes(r8)     // Catch:{ IOException -> 0x0140, Throwable -> 0x013d, all -> 0x013a }
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0140, Throwable -> 0x013d, all -> 0x013a }
            r8.<init>(r7)     // Catch:{ IOException -> 0x0140, Throwable -> 0x013d, all -> 0x013a }
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
        L_0x00c7:
            int r0 = r8.read(r7)     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
            r3 = -1
            if (r0 == r3) goto L_0x00d5
            r6.write(r7, r2, r0)     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
            r6.flush()     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
            goto L_0x00c7
        L_0x00d5:
            java.lang.String r7 = "\r\n"
            r6.writeBytes(r7)     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
            java.lang.String r7 = "--"
            r6.writeBytes(r7)     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
            java.lang.String r7 = "*****"
            r6.writeBytes(r7)     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
            java.lang.String r7 = "--"
            r6.writeBytes(r7)     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
            java.lang.String r7 = "\r\n"
            r6.writeBytes(r7)     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
            r6.flush()     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
            java.lang.StringBuffer r7 = new java.lang.StringBuffer     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
            r7.<init>()     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
            com.xiaomi.mistatistic.sdk.controller.j$a r3 = new com.xiaomi.mistatistic.sdk.controller.j$a     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
            java.io.InputStream r5 = r5.getInputStream()     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
            r3.<init>(r5)     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
            r0.<init>(r2)     // Catch:{ IOException -> 0x0136, Throwable -> 0x0132, all -> 0x012e }
        L_0x0109:
            java.lang.String r5 = r0.readLine()     // Catch:{ IOException -> 0x012c, Throwable -> 0x012a, all -> 0x0128 }
            if (r5 == 0) goto L_0x0113
            r7.append(r5)     // Catch:{ IOException -> 0x012c, Throwable -> 0x012a, all -> 0x0128 }
            goto L_0x0109
        L_0x0113:
            java.lang.String r5 = r7.toString()     // Catch:{ IOException -> 0x012c, Throwable -> 0x012a, all -> 0x0128 }
            r8.close()     // Catch:{ IOException -> 0x0121 }
            r6.close()     // Catch:{ IOException -> 0x0121 }
            r0.close()     // Catch:{ IOException -> 0x0121 }
            goto L_0x0127
        L_0x0121:
            r6 = move-exception
            java.lang.String r7 = ""
            com.xiaomi.mistatistic.sdk.controller.h.a((java.lang.String) r7, (java.lang.Throwable) r6)
        L_0x0127:
            return r5
        L_0x0128:
            r5 = move-exception
            goto L_0x0130
        L_0x012a:
            r5 = move-exception
            goto L_0x0134
        L_0x012c:
            r5 = move-exception
            goto L_0x0138
        L_0x012e:
            r5 = move-exception
            r0 = r1
        L_0x0130:
            r1 = r8
            goto L_0x0159
        L_0x0132:
            r5 = move-exception
            r0 = r1
        L_0x0134:
            r1 = r8
            goto L_0x014a
        L_0x0136:
            r5 = move-exception
            r0 = r1
        L_0x0138:
            r1 = r8
            goto L_0x0157
        L_0x013a:
            r5 = move-exception
            r0 = r1
            goto L_0x0159
        L_0x013d:
            r5 = move-exception
            r0 = r1
            goto L_0x014a
        L_0x0140:
            r5 = move-exception
            r0 = r1
            goto L_0x0157
        L_0x0143:
            r5 = move-exception
            r6 = r1
            r0 = r6
            goto L_0x0159
        L_0x0147:
            r5 = move-exception
            r6 = r1
            r0 = r6
        L_0x014a:
            java.io.IOException r7 = new java.io.IOException     // Catch:{ all -> 0x0158 }
            java.lang.String r5 = r5.getMessage()     // Catch:{ all -> 0x0158 }
            r7.<init>(r5)     // Catch:{ all -> 0x0158 }
            throw r7     // Catch:{ all -> 0x0158 }
        L_0x0154:
            r5 = move-exception
            r6 = r1
            r0 = r6
        L_0x0157:
            throw r5     // Catch:{ all -> 0x0158 }
        L_0x0158:
            r5 = move-exception
        L_0x0159:
            if (r1 == 0) goto L_0x0161
            r1.close()     // Catch:{ IOException -> 0x015f }
            goto L_0x0161
        L_0x015f:
            r6 = move-exception
            goto L_0x016c
        L_0x0161:
            if (r6 == 0) goto L_0x0166
            r6.close()     // Catch:{ IOException -> 0x015f }
        L_0x0166:
            if (r0 == 0) goto L_0x0171
            r0.close()     // Catch:{ IOException -> 0x015f }
            goto L_0x0171
        L_0x016c:
            java.lang.String r7 = ""
            com.xiaomi.mistatistic.sdk.controller.h.a((java.lang.String) r7, (java.lang.Throwable) r6)
        L_0x0171:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mistatistic.sdk.controller.j.a(java.lang.String, java.util.Map, java.io.File, java.lang.String):java.lang.String");
    }

    public static void b(final Context context, final String str, final Map<String, String> map, final b bVar) {
        try {
            Intent intent = new Intent();
            intent.setClassName(c.f23036a, c.b);
            if (!context.bindService(intent, new ServiceConnection() {
                /* access modifiers changed from: private */
                public boolean e = false;

                public void onServiceDisconnected(ComponentName componentName) {
                    h.a("error while perform IPC connection.", (Throwable) null);
                    if (!this.e) {
                        bVar.a((String) null);
                        h.a("disconnected, remote http post hasn't not processed");
                    }
                }

                public void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
                    d.a().a((d.a) new d.a() {
                        public void a() {
                            try {
                                bVar.a(a.C0091a.a(iBinder).a(q.a(context, str), map));
                                boolean unused = AnonymousClass1.this.e = true;
                                h.a("connected, do remote http post");
                                context.unbindService(this);
                            } catch (Throwable th) {
                                h.a("error while uploading the logs by IPC.", th);
                                bVar.a((String) null);
                                boolean unused2 = AnonymousClass1.this.e = true;
                            }
                        }
                    });
                }
            }, 1)) {
                h.a("failed to bind");
                bVar.a((String) null);
            }
        } catch (Exception e) {
            h.a("uploadDataThroughSystemService", (Throwable) e);
            bVar.a((String) null);
        }
    }
}
