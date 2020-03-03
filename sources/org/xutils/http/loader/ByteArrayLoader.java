package org.xutils.http.loader;

import java.io.InputStream;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.common.util.IOUtil;
import org.xutils.http.request.UriRequest;

class ByteArrayLoader extends Loader<byte[]> {
    /* renamed from: a */
    public byte[] b(DiskCacheEntity diskCacheEntity) throws Throwable {
        return null;
    }

    public void b(UriRequest uriRequest) {
    }

    ByteArrayLoader() {
    }

    public Loader<byte[]> a() {
        return new ByteArrayLoader();
    }

    /* renamed from: a */
    public byte[] b(InputStream inputStream) throws Throwable {
        return IOUtil.a(inputStream);
    }

    /* renamed from: a */
    public byte[] c(UriRequest uriRequest) throws Throwable {
        uriRequest.a();
        return b(uriRequest.g());
    }
}
