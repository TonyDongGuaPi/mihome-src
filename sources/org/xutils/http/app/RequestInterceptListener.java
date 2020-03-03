package org.xutils.http.app;

import org.xutils.http.request.UriRequest;

public interface RequestInterceptListener {
    void a(UriRequest uriRequest) throws Throwable;

    void b(UriRequest uriRequest) throws Throwable;
}
