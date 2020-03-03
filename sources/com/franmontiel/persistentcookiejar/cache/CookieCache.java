package com.franmontiel.persistentcookiejar.cache;

import java.util.Collection;
import okhttp3.Cookie;

public interface CookieCache extends Iterable<Cookie> {
    void a();

    void a(Collection<Cookie> collection);
}
