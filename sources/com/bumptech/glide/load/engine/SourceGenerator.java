package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.util.LogTime;
import java.util.Collections;
import java.util.List;

class SourceGenerator implements DataFetcher.DataCallback<Object>, DataFetcherGenerator, DataFetcherGenerator.FetcherReadyCallback {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4886a = "SourceGenerator";
    private final DecodeHelper<?> b;
    private final DataFetcherGenerator.FetcherReadyCallback c;
    private int d;
    private DataCacheGenerator e;
    private Object f;
    private volatile ModelLoader.LoadData<?> g;
    private DataCacheKey h;

    SourceGenerator(DecodeHelper<?> decodeHelper, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.b = decodeHelper;
        this.c = fetcherReadyCallback;
    }

    public boolean a() {
        if (this.f != null) {
            Object obj = this.f;
            this.f = null;
            b(obj);
        }
        if (this.e != null && this.e.a()) {
            return true;
        }
        this.e = null;
        this.g = null;
        boolean z = false;
        while (!z && d()) {
            List<ModelLoader.LoadData<?>> n = this.b.n();
            int i = this.d;
            this.d = i + 1;
            this.g = n.get(i);
            if (this.g != null && (this.b.c().a(this.g.c.d()) || this.b.a((Class<?>) this.g.c.a()))) {
                this.g.c.a(this.b.d(), this);
                z = true;
            }
        }
        return z;
    }

    private boolean d() {
        return this.d < this.b.n().size();
    }

    /* JADX INFO: finally extract failed */
    private void b(Object obj) {
        long a2 = LogTime.a();
        try {
            Encoder<X> a3 = this.b.a(obj);
            DataCacheWriter dataCacheWriter = new DataCacheWriter(a3, obj, this.b.e());
            this.h = new DataCacheKey(this.g.f4958a, this.b.f());
            this.b.b().a(this.h, dataCacheWriter);
            if (Log.isLoggable(f4886a, 2)) {
                Log.v(f4886a, "Finished encoding source to cache, key: " + this.h + ", data: " + obj + ", encoder: " + a3 + ", duration: " + LogTime.a(a2));
            }
            this.g.c.b();
            this.e = new DataCacheGenerator(Collections.singletonList(this.g.f4958a), this.b, this);
        } catch (Throwable th) {
            this.g.c.b();
            throw th;
        }
    }

    public void b() {
        ModelLoader.LoadData<?> loadData = this.g;
        if (loadData != null) {
            loadData.c.c();
        }
    }

    public void a(Object obj) {
        DiskCacheStrategy c2 = this.b.c();
        if (obj == null || !c2.a(this.g.c.d())) {
            this.c.a(this.g.f4958a, obj, this.g.c, this.g.c.d(), this.h);
            return;
        }
        this.f = obj;
        this.c.c();
    }

    public void a(@NonNull Exception exc) {
        this.c.a(this.h, exc, this.g.c, this.g.c.d());
    }

    public void c() {
        throw new UnsupportedOperationException();
    }

    public void a(Key key, Object obj, DataFetcher<?> dataFetcher, DataSource dataSource, Key key2) {
        this.c.a(key, obj, dataFetcher, this.g.c.d(), key);
    }

    public void a(Key key, Exception exc, DataFetcher<?> dataFetcher, DataSource dataSource) {
        this.c.a(key, exc, dataFetcher, this.g.c.d());
    }
}
