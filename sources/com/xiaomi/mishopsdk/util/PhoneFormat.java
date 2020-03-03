package com.xiaomi.mishopsdk.util;

import android.text.TextUtils;
import java.util.regex.Pattern;

public class PhoneFormat {
    public static String valueOf(String str) {
        if (TextUtils.isEmpty(str) || !Pattern.compile("^(1)\\d{10}$").matcher(str).matches()) {
            return str;
        }
        return str.substring(0, 3) + "****" + str.substring(7);
    }
}
