package com.alipay.mobile.security.bio.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.StatFs;
import android.util.Log;
import java.io.File;

public class DeviceUtil {
    public static int getProcessorNum() {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(new b()).length;
        } catch (Throwable unused) {
            return 0;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x004f, code lost:
        if (r0 == null) goto L_0x0052;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x002c */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003f A[SYNTHETIC, Splitter:B:22:0x003f] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0044 A[SYNTHETIC, Splitter:B:26:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x004c A[SYNTHETIC, Splitter:B:34:0x004c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long getMemorySize() {
        /*
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            r2 = 0
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ Throwable -> 0x0048, all -> 0x0039 }
            r4.<init>(r0)     // Catch:{ Throwable -> 0x0048, all -> 0x0039 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0037, all -> 0x0032 }
            r5 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r4, r5)     // Catch:{ Throwable -> 0x0037, all -> 0x0032 }
            java.lang.String r1 = r0.readLine()     // Catch:{ Throwable -> 0x004a, all -> 0x0030 }
            if (r1 == 0) goto L_0x0029
            java.lang.String r5 = "\\s+"
            java.lang.String[] r1 = r1.split(r5)     // Catch:{ Throwable -> 0x004a, all -> 0x0030 }
            r5 = 1
            r1 = r1[r5]     // Catch:{ Throwable -> 0x004a, all -> 0x0030 }
            long r5 = java.lang.Long.parseLong(r1)     // Catch:{ Throwable -> 0x004a, all -> 0x0030 }
            r1 = 1024(0x400, double:5.06E-321)
            long r5 = r5 * r1
            r2 = r5
        L_0x0029:
            r4.close()     // Catch:{ Throwable -> 0x002c }
        L_0x002c:
            r0.close()     // Catch:{ Throwable -> 0x0052 }
            goto L_0x0052
        L_0x0030:
            r1 = move-exception
            goto L_0x003d
        L_0x0032:
            r0 = move-exception
            r7 = r1
            r1 = r0
            r0 = r7
            goto L_0x003d
        L_0x0037:
            r0 = r1
            goto L_0x004a
        L_0x0039:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x003d:
            if (r4 == 0) goto L_0x0042
            r4.close()     // Catch:{ Throwable -> 0x0042 }
        L_0x0042:
            if (r0 == 0) goto L_0x0047
            r0.close()     // Catch:{ Throwable -> 0x0047 }
        L_0x0047:
            throw r1
        L_0x0048:
            r0 = r1
            r4 = r0
        L_0x004a:
            if (r4 == 0) goto L_0x004f
            r4.close()     // Catch:{ Throwable -> 0x004f }
        L_0x004f:
            if (r0 == 0) goto L_0x0052
            goto L_0x002c
        L_0x0052:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.security.bio.utils.DeviceUtil.getMemorySize():long");
    }

    public static long getTotalStorageSize(File file) {
        try {
            StatFs statFs = new StatFs(file.getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static long getAvailableStorageSize(File file) {
        try {
            StatFs statFs = new StatFs(file.getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static boolean isDebug(Context context) {
        try {
            return (context.getPackageManager().getApplicationInfo(context.getPackageName(), 16384).flags & 2) != 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String getVersionName(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static String getUtdid(Context context) {
        String str;
        try {
            str = (String) Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
        } catch (Exception unused) {
            Log.i("UTDID", "[*] UTDID error。");
            str = "";
        }
        Log.i("UTDID", "[*] UTDID:" + str);
        return str;
    }
}
