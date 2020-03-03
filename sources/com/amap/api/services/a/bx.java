package com.amap.api.services.a;

import android.content.Context;
import android.os.Build;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;

public class bx {
    public static Proxy a(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                return a(context, new URI("http://restapi.amap.com"));
            }
            return b(context);
        } catch (Throwable th) {
            cl.c(th, "pu", "gp");
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006f, code lost:
        if (r4 == -1) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ad, code lost:
        if (r4 == -1) goto L_0x00b8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x014e A[SYNTHETIC, Splitter:B:109:0x014e] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0157 A[Catch:{ Throwable -> 0x0163 }] */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0172 A[SYNTHETIC, Splitter:B:120:0x0172] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00ba A[SYNTHETIC, Splitter:B:62:0x00ba] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00dd A[SYNTHETIC, Splitter:B:75:0x00dd] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00f3 A[Catch:{ all -> 0x016f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.net.Proxy b(android.content.Context r11) {
        /*
            boolean r0 = c(r11)
            r1 = 0
            if (r0 == 0) goto L_0x017f
            java.lang.String r0 = "content://telephony/carriers/preferapn"
            android.net.Uri r3 = android.net.Uri.parse(r0)
            android.content.ContentResolver r2 = r11.getContentResolver()
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r0 = 0
            r8 = 1
            r9 = 80
            r10 = -1
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ SecurityException -> 0x00e2, Throwable -> 0x00cd, all -> 0x00c9 }
            if (r2 == 0) goto L_0x00b6
            boolean r3 = r2.moveToFirst()     // Catch:{ SecurityException -> 0x00b3, Throwable -> 0x00b0 }
            if (r3 == 0) goto L_0x00b6
            java.lang.String r3 = "apn"
            int r3 = r2.getColumnIndex(r3)     // Catch:{ SecurityException -> 0x00b3, Throwable -> 0x00b0 }
            java.lang.String r3 = r2.getString(r3)     // Catch:{ SecurityException -> 0x00b3, Throwable -> 0x00b0 }
            if (r3 == 0) goto L_0x0038
            java.util.Locale r4 = java.util.Locale.US     // Catch:{ SecurityException -> 0x00b3, Throwable -> 0x00b0 }
            java.lang.String r3 = r3.toLowerCase(r4)     // Catch:{ SecurityException -> 0x00b3, Throwable -> 0x00b0 }
        L_0x0038:
            if (r3 == 0) goto L_0x007d
            java.lang.String r4 = "ctwap"
            boolean r4 = r3.contains(r4)     // Catch:{ SecurityException -> 0x00b3, Throwable -> 0x00b0 }
            if (r4 == 0) goto L_0x007d
            java.lang.String r3 = a()     // Catch:{ SecurityException -> 0x00b3, Throwable -> 0x00b0 }
            int r4 = b()     // Catch:{ SecurityException -> 0x00b3, Throwable -> 0x00b0 }
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ SecurityException -> 0x0079, Throwable -> 0x0074 }
            if (r5 != 0) goto L_0x005b
            java.lang.String r5 = "null"
            boolean r5 = r3.equals(r5)     // Catch:{ SecurityException -> 0x0079, Throwable -> 0x0074 }
            if (r5 != 0) goto L_0x005b
            r5 = r3
            r3 = 1
            goto L_0x005d
        L_0x005b:
            r5 = r1
            r3 = 0
        L_0x005d:
            if (r3 != 0) goto L_0x006e
            java.lang.String r3 = "QMTAuMC4wLjIwMA=="
            java.lang.String r3 = a((java.lang.String) r3)     // Catch:{ SecurityException -> 0x006b, Throwable -> 0x0066 }
            goto L_0x006f
        L_0x0066:
            r11 = move-exception
            r9 = r4
            r3 = r5
            goto L_0x00d1
        L_0x006b:
            r3 = move-exception
            goto L_0x00e6
        L_0x006e:
            r3 = r5
        L_0x006f:
            if (r4 != r10) goto L_0x0072
            goto L_0x00b8
        L_0x0072:
            r9 = r4
            goto L_0x00b8
        L_0x0074:
            r11 = move-exception
            r3 = r1
            r9 = r4
            goto L_0x00d1
        L_0x0079:
            r3 = move-exception
            r5 = r1
            goto L_0x00e6
        L_0x007d:
            if (r3 == 0) goto L_0x00b6
            java.lang.String r4 = "wap"
            boolean r3 = r3.contains(r4)     // Catch:{ SecurityException -> 0x00b3, Throwable -> 0x00b0 }
            if (r3 == 0) goto L_0x00b6
            java.lang.String r3 = a()     // Catch:{ SecurityException -> 0x00b3, Throwable -> 0x00b0 }
            int r4 = b()     // Catch:{ SecurityException -> 0x00b3, Throwable -> 0x00b0 }
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ SecurityException -> 0x0079, Throwable -> 0x0074 }
            if (r5 != 0) goto L_0x00a1
            java.lang.String r5 = "null"
            boolean r5 = r3.equals(r5)     // Catch:{ SecurityException -> 0x0079, Throwable -> 0x0074 }
            if (r5 != 0) goto L_0x00a1
            r5 = r3
            r3 = 1
            goto L_0x00a3
        L_0x00a1:
            r5 = r1
            r3 = 0
        L_0x00a3:
            if (r3 != 0) goto L_0x00ac
            java.lang.String r3 = "QMTAuMC4wLjE3Mg=="
            java.lang.String r3 = a((java.lang.String) r3)     // Catch:{ SecurityException -> 0x006b, Throwable -> 0x0066 }
            goto L_0x00ad
        L_0x00ac:
            r3 = r5
        L_0x00ad:
            if (r4 != r10) goto L_0x0072
            goto L_0x00b8
        L_0x00b0:
            r11 = move-exception
            r3 = r1
            goto L_0x00d0
        L_0x00b3:
            r3 = move-exception
            r5 = r1
            goto L_0x00e5
        L_0x00b6:
            r3 = r1
            r9 = -1
        L_0x00b8:
            if (r2 == 0) goto L_0x0151
            r2.close()     // Catch:{ Throwable -> 0x00bf }
            goto L_0x0151
        L_0x00bf:
            r11 = move-exception
            java.lang.String r0 = "pu"
            java.lang.String r2 = "gPx2"
            com.amap.api.services.a.cl.c(r11, r0, r2)
            goto L_0x0151
        L_0x00c9:
            r11 = move-exception
            r2 = r1
            goto L_0x0170
        L_0x00cd:
            r11 = move-exception
            r2 = r1
            r3 = r2
        L_0x00d0:
            r9 = -1
        L_0x00d1:
            java.lang.String r0 = "pu"
            java.lang.String r4 = "gPx1"
            com.amap.api.services.a.cl.c(r11, r0, r4)     // Catch:{ all -> 0x016f }
            r11.printStackTrace()     // Catch:{ all -> 0x016f }
            if (r2 == 0) goto L_0x0151
            r2.close()     // Catch:{ Throwable -> 0x00bf }
            goto L_0x0151
        L_0x00e2:
            r3 = move-exception
            r2 = r1
            r5 = r2
        L_0x00e5:
            r4 = -1
        L_0x00e6:
            java.lang.String r6 = "pu"
            java.lang.String r7 = "ghp"
            com.amap.api.services.a.cl.c(r3, r6, r7)     // Catch:{ all -> 0x016f }
            java.lang.String r11 = com.amap.api.services.a.bt.s(r11)     // Catch:{ all -> 0x016f }
            if (r11 == 0) goto L_0x014a
            java.util.Locale r3 = java.util.Locale.US     // Catch:{ all -> 0x016f }
            java.lang.String r11 = r11.toLowerCase(r3)     // Catch:{ all -> 0x016f }
            java.lang.String r3 = a()     // Catch:{ all -> 0x016f }
            int r4 = b()     // Catch:{ all -> 0x016f }
            java.lang.String r6 = "ctwap"
            int r6 = r11.indexOf(r6)     // Catch:{ all -> 0x016f }
            if (r6 == r10) goto L_0x0127
            boolean r11 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x016f }
            if (r11 != 0) goto L_0x0119
            java.lang.String r11 = "null"
            boolean r11 = r3.equals(r11)     // Catch:{ all -> 0x016f }
            if (r11 != 0) goto L_0x0119
            r0 = 1
            goto L_0x011a
        L_0x0119:
            r3 = r5
        L_0x011a:
            if (r0 != 0) goto L_0x0122
            java.lang.String r11 = "QMTAuMC4wLjIwMA=="
            java.lang.String r3 = a((java.lang.String) r11)     // Catch:{ all -> 0x016f }
        L_0x0122:
            if (r4 != r10) goto L_0x0125
            goto L_0x014c
        L_0x0125:
            r9 = r4
            goto L_0x014c
        L_0x0127:
            java.lang.String r6 = "wap"
            int r11 = r11.indexOf(r6)     // Catch:{ all -> 0x016f }
            if (r11 == r10) goto L_0x014a
            boolean r11 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x016f }
            if (r11 != 0) goto L_0x0140
            java.lang.String r11 = "null"
            boolean r11 = r3.equals(r11)     // Catch:{ all -> 0x016f }
            if (r11 != 0) goto L_0x0140
            r0 = 1
            goto L_0x0141
        L_0x0140:
            r3 = r5
        L_0x0141:
            if (r0 != 0) goto L_0x014c
            java.lang.String r11 = "QMTAuMC4wLjE3Mg=="
            java.lang.String r3 = a((java.lang.String) r11)     // Catch:{ all -> 0x016f }
            goto L_0x014c
        L_0x014a:
            r9 = r4
            r3 = r5
        L_0x014c:
            if (r2 == 0) goto L_0x0151
            r2.close()     // Catch:{ Throwable -> 0x00bf }
        L_0x0151:
            boolean r11 = a((java.lang.String) r3, (int) r9)     // Catch:{ Throwable -> 0x0163 }
            if (r11 == 0) goto L_0x017f
            java.net.Proxy r11 = new java.net.Proxy     // Catch:{ Throwable -> 0x0163 }
            java.net.Proxy$Type r0 = java.net.Proxy.Type.HTTP     // Catch:{ Throwable -> 0x0163 }
            java.net.InetSocketAddress r2 = java.net.InetSocketAddress.createUnresolved(r3, r9)     // Catch:{ Throwable -> 0x0163 }
            r11.<init>(r0, r2)     // Catch:{ Throwable -> 0x0163 }
            return r11
        L_0x0163:
            r11 = move-exception
            java.lang.String r0 = "pu"
            java.lang.String r2 = "gp2"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r11, (java.lang.String) r0, (java.lang.String) r2)
            r11.printStackTrace()
            goto L_0x017f
        L_0x016f:
            r11 = move-exception
        L_0x0170:
            if (r2 == 0) goto L_0x017e
            r2.close()     // Catch:{ Throwable -> 0x0176 }
            goto L_0x017e
        L_0x0176:
            r0 = move-exception
            java.lang.String r1 = "pu"
            java.lang.String r2 = "gPx2"
            com.amap.api.services.a.cl.c(r0, r1, r2)
        L_0x017e:
            throw r11
        L_0x017f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.bx.b(android.content.Context):java.net.Proxy");
    }

    public static String a(String str) {
        return bz.c(str);
    }

    private static boolean a(String str, int i) {
        return (str == null || str.length() <= 0 || i == -1) ? false : true;
    }

    private static String a() {
        String str;
        try {
            str = android.net.Proxy.getDefaultHost();
        } catch (Throwable th) {
            cl.c(th, "pu", "gdh");
            str = null;
        }
        return str == null ? "null" : str;
    }

    private static Proxy a(Context context, URI uri) {
        Proxy proxy;
        if (c(context)) {
            try {
                List<Proxy> select = ProxySelector.getDefault().select(uri);
                if (select == null || select.isEmpty() || (proxy = select.get(0)) == null || proxy.type() == Proxy.Type.DIRECT) {
                    return null;
                }
                return proxy;
            } catch (Throwable th) {
                cl.c(th, "pu", "gpsc");
            }
        }
        return null;
    }

    private static boolean c(Context context) {
        return bt.q(context) == 0;
    }

    private static int b() {
        try {
            return android.net.Proxy.getDefaultPort();
        } catch (Throwable th) {
            cl.c(th, "pu", "gdp");
            return -1;
        }
    }
}
