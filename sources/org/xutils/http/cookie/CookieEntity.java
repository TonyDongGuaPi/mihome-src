package org.xutils.http.cookie;

import android.text.TextUtils;
import java.net.HttpCookie;
import java.net.URI;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "cookie", onCreated = "CREATE UNIQUE INDEX index_cookie_unique ON cookie(\"name\",\"domain\",\"path\")")
final class CookieEntity {

    /* renamed from: a  reason: collision with root package name */
    private static final long f10782a = (System.currentTimeMillis() + 3110400000000L);
    @Column(isId = true, name = "id")
    private long b;
    @Column(name = "uri")
    private String c;
    @Column(name = "name")
    private String d;
    @Column(name = "value")
    private String e;
    @Column(name = "comment")
    private String f;
    @Column(name = "commentURL")
    private String g;
    @Column(name = "discard")
    private boolean h;
    @Column(name = "domain")
    private String i;
    @Column(name = "expiry")
    private long j = f10782a;
    @Column(name = "path")
    private String k;
    @Column(name = "portList")
    private String l;
    @Column(name = "secure")
    private boolean m;
    @Column(name = "version")
    private int n = 1;

    public CookieEntity() {
    }

    public CookieEntity(URI uri, HttpCookie httpCookie) {
        String str;
        if (uri == null) {
            str = null;
        } else {
            str = uri.toString();
        }
        this.c = str;
        this.d = httpCookie.getName();
        this.e = httpCookie.getValue();
        this.f = httpCookie.getComment();
        this.g = httpCookie.getCommentURL();
        this.h = httpCookie.getDiscard();
        this.i = httpCookie.getDomain();
        long maxAge = httpCookie.getMaxAge();
        if (maxAge == -1 || maxAge <= 0) {
            this.j = -1;
        } else {
            this.j = (maxAge * 1000) + System.currentTimeMillis();
            if (this.j < 0) {
                this.j = f10782a;
            }
        }
        this.k = httpCookie.getPath();
        if (!TextUtils.isEmpty(this.k) && this.k.length() > 1 && this.k.endsWith("/")) {
            this.k = this.k.substring(0, this.k.length() - 1);
        }
        this.l = httpCookie.getPortlist();
        this.m = httpCookie.getSecure();
        this.n = httpCookie.getVersion();
    }

    public HttpCookie a() {
        HttpCookie httpCookie = new HttpCookie(this.d, this.e);
        httpCookie.setComment(this.f);
        httpCookie.setCommentURL(this.g);
        httpCookie.setDiscard(this.h);
        httpCookie.setDomain(this.i);
        if (this.j == -1) {
            httpCookie.setMaxAge(-1);
        } else {
            httpCookie.setMaxAge((this.j - System.currentTimeMillis()) / 1000);
        }
        httpCookie.setPath(this.k);
        httpCookie.setPortlist(this.l);
        httpCookie.setSecure(this.m);
        httpCookie.setVersion(this.n);
        return httpCookie;
    }

    public long b() {
        return this.b;
    }

    public void a(long j2) {
        this.b = j2;
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public boolean d() {
        return this.j != -1 && this.j < System.currentTimeMillis();
    }
}
