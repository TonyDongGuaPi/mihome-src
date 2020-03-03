package com.alipay.mobile.security.zim.api;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alipay.bis.common.service.facade.gw.zim.ZimInitGwResponse;
import com.alipay.mobile.security.bio.api.BioDetectorBuilder;
import com.alipay.mobile.security.bio.runtime.Runtime;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.service.local.apsecurity.ApSecurityService;
import com.alipay.mobile.security.bio.service.local.monitorlog.MonitorLogService;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.utils.DeviceUtil;
import com.alipay.mobile.security.bio.workspace.Env;
import com.alipay.mobile.security.zim.a.a;
import com.alipay.mobile.security.zim.a.b;
import com.alipay.mobile.security.zim.a.c;
import java.util.Map;

public abstract class ZIMFacade {
    public static final int COMMAND_CANCLE = 4099;
    public static final int COMMAND_SERVER_CONTINUE = 4098;
    public static final int COMMAND_SERVER_FAIL = 8193;
    public static final int COMMAND_SERVER_RETRY = 8194;
    public static final int COMMAND_SERVER_SUCCESS = 4097;
    public static final int COMMAND_VALIDATE_FAIL = 4100;
    public static final String KEY_AUTO_CLOSE = "zimAutoClose";
    public static final String KEY_AVATAR = "avatar";
    public static final String KEY_BIO_ACTION = "zimAction";
    public static final String KEY_ENV_NAME = "env_name";
    public static final String KEY_FACE_PAY_INFO = "facepayInfoMap";
    public static final String KEY_FACE_TOKEN = "ftoken";
    public static final String KEY_INIT_RESP = "zimInitResp";
    public static final String KEY_INIT_RESP_OLD = "zim.init.resp";
    public static final String TAG = "ZimPlatform";

    /* renamed from: a  reason: collision with root package name */
    private static String f1051a = "1.0.0";

    public abstract void command(int i);

    public abstract void destroy();

    public abstract ZimInitGwResponse parse(String str);

    public abstract void retry();

    public abstract void verify(String str, ZimInitGwResponse zimInitGwResponse, Map<String, String> map, ZIMCallback zIMCallback);

    public abstract void verify(String str, Map<String, String> map, ZIMCallback zIMCallback);

