package com.xiaomi.jr.idcardverifier.http;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.xiaomi.jr.account.XiaomiAccountInfo;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.UrlUtils;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class XiaomiAccountCookieJar implements CookieJar {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10874a = "MiFiXiaomiAccountCookieJar";
    private Context b;

    public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
    }

    public XiaomiAccountCookieJar(Context context) {
        this.b = context.getApplicationContext();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        r1 = com.xiaomi.jr.account.XiaomiAccountManager.a().a(r5.b, r6.toString(), "idcard_verifier_cookie");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<okhttp3.Cookie> loadForRequest(@android.support.annotation.NonNull okhttp3.HttpUrl r6) {
        /*
            r5 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.xiaomi.jr.account.XiaomiAccountManager r1 = com.xiaomi.jr.account.XiaomiAccountManager.a()
            boolean r1 = r1.d()
            if (r1 != 0) goto L_0x0010
            return r0
        L_0x0010:
            com.xiaomi.jr.account.XiaomiAccountManager r1 = com.xiaomi.jr.account.XiaomiAccountManager.a()
            android.content.Context r2 = r5.b
            java.lang.String r3 = r6.toString()
            java.lang.String r4 = "idcard_verifier_cookie"
            com.xiaomi.jr.account.XiaomiAccountInfo r1 = r1.a((android.content.Context) r2, (java.lang.String) r3, (java.lang.String) r4)
            if (r1 == 0) goto L_0x002b
            java.lang.String r6 = r6.toString()
            java.util.List r6 = r5.a(r0, r6, r1)
            return r6
        L_0x002b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.idcardverifier.http.XiaomiAccountCookieJar.loadForRequest(okhttp3.HttpUrl):java.util.List");
    }

    private List<Cookie> a(@NonNull List<Cookie> list, @NonNull String str, @Nullable XiaomiAccountInfo xiaomiAccountInfo) {
        HttpUrl parse;
        if (xiaomiAccountInfo == null || (parse = HttpUrl.parse(UrlUtils.a(str))) == null) {
            return list;
        }
        String host = parse.host();
        String encodedPath = parse.encodedPath();
        if (!TextUtils.isEmpty(xiaomiAccountInfo.b)) {
            list.add(new Cookie.Builder().domain(host).path(encodedPath).name("cUserId").value(xiaomiAccountInfo.b).httpOnly().secure().build());
        }
        if (!TextUtils.isEmpty(xiaomiAccountInfo.c)) {
            Cookie.Builder path = new Cookie.Builder().domain(host).path(encodedPath);
            list.add(path.name(xiaomiAccountInfo.f10279a + "_serviceToken").value(xiaomiAccountInfo.c).httpOnly().secure().build());
        }
        if (!TextUtils.isEmpty(xiaomiAccountInfo.e)) {
            Cookie.Builder path2 = new Cookie.Builder().domain(host).path(encodedPath);
            list.add(path2.name(xiaomiAccountInfo.f10279a + "_ph").value(xiaomiAccountInfo.e).httpOnly().secure().build());
        }
        if (!TextUtils.isEmpty(xiaomiAccountInfo.f)) {
            Cookie.Builder path3 = new Cookie.Builder().domain(host).path(encodedPath);
            list.add(path3.name(xiaomiAccountInfo.f10279a + "_slh").value(xiaomiAccountInfo.f).httpOnly().secure().build());
        }
        MifiLog.b(f10874a, "XiaomiAccountCookieJar.makeAccountCookie - " + list + ", domain = " + host + ", path = " + encodedPath + ", hasLogin = " + XiaomiAccountManager.a().d());
        return list;
    }
}
