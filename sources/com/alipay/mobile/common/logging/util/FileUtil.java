package com.alipay.mobile.common.logging.util;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import java.io.File;
import java.io.IOException;

public class FileUtil {
    public static boolean isCanUseSdCard() {
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Exception e) {
            LoggerFactory.getTraceLogger().error("", (Throwable) e);
            return false;
        }
    }

    public static String getSDPath() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return Environment.getExternalStorageDirectory().getPath();
        }
        return null;
    }

    public static boolean isSDcardAvailableSpace(long j) {
        if (!isCanUseSdCard()) {
            return false;
        }
        StatFs statFs = new StatFs(new File(Environment.getExternalStorageDirectory().getPath()).getPath());
        if (j < ((long) statFs.getBlockSize()) * (((long) statFs.getAvailableBlocks()) - 4)) {
            return true;
        }
        return false;
    }

    public static void deleteFileNotDir(File file) {
        if (file != null) {
            try {
                if (file.exists() && file.isFile()) {
                    file.delete();
                }
            } catch (Throwable th) {
                Log.e("FileUtil", "deleteFileNotDir: " + file.getAbsolutePath(), th);
            }
        }
    }

    public static void deleteFileByPath(String str) {
        if (!TextUtils.isEmpty(str)) {
            deleteFileNotDir(new File(str));
        }
    }

    public static String getTraceFile() {
        Object obj = null;
        try {
            obj = Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class}).invoke((Object) null, new Object[]{"dalvik.vm.stack-trace-file"});
        } catch (Exception e) {
            LoggerFactory.getTraceLogger().error("", (Throwable) e);
        }
        return obj != null ? obj.toString() : "/data/anr/traces.txt";
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x005c A[SYNTHETIC, Splitter:B:35:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0063 A[SYNTHETIC, Splitter:B:41:0x0063] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String readAssetFile(android.content.Context r6, java.lang.String r7) {
        /*
            r0 = 0
            if (r6 == 0) goto L_0x0067
            boolean r1 = android.text.TextUtils.isEmpty(r7)
            if (r1 == 0) goto L_0x000a
            goto L_0x0067
        L_0x000a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            android.content.res.Resources r6 = r6.getResources()     // Catch:{ Throwable -> 0x004f, all -> 0x004c }
            if (r6 != 0) goto L_0x0016
            return r0
        L_0x0016:
            android.content.res.AssetManager r6 = r6.getAssets()     // Catch:{ Throwable -> 0x004f, all -> 0x004c }
            java.io.InputStream r6 = r6.open(r7)     // Catch:{ Throwable -> 0x004f, all -> 0x004c }
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0047, all -> 0x0042 }
            r7.<init>(r6)     // Catch:{ Throwable -> 0x0047, all -> 0x0042 }
            r6 = 8192(0x2000, float:1.14794E-41)
            byte[] r6 = new byte[r6]     // Catch:{ Throwable -> 0x0040 }
        L_0x0027:
            int r2 = r7.read(r6)     // Catch:{ Throwable -> 0x0040 }
            r3 = -1
            if (r2 == r3) goto L_0x0038
            java.lang.String r3 = new java.lang.String     // Catch:{ Throwable -> 0x0040 }
            r4 = 0
            r3.<init>(r6, r4, r2)     // Catch:{ Throwable -> 0x0040 }
            r1.append(r3)     // Catch:{ Throwable -> 0x0040 }
            goto L_0x0027
        L_0x0038:
            java.lang.String r6 = r1.toString()     // Catch:{ Throwable -> 0x0040 }
            r7.close()     // Catch:{ Throwable -> 0x003f }
        L_0x003f:
            return r6
        L_0x0040:
            r6 = move-exception
            goto L_0x0051
        L_0x0042:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
            goto L_0x0061
        L_0x0047:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
            goto L_0x0051
        L_0x004c:
            r6 = move-exception
            r7 = r0
            goto L_0x0061
        L_0x004f:
            r6 = move-exception
            r7 = r0
        L_0x0051:
            com.alipay.mobile.common.logging.api.trace.TraceLogger r1 = com.alipay.mobile.common.logging.api.LoggerFactory.getTraceLogger()     // Catch:{ all -> 0x0060 }
            java.lang.String r2 = "readAssetFile"
            r1.error((java.lang.String) r2, (java.lang.Throwable) r6)     // Catch:{ all -> 0x0060 }
            if (r7 == 0) goto L_0x005f
            r7.close()     // Catch:{ Throwable -> 0x005f }
        L_0x005f:
            return r0
        L_0x0060:
            r6 = move-exception
        L_0x0061:
            if (r7 == 0) goto L_0x0066
            r7.close()     // Catch:{ Throwable -> 0x0066 }
        L_0x0066:
            throw r6
        L_0x0067:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.util.FileUtil.readAssetFile(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x003e A[SYNTHETIC, Splitter:B:26:0x003e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String readFile(java.io.File r5) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0035 }
            r2.<init>(r5)     // Catch:{ Throwable -> 0x0035 }
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0030, all -> 0x002d }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0030, all -> 0x002d }
            r5 = 8192(0x2000, float:1.14794E-41)
            byte[] r5 = new byte[r5]     // Catch:{ Throwable -> 0x0035 }
        L_0x0014:
            int r2 = r1.read(r5)     // Catch:{ Throwable -> 0x0035 }
            r3 = -1
            if (r2 == r3) goto L_0x0025
            java.lang.String r3 = new java.lang.String     // Catch:{ Throwable -> 0x0035 }
            r4 = 0
            r3.<init>(r5, r4, r2)     // Catch:{ Throwable -> 0x0035 }
            r0.append(r3)     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0014
        L_0x0025:
            java.lang.String r5 = r0.toString()     // Catch:{ Throwable -> 0x0035 }
            r1.close()     // Catch:{ Throwable -> 0x002c }
        L_0x002c:
            return r5
        L_0x002d:
            r5 = move-exception
            r1 = r2
            goto L_0x003c
        L_0x0030:
            r5 = move-exception
            r1 = r2
            goto L_0x0036
        L_0x0033:
            r5 = move-exception
            goto L_0x003c
        L_0x0035:
            r5 = move-exception
        L_0x0036:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0033 }
            r0.<init>(r5)     // Catch:{ all -> 0x0033 }
            throw r0     // Catch:{ all -> 0x0033 }
        L_0x003c:
            if (r1 == 0) goto L_0x0041
            r1.close()     // Catch:{ Throwable -> 0x0041 }
        L_0x0041:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.util.FileUtil.readFile(java.io.File):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0039 A[SYNTHETIC, Splitter:B:23:0x0039] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeFile(java.io.File r2, byte[] r3, boolean r4) {
        /*
            r0 = 0
            java.io.File r1 = r2.getParentFile()     // Catch:{ Throwable -> 0x0030 }
            boolean r1 = r1.exists()     // Catch:{ Throwable -> 0x0030 }
            if (r1 != 0) goto L_0x0012
            java.io.File r1 = r2.getParentFile()     // Catch:{ Throwable -> 0x0030 }
            r1.mkdirs()     // Catch:{ Throwable -> 0x0030 }
        L_0x0012:
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x0030 }
            r1.<init>(r2, r4)     // Catch:{ Throwable -> 0x0030 }
            java.io.BufferedOutputStream r0 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x002b, all -> 0x0028 }
            r2 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r1, r2)     // Catch:{ Throwable -> 0x002b, all -> 0x0028 }
            r0.write(r3)     // Catch:{ Throwable -> 0x0030 }
            r0.flush()     // Catch:{ Throwable -> 0x0030 }
            r0.close()     // Catch:{ Throwable -> 0x0027 }
        L_0x0027:
            return
        L_0x0028:
            r2 = move-exception
            r0 = r1
            goto L_0x0037
        L_0x002b:
            r2 = move-exception
            r0 = r1
            goto L_0x0031
        L_0x002e:
            r2 = move-exception
            goto L_0x0037
        L_0x0030:
            r2 = move-exception
        L_0x0031:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x002e }
            r3.<init>(r2)     // Catch:{ all -> 0x002e }
            throw r3     // Catch:{ all -> 0x002e }
        L_0x0037:
            if (r0 == 0) goto L_0x003c
            r0.close()     // Catch:{ Throwable -> 0x003c }
        L_0x003c:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.util.FileUtil.writeFile(java.io.File, byte[], boolean):void");
    }

    public static void writeFile(File file, String str, boolean z) {
        try {
            writeFile(file, str.getBytes(), z);
        } catch (Throwable th) {
            throw new IOException(th);
        }
    }

    public static void moveFile(File file, File file2) {
        try {
            if (!file.renameTo(file2)) {
                copyFile(file, file2);
                file.delete();
            }
            if (file.exists() || !file2.exists()) {
                throw new Exception("move file fail");
            }
        } catch (Throwable th) {
            throw new IOException(th);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: java.nio.channels.FileChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v15, resolved type: java.nio.channels.FileChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: java.nio.channels.FileChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v18, resolved type: java.nio.channels.FileChannel} */
    /* JADX WARNING: type inference failed for: r8v0, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r8v12 */
    /* JADX WARNING: type inference failed for: r8v13 */
    /* JADX WARNING: type inference failed for: r8v17 */
    /* JADX WARNING: type inference failed for: r8v19 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x002c */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0031 A[SYNTHETIC, Splitter:B:23:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x006b A[SYNTHETIC, Splitter:B:56:0x006b] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0070 A[SYNTHETIC, Splitter:B:60:0x0070] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0075 A[SYNTHETIC, Splitter:B:64:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x007a A[SYNTHETIC, Splitter:B:68:0x007a] */
    /* JADX WARNING: Removed duplicated region for block: B:74:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copyFile(java.io.File r10, java.io.File r11) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x005d, all -> 0x0057 }
            r1.<init>(r10)     // Catch:{ Throwable -> 0x005d, all -> 0x0057 }
            java.nio.channels.FileChannel r10 = r1.getChannel()     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x004a, all -> 0x0047 }
            r8.<init>(r11)     // Catch:{ Throwable -> 0x004a, all -> 0x0047 }
            java.nio.channels.FileChannel r9 = r8.getChannel()     // Catch:{ Throwable -> 0x0044, all -> 0x0041 }
            r3 = 0
            long r5 = r10.size()     // Catch:{ Throwable -> 0x003f, all -> 0x003d }
            r2 = r10
            r7 = r9
            r2.transferTo(r3, r5, r7)     // Catch:{ Throwable -> 0x003f, all -> 0x003d }
            boolean r11 = r11.exists()     // Catch:{ Throwable -> 0x003f, all -> 0x003d }
            if (r11 == 0) goto L_0x0035
            r1.close()     // Catch:{ Throwable -> 0x0027 }
        L_0x0027:
            if (r10 == 0) goto L_0x002c
            r10.close()     // Catch:{ Throwable -> 0x002c }
        L_0x002c:
            r8.close()     // Catch:{ Throwable -> 0x002f }
        L_0x002f:
            if (r9 == 0) goto L_0x0034
            r9.close()     // Catch:{ Throwable -> 0x0034 }
        L_0x0034:
            return
        L_0x0035:
            java.lang.Exception r11 = new java.lang.Exception     // Catch:{ Throwable -> 0x003f, all -> 0x003d }
            java.lang.String r0 = "copy file fail"
            r11.<init>(r0)     // Catch:{ Throwable -> 0x003f, all -> 0x003d }
            throw r11     // Catch:{ Throwable -> 0x003f, all -> 0x003d }
        L_0x003d:
            r11 = move-exception
            goto L_0x0069
        L_0x003f:
            r11 = move-exception
            goto L_0x0055
        L_0x0041:
            r11 = move-exception
            r9 = r0
            goto L_0x0069
        L_0x0044:
            r11 = move-exception
            r9 = r0
            goto L_0x0055
        L_0x0047:
            r11 = move-exception
            r8 = r0
            goto L_0x005b
        L_0x004a:
            r11 = move-exception
            r8 = r0
            goto L_0x0054
        L_0x004d:
            r11 = move-exception
            r10 = r0
            r8 = r10
            goto L_0x005b
        L_0x0051:
            r11 = move-exception
            r10 = r0
            r8 = r10
        L_0x0054:
            r9 = r8
        L_0x0055:
            r0 = r1
            goto L_0x0061
        L_0x0057:
            r11 = move-exception
            r10 = r0
            r1 = r10
            r8 = r1
        L_0x005b:
            r9 = r8
            goto L_0x0069
        L_0x005d:
            r11 = move-exception
            r10 = r0
            r8 = r10
            r9 = r8
        L_0x0061:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ all -> 0x0067 }
            r1.<init>(r11)     // Catch:{ all -> 0x0067 }
            throw r1     // Catch:{ all -> 0x0067 }
        L_0x0067:
            r11 = move-exception
            r1 = r0
        L_0x0069:
            if (r1 == 0) goto L_0x006e
            r1.close()     // Catch:{ Throwable -> 0x006e }
        L_0x006e:
            if (r10 == 0) goto L_0x0073
            r10.close()     // Catch:{ Throwable -> 0x0073 }
        L_0x0073:
            if (r8 == 0) goto L_0x0078
            r8.close()     // Catch:{ Throwable -> 0x0078 }
        L_0x0078:
            if (r9 == 0) goto L_0x007d
            r9.close()     // Catch:{ Throwable -> 0x007d }
        L_0x007d:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.util.FileUtil.copyFile(java.io.File, java.io.File):void");
    }

    public static long getFolderSize(File file) {
        if (file == null || !file.exists() || !file.isDirectory()) {
            return 0;
        }
        try {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                if (listFiles.length != 0) {
                    long j = 0;
                    for (File file2 : listFiles) {
                        if (file2 != null) {
                            if (file2.exists()) {
                                if (file2.isFile()) {
                                    j += file2.length();
                                } else {
                                    j += getFolderSize(file2);
                                }
                            }
                        }
                    }
                    return j;
                }
            }
            return 0;
        } catch (Throwable unused) {
            return 0;
        }
    }
}
