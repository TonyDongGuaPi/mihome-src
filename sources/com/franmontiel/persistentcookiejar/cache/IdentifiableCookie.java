package com.franmontiel.persistentcookiejar.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import okhttp3.Cookie;

class IdentifiableCookie {

    /* renamed from: a  reason: collision with root package name */
    private Cookie f5307a;

    static List<IdentifiableCookie> a(Collection<Cookie> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (Cookie identifiableCookie : collection) {
            arrayList.add(new IdentifiableCookie(identifiableCookie));
        }
        return arrayList;
    }

    IdentifiableCookie(Cookie cookie) {
        this.f5307a = cookie;
    }

    /* access modifiers changed from: package-private */
    public Cookie a() {
        return this.f5307a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IdentifiableCookie)) {
            return false;
        }
        IdentifiableCookie identifiableCookie = (IdentifiableCookie) obj;
        if (!identifiableCookie.f5307a.name().equals(this.f5307a.name()) || !identifiableCookie.f5307a.domain().equals(this.f5307a.domain()) || !identifiableCookie.f5307a.path().equals(this.f5307a.path()) || identifiableCookie.f5307a.secure() != this.f5307a.secure() || identifiableCookie.f5307a.hostOnly() != this.f5307a.hostOnly()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((527 + this.f5307a.name().hashCode()) * 31) + this.f5307a.domain().hashCode()) * 31) + this.f5307a.path().hashCode()) * 31) + (this.f5307a.secure() ^ true ? 1 : 0)) * 31) + (this.f5307a.hostOnly() ^ true ? 1 : 0);
    }
}
