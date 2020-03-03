package com.mi.util;

import android.text.TextUtils;
import android.util.Base64;
import com.coloros.mcssdk.c.a;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.net.URLEncoder;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import miuipub.security.DigestUtils;

public class UserEncryptionUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7423a = "jWw5zMsiWkU29IQkmo2gYA==";
    private static final String b = "AES/CBC/PKCS7Padding";

    public static String a(String str) {
        try {
            byte[] bytes = (str + '|' + System.currentTimeMillis()).getBytes("UTF-8");
            Cipher instance = Cipher.getInstance(b);
            instance.init(1, new SecretKeySpec(AesEncryptionUtil.a().getBytes(), a.b), new IvParameterSpec(AesEncryptionUtil.b().getBytes()));
            return URLEncoder.encode(new String(Base64.encode(instance.doFinal(bytes), 0)), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String b(String str) {
        String str2;
        String str3 = "";
        try {
            byte[] decode = Base64.decode(f7423a, 0);
            MessageDigest instance = MessageDigest.getInstance(DigestUtils.b);
            instance.update(a(decode, str.getBytes()));
            String str4 = new String(Base64.encode(instance.digest(), 0));
            try {
                if (!TextUtils.isEmpty(str4)) {
                    str3 = str4.replace('+', '-');
                    String trim = str3.replace(IOUtils.f15883a, '_').trim();
                    while (true) {
                        str2 = trim;
                        if (!str2.endsWith("=")) {
                            break;
                        }
                        trim = str2.substring(0, str2.length() - 1);
                    }
                } else {
                    str2 = str4;
                }
                return URLEncoder.encode(str2, "UTF-8");
            } catch (Exception e) {
                e = e;
                str3 = str4;
                e.printStackTrace();
                return str3;
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return str3;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }
}
