package com.xiaomi.phonenum.utils;

import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.phonenum.utils.AESCoder;
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
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class RSAEncryptUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12579a = "X.509";
    private static final String b = "RSA/ECB/PKCS1Padding";
    private static final String c = "AES";
    private static final String d = "UTF-8";
    private static String e = "-----BEGIN CERTIFICATE-----\nMIICDzCCAXigAwIBAgIEWMX4OjANBgkqhkiG9w0BAQUFADBMMQswCQYDVQQGEwJD\nTjEPMA0GA1UEChMGeGlhb21pMQ8wDQYDVQQLEwZ4aWFvbWkxGzAZBgNVBAMTEmFj\nY291bnQueGlhb21pLmNvbTAeFw0xNzAzMTMwMTM5MDZaFw0xODAzMTMwMTM5MDZa\nMEwxCzAJBgNVBAYTAkNOMQ8wDQYDVQQKEwZ4aWFvbWkxDzANBgNVBAsTBnhpYW9t\naTEbMBkGA1UEAxMSYWNjb3VudC54aWFvbWkuY29tMIGfMA0GCSqGSIb3DQEBAQUA\nA4GNADCBiQKBgQCRDQSxAwWUmA57Isfphgl7H+QHgw9qObsvZM0Xx1YeDzKYVB62\nypGPcPfxnvD0+EfpdhbsMQYeO495BPPzFk+TsFJl4aR47k9sstxrIu7AFeFbdvGg\nubcEu3y/cAk7CcFE7aqKaW7+WFJzLaPVTj6tn0IUe7lFpHXnBFkpzZMVxwIDAQAB\nMA0GCSqGSIb3DQEBBQUAA4GBAICkoEOZ9OtLeZDSQpTqzq8GfU19C/aJCD6Ex7sl\nYqqXVn/p6AozxihEyvIilM56hyaKlLzNJdxPVRYUim6nv6r+kOwE8i7yDRAfcaZD\nnbBeTATPI7E3iKXLF64gjm3Syq8Pw30Yi2azEdB9U+57GBRa0cxAU6wfhn5GSXM6\nW+j0\n-----END CERTIFICATE-----\n";
    private static volatile PublicKey f;
    private final SecretKey g = b();
    private final String h = a(Base64.encodeToString(this.g.getEncoded(), 10), a());

    public static class EncryptResult {

        /* renamed from: a  reason: collision with root package name */
        public String f12580a;
        public String b;
    }

    public EncryptResult a(String str) throws EncryptException {
        EncryptResult encryptResult = new EncryptResult();
        encryptResult.f12580a = a(str, this.g);
        encryptResult.b = this.h;
        return encryptResult;
    }

    public String b(String str) throws EncryptException {
        return b(str, this.g);
    }

    private static PublicKey a() throws EncryptException {
        if (f != null) {
            return f;
        }
        try {
            f = ((X509Certificate) CertificateFactory.getInstance(f12579a).generateCertificate(new ByteArrayInputStream(e.getBytes("UTF-8")))).getPublicKey();
            return f;
        } catch (CertificateException e2) {
            throw new EncryptException(e2);
        } catch (UnsupportedEncodingException e3) {
            throw new EncryptException(e3);
        }
    }

    private static SecretKey b() throws EncryptException {
        try {
            KeyGenerator instance = KeyGenerator.getInstance("AES");
            new SecureRandom();
            instance.init(128);
            return instance.generateKey();
        } catch (NoSuchAlgorithmException e2) {
            throw new EncryptException(e2);
        }
    }

    private static String a(String str, SecretKey secretKey) throws EncryptException {
        try {
            return new AESCoder(secretKey.getEncoded()).b(str);
        } catch (AESCoder.CipherException e2) {
            throw new EncryptException(e2);
        }
    }

    private static String b(String str, SecretKey secretKey) throws EncryptException {
        try {
            return new AESCoder(secretKey.getEncoded()).a(str);
        } catch (AESCoder.CipherException e2) {
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

    public static void c(String str) {
        e = str;
    }
}
