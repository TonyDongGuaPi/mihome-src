package com.xiaomi.smarthome.miio.camera.cloudstorage.utils;

import android.os.Environment;
import android.text.TextUtils;
import com.unionpay.tsmservice.mi.data.Constant;
import java.io.File;

public class CloudVideoFileUtils {
    public static final File DIR_LOCAL = new File(DIR_ROOT, "local");
    public static final File DIR_ROOT = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), Constant.DEVICE_XIAOMI);

    public static String generateFilepath(boolean z, String str) {
        return generateFilepath(System.currentTimeMillis(), z, str);
    }

    public static String generateFilepath(long j, boolean z, String str) {
        String externalDir = getExternalDir(str);
        if (TextUtils.isEmpty(externalDir)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(externalDir);
        sb.append(File.separator);
        sb.append(FileNamer.getInstance().generateName(j, z));
        sb.append(z ? ".mp4" : ".jpg");
        return sb.toString();
    }

    public static String getExternalDir(String str) {
        String str2 = DIR_LOCAL + File.separator + str;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0053 A[SYNTHETIC, Splitter:B:31:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x005b A[Catch:{ IOException -> 0x0057 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0066 A[SYNTHETIC, Splitter:B:40:0x0066] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x006e A[Catch:{ IOException -> 0x006a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] file2Bytes(java.lang.String r6) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            r1 = 0
            if (r0 != 0) goto L_0x0076
            java.io.File r0 = new java.io.File
            r0.<init>(r6)
            boolean r6 = r0.exists()
            if (r6 == 0) goto L_0x0076
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ IOException -> 0x004b, all -> 0x0046 }
            r6.<init>(r0)     // Catch:{ IOException -> 0x004b, all -> 0x0046 }
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0043, all -> 0x003e }
            r2 = 1024(0x400, float:1.435E-42)
            r0.<init>(r2)     // Catch:{ IOException -> 0x0043, all -> 0x003e }
            byte[] r2 = new byte[r2]     // Catch:{ IOException -> 0x003c }
        L_0x0020:
            int r3 = r6.read(r2)     // Catch:{ IOException -> 0x003c }
            r4 = -1
            if (r3 == r4) goto L_0x002c
            r4 = 0
            r0.write(r2, r4, r3)     // Catch:{ IOException -> 0x003c }
            goto L_0x0020
        L_0x002c:
            byte[] r2 = r0.toByteArray()     // Catch:{ IOException -> 0x003c }
            r6.close()     // Catch:{ IOException -> 0x0037 }
            r0.close()     // Catch:{ IOException -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r6 = move-exception
            r6.printStackTrace()
        L_0x003b:
            return r2
        L_0x003c:
            r2 = move-exception
            goto L_0x004e
        L_0x003e:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0064
        L_0x0043:
            r2 = move-exception
            r0 = r1
            goto L_0x004e
        L_0x0046:
            r6 = move-exception
            r0 = r1
            r1 = r6
            r6 = r0
            goto L_0x0064
        L_0x004b:
            r2 = move-exception
            r6 = r1
            r0 = r6
        L_0x004e:
            r2.printStackTrace()     // Catch:{ all -> 0x0063 }
            if (r6 == 0) goto L_0x0059
            r6.close()     // Catch:{ IOException -> 0x0057 }
            goto L_0x0059
        L_0x0057:
            r6 = move-exception
            goto L_0x005f
        L_0x0059:
            if (r0 == 0) goto L_0x0076
            r0.close()     // Catch:{ IOException -> 0x0057 }
            goto L_0x0076
        L_0x005f:
            r6.printStackTrace()
            goto L_0x0076
        L_0x0063:
            r1 = move-exception
        L_0x0064:
            if (r6 == 0) goto L_0x006c
            r6.close()     // Catch:{ IOException -> 0x006a }
            goto L_0x006c
        L_0x006a:
            r6 = move-exception
            goto L_0x0072
        L_0x006c:
            if (r0 == 0) goto L_0x0075
            r0.close()     // Catch:{ IOException -> 0x006a }
            goto L_0x0075
        L_0x0072:
            r6.printStackTrace()
        L_0x0075:
            throw r1
        L_0x0076:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoFileUtils.file2Bytes(java.lang.String):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0037 A[SYNTHETIC, Splitter:B:25:0x0037] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0040 A[SYNTHETIC, Splitter:B:30:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0045 A[Catch:{ IOException -> 0x0048 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void bytes2File(byte[] r2, java.lang.String r3) {
        /*
            if (r2 == 0) goto L_0x0049
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L_0x0049
            java.io.File r0 = new java.io.File
            r0.<init>(r3)
            r3 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0030, all -> 0x002d }
            r1.<init>(r0)     // Catch:{ IOException -> 0x0030, all -> 0x002d }
            java.io.BufferedOutputStream r0 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x002b }
            r0.<init>(r1)     // Catch:{ IOException -> 0x002b }
            r0.write(r2)     // Catch:{ IOException -> 0x0028, all -> 0x0025 }
            r0.flush()     // Catch:{ IOException -> 0x0028, all -> 0x0025 }
            r0.close()     // Catch:{ IOException -> 0x0049 }
        L_0x0021:
            r1.close()     // Catch:{ IOException -> 0x0049 }
            goto L_0x0049
        L_0x0025:
            r2 = move-exception
            r3 = r0
            goto L_0x003e
        L_0x0028:
            r2 = move-exception
            r3 = r0
            goto L_0x0032
        L_0x002b:
            r2 = move-exception
            goto L_0x0032
        L_0x002d:
            r2 = move-exception
            r1 = r3
            goto L_0x003e
        L_0x0030:
            r2 = move-exception
            r1 = r3
        L_0x0032:
            r2.printStackTrace()     // Catch:{ all -> 0x003d }
            if (r3 == 0) goto L_0x003a
            r3.close()     // Catch:{ IOException -> 0x0049 }
        L_0x003a:
            if (r1 == 0) goto L_0x0049
            goto L_0x0021
        L_0x003d:
            r2 = move-exception
        L_0x003e:
            if (r3 == 0) goto L_0x0043
            r3.close()     // Catch:{ IOException -> 0x0048 }
        L_0x0043:
            if (r1 == 0) goto L_0x0048
            r1.close()     // Catch:{ IOException -> 0x0048 }
        L_0x0048:
            throw r2
        L_0x0049:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoFileUtils.bytes2File(byte[], java.lang.String):void");
    }

    public static void RecursionDeleteFile(File file) {
        if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                file.delete();
                return;
            }
            for (File RecursionDeleteFile : listFiles) {
                RecursionDeleteFile(RecursionDeleteFile);
            }
            file.delete();
        }
    }
}
