package com.franmontiel.persistentcookiejar.persistence;

import java.util.Collection;
import java.util.List;
import okhttp3.Cookie;

public interface CookiePersistor {
    List<Cookie> a();

    void a(Collection<Cookie> collection);

    void b();

    void b(Collection<Cookie> collection);
}
