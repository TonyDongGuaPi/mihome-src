package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.utils.j;
import java.nio.channels.FileLock;

class bt {

    /* renamed from: a  reason: collision with root package name */
    private static bt f9172a;
    private static FileLock e;
    private bu b;
    private boolean c;
    private boolean d;

    private bt() {
    }

    public static bt a() {
        if (f9172a == null) {
            synchronized (bt.class) {
                if (f9172a == null) {
                    f9172a = new bt();
                }
            }
        }
        return f9172a;
    }

    public bu a(boolean z) {
        return z ? this.b : c();
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x007b A[Catch:{ NoSuchMethodException -> 0x0059, Throwable -> 0x003d }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0182 A[Catch:{ NoSuchMethodException -> 0x0059, Throwable -> 0x003d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.content.Context r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            r0 = 1
            com.tencent.smtt.sdk.o r1 = com.tencent.smtt.sdk.o.a((boolean) r0)     // Catch:{ all -> 0x018d }
            r2 = 0
            r1.a(r10, r2, r2)     // Catch:{ all -> 0x018d }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x018d }
            r3.<init>()     // Catch:{ all -> 0x018d }
            com.tencent.smtt.sdk.bh r4 = r1.a()     // Catch:{ all -> 0x018d }
            boolean r5 = r1.b()     // Catch:{ all -> 0x018d }
            r6 = 0
            if (r5 == 0) goto L_0x006f
            if (r4 == 0) goto L_0x006f
            boolean r5 = r9.d     // Catch:{ all -> 0x018d }
            if (r5 != 0) goto L_0x0076
            com.tencent.smtt.sdk.bu r5 = new com.tencent.smtt.sdk.bu     // Catch:{ all -> 0x018d }
            com.tencent.smtt.export.external.DexLoader r7 = r4.b()     // Catch:{ all -> 0x018d }
            r5.<init>(r7)     // Catch:{ all -> 0x018d }
            r9.b = r5     // Catch:{ all -> 0x018d }
            com.tencent.smtt.sdk.bu r5 = r9.b     // Catch:{ NoSuchMethodException -> 0x0059, Throwable -> 0x003d }
            boolean r5 = r5.a()     // Catch:{ NoSuchMethodException -> 0x0059, Throwable -> 0x003d }
            r9.c = r5     // Catch:{ NoSuchMethodException -> 0x0059, Throwable -> 0x003d }
            boolean r5 = r9.c     // Catch:{ NoSuchMethodException -> 0x0059, Throwable -> 0x003d }
            if (r5 != 0) goto L_0x005b
            java.lang.String r5 = "can not use X5 by x5corewizard return false"
            r3.append(r5)     // Catch:{ NoSuchMethodException -> 0x0059, Throwable -> 0x003d }
            goto L_0x005b
        L_0x003d:
            r5 = move-exception
            r9.c = r2     // Catch:{ all -> 0x018d }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x018d }
            r7.<init>()     // Catch:{ all -> 0x018d }
            java.lang.String r8 = "can not use x5 by throwable "
            r7.append(r8)     // Catch:{ all -> 0x018d }
            java.lang.String r8 = android.util.Log.getStackTraceString(r5)     // Catch:{ all -> 0x018d }
            r7.append(r8)     // Catch:{ all -> 0x018d }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x018d }
            r3.append(r7)     // Catch:{ all -> 0x018d }
            goto L_0x005c
        L_0x0059:
            r9.c = r0     // Catch:{ all -> 0x018d }
        L_0x005b:
            r5 = r6
        L_0x005c:
            boolean r7 = r9.c     // Catch:{ all -> 0x018d }
            if (r7 == 0) goto L_0x0077
            com.tencent.smtt.sdk.CookieManager r7 = com.tencent.smtt.sdk.CookieManager.getInstance()     // Catch:{ all -> 0x018d }
            r7.a(r10, r0, r0)     // Catch:{ all -> 0x018d }
            com.tencent.smtt.sdk.CookieManager r7 = com.tencent.smtt.sdk.CookieManager.getInstance()     // Catch:{ all -> 0x018d }
            r7.a()     // Catch:{ all -> 0x018d }
            goto L_0x0077
        L_0x006f:
            r9.c = r2     // Catch:{ all -> 0x018d }
            java.lang.String r5 = "can not use X5 by !tbs available"
            r3.append(r5)     // Catch:{ all -> 0x018d }
        L_0x0076:
            r5 = r6
        L_0x0077:
            boolean r7 = r9.c     // Catch:{ all -> 0x018d }
            if (r7 != 0) goto L_0x0182
            java.lang.String r7 = "X5CoreEngine"
            java.lang.String r8 = "mCanUseX5 is false --> report"
            com.tencent.smtt.utils.TbsLog.e(r7, r8)     // Catch:{ all -> 0x018d }
            boolean r7 = r1.b()     // Catch:{ all -> 0x018d }
            if (r7 == 0) goto L_0x013f
            if (r4 == 0) goto L_0x013f
            if (r5 != 0) goto L_0x013f
            com.tencent.smtt.export.external.DexLoader r1 = r4.b()     // Catch:{ Throwable -> 0x00ea }
            if (r1 == 0) goto L_0x009e
            java.lang.String r4 = "com.tencent.tbs.tbsshell.TBSShell"
            java.lang.String r5 = "getLoadFailureDetails"
            java.lang.Class[] r6 = new java.lang.Class[r2]     // Catch:{ Throwable -> 0x00ea }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00ea }
            java.lang.Object r6 = r1.invokeStaticMethod(r4, r5, r6, r2)     // Catch:{ Throwable -> 0x00ea }
        L_0x009e:
            boolean r1 = r6 instanceof java.lang.Throwable     // Catch:{ Throwable -> 0x00ea }
            if (r1 == 0) goto L_0x00d1
            r1 = r6
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ Throwable -> 0x00ea }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ea }
            r2.<init>()     // Catch:{ Throwable -> 0x00ea }
            java.lang.String r4 = "#"
            r2.append(r4)     // Catch:{ Throwable -> 0x00ea }
            java.lang.String r4 = r1.getMessage()     // Catch:{ Throwable -> 0x00ea }
            r2.append(r4)     // Catch:{ Throwable -> 0x00ea }
            java.lang.String r4 = "; cause: "
            r2.append(r4)     // Catch:{ Throwable -> 0x00ea }
            java.lang.Throwable r4 = r1.getCause()     // Catch:{ Throwable -> 0x00ea }
            r2.append(r4)     // Catch:{ Throwable -> 0x00ea }
            java.lang.String r4 = "; th: "
            r2.append(r4)     // Catch:{ Throwable -> 0x00ea }
            r2.append(r1)     // Catch:{ Throwable -> 0x00ea }
            java.lang.String r1 = r2.toString()     // Catch:{ Throwable -> 0x00ea }
            r3.append(r1)     // Catch:{ Throwable -> 0x00ea }
        L_0x00d1:
            boolean r1 = r6 instanceof java.lang.String     // Catch:{ Throwable -> 0x00ea }
            if (r1 == 0) goto L_0x00ee
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ea }
            r1.<init>()     // Catch:{ Throwable -> 0x00ea }
            java.lang.String r2 = "failure detail:"
            r1.append(r2)     // Catch:{ Throwable -> 0x00ea }
            r1.append(r6)     // Catch:{ Throwable -> 0x00ea }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x00ea }
            r3.append(r1)     // Catch:{ Throwable -> 0x00ea }
            goto L_0x00ee
        L_0x00ea:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x018d }
        L_0x00ee:
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x018d }
            java.lang.String r2 = "isPreloadX5Disabled:-10000"
            boolean r1 = r1.contains(r2)     // Catch:{ all -> 0x018d }
            if (r1 == 0) goto L_0x011e
            com.tencent.smtt.sdk.TbsCoreLoadStat r1 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch:{ all -> 0x018d }
            r2 = 408(0x198, float:5.72E-43)
            java.lang.Throwable r4 = new java.lang.Throwable     // Catch:{ all -> 0x018d }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x018d }
            r5.<init>()     // Catch:{ all -> 0x018d }
            java.lang.String r6 = "X5CoreEngine::init, mCanUseX5=false, available true, details: "
            r5.append(r6)     // Catch:{ all -> 0x018d }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x018d }
            r5.append(r3)     // Catch:{ all -> 0x018d }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x018d }
            r4.<init>(r3)     // Catch:{ all -> 0x018d }
        L_0x011a:
            r1.a(r10, r2, r4)     // Catch:{ all -> 0x018d }
            goto L_0x0189
        L_0x011e:
            com.tencent.smtt.sdk.TbsCoreLoadStat r1 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch:{ all -> 0x018d }
            r2 = 407(0x197, float:5.7E-43)
            java.lang.Throwable r4 = new java.lang.Throwable     // Catch:{ all -> 0x018d }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x018d }
            r5.<init>()     // Catch:{ all -> 0x018d }
            java.lang.String r6 = "X5CoreEngine::init, mCanUseX5=false, available true, details: "
            r5.append(r6)     // Catch:{ all -> 0x018d }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x018d }
            r5.append(r3)     // Catch:{ all -> 0x018d }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x018d }
            r4.<init>(r3)     // Catch:{ all -> 0x018d }
            goto L_0x011a
        L_0x013f:
            boolean r1 = r1.b()     // Catch:{ all -> 0x018d }
            if (r1 == 0) goto L_0x0165
            com.tencent.smtt.sdk.TbsCoreLoadStat r1 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch:{ all -> 0x018d }
            r2 = 409(0x199, float:5.73E-43)
            java.lang.Throwable r3 = new java.lang.Throwable     // Catch:{ all -> 0x018d }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x018d }
            r4.<init>()     // Catch:{ all -> 0x018d }
            java.lang.String r6 = "mCanUseX5=false, available true, reason: "
            r4.append(r6)     // Catch:{ all -> 0x018d }
            r4.append(r5)     // Catch:{ all -> 0x018d }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x018d }
            r3.<init>(r4)     // Catch:{ all -> 0x018d }
        L_0x0161:
            r1.a(r10, r2, r3)     // Catch:{ all -> 0x018d }
            goto L_0x0189
        L_0x0165:
            com.tencent.smtt.sdk.TbsCoreLoadStat r1 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch:{ all -> 0x018d }
            r2 = 410(0x19a, float:5.75E-43)
            java.lang.Throwable r3 = new java.lang.Throwable     // Catch:{ all -> 0x018d }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x018d }
            r4.<init>()     // Catch:{ all -> 0x018d }
            java.lang.String r6 = "mCanUseX5=false, available false, reason: "
            r4.append(r6)     // Catch:{ all -> 0x018d }
            r4.append(r5)     // Catch:{ all -> 0x018d }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x018d }
            r3.<init>(r4)     // Catch:{ all -> 0x018d }
            goto L_0x0161
        L_0x0182:
            java.nio.channels.FileLock r1 = e     // Catch:{ all -> 0x018d }
            if (r1 != 0) goto L_0x0189
            r9.b(r10)     // Catch:{ all -> 0x018d }
        L_0x0189:
            r9.d = r0     // Catch:{ all -> 0x018d }
            monitor-exit(r9)
            return
        L_0x018d:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.bt.a(android.content.Context):void");
    }

    public synchronized FileLock b(Context context) {
        if (e != null) {
            return e;
        }
        e = j.e(context);
        FileLock fileLock = e;
        return e;
    }

    public boolean b() {
        if (QbSdk.f9080a) {
            return false;
        }
        return this.c;
    }

    public bu c() {
        if (QbSdk.f9080a) {
            return null;
        }
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        return this.d;
    }
}
