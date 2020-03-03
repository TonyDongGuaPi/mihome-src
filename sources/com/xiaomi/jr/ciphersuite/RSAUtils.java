package com.xiaomi.jr.ciphersuite;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class RSAUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10356a = "RSAUtils";
    private static String b = null;
    private static final String c = "RSA";
    private static final String d = "SHA1WithRSA";
    private static final String e = "utf-8";

    public static void a(String str) {
        b = str;
    }

    public static PrivateKey b(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Utils.b(str)));
        } catch (Exception e2) {
            Utils.a(f10356a, "get private key failed", e2);
            return null;
        }
    }

    public static PublicKey c(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Utils.b(str)));
        } catch (Exception e2) {
            Utils.a(f10356a, "get public key failed", e2);
            return null;
        }
    }

    public static PublicKey a() {
        return c(b);
    }

    public static String a(PublicKey publicKey, String str) {
        try {
            Cipher instance = Cipher.getInstance("RSA/None/PKCS1Padding");
            instance.init(1, publicKey);
            return Utils.a(instance.doFinal(str.getBytes()));
        } catch (Exception e2) {
            Utils.a(f10356a, "encrypt failed", e2);
            return null;
        }
    }

    public static String a(PrivateKey privateKey, String str) {
        try {
            Cipher instance = Cipher.getInstance("RSA/None/PKCS1Padding");
            instance.init(2, privateKey);
            return new String(instance.doFinal(Utils.b(str)));
        } catch (Exception e2) {
            Utils.a(f10356a, "decrypt failed", e2);
            return null;
        }
    }

    public static String a(String str, String str2) {
        try {
            PrivateKey b2 = b(str2);
            Signature instance = Signature.getInstance("SHA1WithRSA");
            instance.initSign(b2);
            instance.update(str.getBytes("utf-8"));
            return new String(Utils.a(instance.sign()));
        } catch (Exception e2) {
            Utils.a(f10356a, "sign failed", e2);
            return null;
        }
    }

    public static boolean a(String str, String str2, String str3) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Utils.b(str3)));
            Signature instance = Signature.getInstance("SHA1WithRSA");
            instance.initVerify(generatePublic);
            instance.update(str.getBytes("utf-8"));
            return instance.verify(Utils.b(str2));
        } catch (Exception e2) {
            Utils.a(f10356a, "verify sign failed", e2);
            return false;
        }
    }
}
