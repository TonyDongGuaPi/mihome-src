package com.lidroid.xutils.http;

import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;

public final class ResponseInfo<T> {

    /* renamed from: a  reason: collision with root package name */
    public T f6335a;
    public final boolean b;
    public final Locale c;
    public final int d;
    public final ProtocolVersion e;
    public final String f;
    public final long g;
    public final Header h;
    public final Header i;
    private final HttpResponse j;

    public Header[] a() {
        if (this.j == null) {
            return null;
        }
        return this.j.getAllHeaders();
    }

    public Header[] a(String str) {
        if (this.j == null) {
            return null;
        }
        return this.j.getHeaders(str);
    }

    public Header b(String str) {
        if (this.j == null) {
            return null;
        }
        return this.j.getFirstHeader(str);
    }

    public Header c(String str) {
        if (this.j == null) {
            return null;
        }
        return this.j.getLastHeader(str);
    }

    public ResponseInfo(HttpResponse httpResponse, T t, boolean z) {
        this.j = httpResponse;
        this.f6335a = t;
        this.b = z;
        if (httpResponse != null) {
            this.c = httpResponse.getLocale();
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine != null) {
                this.d = statusLine.getStatusCode();
                this.e = statusLine.getProtocolVersion();
                this.f = statusLine.getReasonPhrase();
            } else {
                this.d = 0;
                this.e = null;
                this.f = null;
            }
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                this.g = entity.getContentLength();
                this.h = entity.getContentType();
                this.i = entity.getContentEncoding();
                return;
            }
            this.g = 0;
            this.h = null;
            this.i = null;
            return;
        }
        this.c = null;
        this.d = 0;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = null;
        this.i = null;
    }
}
