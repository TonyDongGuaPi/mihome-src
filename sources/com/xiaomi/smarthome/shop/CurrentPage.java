package com.xiaomi.smarthome.shop;

import android.text.TextUtils;
import com.mi.global.bbs.utils.ConnectionHelper;

public class CurrentPage {

    /* renamed from: a  reason: collision with root package name */
    static final String f22128a = "CurrentPage";
    static final String[] b = {"http://home.mi.com/shop/main", "http://home.mi.com/app/shop/content", "http://home.mi.com/shop/morelist", "http://home.mi.com/shop/detail", "https://mijiayoupin.com/shop/detail", "http://home.mi.com/shop/crowdfundinglist", "http://home.mi.com", "http://m.youpin.mi.com", "http://app.youpin.mi.com"};
    static String c = "";
    static String d = "";
    static boolean e = false;

    public static void a(String str, String str2) {
        if (!str2.startsWith("http://home.mi.com/shop/background")) {
            d = str;
            c = str2;
        }
    }

    public static String a() {
        if (TextUtils.isEmpty(c)) {
            return "";
        }
        if (c.startsWith("https://static.home.mi.com/")) {
            return c;
        }
        if (c.startsWith("https")) {
            c = c.replace("https://", ConnectionHelper.HTTP_PREFIX);
        }
        for (String startsWith : b) {
            if (c.startsWith(startsWith)) {
                return c.replace("http://home.mi.com", "https://mijiayoupin.com");
            }
        }
        return "";
    }

    public static String b() {
        return c;
    }

    public static String c() {
        return d;
    }

    public static void a(boolean z) {
        e = z;
    }

    public static boolean d() {
        return e;
    }
}
