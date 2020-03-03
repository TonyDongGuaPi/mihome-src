package com.xiaomi.mobilestats.common;

import android.util.Base64;
import com.coloros.mcssdk.c.a;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
    public static final String ENCODE_KEY = "*7^%4)KIjh17sd98";
    private static String r = "b92939a1a2724a44";
    private static String s = "a92939b1b2723b33";

    public static byte[] desEncrypt(byte[] bArr, String str, byte[] bArr2) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, new SecretKeySpec(str.getBytes(), a.b), new IvParameterSpec(bArr2));
            return instance.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encrpt(byte[] bArr, String str) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, new SecretKeySpec(str.getBytes(), a.b), new IvParameterSpec(s.getBytes()));
            return new String(Base64.encode(instance.doFinal(bArr), 2));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] encrpt(byte[] bArr) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, new SecretKeySpec(ENCODE_KEY.getBytes(), a.b), new IvParameterSpec(s.getBytes()));
            return Base64.encode(instance.doFinal(bArr), 2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] encrypt(byte[] bArr, String str) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            r = getRandomString(16);
            instance.init(1, new SecretKeySpec(str.getBytes(), a.b), new IvParameterSpec(r.getBytes()));
            byte[] doFinal = instance.doFinal(bArr);
            byte[] bArr2 = new byte[(r.getBytes().length + doFinal.length)];
            System.arraycopy(r.getBytes(), 0, bArr2, 0, r.getBytes().length);
            System.arraycopy(doFinal, 0, bArr2, r.getBytes().length, doFinal.length);
            return bArr2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getRandomString(int i) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt("abcdefghijklmnopqrstuvwxyz0123456789".length())));
        }
        return stringBuffer.toString();
    }

    public static String unEncrpt(String str, String str2) {
        try {
            byte[] decode = Base64.decode(str, 2);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, new SecretKeySpec(str2.getBytes(), a.b), new IvParameterSpec(s.getBytes()));
            return new String(instance.doFinal(decode));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] unEncrpt(String str) {
        try {
            byte[] decode = Base64.decode(str, 2);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, new SecretKeySpec(ENCODE_KEY.getBytes(), a.b), new IvParameterSpec(s.getBytes()));
            return instance.doFinal(decode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
