package com.amap.api.services.a;

import android.content.Context;
import android.os.Build;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.pluginhost.PluginHostActivity;
import com.xiaomi.stat.d;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class cm {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static WeakReference<dj> f4374a = null;
    private static boolean b = true;
    private static WeakReference<ec> c;
    private static WeakReference<ec> d;
    private static String[] e = new String[10];
    private static int f = 0;
    private static boolean g = false;
    private static int h = 0;
    private static by i;

    private static boolean a(by byVar) {
        return byVar != null && byVar.e();
    }

    private static void a(Context context, by byVar, int i2, String str, String str2) {
        String str3;
        String a2 = dp.a();
        String a3 = dp.a(bp.a(context), dp.a(context, byVar), a2, i2, str, str2);
        if (a3 != null && !"".equals(a3)) {
            String b2 = bw.b(str2);
            if (i2 == 1) {
                str3 = cj.b;
            } else if (i2 == 2) {
                str3 = cj.d;
            } else if (i2 == 0) {
                str3 = cj.c;
            } else {
                return;
            }
            a(context, b2, str3, a3);
        }
    }

    static void a(Context context) {
        String a2;
        List<by> c2 = cj.c(context);
        if (c2 != null && c2.size() != 0 && (a2 = a(c2)) != null && !"".equals(a2) && i != null) {
            a(context, i, 2, "ANR", a2);
        }
    }

    public static void a(Context context, Throwable th, int i2, String str, String str2) {
        String a2 = bz.a(th);
        by a3 = a(context, a2);
        if (a(a3)) {
            String replaceAll = a2.replaceAll("\n", "<br/>");
            String a4 = a(th);
            if (a4 != null && !"".equals(a4)) {
                StringBuilder sb = new StringBuilder();
                if (str != null) {
                    sb.append(PluginHostActivity.EXTRA_CLASS);
                    sb.append(str);
                }
                if (str2 != null) {
                    sb.append(" method:");
                    sb.append(str2);
                    sb.append(Operators.DOLLAR_STR);
                    sb.append("<br/>");
                }
                sb.append(replaceAll);
                a(context, a3, i2, a4, sb.toString());
            }
        }
    }

    static void a(by byVar, Context context, String str, String str2) {
        if (a(byVar) && str != null && !"".equals(str)) {
            a(context, byVar, 0, str, str2);
        }
    }

    static void b(by byVar, Context context, String str, String str2) {
        if (a(byVar) && str != null && !"".equals(str)) {
            a(context, byVar, 1, str, str2);
        }
    }

    private static void a(Context context, String str, String str2, String str3) {
        dj a2 = dp.a(f4374a);
        dp.a(context, a2, str2, 1000, 40960, "1");
        if (a2.e == null) {
            a2.e = new ca(new cb(new cd(new cf())));
        }
        try {
            dk.a(str, bz.a(str3.replaceAll("\n", "<br/>")), a2);
        } catch (Throwable unused) {
        }
    }

    static void b(Context context) {
        ea eaVar = new ea(b);
        b = false;
        a(context, eaVar, cj.c);
    }

    static void c(Context context) {
        if (c == null || c.get() == null) {
            c = new WeakReference<>(new eb(context, 3600000, "hKey", new ed(context, false)));
        }
        a(context, (ec) c.get(), cj.d);
    }

    static void d(Context context) {
        if (d == null || d.get() == null) {
            d = new WeakReference<>(new eb(context, 3600000, "gKey", new ed(context, false)));
        }
        a(context, (ec) d.get(), cj.b);
    }

    private static void a(final Context context, final ec ecVar, final String str) {
        cl.c().submit(new Runnable() {
            public void run() {
                try {
                    synchronized (cm.class) {
                        dj a2 = dp.a(cm.f4374a);
                        dp.a(context, a2, str, 1000, 40960, "1");
                        a2.f = ecVar;
                        if (a2.g == null) {
                            a2.g = new dt(new ds(context, new dx(), new cb(new cd(new cf())), "EImtleSI6IiVzIiwicGxhdGZvcm0iOiJhbmRyb2lkIiwiZGl1IjoiJXMiLCJwa2ciOiIlcyIsIm1vZGVsIjoiJXMiLCJhcHBuYW1lIjoiJXMiLCJhcHB2ZXJzaW9uIjoiJXMiLCJzeXN2ZXJzaW9uIjoiJXMiLA=", bp.f(context), bt.u(context), bp.c(context), Build.MODEL, bp.b(context), bp.d(context), Build.VERSION.RELEASE));
                        }
                        a2.h = 3600000;
                        dk.a(a2);
                    }
                } catch (Throwable th) {
                    cl.c(th, d.W, "pul");
                }
            }
        });
    }

    static by a(Context context, String str) {
        List<by> c2 = cj.c(context);
        if (c2 == null) {
            c2 = new ArrayList<>();
        }
        if (str == null || "".equals(str)) {
            return null;
        }
        for (by byVar : c2) {
            if (cj.a(byVar.f(), str)) {
                return byVar;
            }
        }
        if (str.contains("com.amap.api.col")) {
            try {
                return bz.a();
            } catch (bo e2) {
                e2.printStackTrace();
            }
        }
        if (str.contains("com.amap.co") || str.contains("com.amap.opensdk.co") || str.contains("com.amap.location")) {
            try {
                by b2 = bz.b();
                b2.a(true);
                return b2;
            } catch (bo e3) {
                e3.printStackTrace();
            }
        }
        return null;
    }

    private static String a(Throwable th) {
        return th.toString();
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x008c */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00bf A[SYNTHETIC, Splitter:B:59:0x00bf] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00cd A[SYNTHETIC, Splitter:B:64:0x00cd] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00d4 A[SYNTHETIC, Splitter:B:68:0x00d4] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00e2 A[SYNTHETIC, Splitter:B:73:0x00e2] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x00f3 A[SYNTHETIC, Splitter:B:82:0x00f3] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0101 A[SYNTHETIC, Splitter:B:87:0x0101] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x010d A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String a(java.util.List<com.amap.api.services.a.by> r8) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ FileNotFoundException -> 0x00ef, Throwable -> 0x00b3, all -> 0x00af }
            java.lang.String r2 = "/data/anr/traces.txt"
            r1.<init>(r2)     // Catch:{ FileNotFoundException -> 0x00ef, Throwable -> 0x00b3, all -> 0x00af }
            boolean r2 = r1.exists()     // Catch:{ FileNotFoundException -> 0x00ef, Throwable -> 0x00b3, all -> 0x00af }
            if (r2 != 0) goto L_0x000f
            return r0
        L_0x000f:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x00ef, Throwable -> 0x00b3, all -> 0x00af }
            r2.<init>(r1)     // Catch:{ FileNotFoundException -> 0x00ef, Throwable -> 0x00b3, all -> 0x00af }
            int r1 = r2.available()     // Catch:{ FileNotFoundException -> 0x00ad, Throwable -> 0x00aa, all -> 0x00a7 }
            r3 = 1024000(0xfa000, float:1.43493E-39)
            if (r1 <= r3) goto L_0x0022
            int r1 = r1 - r3
            long r3 = (long) r1     // Catch:{ FileNotFoundException -> 0x00ad, Throwable -> 0x00aa, all -> 0x00a7 }
            r2.skip(r3)     // Catch:{ FileNotFoundException -> 0x00ad, Throwable -> 0x00aa, all -> 0x00a7 }
        L_0x0022:
            com.amap.api.services.a.cy r1 = new com.amap.api.services.a.cy     // Catch:{ FileNotFoundException -> 0x00ad, Throwable -> 0x00aa, all -> 0x00a7 }
            java.nio.charset.Charset r3 = com.amap.api.services.a.cz.f4392a     // Catch:{ FileNotFoundException -> 0x00ad, Throwable -> 0x00aa, all -> 0x00a7 }
            r1.<init>(r2, r3)     // Catch:{ FileNotFoundException -> 0x00ad, Throwable -> 0x00aa, all -> 0x00a7 }
            r3 = 0
        L_0x002a:
            java.lang.String r4 = r1.a()     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            java.lang.String r4 = r4.trim()     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            java.lang.String r5 = "pid"
            boolean r5 = r4.contains(r5)     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            r6 = 1
            if (r5 == 0) goto L_0x0049
        L_0x003b:
            java.lang.String r3 = "\"main\""
            boolean r3 = r4.startsWith(r3)     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            if (r3 != 0) goto L_0x0048
            java.lang.String r4 = r1.a()     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            goto L_0x003b
        L_0x0048:
            r3 = 1
        L_0x0049:
            java.lang.String r5 = ""
            boolean r5 = r4.equals(r5)     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            if (r5 == 0) goto L_0x0054
            if (r3 == 0) goto L_0x0054
            goto L_0x008c
        L_0x0054:
            if (r3 == 0) goto L_0x002a
            a((java.lang.String) r4)     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            int r5 = h     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            r7 = 5
            if (r5 != r7) goto L_0x005f
            goto L_0x008c
        L_0x005f:
            boolean r5 = g     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            if (r5 != 0) goto L_0x0084
            java.util.Iterator r5 = r8.iterator()     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
        L_0x0067:
            boolean r6 = r5.hasNext()     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            if (r6 == 0) goto L_0x002a
            java.lang.Object r6 = r5.next()     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            com.amap.api.services.a.by r6 = (com.amap.api.services.a.by) r6     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            java.lang.String[] r7 = r6.f()     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            boolean r7 = com.amap.api.services.a.cj.b((java.lang.String[]) r7, (java.lang.String) r4)     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            g = r7     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            boolean r7 = g     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            if (r7 == 0) goto L_0x0067
            i = r6     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            goto L_0x002a
        L_0x0084:
            int r4 = h     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            int r4 = r4 + r6
            h = r4     // Catch:{ EOFException -> 0x008c, FileNotFoundException -> 0x00f1, Throwable -> 0x008a }
            goto L_0x002a
        L_0x008a:
            r8 = move-exception
            goto L_0x00b6
        L_0x008c:
            r1.close()     // Catch:{ Throwable -> 0x0090 }
            goto L_0x0098
        L_0x0090:
            r8 = move-exception
            java.lang.String r1 = "alg"
            java.lang.String r3 = "getA"
            com.amap.api.services.a.cl.c(r8, r1, r3)
        L_0x0098:
            r2.close()     // Catch:{ Throwable -> 0x009d }
            goto L_0x0104
        L_0x009d:
            r8 = move-exception
            java.lang.String r1 = "alg"
            java.lang.String r2 = "getA"
            com.amap.api.services.a.cl.c(r8, r1, r2)
            goto L_0x0104
        L_0x00a7:
            r8 = move-exception
            r1 = r0
            goto L_0x00d2
        L_0x00aa:
            r8 = move-exception
            r1 = r0
            goto L_0x00b6
        L_0x00ad:
            r1 = r0
            goto L_0x00f1
        L_0x00af:
            r8 = move-exception
            r1 = r0
            r2 = r1
            goto L_0x00d2
        L_0x00b3:
            r8 = move-exception
            r1 = r0
            r2 = r1
        L_0x00b6:
            java.lang.String r3 = "alg"
            java.lang.String r4 = "getA"
            com.amap.api.services.a.cl.c(r8, r3, r4)     // Catch:{ all -> 0x00d1 }
            if (r1 == 0) goto L_0x00cb
            r1.close()     // Catch:{ Throwable -> 0x00c3 }
            goto L_0x00cb
        L_0x00c3:
            r8 = move-exception
            java.lang.String r1 = "alg"
            java.lang.String r3 = "getA"
            com.amap.api.services.a.cl.c(r8, r1, r3)
        L_0x00cb:
            if (r2 == 0) goto L_0x0104
            r2.close()     // Catch:{ Throwable -> 0x009d }
            goto L_0x0104
        L_0x00d1:
            r8 = move-exception
        L_0x00d2:
            if (r1 == 0) goto L_0x00e0
            r1.close()     // Catch:{ Throwable -> 0x00d8 }
            goto L_0x00e0
        L_0x00d8:
            r0 = move-exception
            java.lang.String r1 = "alg"
            java.lang.String r3 = "getA"
            com.amap.api.services.a.cl.c(r0, r1, r3)
        L_0x00e0:
            if (r2 == 0) goto L_0x00ee
            r2.close()     // Catch:{ Throwable -> 0x00e6 }
            goto L_0x00ee
        L_0x00e6:
            r0 = move-exception
            java.lang.String r1 = "alg"
            java.lang.String r2 = "getA"
            com.amap.api.services.a.cl.c(r0, r1, r2)
        L_0x00ee:
            throw r8
        L_0x00ef:
            r1 = r0
            r2 = r1
        L_0x00f1:
            if (r1 == 0) goto L_0x00ff
            r1.close()     // Catch:{ Throwable -> 0x00f7 }
            goto L_0x00ff
        L_0x00f7:
            r8 = move-exception
            java.lang.String r1 = "alg"
            java.lang.String r3 = "getA"
            com.amap.api.services.a.cl.c(r8, r1, r3)
        L_0x00ff:
            if (r2 == 0) goto L_0x0104
            r2.close()     // Catch:{ Throwable -> 0x009d }
        L_0x0104:
            boolean r8 = g
            if (r8 == 0) goto L_0x010d
            java.lang.String r8 = b()
            return r8
        L_0x010d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.cm.a(java.util.List):java.lang.String");
    }

    private static void a(String str) {
        try {
            if (f > 9) {
                f = 0;
            }
            e[f] = str;
            f++;
        } catch (Throwable th) {
            cl.c(th, "alg", "aDa");
        }
    }

    private static String b() {
        StringBuilder sb = new StringBuilder();
        try {
            int i2 = f;
            while (true) {
                if (i2 >= 10) {
                    break;
                } else if (i2 > 9) {
                    break;
                } else {
                    sb.append(e[i2]);
                    i2++;
                }
            }
            for (int i3 = 0; i3 < f; i3++) {
                sb.append(e[i3]);
            }
        } catch (Throwable th) {
            cl.c(th, "alg", "gLI");
        }
        return sb.toString();
    }
}
