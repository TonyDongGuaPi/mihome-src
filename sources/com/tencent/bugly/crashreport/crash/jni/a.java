package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.b;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.util.Map;

public final class a implements NativeExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private final Context f9025a;
    private final b b;
    private final com.tencent.bugly.crashreport.common.info.a c;
    private final com.tencent.bugly.crashreport.common.strategy.a d;

    public a(Context context, com.tencent.bugly.crashreport.common.info.a aVar, b bVar, com.tencent.bugly.crashreport.common.strategy.a aVar2) {
        this.f9025a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
    }

    public final CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, byte[] bArr, Map<String, String> map, boolean z) {
        int length;
        String str11;
        int indexOf;
        String str12 = str;
        String str13 = str8;
        byte[] bArr2 = bArr;
        boolean l = c.a().l();
        if (l) {
            x.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.b = 1;
        crashDetailBean.e = this.c.h();
        crashDetailBean.f = this.c.j;
        crashDetailBean.g = this.c.w();
        crashDetailBean.m = this.c.g();
        crashDetailBean.n = str3;
        crashDetailBean.o = l ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        crashDetailBean.p = str4;
        crashDetailBean.q = str5 == null ? "" : str5;
        crashDetailBean.r = j;
        crashDetailBean.u = z.b(crashDetailBean.q.getBytes());
        crashDetailBean.z = str12;
        crashDetailBean.A = str2;
        crashDetailBean.H = this.c.y();
        crashDetailBean.h = this.c.v();
        crashDetailBean.i = this.c.I();
        crashDetailBean.v = str13;
        NativeCrashHandler instance = NativeCrashHandler.getInstance();
        String dumpFilePath = instance != null ? instance.getDumpFilePath() : null;
        String a2 = b.a(dumpFilePath, str13);
        if (!z.a(a2)) {
            crashDetailBean.T = a2;
        }
        crashDetailBean.U = b.b(dumpFilePath);
        crashDetailBean.w = b.a(str9, c.e, (String) null);
        crashDetailBean.I = str7;
        crashDetailBean.J = str6;
        crashDetailBean.K = str10;
        crashDetailBean.E = this.c.p();
        crashDetailBean.F = this.c.o();
        crashDetailBean.G = this.c.q();
        if (z) {
            crashDetailBean.B = com.tencent.bugly.crashreport.common.info.b.g();
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.e();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.i();
            if (crashDetailBean.w == null) {
                crashDetailBean.w = z.a(this.f9025a, c.e, (String) null);
            }
            crashDetailBean.x = y.a();
            crashDetailBean.L = this.c.f8995a;
            crashDetailBean.M = this.c.a();
            crashDetailBean.O = this.c.F();
            crashDetailBean.P = this.c.G();
            crashDetailBean.Q = this.c.z();
            crashDetailBean.R = this.c.E();
            crashDetailBean.y = z.a(c.f, false);
            int indexOf2 = crashDetailBean.q.indexOf("java:\n");
            if (indexOf2 > 0 && (length = indexOf2 + "java:\n".length()) < crashDetailBean.q.length()) {
                String substring = crashDetailBean.q.substring(length, crashDetailBean.q.length() - 1);
                if (substring.length() > 0 && crashDetailBean.y.containsKey(crashDetailBean.A) && (indexOf = str11.indexOf(substring)) > 0) {
                    String substring2 = (str11 = crashDetailBean.y.get(crashDetailBean.A)).substring(indexOf);
                    crashDetailBean.y.put(crashDetailBean.A, substring2);
                    crashDetailBean.q = crashDetailBean.q.substring(0, length);
                    crashDetailBean.q += substring2;
                }
            }
            if (str12 == null) {
                crashDetailBean.z = this.c.d;
            }
            this.b.c(crashDetailBean);
        } else {
            crashDetailBean.B = -1;
            crashDetailBean.C = -1;
            crashDetailBean.D = -1;
            if (crashDetailBean.w == null) {
                crashDetailBean.w = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            }
            crashDetailBean.L = -1;
            crashDetailBean.O = -1;
            crashDetailBean.P = -1;
            crashDetailBean.Q = map;
            crashDetailBean.R = this.c.E();
            crashDetailBean.y = null;
            if (str12 == null) {
                crashDetailBean.z = "unknown(record)";
            }
            if (bArr2 != null) {
                crashDetailBean.x = bArr2;
            }
        }
        return crashDetailBean;
    }

    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        x.a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, (String[]) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x0158 A[Catch:{ Throwable -> 0x027a }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0204 A[Catch:{ Throwable -> 0x0276 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x020d A[Catch:{ Throwable -> 0x0276 }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0282  */
    /* JADX WARNING: Removed duplicated region for block: B:93:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void handleNativeException2(int r23, int r24, long r25, long r27, java.lang.String r29, java.lang.String r30, java.lang.String r31, java.lang.String r32, int r33, java.lang.String r34, int r35, int r36, int r37, java.lang.String r38, java.lang.String r39, java.lang.String[] r40) {
        /*
            r22 = this;
            r15 = r22
            r0 = r30
            r14 = r33
            r1 = r35
            r2 = r40
            java.lang.String r3 = "Native Crash Happen v2"
            r13 = 0
            java.lang.Object[] r4 = new java.lang.Object[r13]
            com.tencent.bugly.proguard.x.a(r3, r4)
            com.tencent.bugly.crashreport.common.strategy.a r3 = r15.d     // Catch:{ Throwable -> 0x027a }
            boolean r3 = r3.b()     // Catch:{ Throwable -> 0x027a }
            if (r3 != 0) goto L_0x0035
            java.lang.String r3 = "waiting for remote sync"
            java.lang.Object[] r4 = new java.lang.Object[r13]     // Catch:{ Throwable -> 0x027a }
            com.tencent.bugly.proguard.x.e(r3, r4)     // Catch:{ Throwable -> 0x027a }
            r3 = 0
        L_0x0022:
            com.tencent.bugly.crashreport.common.strategy.a r4 = r15.d     // Catch:{ Throwable -> 0x027a }
            boolean r4 = r4.b()     // Catch:{ Throwable -> 0x027a }
            if (r4 != 0) goto L_0x0035
            r4 = 500(0x1f4, double:2.47E-321)
            com.tencent.bugly.proguard.z.b((long) r4)     // Catch:{ Throwable -> 0x027a }
            int r3 = r3 + 500
            r4 = 3000(0xbb8, float:4.204E-42)
            if (r3 < r4) goto L_0x0022
        L_0x0035:
            java.lang.String r12 = com.tencent.bugly.crashreport.crash.jni.b.a((java.lang.String) r31)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r3 = "UNKNOWN"
            if (r14 <= 0) goto L_0x0060
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x027a }
            r1.<init>()     // Catch:{ Throwable -> 0x027a }
            r4 = r29
            r1.append(r4)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r4 = "("
            r1.append(r4)     // Catch:{ Throwable -> 0x027a }
            r5 = r34
            r1.append(r5)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r4 = ")"
            r1.append(r4)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x027a }
            java.lang.String r4 = "KERNEL"
            r11 = r1
            r10 = r3
            r9 = r4
            goto L_0x0094
        L_0x0060:
            r4 = r29
            r5 = r34
            if (r1 <= 0) goto L_0x006c
            android.content.Context r3 = r15.f9025a     // Catch:{ Throwable -> 0x027a }
            java.lang.String r3 = com.tencent.bugly.crashreport.common.info.AppInfo.a((int) r35)     // Catch:{ Throwable -> 0x027a }
        L_0x006c:
            java.lang.String r6 = java.lang.String.valueOf(r35)     // Catch:{ Throwable -> 0x027a }
            boolean r6 = r3.equals(r6)     // Catch:{ Throwable -> 0x027a }
            if (r6 != 0) goto L_0x0091
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x027a }
            r6.<init>()     // Catch:{ Throwable -> 0x027a }
            r6.append(r3)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r3 = "("
            r6.append(r3)     // Catch:{ Throwable -> 0x027a }
            r6.append(r1)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r1 = ")"
            r6.append(r1)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r1 = r6.toString()     // Catch:{ Throwable -> 0x027a }
            r10 = r1
            goto L_0x0092
        L_0x0091:
            r10 = r3
        L_0x0092:
            r11 = r4
            r9 = r5
        L_0x0094:
            com.tencent.bugly.crashreport.common.strategy.a r1 = r15.d     // Catch:{ Throwable -> 0x027a }
            boolean r1 = r1.b()     // Catch:{ Throwable -> 0x027a }
            if (r1 != 0) goto L_0x00a3
            java.lang.String r1 = "no remote but still store!"
            java.lang.Object[] r3 = new java.lang.Object[r13]     // Catch:{ Throwable -> 0x027a }
            com.tencent.bugly.proguard.x.d(r1, r3)     // Catch:{ Throwable -> 0x027a }
        L_0x00a3:
            com.tencent.bugly.crashreport.common.strategy.a r1 = r15.d     // Catch:{ Throwable -> 0x027a }
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r1 = r1.c()     // Catch:{ Throwable -> 0x027a }
            boolean r1 = r1.g     // Catch:{ Throwable -> 0x027a }
            if (r1 != 0) goto L_0x00fa
            com.tencent.bugly.crashreport.common.strategy.a r1 = r15.d     // Catch:{ Throwable -> 0x027a }
            boolean r1 = r1.b()     // Catch:{ Throwable -> 0x027a }
            if (r1 == 0) goto L_0x00fa
            java.lang.String r1 = "crash report was closed by remote , will not upload to Bugly , print local for helpful!"
            java.lang.Object[] r2 = new java.lang.Object[r13]     // Catch:{ Throwable -> 0x027a }
            com.tencent.bugly.proguard.x.e(r1, r2)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r1 = "NATIVE_CRASH"
            java.lang.String r2 = com.tencent.bugly.proguard.z.a()     // Catch:{ Throwable -> 0x027a }
            com.tencent.bugly.crashreport.common.info.a r3 = r15.c     // Catch:{ Throwable -> 0x027a }
            java.lang.String r3 = r3.d     // Catch:{ Throwable -> 0x027a }
            java.lang.Thread r4 = java.lang.Thread.currentThread()     // Catch:{ Throwable -> 0x027a }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x027a }
            r5.<init>()     // Catch:{ Throwable -> 0x027a }
            r5.append(r11)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r6 = "\n"
            r5.append(r6)     // Catch:{ Throwable -> 0x027a }
            r5.append(r0)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r0 = "\n"
            r5.append(r0)     // Catch:{ Throwable -> 0x027a }
            r5.append(r12)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r0 = r5.toString()     // Catch:{ Throwable -> 0x027a }
            r5 = 0
            r23 = r1
            r24 = r2
            r25 = r3
            r26 = r4
            r27 = r0
            r28 = r5
            com.tencent.bugly.crashreport.crash.b.a(r23, r24, r25, r26, r27, r28)     // Catch:{ Throwable -> 0x027a }
            com.tencent.bugly.proguard.z.b((java.lang.String) r32)     // Catch:{ Throwable -> 0x027a }
            return
        L_0x00fa:
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ Throwable -> 0x027a }
            r1.<init>()     // Catch:{ Throwable -> 0x027a }
            if (r2 == 0) goto L_0x0127
            int r3 = r2.length     // Catch:{ Throwable -> 0x027a }
            r4 = 0
        L_0x0103:
            if (r4 >= r3) goto L_0x012e
            r5 = r2[r4]     // Catch:{ Throwable -> 0x027a }
            java.lang.String r6 = "="
            java.lang.String[] r6 = r5.split(r6)     // Catch:{ Throwable -> 0x027a }
            int r7 = r6.length     // Catch:{ Throwable -> 0x027a }
            r8 = 2
            if (r7 != r8) goto L_0x011a
            r5 = r6[r13]     // Catch:{ Throwable -> 0x027a }
            r7 = 1
            r6 = r6[r7]     // Catch:{ Throwable -> 0x027a }
            r1.put(r5, r6)     // Catch:{ Throwable -> 0x027a }
            goto L_0x0124
        L_0x011a:
            java.lang.String r6 = "bad extraMsg %s"
            r7 = 1
            java.lang.Object[] r8 = new java.lang.Object[r7]     // Catch:{ Throwable -> 0x027a }
            r8[r13] = r5     // Catch:{ Throwable -> 0x027a }
            com.tencent.bugly.proguard.x.d(r6, r8)     // Catch:{ Throwable -> 0x027a }
        L_0x0124:
            int r4 = r4 + 1
            goto L_0x0103
        L_0x0127:
            java.lang.String r2 = "not found extraMsg"
            java.lang.Object[] r3 = new java.lang.Object[r13]     // Catch:{ Throwable -> 0x027a }
            com.tencent.bugly.proguard.x.c(r2, r3)     // Catch:{ Throwable -> 0x027a }
        L_0x012e:
            java.lang.String r2 = "ExceptionProcessName"
            java.lang.Object r2 = r1.get(r2)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x027a }
            if (r2 == 0) goto L_0x014a
            int r3 = r2.length()     // Catch:{ Throwable -> 0x027a }
            if (r3 != 0) goto L_0x013f
            goto L_0x014a
        L_0x013f:
            java.lang.String r3 = "crash process name change to %s"
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x027a }
            r5[r13] = r2     // Catch:{ Throwable -> 0x027a }
            com.tencent.bugly.proguard.x.c(r3, r5)     // Catch:{ Throwable -> 0x027a }
            goto L_0x014e
        L_0x014a:
            com.tencent.bugly.crashreport.common.info.a r2 = r15.c     // Catch:{ Throwable -> 0x027a }
            java.lang.String r2 = r2.d     // Catch:{ Throwable -> 0x027a }
        L_0x014e:
            java.lang.String r3 = "ExceptionThreadName"
            java.lang.Object r3 = r1.get(r3)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Throwable -> 0x027a }
            if (r3 == 0) goto L_0x01a9
            int r4 = r3.length()     // Catch:{ Throwable -> 0x027a }
            if (r4 != 0) goto L_0x015f
            goto L_0x01a9
        L_0x015f:
            java.lang.String r4 = "crash thread name change to %s"
            r8 = 1
            java.lang.Object[] r5 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x027a }
            r5[r13] = r3     // Catch:{ Throwable -> 0x027a }
            com.tencent.bugly.proguard.x.c(r4, r5)     // Catch:{ Throwable -> 0x027a }
            java.util.Map r4 = java.lang.Thread.getAllStackTraces()     // Catch:{ Throwable -> 0x027a }
            java.util.Set r4 = r4.keySet()     // Catch:{ Throwable -> 0x027a }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ Throwable -> 0x027a }
        L_0x0175:
            boolean r5 = r4.hasNext()     // Catch:{ Throwable -> 0x027a }
            if (r5 == 0) goto L_0x01cf
            java.lang.Object r5 = r4.next()     // Catch:{ Throwable -> 0x027a }
            java.lang.Thread r5 = (java.lang.Thread) r5     // Catch:{ Throwable -> 0x027a }
            java.lang.String r6 = r5.getName()     // Catch:{ Throwable -> 0x027a }
            boolean r6 = r6.equals(r3)     // Catch:{ Throwable -> 0x027a }
            if (r6 == 0) goto L_0x0175
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x027a }
            r4.<init>()     // Catch:{ Throwable -> 0x027a }
            r4.append(r3)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r3 = "("
            r4.append(r3)     // Catch:{ Throwable -> 0x027a }
            long r5 = r5.getId()     // Catch:{ Throwable -> 0x027a }
            r4.append(r5)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r3 = ")"
            r4.append(r3)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r3 = r4.toString()     // Catch:{ Throwable -> 0x027a }
            goto L_0x01cf
        L_0x01a9:
            r8 = 1
            java.lang.Thread r3 = java.lang.Thread.currentThread()     // Catch:{ Throwable -> 0x027a }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x027a }
            r4.<init>()     // Catch:{ Throwable -> 0x027a }
            java.lang.String r5 = r3.getName()     // Catch:{ Throwable -> 0x027a }
            r4.append(r5)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r5 = "("
            r4.append(r5)     // Catch:{ Throwable -> 0x027a }
            long r5 = r3.getId()     // Catch:{ Throwable -> 0x027a }
            r4.append(r5)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r3 = ")"
            r4.append(r3)     // Catch:{ Throwable -> 0x027a }
            java.lang.String r3 = r4.toString()     // Catch:{ Throwable -> 0x027a }
        L_0x01cf:
            r4 = 1000(0x3e8, double:4.94E-321)
            long r6 = r25 * r4
            long r4 = r27 / r4
            r16 = 0
            long r4 = r4 + r6
            java.lang.String r6 = "SysLogPath"
            java.lang.Object r1 = r1.get(r6)     // Catch:{ Throwable -> 0x027a }
            r16 = r1
            java.lang.String r16 = (java.lang.String) r16     // Catch:{ Throwable -> 0x027a }
            r17 = 0
            r18 = 0
            r19 = 1
            r1 = r22
            r6 = r11
            r7 = r30
            r8 = r12
            r20 = r11
            r11 = r32
            r21 = r12
            r12 = r16
            r13 = r39
            r14 = r17
            r15 = r18
            r16 = r19
            com.tencent.bugly.crashreport.crash.CrashDetailBean r1 = r1.packageCrashDatas(r2, r3, r4, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)     // Catch:{ Throwable -> 0x0276 }
            if (r1 != 0) goto L_0x020d
            java.lang.String r0 = "pkg crash datas fail!"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0276 }
            com.tencent.bugly.proguard.x.e(r0, r1)     // Catch:{ Throwable -> 0x0276 }
            return
        L_0x020d:
            java.lang.String r2 = "NATIVE_CRASH"
            java.lang.String r3 = com.tencent.bugly.proguard.z.a()     // Catch:{ Throwable -> 0x0276 }
            r4 = r22
            com.tencent.bugly.crashreport.common.info.a r5 = r4.c     // Catch:{ Throwable -> 0x0274 }
            java.lang.String r5 = r5.d     // Catch:{ Throwable -> 0x0274 }
            java.lang.Thread r6 = java.lang.Thread.currentThread()     // Catch:{ Throwable -> 0x0274 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0274 }
            r7.<init>()     // Catch:{ Throwable -> 0x0274 }
            r8 = r20
            r7.append(r8)     // Catch:{ Throwable -> 0x0274 }
            java.lang.String r8 = "\n"
            r7.append(r8)     // Catch:{ Throwable -> 0x0274 }
            r7.append(r0)     // Catch:{ Throwable -> 0x0274 }
            java.lang.String r0 = "\n"
            r7.append(r0)     // Catch:{ Throwable -> 0x0274 }
            r0 = r21
            r7.append(r0)     // Catch:{ Throwable -> 0x0274 }
            java.lang.String r0 = r7.toString()     // Catch:{ Throwable -> 0x0274 }
            r23 = r2
            r24 = r3
            r25 = r5
            r26 = r6
            r27 = r0
            r28 = r1
            com.tencent.bugly.crashreport.crash.b.a(r23, r24, r25, r26, r27, r28)     // Catch:{ Throwable -> 0x0274 }
            com.tencent.bugly.crashreport.crash.b r0 = r4.b     // Catch:{ Throwable -> 0x0274 }
            r2 = r33
            boolean r0 = r0.a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r1, (int) r2)     // Catch:{ Throwable -> 0x0274 }
            if (r0 != 0) goto L_0x025f
            com.tencent.bugly.crashreport.crash.b r0 = r4.b     // Catch:{ Throwable -> 0x0274 }
            r2 = 3000(0xbb8, double:1.482E-320)
            r5 = 1
            r0.a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r1, (long) r2, (boolean) r5)     // Catch:{ Throwable -> 0x0274 }
            goto L_0x0260
        L_0x025f:
            r5 = 1
        L_0x0260:
            com.tencent.bugly.crashreport.crash.b r0 = r4.b     // Catch:{ Throwable -> 0x0274 }
            r0.b((com.tencent.bugly.crashreport.crash.CrashDetailBean) r1)     // Catch:{ Throwable -> 0x0274 }
            r0 = 0
            com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler r1 = com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.getInstance()     // Catch:{ Throwable -> 0x0274 }
            if (r1 == 0) goto L_0x0270
            java.lang.String r0 = r1.getDumpFilePath()     // Catch:{ Throwable -> 0x0274 }
        L_0x0270:
            com.tencent.bugly.crashreport.crash.jni.b.a((boolean) r5, (java.lang.String) r0)     // Catch:{ Throwable -> 0x0274 }
            return
        L_0x0274:
            r0 = move-exception
            goto L_0x027c
        L_0x0276:
            r0 = move-exception
            r4 = r22
            goto L_0x027c
        L_0x027a:
            r0 = move-exception
            r4 = r15
        L_0x027c:
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0285
            r0.printStackTrace()
        L_0x0285:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.a.handleNativeException2(int, int, long, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, int, int, int, java.lang.String, java.lang.String, java.lang.String[]):void");
    }
}
