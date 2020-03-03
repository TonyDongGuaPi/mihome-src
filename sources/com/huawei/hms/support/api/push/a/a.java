package com.huawei.hms.support.api.push.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.io.File;
import java.util.List;

public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    private static int f5890a = -1;
    private static final Object b = new Object();

    private static boolean c(Context context) {
        if (com.huawei.hms.support.log.a.a()) {
            com.huawei.hms.support.log.a.a("CommFun", "existFrameworkPush:" + f5890a);
        }
        try {
            if (!new File("/system/framework/" + "hwpush.jar").isFile()) {
                return false;
            }
            if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("CommFun", "push jarFile is exist");
            }
            List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(new Intent().setClassName("android", "com.huawei.android.pushagentproxy.PushService"), 128);
            if (queryIntentServices != null) {
                if (!queryIntentServices.isEmpty()) {
                    if (!com.huawei.hms.support.log.a.b()) {
                        return true;
                    }
                    com.huawei.hms.support.log.a.b("CommFun", "framework push exist, use framework push first");
                    return true;
                }
            }
            if (com.huawei.hms.support.log.a.b()) {
                com.huawei.hms.support.log.a.b("CommFun", "framework push not exist, need vote apk or sdk to support pushservice");
            }
            return false;
        } catch (Exception e) {
            if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.d("CommFun", "get Apk version faild ,Exception e= " + e.toString());
            }
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002e, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
        if (1 != f5890a) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003f, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r5) {
        /*
            boolean r0 = com.huawei.hms.support.log.a.a()
            if (r0 == 0) goto L_0x001e
            java.lang.String r0 = "CommFun"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "existFrameworkPush:"
            r1.append(r2)
            int r2 = f5890a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.huawei.hms.support.log.a.a(r0, r1)
        L_0x001e:
            java.lang.Object r0 = b
            monitor-enter(r0)
            r1 = -1
            int r2 = f5890a     // Catch:{ all -> 0x0041 }
            r3 = 0
            r4 = 1
            if (r1 == r2) goto L_0x002f
            int r5 = f5890a     // Catch:{ all -> 0x0041 }
            if (r4 != r5) goto L_0x002d
            r3 = 1
        L_0x002d:
            monitor-exit(r0)     // Catch:{ all -> 0x0041 }
            return r3
        L_0x002f:
            boolean r5 = c(r5)     // Catch:{ all -> 0x0041 }
            if (r5 == 0) goto L_0x0038
            f5890a = r4     // Catch:{ all -> 0x0041 }
            goto L_0x003a
        L_0x0038:
            f5890a = r3     // Catch:{ all -> 0x0041 }
        L_0x003a:
            monitor-exit(r0)     // Catch:{ all -> 0x0041 }
            int r5 = f5890a
            if (r4 != r5) goto L_0x0040
            r3 = 1
        L_0x0040:
            return r3
        L_0x0041:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0041 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.support.api.push.a.a.a(android.content.Context):boolean");
    }

    public static boolean a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (context.getPackageManager().getApplicationInfo(str, 0) == null) {
                return false;
            }
            if (!com.huawei.hms.support.log.a.a()) {
                return true;
            }
            com.huawei.hms.support.log.a.a("CommFun", str + " is installed");
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        return com.xiaomi.stat.b.m;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(android.content.Context r4) {
        /*
            java.lang.String r0 = "0.0"
            android.content.pm.PackageManager r1 = r4.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0034, Exception -> 0x0012 }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ NameNotFoundException -> 0x0034, Exception -> 0x0012 }
            r2 = 0
            android.content.pm.PackageInfo r4 = r1.getPackageInfo(r4, r2)     // Catch:{ NameNotFoundException -> 0x0034, Exception -> 0x0012 }
            java.lang.String r4 = r4.versionName     // Catch:{ NameNotFoundException -> 0x0034, Exception -> 0x0012 }
            goto L_0x0042
        L_0x0012:
            r4 = move-exception
            boolean r1 = com.huawei.hms.support.log.a.a()
            if (r1 == 0) goto L_0x0041
            java.lang.String r1 = "CommFun"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "getApkVersionName error"
            r2.append(r3)
            java.lang.String r4 = r4.getMessage()
            r2.append(r4)
            java.lang.String r4 = r2.toString()
            com.huawei.hms.support.log.a.d(r1, r4)
            goto L_0x0041
        L_0x0034:
            boolean r4 = com.huawei.hms.support.log.a.a()
            if (r4 == 0) goto L_0x0041
            java.lang.String r4 = "CommFun"
            java.lang.String r1 = "package not exist"
            com.huawei.hms.support.log.a.a(r4, r1)
        L_0x0041:
            r4 = r0
        L_0x0042:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.support.api.push.a.a.b(android.content.Context):java.lang.String");
    }
}
