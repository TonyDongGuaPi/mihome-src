package com.xiaomi.smarthome.framework.plugin;

import com.miuipub.internal.hybrid.SignUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.crypto.rc4coder.Coder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class RSAUtils {
    public static PublicKey a(String str) throws Exception {
        return KeyFactory.getInstance(SignUtils.f8267a).generatePublic(new X509EncodedKeySpec(Coder.a(str)));
    }

    public static PrivateKey b(String str) throws Exception {
        return KeyFactory.getInstance(SignUtils.f8267a).generatePrivate(new PKCS8EncodedKeySpec(Coder.a(str)));
    }

    public static String a(Key key) throws Exception {
        return Coder.a(key.getEncoded());
    }

    public static String c(String str) {
        try {
            InputStream open = SHApplication.getAppContext().getAssets().open(str);
            ByteBuffer allocate = ByteBuffer.allocate(open.available());
            open.read(allocate.array());
            return ByteUtils.a(allocate).replace("-----BEGIN RSA PRIVATE KEY-----\n", "").replace("-----END RSA PRIVATE KEY-----", "").replace("\n", "");
        } catch (IOException unused) {
            return "";
        }
    }

    public static String d(String str) {
        try {
            InputStream open = SHApplication.getAppContext().getAssets().open(str);
            ByteBuffer allocate = ByteBuffer.allocate(open.available());
            open.read(allocate.array());
            return ByteUtils.a(allocate).replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "").replace("\n", "");
        } catch (IOException unused) {
            return "";
        }
    }

    public static byte[] a(byte[] bArr, PrivateKey privateKey) {
        try {
            Signature instance = Signature.getInstance(SignUtils.c);
            instance.initSign(privateKey);
            instance.update(bArr);
            return instance.sign();
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException unused) {
            return new byte[0];
        }
    }

    public static boolean a(byte[] bArr, byte[] bArr2, PublicKey publicKey) {
        try {
            Signature instance = Signature.getInstance(SignUtils.c);
            instance.initVerify(publicKey);
            instance.update(bArr);
            return instance.verify(bArr2);
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException unused) {
            return false;
        }
    }

    public static void a() throws Exception {
        KeyPairGenerator instance = KeyPairGenerator.getInstance(SignUtils.f8267a);
        instance.initialize(1024);
        KeyPair generateKeyPair = instance.generateKeyPair();
        PublicKey publicKey = generateKeyPair.getPublic();
        PrivateKey privateKey = generateKeyPair.getPrivate();
        String a2 = a((Key) publicKey);
        String a3 = a((Key) privateKey);
        Cipher instance2 = Cipher.getInstance(SignUtils.f8267a);
        byte[] bytes = "XiaomiSmartHome".getBytes();
        instance2.init(1, publicKey);
        byte[] doFinal = instance2.doFinal(bytes);
        PublicKey a4 = a(a2);
        PrivateKey b = b(a3);
        instance2.init(2, b);
        byte[] doFinal2 = instance2.doFinal(doFinal);
        a((Key) a4);
        a((Key) b);
        new String(doFinal2);
    }
}
