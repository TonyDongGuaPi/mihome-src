package com.ximalaya.ting.android.opensdk.auth.utils;

import com.google.code.microlog4android.format.PatternFormatter;
import java.security.MessageDigest;

public final class b {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f1844a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f'};

    public static String a(String str) {
        try {
            return a(str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            byte[] digest = instance.digest();
            char[] cArr = new char[32];
            int i = 0;
            for (int i2 = 0; i2 < 16; i2++) {
                byte b = digest[i2];
                int i3 = i + 1;
                cArr[i] = f1844a[(b >>> 4) & 15];
                i = i3 + 1;
                cArr[i3] = f1844a[b & 15];
            }
            return new String(cArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void a() {
        System.out.println(a("c"));
    }
}
