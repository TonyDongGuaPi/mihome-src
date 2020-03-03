package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.a.a.a.a.g;
import com.tencent.wxop.stat.common.StatConstants;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.common.k;
import com.tencent.wxop.stat.common.p;
import com.tencent.wxop.stat.common.q;
import java.net.URI;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class StatConfig {
    private static int A = 1;
    private static String B = null;
    private static String C;
    private static String D;
    private static String E = "mta_channel";
    private static int F = 180;
    private static int G = 1024;
    private static long H = 0;
    private static long I = 300000;
    private static volatile String J = StatConstants.f;
    private static int K = 0;
    private static volatile int L = 0;
    private static int M = 20;
    private static int N = 0;
    private static boolean O = false;
    private static int P = 4096;
    private static boolean Q = false;
    private static String R = null;
    private static boolean S = false;
    private static g T = null;

    /* renamed from: a  reason: collision with root package name */
    static f f9265a = new f(2);
    static f b = new f(1);
    static String c = "__HIBERNATE__";
    static String d = "__HIBERNATE__TIME";
    static String e = "__MTA_KILL__";
    static String f = "";
    static boolean g = false;
    static int h = 100;
    static long i = 10000;
    static boolean j = true;
    public static boolean k = true;
    static volatile String l = StatConstants.d;
    static boolean m = true;
    static int n = 0;
    static long o = 10000;
    static int p = 512;
    private static StatLogger q = k.b();
    private static StatReportStrategy r = StatReportStrategy.APP_LAUNCH;
    private static boolean s = false;
    private static boolean t = true;
    private static int u = 30000;
    private static int v = 100000;
    private static int w = 30;
    private static int x = 10;
    private static int y = 100;
    private static int z = 30;

    public static g A() {
        return T;
    }

    public static boolean B() {
        return m;
    }

    public static int C() {
        return n;
    }

    public static long D() {
        return o;
    }

    public static int E() {
        return p;
    }

    public static StatReportStrategy a() {
        return r;
    }

    static String a(Context context) {
        return q.a(p.a(context, "_mta_ky_tag_", (String) null));
    }

    public static String a(String str) {
        try {
            return f9265a.b.getString(str);
        } catch (Throwable th) {
            q.b(th);
            return null;
        }
    }

    public static String a(String str, String str2) {
        try {
            String string = f9265a.b.getString(str);
            return string != null ? string : str2;
        } catch (Throwable th) {
            q.b(th);
        }
    }

    public static void a(int i2) {
        if (!a(i2, 1000, 86400000)) {
            q.g("setSessionTimoutMillis can not exceed the range of [1000, 24 * 60 * 60 * 1000].");
        } else {
            u = i2;
        }
    }

    public static void a(int i2, long j2) {
        h = i2;
        if (j2 >= 1000) {
            i = j2;
        }
    }

    static void a(long j2) {
        p.b(i.a(), c, j2);
        b(false);
        q.e("MTA is disable for current SDK version");
    }

    static void a(Context context, f fVar) {
        if (fVar.f9327a == b.f9327a) {
            b = fVar;
            a(fVar.b);
            if (!b.b.isNull("iplist")) {
                a.a(context).a(b.b.getString("iplist"));
            }
        } else if (fVar.f9327a == f9265a.f9327a) {
            f9265a = fVar;
        }
    }

    static void a(Context context, f fVar, JSONObject jSONObject) {
        boolean z2 = false;
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (next.equalsIgnoreCase("v")) {
                    int i2 = jSONObject.getInt(next);
                    if (fVar.d != i2) {
                        z2 = true;
                    }
                    fVar.d = i2;
                } else if (next.equalsIgnoreCase("c")) {
                    String string = jSONObject.getString("c");
                    if (string.length() > 0) {
                        fVar.b = new JSONObject(string);
                    }
                } else if (next.equalsIgnoreCase("m")) {
                    fVar.c = jSONObject.getString("m");
                }
            }
            if (z2) {
                au a2 = au.a(i.a());
                if (a2 != null) {
                    a2.a(fVar);
                }
                if (fVar.f9327a == b.f9327a) {
                    a(fVar.b);
                    b(fVar.b);
                }
            }
            a(context, fVar);
        } catch (Throwable th) {
            q.b(th);
        }
    }

    static void a(Context context, String str) {
        if (str != null) {
            p.b(context, "_mta_ky_tag_", q.b(str));
        }
    }

    static void a(Context context, JSONObject jSONObject) {
        JSONObject jSONObject2;
        f fVar;
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (next.equalsIgnoreCase(Integer.toString(b.f9327a))) {
                    jSONObject2 = jSONObject.getJSONObject(next);
                    fVar = b;
                } else if (next.equalsIgnoreCase(Integer.toString(f9265a.f9327a))) {
                    jSONObject2 = jSONObject.getJSONObject(next);
                    fVar = f9265a;
                } else if (next.equalsIgnoreCase("rs")) {
                    StatReportStrategy statReportStrategy = StatReportStrategy.getStatReportStrategy(jSONObject.getInt(next));
                    if (statReportStrategy != null) {
                        r = statReportStrategy;
                        if (b()) {
                            StatLogger statLogger = q;
                            statLogger.j("Change to ReportStrategy:" + statReportStrategy.name());
                        }
                    }
                } else {
                    return;
                }
                a(context, fVar, jSONObject2);
            }
        } catch (JSONException e2) {
            q.b((Throwable) e2);
        }
    }

    public static void a(StatReportStrategy statReportStrategy) {
        r = statReportStrategy;
        if (statReportStrategy != StatReportStrategy.PERIOD) {
            StatServiceImpl.c = 0;
        }
        if (b()) {
            StatLogger statLogger = q;
            statLogger.j("Change to statSendStrategy: " + statReportStrategy);
        }
    }

    public static void a(g gVar) {
        T = gVar;
    }

    static void a(JSONObject jSONObject) {
        try {
            StatReportStrategy statReportStrategy = StatReportStrategy.getStatReportStrategy(jSONObject.getInt("rs"));
            if (statReportStrategy != null) {
                a(statReportStrategy);
            }
        } catch (JSONException unused) {
            if (b()) {
                q.b((Object) "rs not found.");
            }
        }
    }

    public static void a(boolean z2) {
        s = z2;
        k.b().a(z2);
    }

    static boolean a(int i2, int i3, int i4) {
        return i2 >= i3 && i2 <= i4;
    }

    static boolean a(JSONObject jSONObject, String str, String str2) {
        if (jSONObject.isNull(str)) {
            return false;
        }
        String optString = jSONObject.optString(str);
        return k.c(str2) && k.c(optString) && str2.equalsIgnoreCase(optString);
    }

    public static synchronized String b(Context context) {
        synchronized (StatConfig.class) {
            if (C != null) {
                String str = C;
                return str;
            }
            if (context != null) {
                if (C == null) {
                    C = k.f(context);
                }
            }
            if (C == null || C.trim().length() == 0) {
                q.g("AppKey can not be null or empty, please read Developer's Guide first!");
            }
            String str2 = C;
            return str2;
        }
    }

    static String b(String str, String str2) {
        try {
            String string = b.b.getString(str);
            return string != null ? string : str2;
        } catch (Throwable unused) {
            StatLogger statLogger = q;
            statLogger.f("can't find custom key:" + str);
        }
    }

    public static void b(int i2) {
        if (i2 > 100) {
            y = i2;
        }
    }

    public static void b(long j2) {
        if (j2 > 0) {
            o = j2;
        }
    }

    public static void b(Context context, String str) {
        StatLogger statLogger;
        String str2;
        if (context == null) {
            statLogger = q;
            str2 = "ctx in StatConfig.setAppKey() is null";
        } else if (str == null || str.length() > 256) {
            statLogger = q;
            str2 = "appkey in StatConfig.setAppKey() is null or exceed 256 bytes";
        } else {
            if (C == null) {
                C = a(context);
            }
            if (e(str) || e(k.f(context))) {
                a(context, C);
                return;
            }
            return;
        }
        statLogger.g(str2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0040 A[Catch:{ Exception -> 0x01b6 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void b(android.content.Context r6, org.json.JSONObject r7) {
        /*
            java.lang.String r0 = e     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r7 = r7.optString(r0)     // Catch:{ Exception -> 0x01b6 }
            boolean r0 = com.tencent.wxop.stat.common.k.c((java.lang.String) r7)     // Catch:{ Exception -> 0x01b6 }
            if (r0 == 0) goto L_0x01b5
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x01b6 }
            r0.<init>(r7)     // Catch:{ Exception -> 0x01b6 }
            int r7 = r0.length()     // Catch:{ Exception -> 0x01b6 }
            if (r7 != 0) goto L_0x0018
            return
        L_0x0018:
            java.lang.String r7 = "sm"
            boolean r7 = r0.isNull(r7)     // Catch:{ Exception -> 0x01b6 }
            r1 = 0
            if (r7 != 0) goto L_0x0077
            java.lang.String r7 = "sm"
            java.lang.Object r7 = r0.get(r7)     // Catch:{ Exception -> 0x01b6 }
            boolean r2 = r7 instanceof java.lang.Integer     // Catch:{ Exception -> 0x01b6 }
            if (r2 == 0) goto L_0x0032
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ Exception -> 0x01b6 }
        L_0x002d:
            int r7 = r7.intValue()     // Catch:{ Exception -> 0x01b6 }
            goto L_0x003e
        L_0x0032:
            boolean r2 = r7 instanceof java.lang.String     // Catch:{ Exception -> 0x01b6 }
            if (r2 == 0) goto L_0x003d
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Exception -> 0x01b6 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ Exception -> 0x01b6 }
            goto L_0x002d
        L_0x003d:
            r7 = 0
        L_0x003e:
            if (r7 <= 0) goto L_0x0077
            boolean r2 = b()     // Catch:{ Exception -> 0x01b6 }
            if (r2 == 0) goto L_0x005e
            com.tencent.wxop.stat.common.StatLogger r2 = q     // Catch:{ Exception -> 0x01b6 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r4 = "match sleepTime:"
            r3.<init>(r4)     // Catch:{ Exception -> 0x01b6 }
            r3.append(r7)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r4 = " minutes"
            r3.append(r4)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x01b6 }
            r2.b((java.lang.Object) r3)     // Catch:{ Exception -> 0x01b6 }
        L_0x005e:
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x01b6 }
            int r7 = r7 * 60
            int r7 = r7 * 1000
            long r4 = (long) r7     // Catch:{ Exception -> 0x01b6 }
            long r2 = r2 + r4
            java.lang.String r7 = d     // Catch:{ Exception -> 0x01b6 }
            com.tencent.wxop.stat.common.p.b((android.content.Context) r6, (java.lang.String) r7, (long) r2)     // Catch:{ Exception -> 0x01b6 }
            b((boolean) r1)     // Catch:{ Exception -> 0x01b6 }
            com.tencent.wxop.stat.common.StatLogger r7 = q     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r2 = "MTA is disable for current SDK version"
            r7.e(r2)     // Catch:{ Exception -> 0x01b6 }
        L_0x0077:
            java.lang.String r7 = "sv"
            java.lang.String r2 = "2.0.3"
            boolean r7 = a((org.json.JSONObject) r0, (java.lang.String) r7, (java.lang.String) r2)     // Catch:{ Exception -> 0x01b6 }
            r2 = 1
            if (r7 == 0) goto L_0x008a
            com.tencent.wxop.stat.common.StatLogger r7 = q     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r1 = "match sdk version:2.0.3"
            r7.b((java.lang.Object) r1)     // Catch:{ Exception -> 0x01b6 }
            r1 = 1
        L_0x008a:
            java.lang.String r7 = "md"
            java.lang.String r3 = android.os.Build.MODEL     // Catch:{ Exception -> 0x01b6 }
            boolean r7 = a((org.json.JSONObject) r0, (java.lang.String) r7, (java.lang.String) r3)     // Catch:{ Exception -> 0x01b6 }
            if (r7 == 0) goto L_0x00aa
            com.tencent.wxop.stat.common.StatLogger r7 = q     // Catch:{ Exception -> 0x01b6 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r3 = "match MODEL:"
            r1.<init>(r3)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r3 = android.os.Build.MODEL     // Catch:{ Exception -> 0x01b6 }
            r1.append(r3)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x01b6 }
            r7.b((java.lang.Object) r1)     // Catch:{ Exception -> 0x01b6 }
            r1 = 1
        L_0x00aa:
            java.lang.String r7 = "av"
            java.lang.String r3 = com.tencent.wxop.stat.common.k.j(r6)     // Catch:{ Exception -> 0x01b6 }
            boolean r7 = a((org.json.JSONObject) r0, (java.lang.String) r7, (java.lang.String) r3)     // Catch:{ Exception -> 0x01b6 }
            if (r7 == 0) goto L_0x00ce
            com.tencent.wxop.stat.common.StatLogger r7 = q     // Catch:{ Exception -> 0x01b6 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r3 = "match app version:"
            r1.<init>(r3)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r3 = com.tencent.wxop.stat.common.k.j(r6)     // Catch:{ Exception -> 0x01b6 }
            r1.append(r3)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x01b6 }
            r7.b((java.lang.Object) r1)     // Catch:{ Exception -> 0x01b6 }
            r1 = 1
        L_0x00ce:
            java.lang.String r7 = "mf"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b6 }
            r3.<init>()     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r4 = android.os.Build.MANUFACTURER     // Catch:{ Exception -> 0x01b6 }
            r3.append(r4)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x01b6 }
            boolean r7 = a((org.json.JSONObject) r0, (java.lang.String) r7, (java.lang.String) r3)     // Catch:{ Exception -> 0x01b6 }
            if (r7 == 0) goto L_0x00fa
            com.tencent.wxop.stat.common.StatLogger r7 = q     // Catch:{ Exception -> 0x01b6 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r3 = "match MANUFACTURER:"
            r1.<init>(r3)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r3 = android.os.Build.MANUFACTURER     // Catch:{ Exception -> 0x01b6 }
            r1.append(r3)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x01b6 }
            r7.b((java.lang.Object) r1)     // Catch:{ Exception -> 0x01b6 }
            r1 = 1
        L_0x00fa:
            java.lang.String r7 = "osv"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b6 }
            r3.<init>()     // Catch:{ Exception -> 0x01b6 }
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x01b6 }
            r3.append(r4)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x01b6 }
            boolean r7 = a((org.json.JSONObject) r0, (java.lang.String) r7, (java.lang.String) r3)     // Catch:{ Exception -> 0x01b6 }
            if (r7 == 0) goto L_0x0126
            com.tencent.wxop.stat.common.StatLogger r7 = q     // Catch:{ Exception -> 0x01b6 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r3 = "match android SDK version:"
            r1.<init>(r3)     // Catch:{ Exception -> 0x01b6 }
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x01b6 }
            r1.append(r3)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x01b6 }
            r7.b((java.lang.Object) r1)     // Catch:{ Exception -> 0x01b6 }
            r1 = 1
        L_0x0126:
            java.lang.String r7 = "ov"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b6 }
            r3.<init>()     // Catch:{ Exception -> 0x01b6 }
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x01b6 }
            r3.append(r4)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x01b6 }
            boolean r7 = a((org.json.JSONObject) r0, (java.lang.String) r7, (java.lang.String) r3)     // Catch:{ Exception -> 0x01b6 }
            if (r7 == 0) goto L_0x0152
            com.tencent.wxop.stat.common.StatLogger r7 = q     // Catch:{ Exception -> 0x01b6 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r3 = "match android SDK version:"
            r1.<init>(r3)     // Catch:{ Exception -> 0x01b6 }
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x01b6 }
            r1.append(r3)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x01b6 }
            r7.b((java.lang.Object) r1)     // Catch:{ Exception -> 0x01b6 }
            r1 = 1
        L_0x0152:
            java.lang.String r7 = "ui"
            com.tencent.wxop.stat.au r3 = com.tencent.wxop.stat.au.a((android.content.Context) r6)     // Catch:{ Exception -> 0x01b6 }
            com.tencent.wxop.stat.common.a r3 = r3.b((android.content.Context) r6)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r3 = r3.b()     // Catch:{ Exception -> 0x01b6 }
            boolean r7 = a((org.json.JSONObject) r0, (java.lang.String) r7, (java.lang.String) r3)     // Catch:{ Exception -> 0x01b6 }
            if (r7 == 0) goto L_0x0186
            com.tencent.wxop.stat.common.StatLogger r7 = q     // Catch:{ Exception -> 0x01b6 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r3 = "match imei:"
            r1.<init>(r3)     // Catch:{ Exception -> 0x01b6 }
            com.tencent.wxop.stat.au r3 = com.tencent.wxop.stat.au.a((android.content.Context) r6)     // Catch:{ Exception -> 0x01b6 }
            com.tencent.wxop.stat.common.a r3 = r3.b((android.content.Context) r6)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r3 = r3.b()     // Catch:{ Exception -> 0x01b6 }
            r1.append(r3)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x01b6 }
            r7.b((java.lang.Object) r1)     // Catch:{ Exception -> 0x01b6 }
            r1 = 1
        L_0x0186:
            java.lang.String r7 = "mid"
            java.lang.String r3 = g((android.content.Context) r6)     // Catch:{ Exception -> 0x01b6 }
            boolean r7 = a((org.json.JSONObject) r0, (java.lang.String) r7, (java.lang.String) r3)     // Catch:{ Exception -> 0x01b6 }
            if (r7 == 0) goto L_0x01aa
            com.tencent.wxop.stat.common.StatLogger r7 = q     // Catch:{ Exception -> 0x01b6 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r1 = "match mid:"
            r0.<init>(r1)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r6 = g((android.content.Context) r6)     // Catch:{ Exception -> 0x01b6 }
            r0.append(r6)     // Catch:{ Exception -> 0x01b6 }
            java.lang.String r6 = r0.toString()     // Catch:{ Exception -> 0x01b6 }
            r7.b((java.lang.Object) r6)     // Catch:{ Exception -> 0x01b6 }
            r1 = 1
        L_0x01aa:
            if (r1 == 0) goto L_0x01b5
            java.lang.String r6 = "2.0.3"
            long r6 = com.tencent.wxop.stat.common.k.b((java.lang.String) r6)     // Catch:{ Exception -> 0x01b6 }
            a((long) r6)     // Catch:{ Exception -> 0x01b6 }
        L_0x01b5:
            return
        L_0x01b6:
            r6 = move-exception
            com.tencent.wxop.stat.common.StatLogger r7 = q
            r7.b((java.lang.Throwable) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.StatConfig.b(android.content.Context, org.json.JSONObject):void");
    }

    public static void b(String str) {
        StatLogger statLogger;
        String str2;
        if (str == null) {
            statLogger = q;
            str2 = "appkey in StatConfig.setAppKey() is null";
        } else if (str.length() > 256) {
            statLogger = q;
            str2 = "The length of appkey cann't exceed 256 bytes.";
        } else {
            C = str;
            return;
        }
        statLogger.g(str2);
    }

    static void b(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.length() != 0) {
            try {
                b(i.a(), jSONObject);
                String string = jSONObject.getString(c);
                if (b()) {
                    StatLogger statLogger = q;
                    statLogger.j("hibernateVer:" + string + ", current version:2.0.3");
                }
                long b2 = k.b(string);
                if (k.b(StatConstants.f9313a) <= b2) {
                    a(b2);
                }
            } catch (JSONException unused) {
                q.j("__HIBERNATE__ not found.");
            }
        }
    }

    public static void b(boolean z2) {
        t = z2;
        if (!z2) {
            q.e("!!!!!!MTA StatService has been disabled!!!!!!");
        }
    }

    public static boolean b() {
        return s;
    }

    public static synchronized String c(Context context) {
        synchronized (StatConfig.class) {
            if (D != null) {
                String str = D;
                return str;
            }
            String a2 = p.a(context, E, "");
            D = a2;
            if (a2 == null || D.trim().length() == 0) {
                D = k.g(context);
            }
            if (D == null || D.trim().length() == 0) {
                q.f("installChannel can not be null or empty, please read Developer's Guide first!");
            }
            String str2 = D;
            return str2;
        }
    }

    public static void c(int i2) {
        if (!a(i2, 2, 1000)) {
            q.g("setMaxBatchReportCount can not exceed the range of [2,1000].");
        } else {
            z = i2;
        }
    }

    public static void c(Context context, String str) {
        if (str.length() > 128) {
            q.g("the length of installChannel can not exceed the range of 128 bytes.");
            return;
        }
        D = str;
        p.b(context, E, str);
    }

    public static void c(String str) {
        if (str.length() > 128) {
            q.g("the length of installChannel can not exceed the range of 128 bytes.");
        } else {
            D = str;
        }
    }

    public static void c(boolean z2) {
        j = z2;
    }

    public static boolean c() {
        return t;
    }

    public static int d() {
        return u;
    }

    public static String d(Context context) {
        return p.a(context, "mta.acc.qq", f);
    }

    public static void d(int i2) {
        if (!a(i2, 1, 1000)) {
            q.g("setMaxSendRetryCount can not exceed the range of [1,1000].");
        } else {
            x = i2;
        }
    }

    public static void d(Context context, String str) {
        p.b(context, "mta.acc.qq", str);
        f = str;
    }

    public static void d(String str) {
        if (str == null || str.length() == 0) {
            q.g("statReportUrl cannot be null or empty.");
            return;
        }
        J = str;
        try {
            l = new URI(J).getHost();
        } catch (Exception e2) {
            q.f(e2);
        }
        if (b()) {
            StatLogger statLogger = q;
            statLogger.b((Object) "url:" + J + ", domain:" + l);
        }
    }

    public static void d(boolean z2) {
        k = z2;
    }

    public static int e() {
        return y;
    }

    public static String e(Context context) {
        if (context == null) {
            q.g("Context for getCustomUid is null.");
            return null;
        }
        if (R == null) {
            R = p.a(context, "MTA_CUSTOM_UID", "");
        }
        return R;
    }

    public static void e(int i2) {
        if (i2 > 0) {
            A = i2;
        }
    }

    public static void e(Context context, String str) {
        if (context == null) {
            q.g("Context for setCustomUid is null.");
            return;
        }
        p.b(context, "MTA_CUSTOM_UID", str);
        R = str;
    }

    public static void e(boolean z2) {
        Q = z2;
    }

    private static boolean e(String str) {
        if (str == null) {
            return false;
        }
        if (C == null) {
            C = str;
            return true;
        } else if (C.contains(str)) {
            return false;
        } else {
            C += "|" + str;
            return true;
        }
    }

    public static int f() {
        return z;
    }

    public static String f(Context context) {
        return g(context);
    }

    public static void f(int i2) {
        if (!a(i2, 0, 500000)) {
            q.g("setMaxStoreEventCount can not exceed the range of [0, 500000].");
        } else {
            v = i2;
        }
    }

    public static void f(boolean z2) {
        S = z2;
    }

    public static int g() {
        return x;
    }

    public static String g(Context context) {
        return context != null ? g.a(context).a().a() : "0";
    }

    public static void g(int i2) {
        if (!a(i2, 1, 10080)) {
            q.g("setSendPeriodMinutes can not exceed the range of [1, 7*24*60] minutes.");
        } else {
            F = i2;
        }
    }

    public static void g(boolean z2) {
        m = z2;
    }

    public static int h() {
        return A;
    }

    public static void h(int i2) {
        if (!a(i2, 1, 4096)) {
            q.g("setMaxParallelTimmingEvents can not exceed the range of [1, 4096].");
        } else {
            G = i2;
        }
    }

    static int i() {
        return w;
    }

    public static void i(int i2) {
        if (i2 < 0) {
            q.g("maxSessionStatReportCount cannot be less than 0.");
        } else {
            K = i2;
        }
    }

    public static int j() {
        return v;
    }

    static synchronized void j(int i2) {
        synchronized (StatConfig.class) {
            L = i2;
        }
    }

    public static int k() {
        return h;
    }

    public static void k(int i2) {
        if (i2 <= 0) {
            q.h("maxDaySessionNumbers must be greater than 0.");
        } else {
            M = i2;
        }
    }

    public static long l() {
        return i;
    }

    static void l(int i2) {
        if (i2 >= 0) {
            N = i2;
        }
    }

    public static int m() {
        return F;
    }

    public static void m(int i2) {
        if (i2 <= 0) {
            q.g("maxReportEventLength on setMaxReportEventLength() must greater than 0.");
        } else {
            P = i2;
        }
    }

    public static int n() {
        return G;
    }

    public static void n(int i2) {
        if (i2 >= 0) {
            n = i2;
        }
    }

    public static void o(int i2) {
        if (i2 > 0) {
            p = i2;
        }
    }

    public static boolean o() {
        return j;
    }

    public static boolean p() {
        return k;
    }

    public static String q() {
        return J;
    }

    public static String r() {
        return l;
    }

    public static int s() {
        return K;
    }

    public static int t() {
        return L;
    }

    public static int u() {
        return M;
    }

    static void v() {
        N++;
    }

    static int w() {
        return N;
    }

    public static int x() {
        return P;
    }

    public static boolean y() {
        return Q;
    }

    public static boolean z() {
        return S;
    }
}
