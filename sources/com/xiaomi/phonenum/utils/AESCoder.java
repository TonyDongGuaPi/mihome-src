package com.xiaomi.phonenum.utils;

import android.util.Base64;
import com.coloros.mcssdk.c.a;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCoder {

    /* renamed from: a  reason: collision with root package name */
    public static final String f12577a = "AESCoder";
    public static final String b = "AES/CBC/PKCS5Padding";
    private static Logger c = LoggerManager.a();
    private static final String d = "UTF-8";
    private SecretKeySpec e;

    public AESCoder(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length != 16) {
                c.b("AESCoder", "aesKey is invalid");
            }
            this.e = new SecretKeySpec(bArr, a.b);
            return;
        }
        throw new SecurityException("aes key is null");
    }

    public String a(String str) throws CipherException {
        if (str == null) {
            c.b("AESCoder", "decrypt failed for empty data");
            return null;
        }
        try {
            return new String(a(Base64.decode(str, 2)), "UTF-8");
        } catch (Exception e2) {
            throw new CipherException("fail to decrypt by aescoder", e2);
        }
    }

    private byte[] a(byte[] bArr) throws CipherException {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, this.e, new IvParameterSpec(a()));
            if (bArr != null) {
                return instance.doFinal(bArr);
            }
            throw new IllegalBlockSizeException("no block data");
        } catch (Exception e2) {
            throw new CipherException("fail to decrypt by aescoder", e2);
        }
    }

    private byte[] a() {
        return "0102030405060708".getBytes();
    }

    public String b(String str) throws CipherException {
        try {
            return Base64.encodeToString(b(str.getBytes("UTF-8")), 10);
        } catch (Exception e2) {
            throw new CipherException("fail to encrypt by aescoder", e2);
        }
    }

    private byte[] b(byte[] bArr) throws CipherException {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, this.e, new IvParameterSpec(a()));
            return instance.doFinal(bArr);
        } catch (Exception e2) {
            throw new CipherException("fail to encrypt by aescoder", e2);
        }
    }

    public class CipherException extends Exception {
        private static final long serialVersionUID = -1479750857131098427L;

        public CipherException(String str) {
            super(str);
        }

        public CipherException(String str, Throwable th) {
            super(str, th);
        }

        public CipherException(Throwable th) {
            super(th);
        }
    }
}
