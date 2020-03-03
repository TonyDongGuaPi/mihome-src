package com.mi.mistatistic.sdk.controller;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class AesUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7322a = "AES/ECB/PKCS5Padding";
    private static final String b = "AES";
    private static KeyGenerator c;
    private static Cipher d;

    static {
        try {
            c = KeyGenerator.getInstance("AES");
            c.init(128);
            d = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] a() {
        return c.generateKey().getEncoded();
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            d.init(1, new SecretKeySpec(bArr2, "AES"));
            return d.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] c(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(a(str2), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] bytes = str.getBytes("utf-8");
            instance.init(1, secretKeySpec);
            return instance.doFinal(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] a(byte[] bArr, String str) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(a(str), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(2, secretKeySpec);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] a(String str) {
        if (str != null) {
            return str.getBytes();
        }
        return null;
    }

    private static byte[] b(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length() / 2; i++) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            bArr[i] = (byte) ((Integer.parseInt(str.substring(i2, i3), 16) * 16) + Integer.parseInt(str.substring(i3, i2 + 2), 16));
        }
        return bArr;
    }

    public static String a(String str, String str2) {
        return a(c(str, str2));
    }

    public static String b(String str, String str2) {
        return new String(a(b(str), str2));
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            while (hexString.length() < 2) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }
}
