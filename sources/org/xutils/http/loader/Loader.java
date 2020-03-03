package org.xutils.http.loader;

import android.text.TextUtils;
import java.io.InputStream;
import java.util.Date;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.cache.LruDiskCache;
import org.xutils.http.ProgressHandler;
import org.xutils.http.RequestParams;
import org.xutils.http.request.UriRequest;

public abstract class Loader<T> {

    /* renamed from: a  reason: collision with root package name */
    protected RequestParams f10784a;
    protected ProgressHandler b;

    public abstract Loader<T> a();

    public abstract T b(InputStream inputStream) throws Throwable;

    public abstract T b(DiskCacheEntity diskCacheEntity) throws Throwable;

    public abstract void b(UriRequest uriRequest);

    public abstract T c(UriRequest uriRequest) throws Throwable;

    public void a(RequestParams requestParams) {
        this.f10784a = requestParams;
    }

    public void a(ProgressHandler progressHandler) {
        this.b = progressHandler;
    }

    /* access modifiers changed from: protected */
    public void a(UriRequest uriRequest, String str) {
        if (!TextUtils.isEmpty(str)) {
            DiskCacheEntity diskCacheEntity = new DiskCacheEntity();
            diskCacheEntity.a(uriRequest.c());
            diskCacheEntity.d(System.currentTimeMillis());
            diskCacheEntity.d(uriRequest.m());
            diskCacheEntity.b(uriRequest.k());
            diskCacheEntity.a(new Date(uriRequest.l()));
            diskCacheEntity.c(str);
            LruDiskCache.a(uriRequest.q().v()).a(diskCacheEntity);
        }
    }
}
