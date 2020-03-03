package com.tencent.smtt.sdk.a;

import MTT.ThirdAppInfoNew;
import android.content.Context;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.TbsCoreLoadStat;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloader;
import com.tencent.smtt.sdk.TbsShareManager;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.o;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import miuipub.security.DigestUtils;
import org.json.JSONObject;

public class b {

    /* renamed from: a  reason: collision with root package name */
    public static byte[] f9120a;

    static {
        try {
            f9120a = "65dRa93L".getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
        }
    }

    private static String a(Context context) {
        try {
            byte[] byteArray = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray();
            if (byteArray != null) {
                MessageDigest instance = MessageDigest.getInstance(DigestUtils.b);
                instance.update(byteArray);
                byte[] digest = instance.digest();
                if (digest != null) {
                    StringBuilder sb = new StringBuilder("");
                    if (digest != null) {
                        if (digest.length > 0) {
                            for (int i = 0; i < digest.length; i++) {
                                String upperCase = Integer.toHexString(digest[i] & 255).toUpperCase();
                                if (i > 0) {
                                    sb.append(":");
                                }
                                if (upperCase.length() < 2) {
                                    sb.append(0);
                                }
                                sb.append(upperCase);
                            }
                            return sb.toString();
                        }
                    }
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void a(ThirdAppInfoNew thirdAppInfoNew, Context context) {
        new c("HttpUtils", context, thirdAppInfoNew).start();
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ac A[Catch:{ Throwable -> 0x012b }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00af A[Catch:{ Throwable -> 0x012b }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e5 A[Catch:{ Throwable -> 0x012b }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00fc A[Catch:{ Throwable -> 0x012b }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x010f A[Catch:{ Throwable -> 0x012b }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x011b A[Catch:{ Throwable -> 0x012b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, int r10, boolean r11, long r12) {
        /*
            java.util.Map r0 = com.tencent.smtt.sdk.QbSdk.getSettings()
            if (r0 == 0) goto L_0x002c
            java.util.Map r0 = com.tencent.smtt.sdk.QbSdk.getSettings()
            java.lang.String r1 = com.tencent.smtt.sdk.QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD
            boolean r0 = r0.containsKey(r1)
            if (r0 == 0) goto L_0x002c
            java.util.Map r0 = com.tencent.smtt.sdk.QbSdk.getSettings()
            java.lang.String r1 = com.tencent.smtt.sdk.QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r1 = "false"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x002c
            java.lang.String r6 = "sdkreport"
            java.lang.String r7 = "[HttpUtils.doReport] -- SET_SENDREQUEST_AND_UPLOAD is false"
            com.tencent.smtt.utils.TbsLog.i(r6, r7)
            return
        L_0x002c:
            java.lang.String r0 = ""
            r1 = 0
            android.content.pm.ApplicationInfo r2 = r6.getApplicationInfo()     // Catch:{ Exception -> 0x006e }
            java.lang.String r3 = "com.tencent.mobileqq"
            java.lang.String r4 = r2.packageName     // Catch:{ Exception -> 0x006e }
            boolean r3 = r3.equals(r4)     // Catch:{ Exception -> 0x006e }
            if (r3 == 0) goto L_0x0076
            android.content.pm.PackageManager r3 = r6.getPackageManager()     // Catch:{ Exception -> 0x006e }
            java.lang.String r2 = r2.packageName     // Catch:{ Exception -> 0x006e }
            android.content.pm.PackageInfo r2 = r3.getPackageInfo(r2, r1)     // Catch:{ Exception -> 0x006e }
            java.lang.String r2 = r2.versionName     // Catch:{ Exception -> 0x006e }
            java.lang.String r0 = com.tencent.smtt.sdk.QbSdk.getQQBuildNumber()     // Catch:{ Exception -> 0x006c }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x006c }
            if (r0 != 0) goto L_0x0075
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x006c }
            r0.<init>()     // Catch:{ Exception -> 0x006c }
            r0.append(r2)     // Catch:{ Exception -> 0x006c }
            java.lang.String r3 = "."
            r0.append(r3)     // Catch:{ Exception -> 0x006c }
            java.lang.String r3 = com.tencent.smtt.sdk.QbSdk.getQQBuildNumber()     // Catch:{ Exception -> 0x006c }
            r0.append(r3)     // Catch:{ Exception -> 0x006c }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x006c }
            goto L_0x0076
        L_0x006c:
            r0 = move-exception
            goto L_0x0072
        L_0x006e:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
        L_0x0072:
            r0.printStackTrace()
        L_0x0075:
            r0 = r2
        L_0x0076:
            MTT.ThirdAppInfoNew r2 = new MTT.ThirdAppInfoNew     // Catch:{ Throwable -> 0x012b }
            r2.<init>()     // Catch:{ Throwable -> 0x012b }
            android.content.Context r3 = r6.getApplicationContext()     // Catch:{ Throwable -> 0x012b }
            android.content.pm.ApplicationInfo r3 = r3.getApplicationInfo()     // Catch:{ Throwable -> 0x012b }
            java.lang.String r3 = r3.packageName     // Catch:{ Throwable -> 0x012b }
            r2.sAppName = r3     // Catch:{ Throwable -> 0x012b }
            com.tencent.smtt.utils.s.a(r6)     // Catch:{ Throwable -> 0x012b }
            java.text.SimpleDateFormat r3 = new java.text.SimpleDateFormat     // Catch:{ Throwable -> 0x012b }
            java.lang.String r4 = "yyyy-MM-dd hh:mm:ss"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x012b }
            java.lang.String r4 = "GMT+08"
            java.util.TimeZone r4 = java.util.TimeZone.getTimeZone(r4)     // Catch:{ Throwable -> 0x012b }
            r3.setTimeZone(r4)     // Catch:{ Throwable -> 0x012b }
            java.util.Calendar r4 = java.util.Calendar.getInstance()     // Catch:{ Throwable -> 0x012b }
            java.util.Date r4 = r4.getTime()     // Catch:{ Throwable -> 0x012b }
            java.lang.String r3 = r3.format(r4)     // Catch:{ Throwable -> 0x012b }
            r2.sTime = r3     // Catch:{ Throwable -> 0x012b }
            r2.sGuid = r7     // Catch:{ Throwable -> 0x012b }
            if (r11 == 0) goto L_0x00af
            r2.sQua2 = r8     // Catch:{ Throwable -> 0x012b }
            goto L_0x00b5
        L_0x00af:
            java.lang.String r7 = com.tencent.smtt.utils.r.a((android.content.Context) r6)     // Catch:{ Throwable -> 0x012b }
            r2.sQua2 = r7     // Catch:{ Throwable -> 0x012b }
        L_0x00b5:
            r2.sLc = r9     // Catch:{ Throwable -> 0x012b }
            java.lang.String r7 = com.tencent.smtt.utils.b.e(r6)     // Catch:{ Throwable -> 0x012b }
            java.lang.String r8 = com.tencent.smtt.utils.b.c(r6)     // Catch:{ Throwable -> 0x012b }
            java.lang.String r9 = com.tencent.smtt.utils.b.d(r6)     // Catch:{ Throwable -> 0x012b }
            java.lang.String r3 = com.tencent.smtt.utils.b.f(r6)     // Catch:{ Throwable -> 0x012b }
            if (r8 == 0) goto L_0x00d3
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r8)     // Catch:{ Throwable -> 0x012b }
            if (r4 != 0) goto L_0x00d3
            r2.sImei = r8     // Catch:{ Throwable -> 0x012b }
        L_0x00d3:
            if (r9 == 0) goto L_0x00df
            java.lang.String r8 = ""
            boolean r8 = r8.equals(r9)     // Catch:{ Throwable -> 0x012b }
            if (r8 != 0) goto L_0x00df
            r2.sImsi = r9     // Catch:{ Throwable -> 0x012b }
        L_0x00df:
            boolean r8 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x012b }
            if (r8 != 0) goto L_0x00e7
            r2.sAndroidID = r3     // Catch:{ Throwable -> 0x012b }
        L_0x00e7:
            if (r7 == 0) goto L_0x00f3
            java.lang.String r8 = ""
            boolean r8 = r8.equals(r7)     // Catch:{ Throwable -> 0x012b }
            if (r8 != 0) goto L_0x00f3
            r2.sMac = r7     // Catch:{ Throwable -> 0x012b }
        L_0x00f3:
            long r7 = (long) r10     // Catch:{ Throwable -> 0x012b }
            r2.iPv = r7     // Catch:{ Throwable -> 0x012b }
            boolean r7 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r6)     // Catch:{ Throwable -> 0x012b }
            if (r7 == 0) goto L_0x010f
            if (r11 == 0) goto L_0x010c
            boolean r7 = com.tencent.smtt.sdk.TbsShareManager.getCoreFormOwn()     // Catch:{ Throwable -> 0x012b }
            if (r7 == 0) goto L_0x0108
            r7 = 2
            r2.iCoreType = r7     // Catch:{ Throwable -> 0x012b }
            goto L_0x0111
        L_0x0108:
            r7 = 1
            r2.iCoreType = r7     // Catch:{ Throwable -> 0x012b }
            goto L_0x0111
        L_0x010c:
            r2.iCoreType = r1     // Catch:{ Throwable -> 0x012b }
            goto L_0x0111
        L_0x010f:
            r2.iCoreType = r11     // Catch:{ Throwable -> 0x012b }
        L_0x0111:
            r2.sAppVersionName = r0     // Catch:{ Throwable -> 0x012b }
            java.lang.String r7 = a(r6)     // Catch:{ Throwable -> 0x012b }
            r2.sAppSignature = r7     // Catch:{ Throwable -> 0x012b }
            if (r11 != 0) goto L_0x0123
            r2.sWifiConnectedTime = r12     // Catch:{ Throwable -> 0x012b }
            int r7 = com.tencent.smtt.sdk.QbSdk.getTbsVersion(r6)     // Catch:{ Throwable -> 0x012b }
            r2.localCoreVersion = r7     // Catch:{ Throwable -> 0x012b }
        L_0x0123:
            android.content.Context r6 = r6.getApplicationContext()     // Catch:{ Throwable -> 0x012b }
            a(r2, r6)     // Catch:{ Throwable -> 0x012b }
            goto L_0x012f
        L_0x012b:
            r6 = move-exception
            r6.printStackTrace()
        L_0x012f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.a.b.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, int, boolean, long):void");
    }

    /* access modifiers changed from: private */
    public static JSONObject c(ThirdAppInfoNew thirdAppInfoNew, Context context) {
        String str;
        String str2;
        String str3;
        int i;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("APPNAME", thirdAppInfoNew.sAppName);
            jSONObject.put("TIME", thirdAppInfoNew.sTime);
            jSONObject.put("QUA2", thirdAppInfoNew.sQua2);
            jSONObject.put("LC", thirdAppInfoNew.sLc);
            jSONObject.put("GUID", thirdAppInfoNew.sGuid);
            jSONObject.put("IMEI", thirdAppInfoNew.sImei);
            jSONObject.put("IMSI", thirdAppInfoNew.sImsi);
            jSONObject.put("MAC", thirdAppInfoNew.sMac);
            jSONObject.put("PV", thirdAppInfoNew.iPv);
            jSONObject.put("CORETYPE", thirdAppInfoNew.iCoreType);
            jSONObject.put("APPVN", thirdAppInfoNew.sAppVersionName);
            if (thirdAppInfoNew.sAppSignature == null) {
                str = "SIGNATURE";
                str2 = "0";
            } else {
                str = "SIGNATURE";
                str2 = thirdAppInfoNew.sAppSignature;
            }
            jSONObject.put(str, str2);
            jSONObject.put("PROTOCOL_VERSION", 3);
            jSONObject.put("ANDROID_ID", thirdAppInfoNew.sAndroidID);
            if (TbsShareManager.isThirdPartyApp(context)) {
                jSONObject.put("HOST_COREVERSION", TbsShareManager.getHostCoreVersions(context));
            } else {
                jSONObject.put("HOST_COREVERSION", TbsDownloader.getCoreShareDecoupleCoreVersionByContext(context));
                jSONObject.put("DECOUPLE_COREVERSION", TbsDownloader.getCoreShareDecoupleCoreVersionByContext(context));
            }
            if (thirdAppInfoNew.iCoreType == 0) {
                jSONObject.put("WIFICONNECTEDTIME", thirdAppInfoNew.sWifiConnectedTime);
                jSONObject.put("CORE_EXIST", thirdAppInfoNew.localCoreVersion);
                int i2 = TbsCoreLoadStat.mLoadErrorCode;
                if (thirdAppInfoNew.localCoreVersion <= 0) {
                    jSONObject.put("TBS_ERROR_CODE", TbsDownloadConfig.getInstance(context).getDownloadInterruptCode());
                } else {
                    jSONObject.put("TBS_ERROR_CODE", i2);
                }
                if (i2 == -1) {
                    TbsLog.e("sdkreport", "ATTENTION: Load errorCode missed!");
                }
            }
            TbsDownloadConfig.getInstance(context).uploadDownloadInterruptCodeIfNeeded(context);
            try {
                if (QbSdk.getTID() != null) {
                    if (thirdAppInfoNew.sAppName.equals(TbsConfig.APP_QQ)) {
                        jSONObject.put("TID", o.a().a(QbSdk.getTID()));
                        str3 = "TIDTYPE";
                        i = 1;
                    } else if (thirdAppInfoNew.sAppName.equals("com.tencent.mm")) {
                        jSONObject.put("TID", QbSdk.getTID());
                        str3 = "TIDTYPE";
                        i = 0;
                    }
                    jSONObject.put(str3, i);
                }
            } catch (Exception unused) {
            }
            return jSONObject;
        } catch (Exception unused2) {
            TbsLog.e("sdkreport", "getPostData exception!");
            return null;
        }
    }
}
