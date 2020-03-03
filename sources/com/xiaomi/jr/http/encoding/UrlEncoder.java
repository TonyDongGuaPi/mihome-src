package com.xiaomi.jr.http.encoding;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UrlEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10830a = "UTF-8";
    private static final PercentEscaper b = new PercentEscaper("-._~", false);

    public static String a(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int i = 0;
        while (i < length) {
            int i2 = length - i;
            if (i2 >= 524288) {
                i2 = 524288;
            }
            int i3 = i2 + i;
            sb.append(b.a(str.substring(i, i3)));
            i = i3;
        }
        return sb.toString();
    }

    public static String b(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
