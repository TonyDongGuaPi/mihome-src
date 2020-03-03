package com.xiaomi.youpin.common.util.crypto.rc4coder;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class RC4Coder {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23300a = "RC4";
    private SecretKeySpec b;

    public RC4Coder(byte[] bArr) throws SecurityException {
        if (c(bArr)) {
            throw new SecurityException("rc4 key is null");
        } else if (bArr.length == 16) {
            this.b = new SecretKeySpec(bArr, "RC4");
        } else {
            throw new IllegalArgumentException("rc4Key is invalid");
        }
    }

    private static boolean c(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    public byte[] a(byte[] bArr) throws SecurityException {
        try {
            Cipher instance = Cipher.getInstance("RC4");
            instance.init(2, this.b);
            if (bArr != null) {
                return instance.doFinal(bArr);
            }
            throw new IllegalBlockSizeException("no block data");
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException(e);
        } catch (NoSuchPaddingException e2) {
            throw new SecurityException(e2);
        } catch (InvalidKeyException e3) {
            throw new SecurityException(e3);
        } catch (IllegalBlockSizeException e4) {
            throw new SecurityException(e4);
        } catch (BadPaddingException e5) {
            throw new SecurityException(e5);
        }
    }

    public byte[] b(byte[] bArr) throws SecurityException {
        try {
            Cipher instance = Cipher.getInstance("RC4");
            instance.init(1, this.b);
            return instance.doFinal(bArr);
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException(e);
        } catch (NoSuchPaddingException e2) {
            throw new SecurityException(e2);
        } catch (InvalidKeyException e3) {
            throw new SecurityException(e3);
        } catch (IllegalBlockSizeException e4) {
            throw new SecurityException(e4);
        } catch (BadPaddingException e5) {
            throw new SecurityException(e5);
        }
    }
}
