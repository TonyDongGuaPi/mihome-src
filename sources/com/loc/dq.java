package com.loc;

import android.content.Context;
import java.util.concurrent.Callable;

final class dq implements Callable {
    private static dg c = dg.a();
    /* access modifiers changed from: private */
    public static Context d;

    /* renamed from: a  reason: collision with root package name */
    private String f6566a;
    private int b = 1;

    dq(String str) {
        this.f6566a = str;
    }

    static void a(Context context) {
        d = context;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x012c A[Catch:{ all -> 0x0165 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0147  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x016d A[SYNTHETIC, Splitter:B:85:0x016d] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0175 A[Catch:{ IOException -> 0x0171 }] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String[] call() {
        /*
            r7 = this;
            r0 = 0
            boolean r1 = com.loc.dl.a()     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            if (r1 != 0) goto L_0x001c
            java.lang.Thread r1 = new java.lang.Thread     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            com.loc.dr r2 = new com.loc.dr     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            r2.<init>(r7)     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            com.loc.dm r2 = new com.loc.dm     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            r2.<init>()     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            r1.setUncaughtExceptionHandler(r2)     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            r1.start()     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
        L_0x001c:
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            r2 = 14
            if (r1 < r2) goto L_0x0028
            r1 = 40965(0xa005, float:5.7404E-41)
            android.net.TrafficStats.setThreadStatsTag(r1)     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
        L_0x0028:
            java.lang.String r1 = r7.f6566a     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            com.loc.dg.c(r1)     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            java.lang.String r2 = "http://203.107.1.1:80/"
            r1.<init>(r2)     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            java.lang.String r2 = com.loc.dj.f6561a     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            r1.append(r2)     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            java.lang.String r2 = "/d?host="
            r1.append(r2)     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            java.lang.String r2 = r7.f6566a     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            r1.append(r2)     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            java.net.URL r2 = new java.net.URL     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            java.net.URLConnection r1 = r2.openConnection()     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Throwable -> 0x011c, all -> 0x0116 }
            r2 = 15000(0x3a98, float:2.102E-41)
            r1.setConnectTimeout(r2)     // Catch:{ Throwable -> 0x0111, all -> 0x010b }
            r1.setReadTimeout(r2)     // Catch:{ Throwable -> 0x0111, all -> 0x010b }
            int r2 = r1.getResponseCode()     // Catch:{ Throwable -> 0x0111, all -> 0x010b }
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 == r3) goto L_0x0083
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0111, all -> 0x010b }
            java.lang.String r3 = "response code is "
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0111, all -> 0x010b }
            int r3 = r1.getResponseCode()     // Catch:{ Throwable -> 0x0111, all -> 0x010b }
            r2.append(r3)     // Catch:{ Throwable -> 0x0111, all -> 0x010b }
            java.lang.String r3 = " expect 200"
            r2.append(r3)     // Catch:{ Throwable -> 0x0111, all -> 0x010b }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x0111, all -> 0x010b }
            com.loc.dk.b(r2)     // Catch:{ Throwable -> 0x0111, all -> 0x010b }
            if (r1 == 0) goto L_0x015d
            r1.disconnect()
            goto L_0x015d
        L_0x0083:
            java.io.InputStream r2 = r1.getInputStream()     // Catch:{ Throwable -> 0x0111, all -> 0x010b }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0106, all -> 0x0100 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0106, all -> 0x0100 }
            java.lang.String r5 = "UTF-8"
            r4.<init>(r2, r5)     // Catch:{ Throwable -> 0x0106, all -> 0x0100 }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x0106, all -> 0x0100 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00fe }
            r0.<init>()     // Catch:{ Throwable -> 0x00fe }
        L_0x0098:
            java.lang.String r4 = r3.readLine()     // Catch:{ Throwable -> 0x00fe }
            if (r4 == 0) goto L_0x00a2
            r0.append(r4)     // Catch:{ Throwable -> 0x00fe }
            goto L_0x0098
        L_0x00a2:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r5 = "resolve host: "
            r4.<init>(r5)     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r5 = r7.f6566a     // Catch:{ Throwable -> 0x00fe }
            r4.append(r5)     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r5 = ", return: "
            r4.append(r5)     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r5 = r0.toString()     // Catch:{ Throwable -> 0x00fe }
            r4.append(r5)     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x00fe }
            com.loc.dk.a((java.lang.String) r4)     // Catch:{ Throwable -> 0x00fe }
            com.loc.dh r4 = new com.loc.dh     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x00fe }
            r4.<init>(r0)     // Catch:{ Throwable -> 0x00fe }
            int r0 = com.loc.dg.b()     // Catch:{ Throwable -> 0x00fe }
            r5 = 100
            if (r0 >= r5) goto L_0x00f5
            java.lang.String r0 = r7.f6566a     // Catch:{ Throwable -> 0x00fe }
            com.loc.dg.a(r0, r4)     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r0 = r7.f6566a     // Catch:{ Throwable -> 0x00fe }
            com.loc.dg.d(r0)     // Catch:{ Throwable -> 0x00fe }
            java.lang.String[] r0 = r4.a()     // Catch:{ Throwable -> 0x00fe }
            if (r1 == 0) goto L_0x00e5
            r1.disconnect()
        L_0x00e5:
            if (r2 == 0) goto L_0x00ed
            r2.close()     // Catch:{ IOException -> 0x00eb }
            goto L_0x00ed
        L_0x00eb:
            r1 = move-exception
            goto L_0x00f1
        L_0x00ed:
            r3.close()     // Catch:{ IOException -> 0x00eb }
            goto L_0x00f4
        L_0x00f1:
            com.loc.dk.a((java.lang.Throwable) r1)
        L_0x00f4:
            return r0
        L_0x00f5:
            java.lang.Exception r0 = new java.lang.Exception     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r4 = "the total number of hosts is exceed 100"
            r0.<init>(r4)     // Catch:{ Throwable -> 0x00fe }
            throw r0     // Catch:{ Throwable -> 0x00fe }
        L_0x00fe:
            r0 = move-exception
            goto L_0x0121
        L_0x0100:
            r3 = move-exception
            r6 = r3
            r3 = r0
            r0 = r6
            goto L_0x0166
        L_0x0106:
            r3 = move-exception
            r6 = r3
            r3 = r0
            r0 = r6
            goto L_0x0121
        L_0x010b:
            r2 = move-exception
            r3 = r0
            r0 = r2
            r2 = r3
            goto L_0x0166
        L_0x0111:
            r2 = move-exception
            r3 = r0
            r0 = r2
            r2 = r3
            goto L_0x0121
        L_0x0116:
            r1 = move-exception
            r2 = r0
            r3 = r2
            r0 = r1
            r1 = r3
            goto L_0x0166
        L_0x011c:
            r1 = move-exception
            r2 = r0
            r3 = r2
            r0 = r1
            r1 = r3
        L_0x0121:
            com.loc.dk.a((java.lang.Throwable) r0)     // Catch:{ all -> 0x0165 }
            int r0 = r7.b     // Catch:{ all -> 0x0165 }
            int r4 = r0 + -1
            r7.b = r4     // Catch:{ all -> 0x0165 }
            if (r0 <= 0) goto L_0x0147
            java.lang.String[] r0 = r7.call()     // Catch:{ all -> 0x0165 }
            if (r1 == 0) goto L_0x0135
            r1.disconnect()
        L_0x0135:
            if (r2 == 0) goto L_0x013d
            r2.close()     // Catch:{ IOException -> 0x013b }
            goto L_0x013d
        L_0x013b:
            r1 = move-exception
            goto L_0x0143
        L_0x013d:
            if (r3 == 0) goto L_0x0146
            r3.close()     // Catch:{ IOException -> 0x013b }
            goto L_0x0146
        L_0x0143:
            com.loc.dk.a((java.lang.Throwable) r1)
        L_0x0146:
            return r0
        L_0x0147:
            if (r1 == 0) goto L_0x014c
            r1.disconnect()
        L_0x014c:
            if (r2 == 0) goto L_0x0154
            r2.close()     // Catch:{ IOException -> 0x0152 }
            goto L_0x0154
        L_0x0152:
            r0 = move-exception
            goto L_0x015a
        L_0x0154:
            if (r3 == 0) goto L_0x015d
            r3.close()     // Catch:{ IOException -> 0x0152 }
            goto L_0x015d
        L_0x015a:
            com.loc.dk.a((java.lang.Throwable) r0)
        L_0x015d:
            java.lang.String r0 = r7.f6566a
            com.loc.dg.d(r0)
            java.lang.String[] r0 = com.loc.dj.b
            return r0
        L_0x0165:
            r0 = move-exception
        L_0x0166:
            if (r1 == 0) goto L_0x016b
            r1.disconnect()
        L_0x016b:
            if (r2 == 0) goto L_0x0173
            r2.close()     // Catch:{ IOException -> 0x0171 }
            goto L_0x0173
        L_0x0171:
            r1 = move-exception
            goto L_0x0179
        L_0x0173:
            if (r3 == 0) goto L_0x017c
            r3.close()     // Catch:{ IOException -> 0x0171 }
            goto L_0x017c
        L_0x0179:
            com.loc.dk.a((java.lang.Throwable) r1)
        L_0x017c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dq.call():java.lang.String[]");
    }
}
