package com.xiaomi.smarthome.miio.camera.cloudstorage.utils;

import android.util.Base64;
import com.coloros.mcssdk.c.a;
import com.xiaomi.smarthome.framework.log.LogUtil;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CloudVideoCryptoUtils {
    private static final String TAG = "CloudVideoCryptoUtils";
    private static CloudVideoCryptoUtils instance;
    public Cipher cipher;
    public IvParameterSpec iv;

    public byte[] encrypt() {
        return null;
    }

    private CloudVideoCryptoUtils() {
        init();
    }

    public static CloudVideoCryptoUtils getInstance() {
        if (instance == null) {
            synchronized (CloudVideoCryptoUtils.class) {
                instance = new CloudVideoCryptoUtils();
            }
        }
        return instance;
    }

    private void init() {
        this.iv = genIV();
        try {
            this.cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException unused) {
            this.cipher = null;
        }
    }

    public byte[] decrypt(byte[] bArr) {
        try {
            this.cipher.init(2, new SecretKeySpec(Base64.decode(CloudVideoNetUtils.getInstance().getTokenInfo().d, 2), a.b), this.iv);
            return this.cipher.doFinal(bArr);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            LogUtil.b(TAG, "e:" + e.toString());
            return null;
        }
    }

    private IvParameterSpec genIV() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return new IvParameterSpec(bArr);
    }
}
