package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.facebook.appevents.UserDataStore;
import com.xiaomi.stat.a.l;
import com.xiaomi.stat.ab;
import com.xiaomi.stat.d;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public final class er {
    private static long A = 0;
    private static ArrayList<String> B = new ArrayList<>();
    private static boolean C = false;
    private static int D = -1;
    private static long E = 0;
    private static ArrayList<String> F = new ArrayList<>();
    private static boolean G = false;
    private static int H = 3000;
    private static int I = 3000;
    private static boolean J = true;
    private static long K = 300000;
    private static List<ev> L = new ArrayList();
    private static boolean M = false;
    private static long N = 0;
    private static int O = 0;
    private static int P = 0;
    private static List<String> Q = new ArrayList();
    private static boolean R = true;
    private static int S = 80;
    private static boolean T = false;
    private static boolean U = true;
    private static boolean V = false;
    private static boolean W = false;
    private static boolean X = true;
    private static boolean Y = false;
    private static int Z = -1;

    /* renamed from: a  reason: collision with root package name */
    public static boolean f6594a = true;
    private static boolean aa = true;
    private static long ab = -1;
    private static boolean ac = true;
    private static int ad = 1;
    private static long ae = 0;
    static boolean b = false;
    static boolean c = false;
    static int d = 3600000;
    static long e = 0;
    static long f = 0;
    static boolean g = false;
    static boolean h = true;
    private static boolean i = false;
    private static boolean j = false;
    private static long k = 0;
    private static long l = 0;
    private static long m = 5000;
    private static boolean n = false;
    private static int o = 0;
    private static boolean p = false;
    private static int q = 0;
    private static boolean r = false;
    private static boolean s = true;
    private static int t = 1000;
    private static int u = 200;
    private static boolean v = false;
    private static int w = 20;
    private static boolean x = true;
    private static boolean y = true;
    private static int z = -1;

    static class a {

        /* renamed from: a  reason: collision with root package name */
        boolean f6595a = false;
        String b = "0";
        boolean c = false;
        int d = 5;

        a() {
        }
    }

    public static boolean A() {
        return aa;
    }

    public static long B() {
        return ab;
    }

    public static boolean C() {
        return ac && ad > 0;
    }

    public static int D() {
        return ad;
    }

    public static long E() {
        return ae;
    }

    private static a a(JSONObject jSONObject, String str) {
        a aVar;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                if (jSONObject2 != null) {
                    aVar = new a();
                    try {
                        aVar.f6595a = v.a(jSONObject2.optString("b"), false);
                        aVar.b = jSONObject2.optString("t");
                        aVar.c = v.a(jSONObject2.optString("st"), false);
                        aVar.d = jSONObject2.optInt("i", 0);
                        return aVar;
                    } catch (Throwable th) {
                        th = th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                aVar = null;
                es.a(th, "AuthUtil", "getLocateObj");
                return aVar;
            }
        }
        return null;
    }

    public static boolean a() {
        return n;
    }

    public static boolean a(long j2) {
        long c2 = fa.c();
        return j && c2 - l <= k && c2 - j2 >= m;
    }

    public static boolean a(Context context) {
        boolean z2;
        x = true;
        try {
            i = ez.b(context, ab.a.b, "oda", false);
            z2 = a(context, v.a(context, es.b(), es.c()));
        } catch (Throwable th) {
            es.a(th, "AuthUtil", "getConfig");
            z2 = false;
        }
        f = fa.c();
        e = fa.c();
        return z2;
    }

    public static boolean a(Context context, long j2) {
        if (!G) {
            return false;
        }
        long b2 = fa.b();
        if (b2 - j2 < ((long) H)) {
            return false;
        }
        if (I == -1) {
            return true;
        }
        if (!fa.c(b2, ez.b(context, ab.a.b, "ngpsTime", 0))) {
            try {
                SharedPreferences.Editor edit = context.getSharedPreferences(ab.a.b, 0).edit();
                edit.putLong("ngpsTime", b2);
                edit.putInt("ngpsCount", 0);
                ez.a(edit);
            } catch (Throwable th) {
                es.a(th, "AuthUtil", "resetPrefsNGPS");
            }
            ez.a(context, ab.a.b, "ngpsCount", 1);
            return true;
        }
        int b3 = ez.b(context, ab.a.b, "ngpsCount", 0);
        if (b3 >= I) {
            return false;
        }
        ez.a(context, ab.a.b, "ngpsCount", b3 + 1);
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:209:0x0396 A[SYNTHETIC, Splitter:B:209:0x0396] */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x042b A[Catch:{ Throwable -> 0x044a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(android.content.Context r16, com.loc.v.a r17) {
        /*
            r1 = r16
            r2 = r17
            r3 = 0
            org.json.JSONObject r4 = r2.g     // Catch:{ Throwable -> 0x00bc }
            if (r4 == 0) goto L_0x00c4
            java.lang.String r0 = "at"
            r5 = 123(0x7b, float:1.72E-43)
            int r0 = r4.optInt(r0, r5)     // Catch:{ Throwable -> 0x0018 }
            int r0 = r0 * 60
            int r0 = r0 * 1000
            d = r0     // Catch:{ Throwable -> 0x0018 }
            goto L_0x0020
        L_0x0018:
            r0 = move-exception
            java.lang.String r5 = "AuthUtil"
            java.lang.String r6 = "requestSdkAuthInterval"
            com.loc.es.a(r0, r5, r6)     // Catch:{ Throwable -> 0x00bc }
        L_0x0020:
            java.lang.String r0 = "ila"
            java.lang.String r0 = r4.optString(r0)     // Catch:{ Throwable -> 0x002f }
            boolean r5 = T     // Catch:{ Throwable -> 0x002f }
            boolean r0 = com.loc.v.a((java.lang.String) r0, (boolean) r5)     // Catch:{ Throwable -> 0x002f }
            T = r0     // Catch:{ Throwable -> 0x002f }
            goto L_0x0037
        L_0x002f:
            r0 = move-exception
            java.lang.String r5 = "AuthUtil"
            java.lang.String r6 = "loadConfigData_indoor"
            com.loc.es.a(r0, r5, r6)     // Catch:{ Throwable -> 0x00bc }
        L_0x0037:
            if (r1 == 0) goto L_0x005c
            if (r4 != 0) goto L_0x003c
            goto L_0x005c
        L_0x003c:
            java.lang.String r0 = "re"
            java.lang.String r0 = r4.optString(r0)     // Catch:{ Throwable -> 0x0054 }
            boolean r5 = h     // Catch:{ Throwable -> 0x0054 }
            boolean r0 = com.loc.v.a((java.lang.String) r0, (boolean) r5)     // Catch:{ Throwable -> 0x0054 }
            h = r0     // Catch:{ Throwable -> 0x0054 }
            java.lang.String r0 = "pref"
            java.lang.String r5 = "fr"
            boolean r6 = h     // Catch:{ Throwable -> 0x0054 }
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r5, (boolean) r6)     // Catch:{ Throwable -> 0x0054 }
            goto L_0x005c
        L_0x0054:
            r0 = move-exception
            java.lang.String r5 = "AuthUtil"
            java.lang.String r6 = "checkReLocationAble"
            com.loc.es.a(r0, r5, r6)     // Catch:{ Throwable -> 0x00bc }
        L_0x005c:
            java.lang.String r0 = "nla"
            java.lang.String r0 = r4.optString(r0)     // Catch:{ Throwable -> 0x006a }
            boolean r5 = U     // Catch:{ Throwable -> 0x006a }
            boolean r0 = com.loc.v.a((java.lang.String) r0, (boolean) r5)     // Catch:{ Throwable -> 0x006a }
            U = r0     // Catch:{ Throwable -> 0x006a }
        L_0x006a:
            java.lang.String r0 = "oda"
            java.lang.String r0 = r4.optString(r0)     // Catch:{ Throwable -> 0x0081 }
            boolean r5 = i     // Catch:{ Throwable -> 0x0081 }
            boolean r0 = com.loc.v.a((java.lang.String) r0, (boolean) r5)     // Catch:{ Throwable -> 0x0081 }
            i = r0     // Catch:{ Throwable -> 0x0081 }
            java.lang.String r0 = "pref"
            java.lang.String r5 = "oda"
            boolean r6 = i     // Catch:{ Throwable -> 0x0081 }
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r5, (boolean) r6)     // Catch:{ Throwable -> 0x0081 }
        L_0x0081:
            java.lang.String r0 = "asw"
            java.lang.String r0 = r4.optString(r0)     // Catch:{ Throwable -> 0x0098 }
            boolean r5 = aa     // Catch:{ Throwable -> 0x0098 }
            boolean r0 = com.loc.v.a((java.lang.String) r0, (boolean) r5)     // Catch:{ Throwable -> 0x0098 }
            aa = r0     // Catch:{ Throwable -> 0x0098 }
            java.lang.String r0 = "pref"
            java.lang.String r5 = "asw"
            boolean r6 = aa     // Catch:{ Throwable -> 0x0098 }
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r5, (boolean) r6)     // Catch:{ Throwable -> 0x0098 }
        L_0x0098:
            java.lang.String r0 = "mlpl"
            org.json.JSONArray r0 = r4.optJSONArray(r0)     // Catch:{ Throwable -> 0x00c4 }
            if (r0 == 0) goto L_0x00c4
            int r4 = r0.length()     // Catch:{ Throwable -> 0x00c4 }
            if (r4 <= 0) goto L_0x00c4
            r4 = 0
        L_0x00a7:
            int r5 = r0.length()     // Catch:{ Throwable -> 0x00c4 }
            if (r4 >= r5) goto L_0x00c4
            java.lang.String r5 = r0.getString(r4)     // Catch:{ Throwable -> 0x00c4 }
            boolean r5 = com.loc.fa.c((android.content.Context) r1, (java.lang.String) r5)     // Catch:{ Throwable -> 0x00c4 }
            V = r5     // Catch:{ Throwable -> 0x00c4 }
            if (r5 != 0) goto L_0x00c4
            int r4 = r4 + 1
            goto L_0x00a7
        L_0x00bc:
            r0 = move-exception
            java.lang.String r4 = "AuthUtil"
            java.lang.String r5 = "loadConfigAbleStatus"
            com.loc.es.a(r0, r4, r5)     // Catch:{ Throwable -> 0x05b6 }
        L_0x00c4:
            r4 = 0
            r6 = -1
            r7 = 1
            org.json.JSONObject r0 = r2.h     // Catch:{ Throwable -> 0x0148 }
            if (r0 == 0) goto L_0x0150
            java.lang.String r8 = "callamapflag"
            java.lang.String r8 = r0.optString(r8)     // Catch:{ Throwable -> 0x0148 }
            boolean r9 = y     // Catch:{ Throwable -> 0x0148 }
            boolean r8 = com.loc.v.a((java.lang.String) r8, (boolean) r9)     // Catch:{ Throwable -> 0x0148 }
            y = r8     // Catch:{ Throwable -> 0x0148 }
            java.lang.String r8 = "co"
            java.lang.String r8 = r0.optString(r8)     // Catch:{ Throwable -> 0x0148 }
            boolean r8 = com.loc.v.a((java.lang.String) r8, (boolean) r3)     // Catch:{ Throwable -> 0x0148 }
            if (r8 == 0) goto L_0x00ec
            boolean r8 = y     // Catch:{ Throwable -> 0x0148 }
            if (r8 == 0) goto L_0x00ec
            r8 = 1
            goto L_0x00ed
        L_0x00ec:
            r8 = 0
        L_0x00ed:
            b = r8     // Catch:{ Throwable -> 0x0148 }
            boolean r8 = y     // Catch:{ Throwable -> 0x0148 }
            if (r8 == 0) goto L_0x0150
            java.lang.String r8 = "count"
            int r9 = z     // Catch:{ Throwable -> 0x0148 }
            int r8 = r0.optInt(r8, r9)     // Catch:{ Throwable -> 0x0148 }
            z = r8     // Catch:{ Throwable -> 0x0148 }
            java.lang.String r8 = "sysTime"
            long r9 = A     // Catch:{ Throwable -> 0x0148 }
            long r8 = r0.optLong(r8, r9)     // Catch:{ Throwable -> 0x0148 }
            A = r8     // Catch:{ Throwable -> 0x0148 }
            java.lang.String r8 = "sn"
            org.json.JSONArray r0 = r0.optJSONArray(r8)     // Catch:{ Throwable -> 0x0148 }
            if (r0 == 0) goto L_0x012a
            int r8 = r0.length()     // Catch:{ Throwable -> 0x0148 }
            if (r8 <= 0) goto L_0x012a
            r8 = 0
        L_0x0118:
            int r9 = r0.length()     // Catch:{ Throwable -> 0x0148 }
            if (r8 >= r9) goto L_0x012a
            java.util.ArrayList<java.lang.String> r9 = B     // Catch:{ Throwable -> 0x0148 }
            java.lang.String r10 = r0.getString(r8)     // Catch:{ Throwable -> 0x0148 }
            r9.add(r10)     // Catch:{ Throwable -> 0x0148 }
            int r8 = r8 + 1
            goto L_0x0118
        L_0x012a:
            int r0 = z     // Catch:{ Throwable -> 0x0148 }
            if (r0 == r6) goto L_0x0150
            long r8 = A     // Catch:{ Throwable -> 0x0148 }
            int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0150
            java.lang.String r0 = "pref"
            java.lang.String r8 = "nowtime"
            long r8 = com.loc.ez.b((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r8, (long) r4)     // Catch:{ Throwable -> 0x0148 }
            long r10 = A     // Catch:{ Throwable -> 0x0148 }
            boolean r0 = com.loc.fa.b((long) r10, (long) r8)     // Catch:{ Throwable -> 0x0148 }
            if (r0 != 0) goto L_0x0150
            h(r16)     // Catch:{ Throwable -> 0x0148 }
            goto L_0x0150
        L_0x0148:
            r0 = move-exception
            java.lang.String r8 = "AuthUtil"
            java.lang.String r9 = "loadConfigDataCallAMapSer"
            com.loc.es.a(r0, r8, r9)     // Catch:{ Throwable -> 0x05b6 }
        L_0x0150:
            org.json.JSONObject r0 = r2.k     // Catch:{ Throwable -> 0x01b9 }
            if (r0 == 0) goto L_0x01c1
            java.lang.String r8 = "amappushflag"
            java.lang.String r8 = r0.optString(r8)     // Catch:{ Throwable -> 0x01b9 }
            boolean r9 = C     // Catch:{ Throwable -> 0x01b9 }
            boolean r8 = com.loc.v.a((java.lang.String) r8, (boolean) r9)     // Catch:{ Throwable -> 0x01b9 }
            C = r8     // Catch:{ Throwable -> 0x01b9 }
            if (r8 == 0) goto L_0x01c1
            java.lang.String r8 = "count"
            int r9 = D     // Catch:{ Throwable -> 0x01b9 }
            int r8 = r0.optInt(r8, r9)     // Catch:{ Throwable -> 0x01b9 }
            D = r8     // Catch:{ Throwable -> 0x01b9 }
            java.lang.String r8 = "sysTime"
            long r9 = E     // Catch:{ Throwable -> 0x01b9 }
            long r8 = r0.optLong(r8, r9)     // Catch:{ Throwable -> 0x01b9 }
            E = r8     // Catch:{ Throwable -> 0x01b9 }
            java.lang.String r8 = "sn"
            org.json.JSONArray r0 = r0.optJSONArray(r8)     // Catch:{ Throwable -> 0x01b9 }
            if (r0 == 0) goto L_0x019b
            int r8 = r0.length()     // Catch:{ Throwable -> 0x01b9 }
            if (r8 <= 0) goto L_0x019b
            r8 = 0
        L_0x0189:
            int r9 = r0.length()     // Catch:{ Throwable -> 0x01b9 }
            if (r8 >= r9) goto L_0x019b
            java.util.ArrayList<java.lang.String> r9 = F     // Catch:{ Throwable -> 0x01b9 }
            java.lang.String r10 = r0.getString(r8)     // Catch:{ Throwable -> 0x01b9 }
            r9.add(r10)     // Catch:{ Throwable -> 0x01b9 }
            int r8 = r8 + 1
            goto L_0x0189
        L_0x019b:
            int r0 = D     // Catch:{ Throwable -> 0x01b9 }
            if (r0 == r6) goto L_0x01c1
            long r8 = E     // Catch:{ Throwable -> 0x01b9 }
            int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x01c1
            java.lang.String r0 = "pref"
            java.lang.String r8 = "pushSerTime"
            long r4 = com.loc.ez.b((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r8, (long) r4)     // Catch:{ Throwable -> 0x01b9 }
            long r8 = E     // Catch:{ Throwable -> 0x01b9 }
            boolean r0 = com.loc.fa.b((long) r8, (long) r4)     // Catch:{ Throwable -> 0x01b9 }
            if (r0 != 0) goto L_0x01c1
            i(r16)     // Catch:{ Throwable -> 0x01b9 }
            goto L_0x01c1
        L_0x01b9:
            r0 = move-exception
            java.lang.String r4 = "AuthUtil"
            java.lang.String r5 = "loadConfigDataCallAMapPush"
            com.loc.es.a(r0, r4, r5)     // Catch:{ Throwable -> 0x05b6 }
        L_0x01c1:
            r4 = 30
            com.loc.v$a$a r0 = r2.x     // Catch:{ Throwable -> 0x023c }
            if (r0 == 0) goto L_0x0244
            boolean r5 = r0.f6641a     // Catch:{ Throwable -> 0x023c }
            s = r5     // Catch:{ Throwable -> 0x023c }
            java.lang.String r5 = "pref"
            java.lang.String r8 = "exception"
            boolean r9 = s     // Catch:{ Throwable -> 0x023c }
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r5, (java.lang.String) r8, (boolean) r9)     // Catch:{ Throwable -> 0x023c }
            org.json.JSONObject r0 = r0.c     // Catch:{ Throwable -> 0x023c }
            java.lang.String r5 = "fn"
            int r8 = t     // Catch:{ Throwable -> 0x023c }
            int r5 = r0.optInt(r5, r8)     // Catch:{ Throwable -> 0x023c }
            t = r5     // Catch:{ Throwable -> 0x023c }
            java.lang.String r5 = "mpn"
            int r8 = u     // Catch:{ Throwable -> 0x023c }
            int r5 = r0.optInt(r5, r8)     // Catch:{ Throwable -> 0x023c }
            u = r5     // Catch:{ Throwable -> 0x023c }
            r8 = 500(0x1f4, float:7.0E-43)
            if (r5 <= r8) goto L_0x01f0
            u = r8     // Catch:{ Throwable -> 0x023c }
        L_0x01f0:
            int r5 = u     // Catch:{ Throwable -> 0x023c }
            if (r5 >= r4) goto L_0x01f6
            u = r4     // Catch:{ Throwable -> 0x023c }
        L_0x01f6:
            java.lang.String r5 = "igu"
            java.lang.String r5 = r0.optString(r5)     // Catch:{ Throwable -> 0x023c }
            boolean r8 = v     // Catch:{ Throwable -> 0x023c }
            boolean r5 = com.loc.v.a((java.lang.String) r5, (boolean) r8)     // Catch:{ Throwable -> 0x023c }
            v = r5     // Catch:{ Throwable -> 0x023c }
            java.lang.String r5 = "ms"
            int r8 = w     // Catch:{ Throwable -> 0x023c }
            int r0 = r0.optInt(r5, r8)     // Catch:{ Throwable -> 0x023c }
            w = r0     // Catch:{ Throwable -> 0x023c }
            int r0 = t     // Catch:{ Throwable -> 0x023c }
            boolean r5 = v     // Catch:{ Throwable -> 0x023c }
            int r8 = w     // Catch:{ Throwable -> 0x023c }
            com.loc.bp.a(r0, r5, r8)     // Catch:{ Throwable -> 0x023c }
            java.lang.String r0 = "pref"
            java.lang.String r5 = "fn"
            int r8 = t     // Catch:{ Throwable -> 0x023c }
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r5, (int) r8)     // Catch:{ Throwable -> 0x023c }
            java.lang.String r0 = "pref"
            java.lang.String r5 = "mpn"
            int r8 = u     // Catch:{ Throwable -> 0x023c }
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r5, (int) r8)     // Catch:{ Throwable -> 0x023c }
            java.lang.String r0 = "pref"
            java.lang.String r5 = "igu"
            boolean r8 = v     // Catch:{ Throwable -> 0x023c }
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r5, (boolean) r8)     // Catch:{ Throwable -> 0x023c }
            java.lang.String r0 = "pref"
            java.lang.String r5 = "ms"
            int r8 = w     // Catch:{ Throwable -> 0x023c }
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r5, (int) r8)     // Catch:{ Throwable -> 0x023c }
            goto L_0x0244
        L_0x023c:
            r0 = move-exception
            java.lang.String r5 = "AuthUtil"
            java.lang.String r8 = "loadConfigDataUploadException"
            com.loc.es.a(r0, r5, r8)     // Catch:{ Throwable -> 0x05b6 }
        L_0x0244:
            r5 = 2
            org.json.JSONObject r8 = r2.m     // Catch:{ Throwable -> 0x02ca }
            if (r8 == 0) goto L_0x02d2
            java.lang.String r0 = "able"
            java.lang.String r0 = r8.optString(r0)     // Catch:{ Throwable -> 0x02ca }
            boolean r0 = com.loc.v.a((java.lang.String) r0, (boolean) r3)     // Catch:{ Throwable -> 0x02ca }
            if (r0 == 0) goto L_0x02d2
            java.lang.String r0 = "fs"
            com.loc.er$a r0 = a((org.json.JSONObject) r8, (java.lang.String) r0)     // Catch:{ Throwable -> 0x02ca }
            if (r0 == 0) goto L_0x0272
            boolean r9 = r0.c     // Catch:{ Throwable -> 0x02ca }
            n = r9     // Catch:{ Throwable -> 0x02ca }
            java.lang.String r0 = r0.b     // Catch:{ Throwable -> 0x026a }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Throwable -> 0x026a }
            o = r0     // Catch:{ Throwable -> 0x026a }
            goto L_0x0272
        L_0x026a:
            r0 = move-exception
            java.lang.String r9 = "AuthUtil"
            java.lang.String r10 = "loadconfig part2"
            com.loc.es.a(r0, r9, r10)     // Catch:{ Throwable -> 0x02ca }
        L_0x0272:
            java.lang.String r0 = "us"
            com.loc.er$a r0 = a((org.json.JSONObject) r8, (java.lang.String) r0)     // Catch:{ Throwable -> 0x02ca }
            if (r0 == 0) goto L_0x029a
            boolean r9 = r0.c     // Catch:{ Throwable -> 0x02ca }
            p = r9     // Catch:{ Throwable -> 0x02ca }
            boolean r9 = r0.f6595a     // Catch:{ Throwable -> 0x02ca }
            r = r9     // Catch:{ Throwable -> 0x02ca }
            java.lang.String r0 = r0.b     // Catch:{ Throwable -> 0x028c }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Throwable -> 0x028c }
            q = r0     // Catch:{ Throwable -> 0x028c }
            goto L_0x0294
        L_0x028c:
            r0 = move-exception
            java.lang.String r9 = "AuthUtil"
            java.lang.String r10 = "loadconfig part1"
            com.loc.es.a(r0, r9, r10)     // Catch:{ Throwable -> 0x02ca }
        L_0x0294:
            int r0 = q     // Catch:{ Throwable -> 0x02ca }
            if (r0 >= r5) goto L_0x029a
            p = r3     // Catch:{ Throwable -> 0x02ca }
        L_0x029a:
            java.lang.String r0 = "rs"
            com.loc.er$a r0 = a((org.json.JSONObject) r8, (java.lang.String) r0)     // Catch:{ Throwable -> 0x02ca }
            if (r0 == 0) goto L_0x02d2
            boolean r8 = r0.c     // Catch:{ Throwable -> 0x02ca }
            j = r8     // Catch:{ Throwable -> 0x02ca }
            if (r8 == 0) goto L_0x02b5
            long r8 = com.loc.fa.c()     // Catch:{ Throwable -> 0x02ca }
            l = r8     // Catch:{ Throwable -> 0x02ca }
            int r8 = r0.d     // Catch:{ Throwable -> 0x02ca }
            int r8 = r8 * 1000
            long r8 = (long) r8     // Catch:{ Throwable -> 0x02ca }
            m = r8     // Catch:{ Throwable -> 0x02ca }
        L_0x02b5:
            java.lang.String r0 = r0.b     // Catch:{ Throwable -> 0x02c1 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Throwable -> 0x02c1 }
            int r0 = r0 * 1000
            long r8 = (long) r0     // Catch:{ Throwable -> 0x02c1 }
            k = r8     // Catch:{ Throwable -> 0x02c1 }
            goto L_0x02d2
        L_0x02c1:
            r0 = move-exception
            java.lang.String r8 = "AuthUtil"
            java.lang.String r9 = "loadconfig part"
            com.loc.es.a(r0, r8, r9)     // Catch:{ Throwable -> 0x02ca }
            goto L_0x02d2
        L_0x02ca:
            r0 = move-exception
            java.lang.String r8 = "AuthUtil"
            java.lang.String r9 = "loadConfigDataLocate"
            com.loc.es.a(r0, r8, r9)     // Catch:{ Throwable -> 0x05b6 }
        L_0x02d2:
            org.json.JSONObject r0 = r2.o     // Catch:{ Throwable -> 0x0302 }
            if (r0 == 0) goto L_0x030a
            java.lang.String r8 = "able"
            java.lang.String r8 = r0.optString(r8)     // Catch:{ Throwable -> 0x0302 }
            boolean r9 = G     // Catch:{ Throwable -> 0x0302 }
            boolean r8 = com.loc.v.a((java.lang.String) r8, (boolean) r9)     // Catch:{ Throwable -> 0x0302 }
            G = r8     // Catch:{ Throwable -> 0x0302 }
            if (r8 == 0) goto L_0x030a
            java.lang.String r8 = "c"
            int r8 = r0.optInt(r8, r3)     // Catch:{ Throwable -> 0x0302 }
            if (r8 != 0) goto L_0x02f3
            r8 = 3000(0xbb8, float:4.204E-42)
            H = r8     // Catch:{ Throwable -> 0x0302 }
            goto L_0x02f7
        L_0x02f3:
            int r8 = r8 * 1000
            H = r8     // Catch:{ Throwable -> 0x0302 }
        L_0x02f7:
            java.lang.String r8 = "t"
            int r0 = r0.getInt(r8)     // Catch:{ Throwable -> 0x0302 }
            int r0 = r0 / r5
            I = r0     // Catch:{ Throwable -> 0x0302 }
            goto L_0x030a
        L_0x0302:
            r0 = move-exception
            java.lang.String r5 = "AuthUtil"
            java.lang.String r8 = "loadConfigDataNgps"
            com.loc.es.a(r0, r5, r8)     // Catch:{ Throwable -> 0x05b6 }
        L_0x030a:
            org.json.JSONObject r0 = r2.p     // Catch:{ Throwable -> 0x033e }
            if (r0 == 0) goto L_0x0346
            java.lang.String r5 = "able"
            java.lang.String r5 = r0.optString(r5)     // Catch:{ Throwable -> 0x033e }
            boolean r8 = J     // Catch:{ Throwable -> 0x033e }
            boolean r5 = com.loc.v.a((java.lang.String) r5, (boolean) r8)     // Catch:{ Throwable -> 0x033e }
            J = r5     // Catch:{ Throwable -> 0x033e }
            if (r5 == 0) goto L_0x032b
            java.lang.String r5 = "c"
            r8 = 300(0x12c, float:4.2E-43)
            int r0 = r0.optInt(r5, r8)     // Catch:{ Throwable -> 0x033e }
            int r0 = r0 * 1000
            long r8 = (long) r0     // Catch:{ Throwable -> 0x033e }
            K = r8     // Catch:{ Throwable -> 0x033e }
        L_0x032b:
            java.lang.String r0 = "pref"
            java.lang.String r5 = "ca"
            boolean r8 = J     // Catch:{ Throwable -> 0x033e }
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r5, (boolean) r8)     // Catch:{ Throwable -> 0x033e }
            java.lang.String r0 = "pref"
            java.lang.String r5 = "ct"
            long r8 = K     // Catch:{ Throwable -> 0x033e }
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r5, (long) r8)     // Catch:{ Throwable -> 0x033e }
            goto L_0x0346
        L_0x033e:
            r0 = move-exception
            java.lang.String r5 = "AuthUtil"
            java.lang.String r8 = "loadConfigDataCacheAble"
            com.loc.es.a(r0, r5, r8)     // Catch:{ Throwable -> 0x05b6 }
        L_0x0346:
            org.json.JSONObject r0 = r2.j     // Catch:{ Throwable -> 0x044a }
            if (r0 == 0) goto L_0x0452
            java.lang.String r5 = "able"
            java.lang.String r5 = r0.optString(r5)     // Catch:{ Throwable -> 0x044a }
            boolean r8 = M     // Catch:{ Throwable -> 0x044a }
            boolean r5 = com.loc.v.a((java.lang.String) r5, (boolean) r8)     // Catch:{ Throwable -> 0x044a }
            M = r5     // Catch:{ Throwable -> 0x044a }
            java.lang.String r5 = "sysTime"
            long r8 = com.loc.fa.b()     // Catch:{ Throwable -> 0x044a }
            long r8 = r0.optLong(r5, r8)     // Catch:{ Throwable -> 0x044a }
            N = r8     // Catch:{ Throwable -> 0x044a }
            java.lang.String r5 = "n"
            int r5 = r0.optInt(r5, r7)     // Catch:{ Throwable -> 0x044a }
            O = r5     // Catch:{ Throwable -> 0x044a }
            java.lang.String r5 = "nh"
            int r5 = r0.optInt(r5, r7)     // Catch:{ Throwable -> 0x044a }
            P = r5     // Catch:{ Throwable -> 0x044a }
            int r5 = O     // Catch:{ Throwable -> 0x044a }
            if (r5 == r6) goto L_0x0382
            int r5 = O     // Catch:{ Throwable -> 0x044a }
            int r8 = P     // Catch:{ Throwable -> 0x044a }
            if (r5 < r8) goto L_0x0380
            goto L_0x0382
        L_0x0380:
            r5 = 0
            goto L_0x0383
        L_0x0382:
            r5 = 1
        L_0x0383:
            boolean r8 = M     // Catch:{ Throwable -> 0x044a }
            if (r8 == 0) goto L_0x0452
            if (r5 == 0) goto L_0x0452
            java.lang.String r5 = "l"
            org.json.JSONArray r5 = r0.optJSONArray(r5)     // Catch:{ Throwable -> 0x044a }
            r8 = 0
        L_0x0390:
            int r9 = r5.length()     // Catch:{ Throwable -> 0x044a }
            if (r8 >= r9) goto L_0x0422
            org.json.JSONObject r9 = r5.optJSONObject(r8)     // Catch:{ Throwable -> 0x041b }
            com.loc.ev r10 = new com.loc.ev     // Catch:{ Throwable -> 0x041b }
            r10.<init>()     // Catch:{ Throwable -> 0x041b }
            java.lang.String r11 = "able"
            java.lang.String r12 = "false"
            java.lang.String r11 = r9.optString(r11, r12)     // Catch:{ Throwable -> 0x041b }
            boolean r11 = com.loc.v.a((java.lang.String) r11, (boolean) r3)     // Catch:{ Throwable -> 0x041b }
            r10.f6598a = r11     // Catch:{ Throwable -> 0x041b }
            if (r11 != 0) goto L_0x03b0
            goto L_0x041b
        L_0x03b0:
            java.lang.String r11 = "pn"
            java.lang.String r12 = ""
            java.lang.String r11 = r9.optString(r11, r12)     // Catch:{ Throwable -> 0x041b }
            r10.b = r11     // Catch:{ Throwable -> 0x041b }
            java.lang.String r11 = "cn"
            java.lang.String r12 = ""
            java.lang.String r11 = r9.optString(r11, r12)     // Catch:{ Throwable -> 0x041b }
            r10.c = r11     // Catch:{ Throwable -> 0x041b }
            java.lang.String r11 = "a"
            java.lang.String r12 = ""
            java.lang.String r11 = r9.optString(r11, r12)     // Catch:{ Throwable -> 0x041b }
            r10.e = r11     // Catch:{ Throwable -> 0x041b }
            java.lang.String r11 = "b"
            org.json.JSONArray r11 = r9.optJSONArray(r11)     // Catch:{ Throwable -> 0x041b }
            if (r11 == 0) goto L_0x0408
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ Throwable -> 0x041b }
            r12.<init>()     // Catch:{ Throwable -> 0x041b }
            r13 = 0
        L_0x03dc:
            int r14 = r11.length()     // Catch:{ Throwable -> 0x041b }
            if (r13 >= r14) goto L_0x0406
            org.json.JSONObject r14 = r11.optJSONObject(r13)     // Catch:{ Throwable -> 0x041b }
            java.util.HashMap r15 = new java.util.HashMap     // Catch:{ Throwable -> 0x041b }
            r7 = 16
            r15.<init>(r7)     // Catch:{ Throwable -> 0x041b }
            java.lang.String r7 = "k"
            java.lang.String r7 = r14.optString(r7)     // Catch:{ Throwable -> 0x0400 }
            java.lang.String r4 = "v"
            java.lang.String r4 = r14.optString(r4)     // Catch:{ Throwable -> 0x0400 }
            r15.put(r7, r4)     // Catch:{ Throwable -> 0x0400 }
            r12.add(r15)     // Catch:{ Throwable -> 0x0400 }
        L_0x0400:
            int r13 = r13 + 1
            r4 = 30
            r7 = 1
            goto L_0x03dc
        L_0x0406:
            r10.d = r12     // Catch:{ Throwable -> 0x041b }
        L_0x0408:
            java.lang.String r4 = "is"
            java.lang.String r7 = "false"
            java.lang.String r4 = r9.optString(r4, r7)     // Catch:{ Throwable -> 0x041b }
            boolean r4 = com.loc.v.a((java.lang.String) r4, (boolean) r3)     // Catch:{ Throwable -> 0x041b }
            r10.f = r4     // Catch:{ Throwable -> 0x041b }
            java.util.List<com.loc.ev> r4 = L     // Catch:{ Throwable -> 0x041b }
            r4.add(r10)     // Catch:{ Throwable -> 0x041b }
        L_0x041b:
            int r8 = r8 + 1
            r4 = 30
            r7 = 1
            goto L_0x0390
        L_0x0422:
            java.lang.String r4 = "sl"
            org.json.JSONArray r0 = r0.optJSONArray(r4)     // Catch:{ Throwable -> 0x044a }
            if (r0 == 0) goto L_0x0452
            r4 = 0
        L_0x042c:
            int r5 = r0.length()     // Catch:{ Throwable -> 0x044a }
            if (r4 >= r5) goto L_0x0452
            org.json.JSONObject r5 = r0.optJSONObject(r4)     // Catch:{ Throwable -> 0x044a }
            java.lang.String r7 = "pan"
            java.lang.String r5 = r5.optString(r7)     // Catch:{ Throwable -> 0x044a }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x044a }
            if (r7 != 0) goto L_0x0447
            java.util.List<java.lang.String> r7 = Q     // Catch:{ Throwable -> 0x044a }
            r7.add(r5)     // Catch:{ Throwable -> 0x044a }
        L_0x0447:
            int r4 = r4 + 1
            goto L_0x042c
        L_0x044a:
            r0 = move-exception
            java.lang.String r4 = "AuthUtil"
            java.lang.String r5 = "loadConfigData_otherServiceList"
            com.loc.es.a(r0, r4, r5)     // Catch:{ Throwable -> 0x05b6 }
        L_0x0452:
            org.json.JSONObject r0 = r2.i     // Catch:{ Throwable -> 0x0471 }
            if (r0 == 0) goto L_0x0479
            java.lang.String r4 = "able"
            java.lang.String r4 = r0.optString(r4)     // Catch:{ Throwable -> 0x0471 }
            boolean r5 = R     // Catch:{ Throwable -> 0x0471 }
            boolean r4 = com.loc.v.a((java.lang.String) r4, (boolean) r5)     // Catch:{ Throwable -> 0x0471 }
            R = r4     // Catch:{ Throwable -> 0x0471 }
            if (r4 == 0) goto L_0x0479
            java.lang.String r4 = "c"
            int r5 = S     // Catch:{ Throwable -> 0x0471 }
            int r0 = r0.optInt(r4, r5)     // Catch:{ Throwable -> 0x0471 }
            S = r0     // Catch:{ Throwable -> 0x0471 }
            goto L_0x0479
        L_0x0471:
            r0 = move-exception
            java.lang.String r4 = "AuthUtil"
            java.lang.String r5 = "loadConfigDataGpsGeoAble"
            com.loc.es.a(r0, r4, r5)     // Catch:{ Throwable -> 0x05b6 }
        L_0x0479:
            org.json.JSONObject r2 = r2.w     // Catch:{ Throwable -> 0x05b6 }
            if (r2 == 0) goto L_0x04e2
            java.lang.String r0 = "157"
            org.json.JSONObject r0 = r2.optJSONObject(r0)     // Catch:{ Throwable -> 0x04da }
            if (r0 == 0) goto L_0x04e2
            java.lang.String r4 = "able"
            java.lang.String r4 = r0.optString(r4)     // Catch:{ Throwable -> 0x04da }
            boolean r5 = W     // Catch:{ Throwable -> 0x04da }
            boolean r4 = com.loc.v.a((java.lang.String) r4, (boolean) r5)     // Catch:{ Throwable -> 0x04da }
            W = r4     // Catch:{ Throwable -> 0x04da }
            if (r4 == 0) goto L_0x04ba
            java.lang.String r4 = "cv"
            int r4 = r0.optInt(r4, r6)     // Catch:{ Throwable -> 0x04da }
            Z = r4     // Catch:{ Throwable -> 0x04da }
            java.lang.String r4 = "co"
            java.lang.String r4 = r0.optString(r4)     // Catch:{ Throwable -> 0x04da }
            boolean r5 = X     // Catch:{ Throwable -> 0x04da }
            boolean r4 = com.loc.v.a((java.lang.String) r4, (boolean) r5)     // Catch:{ Throwable -> 0x04da }
            X = r4     // Catch:{ Throwable -> 0x04da }
            java.lang.String r4 = "oo"
            java.lang.String r0 = r0.optString(r4)     // Catch:{ Throwable -> 0x04da }
            boolean r4 = Y     // Catch:{ Throwable -> 0x04da }
            boolean r0 = com.loc.v.a((java.lang.String) r0, (boolean) r4)     // Catch:{ Throwable -> 0x04da }
            Y = r0     // Catch:{ Throwable -> 0x04da }
            goto L_0x04be
        L_0x04ba:
            X = r3     // Catch:{ Throwable -> 0x04da }
            Y = r3     // Catch:{ Throwable -> 0x04da }
        L_0x04be:
            java.lang.String r0 = "pref"
            java.lang.String r4 = "ok0"
            boolean r5 = W     // Catch:{ Throwable -> 0x04da }
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r4, (boolean) r5)     // Catch:{ Throwable -> 0x04da }
            java.lang.String r0 = "pref"
            java.lang.String r4 = "ok2"
            boolean r5 = X     // Catch:{ Throwable -> 0x04da }
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r4, (boolean) r5)     // Catch:{ Throwable -> 0x04da }
            java.lang.String r0 = "pref"
            java.lang.String r4 = "ok3"
            boolean r5 = Y     // Catch:{ Throwable -> 0x04da }
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r4, (boolean) r5)     // Catch:{ Throwable -> 0x04da }
            goto L_0x04e2
        L_0x04da:
            r0 = move-exception
            java.lang.String r4 = "AuthUtil"
            java.lang.String r5 = "loadConfigDataNewCollectionOffline"
            com.loc.es.a(r0, r4, r5)     // Catch:{ Throwable -> 0x05b6 }
        L_0x04e2:
            if (r1 == 0) goto L_0x0530
            if (r2 != 0) goto L_0x04e7
            goto L_0x0530
        L_0x04e7:
            java.lang.String r0 = "15O"
            org.json.JSONObject r0 = r2.optJSONObject(r0)     // Catch:{ Throwable -> 0x0530 }
            if (r0 == 0) goto L_0x0530
            java.lang.String r4 = "able"
            java.lang.String r4 = r0.optString(r4)     // Catch:{ Throwable -> 0x0530 }
            boolean r4 = com.loc.v.a((java.lang.String) r4, (boolean) r3)     // Catch:{ Throwable -> 0x0530 }
            if (r4 == 0) goto L_0x0523
            java.lang.String r4 = "fl"
            org.json.JSONArray r4 = r0.optJSONArray(r4)     // Catch:{ Throwable -> 0x0530 }
            if (r4 == 0) goto L_0x0515
            int r5 = r4.length()     // Catch:{ Throwable -> 0x0530 }
            if (r5 <= 0) goto L_0x0515
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x0530 }
            java.lang.String r5 = android.os.Build.MANUFACTURER     // Catch:{ Throwable -> 0x0530 }
            boolean r4 = r4.contains(r5)     // Catch:{ Throwable -> 0x0530 }
            if (r4 == 0) goto L_0x0523
        L_0x0515:
            java.lang.String r4 = "iv"
            r5 = 30
            int r0 = r0.optInt(r4, r5)     // Catch:{ Throwable -> 0x0530 }
            int r0 = r0 * 1000
            long r4 = (long) r0     // Catch:{ Throwable -> 0x0530 }
            ab = r4     // Catch:{ Throwable -> 0x0530 }
            goto L_0x0527
        L_0x0523:
            r4 = -1
            ab = r4     // Catch:{ Throwable -> 0x0530 }
        L_0x0527:
            java.lang.String r0 = "pref"
            java.lang.String r4 = "awsi"
            long r5 = ab     // Catch:{ Throwable -> 0x0530 }
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r4, (long) r5)     // Catch:{ Throwable -> 0x0530 }
        L_0x0530:
            if (r1 == 0) goto L_0x0574
            if (r2 != 0) goto L_0x0535
            goto L_0x0574
        L_0x0535:
            java.lang.String r0 = "15U"
            org.json.JSONObject r0 = r2.optJSONObject(r0)     // Catch:{ Throwable -> 0x0574 }
            if (r0 == 0) goto L_0x0574
            java.lang.String r4 = "able"
            java.lang.String r4 = r0.optString(r4)     // Catch:{ Throwable -> 0x0574 }
            boolean r5 = ac     // Catch:{ Throwable -> 0x0574 }
            boolean r4 = com.loc.v.a((java.lang.String) r4, (boolean) r5)     // Catch:{ Throwable -> 0x0574 }
            java.lang.String r5 = "yn"
            int r6 = ad     // Catch:{ Throwable -> 0x0574 }
            int r5 = r0.optInt(r5, r6)     // Catch:{ Throwable -> 0x0574 }
            java.lang.String r6 = "sysTime"
            long r7 = ae     // Catch:{ Throwable -> 0x0574 }
            long r6 = r0.optLong(r6, r7)     // Catch:{ Throwable -> 0x0574 }
            ae = r6     // Catch:{ Throwable -> 0x0574 }
            java.lang.String r0 = "pref"
            java.lang.String r6 = "15ua"
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r6, (boolean) r4)     // Catch:{ Throwable -> 0x0574 }
            java.lang.String r0 = "pref"
            java.lang.String r4 = "15un"
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r4, (int) r5)     // Catch:{ Throwable -> 0x0574 }
            java.lang.String r0 = "pref"
            java.lang.String r4 = "15ust"
            long r5 = ae     // Catch:{ Throwable -> 0x0574 }
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r4, (long) r5)     // Catch:{ Throwable -> 0x0574 }
        L_0x0574:
            if (r1 == 0) goto L_0x05b4
            if (r2 != 0) goto L_0x0579
            goto L_0x05b4
        L_0x0579:
            java.lang.String r0 = "16P"
            org.json.JSONObject r0 = r2.optJSONObject(r0)     // Catch:{ Throwable -> 0x05b4 }
            if (r0 != 0) goto L_0x0589
            java.lang.String r0 = "pref"
            java.lang.String r2 = "dnab"
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r0, (java.lang.String) r2, (boolean) r3)     // Catch:{ Throwable -> 0x05b4 }
            goto L_0x05b4
        L_0x0589:
            java.lang.String r2 = "able"
            java.lang.String r2 = r0.optString(r2)     // Catch:{ Throwable -> 0x05b4 }
            boolean r2 = com.loc.v.a((java.lang.String) r2, (boolean) r3)     // Catch:{ Throwable -> 0x05b4 }
            java.lang.String r3 = "mi"
            int r3 = r0.optInt(r3)     // Catch:{ Throwable -> 0x05b4 }
            java.lang.String r4 = "ma"
            int r0 = r0.optInt(r4)     // Catch:{ Throwable -> 0x05b4 }
            java.lang.String r4 = "pref"
            java.lang.String r5 = "dnab"
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r4, (java.lang.String) r5, (boolean) r2)     // Catch:{ Throwable -> 0x05b4 }
            java.lang.String r2 = "pref"
            java.lang.String r4 = "dnmi"
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r2, (java.lang.String) r4, (int) r3)     // Catch:{ Throwable -> 0x05b4 }
            java.lang.String r2 = "pref"
            java.lang.String r3 = "dnma"
            com.loc.ez.a((android.content.Context) r1, (java.lang.String) r2, (java.lang.String) r3, (int) r0)     // Catch:{ Throwable -> 0x05b4 }
        L_0x05b4:
            r1 = 1
            return r1
        L_0x05b6:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.er.a(android.content.Context, com.loc.v$a):boolean");
    }

    public static int b() {
        return o;
    }

    public static boolean b(long j2) {
        if (!J) {
            return false;
        }
        return K < 0 || fa.b() - j2 < K;
    }

    public static boolean b(Context context) {
        if (!y) {
            return false;
        }
        if (z == -1 || A == 0) {
            return true;
        }
        if (!fa.b(A, ez.b(context, ab.a.b, "nowtime", 0))) {
            h(context);
            ez.a(context, ab.a.b, "count", 1);
            return true;
        }
        int b2 = ez.b(context, ab.a.b, "count", 0);
        if (b2 >= z) {
            return false;
        }
        ez.a(context, ab.a.b, "count", b2 + 1);
        return true;
    }

    public static boolean c() {
        return p;
    }

    public static boolean c(Context context) {
        if (!C) {
            return false;
        }
        if (D == -1 || E == 0) {
            return true;
        }
        if (!fa.b(E, ez.b(context, ab.a.b, "pushSerTime", 0))) {
            i(context);
            ez.a(context, ab.a.b, "pushCount", 1);
            return true;
        }
        int b2 = ez.b(context, ab.a.b, "pushCount", 0);
        if (b2 >= D) {
            return false;
        }
        ez.a(context, ab.a.b, "pushCount", b2 + 1);
        return true;
    }

    public static int d() {
        return q;
    }

    public static void d(Context context) {
        try {
            s = ez.b(context, ab.a.b, LogCategory.CATEGORY_EXCEPTION, s);
            e(context);
        } catch (Throwable th) {
            es.a(th, "AuthUtil", "loadLastAbleState p1");
        }
        try {
            t = ez.b(context, ab.a.b, UserDataStore.FIRST_NAME, t);
            u = ez.b(context, ab.a.b, "mpn", u);
            v = ez.b(context, ab.a.b, "igu", v);
            w = ez.b(context, ab.a.b, d.H, w);
            bp.a(t, v, w);
        } catch (Throwable unused) {
        }
        try {
            J = ez.b(context, ab.a.b, l.a.x, J);
            K = ez.b(context, ab.a.b, "ct", K);
        } catch (Throwable unused2) {
        }
        try {
            h = ez.b(context, ab.a.b, "fr", h);
        } catch (Throwable unused3) {
        }
        try {
            W = ez.b(context, ab.a.b, "ok0", W);
            X = ez.b(context, ab.a.b, "ok2", X);
            Y = ez.b(context, ab.a.b, "ok3", Y);
        } catch (Throwable unused4) {
        }
        try {
            aa = ez.b(context, ab.a.b, "asw", aa);
        } catch (Throwable unused5) {
        }
        try {
            ab = ez.b(context, ab.a.b, "awsi", ab);
        } catch (Throwable unused6) {
        }
        try {
            ac = ez.b(context, ab.a.b, "15ua", ac);
            ad = ez.b(context, ab.a.b, "15un", ad);
            ae = ez.b(context, ab.a.b, "15ust", ae);
        } catch (Throwable unused7) {
        }
    }

    public static void e(Context context) {
        try {
            ac b2 = es.b();
            b2.a(s);
            aq.a(context, b2);
        } catch (Throwable unused) {
        }
    }

    public static boolean e() {
        return r;
    }

    public static boolean f() {
        return b;
    }

    public static boolean f(Context context) {
        boolean z2 = O != -1 && O < P;
        if (!(!M || O == 0 || P == 0 || N == 0 || z2)) {
            if (Q != null && Q.size() > 0) {
                for (String b2 : Q) {
                    if (fa.b(context, b2)) {
                        return false;
                    }
                }
            }
            if (O == -1 && P == -1) {
                return true;
            }
            long b3 = ez.b(context, ab.a.b, "ots", 0);
            long b4 = ez.b(context, ab.a.b, "otsh", 0);
            int b5 = ez.b(context, ab.a.b, "otn", 0);
            int b6 = ez.b(context, ab.a.b, "otnh", 0);
            if (O != -1) {
                if (!fa.b(N, b3)) {
                    try {
                        SharedPreferences.Editor edit = context.getSharedPreferences(ab.a.b, 0).edit();
                        edit.putLong("ots", N);
                        edit.putLong("otsh", N);
                        edit.putInt("otn", 0);
                        edit.putInt("otnh", 0);
                        ez.a(edit);
                    } catch (Throwable th) {
                        es.a(th, "AuthUtil", "resetPrefsBind");
                    }
                    ez.a(context, ab.a.b, "otn", 1);
                    ez.a(context, ab.a.b, "otnh", 1);
                    return true;
                } else if (b5 < O) {
                    if (P == -1) {
                        ez.a(context, ab.a.b, "otn", b5 + 1);
                        ez.a(context, ab.a.b, "otnh", 0);
                        return true;
                    } else if (!fa.a(N, b4)) {
                        ez.a(context, ab.a.b, "otsh", N);
                        ez.a(context, ab.a.b, "otn", b5 + 1);
                        ez.a(context, ab.a.b, "otnh", 1);
                        return true;
                    } else if (b6 < P) {
                        ez.a(context, ab.a.b, "otn", b5 + 1);
                        ez.a(context, ab.a.b, "otnh", b6 + 1);
                        return true;
                    }
                }
            }
            if (O == -1) {
                ez.a(context, ab.a.b, "otn", 0);
                if (P == -1) {
                    ez.a(context, ab.a.b, "otnh", 0);
                    return true;
                } else if (!fa.a(N, b4)) {
                    ez.a(context, ab.a.b, "otsh", N);
                    ez.a(context, ab.a.b, "otnh", 1);
                    return true;
                } else if (b6 < P) {
                    ez.a(context, ab.a.b, "otnh", b6 + 1);
                    return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<String> g() {
        return B;
    }

    public static boolean g(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (fa.c() - f >= ((long) d)) {
                g = true;
                return true;
            }
        } catch (Throwable th) {
            es.a(th, "Aps", "isConfigNeedUpdate");
        }
        return false;
    }

    public static ArrayList<String> h() {
        return F;
    }

    private static void h(Context context) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(ab.a.b, 0).edit();
            edit.putLong("nowtime", A);
            edit.putInt("count", 0);
            ez.a(edit);
        } catch (Throwable th) {
            es.a(th, "AuthUtil", "resetPrefsBind");
        }
    }

    private static void i(Context context) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(ab.a.b, 0).edit();
            edit.putLong("pushSerTime", E);
            edit.putInt("pushCount", 0);
            ez.a(edit);
        } catch (Throwable th) {
            es.a(th, "AuthUtil", "resetPrefsBind");
        }
    }

    public static boolean i() {
        return s;
    }

    public static int j() {
        return u;
    }

    public static boolean k() {
        return x;
    }

    public static void l() {
        x = false;
    }

    public static boolean m() {
        return G;
    }

    public static long n() {
        return K;
    }

    public static boolean o() {
        return J;
    }

    public static List<ev> p() {
        return L;
    }

    public static boolean q() {
        return R;
    }

    public static int r() {
        return S;
    }

    public static boolean s() {
        return U;
    }

    public static boolean t() {
        return V;
    }

    public static boolean u() {
        if (!g) {
            return g;
        }
        g = false;
        return true;
    }

    public static boolean v() {
        return h;
    }

    public static boolean w() {
        return W;
    }

    public static boolean x() {
        return Y;
    }

    public static boolean y() {
        return X;
    }

    public static int z() {
        return Z;
    }
}
