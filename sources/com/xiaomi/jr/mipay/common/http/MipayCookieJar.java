package com.xiaomi.jr.mipay.common.http;

import android.content.Context;
import com.xiaomi.jr.account.XiaomiAccountInfo;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.http.DefaultCookieJar;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class MipayCookieJar extends DefaultCookieJar {
    public MipayCookieJar(Context context) {
        super(context);
    }

    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        List<Cookie> loadForRequest = super.loadForRequest(httpUrl);
        if (!XiaomiAccountManager.a().d()) {
            return loadForRequest;
        }
        XiaomiAccountInfo a2 = XiaomiAccountManager.a().a(this.f10811a, httpUrl.toString(), "mipay_cookie");
        if (a2 != null) {
            loadForRequest.add(new Cookie.Builder().domain(httpUrl.host()).path("/").name("userId").value(XiaomiAccountManager.h()).httpOnly().secure().build());
            loadForRequest.add(new Cookie.Builder().domain(httpUrl.host()).path("/").name("serviceToken").value(a2.c).httpOnly().secure().build());
        }
        HttpUtils.a("[cookie] " + loadForRequest);
        return loadForRequest;
    }
}
