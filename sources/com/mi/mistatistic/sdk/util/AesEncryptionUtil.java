package com.mi.mistatistic.sdk.util;

import android.util.Base64;
import com.coloros.mcssdk.c.a;
import java.io.UnsupportedEncodingException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesEncryptionUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7372a = "1431ed34249f13de";
    public static final String b = "4def5eca70e41551";
    private static final String c = "AES/CBC/PKCS5Padding";

    private static SecretKeySpec e(String str) {
        byte[] bArr;
        if (str == null) {
            str = "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(str);
        while (stringBuffer.length() < 16) {
            stringBuffer.append("0");
        }
        if (stringBuffer.length() > 16) {
            stringBuffer.setLength(16);
        }
        try {
            bArr = stringBuffer.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            bArr = null;
        }
        return new SecretKeySpec(bArr, a.b);
    }

    private static IvParameterSpec f(String str) {
        byte[] bArr;
        if (str == null) {
            str = "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(str);
        while (stringBuffer.length() < 16) {
            stringBuffer.append("0");
        }
        if (stringBuffer.length() > 16) {
            stringBuffer.setLength(16);
        }
        try {
            bArr = stringBuffer.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            bArr = null;
        }
        return new IvParameterSpec(bArr);
    }

    public static byte[] a(byte[] bArr, String str, String str2) {
        try {
            SecretKeySpec e = e(str);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, e, f(str2));
            return instance.doFinal(bArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String b(byte[] bArr, String str, String str2) {
        try {
            SecretKeySpec e = e(str);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, e, f(str2));
            return Base64.encodeToString(instance.doFinal(bArr), 0);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String a(String str, String str2, String str3) {
        try {
            return b(a(str.getBytes("UTF-8"), str2, str3));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String b(String str, String str2, String str3) {
        try {
            return b(str.getBytes("UTF-8"), str2, str3);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(String str) {
        return a(str, "1431ed34249f13de", "4def5eca70e41551");
    }

    public static String b(String str) {
        return b(str, "1431ed34249f13de", "4def5eca70e41551");
    }

    public static String c(String str) {
        return c(str, "1431ed34249f13de", "4def5eca70e41551");
    }

    public static byte[] a(byte[] bArr) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec("1431ed34249f13de".getBytes(), a.b);
            IvParameterSpec ivParameterSpec = new IvParameterSpec("4def5eca70e41551".getBytes());
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, ivParameterSpec);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] c(byte[] bArr, String str, String str2) {
        try {
            SecretKeySpec e = e(str);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, e, f(str2));
            return instance.doFinal(bArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String c(String str, String str2, String str3) {
        byte[] bArr;
        try {
            bArr = d(str);
        } catch (Exception e) {
            e.printStackTrace();
            bArr = null;
        }
        byte[] c2 = c(bArr, str2, str3);
        if (c2 == null) {
            return null;
        }
        try {
            return new String(c2, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String b(byte[] bArr) {
        String str = "";
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                str = str + "0" + hexString;
            } else {
                str = str + hexString;
            }
        }
        return str.toUpperCase();
    }

    public static byte[] d(String str) {
        byte[] bArr = new byte[(str.length() / 2)];
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            int i3 = i + 2;
            bArr[i2] = (byte) Integer.parseInt(str.substring(i, i3), 16);
            i2++;
            i = i3;
        }
        return bArr;
    }
}
