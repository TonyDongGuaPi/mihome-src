package com.xiaomi.smarthome.library.crypto.rc4coder;

import com.xiaomi.smarthome.library.crypto.Base64Coder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public abstract class Coder {

    /* renamed from: a  reason: collision with root package name */
    public static final String f19091a = "SHA";
    public static final String b = "MD5";
    public static final String c = "HmacMD5";
    public static final String d = "PBKDF2WithHmacSHA1";
    private static final String e = "HmacSHA1";
    private static final String f = "SHA-256";

    private static boolean e(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    public static byte[] a(String str) {
        return Base64Coder.a(str);
    }

    public static String a(byte[] bArr) {
        return String.valueOf(Base64Coder.a(bArr));
    }

    public static byte[] b(byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(bArr);
        return instance.digest();
    }

    public static byte[] a(byte[]... bArr) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        for (byte[] bArr2 : bArr) {
            if (!e(bArr2)) {
                instance.update(bArr2);
            }
        }
        return instance.digest();
    }

    public static byte[] c(byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("SHA");
        instance.update(bArr);
        return instance.digest();
    }

    public static String a() throws Exception {
        return a(KeyGenerator.getInstance("HmacMD5").generateKey().getEncoded());
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "HmacMD5");
        Mac instance = Mac.getInstance(secretKeySpec.getAlgorithm());
        instance.init(secretKeySpec);
        return instance.doFinal(bArr);
    }

    public static byte[] a(byte[] bArr, String str) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(a(str), "HmacMD5");
        Mac instance = Mac.getInstance(secretKeySpec.getAlgorithm());
        instance.init(secretKeySpec);
        return instance.doFinal(bArr);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, e);
        Mac instance = Mac.getInstance(e);
        instance.init(secretKeySpec);
        instance.update(bArr);
        return instance.doFinal();
    }

    public static byte[] d(byte[] bArr) throws NoSuchAlgorithmException, InvalidKeyException {
        MessageDigest instance = MessageDigest.getInstance(f);
        instance.update(bArr);
        return instance.digest();
    }

    public static byte[] a(char[] cArr, byte[] bArr, int i, int i2) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(cArr, bArr, i, i2)).getEncoded();
    }
}
