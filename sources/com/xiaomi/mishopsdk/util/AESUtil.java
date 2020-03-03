package com.xiaomi.mishopsdk.util;

import com.coloros.mcssdk.c.a;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    public static final String ENCRYPTION_IV = "Gruj<tOv]Ok[Ad.T";
    public static final String ENCRYPTION_KEY = "drUsh]ipt?es;Ith";

    public static byte[] encrypt(byte[] bArr) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, makeKey(), makeIv());
            return instance.doFinal(bArr);
        } catch (Exception unused) {
            return bArr;
        }
    }

    public static byte[] decrypt(byte[] bArr) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, makeKey(), makeIv());
            return instance.doFinal(bArr);
        } catch (Exception unused) {
            return new byte[0];
        }
    }

    static AlgorithmParameterSpec makeIv() {
        try {
            return new IvParameterSpec("Gruj<tOv]Ok[Ad.T".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    static Key makeKey() {
        try {
            return new SecretKeySpec("drUsh]ipt?es;Ith".getBytes("UTF-8"), a.b);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
