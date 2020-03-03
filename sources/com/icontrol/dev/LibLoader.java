package com.icontrol.dev;

import android.content.Context;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginSoManager;
import java.io.File;

public final class LibLoader {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f5936a = {"irdnairdb", "irdna_sdk"};
    private static final String[] b = {PluginSoManager.c, "armeabi-v7a", "armeabi"};

    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        java.lang.System.loadLibrary(r6);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0054 */
    private static void a(java.lang.String r8, int r9) {
        /*
            java.lang.String[] r0 = f5936a
            int r0 = r0.length
            if (r9 < 0) goto L_0x0008
            if (r0 <= r9) goto L_0x0008
            goto L_0x0009
        L_0x0008:
            r9 = r0
        L_0x0009:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r8)
            java.lang.String r8 = "/"
            r0.append(r8)
            int r8 = r0.length()
            java.lang.String[] r1 = b
            int r1 = r1.length
            r2 = 0
            r3 = 0
        L_0x001f:
            if (r3 < r1) goto L_0x0022
            return
        L_0x0022:
            r0.setLength(r8)
            java.lang.String[] r4 = b
            r4 = r4[r3]
            r0.append(r4)
            java.lang.String r4 = "/lib"
            r0.append(r4)
            int r4 = r0.length()
            r5 = r3
            r3 = 0
        L_0x0037:
            if (r3 < r9) goto L_0x003c
            int r3 = r5 + 1
            goto L_0x001f
        L_0x003c:
            java.lang.String[] r6 = f5936a
            r6 = r6[r3]
            r0.setLength(r4)
            r0.append(r6)
            java.lang.String r7 = ".so"
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            java.lang.System.load(r7)     // Catch:{ Throwable -> 0x0054 }
            r5 = r1
            goto L_0x0057
        L_0x0054:
            java.lang.System.loadLibrary(r6)     // Catch:{ Throwable -> 0x0057 }
        L_0x0057:
            int r3 = r3 + 1
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: com.icontrol.dev.LibLoader.a(java.lang.String, int):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:6|7) */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        java.lang.System.load(r7);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x003a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.String[] r0 = b
            int r0 = r0.length
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r6)
            java.lang.String r6 = "/"
            r1.append(r6)
            int r6 = r1.length()
            r2 = 0
            r3 = 0
        L_0x0016:
            if (r2 < r0) goto L_0x0019
            goto L_0x0040
        L_0x0019:
            r1.setLength(r6)
            java.lang.String[] r4 = b
            r4 = r4[r2]
            r1.append(r4)
            java.lang.String r4 = "/lib"
            r1.append(r4)
            r1.append(r7)
            java.lang.String r4 = ".so"
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r5 = 1
            java.lang.System.load(r4)     // Catch:{ Throwable -> 0x003a }
        L_0x0038:
            r3 = 1
            goto L_0x003e
        L_0x003a:
            java.lang.System.load(r7)     // Catch:{ Throwable -> 0x003e }
            goto L_0x0038
        L_0x003e:
            if (r3 == 0) goto L_0x0041
        L_0x0040:
            return r3
        L_0x0041:
            int r2 = r2 + 1
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.icontrol.dev.LibLoader.a(java.lang.String, java.lang.String):boolean");
    }

    public static String a(Context context) {
        return context.getFilesDir().getAbsolutePath();
    }

    private static void a(String str) {
        File file = new File(str);
        if (!file.isDirectory()) {
            try {
                file.mkdirs();
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0030 A[SYNTHETIC, Splitter:B:10:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(android.content.Context r8) {
        /*
            java.lang.String r0 = r8.getPackageName()
            java.lang.String r1 = a((android.content.Context) r8)
            r2 = 0
            android.content.SharedPreferences r3 = r8.getSharedPreferences(r0, r2)
            java.lang.String r4 = "app.version"
            int r4 = r3.getInt(r4, r2)
            r5 = 1
            android.content.pm.PackageManager r6 = r8.getPackageManager()     // Catch:{ Throwable -> 0x0023 }
            android.content.pm.PackageInfo r0 = r6.getPackageInfo(r0, r5)     // Catch:{ Throwable -> 0x0023 }
            int r0 = r0.versionCode     // Catch:{ Throwable -> 0x0023 }
            if (r4 >= r0) goto L_0x0021
            goto L_0x0024
        L_0x0021:
            r4 = 0
            goto L_0x0025
        L_0x0023:
            r0 = 1
        L_0x0024:
            r4 = 1
        L_0x0025:
            java.io.File r6 = new java.io.File
            r6.<init>(r1)
            boolean r7 = r6.isDirectory()
            if (r7 != 0) goto L_0x0034
            r6.mkdirs()     // Catch:{ Throwable -> 0x0042 }
            goto L_0x0042
        L_0x0034:
            java.lang.String[] r6 = r6.list()
            if (r6 == 0) goto L_0x0042
            int r6 = r6.length
            java.lang.String[] r7 = f5936a
            int r7 = r7.length
            if (r6 >= r7) goto L_0x0041
            goto L_0x0042
        L_0x0041:
            r5 = r4
        L_0x0042:
            if (r5 == 0) goto L_0x007b
            r4 = 0
            android.content.res.AssetManager r8 = r8.getAssets()     // Catch:{ all -> 0x0062 }
            java.lang.String r5 = "libs.zip"
            java.io.InputStream r8 = r8.open(r5)     // Catch:{ all -> 0x0062 }
            boolean r5 = a((java.io.InputStream) r8, (java.lang.String) r1)     // Catch:{ all -> 0x005b }
            if (r8 == 0) goto L_0x006c
            r8.close()     // Catch:{ all -> 0x0059 }
            goto L_0x006c
        L_0x0059:
            r8 = move-exception
            goto L_0x0064
        L_0x005b:
            r4 = move-exception
            if (r8 == 0) goto L_0x0061
            r8.close()     // Catch:{ all -> 0x0062 }
        L_0x0061:
            throw r4     // Catch:{ all -> 0x0062 }
        L_0x0062:
            r8 = move-exception
            r5 = 0
        L_0x0064:
            if (r4 == 0) goto L_0x0067
            r8 = r4
        L_0x0067:
            throw r8     // Catch:{ Throwable -> 0x0068 }
        L_0x0068:
            r8 = move-exception
            r8.printStackTrace()
        L_0x006c:
            if (r5 == 0) goto L_0x007b
            android.content.SharedPreferences$Editor r8 = r3.edit()
            java.lang.String r2 = "app.version"
            android.content.SharedPreferences$Editor r8 = r8.putInt(r2, r0)
            r8.commit()
        L_0x007b:
            r8 = -1
            a((java.lang.String) r1, (int) r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.icontrol.dev.LibLoader.b(android.content.Context):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:8|9|(3:11|12|13)(4:14|15|(2:17|53)(7:18|19|(3:20|21|(1:56)(2:26|27))|23|24|25|54)|52)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0018 */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0018 A[LOOP:0: B:8:0x0018->B:52:0x0018, LOOP_START, SYNTHETIC, Splitter:B:8:0x0018] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean a(java.io.InputStream r9, java.lang.String r10) {
        /*
            r0 = 0
            if (r9 == 0) goto L_0x0080
            if (r10 == 0) goto L_0x0080
            int r1 = r10.length()
            if (r1 != 0) goto L_0x000d
            goto L_0x0080
        L_0x000d:
            r1 = 16384(0x4000, float:2.2959E-41)
            byte[] r2 = new byte[r1]
            r3 = 1
            r4 = 0
            java.util.zip.ZipInputStream r5 = new java.util.zip.ZipInputStream     // Catch:{ all -> 0x007a }
            r5.<init>(r9)     // Catch:{ all -> 0x007a }
        L_0x0018:
            java.util.zip.ZipEntry r9 = r5.getNextEntry()     // Catch:{ all -> 0x0074 }
            if (r9 != 0) goto L_0x0023
            r5.close()     // Catch:{ all -> 0x007a }
            r0 = 1
            goto L_0x007f
        L_0x0023:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0074 }
            java.lang.String r7 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x0074 }
            r6.<init>(r7)     // Catch:{ all -> 0x0074 }
            r7 = 47
            r6.append(r7)     // Catch:{ all -> 0x0074 }
            java.lang.String r9 = r9.getName()     // Catch:{ all -> 0x0074 }
            r6.append(r9)     // Catch:{ all -> 0x0074 }
            java.lang.String r9 = r6.toString()     // Catch:{ all -> 0x0074 }
            java.lang.String r6 = "/"
            boolean r6 = r9.endsWith(r6)     // Catch:{ all -> 0x0074 }
            if (r6 == 0) goto L_0x0048
            a((java.lang.String) r9)     // Catch:{ all -> 0x0074 }
            goto L_0x0018
        L_0x0048:
            java.io.BufferedOutputStream r6 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x006e }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ all -> 0x006e }
            r7.<init>(r9)     // Catch:{ all -> 0x006e }
            r6.<init>(r7, r1)     // Catch:{ all -> 0x006e }
        L_0x0052:
            int r9 = r5.read(r2, r0, r1)     // Catch:{ all -> 0x0064 }
            r7 = -1
            if (r9 != r7) goto L_0x0060
            r6.flush()     // Catch:{ all -> 0x0064 }
            r6.close()     // Catch:{ all -> 0x006e }
            goto L_0x0018
        L_0x0060:
            r6.write(r2, r0, r9)     // Catch:{ all -> 0x0064 }
            goto L_0x0052
        L_0x0064:
            r9 = move-exception
            r6.close()     // Catch:{ all -> 0x0069 }
            throw r9     // Catch:{ all -> 0x0069 }
        L_0x0069:
            r6 = move-exception
            r8 = r6
            r6 = r9
            r9 = r8
            goto L_0x0070
        L_0x006e:
            r9 = move-exception
            r6 = r4
        L_0x0070:
            if (r6 == 0) goto L_0x0073
            r9 = r6
        L_0x0073:
            throw r9     // Catch:{ Throwable -> 0x0018 }
        L_0x0074:
            r9 = move-exception
            r4 = r9
            r5.close()     // Catch:{ all -> 0x007a }
            throw r4     // Catch:{ all -> 0x007a }
        L_0x007a:
            r9 = move-exception
            if (r4 == 0) goto L_0x007e
            r9 = r4
        L_0x007e:
            throw r9     // Catch:{ Throwable -> 0x007f }
        L_0x007f:
            return r0
        L_0x0080:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.icontrol.dev.LibLoader.a(java.io.InputStream, java.lang.String):boolean");
    }
}
