package org.xutils.http.app;

import org.xutils.http.RequestParams;
import org.xutils.http.request.UriRequest;

public interface RequestTracker {
    void a(RequestParams requestParams);

    void a(UriRequest uriRequest);

    void a(UriRequest uriRequest, Object obj);

    void a(UriRequest uriRequest, Throwable th, boolean z);

    void b(RequestParams requestParams);

    void b(UriRequest uriRequest);

    void b(UriRequest uriRequest, Object obj);

    void c(UriRequest uriRequest);
}
