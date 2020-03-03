package com.adobe.xmp;

import com.adobe.xmp.impl.Base64;
import com.adobe.xmp.impl.ISO8601Converter;
import com.adobe.xmp.impl.XMPUtilsImpl;
import com.adobe.xmp.options.PropertyOptions;
import com.mobikwik.sdk.lib.Constants;

public class XMPUtils {
    private XMPUtils() {
    }

    public static String a(double d) {
        return String.valueOf(d);
    }

    public static String a(int i) {
        return String.valueOf(i);
    }

    public static String a(long j) {
        return String.valueOf(j);
    }

    public static String a(XMPDateTime xMPDateTime) {
        return ISO8601Converter.a(xMPDateTime);
    }

    public static String a(XMPMeta xMPMeta, String str, String str2, String str3, String str4, boolean z) throws XMPException {
        return XMPUtilsImpl.a(xMPMeta, str, str2, str3, str4, z);
    }

    public static String a(boolean z) {
        return z ? XMPConst.af : XMPConst.ag;
    }

    public static String a(byte[] bArr) {
        return new String(Base64.a(bArr));
    }

    public static void a(XMPMeta xMPMeta, XMPMeta xMPMeta2, boolean z, boolean z2) throws XMPException {
        a(xMPMeta, xMPMeta2, z, z2, false);
    }

    public static void a(XMPMeta xMPMeta, XMPMeta xMPMeta2, boolean z, boolean z2, boolean z3) throws XMPException {
        XMPUtilsImpl.a(xMPMeta, xMPMeta2, z, z2, z3);
    }

    public static void a(XMPMeta xMPMeta, String str, String str2, String str3, PropertyOptions propertyOptions, boolean z) throws XMPException {
        XMPUtilsImpl.a(xMPMeta, str, str2, str3, propertyOptions, z);
    }

    public static void a(XMPMeta xMPMeta, String str, String str2, boolean z, boolean z2) throws XMPException {
        XMPUtilsImpl.a(xMPMeta, str, str2, z, z2);
    }

    public static boolean a(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty convert-string", 5);
        }
        String lowerCase = str.toLowerCase();
        try {
            return Integer.parseInt(lowerCase) != 0;
        } catch (NumberFormatException unused) {
            return "true".equals(lowerCase) || "t".equals(lowerCase) || "on".equals(lowerCase) || Constants.YES.equals(lowerCase);
        }
    }

    public static int b(String str) throws XMPException {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    return str.startsWith("0x") ? Integer.parseInt(str.substring(2), 16) : Integer.parseInt(str);
                }
            } catch (NumberFormatException unused) {
                throw new XMPException("Invalid integer string", 5);
            }
        }
        throw new XMPException("Empty convert-string", 5);
    }

    public static long c(String str) throws XMPException {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    return str.startsWith("0x") ? Long.parseLong(str.substring(2), 16) : Long.parseLong(str);
                }
            } catch (NumberFormatException unused) {
                throw new XMPException("Invalid long string", 5);
            }
        }
        throw new XMPException("Empty convert-string", 5);
    }

    public static double d(String str) throws XMPException {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    return Double.parseDouble(str);
                }
            } catch (NumberFormatException unused) {
                throw new XMPException("Invalid double string", 5);
            }
        }
        throw new XMPException("Empty convert-string", 5);
    }

    public static XMPDateTime e(String str) throws XMPException {
        if (str != null && str.length() != 0) {
            return ISO8601Converter.a(str);
        }
        throw new XMPException("Empty convert-string", 5);
    }

    public static byte[] f(String str) throws XMPException {
        try {
            return Base64.b(str.getBytes());
        } catch (Throwable th) {
            throw new XMPException("Invalid base64 string", 5, th);
        }
    }
}
