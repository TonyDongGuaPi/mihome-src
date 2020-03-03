package com.ximalaya.ting.android.opensdk.httputil.util;

import com.google.code.microlog4android.format.PatternFormatter;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.io.UnsupportedEncodingException;

public class BASE64Encoder {

    /* renamed from: a  reason: collision with root package name */
    private static final char f1996a = ((char) Integer.parseInt("00000011", 2));
    private static final char b = ((char) Integer.parseInt("00001111", 2));
    private static final char c = ((char) Integer.parseInt("00111111", 2));
    private static final char d = ((char) Integer.parseInt("11111100", 2));
    private static final char e = ((char) Integer.parseInt("11110000", 2));
    private static final char f = ((char) Integer.parseInt("11000000", 2));
    private static final char[] g = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', PatternFormatter.PRIORITY_CONVERSION_CHAR, 'Q', 'R', 'S', PatternFormatter.THROWABLE_CONVERSION_CHAR, 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f', 'g', 'h', PatternFormatter.CLIENT_ID_CONVERSION_CHAR, 'j', 'k', 'l', PatternFormatter.MESSAGE_CONVERSION_CHAR, 'n', 'o', 'p', 'q', PatternFormatter.RELATIVE_TIME_CONVERSION_CHAR, 's', PatternFormatter.THREAD_CONVERSION_CHAR, 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', IOUtils.f15883a};
    private static final String h = "UTF-8";

    public static String a(String str) throws UnsupportedEncodingException {
        return a(str.getBytes("UTF-8"));
    }

    public static String a(byte[] bArr) {
        double length = (double) bArr.length;
        Double.isNaN(length);
        StringBuffer stringBuffer = new StringBuffer(((int) (length * 1.34d)) + 3);
        int i = 0;
        char c2 = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            i %= 8;
            while (i < 8) {
                if (i == 0) {
                    c2 = (char) (((char) (bArr[i2] & d)) >>> 2);
                } else if (i == 2) {
                    c2 = (char) (bArr[i2] & c);
                } else if (i == 4) {
                    c2 = (char) (((char) (bArr[i2] & b)) << 2);
                    int i3 = i2 + 1;
                    if (i3 < bArr.length) {
                        c2 = (char) (c2 | ((bArr[i3] & f) >>> 6));
                    }
                } else if (i == 6) {
                    c2 = (char) (((char) (bArr[i2] & f1996a)) << 4);
                    int i4 = i2 + 1;
                    if (i4 < bArr.length) {
                        c2 = (char) (c2 | ((bArr[i4] & e) >>> 4));
                    }
                }
                stringBuffer.append(g[c2]);
                i += 6;
            }
        }
        if (stringBuffer.length() % 4 != 0) {
            for (int length2 = 4 - (stringBuffer.length() % 4); length2 > 0; length2--) {
                stringBuffer.append("=");
            }
        }
        return stringBuffer.toString();
    }
}
