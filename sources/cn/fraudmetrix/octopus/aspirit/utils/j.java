package cn.fraudmetrix.octopus.aspirit.utils;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class j {
    public static String a() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String a(String str) {
        try {
            return new String(Base64.decode(str, 2), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static String b(String str) {
        try {
            return Base64.encodeToString(str.getBytes("UTF-8"), 2).toString();
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static String c(String str) {
        try {
            return new StringBuilder(Base64.encodeToString(str.getBytes("UTF-8"), 2).trim()).reverse().toString();
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static boolean d(String str) {
        return str == null || "".equals(str.trim());
    }
}
