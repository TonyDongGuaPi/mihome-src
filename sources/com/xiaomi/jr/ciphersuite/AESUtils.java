package com.xiaomi.jr.ciphersuite;

import com.coloros.mcssdk.c.a;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10354a = "AESUtils";
    private static final String b = "AES/CBC/PKCS5Padding";
    private static final byte[] c = "0102030405060708".getBytes();

    public static String a(byte[] bArr, String str) {
        return a("AES/CBC/PKCS5Padding", bArr, str);
    }

    public static String a(String str, String str2) {
        return a("AES/CBC/PKCS5Padding", str, str2);
    }

    public static byte[] b(byte[] bArr, String str) {
        return b("AES/CBC/PKCS5Padding", bArr, str);
    }

    public static String a(String str, byte[] bArr, String str2) {
        byte[] b2 = b(str, bArr, str2);
        if (b2 != null) {
            return Utils.a(b2);
        }
        return null;
    }

    public static String a(String str, String str2, String str3) {
        if (str2 != null) {
            return a(str, str2.getBytes(), str3);
        }
        return null;
    }

    public static byte[] b(String str, byte[] bArr, String str2) {
        if (bArr == null || Utils.a(str)) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(Utils.b(str2), a.b);
        try {
            Cipher instance = Cipher.getInstance(str);
            if (str.contains("CBC")) {
                instance.init(1, secretKeySpec, new IvParameterSpec(c));
            } else {
                instance.init(1, secretKeySpec);
            }
            return instance.doFinal(bArr);
        } catch (Exception e) {
            Utils.a("AESUtils", "encrypt AES failed", e);
            return null;
        }
    }

    public static String b(String str, String str2) {
        return b("AES/CBC/PKCS5Padding", str, str2);
    }

    public static byte[] c(byte[] bArr, String str) {
        return c("AES/CBC/PKCS5Padding", bArr, str);
    }

    public static String b(String str, String str2, String str3) {
        byte[] c2;
        if (Utils.a(str2) || Utils.a(str) || (c2 = c(str, Utils.b(str2), str3)) == null) {
            return null;
        }
        return new String(c2);
    }

    public static byte[] c(String str, byte[] bArr, String str2) {
        if (bArr == null || bArr.length == 0 || Utils.a(str2) || Utils.a(str)) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(Utils.b(str2), a.b);
        try {
            Cipher instance = Cipher.getInstance(str);
            if (str.contains("CBC")) {
                instance.init(2, secretKeySpec, new IvParameterSpec(c));
            } else {
                instance.init(2, secretKeySpec);
            }
            return instance.doFinal(bArr);
        } catch (Exception e) {
            Utils.a("AESUtils", "decrypt AES failed", e);
            return null;
        }
    }

    public static String a() {
        return a(256);
    }

    public static String a(int i) {
        try {
            KeyGenerator instance = KeyGenerator.getInstance(a.b);
            instance.init(i);
            return Utils.a(instance.generateKey().getEncoded());
        } catch (NoSuchAlgorithmException e) {
            Utils.a("AESUtils", "generate aes key failed", e);
            return null;
        }
    }
}
