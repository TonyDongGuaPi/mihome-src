package org.xutils.http.loader;

import java.io.InputStream;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.http.request.UriRequest;

class IntegerLoader extends Loader<Integer> {
    /* renamed from: a */
    public Integer b(DiskCacheEntity diskCacheEntity) throws Throwable {
        return null;
    }

    public void b(UriRequest uriRequest) {
    }

    IntegerLoader() {
    }

    public Loader<Integer> a() {
        return new IntegerLoader();
    }

    /* renamed from: a */
    public Integer b(InputStream inputStream) throws Throwable {
        return 100;
    }

    /* renamed from: a */
    public Integer c(UriRequest uriRequest) throws Throwable {
        uriRequest.a();
        return Integer.valueOf(uriRequest.i());
    }
}
