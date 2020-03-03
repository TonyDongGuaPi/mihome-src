package org.xutils.http.loader;

import android.text.TextUtils;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.ParameterizedTypeUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpResponse;
import org.xutils.http.app.InputStreamResponseParser;
import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

class ObjectLoader extends Loader<Object> {
    private String c = "UTF-8";
    private String d = null;
    private final Type e;
    private final Class<?> f;
    private final ResponseParser g;

    public ObjectLoader(Type type) {
        Class cls;
        this.e = type;
        if (type instanceof ParameterizedType) {
            this.f = (Class) ((ParameterizedType) type).getRawType();
        } else if (!(type instanceof TypeVariable)) {
            this.f = (Class) type;
        } else {
            throw new IllegalArgumentException("not support callback type " + type.toString());
        }
        if (List.class.equals(this.f)) {
            Type a2 = ParameterizedTypeUtil.a(this.e, (Class<?>) List.class, 0);
            if (a2 instanceof ParameterizedType) {
                cls = (Class) ((ParameterizedType) a2).getRawType();
            } else if (!(a2 instanceof TypeVariable)) {
                cls = (Class) a2;
            } else {
                throw new IllegalArgumentException("not support callback type " + a2.toString());
            }
            HttpResponse httpResponse = (HttpResponse) cls.getAnnotation(HttpResponse.class);
            if (httpResponse != null) {
                try {
                    this.g = (ResponseParser) httpResponse.parser().newInstance();
                } catch (Throwable th) {
                    throw new RuntimeException("create parser error", th);
                }
            } else {
                throw new IllegalArgumentException("not found @HttpResponse from " + a2);
            }
        } else {
            HttpResponse httpResponse2 = (HttpResponse) this.f.getAnnotation(HttpResponse.class);
            if (httpResponse2 != null) {
                try {
                    this.g = (ResponseParser) httpResponse2.parser().newInstance();
                } catch (Throwable th2) {
                    throw new RuntimeException("create parser error", th2);
                }
            } else {
                throw new IllegalArgumentException("not found @HttpResponse from " + this.e);
            }
        }
    }

    public Loader<Object> a() {
        throw new IllegalAccessError("use constructor create ObjectLoader.");
    }

    public void a(RequestParams requestParams) {
        if (requestParams != null) {
            String a2 = requestParams.a();
            if (!TextUtils.isEmpty(a2)) {
                this.c = a2;
            }
        }
    }

    public Object b(InputStream inputStream) throws Throwable {
        if (this.g instanceof InputStreamResponseParser) {
            return ((InputStreamResponseParser) this.g).a(this.e, this.f, inputStream);
        }
        this.d = IOUtil.a(inputStream, this.c);
        return this.g.a(this.e, this.f, this.d);
    }

    /* JADX INFO: finally extract failed */
    public Object c(UriRequest uriRequest) throws Throwable {
        try {
            uriRequest.a();
            this.g.a(uriRequest);
            return b(uriRequest.g());
        } catch (Throwable th) {
            this.g.a(uriRequest);
            throw th;
        }
    }

    public Object b(DiskCacheEntity diskCacheEntity) throws Throwable {
        if (diskCacheEntity == null) {
            return null;
        }
        String d2 = diskCacheEntity.d();
        if (!TextUtils.isEmpty(d2)) {
            return this.g.a(this.e, this.f, d2);
        }
        return null;
    }

    public void b(UriRequest uriRequest) {
        a(uriRequest, this.d);
    }
}
