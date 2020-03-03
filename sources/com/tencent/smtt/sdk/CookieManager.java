package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.alipay.mobile.common.logging.EventCategory;
import com.tencent.smtt.utils.q;
import java.util.ArrayList;
import java.util.Map;

public class CookieManager {
    public static String LOGTAG = "CookieManager";
    private static CookieManager d;

    /* renamed from: a  reason: collision with root package name */
    ArrayList<b> f9068a;
    String b;
    a c = a.MODE_NONE;
    private boolean e = false;
    private boolean f = false;

    public enum a {
        MODE_NONE,
        MODE_KEYS,
        MODE_ALL
    }

    class b {

        /* renamed from: a  reason: collision with root package name */
        int f9070a;
        String b;
        String c;
        ValueCallback<Boolean> d;

        b() {
        }
    }

    private CookieManager() {
    }

    public static CookieManager getInstance() {
        if (d == null) {
            synchronized (CookieManager.class) {
                if (d == null) {
                    d = new CookieManager();
                }
            }
        }
        return d;
    }

    public static int getROMCookieDBVersion(Context context) {
        String str;
        int i;
        if (Build.VERSION.SDK_INT >= 11) {
            str = "cookiedb_info";
            i = 4;
        } else {
            str = "cookiedb_info";
            i = 0;
        }
        return context.getSharedPreferences(str, i).getInt("db_version", -1);
    }

