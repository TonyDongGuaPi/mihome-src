package com.facebook.react.modules.network;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;

public class ReactCookieJarContainer implements CookieJarContainer {
    @Nullable
    private CookieJar cookieJar = null;

    public void setCookieJar(CookieJar cookieJar2) {
        this.cookieJar = cookieJar2;
    }

    public void removeCookieJar() {
        this.cookieJar = null;
    }

    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        if (this.cookieJar != null) {
            this.cookieJar.saveFromResponse(httpUrl, list);
        }
    }

    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        if (this.cookieJar == null) {
            return Collections.emptyList();
        }
        List<Cookie> loadForRequest = this.cookieJar.loadForRequest(httpUrl);
        ArrayList arrayList = new ArrayList();
        for (Cookie next : loadForRequest) {
            try {
                new Headers.Builder().add(next.name(), next.value());
                arrayList.add(next);
            } catch (IllegalArgumentException unused) {
            }
        }
        return arrayList;
    }
}
