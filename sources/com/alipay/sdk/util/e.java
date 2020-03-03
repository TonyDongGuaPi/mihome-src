package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.app.statistic.c;
import com.alipay.sdk.util.n;

public class e {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1128a = "failed";
    public static final String b = "scheme_failed";
    /* access modifiers changed from: private */
    public Activity c;
    /* access modifiers changed from: private */
    public IAlixPay d;
    /* access modifiers changed from: private */
    public final Object e = IAlixPay.class;
    private boolean f;
    /* access modifiers changed from: private */
    public a g;
    private ServiceConnection h = new f(this);
    /* access modifiers changed from: private */
    public String i = null;
    private IRemoteServiceCallback j = new h(this);

    public interface a {
        void a();

        void b();
    }

    public e(Activity activity, a aVar) {
        this.c = activity;
        this.g = aVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0056 A[Catch:{ Throwable -> 0x0062 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = ""
            r1 = 0
            com.alipay.sdk.data.a r2 = com.alipay.sdk.data.a.g()     // Catch:{ Throwable -> 0x0062 }
            java.util.List r2 = r2.f()     // Catch:{ Throwable -> 0x0062 }
            com.alipay.sdk.data.a r3 = com.alipay.sdk.data.a.g()     // Catch:{ Throwable -> 0x0062 }
            boolean r3 = r3.p     // Catch:{ Throwable -> 0x0062 }
            if (r3 == 0) goto L_0x0015
            if (r2 != 0) goto L_0x0017
        L_0x0015:
            java.util.List<com.alipay.sdk.data.a$a> r2 = com.alipay.sdk.app.i.f1076a     // Catch:{ Throwable -> 0x0062 }
        L_0x0017:
            android.app.Activity r3 = r5.c     // Catch:{ Throwable -> 0x0062 }
            com.alipay.sdk.util.n$a r2 = com.alipay.sdk.util.n.a((android.content.Context) r3, (java.util.List<com.alipay.sdk.data.a.C0029a>) r2)     // Catch:{ Throwable -> 0x0062 }
            if (r2 == 0) goto L_0x005f
            boolean r3 = r2.a()     // Catch:{ Throwable -> 0x0062 }
            if (r3 != 0) goto L_0x005f
            boolean r3 = r2.b()     // Catch:{ Throwable -> 0x0062 }
            if (r3 == 0) goto L_0x002c
            goto L_0x005f
        L_0x002c:
            android.content.pm.PackageInfo r3 = r2.f1138a     // Catch:{ Throwable -> 0x0062 }
            boolean r3 = com.alipay.sdk.util.n.a((android.content.pm.PackageInfo) r3)     // Catch:{ Throwable -> 0x0062 }
            if (r3 == 0) goto L_0x0037
            java.lang.String r2 = "failed"
            return r2
        L_0x0037:
            android.content.pm.PackageInfo r3 = r2.f1138a     // Catch:{ Throwable -> 0x0062 }
            if (r3 == 0) goto L_0x004d
            java.lang.String r3 = "com.eg.android.AlipayGphone"
            android.content.pm.PackageInfo r4 = r2.f1138a     // Catch:{ Throwable -> 0x0062 }
            java.lang.String r4 = r4.packageName     // Catch:{ Throwable -> 0x0062 }
            boolean r3 = r3.equals(r4)     // Catch:{ Throwable -> 0x0062 }
            if (r3 == 0) goto L_0x0048
            goto L_0x004d
        L_0x0048:
            android.content.pm.PackageInfo r3 = r2.f1138a     // Catch:{ Throwable -> 0x0062 }
            java.lang.String r3 = r3.packageName     // Catch:{ Throwable -> 0x0062 }
            goto L_0x0051
        L_0x004d:
            java.lang.String r3 = com.alipay.sdk.util.n.a()     // Catch:{ Throwable -> 0x0062 }
        L_0x0051:
            r0 = r3
            android.content.pm.PackageInfo r3 = r2.f1138a     // Catch:{ Throwable -> 0x0062 }
            if (r3 == 0) goto L_0x005b
            android.content.pm.PackageInfo r3 = r2.f1138a     // Catch:{ Throwable -> 0x0062 }
            int r3 = r3.versionCode     // Catch:{ Throwable -> 0x0062 }
            r1 = r3
        L_0x005b:
            r5.a((com.alipay.sdk.util.n.a) r2)     // Catch:{ Throwable -> 0x0062 }
            goto L_0x006a
        L_0x005f:
            java.lang.String r2 = "failed"
            return r2
        L_0x0062:
            r2 = move-exception
            java.lang.String r3 = "biz"
            java.lang.String r4 = "CheckClientSignEx"
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r3, (java.lang.String) r4, (java.lang.Throwable) r2)
        L_0x006a:
            java.lang.String r6 = r5.a(r6, r0, r1)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.e.a(java.lang.String):java.lang.String");
    }

    private void a(n.a aVar) throws InterruptedException {
        PackageInfo packageInfo;
        if (aVar != null && (packageInfo = aVar.f1138a) != null) {
            String str = packageInfo.packageName;
            Intent intent = new Intent();
            intent.setClassName(str, "com.alipay.android.app.TransProcessPayActivity");
            try {
                this.c.startActivity(intent);
            } catch (Throwable th) {
                com.alipay.sdk.app.statistic.a.a(c.b, c.H, th);
            }
            Thread.sleep(200);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0103, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0104, code lost:
        com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, "BSPEx", r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x010e, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x010f, code lost:
        com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, "BSPWaiting", (java.lang.Throwable) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return com.alipay.sdk.app.j.a(com.alipay.sdk.app.k.PAY_WAITTING.a(), com.alipay.sdk.app.k.PAY_WAITTING.b(), "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return b;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x010e A[ExcHandler: InterruptedException (r6v1 'e' java.lang.InterruptedException A[CUSTOM_DECLARE]), Splitter:B:9:0x0044] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.lang.String r6, java.lang.String r7, int r8) {
        /*
            r5 = this;
            java.lang.String r0 = r5.a((java.lang.String) r6, (java.lang.String) r7)
            java.lang.String r1 = "failed"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0129
            java.lang.String r1 = "com.eg.android.AlipayGphone"
            boolean r1 = r1.equals(r7)
            if (r1 == 0) goto L_0x0129
            r1 = 125(0x7d, float:1.75E-43)
            if (r8 <= r1) goto L_0x0129
            com.alipay.sdk.data.a r8 = com.alipay.sdk.data.a.g()
            boolean r8 = r8.b()
            if (r8 == 0) goto L_0x0129
            java.util.concurrent.CountDownLatch r8 = new java.util.concurrent.CountDownLatch
            r0 = 1
            r8.<init>(r0)
            r0 = 32
            java.lang.String r0 = com.alipay.sdk.util.n.a((int) r0)
            java.lang.String r1 = "biz"
            java.lang.String r2 = "BSPStart"
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r1, (java.lang.String) r2, (java.lang.String) r0)
            com.alipay.sdk.util.g r1 = new com.alipay.sdk.util.g
            r1.<init>(r5, r8)
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.ref.WeakReference<com.alipay.sdk.app.AlipayResultActivity$b>> r2 = com.alipay.sdk.app.AlipayResultActivity.d
            java.lang.ref.WeakReference r3 = new java.lang.ref.WeakReference
            r3.<init>(r1)
            r2.put(r0, r3)
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            r1.<init>()     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            java.lang.String r2 = "sourcePid"
            int r3 = android.os.Binder.getCallingPid()     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            r1.put(r2, r3)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            java.lang.String r2 = "external_info"
            r1.put(r2, r6)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            java.lang.String r6 = "pkgName"
            android.app.Activity r2 = r5.c     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            java.lang.String r2 = r2.getPackageName()     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            r1.put(r6, r2)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            java.lang.String r6 = "session"
            r1.put(r6, r0)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            java.lang.String r6 = r1.toString()     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            java.lang.String r0 = "UTF-8"
            byte[] r6 = r6.getBytes(r0)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            r0 = 2
            java.lang.String r6 = android.util.Base64.encodeToString(r6, r0)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            r0.<init>()     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            java.lang.String r1 = "alipays://platefromapi/startapp?appId=20000125&mqpSchemePay="
            r0.append(r1)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            java.lang.String r6 = android.net.Uri.encode(r6)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            r0.append(r6)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            java.lang.String r6 = r0.toString()     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            android.content.Intent r0 = new android.content.Intent     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            r0.<init>()     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            r0.setPackage(r7)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            r7 = 268435456(0x10000000, float:2.5243549E-29)
            r0.addFlags(r7)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            android.net.Uri r6 = android.net.Uri.parse(r6)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            r0.setData(r6)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            android.app.Activity r6 = r5.c     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            r6.startActivity(r0)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            com.alipay.sdk.data.a r6 = com.alipay.sdk.data.a.g()     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            android.app.Activity r7 = r5.c     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            android.content.Context r7 = r7.getApplicationContext()     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            r6.a((android.content.Context) r7)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            r8.await()     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            java.lang.String r6 = r5.i     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            java.lang.String r7 = "unknown"
            java.util.Map r8 = com.alipay.sdk.util.l.a(r6)     // Catch:{ Throwable -> 0x00d1, InterruptedException -> 0x010e }
            java.lang.String r0 = "resultStatus"
            java.lang.Object r8 = r8.get(r0)     // Catch:{ Throwable -> 0x00d1, InterruptedException -> 0x010e }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ Throwable -> 0x00d1, InterruptedException -> 0x010e }
            if (r8 != 0) goto L_0x00cf
            java.lang.String r7 = "null"
            goto L_0x00d9
        L_0x00ca:
            r7 = move-exception
            r4 = r8
            r8 = r7
            r7 = r4
            goto L_0x00d2
        L_0x00cf:
            r7 = r8
            goto L_0x00d9
        L_0x00d1:
            r8 = move-exception
        L_0x00d2:
            java.lang.String r0 = "biz"
            java.lang.String r1 = "BSPStatEx"
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r0, (java.lang.String) r1, (java.lang.Throwable) r8)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
        L_0x00d9:
            java.lang.String r8 = "biz"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            r0.<init>()     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            java.lang.String r1 = "BSPDone-"
            r0.append(r1)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            r0.append(r7)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            java.lang.String r7 = r0.toString()     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            java.lang.String r0 = ""
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r8, (java.lang.String) r7, (java.lang.String) r0)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            if (r7 == 0) goto L_0x0128
            java.lang.String r6 = "biz"
            java.lang.String r7 = "BSPEmpty"
            java.lang.String r8 = ""
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r8)     // Catch:{ InterruptedException -> 0x010e, Throwable -> 0x0103 }
            java.lang.String r6 = "scheme_failed"
            goto L_0x0128
        L_0x0103:
            r6 = move-exception
            java.lang.String r7 = "biz"
            java.lang.String r8 = "BSPEx"
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r7, (java.lang.String) r8, (java.lang.Throwable) r6)
            java.lang.String r6 = "scheme_failed"
            goto L_0x0128
        L_0x010e:
            r6 = move-exception
            java.lang.String r7 = "biz"
            java.lang.String r8 = "BSPWaiting"
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r7, (java.lang.String) r8, (java.lang.Throwable) r6)
            com.alipay.sdk.app.k r6 = com.alipay.sdk.app.k.PAY_WAITTING
            int r6 = r6.a()
            com.alipay.sdk.app.k r7 = com.alipay.sdk.app.k.PAY_WAITTING
            java.lang.String r7 = r7.b()
            java.lang.String r8 = ""
            java.lang.String r6 = com.alipay.sdk.app.j.a(r6, r7, r8)
        L_0x0128:
            return r6
        L_0x0129:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.e.a(java.lang.String, java.lang.String, int):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f0, code lost:
        if (r7.c != null) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00f2, code lost:
        r7.c.setRequestedOrientation(0);
        r7.f = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0132, code lost:
        if (r7.c != null) goto L_0x00f2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0130  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            r0.setPackage(r9)
            java.lang.String r1 = com.alipay.sdk.util.n.a((java.lang.String) r9)
            r0.setAction(r1)
            android.app.Activity r1 = r7.c
            java.lang.String r1 = com.alipay.sdk.util.n.a((android.content.Context) r1, (java.lang.String) r9)
            android.app.Activity r2 = r7.c     // Catch:{ Throwable -> 0x0175 }
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ Throwable -> 0x0175 }
            android.content.ServiceConnection r3 = r7.h     // Catch:{ Throwable -> 0x0175 }
            r4 = 1
            boolean r0 = r2.bindService(r0, r3, r4)     // Catch:{ Throwable -> 0x0175 }
            if (r0 == 0) goto L_0x016d
            java.lang.Object r0 = r7.e
            monitor-enter(r0)
            com.alipay.android.app.IAlixPay r2 = r7.d     // Catch:{ all -> 0x016a }
            if (r2 != 0) goto L_0x0042
            java.lang.Object r2 = r7.e     // Catch:{ InterruptedException -> 0x003a }
            com.alipay.sdk.data.a r3 = com.alipay.sdk.data.a.g()     // Catch:{ InterruptedException -> 0x003a }
            int r3 = r3.a()     // Catch:{ InterruptedException -> 0x003a }
            long r5 = (long) r3     // Catch:{ InterruptedException -> 0x003a }
            r2.wait(r5)     // Catch:{ InterruptedException -> 0x003a }
            goto L_0x0042
        L_0x003a:
            r2 = move-exception
            java.lang.String r3 = "biz"
            java.lang.String r5 = "BindWaitTimeoutEx"
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r3, (java.lang.String) r5, (java.lang.Throwable) r2)     // Catch:{ all -> 0x016a }
        L_0x0042:
            monitor-exit(r0)     // Catch:{ all -> 0x016a }
            r0 = 0
            r2 = 0
            com.alipay.android.app.IAlixPay r3 = r7.d     // Catch:{ Throwable -> 0x00fc }
            if (r3 != 0) goto L_0x00a1
            android.app.Activity r8 = r7.c     // Catch:{ Throwable -> 0x00fc }
            java.lang.String r8 = com.alipay.sdk.util.n.a((android.content.Context) r8, (java.lang.String) r9)     // Catch:{ Throwable -> 0x00fc }
            java.lang.String r9 = "biz"
            java.lang.String r3 = "ClientBindFailed"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00fc }
            r4.<init>()     // Catch:{ Throwable -> 0x00fc }
            r4.append(r1)     // Catch:{ Throwable -> 0x00fc }
            java.lang.String r1 = "|"
            r4.append(r1)     // Catch:{ Throwable -> 0x00fc }
            r4.append(r8)     // Catch:{ Throwable -> 0x00fc }
            java.lang.String r8 = r4.toString()     // Catch:{ Throwable -> 0x00fc }
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r9, (java.lang.String) r3, (java.lang.String) r8)     // Catch:{ Throwable -> 0x00fc }
            java.lang.String r8 = "failed"
            com.alipay.android.app.IAlixPay r9 = r7.d     // Catch:{ Throwable -> 0x0075 }
            com.alipay.android.app.IRemoteServiceCallback r1 = r7.j     // Catch:{ Throwable -> 0x0075 }
            r9.unregisterCallback(r1)     // Catch:{ Throwable -> 0x0075 }
            goto L_0x0079
        L_0x0075:
            r9 = move-exception
            com.alipay.sdk.util.c.a(r9)
        L_0x0079:
            android.app.Activity r9 = r7.c     // Catch:{ Throwable -> 0x0085 }
            android.content.Context r9 = r9.getApplicationContext()     // Catch:{ Throwable -> 0x0085 }
            android.content.ServiceConnection r1 = r7.h     // Catch:{ Throwable -> 0x0085 }
            r9.unbindService(r1)     // Catch:{ Throwable -> 0x0085 }
            goto L_0x0089
        L_0x0085:
            r9 = move-exception
            com.alipay.sdk.util.c.a(r9)
        L_0x0089:
            r7.g = r2
            r7.j = r2
            r7.h = r2
            r7.d = r2
            boolean r9 = r7.f
            if (r9 == 0) goto L_0x00a0
            android.app.Activity r9 = r7.c
            if (r9 == 0) goto L_0x00a0
            android.app.Activity r9 = r7.c
            r9.setRequestedOrientation(r0)
            r7.f = r0
        L_0x00a0:
            return r8
        L_0x00a1:
            com.alipay.sdk.util.e$a r9 = r7.g     // Catch:{ Throwable -> 0x00fc }
            if (r9 == 0) goto L_0x00aa
            com.alipay.sdk.util.e$a r9 = r7.g     // Catch:{ Throwable -> 0x00fc }
            r9.a()     // Catch:{ Throwable -> 0x00fc }
        L_0x00aa:
            android.app.Activity r9 = r7.c     // Catch:{ Throwable -> 0x00fc }
            int r9 = r9.getRequestedOrientation()     // Catch:{ Throwable -> 0x00fc }
            if (r9 != 0) goto L_0x00b9
            android.app.Activity r9 = r7.c     // Catch:{ Throwable -> 0x00fc }
            r9.setRequestedOrientation(r4)     // Catch:{ Throwable -> 0x00fc }
            r7.f = r4     // Catch:{ Throwable -> 0x00fc }
        L_0x00b9:
            com.alipay.android.app.IAlixPay r9 = r7.d     // Catch:{ Throwable -> 0x00fc }
            com.alipay.android.app.IRemoteServiceCallback r1 = r7.j     // Catch:{ Throwable -> 0x00fc }
            r9.registerCallback(r1)     // Catch:{ Throwable -> 0x00fc }
            com.alipay.android.app.IAlixPay r9 = r7.d     // Catch:{ Throwable -> 0x00fc }
            java.lang.String r8 = r9.Pay(r8)     // Catch:{ Throwable -> 0x00fc }
            com.alipay.android.app.IAlixPay r9 = r7.d     // Catch:{ Throwable -> 0x00ce }
            com.alipay.android.app.IRemoteServiceCallback r1 = r7.j     // Catch:{ Throwable -> 0x00ce }
            r9.unregisterCallback(r1)     // Catch:{ Throwable -> 0x00ce }
            goto L_0x00d2
        L_0x00ce:
            r9 = move-exception
            com.alipay.sdk.util.c.a(r9)
        L_0x00d2:
            android.app.Activity r9 = r7.c     // Catch:{ Throwable -> 0x00de }
            android.content.Context r9 = r9.getApplicationContext()     // Catch:{ Throwable -> 0x00de }
            android.content.ServiceConnection r1 = r7.h     // Catch:{ Throwable -> 0x00de }
            r9.unbindService(r1)     // Catch:{ Throwable -> 0x00de }
            goto L_0x00e2
        L_0x00de:
            r9 = move-exception
            com.alipay.sdk.util.c.a(r9)
        L_0x00e2:
            r7.g = r2
            r7.j = r2
            r7.h = r2
            r7.d = r2
            boolean r9 = r7.f
            if (r9 == 0) goto L_0x0135
            android.app.Activity r9 = r7.c
            if (r9 == 0) goto L_0x0135
        L_0x00f2:
            android.app.Activity r9 = r7.c
            r9.setRequestedOrientation(r0)
            r7.f = r0
            goto L_0x0135
        L_0x00fa:
            r8 = move-exception
            goto L_0x0136
        L_0x00fc:
            r8 = move-exception
            java.lang.String r9 = "biz"
            java.lang.String r1 = "ClientBindException"
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r9, (java.lang.String) r1, (java.lang.Throwable) r8)     // Catch:{ all -> 0x00fa }
            java.lang.String r8 = com.alipay.sdk.app.j.c()     // Catch:{ all -> 0x00fa }
            com.alipay.android.app.IAlixPay r9 = r7.d     // Catch:{ Throwable -> 0x0110 }
            com.alipay.android.app.IRemoteServiceCallback r1 = r7.j     // Catch:{ Throwable -> 0x0110 }
            r9.unregisterCallback(r1)     // Catch:{ Throwable -> 0x0110 }
            goto L_0x0114
        L_0x0110:
            r9 = move-exception
            com.alipay.sdk.util.c.a(r9)
        L_0x0114:
            android.app.Activity r9 = r7.c     // Catch:{ Throwable -> 0x0120 }
            android.content.Context r9 = r9.getApplicationContext()     // Catch:{ Throwable -> 0x0120 }
            android.content.ServiceConnection r1 = r7.h     // Catch:{ Throwable -> 0x0120 }
            r9.unbindService(r1)     // Catch:{ Throwable -> 0x0120 }
            goto L_0x0124
        L_0x0120:
            r9 = move-exception
            com.alipay.sdk.util.c.a(r9)
        L_0x0124:
            r7.g = r2
            r7.j = r2
            r7.h = r2
            r7.d = r2
            boolean r9 = r7.f
            if (r9 == 0) goto L_0x0135
            android.app.Activity r9 = r7.c
            if (r9 == 0) goto L_0x0135
            goto L_0x00f2
        L_0x0135:
            return r8
        L_0x0136:
            com.alipay.android.app.IAlixPay r9 = r7.d     // Catch:{ Throwable -> 0x013e }
            com.alipay.android.app.IRemoteServiceCallback r1 = r7.j     // Catch:{ Throwable -> 0x013e }
            r9.unregisterCallback(r1)     // Catch:{ Throwable -> 0x013e }
            goto L_0x0142
        L_0x013e:
            r9 = move-exception
            com.alipay.sdk.util.c.a(r9)
        L_0x0142:
            android.app.Activity r9 = r7.c     // Catch:{ Throwable -> 0x014e }
            android.content.Context r9 = r9.getApplicationContext()     // Catch:{ Throwable -> 0x014e }
            android.content.ServiceConnection r1 = r7.h     // Catch:{ Throwable -> 0x014e }
            r9.unbindService(r1)     // Catch:{ Throwable -> 0x014e }
            goto L_0x0152
        L_0x014e:
            r9 = move-exception
            com.alipay.sdk.util.c.a(r9)
        L_0x0152:
            r7.g = r2
            r7.j = r2
            r7.h = r2
            r7.d = r2
            boolean r9 = r7.f
            if (r9 == 0) goto L_0x0169
            android.app.Activity r9 = r7.c
            if (r9 == 0) goto L_0x0169
            android.app.Activity r9 = r7.c
            r9.setRequestedOrientation(r0)
            r7.f = r0
        L_0x0169:
            throw r8
        L_0x016a:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x016a }
            throw r8
        L_0x016d:
            java.lang.Throwable r8 = new java.lang.Throwable     // Catch:{ Throwable -> 0x0175 }
            java.lang.String r9 = "bindService fail"
            r8.<init>(r9)     // Catch:{ Throwable -> 0x0175 }
            throw r8     // Catch:{ Throwable -> 0x0175 }
        L_0x0175:
            r8 = move-exception
            java.lang.String r9 = "biz"
            java.lang.String r0 = "ClientBindServiceFailed"
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r9, (java.lang.String) r0, (java.lang.Throwable) r8)
            java.lang.String r8 = "failed"
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.e.a(java.lang.String, java.lang.String):java.lang.String");
    }

    public void a() {
        this.c = null;
    }
}
