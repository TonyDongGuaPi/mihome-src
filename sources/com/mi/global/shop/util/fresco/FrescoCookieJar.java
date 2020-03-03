package com.mi.global.shop.util.fresco;

import android.text.TextUtils;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.util.UserEncryptionUtil;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class FrescoCookieJar implements CookieJar {
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        if (list != null) {
            for (Cookie next : list) {
                if ("xm_in_sid".equalsIgnoreCase(next.name())) {
                    Utils.Preference.setStringPref(ShopApp.g(), "xm_in_sid", next.value());
                }
            }
        }
    }

    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        ArrayList arrayList = new ArrayList(3);
        if (httpUrl == null) {
            return arrayList;
        }
        String c = LoginManager.u().c();
        String e = LoginManager.u().e();
        String stringPref = Utils.Preference.getStringPref(ShopApp.g(), "xm_in_sid", (String) null);
        if (!TextUtils.isEmpty(e)) {
            arrayList.add(new Cookie.Builder().name("serviceToken").value(e).domain(httpUrl.host()).build());
        }
        if (!TextUtils.isEmpty(c)) {
            arrayList.add(new Cookie.Builder().name("mUserId").value(UserEncryptionUtil.a(c)).domain(httpUrl.host()).build());
            arrayList.add(new Cookie.Builder().name("cUserId").value(UserEncryptionUtil.b(c)).domain(httpUrl.host()).build());
        }
        if (!TextUtils.isEmpty(stringPref)) {
            arrayList.add(new Cookie.Builder().name("xm_in_sid").value(stringPref).domain(httpUrl.host()).build());
        }
        return arrayList;
    }
}
