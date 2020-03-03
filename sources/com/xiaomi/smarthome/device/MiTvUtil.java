package com.xiaomi.smarthome.device;

import android.util.Base64;
import com.coloros.mcssdk.c.a;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MiTvUtil {
    public static String a(String str) {
        if (str == null) {
            return "";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(str.getBytes());
            return a(instance.digest());
        } catch (Exception unused) {
            return "";
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            while (hexString.length() < 2) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static Cipher a(String str, int i) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.decode(str, 2), a.b);
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(i, secretKeySpec, new IvParameterSpec("0102030405060708".getBytes()));
            return instance;
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException unused) {
            return null;
        }
    }

    public static String a(String str, String str2) {
        Cipher a2 = a(str, 1);
        if (a2 == null) {
            return null;
        }
        try {
            return Base64.encodeToString(a2.doFinal(str2.getBytes("utf-8")), 2);
        } catch (Exception unused) {
            return null;
        }
    }
}
