package com.alipay.mobile.security.bio.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import miuipub.security.DigestUtils;

public class SignHelper {
    public static String SHA1(String str) {
        try {
            return a(MessageDigest.getInstance(DigestUtils.b).digest(str.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            BioLog.e(e.toString());
            return null;
        }
    }

    private static String a(byte[] bArr) {
        String str = "";
        for (int i = 1; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i] & 255);
            if (hexString.length() == 1) {
                str = str + "0" + hexString;
            } else {
                str = str + hexString;
            }
        }
        return str.toLowerCase();
    }

    public static String MD5(String str) {
        try {
            if (StringUtil.isNullorEmpty(str)) {
                return null;
            }
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes("UTF-8"));
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 16; i++) {
                sb.append(String.format("%02x", new Object[]{Byte.valueOf(digest[i])}));
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String MD5(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 16; i++) {
                sb.append(String.format("%02x", new Object[]{Byte.valueOf(digest[i])}));
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }
}
