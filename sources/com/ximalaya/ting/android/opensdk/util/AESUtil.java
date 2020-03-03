package com.ximalaya.ting.android.opensdk.util;

import java.nio.charset.Charset;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2248a = "AES";
    private static final String b = "AES/ECB/PKCS5Padding";
    private static final Charset c = Charset.forName("utf-8");

    public static String a(String str, String str2) {
        return a(str.getBytes(c), str2.getBytes(c));
    }

    public static String a(byte[] bArr, byte[] bArr2) {
        return new String(b(bArr, bArr2));
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        try {
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(1, a(bArr2));
            return instance.doFinal(bArr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] b(String str, String str2) {
        try {
            return b(str.getBytes(c), str2.getBytes(c));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] c(byte[] bArr, byte[] bArr2) {
        try {
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(2, a(bArr2));
            return instance.doFinal(bArr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] c(String str, String str2) {
        return c(str.getBytes(c), str2.getBytes(c));
    }

    public static String d(String str, String str2) {
        return d(str.getBytes(c), str2.getBytes(c));
    }

    public static String d(byte[] bArr, byte[] bArr2) {
        return new String(c(bArr, bArr2));
    }

    private static SecretKey a(byte[] bArr) throws Exception {
        return new SecretKeySpec(bArr, "AES");
    }
}
