package org.xutils.http.loader;

import android.text.TextUtils;
import java.io.InputStream;
import org.json.JSONObject;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.common.util.IOUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.request.UriRequest;

class JSONObjectLoader extends Loader<JSONObject> {
    private String c = "UTF-8";
    private String d = null;

    JSONObjectLoader() {
    }

    public Loader<JSONObject> a() {
        return new JSONObjectLoader();
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
    public JSONObject b(InputStream inputStream) throws Throwable {
        this.d = IOUtil.a(inputStream, this.c);
        return new JSONObject(this.d);
    }

    /* renamed from: a */
    public JSONObject c(UriRequest uriRequest) throws Throwable {
        uriRequest.a();
        return b(uriRequest.g());
    }

    /* renamed from: a */
    public JSONObject b(DiskCacheEntity diskCacheEntity) throws Throwable {
        if (diskCacheEntity == null) {
            return null;
        }
        String d2 = diskCacheEntity.d();
        if (!TextUtils.isEmpty(d2)) {
            return new JSONObject(d2);
        }
        return null;
    }

    public void b(UriRequest uriRequest) {
        a(uriRequest, this.d);
    }
}
