package com.alipay.mobile.common.logging.util;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String encrypt(String str) {
        if (str == null || "".equals(str.trim())) {
            throw new IllegalArgumentException("input is null");
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("md5");
            instance.update(str.getBytes("UTF8"));
            return a(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            Log.e("md5", "md5", e);
            return null;
        } catch (UnsupportedEncodingException e2) {
            Log.e("md5", "md5", e2);
            return null;
        }
    }

    private static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }
}
