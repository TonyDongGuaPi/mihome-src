package com.xiaomi.mobilestats.common;

import android.util.Base64;
import com.coloros.mcssdk.c.a;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncodeUtil {
    private static String a(byte[] bArr) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, new SecretKeySpec("uiodj*7^5%$4)(!~".getBytes(), a.b), new IvParameterSpec("mki*&^1dbriieye8".getBytes()));
            return new String(Base64.encode(instance.doFinal(bArr), 2));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static byte[] b(byte[] bArr) {
        try {
            return MessageDigest.getInstance("MD5").digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        }
    }

    public static String getEncodeBody(byte[] bArr) {
        byte[] bArr2 = new byte[(bArr.length + "1sjkd830)8&jfh^%$#d".getBytes().length)];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        System.arraycopy("1sjkd830)8&jfh^%$#d".getBytes(), 0, bArr2, bArr.length, "1sjkd830)8&jfh^%$#d".getBytes().length);
        try {
            byte[] b = b(bArr2);
            return (b == null || b.length <= 0) ? "" : a(b);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
