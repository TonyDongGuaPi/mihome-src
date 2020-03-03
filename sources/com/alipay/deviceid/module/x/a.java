package com.alipay.deviceid.module.x;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    private Context f866a;
    private b b = b.a();
    private int c = 4;

    public a(Context context) {
        this.f866a = context;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0033, code lost:
        if (com.alipay.deviceid.module.x.e.a(a(r7.f866a, r0)) != false) goto L_0x0035;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006c A[Catch:{ Exception -> 0x0155 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x010e A[Catch:{ Exception -> 0x0155 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(java.util.Map<java.lang.String, java.lang.String> r8) {
        /*
            r7 = this;
            android.content.Context r0 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            java.lang.String r1 = "tid"
            java.lang.String r2 = ""
            java.lang.String r1 = com.alipay.deviceid.module.x.e.a(r8, r1, r2)     // Catch:{ Exception -> 0x0155 }
            java.lang.String r2 = "utdid"
            java.lang.String r3 = ""
            java.lang.String r2 = com.alipay.deviceid.module.x.e.a(r8, r2, r3)     // Catch:{ Exception -> 0x0155 }
            java.lang.String r3 = ""
            com.alipay.deviceid.module.x.z.a(r0, r1, r2, r3)     // Catch:{ Exception -> 0x0155 }
            java.lang.String r0 = "appName"
            java.lang.String r1 = ""
            java.lang.String r0 = com.alipay.deviceid.module.x.e.a(r8, r0, r1)     // Catch:{ Exception -> 0x0155 }
            boolean r1 = com.alipay.deviceid.module.x.ca.a()     // Catch:{ Exception -> 0x0155 }
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0039
            android.content.Context r1 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            java.lang.String r1 = a(r1, r0)     // Catch:{ Exception -> 0x0155 }
            boolean r1 = com.alipay.deviceid.module.x.e.a((java.lang.String) r1)     // Catch:{ Exception -> 0x0155 }
            if (r1 == 0) goto L_0x0037
        L_0x0035:
            r1 = 1
            goto L_0x0067
        L_0x0037:
            r1 = 0
            goto L_0x0067
        L_0x0039:
            com.alipay.deviceid.module.x.d r1 = com.alipay.deviceid.module.x.d.a()     // Catch:{ Exception -> 0x0155 }
            android.content.Context r4 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            java.lang.String r1 = r1.a(r4)     // Catch:{ Exception -> 0x0155 }
            android.content.Context r4 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            java.lang.String r4 = com.alipay.deviceid.module.x.by.a(r4, r0)     // Catch:{ Exception -> 0x0155 }
            boolean r1 = com.alipay.deviceid.module.x.e.a(r1, r4)     // Catch:{ Exception -> 0x0155 }
            r1 = r1 ^ r3
            if (r1 == 0) goto L_0x0051
            goto L_0x0035
        L_0x0051:
            android.content.Context r1 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            boolean r1 = com.alipay.deviceid.module.x.ca.a(r1, r0)     // Catch:{ Exception -> 0x0155 }
            if (r1 != 0) goto L_0x005a
            goto L_0x0035
        L_0x005a:
            android.content.Context r1 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            java.lang.String r1 = a(r1, r0)     // Catch:{ Exception -> 0x0155 }
            boolean r1 = com.alipay.deviceid.module.x.e.a((java.lang.String) r1)     // Catch:{ Exception -> 0x0155 }
            if (r1 == 0) goto L_0x0037
            goto L_0x0035
        L_0x0067:
            if (r1 != 0) goto L_0x006c
        L_0x0069:
            r8 = 0
            goto L_0x00f3
        L_0x006c:
            com.alipay.deviceid.module.x.q r8 = r7.b(r8)     // Catch:{ Exception -> 0x0155 }
            r1 = 3
            r4 = 2
            if (r8 == 0) goto L_0x008d
            boolean r5 = r8.f934a     // Catch:{ Exception -> 0x0155 }
            if (r5 == 0) goto L_0x0082
            java.lang.String r5 = r8.c     // Catch:{ Exception -> 0x0155 }
            boolean r5 = com.alipay.deviceid.module.x.e.a((java.lang.String) r5)     // Catch:{ Exception -> 0x0155 }
            if (r5 != 0) goto L_0x008d
            r4 = 1
            goto L_0x008d
        L_0x0082:
            java.lang.String r5 = "APPKEY_ERROR"
            java.lang.String r6 = r8.b     // Catch:{ Exception -> 0x0155 }
            boolean r5 = r5.equals(r6)     // Catch:{ Exception -> 0x0155 }
            if (r5 == 0) goto L_0x008d
            r4 = 3
        L_0x008d:
            if (r4 == r3) goto L_0x00a1
            if (r4 == r1) goto L_0x009f
            android.content.Context r8 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            java.lang.String r8 = a(r8, r0)     // Catch:{ Exception -> 0x0155 }
            boolean r8 = com.alipay.deviceid.module.x.e.a((java.lang.String) r8)     // Catch:{ Exception -> 0x0155 }
            if (r8 == 0) goto L_0x0069
            r8 = 4
            goto L_0x00f3
        L_0x009f:
            r8 = 1
            goto L_0x00f3
        L_0x00a1:
            android.content.Context r1 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            java.lang.String r4 = "1"
            java.lang.String r5 = r8.e     // Catch:{ Exception -> 0x0155 }
            boolean r4 = r4.equals(r5)     // Catch:{ Exception -> 0x0155 }
            com.alipay.deviceid.module.x.bz.a((android.content.Context) r1, (boolean) r4)     // Catch:{ Exception -> 0x0155 }
            android.content.Context r1 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            java.lang.String r4 = r8.g     // Catch:{ Exception -> 0x0155 }
            com.alipay.deviceid.module.x.bz.a((android.content.Context) r1, (java.lang.String) r4)     // Catch:{ Exception -> 0x0155 }
            android.content.Context r1 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            java.lang.String r4 = r8.c     // Catch:{ Exception -> 0x0155 }
            com.alipay.deviceid.module.x.by.b(r1, r0, r4)     // Catch:{ Exception -> 0x0155 }
            com.alipay.deviceid.module.x.by.a()     // Catch:{ Exception -> 0x0155 }
            android.content.Context r1 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            com.alipay.deviceid.module.x.d r4 = com.alipay.deviceid.module.x.d.a()     // Catch:{ Exception -> 0x0155 }
            android.content.Context r5 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            java.lang.String r4 = r4.a(r5)     // Catch:{ Exception -> 0x0155 }
            com.alipay.deviceid.module.x.by.a(r1, r0, r4)     // Catch:{ Exception -> 0x0155 }
            android.content.Context r1 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            java.lang.String r4 = "public"
            java.lang.String r8 = r8.c     // Catch:{ Exception -> 0x0155 }
            com.alipay.deviceid.module.x.by.b(r1, r4, r8)     // Catch:{ Exception -> 0x0155 }
            android.content.Context r8 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            java.lang.String r1 = "public"
            com.alipay.deviceid.module.x.d r4 = com.alipay.deviceid.module.x.d.a()     // Catch:{ Exception -> 0x0155 }
            android.content.Context r5 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            java.lang.String r4 = r4.a(r5)     // Catch:{ Exception -> 0x0155 }
            com.alipay.deviceid.module.x.by.a(r8, r1, r4)     // Catch:{ Exception -> 0x0155 }
            android.content.Context r8 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0155 }
            com.alipay.deviceid.module.x.bz.a((android.content.Context) r8, (java.lang.String) r0, (long) r4)     // Catch:{ Exception -> 0x0155 }
            goto L_0x0069
        L_0x00f3:
            r7.c = r8     // Catch:{ Exception -> 0x0155 }
            android.content.Context r8 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            com.alipay.deviceid.module.x.b r0 = r7.b     // Catch:{ Exception -> 0x0155 }
            java.lang.String r0 = r0.b()     // Catch:{ Exception -> 0x0155 }
            com.alipay.deviceid.module.x.s r8 = com.alipay.deviceid.module.x.s.a((android.content.Context) r8, (java.lang.String) r0)     // Catch:{ Exception -> 0x0155 }
            android.content.Context r0 = r7.f866a     // Catch:{ Exception -> 0x0155 }
            r1 = 0
            java.lang.String r4 = "connectivity"
            java.lang.Object r4 = r0.getSystemService(r4)     // Catch:{ Exception -> 0x0155 }
            android.net.ConnectivityManager r4 = (android.net.ConnectivityManager) r4     // Catch:{ Exception -> 0x0155 }
            if (r4 == 0) goto L_0x0112
            android.net.NetworkInfo r1 = r4.getActiveNetworkInfo()     // Catch:{ Exception -> 0x0155 }
        L_0x0112:
            if (r1 == 0) goto L_0x0121
            boolean r4 = r1.isConnected()     // Catch:{ Exception -> 0x0155 }
            if (r4 == 0) goto L_0x0121
            int r1 = r1.getType()     // Catch:{ Exception -> 0x0155 }
            if (r1 != r3) goto L_0x0121
            r2 = 1
        L_0x0121:
            if (r2 == 0) goto L_0x0159
            boolean r1 = com.alipay.deviceid.module.x.bz.a(r0)     // Catch:{ Exception -> 0x0155 }
            if (r1 == 0) goto L_0x0159
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0155 }
            r1.<init>()     // Catch:{ Exception -> 0x0155 }
            java.io.File r0 = r0.getFilesDir()     // Catch:{ Exception -> 0x0155 }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ Exception -> 0x0155 }
            r1.append(r0)     // Catch:{ Exception -> 0x0155 }
            java.lang.String r0 = "/log/ap"
            r1.append(r0)     // Catch:{ Exception -> 0x0155 }
            java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x0155 }
            com.alipay.deviceid.module.x.x r1 = new com.alipay.deviceid.module.x.x     // Catch:{ Exception -> 0x0155 }
            r1.<init>(r0, r8)     // Catch:{ Exception -> 0x0155 }
            java.lang.Thread r8 = new java.lang.Thread     // Catch:{ Exception -> 0x0155 }
            com.alipay.deviceid.module.x.x$1 r0 = new com.alipay.deviceid.module.x.x$1     // Catch:{ Exception -> 0x0155 }
            r0.<init>()     // Catch:{ Exception -> 0x0155 }
            r8.<init>(r0)     // Catch:{ Exception -> 0x0155 }
            r8.start()     // Catch:{ Exception -> 0x0155 }
            goto L_0x0159
        L_0x0155:
            r8 = move-exception
            com.alipay.deviceid.module.x.z.a((java.lang.Throwable) r8)
        L_0x0159:
            int r8 = r7.c
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.a.a(java.util.Map):int");
    }

    private q b(Map<String, String> map) {
        try {
            Context context = this.f866a;
            r rVar = new r();
            String a2 = e.a(map, "rpcVersion", "");
            String a3 = a(context, e.a(map, "appName", ""));
            rVar.f935a = "android";
            rVar.b = a3;
            rVar.c = a2;
            d a4 = d.a();
            if (a4.f925a == null) {
                a4.b(context);
            }
            Map<String, String> map2 = a4.f925a;
            HashMap hashMap = new HashMap();
            String a5 = e.a(map, "appName", "");
            String a6 = e.a(map, "appKeyClient", "");
            hashMap.put("AC1", "");
            hashMap.put("AC2", "");
            hashMap.put("AC3", "");
            hashMap.put("AC5", "");
            hashMap.put("AC6", "");
            hashMap.put("AC7", "");
            hashMap.put("AC8", a5);
            hashMap.put("AC9", a6);
            map2.putAll(hashMap);
            Map<String, String> map3 = a4.f925a;
            HashMap hashMap2 = new HashMap();
            hashMap2.put("AE18", "");
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis() / 1000);
            hashMap2.put("AE22", sb.toString());
            map3.putAll(hashMap2);
            Map<String, String> map4 = a4.f925a;
            k.a();
            HashMap hashMap3 = new HashMap();
            hashMap3.put("AD19", k.p(context));
            StringBuilder sb2 = new StringBuilder();
            sb2.append(k.r());
            hashMap3.put("AD32", sb2.toString());
            hashMap3.put("AD33", k.s());
            hashMap3.put("AD35", k.t(context));
            hashMap3.put("AD36", k.r(context));
            hashMap3.put("AD40", k.d(context));
            hashMap3.put("AD41", k.c());
            hashMap3.put("AD42", k.d());
            hashMap3.put("AD43", k.b());
            hashMap3.put("AL3", k.q(context));
            map4.putAll(hashMap3);
            a4.f925a.putAll(c.a(map));
            rVar.d = a4.f925a;
            return s.a(this.f866a, this.b.b()).a(this.f866a, rVar);
        } catch (Throwable th) {
            z.a(th);
            return null;
        }
    }

    public static String a(Context context, String str) {
        try {
            String b2 = by.b(context, str);
            return !e.a(b2) ? b2 : "";
        } catch (Throwable unused) {
            return "";
        }
    }
}