    protected static void a(Context context, String str) {
        String str2;
        StringBuilder sb;
        if (!DeviceUtil.isDebug(context)) {
            BioLog.w("initEnv(). envName=[" + str + "] is ignored, because only debug apk can set env manually.");
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                    str = applicationInfo.metaData.getString(KEY_ENV_NAME);
                }
                str2 = TAG;
                sb = new StringBuilder();
            } catch (PackageManager.NameNotFoundException e) {
                BioLog.w((Throwable) e);
                str2 = TAG;
                sb = new StringBuilder();
            } catch (Throwable th) {
                BioLog.i(TAG, "ApplicationInfo.metaData.env_name == " + str);
                throw th;
            }
            sb.append("ApplicationInfo.metaData.env_name == ");
            sb.append(str);
            BioLog.i(str2, sb.toString());
        }
        if (TextUtils.isEmpty(str)) {
            str = "online";
        }
        BioLog.i(TAG, "initEnv() : env=" + str);
        Env envByName = Env.getEnvByName(str);
        if (envByName != null) {
            BioServiceManager.setEnv(envByName);
        }
    }

    public static void install(Context context, Map<String, Object> map) {
        if (map != null && map.containsKey(KEY_ENV_NAME)) {
            a(context, (String) map.remove(KEY_ENV_NAME));
        }
        install(context);
    }

    public static void install(Context context) {
        MonitorLogService monitorLogService = (MonitorLogService) BioServiceManager.getLocalService(context, MonitorLogService.class);
        if (monitorLogService != null) {
            monitorLogService.install(context);
        }
        ApSecurityService apSecurityService = (ApSecurityService) BioServiceManager.getLocalService(context, ApSecurityService.class);
        if (apSecurityService != null) {
            apSecurityService.init(context);
        }
        if (!Runtime.isRunningOnQuinox(context)) {
            Application application = (Application) context.getApplicationContext();
            b.a(application);
            c.a(application);
        }
    }

    public static ZIMMetaInfo getZimMetaInfo(Context context) {
        a aVar = new a();
        ZIMMetaInfo zIMMetaInfo = new ZIMMetaInfo();
        zIMMetaInfo.setApdidToken(aVar.a(context));
        zIMMetaInfo.setAppName(aVar.b(context));
        zIMMetaInfo.setAppVersion(aVar.c(context));
        zIMMetaInfo.setDeviceModel(aVar.b());
        zIMMetaInfo.setDeviceType(aVar.a());
        zIMMetaInfo.setOsVersion(aVar.c());
        zIMMetaInfo.setBioMetaInfo(BioDetectorBuilder.getMetaInfos(context));
        zIMMetaInfo.setZimVer(f1051a);
        return zIMMetaInfo;
    }

    public static String getMetaInfos(Context context) {
        return a(context, true);
    }

    protected static String a(Context context, boolean z) {
        if (z) {
            a.a(context);
        }
        try {
            return JSON.toJSONString(getZimMetaInfo(context));
        } catch (Throwable th) {
            BioLog.e(th);
            return "";
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x011b, code lost:
        if (android.text.TextUtils.isEmpty(r7) == false) goto L_0x011f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0080, code lost:
        if (android.text.TextUtils.isEmpty(r7) == false) goto L_0x00de;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00ff  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(android.content.Context r7) {
        /*
            java.lang.String r0 = ""
            boolean r1 = com.alipay.mobile.security.bio.runtime.Runtime.isRunningOnQuinox(r7)
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x009d
            java.lang.String r7 = "com.alipay.mobile.framework.LauncherApplicationAgent"
            java.lang.Class r7 = java.lang.Class.forName(r7)     // Catch:{ Throwable -> 0x0083 }
            java.lang.String r1 = "getInstance"
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ Throwable -> 0x0083 }
            java.lang.reflect.Method r1 = r7.getMethod(r1, r4)     // Catch:{ Throwable -> 0x0083 }
            r1.setAccessible(r2)     // Catch:{ Throwable -> 0x0083 }
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0083 }
            java.lang.Object r1 = r1.invoke(r1, r4)     // Catch:{ Throwable -> 0x0083 }
            java.lang.String r4 = "getMicroApplicationContext"
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch:{ Throwable -> 0x0083 }
            java.lang.reflect.Method r7 = r7.getMethod(r4, r5)     // Catch:{ Throwable -> 0x0083 }
            r7.setAccessible(r2)     // Catch:{ Throwable -> 0x0083 }
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0083 }
            java.lang.Object r7 = r7.invoke(r1, r4)     // Catch:{ Throwable -> 0x0083 }
            java.lang.Class r1 = r7.getClass()     // Catch:{ Throwable -> 0x0083 }
            java.lang.String r4 = "findServiceByInterface"
            java.lang.Class[] r5 = new java.lang.Class[r2]     // Catch:{ Throwable -> 0x0083 }
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r5[r3] = r6     // Catch:{ Throwable -> 0x0083 }
            java.lang.reflect.Method r1 = r1.getMethod(r4, r5)     // Catch:{ Throwable -> 0x0083 }
            r1.setAccessible(r2)     // Catch:{ Throwable -> 0x0083 }
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0083 }
            java.lang.String r5 = "com.alipay.apmobilesecuritysdk.DeviceFingerprintService"
            r4[r3] = r5     // Catch:{ Throwable -> 0x0083 }
            java.lang.Object r7 = r1.invoke(r7, r4)     // Catch:{ Throwable -> 0x0083 }
            if (r7 == 0) goto L_0x00f9
            java.lang.Class r1 = r7.getClass()     // Catch:{ Throwable -> 0x0083 }
            java.lang.String r4 = "getApdidToken"
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch:{ Throwable -> 0x0083 }
            java.lang.reflect.Method r1 = r1.getMethod(r4, r5)     // Catch:{ Throwable -> 0x0083 }
            r1.setAccessible(r2)     // Catch:{ Throwable -> 0x0083 }
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0083 }
            java.lang.Object r7 = r1.invoke(r7, r2)     // Catch:{ Throwable -> 0x0083 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Throwable -> 0x0083 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0083 }
            r1.<init>()     // Catch:{ Throwable -> 0x0083 }
            java.lang.String r2 = "DeviceFingerprintService.getApdidToken(): "
            r1.append(r2)     // Catch:{ Throwable -> 0x0083 }
            r1.append(r7)     // Catch:{ Throwable -> 0x0083 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0083 }
            com.alipay.mobile.security.bio.utils.BioLog.d(r1)     // Catch:{ Throwable -> 0x0083 }
            boolean r1 = android.text.TextUtils.isEmpty(r7)     // Catch:{ Throwable -> 0x0083 }
            if (r1 != 0) goto L_0x00f9
            goto L_0x00de
        L_0x0083:
            r7 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Failed to get apdidToken by calling DeviceFingerprintService.getApdidToken() : "
            r1.append(r2)
            java.lang.String r7 = r7.getMessage()
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            com.alipay.mobile.security.bio.utils.BioLog.w((java.lang.String) r7)
            goto L_0x00f9
        L_0x009d:
            java.lang.String r1 = "com.alipay.deviceid.DeviceTokenClient"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ Throwable -> 0x00e0 }
            java.lang.String r4 = "getInstance"
            java.lang.Class[] r5 = new java.lang.Class[r2]     // Catch:{ Throwable -> 0x00e0 }
            java.lang.Class<android.content.Context> r6 = android.content.Context.class
            r5[r3] = r6     // Catch:{ Throwable -> 0x00e0 }
            java.lang.reflect.Method r4 = r1.getMethod(r4, r5)     // Catch:{ Throwable -> 0x00e0 }
            r4.setAccessible(r2)     // Catch:{ Throwable -> 0x00e0 }
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00e0 }
            r5[r3] = r7     // Catch:{ Throwable -> 0x00e0 }
            java.lang.Object r7 = r4.invoke(r4, r5)     // Catch:{ Throwable -> 0x00e0 }
            java.lang.String r4 = "getTokenResult"
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch:{ Throwable -> 0x00e0 }
            java.lang.reflect.Method r1 = r1.getMethod(r4, r5)     // Catch:{ Throwable -> 0x00e0 }
            r1.setAccessible(r2)     // Catch:{ Throwable -> 0x00e0 }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x00e0 }
            java.lang.Object r7 = r1.invoke(r7, r3)     // Catch:{ Throwable -> 0x00e0 }
            java.lang.Class r1 = r7.getClass()     // Catch:{ Throwable -> 0x00e0 }
            java.lang.String r3 = "apdidToken"
            java.lang.reflect.Field r1 = r1.getDeclaredField(r3)     // Catch:{ Throwable -> 0x00e0 }
            r1.setAccessible(r2)     // Catch:{ Throwable -> 0x00e0 }
            java.lang.Object r7 = r1.get(r7)     // Catch:{ Throwable -> 0x00e0 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Throwable -> 0x00e0 }
        L_0x00de:
            r0 = r7
            goto L_0x00f9
        L_0x00e0:
            r7 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Failed to get apdidToken by calling DeviceTokenClient.getTokenResult().apdidToken : "
            r1.append(r2)
            java.lang.String r7 = r7.getMessage()
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            com.alipay.mobile.security.bio.utils.BioLog.w((java.lang.String) r7)
        L_0x00f9:
            boolean r7 = android.text.TextUtils.isEmpty(r0)
            if (r7 == 0) goto L_0x011e
            java.lang.String r7 = com.alipay.mobile.security.bio.service.local.apsecurity.ApSecurityService.getStaticApDidToken()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "ApSecurityService.getStaticApDidToken(): "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            com.alipay.mobile.security.bio.utils.BioLog.d(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r7)
            if (r1 != 0) goto L_0x011e
            goto L_0x011f
        L_0x011e:
            r7 = r0
        L_0x011f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "apDidToken="
            r0.append(r1)
            r0.append(r7)
            java.lang.String r0 = r0.toString()
            com.alipay.mobile.security.bio.utils.BioLog.v(r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.security.zim.api.ZIMFacade.b(android.content.Context):java.lang.String");
    }
}
