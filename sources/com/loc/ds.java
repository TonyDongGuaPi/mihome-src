package com.loc;

import com.coloros.mcssdk.c.a;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class ds {
    public static String a(String str, String str2) {
        byte[] bArr;
        try {
            byte[] a2 = a(str.getBytes());
            byte[] bytes = str2.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(a2, a.b);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
            bArr = instance.doFinal(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            bArr = null;
        }
        if (bArr != null) {
            return b(bArr);
        }
        return null;
    }

    private static byte[] a(byte[] bArr) throws Exception {
        KeyGenerator instance = KeyGenerator.getInstance(a.b);
        SecureRandom instance2 = SecureRandom.getInstance(a.c, a.d);
        instance2.setSeed(bArr);
        instance.init(128, instance2);
        return instance.generateKey().getEncoded();
    }

    public static String b(String str, String str2) {
        try {
            byte[] a2 = a(str.getBytes());
            int length = str2.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                bArr[i] = Integer.valueOf(str2.substring(i2, i2 + 2), 16).byteValue();
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(a2, a.b);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
            return new String(instance.doFinal(bArr));
        } catch (Exception unused) {
            return null;
        }
    }

    private static String b(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            stringBuffer.append(a.f.charAt((b >> 4) & 15));
            stringBuffer.append(a.f.charAt(b & 15));
        }
        return stringBuffer.toString();
    }
}
