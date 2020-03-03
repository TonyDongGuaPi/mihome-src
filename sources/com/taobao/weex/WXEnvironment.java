package com.taobao.weex;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXConfig;
import com.taobao.weex.utils.FontDO;
import com.taobao.weex.utils.LogLevel;
import com.taobao.weex.utils.TypefaceUtil;
import com.taobao.weex.utils.WXFileUtils;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXEnvironment {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final int CORE_JSB_SO_VERSION = 1;
    public static final String CORE_JSC_SO_NAME = "JavaScriptCore";
    private static String CORE_JSC_SO_PATH = null;
    private static String CORE_JSS_ICU_PATH = null;
    public static final String CORE_JSS_SO_NAME = "weexjss";
    private static String CORE_JSS_SO_PATH = null;
    public static final String CORE_SO_NAME = "weexcore";
    public static final String DEV_Id = getDevId();
    public static final String EAGLE = "eagle";
    public static final String ENVIRONMENT = "environment";
    public static String JS_LIB_SDK_VERSION = "null";
    public static volatile boolean JsFrameworkInit = false;
    private static String LIB_LD_PATH = null;
    public static final String OS = "android";
    public static final String SETTING_EXCLUDE_X86SUPPORT = "env_exclude_x86";
    public static boolean SETTING_FORCE_VERTICAL_SCREEN = false;
    public static final String SYS_MODEL = Build.MODEL;
    public static String SYS_VERSION = null;
    public static final String WEEX_CURRENT_KEY = "wx_current_url";
    public static String WXSDK_VERSION = BuildConfig.buildVersion;
    private static boolean isApkDebug = true;
    public static boolean isPerf = false;
    private static boolean openDebugLog = false;
    private static Map<String, String> options = new HashMap();
    public static Application sApplication = null;
    public static long sComponentsAndModulesReadyTime = 0;
    private static boolean sDebugFlagInit = false;
    public static boolean sDebugMode = false;
    public static boolean sDebugNetworkEventReporterEnable = false;
    public static boolean sDebugServerConnectable = false;
    public static String sDebugWsUrl = "";
    @Deprecated
    public static int sDefaultWidth = 750;
    public static boolean sDynamicMode = false;
    public static String sDynamicUrl = "";
    public static final boolean sForceEnableDevTool = true;
    private static String sGlobalFontFamily;
    public static boolean sInAliWeex = false;
    public static long sJSFMStartListenerTime = 0;
    public static long sJSLibInitTime = 0;
    public static LogLevel sLogLevel = LogLevel.DEBUG;
    public static boolean sRemoteDebugMode = false;
    public static String sRemoteDebugProxyUrl = "";
    public static long sSDKInitExecuteTime = 0;
    public static long sSDKInitInvokeTime = 0;
    public static long sSDKInitStart = 0;
    public static long sSDKInitTime = 0;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(1564347387438302021L, "com/taobao/weex/WXEnvironment", PsExtractor.VIDEO_STREAM_MASK);
        $jacocoData = a2;
        return a2;
    }

    public WXEnvironment() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        SYS_VERSION = Build.VERSION.RELEASE;
        $jacocoInit[231] = true;
        if (SYS_VERSION == null) {
            $jacocoInit[232] = true;
        } else if (!SYS_VERSION.toUpperCase().equals("P")) {
            $jacocoInit[233] = true;
        } else {
            SYS_VERSION = "9.0.0";
            $jacocoInit[234] = true;
        }
        $jacocoInit[235] = true;
        $jacocoInit[236] = true;
        $jacocoInit[237] = true;
        options.put("os", "android");
        $jacocoInit[238] = true;
        options.put(WXConfig.osName, "android");
        $jacocoInit[239] = true;
    }

    public static Map<String, String> getConfig() {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        HashMap hashMap = new HashMap();
        $jacocoInit[1] = true;
        hashMap.put("os", "android");
        $jacocoInit[2] = true;
        hashMap.put("appVersion", getAppVersionName());
        $jacocoInit[3] = true;
        hashMap.put(WXConfig.cacheDir, getAppCacheFile());
        $jacocoInit[4] = true;
        hashMap.put("devId", DEV_Id);
        $jacocoInit[5] = true;
        hashMap.put(WXConfig.sysVersion, SYS_VERSION);
        $jacocoInit[6] = true;
        hashMap.put(WXConfig.sysModel, SYS_MODEL);
        $jacocoInit[7] = true;
        hashMap.put(WXConfig.weexVersion, String.valueOf(WXSDK_VERSION));
        $jacocoInit[8] = true;
        hashMap.put(WXConfig.logLevel, sLogLevel.getName());
        try {
            $jacocoInit[9] = true;
            if (isLayoutDirectionRTL()) {
                str = Constants.Name.RTL;
                $jacocoInit[10] = true;
            } else {
                str = "ltr";
                $jacocoInit[11] = true;
            }
            hashMap.put(WXConfig.layoutDirection, str);
            $jacocoInit[12] = true;
        } catch (Exception unused) {
            $jacocoInit[13] = true;
            hashMap.put(WXConfig.layoutDirection, "ltr");
            try {
                $jacocoInit[14] = true;
            } catch (NullPointerException e) {
                $jacocoInit[19] = true;
                WXLogUtils.e("WXEnvironment scale Exception: ", (Throwable) e);
                $jacocoInit[20] = true;
            }
        }
        if (!isApkDebugable()) {
            $jacocoInit[15] = true;
        } else {
            $jacocoInit[16] = true;
            options.put(WXConfig.debugMode, "true");
            $jacocoInit[17] = true;
        }
        options.put("scale", Float.toString(sApplication.getResources().getDisplayMetrics().density));
        $jacocoInit[18] = true;
        hashMap.putAll(options);
        $jacocoInit[21] = true;
        if (hashMap.get("appName") != null) {
            $jacocoInit[23] = true;
        } else if (sApplication == null) {
            $jacocoInit[24] = true;
        } else {
            $jacocoInit[25] = true;
            hashMap.put("appName", sApplication.getPackageName());
            $jacocoInit[26] = true;
        }
        $jacocoInit[27] = true;
        return hashMap;
    }

    private static String getAppVersionName() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = "";
        try {
            $jacocoInit[28] = true;
            PackageManager packageManager = sApplication.getPackageManager();
            $jacocoInit[29] = true;
            String str2 = packageManager.getPackageInfo(sApplication.getPackageName(), 0).versionName;
            $jacocoInit[30] = true;
            str = str2;
        } catch (Exception e) {
            $jacocoInit[31] = true;
            WXLogUtils.e("WXEnvironment getAppVersionName Exception: ", (Throwable) e);
            $jacocoInit[32] = true;
        }
        $jacocoInit[33] = true;
        return str;
    }

    private static String getAppCacheFile() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = "";
        try {
            $jacocoInit[34] = true;
            String path = sApplication.getApplicationContext().getCacheDir().getPath();
            $jacocoInit[35] = true;
            str = path;
        } catch (Exception e) {
            $jacocoInit[36] = true;
            WXLogUtils.e("WXEnvironment getAppCacheFile Exception: ", (Throwable) e);
            $jacocoInit[37] = true;
        }
        $jacocoInit[38] = true;
        return str;
    }

    public static Map<String, String> getCustomOptions() {
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, String> map = options;
        $jacocoInit[39] = true;
        return map;
    }

    public static void addCustomOptions(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        options.put(str, str2);
        $jacocoInit[40] = true;
    }

    @Deprecated
    public static boolean isSupport() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        boolean isInitialized = WXSDKEngine.isInitialized();
        if (isInitialized) {
            $jacocoInit[41] = true;
        } else {
            $jacocoInit[42] = true;
            WXLogUtils.e("WXSDKEngine.isInitialized():" + isInitialized);
            $jacocoInit[43] = true;
        }
        if (!isHardwareSupport()) {
            $jacocoInit[44] = true;
        } else if (!isInitialized) {
            $jacocoInit[45] = true;
        } else {
            $jacocoInit[46] = true;
            z = true;
            $jacocoInit[48] = true;
            return z;
        }
        z = false;
        $jacocoInit[47] = true;
        $jacocoInit[48] = true;
        return z;
    }

    public static boolean isLayoutDirectionRTL() {
        boolean[] $jacocoInit = $jacocoInit();
        if (Build.VERSION.SDK_INT >= 17) {
            $jacocoInit[49] = true;
            boolean z = sApplication.getApplicationContext().getResources().getBoolean(R.bool.weex_is_right_to_left);
            $jacocoInit[50] = true;
            return z;
        }
        $jacocoInit[51] = true;
        return false;
    }

    @Deprecated
    public static boolean isHardwareSupport() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (!isApkDebugable()) {
            $jacocoInit[52] = true;
        } else {
            $jacocoInit[53] = true;
            WXLogUtils.d("isTableDevice:" + WXUtils.isTabletDevice());
            $jacocoInit[54] = true;
        }
        if (!isCPUSupport()) {
            $jacocoInit[55] = true;
        } else if (WXUtils.isTabletDevice()) {
            $jacocoInit[56] = true;
        } else {
            $jacocoInit[57] = true;
            z = true;
            $jacocoInit[59] = true;
            return z;
        }
        z = false;
        $jacocoInit[58] = true;
        $jacocoInit[59] = true;
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isCPUSupport() {
        /*
            boolean[] r0 = $jacocoInit()
            java.lang.String r1 = "true"
            java.util.Map<java.lang.String, java.lang.String> r2 = options
            java.lang.String r3 = "env_exclude_x86"
            java.lang.Object r2 = r2.get(r3)
            boolean r1 = r1.equals(r2)
            r2 = 1
            r3 = 60
            r0[r3] = r2
            boolean r3 = com.taobao.weex.utils.WXSoInstallMgrSdk.isX86()
            r4 = 0
            if (r3 != 0) goto L_0x0023
            r1 = 61
            r0[r1] = r2
            goto L_0x0029
        L_0x0023:
            if (r1 != 0) goto L_0x002f
            r1 = 62
            r0[r1] = r2
        L_0x0029:
            r1 = 64
            r0[r1] = r2
            r1 = 0
            goto L_0x0034
        L_0x002f:
            r1 = 63
            r0[r1] = r2
            r1 = 1
        L_0x0034:
            r3 = 65
            r0[r3] = r2
            boolean r3 = com.taobao.weex.utils.WXSoInstallMgrSdk.isCPUSupport()
            if (r3 != 0) goto L_0x0043
            r3 = 66
            r0[r3] = r2
            goto L_0x0049
        L_0x0043:
            if (r1 == 0) goto L_0x004e
            r3 = 67
            r0[r3] = r2
        L_0x0049:
            r3 = 69
            r0[r3] = r2
            goto L_0x0053
        L_0x004e:
            r3 = 68
            r0[r3] = r2
            r4 = 1
        L_0x0053:
            r3 = 70
            r0[r3] = r2
            boolean r3 = isApkDebugable()
            if (r3 != 0) goto L_0x0062
            r1 = 71
            r0[r1] = r2
            goto L_0x0086
        L_0x0062:
            r3 = 72
            r0[r3] = r2
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "WXEnvironment.sSupport:"
            r3.append(r5)
            r3.append(r4)
            java.lang.String r5 = "isX86AndExclueded: "
            r3.append(r5)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            com.taobao.weex.utils.WXLogUtils.d(r1)
            r1 = 73
            r0[r1] = r2
        L_0x0086:
            r1 = 74
            r0[r1] = r2
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.WXEnvironment.isCPUSupport():boolean");
    }

    public static boolean isApkDebugable() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (sApplication == null) {
            $jacocoInit[75] = true;
            return false;
        } else if (isPerf) {
            $jacocoInit[76] = true;
            return false;
        } else if (!sDebugFlagInit) {
            $jacocoInit[77] = true;
            try {
                String str = getCustomOptions().get(WXConfig.debugMode);
                $jacocoInit[79] = true;
                if (TextUtils.isEmpty(str)) {
                    $jacocoInit[80] = true;
                    if ((sApplication.getApplicationInfo().flags & 2) != 0) {
                        $jacocoInit[81] = true;
                        z = true;
                    } else {
                        $jacocoInit[82] = true;
                        z = false;
                    }
                    isApkDebug = z;
                    $jacocoInit[83] = true;
                } else {
                    isApkDebug = Boolean.valueOf(str).booleanValue();
                    $jacocoInit[84] = true;
                }
                $jacocoInit[85] = true;
            } catch (Exception e) {
                $jacocoInit[86] = true;
                e.printStackTrace();
                isApkDebug = false;
                $jacocoInit[87] = true;
            }
            sDebugFlagInit = true;
            boolean z2 = isApkDebug;
            $jacocoInit[88] = true;
            return z2;
        } else {
            boolean z3 = isApkDebug;
            $jacocoInit[78] = true;
            return z3;
        }
    }

    public static boolean isPerf() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = isPerf;
        $jacocoInit[89] = true;
        return z;
    }

    private static String getDevId() {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        if (sApplication == null) {
            str = "";
            $jacocoInit[90] = true;
        } else {
            Application application = sApplication;
            $jacocoInit[91] = true;
            str = ((TelephonyManager) application.getSystemService("phone")).getDeviceId();
            $jacocoInit[92] = true;
        }
        $jacocoInit[93] = true;
        return str;
    }

    public static Application getApplication() {
        boolean[] $jacocoInit = $jacocoInit();
        Application application = sApplication;
        $jacocoInit[94] = true;
        return application;
    }

    public void initMetrics() {
        boolean[] $jacocoInit = $jacocoInit();
        if (sApplication == null) {
            $jacocoInit[95] = true;
        } else {
            $jacocoInit[96] = true;
        }
    }

    public static String getDiskCacheDir(Context context) {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        String str2 = null;
        if (context == null) {
            $jacocoInit[97] = true;
            return null;
        }
        try {
            $jacocoInit[98] = true;
            if ("mounted".equals(Environment.getExternalStorageState())) {
                $jacocoInit[99] = true;
            } else {
                $jacocoInit[100] = true;
                if (!Environment.isExternalStorageRemovable()) {
                    $jacocoInit[101] = true;
                } else {
                    str = context.getCacheDir().getPath();
                    $jacocoInit[103] = true;
                    $jacocoInit[104] = true;
                    $jacocoInit[107] = true;
                    return str;
                }
            }
            str = context.getExternalCacheDir().getPath();
            try {
                $jacocoInit[102] = true;
                $jacocoInit[104] = true;
            } catch (Exception e) {
                Exception exc = e;
                str2 = str;
                e = exc;
            }
        } catch (Exception e2) {
            e = e2;
            $jacocoInit[105] = true;
            e.printStackTrace();
            $jacocoInit[106] = true;
            str = str2;
            $jacocoInit[107] = true;
            return str;
        }
        $jacocoInit[107] = true;
        return str;
    }

    public static String getFilesDir(Context context) {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        if (context == null) {
            $jacocoInit[108] = true;
            return "";
        }
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            $jacocoInit[109] = true;
            str = filesDir.getPath();
            $jacocoInit[110] = true;
        } else {
            String str2 = getApplication().getApplicationInfo().dataDir;
            $jacocoInit[111] = true;
            $jacocoInit[112] = true;
            str = (str2 + File.separator) + "files";
            $jacocoInit[113] = true;
        }
        $jacocoInit[114] = true;
        return str;
    }

    public static String getCrashFilePath(Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        if (context == null) {
            $jacocoInit[115] = true;
            return "";
        }
        File dir = context.getDir("crash", 0);
        if (dir == null) {
            $jacocoInit[116] = true;
            return "";
        }
        String absolutePath = dir.getAbsolutePath();
        $jacocoInit[117] = true;
        return absolutePath;
    }

    public static String getGlobalFontFamilyName() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = sGlobalFontFamily;
        $jacocoInit[118] = true;
        return str;
    }

    public static void setGlobalFontFamily(String str, Typeface typeface) {
        boolean[] $jacocoInit = $jacocoInit();
        WXLogUtils.d("GlobalFontFamily", "Set global font family: " + str);
        sGlobalFontFamily = str;
        $jacocoInit[119] = true;
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[120] = true;
        } else if (typeface == null) {
            $jacocoInit[121] = true;
            TypefaceUtil.removeFontDO(str);
            $jacocoInit[122] = true;
        } else {
            FontDO fontDO = new FontDO(str, typeface);
            $jacocoInit[123] = true;
            TypefaceUtil.putFontDO(fontDO);
            $jacocoInit[124] = true;
            WXLogUtils.d("TypefaceUtil", "Add new font: " + str);
            $jacocoInit[125] = true;
        }
        $jacocoInit[126] = true;
    }

    public static boolean isOpenDebugLog() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = openDebugLog;
        $jacocoInit[127] = true;
        return z;
    }

    public static void setOpenDebugLog(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        openDebugLog = z;
        $jacocoInit[128] = true;
    }

    public static void setApkDebugable(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        isApkDebug = z;
        if (isApkDebug) {
            $jacocoInit[129] = true;
        } else {
            openDebugLog = false;
            $jacocoInit[130] = true;
        }
        $jacocoInit[131] = true;
    }

    public static String getCacheDir() {
        boolean[] $jacocoInit = $jacocoInit();
        Application application = getApplication();
        $jacocoInit[132] = true;
        if (application == null) {
            $jacocoInit[133] = true;
        } else if (application.getApplicationContext() == null) {
            $jacocoInit[134] = true;
        } else {
            String path = application.getApplicationContext().getCacheDir().getPath();
            $jacocoInit[136] = true;
            return path;
        }
        $jacocoInit[135] = true;
        return null;
    }

    public static boolean extractSo() {
        boolean[] $jacocoInit = $jacocoInit();
        File file = new File(getApplication().getApplicationContext().getApplicationInfo().sourceDir);
        $jacocoInit[137] = true;
        String cacheDir = getCacheDir();
        $jacocoInit[138] = true;
        if (!file.exists()) {
            $jacocoInit[139] = true;
        } else if (TextUtils.isEmpty(cacheDir)) {
            $jacocoInit[140] = true;
        } else {
            try {
                $jacocoInit[141] = true;
                WXFileUtils.extractSo(file.getAbsolutePath(), cacheDir);
                $jacocoInit[144] = true;
                return true;
            } catch (IOException e) {
                $jacocoInit[142] = true;
                WXLogUtils.e("extractSo error " + e.getMessage());
                $jacocoInit[143] = true;
                return false;
            }
        }
        $jacocoInit[145] = true;
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00b0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String findIcuPath() {
        /*
            boolean[] r0 = $jacocoInit()
            java.io.File r1 = new java.io.File
            java.lang.String r2 = "/proc/self/maps"
            r1.<init>(r2)
            r2 = 146(0x92, float:2.05E-43)
            r3 = 0
            r4 = 1
            r0[r2] = r4     // Catch:{ IOException -> 0x0083, all -> 0x0080 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0083, all -> 0x0080 }
            java.io.FileReader r5 = new java.io.FileReader     // Catch:{ IOException -> 0x0083, all -> 0x0080 }
            r5.<init>(r1)     // Catch:{ IOException -> 0x0083, all -> 0x0080 }
            r2.<init>(r5)     // Catch:{ IOException -> 0x0083, all -> 0x0080 }
            r1 = 147(0x93, float:2.06E-43)
            r0[r1] = r4     // Catch:{ IOException -> 0x007e }
        L_0x001f:
            java.lang.String r1 = r2.readLine()     // Catch:{ IOException -> 0x007e }
            if (r1 != 0) goto L_0x003e
            r1 = 148(0x94, float:2.07E-43)
            r0[r1] = r4     // Catch:{ IOException -> 0x007e }
            r2.close()     // Catch:{ IOException -> 0x007e }
            r1 = 160(0xa0, float:2.24E-43)
            r0[r1] = r4     // Catch:{ IOException -> 0x0039 }
            r2.close()     // Catch:{ IOException -> 0x0039 }
            r1 = 161(0xa1, float:2.26E-43)
            r0[r1] = r4
            goto L_0x00a3
        L_0x0039:
            r1 = 162(0xa2, float:2.27E-43)
            r0[r1] = r4
            goto L_0x00a3
        L_0x003e:
            r5 = 149(0x95, float:2.09E-43)
            r0[r5] = r4     // Catch:{ IOException -> 0x007e }
            java.lang.String r5 = "icudt"
            boolean r5 = r1.contains(r5)     // Catch:{ IOException -> 0x007e }
            if (r5 != 0) goto L_0x004f
            r1 = 150(0x96, float:2.1E-43)
            r0[r1] = r4     // Catch:{ IOException -> 0x007e }
            goto L_0x001f
        L_0x004f:
            r5 = 151(0x97, float:2.12E-43)
            r0[r5] = r4     // Catch:{ IOException -> 0x007e }
            r5 = 47
            int r5 = r1.indexOf(r5)     // Catch:{ IOException -> 0x007e }
            r6 = 152(0x98, float:2.13E-43)
            r0[r6] = r4     // Catch:{ IOException -> 0x007e }
            java.lang.String r1 = r1.substring(r5)     // Catch:{ IOException -> 0x007e }
            r5 = 153(0x99, float:2.14E-43)
            r0[r5] = r4     // Catch:{ IOException -> 0x007e }
            java.lang.String r1 = r1.trim()     // Catch:{ IOException -> 0x007e }
            r3 = 155(0x9b, float:2.17E-43)
            r0[r3] = r4     // Catch:{ IOException -> 0x0075 }
            r2.close()     // Catch:{ IOException -> 0x0075 }
            r2 = 156(0x9c, float:2.19E-43)
            r0[r2] = r4
            goto L_0x0079
        L_0x0075:
            r2 = 157(0x9d, float:2.2E-43)
            r0[r2] = r4
        L_0x0079:
            r2 = 158(0x9e, float:2.21E-43)
            r0[r2] = r4
            return r1
        L_0x007e:
            r1 = move-exception
            goto L_0x0085
        L_0x0080:
            r1 = move-exception
            r2 = r3
            goto L_0x00a9
        L_0x0083:
            r1 = move-exception
            r2 = r3
        L_0x0085:
            r5 = 163(0xa3, float:2.28E-43)
            r0[r5] = r4     // Catch:{ all -> 0x00a8 }
            r1.printStackTrace()     // Catch:{ all -> 0x00a8 }
            if (r2 != 0) goto L_0x0093
            r1 = 164(0xa4, float:2.3E-43)
            r0[r1] = r4
            goto L_0x00a3
        L_0x0093:
            r1 = 165(0xa5, float:2.31E-43)
            r0[r1] = r4     // Catch:{ IOException -> 0x009f }
            r2.close()     // Catch:{ IOException -> 0x009f }
            r1 = 166(0xa6, float:2.33E-43)
            r0[r1] = r4
            goto L_0x00a3
        L_0x009f:
            r1 = 167(0xa7, float:2.34E-43)
            r0[r1] = r4
        L_0x00a3:
            r1 = 173(0xad, float:2.42E-43)
            r0[r1] = r4
            return r3
        L_0x00a8:
            r1 = move-exception
        L_0x00a9:
            if (r2 != 0) goto L_0x00b0
            r2 = 168(0xa8, float:2.35E-43)
            r0[r2] = r4
            goto L_0x00c0
        L_0x00b0:
            r3 = 169(0xa9, float:2.37E-43)
            r0[r3] = r4     // Catch:{ IOException -> 0x00bc }
            r2.close()     // Catch:{ IOException -> 0x00bc }
            r2 = 170(0xaa, float:2.38E-43)
            r0[r2] = r4
            goto L_0x00c0
        L_0x00bc:
            r2 = 171(0xab, float:2.4E-43)
            r0[r2] = r4
        L_0x00c0:
            r2 = 172(0xac, float:2.41E-43)
            r0[r2] = r4
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.WXEnvironment.findIcuPath():java.lang.String");
    }

    public static String findSoPath(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        String findLibrary = ((PathClassLoader) WXEnvironment.class.getClassLoader()).findLibrary(str);
        $jacocoInit[174] = true;
        if (TextUtils.isEmpty(findLibrary)) {
            $jacocoInit[175] = true;
        } else {
            $jacocoInit[176] = true;
            File file = new File(findLibrary);
            $jacocoInit[177] = true;
            if (file.exists()) {
                $jacocoInit[178] = true;
                WXLogUtils.e(str + "'s Path is" + findLibrary);
                $jacocoInit[179] = true;
                String absolutePath = file.getAbsolutePath();
                $jacocoInit[180] = true;
                return absolutePath;
            }
            WXLogUtils.e(str + "'s Path is " + findLibrary + " but file does not exist");
            $jacocoInit[181] = true;
        }
        String str2 = ShareConstants.o + str + ".so";
        $jacocoInit[182] = true;
        String cacheDir = getCacheDir();
        $jacocoInit[183] = true;
        if (TextUtils.isEmpty(cacheDir)) {
            $jacocoInit[184] = true;
            WXLogUtils.e("cache dir is null");
            $jacocoInit[185] = true;
            return "";
        }
        if (cacheDir.indexOf("/cache") <= 0) {
            $jacocoInit[186] = true;
        } else {
            $jacocoInit[187] = true;
            findLibrary = new File(cacheDir.replace("/cache", "/lib"), str2).getAbsolutePath();
            $jacocoInit[188] = true;
        }
        File file2 = new File(findLibrary);
        $jacocoInit[189] = true;
        if (file2.exists()) {
            $jacocoInit[190] = true;
            WXLogUtils.e(str + "use lib so");
            $jacocoInit[191] = true;
            return findLibrary;
        } else if (extractSo()) {
            $jacocoInit[192] = true;
            String absolutePath2 = new File(getCacheDir(), str2).getAbsolutePath();
            $jacocoInit[193] = true;
            return absolutePath2;
        } else {
            $jacocoInit[194] = true;
            return findLibrary;
        }
    }

    public static String getLibJScRealPath() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!TextUtils.isEmpty(CORE_JSC_SO_PATH)) {
            $jacocoInit[195] = true;
        } else {
            $jacocoInit[196] = true;
            CORE_JSC_SO_PATH = findSoPath(CORE_JSC_SO_NAME);
            $jacocoInit[197] = true;
            WXLogUtils.e("findLibJscRealPath " + CORE_JSC_SO_PATH);
            $jacocoInit[198] = true;
        }
        String str = CORE_JSC_SO_PATH;
        $jacocoInit[199] = true;
        return str;
    }

    public static String getLibJssRealPath() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!TextUtils.isEmpty(CORE_JSS_SO_PATH)) {
            $jacocoInit[200] = true;
        } else {
            $jacocoInit[201] = true;
            CORE_JSS_SO_PATH = findSoPath(CORE_JSS_SO_NAME);
            $jacocoInit[202] = true;
            WXLogUtils.e("findLibJssRealPath " + CORE_JSS_SO_PATH);
            $jacocoInit[203] = true;
        }
        String str = CORE_JSS_SO_PATH;
        $jacocoInit[204] = true;
        return str;
    }

    public static String getLibJssIcuPath() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!TextUtils.isEmpty(CORE_JSS_ICU_PATH)) {
            $jacocoInit[205] = true;
        } else {
            $jacocoInit[206] = true;
            CORE_JSS_ICU_PATH = findIcuPath();
            $jacocoInit[207] = true;
        }
        String str = CORE_JSS_ICU_PATH;
        $jacocoInit[208] = true;
        return str;
    }

    public static String getLibLdPath() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!TextUtils.isEmpty(LIB_LD_PATH)) {
            $jacocoInit[209] = true;
        } else {
            $jacocoInit[210] = true;
            ClassLoader classLoader = WXEnvironment.class.getClassLoader();
            try {
                $jacocoInit[211] = true;
                Method method = classLoader.getClass().getMethod("getLdLibraryPath", new Class[0]);
                $jacocoInit[212] = true;
                LIB_LD_PATH = (String) method.invoke(classLoader, new Object[0]);
                $jacocoInit[213] = true;
            } catch (IllegalAccessException e) {
                $jacocoInit[214] = true;
                e.printStackTrace();
                $jacocoInit[215] = true;
            } catch (InvocationTargetException e2) {
                $jacocoInit[216] = true;
                e2.printStackTrace();
                $jacocoInit[217] = true;
            } catch (NoSuchMethodException e3) {
                $jacocoInit[218] = true;
                e3.printStackTrace();
                $jacocoInit[219] = true;
            }
        }
        if (!TextUtils.isEmpty(LIB_LD_PATH)) {
            $jacocoInit[220] = true;
        } else {
            try {
                $jacocoInit[221] = true;
                String property = System.getProperty("java.library.path");
                $jacocoInit[222] = true;
                String libJScRealPath = getLibJScRealPath();
                $jacocoInit[223] = true;
                if (TextUtils.isEmpty(libJScRealPath)) {
                    $jacocoInit[224] = true;
                } else {
                    $jacocoInit[225] = true;
                    LIB_LD_PATH = new File(libJScRealPath).getParent() + ":" + property;
                    $jacocoInit[226] = true;
                }
                $jacocoInit[227] = true;
            } catch (Exception e4) {
                $jacocoInit[228] = true;
                e4.printStackTrace();
                $jacocoInit[229] = true;
            }
        }
        WXLogUtils.e("getLibLdPath is " + LIB_LD_PATH);
        String str = LIB_LD_PATH;
        $jacocoInit[230] = true;
        return str;
    }
}
