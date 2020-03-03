package com.alipay.mobile.common.logging.util;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class RSAUtil {

    /* renamed from: a  reason: collision with root package name */
    private static Cipher f971a;
    private static Cipher b;

    private static PublicKey a(String str, String str2) {
        return KeyFactory.getInstance(str).generatePublic(new X509EncodedKeySpec(Base64.decode(str2)));
    }

    private static PrivateKey b(String str, String str2) {
        return KeyFactory.getInstance(str).generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str2)));
    }

    public static String encrypt(String str, String str2) {
        try {
            return Base64.encode(encrypt(str.getBytes("UTF-8"), str2));
        } catch (UnsupportedEncodingException e) {
            Log.w("LoggingRSA", e);
            return null;
        }
    }

    public static String decrypt(String str, String str2) {
        try {
            return new String(decrypt(Base64.decode(str), str2), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.w("LoggingRSA", e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x005a A[SYNTHETIC, Splitter:B:34:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x006b A[SYNTHETIC, Splitter:B:46:0x006b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized byte[] encrypt(byte[] r6, java.lang.String r7) {
        /*
            java.lang.Class<com.alipay.mobile.common.logging.util.RSAUtil> r0 = com.alipay.mobile.common.logging.util.RSAUtil.class
            monitor-enter(r0)
            r1 = 0
            if (r7 != 0) goto L_0x0008
            monitor-exit(r0)
            return r1
        L_0x0008:
            javax.crypto.Cipher r2 = f971a     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            if (r2 != 0) goto L_0x0020
            java.lang.String r2 = "RSA"
            java.security.PublicKey r7 = a(r2, r7)     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            java.lang.String r2 = "RSA/ECB/PKCS1Padding"
            javax.crypto.Cipher r2 = javax.crypto.Cipher.getInstance(r2)     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            f971a = r2     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            javax.crypto.Cipher r2 = f971a     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            r3 = 1
            r2.init(r3, r7)     // Catch:{ Exception -> 0x0051, all -> 0x004f }
        L_0x0020:
            java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            r7.<init>()     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            r2 = 0
        L_0x0026:
            int r3 = r6.length     // Catch:{ Exception -> 0x004d }
            if (r2 >= r3) goto L_0x003e
            javax.crypto.Cipher r3 = f971a     // Catch:{ Exception -> 0x004d }
            int r4 = r6.length     // Catch:{ Exception -> 0x004d }
            int r4 = r4 - r2
            r5 = 117(0x75, float:1.64E-43)
            if (r4 >= r5) goto L_0x0034
            int r4 = r6.length     // Catch:{ Exception -> 0x004d }
            int r5 = r4 - r2
        L_0x0034:
            byte[] r3 = r3.doFinal(r6, r2, r5)     // Catch:{ Exception -> 0x004d }
            r7.write(r3)     // Catch:{ Exception -> 0x004d }
            int r2 = r2 + 117
            goto L_0x0026
        L_0x003e:
            byte[] r6 = r7.toByteArray()     // Catch:{ Exception -> 0x004d }
            r7.close()     // Catch:{ IOException -> 0x0046 }
            goto L_0x0065
        L_0x0046:
            r7 = move-exception
            java.lang.String r1 = "LoggingRSA"
            android.util.Log.w(r1, r7)     // Catch:{ all -> 0x006f }
            goto L_0x0065
        L_0x004d:
            r6 = move-exception
            goto L_0x0053
        L_0x004f:
            r6 = move-exception
            goto L_0x0069
        L_0x0051:
            r6 = move-exception
            r7 = r1
        L_0x0053:
            java.lang.String r2 = "LoggingRSA"
            android.util.Log.w(r2, r6)     // Catch:{ all -> 0x0067 }
            if (r7 == 0) goto L_0x0064
            r7.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x0064
        L_0x005e:
            r6 = move-exception
            java.lang.String r7 = "LoggingRSA"
            android.util.Log.w(r7, r6)     // Catch:{ all -> 0x006f }
        L_0x0064:
            r6 = r1
        L_0x0065:
            monitor-exit(r0)
            return r6
        L_0x0067:
            r6 = move-exception
            r1 = r7
        L_0x0069:
            if (r1 == 0) goto L_0x0077
            r1.close()     // Catch:{ IOException -> 0x0071 }
            goto L_0x0077
        L_0x006f:
            r6 = move-exception
            goto L_0x0078
        L_0x0071:
            r7 = move-exception
            java.lang.String r1 = "LoggingRSA"
            android.util.Log.w(r1, r7)     // Catch:{ all -> 0x006f }
        L_0x0077:
            throw r6     // Catch:{ all -> 0x006f }
        L_0x0078:
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.util.RSAUtil.encrypt(byte[], java.lang.String):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x005a A[SYNTHETIC, Splitter:B:34:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x006b A[SYNTHETIC, Splitter:B:46:0x006b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized byte[] decrypt(byte[] r6, java.lang.String r7) {
        /*
            java.lang.Class<com.alipay.mobile.common.logging.util.RSAUtil> r0 = com.alipay.mobile.common.logging.util.RSAUtil.class
            monitor-enter(r0)
            r1 = 0
            if (r7 != 0) goto L_0x0008
            monitor-exit(r0)
            return r1
        L_0x0008:
            javax.crypto.Cipher r2 = b     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            if (r2 != 0) goto L_0x0020
            java.lang.String r2 = "RSA"
            java.security.PrivateKey r7 = b(r2, r7)     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            java.lang.String r2 = "RSA/ECB/PKCS1Padding"
            javax.crypto.Cipher r2 = javax.crypto.Cipher.getInstance(r2)     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            b = r2     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            javax.crypto.Cipher r2 = b     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            r3 = 2
            r2.init(r3, r7)     // Catch:{ Exception -> 0x0051, all -> 0x004f }
        L_0x0020:
            java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            r7.<init>()     // Catch:{ Exception -> 0x0051, all -> 0x004f }
            r2 = 0
        L_0x0026:
            int r3 = r6.length     // Catch:{ Exception -> 0x004d }
            if (r2 >= r3) goto L_0x003e
            javax.crypto.Cipher r3 = b     // Catch:{ Exception -> 0x004d }
            int r4 = r6.length     // Catch:{ Exception -> 0x004d }
            int r4 = r4 - r2
            r5 = 128(0x80, float:1.794E-43)
            if (r4 >= r5) goto L_0x0034
            int r4 = r6.length     // Catch:{ Exception -> 0x004d }
            int r5 = r4 - r2
        L_0x0034:
            byte[] r3 = r3.doFinal(r6, r2, r5)     // Catch:{ Exception -> 0x004d }
            r7.write(r3)     // Catch:{ Exception -> 0x004d }
            int r2 = r2 + 128
            goto L_0x0026
        L_0x003e:
            byte[] r6 = r7.toByteArray()     // Catch:{ Exception -> 0x004d }
            r7.close()     // Catch:{ IOException -> 0x0046 }
            goto L_0x0065
        L_0x0046:
            r7 = move-exception
            java.lang.String r1 = "LoggingRSA"
            android.util.Log.w(r1, r7)     // Catch:{ all -> 0x006f }
            goto L_0x0065
        L_0x004d:
            r6 = move-exception
            goto L_0x0053
        L_0x004f:
            r6 = move-exception
            goto L_0x0069
        L_0x0051:
            r6 = move-exception
            r7 = r1
        L_0x0053:
            java.lang.String r2 = "LoggingRSA"
            android.util.Log.w(r2, r6)     // Catch:{ all -> 0x0067 }
            if (r7 == 0) goto L_0x0064
            r7.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x0064
        L_0x005e:
            r6 = move-exception
            java.lang.String r7 = "LoggingRSA"
            android.util.Log.w(r7, r6)     // Catch:{ all -> 0x006f }
        L_0x0064:
            r6 = r1
        L_0x0065:
            monitor-exit(r0)
            return r6
        L_0x0067:
            r6 = move-exception
            r1 = r7
        L_0x0069:
            if (r1 == 0) goto L_0x0077
            r1.close()     // Catch:{ IOException -> 0x0071 }
            goto L_0x0077
        L_0x006f:
            r6 = move-exception
            goto L_0x0078
        L_0x0071:
            r7 = move-exception
            java.lang.String r1 = "LoggingRSA"
            android.util.Log.w(r1, r7)     // Catch:{ all -> 0x006f }
        L_0x0077:
            throw r6     // Catch:{ all -> 0x006f }
        L_0x0078:
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.util.RSAUtil.decrypt(byte[], java.lang.String):byte[]");
    }
}
