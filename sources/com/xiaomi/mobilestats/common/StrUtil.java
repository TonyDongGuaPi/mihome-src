package com.xiaomi.mobilestats.common;

import android.annotation.SuppressLint;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class StrUtil {
    @SuppressLint({"SimpleDateFormat"})
    private static final ThreadLocal D = new f();
    public static final String KEY_Sprite = "\n";

    public static boolean checkRegexValied(String str, String str2) {
        return Pattern.compile(str2).matcher(str).matches();
    }

    public static byte[] encodeBytesData(byte[] bArr) {
        return (bArr == null || bArr.length <= 0) ? "".getBytes() : AESUtils.encrypt(bArr, AESUtils.ENCODE_KEY);
    }

    public static boolean isEmail(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        return matchPattern("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", str);
    }

    public static boolean isEmpty(String str) {
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (charAt != ' ' && charAt != 9 && charAt != 13 && charAt != 10) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isPhone(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return matchPattern("^1\\d{10}$", str);
    }

    public static boolean matchPattern(String str, String str2) {
        return Pattern.compile(str).matcher(str2).matches();
    }

    public static String md5(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                byte b2 = b & 255;
                if (b2 < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(b2));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e2);
        }
    }

    public static boolean toBool(String str) {
        try {
            return Boolean.parseBoolean(str);
        } catch (Exception unused) {
            return false;
        }
    }

    public static Date toDate(String str) {
        try {
            return ((SimpleDateFormat) D.get()).parse(str);
        } catch (ParseException unused) {
            return null;
        }
    }

    public static int toInt(Object obj) {
        if (obj == null) {
            return 0;
        }
        return toInt(obj.toString(), 0);
    }

    public static int toInt(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i;
        }
    }

    public static long toLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return 0;
        }
    }
}
