package com.xiaomi.youpin;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.utils.ByteUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class PackageUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23159a = "armeabi";
    public static final String b = "armeabi-v7a";
    public static final String c = "x86";
    public static final String d = "mips";

    static boolean a(PackageManager packageManager, String str, String str2) {
        PackageInfo packageInfo;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        try {
            packageInfo = packageManager.getPackageArchiveInfo(str2, 64);
        } catch (Exception e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo == null) {
            LogUtils.e("PackageUtils", "cannot get signature in checkApkPackageSignature");
            return false;
        } else if (str.equalsIgnoreCase(a(packageInfo.signatures))) {
            return true;
        } else {
            return false;
        }
    }

    private static String a(Signature[] signatureArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            if (signatureArr != null) {
                for (Signature byteArray : signatureArr) {
                    instance.update(byteArray.toByteArray());
                }
            }
            return ByteUtils.a(instance.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004b A[SYNTHETIC, Splitter:B:21:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String r5, java.lang.String r6) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r6)
            r0.mkdirs()
            java.lang.String r0 = "armeabi"
            r1 = 0
            java.util.zip.ZipFile r2 = new java.util.zip.ZipFile     // Catch:{ IOException -> 0x004f, all -> 0x0047 }
            r2.<init>(r5)     // Catch:{ IOException -> 0x004f, all -> 0x0047 }
            java.util.Enumeration r5 = r2.entries()     // Catch:{ IOException -> 0x0050, all -> 0x0045 }
        L_0x0014:
            boolean r1 = r5.hasMoreElements()     // Catch:{ IOException -> 0x0050, all -> 0x0045 }
            if (r1 == 0) goto L_0x0041
            java.lang.Object r1 = r5.nextElement()     // Catch:{ IOException -> 0x0050, all -> 0x0045 }
            java.util.zip.ZipEntry r1 = (java.util.zip.ZipEntry) r1     // Catch:{ IOException -> 0x0050, all -> 0x0045 }
            boolean r3 = r1.isDirectory()     // Catch:{ IOException -> 0x0050, all -> 0x0045 }
            if (r3 == 0) goto L_0x0027
            goto L_0x0014
        L_0x0027:
            java.lang.String r3 = r1.getName()     // Catch:{ IOException -> 0x0050, all -> 0x0045 }
            java.lang.String r4 = ".so"
            boolean r4 = r3.endsWith(r4)     // Catch:{ IOException -> 0x0050, all -> 0x0045 }
            if (r4 == 0) goto L_0x0014
            boolean r4 = r3.contains(r0)     // Catch:{ IOException -> 0x0050, all -> 0x0045 }
            if (r4 == 0) goto L_0x0014
            java.lang.String r3 = a((java.lang.String) r3)     // Catch:{ IOException -> 0x0050, all -> 0x0045 }
            a(r2, r1, r6, r3)     // Catch:{ IOException -> 0x0050, all -> 0x0045 }
            goto L_0x0014
        L_0x0041:
            r2.close()     // Catch:{ Exception -> 0x0053 }
            goto L_0x0053
        L_0x0045:
            r5 = move-exception
            goto L_0x0049
        L_0x0047:
            r5 = move-exception
            r2 = r1
        L_0x0049:
            if (r2 == 0) goto L_0x004e
            r2.close()     // Catch:{ Exception -> 0x004e }
        L_0x004e:
            throw r5
        L_0x004f:
            r2 = r1
        L_0x0050:
            if (r2 == 0) goto L_0x0053
            goto L_0x0041
        L_0x0053:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.PackageUtils.a(java.lang.String, java.lang.String):void");
    }

    private static String a(String str) {
        return str.substring(str.lastIndexOf("/") + 1);
    }

    private static void a(ZipFile zipFile, ZipEntry zipEntry, String str, String str2) {
        FileOutputStream fileOutputStream;
        InputStream inputStream;
        try {
            inputStream = zipFile.getInputStream(zipEntry);
            try {
                fileOutputStream = new FileOutputStream(new File(str, str2));
            } catch (IOException unused) {
            }
        } catch (IOException unused2) {
            inputStream = null;
            fileOutputStream = null;
            a(inputStream, (OutputStream) fileOutputStream);
        }
        a(inputStream, (OutputStream) fileOutputStream);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:6|7|(1:9)|10|11|14) */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0023 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0017 */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0017 A[LOOP:0: B:6:0x0017->B:9:0x001e, LOOP_START, SYNTHETIC, Splitter:B:6:0x0017] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.io.InputStream r3, java.io.OutputStream r4) {
        /*
            if (r3 == 0) goto L_0x002d
            if (r4 != 0) goto L_0x0005
            goto L_0x002d
        L_0x0005:
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream
            r0.<init>(r3)
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream
            r3.<init>(r4)
            r4 = 0
            int r1 = a((java.io.InputStream) r0)     // Catch:{ IOException -> 0x0017 }
            byte[] r1 = new byte[r1]     // Catch:{ IOException -> 0x0017 }
            r4 = r1
        L_0x0017:
            int r1 = r0.read(r4)     // Catch:{ IOException -> 0x0023 }
            r2 = -1
            if (r1 == r2) goto L_0x0023
            r2 = 0
            r3.write(r4, r2, r1)     // Catch:{ IOException -> 0x0023 }
            goto L_0x0017
        L_0x0023:
            r3.flush()     // Catch:{ IOException -> 0x002c }
            r3.close()     // Catch:{ IOException -> 0x002c }
            r0.close()     // Catch:{ IOException -> 0x002c }
        L_0x002c:
            return
        L_0x002d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.PackageUtils.a(java.io.InputStream, java.io.OutputStream):void");
    }

    private static int a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return 0;
        }
        int available = inputStream.available();
        if (available <= 0) {
            return 1024;
        }
        return available;
    }
}
