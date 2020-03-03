package com.xiaomi.youpin.common.util;

import android.text.TextUtils;
import java.util.regex.Pattern;

public class PatternUtils {
    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.matches("^#([A-Fa-f0-9]{8}|[A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", str);
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.matches("^[rR][gG][Bb]\\(((([01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5]),\\s*){2}([01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])\\))$", str);
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.matches("^[rR][gG][Bb][Aa]\\(((([01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5]),\\s*){3}(1|1.0*|0|0.0*|0?.\\d)\\))$", str);
    }
}
