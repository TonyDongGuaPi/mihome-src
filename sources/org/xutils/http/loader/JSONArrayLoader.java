package org.xutils.http.loader;

import android.text.TextUtils;
import java.io.InputStream;
import org.json.JSONArray;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.common.util.IOUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.request.UriRequest;

class JSONArrayLoader extends Loader<JSONArray> {
    private String c = "UTF-8";
    private String d = null;

    JSONArrayLoader() {
    }

    public Loader<JSONArray> a() {
        return new JSONArrayLoader();
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
    public JSONArray b(InputStream inputStream) throws Throwable {
        this.d = IOUtil.a(inputStream, this.c);
        return new JSONArray(this.d);
    }

    /* renamed from: a */
    public JSONArray c(UriRequest uriRequest) throws Throwable {
        uriRequest.a();
        return b(uriRequest.g());
    }

    /* renamed from: a */
    public JSONArray b(DiskCacheEntity diskCacheEntity) throws Throwable {
        if (diskCacheEntity == null) {
            return null;
        }
        String d2 = diskCacheEntity.d();
        if (!TextUtils.isEmpty(d2)) {
            return new JSONArray(d2);
        }
        return null;
    }

    public void b(UriRequest uriRequest) {
        a(uriRequest, this.d);
    }
}
