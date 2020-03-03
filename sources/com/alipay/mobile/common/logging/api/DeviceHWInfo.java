package com.alipay.mobile.common.logging.api;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;

public class DeviceHWInfo {
    public static final int DEVICEINFO_NO_INIT = -100;
    public static final int DEVICEINFO_UNKNOWN = -1;
    public static final String TAG = "DeviceHWInfo";

    /* renamed from: a  reason: collision with root package name */
    private static final FileFilter f946a = new FileFilter() {
        public boolean accept(File file) {
            String name = file.getName();
            if (!name.startsWith("cpu")) {
                return false;
            }
            for (int i = 3; i < name.length(); i++) {
                if (!Character.isDigit(name.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    };
    static int sCoreNum = -100;
    static String sCpuName = String.valueOf(-100);
    static int sFrequency = -100;
    static long sRamSize = -100;

    /* JADX WARNING: Can't wrap try/catch for region: R(4:14|15|16|17) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x002b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getNumberOfCPUCores() {
        /*
            int r0 = sCoreNum
            r1 = -1
            if (r0 != r1) goto L_0x0008
            int r0 = sCoreNum
            return r0
        L_0x0008:
            int r0 = sCoreNum
            r2 = -100
            if (r0 != r2) goto L_0x0032
            java.lang.Class<com.alipay.mobile.common.logging.api.DeviceHWInfo> r0 = com.alipay.mobile.common.logging.api.DeviceHWInfo.class
            monitor-enter(r0)
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x002f }
            r3 = 10
            if (r2 > r3) goto L_0x001a
            r1 = 1
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return r1
        L_0x001a:
            java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x002b }
            java.lang.String r3 = "/sys/devices/system/cpu/"
            r2.<init>(r3)     // Catch:{ Throwable -> 0x002b }
            java.io.FileFilter r3 = f946a     // Catch:{ Throwable -> 0x002b }
            java.io.File[] r2 = r2.listFiles(r3)     // Catch:{ Throwable -> 0x002b }
            int r2 = r2.length     // Catch:{ Throwable -> 0x002b }
            sCoreNum = r2     // Catch:{ Throwable -> 0x002b }
            goto L_0x002d
        L_0x002b:
            sCoreNum = r1     // Catch:{ all -> 0x002f }
        L_0x002d:
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            goto L_0x0032
        L_0x002f:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            throw r1
        L_0x0032:
            int r0 = sCoreNum
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.api.DeviceHWInfo.getNumberOfCPUCores():int");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0070 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00a0 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getCPUMaxFreqKHz() {
        /*
            int r0 = sFrequency
            r1 = -1
            if (r0 != r1) goto L_0x0008
            int r0 = sFrequency
            return r0
        L_0x0008:
            int r0 = sFrequency
            r2 = -100
            if (r0 != r2) goto L_0x00a6
            java.lang.Class<com.alipay.mobile.common.logging.api.DeviceHWInfo> r0 = com.alipay.mobile.common.logging.api.DeviceHWInfo.class
            monitor-enter(r0)
            r3 = 0
            r4 = 0
        L_0x0013:
            int r5 = getNumberOfCPUCores()     // Catch:{ IOException -> 0x00a0 }
            if (r4 >= r5) goto L_0x007c
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00a0 }
            r5.<init>()     // Catch:{ IOException -> 0x00a0 }
            java.lang.String r6 = "/sys/devices/system/cpu/cpu"
            r5.append(r6)     // Catch:{ IOException -> 0x00a0 }
            r5.append(r4)     // Catch:{ IOException -> 0x00a0 }
            java.lang.String r6 = "/cpufreq/cpuinfo_max_freq"
            r5.append(r6)     // Catch:{ IOException -> 0x00a0 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x00a0 }
            java.io.File r6 = new java.io.File     // Catch:{ IOException -> 0x00a0 }
            r6.<init>(r5)     // Catch:{ IOException -> 0x00a0 }
            boolean r5 = r6.exists()     // Catch:{ IOException -> 0x00a0 }
            if (r5 == 0) goto L_0x0079
            r5 = 128(0x80, float:1.794E-43)
            byte[] r5 = new byte[r5]     // Catch:{ IOException -> 0x00a0 }
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ IOException -> 0x00a0 }
            r7.<init>(r6)     // Catch:{ IOException -> 0x00a0 }
            r7.read(r5)     // Catch:{ NumberFormatException -> 0x0070, all -> 0x0074 }
            r6 = 0
        L_0x0047:
            byte r8 = r5[r6]     // Catch:{ NumberFormatException -> 0x0070, all -> 0x0074 }
            boolean r8 = java.lang.Character.isDigit(r8)     // Catch:{ NumberFormatException -> 0x0070, all -> 0x0074 }
            if (r8 == 0) goto L_0x0055
            int r8 = r5.length     // Catch:{ NumberFormatException -> 0x0070, all -> 0x0074 }
            if (r6 >= r8) goto L_0x0055
            int r6 = r6 + 1
            goto L_0x0047
        L_0x0055:
            java.lang.String r8 = new java.lang.String     // Catch:{ NumberFormatException -> 0x0070, all -> 0x0074 }
            r8.<init>(r5, r3, r6)     // Catch:{ NumberFormatException -> 0x0070, all -> 0x0074 }
            int r5 = java.lang.Integer.parseInt(r8)     // Catch:{ NumberFormatException -> 0x0070, all -> 0x0074 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ NumberFormatException -> 0x0070, all -> 0x0074 }
            int r6 = r5.intValue()     // Catch:{ NumberFormatException -> 0x0070, all -> 0x0074 }
            int r8 = sFrequency     // Catch:{ NumberFormatException -> 0x0070, all -> 0x0074 }
            if (r6 <= r8) goto L_0x0070
            int r5 = r5.intValue()     // Catch:{ NumberFormatException -> 0x0070, all -> 0x0074 }
            sFrequency = r5     // Catch:{ NumberFormatException -> 0x0070, all -> 0x0074 }
        L_0x0070:
            r7.close()     // Catch:{ IOException -> 0x00a0 }
            goto L_0x0079
        L_0x0074:
            r2 = move-exception
            r7.close()     // Catch:{ IOException -> 0x00a0 }
            throw r2     // Catch:{ IOException -> 0x00a0 }
        L_0x0079:
            int r4 = r4 + 1
            goto L_0x0013
        L_0x007c:
            int r3 = sFrequency     // Catch:{ IOException -> 0x00a0 }
            if (r3 != r2) goto L_0x00a2
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x00a0 }
            java.lang.String r3 = "/proc/cpuinfo"
            r2.<init>(r3)     // Catch:{ IOException -> 0x00a0 }
            java.lang.String r3 = "cpu MHz"
            int r3 = a((java.lang.String) r3, (java.io.FileInputStream) r2)     // Catch:{ all -> 0x0099 }
            int r3 = r3 * 1000
            int r4 = sFrequency     // Catch:{ all -> 0x0099 }
            if (r3 <= r4) goto L_0x0095
            sFrequency = r3     // Catch:{ all -> 0x0099 }
        L_0x0095:
            r2.close()     // Catch:{ IOException -> 0x00a0 }
            goto L_0x00a2
        L_0x0099:
            r3 = move-exception
            r2.close()     // Catch:{ IOException -> 0x00a0 }
            throw r3     // Catch:{ IOException -> 0x00a0 }
        L_0x009e:
            r1 = move-exception
            goto L_0x00a4
        L_0x00a0:
            sFrequency = r1     // Catch:{ all -> 0x009e }
        L_0x00a2:
            monitor-exit(r0)     // Catch:{ all -> 0x009e }
            goto L_0x00a6
        L_0x00a4:
            monitor-exit(r0)     // Catch:{ all -> 0x009e }
            throw r1
        L_0x00a6:
            int r0 = sFrequency
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.api.DeviceHWInfo.getCPUMaxFreqKHz():int");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:(8:12|13|14|15|16|17|18|19)|26|27|29|30) */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x004d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0052 */
    @android.annotation.TargetApi(16)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long getTotalMemory(android.content.Context r8) {
        /*
            long r0 = sRamSize
            r2 = -1
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x000b
            long r0 = sRamSize
            return r0
        L_0x000b:
            long r0 = sRamSize
            r4 = -100
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0058
            java.lang.Class<com.alipay.mobile.common.logging.api.DeviceHWInfo> r0 = com.alipay.mobile.common.logging.api.DeviceHWInfo.class
            monitor-enter(r0)
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x0052 }
            r4 = 16
            if (r1 < r4) goto L_0x0031
            android.app.ActivityManager$MemoryInfo r1 = new android.app.ActivityManager$MemoryInfo     // Catch:{ Throwable -> 0x0052 }
            r1.<init>()     // Catch:{ Throwable -> 0x0052 }
            java.lang.String r4 = "activity"
            java.lang.Object r8 = r8.getSystemService(r4)     // Catch:{ Throwable -> 0x0052 }
            android.app.ActivityManager r8 = (android.app.ActivityManager) r8     // Catch:{ Throwable -> 0x0052 }
            r8.getMemoryInfo(r1)     // Catch:{ Throwable -> 0x0052 }
            long r4 = r1.totalMem     // Catch:{ Throwable -> 0x0052 }
            sRamSize = r4     // Catch:{ Throwable -> 0x0052 }
            goto L_0x0054
        L_0x0031:
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ IOException -> 0x004c }
            java.lang.String r1 = "/proc/meminfo"
            r8.<init>(r1)     // Catch:{ IOException -> 0x004c }
            java.lang.String r1 = "MemTotal"
            int r1 = a((java.lang.String) r1, (java.io.FileInputStream) r8)     // Catch:{ all -> 0x0047 }
            long r4 = (long) r1
            r6 = 1024(0x400, double:5.06E-321)
            long r4 = r4 * r6
            r8.close()     // Catch:{ IOException -> 0x004d }
            goto L_0x004d
        L_0x0047:
            r1 = move-exception
            r8.close()     // Catch:{ IOException -> 0x004c }
            throw r1     // Catch:{ IOException -> 0x004c }
        L_0x004c:
            r4 = r2
        L_0x004d:
            sRamSize = r4     // Catch:{ Throwable -> 0x0052 }
            goto L_0x0054
        L_0x0050:
            r8 = move-exception
            goto L_0x0056
        L_0x0052:
            sRamSize = r2     // Catch:{ all -> 0x0050 }
        L_0x0054:
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            goto L_0x0058
        L_0x0056:
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            throw r8
        L_0x0058:
            long r0 = sRamSize
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.api.DeviceHWInfo.getTotalMemory(android.content.Context):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0072 A[SYNTHETIC, Splitter:B:40:0x0072] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x007e A[SYNTHETIC, Splitter:B:47:0x007e] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x008b A[SYNTHETIC, Splitter:B:55:0x008b] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0099 A[SYNTHETIC, Splitter:B:63:0x0099] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getCpuName() {
        /*
            java.lang.String r0 = sCpuName
            r1 = -1
            java.lang.String r2 = java.lang.String.valueOf(r1)
            boolean r0 = android.text.TextUtils.equals(r0, r2)
            if (r0 == 0) goto L_0x0010
            java.lang.String r0 = sCpuName
            return r0
        L_0x0010:
            java.lang.String r0 = sCpuName
            r2 = -100
            java.lang.String r2 = java.lang.String.valueOf(r2)
            boolean r0 = android.text.TextUtils.equals(r0, r2)
            if (r0 == 0) goto L_0x00a6
            java.lang.Class<com.alipay.mobile.common.logging.api.DeviceHWInfo> r0 = com.alipay.mobile.common.logging.api.DeviceHWInfo.class
            monitor-enter(r0)
            r2 = 0
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x0061, all -> 0x005d }
            java.lang.String r4 = "/proc/cpuinfo"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x0061, all -> 0x005d }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0058, all -> 0x0055 }
            r4.<init>(r3)     // Catch:{ Throwable -> 0x0058, all -> 0x0055 }
            java.lang.String r2 = r4.readLine()     // Catch:{ Throwable -> 0x0053 }
            java.lang.String r5 = ":\\s+"
            r6 = 2
            java.lang.String[] r2 = r2.split(r5, r6)     // Catch:{ Throwable -> 0x0053 }
            r5 = 1
            r2 = r2[r5]     // Catch:{ Throwable -> 0x0053 }
            sCpuName = r2     // Catch:{ Throwable -> 0x0053 }
            r3.close()     // Catch:{ Throwable -> 0x0042 }
            goto L_0x0048
        L_0x0042:
            r1 = move-exception
            java.lang.String r2 = "DeviceHWInfo"
            android.util.Log.w(r2, r1)     // Catch:{ all -> 0x008f }
        L_0x0048:
            r4.close()     // Catch:{ Throwable -> 0x004c }
            goto L_0x0086
        L_0x004c:
            r1 = move-exception
            java.lang.String r2 = "DeviceHWInfo"
        L_0x004f:
            android.util.Log.w(r2, r1)     // Catch:{ all -> 0x008f }
            goto L_0x0086
        L_0x0053:
            r2 = move-exception
            goto L_0x0065
        L_0x0055:
            r1 = move-exception
            r4 = r2
            goto L_0x0089
        L_0x0058:
            r4 = move-exception
            r7 = r4
            r4 = r2
            r2 = r7
            goto L_0x0065
        L_0x005d:
            r1 = move-exception
            r3 = r2
            r4 = r3
            goto L_0x0089
        L_0x0061:
            r3 = move-exception
            r4 = r2
            r2 = r3
            r3 = r4
        L_0x0065:
            java.lang.String r5 = "DeviceHWInfo"
            android.util.Log.w(r5, r2)     // Catch:{ all -> 0x0088 }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x0088 }
            sCpuName = r1     // Catch:{ all -> 0x0088 }
            if (r3 == 0) goto L_0x007c
            r3.close()     // Catch:{ Throwable -> 0x0076 }
            goto L_0x007c
        L_0x0076:
            r1 = move-exception
            java.lang.String r2 = "DeviceHWInfo"
            android.util.Log.w(r2, r1)     // Catch:{ all -> 0x008f }
        L_0x007c:
            if (r4 == 0) goto L_0x0086
            r4.close()     // Catch:{ Throwable -> 0x0082 }
            goto L_0x0086
        L_0x0082:
            r1 = move-exception
            java.lang.String r2 = "DeviceHWInfo"
            goto L_0x004f
        L_0x0086:
            monitor-exit(r0)     // Catch:{ all -> 0x008f }
            goto L_0x00a6
        L_0x0088:
            r1 = move-exception
        L_0x0089:
            if (r3 == 0) goto L_0x0097
            r3.close()     // Catch:{ Throwable -> 0x0091 }
            goto L_0x0097
        L_0x008f:
            r1 = move-exception
            goto L_0x00a4
        L_0x0091:
            r2 = move-exception
            java.lang.String r3 = "DeviceHWInfo"
            android.util.Log.w(r3, r2)     // Catch:{ all -> 0x008f }
        L_0x0097:
            if (r4 == 0) goto L_0x00a3
            r4.close()     // Catch:{ Throwable -> 0x009d }
            goto L_0x00a3
        L_0x009d:
            r2 = move-exception
            java.lang.String r3 = "DeviceHWInfo"
            android.util.Log.w(r3, r2)     // Catch:{ all -> 0x008f }
        L_0x00a3:
            throw r1     // Catch:{ all -> 0x008f }
        L_0x00a4:
            monitor-exit(r0)     // Catch:{ all -> 0x008f }
            throw r1
        L_0x00a6:
            java.lang.String r0 = sCpuName
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.api.DeviceHWInfo.getCpuName():java.lang.String");
    }

    private static int a(String str, FileInputStream fileInputStream) {
        byte[] bArr = new byte[1024];
        try {
            int read = fileInputStream.read(bArr);
            int i = 0;
            while (i < read) {
                if (bArr[i] == 10 || i == 0) {
                    if (bArr[i] == 10) {
                        i++;
                    }
                    int i2 = i;
                    while (true) {
                        if (i2 >= read) {
                            continue;
                            break;
                        }
                        int i3 = i2 - i;
                        if (bArr[i2] != str.charAt(i3)) {
                            break;
                        } else if (i3 == str.length() - 1) {
                            return a(bArr, i2);
                        } else {
                            i2++;
                        }
                    }
                }
                i++;
            }
            return -1;
        } catch (IOException | NumberFormatException unused) {
            return -1;
        }
    }

    private static int a(byte[] bArr, int i) {
        while (i < bArr.length && bArr[i] != 10) {
            if (Character.isDigit(bArr[i])) {
                int i2 = i + 1;
                while (i2 < bArr.length && Character.isDigit(bArr[i2])) {
                    i2++;
                }
                return Integer.parseInt(new String(bArr, 0, i, i2 - i));
            }
            i++;
        }
        return -1;
    }
}
