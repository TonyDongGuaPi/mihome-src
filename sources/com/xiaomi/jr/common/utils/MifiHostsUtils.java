package com.xiaomi.jr.common.utils;

import android.net.Uri;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class MifiHostsUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1415a = "MifiHostsUtils";
    private static Map<String, ServiceConfig> b = new HashMap();

    public static void a(String str) {
        Map<String, ServiceConfig> i = i(str);
        if (i != null) {
            b = i;
        }
    }

    public static ServiceConfig b(String str) {
        return b.get(str);
    }

    public static String c(String str) {
        String d = d(str);
        return d != null ? d : str;
    }

    public static String d(String str) {
        ServiceConfig serviceConfig = b.get(str);
        if (serviceConfig != null) {
            return serviceConfig.b;
        }
        return null;
    }

    public static String e(String str) {
        for (ServiceConfig next : b.values()) {
            if (TextUtils.equals(next.b, str)) {
                return next.f1416a;
            }
        }
        return null;
    }

    public static String f(String str) {
        for (ServiceConfig next : b.values()) {
            if (TextUtils.equals(Uri.parse(next.f1416a).getHost(), str)) {
                String host = Uri.parse(next.b).getHost();
                return host != null ? host : str;
            }
        }
        return str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0062 A[SYNTHETIC, Splitter:B:23:0x0062] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0068 A[SYNTHETIC, Splitter:B:27:0x0068] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.Map<java.lang.String, com.xiaomi.jr.common.utils.MifiHostsUtils.ServiceConfig> i(java.lang.String r5) {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0049 }
            java.io.StringReader r3 = new java.io.StringReader     // Catch:{ Exception -> 0x0049 }
            r3.<init>(r5)     // Catch:{ Exception -> 0x0049 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0049 }
        L_0x0010:
            java.lang.String r5 = r2.readLine()     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            if (r5 == 0) goto L_0x0038
            com.xiaomi.jr.common.utils.MifiHostsUtils$ServiceConfig r5 = j(r5)     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            if (r5 == 0) goto L_0x0010
            java.lang.String r1 = "MifiHostsUtils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            r3.<init>()     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            java.lang.String r4 = "Found ServiceConfig - "
            r3.append(r4)     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            r3.append(r5)     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            com.xiaomi.jr.common.utils.MifiLog.b(r1, r3)     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            java.lang.String r1 = r5.f1416a     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            r0.put(r1, r5)     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            goto L_0x0010
        L_0x0038:
            r2.close()     // Catch:{ IOException -> 0x003c }
            goto L_0x0065
        L_0x003c:
            r5 = move-exception
            r5.printStackTrace()
            goto L_0x0065
        L_0x0041:
            r5 = move-exception
            r1 = r2
            goto L_0x0066
        L_0x0044:
            r5 = move-exception
            r1 = r2
            goto L_0x004a
        L_0x0047:
            r5 = move-exception
            goto L_0x0066
        L_0x0049:
            r5 = move-exception
        L_0x004a:
            java.lang.String r2 = "MifiHostsUtils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0047 }
            r3.<init>()     // Catch:{ all -> 0x0047 }
            java.lang.String r4 = "readHostsFile throw exception - "
            r3.append(r4)     // Catch:{ all -> 0x0047 }
            r3.append(r5)     // Catch:{ all -> 0x0047 }
            java.lang.String r5 = r3.toString()     // Catch:{ all -> 0x0047 }
            com.xiaomi.jr.common.utils.MifiLog.e(r2, r5)     // Catch:{ all -> 0x0047 }
            if (r1 == 0) goto L_0x0065
            r1.close()     // Catch:{ IOException -> 0x003c }
        L_0x0065:
            return r0
        L_0x0066:
            if (r1 == 0) goto L_0x0070
            r1.close()     // Catch:{ IOException -> 0x006c }
            goto L_0x0070
        L_0x006c:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0070:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.common.utils.MifiHostsUtils.i(java.lang.String):java.util.Map");
    }

    private static ServiceConfig j(String str) {
        int indexOf = str.indexOf(35);
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
        }
        String[] split = str.trim().split(" +");
        if (split.length < 3) {
            return null;
        }
        return new ServiceConfig(split[1], split[0], split[2]);
    }

    public static class ServiceConfig {

        /* renamed from: a  reason: collision with root package name */
        public String f1416a;
        public String b;
        public String c;

        ServiceConfig(String str, String str2, String str3) {
            this.f1416a = str;
            this.b = str2;
            this.c = str3;
        }

        public String toString() {
            return "[ServiceConfig] baseUrl: " + this.f1416a + ", envBaseUrl: " + this.b + ", envServiceId: " + this.c;
        }
    }

    public static String g(String str) {
        String d = d(UrlUtils.a(str));
        return !TextUtils.isEmpty(d) ? UrlUtils.a(str, d) : str;
    }

    public static String h(String str) {
        String e = e(UrlUtils.a(str));
        return !TextUtils.isEmpty(e) ? UrlUtils.a(str, e) : str;
    }
}
