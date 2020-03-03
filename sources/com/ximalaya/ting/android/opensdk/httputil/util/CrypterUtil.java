package com.ximalaya.ting.android.opensdk.httputil.util;

import com.ximalaya.ting.android.opensdk.util.DigestUtils;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class CrypterUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1997a = "HmacSHA1";

    public static byte[] a(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, InvalidKeyException {
        if (bArr == null || bArr2 == null) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, f1997a);
        Mac instance = Mac.getInstance(f1997a);
        instance.init(secretKeySpec);
        return instance.doFinal(bArr);
    }

    public static String b(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, InvalidKeyException {
        return DigestUtils.c(a(bArr, bArr2));
    }

    public static String a(String str, String str2) throws InvalidKeyException, NoSuchAlgorithmException {
        if (str == null || str2 == null) {
            return null;
        }
        return b(str.getBytes(), str2.getBytes());
    }
}
