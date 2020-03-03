package com.xiaomi.youpin.youpin_common.login;

import com.xiaomi.youpin.cookie.YouPinCookieManager;
import java.net.URLEncoder;

public class YouPinCookieUtils {
    public static void a(MiServiceTokenInfo miServiceTokenInfo) {
        YouPinCookieManager.a();
        String str = miServiceTokenInfo.f23799a;
        String str2 = miServiceTokenInfo.f;
        String str3 = miServiceTokenInfo.b;
        String str4 = miServiceTokenInfo.c;
        a(str, str3);
        b(str, str4);
    }

    public static void a(String str, String str2) {
        YouPinCookieManager a2 = YouPinCookieManager.a();
        String a3 = LoginConstant.a(str);
        if (!"passportapi".equals(str)) {
            a2.a("cUserId", str2, a3);
        }
        if ("miotstore".equals(str)) {
            a2.a("cUserId", str2, ".youpin.mi.com");
            a2.a("cUserId", str2, ".m.youpin.mi.com");
            a2.a("cUserId", str2, LoginConstant.w);
        }
    }

    public static void b(String str, String str2) {
        YouPinCookieManager a2 = YouPinCookieManager.a();
        String a3 = LoginConstant.a(str);
        if ("mi_eshopm_go".equals(str) || "mi_huodong".equals(str)) {
            a2.a("serviceToken", URLEncoder.encode(str2), a3);
        } else if (!"passportapi".equals(str)) {
            a2.a("serviceToken", str2, a3);
        }
        if ("miotstore".equals(str)) {
            a2.a("serviceToken", str2, ".youpin.mi.com");
            a2.a("serviceToken", str2, ".m.youpin.mi.com");
            a2.a("serviceToken", str2, LoginConstant.w);
        }
        if ("ypsupport2".equals(str)) {
            a2.a("serviceToken", str2, "ypsupport2.kefu.mi.com");
        }
    }
}
