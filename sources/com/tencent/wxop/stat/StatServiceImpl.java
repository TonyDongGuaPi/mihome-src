package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.a;
import com.tencent.wxop.stat.a.c;
import com.tencent.wxop.stat.a.i;
import com.tencent.wxop.stat.a.l;
import com.tencent.wxop.stat.common.StatConstants;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.common.b;
import com.tencent.wxop.stat.common.e;
import com.tencent.wxop.stat.common.k;
import com.tencent.wxop.stat.common.p;
import java.lang.Thread;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class StatServiceImpl {

    /* renamed from: a  reason: collision with root package name */
    static volatile int f9268a = 0;
    static volatile long b = 0;
    static volatile long c = 0;
    private static e d;
    /* access modifiers changed from: private */
    public static volatile Map<c, Long> e = new ConcurrentHashMap();
    private static volatile Map<String, Properties> f = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public static volatile Map<Integer, Integer> g = new ConcurrentHashMap(10);
    /* access modifiers changed from: private */
    public static volatile long h = 0;
    private static volatile long i = 0;
    private static volatile long j = 0;
    private static String k = "";
    private static volatile int l = 0;
    /* access modifiers changed from: private */
    public static volatile String m = "";
    /* access modifiers changed from: private */
    public static volatile String n = "";
    /* access modifiers changed from: private */
    public static Map<String, Long> o = new ConcurrentHashMap();
    private static Map<String, Long> p = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public static StatLogger q = k.b();
    /* access modifiers changed from: private */
    public static Thread.UncaughtExceptionHandler r = null;
    private static volatile boolean s = true;
    /* access modifiers changed from: private */
    public static Context t = null;

    static int a(Context context, boolean z, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z2 = z && currentTimeMillis - i >= ((long) StatConfig.d());
        i = currentTimeMillis;
        if (j == 0) {
            j = k.c();
        }
        if (currentTimeMillis >= j) {
            j = k.c();
            if (au.a(context).b(context).d() != 1) {
                au.a(context).b(context).a(1);
            }
            StatConfig.l(0);
            f9268a = 0;
            k = k.a(0);
            z2 = true;
        }
        String str = k;
        if (k.a(statSpecifyReportedInfo)) {
            str = statSpecifyReportedInfo.c() + k;
        }
        if (!p.containsKey(str)) {
            z2 = true;
        }
        if (z2) {
            if (k.a(statSpecifyReportedInfo)) {
                a(context, statSpecifyReportedInfo);
            } else if (StatConfig.w() < StatConfig.u()) {
                k.x(context);
                a(context, (StatSpecifyReportedInfo) null);
            } else {
                q.h("Exceed StatConfig.getMaxDaySessionNumbers().");
            }
            p.put(str, 1L);
        }
        if (s) {
            h(context);
            s = false;
        }
        return l;
    }

    public static Context a(Context context) {
        return context != null ? context : t;
    }

    public static void a(Context context, int i2) {
        StatLogger statLogger;
        String str;
        if (StatConfig.c()) {
            if (StatConfig.b()) {
                StatLogger statLogger2 = q;
                statLogger2.b((Object) "commitEvents, maxNumber=" + i2);
            }
            Context a2 = a(context);
            if (a2 == null) {
                statLogger = q;
                str = "The Context of StatService.commitEvents() can not be null!";
            } else if (i2 < -1 || i2 == 0) {
                statLogger = q;
                str = "The maxNumber of StatService.commitEvents() should be -1 or bigger than 0.";
            } else if (a.a(t).f() && e(a2) != null) {
                d.a(new ad(a2, i2));
                return;
            } else {
                return;
            }
            statLogger.g(str);
        }
    }

    public static void a(Context context, int i2, String str, String... strArr) {
        StatLogger statLogger;
        String str2;
        if (StatConfig.c()) {
            if (i2 <= 0) {
                statLogger = q;
                str2 = "The intervalSecond of StatService.trackCustomTimeIntervalEvent() can must bigger than 0!";
            } else {
                Context a2 = a(context);
                if (a2 == null) {
                    statLogger = q;
                    str2 = "The Context of StatService.trackCustomTimeIntervalEvent() can not be null!";
                } else if (e(a2) != null) {
                    d.a(new ab());
                    return;
                } else {
                    return;
                }
            }
            statLogger.g(str2);
        }
    }

    public static void a(Context context, StatAccount statAccount, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                q.h("context is null in reportAccount.");
            } else if (e(a2) != null) {
                d.a(new al(statAccount, a2, statSpecifyReportedInfo));
            }
        }
    }

    public static void a(Context context, StatAppMonitor statAppMonitor, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        StatLogger statLogger;
        String str;
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                statLogger = q;
                str = "The Context of StatService.reportAppMonitorStat() can not be null!";
            } else if (statAppMonitor == null) {
                statLogger = q;
                str = "The StatAppMonitor of StatService.reportAppMonitorStat() can not be null!";
            } else if (statAppMonitor.a() == null) {
                statLogger = q;
                str = "The interfaceName of StatAppMonitor on StatService.reportAppMonitorStat() can not be null!";
            } else {
                StatAppMonitor h2 = statAppMonitor.clone();
                if (e(a2) != null) {
                    d.a(new aa(a2, statSpecifyReportedInfo, h2));
                    return;
                }
                return;
            }
            statLogger.g(str);
        }
    }

    public static void a(Context context, StatGameUser statGameUser, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                q.g("The Context of StatService.reportGameUser() can not be null!");
            } else if (e(a2) != null) {
                d.a(new am(statGameUser, a2, statSpecifyReportedInfo));
            }
        }
    }

    static void a(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (e(context) != null) {
            if (StatConfig.b()) {
                q.j("start new session.");
            }
            if (statSpecifyReportedInfo == null || l == 0) {
                l = k.a();
            }
            StatConfig.j(0);
            StatConfig.v();
            new aq(new l(context, l, b(), statSpecifyReportedInfo)).a();
        }
    }

    public static void a(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null || str == null || str.length() == 0) {
                q.g("The Context or pageName of StatService.trackBeginPage() can not be null or empty!");
                return;
            }
            String str2 = new String(str);
            if (e(a2) != null) {
                d.a(new w(str2, a2, statSpecifyReportedInfo));
            }
        }
    }

    public static void a(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo, String... strArr) {
        StatLogger statLogger;
        String str2;
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                statLogger = q;
                str2 = "The Context of StatService.trackCustomEvent() can not be null!";
            } else if (a(str)) {
                statLogger = q;
                str2 = "The event_id of StatService.trackCustomEvent() can not be null or empty.";
            } else {
                c cVar = new c(str, strArr, (Properties) null);
                if (e(a2) != null) {
                    d.a(new s(a2, statSpecifyReportedInfo, cVar));
                    return;
                }
                return;
            }
            statLogger.g(str2);
        }
    }

    public static void a(Context context, String str, Properties properties, int i2, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        StatLogger statLogger;
        String str2;
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                statLogger = q;
                str2 = "The Context of StatService.trackCustomEndEvent() can not be null!";
            } else if (a(str)) {
                statLogger = q;
                str2 = "The event_id of StatService.trackCustomEndEvent() can not be null or empty.";
            } else {
                c cVar = new c(str, (String[]) null, properties);
                if (e(a2) != null) {
                    d.a(new ac(a2, statSpecifyReportedInfo, cVar, i2));
                    return;
                }
                return;
            }
            statLogger.g(str2);
        }
    }

    public static void a(Context context, String str, Properties properties, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        StatLogger statLogger;
        String str2;
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                statLogger = q;
                str2 = "The Context of StatService.trackCustomEvent() can not be null!";
            } else if (a(str)) {
                statLogger = q;
                str2 = "The event_id of StatService.trackCustomEvent() can not be null or empty.";
            } else {
                c cVar = new c(str, (String[]) null, properties);
                if (e(a2) != null) {
                    d.a(new u(a2, statSpecifyReportedInfo, cVar));
                    return;
                }
                return;
            }
            statLogger.g(str2);
        }
    }

    static void a(Context context, Throwable th) {
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                q.g("The Context of StatService.reportSdkSelfException() can not be null!");
            } else if (e(a2) != null) {
                d.a(new q(a2, th));
            }
        }
    }

    public static void a(Context context, Throwable th, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                q.g("The Context of StatService.reportException() can not be null!");
            } else if (e(a2) != null) {
                d.a(new r(th, a2, statSpecifyReportedInfo));
            }
        }
    }

    public static void a(Context context, Map<String, String> map) {
        if (map == null || map.size() > 512) {
            q.g("The map in setEnvAttributes can't be null or its size can't exceed 512.");
            return;
        }
        try {
            b.a(context, map);
        } catch (JSONException e2) {
            q.b((Throwable) e2);
        }
    }

    public static void a(Context context, Map<String, Integer> map, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        StatLogger statLogger;
        String str;
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                statLogger = q;
                str = "The Context of StatService.testSpeed() can not be null!";
            } else if (map == null || map.size() == 0) {
                statLogger = q;
                str = "The domainMap of StatService.testSpeed() can not be null or empty!";
            } else {
                HashMap hashMap = new HashMap(map);
                if (e(a2) != null) {
                    d.a(new af(a2, hashMap, statSpecifyReportedInfo));
                    return;
                }
                return;
            }
            statLogger.g(str);
        }
    }

    public static void a(String str, Properties properties) {
        if (!k.c(str)) {
            q.h("event_id or commonProp for setCommonKeyValueForKVEvent is invalid.");
        } else if (properties == null || properties.size() <= 0) {
            f.remove(str);
        } else {
            f.put(str, (Properties) properties.clone());
        }
    }

    static boolean a() {
        if (f9268a < 2) {
            return false;
        }
        b = System.currentTimeMillis();
        return true;
    }

    public static boolean a(Context context, String str, String str2, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        try {
            if (!StatConfig.c()) {
                q.g("MTA StatService is disable.");
                return false;
            }
            if (StatConfig.b()) {
                q.j("MTA SDK version, current: " + StatConstants.f9313a + " ,required: " + str2);
            }
            if (context != null) {
                if (str2 != null) {
                    if (k.b(StatConstants.f9313a) < k.b(str2)) {
                        q.g(("MTA SDK version conflicted, current: " + StatConstants.f9313a + ",required: " + str2) + ". please delete the current SDK and download the latest one. official website: http://mta.qq.com/ or http://mta.oa.com/");
                        StatConfig.b(false);
                        return false;
                    }
                    String c2 = StatConfig.c(context);
                    if (c2 == null || c2.length() == 0) {
                        StatConfig.c("-");
                    }
                    if (str != null) {
                        StatConfig.b(context, str);
                    }
                    if (e(context) == null) {
                        return true;
                    }
                    d.a(new an(context, statSpecifyReportedInfo));
                    return true;
                }
            }
            q.g("Context or mtaSdkVersion in StatService.startStatService() is null, please check it!");
            StatConfig.b(false);
            return false;
        } catch (Throwable th) {
            q.b(th);
            return false;
        }
    }

    static boolean a(String str) {
        return str == null || str.length() == 0;
    }

    public static Properties b(String str) {
        return f.get(str);
    }

    static JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (StatConfig.b.d != 0) {
                jSONObject2.put("v", StatConfig.b.d);
            }
            jSONObject.put(Integer.toString(StatConfig.b.f9327a), jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            if (StatConfig.f9265a.d != 0) {
                jSONObject3.put("v", StatConfig.f9265a.d);
            }
            jSONObject.put(Integer.toString(StatConfig.f9265a.f9327a), jSONObject3);
        } catch (JSONException e2) {
            q.b((Throwable) e2);
        }
        return jSONObject;
    }

    public static void b(Context context) {
        if (context != null) {
            t = context.getApplicationContext();
        }
    }

    public static void b(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                q.g("The Context of StatService.startNewSession() can not be null!");
            } else if (e(a2) != null) {
                d.a(new ai(a2, statSpecifyReportedInfo));
            }
        }
    }

    public static void b(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null || str == null || str.length() == 0) {
                q.g("The Context or pageName of StatService.trackEndPage() can not be null or empty!");
                return;
            }
            String str2 = new String(str);
            if (e(a2) != null) {
                d.a(new ah(a2, str2, statSpecifyReportedInfo));
            }
        }
    }

    public static void b(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo, String... strArr) {
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                q.g("The Context of StatService.trackCustomBeginEvent() can not be null!");
                return;
            }
            c cVar = new c(str, strArr, (Properties) null);
            if (e(a2) != null) {
                d.a(new v(str, cVar, a2));
            }
        }
    }

    public static void b(Context context, String str, Properties properties, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                q.g("The Context of StatService.trackCustomBeginEvent() can not be null!");
                return;
            }
            c cVar = new c(str, (String[]) null, properties);
            if (e(a2) != null) {
                d.a(new y(str, cVar, a2));
            }
        }
    }

    public static void c() {
        i = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized void c(android.content.Context r6) {
        /*
            java.lang.Class<com.tencent.wxop.stat.StatServiceImpl> r0 = com.tencent.wxop.stat.StatServiceImpl.class
            monitor-enter(r0)
            if (r6 != 0) goto L_0x0007
            monitor-exit(r0)
            return
        L_0x0007:
            com.tencent.wxop.stat.common.e r1 = d     // Catch:{ all -> 0x003d }
            if (r1 != 0) goto L_0x003b
            boolean r1 = d((android.content.Context) r6)     // Catch:{ all -> 0x003d }
            if (r1 != 0) goto L_0x0013
            monitor-exit(r0)
            return
        L_0x0013:
            android.content.Context r6 = r6.getApplicationContext()     // Catch:{ all -> 0x003d }
            t = r6     // Catch:{ all -> 0x003d }
            com.tencent.wxop.stat.common.e r1 = new com.tencent.wxop.stat.common.e     // Catch:{ all -> 0x003d }
            r1.<init>()     // Catch:{ all -> 0x003d }
            d = r1     // Catch:{ all -> 0x003d }
            r1 = 0
            java.lang.String r1 = com.tencent.wxop.stat.common.k.a((int) r1)     // Catch:{ all -> 0x003d }
            k = r1     // Catch:{ all -> 0x003d }
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x003d }
            long r3 = com.tencent.wxop.stat.StatConfig.i     // Catch:{ all -> 0x003d }
            r5 = 0
            long r1 = r1 + r3
            h = r1     // Catch:{ all -> 0x003d }
            com.tencent.wxop.stat.common.e r1 = d     // Catch:{ all -> 0x003d }
            com.tencent.wxop.stat.l r2 = new com.tencent.wxop.stat.l     // Catch:{ all -> 0x003d }
            r2.<init>(r6)     // Catch:{ all -> 0x003d }
            r1.a(r2)     // Catch:{ all -> 0x003d }
        L_0x003b:
            monitor-exit(r0)
            return
        L_0x003d:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.StatServiceImpl.c(android.content.Context):void");
    }

    /* access modifiers changed from: private */
    public static void c(Context context, StatAccount statAccount, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        try {
            new aq(new a(context, a(context, false, statSpecifyReportedInfo), statAccount, statSpecifyReportedInfo)).a();
        } catch (Throwable th) {
            q.b(th);
            a(context, th);
        }
    }

    public static void c(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.c() && e(context) != null) {
            d.a(new aj(context, statSpecifyReportedInfo));
        }
    }

    public static void c(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                q.g("context is null in reportQQ()");
            } else if (e(a2) != null) {
                d.a(new ak(str, a2, statSpecifyReportedInfo));
            }
        }
    }

    public static void c(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo, String... strArr) {
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                q.g("The Context of StatService.trackCustomEndEvent() can not be null!");
                return;
            }
            c cVar = new c(str, strArr, (Properties) null);
            if (e(a2) != null) {
                d.a(new x(str, cVar, a2, statSpecifyReportedInfo));
            }
        }
    }

    public static void c(Context context, String str, Properties properties, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                q.g("The Context of StatService.trackCustomEndEvent() can not be null!");
                return;
            }
            c cVar = new c(str, (String[]) null, properties);
            if (e(a2) != null) {
                d.a(new z(str, cVar, a2, statSpecifyReportedInfo));
            }
        }
    }

    static void d() {
        f9268a = 0;
        b = 0;
    }

    public static void d(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.c() && e(context) != null) {
            d.a(new m(context, statSpecifyReportedInfo));
        }
    }

    public static void d(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                q.g("The Context of StatService.reportError() can not be null!");
            } else if (e(a2) != null) {
                d.a(new p(str, a2, statSpecifyReportedInfo));
            }
        }
    }

    static boolean d(Context context) {
        boolean z;
        long a2 = p.a(context, StatConfig.c, 0);
        long b2 = k.b(StatConstants.f9313a);
        if (b2 <= a2) {
            StatLogger statLogger = q;
            statLogger.g("MTA is disable for current version:" + b2 + ",wakeup version:" + a2);
            z = false;
        } else {
            z = true;
        }
        long a3 = p.a(context, StatConfig.d, 0);
        if (a3 > System.currentTimeMillis()) {
            StatLogger statLogger2 = q;
            statLogger2.g("MTA is disable for current time:" + System.currentTimeMillis() + ",wakeup time:" + a3);
            z = false;
        }
        StatConfig.b(z);
        return z;
    }

    static e e(Context context) {
        if (d == null) {
            synchronized (StatServiceImpl.class) {
                if (d == null) {
                    try {
                        c(context);
                    } catch (Throwable th) {
                        q.a(th);
                        StatConfig.b(false);
                    }
                }
            }
        }
        return d;
    }

    static void e() {
        f9268a++;
        b = System.currentTimeMillis();
        i(t);
    }

    public static void e(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (e(a2) != null) {
                d.a(new n(a2));
            }
        }
    }

    public static void f(Context context) {
        if (StatConfig.c() && e(a(context)) != null) {
            d.a(new o(context));
        }
    }

    static void g(Context context) {
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                q.g("The Context of StatService.sendNetworkDetector() can not be null!");
                return;
            }
            try {
                i.b(a2).a((com.tencent.wxop.stat.a.e) new i(a2), (h) new t());
            } catch (Throwable th) {
                q.b(th);
            }
        }
    }

    public static void h(Context context) {
        if (StatConfig.c()) {
            Context a2 = a(context);
            if (a2 == null) {
                q.g("The Context of StatService.testSpeed() can not be null!");
            } else if (e(a2) != null) {
                d.a(new ae(a2));
            }
        }
    }

    public static void i(Context context) {
        if (StatConfig.c() && StatConfig.n > 0) {
            Context a2 = a(context);
            if (a2 == null) {
                q.g("The Context of StatService.testSpeed() can not be null!");
            } else {
                au.a(a2).c();
            }
        }
    }

    static void j(Context context) {
        c = System.currentTimeMillis() + ((long) (StatConfig.m() * 60000));
        p.b(context, "last_period_ts", c);
        a(context, -1);
    }
}
