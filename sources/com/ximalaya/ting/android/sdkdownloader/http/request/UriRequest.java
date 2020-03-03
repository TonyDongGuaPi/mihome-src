package com.ximalaya.ting.android.sdkdownloader.http.request;

import com.ximalaya.ting.android.sdkdownloader.http.ProgressHandler;
import com.ximalaya.ting.android.sdkdownloader.http.RequestParams;
import com.ximalaya.ting.android.sdkdownloader.http.loader.FileLoader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public abstract class UriRequest implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    protected final String f2364a;
    protected final RequestParams b;
    protected final FileLoader c;
    protected ClassLoader d = null;
    protected ProgressHandler e = null;

    public abstract String a(String str);

    public abstract void b() throws Throwable;

    public abstract boolean c();

    public abstract void close() throws IOException;

    public abstract InputStream e() throws IOException;

    public abstract void f();

    public abstract long g();

    public abstract int h() throws IOException;

    public abstract String i() throws IOException;

    UriRequest(RequestParams requestParams) throws Throwable {
        this.b = requestParams;
        this.f2364a = a(requestParams);
        this.c = new FileLoader();
        this.c.a(requestParams);
    }

    /* access modifiers changed from: protected */
    public String a(RequestParams requestParams) {
        return requestParams.e();
    }

    public void a(ProgressHandler progressHandler) {
        this.e = progressHandler;
        this.c.a(progressHandler);
    }

    public void a(ClassLoader classLoader) {
        this.d = classLoader;
    }

    public RequestParams j() {
        return this.b;
    }

    public String a() {
        return this.f2364a;
    }

    public Object d() throws Throwable {
        return this.c.a(this);
    }

    public String toString() {
        return a();
    }
}
