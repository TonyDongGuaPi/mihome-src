package com.loc;

import android.content.Context;
import android.os.Build;
import com.xiaomi.pluginhost.PluginHostActivity;
import com.xiaomi.stat.d;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ar {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static WeakReference<bl> f6486a = null;
    private static boolean b = true;
    private static WeakReference<cf> c;
    private static WeakReference<cf> d;
    private static String[] e = new String[10];
    private static int f = 0;
    private static boolean g = false;
    private static int h = 0;
    private static ac i;

    private static ac a(Context context, String str) {
        List<ac> c2 = ao.c(context);
        if (c2 == null) {
            c2 = new ArrayList<>();
        }
        if (str != null && !"".equals(str)) {
            for (ac acVar : c2) {
                if (ao.a(acVar.f(), str)) {
                    return acVar;
                }
            }
            if (str.contains("com.amap.api.col")) {
                try {
                    return ad.a();
                } catch (t e2) {
                    e2.printStackTrace();
                }
            }
            if (str.contains("com.amap.co") || str.contains("com.amap.opensdk.co") || str.contains("com.amap.location")) {
                try {
                    ac b2 = ad.b();
                    b2.a(true);
                    return b2;
                } catch (t e3) {
                    e3.printStackTrace();
                }
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a2, code lost:
        r10 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00a4 */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0125 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00a4 A[EDGE_INSN: B:45:0x00a4->B:46:? ?: BREAK  
    EDGE_INSN: B:34:0x0078->B:45:0x00a4 ?: BREAK  , SYNTHETIC, Splitter:B:45:0x00a4] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00d7 A[SYNTHETIC, Splitter:B:67:0x00d7] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00e5 A[SYNTHETIC, Splitter:B:72:0x00e5] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00ec A[SYNTHETIC, Splitter:B:76:0x00ec] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00fa A[SYNTHETIC, Splitter:B:81:0x00fa] */
    /* JADX WARNING: Removed duplicated region for block: B:89:? A[ExcHandler: FileNotFoundException (unused java.io.FileNotFoundException), SYNTHETIC, Splitter:B:12:0x002b] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x010b A[SYNTHETIC, Splitter:B:90:0x010b] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0119 A[SYNTHETIC, Splitter:B:95:0x0119] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0120  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.util.List<com.loc.ac> r10) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0107, Throwable -> 0x00cb, all -> 0x00c7 }
            java.lang.String r2 = "/data/anr/traces.txt"
            r1.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0107, Throwable -> 0x00cb, all -> 0x00c7 }
            boolean r2 = r1.exists()     // Catch:{ FileNotFoundException -> 0x0107, Throwable -> 0x00cb, all -> 0x00c7 }
            if (r2 != 0) goto L_0x000f
            return r0
        L_0x000f:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0107, Throwable -> 0x00cb, all -> 0x00c7 }
            r2.<init>(r1)     // Catch:{ FileNotFoundException -> 0x0107, Throwable -> 0x00cb, all -> 0x00c7 }
            int r1 = r2.available()     // Catch:{ FileNotFoundException -> 0x00c5, Throwable -> 0x00c2, all -> 0x00bf }
            r3 = 1024000(0xfa000, float:1.43493E-39)
            if (r1 <= r3) goto L_0x0022
            int r1 = r1 - r3
            long r3 = (long) r1     // Catch:{ FileNotFoundException -> 0x00c5, Throwable -> 0x00c2, all -> 0x00bf }
            r2.skip(r3)     // Catch:{ FileNotFoundException -> 0x00c5, Throwable -> 0x00c2, all -> 0x00bf }
        L_0x0022:
            com.loc.bd r1 = new com.loc.bd     // Catch:{ FileNotFoundException -> 0x00c5, Throwable -> 0x00c2, all -> 0x00bf }
            java.nio.charset.Charset r3 = com.loc.be.f6506a     // Catch:{ FileNotFoundException -> 0x00c5, Throwable -> 0x00c2, all -> 0x00bf }
            r1.<init>(r2, r3)     // Catch:{ FileNotFoundException -> 0x00c5, Throwable -> 0x00c2, all -> 0x00bf }
            r3 = 0
            r4 = 0
        L_0x002b:
            java.lang.String r5 = r1.a()     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
            java.lang.String r5 = r5.trim()     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
            java.lang.String r6 = "pid"
            boolean r6 = r5.contains(r6)     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
            r7 = 1
            if (r6 == 0) goto L_0x004a
        L_0x003c:
            java.lang.String r4 = "\"main\""
            boolean r4 = r5.startsWith(r4)     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
            if (r4 != 0) goto L_0x0049
            java.lang.String r5 = r1.a()     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
            goto L_0x003c
        L_0x0049:
            r4 = 1
        L_0x004a:
            java.lang.String r6 = ""
            boolean r6 = r5.equals(r6)     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
            if (r6 == 0) goto L_0x0055
            if (r4 == 0) goto L_0x0055
            goto L_0x00a4
        L_0x0055:
            if (r4 == 0) goto L_0x002b
            int r6 = f     // Catch:{ Throwable -> 0x006b, EOFException -> 0x00a4, FileNotFoundException -> 0x0109 }
            r8 = 9
            if (r6 <= r8) goto L_0x005f
            f = r3     // Catch:{ Throwable -> 0x006b, EOFException -> 0x00a4, FileNotFoundException -> 0x0109 }
        L_0x005f:
            java.lang.String[] r6 = e     // Catch:{ Throwable -> 0x006b, EOFException -> 0x00a4, FileNotFoundException -> 0x0109 }
            int r8 = f     // Catch:{ Throwable -> 0x006b, EOFException -> 0x00a4, FileNotFoundException -> 0x0109 }
            r6[r8] = r5     // Catch:{ Throwable -> 0x006b, EOFException -> 0x00a4, FileNotFoundException -> 0x0109 }
            int r6 = f     // Catch:{ Throwable -> 0x006b, EOFException -> 0x00a4, FileNotFoundException -> 0x0109 }
            int r6 = r6 + r7
            f = r6     // Catch:{ Throwable -> 0x006b, EOFException -> 0x00a4, FileNotFoundException -> 0x0109 }
            goto L_0x0073
        L_0x006b:
            r6 = move-exception
            java.lang.String r8 = "alg"
            java.lang.String r9 = "aDa"
            com.loc.aq.b((java.lang.Throwable) r6, (java.lang.String) r8, (java.lang.String) r9)     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
        L_0x0073:
            int r6 = h     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
            r8 = 5
            if (r6 != r8) goto L_0x0079
            goto L_0x00a4
        L_0x0079:
            boolean r6 = g     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
            if (r6 != 0) goto L_0x009c
            java.util.Iterator r6 = r10.iterator()     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
        L_0x0081:
            boolean r7 = r6.hasNext()     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
            if (r7 == 0) goto L_0x002b
            java.lang.Object r7 = r6.next()     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
            com.loc.ac r7 = (com.loc.ac) r7     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
            java.lang.String[] r8 = r7.f()     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
            boolean r8 = com.loc.ao.b((java.lang.String[]) r8, (java.lang.String) r5)     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
            g = r8     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
            if (r8 == 0) goto L_0x0081
            i = r7     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
            goto L_0x002b
        L_0x009c:
            int r5 = h     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
            int r5 = r5 + r7
            h = r5     // Catch:{ EOFException -> 0x00a4, FileNotFoundException -> 0x0109, Throwable -> 0x00a2 }
            goto L_0x002b
        L_0x00a2:
            r10 = move-exception
            goto L_0x00ce
        L_0x00a4:
            r1.close()     // Catch:{ Throwable -> 0x00a8 }
            goto L_0x00b0
        L_0x00a8:
            r10 = move-exception
            java.lang.String r1 = "alg"
            java.lang.String r3 = "getA"
            com.loc.aq.b((java.lang.Throwable) r10, (java.lang.String) r1, (java.lang.String) r3)
        L_0x00b0:
            r2.close()     // Catch:{ Throwable -> 0x00b5 }
            goto L_0x011c
        L_0x00b5:
            r10 = move-exception
            java.lang.String r1 = "alg"
            java.lang.String r2 = "getA"
            com.loc.aq.b((java.lang.Throwable) r10, (java.lang.String) r1, (java.lang.String) r2)
            goto L_0x011c
        L_0x00bf:
            r10 = move-exception
            r1 = r0
            goto L_0x00ea
        L_0x00c2:
            r10 = move-exception
            r1 = r0
            goto L_0x00ce
        L_0x00c5:
            r1 = r0
            goto L_0x0109
        L_0x00c7:
            r10 = move-exception
            r1 = r0
            r2 = r1
            goto L_0x00ea
        L_0x00cb:
            r10 = move-exception
            r1 = r0
            r2 = r1
        L_0x00ce:
            java.lang.String r3 = "alg"
            java.lang.String r4 = "getA"
            com.loc.aq.b((java.lang.Throwable) r10, (java.lang.String) r3, (java.lang.String) r4)     // Catch:{ all -> 0x00e9 }
            if (r1 == 0) goto L_0x00e3
            r1.close()     // Catch:{ Throwable -> 0x00db }
            goto L_0x00e3
        L_0x00db:
            r10 = move-exception
            java.lang.String r1 = "alg"
            java.lang.String r3 = "getA"
            com.loc.aq.b((java.lang.Throwable) r10, (java.lang.String) r1, (java.lang.String) r3)
        L_0x00e3:
            if (r2 == 0) goto L_0x011c
            r2.close()     // Catch:{ Throwable -> 0x00b5 }
            goto L_0x011c
        L_0x00e9:
            r10 = move-exception
        L_0x00ea:
            if (r1 == 0) goto L_0x00f8
            r1.close()     // Catch:{ Throwable -> 0x00f0 }
            goto L_0x00f8
        L_0x00f0:
            r0 = move-exception
            java.lang.String r1 = "alg"
            java.lang.String r3 = "getA"
            com.loc.aq.b((java.lang.Throwable) r0, (java.lang.String) r1, (java.lang.String) r3)
        L_0x00f8:
            if (r2 == 0) goto L_0x0106
            r2.close()     // Catch:{ Throwable -> 0x00fe }
            goto L_0x0106
        L_0x00fe:
            r0 = move-exception
            java.lang.String r1 = "alg"
            java.lang.String r2 = "getA"
            com.loc.aq.b((java.lang.Throwable) r0, (java.lang.String) r1, (java.lang.String) r2)
        L_0x0106:
            throw r10
        L_0x0107:
            r1 = r0
            r2 = r1
        L_0x0109:
            if (r1 == 0) goto L_0x0117
            r1.close()     // Catch:{ Throwable -> 0x010f }
            goto L_0x0117
        L_0x010f:
            r10 = move-exception
            java.lang.String r1 = "alg"
            java.lang.String r3 = "getA"
            com.loc.aq.b((java.lang.Throwable) r10, (java.lang.String) r1, (java.lang.String) r3)
        L_0x0117:
            if (r2 == 0) goto L_0x011c
            r2.close()     // Catch:{ Throwable -> 0x00b5 }
        L_0x011c:
            boolean r10 = g
            if (r10 == 0) goto L_0x0125
            java.lang.String r10 = b()
            return r10
        L_0x0125:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ar.a(java.util.List):java.lang.String");
    }

    static void a(Context context) {
        String a2;
        List<ac> c2 = ao.c(context);
        if (c2 != null && c2.size() != 0 && (a2 = a(c2)) != null && !"".equals(a2) && i != null) {
            a(context, i, 2, "ANR", a2);
        }
    }

    private static void a(Context context, ac acVar, int i2, String str, String str2) {
        String str3;
        String a2 = ad.a(System.currentTimeMillis());
        String a3 = bs.a(context, acVar);
        u.a(context);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(a3);
        stringBuffer.append(",\"timestamp\":\"");
        stringBuffer.append(a2);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i2);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str2);
        stringBuffer.append("\"");
        String stringBuffer2 = stringBuffer.toString();
        if (stringBuffer2 != null && !"".equals(stringBuffer2)) {
            String c2 = aa.c(str2);
            if (i2 == 1) {
                str3 = ao.b;
            } else if (i2 == 2) {
                str3 = ao.d;
            } else if (i2 == 0) {
                str3 = ao.c;
            } else {
                return;
            }
            String str4 = str3;
            bl a4 = bs.a(f6486a);
            bs.a(context, a4, str4, 1000, 40960, "1");
            if (a4.e == null) {
                a4.e = new af(new ag(new ai(new ak())));
            }
            try {
                bm.a(c2, ad.a(stringBuffer2.replaceAll("\n", "<br/>")), a4);
            } catch (Throwable unused) {
            }
        }
    }

    private static void a(final Context context, final cf cfVar, final String str) {
        aq.d().submit(new Runnable() {
            public final void run() {
                try {
                    synchronized (ar.class) {
                        bl a2 = bs.a(ar.f6486a);
                        bs.a(context, a2, str, 1000, 40960, "1");
                        a2.f = cfVar;
                        if (a2.g == null) {
                            a2.g = new bw(new bv(context, new ca(), new ag(new ai(new ak())), "EImtleSI6IiVzIiwicGxhdGZvcm0iOiJhbmRyb2lkIiwiZGl1IjoiJXMiLCJwa2ciOiIlcyIsIm1vZGVsIjoiJXMiLCJhcHBuYW1lIjoiJXMiLCJhcHB2ZXJzaW9uIjoiJXMiLCJzeXN2ZXJzaW9uIjoiJXMiLA=", u.f(context), x.u(context), u.c(context), Build.MODEL, u.b(context), u.d(context), Build.VERSION.RELEASE));
                        }
                        a2.h = 3600000;
                        bm.a(a2);
                    }
                } catch (Throwable th) {
                    aq.b(th, d.W, "pul");
                }
            }
        });
    }

    public static void a(Context context, Throwable th, int i2, String str, String str2) {
        String a2 = ad.a(th);
        ac a3 = a(context, a2);
        if (a(a3)) {
            String replaceAll = a2.replaceAll("\n", "<br/>");
            String th2 = th.toString();
            if (th2 != null && !"".equals(th2)) {
                StringBuilder sb = new StringBuilder();
                if (str != null) {
                    sb.append(PluginHostActivity.EXTRA_CLASS);
                    sb.append(str);
                }
                if (str2 != null) {
                    sb.append(" method:");
                    sb.append(str2);
                    sb.append("$<br/>");
                }
                sb.append(replaceAll);
                a(context, a3, i2, th2, sb.toString());
            }
        }
    }

    static void a(ac acVar, Context context, String str, String str2) {
        if (a(acVar) && str != null && !"".equals(str)) {
            a(context, acVar, 0, str, str2);
        }
    }

    private static boolean a(ac acVar) {
        return acVar != null && acVar.e();
    }

    private static String b() {
        StringBuilder sb = new StringBuilder();
        try {
            int i2 = f;
            while (i2 < 10 && i2 <= 9) {
                sb.append(e[i2]);
                i2++;
            }
            for (int i3 = 0; i3 < f; i3++) {
                sb.append(e[i3]);
            }
        } catch (Throwable th) {
            aq.b(th, "alg", "gLI");
        }
        return sb.toString();
    }

    static void b(Context context) {
        cd cdVar = new cd(b);
        b = false;
        a(context, cdVar, ao.c);
    }

    static void b(ac acVar, Context context, String str, String str2) {
        if (a(acVar) && str != null && !"".equals(str)) {
            a(context, acVar, 1, str, str2);
        }
    }

    static void c(Context context) {
        if (c == null || c.get() == null) {
            c = new WeakReference<>(new ce(context, 3600000, "hKey", new cg(context)));
        }
        a(context, (cf) c.get(), ao.d);
    }

    static void d(Context context) {
        if (d == null || d.get() == null) {
            d = new WeakReference<>(new ce(context, 3600000, "gKey", new cg(context)));
        }
        a(context, (cf) d.get(), ao.b);
    }
}
