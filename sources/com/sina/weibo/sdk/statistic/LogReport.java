package com.sina.weibo.sdk.statistic;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alipay.android.phone.a.a.a;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.Utility;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

class LogReport {

    /* renamed from: a  reason: collision with root package name */
    public static LogReport f8832a = null;
    private static String b = null;
    private static String c = null;
    private static String d = null;
    private static String e = null;
    private static String f = null;
    private static String g = null;
    private static JSONObject h = null;
    private static String i = "uploadtime";
    private static final int j = 25000;
    private static final int k = 20000;
    private static final String l = "dqwef1864il4c9m6";
    private static String m = "https://api.weibo.com/2/proxy/sdk/statistic.json";

    public LogReport(Context context) {
        try {
            if (d == null) {
                d = context.getPackageName();
            }
            c = StatisticConfig.a(context);
            b(context);
            e = Utility.a(context, d);
            f = LogBuilder.c(context);
            g = StatisticConfig.b(context);
        } catch (Exception e2) {
            LogUtil.c(WBAgent.f8835a, e2.toString());
        }
        b();
    }

    private static JSONObject b() {
        if (h == null) {
            h = new JSONObject();
        }
        try {
            h.put("appkey", c);
            h.put("platform", a.f813a);
            h.put("packagename", d);
            h.put("key_hash", e);
            h.put("version", f);
            h.put("channel", g);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return h;
    }

    private static boolean b(Context context) {
        if (TextUtils.isEmpty(b)) {
            b = Utility.b(context, c);
        }
        if (h == null) {
            h = new JSONObject();
        }
        try {
            h.put("aid", b);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return !TextUtils.isEmpty(b);
    }

    public static void a(String str) {
        d = str;
    }

    public static String a() {
        return d;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00aa, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(android.content.Context r8, java.lang.String r9) {
        /*
            java.lang.Class<com.sina.weibo.sdk.statistic.LogReport> r0 = com.sina.weibo.sdk.statistic.LogReport.class
            monitor-enter(r0)
            com.sina.weibo.sdk.statistic.LogReport r1 = f8832a     // Catch:{ all -> 0x00ab }
            if (r1 != 0) goto L_0x000e
            com.sina.weibo.sdk.statistic.LogReport r1 = new com.sina.weibo.sdk.statistic.LogReport     // Catch:{ all -> 0x00ab }
            r1.<init>(r8)     // Catch:{ all -> 0x00ab }
            f8832a = r1     // Catch:{ all -> 0x00ab }
        L_0x000e:
            boolean r1 = com.sina.weibo.sdk.net.NetStateManager.isNetworkConnected(r8)     // Catch:{ all -> 0x00ab }
            r2 = 1
            if (r1 != 0) goto L_0x0027
            java.lang.String r8 = "WBAgent"
            java.lang.String r1 = "network is not connected"
            com.sina.weibo.sdk.utils.LogUtil.b(r8, r1)     // Catch:{ all -> 0x00ab }
            java.lang.String r8 = "app_logs"
            java.lang.String r8 = com.sina.weibo.sdk.statistic.LogFileUtil.b(r8)     // Catch:{ all -> 0x00ab }
            com.sina.weibo.sdk.statistic.LogFileUtil.a(r8, r9, r2)     // Catch:{ all -> 0x00ab }
            monitor-exit(r0)
            return
        L_0x0027:
            java.util.List r9 = com.sina.weibo.sdk.statistic.LogBuilder.a((java.lang.String) r9)     // Catch:{ all -> 0x00ab }
            if (r9 != 0) goto L_0x0036
            java.lang.String r8 = "WBAgent"
            java.lang.String r9 = "applogs is null"
            com.sina.weibo.sdk.utils.LogUtil.b(r8, r9)     // Catch:{ all -> 0x00ab }
            monitor-exit(r0)
            return
        L_0x0036:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x00ab }
            r1.<init>()     // Catch:{ all -> 0x00ab }
            boolean r3 = b((android.content.Context) r8)     // Catch:{ all -> 0x00ab }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x00ab }
        L_0x0043:
            boolean r4 = r9.hasNext()     // Catch:{ all -> 0x00ab }
            if (r4 == 0) goto L_0x0075
            java.lang.Object r4 = r9.next()     // Catch:{ all -> 0x00ab }
            org.json.JSONArray r4 = (org.json.JSONArray) r4     // Catch:{ all -> 0x00ab }
            r5 = 0
            if (r3 == 0) goto L_0x005c
            java.lang.String r5 = m     // Catch:{ all -> 0x00ab }
            java.lang.String r6 = "POST"
            org.json.JSONObject r7 = h     // Catch:{ all -> 0x00ab }
            boolean r5 = a(r5, r6, r7, r4, r8)     // Catch:{ all -> 0x00ab }
        L_0x005c:
            if (r5 != 0) goto L_0x0069
            r1.add(r4)     // Catch:{ all -> 0x00ab }
            java.lang.String r4 = "WBAgent"
            java.lang.String r5 = "upload applogs error"
            com.sina.weibo.sdk.utils.LogUtil.c(r4, r5)     // Catch:{ all -> 0x00ab }
            goto L_0x0043
        L_0x0069:
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00ab }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x00ab }
            a((android.content.Context) r8, (java.lang.Long) r4)     // Catch:{ all -> 0x00ab }
            goto L_0x0043
        L_0x0075:
            java.lang.String r8 = "app_logs"
            java.lang.String r8 = com.sina.weibo.sdk.statistic.LogFileUtil.b(r8)     // Catch:{ all -> 0x00ab }
            com.sina.weibo.sdk.statistic.LogFileUtil.c(r8)     // Catch:{ all -> 0x00ab }
            int r8 = r1.size()     // Catch:{ all -> 0x00ab }
            if (r8 <= 0) goto L_0x00a9
            java.util.Iterator r8 = r1.iterator()     // Catch:{ all -> 0x00ab }
        L_0x0088:
            boolean r9 = r8.hasNext()     // Catch:{ all -> 0x00ab }
            if (r9 == 0) goto L_0x00a9
            java.lang.Object r9 = r8.next()     // Catch:{ all -> 0x00ab }
            org.json.JSONArray r9 = (org.json.JSONArray) r9     // Catch:{ all -> 0x00ab }
            java.lang.String r1 = "app_logs"
            java.lang.String r1 = com.sina.weibo.sdk.statistic.LogFileUtil.b(r1)     // Catch:{ all -> 0x00ab }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x00ab }
            com.sina.weibo.sdk.statistic.LogFileUtil.a(r1, r9, r2)     // Catch:{ all -> 0x00ab }
            java.lang.String r9 = "WBAgent"
            java.lang.String r1 = "save failed_log"
            com.sina.weibo.sdk.utils.LogUtil.a(r9, r1)     // Catch:{ all -> 0x00ab }
            goto L_0x0088
        L_0x00a9:
            monitor-exit(r0)
            return
        L_0x00ab:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.statistic.LogReport.a(android.content.Context, java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0108, code lost:
        if (r7 != null) goto L_0x010a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0111, code lost:
        if (r7 != null) goto L_0x010a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0117 A[SYNTHETIC, Splitter:B:53:0x0117] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:42:0x0105=Splitter:B:42:0x0105, B:47:0x010e=Splitter:B:47:0x010e} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.lang.String r6, java.lang.String r7, org.json.JSONObject r8, org.json.JSONArray r9, android.content.Context r10) {
        /*
            java.lang.String r7 = c
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            r0 = 0
            if (r7 == 0) goto L_0x0011
            java.lang.String r6 = "WBAgent"
            java.lang.String r7 = "unexpected null AppKey"
            com.sina.weibo.sdk.utils.LogUtil.c(r6, r7)
            return r0
        L_0x0011:
            r7 = 0
            if (r8 != 0) goto L_0x0022
            org.json.JSONObject r8 = b()     // Catch:{ UnsupportedEncodingException -> 0x001f, IOException -> 0x001c }
            goto L_0x0022
        L_0x0019:
            r6 = move-exception
            goto L_0x0115
        L_0x001c:
            r6 = move-exception
            goto L_0x0105
        L_0x001f:
            r6 = move-exception
            goto L_0x010e
        L_0x0022:
            java.lang.String r1 = "time"
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x0072 }
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 / r4
            r8.put(r1, r2)     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r1 = "length"
            int r2 = r9.length()     // Catch:{ JSONException -> 0x0072 }
            r8.put(r1, r2)     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r1 = "sign"
            java.lang.String r2 = "aid"
            java.lang.String r2 = r8.getString(r2)     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r3 = "appkey"
            java.lang.String r3 = r8.getString(r3)     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r4 = "time"
            long r4 = r8.getLong(r4)     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r2 = a(r2, r3, r4)     // Catch:{ JSONException -> 0x0072 }
            r8.put(r1, r2)     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r1 = "content"
            r8.put(r1, r9)     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r9 = "WBAgent"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0072 }
            r1.<init>()     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r2 = "post content--- "
            r1.append(r2)     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r2 = r8.toString()     // Catch:{ JSONException -> 0x0072 }
            r1.append(r2)     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r1 = r1.toString()     // Catch:{ JSONException -> 0x0072 }
            com.sina.weibo.sdk.utils.LogUtil.a(r9, r1)     // Catch:{ JSONException -> 0x0072 }
            goto L_0x0076
        L_0x0072:
            r9 = move-exception
            r9.printStackTrace()     // Catch:{ UnsupportedEncodingException -> 0x001f, IOException -> 0x001c }
        L_0x0076:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x001f, IOException -> 0x001c }
            r9.<init>()     // Catch:{ UnsupportedEncodingException -> 0x001f, IOException -> 0x001c }
            r9.append(r6)     // Catch:{ UnsupportedEncodingException -> 0x001f, IOException -> 0x001c }
            java.lang.String r6 = "?source="
            r9.append(r6)     // Catch:{ UnsupportedEncodingException -> 0x001f, IOException -> 0x001c }
            java.lang.String r6 = c     // Catch:{ UnsupportedEncodingException -> 0x001f, IOException -> 0x001c }
            r9.append(r6)     // Catch:{ UnsupportedEncodingException -> 0x001f, IOException -> 0x001c }
            java.lang.String r6 = r9.toString()     // Catch:{ UnsupportedEncodingException -> 0x001f, IOException -> 0x001c }
            java.net.HttpURLConnection r6 = com.sina.weibo.sdk.net.ConnectionFactory.createConnect(r6, r10)     // Catch:{ UnsupportedEncodingException -> 0x001f, IOException -> 0x001c }
            java.io.ByteArrayOutputStream r9 = new java.io.ByteArrayOutputStream     // Catch:{ UnsupportedEncodingException -> 0x001f, IOException -> 0x001c }
            r9.<init>()     // Catch:{ UnsupportedEncodingException -> 0x001f, IOException -> 0x001c }
            boolean r7 = com.sina.weibo.sdk.statistic.StatisticConfig.b()     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            if (r7 == 0) goto L_0x00a7
            java.lang.String r7 = r8.toString()     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            byte[] r7 = b((java.lang.String) r7)     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            r9.write(r7)     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            goto L_0x00b2
        L_0x00a7:
            java.lang.String r7 = r8.toString()     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            byte[] r7 = r7.getBytes()     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            r9.write(r7)     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
        L_0x00b2:
            a((java.net.HttpURLConnection) r6)     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            r6.connect()     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            java.io.DataOutputStream r7 = new java.io.DataOutputStream     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            java.io.OutputStream r10 = r6.getOutputStream()     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            r7.<init>(r10)     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            java.lang.String r8 = r8.toString()     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            byte[] r8 = b((java.lang.String) r8)     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            r7.write(r8)     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            r7.flush()     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            r7.close()     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            int r7 = r6.getResponseCode()     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            r8 = 200(0xc8, float:2.8E-43)
            if (r7 != r8) goto L_0x00e2
            r6.getResponseMessage()     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            r6 = 1
            r9.close()     // Catch:{ IOException -> 0x00e1 }
        L_0x00e1:
            return r6
        L_0x00e2:
            java.lang.String r6 = "WBAgent"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            r8.<init>()     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            java.lang.String r10 = "status code = "
            r8.append(r10)     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            r8.append(r7)     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            java.lang.String r7 = r8.toString()     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            com.sina.weibo.sdk.utils.LogUtil.b(r6, r7)     // Catch:{ UnsupportedEncodingException -> 0x0102, IOException -> 0x00ff, all -> 0x00fc }
            r9.close()     // Catch:{ IOException -> 0x0114 }
            goto L_0x0114
        L_0x00fc:
            r6 = move-exception
            r7 = r9
            goto L_0x0115
        L_0x00ff:
            r6 = move-exception
            r7 = r9
            goto L_0x0105
        L_0x0102:
            r6 = move-exception
            r7 = r9
            goto L_0x010e
        L_0x0105:
            r6.printStackTrace()     // Catch:{ all -> 0x0019 }
            if (r7 == 0) goto L_0x0114
        L_0x010a:
            r7.close()     // Catch:{ IOException -> 0x0114 }
            goto L_0x0114
        L_0x010e:
            r6.printStackTrace()     // Catch:{ all -> 0x0019 }
            if (r7 == 0) goto L_0x0114
            goto L_0x010a
        L_0x0114:
            return r0
        L_0x0115:
            if (r7 == 0) goto L_0x011a
            r7.close()     // Catch:{ IOException -> 0x011a }
        L_0x011a:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.statistic.LogReport.a(java.lang.String, java.lang.String, org.json.JSONObject, org.json.JSONArray, android.content.Context):boolean");
    }

    private static void a(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
        } catch (Exception unused) {
        }
    }

    private static String a(String str, String str2, long j2) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        sb.append(str2);
        sb.append(l);
        sb.append(j2);
        String a2 = MD5.a(sb.toString());
        String substring = a2.substring(a2.length() - 6);
        String a3 = MD5.a(substring + substring.substring(0, 4));
        return substring + a3.substring(a3.length() + -1);
    }

    private static byte[] b(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bytes = str.getBytes("utf-8");
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bytes);
            gZIPOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static long a(Context context) {
        return context.getSharedPreferences(i, 0).getLong("lasttime", 0);
    }

    private static void a(Context context, Long l2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(i, 0).edit();
        edit.putLong("lasttime", l2.longValue());
        edit.commit();
    }
}
