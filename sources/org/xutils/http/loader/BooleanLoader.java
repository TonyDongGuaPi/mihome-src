package org.xutils.http.loader;

import java.io.InputStream;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.http.request.UriRequest;

class BooleanLoader extends Loader<Boolean> {
    /* renamed from: a */
    public Boolean b(DiskCacheEntity diskCacheEntity) throws Throwable {
        return null;
    }

    public void b(UriRequest uriRequest) {
    }

    BooleanLoader() {
    }

    public Loader<Boolean> a() {
        return new BooleanLoader();
    }

    /* renamed from: a */
    public Boolean b(InputStream inputStream) throws Throwable {
        return false;
    }

    /* renamed from: a */
    public Boolean c(UriRequest uriRequest) throws Throwable {
        uriRequest.a();
        return Boolean.valueOf(uriRequest.i() < 300);
    }
}
