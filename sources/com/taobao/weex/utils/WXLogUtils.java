package com.taobao.weex.utils;

import android.text.TextUtils;
import android.util.Log;
import com.taobao.weex.WXEnvironment;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXLogUtils {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String CLAZZ_NAME_LOG_UTIL = "com.taobao.weex.devtools.common.LogUtil";
    public static final String WEEX_PERF_TAG = "weex_perf";
    public static final String WEEX_TAG = "weex";
    private static StringBuilder builder = new StringBuilder(50);
    private static HashMap<String, Class> clazzMaps = new HashMap<>(2);
    private static List<JsLogWatcher> jsLogWatcherList = new ArrayList();
    private static LogWatcher sLogWatcher;

    public interface JsLogWatcher {
        void onJsLog(int i, String str);
    }

    public interface LogWatcher {
        void onLog(String str, String str2, String str3);
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3876268063097060479L, "com/taobao/weex/utils/WXLogUtils", 162);
        $jacocoData = a2;
        return a2;
    }

    public WXLogUtils() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[158] = true;
        $jacocoInit[159] = true;
        clazzMaps.put(CLAZZ_NAME_LOG_UTIL, loadClass(CLAZZ_NAME_LOG_UTIL));
        $jacocoInit[160] = true;
        $jacocoInit[161] = true;
    }

    private static Class loadClass(String str) {
        Class<?> cls;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[1] = true;
            cls = Class.forName(str);
            if (cls == null) {
                try {
                    $jacocoInit[2] = true;
                } catch (ClassNotFoundException unused) {
                    $jacocoInit[6] = true;
                    $jacocoInit[7] = true;
                    return cls;
                }
            } else {
                $jacocoInit[3] = true;
                clazzMaps.put(str, cls);
                $jacocoInit[4] = true;
            }
            $jacocoInit[5] = true;
        } catch (ClassNotFoundException unused2) {
            cls = null;
            $jacocoInit[6] = true;
            $jacocoInit[7] = true;
            return cls;
        }
        $jacocoInit[7] = true;
        return cls;
    }

    public static void renderPerformanceLog(String str, long j) {
        boolean[] $jacocoInit = $jacocoInit();
        if (WXEnvironment.isApkDebugable()) {
            $jacocoInit[8] = true;
        } else if (!WXEnvironment.isPerf()) {
            $jacocoInit[9] = true;
        } else {
            $jacocoInit[10] = true;
        }
        $jacocoInit[11] = true;
    }

    private static void log(String str, String str2, LogLevel logLevel) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str2 == null) {
            $jacocoInit[12] = true;
        } else if (str == null) {
            $jacocoInit[13] = true;
        } else if (logLevel == null) {
            $jacocoInit[14] = true;
        } else {
            if (sLogWatcher == null) {
                $jacocoInit[16] = true;
            } else {
                $jacocoInit[17] = true;
                sLogWatcher.onLog(logLevel.getName(), str, str2);
                $jacocoInit[18] = true;
            }
            if (WXEnvironment.isApkDebugable()) {
                $jacocoInit[19] = true;
                Log.println(logLevel.getPriority(), str, str2);
                $jacocoInit[20] = true;
                if (logLevel.getName().equals("debug")) {
                    $jacocoInit[21] = true;
                } else {
                    $jacocoInit[22] = true;
                    writeConsoleLog(logLevel.getName(), str2);
                    $jacocoInit[23] = true;
                }
            } else if (logLevel.getPriority() - LogLevel.WARN.getPriority() < 0) {
                $jacocoInit[24] = true;
            } else {
                $jacocoInit[25] = true;
                Log.println(logLevel.getPriority(), str, str2);
                $jacocoInit[26] = true;
            }
            $jacocoInit[27] = true;
            return;
        }
        $jacocoInit[15] = true;
    }

    public static void d(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        d("weex", str);
        $jacocoInit[28] = true;
    }

    public static void i(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        i("weex", str);
        $jacocoInit[29] = true;
    }

    public static void info(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        i("weex", str);
        $jacocoInit[30] = true;
    }

    public static void v(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        v("weex", str);
        $jacocoInit[31] = true;
    }

    public static void w(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        w("weex", str);
        $jacocoInit[32] = true;
    }

    public static void e(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        e("weex", str);
        $jacocoInit[33] = true;
    }

    public static void d(String str, byte[] bArr) {
        boolean[] $jacocoInit = $jacocoInit();
        d(str, new String(bArr));
        $jacocoInit[34] = true;
    }

    public static void wtf(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        wtf("weex", str);
        $jacocoInit[35] = true;
    }

    public static void d(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[36] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[37] = true;
        } else {
            $jacocoInit[38] = true;
            log(str, str2, LogLevel.DEBUG);
            $jacocoInit[39] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[40] = true;
            } else {
                $jacocoInit[41] = true;
                if (!"jsLog".equals(str)) {
                    $jacocoInit[42] = true;
                } else if (jsLogWatcherList == null) {
                    $jacocoInit[43] = true;
                } else if (jsLogWatcherList.size() <= 0) {
                    $jacocoInit[44] = true;
                } else {
                    $jacocoInit[45] = true;
                    $jacocoInit[46] = true;
                    for (JsLogWatcher next : jsLogWatcherList) {
                        $jacocoInit[48] = true;
                        if (str2.endsWith("__DEBUG")) {
                            $jacocoInit[49] = true;
                            next.onJsLog(3, str2.replace("__DEBUG", ""));
                            $jacocoInit[50] = true;
                        } else if (str2.endsWith("__INFO")) {
                            $jacocoInit[51] = true;
                            next.onJsLog(3, str2.replace("__INFO", ""));
                            $jacocoInit[52] = true;
                        } else if (str2.endsWith("__WARN")) {
                            $jacocoInit[53] = true;
                            next.onJsLog(3, str2.replace("__WARN", ""));
                            $jacocoInit[54] = true;
                        } else if (str2.endsWith("__ERROR")) {
                            $jacocoInit[55] = true;
                            next.onJsLog(3, str2.replace("__ERROR", ""));
                            $jacocoInit[56] = true;
                        } else {
                            next.onJsLog(3, str2);
                            $jacocoInit[57] = true;
                        }
                        $jacocoInit[58] = true;
                    }
                    $jacocoInit[47] = true;
                }
                writeConsoleLog("debug", str + ":" + str2);
                $jacocoInit[59] = true;
                if (!str2.contains(" | __")) {
                    $jacocoInit[60] = true;
                } else {
                    $jacocoInit[61] = true;
                    String[] split = str2.split(" | __");
                    $jacocoInit[62] = true;
                    if (split == null) {
                        $jacocoInit[63] = true;
                    } else if (split.length != 4) {
                        $jacocoInit[64] = true;
                    } else if (TextUtils.isEmpty(split[0])) {
                        $jacocoInit[65] = true;
                    } else if (TextUtils.isEmpty(split[2])) {
                        $jacocoInit[66] = true;
                    } else {
                        $jacocoInit[67] = true;
                        getLogLevel(split[2]);
                        $jacocoInit[68] = true;
                        return;
                    }
                }
            }
        }
        $jacocoInit[69] = true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.taobao.weex.utils.LogLevel getLogLevel(java.lang.String r3) {
        /*
            boolean[] r0 = $jacocoInit()
            java.lang.String r3 = r3.trim()
            int r1 = r3.hashCode()
            r2 = 1
            switch(r1) {
                case -1485211506: goto L_0x0061;
                case -1484806554: goto L_0x004e;
                case 90640196: goto L_0x003b;
                case 1198194259: goto L_0x0028;
                case 1199520264: goto L_0x0015;
                default: goto L_0x0010;
            }
        L_0x0010:
            r3 = 70
            r0[r3] = r2
            goto L_0x0074
        L_0x0015:
            java.lang.String r1 = "__ERROR"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x0022
            r3 = 71
            r0[r3] = r2
            goto L_0x0074
        L_0x0022:
            r3 = 0
            r1 = 72
            r0[r1] = r2
            goto L_0x0075
        L_0x0028:
            java.lang.String r1 = "__DEBUG"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x0035
            r3 = 79
            r0[r3] = r2
            goto L_0x0074
        L_0x0035:
            r3 = 4
            r1 = 80
            r0[r1] = r2
            goto L_0x0075
        L_0x003b:
            java.lang.String r1 = "__LOG"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x0048
            r3 = 77
            r0[r3] = r2
            goto L_0x0074
        L_0x0048:
            r3 = 3
            r1 = 78
            r0[r1] = r2
            goto L_0x0075
        L_0x004e:
            java.lang.String r1 = "__WARN"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x005b
            r3 = 73
            r0[r3] = r2
            goto L_0x0074
        L_0x005b:
            r3 = 74
            r0[r3] = r2
            r3 = 1
            goto L_0x0075
        L_0x0061:
            java.lang.String r1 = "__INFO"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x006e
            r3 = 75
            r0[r3] = r2
            goto L_0x0074
        L_0x006e:
            r3 = 2
            r1 = 76
            r0[r1] = r2
            goto L_0x0075
        L_0x0074:
            r3 = -1
        L_0x0075:
            switch(r3) {
                case 0: goto L_0x009b;
                case 1: goto L_0x0094;
                case 2: goto L_0x008d;
                case 3: goto L_0x0086;
                case 4: goto L_0x007f;
                default: goto L_0x0078;
            }
        L_0x0078:
            com.taobao.weex.utils.LogLevel r3 = com.taobao.weex.utils.LogLevel.DEBUG
            r1 = 86
            r0[r1] = r2
            return r3
        L_0x007f:
            com.taobao.weex.utils.LogLevel r3 = com.taobao.weex.utils.LogLevel.DEBUG
            r1 = 85
            r0[r1] = r2
            return r3
        L_0x0086:
            com.taobao.weex.utils.LogLevel r3 = com.taobao.weex.utils.LogLevel.INFO
            r1 = 84
            r0[r1] = r2
            return r3
        L_0x008d:
            com.taobao.weex.utils.LogLevel r3 = com.taobao.weex.utils.LogLevel.INFO
            r1 = 83
            r0[r1] = r2
            return r3
        L_0x0094:
            com.taobao.weex.utils.LogLevel r3 = com.taobao.weex.utils.LogLevel.WARN
            r1 = 82
            r0[r1] = r2
            return r3
        L_0x009b:
            com.taobao.weex.utils.LogLevel r3 = com.taobao.weex.utils.LogLevel.ERROR
            r1 = 81
            r0[r1] = r2
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.utils.WXLogUtils.getLogLevel(java.lang.String):com.taobao.weex.utils.LogLevel");
    }

    public static void i(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        log(str, str2, LogLevel.INFO);
        $jacocoInit[87] = true;
    }

    public static void v(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        log(str, str2, LogLevel.VERBOSE);
        $jacocoInit[88] = true;
    }

    public static void w(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        log(str, str2, LogLevel.WARN);
        $jacocoInit[89] = true;
    }

    public static void e(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        log(str, str2, LogLevel.ERROR);
        $jacocoInit[90] = true;
    }

    public static void wtf(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        log(str, str2, LogLevel.WTF);
        $jacocoInit[91] = true;
    }

    public static void p(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        d(WEEX_PERF_TAG, str);
        $jacocoInit[92] = true;
    }

    public static void d(String str, Throwable th) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[93] = true;
        } else {
            $jacocoInit[94] = true;
            d(str + getStackTrace(th));
            $jacocoInit[95] = true;
        }
        $jacocoInit[96] = true;
    }

    public static void i(String str, Throwable th) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[97] = true;
        } else {
            $jacocoInit[98] = true;
            info(str + getStackTrace(th));
            $jacocoInit[99] = true;
        }
        $jacocoInit[100] = true;
    }

    public static void v(String str, Throwable th) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[101] = true;
        } else {
            $jacocoInit[102] = true;
            v(str + getStackTrace(th));
            $jacocoInit[103] = true;
        }
        $jacocoInit[104] = true;
    }

    public static void w(String str, Throwable th) {
        boolean[] $jacocoInit = $jacocoInit();
        w(str + getStackTrace(th));
        $jacocoInit[105] = true;
    }

    public static void e(String str, Throwable th) {
        boolean[] $jacocoInit = $jacocoInit();
        e(str + getStackTrace(th));
        $jacocoInit[106] = true;
    }

    public static void wtf(String str, Throwable th) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[107] = true;
        } else {
            $jacocoInit[108] = true;
            wtf(str + getStackTrace(th));
            $jacocoInit[109] = true;
        }
        $jacocoInit[110] = true;
    }

    public static void p(String str, Throwable th) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[111] = true;
        } else {
            $jacocoInit[112] = true;
            p(str + getStackTrace(th));
            $jacocoInit[113] = true;
        }
        $jacocoInit[114] = true;
    }

    public static void eTag(String str, Throwable th) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[115] = true;
        } else {
            $jacocoInit[116] = true;
            e(str, getStackTrace(th));
            $jacocoInit[117] = true;
        }
        $jacocoInit[118] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x008f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getStackTrace(@android.support.annotation.Nullable java.lang.Throwable r5) {
        /*
            boolean[] r0 = $jacocoInit()
            r1 = 1
            if (r5 != 0) goto L_0x000e
            java.lang.String r5 = ""
            r2 = 119(0x77, float:1.67E-43)
            r0[r2] = r1
            return r5
        L_0x000e:
            r2 = 120(0x78, float:1.68E-43)
            r3 = 0
            r0[r2] = r1     // Catch:{ all -> 0x0067 }
            java.io.StringWriter r2 = new java.io.StringWriter     // Catch:{ all -> 0x0067 }
            r2.<init>()     // Catch:{ all -> 0x0067 }
            r4 = 121(0x79, float:1.7E-43)
            r0[r4] = r1     // Catch:{ all -> 0x0065 }
            java.io.PrintWriter r4 = new java.io.PrintWriter     // Catch:{ all -> 0x0065 }
            r4.<init>(r2)     // Catch:{ all -> 0x0065 }
            r3 = 122(0x7a, float:1.71E-43)
            r0[r3] = r1     // Catch:{ all -> 0x0062 }
            r5.printStackTrace(r4)     // Catch:{ all -> 0x0062 }
            r5 = 123(0x7b, float:1.72E-43)
            r0[r5] = r1     // Catch:{ all -> 0x0062 }
            r4.flush()     // Catch:{ all -> 0x0062 }
            r5 = 124(0x7c, float:1.74E-43)
            r0[r5] = r1     // Catch:{ all -> 0x0062 }
            r2.flush()     // Catch:{ all -> 0x0062 }
            r5 = 126(0x7e, float:1.77E-43)
            r0[r5] = r1     // Catch:{ IOException -> 0x0042 }
            r2.close()     // Catch:{ IOException -> 0x0042 }
            r5 = 127(0x7f, float:1.78E-43)
            r0[r5] = r1
            goto L_0x004e
        L_0x0042:
            r5 = move-exception
            r3 = 128(0x80, float:1.794E-43)
            r0[r3] = r1
            r5.printStackTrace()
            r5 = 129(0x81, float:1.81E-43)
            r0[r5] = r1
        L_0x004e:
            r5 = 131(0x83, float:1.84E-43)
            r0[r5] = r1
            r4.close()
            r5 = 132(0x84, float:1.85E-43)
            r0[r5] = r1
            java.lang.String r5 = r2.toString()
            r2 = 142(0x8e, float:1.99E-43)
            r0[r2] = r1
            return r5
        L_0x0062:
            r5 = move-exception
            r3 = r4
            goto L_0x0069
        L_0x0065:
            r5 = move-exception
            goto L_0x0069
        L_0x0067:
            r5 = move-exception
            r2 = r3
        L_0x0069:
            if (r2 != 0) goto L_0x0070
            r2 = 133(0x85, float:1.86E-43)
            r0[r2] = r1
            goto L_0x0088
        L_0x0070:
            r4 = 134(0x86, float:1.88E-43)
            r0[r4] = r1     // Catch:{ IOException -> 0x007c }
            r2.close()     // Catch:{ IOException -> 0x007c }
            r2 = 135(0x87, float:1.89E-43)
            r0[r2] = r1
            goto L_0x0088
        L_0x007c:
            r2 = move-exception
            r4 = 136(0x88, float:1.9E-43)
            r0[r4] = r1
            r2.printStackTrace()
            r2 = 137(0x89, float:1.92E-43)
            r0[r2] = r1
        L_0x0088:
            if (r3 != 0) goto L_0x008f
            r2 = 138(0x8a, float:1.93E-43)
            r0[r2] = r1
            goto L_0x009a
        L_0x008f:
            r2 = 139(0x8b, float:1.95E-43)
            r0[r2] = r1
            r3.close()
            r2 = 140(0x8c, float:1.96E-43)
            r0[r2] = r1
        L_0x009a:
            r2 = 141(0x8d, float:1.98E-43)
            r0[r2] = r1
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.utils.WXLogUtils.getStackTrace(java.lang.Throwable):java.lang.String");
    }

    private static void writeConsoleLog(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[143] = true;
        } else {
            try {
                $jacocoInit[144] = true;
                Class cls = clazzMaps.get(CLAZZ_NAME_LOG_UTIL);
                if (cls == null) {
                    $jacocoInit[145] = true;
                } else {
                    $jacocoInit[146] = true;
                    Method method = cls.getMethod("log", new Class[]{String.class, String.class});
                    $jacocoInit[147] = true;
                    method.invoke(cls, new Object[]{str, str2});
                    $jacocoInit[148] = true;
                }
                $jacocoInit[149] = true;
            } catch (Exception unused) {
                $jacocoInit[150] = true;
                Log.d("weex", "LogUtil not found!");
                $jacocoInit[151] = true;
            }
        }
        $jacocoInit[152] = true;
    }

    public static void setJsLogWatcher(JsLogWatcher jsLogWatcher) {
        boolean[] $jacocoInit = $jacocoInit();
        if (jsLogWatcherList.contains(jsLogWatcher)) {
            $jacocoInit[153] = true;
        } else {
            $jacocoInit[154] = true;
            jsLogWatcherList.add(jsLogWatcher);
            $jacocoInit[155] = true;
        }
        $jacocoInit[156] = true;
    }

    public static void setLogWatcher(LogWatcher logWatcher) {
        boolean[] $jacocoInit = $jacocoInit();
        sLogWatcher = logWatcher;
        $jacocoInit[157] = true;
    }
}
