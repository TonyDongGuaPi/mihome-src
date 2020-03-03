package com.xiaomi.smarthome.notishortcut;

import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;

class CookieUtil {
    CookieUtil() {
    }

    public static void a(CookieManager cookieManager) {
        if (cookieManager != null) {
            cookieManager.getCookieStore().removeAll();
        }
    }

    public static void a(CookieManager cookieManager, String str, String str2, String str3, String str4, String str5) {
        if (cookieManager != null) {
            HttpCookie httpCookie = new HttpCookie(str2, str3);
            httpCookie.setDomain(str4);
            httpCookie.setPath(str5);
            try {
                cookieManager.getCookieStore().add(new URI(str), httpCookie);
            } catch (URISyntaxException unused) {
            }
        }
    }
}
