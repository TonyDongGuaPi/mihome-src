package com.nostra13.universalimageloader.cache.disc.naming;

import com.nostra13.universalimageloader.utils.L;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5FileNameGenerator implements FileNameGenerator {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8444a = "MD5";
    private static final int b = 36;

    public String a(String str) {
        return new BigInteger(a(str.getBytes())).abs().toString(36);
    }

    private byte[] a(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            return instance.digest();
        } catch (NoSuchAlgorithmException e) {
            L.a((Throwable) e);
            return null;
        }
    }
}
