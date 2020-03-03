package com.xiaomi.smarthome.library.http.util;

import android.text.TextUtils;
import com.alipay.sdk.util.i;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import okhttp3.Response;

public class CookieUtil {
    public static HttpCookie a(CookieManager cookieManager, String str) {
        if (cookieManager == null || TextUtils.isEmpty(str)) {
            return null;
        }
        for (HttpCookie next : cookieManager.getCookieStore().getCookies()) {
            if (str.equals(next.getName())) {
                return next;
            }
        }
        return null;
    }

    public static HttpCookie a(CookieManager cookieManager, String str, String str2) {
        if (cookieManager == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        for (HttpCookie next : cookieManager.getCookieStore().getCookies()) {
            if (str.equals(next.getName()) && str2.equals(next.getDomain())) {
                return next;
            }
        }
        return null;
    }

    public static String a(Response response) {
        for (String next : response.headers("Set-Cookie")) {
            if (!TextUtils.isEmpty(next)) {
                for (String str : next.split(i.b)) {
                    if (str.contains("serviceToken")) {
                        int indexOf = str.indexOf("=");
                        if (indexOf == -1) {
                            return "";
                        }
                        return str.substring(indexOf + 1, str.length());
                    }
                }
                continue;
            }
        }
        return "";
    }

    public static HttpCookie a(CookieManager cookieManager, String str, String str2, String str3) {
        if (cookieManager == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return null;
        }
        for (HttpCookie next : cookieManager.getCookieStore().getCookies()) {
            if (str.equals(next.getName()) && str2.equals(next.getDomain()) && str3.equals(next.getPath())) {
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
            } catch (URISyntaxException unused) {
            }
        }
    }

    public static void a(CookieManager cookieManager) {
        if (cookieManager != null) {
            cookieManager.getCookieStore().removeAll();
        }
    }

    public static void a(CookieManager cookieManager, WebViewCookieManager webViewCookieManager) {
        if (webViewCookieManager != null) {
            for (HttpCookie next : cookieManager.getCookieStore().getCookies()) {
                webViewCookieManager.a(next.getName(), next.getValue(), next.getDomain());
            }
        }
    }
}
