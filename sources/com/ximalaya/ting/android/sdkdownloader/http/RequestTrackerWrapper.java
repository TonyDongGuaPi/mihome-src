package com.ximalaya.ting.android.sdkdownloader.http;

import com.ximalaya.ting.android.opensdk.util.Logger;
import com.ximalaya.ting.android.sdkdownloader.http.app.RequestTracker;
import com.ximalaya.ting.android.sdkdownloader.http.request.UriRequest;

final class RequestTrackerWrapper implements RequestTracker {

    /* renamed from: a  reason: collision with root package name */
    private final RequestTracker f2361a;

    public RequestTrackerWrapper(RequestTracker requestTracker) {
        this.f2361a = requestTracker;
    }

    /* access modifiers changed from: package-private */
    public void a(String str, Throwable th) {
        Logger.e("RequestTrackerWrapper", str, th);
    }

    public void a(RequestParams requestParams) {
        try {
            this.f2361a.a(requestParams);
        } catch (Throwable th) {
            a(th.getMessage(), th);
        }
    }

    public void b(RequestParams requestParams) {
        try {
            this.f2361a.b(requestParams);
        } catch (Throwable th) {
            a(th.getMessage(), th);
        }
    }

    public void a(UriRequest uriRequest) {
        try {
            this.f2361a.a(uriRequest);
        } catch (Throwable th) {
            a(th.getMessage(), th);
        }
    }

    public void a(UriRequest uriRequest, Object obj) {
        try {
            this.f2361a.a(uriRequest, obj);
        } catch (Throwable th) {
            a(th.getMessage(), th);
        }
    }

    public void b(UriRequest uriRequest) {
        try {
            this.f2361a.b(uriRequest);
        } catch (Throwable th) {
            a(th.getMessage(), th);
        }
    }

    public void c(UriRequest uriRequest) {
        try {
            this.f2361a.c(uriRequest);
        } catch (Throwable th) {
            a(th.getMessage(), th);
        }
    }

    public void a(UriRequest uriRequest, Throwable th, boolean z) {
        try {
            this.f2361a.a(uriRequest, th, z);
        } catch (Throwable th2) {
            a(th2.getMessage(), th2);
        }
    }

    public void d(UriRequest uriRequest) {
        try {
            this.f2361a.d(uriRequest);
        } catch (Throwable th) {
            a(th.getMessage(), th);
        }
    }
}
