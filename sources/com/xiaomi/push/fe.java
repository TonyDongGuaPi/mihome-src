package com.xiaomi.push;

import android.content.Context;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMJobService;
import com.xiaomi.stat.c.c;

public final class fe {

    /* renamed from: a  reason: collision with root package name */
    private static a f12731a;
    private static final String b = XMJobService.class.getCanonicalName();
    private static int c = 0;

    interface a {
        void a();

        void a(boolean z);

        boolean b();
    }

    public static synchronized void a() {
        synchronized (fe.class) {
            if (f12731a != null) {
                b.c("stop alarm.");
                f12731a.a();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005e, code lost:
        if (b.equals(com.xiaomi.push.t.a(r8, r5.name).getSuperclass().getCanonicalName()) != false) goto L_0x0048;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00cc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r8) {
        /*
            android.content.Context r8 = r8.getApplicationContext()
            java.lang.String r0 = "com.xiaomi.xmsf"
            java.lang.String r1 = r8.getPackageName()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0019
            com.xiaomi.push.ff r0 = new com.xiaomi.push.ff
            r0.<init>(r8)
        L_0x0015:
            f12731a = r0
            goto L_0x00d7
        L_0x0019:
            android.content.pm.PackageManager r0 = r8.getPackageManager()
            r1 = 0
            java.lang.String r2 = r8.getPackageName()     // Catch:{ Exception -> 0x0081 }
            r3 = 4
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r2, r3)     // Catch:{ Exception -> 0x0081 }
            android.content.pm.ServiceInfo[] r2 = r0.services     // Catch:{ Exception -> 0x0081 }
            r3 = 1
            if (r2 == 0) goto L_0x007f
            android.content.pm.ServiceInfo[] r0 = r0.services     // Catch:{ Exception -> 0x0081 }
            int r2 = r0.length     // Catch:{ Exception -> 0x0081 }
            r4 = 0
        L_0x0030:
            if (r1 >= r2) goto L_0x009b
            r5 = r0[r1]     // Catch:{ Exception -> 0x007d }
            java.lang.String r6 = "android.permission.BIND_JOB_SERVICE"
            java.lang.String r7 = r5.permission     // Catch:{ Exception -> 0x007d }
            boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x007d }
            if (r6 == 0) goto L_0x0064
            java.lang.String r6 = b     // Catch:{ Exception -> 0x007d }
            java.lang.String r7 = r5.name     // Catch:{ Exception -> 0x007d }
            boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x007d }
            if (r6 == 0) goto L_0x004a
        L_0x0048:
            r4 = 1
            goto L_0x0061
        L_0x004a:
            java.lang.String r6 = r5.name     // Catch:{ Exception -> 0x0061 }
            java.lang.Class r6 = com.xiaomi.push.t.a(r8, r6)     // Catch:{ Exception -> 0x0061 }
            java.lang.String r7 = b     // Catch:{ Exception -> 0x0061 }
            java.lang.Class r6 = r6.getSuperclass()     // Catch:{ Exception -> 0x0061 }
            java.lang.String r6 = r6.getCanonicalName()     // Catch:{ Exception -> 0x0061 }
            boolean r6 = r7.equals(r6)     // Catch:{ Exception -> 0x0061 }
            if (r6 == 0) goto L_0x0061
            goto L_0x0048
        L_0x0061:
            if (r4 != r3) goto L_0x0064
            goto L_0x009b
        L_0x0064:
            java.lang.String r6 = b     // Catch:{ Exception -> 0x007d }
            java.lang.String r7 = r5.name     // Catch:{ Exception -> 0x007d }
            boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x007d }
            if (r6 == 0) goto L_0x007a
            java.lang.String r6 = "android.permission.BIND_JOB_SERVICE"
            java.lang.String r5 = r5.permission     // Catch:{ Exception -> 0x007d }
            boolean r5 = r6.equals(r5)     // Catch:{ Exception -> 0x007d }
            if (r5 == 0) goto L_0x007a
            r4 = 1
            goto L_0x009b
        L_0x007a:
            int r1 = r1 + 1
            goto L_0x0030
        L_0x007d:
            r0 = move-exception
            goto L_0x0083
        L_0x007f:
            r4 = 0
            goto L_0x009b
        L_0x0081:
            r0 = move-exception
            r4 = 0
        L_0x0083:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "check service err : "
            r1.append(r2)
            java.lang.String r0 = r0.getMessage()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r0)
        L_0x009b:
            if (r4 != 0) goto L_0x00cc
            boolean r0 = com.xiaomi.push.t.b(r8)
            if (r0 != 0) goto L_0x00a4
            goto L_0x00cc
        L_0x00a4:
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Should export service: "
            r0.append(r1)
            java.lang.String r1 = b
            r0.append(r1)
            java.lang.String r1 = " with permission "
            r0.append(r1)
            java.lang.String r1 = "android.permission.BIND_JOB_SERVICE"
            r0.append(r1)
            java.lang.String r1 = " in AndroidManifest.xml file"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.<init>(r0)
            throw r8
        L_0x00cc:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            com.xiaomi.push.ff r0 = new com.xiaomi.push.ff
            r0.<init>(r8)
            goto L_0x0015
        L_0x00d7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.fe.a(android.content.Context):void");
    }

    public static synchronized void a(Context context, int i) {
        synchronized (fe.class) {
            int i2 = c;
            if (!c.f23036a.equals(context.getPackageName())) {
                if (i == 2) {
                    c = 2;
                } else {
                    c = 0;
                }
            }
            if (i2 != c && c == 2) {
                a();
                f12731a = new fh(context);
            }
        }
    }

    public static synchronized void a(boolean z) {
        synchronized (fe.class) {
            if (f12731a == null) {
                b.a("timer is not initialized");
                return;
            }
            b.c("register alarm. (" + z + Operators.BRACKET_END_STR);
            f12731a.a(z);
        }
    }

    public static synchronized boolean b() {
        synchronized (fe.class) {
            if (f12731a == null) {
                return false;
            }
            boolean b2 = f12731a.b();
            return b2;
        }
    }
}
