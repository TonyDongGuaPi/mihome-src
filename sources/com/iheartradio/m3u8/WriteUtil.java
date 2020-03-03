package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.Resolution;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class WriteUtil {
    public static String a(boolean z) {
        return z ? Constants.ad : Constants.ae;
    }

    public static String a(List<Byte> list) {
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("hex might not be null or empty!");
        }
        StringBuilder sb = new StringBuilder(list.size() + "0x".length());
        sb.append("0x");
        for (Byte byteValue : list) {
            sb.append(Integer.toHexString(byteValue.byteValue()));
        }
        return sb.toString();
    }

    public static String a(Resolution resolution) {
        return resolution.f6051a + "x" + resolution.b;
    }

    public static String a(String str, String str2) throws ParseException {
        StringBuilder sb = new StringBuilder(str.length() + 2);
        sb.append("\"");
        int i = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (i != 0 || !ParseUtil.a(charAt)) {
                if (charAt == '\"') {
                    sb.append(IOUtils.b);
                    sb.append(charAt);
                } else {
                    sb.append(charAt);
                }
                i++;
            } else {
                throw new ParseException(ParseExceptionType.ILLEGAL_WHITESPACE, str2);
            }
        }
        sb.append("\"");
        return sb.toString();
    }

    public static String a(String str) throws ParseException {
        try {
            return URLEncoder.encode(str.replace("%2B", "+"), "utf-8");
        } catch (UnsupportedEncodingException unused) {
            throw new ParseException(ParseExceptionType.INTERNAL_ERROR);
        }
    }

    public static String a(List<? extends Object> list, String str) {
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("valueList might not be null or empty!");
        } else if (str != null) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < list.size()) {
                sb.append(list.get(i).toString());
                i++;
                if (i < list.size()) {
                    sb.append(str);
                }
            }
            return sb.toString();
        } else {
            throw new IllegalArgumentException("separator might not be null!");
        }
    }
}
