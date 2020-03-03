package org.xutils.http;

import org.xutils.common.util.LogUtil;
import org.xutils.http.app.RequestTracker;
import org.xutils.http.request.UriRequest;

final class RequestTrackerWrapper implements RequestTracker {

    /* renamed from: a  reason: collision with root package name */
    private final RequestTracker f10773a;

    public RequestTrackerWrapper(RequestTracker requestTracker) {
        this.f10773a = requestTracker;
    }

    public void a(RequestParams requestParams) {
        try {
            this.f10773a.a(requestParams);
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
        }
    }

    public void b(RequestParams requestParams) {
        try {
            this.f10773a.b(requestParams);
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
        }
    }

    public void a(UriRequest uriRequest) {
        try {
            this.f10773a.a(uriRequest);
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
        }
    }

    public void a(UriRequest uriRequest, Object obj) {
        try {
            this.f10773a.a(uriRequest, obj);
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
        }
    }

    public void b(UriRequest uriRequest, Object obj) {
        try {
            this.f10773a.b(uriRequest, obj);
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
        }
    }

    public void b(UriRequest uriRequest) {
        try {
            this.f10773a.b(uriRequest);
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
        }
    }

    public void a(UriRequest uriRequest, Throwable th, boolean z) {
        try {
            this.f10773a.a(uriRequest, th, z);
        } catch (Throwable th2) {
            LogUtil.b(th2.getMessage(), th2);
        }
    }

    public void c(UriRequest uriRequest) {
        try {
            this.f10773a.c(uriRequest);
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
        }
    }
}
