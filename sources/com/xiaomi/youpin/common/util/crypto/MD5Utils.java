package com.xiaomi.youpin.common.util.crypto;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD5Utils {
    private static String a(byte b) {
        int i = (b & Byte.MAX_VALUE) + (b < 0 ? (byte) 128 : 0);
        StringBuilder sb = new StringBuilder();
        sb.append(i < 16 ? "0" : "");
        sb.append(Integer.toHexString(i).toLowerCase());
        return sb.toString();
    }

    public static String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            StringBuilder sb = new StringBuilder();
            instance.update(str.getBytes(), 0, str.length());
            for (byte a2 : instance.digest()) {
                sb.append(a(a2));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public static byte[] b(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes(), 0, str.length());
            return Arrays.copyOfRange(instance.digest(), 6, 10);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public static byte[] c(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes(), 0, str.length());
            byte[] digest = instance.digest();
            int length = digest.length;
            if (length < 12) {
                return new byte[0];
            }
            int i = length / 2;
            return Arrays.copyOfRange(digest, i - 6, i + 6);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public static String d(String str) {
        String a2 = a(str);
        if (TextUtils.isEmpty(a2)) {
            return "";
        }
        return a2.subSequence(8, 24).toString();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: java.security.DigestInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v9, resolved type: java.security.DigestInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v12, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v14, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v16, resolved type: java.security.DigestInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v18, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v20, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v22, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v24, resolved type: java.security.DigestInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v25, resolved type: java.security.DigestInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v26, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v27, resolved type: java.security.DigestInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v28, resolved type: java.security.DigestInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v29, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v30, resolved type: java.security.DigestInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v31, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v32, resolved type: java.security.DigestInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v33, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v34, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v35, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v36, resolved type: java.io.File} */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:24|25|51|52|53|54|55) */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:30|31|48|38|39|40|41|60) */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        return "";
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0040 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x006d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x0088 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:44:0x0075=Splitter:B:44:0x0075, B:36:0x0067=Splitter:B:36:0x0067} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.io.File r7) {
        /*
            r0 = 0
            java.lang.String r1 = "MD5"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch:{ NoSuchAlgorithmException -> 0x0079, FileNotFoundException -> 0x0071, IOException -> 0x0063, all -> 0x005e }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ NoSuchAlgorithmException -> 0x0079, FileNotFoundException -> 0x0071, IOException -> 0x0063, all -> 0x005e }
            r2.<init>(r7)     // Catch:{ NoSuchAlgorithmException -> 0x0079, FileNotFoundException -> 0x0071, IOException -> 0x0063, all -> 0x005e }
            java.security.DigestInputStream r7 = new java.security.DigestInputStream     // Catch:{ NoSuchAlgorithmException -> 0x0059, FileNotFoundException -> 0x0054, IOException -> 0x004f, all -> 0x004a }
            r7.<init>(r2, r1)     // Catch:{ NoSuchAlgorithmException -> 0x0059, FileNotFoundException -> 0x0054, IOException -> 0x004f, all -> 0x004a }
            r0 = 262144(0x40000, float:3.67342E-40)
            byte[] r0 = new byte[r0]     // Catch:{ NoSuchAlgorithmException -> 0x0048, FileNotFoundException -> 0x0046, IOException -> 0x0044 }
        L_0x0015:
            int r1 = r7.read(r0)     // Catch:{ NoSuchAlgorithmException -> 0x0048, FileNotFoundException -> 0x0046, IOException -> 0x0044 }
            if (r1 <= 0) goto L_0x001c
            goto L_0x0015
        L_0x001c:
            java.security.MessageDigest r0 = r7.getMessageDigest()     // Catch:{ NoSuchAlgorithmException -> 0x0048, FileNotFoundException -> 0x0046, IOException -> 0x0044 }
            byte[] r0 = r0.digest()     // Catch:{ NoSuchAlgorithmException -> 0x0048, FileNotFoundException -> 0x0046, IOException -> 0x0044 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NoSuchAlgorithmException -> 0x0048, FileNotFoundException -> 0x0046, IOException -> 0x0044 }
            r1.<init>()     // Catch:{ NoSuchAlgorithmException -> 0x0048, FileNotFoundException -> 0x0046, IOException -> 0x0044 }
            int r3 = r0.length     // Catch:{ NoSuchAlgorithmException -> 0x0048, FileNotFoundException -> 0x0046, IOException -> 0x0044 }
            r4 = 0
        L_0x002b:
            if (r4 >= r3) goto L_0x0039
            byte r5 = r0[r4]     // Catch:{ NoSuchAlgorithmException -> 0x0048, FileNotFoundException -> 0x0046, IOException -> 0x0044 }
            java.lang.String r5 = a((byte) r5)     // Catch:{ NoSuchAlgorithmException -> 0x0048, FileNotFoundException -> 0x0046, IOException -> 0x0044 }
            r1.append(r5)     // Catch:{ NoSuchAlgorithmException -> 0x0048, FileNotFoundException -> 0x0046, IOException -> 0x0044 }
            int r4 = r4 + 1
            goto L_0x002b
        L_0x0039:
            java.lang.String r0 = r1.toString()     // Catch:{ NoSuchAlgorithmException -> 0x0048, FileNotFoundException -> 0x0046, IOException -> 0x0044 }
            r7.close()     // Catch:{ Exception -> 0x0040 }
        L_0x0040:
            r2.close()     // Catch:{ Exception -> 0x0043 }
        L_0x0043:
            return r0
        L_0x0044:
            r0 = move-exception
            goto L_0x0067
        L_0x0046:
            r0 = move-exception
            goto L_0x0075
        L_0x0048:
            r0 = move-exception
            goto L_0x007d
        L_0x004a:
            r7 = move-exception
            r6 = r0
            r0 = r7
            r7 = r6
            goto L_0x0085
        L_0x004f:
            r7 = move-exception
            r6 = r0
            r0 = r7
            r7 = r6
            goto L_0x0067
        L_0x0054:
            r7 = move-exception
            r6 = r0
            r0 = r7
            r7 = r6
            goto L_0x0075
        L_0x0059:
            r7 = move-exception
            r6 = r0
            r0 = r7
            r7 = r6
            goto L_0x007d
        L_0x005e:
            r7 = move-exception
            r2 = r0
            r0 = r7
            r7 = r2
            goto L_0x0085
        L_0x0063:
            r7 = move-exception
            r2 = r0
            r0 = r7
            r7 = r2
        L_0x0067:
            r0.printStackTrace()     // Catch:{ all -> 0x0084 }
        L_0x006a:
            r7.close()     // Catch:{ Exception -> 0x006d }
        L_0x006d:
            r2.close()     // Catch:{ Exception -> 0x0081 }
            goto L_0x0081
        L_0x0071:
            r7 = move-exception
            r2 = r0
            r0 = r7
            r7 = r2
        L_0x0075:
            r0.printStackTrace()     // Catch:{ all -> 0x0084 }
            goto L_0x006a
        L_0x0079:
            r7 = move-exception
            r2 = r0
            r0 = r7
            r7 = r2
        L_0x007d:
            r0.printStackTrace()     // Catch:{ all -> 0x0084 }
            goto L_0x006a
        L_0x0081:
            java.lang.String r7 = ""
            return r7
        L_0x0084:
            r0 = move-exception
        L_0x0085:
            r7.close()     // Catch:{ Exception -> 0x0088 }
        L_0x0088:
            r2.close()     // Catch:{ Exception -> 0x008b }
        L_0x008b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.crypto.MD5Utils.a(java.io.File):java.lang.String");
    }

    public static String e(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(f(str));
            return String.format("%1$032X", new Object[]{new BigInteger(1, instance.digest())});
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] f(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }
}
