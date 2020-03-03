package cn.com.fmsh.util.algorithm;

import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import com.miuipub.internal.hybrid.SignUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA {
    public static final String EXPONENT_KEY = "Exponent";
    public static final String MODULUS_KEY = "Modulus";
    public static final String PRIVAET_KEY = "privateKey";
    public static final String PUBLIC_KEY = "publicKey";
    private static FMLog log = LogFactory.getInstance().getLog();

    public static Map<String, byte[]> generateKey(int i) {
        KeyPairGenerator keyPairGenerator;
        HashMap hashMap = new HashMap();
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(SignUtils.f8267a);
        } catch (NoSuchAlgorithmException e) {
            log.error(RSA.class.getName(), "RSA产生密钥出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e));
            keyPairGenerator = null;
        }
        if (keyPairGenerator == null) {
            return null;
        }
        keyPairGenerator.initialize(i, new SecureRandom());
        KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
        hashMap.put(PUBLIC_KEY, generateKeyPair.getPublic().getEncoded());
        hashMap.put(PRIVAET_KEY, generateKeyPair.getPrivate().getEncoded());
        return hashMap;
    }

    public static Map<String, BigInteger> privateKey2RSA(PrivateKey privateKey) {
        RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) privateKey;
        HashMap hashMap = new HashMap();
        hashMap.put(MODULUS_KEY, rSAPrivateKey.getModulus());
        hashMap.put(EXPONENT_KEY, rSAPrivateKey.getPrivateExponent());
        return hashMap;
    }

    public static Map<String, BigInteger> publicKey2RSA(PublicKey publicKey) {
        RSAPublicKey rSAPublicKey = (RSAPublicKey) publicKey;
        HashMap hashMap = new HashMap();
        hashMap.put(MODULUS_KEY, rSAPublicKey.getModulus());
        hashMap.put(EXPONENT_KEY, rSAPublicKey.getPublicExponent());
        return hashMap;
    }

    public static byte[] sign(byte[] bArr, PrivateKey privateKey) {
        try {
            Signature instance = Signature.getInstance(SignUtils.c);
            instance.initSign(privateKey);
            instance.update(bArr);
            return instance.sign();
        } catch (NoSuchAlgorithmException e) {
            log.error(RSA.class.getName(), "私钥签名异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e));
            return null;
        } catch (InvalidKeyException e2) {
            log.error(RSA.class.getName(), "私钥签名异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e2));
            return null;
        } catch (SignatureException e3) {
            log.error(RSA.class.getName(), "私钥签名异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e3));
            return null;
        }
    }

    public static byte[] sign(byte[] bArr, byte[] bArr2) {
        PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(bArr2);
        try {
            KeyFactory instance = KeyFactory.getInstance(SignUtils.f8267a);
            Signature instance2 = Signature.getInstance(SignUtils.c);
            instance2.initSign(instance.generatePrivate(pKCS8EncodedKeySpec));
            instance2.update(bArr);
            return instance2.sign();
        } catch (NoSuchAlgorithmException e) {
            log.error(RSA.class.getName(), "私钥签名异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e));
            return null;
        } catch (InvalidKeySpecException e2) {
            log.error(RSA.class.getName(), "私钥签名异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e2));
            return null;
        } catch (InvalidKeyException e3) {
            log.error(RSA.class.getName(), "私钥签名异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e3));
            return null;
        } catch (SignatureException e4) {
            log.error(RSA.class.getName(), "私钥签名异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e4));
            return null;
        }
    }

    public static byte[] sign(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        RSAPrivateKeySpec rSAPrivateKeySpec = new RSAPrivateKeySpec(new BigInteger(1, bArr2), new BigInteger(1, bArr3));
        try {
            KeyFactory instance = KeyFactory.getInstance(SignUtils.f8267a);
            Signature instance2 = Signature.getInstance(SignUtils.c);
            instance2.initSign(instance.generatePrivate(rSAPrivateKeySpec));
            instance2.update(bArr);
            return instance2.sign();
        } catch (NoSuchAlgorithmException e) {
            log.error(RSA.class.getName(), "私钥签名异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e));
            return null;
        } catch (InvalidKeySpecException e2) {
            log.error(RSA.class.getName(), "私钥签名异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e2));
            return null;
        } catch (InvalidKeyException e3) {
            log.error(RSA.class.getName(), "私钥签名异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e3));
            return null;
        } catch (SignatureException e4) {
            log.error(RSA.class.getName(), "私钥签名异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e4));
            return null;
        }
    }

    public static boolean verify(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        RSAPublicKeySpec rSAPublicKeySpec = new RSAPublicKeySpec(new BigInteger(1, bArr2), new BigInteger(1, bArr3));
        try {
            KeyFactory instance = KeyFactory.getInstance(SignUtils.f8267a);
            Signature instance2 = Signature.getInstance(SignUtils.c);
            instance2.initVerify(instance.generatePublic(rSAPublicKeySpec));
            instance2.update(bArr);
            return instance2.verify(bArr4);
        } catch (NoSuchAlgorithmException e) {
            log.error(RSA.class.getName(), "公钥验签异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e));
            return false;
        } catch (InvalidKeySpecException e2) {
            log.error(RSA.class.getName(), "公钥验签异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e2));
            return false;
        } catch (InvalidKeyException e3) {
            log.error(RSA.class.getName(), "公钥验签异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e3));
            return false;
        } catch (SignatureException e4) {
            log.error(RSA.class.getName(), "公钥验签异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e4));
            return false;
        }
    }

    public static byte[] decrtyByPrivate(byte[] bArr, byte[] bArr2, byte[] bArr3, boolean z) {
        if (bArr == null || bArr.length < 1 || bArr2 == null || bArr2.length < 1 || bArr3 == null || bArr3.length < 1) {
            return null;
        }
        RSAPrivateKeySpec rSAPrivateKeySpec = new RSAPrivateKeySpec(new BigInteger(1, bArr), new BigInteger(1, bArr2));
        try {
            log.debug(RSA.class.getName(), "非对称解密算法的名称是RSA");
            return doFinal(2, KeyFactory.getInstance(SignUtils.f8267a).generatePrivate(rSAPrivateKeySpec), bArr3, z);
        } catch (NoSuchAlgorithmException e) {
            log.error(RSA.class.getName(), "RSA私钥解密出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e));
            return null;
        } catch (InvalidKeySpecException e2) {
            log.error(RSA.class.getName(), "RSA私钥解密出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e2));
            return null;
        }
    }

    public static byte[] encrtyByPrivate(byte[] bArr, byte[] bArr2, byte[] bArr3, boolean z) {
        PrivateKey privateKey;
        if (bArr == null || bArr2.length < 1 || bArr == null || bArr.length < 1 || bArr3 == null || bArr3.length < 1) {
            return null;
        }
        try {
            privateKey = KeyFactory.getInstance(SignUtils.f8267a).generatePrivate(new RSAPrivateKeySpec(new BigInteger(1, bArr), new BigInteger(1, bArr2)));
        } catch (NoSuchAlgorithmException e) {
            log.error(RSA.class.getName(), "RSA私钥加密出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e));
            privateKey = null;
            return doFinal(1, privateKey, bArr3, z);
        } catch (InvalidKeySpecException e2) {
            log.error(RSA.class.getName(), "RSA私钥加密出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e2));
            privateKey = null;
            return doFinal(1, privateKey, bArr3, z);
        }
        return doFinal(1, privateKey, bArr3, z);
    }

    public static byte[] decryptByPrivate(byte[] bArr, byte[] bArr2, boolean z) {
        PrivateKey privateKey;
        try {
            privateKey = KeyFactory.getInstance(SignUtils.f8267a).generatePrivate(new PKCS8EncodedKeySpec(bArr));
        } catch (NoSuchAlgorithmException e) {
            log.error(RSA.class.getName(), "RSA解密时出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e));
            privateKey = null;
            return doFinal(2, privateKey, bArr2, z);
        } catch (InvalidKeySpecException e2) {
            log.error(RSA.class.getName(), "RSA解密时出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e2));
            privateKey = null;
            return doFinal(2, privateKey, bArr2, z);
        }
        return doFinal(2, privateKey, bArr2, z);
    }

    public static byte[] encrtyByPrivate(byte[] bArr, byte[] bArr2, boolean z) {
        PrivateKey privateKey;
        try {
            privateKey = KeyFactory.getInstance(SignUtils.f8267a).generatePrivate(new PKCS8EncodedKeySpec(bArr));
        } catch (NoSuchAlgorithmException e) {
            log.error(RSA.class.getName(), "RSA私钥加密出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e));
            privateKey = null;
            return doFinal(1, privateKey, bArr2, z);
        } catch (InvalidKeySpecException e2) {
            log.error(RSA.class.getName(), "RSA私钥加密出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e2));
            privateKey = null;
            return doFinal(1, privateKey, bArr2, z);
        }
        return doFinal(1, privateKey, bArr2, z);
    }

    public static byte[] encrtyByPublic(byte[] bArr, byte[] bArr2, boolean z) {
        PublicKey publicKey;
        try {
            publicKey = KeyFactory.getInstance(SignUtils.f8267a).generatePublic(new X509EncodedKeySpec(bArr));
        } catch (NoSuchAlgorithmException e) {
            log.error(RSA.class.getName(), "RSA私钥加密出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e));
            publicKey = null;
            return doFinal(1, publicKey, bArr2, z);
        } catch (InvalidKeySpecException e2) {
            log.error(RSA.class.getName(), "RSA私钥加密出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e2));
            publicKey = null;
            return doFinal(1, publicKey, bArr2, z);
        }
        return doFinal(1, publicKey, bArr2, z);
    }

    public static byte[] encrtyByPublic(byte[] bArr, byte[] bArr2, byte[] bArr3, boolean z) {
        PublicKey publicKey;
        if (bArr == null || bArr.length < 1 || bArr2 == null || bArr2.length < 1 || bArr3 == null || bArr3.length < 1) {
            return null;
        }
        try {
            publicKey = KeyFactory.getInstance(SignUtils.f8267a).generatePublic(new RSAPublicKeySpec(new BigInteger(1, bArr), new BigInteger(1, bArr2)));
        } catch (NoSuchAlgorithmException e) {
            log.error(RSA.class.getName(), "RSA公钥加密出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e));
            publicKey = null;
            return doFinal(1, publicKey, bArr3, z);
        } catch (InvalidKeySpecException e2) {
            log.error(RSA.class.getName(), "RSA公钥加密出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e2));
            publicKey = null;
            return doFinal(1, publicKey, bArr3, z);
        }
        return doFinal(1, publicKey, bArr3, z);
    }

    public static byte[] decryptByPublic(byte[] bArr, byte[] bArr2, byte[] bArr3, boolean z) {
        PublicKey publicKey;
        if (bArr == null || bArr.length < 1 || bArr2 == null || bArr2.length < 1 || bArr3 == null || bArr3.length < 1) {
            return null;
        }
        try {
            publicKey = KeyFactory.getInstance(SignUtils.f8267a).generatePublic(new RSAPublicKeySpec(new BigInteger(1, bArr), new BigInteger(1, bArr2)));
        } catch (NoSuchAlgorithmException e) {
            log.error(RSA.class.getName(), "RSA解密时出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e));
            publicKey = null;
            return doFinal(2, publicKey, bArr3, z);
        } catch (InvalidKeySpecException e2) {
            log.error(RSA.class.getName(), "RSA解密时出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e2));
            publicKey = null;
            return doFinal(2, publicKey, bArr3, z);
        }
        return doFinal(2, publicKey, bArr3, z);
    }

    public static byte[] decryptByPublic(PublicKey publicKey, byte[] bArr, boolean z) {
        return doFinal(2, publicKey, bArr, z);
    }

    public static byte[] encryptByPublic(PublicKey publicKey, byte[] bArr, boolean z) {
        return doFinal(1, publicKey, bArr, z);
    }

    public static byte[] encrtyByPrivate(PrivateKey privateKey, byte[] bArr, boolean z) {
        return doFinal(1, privateKey, bArr, z);
    }

    private static byte[] doFinal(int i, Key key, byte[] bArr, boolean z) {
        NoSuchAlgorithmException e;
        NoSuchPaddingException e2;
        InvalidKeyException e3;
        IllegalBlockSizeException e4;
        BadPaddingException e5;
        IOException e6;
        Cipher cipher;
        byte[] bArr2;
        byte[] bArr3 = null;
        if (z) {
            try {
                cipher = Cipher.getInstance(SignUtils.b);
            } catch (NoSuchAlgorithmException e7) {
                e = e7;
                log.error(RSA.class.getName(), "RSA加/解密时出现异常");
                log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e));
                return bArr3;
            } catch (NoSuchPaddingException e8) {
                e2 = e8;
                log.error(RSA.class.getName(), "RSA加/解密时出现异常");
                log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e2));
                return bArr3;
            } catch (InvalidKeyException e9) {
                e3 = e9;
                log.error(RSA.class.getName(), "RSA加/解密时出现异常");
                log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e3));
                return bArr3;
            } catch (IllegalBlockSizeException e10) {
                e4 = e10;
                log.error(RSA.class.getName(), "RSA加/解密时出现异常");
                log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e4));
                return bArr3;
            } catch (BadPaddingException e11) {
                e5 = e11;
                log.error(RSA.class.getName(), "RSA加/解密时出现异常");
                log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e5));
                return bArr3;
            } catch (IOException e12) {
                e6 = e12;
                log.error(RSA.class.getName(), "RSA加/解密时出现异常");
                log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e6));
                return bArr3;
            }
        } else {
            cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        }
        cipher.init(i, key);
        int i2 = i == 2 ? 128 : 0;
        if (i == 1) {
            i2 = 117;
        }
        int length = bArr.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i3 = 0;
        while (true) {
            int i4 = length - i3;
            if (i4 <= 0) {
                break;
            }
            if (i4 > i2) {
                bArr2 = cipher.doFinal(bArr, i3, i2);
            } else {
                bArr2 = cipher.doFinal(bArr, i3, i4);
            }
            byteArrayOutputStream.write(bArr2, 0, bArr2.length);
            i3 += i2;
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
            if (byteArray == null) {
                return byteArray;
            }
            int i5 = 0;
            while (true) {
                if (i5 >= byteArray.length) {
                    i5 = 0;
                    break;
                } else if (byteArray[i5] != 0) {
                    break;
                } else {
                    i5++;
                }
            }
            return FM_Bytes.copyOfRange(byteArray, i5, byteArray.length);
        } catch (NoSuchAlgorithmException e13) {
            bArr3 = byteArray;
            e = e13;
            log.error(RSA.class.getName(), "RSA加/解密时出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e));
            return bArr3;
        } catch (NoSuchPaddingException e14) {
            bArr3 = byteArray;
            e2 = e14;
            log.error(RSA.class.getName(), "RSA加/解密时出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e2));
            return bArr3;
        } catch (InvalidKeyException e15) {
            bArr3 = byteArray;
            e3 = e15;
            log.error(RSA.class.getName(), "RSA加/解密时出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e3));
            return bArr3;
        } catch (IllegalBlockSizeException e16) {
            bArr3 = byteArray;
            e4 = e16;
            log.error(RSA.class.getName(), "RSA加/解密时出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e4));
            return bArr3;
        } catch (BadPaddingException e17) {
            bArr3 = byteArray;
            e5 = e17;
            log.error(RSA.class.getName(), "RSA加/解密时出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e5));
            return bArr3;
        } catch (IOException e18) {
            bArr3 = byteArray;
            e6 = e18;
            log.error(RSA.class.getName(), "RSA加/解密时出现异常");
            log.error(RSA.class.getName(), Util4Java.getExceptionInfo(e6));
            return bArr3;
        }
    }

    public static void main(String[] strArr) {
        System.out.println(FM_Bytes.bytesToHexString(encrtyByPublic(FM_Bytes.hexStringToBytes("CC5FFBB1DC42D3233AC74E7CF43031DFEADC18EA8A3059D406F307943B886E6E313A7F45D51F89BD46D8F0B556B2B6C336C783951015FC02E3438B11BDB0AB24A0A411103F44EBAA494137CEA82C3C29C0262EB54AD0ADB3FB49C211A0B36E05D1D98F7511DDA91A89341E98A4F559B99C209028DFDDACE7DD1026E9DEF090F3"), FM_Bytes.hexStringToBytes("010001"), FM_Bytes.hexStringToBytes("000102030405060708090001"), true)));
    }
}
