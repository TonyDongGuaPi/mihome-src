package com.alipay.mobile.security.bio.security;

import com.alipay.mobile.security.bio.utils.BioLog;
import com.coloros.mcssdk.c.a;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncrypt {
    public static final String VIPARA = "4306020520119888";

    public static byte[] encrypt(String str, String str2) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec("4306020520119888".getBytes());
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, new SecretKeySpec(str2.getBytes(), a.b), ivParameterSpec);
            return instance.doFinal(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            BioLog.e(e.toString());
            return null;
        } catch (NoSuchPaddingException e2) {
            BioLog.e(e2.toString());
            return null;
        } catch (InvalidKeyException e3) {
            BioLog.e(e3.toString());
            return null;
        } catch (IllegalBlockSizeException e4) {
            BioLog.e(e4.toString());
            return null;
        } catch (BadPaddingException e5) {
            BioLog.e(e5.toString());
            return null;
        } catch (InvalidAlgorithmParameterException e6) {
            BioLog.e(e6.toString());
            return null;
        }
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec("4306020520119888".getBytes());
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, new SecretKeySpec(bArr2, a.b), ivParameterSpec);
            return instance.doFinal(bArr);
        } catch (NoSuchAlgorithmException e) {
            BioLog.e(e.toString());
            return null;
        } catch (NoSuchPaddingException e2) {
            BioLog.e(e2.toString());
            return null;
        } catch (InvalidKeyException e3) {
            BioLog.e(e3.toString());
            return null;
        } catch (IllegalBlockSizeException e4) {
            BioLog.e(e4.toString());
            return null;
        } catch (BadPaddingException e5) {
            BioLog.e(e5.toString());
            return null;
        } catch (InvalidAlgorithmParameterException e6) {
            BioLog.e(e6.toString());
            return null;
        }
    }

    public static byte[] decrypt(byte[] bArr, String str) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec("4306020520119888".getBytes());
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, new SecretKeySpec(str.getBytes(), a.b), ivParameterSpec);
            return instance.doFinal(bArr);
        } catch (NoSuchAlgorithmException e) {
            BioLog.e(e.toString());
            return null;
        } catch (NoSuchPaddingException e2) {
            BioLog.e(e2.toString());
            return null;
        } catch (InvalidKeyException e3) {
            BioLog.e(e3.toString());
            return null;
        } catch (IllegalBlockSizeException e4) {
            BioLog.e(e4.toString());
            return null;
        } catch (BadPaddingException e5) {
            BioLog.e(e5.toString());
            return null;
        } catch (InvalidAlgorithmParameterException e6) {
            BioLog.e(e6.toString());
            return null;
        }
    }

    public static byte[] decrypt(byte[] bArr, byte[] bArr2) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec("4306020520119888".getBytes());
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, new SecretKeySpec(bArr2, a.b), ivParameterSpec);
            return instance.doFinal(bArr);
        } catch (NoSuchAlgorithmException e) {
            BioLog.e(e.toString());
            return null;
        } catch (NoSuchPaddingException e2) {
            BioLog.e(e2.toString());
            return null;
        } catch (InvalidKeyException e3) {
            BioLog.e(e3.toString());
            return null;
        } catch (IllegalBlockSizeException e4) {
            BioLog.e(e4.toString());
            return null;
        } catch (BadPaddingException e5) {
            BioLog.e(e5.toString());
            return null;
        } catch (InvalidAlgorithmParameterException e6) {
            BioLog.e(e6.toString());
            return null;
        }
    }
}
