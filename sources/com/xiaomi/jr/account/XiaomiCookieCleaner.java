package com.xiaomi.jr.account;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import com.xiaomi.jr.common.utils.MifiLog;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

class XiaomiCookieCleaner {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10281a = "XiaomiCookieCleaner";

    XiaomiCookieCleaner() {
    }

    static void a(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager instance = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= 21) {
            instance.removeAllCookies((ValueCallback) null);
            instance.flush();
            return;
        }
        instance.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

    static void b(Context context) {
        for (XiaomiService next : XiaomiServices.a()) {
            a(context, next.b, next.f10282a);
        }
    }

    private static void a(Context context, String str, String str2) {
        String a2 = a(str2);
        String b = b(str2);
        String c = c(a2);
        if (!TextUtils.isEmpty(a2) && !TextUtils.isEmpty(b) && !TextUtils.isEmpty(c)) {
            CookieSyncManager.createInstance(context);
            CookieManager instance = CookieManager.getInstance();
            a(instance, str2, c, "cUserId", b);
            a(instance, str2, c, "userId", b);
            a(instance, str2, c, str + "_slh", b);
            a(instance, str2, a2, "serviceToken", b);
            a(instance, str2, a2, str + "_serviceToken", b);
            a(instance, str2, a2, str + "_ph", b);
            if (Build.VERSION.SDK_INT >= 21) {
                instance.flush();
            } else {
                CookieSyncManager.getInstance().sync();
            }
        }
    }

    private static void a(CookieManager cookieManager, String str, String str2, String str3, String str4) {
        cookieManager.setCookie(str, a(str3, str4));
        cookieManager.setCookie(str, a(str2, str3, str4));
        cookieManager.setCookie(str, a("." + str2, str3, str4));
    }

    private static String a(String str, String str2) {
        return String.format("%s=; path=%s; Expires=Thu, 01 Jan 1970 00:00:00 GMT", new Object[]{str, str2});
    }

    private static String a(String str, String str2, String str3) {
        return String.format("%s=; domain=%s; path=%s; Expires=Thu, 01 Jan 1970 00:00:00 GMT", new Object[]{str2, str, str3});
    }

    private static String a(String str) {
        try {
            return new URL(str).getHost();
        } catch (MalformedURLException e) {
            MifiLog.d(f10281a, "bad url", e);
            return null;
        }
    }

    private static String b(String str) {
        String str2;
        try {
            str2 = new URL(str).getPath();
        } catch (MalformedURLException e) {
            MifiLog.d(f10281a, "bad url", e);
            str2 = null;
        }
        return TextUtils.isEmpty(str2) ? File.separator : str2;
    }

    private static String c(String str) {
        String[] split = str.split("\\.");
        int length = split.length;
        if (length <= 2) {
            return str;
        }
        return String.format("%s.%s", new Object[]{split[length - 2], split[length - 1]});
    }
}
