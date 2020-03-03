package com.mi.blockcanary.internal;

import android.app.ActivityManager;
import android.util.Log;
import com.mi.blockcanary.BlockCanaryInternals;
import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

class PerformanceUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6754a = "PerformanceUtils";
    private static int b;
    private static long c;

    private PerformanceUtils() {
        throw new InstantiationError("Must not instantiate this class");
    }

    public static int a() {
        if (b == 0) {
            try {
                b = new File("/sys/devices/system/cpu/").listFiles(new FileFilter() {
                    public boolean accept(File file) {
                        return Pattern.matches("cpu[0-9]", file.getName());
                    }
                }).length;
            } catch (Exception e) {
                Log.e(f6754a, "getNumCores exception", e);
                b = 1;
            }
        }
        return b;
    }

    public static long b() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) BlockCanaryInternals.c().j().getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem / 1024;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0053 A[SYNTHETIC, Splitter:B:24:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005b A[SYNTHETIC, Splitter:B:28:0x005b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long c() {
        /*
            long r0 = c
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x0068
            java.lang.String r0 = "/proc/meminfo"
            r1 = -1
            r3 = 0
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ IOException -> 0x0049 }
            r4.<init>(r0)     // Catch:{ IOException -> 0x0049 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0043, all -> 0x0041 }
            r3 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r4, r3)     // Catch:{ IOException -> 0x0043, all -> 0x0041 }
            java.lang.String r3 = r0.readLine()     // Catch:{ IOException -> 0x0043, all -> 0x0041 }
            if (r3 == 0) goto L_0x0031
            java.lang.String r5 = "\\s+"
            java.lang.String[] r3 = r3.split(r5)     // Catch:{ IOException -> 0x0043, all -> 0x0041 }
            r5 = 1
            r3 = r3[r5]     // Catch:{ IOException -> 0x0043, all -> 0x0041 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ IOException -> 0x0043, all -> 0x0041 }
            int r3 = r3.intValue()     // Catch:{ IOException -> 0x0043, all -> 0x0041 }
            long r1 = (long) r3     // Catch:{ IOException -> 0x0043, all -> 0x0041 }
        L_0x0031:
            r0.close()     // Catch:{ IOException -> 0x0043, all -> 0x0041 }
            r4.close()     // Catch:{ IOException -> 0x0038 }
            goto L_0x0056
        L_0x0038:
            r0 = move-exception
            java.lang.String r3 = "PerformanceUtils"
            java.lang.String r4 = "close localFileReader exception = "
            android.util.Log.e(r3, r4, r0)
            goto L_0x0056
        L_0x0041:
            r0 = move-exception
            goto L_0x0059
        L_0x0043:
            r0 = move-exception
            r3 = r4
            goto L_0x004a
        L_0x0046:
            r0 = move-exception
            r4 = r3
            goto L_0x0059
        L_0x0049:
            r0 = move-exception
        L_0x004a:
            java.lang.String r4 = "PerformanceUtils"
            java.lang.String r5 = "getTotalMemory exception = "
            android.util.Log.e(r4, r5, r0)     // Catch:{ all -> 0x0046 }
            if (r3 == 0) goto L_0x0056
            r3.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0056:
            c = r1
            goto L_0x0068
        L_0x0059:
            if (r4 == 0) goto L_0x0067
            r4.close()     // Catch:{ IOException -> 0x005f }
            goto L_0x0067
        L_0x005f:
            r1 = move-exception
            java.lang.String r2 = "PerformanceUtils"
            java.lang.String r3 = "close localFileReader exception = "
            android.util.Log.e(r2, r3, r1)
        L_0x0067:
            throw r0
        L_0x0068:
            long r0 = c
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.blockcanary.internal.PerformanceUtils.c():long");
    }
}
