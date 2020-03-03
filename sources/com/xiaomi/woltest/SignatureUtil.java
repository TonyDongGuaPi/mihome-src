package com.xiaomi.woltest;

import android.util.Base64;
import android.util.Log;
import com.coloros.mcssdk.c.a;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SignatureUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23143a = "SignatureUtil";

    public static String a(byte[] bArr, byte[] bArr2) throws InvalidKeyException, NoSuchAlgorithmException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "HmacSHA1");
        Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(secretKeySpec);
        return IOUtil.a(instance.doFinal(bArr));
    }

    public static String a(String str, String str2) throws Exception {
        if (str == null || str2 == null) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.decode(str2.getBytes("UTF-8"), 0), a.b);
        Cipher instance = Cipher.getInstance(a.b);
        instance.init(1, secretKeySpec);
        return Base64.encodeToString(instance.doFinal(str.getBytes("UTF-8")), 0);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        if (bArr == null || bArr2 == null) {
            return null;
        }
        String str = f23143a;
        Log.i(str, "decrypt key lenght: " + bArr2.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES/CBC/NoPadding");
        Cipher instance = Cipher.getInstance("AES/CBC/NoPadding");
        instance.init(2, secretKeySpec, new IvParameterSpec(new byte[16]));
        return instance.doFinal(Base64.decode(bArr, 0));
    }

    public static String b(String str, String str2) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        if (str == null || str2 == null) {
            return null;
        }
        return new String(b(str.getBytes("UTF-8"), str2.getBytes("UTF-8")), "UTF-8");
    }

    public static String a(String str) {
        return str == null ? "" : a(str.getBytes());
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0035 A[SYNTHETIC, Splitter:B:20:0x0035] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x003c A[SYNTHETIC, Splitter:B:28:0x003c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.io.File r6) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0039, all -> 0x0031 }
            java.lang.String r6 = r6.getAbsolutePath()     // Catch:{ Exception -> 0x0039, all -> 0x0031 }
            r1.<init>(r6)     // Catch:{ Exception -> 0x0039, all -> 0x0031 }
            r6 = 1024(0x400, float:1.435E-42)
            byte[] r6 = new byte[r6]     // Catch:{ Exception -> 0x003a, all -> 0x002f }
            java.lang.String r2 = "MD5"
            java.security.MessageDigest r2 = java.security.MessageDigest.getInstance(r2)     // Catch:{ Exception -> 0x003a, all -> 0x002f }
            r3 = 0
            r4 = 0
        L_0x0016:
            r5 = -1
            if (r4 == r5) goto L_0x0023
            int r4 = r1.read(r6)     // Catch:{ Exception -> 0x003a, all -> 0x002f }
            if (r4 <= 0) goto L_0x0016
            r2.update(r6, r3, r4)     // Catch:{ Exception -> 0x003a, all -> 0x002f }
            goto L_0x0016
        L_0x0023:
            byte[] r6 = r2.digest()     // Catch:{ Exception -> 0x003a, all -> 0x002f }
            java.lang.String r6 = com.xiaomi.woltest.IOUtil.a((byte[]) r6)     // Catch:{ Exception -> 0x003a, all -> 0x002f }
            r1.close()     // Catch:{ Exception -> 0x002e }
        L_0x002e:
            return r6
        L_0x002f:
            r6 = move-exception
            goto L_0x0033
        L_0x0031:
            r6 = move-exception
            r1 = r0
        L_0x0033:
            if (r1 == 0) goto L_0x0038
            r1.close()     // Catch:{ Exception -> 0x0038 }
        L_0x0038:
            throw r6
        L_0x0039:
            r1 = r0
        L_0x003a:
            if (r1 == 0) goto L_0x003f
            r1.close()     // Catch:{ Exception -> 0x003f }
        L_0x003f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.woltest.SignatureUtil.a(java.io.File):java.lang.String");
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bArr);
            return IOUtil.a(instance.digest());
        } catch (Exception unused) {
            return "";
        }
    }

    public static byte[] c(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return null;
        }
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr2[i % bArr2.length] ^ bArr[i]);
        }
        return bArr;
    }

    public static String c(String str, String str2) throws UnsupportedEncodingException {
        return (str == null || str2 == null) ? "" : new String(c(str.getBytes("UTF-8"), str2.getBytes("UTF-8")), "UTF-8");
    }
}
