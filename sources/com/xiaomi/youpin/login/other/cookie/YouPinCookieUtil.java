package com.xiaomi.youpin.login.other.cookie;

import android.text.TextUtils;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;

public class YouPinCookieUtil {
    public static HttpCookie a(CookieManager cookieManager, String str) {
        return a(cookieManager, str, "");
    }

    public static HttpCookie a(CookieManager cookieManager, String str, String str2) {
        return a(cookieManager, str, str2, "");
    }

    public static HttpCookie a(CookieManager cookieManager, String str, String str2, String str3) {
        if (cookieManager == null || TextUtils.isEmpty(str)) {
            return null;
        }
        for (HttpCookie next : cookieManager.getCookieStore().getCookies()) {
            boolean z = false;
            boolean z2 = TextUtils.isEmpty(str2) || str2.equals(next.getDomain());
            if (TextUtils.isEmpty(str3) || str3.equals(next.getPath())) {
                z = true;
            }
            if (str.equals(next.getName()) && z2 && z) {
                return next;
            }
        }
        return null;
    }

    public static void a(CookieManager cookieManager, String str, String str2, String str3, String str4, String str5) {
        if (cookieManager != null) {
            HttpCookie httpCookie = new HttpCookie(str2, str3);
            httpCookie.setDomain(str4);
            httpCookie.setPath(str5);
            try {
                cookieManager.getCookieStore().add(new URI(str), httpCookie);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(CookieManager cookieManager) {
        if (cookieManager != null) {
            cookieManager.getCookieStore().removeAll();
        }
    }

    public static void a(CookieManager cookieManager, YouPinCookieManager youPinCookieManager) {
        if (youPinCookieManager != null) {
            for (HttpCookie next : cookieManager.getCookieStore().getCookies()) {
                youPinCookieManager.a(next.getName(), next.getValue(), next.getDomain());
            }
        }
    }
}
