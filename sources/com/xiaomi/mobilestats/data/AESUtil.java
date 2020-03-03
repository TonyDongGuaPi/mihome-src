package com.xiaomi.mobilestats.data;

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
            instance.init(1, i(), h());
            return instance.doFinal(bArr);
        } catch (Exception unused) {
            return bArr;
        }
    }

    static AlgorithmParameterSpec h() {
        try {
            return new IvParameterSpec("Gruj<tOv]Ok[Ad.T".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    static Key i() {
        try {
            return new SecretKeySpec("drUsh]ipt?es;Ith".getBytes("UTF-8"), a.b);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
