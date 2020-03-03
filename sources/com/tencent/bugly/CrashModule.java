package com.tencent.bugly;

import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.c;

public class CrashModule extends a {
    public static final int MODULE_ID = 1004;
    private static int c = 0;
    private static boolean d = false;
    private static CrashModule e = new CrashModule();

    /* renamed from: a  reason: collision with root package name */
    private long f8979a;
    private BuglyStrategy.a b;

    public static CrashModule getInstance() {
        e.id = 1004;
        return e;
    }

    public static boolean hasInitialized() {
        return d;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0089, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0053  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void init(android.content.Context r12, boolean r13, com.tencent.bugly.BuglyStrategy r14) {
        /*
            r11 = this;
            monitor-enter(r11)
            if (r12 == 0) goto L_0x0088
            boolean r0 = d     // Catch:{ all -> 0x0085 }
            if (r0 == 0) goto L_0x0009
            goto L_0x0088
        L_0x0009:
            java.lang.String r0 = "Initializing crash module."
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x0085 }
            com.tencent.bugly.proguard.x.a(r0, r2)     // Catch:{ all -> 0x0085 }
            com.tencent.bugly.proguard.n r0 = com.tencent.bugly.proguard.n.a()     // Catch:{ all -> 0x0085 }
            int r2 = c     // Catch:{ all -> 0x0085 }
            r3 = 1
            int r2 = r2 + r3
            c = r2     // Catch:{ all -> 0x0085 }
            r4 = 1004(0x3ec, float:1.407E-42)
            r0.a((int) r4, (int) r2)     // Catch:{ all -> 0x0085 }
            d = r3     // Catch:{ all -> 0x0085 }
            com.tencent.bugly.crashreport.CrashReport.setContext(r12)     // Catch:{ all -> 0x0085 }
            r11.a(r12, r14)     // Catch:{ all -> 0x0085 }
            r5 = 1004(0x3ec, float:1.407E-42)
            com.tencent.bugly.BuglyStrategy$a r8 = r11.b     // Catch:{ all -> 0x0085 }
            r9 = 0
            r10 = 0
            r6 = r12
            r7 = r13
            com.tencent.bugly.crashreport.crash.c.a((int) r5, (android.content.Context) r6, (boolean) r7, (com.tencent.bugly.BuglyStrategy.a) r8, (com.tencent.bugly.proguard.o) r9, (java.lang.String) r10)     // Catch:{ all -> 0x0085 }
            com.tencent.bugly.crashreport.crash.c r13 = com.tencent.bugly.crashreport.crash.c.a()     // Catch:{ all -> 0x0085 }
            r13.e()     // Catch:{ all -> 0x0085 }
            if (r14 == 0) goto L_0x004e
            boolean r0 = r14.isEnableNativeCrashMonitor()     // Catch:{ all -> 0x0085 }
            if (r0 == 0) goto L_0x0043
            goto L_0x004e
        L_0x0043:
            java.lang.String r0 = "[crash] Closed native crash monitor!"
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x0085 }
            com.tencent.bugly.proguard.x.a(r0, r2)     // Catch:{ all -> 0x0085 }
            r13.f()     // Catch:{ all -> 0x0085 }
            goto L_0x0051
        L_0x004e:
            r13.g()     // Catch:{ all -> 0x0085 }
        L_0x0051:
            if (r14 == 0) goto L_0x0065
            boolean r14 = r14.isEnableANRCrashMonitor()     // Catch:{ all -> 0x0085 }
            if (r14 == 0) goto L_0x005a
            goto L_0x0065
        L_0x005a:
            java.lang.String r14 = "[crash] Closed ANR monitor!"
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ all -> 0x0085 }
            com.tencent.bugly.proguard.x.a(r14, r0)     // Catch:{ all -> 0x0085 }
            r13.i()     // Catch:{ all -> 0x0085 }
            goto L_0x0068
        L_0x0065:
            r13.h()     // Catch:{ all -> 0x0085 }
        L_0x0068:
            com.tencent.bugly.crashreport.crash.d.a((android.content.Context) r12)     // Catch:{ all -> 0x0085 }
            com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier r13 = com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier.getInstance()     // Catch:{ all -> 0x0085 }
            java.lang.String r14 = "android.net.conn.CONNECTIVITY_CHANGE"
            r13.addFilter(r14)     // Catch:{ all -> 0x0085 }
            r13.register(r12)     // Catch:{ all -> 0x0085 }
            com.tencent.bugly.proguard.n r12 = com.tencent.bugly.proguard.n.a()     // Catch:{ all -> 0x0085 }
            int r13 = c     // Catch:{ all -> 0x0085 }
            int r13 = r13 - r3
            c = r13     // Catch:{ all -> 0x0085 }
            r12.a((int) r4, (int) r13)     // Catch:{ all -> 0x0085 }
            monitor-exit(r11)
            return
        L_0x0085:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        L_0x0088:
            monitor-exit(r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.CrashModule.init(android.content.Context, boolean, com.tencent.bugly.BuglyStrategy):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0053, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(android.content.Context r7, com.tencent.bugly.BuglyStrategy r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r8 != 0) goto L_0x0005
            monitor-exit(r6)
            return
        L_0x0005:
            java.lang.String r0 = r8.getLibBuglySOFilePath()     // Catch:{ all -> 0x0054 }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0054 }
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0020
            com.tencent.bugly.crashreport.common.info.a r7 = com.tencent.bugly.crashreport.common.info.a.a((android.content.Context) r7)     // Catch:{ all -> 0x0054 }
            r7.m = r0     // Catch:{ all -> 0x0054 }
            java.lang.String r7 = "setted libBugly.so file path :%s"
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0054 }
            r1[r3] = r0     // Catch:{ all -> 0x0054 }
            com.tencent.bugly.proguard.x.a(r7, r1)     // Catch:{ all -> 0x0054 }
        L_0x0020:
            com.tencent.bugly.BuglyStrategy$a r7 = r8.getCrashHandleCallback()     // Catch:{ all -> 0x0054 }
            if (r7 == 0) goto L_0x0033
            com.tencent.bugly.BuglyStrategy$a r7 = r8.getCrashHandleCallback()     // Catch:{ all -> 0x0054 }
            r6.b = r7     // Catch:{ all -> 0x0054 }
            java.lang.String r7 = "setted CrashHanldeCallback"
            java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch:{ all -> 0x0054 }
            com.tencent.bugly.proguard.x.a(r7, r0)     // Catch:{ all -> 0x0054 }
        L_0x0033:
            long r0 = r8.getAppReportDelay()     // Catch:{ all -> 0x0054 }
            r4 = 0
            int r7 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r7 <= 0) goto L_0x0052
            long r7 = r8.getAppReportDelay()     // Catch:{ all -> 0x0054 }
            r6.f8979a = r7     // Catch:{ all -> 0x0054 }
            java.lang.String r7 = "setted delay: %d"
            java.lang.Object[] r8 = new java.lang.Object[r2]     // Catch:{ all -> 0x0054 }
            long r0 = r6.f8979a     // Catch:{ all -> 0x0054 }
            java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch:{ all -> 0x0054 }
            r8[r3] = r0     // Catch:{ all -> 0x0054 }
            com.tencent.bugly.proguard.x.a(r7, r8)     // Catch:{ all -> 0x0054 }
        L_0x0052:
            monitor-exit(r6)
            return
        L_0x0054:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.CrashModule.a(android.content.Context, com.tencent.bugly.BuglyStrategy):void");
    }

    public void onServerStrategyChanged(StrategyBean strategyBean) {
        c a2;
        if (strategyBean != null && (a2 = c.a()) != null) {
            a2.a(strategyBean);
        }
    }

    public String[] getTables() {
        return new String[]{"t_cr"};
    }
}
