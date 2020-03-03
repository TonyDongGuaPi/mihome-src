package com.xiaomi.verificationsdk.internal;

import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.utils.AESCoder;
import com.xiaomi.passport.utils.PassportEnvEncryptUtils;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.UUID;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class EnvEncryptUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23120a = "X.509";
    private static final String b = "RSA/ECB/PKCS1Padding";
    private static final String c = "AES";
    private static final String d = "UTF-8";
    private static String e = "-----BEGIN CERTIFICATE-----\nMIIDXTCCAkWgAwIBAgIJAOMjETkYAg3lMA0GCSqGSIb3DQEBCwUAMEUxCzAJBgNV\nBAYTAkFVMRMwEQYDVQQIDApTb21lLVN0YXRlMSEwHwYDVQQKDBhJbnRlcm5ldCBX\naWRnaXRzIFB0eSBMdGQwHhcNMTgwMTExMDk0ODQ3WhcNMTgwMjEwMDk0ODQ3WjBF\nMQswCQYDVQQGEwJBVTETMBEGA1UECAwKU29tZS1TdGF0ZTEhMB8GA1UECgwYSW50\nZXJuZXQgV2lkZ2l0cyBQdHkgTHRkMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIB\nCgKCAQEArxfNLkuAQ/BYHzkzVwtug+0abmYRBVCEScSzGxJIOsfxVzcuqaKO87H2\no2wBcacD3bRHhMjTkhSEqxPjQ/FEXuJ1cdbmr3+b3EQR6wf/cYcMx2468/QyVoQ7\nBADLSPecQhtgGOllkC+cLYN6Md34Uii6U+VJf0p0q/saxUTZvhR2ka9fqJ4+6C6c\nOghIecjMYQNHIaNW+eSKunfFsXVU+QfMD0q2EM9wo20aLnos24yDzRjh9HJc6xfr\n37jRlv1/boG/EABMG9FnTm35xWrVR0nw3cpYF7GZg13QicS/ZwEsSd4HyboAruMx\nJBPvK3Jdr4ZS23bpN0cavWOJsBqZVwIDAQABo1AwTjAdBgNVHQ4EFgQU0AWcCdJQ\nruwyc3Hjt2dEmYXmUCUwHwYDVR0jBBgwFoAU0AWcCdJQruwyc3Hjt2dEmYXmUCUw\nDAYDVR0TBAUwAwEB/zANBgkqhkiG9w0BAQsFAAOCAQEASssjoOG4RbRjy6ivCDkZ\nteOLur7jQ6/AZru8n8Fow8WGd9zM1uFZs9+c6okvlmUeCPuOiYDxps6xfL23hkQc\nKghkMofjBn2eLoqTiQ+woAoZHAqLuiFUHae85KH7185EV8NTm2LpJJoqh2x5KU4Q\nlCWm+5uadtqPEbO9wmI1er5Kob/njPI0QtQn2/5H9u7t71MCjHIGuQ5OSrZmmyzV\nTFUVbdkf0mwoL44JO7ErbdOQ/nq+dElYiGnq7tI9gdt78k3Pgi7ykU5W7pnDbxLN\no66M6umwCy2ies0zzsxS9pKzurddXd+Zt4NBh74MV0RCf2PbDCtUODTBowLeDluq\nXw==\n-----END CERTIFICATE-----\n";
    private static volatile PublicKey f;
    private static volatile SecretKey g;
    private static volatile String h;

    public static class EncryptResult {

        /* renamed from: a  reason: collision with root package name */
        public String f23121a;
        public String b;
    }

    public static EncryptResult a(String[] strArr) throws EncryptException {
        return a(TextUtils.join(":", strArr));
    }

    public static EncryptResult a(String str) throws EncryptException {
        EncryptResult encryptResult = new EncryptResult();
        synchronized (PassportEnvEncryptUtils.class) {
            if (g == null || h == null) {
                g = c();
                h = a(Base64.encodeToString(g.getEncoded(), 10), b());
            }
        }
        encryptResult.f23121a = a(str, g);
        encryptResult.b = h;
        return encryptResult;
    }

    private static PublicKey b() throws EncryptException {
        if (f != null) {
            return f;
        }
        try {
            f = ((X509Certificate) CertificateFactory.getInstance(f23120a).generateCertificate(new ByteArrayInputStream(e.getBytes("UTF-8")))).getPublicKey();
            return f;
        } catch (CertificateException e2) {
            throw new EncryptException(e2);
        } catch (UnsupportedEncodingException e3) {
            throw new EncryptException(e3);
        }
    }

    private static SecretKey c() throws EncryptException {
        try {
            KeyGenerator instance = KeyGenerator.getInstance("AES");
            instance.init(128, new SecureRandom());
            return instance.generateKey();
        } catch (NoSuchAlgorithmException e2) {
            throw new EncryptException(e2);
        }
    }

    private static String a(String str, SecretKey secretKey) throws EncryptException {
        try {
            return new AESCoder(secretKey.getEncoded()).encrypt(str);
        } catch (CipherException e2) {
            throw new EncryptException(e2);
        }
    }

    private static String a(String str, PublicKey publicKey) throws EncryptException {
        return a(str, (Key) publicKey, "RSA/ECB/PKCS1Padding");
    }

    private static String a(String str, Key key, String str2) throws EncryptException {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return new String(Base64.encode(a(str.getBytes("UTF-8"), key, str2), 10), "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            throw new EncryptException(e2);
        }
    }

    private static byte[] a(byte[] bArr, Key key, String str) throws EncryptException {
        try {
            Cipher instance = Cipher.getInstance(str);
            instance.init(1, key);
            return instance.doFinal(bArr);
        } catch (IllegalBlockSizeException e2) {
            throw new EncryptException(e2);
        } catch (BadPaddingException e3) {
            throw new EncryptException(e3);
        } catch (NoSuchAlgorithmException e4) {
            throw new EncryptException(e4);
        } catch (NoSuchPaddingException e5) {
            throw new EncryptException(e5);
        } catch (InvalidKeyException e6) {
            throw new EncryptException(e6);
        }
    }

    public static class EncryptException extends Exception {
        public EncryptException(Throwable th) {
            super(th);
        }
    }

    static void b(String str) {
        e = str;
    }

    public static String a() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }
}
