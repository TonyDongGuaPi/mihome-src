package com.xiaomi.jr.http;

import android.content.Context;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.CookieCache;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.CookiePersistor;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

public class DefaultCookieJar extends PersistentCookieJar {

    /* renamed from: a  reason: collision with root package name */
    protected Context f10811a;

    public DefaultCookieJar(Context context) {
        this(new SetCookieCache(), new SharedPrefsCookiePersistor(context.getApplicationContext()));
        this.f10811a = context.getApplicationContext();
    }

    public DefaultCookieJar(CookieCache cookieCache, CookiePersistor cookiePersistor) {
        super(cookieCache, cookiePersistor);
    }
}
