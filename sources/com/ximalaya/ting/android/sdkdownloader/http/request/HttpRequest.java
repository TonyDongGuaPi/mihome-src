package com.ximalaya.ting.android.sdkdownloader.http.request;

import android.annotation.TargetApi;
import com.ximalaya.ting.android.sdkdownloader.exception.HttpException;
import com.ximalaya.ting.android.sdkdownloader.http.RequestParams;
import com.ximalaya.ting.android.sdkdownloader.util.IOUtil;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import okhttp3.Call;
import okhttp3.Response;

public final class HttpRequest extends UriRequest {
    private boolean f = false;
    private InputStream g = null;
    private Call h;
    private Response i;
    private int j = 0;

    public HttpRequest(RequestParams requestParams) throws Throwable {
        super(requestParams);
    }

    public String a() {
        return this.i != null ? this.i.request().url().toString() : this.f2364a;
    }

    @TargetApi(19)
    public void b() throws Throwable {
        this.f = false;
        this.j = 0;
        this.h = OKHttpFileDownloadManage.a(this.b, this.e);
        this.i = this.h.execute();
        this.j = this.i.code();
        if (this.j == 204 || this.j == 205) {
            throw new HttpException(this.j, i());
        } else if (this.j < 300) {
            this.f = true;
        } else {
            HttpException httpException = new HttpException(this.j, i());
            try {
                httpException.setResult(IOUtil.a(e(), this.b.a()));
            } catch (Throwable unused) {
            }
            throw httpException;
        }
    }

    public boolean c() {
        return this.f;
    }

    public Object d() throws Throwable {
        this.f = true;
        return super.d();
    }

    public InputStream e() throws IOException {
        if (this.i != null && this.g == null) {
            this.g = this.i.body().byteStream();
        }
        return this.g;
    }

    public void close() throws IOException {
        if (this.h != null) {
            this.h.cancel();
        }
        if (this.g != null) {
            IOUtil.a((Closeable) this.g);
            this.g = null;
        }
        if (this.c != null) {
            this.c.a();
        }
        if (this.i != null) {
            this.i.close();
        }
    }

    public void f() {
        if (this.h != null) {
            this.h.cancel();
        }
        if (this.g != null) {
            IOUtil.a((Closeable) this.g);
            this.g = null;
        }
        if (this.c != null) {
            this.c.b();
        }
        if (this.i != null) {
            this.i.close();
        }
    }

    public long g() {
        long j2 = 0;
        if (this.i == null) {
            return (long) e().available();
        }
        try {
            j2 = this.i.body().contentLength();
        } catch (Throwable unused) {
        }
        if (j2 >= 1) {
            return j2;
        }
        try {
            return (long) e().available();
        } catch (Throwable unused2) {
            return j2;
        }
    }

    public int h() throws IOException {
        if (this.i != null) {
            return this.j;
        }
        return e() != null ? 200 : 404;
    }

    public String i() throws IOException {
        if (this.i != null) {
            return URLDecoder.decode(this.i.message(), this.b.a());
        }
        return null;
    }

    public String a(String str) {
        if (this.i == null) {
            return null;
        }
        return this.i.header(str);
    }
}
