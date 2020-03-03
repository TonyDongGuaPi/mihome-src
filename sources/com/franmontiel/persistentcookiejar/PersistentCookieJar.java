package com.franmontiel.persistentcookiejar;

import com.franmontiel.persistentcookiejar.cache.CookieCache;
import com.franmontiel.persistentcookiejar.persistence.CookiePersistor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class PersistentCookieJar implements ClearableCookieJar {

    /* renamed from: a  reason: collision with root package name */
    private CookieCache f5306a;
    private CookiePersistor b;

    public PersistentCookieJar(CookieCache cookieCache, CookiePersistor cookiePersistor) {
        this.f5306a = cookieCache;
        this.b = cookiePersistor;
        this.f5306a.a(cookiePersistor.a());
    }

    public synchronized void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        this.f5306a.a(list);
        this.b.a(a(list));
    }

    private static List<Cookie> a(List<Cookie> list) {
        ArrayList arrayList = new ArrayList();
        for (Cookie next : list) {
            if (next.persistent()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public synchronized List<Cookie> loadForRequest(HttpUrl httpUrl) {
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList();
        arrayList = new ArrayList();
        Iterator it = this.f5306a.iterator();
        while (it.hasNext()) {
            Cookie cookie = (Cookie) it.next();
            if (a(cookie)) {
                arrayList2.add(cookie);
                it.remove();
            } else if (cookie.matches(httpUrl)) {
                arrayList.add(cookie);
            }
        }
        this.b.b(arrayList2);
        return arrayList;
    }

    private static boolean a(Cookie cookie) {
        return cookie.expiresAt() < System.currentTimeMillis();
    }

    public synchronized void a() {
        this.f5306a.a();
        this.f5306a.a(this.b.a());
    }

    public synchronized void b() {
        this.f5306a.a();
        this.b.b();
    }
}
