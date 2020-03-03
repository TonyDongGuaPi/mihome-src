package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils;

import com.coloros.mcssdk.c.a;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1.ASN1Encodable;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1.ASN1EncodableVector;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1.ASN1Integer;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1.DEROutputStream;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1.DERSequence;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.CipherParameters;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.InvalidCipherTextException;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.engines.AESEngine;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.modes.CCMBlockCipher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.params.AEADParameters;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.params.KeyParameter;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.IOUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SecurityChipUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14468a = 32;

    public static KeyPair a() {
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance("EC");
            instance.initialize(new ECGenParameterSpec("secp256r1"));
            return instance.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidAlgorithmParameterException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] a(PublicKey publicKey) {
        byte[] b = ECCPointConvert.b((ECPublicKey) publicKey);
        return b.length == 65 ? Arrays.copyOfRange(b, 1, 65) : b;
    }

    public static SecretKey a(PublicKey publicKey, PrivateKey privateKey) {
        try {
            KeyAgreement instance = KeyAgreement.getInstance("ECDH");
            instance.init(privateKey);
            instance.doPhase(publicKey, true);
            return instance.generateSecret("ECDH");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static AESEngine a(SecretKey secretKey, boolean z) {
        AESEngine aESEngine = new AESEngine();
        aESEngine.a(z, (CipherParameters) new KeyParameter(secretKey.getEncoded()));
        return aESEngine;
    }

    private static CCMBlockCipher a(SecretKey secretKey, boolean z, byte[] bArr, byte[] bArr2) {
        CCMBlockCipher cCMBlockCipher = new CCMBlockCipher(a(secretKey, z));
        cCMBlockCipher.a(z, (CipherParameters) new AEADParameters(new KeyParameter(secretKey.getEncoded()), 32, bArr, bArr2));
        return cCMBlockCipher;
    }

    public static AuthenticatedCipherText a(SecretKey secretKey, byte[] bArr, byte[] bArr2, byte[] bArr3) throws InvalidCipherTextException {
        CCMBlockCipher a2 = a(secretKey, true, bArr, bArr3);
        byte[] bArr4 = new byte[a2.b(bArr2.length)];
        int a3 = a2.a(bArr2, 0, bArr2.length, bArr4, 0);
        int a4 = (a3 + a2.a(bArr4, a3)) - 4;
        byte[] bArr5 = new byte[a4];
        byte[] bArr6 = new byte[4];
        System.arraycopy(bArr4, 0, bArr5, 0, bArr5.length);
        System.arraycopy(bArr4, a4, bArr6, 0, bArr6.length);
        return new AuthenticatedCipherText(bArr5, bArr6);
    }

    public static byte[] a(SecretKey secretKey, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws InvalidCipherTextException {
        CCMBlockCipher a2 = a(secretKey, false, bArr, bArr3);
        byte[] bArr5 = new byte[(bArr2.length + bArr4.length)];
        System.arraycopy(bArr2, 0, bArr5, 0, bArr2.length);
        System.arraycopy(bArr4, 0, bArr5, bArr2.length, bArr4.length);
        byte[] bArr6 = new byte[a2.b(bArr5.length)];
        a2.a(bArr6, a2.a(bArr5, 0, bArr5.length, bArr6, 0));
        return bArr6;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        try {
            return a((SecretKey) new SecretKeySpec(bArr, a.b), bArr2, bArr3, (byte[]) null).c();
        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        try {
            return a(new SecretKeySpec(bArr, a.b), bArr2, Arrays.copyOfRange(bArr3, 0, bArr3.length - 4), (byte[]) null, Arrays.copyOfRange(bArr3, bArr3.length - 4, bArr3.length));
        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] a(byte[] bArr) {
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, bArr.length / 2);
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr, bArr.length / 2, bArr.length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DEROutputStream dEROutputStream = new DEROutputStream(byteArrayOutputStream);
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a((ASN1Encodable) new ASN1Integer(new BigInteger(1, copyOfRange)));
        aSN1EncodableVector.a((ASN1Encodable) new ASN1Integer(new BigInteger(1, copyOfRange2)));
        try {
            dEROutputStream.a(new DERSequence(aSN1EncodableVector));
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            dEROutputStream.b();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            byteArrayOutputStream.close();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        return byteArray;
    }

    public static boolean a(byte[] bArr, byte[] bArr2, PublicKey publicKey) {
        try {
            Signature instance = Signature.getInstance("SHA256WithECDSA");
            instance.initVerify(publicKey);
            instance.update(bArr);
            return instance.verify(bArr2);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        } catch (InvalidKeyException e2) {
            e2.printStackTrace();
            return false;
        } catch (SignatureException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public static byte[] a(String str) {
        byte[] bArr = new byte[6];
        String[] split = str.split("[:]");
        int length = split.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            bArr[i2] = (byte) Integer.parseInt(split[i], 16);
            i++;
            i2++;
        }
        return bArr;
    }

    public static X509Certificate b() {
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            InputStream open = BluetoothUtils.n().getAssets().open("MijiaRootCert.der");
            X509Certificate x509Certificate = (X509Certificate) instance.generateCertificate(open);
            open.close();
            return x509Certificate;
        } catch (CertificateException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static javax.security.cert.X509Certificate c() {
        Throwable th;
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
            inputStream = BluetoothUtils.n().getAssets().open("MijiaRootCert.der");
            try {
                javax.security.cert.X509Certificate instance = javax.security.cert.X509Certificate.getInstance(inputStream);
                IOUtils.a(inputStream);
                return instance;
            } catch (Exception e) {
                e = e;
                try {
                    e.printStackTrace();
                    IOUtils.a(inputStream);
                    return null;
                } catch (Throwable th2) {
                    InputStream inputStream3 = inputStream;
                    th = th2;
                    inputStream2 = inputStream3;
                    IOUtils.a(inputStream2);
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            inputStream = null;
            e.printStackTrace();
            IOUtils.a(inputStream);
            return null;
        } catch (Throwable th3) {
            th = th3;
            IOUtils.a(inputStream2);
            throw th;
        }
    }

    public static boolean a(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        try {
            x509Certificate2.verify(x509Certificate.getPublicKey());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean a(javax.security.cert.X509Certificate x509Certificate, javax.security.cert.X509Certificate x509Certificate2) {
        try {
            x509Certificate2.verify(x509Certificate.getPublicKey());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            Mac instance = Mac.getInstance("HmacSHA256");
            instance.init(new SecretKeySpec(bArr, "HmacSHA256"));
            return instance.doFinal(bArr2);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
