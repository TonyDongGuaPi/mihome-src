package com.taobao.weex.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.taobao.weex.IWXStatisticsListener;
import com.taobao.weex.adapter.IWXSoLoaderAdapter;
import com.taobao.weex.adapter.IWXUserTrackAdapter;
import com.taobao.weex.common.WXErrorCode;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXSoInstallMgrSdk {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String ARMEABI = "armeabi";
    private static final int ARMEABI_Size = 3583820;
    static final String LOGTAG = "INIT_SO";
    private static final String MIPS = "mips";
    private static final String STARTUPSO = "/libweexjsb.so";
    private static final String STARTUPSOANDROID15 = "/libweexjst.so";
    private static final String X86 = "x86";
    private static final int X86_Size = 4340864;
    static Context mContext = null;
    private static IWXSoLoaderAdapter mSoLoader = null;
    private static IWXStatisticsListener mStatisticsListener = null;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(88946924628839503L, "com/taobao/weex/utils/WXSoInstallMgrSdk", 238);
        $jacocoData = a2;
        return a2;
    }

    public WXSoInstallMgrSdk() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[237] = true;
    }

    public static void init(Context context, IWXSoLoaderAdapter iWXSoLoaderAdapter, IWXStatisticsListener iWXStatisticsListener) {
        boolean[] $jacocoInit = $jacocoInit();
        mContext = context;
        mSoLoader = iWXSoLoaderAdapter;
        mStatisticsListener = iWXStatisticsListener;
        $jacocoInit[1] = true;
    }

    public static boolean isX86() {
        boolean[] $jacocoInit = $jacocoInit();
        String _cpuType = _cpuType();
        $jacocoInit[2] = true;
        boolean equalsIgnoreCase = _cpuType.equalsIgnoreCase("x86");
        $jacocoInit[3] = true;
        return equalsIgnoreCase;
    }

    public static boolean isCPUSupport() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        String _cpuType = _cpuType();
        $jacocoInit[4] = true;
        if (!_cpuType.equalsIgnoreCase("mips")) {
            $jacocoInit[5] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[6] = true;
        }
        $jacocoInit[7] = true;
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00e8, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e9, code lost:
        r0[34] = true;
        r10.printStackTrace();
        r0[35] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0104, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0105, code lost:
        r0[37] = true;
        r10.printStackTrace();
        r0[38] = true;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0104 A[ExcHandler: Error | Exception (r10v2 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:21:0x00a4] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean initSo(java.lang.String r10, int r11, com.taobao.weex.adapter.IWXUserTrackAdapter r12) {
        /*
            boolean[] r0 = $jacocoInit()
            java.lang.String r1 = _cpuType()
            r2 = 1
            r3 = 8
            r0[r3] = r2
            java.lang.String r3 = "mips"
            boolean r3 = r1.equalsIgnoreCase(r3)
            r4 = 0
            r5 = 0
            if (r3 == 0) goto L_0x0029
            r10 = 9
            r0[r10] = r2
            com.taobao.weex.common.WXErrorCode r10 = com.taobao.weex.common.WXErrorCode.WX_KEY_EXCEPTION_SDK_INIT
            java.lang.String r11 = "initSo"
            java.lang.String r12 = "[WX_KEY_EXCEPTION_SDK_INIT_CPU_NOT_SUPPORT] for android cpuType is MIPS"
            com.taobao.weex.utils.WXExceptionUtils.commitCriticalExceptionRT(r5, r10, r11, r12, r5)
            r10 = 10
            r0[r10] = r2
            return r4
        L_0x0029:
            copyStartUpSo()
            r3 = 11
            r0[r3] = r2     // Catch:{ Error | Exception -> 0x004f }
            com.taobao.weex.adapter.IWXSoLoaderAdapter r3 = mSoLoader     // Catch:{ Error | Exception -> 0x004f }
            if (r3 == 0) goto L_0x0042
            r3 = 12
            r0[r3] = r2     // Catch:{ Error | Exception -> 0x004f }
            com.taobao.weex.adapter.IWXSoLoaderAdapter r3 = mSoLoader     // Catch:{ Error | Exception -> 0x004f }
            r3.doLoadLibrary(r10)     // Catch:{ Error | Exception -> 0x004f }
            r3 = 13
            r0[r3] = r2     // Catch:{ Error | Exception -> 0x004f }
            goto L_0x0049
        L_0x0042:
            java.lang.System.loadLibrary(r10)     // Catch:{ Error | Exception -> 0x004f }
            r3 = 14
            r0[r3] = r2     // Catch:{ Error | Exception -> 0x004f }
        L_0x0049:
            r3 = 15
            r0[r3] = r2
            r3 = 1
            goto L_0x00a7
        L_0x004f:
            r3 = move-exception
            r6 = 16
            r0[r6] = r2
            java.lang.String r6 = "armeabi"
            boolean r6 = r1.contains(r6)
            if (r6 == 0) goto L_0x0061
            r6 = 17
            r0[r6] = r2
            goto L_0x0072
        L_0x0061:
            java.lang.String r6 = "x86"
            boolean r6 = r1.contains(r6)
            if (r6 != 0) goto L_0x006e
            r3 = 18
            r0[r3] = r2
            goto L_0x00a2
        L_0x006e:
            r6 = 19
            r0[r6] = r2
        L_0x0072:
            com.taobao.weex.common.WXErrorCode r6 = com.taobao.weex.common.WXErrorCode.WX_KEY_EXCEPTION_SDK_INIT
            java.lang.String r7 = "initSo"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "[WX_KEY_EXCEPTION_SDK_INIT_CPU_NOT_SUPPORT] for android cpuType is "
            r8.append(r9)
            r8.append(r1)
            java.lang.String r9 = "\n Detail Error is: "
            r8.append(r9)
            r9 = 20
            r0[r9] = r2
            java.lang.String r3 = r3.getMessage()
            r8.append(r3)
            java.lang.String r3 = r8.toString()
            r8 = 21
            r0[r8] = r2
            com.taobao.weex.utils.WXExceptionUtils.commitCriticalExceptionRT(r5, r6, r7, r3, r5)
            r3 = 22
            r0[r3] = r2
        L_0x00a2:
            r3 = 23
            r0[r3] = r2     // Catch:{ Error | Exception -> 0x0104 }
            r3 = 0
        L_0x00a7:
            if (r3 == 0) goto L_0x00ae
            r10 = 24
            r0[r10] = r2     // Catch:{ Error | Exception -> 0x0104 }
            goto L_0x00f4
        L_0x00ae:
            r5 = 25
            r0[r5] = r2     // Catch:{ Error | Exception -> 0x0104 }
            boolean r5 = isExist(r10, r11)     // Catch:{ Error | Exception -> 0x0104 }
            if (r5 != 0) goto L_0x00bd
            r5 = 26
            r0[r5] = r2     // Catch:{ Error | Exception -> 0x0104 }
            goto L_0x00d2
        L_0x00bd:
            r5 = 27
            r0[r5] = r2     // Catch:{ Error | Exception -> 0x0104 }
            boolean r5 = _loadUnzipSo(r10, r11, r12)     // Catch:{ Error | Exception -> 0x0104 }
            if (r5 != 0) goto L_0x00ff
            r5 = 28
            r0[r5] = r2     // Catch:{ Error | Exception -> 0x0104 }
            removeSoIfExit(r10, r11)     // Catch:{ Error | Exception -> 0x0104 }
            r5 = 30
            r0[r5] = r2     // Catch:{ Error | Exception -> 0x0104 }
        L_0x00d2:
            java.lang.String r5 = "mips"
            boolean r1 = r1.equalsIgnoreCase(r5)     // Catch:{ Error | Exception -> 0x0104 }
            if (r1 != 0) goto L_0x00fa
            r1 = 31
            r0[r1] = r2     // Catch:{ Error | Exception -> 0x0104 }
            boolean r10 = unZipSelectedFiles(r10, r11, r12)     // Catch:{ IOException -> 0x00e8, Error | Exception -> 0x0104 }
            r11 = 33
            r0[r11] = r2     // Catch:{ Error | Exception -> 0x0104 }
            r4 = r10
            goto L_0x00f5
        L_0x00e8:
            r10 = move-exception
            r11 = 34
            r0[r11] = r2     // Catch:{ Error | Exception -> 0x0104 }
            r10.printStackTrace()     // Catch:{ Error | Exception -> 0x0104 }
            r10 = 35
            r0[r10] = r2     // Catch:{ Error | Exception -> 0x0104 }
        L_0x00f4:
            r4 = r3
        L_0x00f5:
            r10 = 36
            r0[r10] = r2
            goto L_0x0110
        L_0x00fa:
            r10 = 32
            r0[r10] = r2
            return r4
        L_0x00ff:
            r10 = 29
            r0[r10] = r2
            return r5
        L_0x0104:
            r10 = move-exception
            r11 = 37
            r0[r11] = r2
            r10.printStackTrace()
            r10 = 38
            r0[r10] = r2
        L_0x0110:
            r10 = 39
            r0[r10] = r2
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.utils.WXSoInstallMgrSdk.initSo(java.lang.String, int, com.taobao.weex.adapter.IWXUserTrackAdapter):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00f4 A[Catch:{ FileNotFoundException -> 0x0104, all -> 0x00eb, Throwable -> 0x021a }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0107 A[Catch:{ FileNotFoundException -> 0x0104, all -> 0x00eb, Throwable -> 0x021a }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x010c A[Catch:{ FileNotFoundException -> 0x0104, all -> 0x00eb, Throwable -> 0x021a }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0133 A[Catch:{ FileNotFoundException -> 0x0104, all -> 0x00eb, Throwable -> 0x021a }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0138 A[Catch:{ FileNotFoundException -> 0x0104, all -> 0x00eb, Throwable -> 0x021a }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0157 A[Catch:{ FileNotFoundException -> 0x0104, all -> 0x00eb, Throwable -> 0x021a }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0171 A[Catch:{ FileNotFoundException -> 0x0104, all -> 0x00eb, Throwable -> 0x021a }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0195 A[Catch:{ FileNotFoundException -> 0x0104, all -> 0x00eb, Throwable -> 0x021a }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01a1 A[Catch:{ FileNotFoundException -> 0x0104, all -> 0x00eb, Throwable -> 0x021a }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01b2 A[Catch:{ Exception -> 0x0203, all -> 0x01eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01b7 A[Catch:{ Exception -> 0x0203, all -> 0x01eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01ee A[Catch:{ FileNotFoundException -> 0x0104, all -> 0x00eb, Throwable -> 0x021a }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01f3 A[Catch:{ FileNotFoundException -> 0x0104, all -> 0x00eb, Throwable -> 0x021a }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0205 A[Catch:{ FileNotFoundException -> 0x0104, all -> 0x00eb, Throwable -> 0x021a }] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x020a A[Catch:{ FileNotFoundException -> 0x0104, all -> 0x00eb, Throwable -> 0x021a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copyStartUpSo() {
        /*
            boolean[] r0 = $jacocoInit()
            r1 = 40
            r2 = 1
            r0[r1] = r2     // Catch:{ Throwable -> 0x021a }
            android.app.Application r1 = com.taobao.weex.WXEnvironment.getApplication()     // Catch:{ Throwable -> 0x021a }
            java.lang.String r1 = r1.getPackageName()     // Catch:{ Throwable -> 0x021a }
            r3 = 42
            r0[r3] = r2     // Catch:{ Throwable -> 0x021a }
            android.app.Application r3 = com.taobao.weex.WXEnvironment.getApplication()     // Catch:{ Throwable -> 0x021a }
            android.content.Context r3 = r3.getApplicationContext()     // Catch:{ Throwable -> 0x021a }
            java.io.File r3 = r3.getCacheDir()     // Catch:{ Throwable -> 0x021a }
            java.lang.String r3 = r3.getPath()     // Catch:{ Throwable -> 0x021a }
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x021a }
            r5 = 16
            if (r4 >= r5) goto L_0x004b
            r4 = 0
            r5 = 43
            r0[r5] = r2     // Catch:{ Throwable -> 0x021a }
            java.io.File r5 = new java.io.File     // Catch:{ Throwable -> 0x021a }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x021a }
            r6.<init>()     // Catch:{ Throwable -> 0x021a }
            r6.append(r3)     // Catch:{ Throwable -> 0x021a }
            java.lang.String r7 = "/libweexjst.so"
            r6.append(r7)     // Catch:{ Throwable -> 0x021a }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x021a }
            r5.<init>(r6)     // Catch:{ Throwable -> 0x021a }
            r6 = 44
            r0[r6] = r2     // Catch:{ Throwable -> 0x021a }
            goto L_0x0066
        L_0x004b:
            java.io.File r5 = new java.io.File     // Catch:{ Throwable -> 0x021a }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x021a }
            r4.<init>()     // Catch:{ Throwable -> 0x021a }
            r4.append(r3)     // Catch:{ Throwable -> 0x021a }
            java.lang.String r6 = "/libweexjsb.so"
            r4.append(r6)     // Catch:{ Throwable -> 0x021a }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x021a }
            r5.<init>(r4)     // Catch:{ Throwable -> 0x021a }
            r4 = 45
            r0[r4] = r2     // Catch:{ Throwable -> 0x021a }
            r4 = 1
        L_0x0066:
            java.lang.String r6 = "jsb.version"
            r7 = 46
            r0[r7] = r2     // Catch:{ Throwable -> 0x021a }
            java.io.File r7 = new java.io.File     // Catch:{ Throwable -> 0x021a }
            r7.<init>(r3, r6)     // Catch:{ Throwable -> 0x021a }
            r6 = 47
            r0[r6] = r2     // Catch:{ Throwable -> 0x021a }
            boolean r6 = r5.exists()     // Catch:{ Throwable -> 0x021a }
            r8 = 0
            if (r6 != 0) goto L_0x0082
            r6 = 48
            r0[r6] = r2     // Catch:{ Throwable -> 0x021a }
            goto L_0x0117
        L_0x0082:
            boolean r6 = r7.exists()     // Catch:{ Throwable -> 0x021a }
            if (r6 != 0) goto L_0x008e
            r6 = 49
            r0[r6] = r2     // Catch:{ Throwable -> 0x021a }
            goto L_0x0117
        L_0x008e:
            r6 = 50
            r0[r6] = r2     // Catch:{ FileNotFoundException -> 0x0104, all -> 0x00eb }
            java.io.FileReader r6 = new java.io.FileReader     // Catch:{ FileNotFoundException -> 0x0104, all -> 0x00eb }
            r6.<init>(r7)     // Catch:{ FileNotFoundException -> 0x0104, all -> 0x00eb }
            r9 = 51
            r0[r9] = r2     // Catch:{ FileNotFoundException -> 0x0105, all -> 0x00e9 }
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x0105, all -> 0x00e9 }
            r9.<init>(r6)     // Catch:{ FileNotFoundException -> 0x0105, all -> 0x00e9 }
            r10 = 52
            r0[r10] = r2     // Catch:{ FileNotFoundException -> 0x0105, all -> 0x00e9 }
            java.lang.String r9 = r9.readLine()     // Catch:{ FileNotFoundException -> 0x0105, all -> 0x00e9 }
            r10 = 53
            r0[r10] = r2     // Catch:{ FileNotFoundException -> 0x0105, all -> 0x00e9 }
            boolean r10 = android.text.TextUtils.isEmpty(r9)     // Catch:{ FileNotFoundException -> 0x0105, all -> 0x00e9 }
            if (r10 == 0) goto L_0x00b7
            r9 = 54
            r0[r9] = r2     // Catch:{ FileNotFoundException -> 0x0105, all -> 0x00e9 }
            goto L_0x00cd
        L_0x00b7:
            r10 = 55
            r0[r10] = r2     // Catch:{ FileNotFoundException -> 0x0105, all -> 0x00e9 }
            java.lang.String r10 = java.lang.String.valueOf(r2)     // Catch:{ FileNotFoundException -> 0x0105, all -> 0x00e9 }
            java.lang.String r9 = r9.trim()     // Catch:{ FileNotFoundException -> 0x0105, all -> 0x00e9 }
            boolean r9 = r10.equals(r9)     // Catch:{ FileNotFoundException -> 0x0105, all -> 0x00e9 }
            if (r9 != 0) goto L_0x00d9
            r9 = 56
            r0[r9] = r2     // Catch:{ FileNotFoundException -> 0x0105, all -> 0x00e9 }
        L_0x00cd:
            r9 = 62
            r0[r9] = r2     // Catch:{ Throwable -> 0x021a }
            r6.close()     // Catch:{ Throwable -> 0x021a }
            r6 = 63
            r0[r6] = r2     // Catch:{ Throwable -> 0x021a }
            goto L_0x0117
        L_0x00d9:
            r1 = 58
            r0[r1] = r2     // Catch:{ Throwable -> 0x021a }
            r6.close()     // Catch:{ Throwable -> 0x021a }
            r1 = 59
            r0[r1] = r2     // Catch:{ Throwable -> 0x021a }
            r1 = 60
            r0[r1] = r2
            return
        L_0x00e9:
            r1 = move-exception
            goto L_0x00ed
        L_0x00eb:
            r1 = move-exception
            r6 = r8
        L_0x00ed:
            if (r6 != 0) goto L_0x00f4
            r3 = 67
            r0[r3] = r2     // Catch:{ Throwable -> 0x021a }
            goto L_0x00ff
        L_0x00f4:
            r3 = 68
            r0[r3] = r2     // Catch:{ Throwable -> 0x021a }
            r6.close()     // Catch:{ Throwable -> 0x021a }
            r3 = 69
            r0[r3] = r2     // Catch:{ Throwable -> 0x021a }
        L_0x00ff:
            r3 = 70
            r0[r3] = r2     // Catch:{ Throwable -> 0x021a }
            throw r1     // Catch:{ Throwable -> 0x021a }
        L_0x0104:
            r6 = r8
        L_0x0105:
            if (r6 != 0) goto L_0x010c
            r6 = 64
            r0[r6] = r2     // Catch:{ Throwable -> 0x021a }
            goto L_0x0117
        L_0x010c:
            r9 = 65
            r0[r9] = r2     // Catch:{ Throwable -> 0x021a }
            r6.close()     // Catch:{ Throwable -> 0x021a }
            r6 = 66
            r0[r6] = r2     // Catch:{ Throwable -> 0x021a }
        L_0x0117:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x021a }
            r6.<init>()     // Catch:{ Throwable -> 0x021a }
            java.lang.String r9 = "/data/data/"
            r6.append(r9)     // Catch:{ Throwable -> 0x021a }
            r6.append(r1)     // Catch:{ Throwable -> 0x021a }
            java.lang.String r1 = "/lib"
            r6.append(r1)     // Catch:{ Throwable -> 0x021a }
            java.lang.String r1 = r6.toString()     // Catch:{ Throwable -> 0x021a }
            r6 = 71
            r0[r6] = r2     // Catch:{ Throwable -> 0x021a }
            if (r3 != 0) goto L_0x0138
            r3 = 72
            r0[r3] = r2     // Catch:{ Throwable -> 0x021a }
            goto L_0x0155
        L_0x0138:
            java.lang.String r6 = "/cache"
            int r6 = r3.indexOf(r6)     // Catch:{ Throwable -> 0x021a }
            if (r6 > 0) goto L_0x0145
            r3 = 73
            r0[r3] = r2     // Catch:{ Throwable -> 0x021a }
            goto L_0x0155
        L_0x0145:
            r1 = 74
            r0[r1] = r2     // Catch:{ Throwable -> 0x021a }
            java.lang.String r1 = "/cache"
            java.lang.String r6 = "/lib"
            java.lang.String r1 = r3.replace(r1, r6)     // Catch:{ Throwable -> 0x021a }
            r3 = 75
            r0[r3] = r2     // Catch:{ Throwable -> 0x021a }
        L_0x0155:
            if (r4 == 0) goto L_0x0171
            r3 = 76
            r0[r3] = r2     // Catch:{ Throwable -> 0x021a }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x021a }
            r3.<init>()     // Catch:{ Throwable -> 0x021a }
            r3.append(r1)     // Catch:{ Throwable -> 0x021a }
            java.lang.String r1 = "/libweexjsb.so"
            r3.append(r1)     // Catch:{ Throwable -> 0x021a }
            java.lang.String r1 = r3.toString()     // Catch:{ Throwable -> 0x021a }
            r3 = 77
            r0[r3] = r2     // Catch:{ Throwable -> 0x021a }
            goto L_0x0186
        L_0x0171:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x021a }
            r3.<init>()     // Catch:{ Throwable -> 0x021a }
            r3.append(r1)     // Catch:{ Throwable -> 0x021a }
            java.lang.String r1 = "/libweexjst.so"
            r3.append(r1)     // Catch:{ Throwable -> 0x021a }
            java.lang.String r1 = r3.toString()     // Catch:{ Throwable -> 0x021a }
            r3 = 78
            r0[r3] = r2     // Catch:{ Throwable -> 0x021a }
        L_0x0186:
            java.io.File r3 = new java.io.File     // Catch:{ Throwable -> 0x021a }
            r3.<init>(r1)     // Catch:{ Throwable -> 0x021a }
            r1 = 79
            r0[r1] = r2     // Catch:{ Throwable -> 0x021a }
            boolean r1 = r3.exists()     // Catch:{ Throwable -> 0x021a }
            if (r1 == 0) goto L_0x01a1
            r1 = 80
            r0[r1] = r2     // Catch:{ Throwable -> 0x021a }
            com.taobao.weex.utils.WXFileUtils.copyFile(r3, r5)     // Catch:{ Throwable -> 0x021a }
            r1 = 81
            r0[r1] = r2     // Catch:{ Throwable -> 0x021a }
            goto L_0x01a8
        L_0x01a1:
            com.taobao.weex.WXEnvironment.extractSo()     // Catch:{ Throwable -> 0x021a }
            r1 = 82
            r0[r1] = r2     // Catch:{ Throwable -> 0x021a }
        L_0x01a8:
            r1 = 83
            r0[r1] = r2     // Catch:{ Exception -> 0x0203, all -> 0x01eb }
            boolean r1 = r7.exists()     // Catch:{ Exception -> 0x0203, all -> 0x01eb }
            if (r1 == 0) goto L_0x01b7
            r1 = 84
            r0[r1] = r2     // Catch:{ Exception -> 0x0203, all -> 0x01eb }
            goto L_0x01c2
        L_0x01b7:
            r1 = 85
            r0[r1] = r2     // Catch:{ Exception -> 0x0203, all -> 0x01eb }
            r7.createNewFile()     // Catch:{ Exception -> 0x0203, all -> 0x01eb }
            r1 = 86
            r0[r1] = r2     // Catch:{ Exception -> 0x0203, all -> 0x01eb }
        L_0x01c2:
            java.io.FileWriter r1 = new java.io.FileWriter     // Catch:{ Exception -> 0x0203, all -> 0x01eb }
            r1.<init>(r7)     // Catch:{ Exception -> 0x0203, all -> 0x01eb }
            r3 = 87
            r0[r3] = r2     // Catch:{ Exception -> 0x01e9, all -> 0x01e5 }
            java.lang.String r3 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x01e9, all -> 0x01e5 }
            r1.write(r3)     // Catch:{ Exception -> 0x01e9, all -> 0x01e5 }
            r3 = 88
            r0[r3] = r2     // Catch:{ Exception -> 0x01e9, all -> 0x01e5 }
            r1.flush()     // Catch:{ Exception -> 0x01e9, all -> 0x01e5 }
            r3 = 90
            r0[r3] = r2     // Catch:{ Throwable -> 0x021a }
            r1.close()     // Catch:{ Throwable -> 0x021a }
            r1 = 91
            r0[r1] = r2     // Catch:{ Throwable -> 0x021a }
            goto L_0x0215
        L_0x01e5:
            r3 = move-exception
            r8 = r1
            r1 = r3
            goto L_0x01ec
        L_0x01e9:
            r8 = r1
            goto L_0x0203
        L_0x01eb:
            r1 = move-exception
        L_0x01ec:
            if (r8 != 0) goto L_0x01f3
            r3 = 95
            r0[r3] = r2     // Catch:{ Throwable -> 0x021a }
            goto L_0x01fe
        L_0x01f3:
            r3 = 96
            r0[r3] = r2     // Catch:{ Throwable -> 0x021a }
            r8.close()     // Catch:{ Throwable -> 0x021a }
            r3 = 97
            r0[r3] = r2     // Catch:{ Throwable -> 0x021a }
        L_0x01fe:
            r3 = 98
            r0[r3] = r2     // Catch:{ Throwable -> 0x021a }
            throw r1     // Catch:{ Throwable -> 0x021a }
        L_0x0203:
            if (r8 != 0) goto L_0x020a
            r1 = 92
            r0[r1] = r2     // Catch:{ Throwable -> 0x021a }
            goto L_0x0215
        L_0x020a:
            r1 = 93
            r0[r1] = r2     // Catch:{ Throwable -> 0x021a }
            r8.close()     // Catch:{ Throwable -> 0x021a }
            r1 = 94
            r0[r1] = r2     // Catch:{ Throwable -> 0x021a }
        L_0x0215:
            r1 = 99
            r0[r1] = r2
            goto L_0x0226
        L_0x021a:
            r1 = move-exception
            r3 = 100
            r0[r3] = r2
            r1.printStackTrace()
            r1 = 101(0x65, float:1.42E-43)
            r0[r1] = r2
        L_0x0226:
            r1 = 102(0x66, float:1.43E-43)
            r0[r1] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.utils.WXSoInstallMgrSdk.copyStartUpSo():void");
    }

    private static String _getFieldReflectively(Build build, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            Field field = Build.class.getField(str);
            $jacocoInit[103] = true;
            String obj = field.get(build).toString();
            $jacocoInit[104] = true;
            return obj;
        } catch (Exception unused) {
            $jacocoInit[105] = true;
            return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
    }

    private static String _cpuType() {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            str = Build.CPU_ABI;
            $jacocoInit[106] = true;
        } catch (Exception e) {
            $jacocoInit[107] = true;
            e.printStackTrace();
            str = "armeabi";
            $jacocoInit[108] = true;
        }
        if (!TextUtils.isEmpty(str)) {
            $jacocoInit[109] = true;
        } else {
            str = "armeabi";
            $jacocoInit[110] = true;
        }
        String lowerCase = str.toLowerCase();
        $jacocoInit[111] = true;
        return lowerCase;
    }

    static boolean checkSoIsValid(String str, long j) {
        boolean[] $jacocoInit = $jacocoInit();
        if (mContext != null) {
            $jacocoInit[112] = true;
            try {
                long currentTimeMillis = System.currentTimeMillis();
                $jacocoInit[114] = true;
                if (WXSoInstallMgrSdk.class.getClassLoader() instanceof PathClassLoader) {
                    $jacocoInit[115] = true;
                    String findLibrary = ((PathClassLoader) WXSoInstallMgrSdk.class.getClassLoader()).findLibrary(str);
                    $jacocoInit[116] = true;
                    if (!TextUtils.isEmpty(findLibrary)) {
                        $jacocoInit[117] = true;
                        File file = new File(findLibrary);
                        $jacocoInit[119] = true;
                        if (!file.exists()) {
                            $jacocoInit[120] = true;
                        } else if (j != file.length()) {
                            $jacocoInit[121] = true;
                            $jacocoInit[124] = true;
                            return false;
                        } else {
                            $jacocoInit[122] = true;
                        }
                        WXLogUtils.w("weex so size check path :" + findLibrary + "   " + (System.currentTimeMillis() - currentTimeMillis));
                        $jacocoInit[123] = true;
                        return true;
                    }
                    $jacocoInit[118] = true;
                    return false;
                }
                $jacocoInit[125] = true;
                $jacocoInit[131] = true;
                return true;
            } catch (Throwable th) {
                $jacocoInit[126] = true;
                WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_SDK_INIT;
                StringBuilder sb = new StringBuilder();
                sb.append("[WX_KEY_EXCEPTION_SDK_INIT_CPU_NOT_SUPPORT] for weex so size check fail exception :");
                $jacocoInit[127] = true;
                sb.append(th.getMessage());
                String sb2 = sb.toString();
                $jacocoInit[128] = true;
                WXExceptionUtils.commitCriticalExceptionRT((String) null, wXErrorCode, "checkSoIsValid", sb2, (Map<String, String>) null);
                $jacocoInit[129] = true;
                WXLogUtils.e("weex so size check fail exception :" + th.getMessage());
                $jacocoInit[130] = true;
            }
        } else {
            $jacocoInit[113] = true;
            return false;
        }
    }

    static String _targetSoFile(String str, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        Context context = mContext;
        if (context == null) {
            $jacocoInit[132] = true;
            return "";
        }
        String str2 = "/data/data/" + context.getPackageName() + "/files";
        $jacocoInit[133] = true;
        File filesDir = context.getFilesDir();
        if (filesDir == null) {
            $jacocoInit[134] = true;
        } else {
            $jacocoInit[135] = true;
            str2 = filesDir.getPath();
            $jacocoInit[136] = true;
        }
        String str3 = str2 + "/lib" + str + "bk" + i + ".so";
        $jacocoInit[137] = true;
        return str3;
    }

    static void removeSoIfExit(String str, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        String _targetSoFile = _targetSoFile(str, i);
        $jacocoInit[138] = true;
        File file = new File(_targetSoFile);
        $jacocoInit[139] = true;
        if (!file.exists()) {
            $jacocoInit[140] = true;
        } else {
            $jacocoInit[141] = true;
            file.delete();
            $jacocoInit[142] = true;
        }
        $jacocoInit[143] = true;
    }

    static boolean isExist(String str, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        String _targetSoFile = _targetSoFile(str, i);
        $jacocoInit[144] = true;
        File file = new File(_targetSoFile);
        $jacocoInit[145] = true;
        boolean exists = file.exists();
        $jacocoInit[146] = true;
        return exists;
    }

    static boolean _loadUnzipSo(String str, int i, IWXUserTrackAdapter iWXUserTrackAdapter) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[147] = true;
            if (!isExist(str, i)) {
                $jacocoInit[148] = true;
            } else if (mSoLoader != null) {
                $jacocoInit[149] = true;
                mSoLoader.doLoad(_targetSoFile(str, i));
                $jacocoInit[150] = true;
            } else {
                System.load(_targetSoFile(str, i));
                $jacocoInit[151] = true;
            }
            $jacocoInit[152] = true;
            z = true;
        } catch (Throwable th) {
            z = false;
            $jacocoInit[153] = true;
            WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_SDK_INIT_CPU_NOT_SUPPORT;
            StringBuilder sb = new StringBuilder();
            sb.append("[WX_KEY_EXCEPTION_SDK_INIT_WX_ERR_COPY_FROM_APK] \n Detail Msg is : ");
            $jacocoInit[154] = true;
            sb.append(th.getMessage());
            String sb2 = sb.toString();
            $jacocoInit[155] = true;
            WXExceptionUtils.commitCriticalExceptionRT((String) null, wXErrorCode, "_loadUnzipSo", sb2, (Map<String, String>) null);
            $jacocoInit[156] = true;
            WXLogUtils.e("", th);
            $jacocoInit[157] = true;
        }
        $jacocoInit[158] = true;
        return z;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.nio.channels.FileChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: java.nio.channels.FileChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: java.io.FileOutputStream} */
    /* JADX WARNING: type inference failed for: r7v0, types: [java.util.Map, java.util.zip.ZipFile, java.lang.String] */
    /* JADX WARNING: type inference failed for: r2v8, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r2v18 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x019f A[Catch:{ IOException -> 0x01ff }] */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x01be A[Catch:{ IOException -> 0x01ff }] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01c3  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0248  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x024d  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0185  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean unZipSelectedFiles(java.lang.String r12, int r13, com.taobao.weex.adapter.IWXUserTrackAdapter r14) throws java.util.zip.ZipException, java.io.IOException {
        /*
            boolean[] r0 = $jacocoInit()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "lib/armeabi/lib"
            r1.append(r2)
            r1.append(r12)
            java.lang.String r2 = ".so"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = ""
            android.content.Context r3 = mContext
            r4 = 0
            r5 = 1
            if (r3 != 0) goto L_0x0027
            r12 = 159(0x9f, float:2.23E-43)
            r0[r12] = r5
            return r4
        L_0x0027:
            android.content.pm.ApplicationInfo r6 = r3.getApplicationInfo()
            if (r6 != 0) goto L_0x0032
            r6 = 160(0xa0, float:2.24E-43)
            r0[r6] = r5
            goto L_0x0038
        L_0x0032:
            java.lang.String r2 = r6.sourceDir
            r6 = 161(0xa1, float:2.26E-43)
            r0[r6] = r5
        L_0x0038:
            java.util.zip.ZipFile r6 = new java.util.zip.ZipFile
            r6.<init>(r2)
            r2 = 162(0xa2, float:2.27E-43)
            r7 = 0
            r0[r2] = r5     // Catch:{ IOException -> 0x01ff }
            java.util.Enumeration r2 = r6.entries()     // Catch:{ IOException -> 0x01ff }
            r8 = 163(0xa3, float:2.28E-43)
            r0[r8] = r5     // Catch:{ IOException -> 0x01ff }
        L_0x004a:
            boolean r8 = r2.hasMoreElements()     // Catch:{ IOException -> 0x01ff }
            if (r8 == 0) goto L_0x01f1
            r8 = 164(0xa4, float:2.3E-43)
            r0[r8] = r5     // Catch:{ IOException -> 0x01ff }
            java.lang.Object r8 = r2.nextElement()     // Catch:{ IOException -> 0x01ff }
            java.util.zip.ZipEntry r8 = (java.util.zip.ZipEntry) r8     // Catch:{ IOException -> 0x01ff }
            r9 = 165(0xa5, float:2.31E-43)
            r0[r9] = r5     // Catch:{ IOException -> 0x01ff }
            java.lang.String r9 = r8.getName()     // Catch:{ IOException -> 0x01ff }
            boolean r9 = r9.startsWith(r1)     // Catch:{ IOException -> 0x01ff }
            if (r9 != 0) goto L_0x0071
            r8 = 166(0xa6, float:2.33E-43)
            r0[r8] = r5     // Catch:{ IOException -> 0x01ff }
            r8 = 221(0xdd, float:3.1E-43)
            r0[r8] = r5     // Catch:{ IOException -> 0x01ff }
            goto L_0x004a
        L_0x0071:
            r1 = 167(0xa7, float:2.34E-43)
            r0[r1] = r5     // Catch:{ all -> 0x017a }
            removeSoIfExit(r12, r13)     // Catch:{ all -> 0x017a }
            r1 = 168(0xa8, float:2.35E-43)
            r0[r1] = r5     // Catch:{ all -> 0x017a }
            java.io.InputStream r1 = r6.getInputStream(r8)     // Catch:{ all -> 0x017a }
            r2 = 169(0xa9, float:2.37E-43)
            r0[r2] = r5     // Catch:{ all -> 0x0177 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0177 }
            r2.<init>()     // Catch:{ all -> 0x0177 }
            java.lang.String r8 = "lib"
            r2.append(r8)     // Catch:{ all -> 0x0177 }
            r2.append(r12)     // Catch:{ all -> 0x0177 }
            java.lang.String r8 = "bk"
            r2.append(r8)     // Catch:{ all -> 0x0177 }
            r2.append(r13)     // Catch:{ all -> 0x0177 }
            java.lang.String r8 = ".so"
            r2.append(r8)     // Catch:{ all -> 0x0177 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0177 }
            java.io.FileOutputStream r2 = r3.openFileOutput(r2, r4)     // Catch:{ all -> 0x0177 }
            r3 = 170(0xaa, float:2.38E-43)
            r0[r3] = r5     // Catch:{ all -> 0x0174 }
            java.nio.channels.FileChannel r3 = r2.getChannel()     // Catch:{ all -> 0x0174 }
            r8 = 1024(0x400, float:1.435E-42)
            byte[] r8 = new byte[r8]     // Catch:{ all -> 0x0172 }
            r9 = 171(0xab, float:2.4E-43)
            r0[r9] = r5     // Catch:{ all -> 0x0172 }
            r9 = 0
        L_0x00b7:
            int r10 = r1.read(r8)     // Catch:{ all -> 0x0172 }
            if (r10 <= 0) goto L_0x00ce
            r11 = 172(0xac, float:2.41E-43)
            r0[r11] = r5     // Catch:{ all -> 0x0172 }
            java.nio.ByteBuffer r11 = java.nio.ByteBuffer.wrap(r8, r4, r10)     // Catch:{ all -> 0x0172 }
            r3.write(r11)     // Catch:{ all -> 0x0172 }
            int r9 = r9 + r10
            r10 = 173(0xad, float:2.42E-43)
            r0[r10] = r5     // Catch:{ all -> 0x0172 }
            goto L_0x00b7
        L_0x00ce:
            if (r1 != 0) goto L_0x00d5
            r1 = 174(0xae, float:2.44E-43)
            r0[r1] = r5     // Catch:{ IOException -> 0x01ff }
            goto L_0x00ed
        L_0x00d5:
            r8 = 175(0xaf, float:2.45E-43)
            r0[r8] = r5     // Catch:{ Exception -> 0x00e1 }
            r1.close()     // Catch:{ Exception -> 0x00e1 }
            r1 = 176(0xb0, float:2.47E-43)
            r0[r1] = r5     // Catch:{ IOException -> 0x01ff }
            goto L_0x00ed
        L_0x00e1:
            r1 = move-exception
            r8 = 177(0xb1, float:2.48E-43)
            r0[r8] = r5     // Catch:{ IOException -> 0x01ff }
            r1.printStackTrace()     // Catch:{ IOException -> 0x01ff }
            r1 = 178(0xb2, float:2.5E-43)
            r0[r1] = r5     // Catch:{ IOException -> 0x01ff }
        L_0x00ed:
            if (r3 != 0) goto L_0x00f4
            r1 = 179(0xb3, float:2.51E-43)
            r0[r1] = r5     // Catch:{ IOException -> 0x01ff }
            goto L_0x010c
        L_0x00f4:
            r1 = 180(0xb4, float:2.52E-43)
            r0[r1] = r5     // Catch:{ Exception -> 0x0100 }
            r3.close()     // Catch:{ Exception -> 0x0100 }
            r1 = 181(0xb5, float:2.54E-43)
            r0[r1] = r5     // Catch:{ IOException -> 0x01ff }
            goto L_0x010c
        L_0x0100:
            r1 = move-exception
            r3 = 182(0xb6, float:2.55E-43)
            r0[r3] = r5     // Catch:{ IOException -> 0x01ff }
            r1.printStackTrace()     // Catch:{ IOException -> 0x01ff }
            r1 = 183(0xb7, float:2.56E-43)
            r0[r1] = r5     // Catch:{ IOException -> 0x01ff }
        L_0x010c:
            if (r2 != 0) goto L_0x0113
            r1 = 184(0xb8, float:2.58E-43)
            r0[r1] = r5     // Catch:{ IOException -> 0x01ff }
            goto L_0x012b
        L_0x0113:
            r1 = 185(0xb9, float:2.59E-43)
            r0[r1] = r5     // Catch:{ Exception -> 0x011f }
            r2.close()     // Catch:{ Exception -> 0x011f }
            r1 = 186(0xba, float:2.6E-43)
            r0[r1] = r5     // Catch:{ IOException -> 0x01ff }
            goto L_0x012b
        L_0x011f:
            r1 = move-exception
            r2 = 187(0xbb, float:2.62E-43)
            r0[r2] = r5     // Catch:{ IOException -> 0x01ff }
            r1.printStackTrace()     // Catch:{ IOException -> 0x01ff }
            r1 = 188(0xbc, float:2.63E-43)
            r0[r1] = r5     // Catch:{ IOException -> 0x01ff }
        L_0x012b:
            r1 = 190(0xbe, float:2.66E-43)
            r0[r1] = r5     // Catch:{ IOException -> 0x01ff }
            r6.close()     // Catch:{ IOException -> 0x01ff }
            r1 = 191(0xbf, float:2.68E-43)
            r0[r1] = r5     // Catch:{ IOException -> 0x01ee, all -> 0x01eb }
            if (r9 > 0) goto L_0x0153
            r12 = 211(0xd3, float:2.96E-43)
            r0[r12] = r5     // Catch:{ IOException -> 0x01ee, all -> 0x01eb }
            if (r7 != 0) goto L_0x0143
            r12 = 217(0xd9, float:3.04E-43)
            r0[r12] = r5
            goto L_0x014e
        L_0x0143:
            r12 = 218(0xda, float:3.05E-43)
            r0[r12] = r5
            r7.close()
            r12 = 219(0xdb, float:3.07E-43)
            r0[r12] = r5
        L_0x014e:
            r12 = 220(0xdc, float:3.08E-43)
            r0[r12] = r5
            return r4
        L_0x0153:
            r1 = 212(0xd4, float:2.97E-43)
            r0[r1] = r5     // Catch:{ IOException -> 0x01ee, all -> 0x01eb }
            boolean r12 = _loadUnzipSo(r12, r13, r14)     // Catch:{ IOException -> 0x01ee, all -> 0x01eb }
            if (r7 != 0) goto L_0x0162
            r13 = 213(0xd5, float:2.98E-43)
            r0[r13] = r5
            goto L_0x016d
        L_0x0162:
            r13 = 214(0xd6, float:3.0E-43)
            r0[r13] = r5
            r7.close()
            r13 = 215(0xd7, float:3.01E-43)
            r0[r13] = r5
        L_0x016d:
            r13 = 216(0xd8, float:3.03E-43)
            r0[r13] = r5
            return r12
        L_0x0172:
            r12 = move-exception
            goto L_0x017e
        L_0x0174:
            r12 = move-exception
            r3 = r7
            goto L_0x017e
        L_0x0177:
            r12 = move-exception
            r2 = r7
            goto L_0x017d
        L_0x017a:
            r12 = move-exception
            r1 = r7
            r2 = r1
        L_0x017d:
            r3 = r2
        L_0x017e:
            if (r1 != 0) goto L_0x0185
            r13 = 192(0xc0, float:2.69E-43)
            r0[r13] = r5     // Catch:{ IOException -> 0x01ff }
            goto L_0x019d
        L_0x0185:
            r13 = 193(0xc1, float:2.7E-43)
            r0[r13] = r5     // Catch:{ Exception -> 0x0191 }
            r1.close()     // Catch:{ Exception -> 0x0191 }
            r13 = 194(0xc2, float:2.72E-43)
            r0[r13] = r5     // Catch:{ IOException -> 0x01ff }
            goto L_0x019d
        L_0x0191:
            r13 = move-exception
            r14 = 195(0xc3, float:2.73E-43)
            r0[r14] = r5     // Catch:{ IOException -> 0x01ff }
            r13.printStackTrace()     // Catch:{ IOException -> 0x01ff }
            r13 = 196(0xc4, float:2.75E-43)
            r0[r13] = r5     // Catch:{ IOException -> 0x01ff }
        L_0x019d:
            if (r3 != 0) goto L_0x01a4
            r13 = 197(0xc5, float:2.76E-43)
            r0[r13] = r5     // Catch:{ IOException -> 0x01ff }
            goto L_0x01bc
        L_0x01a4:
            r13 = 198(0xc6, float:2.77E-43)
            r0[r13] = r5     // Catch:{ Exception -> 0x01b0 }
            r3.close()     // Catch:{ Exception -> 0x01b0 }
            r13 = 199(0xc7, float:2.79E-43)
            r0[r13] = r5     // Catch:{ IOException -> 0x01ff }
            goto L_0x01bc
        L_0x01b0:
            r13 = move-exception
            r14 = 200(0xc8, float:2.8E-43)
            r0[r14] = r5     // Catch:{ IOException -> 0x01ff }
            r13.printStackTrace()     // Catch:{ IOException -> 0x01ff }
            r13 = 201(0xc9, float:2.82E-43)
            r0[r13] = r5     // Catch:{ IOException -> 0x01ff }
        L_0x01bc:
            if (r2 != 0) goto L_0x01c3
            r13 = 202(0xca, float:2.83E-43)
            r0[r13] = r5     // Catch:{ IOException -> 0x01ff }
            goto L_0x01db
        L_0x01c3:
            r13 = 203(0xcb, float:2.84E-43)
            r0[r13] = r5     // Catch:{ Exception -> 0x01cf }
            r2.close()     // Catch:{ Exception -> 0x01cf }
            r13 = 204(0xcc, float:2.86E-43)
            r0[r13] = r5     // Catch:{ IOException -> 0x01ff }
            goto L_0x01db
        L_0x01cf:
            r13 = move-exception
            r14 = 205(0xcd, float:2.87E-43)
            r0[r14] = r5     // Catch:{ IOException -> 0x01ff }
            r13.printStackTrace()     // Catch:{ IOException -> 0x01ff }
            r13 = 206(0xce, float:2.89E-43)
            r0[r13] = r5     // Catch:{ IOException -> 0x01ff }
        L_0x01db:
            r13 = 208(0xd0, float:2.91E-43)
            r0[r13] = r5     // Catch:{ IOException -> 0x01ff }
            r6.close()     // Catch:{ IOException -> 0x01ff }
            r13 = 209(0xd1, float:2.93E-43)
            r0[r13] = r5     // Catch:{ IOException -> 0x01ee, all -> 0x01eb }
            r13 = 210(0xd2, float:2.94E-43)
            r0[r13] = r5     // Catch:{ IOException -> 0x01ee, all -> 0x01eb }
            throw r12     // Catch:{ IOException -> 0x01ee, all -> 0x01eb }
        L_0x01eb:
            r12 = move-exception
            r6 = r7
            goto L_0x0246
        L_0x01ee:
            r12 = move-exception
            r6 = r7
            goto L_0x0200
        L_0x01f1:
            r12 = 223(0xdf, float:3.12E-43)
            r0[r12] = r5
            r6.close()
            r12 = 224(0xe0, float:3.14E-43)
            r0[r12] = r5
            goto L_0x0241
        L_0x01fd:
            r12 = move-exception
            goto L_0x0246
        L_0x01ff:
            r12 = move-exception
        L_0x0200:
            r13 = 225(0xe1, float:3.15E-43)
            r0[r13] = r5     // Catch:{ all -> 0x01fd }
            r12.printStackTrace()     // Catch:{ all -> 0x01fd }
            r13 = 226(0xe2, float:3.17E-43)
            r0[r13] = r5     // Catch:{ all -> 0x01fd }
            com.taobao.weex.common.WXErrorCode r13 = com.taobao.weex.common.WXErrorCode.WX_KEY_EXCEPTION_SDK_INIT_CPU_NOT_SUPPORT     // Catch:{ all -> 0x01fd }
            java.lang.String r14 = "unZipSelectedFiles"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x01fd }
            r1.<init>()     // Catch:{ all -> 0x01fd }
            java.lang.String r2 = "[WX_KEY_EXCEPTION_SDK_INIT_unZipSelectedFiles] \n Detail msg is: "
            r1.append(r2)     // Catch:{ all -> 0x01fd }
            r2 = 227(0xe3, float:3.18E-43)
            r0[r2] = r5     // Catch:{ all -> 0x01fd }
            java.lang.String r12 = r12.getMessage()     // Catch:{ all -> 0x01fd }
            r1.append(r12)     // Catch:{ all -> 0x01fd }
            java.lang.String r12 = r1.toString()     // Catch:{ all -> 0x01fd }
            r1 = 228(0xe4, float:3.2E-43)
            r0[r1] = r5     // Catch:{ all -> 0x01fd }
            com.taobao.weex.utils.WXExceptionUtils.commitCriticalExceptionRT(r7, r13, r14, r12, r7)     // Catch:{ all -> 0x01fd }
            if (r6 != 0) goto L_0x0236
            r12 = 229(0xe5, float:3.21E-43)
            r0[r12] = r5
            goto L_0x0241
        L_0x0236:
            r12 = 230(0xe6, float:3.22E-43)
            r0[r12] = r5
            r6.close()
            r12 = 231(0xe7, float:3.24E-43)
            r0[r12] = r5
        L_0x0241:
            r12 = 236(0xec, float:3.31E-43)
            r0[r12] = r5
            return r4
        L_0x0246:
            if (r6 != 0) goto L_0x024d
            r13 = 232(0xe8, float:3.25E-43)
            r0[r13] = r5
            goto L_0x0258
        L_0x024d:
            r13 = 233(0xe9, float:3.27E-43)
            r0[r13] = r5
            r6.close()
            r13 = 234(0xea, float:3.28E-43)
            r0[r13] = r5
        L_0x0258:
            r13 = 235(0xeb, float:3.3E-43)
            r0[r13] = r5
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.utils.WXSoInstallMgrSdk.unZipSelectedFiles(java.lang.String, int, com.taobao.weex.adapter.IWXUserTrackAdapter):boolean");
    }
}
