package com.google.firebase.iid;

import com.miuipub.internal.hybrid.SignUtils;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public final class zza {
    public static KeyPair zzb() {
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance(SignUtils.f8267a);
            instance.initialize(2048);
            return instance.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }
}