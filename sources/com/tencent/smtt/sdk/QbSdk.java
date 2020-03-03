package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.a.d;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.TbsLogClient;
import com.tencent.smtt.utils.j;
import com.tencent.smtt.utils.q;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"NewApi"})
public class QbSdk {
    private static boolean A = true;
    /* access modifiers changed from: private */
    public static TbsListener B = null;
    /* access modifiers changed from: private */
    public static TbsListener C = null;
    private static boolean D = false;
    private static boolean E = false;
    public static final int EXTENSION_INIT_FAILURE = -99999;
    public static String KEY_SET_SENDREQUEST_AND_UPLOAD = "SET_SENDREQUEST_AND_UPLOAD";
    public static final String LOGIN_TYPE_KEY_PARTNER_CALL_POS = "PosID";
    public static final String LOGIN_TYPE_KEY_PARTNER_ID = "ChannelID";
    public static final String PARAM_KEY_FEATUREID = "param_key_featureid";
    public static final String PARAM_KEY_FUNCTIONID = "param_key_functionid";
    public static final String PARAM_KEY_POSITIONID = "param_key_positionid";
    public static final int QBMODE = 2;
    public static final String SVNVERSION = "jnizz";
    public static final int TBSMODE = 1;
    public static final String TID_QQNumber_Prefix = "QQ:";
    public static final int VERSION = 1;

    /* renamed from: a  reason: collision with root package name */
    static boolean f9080a = false;
    static boolean b = false;
    static boolean c = true;
    static String d = null;
    static boolean e = false;
    static long f = 0;
    static long g = 0;
    static Object h = new Object();
    static boolean i = true;
    static boolean j = true;
    static boolean k = false;
    static volatile boolean l = f9080a;
    static TbsListener m = new n();
    static Map<String, Object> n = null;
    private static int o = 0;
    private static String p = "";
    private static Class<?> q = null;
    private static Object r = null;
    private static boolean s = false;
    public static boolean sIsVersionPrinted = false;
    private static String[] t = null;
    private static String u = "NULL";
    private static String v = "UNKNOWN";
    private static int w = 0;
    private static int x = 170;
    private static String y = null;
    private static String z = null;

    public interface PreInitCallback {
        void onCoreInitFinished();

        void onViewInitFinished(boolean z);
    }

    static Bundle a(Context context, Bundle bundle) {
        TbsLogReport a2;
        String str;
        if (!a(context)) {
            a2 = TbsLogReport.a(context);
            str = "initForPatch return false!";
        } else {
            Object a3 = q.a(r, "incrUpdate", (Class<?>[]) new Class[]{Context.class, Bundle.class}, context, bundle);
            if (a3 != null) {
                return (Bundle) a3;
            }
            a2 = TbsLogReport.a(context);
            str = "incrUpdate return null!";
        }
        a2.a(216, str);
        return null;
    }

    static Object a(Context context, String str, Bundle bundle) {
        if (!a(context)) {
            return Integer.valueOf(EXTENSION_INIT_FAILURE);
        }
        Object a2 = q.a(r, "miscCall", (Class<?>[]) new Class[]{String.class, Bundle.class}, str, bundle);
        if (a2 != null) {
            return a2;
        }
        return null;
    }

    static String a() {
        return p;
    }

    static synchronized void a(Context context, String str) {
        synchronized (QbSdk.class) {
            if (!f9080a) {
                f9080a = true;
                v = "forceSysWebViewInner: " + str;
                TbsLog.e("QbSdk", "QbSdk.SysWebViewForcedInner..." + v);
                TbsCoreLoadStat.getInstance().a(context, 401, new Throwable(v));
            }
        }
    }

    static boolean a(Context context) {
        try {
            if (q != null) {
                return true;
            }
            File q2 = am.a().q(context);
            if (q2 == null) {
                TbsLog.e("QbSdk", "QbSdk initExtension (false) optDir == null");
                return false;
            }
            File file = new File(q2, "tbs_sdk_extension_dex.jar");
            if (!file.exists()) {
                TbsLog.e("QbSdk", "QbSdk initExtension (false) dexFile.exists()=false", true);
                return false;
            }
            TbsLog.i("QbSdk", "new DexLoader #3 dexFile is " + file.getAbsolutePath());
            Context context2 = context;
            q = new DexLoader(file.getParent(), context2, new String[]{file.getAbsolutePath()}, q2.getAbsolutePath(), (Map<String, Object>) null).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
            b(context, file.getParent());
            return true;
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "initExtension sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    static boolean a(Context context, int i2) {
        return a(context, i2, 20000);
    }

    static boolean a(Context context, int i2, int i3) {
        if (n != null && n.containsKey(KEY_SET_SENDREQUEST_AND_UPLOAD) && n.get(KEY_SET_SENDREQUEST_AND_UPLOAD).equals("false")) {
            TbsLog.i("QbSdk", "[QbSdk.isX5Disabled] -- SET_SENDREQUEST_AND_UPLOAD is false");
            return true;
        } else if (!c(context)) {
            return true;
        } else {
            Object a2 = q.a(r, "isX5Disabled", (Class<?>[]) new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE}, Integer.valueOf(i2), 43610, Integer.valueOf(i3));
            if (a2 == null && (a2 = q.a(r, "isX5Disabled", (Class<?>[]) new Class[]{Integer.TYPE, Integer.TYPE}, Integer.valueOf(i2), 43610)) == null) {
                return true;
            }
            return ((Boolean) a2).booleanValue();
        }
    }

