package com.xiaomi.smarthome.library.bluetooth.utils;

import android.util.Base64;
import com.coloros.mcssdk.c.a;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AESCryptUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18545a = "AESCrypt";
    private static final String b = "AES/CBC/PKCS7Padding";
    private static final String c = "UTF-8";
    private static final String d = "SHA-256";
    private static final byte[] e = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private static SecretKeySpec a(String str) throws Exception {
        MessageDigest instance = MessageDigest.getInstance(d);
        byte[] bytes = str.getBytes("UTF-8");
        instance.update(bytes, 0, bytes.length);
        return new SecretKeySpec(instance.digest(), a.b);
    }

    public static String a(String str, String str2) throws Exception {
        return Base64.encodeToString(a(a(str), e, str2.getBytes("UTF-8")), 2);
    }

    private static byte[] a(SecretKeySpec secretKeySpec, byte[] bArr, byte[] bArr2) throws Exception {
        Cipher instance = Cipher.getInstance(b);
        instance.init(1, secretKeySpec, new IvParameterSpec(bArr));
        return instance.doFinal(bArr2);
    }

    public static String b(String str, String str2) throws Exception {
        return new String(b(a(str), e, Base64.decode(str2, 2)), "UTF-8");
    }

    private static byte[] b(SecretKeySpec secretKeySpec, byte[] bArr, byte[] bArr2) throws Exception {
        Cipher instance = Cipher.getInstance(b);
        instance.init(2, secretKeySpec, new IvParameterSpec(bArr));
        return instance.doFinal(bArr2);
    }
}
