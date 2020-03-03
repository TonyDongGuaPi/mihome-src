package com.xiaomi.mishopsdk.util;

import com.coloros.mcssdk.c.a;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PBAESUtil {
    public static final String ENCRYPTION_KEY_ALIVE = "asd&^5sd$()ns1s9";
    public static final String ENCRYPTION_KEY_API = "cjs8&63ld0~!9f@j";

    public static byte[] encrypt(byte[] bArr, String str, byte[] bArr2) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, makeKey(str), makeIv(bArr2));
            return instance.doFinal(bArr);
        } catch (Exception unused) {
            return bArr;
        }
    }

    public static byte[] decrypt(byte[] bArr, String str, byte[] bArr2) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, makeKey(str), makeIv(bArr2));
            return instance.doFinal(bArr);
        } catch (Exception unused) {
            return new byte[0];
        }
    }

    static AlgorithmParameterSpec makeIv(byte[] bArr) {
        return new IvParameterSpec(bArr);
    }

    static Key makeKey(String str) {
        try {
            return new SecretKeySpec(str.getBytes("UTF-8"), a.b);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
