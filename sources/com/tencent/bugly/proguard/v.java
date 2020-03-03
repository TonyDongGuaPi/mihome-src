package com.tencent.bugly.proguard;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.a;
import java.util.Map;
import java.util.UUID;

public final class v implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private int f9060a;
    private int b;
    private final Context c;
    private final int d;
    private final byte[] e;
    private final a f;
    private final com.tencent.bugly.crashreport.common.strategy.a g;
    private final s h;
    private final u i;
    private final int j;
    private final t k;
    private final t l;
    private String m;
    private final String n;
    private final Map<String, String> o;
    private int p;
    private long q;
    private long r;
    private boolean s;
    private boolean t;

    public v(Context context, int i2, int i3, byte[] bArr, String str, String str2, t tVar, boolean z, boolean z2) {
        this(context, i2, i3, bArr, str, str2, tVar, z, 2, 30000, z2, (Map<String, String>) null);
    }

    public v(Context context, int i2, int i3, byte[] bArr, String str, String str2, t tVar, boolean z, int i4, int i5, boolean z2, Map<String, String> map) {
        int i6 = i4;
        int i7 = i5;
        this.f9060a = 2;
        this.b = 30000;
        this.m = null;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = true;
        this.t = false;
        this.c = context;
        this.f = a.a(context);
        this.e = bArr;
        this.g = com.tencent.bugly.crashreport.common.strategy.a.a();
        this.h = s.a(context);
        this.i = u.a();
        this.j = i2;
        this.m = str;
        this.n = str2;
        this.k = tVar;
        u uVar = this.i;
        this.l = null;
        this.s = z;
        this.d = i3;
        if (i6 > 0) {
            this.f9060a = i6;
        }
        if (i7 > 0) {
            this.b = i7;
        }
        this.t = z2;
        this.o = map;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.tencent.bugly.proguard.an r5, boolean r6, int r7, java.lang.String r8, int r9) {
        /*
            r4 = this;
            int r5 = r4.d
            r0 = 630(0x276, float:8.83E-43)
            if (r5 == r0) goto L_0x001c
            r0 = 640(0x280, float:8.97E-43)
            if (r5 == r0) goto L_0x0019
            r0 = 830(0x33e, float:1.163E-42)
            if (r5 == r0) goto L_0x001c
            r0 = 840(0x348, float:1.177E-42)
            if (r5 == r0) goto L_0x0019
            int r5 = r4.d
            java.lang.String r5 = java.lang.String.valueOf(r5)
            goto L_0x001e
        L_0x0019:
            java.lang.String r5 = "userinfo"
            goto L_0x001e
        L_0x001c:
            java.lang.String r5 = "crash"
        L_0x001e:
            r0 = 0
            r1 = 1
            if (r6 == 0) goto L_0x002c
            java.lang.String r7 = "[Upload] Success: %s"
            java.lang.Object[] r8 = new java.lang.Object[r1]
            r8[r0] = r5
            com.tencent.bugly.proguard.x.a(r7, r8)
            goto L_0x0049
        L_0x002c:
            java.lang.String r2 = "[Upload] Failed to upload(%d) %s: %s"
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r3[r0] = r7
            r3[r1] = r5
            r5 = 2
            r3[r5] = r8
            com.tencent.bugly.proguard.x.e(r2, r3)
            boolean r5 = r4.s
            if (r5 == 0) goto L_0x0049
            com.tencent.bugly.proguard.u r5 = r4.i
            r7 = 0
            r5.a((int) r9, (com.tencent.bugly.proguard.an) r7)
        L_0x0049:
            long r7 = r4.q
            long r0 = r4.r
            long r7 = r7 + r0
            r0 = 0
            int r5 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r5 <= 0) goto L_0x0069
            com.tencent.bugly.proguard.u r5 = r4.i
            boolean r7 = r4.t
            long r7 = r5.a((boolean) r7)
            long r0 = r4.q
            long r7 = r7 + r0
            long r0 = r4.r
            long r7 = r7 + r0
            com.tencent.bugly.proguard.u r5 = r4.i
            boolean r9 = r4.t
            r5.a((long) r7, (boolean) r9)
        L_0x0069:
            com.tencent.bugly.proguard.t r5 = r4.k
            if (r5 == 0) goto L_0x0078
            com.tencent.bugly.proguard.t r5 = r4.k
            int r7 = r4.d
            long r7 = r4.q
            long r7 = r4.r
            r5.a(r6)
        L_0x0078:
            com.tencent.bugly.proguard.t r5 = r4.l
            if (r5 == 0) goto L_0x0087
            com.tencent.bugly.proguard.t r5 = r4.l
            int r7 = r4.d
            long r7 = r4.q
            long r7 = r4.r
            r5.a(r6)
        L_0x0087:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.v.a(com.tencent.bugly.proguard.an, boolean, int, java.lang.String, int):void");
    }

    private static boolean a(an anVar, a aVar, com.tencent.bugly.crashreport.common.strategy.a aVar2) {
        if (anVar == null) {
            x.d("resp == null!", new Object[0]);
            return false;
        } else if (anVar.f9036a != 0) {
            x.e("resp result error %d", Byte.valueOf(anVar.f9036a));
            return false;
        } else {
            try {
                if (!z.a(anVar.d) && !a.b().i().equals(anVar.d)) {
                    p.a().a(com.tencent.bugly.crashreport.common.strategy.a.f8998a, "key_ip", anVar.d.getBytes("UTF-8"), (o) null, true);
                    aVar.d(anVar.d);
                }
                if (!z.a(anVar.f) && !a.b().j().equals(anVar.f)) {
                    p.a().a(com.tencent.bugly.crashreport.common.strategy.a.f8998a, "key_imei", anVar.f.getBytes("UTF-8"), (o) null, true);
                    aVar.e(anVar.f);
                }
            } catch (Throwable th) {
                x.a(th);
            }
            aVar.i = anVar.e;
            if (anVar.b == 510) {
                if (anVar.c == null) {
                    x.e("[Upload] Strategy data is null. Response cmd: %d", Integer.valueOf(anVar.b));
                    return false;
                }
                ap apVar = (ap) a.a(anVar.c, ap.class);
                if (apVar == null) {
                    x.e("[Upload] Failed to decode strategy from server. Response cmd: %d", Integer.valueOf(anVar.b));
                    return false;
                }
                aVar2.a(apVar);
            }
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:102:0x02e8, code lost:
        if ((r7.q + r7.r) <= 0) goto L_0x0301;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x02ea, code lost:
        r7.i.a((r7.i.a(r7.t) + r7.q) + r7.r, r7.t);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0301, code lost:
        r7.i.a(r13, (com.tencent.bugly.proguard.an) null);
        com.tencent.bugly.proguard.x.a("[Upload] Session ID is invalid, will try again immediately (pid=%d | tid=%d).", java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
        r7.i.a(r7.j, r7.d, r7.e, r7.m, r7.n, r7.k, r7.f9060a, r7.b, true, r7.o);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0343, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0344, code lost:
        a((com.tencent.bugly.proguard.an) null, false, 1, "status of server is " + r13, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x035b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x035c, code lost:
        r11 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x02da, code lost:
        if (r13 == 0) goto L_0x035c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x02dc, code lost:
        if (r13 != 2) goto L_0x0344;
     */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0245 A[Catch:{ Throwable -> 0x035e, Throwable -> 0x0477 }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x02ad  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r21 = this;
            r7 = r21
            r0 = 0
            r7.p = r0     // Catch:{ Throwable -> 0x0477 }
            r1 = 0
            r7.q = r1     // Catch:{ Throwable -> 0x0477 }
            r7.r = r1     // Catch:{ Throwable -> 0x0477 }
            byte[] r3 = r7.e     // Catch:{ Throwable -> 0x0477 }
            android.content.Context r4 = r7.c     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r4 = com.tencent.bugly.crashreport.common.info.b.e(r4)     // Catch:{ Throwable -> 0x0477 }
            if (r4 != 0) goto L_0x0021
            r2 = 0
            r3 = 0
            r4 = 0
            java.lang.String r5 = "network is not available"
            r6 = 0
            r1 = r21
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0477 }
            return
        L_0x0021:
            if (r3 == 0) goto L_0x046b
            int r4 = r3.length     // Catch:{ Throwable -> 0x0477 }
            if (r4 != 0) goto L_0x0028
            goto L_0x046b
        L_0x0028:
            com.tencent.bugly.proguard.u r4 = r7.i     // Catch:{ Throwable -> 0x0477 }
            boolean r5 = r7.t     // Catch:{ Throwable -> 0x0477 }
            long r4 = r4.a((boolean) r5)     // Catch:{ Throwable -> 0x0477 }
            int r6 = r3.length     // Catch:{ Throwable -> 0x0477 }
            long r8 = (long) r6     // Catch:{ Throwable -> 0x0477 }
            long r8 = r8 + r4
            r10 = 2097152(0x200000, double:1.0361308E-317)
            int r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            r8 = 2
            r9 = 1
            if (r6 < 0) goto L_0x006e
            java.lang.String r1 = "[Upload] Upload too much data, try next time: %d/%d"
            java.lang.Object[] r2 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x0477 }
            java.lang.Long r3 = java.lang.Long.valueOf(r4)     // Catch:{ Throwable -> 0x0477 }
            r2[r0] = r3     // Catch:{ Throwable -> 0x0477 }
            java.lang.Long r0 = java.lang.Long.valueOf(r10)     // Catch:{ Throwable -> 0x0477 }
            r2[r9] = r0     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.e(r1, r2)     // Catch:{ Throwable -> 0x0477 }
            r2 = 0
            r3 = 0
            r4 = 0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r1 = "over net consume: "
            r0.<init>(r1)     // Catch:{ Throwable -> 0x0477 }
            r5 = 2048(0x800, double:1.0118E-320)
            r0.append(r5)     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r1 = "K"
            r0.append(r1)     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r5 = r0.toString()     // Catch:{ Throwable -> 0x0477 }
            r6 = 0
            r1 = r21
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0477 }
            return
        L_0x006e:
            java.lang.String r4 = "[Upload] Run upload task with cmd: %d"
            java.lang.Object[] r5 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x0477 }
            int r6 = r7.d     // Catch:{ Throwable -> 0x0477 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Throwable -> 0x0477 }
            r5[r0] = r6     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.c(r4, r5)     // Catch:{ Throwable -> 0x0477 }
            android.content.Context r4 = r7.c     // Catch:{ Throwable -> 0x0477 }
            if (r4 == 0) goto L_0x045f
            com.tencent.bugly.crashreport.common.info.a r4 = r7.f     // Catch:{ Throwable -> 0x0477 }
            if (r4 == 0) goto L_0x045f
            com.tencent.bugly.crashreport.common.strategy.a r4 = r7.g     // Catch:{ Throwable -> 0x0477 }
            if (r4 == 0) goto L_0x045f
            com.tencent.bugly.proguard.s r4 = r7.h     // Catch:{ Throwable -> 0x0477 }
            if (r4 != 0) goto L_0x008f
            goto L_0x045f
        L_0x008f:
            com.tencent.bugly.crashreport.common.strategy.a r4 = r7.g     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r4 = r4.c()     // Catch:{ Throwable -> 0x0477 }
            if (r4 != 0) goto L_0x00a3
            r2 = 0
            r3 = 0
            r4 = 0
            java.lang.String r5 = "illegal local strategy"
            r6 = 0
            r1 = r21
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0477 }
            return
        L_0x00a3:
            java.util.HashMap r5 = new java.util.HashMap     // Catch:{ Throwable -> 0x0477 }
            r5.<init>()     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r6 = "prodId"
            com.tencent.bugly.crashreport.common.info.a r10 = r7.f     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r10 = r10.f()     // Catch:{ Throwable -> 0x0477 }
            r5.put(r6, r10)     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r6 = "bundleId"
            com.tencent.bugly.crashreport.common.info.a r10 = r7.f     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r10 = r10.c     // Catch:{ Throwable -> 0x0477 }
            r5.put(r6, r10)     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r6 = "appVer"
            com.tencent.bugly.crashreport.common.info.a r10 = r7.f     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r10 = r10.j     // Catch:{ Throwable -> 0x0477 }
            r5.put(r6, r10)     // Catch:{ Throwable -> 0x0477 }
            java.util.Map<java.lang.String, java.lang.String> r6 = r7.o     // Catch:{ Throwable -> 0x0477 }
            if (r6 == 0) goto L_0x00ce
            java.util.Map<java.lang.String, java.lang.String> r6 = r7.o     // Catch:{ Throwable -> 0x0477 }
            r5.putAll(r6)     // Catch:{ Throwable -> 0x0477 }
        L_0x00ce:
            boolean r6 = r7.s     // Catch:{ Throwable -> 0x0477 }
            if (r6 == 0) goto L_0x0137
            java.lang.String r6 = "cmd"
            int r10 = r7.d     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r10 = java.lang.Integer.toString(r10)     // Catch:{ Throwable -> 0x0477 }
            r5.put(r6, r10)     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r6 = "platformId"
            java.lang.String r10 = java.lang.Byte.toString(r9)     // Catch:{ Throwable -> 0x0477 }
            r5.put(r6, r10)     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r6 = "sdkVer"
            com.tencent.bugly.crashreport.common.info.a r10 = r7.f     // Catch:{ Throwable -> 0x0477 }
            r10.getClass()     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r10 = "2.6.5"
            r5.put(r6, r10)     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r6 = "strategylastUpdateTime"
            long r10 = r4.p     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r4 = java.lang.Long.toString(r10)     // Catch:{ Throwable -> 0x0477 }
            r5.put(r6, r4)     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.u r4 = r7.i     // Catch:{ Throwable -> 0x0477 }
            boolean r4 = r4.a((java.util.Map<java.lang.String, java.lang.String>) r5)     // Catch:{ Throwable -> 0x0477 }
            if (r4 != 0) goto L_0x0111
            r2 = 0
            r3 = 0
            r4 = 0
            java.lang.String r5 = "failed to add security info to HTTP headers"
            r6 = 0
            r1 = r21
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0477 }
            return
        L_0x0111:
            byte[] r3 = com.tencent.bugly.proguard.z.a((byte[]) r3, (int) r8)     // Catch:{ Throwable -> 0x0477 }
            if (r3 != 0) goto L_0x0123
            r2 = 0
            r3 = 0
            r4 = 0
            java.lang.String r5 = "failed to zip request body"
            r6 = 0
            r1 = r21
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0477 }
            return
        L_0x0123:
            com.tencent.bugly.proguard.u r4 = r7.i     // Catch:{ Throwable -> 0x0477 }
            byte[] r3 = r4.a((byte[]) r3)     // Catch:{ Throwable -> 0x0477 }
            if (r3 != 0) goto L_0x0137
            r2 = 0
            r3 = 0
            r4 = 0
            java.lang.String r5 = "failed to encrypt request body"
            r6 = 0
            r1 = r21
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0477 }
            return
        L_0x0137:
            com.tencent.bugly.proguard.u r4 = r7.i     // Catch:{ Throwable -> 0x0477 }
            int r6 = r7.j     // Catch:{ Throwable -> 0x0477 }
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0477 }
            r4.a((int) r6, (long) r10)     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.t r4 = r7.k     // Catch:{ Throwable -> 0x0477 }
            if (r4 == 0) goto L_0x014a
            com.tencent.bugly.proguard.t r4 = r7.k     // Catch:{ Throwable -> 0x0477 }
            int r4 = r7.d     // Catch:{ Throwable -> 0x0477 }
        L_0x014a:
            com.tencent.bugly.proguard.t r4 = r7.l     // Catch:{ Throwable -> 0x0477 }
            if (r4 == 0) goto L_0x0152
            com.tencent.bugly.proguard.t r4 = r7.l     // Catch:{ Throwable -> 0x0477 }
            int r4 = r7.d     // Catch:{ Throwable -> 0x0477 }
        L_0x0152:
            java.lang.String r4 = r7.m     // Catch:{ Throwable -> 0x0477 }
            r6 = -1
            r10 = r4
            r4 = 0
            r6 = 0
            r11 = -1
        L_0x0159:
            int r12 = r4 + 1
            int r13 = r7.f9060a     // Catch:{ Throwable -> 0x0477 }
            if (r4 >= r13) goto L_0x0452
            if (r12 <= r9) goto L_0x0185
            java.lang.String r4 = "[Upload] Failed to upload last time, wait and try(%d) again."
            java.lang.Object[] r6 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x0477 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r12)     // Catch:{ Throwable -> 0x0477 }
            r6[r0] = r13     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.d(r4, r6)     // Catch:{ Throwable -> 0x0477 }
            int r4 = r7.b     // Catch:{ Throwable -> 0x0477 }
            long r13 = (long) r4     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.z.b((long) r13)     // Catch:{ Throwable -> 0x0477 }
            int r4 = r7.f9060a     // Catch:{ Throwable -> 0x0477 }
            if (r12 != r4) goto L_0x0185
            java.lang.String r4 = "[Upload] Use the back-up url at the last time: %s"
            java.lang.Object[] r6 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r10 = r7.n     // Catch:{ Throwable -> 0x0477 }
            r6[r0] = r10     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.d(r4, r6)     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r10 = r7.n     // Catch:{ Throwable -> 0x0477 }
        L_0x0185:
            java.lang.String r4 = "[Upload] Send %d bytes"
            java.lang.Object[] r6 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x0477 }
            int r13 = r3.length     // Catch:{ Throwable -> 0x0477 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ Throwable -> 0x0477 }
            r6[r0] = r13     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.c(r4, r6)     // Catch:{ Throwable -> 0x0477 }
            boolean r4 = r7.s     // Catch:{ Throwable -> 0x0477 }
            if (r4 == 0) goto L_0x019c
            java.lang.String r4 = a((java.lang.String) r10)     // Catch:{ Throwable -> 0x0477 }
            r10 = r4
        L_0x019c:
            java.lang.String r4 = "[Upload] Upload to %s with cmd %d (pid=%d | tid=%d)."
            r6 = 4
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Throwable -> 0x0477 }
            r6[r0] = r10     // Catch:{ Throwable -> 0x0477 }
            int r13 = r7.d     // Catch:{ Throwable -> 0x0477 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ Throwable -> 0x0477 }
            r6[r9] = r13     // Catch:{ Throwable -> 0x0477 }
            int r13 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x0477 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ Throwable -> 0x0477 }
            r6[r8] = r13     // Catch:{ Throwable -> 0x0477 }
            int r13 = android.os.Process.myTid()     // Catch:{ Throwable -> 0x0477 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ Throwable -> 0x0477 }
            r14 = 3
            r6[r14] = r13     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.c(r4, r6)     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.s r4 = r7.h     // Catch:{ Throwable -> 0x0477 }
            byte[] r4 = r4.a((java.lang.String) r10, (byte[]) r3, (com.tencent.bugly.proguard.v) r7, (java.util.Map<java.lang.String, java.lang.String>) r5)     // Catch:{ Throwable -> 0x0477 }
            if (r4 != 0) goto L_0x01e0
            java.lang.String r4 = "Failed to upload for no response!"
            java.lang.String r6 = "[Upload] Failed to upload(%d): %s"
            java.lang.Object[] r13 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x0477 }
            java.lang.Integer r14 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0477 }
            r13[r0] = r14     // Catch:{ Throwable -> 0x0477 }
            r13[r9] = r4     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.e(r6, r13)     // Catch:{ Throwable -> 0x0477 }
            r4 = r12
        L_0x01dd:
            r6 = 1
            goto L_0x0159
        L_0x01e0:
            com.tencent.bugly.proguard.s r6 = r7.h     // Catch:{ Throwable -> 0x0477 }
            java.util.Map<java.lang.String, java.lang.String> r6 = r6.f9055a     // Catch:{ Throwable -> 0x0477 }
            boolean r13 = r7.s     // Catch:{ Throwable -> 0x0477 }
            if (r13 == 0) goto L_0x0389
            if (r6 == 0) goto L_0x023b
            int r13 = r6.size()     // Catch:{ Throwable -> 0x0477 }
            if (r13 != 0) goto L_0x01f1
            goto L_0x023b
        L_0x01f1:
            java.lang.String r13 = "status"
            boolean r13 = r6.containsKey(r13)     // Catch:{ Throwable -> 0x0477 }
            if (r13 != 0) goto L_0x0205
            java.lang.String r13 = "[Upload] Headers does not contain %s"
            java.lang.Object[] r15 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r16 = "status"
            r15[r0] = r16     // Catch:{ Throwable -> 0x0477 }
        L_0x0201:
            com.tencent.bugly.proguard.x.d(r13, r15)     // Catch:{ Throwable -> 0x0477 }
            goto L_0x0242
        L_0x0205:
            java.lang.String r13 = "Bugly-Version"
            boolean r13 = r6.containsKey(r13)     // Catch:{ Throwable -> 0x0477 }
            if (r13 != 0) goto L_0x0216
            java.lang.String r13 = "[Upload] Headers does not contain %s"
            java.lang.Object[] r15 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r16 = "Bugly-Version"
            r15[r0] = r16     // Catch:{ Throwable -> 0x0477 }
            goto L_0x0201
        L_0x0216:
            java.lang.String r13 = "Bugly-Version"
            java.lang.Object r13 = r6.get(r13)     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r13 = (java.lang.String) r13     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r15 = "bugly"
            boolean r15 = r13.contains(r15)     // Catch:{ Throwable -> 0x0477 }
            if (r15 != 0) goto L_0x0230
            java.lang.String r15 = "[Upload] Bugly version is not valid: %s"
            java.lang.Object[] r1 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x0477 }
            r1[r0] = r13     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.d(r15, r1)     // Catch:{ Throwable -> 0x0477 }
            goto L_0x0242
        L_0x0230:
            java.lang.String r1 = "[Upload] Bugly version from headers is: %s"
            java.lang.Object[] r2 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x0477 }
            r2[r0] = r13     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.c(r1, r2)     // Catch:{ Throwable -> 0x0477 }
            r1 = 1
            goto L_0x0243
        L_0x023b:
            java.lang.String r1 = "[Upload] Headers is empty."
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.d(r1, r2)     // Catch:{ Throwable -> 0x0477 }
        L_0x0242:
            r1 = 0
        L_0x0243:
            if (r1 != 0) goto L_0x02ad
            java.lang.String r1 = "[Upload] Headers from server is not valid, just try again (pid=%d | tid=%d)."
            java.lang.Object[] r2 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x0477 }
            int r4 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x0477 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x0477 }
            r2[r0] = r4     // Catch:{ Throwable -> 0x0477 }
            int r4 = android.os.Process.myTid()     // Catch:{ Throwable -> 0x0477 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x0477 }
            r2[r9] = r4     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.c(r1, r2)     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r1 = "[Upload] Failed to upload for no status header."
            java.lang.String r2 = "[Upload] Failed to upload(%d): %s"
            java.lang.Object[] r4 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x0477 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0477 }
            r4[r0] = r13     // Catch:{ Throwable -> 0x0477 }
            r4[r9] = r1     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.e(r2, r4)     // Catch:{ Throwable -> 0x0477 }
            if (r6 == 0) goto L_0x02a1
            java.util.Set r1 = r6.entrySet()     // Catch:{ Throwable -> 0x0477 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Throwable -> 0x0477 }
        L_0x027b:
            boolean r2 = r1.hasNext()     // Catch:{ Throwable -> 0x0477 }
            if (r2 == 0) goto L_0x02a1
            java.lang.Object r2 = r1.next()     // Catch:{ Throwable -> 0x0477 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r4 = "[key]: %s, [value]: %s"
            java.lang.Object[] r6 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x0477 }
            java.lang.Object r13 = r2.getKey()     // Catch:{ Throwable -> 0x0477 }
            r6[r0] = r13     // Catch:{ Throwable -> 0x0477 }
            java.lang.Object r2 = r2.getValue()     // Catch:{ Throwable -> 0x0477 }
            r6[r9] = r2     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r2 = java.lang.String.format(r4, r6)     // Catch:{ Throwable -> 0x0477 }
            java.lang.Object[] r4 = new java.lang.Object[r0]     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.c(r2, r4)     // Catch:{ Throwable -> 0x0477 }
            goto L_0x027b
        L_0x02a1:
            java.lang.String r1 = "[Upload] Failed to upload for no status header."
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.c(r1, r2)     // Catch:{ Throwable -> 0x0477 }
            r4 = r12
            r1 = 0
            goto L_0x01dd
        L_0x02ad:
            java.lang.String r1 = "status"
            java.lang.Object r1 = r6.get(r1)     // Catch:{ Throwable -> 0x0362 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Throwable -> 0x0362 }
            int r13 = java.lang.Integer.parseInt(r1)     // Catch:{ Throwable -> 0x0362 }
            java.lang.String r1 = "[Upload] Status from server is %d (pid=%d | tid=%d)."
            java.lang.Object[] r2 = new java.lang.Object[r14]     // Catch:{ Throwable -> 0x035e }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r13)     // Catch:{ Throwable -> 0x035e }
            r2[r0] = r11     // Catch:{ Throwable -> 0x035e }
            int r11 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x035e }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ Throwable -> 0x035e }
            r2[r9] = r11     // Catch:{ Throwable -> 0x035e }
            int r11 = android.os.Process.myTid()     // Catch:{ Throwable -> 0x035e }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ Throwable -> 0x035e }
            r2[r8] = r11     // Catch:{ Throwable -> 0x035e }
            com.tencent.bugly.proguard.x.c(r1, r2)     // Catch:{ Throwable -> 0x035e }
            if (r13 == 0) goto L_0x035c
            if (r13 != r8) goto L_0x0344
            long r1 = r7.q     // Catch:{ Throwable -> 0x0477 }
            long r3 = r7.r     // Catch:{ Throwable -> 0x0477 }
            r5 = 0
            long r1 = r1 + r3
            r14 = 0
            int r3 = (r1 > r14 ? 1 : (r1 == r14 ? 0 : -1))
            if (r3 <= 0) goto L_0x0301
            com.tencent.bugly.proguard.u r1 = r7.i     // Catch:{ Throwable -> 0x0477 }
            boolean r2 = r7.t     // Catch:{ Throwable -> 0x0477 }
            long r1 = r1.a((boolean) r2)     // Catch:{ Throwable -> 0x0477 }
            long r3 = r7.q     // Catch:{ Throwable -> 0x0477 }
            r5 = 0
            long r1 = r1 + r3
            long r3 = r7.r     // Catch:{ Throwable -> 0x0477 }
            r5 = 0
            long r1 = r1 + r3
            com.tencent.bugly.proguard.u r3 = r7.i     // Catch:{ Throwable -> 0x0477 }
            boolean r4 = r7.t     // Catch:{ Throwable -> 0x0477 }
            r3.a((long) r1, (boolean) r4)     // Catch:{ Throwable -> 0x0477 }
        L_0x0301:
            com.tencent.bugly.proguard.u r1 = r7.i     // Catch:{ Throwable -> 0x0477 }
            r2 = 0
            r1.a((int) r13, (com.tencent.bugly.proguard.an) r2)     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r1 = "[Upload] Session ID is invalid, will try again immediately (pid=%d | tid=%d)."
            java.lang.Object[] r2 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x0477 }
            int r3 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x0477 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Throwable -> 0x0477 }
            r2[r0] = r3     // Catch:{ Throwable -> 0x0477 }
            int r0 = android.os.Process.myTid()     // Catch:{ Throwable -> 0x0477 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0477 }
            r2[r9] = r0     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.a(r1, r2)     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.u r10 = r7.i     // Catch:{ Throwable -> 0x0477 }
            int r11 = r7.j     // Catch:{ Throwable -> 0x0477 }
            int r12 = r7.d     // Catch:{ Throwable -> 0x0477 }
            byte[] r13 = r7.e     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r14 = r7.m     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r15 = r7.n     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.t r0 = r7.k     // Catch:{ Throwable -> 0x0477 }
            int r1 = r7.f9060a     // Catch:{ Throwable -> 0x0477 }
            int r2 = r7.b     // Catch:{ Throwable -> 0x0477 }
            r19 = 1
            java.util.Map<java.lang.String, java.lang.String> r3 = r7.o     // Catch:{ Throwable -> 0x0477 }
            r16 = r0
            r17 = r1
            r18 = r2
            r20 = r3
            r10.a(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ Throwable -> 0x0477 }
            return
        L_0x0344:
            r2 = 0
            r3 = 0
            r4 = 1
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r1 = "status of server is "
            r0.<init>(r1)     // Catch:{ Throwable -> 0x0477 }
            r0.append(r13)     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r5 = r0.toString()     // Catch:{ Throwable -> 0x0477 }
            r1 = r21
            r6 = r13
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0477 }
            return
        L_0x035c:
            r11 = r13
            goto L_0x0389
        L_0x035e:
            r14 = 0
            r11 = r13
            goto L_0x0364
        L_0x0362:
            r14 = 0
        L_0x0364:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r2 = "[Upload] Failed to upload for format of status header is invalid: "
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r2 = java.lang.Integer.toString(r11)     // Catch:{ Throwable -> 0x0477 }
            r1.append(r2)     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r2 = "[Upload] Failed to upload(%d): %s"
            java.lang.Object[] r4 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x0477 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0477 }
            r4[r0] = r6     // Catch:{ Throwable -> 0x0477 }
            r4[r9] = r1     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.e(r2, r4)     // Catch:{ Throwable -> 0x0477 }
            r4 = r12
            r1 = r14
            goto L_0x01dd
        L_0x0389:
            java.lang.String r1 = "[Upload] Received %d bytes"
            java.lang.Object[] r2 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x0477 }
            int r3 = r4.length     // Catch:{ Throwable -> 0x0477 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Throwable -> 0x0477 }
            r2[r0] = r3     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.c(r1, r2)     // Catch:{ Throwable -> 0x0477 }
            boolean r1 = r7.s     // Catch:{ Throwable -> 0x0477 }
            if (r1 == 0) goto L_0x03f8
            int r1 = r4.length     // Catch:{ Throwable -> 0x0477 }
            if (r1 != 0) goto L_0x03d2
            java.util.Set r1 = r6.entrySet()     // Catch:{ Throwable -> 0x0477 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Throwable -> 0x0477 }
        L_0x03a6:
            boolean r2 = r1.hasNext()     // Catch:{ Throwable -> 0x0477 }
            if (r2 == 0) goto L_0x03c6
            java.lang.Object r2 = r1.next()     // Catch:{ Throwable -> 0x0477 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ Throwable -> 0x0477 }
            java.lang.String r3 = "[Upload] HTTP headers from server: key = %s, value = %s"
            java.lang.Object[] r4 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x0477 }
            java.lang.Object r5 = r2.getKey()     // Catch:{ Throwable -> 0x0477 }
            r4[r0] = r5     // Catch:{ Throwable -> 0x0477 }
            java.lang.Object r2 = r2.getValue()     // Catch:{ Throwable -> 0x0477 }
            r4[r9] = r2     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.c(r3, r4)     // Catch:{ Throwable -> 0x0477 }
            goto L_0x03a6
        L_0x03c6:
            r2 = 0
            r3 = 0
            r4 = 1
            java.lang.String r5 = "response data from server is empty"
            r6 = 0
            r1 = r21
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0477 }
            return
        L_0x03d2:
            com.tencent.bugly.proguard.u r1 = r7.i     // Catch:{ Throwable -> 0x0477 }
            byte[] r1 = r1.b((byte[]) r4)     // Catch:{ Throwable -> 0x0477 }
            if (r1 != 0) goto L_0x03e6
            r2 = 0
            r3 = 0
            r4 = 1
            java.lang.String r5 = "failed to decrypt response from server"
            r6 = 0
            r1 = r21
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0477 }
            return
        L_0x03e6:
            byte[] r4 = com.tencent.bugly.proguard.z.b((byte[]) r1, (int) r8)     // Catch:{ Throwable -> 0x0477 }
            if (r4 != 0) goto L_0x03f8
            r2 = 0
            r3 = 0
            r4 = 1
            java.lang.String r5 = "failed unzip(Gzip) response from server"
            r6 = 0
            r1 = r21
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0477 }
            return
        L_0x03f8:
            boolean r1 = r7.s     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.an r2 = com.tencent.bugly.proguard.a.a((byte[]) r4, (boolean) r1)     // Catch:{ Throwable -> 0x0477 }
            if (r2 != 0) goto L_0x040c
            r2 = 0
            r3 = 0
            r4 = 1
            java.lang.String r5 = "failed to decode response package"
            r6 = 0
            r1 = r21
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0477 }
            return
        L_0x040c:
            boolean r1 = r7.s     // Catch:{ Throwable -> 0x0477 }
            if (r1 == 0) goto L_0x0415
            com.tencent.bugly.proguard.u r1 = r7.i     // Catch:{ Throwable -> 0x0477 }
            r1.a((int) r11, (com.tencent.bugly.proguard.an) r2)     // Catch:{ Throwable -> 0x0477 }
        L_0x0415:
            java.lang.String r1 = "[Upload] Response cmd is: %d, length of sBuffer is: %d"
            java.lang.Object[] r3 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x0477 }
            int r4 = r2.b     // Catch:{ Throwable -> 0x0477 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x0477 }
            r3[r0] = r4     // Catch:{ Throwable -> 0x0477 }
            byte[] r4 = r2.c     // Catch:{ Throwable -> 0x0477 }
            if (r4 != 0) goto L_0x0426
            goto L_0x0429
        L_0x0426:
            byte[] r0 = r2.c     // Catch:{ Throwable -> 0x0477 }
            int r0 = r0.length     // Catch:{ Throwable -> 0x0477 }
        L_0x0429:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0477 }
            r3[r9] = r0     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.proguard.x.c(r1, r3)     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.crashreport.common.info.a r0 = r7.f     // Catch:{ Throwable -> 0x0477 }
            com.tencent.bugly.crashreport.common.strategy.a r1 = r7.g     // Catch:{ Throwable -> 0x0477 }
            boolean r0 = a(r2, r0, r1)     // Catch:{ Throwable -> 0x0477 }
            if (r0 != 0) goto L_0x0447
            r3 = 0
            r4 = 2
            java.lang.String r5 = "failed to process response package"
            r6 = 0
            r1 = r21
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0477 }
            return
        L_0x0447:
            r3 = 1
            r4 = 2
            java.lang.String r5 = "successfully uploaded"
            r6 = 0
            r1 = r21
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0477 }
            return
        L_0x0452:
            r2 = 0
            r3 = 0
            java.lang.String r5 = "failed after many attempts"
            r0 = 0
            r1 = r21
            r4 = r6
            r6 = r0
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0477 }
            return
        L_0x045f:
            r2 = 0
            r3 = 0
            r4 = 0
            java.lang.String r5 = "illegal access error"
            r6 = 0
            r1 = r21
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0477 }
            return
        L_0x046b:
            r2 = 0
            r3 = 0
            r4 = 0
            java.lang.String r5 = "request package is empty!"
            r6 = 0
            r1 = r21
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0477 }
            return
        L_0x0477:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0481
            r0.printStackTrace()
        L_0x0481:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.v.run():void");
    }

    public final void a(long j2) {
        this.p++;
        this.q += j2;
    }

    public final void b(long j2) {
        this.r += j2;
    }

    private static String a(String str) {
        if (z.a(str)) {
            return str;
        }
        try {
            return String.format("%s?aid=%s", new Object[]{str, UUID.randomUUID().toString()});
        } catch (Throwable th) {
            x.a(th);
            return str;
        }
    }
}
