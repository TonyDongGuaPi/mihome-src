package com.mobikwik.sdk.lib.utils;

import android.annotation.SuppressLint;
import android.util.Base64;
import android.util.Log;
import com.miuipub.internal.hybrid.SignUtils;
import com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

public class RSAEncUtils {
    private static final String KEY_FACTORY_ALGORITHM = "RSA/ECB/PKCS1Padding";
    private static final String TAG = "EncUtils";
    PaymentsMappingAPIResponse.PaymentsMapping.MbkPublicKey mbkPublickey;
    PaymentsMappingAPIResponse.PaymentsMapping.ZaakPayPublicKey zaakPublickey;

    public RSAEncUtils(PaymentsMappingAPIResponse.PaymentsMapping.MbkPublicKey mbkPublicKey) {
        this.mbkPublickey = mbkPublicKey;
    }

    public RSAEncUtils(PaymentsMappingAPIResponse.PaymentsMapping.ZaakPayPublicKey zaakPayPublicKey) {
        this.zaakPublickey = zaakPayPublicKey;
    }

    private PublicKey generatePublicKey(String str) {
        try {
            return KeyFactory.getInstance(SignUtils.f8267a).generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e2) {
            Log.e(TAG, "Invalid key specification.");
            throw new IllegalArgumentException(e2);
        }
    }

    @SuppressLint({"TrulyRandom"})
    public String encrypt(String str) {
        PublicKey generatePublicKey = generatePublicKey(this.zaakPublickey != null ? this.zaakPublickey.key : this.mbkPublickey.key);
        try {
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, generatePublicKey);
            return Utils.utf8Encode(new String(Base64.encode(instance.doFinal(str.getBytes()), 0)));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return "";
        } catch (IllegalBlockSizeException e2) {
            e2.printStackTrace();
            return "";
        } catch (BadPaddingException e3) {
            e3.printStackTrace();
            return "";
        } catch (Exception e4) {
            e4.printStackTrace();
            return "";
        }
    }
}
