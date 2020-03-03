package com.Utils;

import android.util.Log;
import com.coloros.mcssdk.c.a;
import com.mijia.app.AppConfig;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class VideoDecryption {

    /* renamed from: a  reason: collision with root package name */
    static final String f672a = "VideoDecryption";
    private static final byte[] b = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public static void a(String str, String str2, String str3) {
        byte[] b2 = b(str);
        if (b2 == null) {
            Log.e(f672a, "read file error:" + str);
            return;
        }
        byte[] a2 = a(str2);
        if (a2 == null) {
            Log.e(f672a, "md5 error:" + str2);
            return;
        }
        byte[] a3 = a(b2, a2);
        if (a3 == null || a3.length < 2) {
            Log.e(f672a, "decryption error");
        } else if (AppConfig.c()) {
            byte b3 = a3[a3.length - 1];
            if (b3 <= 0 || b3 > 16) {
                b(a3, str3);
                return;
            }
            int length = a3.length - b3;
            byte[] bArr = new byte[length];
            System.arraycopy(a3, 0, bArr, 0, length);
            b(bArr, str3);
        } else {
            b(a3, str3);
        }
    }

    public static byte[] a(byte[] bArr, String str) {
        if (bArr == null) {
            Log.e(f672a, "decryption indata null");
            return null;
        }
        byte[] a2 = a(str);
        if (a2 == null) {
            Log.e(f672a, "md5 error:" + str);
            return null;
        }
        byte[] a3 = a(bArr, a2);
        if (a3 == null) {
            Log.e(f672a, "decryption error");
        }
        return a3;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, a.b);
            Cipher instance = Cipher.getInstance("AES/CBC/NoPadding");
            instance.init(2, secretKeySpec, new IvParameterSpec(b));
            return instance.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes(), 0, str.length());
            return a(instance.digest()).getBytes();
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString().toLowerCase();
    }

    public static byte[] b(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[fileInputStream.available()];
            try {
                fileInputStream.read(bArr);
                fileInputStream.close();
                return bArr;
            } catch (FileNotFoundException | IOException unused) {
                return bArr;
            }
        } catch (FileNotFoundException | IOException unused2) {
            return null;
        }
    }

    public static void b(byte[] bArr, String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            fileOutputStream.write(bArr);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
