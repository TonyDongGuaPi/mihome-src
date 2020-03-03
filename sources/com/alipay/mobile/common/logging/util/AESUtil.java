package com.alipay.mobile.common.logging.util;

import android.os.Build;
import android.util.Log;
import com.coloros.mcssdk.c.a;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    public static byte[] encrypt(byte[] bArr, byte[] bArr2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, a.b);
        Cipher instance = Cipher.getInstance(a.b);
        instance.init(1, secretKeySpec);
        return instance.doFinal(bArr2);
    }

    public static byte[] decrypt(byte[] bArr, byte[] bArr2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, a.b);
        Cipher instance = Cipher.getInstance(a.b);
        instance.init(2, secretKeySpec);
        return instance.doFinal(bArr2);
    }

    public static byte[] getRawKey(byte[] bArr) {
        SecureRandom secureRandom;
        try {
            KeyGenerator instance = KeyGenerator.getInstance(a.b);
            if (Build.VERSION.SDK_INT >= 17) {
                secureRandom = SecureRandom.getInstance(a.c, a.d);
            } else {
                secureRandom = SecureRandom.getInstance(a.c);
            }
            secureRandom.setSeed(bArr);
            instance.init(128, secureRandom);
            return instance.generateKey().getEncoded();
        } catch (Exception e) {
            Log.w("LoggingAES", e);
            return null;
        }
    }
}
