package com.xiaomi.smarthome.framework.plugin;

import com.coloros.mcssdk.c.a;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f17128a = "AESUtils";

    public static String a(String str, String str2) {
        byte[] bArr;
        try {
            bArr = a(b(str.getBytes()), str2.getBytes());
        } catch (Exception unused) {
            bArr = null;
        }
        return a(bArr);
    }

    public static String b(String str, String str2) {
        try {
            return new String(b(b(str.getBytes()), c(str2)));
        } catch (Exception unused) {
            return null;
        }
    }

    private static byte[] b(byte[] bArr) throws Exception {
        KeyGenerator instance = KeyGenerator.getInstance(a.b);
        SecureRandom instance2 = SecureRandom.getInstance(a.c);
        instance2.setSeed(bArr);
        instance.init(128, instance2);
        return instance.generateKey().getEncoded();
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, a.b);
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
        return instance.doFinal(bArr2);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, a.b);
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
        return instance.doFinal(bArr2);
    }

    public static String a(String str) {
        return a(str.getBytes());
    }

    public static String b(String str) {
        return new String(c(str));
    }

    public static byte[] c(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte a2 : bArr) {
            a(stringBuffer, a2);
        }
        return stringBuffer.toString();
    }

    private static void a(StringBuffer stringBuffer, byte b) {
        stringBuffer.append(a.f.charAt((b >> 4) & 15));
        stringBuffer.append(a.f.charAt(b & 15));
    }
}
