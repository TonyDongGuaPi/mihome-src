package com.tencent.tinker.loader.shareutil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import com.tencent.tinker.loader.TinkerRuntimeException;
import java.io.File;
import java.security.cert.Certificate;
import java.util.HashMap;

public class ShareSecurityCheck {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1361a = "Tinker.SecurityCheck";
    private static String b;
    private final Context c;
    private final HashMap<String, String> d = new HashMap<>();
    private final HashMap<String, String> e = new HashMap<>();

    public ShareSecurityCheck(Context context) {
        this.c = context;
        if (b == null) {
            a(this.c);
        }
    }

    public HashMap<String, String> a() {
        return this.d;
    }

    public HashMap<String, String> b() {
        String[] split;
        if (!this.e.isEmpty()) {
            return this.e;
        }
        String str = this.d.get(ShareConstants.m);
        if (str == null) {
            return null;
        }
        for (String str2 : str.split("\n")) {
            if (str2 != null && str2.length() > 0 && !str2.startsWith("#") && (split = str2.split("=", 2)) != null && split.length >= 2) {
                this.e.put(split[0].trim(), split[1].trim());
            }
        }
        return this.e;
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0097 A[SYNTHETIC, Splitter:B:43:0x0097] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.io.File r11) {
        /*
            r10 = this;
            boolean r0 = com.tencent.tinker.loader.shareutil.SharePatchFileUtil.a((java.io.File) r11)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            r0 = 0
            r2 = 1
            java.util.jar.JarFile r3 = new java.util.jar.JarFile     // Catch:{ Exception -> 0x0071, all -> 0x006d }
            r3.<init>(r11)     // Catch:{ Exception -> 0x0071, all -> 0x006d }
            java.util.Enumeration r0 = r3.entries()     // Catch:{ Exception -> 0x006b }
        L_0x0013:
            boolean r4 = r0.hasMoreElements()     // Catch:{ Exception -> 0x006b }
            if (r4 == 0) goto L_0x005c
            java.lang.Object r4 = r0.nextElement()     // Catch:{ Exception -> 0x006b }
            java.util.jar.JarEntry r4 = (java.util.jar.JarEntry) r4     // Catch:{ Exception -> 0x006b }
            if (r4 != 0) goto L_0x0022
            goto L_0x0013
        L_0x0022:
            java.lang.String r5 = r4.getName()     // Catch:{ Exception -> 0x006b }
            java.lang.String r6 = "META-INF/"
            boolean r6 = r5.startsWith(r6)     // Catch:{ Exception -> 0x006b }
            if (r6 == 0) goto L_0x002f
            goto L_0x0013
        L_0x002f:
            java.lang.String r6 = "meta.txt"
            boolean r6 = r5.endsWith(r6)     // Catch:{ Exception -> 0x006b }
            if (r6 != 0) goto L_0x0038
            goto L_0x0013
        L_0x0038:
            java.util.HashMap<java.lang.String, java.lang.String> r6 = r10.d     // Catch:{ Exception -> 0x006b }
            java.lang.String r7 = com.tencent.tinker.loader.shareutil.SharePatchFileUtil.a((java.util.jar.JarFile) r3, (java.util.jar.JarEntry) r4)     // Catch:{ Exception -> 0x006b }
            r6.put(r5, r7)     // Catch:{ Exception -> 0x006b }
            java.security.cert.Certificate[] r4 = r4.getCertificates()     // Catch:{ Exception -> 0x006b }
            if (r4 == 0) goto L_0x004d
            boolean r4 = r10.a(r11, r4)     // Catch:{ Exception -> 0x006b }
            if (r4 != 0) goto L_0x0013
        L_0x004d:
            r3.close()     // Catch:{ IOException -> 0x0051 }
            goto L_0x005b
        L_0x0051:
            r0 = move-exception
            java.lang.String r2 = "Tinker.SecurityCheck"
            java.lang.String r11 = r11.getAbsolutePath()
            android.util.Log.e(r2, r11, r0)
        L_0x005b:
            return r1
        L_0x005c:
            r3.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x006a
        L_0x0060:
            r0 = move-exception
            java.lang.String r1 = "Tinker.SecurityCheck"
            java.lang.String r11 = r11.getAbsolutePath()
            android.util.Log.e(r1, r11, r0)
        L_0x006a:
            return r2
        L_0x006b:
            r0 = move-exception
            goto L_0x0075
        L_0x006d:
            r1 = move-exception
            r3 = r0
            r0 = r1
            goto L_0x0095
        L_0x0071:
            r3 = move-exception
            r9 = r3
            r3 = r0
            r0 = r9
        L_0x0075:
            com.tencent.tinker.loader.TinkerRuntimeException r4 = new com.tencent.tinker.loader.TinkerRuntimeException     // Catch:{ all -> 0x0094 }
            java.lang.String r5 = "ShareSecurityCheck file %s, size %d verifyPatchMetaSignature fail"
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ all -> 0x0094 }
            java.lang.String r7 = r11.getAbsolutePath()     // Catch:{ all -> 0x0094 }
            r6[r1] = r7     // Catch:{ all -> 0x0094 }
            long r7 = r11.length()     // Catch:{ all -> 0x0094 }
            java.lang.Long r1 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x0094 }
            r6[r2] = r1     // Catch:{ all -> 0x0094 }
            java.lang.String r1 = java.lang.String.format(r5, r6)     // Catch:{ all -> 0x0094 }
            r4.<init>(r1, r0)     // Catch:{ all -> 0x0094 }
            throw r4     // Catch:{ all -> 0x0094 }
        L_0x0094:
            r0 = move-exception
        L_0x0095:
            if (r3 == 0) goto L_0x00a5
            r3.close()     // Catch:{ IOException -> 0x009b }
            goto L_0x00a5
        L_0x009b:
            r1 = move-exception
            java.lang.String r11 = r11.getAbsolutePath()
            java.lang.String r2 = "Tinker.SecurityCheck"
            android.util.Log.e(r2, r11, r1)
        L_0x00a5:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.shareutil.ShareSecurityCheck.a(java.io.File):boolean");
    }

    private boolean a(File file, Certificate[] certificateArr) {
        if (certificateArr.length <= 0) {
            return false;
        }
        int length = certificateArr.length - 1;
        while (length >= 0) {
            try {
                if (b.equals(SharePatchFileUtil.a(certificateArr[length].getEncoded()))) {
                    return true;
                }
                length--;
            } catch (Exception e2) {
                Log.e(f1361a, file.getAbsolutePath(), e2);
            }
        }
        return false;
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    private void a(Context context) {
        try {
            b = SharePatchFileUtil.a(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray());
            if (b != null) {
                SharePatchFileUtil.a((Object) null);
                return;
            }
            throw new TinkerRuntimeException("get public key md5 is null");
        } catch (Exception e2) {
            throw new TinkerRuntimeException("ShareSecurityCheck init public key fail", e2);
        } catch (Throwable th) {
            SharePatchFileUtil.a((Object) null);
            throw th;
        }
    }
}