    @SuppressLint({"NewApi"})
    private static boolean a(Context context, boolean z2) {
        int i2;
        File file;
        TbsCoreLoadStat instance;
        int i3;
        Exception exc;
        TbsLog.initIfNeed(context);
        if (!sIsVersionPrinted) {
            TbsLog.i("QbSdk", "svn revision: jnizz; SDK_VERSION_CODE: 43610; SDK_VERSION_NAME: 3.6.0.1249");
            sIsVersionPrinted = true;
        }
        if (f9080a && !z2) {
            TbsLog.e("QbSdk", "QbSdk init: " + v, false);
            TbsCoreLoadStat.getInstance().a(context, 414, new Throwable(v));
            return false;
        } else if (b) {
            TbsLog.e("QbSdk", "QbSdk init mIsSysWebViewForcedByOuter = true", true);
            TbsCoreLoadStat.getInstance().a(context, 402, new Throwable(u));
            return false;
        } else {
            if (!A) {
                d(context);
            }
            try {
                File q2 = am.a().q(context);
                if (q2 == null) {
                    TbsLog.e("QbSdk", "QbSdk init (false) optDir == null");
                    TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.ERROR_TBSCORE_SHARE_DIR, new Throwable("QbSdk.init (false) TbsCoreShareDir is null"));
                    return false;
                }
                if (TbsShareManager.isThirdPartyApp(context)) {
                    if (o == 0 || o == TbsShareManager.d(context)) {
                        i2 = TbsShareManager.d(context);
                    } else {
                        q = null;
                        r = null;
                        TbsLog.e("QbSdk", "QbSdk init (false) ERROR_UNMATCH_TBSCORE_VER_THIRDPARTY!");
                        TbsCoreLoadStat instance2 = TbsCoreLoadStat.getInstance();
                        instance2.a(context, 302, new Throwable("sTbsVersion: " + o + "; AvailableTbsCoreVersion: " + TbsShareManager.d(context)));
                        return false;
                    }
                } else if (o != 0) {
                    i2 = am.a().a(true, context);
                    if (o != i2) {
                        q = null;
                        r = null;
                        TbsLog.e("QbSdk", "QbSdk init (false) not isThirdPartyApp tbsCoreInstalledVer=" + i2, true);
                        TbsLog.e("QbSdk", "QbSdk init (false) not isThirdPartyApp sTbsVersion=" + o, true);
                        TbsCoreLoadStat instance3 = TbsCoreLoadStat.getInstance();
                        instance3.a(context, 303, new Throwable("sTbsVersion: " + o + "; tbsCoreInstalledVer: " + i2));
                        return false;
                    }
                } else {
                    i2 = 0;
                }
                o = i2;
                if (q != null) {
                    return true;
                }
                if (!TbsShareManager.isThirdPartyApp(context)) {
                    file = new File(am.a().q(context), "tbs_sdk_extension_dex.jar");
                } else if (TbsShareManager.j(context)) {
                    file = new File(TbsShareManager.c(context), "tbs_sdk_extension_dex.jar");
                } else {
                    TbsCoreLoadStat.getInstance().a(context, 304, new Throwable("isShareTbsCoreAvailable false!"));
                    return false;
                }
                if (!file.exists()) {
                    TbsLog.e("QbSdk", "QbSdk init (false) tbs_sdk_extension_dex.jar is not exist!");
                    int i4 = am.a().i(context);
                    if (new File(file.getParentFile(), "tbs_jars_fusion_dex.jar").exists()) {
                        if (i4 > 0) {
                            instance = TbsCoreLoadStat.getInstance();
                            i3 = 4131;
                            exc = new Exception("tbs_sdk_extension_dex not exist(with fusion dex)!" + i4);
                        } else {
                            instance = TbsCoreLoadStat.getInstance();
                            i3 = 4132;
                            exc = new Exception("tbs_sdk_extension_dex not exist(with fusion dex)!" + i4);
                        }
                    } else if (i4 > 0) {
                        instance = TbsCoreLoadStat.getInstance();
                        i3 = 4121;
                        exc = new Exception("tbs_sdk_extension_dex not exist(without fusion dex)!" + i4);
                    } else {
                        instance = TbsCoreLoadStat.getInstance();
                        i3 = 4122;
                        exc = new Exception("tbs_sdk_extension_dex not exist(without fusion dex)!" + i4);
                    }
                    instance.a(context, i3, exc);
                    return false;
                }
                String hostCorePathAppDefined = TbsShareManager.getHostCorePathAppDefined() != null ? TbsShareManager.getHostCorePathAppDefined() : q2.getAbsolutePath();
                TbsLog.i("QbSdk", "QbSdk init optDirExtension #1 is " + hostCorePathAppDefined);
                TbsLog.i("QbSdk", "new DexLoader #1 dexFile is " + file.getAbsolutePath());
                q = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, hostCorePathAppDefined, (Map<String, Object>) null).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                b(context, file.getParent());
                q.a(r, "setClientVersion", (Class<?>[]) new Class[]{Integer.TYPE}, 1);
                return true;
            } catch (Throwable th) {
                TbsLog.e("QbSdk", "QbSdk init Throwable: " + Log.getStackTraceString(th));
                TbsCoreLoadStat.getInstance().a(context, 306, th);
                return false;
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0299, code lost:
        r7.a(r6, r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x02ba, code lost:
        r0 = r2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0162  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean a(android.content.Context r6, boolean r7, boolean r8) {
        /*
            boolean r8 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r6)
            r0 = 0
            if (r8 == 0) goto L_0x0017
            boolean r8 = com.tencent.smtt.sdk.TbsShareManager.i(r6)
            if (r8 != 0) goto L_0x0017
            com.tencent.smtt.sdk.TbsCoreLoadStat r7 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            r8 = 302(0x12e, float:4.23E-43)
            r7.a(r6, r8)
            return r0
        L_0x0017:
            boolean r7 = a((android.content.Context) r6, (boolean) r7)
            if (r7 != 0) goto L_0x0025
            java.lang.String r6 = "QbSdk"
            java.lang.String r7 = "QbSdk.init failure!"
            com.tencent.smtt.utils.TbsLog.e(r6, r7)
            return r0
        L_0x0025:
            java.lang.Object r7 = r
            java.lang.String r8 = "canLoadX5Core"
            r1 = 1
            java.lang.Class[] r2 = new java.lang.Class[r1]
            java.lang.Class r3 = java.lang.Integer.TYPE
            r2[r0] = r3
            java.lang.Object[] r3 = new java.lang.Object[r1]
            r4 = 43610(0xaa5a, float:6.111E-41)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r0] = r4
            java.lang.Object r7 = com.tencent.smtt.utils.q.a((java.lang.Object) r7, (java.lang.String) r8, (java.lang.Class<?>[]) r2, (java.lang.Object[]) r3)
            if (r7 == 0) goto L_0x02c9
            boolean r8 = r7 instanceof java.lang.String
            if (r8 == 0) goto L_0x0051
            r8 = r7
            java.lang.String r8 = (java.lang.String) r8
            java.lang.String r2 = "AuthenticationFail"
            boolean r8 = r8.equalsIgnoreCase(r2)
            if (r8 == 0) goto L_0x0051
            return r0
        L_0x0051:
            boolean r8 = r7 instanceof android.os.Bundle
            if (r8 != 0) goto L_0x007c
            com.tencent.smtt.sdk.TbsCoreLoadStat r8 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            r1 = 330(0x14a, float:4.62E-43)
            java.lang.Throwable r2 = new java.lang.Throwable
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = ""
            r3.append(r4)
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            r2.<init>(r7)
            r8.a(r6, r1, r2)
            java.lang.String r6 = "loaderror"
            java.lang.String r7 = "ret not instance of bundle"
            com.tencent.smtt.utils.TbsLog.e(r6, r7)
            return r0
        L_0x007c:
            r8 = r7
            android.os.Bundle r8 = (android.os.Bundle) r8
            boolean r2 = r8.isEmpty()
            if (r2 == 0) goto L_0x00ac
            com.tencent.smtt.sdk.TbsCoreLoadStat r8 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            r1 = 331(0x14b, float:4.64E-43)
            java.lang.Throwable r2 = new java.lang.Throwable
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = ""
            r3.append(r4)
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            r2.<init>(r7)
            r8.a(r6, r1, r2)
            java.lang.String r6 = "loaderror"
            java.lang.String r7 = "empty bundle"
            com.tencent.smtt.utils.TbsLog.e(r6, r7)
            return r0
        L_0x00ac:
            r7 = -1
            java.lang.String r2 = "result_code"
            int r2 = r8.getInt(r2, r7)     // Catch:{ Exception -> 0x00b5 }
            r7 = r2
            goto L_0x00d0
        L_0x00b5:
            r2 = move-exception
            java.lang.String r3 = "QbSdk"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "bundle.getInt(KEY_RESULT_CODE) error : "
            r4.append(r5)
            java.lang.String r2 = r2.toString()
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            com.tencent.smtt.utils.TbsLog.e(r3, r2)
        L_0x00d0:
            if (r7 != 0) goto L_0x00d4
            r2 = 1
            goto L_0x00d5
        L_0x00d4:
            r2 = 0
        L_0x00d5:
            boolean r3 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r6)
            r4 = 307(0x133, float:4.3E-43)
            if (r3 == 0) goto L_0x011b
            int r1 = com.tencent.smtt.sdk.TbsShareManager.d(r6)
            com.tencent.smtt.sdk.o.a((int) r1)
            int r1 = com.tencent.smtt.sdk.TbsShareManager.d(r6)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            p = r1
            java.lang.String r1 = p
            int r1 = r1.length()
            r3 = 5
            if (r1 != r3) goto L_0x010c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "0"
            r1.append(r3)
            java.lang.String r3 = p
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            p = r1
        L_0x010c:
            java.lang.String r1 = p
            int r1 = r1.length()
            r3 = 6
            if (r1 == r3) goto L_0x01b9
            java.lang.String r1 = ""
            p = r1
            goto L_0x01b9
        L_0x011b:
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x013b }
            r5 = 12
            if (r3 < r5) goto L_0x012c
            java.lang.String r3 = "tbs_core_version"
            java.lang.String r5 = "0"
            java.lang.String r3 = r8.getString(r3, r5)     // Catch:{ Exception -> 0x013b }
        L_0x0129:
            p = r3     // Catch:{ Exception -> 0x013b }
            goto L_0x013f
        L_0x012c:
            java.lang.String r3 = "tbs_core_version"
            java.lang.String r3 = r8.getString(r3)     // Catch:{ Exception -> 0x013b }
            p = r3     // Catch:{ Exception -> 0x013b }
            java.lang.String r3 = p     // Catch:{ Exception -> 0x013b }
            if (r3 != 0) goto L_0x013f
            java.lang.String r3 = "0"
            goto L_0x0129
        L_0x013b:
            java.lang.String r3 = "0"
            p = r3
        L_0x013f:
            java.lang.String r3 = p     // Catch:{ NumberFormatException -> 0x0148 }
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ NumberFormatException -> 0x0148 }
            o = r3     // Catch:{ NumberFormatException -> 0x0148 }
            goto L_0x014a
        L_0x0148:
            o = r0
        L_0x014a:
            int r3 = o
            com.tencent.smtt.sdk.o.a((int) r3)
            int r3 = o
            if (r3 != 0) goto L_0x0162
            com.tencent.smtt.sdk.TbsCoreLoadStat r7 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            java.lang.Throwable r8 = new java.lang.Throwable
            java.lang.String r1 = "sTbsVersion is 0"
            r8.<init>(r1)
            r7.a(r6, r4, r8)
            return r0
        L_0x0162:
            int r3 = o
            if (r3 <= 0) goto L_0x016c
            int r3 = o
            r5 = 25442(0x6362, float:3.5652E-41)
            if (r3 <= r5) goto L_0x0174
        L_0x016c:
            int r3 = o
            r5 = 25472(0x6380, float:3.5694E-41)
            if (r3 != r5) goto L_0x0173
            goto L_0x0174
        L_0x0173:
            r1 = 0
        L_0x0174:
            if (r1 == 0) goto L_0x01b9
            java.lang.String r7 = "TbsDownload"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r1 = "is_obsolete --> delete old core:"
            r8.append(r1)
            int r1 = o
            r8.append(r1)
            java.lang.String r8 = r8.toString()
            com.tencent.smtt.utils.TbsLog.e(r7, r8)
            com.tencent.smtt.sdk.am r7 = com.tencent.smtt.sdk.am.a()
            java.io.File r7 = r7.q(r6)
            com.tencent.smtt.utils.j.b((java.io.File) r7)
            com.tencent.smtt.sdk.TbsCoreLoadStat r7 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            java.lang.Throwable r8 = new java.lang.Throwable
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "is_obsolete --> delete old core:"
            r1.append(r2)
            int r2 = o
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r8.<init>(r1)
            r7.a(r6, r4, r8)
            return r0
        L_0x01b9:
            java.lang.String r1 = "tbs_jarfiles"
            java.lang.String[] r1 = r8.getStringArray(r1)     // Catch:{ Throwable -> 0x02be }
            t = r1     // Catch:{ Throwable -> 0x02be }
            java.lang.String[] r1 = t
            boolean r1 = r1 instanceof java.lang.String[]
            if (r1 != 0) goto L_0x01e7
            com.tencent.smtt.sdk.TbsCoreLoadStat r7 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            java.lang.Throwable r8 = new java.lang.Throwable
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "sJarFiles not instanceof String[]: "
            r1.append(r2)
            java.lang.String[] r2 = t
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r8.<init>(r1)
            r7.a(r6, r4, r8)
            return r0
        L_0x01e7:
            java.lang.String r1 = "tbs_librarypath"
            java.lang.String r8 = r8.getString(r1)     // Catch:{ Exception -> 0x02bd }
            d = r8     // Catch:{ Exception -> 0x02bd }
            r8 = 0
            if (r7 == 0) goto L_0x0204
            java.lang.Object r1 = r     // Catch:{ Exception -> 0x0200 }
            java.lang.String r3 = "getErrorCodeForLogReport"
            java.lang.Class[] r5 = new java.lang.Class[r0]     // Catch:{ Exception -> 0x0200 }
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x0200 }
            java.lang.Object r0 = com.tencent.smtt.utils.q.a((java.lang.Object) r1, (java.lang.String) r3, (java.lang.Class<?>[]) r5, (java.lang.Object[]) r0)     // Catch:{ Exception -> 0x0200 }
            r8 = r0
            goto L_0x0204
        L_0x0200:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0204:
            switch(r7) {
                case -2: goto L_0x0274;
                case -1: goto L_0x0230;
                case 0: goto L_0x02ba;
                default: goto L_0x0207;
            }
        L_0x0207:
            com.tencent.smtt.sdk.TbsCoreLoadStat r0 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            r1 = 415(0x19f, float:5.82E-43)
            java.lang.Throwable r3 = new java.lang.Throwable
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "detail: "
            r4.append(r5)
            r4.append(r8)
            java.lang.String r8 = "errcode"
            r4.append(r8)
            r4.append(r7)
            java.lang.String r7 = r4.toString()
            r3.<init>(r7)
            r0.a(r6, r1, r3)
            goto L_0x02ba
        L_0x0230:
            boolean r7 = r8 instanceof java.lang.Integer
            if (r7 == 0) goto L_0x0256
            com.tencent.smtt.sdk.TbsCoreLoadStat r7 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            r0 = r8
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            java.lang.Throwable r1 = new java.lang.Throwable
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "detail: "
            r3.append(r4)
            r3.append(r8)
            java.lang.String r8 = r3.toString()
            r1.<init>(r8)
            goto L_0x0299
        L_0x0256:
            com.tencent.smtt.sdk.TbsCoreLoadStat r7 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            java.lang.Throwable r0 = new java.lang.Throwable
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "detail: "
            r1.append(r3)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            r0.<init>(r8)
            r7.a(r6, r4, r0)
            goto L_0x02ba
        L_0x0274:
            boolean r7 = r8 instanceof java.lang.Integer
            if (r7 == 0) goto L_0x029d
            com.tencent.smtt.sdk.TbsCoreLoadStat r7 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            r0 = r8
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            java.lang.Throwable r1 = new java.lang.Throwable
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "detail: "
            r3.append(r4)
            r3.append(r8)
            java.lang.String r8 = r3.toString()
            r1.<init>(r8)
        L_0x0299:
            r7.a(r6, r0, r1)
            goto L_0x02ba
        L_0x029d:
            com.tencent.smtt.sdk.TbsCoreLoadStat r7 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            r0 = 404(0x194, float:5.66E-43)
            java.lang.Throwable r1 = new java.lang.Throwable
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "detail: "
            r3.append(r4)
            r3.append(r8)
            java.lang.String r8 = r3.toString()
            r1.<init>(r8)
            goto L_0x0299
        L_0x02ba:
            r0 = r2
            goto L_0x0351
        L_0x02bd:
            return r0
        L_0x02be:
            r7 = move-exception
            com.tencent.smtt.sdk.TbsCoreLoadStat r8 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            r1 = 329(0x149, float:4.61E-43)
            r8.a(r6, r1, r7)
            return r0
        L_0x02c9:
            java.lang.Object r7 = r
            java.lang.String r8 = "canLoadX5"
            java.lang.Class[] r2 = new java.lang.Class[r1]
            java.lang.Class r3 = java.lang.Integer.TYPE
            r2[r0] = r3
            java.lang.Object[] r3 = new java.lang.Object[r1]
            int r4 = com.tencent.smtt.sdk.a.a()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r0] = r4
            java.lang.Object r7 = com.tencent.smtt.utils.q.a((java.lang.Object) r7, (java.lang.String) r8, (java.lang.Class<?>[]) r2, (java.lang.Object[]) r3)
            if (r7 == 0) goto L_0x0348
            boolean r8 = r7 instanceof java.lang.String
            if (r8 == 0) goto L_0x02f5
            r8 = r7
            java.lang.String r8 = (java.lang.String) r8
            java.lang.String r2 = "AuthenticationFail"
            boolean r8 = r8.equalsIgnoreCase(r2)
            if (r8 == 0) goto L_0x02f5
            return r0
        L_0x02f5:
            boolean r8 = r7 instanceof java.lang.Boolean
            if (r8 == 0) goto L_0x0351
            int r8 = com.tencent.smtt.sdk.o.d()
            o = r8
            int r8 = com.tencent.smtt.sdk.o.d()
            boolean r6 = a((android.content.Context) r6, (int) r8)
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r8 = r7.booleanValue()
            if (r8 == 0) goto L_0x0312
            if (r6 != 0) goto L_0x0312
            r0 = 1
        L_0x0312:
            if (r0 != 0) goto L_0x0347
            java.lang.String r8 = "loaderror"
            java.lang.String r1 = "318"
            com.tencent.smtt.utils.TbsLog.e(r8, r1)
            java.lang.String r8 = "loaderror"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "isX5Disable:"
            r1.append(r2)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            com.tencent.smtt.utils.TbsLog.w(r8, r6)
            java.lang.String r6 = "loaderror"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r1 = "(Boolean) ret:"
            r8.append(r1)
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            com.tencent.smtt.utils.TbsLog.w(r6, r7)
        L_0x0347:
            return r0
        L_0x0348:
            com.tencent.smtt.sdk.TbsCoreLoadStat r7 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            r8 = 308(0x134, float:4.32E-43)
            r7.a(r6, r8)
        L_0x0351:
            if (r0 != 0) goto L_0x035a
            java.lang.String r6 = "loaderror"
            java.lang.String r7 = "319"
            com.tencent.smtt.utils.TbsLog.e(r6, r7)
        L_0x035a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.a(android.content.Context, boolean, boolean):boolean");
    }

    protected static String b() {
        Object invokeStaticMethod;
        bt a2 = bt.a();
        if (a2 == null || !a2.b() || (invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getGUID", new Class[0], new Object[0])) == null || !(invokeStaticMethod instanceof String)) {
            return null;
        }
        return (String) invokeStaticMethod;
    }

    private static void b(Context context, String str) {
        Constructor<?> constructor;
        boolean z2;
        Object[] objArr;
        Constructor<?> constructor2;
        Object[] objArr2;
        Object newInstance;
        try {
            constructor = q.getConstructor(new Class[]{Context.class, Context.class, String.class, String.class, String.class});
            z2 = true;
        } catch (Throwable unused) {
            constructor = null;
            z2 = false;
        }
        try {
            if (TbsShareManager.isThirdPartyApp(context)) {
                Context e2 = TbsShareManager.e(context);
                if (e2 == null && TbsShareManager.getHostCorePathAppDefined() == null) {
                    TbsLogReport.a(context.getApplicationContext()).b((int) TbsListener.ErrorCode.HOST_CONTEXT_IS_NULL, "host context is null!");
                    return;
                } else if (z2) {
                    objArr = new Object[]{context, e2, TbsShareManager.getHostCorePathAppDefined(), str, null};
                    newInstance = constructor.newInstance(objArr);
                    r = newInstance;
                } else if (e2 == null) {
                    constructor2 = q.getConstructor(new Class[]{Context.class, Context.class, String.class});
                    objArr2 = new Object[]{context, e2, TbsShareManager.getHostCorePathAppDefined()};
                } else {
                    constructor2 = q.getConstructor(new Class[]{Context.class, Context.class});
                    objArr2 = new Object[]{context, e2};
                }
            } else if (!z2) {
                constructor2 = q.getConstructor(new Class[]{Context.class, Context.class});
                objArr2 = new Object[]{context, context};
            } else {
                objArr = new Object[]{context, context, null, str, null};
                newInstance = constructor.newInstance(objArr);
                r = newInstance;
            }
            newInstance = constructor2.newInstance(objArr2);
            r = newInstance;
        } catch (Throwable unused2) {
        }
    }

    static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (!context.getApplicationInfo().packageName.contains("com.tencent.portfolio")) {
                return true;
            }
            TbsLog.i("QbSdk", "clearPluginConfigFile #1");
            String string = TbsDownloadConfig.getInstance(context).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONNAME, (String) null);
            String str = context.getPackageManager().getPackageInfo("com.tencent.portfolio", 0).versionName;
            TbsLog.i("QbSdk", "clearPluginConfigFile oldAppVersionName is " + string + " newAppVersionName is " + str);
            if (string == null) {
                return true;
            }
            if (string.contains(str)) {
                return true;
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences("plugin_setting", 0);
            if (sharedPreferences == null) {
                return true;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.clear();
            edit.commit();
            TbsLog.i("QbSdk", "clearPluginConfigFile done");
            return true;
        } catch (Throwable th) {
            TbsLog.i("QbSdk", "clearPluginConfigFile error is " + th.getMessage());
            return false;
        }
    }

    private static boolean c(Context context) {
        File file;
        try {
            if (q != null) {
                return true;
            }
            File q2 = am.a().q(context);
            if (q2 == null) {
                TbsLog.e("QbSdk", "QbSdk initForX5DisableConfig (false) optDir == null");
                return false;
            }
            if (!TbsShareManager.isThirdPartyApp(context)) {
                file = new File(am.a().q(context), "tbs_sdk_extension_dex.jar");
            } else if (TbsShareManager.j(context)) {
                file = new File(TbsShareManager.c(context), "tbs_sdk_extension_dex.jar");
            } else {
                TbsCoreLoadStat.getInstance().a(context, 304);
                return false;
            }
            if (!file.exists()) {
                TbsCoreLoadStat.getInstance().a(context, 406, new Exception("initForX5DisableConfig failure -- tbs_sdk_extension_dex.jar is not exist!"));
                return false;
            }
            String hostCorePathAppDefined = TbsShareManager.getHostCorePathAppDefined() != null ? TbsShareManager.getHostCorePathAppDefined() : q2.getAbsolutePath();
            TbsLog.i("QbSdk", "QbSdk init optDirExtension #3 is " + hostCorePathAppDefined);
            TbsLog.i("QbSdk", "new DexLoader #4 dexFile is " + file.getAbsolutePath());
            q = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, hostCorePathAppDefined, (Map<String, Object>) null).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
            b(context, file.getParent());
            q.a(r, "setClientVersion", (Class<?>[]) new Class[]{Integer.TYPE}, 1);
            return true;
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "initForX5DisableConfig sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean canLoadVideo(android.content.Context r6) {
        /*
            java.lang.Object r0 = r
            java.lang.String r1 = "canLoadVideo"
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]
            java.lang.Class r4 = java.lang.Integer.TYPE
            r5 = 0
            r3[r5] = r4
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)
            r2[r5] = r4
            java.lang.Object r0 = com.tencent.smtt.utils.q.a((java.lang.Object) r0, (java.lang.String) r1, (java.lang.Class<?>[]) r3, (java.lang.Object[]) r2)
            if (r0 == 0) goto L_0x002a
            r1 = r0
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L_0x0033
            com.tencent.smtt.sdk.TbsCoreLoadStat r1 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            r2 = 313(0x139, float:4.39E-43)
            goto L_0x0030
        L_0x002a:
            com.tencent.smtt.sdk.TbsCoreLoadStat r1 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()
            r2 = 314(0x13a, float:4.4E-43)
        L_0x0030:
            r1.a(r6, r2)
        L_0x0033:
            if (r0 != 0) goto L_0x0036
            goto L_0x003c
        L_0x0036:
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r5 = r0.booleanValue()
        L_0x003c:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.canLoadVideo(android.content.Context):boolean");
    }

    public static boolean canLoadX5(Context context) {
        return a(context, false, false);
    }

    public static boolean canLoadX5FirstTimeThirdApp(Context context) {
        try {
            if (context.getApplicationInfo().packageName.contains("com.moji.mjweather") && Build.VERSION.SDK_INT == 19) {
                return true;
            }
            if (q == null) {
                File q2 = am.a().q(context);
                if (q2 == null) {
                    TbsLog.e("QbSdk", "QbSdk canLoadX5FirstTimeThirdApp (false) optDir == null");
                    return false;
                }
                File file = new File(TbsShareManager.c(context), "tbs_sdk_extension_dex.jar");
                if (!file.exists()) {
                    TbsLog.e("QbSdk", "QbSdk canLoadX5FirstTimeThirdApp (false) dexFile.exists()=false", true);
                    return false;
                }
                String hostCorePathAppDefined = TbsShareManager.getHostCorePathAppDefined() != null ? TbsShareManager.getHostCorePathAppDefined() : q2.getAbsolutePath();
                TbsLog.i("QbSdk", "QbSdk init optDirExtension #2 is " + hostCorePathAppDefined);
                TbsLog.i("QbSdk", "new DexLoader #2 dexFile is " + file.getAbsolutePath());
                q = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, hostCorePathAppDefined, (Map<String, Object>) null).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                if (r == null) {
                    if (TbsShareManager.e(context) == null && TbsShareManager.getHostCorePathAppDefined() == null) {
                        TbsLogReport.a(context.getApplicationContext()).b((int) TbsListener.ErrorCode.HOST_CONTEXT_IS_NULL, "host context is null!");
                        return false;
                    }
                    b(context, file.getParent());
                }
            }
            Object a2 = q.a(r, "canLoadX5CoreForThirdApp", (Class<?>[]) new Class[0], new Object[0]);
            if (a2 == null || !(a2 instanceof Boolean)) {
                return false;
            }
            return ((Boolean) a2).booleanValue();
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "canLoadX5FirstTimeThirdApp sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    public static void canOpenFile(Context context, String str, ValueCallback<Boolean> valueCallback) {
        new h(context, str, valueCallback).start();
    }

    public static boolean canOpenMimeFileType(Context context, String str) {
        if (!a(context, false)) {
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r5v6, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00f8 A[SYNTHETIC, Splitter:B:53:0x00f8] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0101 A[SYNTHETIC, Splitter:B:61:0x0101] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0134 A[SYNTHETIC, Splitter:B:81:0x0134] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x013a A[SYNTHETIC, Splitter:B:86:0x013a] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean canOpenWebPlus(android.content.Context r8) {
        /*
            int r0 = w
            if (r0 != 0) goto L_0x000a
            int r0 = com.tencent.smtt.sdk.a.a()
            w = r0
        L_0x000a:
            java.lang.String r0 = "QbSdk"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "canOpenWebPlus - totalRAM: "
            r1.append(r2)
            int r2 = w
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 7
            r2 = 0
            if (r0 < r1) goto L_0x013e
            int r0 = w
            int r1 = x
            if (r0 >= r1) goto L_0x0030
            goto L_0x013e
        L_0x0030:
            if (r8 != 0) goto L_0x0033
            return r2
        L_0x0033:
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x0127 }
            com.tencent.smtt.sdk.am r3 = com.tencent.smtt.sdk.am.a()     // Catch:{ Throwable -> 0x0127 }
            java.io.File r3 = r3.q(r8)     // Catch:{ Throwable -> 0x0127 }
            java.lang.String r4 = "tbs.conf"
            r1.<init>(r3, r4)     // Catch:{ Throwable -> 0x0127 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0127 }
            r3.<init>(r1)     // Catch:{ Throwable -> 0x0127 }
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0127 }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x0127 }
            java.util.Properties r3 = new java.util.Properties     // Catch:{ Throwable -> 0x0121, all -> 0x011f }
            r3.<init>()     // Catch:{ Throwable -> 0x0121, all -> 0x011f }
            r3.load(r1)     // Catch:{ Throwable -> 0x0121, all -> 0x011f }
            java.lang.String r4 = "android_sdk_max_supported"
            java.lang.String r4 = r3.getProperty(r4)     // Catch:{ Throwable -> 0x0121, all -> 0x011f }
            java.lang.String r5 = "android_sdk_min_supported"
            java.lang.String r5 = r3.getProperty(r5)     // Catch:{ Throwable -> 0x0121, all -> 0x011f }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Throwable -> 0x0121, all -> 0x011f }
            int r5 = java.lang.Integer.parseInt(r5)     // Catch:{ Throwable -> 0x0121, all -> 0x011f }
            java.lang.String r6 = android.os.Build.VERSION.SDK     // Catch:{ Throwable -> 0x0121, all -> 0x011f }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ Throwable -> 0x0121, all -> 0x011f }
            if (r6 > r4) goto L_0x0105
            if (r6 >= r5) goto L_0x0075
            goto L_0x0105
        L_0x0075:
            java.lang.String r4 = "tbs_core_version"
            java.lang.String r3 = r3.getProperty(r4)     // Catch:{ Throwable -> 0x0121, all -> 0x011f }
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ Throwable -> 0x0121, all -> 0x011f }
            r1.close()     // Catch:{ Exception -> 0x0082 }
        L_0x0082:
            r1 = 1
            java.io.File r4 = new java.io.File     // Catch:{ Throwable -> 0x00ef }
            java.io.File r5 = com.tencent.smtt.sdk.am.s(r8)     // Catch:{ Throwable -> 0x00ef }
            java.lang.String r6 = "tbs_extension.conf"
            r4.<init>(r5, r6)     // Catch:{ Throwable -> 0x00ef }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00ef }
            r5.<init>(r4)     // Catch:{ Throwable -> 0x00ef }
            java.util.Properties r0 = new java.util.Properties     // Catch:{ Throwable -> 0x00ea, all -> 0x00e8 }
            r0.<init>()     // Catch:{ Throwable -> 0x00ea, all -> 0x00e8 }
            r0.load(r5)     // Catch:{ Throwable -> 0x00ea, all -> 0x00e8 }
            java.lang.String r4 = "tbs_local_version"
            java.lang.String r4 = r0.getProperty(r4)     // Catch:{ Throwable -> 0x00ea, all -> 0x00e8 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Throwable -> 0x00ea, all -> 0x00e8 }
            java.lang.String r6 = "app_versioncode_for_switch"
            java.lang.String r6 = r0.getProperty(r6)     // Catch:{ Throwable -> 0x00ea, all -> 0x00e8 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ Throwable -> 0x00ea, all -> 0x00e8 }
            r7 = 88888888(0x54c5638, float:9.60787E-36)
            if (r3 == r7) goto L_0x00e4
            if (r4 != r7) goto L_0x00b7
            goto L_0x00e4
        L_0x00b7:
            if (r3 <= r4) goto L_0x00ba
            goto L_0x00e4
        L_0x00ba:
            if (r3 != r4) goto L_0x00e4
            if (r6 <= 0) goto L_0x00c5
            int r3 = com.tencent.smtt.utils.b.b(r8)     // Catch:{ Throwable -> 0x00ea, all -> 0x00e8 }
            if (r6 == r3) goto L_0x00c5
            goto L_0x00e4
        L_0x00c5:
            java.lang.String r3 = "x5_disabled"
            java.lang.String r0 = r0.getProperty(r3)     // Catch:{ Throwable -> 0x00ea, all -> 0x00e8 }
            boolean r0 = java.lang.Boolean.parseBoolean(r0)     // Catch:{ Throwable -> 0x00ea, all -> 0x00e8 }
            if (r0 == 0) goto L_0x00e4
            android.content.Context r8 = r8.getApplicationContext()     // Catch:{ Throwable -> 0x00ea, all -> 0x00e8 }
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8)     // Catch:{ Throwable -> 0x00ea, all -> 0x00e8 }
            android.content.SharedPreferences r8 = r8.mPreferences     // Catch:{ Throwable -> 0x00ea, all -> 0x00e8 }
            java.lang.String r0 = "switch_backupcore_enable"
            boolean r8 = r8.getBoolean(r0, r2)     // Catch:{ Throwable -> 0x00ea, all -> 0x00e8 }
            if (r8 != 0) goto L_0x00e4
            r2 = 1
        L_0x00e4:
            r5.close()     // Catch:{ Exception -> 0x00fc }
            goto L_0x00fc
        L_0x00e8:
            r8 = move-exception
            goto L_0x00ff
        L_0x00ea:
            r0 = r5
            goto L_0x00ef
        L_0x00ec:
            r8 = move-exception
            r5 = r0
            goto L_0x00ff
        L_0x00ef:
            java.lang.String r8 = "QbSdk"
            java.lang.String r2 = "canOpenWebPlus - isX5Disabled Exception"
            com.tencent.smtt.utils.TbsLog.i(r8, r2)     // Catch:{ all -> 0x00ec }
            if (r0 == 0) goto L_0x00fb
            r0.close()     // Catch:{ Exception -> 0x00fb }
        L_0x00fb:
            r2 = 1
        L_0x00fc:
            r8 = r2 ^ 1
            return r8
        L_0x00ff:
            if (r5 == 0) goto L_0x0104
            r5.close()     // Catch:{ Exception -> 0x0104 }
        L_0x0104:
            throw r8
        L_0x0105:
            java.lang.String r8 = "QbSdk"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0121, all -> 0x011f }
            r0.<init>()     // Catch:{ Throwable -> 0x0121, all -> 0x011f }
            java.lang.String r3 = "canOpenWebPlus - sdkVersion: "
            r0.append(r3)     // Catch:{ Throwable -> 0x0121, all -> 0x011f }
            r0.append(r6)     // Catch:{ Throwable -> 0x0121, all -> 0x011f }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0121, all -> 0x011f }
            com.tencent.smtt.utils.TbsLog.i(r8, r0)     // Catch:{ Throwable -> 0x0121, all -> 0x011f }
            r1.close()     // Catch:{ Exception -> 0x011e }
        L_0x011e:
            return r2
        L_0x011f:
            r8 = move-exception
            goto L_0x0138
        L_0x0121:
            r8 = move-exception
            r0 = r1
            goto L_0x0128
        L_0x0124:
            r8 = move-exception
            r1 = r0
            goto L_0x0138
        L_0x0127:
            r8 = move-exception
        L_0x0128:
            r8.printStackTrace()     // Catch:{ all -> 0x0124 }
            java.lang.String r8 = "QbSdk"
            java.lang.String r1 = "canOpenWebPlus - canLoadX5 Exception"
            com.tencent.smtt.utils.TbsLog.i(r8, r1)     // Catch:{ all -> 0x0124 }
            if (r0 == 0) goto L_0x0137
            r0.close()     // Catch:{ Exception -> 0x0137 }
        L_0x0137:
            return r2
        L_0x0138:
            if (r1 == 0) goto L_0x013d
            r1.close()     // Catch:{ Exception -> 0x013d }
        L_0x013d:
            throw r8
        L_0x013e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.canOpenWebPlus(android.content.Context):boolean");
    }

    public static boolean canUseVideoFeatrue(Context context, int i2) {
        Object a2 = q.a(r, "canUseVideoFeatrue", (Class<?>[]) new Class[]{Integer.TYPE}, Integer.valueOf(i2));
        if (a2 == null || !(a2 instanceof Boolean)) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public static void clear(Context context) {
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0070 A[SYNTHETIC, Splitter:B:19:0x0070] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void clearAllWebViewCache(android.content.Context r6, boolean r7) {
        /*
            java.lang.String r0 = "QbSdk"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "clearAllWebViewCache("
            r1.append(r2)
            r1.append(r6)
            java.lang.String r2 = ", "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r2 = ")"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            r0 = 1
            r1 = 0
            com.tencent.smtt.sdk.WebView r2 = new com.tencent.smtt.sdk.WebView     // Catch:{ Throwable -> 0x0048 }
            r2.<init>(r6)     // Catch:{ Throwable -> 0x0048 }
            com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension r2 = r2.getWebViewClientExtension()     // Catch:{ Throwable -> 0x0048 }
            if (r2 == 0) goto L_0x0066
            com.tencent.smtt.sdk.bt r1 = com.tencent.smtt.sdk.bt.a()     // Catch:{ Throwable -> 0x0045 }
            if (r1 == 0) goto L_0x0043
            boolean r2 = r1.b()     // Catch:{ Throwable -> 0x0045 }
            if (r2 == 0) goto L_0x0043
            com.tencent.smtt.sdk.bu r1 = r1.c()     // Catch:{ Throwable -> 0x0045 }
            r1.a((android.content.Context) r6, (boolean) r7)     // Catch:{ Throwable -> 0x0045 }
        L_0x0043:
            r1 = 1
            goto L_0x0066
        L_0x0045:
            r1 = move-exception
            r2 = 1
            goto L_0x004b
        L_0x0048:
            r2 = move-exception
            r1 = r2
            r2 = 0
        L_0x004b:
            java.lang.String r3 = "QbSdk"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "clearAllWebViewCache exception 2 -- "
            r4.append(r5)
            java.lang.String r1 = android.util.Log.getStackTraceString(r1)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            com.tencent.smtt.utils.TbsLog.e(r3, r1)
            r1 = r2
        L_0x0066:
            if (r1 == 0) goto L_0x0070
            java.lang.String r6 = "QbSdk"
            java.lang.String r7 = "is_in_x5_mode --> no need to clear system webview!"
            com.tencent.smtt.utils.TbsLog.i(r6, r7)
            return
        L_0x0070:
            android.webkit.WebView r1 = new android.webkit.WebView     // Catch:{ Throwable -> 0x00bd }
            r1.<init>(r6)     // Catch:{ Throwable -> 0x00bd }
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x00bd }
            r3 = 11
            if (r2 < r3) goto L_0x008a
            java.lang.String r2 = "searchBoxJavaBridge_"
            r1.removeJavascriptInterface(r2)     // Catch:{ Throwable -> 0x00bd }
            java.lang.String r2 = "accessibility"
            r1.removeJavascriptInterface(r2)     // Catch:{ Throwable -> 0x00bd }
            java.lang.String r2 = "accessibilityTraversal"
            r1.removeJavascriptInterface(r2)     // Catch:{ Throwable -> 0x00bd }
        L_0x008a:
            r1.clearCache(r0)     // Catch:{ Throwable -> 0x00bd }
            if (r7 == 0) goto L_0x0099
            android.webkit.CookieSyncManager.createInstance(r6)     // Catch:{ Throwable -> 0x00bd }
            android.webkit.CookieManager r7 = android.webkit.CookieManager.getInstance()     // Catch:{ Throwable -> 0x00bd }
            r7.removeAllCookie()     // Catch:{ Throwable -> 0x00bd }
        L_0x0099:
            android.webkit.WebViewDatabase r7 = android.webkit.WebViewDatabase.getInstance(r6)     // Catch:{ Throwable -> 0x00bd }
            r7.clearUsernamePassword()     // Catch:{ Throwable -> 0x00bd }
            android.webkit.WebViewDatabase r7 = android.webkit.WebViewDatabase.getInstance(r6)     // Catch:{ Throwable -> 0x00bd }
            r7.clearHttpAuthUsernamePassword()     // Catch:{ Throwable -> 0x00bd }
            android.webkit.WebViewDatabase r6 = android.webkit.WebViewDatabase.getInstance(r6)     // Catch:{ Throwable -> 0x00bd }
            r6.clearFormData()     // Catch:{ Throwable -> 0x00bd }
            android.webkit.WebStorage r6 = android.webkit.WebStorage.getInstance()     // Catch:{ Throwable -> 0x00bd }
            r6.deleteAllData()     // Catch:{ Throwable -> 0x00bd }
            android.webkit.WebIconDatabase r6 = android.webkit.WebIconDatabase.getInstance()     // Catch:{ Throwable -> 0x00bd }
            r6.removeAllIcons()     // Catch:{ Throwable -> 0x00bd }
            goto L_0x00d8
        L_0x00bd:
            r6 = move-exception
            java.lang.String r7 = "QbSdk"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "clearAllWebViewCache exception 1 -- "
            r0.append(r1)
            java.lang.String r6 = android.util.Log.getStackTraceString(r6)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r7, r6)
        L_0x00d8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.clearAllWebViewCache(android.content.Context, boolean):void");
    }

    public static void closeFileReader(Context context) {
        bt a2 = bt.a();
        a2.a(context);
        if (a2.b()) {
            a2.c().p();
        }
    }

    public static boolean createMiniQBShortCut(Context context, String str, String str2, Drawable drawable) {
        bt a2;
        if (!(context == null || TbsDownloader.getOverSea(context) || isMiniQBShortCutExist(context, str, str2) || (a2 = bt.a()) == null || !a2.b())) {
            Bitmap bitmap = null;
            if (drawable instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) drawable).getBitmap();
            }
            DexLoader b2 = a2.c().b();
            TbsLog.e("QbSdk", "qbsdk createMiniQBShortCut");
            Object invokeStaticMethod = b2.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createMiniQBShortCut", new Class[]{Context.class, String.class, String.class, Bitmap.class}, context, str, str2, bitmap);
            TbsLog.e("QbSdk", "qbsdk after createMiniQBShortCut ret: " + invokeStaticMethod);
            if (invokeStaticMethod != null) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0104  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void d(android.content.Context r9) {
        /*
            r0 = 1
            A = r0
            r1 = 0
            r2 = -1
            r3 = 0
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x0062 }
            r5 = 11
            r6 = 4
            if (r4 < r5) goto L_0x0015
            java.lang.String r4 = "tbs_preloadx5_check_cfg_file"
            android.content.SharedPreferences r4 = r9.getSharedPreferences(r4, r6)     // Catch:{ Throwable -> 0x0062 }
        L_0x0013:
            r3 = r4
            goto L_0x001c
        L_0x0015:
            java.lang.String r4 = "tbs_preloadx5_check_cfg_file"
            android.content.SharedPreferences r4 = r9.getSharedPreferences(r4, r1)     // Catch:{ Throwable -> 0x0062 }
            goto L_0x0013
        L_0x001c:
            java.lang.String r4 = "tbs_preload_x5_recorder"
            int r4 = r3.getInt(r4, r2)     // Catch:{ Throwable -> 0x0062 }
            if (r4 < 0) goto L_0x002b
            int r4 = r4 + 1
            if (r4 <= r6) goto L_0x0029
            return
        L_0x0029:
            r5 = r4
            goto L_0x002c
        L_0x002b:
            r5 = -1
        L_0x002c:
            com.tencent.smtt.sdk.am r7 = com.tencent.smtt.sdk.am.a()     // Catch:{ Throwable -> 0x0060 }
            int r7 = r7.i(r9)     // Catch:{ Throwable -> 0x0060 }
            if (r7 > 0) goto L_0x0037
            return
        L_0x0037:
            if (r4 > r6) goto L_0x0049
            android.content.SharedPreferences$Editor r6 = r3.edit()     // Catch:{ Throwable -> 0x0047 }
            java.lang.String r8 = "tbs_preload_x5_recorder"
            android.content.SharedPreferences$Editor r4 = r6.putInt(r8, r4)     // Catch:{ Throwable -> 0x0047 }
            r4.commit()     // Catch:{ Throwable -> 0x0047 }
            goto L_0x0049
        L_0x0047:
            r0 = move-exception
            goto L_0x0065
        L_0x0049:
            java.lang.String r4 = "tbs_preload_x5_counter"
            int r4 = r3.getInt(r4, r2)     // Catch:{ Throwable -> 0x0047 }
            if (r4 < 0) goto L_0x007f
            android.content.SharedPreferences$Editor r6 = r3.edit()     // Catch:{ Throwable -> 0x0047 }
            java.lang.String r8 = "tbs_preload_x5_counter"
            int r4 = r4 + r0
            android.content.SharedPreferences$Editor r0 = r6.putInt(r8, r4)     // Catch:{ Throwable -> 0x0047 }
            r0.commit()     // Catch:{ Throwable -> 0x0047 }
            goto L_0x0080
        L_0x0060:
            r0 = move-exception
            goto L_0x0064
        L_0x0062:
            r0 = move-exception
            r5 = -1
        L_0x0064:
            r7 = -1
        L_0x0065:
            java.lang.String r4 = "QbSdk"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "tbs_preload_x5_counter Inc exception:"
            r6.append(r8)
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            com.tencent.smtt.utils.TbsLog.e(r4, r0)
        L_0x007f:
            r4 = -1
        L_0x0080:
            r0 = 3
            if (r4 <= r0) goto L_0x0104
            java.lang.String r0 = "tbs_preload_x5_version"
            int r0 = r3.getInt(r0, r2)     // Catch:{ Throwable -> 0x00e8 }
            android.content.SharedPreferences$Editor r2 = r3.edit()     // Catch:{ Throwable -> 0x00e8 }
            if (r0 != r7) goto L_0x00c3
            com.tencent.smtt.sdk.am r3 = com.tencent.smtt.sdk.am.a()     // Catch:{ Throwable -> 0x00e8 }
            java.io.File r3 = r3.q(r9)     // Catch:{ Throwable -> 0x00e8 }
            com.tencent.smtt.utils.j.a((java.io.File) r3, (boolean) r1)     // Catch:{ Throwable -> 0x00e8 }
            com.tencent.smtt.sdk.ai r9 = com.tencent.smtt.sdk.ai.a((android.content.Context) r9)     // Catch:{ Throwable -> 0x00e8 }
            java.io.File r9 = r9.a()     // Catch:{ Throwable -> 0x00e8 }
            if (r9 == 0) goto L_0x00a7
            com.tencent.smtt.utils.j.a((java.io.File) r9, (boolean) r1)     // Catch:{ Throwable -> 0x00e8 }
        L_0x00a7:
            java.lang.String r9 = "QbSdk"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00e8 }
            r1.<init>()     // Catch:{ Throwable -> 0x00e8 }
            java.lang.String r3 = "QbSdk - preload_x5_check: tbs core "
            r1.append(r3)     // Catch:{ Throwable -> 0x00e8 }
            r1.append(r7)     // Catch:{ Throwable -> 0x00e8 }
            java.lang.String r3 = " is deleted!"
            r1.append(r3)     // Catch:{ Throwable -> 0x00e8 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x00e8 }
        L_0x00bf:
            com.tencent.smtt.utils.TbsLog.e(r9, r1)     // Catch:{ Throwable -> 0x00e8 }
            goto L_0x00df
        L_0x00c3:
            java.lang.String r9 = "QbSdk"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00e8 }
            r1.<init>()     // Catch:{ Throwable -> 0x00e8 }
            java.lang.String r3 = "QbSdk - preload_x5_check -- reset exception core_ver:"
            r1.append(r3)     // Catch:{ Throwable -> 0x00e8 }
            r1.append(r7)     // Catch:{ Throwable -> 0x00e8 }
            java.lang.String r3 = "; value:"
            r1.append(r3)     // Catch:{ Throwable -> 0x00e8 }
            r1.append(r0)     // Catch:{ Throwable -> 0x00e8 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x00e8 }
            goto L_0x00bf
        L_0x00df:
            java.lang.String r9 = "tbs_precheck_disable_version"
            r2.putInt(r9, r0)     // Catch:{ Throwable -> 0x00e8 }
            r2.commit()     // Catch:{ Throwable -> 0x00e8 }
            goto L_0x0103
        L_0x00e8:
            r9 = move-exception
            java.lang.String r0 = "QbSdk"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "tbs_preload_x5_counter disable version exception:"
            r1.append(r2)
            java.lang.String r9 = android.util.Log.getStackTraceString(r9)
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            com.tencent.smtt.utils.TbsLog.e(r0, r9)
        L_0x0103:
            return
        L_0x0104:
            if (r5 <= 0) goto L_0x011e
            if (r5 > r0) goto L_0x011e
            java.lang.String r0 = "QbSdk"
            java.lang.String r4 = "QbSdk - preload_x5_check -- before creation!"
            com.tencent.smtt.utils.TbsLog.i(r0, r4)
            com.tencent.smtt.sdk.bt r0 = com.tencent.smtt.sdk.bt.a()
            r0.a((android.content.Context) r9)
            java.lang.String r9 = "QbSdk"
            java.lang.String r0 = "QbSdk - preload_x5_check -- after creation!"
            com.tencent.smtt.utils.TbsLog.i(r9, r0)
            goto L_0x011f
        L_0x011e:
            r1 = -1
        L_0x011f:
            java.lang.String r9 = "tbs_preload_x5_counter"
            int r9 = r3.getInt(r9, r2)     // Catch:{ Throwable -> 0x0136 }
            if (r9 <= 0) goto L_0x0151
            android.content.SharedPreferences$Editor r0 = r3.edit()     // Catch:{ Throwable -> 0x0136 }
            java.lang.String r3 = "tbs_preload_x5_counter"
            int r9 = r9 + r2
            android.content.SharedPreferences$Editor r9 = r0.putInt(r3, r9)     // Catch:{ Throwable -> 0x0136 }
            r9.commit()     // Catch:{ Throwable -> 0x0136 }
            goto L_0x0151
        L_0x0136:
            r9 = move-exception
            java.lang.String r0 = "QbSdk"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "tbs_preload_x5_counter Dec exception:"
            r2.append(r3)
            java.lang.String r9 = android.util.Log.getStackTraceString(r9)
            r2.append(r9)
            java.lang.String r9 = r2.toString()
            com.tencent.smtt.utils.TbsLog.e(r0, r9)
        L_0x0151:
            java.lang.String r9 = "QbSdk"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "QbSdk -- preload_x5_check result:"
            r0.append(r2)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.i(r9, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.d(android.content.Context):void");
    }

    public static boolean deleteMiniQBShortCut(Context context, String str, String str2) {
        bt a2;
        if (context != null && !TbsDownloader.getOverSea(context) && (a2 = bt.a()) != null && a2.b()) {
            if (a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "deleteMiniQBShortCut", new Class[]{Context.class, String.class, String.class}, context, str, str2) != null) {
                return true;
            }
        }
        return false;
    }

    public static void disAllowThirdAppDownload() {
        c = false;
    }

    public static void disableAutoCreateX5Webview() {
        j = false;
    }

    public static void fileInfoDetect(Context context, String str, ValueCallback<String> valueCallback) {
        bt a2 = bt.a();
        if (a2 != null && a2.b()) {
            try {
                a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "fileInfoDetect", new Class[]{Context.class, String.class, ValueCallback.class}, context, str, valueCallback);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void forceSysWebView() {
        b = true;
        u = "SysWebViewForcedByOuter: " + Log.getStackTraceString(new Throwable());
        TbsLog.e("QbSdk", "sys WebView: SysWebViewForcedByOuter");
    }

    public static long getApkFileSize(Context context) {
        if (context != null) {
            return TbsDownloadConfig.getInstance(context.getApplicationContext()).mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSAPKFILESIZE, 0);
        }
        return 0;
    }

    public static String[] getDexLoaderFileList(Context context, Context context2, String str) {
        if (t instanceof String[]) {
            int length = t.length;
            String[] strArr = new String[length];
            for (int i2 = 0; i2 < length; i2++) {
                strArr[i2] = str + t[i2];
            }
            return strArr;
        }
        Object a2 = q.a(r, "getJarFiles", (Class<?>[]) new Class[]{Context.class, Context.class, String.class}, context, context2, str);
        boolean z2 = a2 instanceof String[];
        String[] strArr2 = a2;
        if (!z2) {
            strArr2 = new String[]{""};
        }
        return (String[]) strArr2;
    }

    public static boolean getDownloadWithoutWifi() {
        return D;
    }

    public static boolean getIsSysWebViewForcedByOuter() {
        return b;
    }

    public static String getMiniQBVersion(Context context) {
        bt a2 = bt.a();
        a2.a(context);
        if (a2 == null || !a2.b()) {
            return null;
        }
        return a2.c().f();
    }

    public static boolean getOnlyDownload() {
        return k;
    }

    public static String getQQBuildNumber() {
        return z;
    }

    public static Map<String, Object> getSettings() {
        return n;
    }

    public static boolean getTBSInstalling() {
        return E;
    }

    public static String getTID() {
        return y;
    }

    public static String getTbsResourcesPath(Context context) {
        return TbsShareManager.g(context);
    }

    public static int getTbsVersion(Context context) {
        if (TbsShareManager.isThirdPartyApp(context)) {
            return TbsShareManager.a(context, false);
        }
        int i2 = am.a().i(context);
        if (i2 == 0 && ai.a(context).c() == 3) {
            reset(context);
        }
        return i2;
    }

    public static int getTbsVersionForCrash(Context context) {
        if (TbsShareManager.isThirdPartyApp(context)) {
            return TbsShareManager.a(context, false);
        }
        int j2 = am.a().j(context);
        if (j2 == 0 && ai.a(context).c() == 3) {
            reset(context);
        }
        return j2;
    }

    public static void initBuglyAsync(boolean z2) {
        i = z2;
    }

    public static void initTbsSettings(Map<String, Object> map) {
        if (n == null) {
            n = map;
            return;
        }
        try {
            n.putAll(map);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void initX5Environment(Context context, PreInitCallback preInitCallback) {
        if (context != null) {
            b(context);
            C = new l(context, preInitCallback);
            if (TbsShareManager.isThirdPartyApp(context)) {
                am.a().b(context, true);
            }
            TbsDownloader.needDownload(context, false, false, new m(context, preInitCallback));
        }
    }

    public static boolean installLocalQbApk(Context context, String str, String str2, Bundle bundle) {
        o a2 = o.a(true);
        a2.a(context, false, false);
        if (a2 == null || !a2.b()) {
            return false;
        }
        return a2.a().a(context, str, str2, bundle);
    }

    public static boolean intentDispatch(WebView webView, Intent intent, String str, String str2) {
        if (webView == null) {
            return false;
        }
        if (str.startsWith("mttbrowser://miniqb/ch=icon?")) {
            Context context = webView.getContext();
            int indexOf = str.indexOf("url=");
            str = indexOf > 0 ? str.substring(indexOf + 4) : null;
            HashMap hashMap = new HashMap();
            String str3 = "unknown";
            try {
                str3 = context.getApplicationInfo().packageName;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, str3);
            hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, "14004");
            if (d.a(context, "miniqb://home".equals(str) ? "qb://navicard/addCard?cardId=168&cardName=168" : str, hashMap, "QbSdk.startMiniQBToLoadUrl", (WebView) null) != 0) {
                bt a2 = bt.a();
                if (a2 != null && a2.b() && a2.c().a(context, str, (Map<String, String>) null, str2, (ValueCallback<String>) null) == 0) {
                    return true;
                }
            }
            return false;
        }
        webView.loadUrl(str);
        return false;
    }

    public static boolean isMiniQBShortCutExist(Context context, String str, String str2) {
        bt a2;
        Object invokeStaticMethod;
        if (context == null || TbsDownloader.getOverSea(context) || (a2 = bt.a()) == null || !a2.b() || (invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "isMiniQBShortCutExist", new Class[]{Context.class, String.class}, context, str)) == null) {
            return false;
        }
        Boolean bool = false;
        if (invokeStaticMethod instanceof Boolean) {
            bool = (Boolean) invokeStaticMethod;
        }
        return bool.booleanValue();
    }

    public static boolean isTbsCoreInited() {
        o a2 = o.a(false);
        return a2 != null && a2.g();
    }

    public static boolean isX5DisabledSync(Context context) {
        if (ai.a(context).c() == 2) {
            return false;
        }
        if (!c(context)) {
            return true;
        }
        Object a2 = q.a(r, "isX5DisabledSync", (Class<?>[]) new Class[]{Integer.TYPE, Integer.TYPE}, Integer.valueOf(am.a().i(context)), 43610);
        if (a2 != null) {
            return ((Boolean) a2).booleanValue();
        }
        return true;
    }

    public static synchronized void preInit(Context context) {
        synchronized (QbSdk.class) {
            preInit(context, (PreInitCallback) null);
        }
    }

    public static synchronized void preInit(Context context, PreInitCallback preInitCallback) {
        synchronized (QbSdk.class) {
            TbsLog.initIfNeed(context);
            TbsLog.i("QbSdk", "preInit -- stack: " + Log.getStackTraceString(new Throwable("#")));
            l = f9080a;
            if (!s) {
                k kVar = new k(context, new j(Looper.getMainLooper(), preInitCallback, context));
                kVar.setName("tbs_preinit");
                kVar.setPriority(10);
                kVar.start();
                s = true;
            }
        }
    }

    public static void reset(Context context) {
        reset(context, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x005d A[Catch:{ Throwable -> 0x007d }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0062 A[Catch:{ Throwable -> 0x007d }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void reset(android.content.Context r4, boolean r5) {
        /*
            java.lang.String r0 = "QbSdk"
            java.lang.String r1 = "QbSdk reset!"
            r2 = 1
            com.tencent.smtt.utils.TbsLog.e(r0, r1, r2)
            com.tencent.smtt.sdk.TbsDownloader.stopDownload()     // Catch:{ Throwable -> 0x007d }
            r0 = 0
            if (r5 == 0) goto L_0x002d
            boolean r5 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r4)     // Catch:{ Throwable -> 0x007d }
            if (r5 != 0) goto L_0x002d
            com.tencent.smtt.sdk.am r5 = com.tencent.smtt.sdk.am.a()     // Catch:{ Throwable -> 0x007d }
            int r5 = r5.h(r4)     // Catch:{ Throwable -> 0x007d }
            com.tencent.smtt.sdk.am r1 = com.tencent.smtt.sdk.am.a()     // Catch:{ Throwable -> 0x007d }
            int r1 = r1.i(r4)     // Catch:{ Throwable -> 0x007d }
            r3 = 43300(0xa924, float:6.0676E-41)
            if (r5 <= r3) goto L_0x002d
            if (r5 == r1) goto L_0x002d
            r5 = 1
            goto L_0x002e
        L_0x002d:
            r5 = 0
        L_0x002e:
            com.tencent.smtt.sdk.TbsDownloader.b((android.content.Context) r4)     // Catch:{ Throwable -> 0x007d }
            java.lang.String r1 = "tbs"
            java.io.File r1 = r4.getDir(r1, r0)     // Catch:{ Throwable -> 0x007d }
            java.lang.String r3 = "core_share_decouple"
            com.tencent.smtt.utils.j.a((java.io.File) r1, (boolean) r0, (java.lang.String) r3)     // Catch:{ Throwable -> 0x007d }
            java.lang.String r1 = "QbSdk"
            java.lang.String r3 = "delete downloaded apk success"
            com.tencent.smtt.utils.TbsLog.i(r1, r3, r2)     // Catch:{ Throwable -> 0x007d }
            java.lang.ThreadLocal<java.lang.Integer> r1 = com.tencent.smtt.sdk.am.f9142a     // Catch:{ Throwable -> 0x007d }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x007d }
            r1.set(r0)     // Catch:{ Throwable -> 0x007d }
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x007d }
            java.io.File r1 = r4.getFilesDir()     // Catch:{ Throwable -> 0x007d }
            java.lang.String r2 = "bugly_switch.txt"
            r0.<init>(r1, r2)     // Catch:{ Throwable -> 0x007d }
            boolean r1 = r0.exists()     // Catch:{ Throwable -> 0x007d }
            if (r1 == 0) goto L_0x0060
            r0.delete()     // Catch:{ Throwable -> 0x007d }
        L_0x0060:
            if (r5 == 0) goto L_0x0098
            com.tencent.smtt.sdk.am r5 = com.tencent.smtt.sdk.am.a()     // Catch:{ Throwable -> 0x007d }
            java.io.File r5 = r5.p(r4)     // Catch:{ Throwable -> 0x007d }
            com.tencent.smtt.sdk.am r0 = com.tencent.smtt.sdk.am.a()     // Catch:{ Throwable -> 0x007d }
            java.io.File r0 = r0.t(r4)     // Catch:{ Throwable -> 0x007d }
            com.tencent.smtt.utils.j.b(r5, r0)     // Catch:{ Throwable -> 0x007d }
            com.tencent.smtt.sdk.am r5 = com.tencent.smtt.sdk.am.a()     // Catch:{ Throwable -> 0x007d }
            r5.b(r4)     // Catch:{ Throwable -> 0x007d }
            goto L_0x0098
        L_0x007d:
            r4 = move-exception
            java.lang.String r5 = "QbSdk"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "QbSdk reset exception:"
            r0.append(r1)
            java.lang.String r4 = android.util.Log.getStackTraceString(r4)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r5, r4)
        L_0x0098:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.reset(android.content.Context, boolean):void");
    }

    public static void resetDecoupleCore(Context context) {
        TbsLog.e("QbSdk", "QbSdk resetDecoupleCore!", true);
        try {
            j.b(am.a().p(context));
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "QbSdk resetDecoupleCore exception:" + Log.getStackTraceString(th));
        }
    }

    public static void setCurrentID(String str) {
        if (str != null && str.startsWith(TID_QQNumber_Prefix)) {
            String substring = str.substring(TID_QQNumber_Prefix.length());
            y = "0000000000000000".substring(substring.length()) + substring;
        }
    }

    public static void setDownloadWithoutWifi(boolean z2) {
        D = z2;
    }

    public static void setOnlyDownload(boolean z2) {
        k = z2;
    }

    public static void setQQBuildNumber(String str) {
        z = str;
    }

    public static void setTBSInstallingStatus(boolean z2) {
        E = z2;
    }

    public static void setTbsListener(TbsListener tbsListener) {
        B = tbsListener;
    }

    public static void setTbsLogClient(TbsLogClient tbsLogClient) {
        TbsLog.setTbsLogClient(tbsLogClient);
    }

    public static int startMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        TbsCoreLoadStat.getInstance().a(context, 501);
        if (context == null) {
            return -100;
        }
        bt a2 = bt.a();
        a2.a(context);
        if (!a2.b()) {
            TbsCoreLoadStat.getInstance().a(context, 502);
            Log.e("QbSdk", "startMiniQBToLoadUrl  ret = -102");
            return -102;
        } else if (context != null && context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") && getTbsVersion(context) < 25487) {
            return -101;
        } else {
            int a3 = a2.c().a(context, str, hashMap, (String) null, valueCallback);
            if (a3 == 0) {
                TbsCoreLoadStat.getInstance().a(context, 503);
            } else {
                TbsLogReport a4 = TbsLogReport.a(context);
                a4.b(504, "" + a3);
            }
            Log.e("QbSdk", "startMiniQBToLoadUrl  ret = " + a3);
            return a3;
        }
    }

    public static boolean startQBForDoc(Context context, String str, int i2, int i3, String str2, Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationContext().getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i2));
        return d.a(context, str, i3, str2, hashMap, bundle);
    }

    public static boolean startQBForVideo(Context context, String str, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i2));
        return d.a(context, str, (HashMap<String, String>) hashMap);
    }

    public static boolean startQBToLoadurl(Context context, String str, int i2, WebView webView) {
        bt a2;
        Object invokeStaticMethod;
        IX5WebViewBase iX5WebViewBase;
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i2));
        if (webView == null) {
            try {
                String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
                if (!((str2 != "com.tencent.mm" && str2 != TbsConfig.APP_QQ) || (a2 = bt.a()) == null || !a2.b() || (invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.smtt.webkit.WebViewList", "getCurrentMainWebviewJustForQQandWechat", new Class[0], new Object[0])) == null || (iX5WebViewBase = (IX5WebViewBase) invokeStaticMethod) == null)) {
                    webView = (WebView) iX5WebViewBase.getView().getParent();
                }
            } catch (Exception unused) {
            }
        }
        return d.a(context, str, hashMap, "QbSdk.startQBToLoadurl", webView) == 0;
    }

    public static boolean startQbOrMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        if (context == null) {
            return false;
        }
        bt a2 = bt.a();
        a2.a(context);
        if (hashMap != null && "5".equals(hashMap.get(LOGIN_TYPE_KEY_PARTNER_CALL_POS)) && a2.b()) {
            Bundle bundle = (Bundle) a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getAdWebViewInfoFromX5Core", new Class[0], new Object[0]);
        }
        if (d.a(context, str, hashMap, "QbSdk.startMiniQBToLoadUrl", (WebView) null) != 0) {
            return a2.b() && (context == null || !context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") || getTbsVersion(context) >= 25487) && a2.c().a(context, str, hashMap, (String) null, valueCallback) == 0;
        }
        return true;
    }

    public static void unForceSysWebView() {
        b = false;
        TbsLog.e("QbSdk", "sys WebView: unForceSysWebView called");
    }

    public static boolean useSoftWare() {
        if (r == null) {
            return false;
        }
        Object a2 = q.a(r, "useSoftWare", (Class<?>[]) new Class[0], new Object[0]);
        if (a2 == null) {
            a2 = q.a(r, "useSoftWare", (Class<?>[]) new Class[]{Integer.TYPE}, Integer.valueOf(a.a()));
        }
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }
}
