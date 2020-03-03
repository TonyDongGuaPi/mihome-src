package com.xiaomi.smarthome.miio;

import android.util.Base64;
import com.coloros.mcssdk.c.a;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CameraCryptoUtils {
    private static final String c = "CameraCryptoUtils";
    private static CameraCryptoUtils d;

    /* renamed from: a  reason: collision with root package name */
    public IvParameterSpec f11570a;
    public Cipher b;

    private CameraCryptoUtils() {
        b();
    }

    public static CameraCryptoUtils a() {
        if (d == null) {
            synchronized (CameraCryptoUtils.class) {
                d = new CameraCryptoUtils();
            }
        }
        return d;
    }

    private void b() {
        this.f11570a = c();
        try {
            this.b = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException unused) {
            this.b = null;
        }
    }

    public byte[] a(byte[] bArr) {
        try {
            this.b.init(1, new SecretKeySpec(Base64.decode(CloudVideoNetUtils.getInstance().getTokenInfo().d, 2), a.b), this.f11570a);
            return this.b.doFinal(bArr);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            LogUtil.b(c, "encrypt e:" + e.toString());
            return null;
        }
    }

    public byte[] b(byte[] bArr) {
        try {
            this.b.init(2, new SecretKeySpec(Base64.decode(CloudVideoNetUtils.getInstance().getTokenInfo().d, 2), a.b), this.f11570a);
            return this.b.doFinal(bArr);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            LogUtil.b(c, "decrypt e:" + e.toString());
            return null;
        }
    }

    public byte[] c(byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        instance.update(bArr);
        return instance.digest();
    }

    private IvParameterSpec c() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return new IvParameterSpec(bArr);
    }
}
