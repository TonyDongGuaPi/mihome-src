package com.tencent.bugly.crashreport.biz;

import android.app.Application;
import android.content.Context;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;

public class b {

    /* renamed from: a  reason: collision with root package name */
    public static a f8991a = null;
    private static boolean b = false;
    /* access modifiers changed from: private */
    public static int c = 10;
    /* access modifiers changed from: private */
    public static long d = 300000;
    /* access modifiers changed from: private */
    public static long e = 30000;
    /* access modifiers changed from: private */
    public static long f = 0;
    /* access modifiers changed from: private */
    public static int g = 0;
    /* access modifiers changed from: private */
    public static long h = 0;
    /* access modifiers changed from: private */
    public static long i = 0;
    /* access modifiers changed from: private */
    public static long j = 0;
    private static Application.ActivityLifecycleCallbacks k = null;
    /* access modifiers changed from: private */
    public static Class<?> l = null;
    /* access modifiers changed from: private */
    public static boolean m = true;

    static /* synthetic */ String a(String str, String str2) {
        return z.a() + "  " + str + "  " + str2 + "\n";
    }

    static /* synthetic */ int g() {
        int i2 = g;
        g = i2 + 1;
        return i2;
    }

    /* JADX WARNING: type inference failed for: r14v11, types: [android.content.Context] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0068 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0069  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void c(android.content.Context r14, com.tencent.bugly.BuglyStrategy r15) {
        /*
            r0 = 0
            r1 = 1
            if (r15 == 0) goto L_0x000d
            boolean r2 = r15.recordUserInfoOnceADay()
            boolean r15 = r15.isEnableUserInfo()
            goto L_0x000f
        L_0x000d:
            r15 = 1
            r2 = 0
        L_0x000f:
            r3 = 0
            if (r2 == 0) goto L_0x006a
            com.tencent.bugly.crashreport.common.info.a r15 = com.tencent.bugly.crashreport.common.info.a.a((android.content.Context) r14)
            java.lang.String r2 = r15.d
            com.tencent.bugly.crashreport.biz.a r5 = f8991a
            java.util.List r2 = r5.a((java.lang.String) r2)
            if (r2 == 0) goto L_0x0065
            r5 = 0
        L_0x0022:
            int r6 = r2.size()
            if (r5 >= r6) goto L_0x0065
            java.lang.Object r6 = r2.get(r5)
            com.tencent.bugly.crashreport.biz.UserInfoBean r6 = (com.tencent.bugly.crashreport.biz.UserInfoBean) r6
            java.lang.String r7 = r6.n
            java.lang.String r8 = r15.j
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x0062
            int r7 = r6.b
            if (r7 != r1) goto L_0x0062
            long r7 = com.tencent.bugly.proguard.z.b()
            int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r9 <= 0) goto L_0x0065
            long r9 = r6.e
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 < 0) goto L_0x0062
            long r5 = r6.f
            int r15 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r15 > 0) goto L_0x0060
            com.tencent.bugly.crashreport.biz.a r15 = f8991a
            com.tencent.bugly.proguard.w r2 = com.tencent.bugly.proguard.w.a()
            if (r2 == 0) goto L_0x0060
            com.tencent.bugly.crashreport.biz.a$2 r5 = new com.tencent.bugly.crashreport.biz.a$2
            r5.<init>()
            r2.a(r5)
        L_0x0060:
            r15 = 0
            goto L_0x0066
        L_0x0062:
            int r5 = r5 + 1
            goto L_0x0022
        L_0x0065:
            r15 = 1
        L_0x0066:
            if (r15 != 0) goto L_0x0069
            return
        L_0x0069:
            r15 = 0
        L_0x006a:
            com.tencent.bugly.crashreport.common.info.a r2 = com.tencent.bugly.crashreport.common.info.a.b()
            r5 = 0
            if (r2 == 0) goto L_0x00b0
            java.lang.Thread r6 = java.lang.Thread.currentThread()
            java.lang.StackTraceElement[] r6 = r6.getStackTrace()
            int r7 = r6.length
            r9 = r5
            r8 = 0
            r10 = 0
        L_0x007d:
            if (r8 >= r7) goto L_0x00a1
            r11 = r6[r8]
            java.lang.String r12 = r11.getMethodName()
            java.lang.String r13 = "onCreate"
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x0091
            java.lang.String r9 = r11.getClassName()
        L_0x0091:
            java.lang.String r11 = r11.getClassName()
            java.lang.String r12 = "android.app.Activity"
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x009e
            r10 = 1
        L_0x009e:
            int r8 = r8 + 1
            goto L_0x007d
        L_0x00a1:
            if (r9 == 0) goto L_0x00ac
            if (r10 == 0) goto L_0x00a9
            r2.a((boolean) r1)
            goto L_0x00ae
        L_0x00a9:
            java.lang.String r9 = "background"
            goto L_0x00ae
        L_0x00ac:
            java.lang.String r9 = "unknown"
        L_0x00ae:
            r2.p = r9
        L_0x00b0:
            if (r15 == 0) goto L_0x00e4
            int r15 = android.os.Build.VERSION.SDK_INT
            r2 = 14
            if (r15 < r2) goto L_0x00e4
            android.content.Context r15 = r14.getApplicationContext()
            boolean r15 = r15 instanceof android.app.Application
            if (r15 == 0) goto L_0x00c7
            android.content.Context r14 = r14.getApplicationContext()
            r5 = r14
            android.app.Application r5 = (android.app.Application) r5
        L_0x00c7:
            if (r5 == 0) goto L_0x00e4
            android.app.Application$ActivityLifecycleCallbacks r14 = k     // Catch:{ Exception -> 0x00da }
            if (r14 != 0) goto L_0x00d4
            com.tencent.bugly.crashreport.biz.b$2 r14 = new com.tencent.bugly.crashreport.biz.b$2     // Catch:{ Exception -> 0x00da }
            r14.<init>()     // Catch:{ Exception -> 0x00da }
            k = r14     // Catch:{ Exception -> 0x00da }
        L_0x00d4:
            android.app.Application$ActivityLifecycleCallbacks r14 = k     // Catch:{ Exception -> 0x00da }
            r5.registerActivityLifecycleCallbacks(r14)     // Catch:{ Exception -> 0x00da }
            goto L_0x00e4
        L_0x00da:
            r14 = move-exception
            boolean r15 = com.tencent.bugly.proguard.x.a(r14)
            if (r15 != 0) goto L_0x00e4
            r14.printStackTrace()
        L_0x00e4:
            boolean r14 = m
            if (r14 == 0) goto L_0x0110
            long r14 = java.lang.System.currentTimeMillis()
            i = r14
            com.tencent.bugly.crashreport.biz.a r14 = f8991a
            r14.a((int) r1, (boolean) r0, (long) r3)
            java.lang.String r14 = "[session] launch app, new start"
            java.lang.Object[] r15 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.x.a(r14, r15)
            com.tencent.bugly.crashreport.biz.a r14 = f8991a
            r14.a()
            com.tencent.bugly.crashreport.biz.a r14 = f8991a
            r0 = 21600000(0x1499700, double:1.0671818E-316)
            com.tencent.bugly.proguard.w r15 = com.tencent.bugly.proguard.w.a()
            com.tencent.bugly.crashreport.biz.a$c r2 = new com.tencent.bugly.crashreport.biz.a$c
            r2.<init>(r0)
            r15.a(r2, r0)
        L_0x0110:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.biz.b.c(android.content.Context, com.tencent.bugly.BuglyStrategy):void");
    }

    public static void a(final Context context, final BuglyStrategy buglyStrategy) {
        long j2;
        if (!b) {
            m = a.a(context).e;
            f8991a = new a(context, m);
            b = true;
            if (buglyStrategy != null) {
                l = buglyStrategy.getUserInfoActivity();
                j2 = buglyStrategy.getAppReportDelay();
            } else {
                j2 = 0;
            }
            if (j2 <= 0) {
                c(context, buglyStrategy);
            } else {
                w.a().a(new Runnable() {
                    public final void run() {
                        b.c(context, buglyStrategy);
                    }
                }, j2);
            }
        }
    }

    public static void a(long j2) {
        if (j2 < 0) {
            j2 = com.tencent.bugly.crashreport.common.strategy.a.a().c().q;
        }
        f = j2;
    }

    public static void a(StrategyBean strategyBean, boolean z) {
        if (f8991a != null && !z) {
            a aVar = f8991a;
            w a2 = w.a();
            if (a2 != null) {
                a2.a(new Runnable() {
                    public final void run() {
                        try {
                            a.this.c();
                        } catch (Throwable th) {
                            x.a(th);
                        }
                    }
                });
            }
        }
        if (strategyBean != null) {
            if (strategyBean.q > 0) {
                e = strategyBean.q;
            }
            if (strategyBean.w > 0) {
                c = strategyBean.w;
            }
            if (strategyBean.x > 0) {
                d = strategyBean.x;
            }
        }
    }

    public static void a() {
        if (f8991a != null) {
            f8991a.a(2, false, 0);
        }
    }

    /* JADX WARNING: type inference failed for: r3v5, types: [android.content.Context] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r3) {
        /*
            boolean r0 = b
            if (r0 == 0) goto L_0x0037
            if (r3 != 0) goto L_0x0007
            goto L_0x0037
        L_0x0007:
            r0 = 0
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 14
            if (r1 < r2) goto L_0x0033
            android.content.Context r1 = r3.getApplicationContext()
            boolean r1 = r1 instanceof android.app.Application
            if (r1 == 0) goto L_0x001d
            android.content.Context r3 = r3.getApplicationContext()
            r0 = r3
            android.app.Application r0 = (android.app.Application) r0
        L_0x001d:
            if (r0 == 0) goto L_0x0033
            android.app.Application$ActivityLifecycleCallbacks r3 = k     // Catch:{ Exception -> 0x0029 }
            if (r3 == 0) goto L_0x0033
            android.app.Application$ActivityLifecycleCallbacks r3 = k     // Catch:{ Exception -> 0x0029 }
            r0.unregisterActivityLifecycleCallbacks(r3)     // Catch:{ Exception -> 0x0029 }
            goto L_0x0033
        L_0x0029:
            r3 = move-exception
            boolean r0 = com.tencent.bugly.proguard.x.a(r3)
            if (r0 != 0) goto L_0x0033
            r3.printStackTrace()
        L_0x0033:
            r3 = 0
            b = r3
            return
        L_0x0037:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.biz.b.a(android.content.Context):void");
    }
}
