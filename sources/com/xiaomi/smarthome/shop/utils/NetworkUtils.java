package com.xiaomi.smarthome.shop.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.xiaomi.smarthome.application.CommonApplication;
import okhttp3.OkHttpClient;

public final class NetworkUtils {

    /* renamed from: a  reason: collision with root package name */
    private static OkHttpClient f22200a = new OkHttpClient();

    public static OkHttpClient a() {
        return f22200a;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(12:1|2|3|4|5|6|7|8|9|10|11|12) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0036 */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005b A[SYNTHETIC, Splitter:B:27:0x005b] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0060 A[SYNTHETIC, Splitter:B:31:0x0060] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0068 A[SYNTHETIC, Splitter:B:36:0x0068] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x006d A[SYNTHETIC, Splitter:B:40:0x006d] */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(@android.support.annotation.NonNull java.lang.String r4, @android.support.annotation.NonNull java.io.File r5) {
        /*
            r0 = 0
            okhttp3.Request$Builder r1 = new okhttp3.Request$Builder     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            r1.<init>()     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            okhttp3.Request$Builder r1 = r1.url((java.lang.String) r4)     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            okhttp3.Request r1 = r1.build()     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            okhttp3.OkHttpClient r2 = a()     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            okhttp3.Call r1 = r2.newCall(r1)     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            okhttp3.Response r1 = r1.execute()     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            r2.<init>(r5)     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0043 }
            okhttp3.ResponseBody r1 = r1.body()     // Catch:{ IOException -> 0x0043 }
            java.io.InputStream r1 = r1.byteStream()     // Catch:{ IOException -> 0x0043 }
            r5.<init>(r1)     // Catch:{ IOException -> 0x0043 }
            com.xiaomi.smarthome.shop.utils.IOUtils.a((java.io.InputStream) r5, (java.io.OutputStream) r2)     // Catch:{ IOException -> 0x003d, all -> 0x003a }
            r2.flush()     // Catch:{ IOException -> 0x003d, all -> 0x003a }
            r4 = 1
            r5.close()     // Catch:{ IOException -> 0x0036 }
        L_0x0036:
            r2.close()     // Catch:{ IOException -> 0x0039 }
        L_0x0039:
            return r4
        L_0x003a:
            r4 = move-exception
            r0 = r5
            goto L_0x0066
        L_0x003d:
            r0 = r5
            goto L_0x0043
        L_0x003f:
            r4 = move-exception
            r2 = r0
            goto L_0x0066
        L_0x0042:
            r2 = r0
        L_0x0043:
            java.lang.String r5 = "okhttp"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0065 }
            r1.<init>()     // Catch:{ all -> 0x0065 }
            java.lang.String r3 = "download file failed: "
            r1.append(r3)     // Catch:{ all -> 0x0065 }
            r1.append(r4)     // Catch:{ all -> 0x0065 }
            java.lang.String r4 = r1.toString()     // Catch:{ all -> 0x0065 }
            android.util.Log.e(r5, r4)     // Catch:{ all -> 0x0065 }
            if (r0 == 0) goto L_0x005e
            r0.close()     // Catch:{ IOException -> 0x005e }
        L_0x005e:
            if (r2 == 0) goto L_0x0063
            r2.close()     // Catch:{ IOException -> 0x0063 }
        L_0x0063:
            r4 = 0
            return r4
        L_0x0065:
            r4 = move-exception
        L_0x0066:
            if (r0 == 0) goto L_0x006b
            r0.close()     // Catch:{ IOException -> 0x006b }
        L_0x006b:
            if (r2 == 0) goto L_0x0070
            r2.close()     // Catch:{ IOException -> 0x0070 }
        L_0x0070:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.shop.utils.NetworkUtils.a(java.lang.String, java.io.File):boolean");
    }

    public static boolean b() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) CommonApplication.getAppContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return true;
    }

    public static boolean c() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) CommonApplication.getAppContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
