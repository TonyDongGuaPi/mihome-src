package com.tencent.smtt.sdk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

class o {

    /* renamed from: a  reason: collision with root package name */
    static boolean f9185a = false;
    private static o c = null;
    private static int f = 0;
    private static int g = 0;
    private static int h = 3;
    private static String j;
    private bh b = null;
    private boolean d = false;
    private boolean e = false;
    private File i = null;

    private o() {
    }

    public static o a(boolean z) {
        if (c == null && z) {
            synchronized (o.class) {
                if (c == null) {
                    c = new o();
                }
            }
        }
        return c;
    }

    static void a(int i2) {
        f = i2;
    }

    private void b(int i2) {
        String valueOf = String.valueOf(i2);
        Properties properties = new Properties();
        properties.setProperty(j, valueOf);
        try {
            properties.store(new FileOutputStream(new File(this.i, "count.prop")), (String) null);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public static int d() {
        return f;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x004d A[SYNTHETIC, Splitter:B:22:0x004d] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0058 A[SYNTHETIC, Splitter:B:28:0x0058] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int i() {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0047 }
            java.io.File r3 = r6.i     // Catch:{ Exception -> 0x0047 }
            java.lang.String r4 = "count.prop"
            r2.<init>(r3, r4)     // Catch:{ Exception -> 0x0047 }
            boolean r3 = r2.exists()     // Catch:{ Exception -> 0x0047 }
            if (r3 != 0) goto L_0x0012
            return r0
        L_0x0012:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0047 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0047 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0047 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0047 }
            java.util.Properties r1 = new java.util.Properties     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            r1.<init>()     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            r1.load(r2)     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            java.lang.String r3 = j     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            java.lang.String r4 = "1"
            java.lang.String r1 = r1.getProperty(r3, r4)     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            int r1 = r1.intValue()     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            r2.close()     // Catch:{ IOException -> 0x0038 }
            goto L_0x003c
        L_0x0038:
            r0 = move-exception
            r0.printStackTrace()
        L_0x003c:
            return r1
        L_0x003d:
            r0 = move-exception
            r1 = r2
            goto L_0x0056
        L_0x0040:
            r1 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
            goto L_0x0048
        L_0x0045:
            r0 = move-exception
            goto L_0x0056
        L_0x0047:
            r2 = move-exception
        L_0x0048:
            r2.printStackTrace()     // Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x0055
            r1.close()     // Catch:{ IOException -> 0x0051 }
            goto L_0x0055
        L_0x0051:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0055:
            return r0
        L_0x0056:
            if (r1 == 0) goto L_0x0060
            r1.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x0060
        L_0x005c:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0060:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.i():int");
    }

    public bh a() {
        if (this.d) {
            return this.b;
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x00fe A[Catch:{ Throwable -> 0x015b }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0104 A[Catch:{ Throwable -> 0x015b }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0107 A[Catch:{ Throwable -> 0x015b }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0110  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.content.Context r12, boolean r13, boolean r14) {
        /*
            r11 = this;
            monitor-enter(r11)
            r0 = 999(0x3e7, float:1.4E-42)
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x01e4 }
            r3 = 0
            com.tencent.smtt.utils.TbsLog.addLog(r0, r3, r2)     // Catch:{ all -> 0x01e4 }
            com.tencent.smtt.utils.TbsLog.initIfNeed(r12)     // Catch:{ all -> 0x01e4 }
            java.lang.String r0 = "SDKEngine"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x01e4 }
            r2.<init>()     // Catch:{ all -> 0x01e4 }
            java.lang.String r4 = "init -- context: "
            r2.append(r4)     // Catch:{ all -> 0x01e4 }
            r2.append(r12)     // Catch:{ all -> 0x01e4 }
            java.lang.String r4 = ", isPreIniting: "
            r2.append(r4)     // Catch:{ all -> 0x01e4 }
            r2.append(r14)     // Catch:{ all -> 0x01e4 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x01e4 }
            com.tencent.smtt.utils.TbsLog.i(r0, r2)     // Catch:{ all -> 0x01e4 }
            int r0 = g     // Catch:{ all -> 0x01e4 }
            r2 = 1
            int r0 = r0 + r2
            g = r0     // Catch:{ all -> 0x01e4 }
            com.tencent.smtt.sdk.TbsCoreLoadStat r0 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch:{ all -> 0x01e4 }
            r0.a()     // Catch:{ all -> 0x01e4 }
            com.tencent.smtt.sdk.am r0 = com.tencent.smtt.sdk.am.a()     // Catch:{ all -> 0x01e4 }
            int r4 = g     // Catch:{ all -> 0x01e4 }
            if (r4 != r2) goto L_0x0042
            r4 = 1
            goto L_0x0043
        L_0x0042:
            r4 = 0
        L_0x0043:
            r0.b((android.content.Context) r12, (boolean) r4)     // Catch:{ all -> 0x01e4 }
            com.tencent.smtt.sdk.am r0 = com.tencent.smtt.sdk.am.a()     // Catch:{ all -> 0x01e4 }
            r0.k(r12)     // Catch:{ all -> 0x01e4 }
            com.tencent.smtt.sdk.TbsShareManager.forceToLoadX5ForThirdApp(r12, r2)     // Catch:{ all -> 0x01e4 }
            boolean r13 = com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r12, (boolean) r13, (boolean) r14)     // Catch:{ all -> 0x01e4 }
            int r14 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x01e4 }
            r0 = 7
            if (r14 < r0) goto L_0x005b
            r14 = 1
            goto L_0x005c
        L_0x005b:
            r14 = 0
        L_0x005c:
            if (r13 == 0) goto L_0x0062
            if (r14 == 0) goto L_0x0062
            r0 = 1
            goto L_0x0063
        L_0x0062:
            r0 = 0
        L_0x0063:
            if (r0 == 0) goto L_0x0099
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x01e4 }
            com.tencent.smtt.sdk.am r0 = com.tencent.smtt.sdk.am.a()     // Catch:{ all -> 0x01e4 }
            int r6 = d()     // Catch:{ all -> 0x01e4 }
            boolean r0 = r0.c((android.content.Context) r12, (int) r6)     // Catch:{ all -> 0x01e4 }
            java.lang.String r6 = "SDKEngine"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x01e4 }
            r7.<init>()     // Catch:{ all -> 0x01e4 }
            java.lang.String r8 = "isTbsCoreLegal: "
            r7.append(r8)     // Catch:{ all -> 0x01e4 }
            r7.append(r0)     // Catch:{ all -> 0x01e4 }
            java.lang.String r8 = "; cost: "
            r7.append(r8)     // Catch:{ all -> 0x01e4 }
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x01e4 }
            r10 = 0
            long r8 = r8 - r4
            r7.append(r8)     // Catch:{ all -> 0x01e4 }
            java.lang.String r4 = r7.toString()     // Catch:{ all -> 0x01e4 }
            com.tencent.smtt.utils.TbsLog.i(r6, r4)     // Catch:{ all -> 0x01e4 }
        L_0x0099:
            if (r0 == 0) goto L_0x0192
            boolean r13 = r11.d     // Catch:{ all -> 0x01e4 }
            if (r13 == 0) goto L_0x00a1
            monitor-exit(r11)
            return
        L_0x00a1:
            boolean r13 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r12)     // Catch:{ Throwable -> 0x015b }
            if (r13 == 0) goto L_0x00dd
            r13 = 995(0x3e3, float:1.394E-42)
            java.lang.Object[] r14 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x015b }
            com.tencent.smtt.utils.TbsLog.addLog(r13, r3, r14)     // Catch:{ Throwable -> 0x015b }
            boolean r13 = com.tencent.smtt.sdk.TbsShareManager.j(r12)     // Catch:{ Throwable -> 0x015b }
            if (r13 == 0) goto L_0x00d4
            java.io.File r13 = new java.io.File     // Catch:{ Throwable -> 0x015b }
            java.lang.String r14 = com.tencent.smtt.sdk.TbsShareManager.c(r12)     // Catch:{ Throwable -> 0x015b }
            r13.<init>(r14)     // Catch:{ Throwable -> 0x015b }
            com.tencent.smtt.sdk.am r14 = com.tencent.smtt.sdk.am.a()     // Catch:{ Throwable -> 0x015b }
            java.io.File r14 = r14.q(r12)     // Catch:{ Throwable -> 0x015b }
            android.content.Context r0 = com.tencent.smtt.sdk.TbsShareManager.e(r12)     // Catch:{ Throwable -> 0x015b }
            if (r14 != 0) goto L_0x0111
            r11.d = r1     // Catch:{ Throwable -> 0x015b }
            java.lang.String r13 = "SDKEngine::useSystemWebView by error_tbs_core_dexopt_dir null!"
            com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r12, (java.lang.String) r13)     // Catch:{ Throwable -> 0x015b }
            monitor-exit(r11)
            return
        L_0x00d4:
            r11.d = r1     // Catch:{ Throwable -> 0x015b }
            java.lang.String r13 = "SDKEngine::useSystemWebView by error_host_unavailable"
            com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r12, (java.lang.String) r13)     // Catch:{ Throwable -> 0x015b }
            monitor-exit(r11)
            return
        L_0x00dd:
            r13 = 996(0x3e4, float:1.396E-42)
            java.lang.Object[] r14 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x015b }
            com.tencent.smtt.utils.TbsLog.addLog(r13, r3, r14)     // Catch:{ Throwable -> 0x015b }
            com.tencent.smtt.sdk.am r13 = com.tencent.smtt.sdk.am.a()     // Catch:{ Throwable -> 0x015b }
            java.io.File r13 = r13.q(r12)     // Catch:{ Throwable -> 0x015b }
            int r14 = f     // Catch:{ Throwable -> 0x015b }
            r0 = 25436(0x635c, float:3.5643E-41)
            if (r14 == r0) goto L_0x00fb
            int r14 = f     // Catch:{ Throwable -> 0x015b }
            r0 = 25437(0x635d, float:3.5645E-41)
            if (r14 != r0) goto L_0x00f9
            goto L_0x00fb
        L_0x00f9:
            r14 = 0
            goto L_0x00fc
        L_0x00fb:
            r14 = 1
        L_0x00fc:
            if (r14 == 0) goto L_0x0104
            android.content.Context r14 = r12.getApplicationContext()     // Catch:{ Throwable -> 0x015b }
            r0 = r14
            goto L_0x0105
        L_0x0104:
            r0 = r12
        L_0x0105:
            if (r13 != 0) goto L_0x0110
            r11.d = r1     // Catch:{ Throwable -> 0x015b }
            java.lang.String r13 = "SDKEngine::useSystemWebView by tbs_core_share_dir null!"
            com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r12, (java.lang.String) r13)     // Catch:{ Throwable -> 0x015b }
            monitor-exit(r11)
            return
        L_0x0110:
            r14 = r13
        L_0x0111:
            r5 = r0
            java.lang.String r0 = r13.getAbsolutePath()     // Catch:{ Throwable -> 0x015b }
            java.lang.String[] r8 = com.tencent.smtt.sdk.QbSdk.getDexLoaderFileList(r12, r5, r0)     // Catch:{ Throwable -> 0x015b }
            r0 = 0
        L_0x011b:
            int r3 = r8.length     // Catch:{ Throwable -> 0x015b }
            if (r0 >= r3) goto L_0x0121
            int r0 = r0 + 1
            goto L_0x011b
        L_0x0121:
            java.lang.String r0 = com.tencent.smtt.sdk.TbsShareManager.getHostCorePathAppDefined()     // Catch:{ Throwable -> 0x015b }
            if (r0 == 0) goto L_0x012d
            java.lang.String r14 = com.tencent.smtt.sdk.TbsShareManager.getHostCorePathAppDefined()     // Catch:{ Throwable -> 0x015b }
        L_0x012b:
            r7 = r14
            goto L_0x0132
        L_0x012d:
            java.lang.String r14 = r14.getAbsolutePath()     // Catch:{ Throwable -> 0x015b }
            goto L_0x012b
        L_0x0132:
            java.lang.String r14 = "SDKEngine"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x015b }
            r0.<init>()     // Catch:{ Throwable -> 0x015b }
            java.lang.String r3 = "SDKEngine init optDir is "
            r0.append(r3)     // Catch:{ Throwable -> 0x015b }
            r0.append(r7)     // Catch:{ Throwable -> 0x015b }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x015b }
            com.tencent.smtt.utils.TbsLog.i(r14, r0)     // Catch:{ Throwable -> 0x015b }
            com.tencent.smtt.sdk.bh r14 = new com.tencent.smtt.sdk.bh     // Catch:{ Throwable -> 0x015b }
            java.lang.String r6 = r13.getAbsolutePath()     // Catch:{ Throwable -> 0x015b }
            java.lang.String r9 = com.tencent.smtt.sdk.QbSdk.d     // Catch:{ Throwable -> 0x015b }
            r3 = r14
            r4 = r12
            r3.<init>(r4, r5, r6, r7, r8, r9)     // Catch:{ Throwable -> 0x015b }
            r11.b = r14     // Catch:{ Throwable -> 0x015b }
            r11.d = r2     // Catch:{ Throwable -> 0x015b }
            goto L_0x01da
        L_0x015b:
            r13 = move-exception
            java.lang.String r14 = "SDKEngine"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01e4 }
            r0.<init>()     // Catch:{ all -> 0x01e4 }
            java.lang.String r3 = "useSystemWebView by exception: "
            r0.append(r3)     // Catch:{ all -> 0x01e4 }
            r0.append(r13)     // Catch:{ all -> 0x01e4 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x01e4 }
            com.tencent.smtt.utils.TbsLog.e(r14, r0)     // Catch:{ all -> 0x01e4 }
            com.tencent.smtt.sdk.TbsCoreLoadStat r14 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch:{ all -> 0x01e4 }
            r0 = 327(0x147, float:4.58E-43)
            r14.a(r12, r0, r13)     // Catch:{ all -> 0x01e4 }
            r11.d = r1     // Catch:{ all -> 0x01e4 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x01e4 }
            r14.<init>()     // Catch:{ all -> 0x01e4 }
            java.lang.String r0 = "SDKEngine::useSystemWebView by exception: "
            r14.append(r0)     // Catch:{ all -> 0x01e4 }
            r14.append(r13)     // Catch:{ all -> 0x01e4 }
            java.lang.String r13 = r14.toString()     // Catch:{ all -> 0x01e4 }
            com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r12, (java.lang.String) r13)     // Catch:{ all -> 0x01e4 }
            goto L_0x01da
        L_0x0192:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01e4 }
            r0.<init>()     // Catch:{ all -> 0x01e4 }
            java.lang.String r3 = "can_load_x5="
            r0.append(r3)     // Catch:{ all -> 0x01e4 }
            r0.append(r13)     // Catch:{ all -> 0x01e4 }
            java.lang.String r13 = "; is_compatible="
            r0.append(r13)     // Catch:{ all -> 0x01e4 }
            r0.append(r14)     // Catch:{ all -> 0x01e4 }
            java.lang.String r13 = r0.toString()     // Catch:{ all -> 0x01e4 }
            java.lang.String r14 = "SDKEngine"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01e4 }
            r0.<init>()     // Catch:{ all -> 0x01e4 }
            java.lang.String r3 = "SDKEngine.init canLoadTbs=false; failure: "
            r0.append(r3)     // Catch:{ all -> 0x01e4 }
            r0.append(r13)     // Catch:{ all -> 0x01e4 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x01e4 }
            com.tencent.smtt.utils.TbsLog.e(r14, r0)     // Catch:{ all -> 0x01e4 }
            boolean r14 = com.tencent.smtt.sdk.QbSdk.f9080a     // Catch:{ all -> 0x01e4 }
            if (r14 == 0) goto L_0x01ca
            boolean r14 = r11.d     // Catch:{ all -> 0x01e4 }
            if (r14 == 0) goto L_0x01ca
            goto L_0x01da
        L_0x01ca:
            r11.d = r1     // Catch:{ all -> 0x01e4 }
            com.tencent.smtt.sdk.TbsCoreLoadStat r14 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch:{ all -> 0x01e4 }
            r0 = 405(0x195, float:5.68E-43)
            java.lang.Throwable r1 = new java.lang.Throwable     // Catch:{ all -> 0x01e4 }
            r1.<init>(r13)     // Catch:{ all -> 0x01e4 }
            r14.a(r12, r0, r1)     // Catch:{ all -> 0x01e4 }
        L_0x01da:
            java.io.File r12 = com.tencent.smtt.sdk.am.s(r12)     // Catch:{ all -> 0x01e4 }
            r11.i = r12     // Catch:{ all -> 0x01e4 }
            r11.e = r2     // Catch:{ all -> 0x01e4 }
            monitor-exit(r11)
            return
        L_0x01e4:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.o.a(android.content.Context, boolean, boolean):void");
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        j = str;
    }

    public boolean b() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public boolean b(boolean z) {
        f9185a = z;
        return z;
    }

    /* access modifiers changed from: package-private */
    public bh c() {
        return this.b;
    }

    public String e() {
        return (this.b == null || QbSdk.f9080a) ? "system webview get nothing..." : this.b.a();
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        if (f9185a) {
            if (j == null) {
                return false;
            }
            int i2 = i();
            if (i2 == 0) {
                b(1);
            } else {
                int i3 = i2 + 1;
                if (i3 > h) {
                    return false;
                }
                b(i3);
            }
        }
        return f9185a;
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return this.e;
    }

    public boolean h() {
        return QbSdk.useSoftWare();
    }
}
