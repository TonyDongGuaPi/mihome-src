package com.ximalaya.ting.android.sdkdownloader.http.app;

import com.ximalaya.ting.android.sdkdownloader.http.RequestParams;
import com.ximalaya.ting.android.sdkdownloader.http.request.UriRequest;

public interface RequestTracker {
    void a(RequestParams requestParams);

    void a(UriRequest uriRequest);

    void a(UriRequest uriRequest, Object obj);

    void a(UriRequest uriRequest, Throwable th, boolean z);

    void b(RequestParams requestParams);

    void b(UriRequest uriRequest);

    void c(UriRequest uriRequest);

    void d(UriRequest uriRequest);
}
