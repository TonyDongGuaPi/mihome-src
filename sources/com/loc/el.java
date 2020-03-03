package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.stat.ab;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public final class el {
    private static el c;

    /* renamed from: a  reason: collision with root package name */
    eo f6588a = null;
    int b = 0;
    private Object d = null;
    private Context e = null;
    private ExecutorService f = null;
    private boolean g = false;
    private boolean h = true;
    private final int i = 2;
    private String j = "";
    private String k = "";
    private String[] l = null;
    private final int m = 5;
    private final int n = 2;

    class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        eo f6589a = null;

        a(eo eoVar) {
            this.f6589a = eoVar;
        }

        public final void run() {
            el.this.b++;
            el.this.b(this.f6589a);
            el elVar = el.this;
            elVar.b--;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x006e A[SYNTHETIC, Splitter:B:17:0x006e] */
    /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private el(android.content.Context r11) {
        /*
            r10 = this;
            r10.<init>()
            r0 = 0
            r10.d = r0
            r10.e = r0
            r10.f = r0
            r1 = 0
            r10.g = r1
            r2 = 1
            r10.h = r2
            r10.f6588a = r0
            r3 = 2
            r10.i = r3
            java.lang.String r4 = ""
            r10.j = r4
            java.lang.String r4 = ""
            r10.k = r4
            r10.l = r0
            r10.b = r1
            r0 = 5
            r10.m = r0
            r10.n = r3
            r10.e = r11
            android.content.Context r11 = r10.e
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r3 = "DnsManager ==> init "
            r0[r1] = r3
            com.loc.fa.a()
            java.lang.String r0 = "pref"
            java.lang.String r3 = "dnab"
            boolean r0 = com.loc.ez.b((android.content.Context) r11, (java.lang.String) r0, (java.lang.String) r3, (boolean) r2)
            if (r0 == 0) goto L_0x006b
            int r0 = android.os.Build.VERSION.SDK_INT
            r3 = 8
            if (r11 != 0) goto L_0x0044
            goto L_0x0050
        L_0x0044:
            java.lang.String r4 = "pref"
            java.lang.String r5 = "dnmi"
            int r4 = com.loc.ez.b((android.content.Context) r11, (java.lang.String) r4, (java.lang.String) r5, (int) r3)
            if (r4 > r3) goto L_0x004f
            goto L_0x0050
        L_0x004f:
            r3 = r4
        L_0x0050:
            if (r0 < r3) goto L_0x006b
            int r0 = android.os.Build.VERSION.SDK_INT
            r3 = 22
            if (r11 != 0) goto L_0x0059
            goto L_0x0067
        L_0x0059:
            java.lang.String r4 = "pref"
            java.lang.String r5 = "dnma"
            int r4 = com.loc.ez.b((android.content.Context) r11, (java.lang.String) r4, (java.lang.String) r5, (int) r3)
            r5 = 28
            if (r4 < r5) goto L_0x0066
            goto L_0x0067
        L_0x0066:
            r3 = r4
        L_0x0067:
            if (r0 > r3) goto L_0x006b
            r0 = 1
            goto L_0x006c
        L_0x006b:
            r0 = 0
        L_0x006c:
            if (r0 == 0) goto L_0x00ee
            java.lang.Object r0 = r10.d     // Catch:{ Throwable -> 0x00e6 }
            if (r0 != 0) goto L_0x00e5
            java.lang.String r0 = "pref"
            java.lang.String r3 = "ok6"
            int r0 = com.loc.ez.b((android.content.Context) r11, (java.lang.String) r0, (java.lang.String) r3, (int) r1)     // Catch:{ Throwable -> 0x00e6 }
            java.lang.String r3 = "pref"
            java.lang.String r4 = "ok8"
            r5 = 0
            long r3 = com.loc.ez.b((android.content.Context) r11, (java.lang.String) r3, (java.lang.String) r4, (long) r5)     // Catch:{ Throwable -> 0x00e6 }
            if (r0 == 0) goto L_0x0098
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0098
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00e6 }
            r9 = 0
            long r7 = r7 - r3
            r3 = 259200000(0xf731400, double:1.280618154E-315)
            int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r9 >= 0) goto L_0x0098
            return
        L_0x0098:
            java.lang.String r3 = "pref"
            java.lang.String r4 = "ok6"
            int r0 = r0 + r2
            com.loc.ez.a((android.content.Context) r11, (java.lang.String) r3, (java.lang.String) r4, (int) r0)     // Catch:{ Throwable -> 0x00e6 }
            java.lang.String r0 = "pref"
            java.lang.String r3 = "ok8"
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00e6 }
            com.loc.ez.a((android.content.Context) r11, (java.lang.String) r0, (java.lang.String) r3, (long) r7)     // Catch:{ Throwable -> 0x00e6 }
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00e6 }
            java.lang.String r3 = "DnsManager ==> initForJar "
            r0[r1] = r3     // Catch:{ Throwable -> 0x00e6 }
            com.loc.fa.a()     // Catch:{ Throwable -> 0x00e6 }
            java.lang.String r0 = "com.autonavi.httpdns.HttpDnsManager"
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ Throwable -> 0x00cf }
            java.lang.Class<android.content.Context> r4 = android.content.Context.class
            r3[r1] = r4     // Catch:{ Throwable -> 0x00cf }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00cf }
            r2[r1] = r11     // Catch:{ Throwable -> 0x00cf }
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ Throwable -> 0x00cf }
            java.lang.reflect.Constructor r0 = r0.getConstructor(r3)     // Catch:{ Throwable -> 0x00cf }
            java.lang.Object r0 = r0.newInstance(r2)     // Catch:{ Throwable -> 0x00cf }
            r10.d = r0     // Catch:{ Throwable -> 0x00cf }
            goto L_0x00d7
        L_0x00cf:
            r0 = move-exception
            java.lang.String r2 = "DnsManager"
            java.lang.String r3 = "initForJar"
            com.loc.es.a(r0, r2, r3)     // Catch:{ Throwable -> 0x00e6 }
        L_0x00d7:
            java.lang.String r0 = "pref"
            java.lang.String r2 = "ok6"
            com.loc.ez.a((android.content.Context) r11, (java.lang.String) r0, (java.lang.String) r2, (int) r1)     // Catch:{ Throwable -> 0x00e6 }
            java.lang.String r0 = "pref"
            java.lang.String r1 = "ok8"
            com.loc.ez.a((android.content.Context) r11, (java.lang.String) r0, (java.lang.String) r1, (long) r5)     // Catch:{ Throwable -> 0x00e6 }
        L_0x00e5:
            return
        L_0x00e6:
            r11 = move-exception
            java.lang.String r0 = "APSCoManager"
            java.lang.String r1 = "init"
            com.loc.es.a(r11, r0, r1)
        L_0x00ee:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.el.<init>(android.content.Context):void");
    }

    public static el a(Context context) {
        if (c == null) {
            c = new el(context);
        }
        return c;
    }

    private String a(String str) {
        int i2;
        String str2;
        String str3 = null;
        if (e()) {
            try {
                String[] strArr = (String[]) ew.a(this.d, "getIpsByHostAsync", str);
                if (strArr == null || strArr.length <= 0) {
                    str2 = null;
                } else if (this.l == null) {
                    this.l = strArr;
                    str2 = strArr[0];
                } else if (a(strArr, this.l)) {
                    str2 = this.l[0];
                } else {
                    this.l = strArr;
                    str2 = strArr[0];
                }
                str3 = str2;
                i2 = 1;
            } catch (Throwable unused) {
                i2 = 0;
            }
            ey.a(this.e, "HttpDns", i2);
        }
        new Object[1][0] = "DnsManager ==> getIpAsync  host ： " + str + " ， ip ： " + str3;
        fa.a();
        return str3;
    }

    private static boolean a(String[] strArr, String[] strArr2) {
        if (strArr != null && strArr2 == null) {
            return false;
        }
        if (strArr == null && strArr2 != null) {
            return false;
        }
        if (strArr == null && strArr2 == null) {
            return true;
        }
        try {
            if (strArr.length != strArr2.length) {
                return false;
            }
            ArrayList arrayList = new ArrayList(12);
            ArrayList arrayList2 = new ArrayList(12);
            arrayList.addAll(Arrays.asList(strArr));
            arrayList2.addAll(Arrays.asList(strArr2));
            Collections.sort(arrayList);
            Collections.sort(arrayList2);
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (!((String) arrayList.get(i2)).equals(arrayList2.get(i2))) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void d() {
        c = null;
    }

    private boolean e() {
        return this.d != null && !f() && ez.b(this.e, ab.a.b, "dns_faile_count_total", 0) < 2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x003e A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean f() {
        /*
            r7 = this;
            r0 = 0
            r1 = 1
            r2 = -1
            r3 = 0
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x0034 }
            r5 = 14
            if (r4 < r5) goto L_0x000c
            r4 = 1
            goto L_0x000d
        L_0x000c:
            r4 = 0
        L_0x000d:
            if (r4 == 0) goto L_0x0027
            java.lang.String r4 = "http.proxyHost"
            java.lang.String r4 = java.lang.System.getProperty(r4)     // Catch:{ Throwable -> 0x0034 }
            java.lang.String r3 = "http.proxyPort"
            java.lang.String r3 = java.lang.System.getProperty(r3)     // Catch:{ Throwable -> 0x0025 }
            if (r3 == 0) goto L_0x001e
            goto L_0x0020
        L_0x001e:
            java.lang.String r3 = "-1"
        L_0x0020:
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ Throwable -> 0x0025 }
            goto L_0x003c
        L_0x0025:
            r3 = move-exception
            goto L_0x0038
        L_0x0027:
            android.content.Context r4 = r7.e     // Catch:{ Throwable -> 0x0034 }
            java.lang.String r4 = android.net.Proxy.getHost(r4)     // Catch:{ Throwable -> 0x0034 }
            android.content.Context r3 = r7.e     // Catch:{ Throwable -> 0x0025 }
            int r3 = android.net.Proxy.getPort(r3)     // Catch:{ Throwable -> 0x0025 }
            goto L_0x003c
        L_0x0034:
            r4 = move-exception
            r6 = r4
            r4 = r3
            r3 = r6
        L_0x0038:
            r3.printStackTrace()
            r3 = -1
        L_0x003c:
            if (r4 == 0) goto L_0x0041
            if (r3 == r2) goto L_0x0041
            return r1
        L_0x0041:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.el.f():boolean");
    }

    public final void a() {
        if (TextUtils.isEmpty(this.k)) {
            return;
        }
        if (TextUtils.isEmpty(this.j) || !this.k.equals(this.j)) {
            this.j = this.k;
            ez.a(this.e, IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, "last_ip", this.k);
        }
    }

    public final void a(eo eoVar) {
        try {
            this.g = false;
            if (e() && eoVar != null) {
                this.f6588a = eoVar;
                String c2 = eoVar.c();
                String host = new URL(c2).getHost();
                if ("http://abroad.apilocate.amap.com/mobile/binary".equals(c2)) {
                    return;
                }
                if (!"abroad.apilocate.amap.com".equals(host)) {
                    String str = "apilocate.amap.com".equalsIgnoreCase(host) ? "httpdns.apilocate.amap.com" : host;
                    String a2 = a(str);
                    if (this.h && TextUtils.isEmpty(a2)) {
                        this.h = false;
                        a2 = ez.b(this.e, IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, "last_ip", "");
                        if (!TextUtils.isEmpty(a2)) {
                            this.j = a2;
                        }
                    }
                    if (!TextUtils.isEmpty(a2)) {
                        this.k = a2;
                        eoVar.g = c2.replace(host, a2);
                        eoVar.a().put("host", str);
                        eoVar.a(str);
                        this.g = true;
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public final void b() {
        if (this.g) {
            ez.a(this.e, ab.a.b, "dns_faile_count_total", 0);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        com.loc.ez.a(r7.e, com.xiaomi.stat.ab.a.b, "dns_faile_count_total", 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003c, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0032 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void b(com.loc.eo r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = 0
            java.lang.String r2 = com.loc.es.a()     // Catch:{ Throwable -> 0x0032 }
            r8.g = r2     // Catch:{ Throwable -> 0x0032 }
            android.content.Context r2 = r7.e     // Catch:{ Throwable -> 0x0032 }
            java.lang.String r3 = "pref"
            java.lang.String r4 = "dns_faile_count_total"
            long r2 = com.loc.ez.b((android.content.Context) r2, (java.lang.String) r3, (java.lang.String) r4, (long) r0)     // Catch:{ Throwable -> 0x0032 }
            r4 = 2
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 < 0) goto L_0x001b
            monitor-exit(r7)
            return
        L_0x001b:
            com.loc.bg.a()     // Catch:{ Throwable -> 0x0032 }
            r4 = 0
            com.loc.bg.a(r8, r4)     // Catch:{ Throwable -> 0x0032 }
            r4 = 1
            long r2 = r2 + r4
            android.content.Context r8 = r7.e     // Catch:{ Throwable -> 0x0032 }
            java.lang.String r4 = "pref"
            java.lang.String r5 = "dns_faile_count_total"
            com.loc.ez.a((android.content.Context) r8, (java.lang.String) r4, (java.lang.String) r5, (long) r2)     // Catch:{ Throwable -> 0x0032 }
            monitor-exit(r7)
            return
        L_0x0030:
            r8 = move-exception
            goto L_0x003d
        L_0x0032:
            android.content.Context r8 = r7.e     // Catch:{ all -> 0x0030 }
            java.lang.String r2 = "pref"
            java.lang.String r3 = "dns_faile_count_total"
            com.loc.ez.a((android.content.Context) r8, (java.lang.String) r2, (java.lang.String) r3, (long) r0)     // Catch:{ all -> 0x0030 }
            monitor-exit(r7)
            return
        L_0x003d:
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.el.b(com.loc.eo):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0042 A[Catch:{ Throwable -> 0x0060 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c() {
        /*
            r4 = this;
            boolean r0 = r4.e()     // Catch:{ Throwable -> 0x0060 }
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            boolean r0 = r4.g     // Catch:{ Throwable -> 0x0060 }
            if (r0 == 0) goto L_0x0039
            java.lang.String[] r0 = r4.l     // Catch:{ Throwable -> 0x0060 }
            if (r0 == 0) goto L_0x0039
            java.lang.String[] r0 = r4.l     // Catch:{ Throwable -> 0x0060 }
            if (r0 == 0) goto L_0x0039
            int r1 = r0.length     // Catch:{ Throwable -> 0x0039 }
            r2 = 1
            if (r1 > r2) goto L_0x0018
            goto L_0x0039
        L_0x0018:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0039 }
            r2 = 12
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0039 }
            java.util.List r2 = java.util.Arrays.asList(r0)     // Catch:{ Throwable -> 0x0039 }
            r1.addAll(r2)     // Catch:{ Throwable -> 0x0039 }
            java.util.Iterator r2 = r1.iterator()     // Catch:{ Throwable -> 0x0039 }
            java.lang.Object r3 = r2.next()     // Catch:{ Throwable -> 0x0039 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Throwable -> 0x0039 }
            r2.remove()     // Catch:{ Throwable -> 0x0039 }
            r1.add(r3)     // Catch:{ Throwable -> 0x0039 }
            r1.toArray(r0)     // Catch:{ Throwable -> 0x0039 }
        L_0x0039:
            int r0 = r4.b     // Catch:{ Throwable -> 0x0060 }
            r1 = 5
            if (r0 > r1) goto L_0x0060
            boolean r0 = r4.g     // Catch:{ Throwable -> 0x0060 }
            if (r0 == 0) goto L_0x0060
            java.util.concurrent.ExecutorService r0 = r4.f     // Catch:{ Throwable -> 0x0060 }
            if (r0 != 0) goto L_0x004c
            java.util.concurrent.ExecutorService r0 = com.loc.aq.d()     // Catch:{ Throwable -> 0x0060 }
            r4.f = r0     // Catch:{ Throwable -> 0x0060 }
        L_0x004c:
            java.util.concurrent.ExecutorService r0 = r4.f     // Catch:{ Throwable -> 0x0060 }
            boolean r0 = r0.isShutdown()     // Catch:{ Throwable -> 0x0060 }
            if (r0 != 0) goto L_0x0060
            java.util.concurrent.ExecutorService r0 = r4.f     // Catch:{ Throwable -> 0x0060 }
            com.loc.el$a r1 = new com.loc.el$a     // Catch:{ Throwable -> 0x0060 }
            com.loc.eo r2 = r4.f6588a     // Catch:{ Throwable -> 0x0060 }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0060 }
            r0.submit(r1)     // Catch:{ Throwable -> 0x0060 }
        L_0x0060:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.el.c():void");
    }
}
