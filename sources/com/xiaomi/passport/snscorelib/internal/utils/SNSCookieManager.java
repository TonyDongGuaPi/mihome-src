package com.xiaomi.passport.snscorelib.internal.utils;

import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import com.alipay.sdk.util.i;
import com.xiaomi.accountsdk.utils.AbstractAccountWebViewSingleCookieUtil;
import java.util.Map;

public class SNSCookieManager {
    public static void setupCookiesForAccountWeb(WebView webView, Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            CookieSyncManager.createInstance(webView.getContext());
            CookieManager instance = CookieManager.getInstance();
            for (Map.Entry next : map.entrySet()) {
                setAccountCookie(instance, (String) next.getKey(), (String) next.getValue());
            }
            CookieSyncManager.getInstance().sync();
        }
    }

    private static void setAccountCookie(CookieManager cookieManager, String str, String str2) {
        String str3;
        if (cookieManager != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (str.equals("passToken")) {
                str3 = String.format("%s=%s;HttpOnly;", new Object[]{str, str2});
            } else {
                str3 = String.format("%s=%s;Secure;", new Object[]{str, str2});
            }
            cookieManager.setCookie(AbstractAccountWebViewSingleCookieUtil.DOMAIN_URL_SET_COOKIE, str3);
        }
    }

    public static String getSnsBindCookie(String str) {
        return extractFromCookieString(str, "sns-bind-step");
    }

    public static String extractFromCookieString(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        for (String str3 : str.split(i.b)) {
            if (str3.contains(str2) && str3.split("=")[0].trim().equals(str2)) {
                return str3.substring(str3.indexOf("=") + 1);
            }
        }
        return null;
    }
}
