package com.ximalaya.ting.android.opensdk.util;

import com.google.code.microlog4android.format.PatternFormatter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f2253a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f'};

    static MessageDigest a(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected static String a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[(length << 1)];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr[i] = f2253a[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr[i3] = f2253a[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    public static byte[] b(byte[] bArr) {
        return a("MD5").digest(bArr);
    }

    public static String b(String str) {
        return a(b(e(str)));
    }

    public static String c(byte[] bArr) {
        return a(b(bArr));
    }

    private static byte[] e(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String c(String str) {
        return new String(a(d(str)));
    }

    public static byte[] d(String str) {
        return d(e(str));
    }

    public static byte[] d(byte[] bArr) {
        return a(miuipub.security.DigestUtils.b).digest(bArr);
    }
}
