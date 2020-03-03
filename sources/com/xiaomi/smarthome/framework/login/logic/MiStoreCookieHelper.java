package com.xiaomi.smarthome.framework.login.logic;

import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.http.util.WebViewCookieManager;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;

public class MiStoreCookieHelper {
    public static void a() {
        String s = CoreApi.a().s();
        MiServiceTokenInfo a2 = CoreApi.a().a("miotstore");
        if (a2 != null) {
            a(s, a2.c, a2.f);
        }
        MiServiceTokenInfo a3 = CoreApi.a().a("mi_eshopm_go");
        if (a3 != null) {
            a(s, a3.c, a3.f);
        }
        MiServiceTokenInfo a4 = CoreApi.a().a("mi_huodong");
        if (a4 != null) {
            a(s, a4.c, a4.f);
        }
        MiServiceTokenInfo a5 = CoreApi.a().a("xiaomihome");
        if (a5 != null) {
            a(s, a5.c, a5.f);
        }
    }

    public static void a(String str, String str2, String str3) {
        LoginMiAccount y;
        if (!(!str3.equals("m.mi.com") || (y = CoreApi.a().y()) == null || y.a("mi_eshopm_go") == null)) {
            WebViewCookieManager.a().a("cUserId", y.a("mi_eshopm_go").b, str3);
        }
        WebViewCookieManager.a().a("serviceToken", str2, str3);
    }
}
