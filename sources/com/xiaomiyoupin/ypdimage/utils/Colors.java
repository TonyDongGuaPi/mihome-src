package com.xiaomiyoupin.ypdimage.utils;

import android.graphics.Color;
import com.taobao.weex.el.parse.Operators;

public class Colors {
    public static int a(String str) {
        int length = str.length();
        String lowerCase = str.toLowerCase();
        if (lowerCase.charAt(0) == '#') {
            long j = 0;
            if (length == 4) {
                char charAt = lowerCase.charAt(1);
                char charAt2 = lowerCase.charAt(2);
                char charAt3 = lowerCase.charAt(3);
                j = Long.parseLong("" + charAt + charAt + charAt2 + charAt2 + charAt3 + charAt3, 16) | -16777216;
            } else if (length == 7 || length == 9) {
                j = Long.parseLong(lowerCase.substring(1), 16);
                if (lowerCase.length() == 7) {
                    j |= -16777216;
                }
            }
            return (int) j;
        } else if (lowerCase.startsWith("rgba(")) {
            int indexOf = lowerCase.indexOf(Operators.BRACKET_START_STR) + 1;
            int indexOf2 = lowerCase.indexOf(Operators.BRACKET_END_STR);
            if (indexOf != 5 || indexOf2 != length - 1) {
                return 0;
            }
            String[] split = lowerCase.substring(indexOf, indexOf2).split(",");
            if (split.length != 4) {
                return 0;
            }
            return Color.argb(b(split[3]), b(split[0]), b(split[1]), b(split[2]));
        } else if (!lowerCase.startsWith("rgb(")) {
            return 0;
        } else {
            int indexOf3 = lowerCase.indexOf(Operators.BRACKET_START_STR) + 1;
            int indexOf4 = lowerCase.indexOf(Operators.BRACKET_END_STR);
            if (indexOf3 != 4 || indexOf4 != length - 1) {
                return 0;
            }
            String[] split2 = lowerCase.substring(indexOf3, indexOf4).split(",");
            if (split2.length != 3) {
                return 0;
            }
            return Color.rgb(b(split2[0]), b(split2[1]), b(split2[2]));
        }
    }

    private static int b(String str) {
        try {
            if (str.contains(".")) {
                return (int) (Float.valueOf(str).floatValue() * 255.0f);
            }
            return Integer.valueOf(str).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }
}
