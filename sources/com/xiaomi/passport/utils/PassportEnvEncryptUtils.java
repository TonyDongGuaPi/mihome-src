package com.xiaomi.passport.utils;

import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.accountsdk.account.exception.CryptoException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.utils.AESCoder;
import com.xiaomi.accountsdk.utils.RSACoder;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class PassportEnvEncryptUtils {
    private static final String SYMMETRIC_ALGORITHM = "AES";

    public static class EncryptResult {
        public String content;
        public String encryptedKey;
    }

    public static EncryptResult encrypt(String[] strArr) throws EncryptException {
        return encrypt(TextUtils.join(":", strArr));
    }

    public static EncryptResult encrypt(String str) throws EncryptException {
        EncryptResult encryptResult = new EncryptResult();
        SecretKey generateSymmetricKey = generateSymmetricKey();
        try {
            String encodeToString = Base64.encodeToString(RSACoder.encrypt(Base64.encode(generateSymmetricKey.getEncoded(), 10), (Key) RSACoder.getCertificatePublicKey(RSACoder.SPECIFIED_RSA_PUBLIC_KEY)), 10);
            encryptResult.content = aesEncrypt(str, generateSymmetricKey);
            encryptResult.encryptedKey = encodeToString;
            return encryptResult;
        } catch (CryptoException e) {
            throw new EncryptException(e);
        }
    }

    private static SecretKey generateSymmetricKey() throws EncryptException {
        try {
            KeyGenerator instance = KeyGenerator.getInstance("AES");
            instance.init(new SecureRandom());
            return instance.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptException(e);
        }
    }

    private static String aesEncrypt(String str, SecretKey secretKey) throws EncryptException {
        try {
            return new AESCoder(secretKey.getEncoded()).encrypt(str);
        } catch (CipherException e) {
            throw new EncryptException(e);
        }
    }

    public static class EncryptException extends Exception {
        public EncryptException(Throwable th) {
            super(th);
        }
    }
}
