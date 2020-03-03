package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.File;
import java.util.List;

class ResourceCacheGenerator implements DataFetcher.DataCallback<Object>, DataFetcherGenerator {

    /* renamed from: a  reason: collision with root package name */
    private final DataFetcherGenerator.FetcherReadyCallback f4883a;
    private final DecodeHelper<?> b;
    private int c;
    private int d = -1;
    private Key e;
    private List<ModelLoader<File, ?>> f;
    private int g;
    private volatile ModelLoader.LoadData<?> h;
    private File i;
    private ResourceCacheKey j;

    ResourceCacheGenerator(DecodeHelper<?> decodeHelper, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.b = decodeHelper;
        this.f4883a = fetcherReadyCallback;
    }

    public boolean a() {
        List<Key> o = this.b.o();
        boolean z = false;
        if (o.isEmpty()) {
            return false;
        }
        List<Class<?>> l = this.b.l();
        if (!l.isEmpty()) {
            while (true) {
                if (this.f == null || !c()) {
                    this.d++;
                    if (this.d >= l.size()) {
                        this.c++;
                        if (this.c >= o.size()) {
                            return false;
                        }
                        this.d = 0;
                    }
                    Key key = o.get(this.c);
                    Class cls = l.get(this.d);
                    this.j = new ResourceCacheKey(this.b.i(), key, this.b.f(), this.b.g(), this.b.h(), this.b.c(cls), cls, this.b.e());
                    this.i = this.b.b().a(this.j);
                    if (this.i != null) {
                        this.e = key;
                        this.f = this.b.a(this.i);
                        this.g = 0;
                    }
                } else {
                    this.h = null;
                    while (!z && c()) {
                        List<ModelLoader<File, ?>> list = this.f;
                        int i2 = this.g;
                        this.g = i2 + 1;
                        this.h = list.get(i2).a(this.i, this.b.g(), this.b.h(), this.b.e());
                        if (this.h != null && this.b.a((Class<?>) this.h.c.a())) {
                            this.h.c.a(this.b.d(), this);
                            z = true;
                        }
                    }
                    return z;
                }
            }
        } else if (File.class.equals(this.b.j())) {
            return false;
        } else {
            throw new IllegalStateException("Failed to find any load path from " + this.b.k() + " to " + this.b.j());
        }
    }

    private boolean c() {
        return this.g < this.f.size();
    }

    public void b() {
        ModelLoader.LoadData<?> loadData = this.h;
        if (loadData != null) {
            loadData.c.c();
        }
    }

    public void a(Object obj) {
        this.f4883a.a(this.e, obj, this.h.c, DataSource.RESOURCE_DISK_CACHE, this.j);
    }

    public void a(@NonNull Exception exc) {
        this.f4883a.a(this.j, exc, this.h.c, DataSource.RESOURCE_DISK_CACHE);
    }
}
