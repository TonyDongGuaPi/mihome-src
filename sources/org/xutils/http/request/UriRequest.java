package org.xutils.http.request;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import org.xutils.common.util.LogUtil;
import org.xutils.http.ProgressHandler;
import org.xutils.http.RequestParams;
import org.xutils.http.app.RequestInterceptListener;
import org.xutils.http.loader.Loader;
import org.xutils.http.loader.LoaderFactory;
import org.xutils.x;

public abstract class UriRequest implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    protected final String f10786a;
    protected final RequestParams b;
    protected final Loader<?> c;
    protected ClassLoader d = null;
    protected ProgressHandler e = null;
    protected RequestInterceptListener f = null;

    public abstract long a(String str, long j);

    public abstract String a(String str);

    public abstract void a() throws Throwable;

    public abstract boolean b();

    public abstract String c();

    public abstract void close() throws IOException;

    public abstract Object e() throws Throwable;

    public abstract void f();

    public abstract InputStream g() throws IOException;

    public abstract long h();

    public abstract int i() throws IOException;

    public abstract String j() throws IOException;

    public abstract long k();

    public abstract long l();

    public abstract String m();

    public abstract Map<String, List<String>> n();

    UriRequest(RequestParams requestParams, Type type) throws Throwable {
        this.b = requestParams;
        this.f10786a = a(requestParams);
        this.c = LoaderFactory.a(type, requestParams);
    }

    /* access modifiers changed from: protected */
    public String a(RequestParams requestParams) {
        return requestParams.o();
    }

    public void a(ProgressHandler progressHandler) {
        this.e = progressHandler;
        this.c.a(progressHandler);
    }

    public void a(ClassLoader classLoader) {
        this.d = classLoader;
    }

    public void a(RequestInterceptListener requestInterceptListener) {
        this.f = requestInterceptListener;
    }

    public RequestParams q() {
        return this.b;
    }

    public String aj_() {
        return this.f10786a;
    }

    public Object d() throws Throwable {
        return this.c.c(this);
    }

    public void p() {
        x.c().c(new Runnable() {
            public void run() {
                try {
                    UriRequest.this.c.b(UriRequest.this);
                } catch (Throwable th) {
                    LogUtil.b(th.getMessage(), th);
                }
            }
        });
    }

    public String toString() {
        return aj_();
    }
}
