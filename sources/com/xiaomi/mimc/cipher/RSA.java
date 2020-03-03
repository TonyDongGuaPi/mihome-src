package com.xiaomi.mimc.cipher;

import com.xiaomi.msg.logger.MIMCLog;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class RSA {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11163a = "RSACoder";
    private static final String b = "RSA";
    private static final String c = "RSA/ECB/PKCS1Padding";

    public static KeyPair a() {
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
            instance.initialize(1024);
            return instance.generateKeyPair();
        } catch (Exception e) {
            MIMCLog.d(f11163a, "RSA genKeyPair error, ", e);
            return null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, short s, short s2) {
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr2);
            byte[] bArr3 = new byte[(s + 1)];
            wrap.get(bArr3, 1, s);
            byte[] bArr4 = new byte[s2];
            wrap.get(bArr4, 0, s2);
            return a(bArr, b(bArr3, bArr4));
        } catch (Exception e) {
            MIMCLog.d(f11163a, String.format("RSA encrypt error, publicKeyParams=%s, moduleLen=%d, publicExponentLen=%d", new Object[]{new String(bArr2), Short.valueOf(s), Short.valueOf(s2)}), e);
            return null;
        }
    }

    private static byte[] a(byte[] bArr, PublicKey publicKey) {
        byte[] bArr2;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, publicKey);
            int bitLength = (((RSAPublicKey) publicKey).getModulus().bitLength() / 8) - 11;
            int length = bArr.length;
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = length - i;
                if (i3 <= 0) {
                    break;
                }
                if (i3 > bitLength) {
                    bArr2 = instance.doFinal(bArr, i, bitLength);
                } else {
                    bArr2 = instance.doFinal(bArr, i, i3);
                }
                byteArrayOutputStream.write(bArr2, 0, bArr2.length);
                i2++;
                i = i2 * bitLength;
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Exception e) {
                MIMCLog.d(f11163a, "RSA encrypt close output stream error, ", e);
            }
            return byteArray;
        } catch (Exception e2) {
            MIMCLog.d(f11163a, "RSA encrypt error, ", e2);
            try {
                byteArrayOutputStream.close();
            } catch (Exception e3) {
                MIMCLog.d(f11163a, "RSA encrypt close output stream error, ", e3);
            }
            return null;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Exception e4) {
                MIMCLog.d(f11163a, "RSA encrypt close output stream error, ", e4);
            }
            throw th;
        }
    }

    private static PublicKey b(byte[] bArr, byte[] bArr2) throws Exception {
        return KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(1, bArr), new BigInteger(bArr2)));
    }

    public static PublicKey a(byte[] bArr) throws Exception {
        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr));
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PrivateKey b2 = b(bArr2);
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(2, b2);
            int bitLength = ((RSAPrivateKey) b2).getModulus().bitLength() / 8;
            int length = bArr.length;
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = length - i;
                if (i3 <= 0) {
                    break;
                }
                if (i3 > bitLength) {
                    bArr3 = instance.doFinal(bArr, i, bitLength);
                } else {
                    bArr3 = instance.doFinal(bArr, i, i3);
                }
                byteArrayOutputStream.write(bArr3, 0, bArr3.length);
                i2++;
                i = i2 * bitLength;
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Exception e) {
                MIMCLog.d(f11163a, "RSA decrypt close output stream error, ", e);
            }
            MIMCLog.a(f11163a, "finish decrypt!");
            return byteArray;
        } catch (Exception e2) {
            MIMCLog.d(f11163a, "RSA decrypt error, ", e2);
            try {
                byteArrayOutputStream.close();
            } catch (Exception e3) {
                MIMCLog.d(f11163a, "RSA decrypt close output stream error, ", e3);
            }
            MIMCLog.a(f11163a, "finish decrypt!");
            return null;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Exception e4) {
                MIMCLog.d(f11163a, "RSA decrypt close output stream error, ", e4);
            }
            MIMCLog.a(f11163a, "finish decrypt!");
            throw th;
        }
    }

    private static PrivateKey b(byte[] bArr) throws Exception {
        return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(bArr));
    }
}
