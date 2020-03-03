package com.alipay.mobile.common.logging;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.helper.ClientIdHelper;
import com.alipay.mobile.common.logging.util.LoggingSPCache;
import com.alipay.zoloz.android.phone.mrpc.core.MiscUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ContextInfo {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<String, String> f942a = new HashMap();
    private static final String b = ContextInfo.class.getSimpleName();
    private Context c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i = c();
    private String j;
    private String k = d();
    private String l = e();
    private String m;
    private String n;
    private String o = f();
    private String p;
    private String q;
    private String r = g();
    private String s = h();

    static {
        f942a.put("com.eg.android.AlipayGphone", "Android-container");
        f942a.put(MiscUtils.RC_PACKAGE_NAME, "Android-container-RC");
    }

    public ContextInfo(Context context) {
        this.c = context;
        b();
        a(this.h);
        this.j = new ClientIdHelper().initClientId(context);
    }

    public String getChannelId() {
        return this.d;
    }

    public String getReleaseType() {
        return this.e;
    }

    public String getReleaseCode() {
        return this.f;
    }

    public String getProductId() {
        return this.g;
    }

    public String getProductVersion() {
        return this.h;
    }

    public String getUserId() {
        return this.i;
    }

    public String getClientId() {
        return this.j;
    }

    public String getDeviceId() {
        return this.k;
    }

    public String getLanguage() {
        return this.l;
    }

    public synchronized String getSessionId() {
        return this.m;
    }

    public String getSourceId() {
        return this.n;
    }

    public String getHotpatchVersion() {
        return this.o;
    }

    public String getBundleVersion() {
        return this.r;
    }

    public String getBirdNestVersion() {
        return this.s;
    }

    public String getPackageId() {
        return this.p;
    }

    public String getApkUniqueId() {
        return this.q;
    }

    public void setChannelId(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.d = str;
            LoggingSPCache.getInstance().putString(LoggingSPCache.STORAGE_CHANNELID, str);
            a(LoggingSPCache.STORAGE_CHANNELID, str);
        }
    }

    public void setReleaseType(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.e = str;
            LoggingSPCache.getInstance().putString(LoggingSPCache.STORAGE_RELEASETYPE, str);
            a(LoggingSPCache.STORAGE_RELEASETYPE, str);
        }
    }

    public void setReleaseCode(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f = str;
            LoggingSPCache instance = LoggingSPCache.getInstance();
            instance.putString(LoggingSPCache.STORAGE_RELEASECODE + this.h, str);
            a(LoggingSPCache.STORAGE_RELEASECODE, str);
        }
    }

    public void setProductId(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.g = str;
            LoggingSPCache.getInstance().putString(LoggingSPCache.STORAGE_PRODUCTID, str);
            a(LoggingSPCache.STORAGE_PRODUCTID, str);
        }
    }

    public void setProductVersion(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.h = str;
        }
    }

    public void setUserId(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.i = str;
            LoggingSPCache.getInstance().putString(LoggingSPCache.STORAGE_USERID, str);
            a(LoggingSPCache.STORAGE_USERID, str);
        }
    }

    public void setClientId(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.j = str;
            LoggingSPCache.getInstance().putString(LoggingSPCache.STORAGE_CLIENTID, str);
            a(LoggingSPCache.STORAGE_CLIENTID, str);
        }
    }

    public void setDeviceId(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.k = str;
            LoggingSPCache.getInstance().putString("utdid", str);
            a("utdid", str);
        }
    }

    public void setLanguage(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.l = str;
            LoggingSPCache.getInstance().putString("language", str);
            a("language", str);
        }
    }

    public synchronized void refreshSessionId() {
        this.m = UUID.randomUUID().toString();
    }

    public void setSourceId(String str) {
        this.n = str;
    }

    public void setHotpatchVersion(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.o = str;
            LoggingSPCache instance = LoggingSPCache.getInstance();
            instance.putString(LoggingSPCache.STORAGE_HOTPATCHVERSION + this.h, str);
            a(LoggingSPCache.STORAGE_HOTPATCHVERSION, str);
        }
    }

    public void setBundleVersion(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.r = str;
            LoggingSPCache instance = LoggingSPCache.getInstance();
            instance.putString(LoggingSPCache.STORAGE_BUNDLEVERSION + this.h, str);
            a(LoggingSPCache.STORAGE_BUNDLEVERSION, str);
        }
    }

    public void setBirdNestVersion(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.s = str;
            LoggingSPCache instance = LoggingSPCache.getInstance();
            instance.putString(LoggingSPCache.STORAGE_BIRDNESTVERSION + this.h, str);
            a(LoggingSPCache.STORAGE_BIRDNESTVERSION, str);
        }
    }

    public void setPackageId(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.p = str;
            LoggingSPCache instance = LoggingSPCache.getInstance();
            instance.putString(LoggingSPCache.STORAGE_PACKAGEID + this.h, str);
            a();
            a(LoggingSPCache.STORAGE_PACKAGEID, str);
        }
    }

    public void setApkUniqueId(String str) {
        this.q = str;
    }

    private void a(String str, String str2) {
        if (LoggerFactory.getProcessInfo().isMainProcess()) {
            a(LogContext.PUSH_SERVICE_CLASS_NAME, str, str2);
            if (!LoggerFactory.getLogContext().isDisableToolsProcess()) {
                a(LogContext.TOOLS_SERVICE_CLASS_NAME, str, str2);
            }
        } else if (LoggerFactory.getProcessInfo().isPushProcess()) {
            if (!LoggerFactory.getLogContext().isDisableToolsProcess()) {
                a(LogContext.TOOLS_SERVICE_CLASS_NAME, str, str2);
            }
        } else if (!LoggerFactory.getProcessInfo().isToolsProcess()) {
            String str3 = b;
            Log.e(str3, "updateLogContext, error: unknown process " + LoggerFactory.getProcessInfo().getProcessAlias());
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0039 */
    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0041 A[Catch:{ Throwable -> 0x0049 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r3, java.lang.String r4, java.lang.String r5) {
        /*
            r2 = this;
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            android.content.Context r1 = r2.c
            r0.setClassName(r1, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            android.content.Context r1 = r2.c
            java.lang.String r1 = r1.getPackageName()
            r3.append(r1)
            java.lang.String r1 = ".monitor.action.UPDATE_LOG_CONTEXT"
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            r0.setAction(r3)
            java.lang.String r3 = "type"
            r0.putExtra(r3, r4)
            java.lang.String r3 = "value"
            r0.putExtra(r3, r5)
            android.content.Context r3 = r2.c     // Catch:{ Throwable -> 0x0039 }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ Throwable -> 0x0039 }
            r0.setPackage(r3)     // Catch:{ Throwable -> 0x0039 }
        L_0x0039:
            android.content.Context r3 = r2.c     // Catch:{ Throwable -> 0x0049 }
            android.content.ComponentName r3 = r3.startService(r0)     // Catch:{ Throwable -> 0x0049 }
            if (r3 != 0) goto L_0x0051
            java.lang.String r3 = b     // Catch:{ Throwable -> 0x0049 }
            java.lang.String r4 = "notifyOtherProcessToUpdateLogContext: start service occured error"
            android.util.Log.e(r3, r4)     // Catch:{ Throwable -> 0x0049 }
            goto L_0x0051
        L_0x0049:
            r3 = move-exception
            java.lang.String r4 = b
            java.lang.String r5 = "notifyOtherProcessToUpdateLogContext"
            android.util.Log.e(r4, r5, r3)
        L_0x0051:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.ContextInfo.a(java.lang.String, java.lang.String, java.lang.String):void");
    }

    private void a() {
        if (TextUtils.isEmpty(this.p)) {
            this.q = this.p;
            return;
        }
        int lastIndexOf = this.p.lastIndexOf(45);
        if (lastIndexOf < 0) {
            this.q = null;
        } else {
            this.q = this.p.substring(lastIndexOf);
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:36:0x0124 */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x013e A[SYNTHETIC, Splitter:B:49:0x013e] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0149 A[SYNTHETIC, Splitter:B:57:0x0149] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x014e A[SYNTHETIC, Splitter:B:61:0x014e] */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r7) {
        /*
            r6 = this;
            com.alipay.mobile.common.logging.util.LoggingSPCache r0 = com.alipay.mobile.common.logging.util.LoggingSPCache.getInstance()
            java.lang.String r1 = "channelId"
            r2 = 0
            java.lang.String r0 = r0.getString(r1, r2)
            r6.d = r0
            com.alipay.mobile.common.logging.util.LoggingSPCache r0 = com.alipay.mobile.common.logging.util.LoggingSPCache.getInstance()
            java.lang.String r1 = "releaseType"
            java.lang.String r0 = r0.getString(r1, r2)
            r6.e = r0
            com.alipay.mobile.common.logging.util.LoggingSPCache r0 = com.alipay.mobile.common.logging.util.LoggingSPCache.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "releaseCode"
            r1.append(r3)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            java.lang.String r0 = r0.getString(r1, r2)
            r6.f = r0
            com.alipay.mobile.common.logging.util.LoggingSPCache r0 = com.alipay.mobile.common.logging.util.LoggingSPCache.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "packageId"
            r1.append(r3)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            java.lang.String r0 = r0.getString(r1, r2)
            r6.p = r0
            r6.a()
            java.lang.String r0 = r6.d
            if (r0 == 0) goto L_0x0062
            java.lang.String r0 = r6.e
            if (r0 == 0) goto L_0x0062
            java.lang.String r0 = r6.f
            if (r0 == 0) goto L_0x0062
            java.lang.String r0 = r6.p
            if (r0 != 0) goto L_0x0144
        L_0x0062:
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0133, all -> 0x012f }
            android.content.Context r1 = r6.c     // Catch:{ Throwable -> 0x0133, all -> 0x012f }
            android.content.res.Resources r1 = r1.getResources()     // Catch:{ Throwable -> 0x0133, all -> 0x012f }
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch:{ Throwable -> 0x0133, all -> 0x012f }
            java.lang.String r3 = "channel.config"
            java.io.InputStream r1 = r1.open(r3)     // Catch:{ Throwable -> 0x0133, all -> 0x012f }
            r0.<init>(r1)     // Catch:{ Throwable -> 0x0133, all -> 0x012f }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x012d }
            r1.<init>(r0)     // Catch:{ Throwable -> 0x012d }
            java.util.Properties r2 = new java.util.Properties     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            r2.<init>()     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            r2.load(r1)     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            java.lang.String r3 = r6.d     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            if (r3 != 0) goto L_0x00a3
            java.lang.String r3 = "channel_id"
            java.lang.String r3 = r2.getProperty(r3)     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            r6.d = r3     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            java.lang.String r3 = r6.d     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            if (r3 != 0) goto L_0x00a3
            com.alipay.mobile.common.logging.util.LoggingSPCache r3 = com.alipay.mobile.common.logging.util.LoggingSPCache.getInstance()     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            java.lang.String r4 = "channelId"
            java.lang.String r5 = r6.d     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            r3.putString(r4, r5)     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
        L_0x00a3:
            java.lang.String r3 = r6.e     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            if (r3 != 0) goto L_0x00c2
            java.lang.String r3 = "release_type"
            java.lang.String r3 = r2.getProperty(r3)     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            r6.e = r3     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            java.lang.String r3 = r6.e     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            if (r3 != 0) goto L_0x00c2
            com.alipay.mobile.common.logging.util.LoggingSPCache r3 = com.alipay.mobile.common.logging.util.LoggingSPCache.getInstance()     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            java.lang.String r4 = "releaseType"
            java.lang.String r5 = r6.e     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            r3.putString(r4, r5)     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
        L_0x00c2:
            java.lang.String r3 = r6.f     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            if (r3 != 0) goto L_0x00f0
            java.lang.String r3 = "release_version"
            java.lang.String r3 = r2.getProperty(r3)     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            r6.f = r3     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            java.lang.String r3 = r6.f     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            if (r3 != 0) goto L_0x00f0
            com.alipay.mobile.common.logging.util.LoggingSPCache r3 = com.alipay.mobile.common.logging.util.LoggingSPCache.getInstance()     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            r4.<init>()     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            java.lang.String r5 = "releaseCode"
            r4.append(r5)     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            r4.append(r7)     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            java.lang.String r5 = r6.f     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            r3.putString(r4, r5)     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
        L_0x00f0:
            java.lang.String r3 = r6.p     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            if (r3 != 0) goto L_0x0121
            java.lang.String r3 = "package_id"
            java.lang.String r2 = r2.getProperty(r3)     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            r6.p = r2     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            java.lang.String r2 = r6.p     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            if (r2 != 0) goto L_0x011e
            com.alipay.mobile.common.logging.util.LoggingSPCache r2 = com.alipay.mobile.common.logging.util.LoggingSPCache.getInstance()     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            r3.<init>()     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            java.lang.String r4 = "packageId"
            r3.append(r4)     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            r3.append(r7)     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            java.lang.String r7 = r3.toString()     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            java.lang.String r3 = r6.p     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
            r2.putString(r7, r3)     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
        L_0x011e:
            r6.a()     // Catch:{ Throwable -> 0x012a, all -> 0x0128 }
        L_0x0121:
            r1.close()     // Catch:{ Throwable -> 0x0124 }
        L_0x0124:
            r0.close()     // Catch:{ Throwable -> 0x0144 }
            goto L_0x0144
        L_0x0128:
            r7 = move-exception
            goto L_0x0147
        L_0x012a:
            r7 = move-exception
            r2 = r1
            goto L_0x0135
        L_0x012d:
            r7 = move-exception
            goto L_0x0135
        L_0x012f:
            r7 = move-exception
            r0 = r2
            r1 = r0
            goto L_0x0147
        L_0x0133:
            r7 = move-exception
            r0 = r2
        L_0x0135:
            java.lang.String r1 = b     // Catch:{ all -> 0x0145 }
            java.lang.String r3 = "read channel.config fail"
            android.util.Log.e(r1, r3, r7)     // Catch:{ all -> 0x0145 }
            if (r2 == 0) goto L_0x0141
            r2.close()     // Catch:{ Throwable -> 0x0141 }
        L_0x0141:
            if (r0 == 0) goto L_0x0144
            goto L_0x0124
        L_0x0144:
            return
        L_0x0145:
            r7 = move-exception
            r1 = r2
        L_0x0147:
            if (r1 == 0) goto L_0x014c
            r1.close()     // Catch:{ Throwable -> 0x014c }
        L_0x014c:
            if (r0 == 0) goto L_0x0151
            r0.close()     // Catch:{ Throwable -> 0x0151 }
        L_0x0151:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.ContextInfo.a(java.lang.String):void");
    }

    private void b() {
        PackageInfo packageInfo;
        ApplicationInfo applicationInfo;
        String str;
        String str2;
        this.g = LoggingSPCache.getInstance().getString(LoggingSPCache.STORAGE_PRODUCTID, (String) null);
        if (this.g == null) {
            try {
                applicationInfo = this.c.getPackageManager().getApplicationInfo(this.c.getPackageName(), 128);
            } catch (Throwable unused) {
                applicationInfo = null;
            }
            if (applicationInfo == null || applicationInfo.metaData == null) {
                str2 = null;
                str = null;
            } else {
                str = applicationInfo.metaData.getString("appkey");
                str2 = applicationInfo.metaData.getString("workspaceId");
            }
            if (str != null) {
                this.g = str;
                if (str2 != null && str2.length() > 1) {
                    this.g += "-" + str2.substring(1);
                }
            }
        }
        if (this.g == null) {
            this.g = f942a.get(this.c.getPackageName());
        }
        if (this.h == null) {
            try {
                packageInfo = this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 0);
            } catch (Throwable unused2) {
                packageInfo = null;
            }
            if (packageInfo != null) {
                this.h = packageInfo.versionName;
            }
        }
    }

    private String c() {
        return LoggingSPCache.getInstance().getString(LoggingSPCache.STORAGE_USERID, (String) null);
    }

    private String d() {
        return LoggingSPCache.getInstance().getString("utdid", (String) null);
    }

    private String e() {
        return LoggingSPCache.getInstance().getString("language", (String) null);
    }

    private String f() {
        LoggingSPCache instance = LoggingSPCache.getInstance();
        return instance.getString(LoggingSPCache.STORAGE_HOTPATCHVERSION + this.h, "0");
    }

    private String g() {
        LoggingSPCache instance = LoggingSPCache.getInstance();
        return instance.getString(LoggingSPCache.STORAGE_BUNDLEVERSION + this.h, "0");
    }

    private String h() {
        LoggingSPCache instance = LoggingSPCache.getInstance();
        return instance.getString(LoggingSPCache.STORAGE_BIRDNESTVERSION + this.h, "0");
    }
}