    public static void setROMCookieDBVersion(Context context, int i) {
        String str;
        int i2;
        if (Build.VERSION.SDK_INT >= 11) {
            str = "cookiedb_info";
            i2 = 4;
        } else {
            str = "cookiedb_info";
            i2 = 0;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(str, i2).edit();
        edit.putInt("db_version", i);
        edit.commit();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a3, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a() {
        /*
            r10 = this;
            monitor-enter(r10)
            r0 = 1
            r10.f = r0     // Catch:{ all -> 0x00a4 }
            java.util.ArrayList<com.tencent.smtt.sdk.CookieManager$b> r1 = r10.f9068a     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x00a2
            java.util.ArrayList<com.tencent.smtt.sdk.CookieManager$b> r1 = r10.f9068a     // Catch:{ all -> 0x00a4 }
            int r1 = r1.size()     // Catch:{ all -> 0x00a4 }
            if (r1 != 0) goto L_0x0012
            goto L_0x00a2
        L_0x0012:
            com.tencent.smtt.sdk.bt r1 = com.tencent.smtt.sdk.bt.a()     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x0048
            boolean r1 = r1.b()     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x0048
            java.util.ArrayList<com.tencent.smtt.sdk.CookieManager$b> r0 = r10.f9068a     // Catch:{ all -> 0x00a4 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x00a4 }
        L_0x0024:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x009b
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x00a4 }
            com.tencent.smtt.sdk.CookieManager$b r1 = (com.tencent.smtt.sdk.CookieManager.b) r1     // Catch:{ all -> 0x00a4 }
            int r2 = r1.f9070a     // Catch:{ all -> 0x00a4 }
            switch(r2) {
                case 1: goto L_0x003e;
                case 2: goto L_0x0036;
                default: goto L_0x0035;
            }     // Catch:{ all -> 0x00a4 }
        L_0x0035:
            goto L_0x0024
        L_0x0036:
            java.lang.String r2 = r1.b     // Catch:{ all -> 0x00a4 }
            java.lang.String r1 = r1.c     // Catch:{ all -> 0x00a4 }
            r10.setCookie(r2, r1)     // Catch:{ all -> 0x00a4 }
            goto L_0x0024
        L_0x003e:
            java.lang.String r2 = r1.b     // Catch:{ all -> 0x00a4 }
            java.lang.String r3 = r1.c     // Catch:{ all -> 0x00a4 }
            com.tencent.smtt.sdk.ValueCallback<java.lang.Boolean> r1 = r1.d     // Catch:{ all -> 0x00a4 }
            r10.setCookie((java.lang.String) r2, (java.lang.String) r3, (com.tencent.smtt.sdk.ValueCallback<java.lang.Boolean>) r1)     // Catch:{ all -> 0x00a4 }
            goto L_0x0024
        L_0x0048:
            java.util.ArrayList<com.tencent.smtt.sdk.CookieManager$b> r1 = r10.f9068a     // Catch:{ all -> 0x00a4 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00a4 }
        L_0x004e:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x00a4 }
            if (r2 == 0) goto L_0x009b
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x00a4 }
            com.tencent.smtt.sdk.CookieManager$b r2 = (com.tencent.smtt.sdk.CookieManager.b) r2     // Catch:{ all -> 0x00a4 }
            int r3 = r2.f9070a     // Catch:{ all -> 0x00a4 }
            switch(r3) {
                case 1: goto L_0x006c;
                case 2: goto L_0x0060;
                default: goto L_0x005f;
            }     // Catch:{ all -> 0x00a4 }
        L_0x005f:
            goto L_0x004e
        L_0x0060:
            android.webkit.CookieManager r3 = android.webkit.CookieManager.getInstance()     // Catch:{ all -> 0x00a4 }
            java.lang.String r4 = r2.b     // Catch:{ all -> 0x00a4 }
            java.lang.String r2 = r2.c     // Catch:{ all -> 0x00a4 }
            r3.setCookie(r4, r2)     // Catch:{ all -> 0x00a4 }
            goto L_0x004e
        L_0x006c:
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x00a4 }
            r4 = 21
            if (r3 < r4) goto L_0x004e
            android.webkit.CookieManager r3 = android.webkit.CookieManager.getInstance()     // Catch:{ all -> 0x00a4 }
            java.lang.String r4 = "setCookie"
            r5 = 3
            java.lang.Class[] r6 = new java.lang.Class[r5]     // Catch:{ all -> 0x00a4 }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r8 = 0
            r6[r8] = r7     // Catch:{ all -> 0x00a4 }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r0] = r7     // Catch:{ all -> 0x00a4 }
            java.lang.Class<android.webkit.ValueCallback> r7 = android.webkit.ValueCallback.class
            r9 = 2
            r6[r9] = r7     // Catch:{ all -> 0x00a4 }
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x00a4 }
            java.lang.String r7 = r2.b     // Catch:{ all -> 0x00a4 }
            r5[r8] = r7     // Catch:{ all -> 0x00a4 }
            java.lang.String r7 = r2.c     // Catch:{ all -> 0x00a4 }
            r5[r0] = r7     // Catch:{ all -> 0x00a4 }
            com.tencent.smtt.sdk.ValueCallback<java.lang.Boolean> r2 = r2.d     // Catch:{ all -> 0x00a4 }
            r5[r9] = r2     // Catch:{ all -> 0x00a4 }
            com.tencent.smtt.utils.q.a((java.lang.Object) r3, (java.lang.String) r4, (java.lang.Class<?>[]) r6, (java.lang.Object[]) r5)     // Catch:{ all -> 0x00a4 }
            goto L_0x004e
        L_0x009b:
            java.util.ArrayList<com.tencent.smtt.sdk.CookieManager$b> r0 = r10.f9068a     // Catch:{ all -> 0x00a4 }
            r0.clear()     // Catch:{ all -> 0x00a4 }
            monitor-exit(r10)
            return
        L_0x00a2:
            monitor-exit(r10)
            return
        L_0x00a4:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.CookieManager.a():void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0151, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ed A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00ff  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.content.Context r10, boolean r11, boolean r12) {
        /*
            r9 = this;
            monitor-enter(r9)
            com.tencent.smtt.sdk.CookieManager$a r0 = r9.c     // Catch:{ all -> 0x0152 }
            com.tencent.smtt.sdk.CookieManager$a r1 = com.tencent.smtt.sdk.CookieManager.a.MODE_NONE     // Catch:{ all -> 0x0152 }
            if (r0 == r1) goto L_0x0150
            if (r10 == 0) goto L_0x0150
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r0 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ all -> 0x0152 }
            java.lang.String r1 = "cookie_switch.txt"
            boolean r0 = r0.canUseFunction(r10, r1)     // Catch:{ all -> 0x0152 }
            if (r0 == 0) goto L_0x0150
            boolean r0 = r9.e     // Catch:{ all -> 0x0152 }
            if (r0 == 0) goto L_0x001b
            goto L_0x0150
        L_0x001b:
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0152 }
            r2 = 0
            java.lang.String r4 = LOGTAG     // Catch:{ all -> 0x0152 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0152 }
            r5.<init>()     // Catch:{ all -> 0x0152 }
            java.lang.String r6 = "compatiableCookieDatabaseIfNeed,isX5Inited:"
            r5.append(r6)     // Catch:{ all -> 0x0152 }
            r5.append(r11)     // Catch:{ all -> 0x0152 }
            java.lang.String r6 = ",useX5:"
            r5.append(r6)     // Catch:{ all -> 0x0152 }
            r5.append(r12)     // Catch:{ all -> 0x0152 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0152 }
            com.tencent.smtt.utils.TbsLog.i(r4, r5)     // Catch:{ all -> 0x0152 }
            if (r11 != 0) goto L_0x0055
            boolean r11 = com.tencent.smtt.sdk.QbSdk.getIsSysWebViewForcedByOuter()     // Catch:{ all -> 0x0152 }
            if (r11 != 0) goto L_0x0055
            boolean r11 = com.tencent.smtt.sdk.QbSdk.f9080a     // Catch:{ all -> 0x0152 }
            if (r11 == 0) goto L_0x004c
            goto L_0x0055
        L_0x004c:
            com.tencent.smtt.sdk.bt r11 = com.tencent.smtt.sdk.bt.a()     // Catch:{ all -> 0x0152 }
            r11.a((android.content.Context) r10)     // Catch:{ all -> 0x0152 }
            monitor-exit(r9)
            return
        L_0x0055:
            boolean r11 = com.tencent.smtt.sdk.QbSdk.getIsSysWebViewForcedByOuter()     // Catch:{ all -> 0x0152 }
            r4 = 0
            if (r11 != 0) goto L_0x0060
            boolean r11 = com.tencent.smtt.sdk.QbSdk.f9080a     // Catch:{ all -> 0x0152 }
            if (r11 == 0) goto L_0x0061
        L_0x0060:
            r12 = 0
        L_0x0061:
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r11 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ all -> 0x0152 }
            java.lang.String r5 = "usex5.txt"
            boolean r11 = r11.canUseFunction(r10, r5)     // Catch:{ all -> 0x0152 }
            java.lang.String r5 = LOGTAG     // Catch:{ all -> 0x0152 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0152 }
            r6.<init>()     // Catch:{ all -> 0x0152 }
            java.lang.String r7 = "usex5 : mUseX5LastProcess->"
            r6.append(r7)     // Catch:{ all -> 0x0152 }
            r6.append(r11)     // Catch:{ all -> 0x0152 }
            java.lang.String r7 = ",useX5:"
            r6.append(r7)     // Catch:{ all -> 0x0152 }
            r6.append(r12)     // Catch:{ all -> 0x0152 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0152 }
            com.tencent.smtt.utils.TbsLog.i(r5, r6)     // Catch:{ all -> 0x0152 }
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r5 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ all -> 0x0152 }
            java.lang.String r6 = "usex5.txt"
            r5.setFunctionEnable(r10, r6, r12)     // Catch:{ all -> 0x0152 }
            if (r11 != r12) goto L_0x0096
            monitor-exit(r9)
            return
        L_0x0096:
            com.tencent.smtt.sdk.TbsLogReport r5 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r10)     // Catch:{ all -> 0x0152 }
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r5 = r5.a()     // Catch:{ all -> 0x0152 }
            java.lang.String r6 = r9.b     // Catch:{ all -> 0x0152 }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x0152 }
            if (r6 != 0) goto L_0x0112
            com.tencent.smtt.sdk.am r6 = com.tencent.smtt.sdk.am.a()     // Catch:{ all -> 0x0152 }
            int r6 = r6.m(r10)     // Catch:{ all -> 0x0152 }
            if (r6 <= 0) goto L_0x00bf
            com.tencent.smtt.sdk.am r6 = com.tencent.smtt.sdk.am.a()     // Catch:{ all -> 0x0152 }
            int r6 = r6.m(r10)     // Catch:{ all -> 0x0152 }
            r7 = 36001(0x8ca1, float:5.0448E-41)
            if (r6 >= r7) goto L_0x00bf
            monitor-exit(r9)
            return
        L_0x00bf:
            if (r11 == 0) goto L_0x00d1
            int r6 = com.tencent.smtt.sdk.x.d(r10)     // Catch:{ all -> 0x0152 }
            if (r6 <= 0) goto L_0x00cf
            int r7 = getROMCookieDBVersion(r10)     // Catch:{ all -> 0x0152 }
            if (r7 > 0) goto L_0x00eb
            r4 = 1
            goto L_0x00eb
        L_0x00cf:
            r7 = 0
            goto L_0x00eb
        L_0x00d1:
            int r6 = com.tencent.smtt.sdk.x.d(r10)     // Catch:{ all -> 0x0152 }
            if (r6 <= 0) goto L_0x00cf
            com.tencent.smtt.sdk.am r7 = com.tencent.smtt.sdk.am.a()     // Catch:{ all -> 0x0152 }
            java.lang.String r8 = "cookies_database_version"
            java.lang.String r7 = r7.c((android.content.Context) r10, (java.lang.String) r8)     // Catch:{ all -> 0x0152 }
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x0152 }
            if (r8 != 0) goto L_0x00cf
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ Exception -> 0x00cf }
        L_0x00eb:
            if (r4 != 0) goto L_0x00f7
            if (r6 <= 0) goto L_0x00f1
            if (r7 > 0) goto L_0x00f7
        L_0x00f1:
            r12 = 702(0x2be, float:9.84E-43)
            r5.setErrorCode(r12)     // Catch:{ all -> 0x0152 }
            goto L_0x0119
        L_0x00f7:
            if (r7 < r6) goto L_0x00ff
            r12 = 703(0x2bf, float:9.85E-43)
            r5.setErrorCode(r12)     // Catch:{ all -> 0x0152 }
            goto L_0x0119
        L_0x00ff:
            com.tencent.smtt.sdk.CookieManager$a r2 = r9.c     // Catch:{ all -> 0x0152 }
            java.lang.String r3 = r9.b     // Catch:{ all -> 0x0152 }
            com.tencent.smtt.sdk.x.a(r10, r2, r3, r4, r12)     // Catch:{ all -> 0x0152 }
            r12 = 704(0x2c0, float:9.87E-43)
            r5.setErrorCode(r12)     // Catch:{ all -> 0x0152 }
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0152 }
            r12 = 0
            long r2 = r2 - r0
            goto L_0x0119
        L_0x0112:
            r12 = 701(0x2bd, float:9.82E-43)
            r5.setErrorCode(r12)     // Catch:{ all -> 0x0152 }
            r6 = 0
            r7 = 0
        L_0x0119:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0152 }
            r12.<init>()     // Catch:{ all -> 0x0152 }
            java.lang.String r0 = "x5->sys:"
            r12.append(r0)     // Catch:{ all -> 0x0152 }
            r12.append(r11)     // Catch:{ all -> 0x0152 }
            java.lang.String r11 = " from:"
            r12.append(r11)     // Catch:{ all -> 0x0152 }
            r12.append(r6)     // Catch:{ all -> 0x0152 }
            java.lang.String r11 = " to:"
            r12.append(r11)     // Catch:{ all -> 0x0152 }
            r12.append(r7)     // Catch:{ all -> 0x0152 }
            java.lang.String r11 = ",timeused:"
            r12.append(r11)     // Catch:{ all -> 0x0152 }
            r12.append(r2)     // Catch:{ all -> 0x0152 }
            java.lang.String r11 = r12.toString()     // Catch:{ all -> 0x0152 }
            r5.setFailDetail((java.lang.String) r11)     // Catch:{ all -> 0x0152 }
            com.tencent.smtt.sdk.TbsLogReport r10 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r10)     // Catch:{ all -> 0x0152 }
            com.tencent.smtt.sdk.TbsLogReport$EventType r11 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_COOKIE_DB_SWITCH     // Catch:{ all -> 0x0152 }
            r10.a((com.tencent.smtt.sdk.TbsLogReport.EventType) r11, (com.tencent.smtt.sdk.TbsLogReport.TbsLogInfo) r5)     // Catch:{ all -> 0x0152 }
            monitor-exit(r9)
            return
        L_0x0150:
            monitor-exit(r9)
            return
        L_0x0152:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.CookieManager.a(android.content.Context, boolean, boolean):void");
    }

    public boolean acceptCookie() {
        bt a2 = bt.a();
        return (a2 == null || !a2.b()) ? android.webkit.CookieManager.getInstance().acceptCookie() : a2.c().d();
    }

    public synchronized boolean acceptThirdPartyCookies(WebView webView) {
        bt a2 = bt.a();
        if (a2 != null && a2.b()) {
            Object invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_acceptThirdPartyCookies", new Class[]{Object.class}, webView.getView());
            if (invokeStaticMethod == null) {
                return true;
            }
            return ((Boolean) invokeStaticMethod).booleanValue();
        } else if (Build.VERSION.SDK_INT < 21) {
            return true;
        } else {
            Object a3 = q.a((Object) android.webkit.CookieManager.getInstance(), "acceptThirdPartyCookies", (Class<?>[]) new Class[]{WebView.class}, webView.getView());
            if (a3 == null) {
                return false;
            }
            return ((Boolean) a3).booleanValue();
        }
    }

    public void flush() {
        bt a2 = bt.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_flush", new Class[0], new Object[0]);
        } else if (Build.VERSION.SDK_INT >= 21) {
            q.a((Object) android.webkit.CookieManager.getInstance(), EventCategory.CATEGORY_FLUSH, (Class<?>[]) new Class[0], new Object[0]);
        }
    }

    public String getCookie(String str) {
        bt a2 = bt.a();
        if (a2 != null && a2.b()) {
            return a2.c().a(str);
        }
        try {
            return android.webkit.CookieManager.getInstance().getCookie(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public boolean hasCookies() {
        bt a2 = bt.a();
        return (a2 == null || !a2.b()) ? android.webkit.CookieManager.getInstance().hasCookies() : a2.c().h();
    }

    public void removeAllCookie() {
        if (this.f9068a != null) {
            this.f9068a.clear();
        }
        bt a2 = bt.a();
        if (a2 == null || !a2.b()) {
            android.webkit.CookieManager.getInstance().removeAllCookie();
        } else {
            a2.c().e();
        }
    }

    public void removeAllCookies(ValueCallback<Boolean> valueCallback) {
        if (this.f9068a != null) {
            this.f9068a.clear();
        }
        bt a2 = bt.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeAllCookies", new Class[]{ValueCallback.class}, valueCallback);
        } else if (Build.VERSION.SDK_INT >= 21) {
            q.a((Object) android.webkit.CookieManager.getInstance(), "removeAllCookies", (Class<?>[]) new Class[]{ValueCallback.class}, valueCallback);
        }
    }

    public void removeExpiredCookie() {
        bt a2 = bt.a();
        if (a2 == null || !a2.b()) {
            android.webkit.CookieManager.getInstance().removeExpiredCookie();
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeExpiredCookie", new Class[0], new Object[0]);
        }
    }

    public void removeSessionCookie() {
        bt a2 = bt.a();
        if (a2 == null || !a2.b()) {
            android.webkit.CookieManager.getInstance().removeSessionCookie();
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookie", new Class[0], new Object[0]);
        }
    }

    public void removeSessionCookies(ValueCallback<Boolean> valueCallback) {
        bt a2 = bt.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookies", new Class[]{ValueCallback.class}, valueCallback);
        } else if (Build.VERSION.SDK_INT >= 21) {
            q.a((Object) android.webkit.CookieManager.getInstance(), "removeSessionCookies", (Class<?>[]) new Class[]{ValueCallback.class}, valueCallback);
        }
    }

    public synchronized void setAcceptCookie(boolean z) {
        bt a2 = bt.a();
        if (a2 == null || !a2.b()) {
            try {
                android.webkit.CookieManager.getInstance().setAcceptCookie(z);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptCookie", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
        return;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0062, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setAcceptThirdPartyCookies(com.tencent.smtt.sdk.WebView r9, boolean r10) {
        /*
            r8 = this;
            monitor-enter(r8)
            com.tencent.smtt.sdk.bt r0 = com.tencent.smtt.sdk.bt.a()     // Catch:{ all -> 0x0063 }
            r1 = 1
            r2 = 0
            r3 = 2
            if (r0 == 0) goto L_0x0038
            boolean r4 = r0.b()     // Catch:{ all -> 0x0063 }
            if (r4 == 0) goto L_0x0038
            com.tencent.smtt.sdk.bu r0 = r0.c()     // Catch:{ all -> 0x0063 }
            com.tencent.smtt.export.external.DexLoader r0 = r0.b()     // Catch:{ all -> 0x0063 }
            java.lang.String r4 = "com.tencent.tbs.tbsshell.WebCoreProxy"
            java.lang.String r5 = "cookieManager_setAcceptThirdPartyCookies"
            java.lang.Class[] r6 = new java.lang.Class[r3]     // Catch:{ all -> 0x0063 }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r6[r2] = r7     // Catch:{ all -> 0x0063 }
            java.lang.Class r7 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x0063 }
            r6[r1] = r7     // Catch:{ all -> 0x0063 }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0063 }
            android.view.View r9 = r9.getView()     // Catch:{ all -> 0x0063 }
            r3[r2] = r9     // Catch:{ all -> 0x0063 }
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r10)     // Catch:{ all -> 0x0063 }
            r3[r1] = r9     // Catch:{ all -> 0x0063 }
            r0.invokeStaticMethod(r4, r5, r6, r3)     // Catch:{ all -> 0x0063 }
            goto L_0x0061
        L_0x0038:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0063 }
            r4 = 21
            if (r0 >= r4) goto L_0x0040
            monitor-exit(r8)
            return
        L_0x0040:
            android.webkit.CookieManager r0 = android.webkit.CookieManager.getInstance()     // Catch:{ all -> 0x0063 }
            java.lang.String r4 = "setAcceptThirdPartyCookies"
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch:{ all -> 0x0063 }
            java.lang.Class<android.webkit.WebView> r6 = android.webkit.WebView.class
            r5[r2] = r6     // Catch:{ all -> 0x0063 }
            java.lang.Class r6 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x0063 }
            r5[r1] = r6     // Catch:{ all -> 0x0063 }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0063 }
            android.view.View r9 = r9.getView()     // Catch:{ all -> 0x0063 }
            r3[r2] = r9     // Catch:{ all -> 0x0063 }
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r10)     // Catch:{ all -> 0x0063 }
            r3[r1] = r9     // Catch:{ all -> 0x0063 }
            com.tencent.smtt.utils.q.a((java.lang.Object) r0, (java.lang.String) r4, (java.lang.Class<?>[]) r5, (java.lang.Object[]) r3)     // Catch:{ all -> 0x0063 }
        L_0x0061:
            monitor-exit(r8)
            return
        L_0x0063:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.CookieManager.setAcceptThirdPartyCookies(com.tencent.smtt.sdk.WebView, boolean):void");
    }

    public synchronized void setCookie(String str, String str2) {
        setCookie(str, str2, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setCookie(java.lang.String r10, java.lang.String r11, com.tencent.smtt.sdk.ValueCallback<java.lang.Boolean> r12) {
        /*
            r9 = this;
            monitor-enter(r9)
            com.tencent.smtt.sdk.bt r0 = com.tencent.smtt.sdk.bt.a()     // Catch:{ all -> 0x008b }
            r1 = 2
            r2 = 0
            r3 = 3
            r4 = 1
            if (r0 == 0) goto L_0x0037
            boolean r5 = r0.b()     // Catch:{ all -> 0x008b }
            if (r5 == 0) goto L_0x0037
            com.tencent.smtt.sdk.bu r0 = r0.c()     // Catch:{ all -> 0x008b }
            com.tencent.smtt.export.external.DexLoader r0 = r0.b()     // Catch:{ all -> 0x008b }
            java.lang.String r5 = "com.tencent.tbs.tbsshell.WebCoreProxy"
            java.lang.String r6 = "cookieManager_setCookie"
            java.lang.Class[] r7 = new java.lang.Class[r3]     // Catch:{ all -> 0x008b }
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            r7[r2] = r8     // Catch:{ all -> 0x008b }
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            r7[r4] = r8     // Catch:{ all -> 0x008b }
            java.lang.Class<android.webkit.ValueCallback> r8 = android.webkit.ValueCallback.class
            r7[r1] = r8     // Catch:{ all -> 0x008b }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x008b }
            r3[r2] = r10     // Catch:{ all -> 0x008b }
            r3[r4] = r11     // Catch:{ all -> 0x008b }
            r3[r1] = r12     // Catch:{ all -> 0x008b }
            r0.invokeStaticMethod(r5, r6, r7, r3)     // Catch:{ all -> 0x008b }
            goto L_0x0089
        L_0x0037:
            com.tencent.smtt.sdk.bt r0 = com.tencent.smtt.sdk.bt.a()     // Catch:{ all -> 0x008b }
            boolean r0 = r0.d()     // Catch:{ all -> 0x008b }
            if (r0 != 0) goto L_0x005e
            com.tencent.smtt.sdk.CookieManager$b r0 = new com.tencent.smtt.sdk.CookieManager$b     // Catch:{ all -> 0x008b }
            r0.<init>()     // Catch:{ all -> 0x008b }
            r0.f9070a = r4     // Catch:{ all -> 0x008b }
            r0.b = r10     // Catch:{ all -> 0x008b }
            r0.c = r11     // Catch:{ all -> 0x008b }
            r0.d = r12     // Catch:{ all -> 0x008b }
            java.util.ArrayList<com.tencent.smtt.sdk.CookieManager$b> r5 = r9.f9068a     // Catch:{ all -> 0x008b }
            if (r5 != 0) goto L_0x0059
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x008b }
            r5.<init>()     // Catch:{ all -> 0x008b }
            r9.f9068a = r5     // Catch:{ all -> 0x008b }
        L_0x0059:
            java.util.ArrayList<com.tencent.smtt.sdk.CookieManager$b> r5 = r9.f9068a     // Catch:{ all -> 0x008b }
            r5.add(r0)     // Catch:{ all -> 0x008b }
        L_0x005e:
            boolean r0 = r9.f     // Catch:{ all -> 0x008b }
            if (r0 == 0) goto L_0x0089
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x008b }
            r5 = 21
            if (r0 >= r5) goto L_0x006a
            monitor-exit(r9)
            return
        L_0x006a:
            android.webkit.CookieManager r0 = android.webkit.CookieManager.getInstance()     // Catch:{ all -> 0x008b }
            java.lang.String r5 = "setCookie"
            java.lang.Class[] r6 = new java.lang.Class[r3]     // Catch:{ all -> 0x008b }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r2] = r7     // Catch:{ all -> 0x008b }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r4] = r7     // Catch:{ all -> 0x008b }
            java.lang.Class<android.webkit.ValueCallback> r7 = android.webkit.ValueCallback.class
            r6[r1] = r7     // Catch:{ all -> 0x008b }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x008b }
            r3[r2] = r10     // Catch:{ all -> 0x008b }
            r3[r4] = r11     // Catch:{ all -> 0x008b }
            r3[r1] = r12     // Catch:{ all -> 0x008b }
            com.tencent.smtt.utils.q.a((java.lang.Object) r0, (java.lang.String) r5, (java.lang.Class<?>[]) r6, (java.lang.Object[]) r3)     // Catch:{ all -> 0x008b }
        L_0x0089:
            monitor-exit(r9)
            return
        L_0x008b:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.CookieManager.setCookie(java.lang.String, java.lang.String, com.tencent.smtt.sdk.ValueCallback):void");
    }

    public synchronized void setCookie(String str, String str2, boolean z) {
        bt a2 = bt.a();
        if (a2 == null || !a2.b()) {
            if (this.f || z) {
                android.webkit.CookieManager.getInstance().setCookie(str, str2);
            }
            if (!bt.a().d()) {
                b bVar = new b();
                bVar.f9070a = 2;
                bVar.b = str;
                bVar.c = str2;
                bVar.d = null;
                if (this.f9068a == null) {
                    this.f9068a = new ArrayList<>();
                }
                this.f9068a.add(bVar);
            }
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class}, str, str2);
        }
    }

    public boolean setCookieCompatialbeMode(Context context, a aVar, String str, boolean z) {
        System.currentTimeMillis();
        if (context == null || !TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.COOKIE_SWITCH_FILE_NAME)) {
            return false;
        }
        this.c = aVar;
        if (str != null) {
            this.b = str;
        }
        if (this.c == a.MODE_NONE || !z || bt.a().d()) {
            return true;
        }
        bt.a().a(context);
        return true;
    }

    public void setCookies(Map<String, String[]> map) {
        bt a2 = bt.a();
        if (!((a2 == null || !a2.b()) ? false : a2.c().a(map))) {
            for (String next : map.keySet()) {
                for (String cookie : map.get(next)) {
                    setCookie(next, cookie);
                }
            }
        }
    }
}
