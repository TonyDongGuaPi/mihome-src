package com.huawei.hms.support.api.push.a.a.b;

import android.text.TextUtils;
import com.huawei.hms.support.api.push.a.a.a.b;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public abstract class a {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return a(str, a());
    }

    public static String a(String str, byte[] bArr) {
        if (TextUtils.isEmpty(str) || bArr == null || bArr.length <= 0) {
            return "";
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, com.coloros.mcssdk.c.a.b);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] bArr2 = new byte[16];
            new SecureRandom().nextBytes(bArr2);
            instance.init(1, secretKeySpec, new IvParameterSpec(bArr2));
            return a(com.huawei.hms.support.api.push.a.a.a.a.a(bArr2), com.huawei.hms.support.api.push.a.a.a.a.a(instance.doFinal(str.getBytes("UTF-8"))));
        } catch (IllegalArgumentException e) {
            b("IllegalArgumentException aes cbc encrypter data error", (Exception) e);
            return null;
        } catch (InvalidKeyException e2) {
            b("InvalidKeyException aes cbc encrypter data error", (Exception) e2);
            return null;
        } catch (InvalidAlgorithmParameterException e3) {
            b("InvalidAlgorithmParameterException aes cbc encrypter data error", (Exception) e3);
            return null;
        } catch (IllegalBlockSizeException e4) {
            b("IllegalBlockSizeException aes cbc encrypter data error", (Exception) e4);
            return null;
        } catch (UnsupportedEncodingException e5) {
            b("UnsupportedEncodingException aes cbc encrypter data error", (Exception) e5);
            return null;
        } catch (Exception e6) {
            b("aes cbc encrypter data error", e6);
            return null;
        }
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return b(str, a());
    }

    public static String b(String str, byte[] bArr) {
        if (TextUtils.isEmpty(str) || bArr == null || bArr.length <= 0) {
            return "";
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, com.coloros.mcssdk.c.a.b);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            String c = c(str);
            String d = d(str);
            if (!TextUtils.isEmpty(c)) {
                if (!TextUtils.isEmpty(d)) {
                    instance.init(2, secretKeySpec, new IvParameterSpec(com.huawei.hms.support.api.push.a.a.a.a.b(c)));
                    return new String(instance.doFinal(com.huawei.hms.support.api.push.a.a.a.a.b(d)), "UTF-8");
                }
            }
            if (!com.huawei.hms.support.log.a.b()) {
                return "";
            }
            com.huawei.hms.support.log.a.b("AES128_CBC", "ivParameter or encrypedWord is null");
            return "";
        } catch (IllegalBlockSizeException e) {
            a("aes cbc decrypter data error", (Exception) e);
            return "";
        } catch (BadPaddingException e2) {
            a("aes cbc decrypter data error", (Exception) e2);
            return "";
        } catch (UnsupportedEncodingException e3) {
            a("aes cbc decrypter data error", (Exception) e3);
            return "";
        } catch (NoSuchAlgorithmException e4) {
            a("aes cbc decrypter data error", (Exception) e4);
            return "";
        } catch (NoSuchPaddingException e5) {
            a("aes cbc decrypter data error", (Exception) e5);
            return "";
        } catch (Exception e6) {
            a("aes cbc encrypter data error", e6);
            return "";
        }
    }

    private static byte[] a() {
        byte[] b = com.huawei.hms.support.api.push.a.a.a.a.b(b.a());
        byte[] b2 = com.huawei.hms.support.api.push.a.a.a.a.b(b.a());
        return a(a(a(b, b2), com.huawei.hms.support.api.push.a.a.a.a.b("2A57086C86EF54970C1E6EB37BFC72B1")));
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr.length == 0 || bArr2.length == 0) {
            return new byte[0];
        }
        int length = bArr.length;
        if (length != bArr2.length) {
            return new byte[0];
        }
        byte[] bArr3 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
        return bArr3;
    }

    private static byte[] a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return new byte[0];
        }
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] >> 2);
        }
        return bArr;
    }

    private static String a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str2.substring(0, 6));
            stringBuffer.append(str.substring(0, 6));
            stringBuffer.append(str2.substring(6, 10));
            stringBuffer.append(str.substring(6, 16));
            stringBuffer.append(str2.substring(10, 16));
            stringBuffer.append(str.substring(16));
            stringBuffer.append(str2.substring(16));
            return stringBuffer.toString();
        } catch (Exception e) {
            if (!com.huawei.hms.support.log.a.d()) {
                return "";
            }
            com.huawei.hms.support.log.a.d("AES128_CBC", e.getMessage());
            return "";
        }
    }

    private static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str.substring(6, 12));
            stringBuffer.append(str.substring(16, 26));
            stringBuffer.append(str.substring(32, 48));
            return stringBuffer.toString();
        } catch (Exception e) {
            if (!com.huawei.hms.support.log.a.d()) {
                return "";
            }
            com.huawei.hms.support.log.a.d("AES128_CBC", "get iv error:" + e.getMessage());
            return "";
        }
    }

    private static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str.substring(0, 6));
            stringBuffer.append(str.substring(12, 16));
            stringBuffer.append(str.substring(26, 32));
            stringBuffer.append(str.substring(48));
            return stringBuffer.toString();
        } catch (Exception e) {
            if (!com.huawei.hms.support.log.a.d()) {
                return "";
            }
            com.huawei.hms.support.log.a.d("AES128_CBC", "get encrypt word error:" + e.getMessage());
            return "";
        }
    }

    private static void a(String str, Exception exc) {
        if (com.huawei.hms.support.log.a.d()) {
            com.huawei.hms.support.log.a.d("AES128_CBC", str + exc.getMessage());
        }
    }

    private static void b(String str, Exception exc) {
        if (com.huawei.hms.support.log.a.d()) {
            com.huawei.hms.support.log.a.d("AES128_CBC", str + exc.getMessage());
        }
    }
}
