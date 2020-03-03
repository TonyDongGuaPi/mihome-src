package com.miuipub.internal.hybrid;

import android.util.Base64;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class SignUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8267a = "RSA";
    public static final String b = "RSA/ECB/PKCS1Padding";
    public static final String c = "SHA1withRSA";
    public static final String d = "MD5withRSA";
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 4;
    public static final int i = 8;
    public static final int j = 16;

    private SignUtils() {
    }

    public static PublicKey a(KeySpec keySpec) throws Exception {
        return KeyFactory.getInstance(f8267a).generatePublic(keySpec);
    }

    public static PublicKey a(String str, int i2) throws Exception {
        return a((KeySpec) new X509EncodedKeySpec(Base64.decode(str, i2)));
    }

    public static PublicKey a(String str) throws Exception {
        return a(str, 0);
    }

    public static PrivateKey b(KeySpec keySpec) throws Exception {
        return KeyFactory.getInstance(f8267a).generatePrivate(keySpec);
    }

    public static PrivateKey b(String str, int i2) throws Exception {
        return b((KeySpec) new PKCS8EncodedKeySpec(Base64.decode(str, i2)));
    }

    public static PrivateKey b(String str) throws Exception {
        return b(str, 0);
    }

    public static byte[] a(byte[] bArr, PrivateKey privateKey, String str) throws Exception {
        Signature instance = Signature.getInstance(str);
        instance.initSign(privateKey);
        instance.update(bArr);
        return instance.sign();
    }

    public static byte[] a(byte[] bArr, PrivateKey privateKey) throws Exception {
        return a(bArr, privateKey, c);
    }

    public static String a(String str, PrivateKey privateKey, String str2) throws Exception {
        return new String(Base64.encode(a(str.getBytes(), privateKey, str2), 2));
    }

    public static String a(String str, PrivateKey privateKey) throws Exception {
        return a(str, privateKey, c);
    }

    public static boolean a(byte[] bArr, PublicKey publicKey, byte[] bArr2, String str) throws Exception {
        Signature instance = Signature.getInstance(str);
        instance.initVerify(publicKey);
        instance.update(bArr);
        return instance.verify(bArr2);
    }

    public static boolean a(byte[] bArr, PublicKey publicKey, byte[] bArr2) throws Exception {
        return a(bArr, publicKey, bArr2, c);
    }

    public static boolean a(String str, PublicKey publicKey, String str2, String str3) throws Exception {
        return a(str.getBytes(), publicKey, Base64.decode(str2, 2), str3);
    }

    public static boolean a(String str, PublicKey publicKey, String str2) throws Exception {
        return a(str, publicKey, str2, c);
    }
}
