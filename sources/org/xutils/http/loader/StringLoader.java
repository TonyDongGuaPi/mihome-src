package org.xutils.http.loader;

import android.text.TextUtils;
import java.io.InputStream;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.common.util.IOUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.request.UriRequest;

class StringLoader extends Loader<String> {
    private String c = "UTF-8";
    private String d = null;

    StringLoader() {
    }

    public Loader<String> a() {
        return new StringLoader();
    }

    public void a(RequestParams requestParams) {
        if (requestParams != null) {
            String a2 = requestParams.a();
            if (!TextUtils.isEmpty(a2)) {
                this.c = a2;
            }
        }
    }

    /* renamed from: a */
    public String b(InputStream inputStream) throws Throwable {
        this.d = IOUtil.a(inputStream, this.c);
        return this.d;
    }

    /* renamed from: a */
    public String c(UriRequest uriRequest) throws Throwable {
        uriRequest.a();
        return b(uriRequest.g());
    }

    /* renamed from: a */
    public String b(DiskCacheEntity diskCacheEntity) throws Throwable {
        if (diskCacheEntity != null) {
            return diskCacheEntity.d();
        }
        return null;
    }

    public void b(UriRequest uriRequest) {
        a(uriRequest, this.d);
    }
}
