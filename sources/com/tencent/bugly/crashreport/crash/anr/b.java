package com.tencent.bugly.crashreport.crash.anr;

import android.content.Context;
import android.os.FileObserver;
import com.mi.global.shop.model.Tags;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import com.xiaomi.smarthome.download.Constants;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class b {

    /* renamed from: a  reason: collision with root package name */
    private AtomicInteger f9008a = new AtomicInteger(0);
    private long b = -1;
    private final Context c;
    private final a d;
    private final w e;
    private final com.tencent.bugly.crashreport.common.strategy.a f;
    private final String g;
    private final com.tencent.bugly.crashreport.crash.b h;
    private FileObserver i;
    private boolean j = true;

    public b(Context context, com.tencent.bugly.crashreport.common.strategy.a aVar, a aVar2, w wVar, com.tencent.bugly.crashreport.crash.b bVar) {
        this.c = z.a(context);
        this.g = context.getDir("bugly", 0).getAbsolutePath();
        this.d = aVar2;
        this.e = wVar;
        this.f = aVar;
        this.h = bVar;
    }

    private CrashDetailBean a(a aVar) {
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        try {
            crashDetailBean.B = com.tencent.bugly.crashreport.common.info.b.g();
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.e();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.i();
            crashDetailBean.E = this.d.p();
            crashDetailBean.F = this.d.o();
            crashDetailBean.G = this.d.q();
            crashDetailBean.w = z.a(this.c, c.e, (String) null);
            crashDetailBean.b = 3;
            crashDetailBean.e = this.d.h();
            crashDetailBean.f = this.d.j;
            crashDetailBean.g = this.d.w();
            crashDetailBean.m = this.d.g();
            crashDetailBean.n = "ANR_EXCEPTION";
            crashDetailBean.o = aVar.f;
            crashDetailBean.q = aVar.g;
            crashDetailBean.N = new HashMap();
            crashDetailBean.N.put("BUGLY_CR_01", aVar.e);
            int i2 = -1;
            if (crashDetailBean.q != null) {
                i2 = crashDetailBean.q.indexOf("\n");
            }
            crashDetailBean.p = i2 > 0 ? crashDetailBean.q.substring(0, i2) : "GET_FAIL";
            crashDetailBean.r = aVar.c;
            if (crashDetailBean.q != null) {
                crashDetailBean.u = z.b(crashDetailBean.q.getBytes());
            }
            crashDetailBean.y = aVar.b;
            crashDetailBean.z = this.d.d;
            crashDetailBean.A = "main(1)";
            crashDetailBean.H = this.d.y();
            crashDetailBean.h = this.d.v();
            crashDetailBean.i = this.d.I();
            crashDetailBean.v = aVar.d;
            crashDetailBean.K = this.d.n;
            crashDetailBean.L = this.d.f8995a;
            crashDetailBean.M = this.d.a();
            crashDetailBean.O = this.d.F();
            crashDetailBean.P = this.d.G();
            crashDetailBean.Q = this.d.z();
            crashDetailBean.R = this.d.E();
            this.h.c(crashDetailBean);
            crashDetailBean.x = y.a();
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
        return crashDetailBean;
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x012f A[Catch:{ all -> 0x0125 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x015d A[SYNTHETIC, Splitter:B:56:0x015d] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x016e A[SYNTHETIC, Splitter:B:64:0x016e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            r0 = 1
            com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$a r9 = com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readTargetDumpInfo(r11, r9, r0)
            r1 = 0
            if (r9 == 0) goto L_0x01bd
            java.util.Map<java.lang.String, java.lang.String[]> r2 = r9.d
            if (r2 == 0) goto L_0x01bd
            java.util.Map<java.lang.String, java.lang.String[]> r2 = r9.d
            int r2 = r2.size()
            if (r2 > 0) goto L_0x0016
            goto L_0x01bd
        L_0x0016:
            java.io.File r11 = new java.io.File
            r11.<init>(r10)
            r2 = 2
            boolean r3 = r11.exists()     // Catch:{ Exception -> 0x0187 }
            if (r3 != 0) goto L_0x0036
            java.io.File r3 = r11.getParentFile()     // Catch:{ Exception -> 0x0187 }
            boolean r3 = r3.exists()     // Catch:{ Exception -> 0x0187 }
            if (r3 != 0) goto L_0x0033
            java.io.File r3 = r11.getParentFile()     // Catch:{ Exception -> 0x0187 }
            r3.mkdirs()     // Catch:{ Exception -> 0x0187 }
        L_0x0033:
            r11.createNewFile()     // Catch:{ Exception -> 0x0187 }
        L_0x0036:
            boolean r3 = r11.exists()
            if (r3 == 0) goto L_0x017d
            boolean r3 = r11.canWrite()
            if (r3 != 0) goto L_0x0044
            goto L_0x017d
        L_0x0044:
            r10 = 0
            java.io.BufferedWriter r3 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x0128 }
            java.io.FileWriter r4 = new java.io.FileWriter     // Catch:{ IOException -> 0x0128 }
            r4.<init>(r11, r1)     // Catch:{ IOException -> 0x0128 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0128 }
            java.util.Map<java.lang.String, java.lang.String[]> r10 = r9.d     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r11 = "main"
            java.lang.Object r10 = r10.get(r11)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String[] r10 = (java.lang.String[]) r10     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r11 = 3
            if (r10 == 0) goto L_0x008e
            int r4 = r10.length     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            if (r4 < r11) goto L_0x008e
            r4 = r10[r1]     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r5 = r10[r0]     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r10 = r10[r2]     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r7 = "\"main\" tid="
            r6.<init>(r7)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r6.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = " :\n"
            r6.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r6.append(r4)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = "\n"
            r6.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r6.append(r5)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = "\n\n"
            r6.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = r6.toString()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r3.write(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r3.flush()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
        L_0x008e:
            java.util.Map<java.lang.String, java.lang.String[]> r9 = r9.d     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.util.Set r9 = r9.entrySet()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
        L_0x0098:
            boolean r10 = r9.hasNext()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            if (r10 == 0) goto L_0x0111
            java.lang.Object r10 = r9.next()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.util.Map$Entry r10 = (java.util.Map.Entry) r10     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.Object r4 = r10.getKey()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r5 = "main"
            boolean r4 = r4.equals(r5)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            if (r4 != 0) goto L_0x0098
            java.lang.Object r4 = r10.getValue()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            if (r4 == 0) goto L_0x0098
            java.lang.Object r4 = r10.getValue()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            int r4 = r4.length     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            if (r4 < r11) goto L_0x0098
            java.lang.Object r4 = r10.getValue()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r4 = r4[r1]     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.Object r5 = r10.getValue()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String[] r5 = (java.lang.String[]) r5     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r5 = r5[r0]     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.Object r6 = r10.getValue()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String[] r6 = (java.lang.String[]) r6     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r6 = r6[r2]     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r8 = "\""
            r7.<init>(r8)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.Object r10 = r10.getKey()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r7.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = "\" tid="
            r7.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r7.append(r6)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = " :\n"
            r7.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r7.append(r4)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = "\n"
            r7.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r7.append(r5)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = "\n\n"
            r7.append(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            java.lang.String r10 = r7.toString()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r3.write(r10)     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            r3.flush()     // Catch:{ IOException -> 0x0122, all -> 0x0120 }
            goto L_0x0098
        L_0x0111:
            r3.close()     // Catch:{ IOException -> 0x0115 }
            goto L_0x011f
        L_0x0115:
            r9 = move-exception
            boolean r10 = com.tencent.bugly.proguard.x.a(r9)
            if (r10 != 0) goto L_0x011f
            r9.printStackTrace()
        L_0x011f:
            return r0
        L_0x0120:
            r9 = move-exception
            goto L_0x016c
        L_0x0122:
            r9 = move-exception
            r10 = r3
            goto L_0x0129
        L_0x0125:
            r9 = move-exception
            r3 = r10
            goto L_0x016c
        L_0x0128:
            r9 = move-exception
        L_0x0129:
            boolean r11 = com.tencent.bugly.proguard.x.a(r9)     // Catch:{ all -> 0x0125 }
            if (r11 != 0) goto L_0x0132
            r9.printStackTrace()     // Catch:{ all -> 0x0125 }
        L_0x0132:
            java.lang.String r11 = "dump trace fail %s"
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x0125 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0125 }
            r2.<init>()     // Catch:{ all -> 0x0125 }
            java.lang.Class r3 = r9.getClass()     // Catch:{ all -> 0x0125 }
            java.lang.String r3 = r3.getName()     // Catch:{ all -> 0x0125 }
            r2.append(r3)     // Catch:{ all -> 0x0125 }
            java.lang.String r3 = ":"
            r2.append(r3)     // Catch:{ all -> 0x0125 }
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x0125 }
            r2.append(r9)     // Catch:{ all -> 0x0125 }
            java.lang.String r9 = r2.toString()     // Catch:{ all -> 0x0125 }
            r0[r1] = r9     // Catch:{ all -> 0x0125 }
            com.tencent.bugly.proguard.x.e(r11, r0)     // Catch:{ all -> 0x0125 }
            if (r10 == 0) goto L_0x016b
            r10.close()     // Catch:{ IOException -> 0x0161 }
            goto L_0x016b
        L_0x0161:
            r9 = move-exception
            boolean r10 = com.tencent.bugly.proguard.x.a(r9)
            if (r10 != 0) goto L_0x016b
            r9.printStackTrace()
        L_0x016b:
            return r1
        L_0x016c:
            if (r3 == 0) goto L_0x017c
            r3.close()     // Catch:{ IOException -> 0x0172 }
            goto L_0x017c
        L_0x0172:
            r10 = move-exception
            boolean r11 = com.tencent.bugly.proguard.x.a(r10)
            if (r11 != 0) goto L_0x017c
            r10.printStackTrace()
        L_0x017c:
            throw r9
        L_0x017d:
            java.lang.String r9 = "backup file create fail %s"
            java.lang.Object[] r11 = new java.lang.Object[r0]
            r11[r1] = r10
            com.tencent.bugly.proguard.x.e(r9, r11)
            return r1
        L_0x0187:
            r9 = move-exception
            boolean r11 = com.tencent.bugly.proguard.x.a(r9)
            if (r11 != 0) goto L_0x0191
            r9.printStackTrace()
        L_0x0191:
            java.lang.String r11 = "backup file create error! %s  %s"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.Class r4 = r9.getClass()
            java.lang.String r4 = r4.getName()
            r3.append(r4)
            java.lang.String r4 = ":"
            r3.append(r4)
            java.lang.String r9 = r9.getMessage()
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            r2[r1] = r9
            r2[r0] = r10
            com.tencent.bugly.proguard.x.e(r11, r2)
            return r1
        L_0x01bd:
            java.lang.String r9 = "not found trace dump for %s"
            java.lang.Object[] r10 = new java.lang.Object[r0]
            r10[r1] = r11
            com.tencent.bugly.proguard.x.e(r9, r10)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.b.a(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    public final boolean a() {
        return this.f9008a.get() != 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0276, code lost:
        com.tencent.bugly.proguard.x.a(r0);
        com.tencent.bugly.proguard.x.e("get all thread stack fail!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0282, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0284, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0289, code lost:
        if (com.tencent.bugly.proguard.x.a(r0) == false) goto L_0x028b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x028b, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x028e, code lost:
        com.tencent.bugly.proguard.x.e("handle anr error %s", r0.getClass().toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x02a1, code lost:
        r1.f9008a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x02a6, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        com.tencent.bugly.proguard.x.c("read trace first dump for create time!", new java.lang.Object[0]);
        r2 = com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readFirstDumpInfo(r0, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        if (r2 == null) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002d, code lost:
        r7 = r2.c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0030, code lost:
        r7 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0033, code lost:
        if (r7 != -1) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        com.tencent.bugly.proguard.x.d("trace dump fail could not get time!", new java.lang.Object[0]);
        r7 = java.lang.System.currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004d, code lost:
        if (java.lang.Math.abs(r7 - r1.b) >= 10000) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004f, code lost:
        com.tencent.bugly.proguard.x.d("should not process ANR too Fre in %d", 10000);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r1.b = r7;
        r1.f9008a.set(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r2 = com.tencent.bugly.proguard.z.a(com.tencent.bugly.crashreport.crash.c.f, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0071, code lost:
        if (r2 == null) goto L_0x026c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0077, code lost:
        if (r2.size() > 0) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007b, code lost:
        r5 = r1.c;
        com.tencent.bugly.proguard.x.c("to find!", new java.lang.Object[0]);
        r5 = (android.app.ActivityManager) r5.getSystemService("activity");
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008f, code lost:
        com.tencent.bugly.proguard.x.c("waiting!", new java.lang.Object[0]);
        r11 = r5.getProcessesInErrorState();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009d, code lost:
        if (r11 == null) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009f, code lost:
        r11 = r11.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a7, code lost:
        if (r11.hasNext() == false) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a9, code lost:
        r15 = r11.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b1, code lost:
        if (r15.condition != 2) goto L_0x00bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b3, code lost:
        com.tencent.bugly.proguard.x.c("found!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00bd, code lost:
        com.tencent.bugly.proguard.z.b(500);
        r4 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00c5, code lost:
        if (((long) r6) < 20) goto L_0x0268;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c7, code lost:
        com.tencent.bugly.proguard.x.c("end!", new java.lang.Object[0]);
        r15 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00cf, code lost:
        if (r15 != null) goto L_0x00d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00d1, code lost:
        com.tencent.bugly.proguard.x.c("proc state is unvisiable!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00df, code lost:
        if (r15.pid == android.os.Process.myPid()) goto L_0x00ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e1, code lost:
        com.tencent.bugly.proguard.x.c("not mind proc!", r15.processName);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ef, code lost:
        com.tencent.bugly.proguard.x.a("found visiable anr , start to process!", new java.lang.Object[0]);
        r4 = r1.c;
        r1.f.c();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0103, code lost:
        if (r1.f.b() != false) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0105, code lost:
        com.tencent.bugly.proguard.x.e("waiting for remote sync", new java.lang.Object[0]);
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0113, code lost:
        if (r1.f.b() != false) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0115, code lost:
        com.tencent.bugly.proguard.z.b(500);
        r5 = r5 + 500;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x011e, code lost:
        if (r5 < 3000) goto L_0x010d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0120, code lost:
        r5 = new java.io.File(r4.getFilesDir(), "bugly/bugly_trace_" + r7 + com.xiaomi.smarthome.download.Constants.n);
        r4 = new com.tencent.bugly.crashreport.crash.anr.a();
        r4.c = r7;
        r4.d = r5.getAbsolutePath();
        r4.f9007a = r15.processName;
        r4.f = r15.shortMsg;
        r4.e = r15.longMsg;
        r4.b = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0157, code lost:
        if (r2 == null) goto L_0x017e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0159, code lost:
        r5 = r2.keySet().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0165, code lost:
        if (r5.hasNext() == false) goto L_0x017e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0167, code lost:
        r6 = r5.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0173, code lost:
        if (r6.startsWith("main(") == false) goto L_0x0161;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0175, code lost:
        r4.g = r2.get(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x017e, code lost:
        r5 = new java.lang.Object[6];
        r5[0] = java.lang.Long.valueOf(r4.c);
        r5[1] = r4.d;
        r5[2] = r4.f9007a;
        r5[3] = r4.f;
        r5[4] = r4.e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01a2, code lost:
        if (r4.b != null) goto L_0x01a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01a4, code lost:
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01a6, code lost:
        r8 = r4.b.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01ac, code lost:
        r5[5] = java.lang.Integer.valueOf(r8);
        com.tencent.bugly.proguard.x.c("anr tm:%d\ntr:%s\nproc:%s\nsMsg:%s\n lMsg:%s\n threads:%d", r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01bb, code lost:
        if (r1.f.b() != false) goto L_0x01d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01bd, code lost:
        com.tencent.bugly.proguard.x.e("crash report sync remote fail, will not upload to Bugly , print local for helpful!", new java.lang.Object[0]);
        com.tencent.bugly.crashreport.crash.b.a("ANR", com.tencent.bugly.proguard.z.a(), r4.f9007a, (java.lang.Thread) null, r4.e, (com.tencent.bugly.crashreport.crash.CrashDetailBean) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01dd, code lost:
        if (r1.f.c().j != false) goto L_0x01e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01df, code lost:
        com.tencent.bugly.proguard.x.d("ANR Report is closed!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01e8, code lost:
        com.tencent.bugly.proguard.x.a("found visiable anr , start to upload!", new java.lang.Object[0]);
        r2 = a(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01f3, code lost:
        if (r2 != null) goto L_0x01fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01f5, code lost:
        com.tencent.bugly.proguard.x.e("pack anr fail!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01fd, code lost:
        com.tencent.bugly.crashreport.crash.c.a().a(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x020a, code lost:
        if (r2.f9002a < 0) goto L_0x0214;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x020c, code lost:
        com.tencent.bugly.proguard.x.a("backup anr record success!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0214, code lost:
        com.tencent.bugly.proguard.x.d("backup anr record fail!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x021b, code lost:
        if (r0 == null) goto L_0x023e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0226, code lost:
        if (new java.io.File(r0).exists() == false) goto L_0x023e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0228, code lost:
        r1.f9008a.set(3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0235, code lost:
        if (a(r0, r4.d, r4.f9007a) == false) goto L_0x023e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0237, code lost:
        com.tencent.bugly.proguard.x.a("backup trace success", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x023e, code lost:
        com.tencent.bugly.crashreport.crash.b.a("ANR", com.tencent.bugly.proguard.z.a(), r4.f9007a, (java.lang.Thread) null, r4.e, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0253, code lost:
        if (r1.h.a(r2) != false) goto L_0x025d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0255, code lost:
        r1.h.a(r2, 3000, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x025d, code lost:
        r1.h.b(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0268, code lost:
        r6 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:?, code lost:
        com.tencent.bugly.proguard.x.d("can't get all thread skip this anr", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0275, code lost:
        r0 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r17) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            monitor-enter(r16)
            java.util.concurrent.atomic.AtomicInteger r2 = r1.f9008a     // Catch:{ all -> 0x02a7 }
            int r2 = r2.get()     // Catch:{ all -> 0x02a7 }
            r3 = 0
            if (r2 == 0) goto L_0x0017
            java.lang.String r0 = "trace started return "
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ all -> 0x02a7 }
            com.tencent.bugly.proguard.x.c(r0, r2)     // Catch:{ all -> 0x02a7 }
            monitor-exit(r16)     // Catch:{ all -> 0x02a7 }
            return
        L_0x0017:
            java.util.concurrent.atomic.AtomicInteger r2 = r1.f9008a     // Catch:{ all -> 0x02a7 }
            r4 = 1
            r2.set(r4)     // Catch:{ all -> 0x02a7 }
            monitor-exit(r16)     // Catch:{ all -> 0x02a7 }
            java.lang.String r2 = "read trace first dump for create time!"
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.c(r2, r5)     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$a r2 = com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readFirstDumpInfo(r0, r3)     // Catch:{ Throwable -> 0x0284 }
            r5 = -1
            if (r2 == 0) goto L_0x0030
            long r7 = r2.c     // Catch:{ Throwable -> 0x0284 }
            goto L_0x0031
        L_0x0030:
            r7 = r5
        L_0x0031:
            int r2 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r2 != 0) goto L_0x0040
            java.lang.String r2 = "trace dump fail could not get time!"
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.d(r2, r5)     // Catch:{ Throwable -> 0x0284 }
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0284 }
        L_0x0040:
            long r5 = r1.b     // Catch:{ Throwable -> 0x0284 }
            r2 = 0
            long r5 = r7 - r5
            long r5 = java.lang.Math.abs(r5)     // Catch:{ Throwable -> 0x0284 }
            r9 = 10000(0x2710, double:4.9407E-320)
            int r2 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r2 >= 0) goto L_0x0064
            java.lang.String r0 = "should not process ANR too Fre in %d"
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0284 }
            r5 = 10000(0x2710, float:1.4013E-41)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Throwable -> 0x0284 }
            r2[r3] = r5     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.d(r0, r2)     // Catch:{ Throwable -> 0x0284 }
        L_0x005e:
            java.util.concurrent.atomic.AtomicInteger r0 = r1.f9008a
            r0.set(r3)
            return
        L_0x0064:
            r1.b = r7     // Catch:{ Throwable -> 0x0284 }
            java.util.concurrent.atomic.AtomicInteger r2 = r1.f9008a     // Catch:{ Throwable -> 0x0284 }
            r2.set(r4)     // Catch:{ Throwable -> 0x0284 }
            int r2 = com.tencent.bugly.crashreport.crash.c.f     // Catch:{ Throwable -> 0x0275 }
            java.util.Map r2 = com.tencent.bugly.proguard.z.a((int) r2, (boolean) r3)     // Catch:{ Throwable -> 0x0275 }
            if (r2 == 0) goto L_0x026c
            int r5 = r2.size()     // Catch:{ Throwable -> 0x0284 }
            if (r5 > 0) goto L_0x007b
            goto L_0x026c
        L_0x007b:
            android.content.Context r5 = r1.c     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r6 = "to find!"
            java.lang.Object[] r9 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.c(r6, r9)     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r6 = "activity"
            java.lang.Object r5 = r5.getSystemService(r6)     // Catch:{ Throwable -> 0x0284 }
            android.app.ActivityManager r5 = (android.app.ActivityManager) r5     // Catch:{ Throwable -> 0x0284 }
            r9 = 20
            r6 = 0
        L_0x008f:
            java.lang.String r11 = "waiting!"
            java.lang.Object[] r12 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.c(r11, r12)     // Catch:{ Throwable -> 0x0284 }
            java.util.List r11 = r5.getProcessesInErrorState()     // Catch:{ Throwable -> 0x0284 }
            r12 = 2
            r13 = 500(0x1f4, double:2.47E-321)
            if (r11 == 0) goto L_0x00bd
            java.util.Iterator r11 = r11.iterator()     // Catch:{ Throwable -> 0x0284 }
        L_0x00a3:
            boolean r15 = r11.hasNext()     // Catch:{ Throwable -> 0x0284 }
            if (r15 == 0) goto L_0x00bd
            java.lang.Object r15 = r11.next()     // Catch:{ Throwable -> 0x0284 }
            android.app.ActivityManager$ProcessErrorStateInfo r15 = (android.app.ActivityManager.ProcessErrorStateInfo) r15     // Catch:{ Throwable -> 0x0284 }
            int r4 = r15.condition     // Catch:{ Throwable -> 0x0284 }
            if (r4 != r12) goto L_0x00bb
            java.lang.String r4 = "found!"
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.c(r4, r5)     // Catch:{ Throwable -> 0x0284 }
            goto L_0x00cf
        L_0x00bb:
            r4 = 1
            goto L_0x00a3
        L_0x00bd:
            com.tencent.bugly.proguard.z.b((long) r13)     // Catch:{ Throwable -> 0x0284 }
            int r4 = r6 + 1
            long r12 = (long) r6     // Catch:{ Throwable -> 0x0284 }
            int r6 = (r12 > r9 ? 1 : (r12 == r9 ? 0 : -1))
            if (r6 < 0) goto L_0x0268
            java.lang.String r4 = "end!"
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.c(r4, r5)     // Catch:{ Throwable -> 0x0284 }
            r15 = 0
        L_0x00cf:
            if (r15 != 0) goto L_0x00d9
            java.lang.String r0 = "proc state is unvisiable!"
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.c(r0, r2)     // Catch:{ Throwable -> 0x0284 }
            goto L_0x005e
        L_0x00d9:
            int r4 = r15.pid     // Catch:{ Throwable -> 0x0284 }
            int r5 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x0284 }
            if (r4 == r5) goto L_0x00ef
            java.lang.String r0 = "not mind proc!"
            r2 = 1
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r2 = r15.processName     // Catch:{ Throwable -> 0x0284 }
            r4[r3] = r2     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.c(r0, r4)     // Catch:{ Throwable -> 0x0284 }
            goto L_0x005e
        L_0x00ef:
            java.lang.String r4 = "found visiable anr , start to process!"
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.a(r4, r5)     // Catch:{ Throwable -> 0x0284 }
            android.content.Context r4 = r1.c     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.crashreport.common.strategy.a r5 = r1.f     // Catch:{ Throwable -> 0x0284 }
            r5.c()     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.crashreport.common.strategy.a r5 = r1.f     // Catch:{ Throwable -> 0x0284 }
            boolean r5 = r5.b()     // Catch:{ Throwable -> 0x0284 }
            if (r5 != 0) goto L_0x0120
            java.lang.String r5 = "waiting for remote sync"
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.e(r5, r6)     // Catch:{ Throwable -> 0x0284 }
            r5 = 0
        L_0x010d:
            com.tencent.bugly.crashreport.common.strategy.a r6 = r1.f     // Catch:{ Throwable -> 0x0284 }
            boolean r6 = r6.b()     // Catch:{ Throwable -> 0x0284 }
            if (r6 != 0) goto L_0x0120
            r9 = 500(0x1f4, double:2.47E-321)
            com.tencent.bugly.proguard.z.b((long) r9)     // Catch:{ Throwable -> 0x0284 }
            int r5 = r5 + 500
            r6 = 3000(0xbb8, float:4.204E-42)
            if (r5 < r6) goto L_0x010d
        L_0x0120:
            java.io.File r5 = new java.io.File     // Catch:{ Throwable -> 0x0284 }
            java.io.File r4 = r4.getFilesDir()     // Catch:{ Throwable -> 0x0284 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r9 = "bugly/bugly_trace_"
            r6.<init>(r9)     // Catch:{ Throwable -> 0x0284 }
            r6.append(r7)     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r9 = ".txt"
            r6.append(r9)     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x0284 }
            r5.<init>(r4, r6)     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.crashreport.crash.anr.a r4 = new com.tencent.bugly.crashreport.crash.anr.a     // Catch:{ Throwable -> 0x0284 }
            r4.<init>()     // Catch:{ Throwable -> 0x0284 }
            r4.c = r7     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r5 = r5.getAbsolutePath()     // Catch:{ Throwable -> 0x0284 }
            r4.d = r5     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r5 = r15.processName     // Catch:{ Throwable -> 0x0284 }
            r4.f9007a = r5     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r5 = r15.shortMsg     // Catch:{ Throwable -> 0x0284 }
            r4.f = r5     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r5 = r15.longMsg     // Catch:{ Throwable -> 0x0284 }
            r4.e = r5     // Catch:{ Throwable -> 0x0284 }
            r4.b = r2     // Catch:{ Throwable -> 0x0284 }
            if (r2 == 0) goto L_0x017e
            java.util.Set r5 = r2.keySet()     // Catch:{ Throwable -> 0x0284 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ Throwable -> 0x0284 }
        L_0x0161:
            boolean r6 = r5.hasNext()     // Catch:{ Throwable -> 0x0284 }
            if (r6 == 0) goto L_0x017e
            java.lang.Object r6 = r5.next()     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r7 = "main("
            boolean r7 = r6.startsWith(r7)     // Catch:{ Throwable -> 0x0284 }
            if (r7 == 0) goto L_0x0161
            java.lang.Object r6 = r2.get(r6)     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Throwable -> 0x0284 }
            r4.g = r6     // Catch:{ Throwable -> 0x0284 }
            goto L_0x0161
        L_0x017e:
            java.lang.String r2 = "anr tm:%d\ntr:%s\nproc:%s\nsMsg:%s\n lMsg:%s\n threads:%d"
            r5 = 6
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x0284 }
            long r6 = r4.c     // Catch:{ Throwable -> 0x0284 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Throwable -> 0x0284 }
            r5[r3] = r6     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r6 = r4.d     // Catch:{ Throwable -> 0x0284 }
            r7 = 1
            r5[r7] = r6     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r6 = r4.f9007a     // Catch:{ Throwable -> 0x0284 }
            r7 = 2
            r5[r7] = r6     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r6 = r4.f     // Catch:{ Throwable -> 0x0284 }
            r7 = 3
            r5[r7] = r6     // Catch:{ Throwable -> 0x0284 }
            r6 = 4
            java.lang.String r8 = r4.e     // Catch:{ Throwable -> 0x0284 }
            r5[r6] = r8     // Catch:{ Throwable -> 0x0284 }
            r6 = 5
            java.util.Map<java.lang.String, java.lang.String> r8 = r4.b     // Catch:{ Throwable -> 0x0284 }
            if (r8 != 0) goto L_0x01a6
            r8 = 0
            goto L_0x01ac
        L_0x01a6:
            java.util.Map<java.lang.String, java.lang.String> r8 = r4.b     // Catch:{ Throwable -> 0x0284 }
            int r8 = r8.size()     // Catch:{ Throwable -> 0x0284 }
        L_0x01ac:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Throwable -> 0x0284 }
            r5[r6] = r8     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.c(r2, r5)     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.crashreport.common.strategy.a r2 = r1.f     // Catch:{ Throwable -> 0x0284 }
            boolean r2 = r2.b()     // Catch:{ Throwable -> 0x0284 }
            if (r2 != 0) goto L_0x01d5
            java.lang.String r0 = "crash report sync remote fail, will not upload to Bugly , print local for helpful!"
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.e(r0, r2)     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r5 = "ANR"
            java.lang.String r6 = com.tencent.bugly.proguard.z.a()     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r7 = r4.f9007a     // Catch:{ Throwable -> 0x0284 }
            r8 = 0
            java.lang.String r9 = r4.e     // Catch:{ Throwable -> 0x0284 }
            r10 = 0
            com.tencent.bugly.crashreport.crash.b.a(r5, r6, r7, r8, r9, r10)     // Catch:{ Throwable -> 0x0284 }
            goto L_0x0262
        L_0x01d5:
            com.tencent.bugly.crashreport.common.strategy.a r2 = r1.f     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r2 = r2.c()     // Catch:{ Throwable -> 0x0284 }
            boolean r2 = r2.j     // Catch:{ Throwable -> 0x0284 }
            if (r2 != 0) goto L_0x01e8
            java.lang.String r0 = "ANR Report is closed!"
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.d(r0, r2)     // Catch:{ Throwable -> 0x0284 }
            goto L_0x0262
        L_0x01e8:
            java.lang.String r2 = "found visiable anr , start to upload!"
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.a(r2, r5)     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.crashreport.crash.CrashDetailBean r2 = r1.a((com.tencent.bugly.crashreport.crash.anr.a) r4)     // Catch:{ Throwable -> 0x0284 }
            if (r2 != 0) goto L_0x01fd
            java.lang.String r0 = "pack anr fail!"
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.e(r0, r2)     // Catch:{ Throwable -> 0x0284 }
            goto L_0x0262
        L_0x01fd:
            com.tencent.bugly.crashreport.crash.c r5 = com.tencent.bugly.crashreport.crash.c.a()     // Catch:{ Throwable -> 0x0284 }
            r5.a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r2)     // Catch:{ Throwable -> 0x0284 }
            long r5 = r2.f9002a     // Catch:{ Throwable -> 0x0284 }
            r8 = 0
            int r10 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r10 < 0) goto L_0x0214
            java.lang.String r5 = "backup anr record success!"
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.a(r5, r6)     // Catch:{ Throwable -> 0x0284 }
            goto L_0x021b
        L_0x0214:
            java.lang.String r5 = "backup anr record fail!"
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.d(r5, r6)     // Catch:{ Throwable -> 0x0284 }
        L_0x021b:
            if (r0 == 0) goto L_0x023e
            java.io.File r5 = new java.io.File     // Catch:{ Throwable -> 0x0284 }
            r5.<init>(r0)     // Catch:{ Throwable -> 0x0284 }
            boolean r5 = r5.exists()     // Catch:{ Throwable -> 0x0284 }
            if (r5 == 0) goto L_0x023e
            java.util.concurrent.atomic.AtomicInteger r5 = r1.f9008a     // Catch:{ Throwable -> 0x0284 }
            r5.set(r7)     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r5 = r4.d     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r6 = r4.f9007a     // Catch:{ Throwable -> 0x0284 }
            boolean r0 = a(r0, r5, r6)     // Catch:{ Throwable -> 0x0284 }
            if (r0 == 0) goto L_0x023e
            java.lang.String r0 = "backup trace success"
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.a(r0, r5)     // Catch:{ Throwable -> 0x0284 }
        L_0x023e:
            java.lang.String r8 = "ANR"
            java.lang.String r9 = com.tencent.bugly.proguard.z.a()     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r10 = r4.f9007a     // Catch:{ Throwable -> 0x0284 }
            r11 = 0
            java.lang.String r12 = r4.e     // Catch:{ Throwable -> 0x0284 }
            r13 = r2
            com.tencent.bugly.crashreport.crash.b.a(r8, r9, r10, r11, r12, r13)     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.crashreport.crash.b r0 = r1.h     // Catch:{ Throwable -> 0x0284 }
            boolean r0 = r0.a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r2)     // Catch:{ Throwable -> 0x0284 }
            if (r0 != 0) goto L_0x025d
            com.tencent.bugly.crashreport.crash.b r0 = r1.h     // Catch:{ Throwable -> 0x0284 }
            r4 = 3000(0xbb8, double:1.482E-320)
            r6 = 1
            r0.a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r2, (long) r4, (boolean) r6)     // Catch:{ Throwable -> 0x0284 }
        L_0x025d:
            com.tencent.bugly.crashreport.crash.b r0 = r1.h     // Catch:{ Throwable -> 0x0284 }
            r0.b((com.tencent.bugly.crashreport.crash.CrashDetailBean) r2)     // Catch:{ Throwable -> 0x0284 }
        L_0x0262:
            java.util.concurrent.atomic.AtomicInteger r0 = r1.f9008a
            r0.set(r3)
            return
        L_0x0268:
            r6 = r4
            r4 = 1
            goto L_0x008f
        L_0x026c:
            java.lang.String r0 = "can't get all thread skip this anr"
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.d(r0, r2)     // Catch:{ Throwable -> 0x0284 }
            goto L_0x005e
        L_0x0275:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)     // Catch:{ Throwable -> 0x0284 }
            java.lang.String r0 = "get all thread stack fail!"
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0284 }
            com.tencent.bugly.proguard.x.e(r0, r2)     // Catch:{ Throwable -> 0x0284 }
            goto L_0x005e
        L_0x0282:
            r0 = move-exception
            goto L_0x02a1
        L_0x0284:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.a(r0)     // Catch:{ all -> 0x0282 }
            if (r2 != 0) goto L_0x028e
            r0.printStackTrace()     // Catch:{ all -> 0x0282 }
        L_0x028e:
            java.lang.String r2 = "handle anr error %s"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0282 }
            java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x0282 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0282 }
            r4[r3] = r0     // Catch:{ all -> 0x0282 }
            com.tencent.bugly.proguard.x.e(r2, r4)     // Catch:{ all -> 0x0282 }
            goto L_0x0262
        L_0x02a1:
            java.util.concurrent.atomic.AtomicInteger r2 = r1.f9008a
            r2.set(r3)
            throw r0
        L_0x02a7:
            r0 = move-exception
            monitor-exit(r16)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.b.a(java.lang.String):void");
    }

    private synchronized void c() {
        if (e()) {
            x.d("start when started!", new Object[0]);
            return;
        }
        this.i = new FileObserver("/data/anr/", 8) {
            public final void onEvent(int i, String str) {
                if (str != null) {
                    String str2 = "/data/anr/" + str;
                    if (!str2.contains(Tags.Order.ORDER_TRACE)) {
                        x.d("not anr file %s", str2);
                        return;
                    }
                    b.this.a(str2);
                }
            }
        };
        try {
            this.i.startWatching();
            x.a("start anr monitor!", new Object[0]);
            this.e.a(new Runnable() {
                public final void run() {
                    b.this.b();
                }
            });
        } catch (Throwable th) {
            this.i = null;
            x.d("start anr monitor failed!", new Object[0]);
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
    }

    private synchronized void d() {
        if (!e()) {
            x.d("close when closed!", new Object[0]);
            return;
        }
        try {
            this.i.stopWatching();
            this.i = null;
            x.d("close anr monitor!", new Object[0]);
        } catch (Throwable th) {
            x.d("stop anr monitor failed!", new Object[0]);
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
    }

    private synchronized boolean e() {
        return this.i != null;
    }

    private synchronized void b(boolean z) {
        if (z) {
            c();
        } else {
            d();
        }
    }

    private synchronized boolean f() {
        return this.j;
    }

    private synchronized void c(boolean z) {
        if (this.j != z) {
            x.a("user change anr %b", Boolean.valueOf(z));
            this.j = z;
        }
    }

    public final void a(boolean z) {
        c(z);
        boolean f2 = f();
        com.tencent.bugly.crashreport.common.strategy.a a2 = com.tencent.bugly.crashreport.common.strategy.a.a();
        if (a2 != null) {
            f2 = f2 && a2.c().g;
        }
        if (f2 != e()) {
            x.a("anr changed to %b", Boolean.valueOf(f2));
            b(f2);
        }
    }

    /* access modifiers changed from: protected */
    public final void b() {
        File[] listFiles;
        long b2 = z.b() - c.g;
        File file = new File(this.g);
        if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null && listFiles.length != 0) {
            int length = "bugly_trace_".length();
            int i2 = 0;
            for (File file2 : listFiles) {
                String name = file2.getName();
                if (name.startsWith("bugly_trace_")) {
                    try {
                        int indexOf = name.indexOf(Constants.n);
                        if (indexOf > 0 && Long.parseLong(name.substring(length, indexOf)) >= b2) {
                        }
                    } catch (Throwable unused) {
                        x.e("tomb format error delete %s", name);
                    }
                    if (file2.delete()) {
                        i2++;
                    }
                }
            }
            x.c("clean tombs %d", Integer.valueOf(i2));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0042, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(com.tencent.bugly.crashreport.common.strategy.StrategyBean r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            if (r6 != 0) goto L_0x0005
            monitor-exit(r5)
            return
        L_0x0005:
            boolean r0 = r6.j     // Catch:{ all -> 0x0043 }
            boolean r1 = r5.e()     // Catch:{ all -> 0x0043 }
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L_0x001e
            java.lang.String r0 = "server anr changed to %b"
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch:{ all -> 0x0043 }
            boolean r4 = r6.j     // Catch:{ all -> 0x0043 }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x0043 }
            r1[r2] = r4     // Catch:{ all -> 0x0043 }
            com.tencent.bugly.proguard.x.d(r0, r1)     // Catch:{ all -> 0x0043 }
        L_0x001e:
            boolean r6 = r6.j     // Catch:{ all -> 0x0043 }
            if (r6 == 0) goto L_0x002a
            boolean r6 = r5.f()     // Catch:{ all -> 0x0043 }
            if (r6 == 0) goto L_0x002a
            r6 = 1
            goto L_0x002b
        L_0x002a:
            r6 = 0
        L_0x002b:
            boolean r0 = r5.e()     // Catch:{ all -> 0x0043 }
            if (r6 == r0) goto L_0x0041
            java.lang.String r0 = "anr changed to %b"
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch:{ all -> 0x0043 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r6)     // Catch:{ all -> 0x0043 }
            r1[r2] = r3     // Catch:{ all -> 0x0043 }
            com.tencent.bugly.proguard.x.a(r0, r1)     // Catch:{ all -> 0x0043 }
            r5.b(r6)     // Catch:{ all -> 0x0043 }
        L_0x0041:
            monitor-exit(r5)
            return
        L_0x0043:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.b.a(com.tencent.bugly.crashreport.common.strategy.StrategyBean):void");
    }
}
