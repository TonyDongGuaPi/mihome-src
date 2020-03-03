package com.mi.global.shop.util;

import android.text.TextUtils;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.shop.ShopApp;

public class UrlUtil {
    public static String a(String str) {
        if (str.startsWith("http:") || str.startsWith("https:")) {
            return str;
        }
        if (str.startsWith("//")) {
            return "http:" + str;
        }
        return ConnectionHelper.HTTP_PREFIX + str;
    }

    public static String b(String str) {
        return (!TextUtils.isEmpty(str) && str.startsWith("http:") && ShopApp.c) ? str.replaceFirst("http:", "https:") : str;
    }

    public static String c(String str) {
        return (!TextUtils.isEmpty(str) && str.startsWith("http:") && ShopApp.d) ? str.replaceFirst("http:", "https:") : str;
    }

    public static String d(String str) {
        return (!TextUtils.isEmpty(str) && str.startsWith("http:") && ShopApp.e) ? str.replaceFirst("http:", "https:") : str;
    }
}
