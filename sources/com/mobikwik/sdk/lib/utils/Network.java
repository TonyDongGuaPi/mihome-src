package com.mobikwik.sdk.lib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.alipay.sdk.sys.a;
import com.mobikwik.sdk.lib.Constants;
import java.util.HashMap;

public class Network {
    public static final int RECHARGE_TIMEOUT_IN_MS = 50000;
    public static final String TAG = "Network";
    public static final int TIMEOUT_IN_MS = 20000;
    private static ConnectivityManager cm;

    public static String getConnectionType(Context context) {
        if (cm == null) {
            cm = (ConnectivityManager) context.getSystemService("connectivity");
        }
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return "";
        }
        if (activeNetworkInfo.getType() != 0) {
            return activeNetworkInfo.getTypeName();
        }
        return activeNetworkInfo.getTypeName() + ":" + activeNetworkInfo.getSubtypeName();
    }

    private static String getQueryString(HashMap hashMap) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : hashMap.keySet()) {
            String str2 = (String) hashMap.get(str);
            if (z) {
                z = false;
            } else {
                sb.append(a.b);
            }
            sb.append(Utils.utf8Encode(str));
            sb.append("=");
            if (str2 == null) {
                str2 = "";
            }
            sb.append(Utils.utf8Encode(str2));
        }
        return sb.toString();
    }

    public static String getResponseFromQuery(Context context, String str) {
        return getResponseFromQuery(context, str, (String) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c1  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:34:0x00a5=Splitter:B:34:0x00a5, B:26:0x008f=Splitter:B:26:0x008f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getResponseFromQuery(android.content.Context r3, java.lang.String r4, java.lang.String r5) {
        /*
            int r3 = android.os.Build.VERSION.SDK_INT
            r0 = 8
            if (r3 > r0) goto L_0x000d
            java.lang.String r3 = "http.keepAlive"
            java.lang.String r0 = "false"
            java.lang.System.setProperty(r3, r0)
        L_0x000d:
            com.mobikwik.sdk.lib.utils.Utils.print(r4)
            r3 = 0
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ SocketTimeoutException -> 0x00a1, IOException -> 0x008b, all -> 0x0086 }
            java.lang.Long.valueOf(r0)     // Catch:{ SocketTimeoutException -> 0x00a1, IOException -> 0x008b, all -> 0x0086 }
            java.net.URL r0 = new java.net.URL     // Catch:{ SocketTimeoutException -> 0x00a1, IOException -> 0x008b, all -> 0x0086 }
            r0.<init>(r4)     // Catch:{ SocketTimeoutException -> 0x00a1, IOException -> 0x008b, all -> 0x0086 }
            java.net.URLConnection r4 = r0.openConnection()     // Catch:{ SocketTimeoutException -> 0x00a1, IOException -> 0x008b, all -> 0x0086 }
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ SocketTimeoutException -> 0x00a1, IOException -> 0x008b, all -> 0x0086 }
            r3 = 20000(0x4e20, float:2.8026E-41)
            r4.setConnectTimeout(r3)     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            r4.setReadTimeout(r3)     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            java.lang.String r3 = "POST"
            r4.setRequestMethod(r3)     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            if (r5 == 0) goto L_0x0037
            java.lang.String r3 = "User-Agent"
            r4.setRequestProperty(r3, r5)     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
        L_0x0037:
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            java.io.InputStream r5 = r4.getInputStream()     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            r3.<init>(r5)     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            r0.<init>(r3)     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            r5.<init>(r0)     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            r3.<init>()     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
        L_0x004f:
            java.lang.String r0 = r5.readLine()     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            if (r0 == 0) goto L_0x006a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            r1.<init>()     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            r1.append(r0)     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            java.lang.String r0 = "\n"
            r1.append(r0)     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            java.lang.String r0 = r1.toString()     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            r3.append(r0)     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            goto L_0x004f
        L_0x006a:
            java.lang.String r5 = r3.toString()     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            com.mobikwik.sdk.lib.utils.Utils.print(r5)     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            java.lang.String r3 = r3.toString()     // Catch:{ SocketTimeoutException -> 0x0084, IOException -> 0x0082 }
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.Long.valueOf(r0)
            if (r4 == 0) goto L_0x0081
            r4.disconnect()
        L_0x0081:
            return r3
        L_0x0082:
            r3 = move-exception
            goto L_0x008f
        L_0x0084:
            r3 = move-exception
            goto L_0x00a5
        L_0x0086:
            r4 = move-exception
            r2 = r4
            r4 = r3
            r3 = r2
            goto L_0x00b8
        L_0x008b:
            r4 = move-exception
            r2 = r4
            r4 = r3
            r3 = r2
        L_0x008f:
            r3.printStackTrace()     // Catch:{ all -> 0x00b7 }
            java.lang.String r3 = "No Connection available"
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.Long.valueOf(r0)
            if (r4 == 0) goto L_0x00a0
            r4.disconnect()
        L_0x00a0:
            return r3
        L_0x00a1:
            r4 = move-exception
            r2 = r4
            r4 = r3
            r3 = r2
        L_0x00a5:
            r3.printStackTrace()     // Catch:{ all -> 0x00b7 }
            java.lang.String r3 = "Connection timed out"
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.Long.valueOf(r0)
            if (r4 == 0) goto L_0x00b6
            r4.disconnect()
        L_0x00b6:
            return r3
        L_0x00b7:
            r3 = move-exception
        L_0x00b8:
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.Long.valueOf(r0)
            if (r4 == 0) goto L_0x00c4
            r4.disconnect()
        L_0x00c4:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobikwik.sdk.lib.utils.Network.getResponseFromQuery(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:49:0x01a1=Splitter:B:49:0x01a1, B:55:0x01ce=Splitter:B:55:0x01ce} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getResponseOfFormPostRequest(java.lang.String r4, java.lang.String r5, java.util.HashMap r6, int r7, java.lang.String r8, boolean r9) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "getResponseOfPostRequest URL: "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            com.mobikwik.sdk.lib.utils.Utils.print(r0)
            r0 = 0
            if (r4 != 0) goto L_0x001d
            java.lang.String r4 = "URL is null RETURNING BACK"
        L_0x0019:
            com.mobikwik.sdk.lib.utils.Utils.print(r4)
            return r0
        L_0x001d:
            if (r5 != 0) goto L_0x0022
            java.lang.String r4 = "DATA is null RETURNING BACK"
            goto L_0x0019
        L_0x0022:
            long r1 = java.lang.System.currentTimeMillis()
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x01cd, IOException -> 0x01a0 }
            r2.<init>()     // Catch:{ SocketTimeoutException -> 0x01cd, IOException -> 0x01a0 }
            java.lang.String r3 = "connecting...: "
            r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x01cd, IOException -> 0x01a0 }
            r2.append(r1)     // Catch:{ SocketTimeoutException -> 0x01cd, IOException -> 0x01a0 }
            java.lang.String r1 = r2.toString()     // Catch:{ SocketTimeoutException -> 0x01cd, IOException -> 0x01a0 }
            com.mobikwik.sdk.lib.utils.Utils.print(r1)     // Catch:{ SocketTimeoutException -> 0x01cd, IOException -> 0x01a0 }
            java.net.URL r1 = new java.net.URL     // Catch:{ SocketTimeoutException -> 0x01cd, IOException -> 0x01a0 }
            r1.<init>(r4)     // Catch:{ SocketTimeoutException -> 0x01cd, IOException -> 0x01a0 }
            java.net.URLConnection r4 = r1.openConnection()     // Catch:{ SocketTimeoutException -> 0x01cd, IOException -> 0x01a0 }
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ SocketTimeoutException -> 0x01cd, IOException -> 0x01a0 }
            r0 = 50000(0xc350, float:7.0065E-41)
            java.lang.String r1 = "User-Agent"
            r4.setRequestProperty(r1, r8)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r4.setConnectTimeout(r0)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r4.setReadTimeout(r7)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.String r7 = "POST"
            r4.setRequestMethod(r7)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r7 = 1
            r4.setDoInput(r7)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r4.setDoOutput(r7)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.String r8 = "Content-Type"
            java.lang.String r0 = "application/x-www-form-urlencoded"
            r4.setRequestProperty(r8, r0)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.String r8 = "charset"
            java.lang.String r0 = "utf-8"
            r4.setRequestProperty(r8, r0)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.String r8 = "Content-Length"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r0.<init>()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.String r1 = ""
            r0.append(r1)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            byte[] r1 = r5.getBytes()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            int r1 = r1.length     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.String r1 = java.lang.Integer.toString(r1)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r0.append(r1)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.String r0 = r0.toString()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r4.setRequestProperty(r8, r0)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r4.setInstanceFollowRedirects(r7)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            if (r6 == 0) goto L_0x00bc
            java.util.Set r6 = r6.entrySet()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
        L_0x009d:
            boolean r7 = r6.hasNext()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            if (r7 == 0) goto L_0x00bc
            java.lang.Object r7 = r6.next()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.Object r8 = r7.getKey()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.Object r7 = r7.getValue()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r4.setRequestProperty(r8, r7)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r6.remove()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            goto L_0x009d
        L_0x00bc:
            r6 = 0
            r4.setUseCaches(r6)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.io.OutputStream r6 = r4.getOutputStream()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.io.BufferedWriter r7 = new java.io.BufferedWriter     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.io.OutputStreamWriter r8 = new java.io.OutputStreamWriter     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.String r0 = "UTF-8"
            r8.<init>(r6, r0)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r7.<init>(r8)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r7.write(r5)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r7.flush()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r7.close()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r6.close()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            int r5 = r4.getResponseCode()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r6 = 302(0x12e, float:4.23E-43)
            if (r5 == r6) goto L_0x0177
            r6 = 301(0x12d, float:4.22E-43)
            if (r5 == r6) goto L_0x0177
            r6 = 303(0x12f, float:4.25E-43)
            if (r5 != r6) goto L_0x00ee
            goto L_0x0177
        L_0x00ee:
            r6 = 400(0x190, float:5.6E-43)
            if (r5 < r6) goto L_0x00fe
            if (r9 == 0) goto L_0x00fe
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.io.InputStream r6 = r4.getErrorStream()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r5.<init>(r6)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            goto L_0x0107
        L_0x00fe:
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.io.InputStream r6 = r4.getInputStream()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r5.<init>(r6)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
        L_0x0107:
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r7.<init>(r5)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r6.<init>(r7)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r7.<init>()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
        L_0x0116:
            java.lang.String r8 = r6.readLine()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            if (r8 == 0) goto L_0x0131
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r9.<init>()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r9.append(r8)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.String r8 = "\n"
            r9.append(r8)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.String r8 = r9.toString()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r7.append(r8)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            goto L_0x0116
        L_0x0131:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r6.<init>()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.String r8 = "Response: "
            r6.append(r8)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.String r8 = r7.toString()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r6.append(r8)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.String r6 = r6.toString()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            com.mobikwik.sdk.lib.utils.Utils.print(r6)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.Long.valueOf(r8)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            r5.close()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.String r5 = r7.toString()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            long r6 = java.lang.System.currentTimeMillis()
            java.lang.Long r6 = java.lang.Long.valueOf(r6)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
        L_0x0164:
            java.lang.String r8 = "disconnecting...: "
            r7.append(r8)
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            com.mobikwik.sdk.lib.utils.Utils.print(r6)
            r4.disconnect()
            return r5
        L_0x0177:
            r4.getResponseMessage()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.String r5 = "Location"
            java.lang.String r5 = r4.getHeaderField(r5)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            java.lang.Long.valueOf(r6)     // Catch:{ SocketTimeoutException -> 0x019a, IOException -> 0x0197, all -> 0x0195 }
            long r6 = java.lang.System.currentTimeMillis()
            java.lang.Long r6 = java.lang.Long.valueOf(r6)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            goto L_0x0164
        L_0x0195:
            r5 = move-exception
            goto L_0x01e8
        L_0x0197:
            r5 = move-exception
            r0 = r4
            goto L_0x01a1
        L_0x019a:
            r5 = move-exception
            r0 = r4
            goto L_0x01ce
        L_0x019d:
            r5 = move-exception
            r4 = r0
            goto L_0x01e8
        L_0x01a0:
            r5 = move-exception
        L_0x01a1:
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x019d }
            java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x019d }
            r5.printStackTrace()     // Catch:{ all -> 0x019d }
            java.lang.String r4 = "No Connection available"
            long r5 = java.lang.System.currentTimeMillis()
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
        L_0x01ba:
            java.lang.String r7 = "disconnecting...: "
            r6.append(r7)
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            com.mobikwik.sdk.lib.utils.Utils.print(r5)
            r0.disconnect()
            return r4
        L_0x01cd:
            r5 = move-exception
        L_0x01ce:
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x019d }
            java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x019d }
            r5.printStackTrace()     // Catch:{ all -> 0x019d }
            java.lang.String r4 = "Connection timed out"
            long r5 = java.lang.System.currentTimeMillis()
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            goto L_0x01ba
        L_0x01e8:
            long r6 = java.lang.System.currentTimeMillis()
            java.lang.Long r6 = java.lang.Long.valueOf(r6)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "disconnecting...: "
            r7.append(r8)
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            com.mobikwik.sdk.lib.utils.Utils.print(r6)
            r4.disconnect()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobikwik.sdk.lib.utils.Network.getResponseOfFormPostRequest(java.lang.String, java.lang.String, java.util.HashMap, int, java.lang.String, boolean):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x011f  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:31:0x00f1=Splitter:B:31:0x00f1, B:38:0x0104=Splitter:B:38:0x0104} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getResponseOfPostRequest(java.lang.String r3, java.lang.String r4, java.lang.String r5, java.lang.String r6) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 8
            if (r0 > r1) goto L_0x000d
            java.lang.String r0 = "http.keepAlive"
            java.lang.String r1 = "false"
            java.lang.System.setProperty(r0, r1)
        L_0x000d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "url :"
            r0.append(r1)
            r0.append(r3)
            java.lang.String r1 = "\n data :"
            r0.append(r1)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            com.mobikwik.sdk.lib.utils.Utils.print(r0)
            r0 = 0
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ SocketTimeoutException -> 0x0103, IOException -> 0x00f0 }
            java.lang.Long.valueOf(r1)     // Catch:{ SocketTimeoutException -> 0x0103, IOException -> 0x00f0 }
            java.net.URL r1 = new java.net.URL     // Catch:{ SocketTimeoutException -> 0x0103, IOException -> 0x00f0 }
            r1.<init>(r3)     // Catch:{ SocketTimeoutException -> 0x0103, IOException -> 0x00f0 }
            java.net.URLConnection r3 = r1.openConnection()     // Catch:{ SocketTimeoutException -> 0x0103, IOException -> 0x00f0 }
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ SocketTimeoutException -> 0x0103, IOException -> 0x00f0 }
            r0 = 20000(0x4e20, float:2.8026E-41)
            r3.setConnectTimeout(r0)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            r3.setReadTimeout(r0)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            java.lang.String r0 = "POST"
            r3.setRequestMethod(r0)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            r0 = 1
            r3.setDoOutput(r0)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            if (r6 == 0) goto L_0x0054
            java.lang.String r0 = "User-Agent"
            r3.setRequestProperty(r0, r6)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
        L_0x0054:
            java.lang.String r6 = "application/json"
            boolean r5 = r6.equals(r5)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            if (r5 == 0) goto L_0x0063
            java.lang.String r5 = "Content-Type"
            java.lang.String r6 = "application/json"
            r3.setRequestProperty(r5, r6)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
        L_0x0063:
            java.lang.String r5 = "UTF-8"
            byte[] r4 = r4.getBytes(r5)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            java.lang.String r5 = "Content-Length"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            r6.<init>()     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            java.lang.String r0 = ""
            r6.append(r0)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            int r0 = r4.length     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            r6.append(r0)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            java.lang.String r6 = r6.toString()     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            r3.setRequestProperty(r5, r6)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            java.io.OutputStream r5 = r3.getOutputStream()     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            r6 = 0
            int r0 = r4.length     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            r5.write(r4, r6, r0)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            java.io.InputStream r5 = r3.getInputStream()     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            r4.<init>(r5)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            r6.<init>(r4)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            r5.<init>(r6)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            r4.<init>()     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
        L_0x00a1:
            java.lang.String r6 = r5.readLine()     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            if (r6 == 0) goto L_0x00bc
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            r0.<init>()     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            r0.append(r6)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            java.lang.String r6 = "\n"
            r0.append(r6)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            java.lang.String r6 = r0.toString()     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            r4.append(r6)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            goto L_0x00a1
        L_0x00bc:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            r5.<init>()     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            java.lang.String r6 = "Response :"
            r5.append(r6)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            java.lang.String r6 = r4.toString()     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            r5.append(r6)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            java.lang.String r5 = r5.toString()     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            com.mobikwik.sdk.lib.utils.Utils.print(r5)     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            java.lang.String r4 = r4.toString()     // Catch:{ SocketTimeoutException -> 0x00ea, IOException -> 0x00e7, all -> 0x00e5 }
            long r5 = java.lang.System.currentTimeMillis()
            java.lang.Long.valueOf(r5)
            if (r3 == 0) goto L_0x00e4
            r3.disconnect()
        L_0x00e4:
            return r4
        L_0x00e5:
            r4 = move-exception
            goto L_0x0116
        L_0x00e7:
            r4 = move-exception
            r0 = r3
            goto L_0x00f1
        L_0x00ea:
            r4 = move-exception
            r0 = r3
            goto L_0x0104
        L_0x00ed:
            r4 = move-exception
            r3 = r0
            goto L_0x0116
        L_0x00f0:
            r4 = move-exception
        L_0x00f1:
            r4.printStackTrace()     // Catch:{ all -> 0x00ed }
            java.lang.String r3 = "No Connection available"
            long r4 = java.lang.System.currentTimeMillis()
            java.lang.Long.valueOf(r4)
            if (r0 == 0) goto L_0x0102
            r0.disconnect()
        L_0x0102:
            return r3
        L_0x0103:
            r4 = move-exception
        L_0x0104:
            r4.printStackTrace()     // Catch:{ all -> 0x00ed }
            java.lang.String r3 = "Connection timed out"
            long r4 = java.lang.System.currentTimeMillis()
            java.lang.Long.valueOf(r4)
            if (r0 == 0) goto L_0x0115
            r0.disconnect()
        L_0x0115:
            return r3
        L_0x0116:
            long r5 = java.lang.System.currentTimeMillis()
            java.lang.Long.valueOf(r5)
            if (r3 == 0) goto L_0x0122
            r3.disconnect()
        L_0x0122:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobikwik.sdk.lib.utils.Network.getResponseOfPostRequest(java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    public static String getResponseOfPostRequest(String str, HashMap hashMap) {
        return getResponseOfPostRequest(str, hashMap, (String) null);
    }

    public static String getResponseOfPostRequest(String str, HashMap hashMap, String str2) {
        if (Build.VERSION.SDK_INT <= 8) {
            System.setProperty("http.keepAlive", "false");
        }
        return getResponseOfPostRequest(str, getQueryString(hashMap), (String) null, str2);
    }

    public static boolean isConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            UIFunctions.showToast(context, Constants.CONNECTION_ERROR);
            return false;
        }
        boolean isConnected = activeNetworkInfo.isConnected();
        if (!isConnected) {
            UIFunctions.showToast(context, Constants.CONNECTION_ERROR_DATA);
        }
        return isConnected;
    }

    public static boolean validateNetworkResponse(Context context, String str, boolean z) {
        if (str == null || str.length() < 1 || str.equals(Constants.GENERIC_ERROR)) {
            if (z) {
                UIFunctions.showToastLong(context, Constants.GENERIC_ERROR);
            }
            return false;
        } else if (str.equals(Constants.CONNECTION_ERROR)) {
            if (z) {
                UIFunctions.showToastLong(context, Constants.CONNECTION_ERROR);
            }
            return false;
        } else if (!str.equals(Constants.CONNECTION_TIMED_OUT)) {
            return true;
        } else {
            if (z) {
                UIFunctions.showToastLong(context, Constants.CONNECTION_TIMED_OUT);
            }
            return false;
        }
    }

    public static boolean validateNetworkResponse(String str) {
        return str != null && str.length() >= 1 && !str.equals(Constants.GENERIC_ERROR) && !str.equals(Constants.CONNECTION_ERROR) && !str.equals(Constants.CONNECTION_TIMED_OUT);
    }
}
