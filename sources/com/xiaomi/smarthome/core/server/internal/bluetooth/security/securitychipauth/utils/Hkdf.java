package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

public final class Hkdf {

    /* renamed from: a  reason: collision with root package name */
    private static final byte[] f14467a = new byte[0];
    private final String b;
    private final Provider c;
    private SecretKey d = null;

    public static Hkdf a(String str) throws NoSuchAlgorithmException {
        Mac.getInstance(str);
        return new Hkdf(str);
    }

    public static Hkdf a(String str, String str2) throws NoSuchAlgorithmException, NoSuchProviderException {
        return new Hkdf(str, Mac.getInstance(str, str2).getProvider());
    }

    public static Hkdf a(String str, Provider provider) throws NoSuchAlgorithmException {
        return new Hkdf(str, Mac.getInstance(str, provider).getProvider());
    }

    public void a(byte[] bArr) {
        a(bArr, (byte[]) null);
    }

    public void a(byte[] bArr, byte[] bArr2) {
        Mac mac;
        byte[] bArr3 = bArr2 == null ? f14467a : (byte[]) bArr2.clone();
        byte[] bArr4 = f14467a;
        try {
            if (this.c != null) {
                mac = Mac.getInstance(this.b, this.c);
            } else {
                mac = Mac.getInstance(this.b);
            }
            if (bArr3.length == 0) {
                bArr3 = new byte[mac.getMacLength()];
                Arrays.fill(bArr3, (byte) 0);
            }
            mac.init(new SecretKeySpec(bArr3, this.b));
            byte[] doFinal = mac.doFinal(bArr);
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(doFinal, this.b);
                Arrays.fill(doFinal, (byte) 0);
                a((SecretKey) secretKeySpec);
                Arrays.fill(doFinal, (byte) 0);
            } catch (GeneralSecurityException e) {
                bArr4 = doFinal;
                e = e;
                try {
                    throw new RuntimeException("Unexpected exception", e);
                } catch (Throwable th) {
                    th = th;
                    Arrays.fill(bArr4, (byte) 0);
                    throw th;
                }
            } catch (Throwable th2) {
                bArr4 = doFinal;
                th = th2;
                Arrays.fill(bArr4, (byte) 0);
                throw th;
            }
        } catch (GeneralSecurityException e2) {
            e = e2;
            throw new RuntimeException("Unexpected exception", e);
        }
    }

    public void a(SecretKey secretKey) throws InvalidKeyException {
        if (secretKey.getAlgorithm().equals(this.b)) {
            this.d = secretKey;
            return;
        }
        throw new InvalidKeyException("Algorithm for the provided key must match the algorithm for this Hkdf. Expected " + this.b + " but found " + secretKey.getAlgorithm());
    }

    private Hkdf(String str) {
        if (str.startsWith("Hmac")) {
            this.b = str;
            this.c = null;
            return;
        }
        throw new IllegalArgumentException("Invalid algorithm " + str + ". Hkdf may only be used with Hmac algorithms.");
    }

    private Hkdf(String str, Provider provider) {
        if (str.startsWith("Hmac")) {
            this.b = str;
            this.c = provider;
            return;
        }
        throw new IllegalArgumentException("Invalid algorithm " + str + ". Hkdf may only be used with Hmac algorithms.");
    }

    public byte[] a(String str, int i) throws IllegalStateException {
        return a(str != null ? str.getBytes(Charset.forName("UTF-8")) : null, i);
    }

    public byte[] a(byte[] bArr, int i) throws IllegalStateException {
        byte[] bArr2 = new byte[i];
        try {
            a(bArr, i, bArr2, 0);
            return bArr2;
        } catch (ShortBufferException e) {
            throw new RuntimeException(e);
        }
    }

    public void a(byte[] bArr, int i, byte[] bArr2, int i2) throws ShortBufferException, IllegalStateException {
        b();
        if (i < 0) {
            throw new IllegalArgumentException("Length must be a non-negative value.");
        } else if (bArr2.length >= i2 + i) {
            Mac a2 = a();
            if (i <= a2.getMacLength() * 255) {
                byte[] bArr3 = f14467a;
                int i3 = 0;
                byte b2 = 1;
                while (i3 < i) {
                    try {
                        a2.update(bArr3);
                        a2.update(bArr);
                        a2.update(b2);
                        byte[] doFinal = a2.doFinal();
                        int i4 = i3;
                        int i5 = 0;
                        while (i5 < doFinal.length && i4 < i) {
                            try {
                                bArr2[i4] = doFinal[i5];
                                i5++;
                                i4++;
                            } catch (Throwable th) {
                                th = th;
                                bArr3 = doFinal;
                                Arrays.fill(bArr3, (byte) 0);
                                throw th;
                            }
                        }
                        b2 = (byte) (b2 + 1);
                        i3 = i4;
                        bArr3 = doFinal;
                    } catch (Throwable th2) {
                        th = th2;
                        Arrays.fill(bArr3, (byte) 0);
                        throw th;
                    }
                }
                Arrays.fill(bArr3, (byte) 0);
                return;
            }
            throw new IllegalArgumentException("Requested keys may not be longer than 255 times the underlying HMAC length.");
        } else {
            throw new ShortBufferException();
        }
    }

    private Mac a() {
        Mac mac;
        try {
            if (this.c != null) {
                mac = Mac.getInstance(this.b, this.c);
            } else {
                mac = Mac.getInstance(this.b);
            }
            mac.init(this.d);
            return mac;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e2) {
            throw new RuntimeException(e2);
        }
    }

    private void b() throws IllegalStateException {
        if (this.d == null) {
            throw new IllegalStateException("Hkdf has not been initialized");
        }
    }
}
