package org.xutils.http.request;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import org.xutils.common.util.IOUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.loader.FileLoader;

public class LocalFileRequest extends UriRequest {
    private InputStream g;

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

    public String c() {
        return null;
    }

    public Object e() throws Throwable {
        return null;
    }

    public void f() {
    }

    public String j() throws IOException {
        return null;
    }

    public long k() {
        return -1;
    }

    public String m() {
        return null;
    }

    public Map<String, List<String>> n() {
        return null;
    }

    public void p() {
    }

    LocalFileRequest(RequestParams requestParams, Type type) throws Throwable {
        super(requestParams, type);
    }

    public Object d() throws Throwable {
        if (this.c instanceof FileLoader) {
            return r();
        }
        return this.c.c(this);
    }

    private File r() {
        String str;
        if (this.f10786a.startsWith("file:")) {
            str = this.f10786a.substring("file:".length());
        } else {
            str = this.f10786a;
        }
        return new File(str);
    }

    public InputStream g() throws IOException {
        if (this.g == null) {
            this.g = new FileInputStream(r());
        }
        return this.g;
    }

    public void close() throws IOException {
        IOUtil.a((Closeable) this.g);
        this.g = null;
    }

    public long h() {
        return r().length();
    }

    public int i() throws IOException {
        return r().exists() ? 200 : 404;
    }

    public long l() {
        return r().lastModified();
    }
}
