package org.apache.commons.lang;

import java.io.UnsupportedEncodingException;

public class CharEncoding {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3356a = "ISO-8859-1";
    public static final String b = "US-ASCII";
    public static final String c = "UTF-16";
    public static final String d = "UTF-16BE";
    public static final String e = "UTF-16LE";
    public static final String f = "UTF-8";

    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        try {
            new String(ArrayUtils.j, str);
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }
}
