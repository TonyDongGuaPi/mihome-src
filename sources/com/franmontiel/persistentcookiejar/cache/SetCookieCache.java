package com.franmontiel.persistentcookiejar.cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import okhttp3.Cookie;

public class SetCookieCache implements CookieCache {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Set<IdentifiableCookie> f5308a = new HashSet();

    public void a(Collection<Cookie> collection) {
        for (IdentifiableCookie next : IdentifiableCookie.a(collection)) {
            this.f5308a.remove(next);
            this.f5308a.add(next);
        }
    }

    public void a() {
        this.f5308a.clear();
    }

    public Iterator<Cookie> iterator() {
        return new SetCookieCacheIterator();
    }

    private class SetCookieCacheIterator implements Iterator<Cookie> {
        private Iterator<IdentifiableCookie> b;

        public SetCookieCacheIterator() {
            this.b = SetCookieCache.this.f5308a.iterator();
        }

        public boolean hasNext() {
            return this.b.hasNext();
        }

        /* renamed from: a */
        public Cookie next() {
            return this.b.next().a();
        }

        public void remove() {
            this.b.remove();
        }
    }
}
