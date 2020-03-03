package com.bumptech.glide.load.model;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.Preconditions;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Map;

public class GlideUrl implements Key {
    private static final String c = "@#&=*+-_.,:!?()/~'%;$";
    private final Headers d;
    @Nullable
    private final URL e;
    @Nullable
    private final String f;
    @Nullable
    private String g;
    @Nullable
    private URL h;
    @Nullable
    private volatile byte[] i;
    private int j;

    public GlideUrl(URL url) {
        this(url, Headers.b);
    }

    public GlideUrl(String str) {
        this(str, Headers.b);
    }

    public GlideUrl(URL url, Headers headers) {
        this.e = (URL) Preconditions.a(url);
        this.f = null;
        this.d = (Headers) Preconditions.a(headers);
    }

    public GlideUrl(String str, Headers headers) {
        this.e = null;
        this.f = Preconditions.a(str);
        this.d = (Headers) Preconditions.a(headers);
    }

    public URL a() throws MalformedURLException {
        return e();
    }

    private URL e() throws MalformedURLException {
        if (this.h == null) {
            this.h = new URL(f());
        }
        return this.h;
    }

    public String b() {
        return f();
    }

    private String f() {
        if (TextUtils.isEmpty(this.g)) {
            String str = this.f;
            if (TextUtils.isEmpty(str)) {
                str = ((URL) Preconditions.a(this.e)).toString();
            }
            this.g = Uri.encode(str, c);
        }
        return this.g;
    }

    public Map<String, String> c() {
        return this.d.a();
    }

    public String d() {
        return this.f != null ? this.f : ((URL) Preconditions.a(this.e)).toString();
    }

    public String toString() {
        return d();
    }

    public void a(@NonNull MessageDigest messageDigest) {
        messageDigest.update(g());
    }

    private byte[] g() {
        if (this.i == null) {
            this.i = d().getBytes(b);
        }
        return this.i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GlideUrl)) {
            return false;
        }
        GlideUrl glideUrl = (GlideUrl) obj;
        if (!d().equals(glideUrl.d()) || !this.d.equals(glideUrl.d)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        if (this.j == 0) {
            this.j = d().hashCode();
            this.j = (this.j * 31) + this.d.hashCode();
        }
        return this.j;
    }
}
