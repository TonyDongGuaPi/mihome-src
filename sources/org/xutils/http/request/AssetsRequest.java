package org.xutils.http.request;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.cache.LruDiskCache;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class AssetsRequest extends UriRequest {
    private long g = 0;
    private InputStream h;

    public long a(String str, long j) {
        return j;
    }

    public String a(String str) {
        return null;
    }

    public void a() throws Throwable {
    }

    public boolean b() {
        return true;
    }

    public void f() {
    }

    public String j() throws IOException {
        return null;
    }

    public long k() {
        return Long.MAX_VALUE;
    }

    public String m() {
        return null;
    }

    public Map<String, List<String>> n() {
        return null;
    }

    public AssetsRequest(RequestParams requestParams, Type type) throws Throwable {
        super(requestParams, type);
    }

    public String c() {
        return this.f10786a;
    }

    public Object d() throws Throwable {
        return this.c.c(this);
    }

    public Object e() throws Throwable {
        Date h2;
        DiskCacheEntity b = LruDiskCache.a(this.b.v()).a(this.b.w()).b(c());
        if (b == null || (h2 = b.h()) == null || h2.getTime() < o()) {
            return null;
        }
        return this.c.b(b);
    }

    public InputStream g() throws IOException {
        if (this.h == null && this.d != null) {
            this.h = this.d.getResourceAsStream("assets/" + this.f10786a.substring("assets://".length()));
            this.g = (long) this.h.available();
        }
        return this.h;
    }

    public void close() throws IOException {
        IOUtil.a((Closeable) this.h);
        this.h = null;
    }

    public long h() {
        try {
            g();
            return this.g;
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
            return 0;
        }
    }

    public int i() throws IOException {
        return g() != null ? 200 : 404;
    }

    public long l() {
        return o();
    }

    /* access modifiers changed from: protected */
    public long o() {
        return new File(x.b().getApplicationInfo().sourceDir).lastModified();
    }
}
