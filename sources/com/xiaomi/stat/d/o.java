package com.xiaomi.stat.d;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class o {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23062a = "RsaUtils";
    private static final String b = "RSA/ECB/PKCS1Padding";
    private static final String c = "BC";
    private static final String d = "RSA";

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            RSAPublicKey a2 = a(bArr);
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, a2);
            return instance.doFinal(bArr2);
        } catch (Exception e) {
            k.d(f23062a, "RsaUtils encrypt exception:", e);
            return null;
        }
    }

    private static RSAPublicKey a(byte[] bArr) throws Exception {
        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr));
    }
}
