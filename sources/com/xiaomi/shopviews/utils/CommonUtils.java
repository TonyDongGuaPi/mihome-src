package com.xiaomi.shopviews.utils;

import android.text.TextUtils;
import com.mi.global.bbs.utils.ConnectionHelper;

public class CommonUtils {
    public static String a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("http:") || str.startsWith("https:")) {
            return str;
        }
        if (str.startsWith("//")) {
            return "http:" + str;
        }
        return ConnectionHelper.HTTP_PREFIX + str;
    }
}
