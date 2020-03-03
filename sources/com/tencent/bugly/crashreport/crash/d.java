package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.Map;

public final class d {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static d f9016a;
    private a b;
    private com.tencent.bugly.crashreport.common.info.a c;
    private b d;
    private Context e;

    static /* synthetic */ void a(d dVar) {
        x.c("[ExtraCrashManager] Trying to notify Bugly agents.", new Object[0]);
        try {
            Class<?> cls = Class.forName("com.tencent.bugly.agent.GameAgent");
            String str = "com.tencent.bugly";
            dVar.c.getClass();
            if (!"".equals("")) {
                str = str + "." + "";
            }
            z.a(cls, "sdkPackageName", (Object) str, (Object) null);
            x.c("[ExtraCrashManager] Bugly game agent has been notified.", new Object[0]);
        } catch (Throwable unused) {
            x.a("[ExtraCrashManager] no game agent", new Object[0]);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0248 A[Catch:{ Throwable -> 0x0241, all -> 0x023f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(com.tencent.bugly.crashreport.crash.d r9, java.lang.Thread r10, int r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, java.util.Map r15) {
        /*
            r0 = 1
            r1 = 0
            switch(r11) {
                case 4: goto L_0x0019;
                case 5: goto L_0x0016;
                case 6: goto L_0x0016;
                case 7: goto L_0x0005;
                case 8: goto L_0x0013;
                default: goto L_0x0005;
            }
        L_0x0005:
            java.lang.String r9 = "[ExtraCrashManager] Unknown extra crash type: %d"
            java.lang.Object[] r10 = new java.lang.Object[r0]
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r10[r1] = r11
            com.tencent.bugly.proguard.x.d(r9, r10)
            return
        L_0x0013:
            java.lang.String r2 = "H5"
            goto L_0x001b
        L_0x0016:
            java.lang.String r2 = "Cocos"
            goto L_0x001b
        L_0x0019:
            java.lang.String r2 = "Unity"
        L_0x001b:
            r3 = r2
            java.lang.String r2 = "[ExtraCrashManager] %s Crash Happen"
            java.lang.Object[] r4 = new java.lang.Object[r0]
            r4[r1] = r3
            com.tencent.bugly.proguard.x.e(r2, r4)
            com.tencent.bugly.crashreport.common.strategy.a r2 = r9.b     // Catch:{ Throwable -> 0x0241 }
            boolean r2 = r2.b()     // Catch:{ Throwable -> 0x0241 }
            if (r2 != 0) goto L_0x0048
            java.lang.String r2 = "waiting for remote sync"
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.proguard.x.e(r2, r4)     // Catch:{ Throwable -> 0x0241 }
            r2 = 0
        L_0x0035:
            com.tencent.bugly.crashreport.common.strategy.a r4 = r9.b     // Catch:{ Throwable -> 0x0241 }
            boolean r4 = r4.b()     // Catch:{ Throwable -> 0x0241 }
            if (r4 != 0) goto L_0x0048
            r4 = 500(0x1f4, double:2.47E-321)
            com.tencent.bugly.proguard.z.b((long) r4)     // Catch:{ Throwable -> 0x0241 }
            int r2 = r2 + 500
            r4 = 3000(0xbb8, float:4.204E-42)
            if (r2 < r4) goto L_0x0035
        L_0x0048:
            com.tencent.bugly.crashreport.common.strategy.a r2 = r9.b     // Catch:{ Throwable -> 0x0241 }
            boolean r2 = r2.b()     // Catch:{ Throwable -> 0x0241 }
            if (r2 != 0) goto L_0x0057
            java.lang.String r2 = "[ExtraCrashManager] There is no remote strategy, but still store it."
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.proguard.x.d(r2, r4)     // Catch:{ Throwable -> 0x0241 }
        L_0x0057:
            com.tencent.bugly.crashreport.common.strategy.a r2 = r9.b     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r2 = r2.c()     // Catch:{ Throwable -> 0x0241 }
            boolean r4 = r2.g     // Catch:{ Throwable -> 0x0241 }
            if (r4 != 0) goto L_0x00a1
            com.tencent.bugly.crashreport.common.strategy.a r4 = r9.b     // Catch:{ Throwable -> 0x0241 }
            boolean r4 = r4.b()     // Catch:{ Throwable -> 0x0241 }
            if (r4 == 0) goto L_0x00a1
            java.lang.String r11 = "[ExtraCrashManager] Crash report was closed by remote , will not upload to Bugly , print local for helpful!"
            java.lang.Object[] r15 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.proguard.x.e(r11, r15)     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r4 = com.tencent.bugly.proguard.z.a()     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r9 = r9.c     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r5 = r9.d     // Catch:{ Throwable -> 0x0241 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0241 }
            r9.<init>()     // Catch:{ Throwable -> 0x0241 }
            r9.append(r12)     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r11 = "\n"
            r9.append(r11)     // Catch:{ Throwable -> 0x0241 }
            r9.append(r13)     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r11 = "\n"
            r9.append(r11)     // Catch:{ Throwable -> 0x0241 }
            r9.append(r14)     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r7 = r9.toString()     // Catch:{ Throwable -> 0x0241 }
            r8 = 0
            r6 = r10
            com.tencent.bugly.crashreport.crash.b.a(r3, r4, r5, r6, r7, r8)     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r9 = "[ExtraCrashManager] Successfully handled."
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
            return
        L_0x00a1:
            switch(r11) {
                case 4: goto L_0x00cf;
                case 5: goto L_0x00ba;
                case 6: goto L_0x00ba;
                case 7: goto L_0x00a4;
                case 8: goto L_0x00a5;
                default: goto L_0x00a4;
            }
        L_0x00a4:
            goto L_0x00cf
        L_0x00a5:
            boolean r2 = r2.m     // Catch:{ Throwable -> 0x0241 }
            if (r2 != 0) goto L_0x00cf
            java.lang.String r9 = "[ExtraCrashManager] %s report is disabled."
            java.lang.Object[] r10 = new java.lang.Object[r0]     // Catch:{ Throwable -> 0x0241 }
            r10[r1] = r3     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.proguard.x.e(r9, r10)     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r9 = "[ExtraCrashManager] Successfully handled."
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
            return
        L_0x00ba:
            boolean r2 = r2.l     // Catch:{ Throwable -> 0x0241 }
            if (r2 != 0) goto L_0x00cf
            java.lang.String r9 = "[ExtraCrashManager] %s report is disabled."
            java.lang.Object[] r10 = new java.lang.Object[r0]     // Catch:{ Throwable -> 0x0241 }
            r10[r1] = r3     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.proguard.x.e(r9, r10)     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r9 = "[ExtraCrashManager] Successfully handled."
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
            return
        L_0x00cf:
            r0 = 8
            if (r11 != r0) goto L_0x00d4
            r11 = 5
        L_0x00d4:
            com.tencent.bugly.crashreport.crash.CrashDetailBean r0 = new com.tencent.bugly.crashreport.crash.CrashDetailBean     // Catch:{ Throwable -> 0x0241 }
            r0.<init>()     // Catch:{ Throwable -> 0x0241 }
            long r4 = com.tencent.bugly.crashreport.common.info.b.g()     // Catch:{ Throwable -> 0x0241 }
            r0.B = r4     // Catch:{ Throwable -> 0x0241 }
            long r4 = com.tencent.bugly.crashreport.common.info.b.e()     // Catch:{ Throwable -> 0x0241 }
            r0.C = r4     // Catch:{ Throwable -> 0x0241 }
            long r4 = com.tencent.bugly.crashreport.common.info.b.i()     // Catch:{ Throwable -> 0x0241 }
            r0.D = r4     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r2 = r9.c     // Catch:{ Throwable -> 0x0241 }
            long r4 = r2.p()     // Catch:{ Throwable -> 0x0241 }
            r0.E = r4     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r2 = r9.c     // Catch:{ Throwable -> 0x0241 }
            long r4 = r2.o()     // Catch:{ Throwable -> 0x0241 }
            r0.F = r4     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r2 = r9.c     // Catch:{ Throwable -> 0x0241 }
            long r4 = r2.q()     // Catch:{ Throwable -> 0x0241 }
            r0.G = r4     // Catch:{ Throwable -> 0x0241 }
            android.content.Context r2 = r9.e     // Catch:{ Throwable -> 0x0241 }
            int r4 = com.tencent.bugly.crashreport.crash.c.e     // Catch:{ Throwable -> 0x0241 }
            r5 = 0
            java.lang.String r2 = com.tencent.bugly.proguard.z.a((android.content.Context) r2, (int) r4, (java.lang.String) r5)     // Catch:{ Throwable -> 0x0241 }
            r0.w = r2     // Catch:{ Throwable -> 0x0241 }
            r0.b = r11     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r11 = r9.c     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r11 = r11.h()     // Catch:{ Throwable -> 0x0241 }
            r0.e = r11     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r11 = r9.c     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r11 = r11.j     // Catch:{ Throwable -> 0x0241 }
            r0.f = r11     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r11 = r9.c     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r11 = r11.w()     // Catch:{ Throwable -> 0x0241 }
            r0.g = r11     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r11 = r9.c     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r11 = r11.g()     // Catch:{ Throwable -> 0x0241 }
            r0.m = r11     // Catch:{ Throwable -> 0x0241 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0241 }
            r11.<init>()     // Catch:{ Throwable -> 0x0241 }
            r11.append(r12)     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r11 = r11.toString()     // Catch:{ Throwable -> 0x0241 }
            r0.n = r11     // Catch:{ Throwable -> 0x0241 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0241 }
            r11.<init>()     // Catch:{ Throwable -> 0x0241 }
            r11.append(r13)     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r11 = r11.toString()     // Catch:{ Throwable -> 0x0241 }
            r0.o = r11     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r11 = ""
            if (r14 == 0) goto L_0x015b
            java.lang.String r2 = "\n"
            java.lang.String[] r2 = r14.split(r2)     // Catch:{ Throwable -> 0x0241 }
            int r4 = r2.length     // Catch:{ Throwable -> 0x0241 }
            if (r4 <= 0) goto L_0x0159
            r11 = r2[r1]     // Catch:{ Throwable -> 0x0241 }
        L_0x0159:
            r2 = r14
            goto L_0x015d
        L_0x015b:
            java.lang.String r2 = ""
        L_0x015d:
            r0.p = r11     // Catch:{ Throwable -> 0x0241 }
            r0.q = r2     // Catch:{ Throwable -> 0x0241 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0241 }
            r0.r = r4     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r11 = r0.q     // Catch:{ Throwable -> 0x0241 }
            byte[] r11 = r11.getBytes()     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r11 = com.tencent.bugly.proguard.z.b((byte[]) r11)     // Catch:{ Throwable -> 0x0241 }
            r0.u = r11     // Catch:{ Throwable -> 0x0241 }
            int r11 = com.tencent.bugly.crashreport.crash.c.f     // Catch:{ Throwable -> 0x0241 }
            java.util.Map r11 = com.tencent.bugly.proguard.z.a((int) r11, (boolean) r1)     // Catch:{ Throwable -> 0x0241 }
            r0.y = r11     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r11 = r9.c     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r11 = r11.d     // Catch:{ Throwable -> 0x0241 }
            r0.z = r11     // Catch:{ Throwable -> 0x0241 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0241 }
            r11.<init>()     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r2 = r10.getName()     // Catch:{ Throwable -> 0x0241 }
            r11.append(r2)     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r2 = "("
            r11.append(r2)     // Catch:{ Throwable -> 0x0241 }
            long r4 = r10.getId()     // Catch:{ Throwable -> 0x0241 }
            r11.append(r4)     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r2 = ")"
            r11.append(r2)     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r11 = r11.toString()     // Catch:{ Throwable -> 0x0241 }
            r0.A = r11     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r11 = r9.c     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r11 = r11.y()     // Catch:{ Throwable -> 0x0241 }
            r0.H = r11     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r11 = r9.c     // Catch:{ Throwable -> 0x0241 }
            java.util.Map r11 = r11.v()     // Catch:{ Throwable -> 0x0241 }
            r0.h = r11     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r11 = r9.c     // Catch:{ Throwable -> 0x0241 }
            long r4 = r11.f8995a     // Catch:{ Throwable -> 0x0241 }
            r0.L = r4     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r11 = r9.c     // Catch:{ Throwable -> 0x0241 }
            boolean r11 = r11.a()     // Catch:{ Throwable -> 0x0241 }
            r0.M = r11     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r11 = r9.c     // Catch:{ Throwable -> 0x0241 }
            int r11 = r11.F()     // Catch:{ Throwable -> 0x0241 }
            r0.O = r11     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r11 = r9.c     // Catch:{ Throwable -> 0x0241 }
            int r11 = r11.G()     // Catch:{ Throwable -> 0x0241 }
            r0.P = r11     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r11 = r9.c     // Catch:{ Throwable -> 0x0241 }
            java.util.Map r11 = r11.z()     // Catch:{ Throwable -> 0x0241 }
            r0.Q = r11     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r11 = r9.c     // Catch:{ Throwable -> 0x0241 }
            java.util.Map r11 = r11.E()     // Catch:{ Throwable -> 0x0241 }
            r0.R = r11     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.crash.b r11 = r9.d     // Catch:{ Throwable -> 0x0241 }
            r11.c((com.tencent.bugly.crashreport.crash.CrashDetailBean) r0)     // Catch:{ Throwable -> 0x0241 }
            byte[] r11 = com.tencent.bugly.proguard.y.a()     // Catch:{ Throwable -> 0x0241 }
            r0.x = r11     // Catch:{ Throwable -> 0x0241 }
            java.util.Map<java.lang.String, java.lang.String> r11 = r0.N     // Catch:{ Throwable -> 0x0241 }
            if (r11 != 0) goto L_0x01f8
            java.util.LinkedHashMap r11 = new java.util.LinkedHashMap     // Catch:{ Throwable -> 0x0241 }
            r11.<init>()     // Catch:{ Throwable -> 0x0241 }
            r0.N = r11     // Catch:{ Throwable -> 0x0241 }
        L_0x01f8:
            if (r15 == 0) goto L_0x01ff
            java.util.Map<java.lang.String, java.lang.String> r11 = r0.N     // Catch:{ Throwable -> 0x0241 }
            r11.putAll(r15)     // Catch:{ Throwable -> 0x0241 }
        L_0x01ff:
            java.lang.String r4 = com.tencent.bugly.proguard.z.a()     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.common.info.a r11 = r9.c     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r5 = r11.d     // Catch:{ Throwable -> 0x0241 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0241 }
            r11.<init>()     // Catch:{ Throwable -> 0x0241 }
            r11.append(r12)     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r12 = "\n"
            r11.append(r12)     // Catch:{ Throwable -> 0x0241 }
            r11.append(r13)     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r12 = "\n"
            r11.append(r12)     // Catch:{ Throwable -> 0x0241 }
            r11.append(r14)     // Catch:{ Throwable -> 0x0241 }
            java.lang.String r7 = r11.toString()     // Catch:{ Throwable -> 0x0241 }
            r6 = r10
            r8 = r0
            com.tencent.bugly.crashreport.crash.b.a(r3, r4, r5, r6, r7, r8)     // Catch:{ Throwable -> 0x0241 }
            com.tencent.bugly.crashreport.crash.b r10 = r9.d     // Catch:{ Throwable -> 0x0241 }
            boolean r10 = r10.a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r0)     // Catch:{ Throwable -> 0x0241 }
            if (r10 != 0) goto L_0x0237
            com.tencent.bugly.crashreport.crash.b r9 = r9.d     // Catch:{ Throwable -> 0x0241 }
            r10 = 3000(0xbb8, double:1.482E-320)
            r9.a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r0, (long) r10, (boolean) r1)     // Catch:{ Throwable -> 0x0241 }
        L_0x0237:
            java.lang.String r9 = "[ExtraCrashManager] Successfully handled."
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
            return
        L_0x023f:
            r9 = move-exception
            goto L_0x0253
        L_0x0241:
            r9 = move-exception
            boolean r10 = com.tencent.bugly.proguard.x.a(r9)     // Catch:{ all -> 0x023f }
            if (r10 != 0) goto L_0x024b
            r9.printStackTrace()     // Catch:{ all -> 0x023f }
        L_0x024b:
            java.lang.String r9 = "[ExtraCrashManager] Successfully handled."
            java.lang.Object[] r10 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.e(r9, r10)
            return
        L_0x0253:
            java.lang.Object[] r10 = new java.lang.Object[r1]
            java.lang.String r11 = "[ExtraCrashManager] Successfully handled."
            com.tencent.bugly.proguard.x.e(r11, r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.d.a(com.tencent.bugly.crashreport.crash.d, java.lang.Thread, int, java.lang.String, java.lang.String, java.lang.String, java.util.Map):void");
    }

    private d(Context context) {
        c a2 = c.a();
        if (a2 != null) {
            this.b = a.a();
            this.c = com.tencent.bugly.crashreport.common.info.a.a(context);
            this.d = a2.o;
            this.e = context;
            w.a().a(new Runnable() {
                public final void run() {
                    d.a(d.this);
                }
            });
        }
    }

    public static d a(Context context) {
        if (f9016a == null) {
            f9016a = new d(context);
        }
        return f9016a;
    }

    public static void a(Thread thread, int i, String str, String str2, String str3, Map<String, String> map) {
        final Thread thread2 = thread;
        final int i2 = i;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final Map<String, String> map2 = map;
        w.a().a(new Runnable() {
            public final void run() {
                try {
                    if (d.f9016a == null) {
                        x.e("[ExtraCrashManager] Extra crash manager has not been initialized.", new Object[0]);
                    } else {
                        d.a(d.f9016a, thread2, i2, str4, str5, str6, map2);
                    }
                } catch (Throwable th) {
                    if (!x.b(th)) {
                        th.printStackTrace();
                    }
                    x.e("[ExtraCrashManager] Crash error %s %s %s", str4, str5, str6);
                }
            }
        });
    }
}
