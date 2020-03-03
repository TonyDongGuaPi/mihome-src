package com.tencent.smtt.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import com.tencent.smtt.sdk.TbsExtensionFunctionManager;
import java.io.File;

public class a {
    public static int a(Context context, File file) {
        return a(context, file, Build.VERSION.SDK_INT >= 23 ? !TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.DISABLE_GET_APK_VERSION_SWITCH_FILE_NAME) : false);
    }

    public static int a(Context context, File file, boolean z) {
        int b;
        if (file != null) {
            try {
                if (file.exists()) {
                    boolean z2 = (Build.VERSION.SDK_INT == 23 || Build.VERSION.SDK_INT == 25) && Build.MANUFACTURER.toLowerCase().contains("mi");
                    if ((z || z2) && (b = b(file)) > 0) {
                        return b;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (file == null || !file.exists()) {
            return 0;
        }
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 1);
            if (packageArchiveInfo != null) {
                return packageArchiveInfo.versionCode;
            }
            return 0;
        } catch (Throwable th2) {
            th2.printStackTrace();
            return -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x005f A[SYNTHETIC, Splitter:B:26:0x005f] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006b A[SYNTHETIC, Splitter:B:33:0x006b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.io.File r10) {
        /*
            r0 = 16
            char[] r1 = new char[r0]
            r1 = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102} // fill-array
            r2 = 32
            char[] r2 = new char[r2]
            r3 = 0
            java.lang.String r4 = "MD5"
            java.security.MessageDigest r4 = java.security.MessageDigest.getInstance(r4)     // Catch:{ Exception -> 0x0058, all -> 0x0055 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0058, all -> 0x0055 }
            r5.<init>(r10)     // Catch:{ Exception -> 0x0058, all -> 0x0055 }
            r10 = 8192(0x2000, float:1.14794E-41)
            byte[] r10 = new byte[r10]     // Catch:{ Exception -> 0x0053 }
        L_0x001b:
            int r6 = r5.read(r10)     // Catch:{ Exception -> 0x0053 }
            r7 = -1
            r8 = 0
            if (r6 == r7) goto L_0x0027
            r4.update(r10, r8, r6)     // Catch:{ Exception -> 0x0053 }
            goto L_0x001b
        L_0x0027:
            byte[] r10 = r4.digest()     // Catch:{ Exception -> 0x0053 }
            r4 = 0
        L_0x002c:
            if (r8 >= r0) goto L_0x0045
            byte r6 = r10[r8]     // Catch:{ Exception -> 0x0053 }
            int r7 = r4 + 1
            int r9 = r6 >>> 4
            r9 = r9 & 15
            char r9 = r1[r9]     // Catch:{ Exception -> 0x0053 }
            r2[r4] = r9     // Catch:{ Exception -> 0x0053 }
            int r4 = r7 + 1
            r6 = r6 & 15
            char r6 = r1[r6]     // Catch:{ Exception -> 0x0053 }
            r2[r7] = r6     // Catch:{ Exception -> 0x0053 }
            int r8 = r8 + 1
            goto L_0x002c
        L_0x0045:
            java.lang.String r10 = new java.lang.String     // Catch:{ Exception -> 0x0053 }
            r10.<init>(r2)     // Catch:{ Exception -> 0x0053 }
            r5.close()     // Catch:{ IOException -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0052:
            return r10
        L_0x0053:
            r10 = move-exception
            goto L_0x005a
        L_0x0055:
            r10 = move-exception
            r5 = r3
            goto L_0x0069
        L_0x0058:
            r10 = move-exception
            r5 = r3
        L_0x005a:
            r10.printStackTrace()     // Catch:{ all -> 0x0068 }
            if (r5 == 0) goto L_0x0067
            r5.close()     // Catch:{ IOException -> 0x0063 }
            goto L_0x0067
        L_0x0063:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0067:
            return r3
        L_0x0068:
            r10 = move-exception
        L_0x0069:
            if (r5 == 0) goto L_0x0073
            r5.close()     // Catch:{ IOException -> 0x006f }
            goto L_0x0073
        L_0x006f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0073:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.a.a(java.io.File):java.lang.String");
    }

    public static boolean a(Context context, File file, long j, int i) {
        if (file != null && file.exists()) {
            if (j > 0 && j != file.length()) {
                return false;
            }
            try {
                return i == a(context, file) && "3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a".equals(b.a(context, file));
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0063 A[SYNTHETIC, Splitter:B:35:0x0063] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x006e A[SYNTHETIC, Splitter:B:41:0x006e] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0073 A[Catch:{ Exception -> 0x0076 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int b(java.io.File r5) {
        /*
            r0 = 0
            java.util.jar.JarFile r1 = new java.util.jar.JarFile     // Catch:{ Exception -> 0x005c, all -> 0x0059 }
            r1.<init>(r5)     // Catch:{ Exception -> 0x005c, all -> 0x0059 }
            java.lang.String r5 = "assets/webkit/tbs.conf"
            java.util.jar.JarEntry r5 = r1.getJarEntry(r5)     // Catch:{ Exception -> 0x0057 }
            java.io.InputStream r5 = r1.getInputStream(r5)     // Catch:{ Exception -> 0x0057 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0057 }
            r2.<init>(r5)     // Catch:{ Exception -> 0x0057 }
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0057 }
            r5.<init>(r2)     // Catch:{ Exception -> 0x0057 }
        L_0x001a:
            java.lang.String r0 = r5.readLine()     // Catch:{ Exception -> 0x0052, all -> 0x004d }
            if (r0 == 0) goto L_0x0046
            java.lang.String r2 = "tbs_core_version"
            boolean r2 = r0.contains(r2)     // Catch:{ Exception -> 0x0052, all -> 0x004d }
            if (r2 == 0) goto L_0x001a
            java.lang.String r2 = "="
            java.lang.String[] r0 = r0.split(r2)     // Catch:{ Exception -> 0x0052, all -> 0x004d }
            if (r0 == 0) goto L_0x001a
            int r2 = r0.length     // Catch:{ Exception -> 0x0052, all -> 0x004d }
            r3 = 2
            if (r2 != r3) goto L_0x001a
            r2 = 1
            r0 = r0[r2]     // Catch:{ Exception -> 0x0052, all -> 0x004d }
            java.lang.String r0 = r0.trim()     // Catch:{ Exception -> 0x0052, all -> 0x004d }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Exception -> 0x0052, all -> 0x004d }
            r5.close()     // Catch:{ Exception -> 0x0045 }
            r1.close()     // Catch:{ Exception -> 0x0045 }
        L_0x0045:
            return r0
        L_0x0046:
            r5.close()     // Catch:{ Exception -> 0x0069 }
        L_0x0049:
            r1.close()     // Catch:{ Exception -> 0x0069 }
            goto L_0x0069
        L_0x004d:
            r0 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
            goto L_0x006c
        L_0x0052:
            r0 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
            goto L_0x005e
        L_0x0057:
            r5 = move-exception
            goto L_0x005e
        L_0x0059:
            r5 = move-exception
            r1 = r0
            goto L_0x006c
        L_0x005c:
            r5 = move-exception
            r1 = r0
        L_0x005e:
            r5.printStackTrace()     // Catch:{ all -> 0x006b }
            if (r0 == 0) goto L_0x0066
            r0.close()     // Catch:{ Exception -> 0x0069 }
        L_0x0066:
            if (r1 == 0) goto L_0x0069
            goto L_0x0049
        L_0x0069:
            r5 = -1
            return r5
        L_0x006b:
            r5 = move-exception
        L_0x006c:
            if (r0 == 0) goto L_0x0071
            r0.close()     // Catch:{ Exception -> 0x0076 }
        L_0x0071:
            if (r1 == 0) goto L_0x0076
            r1.close()     // Catch:{ Exception -> 0x0076 }
        L_0x0076:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.a.b(java.io.File):int");
    }
}
