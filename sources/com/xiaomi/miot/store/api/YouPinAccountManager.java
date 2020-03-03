package com.xiaomi.miot.store.api;

import com.xiaomi.miot.store.common.RNAppStoreApiManager;
import com.xiaomi.youpin.cookie.YouPinCookieManager;
import com.xiaomi.youpin.youpin_common.login.IServiceTokenCallback;
import com.xiaomi.youpin.youpin_common.login.IYouPinAccountManager;
import com.xiaomi.youpin.youpin_common.login.YouPinCookieUtils;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

public class YouPinAccountManager {

    /* renamed from: a  reason: collision with root package name */
    private static List<String> f11368a = new ArrayList();

    static {
        f11368a.add("miotstore");
        f11368a.add("xiaomihome");
        f11368a.add("ypsupport2");
    }

    public static void a() {
        final IYouPinAccountManager b = RNAppStoreApiManager.a().b();
        if (b != null) {
            for (final String next : f11368a) {
                b.a(next, new IServiceTokenCallback() {
                    public void a(int i, String str) {
                    }

                    public void a(String str) {
                        if (b.a()) {
                            YouPinCookieUtils.b(next, str);
                        }
                    }
                });
            }
        }
    }

    public static void logout() {
        YouPinCookieManager.a().b();
        CookieHandler cookieHandler = CookieHandler.getDefault();
        if (cookieHandler != null && (cookieHandler instanceof CookieManager)) {
            ((CookieManager) cookieHandler).getCookieStore().removeAll();
        }
    }
}
