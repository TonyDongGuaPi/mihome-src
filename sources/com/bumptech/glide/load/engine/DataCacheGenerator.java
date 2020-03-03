package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.File;
import java.util.List;

class DataCacheGenerator implements DataFetcher.DataCallback<Object>, DataFetcherGenerator {

    /* renamed from: a  reason: collision with root package name */
    private final List<Key> f4856a;
    private final DecodeHelper<?> b;
    private final DataFetcherGenerator.FetcherReadyCallback c;
    private int d;
    private Key e;
    private List<ModelLoader<File, ?>> f;
    private int g;
    private volatile ModelLoader.LoadData<?> h;
    private File i;

    DataCacheGenerator(DecodeHelper<?> decodeHelper, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this(decodeHelper.o(), decodeHelper, fetcherReadyCallback);
    }

    DataCacheGenerator(List<Key> list, DecodeHelper<?> decodeHelper, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.d = -1;
        this.f4856a = list;
        this.b = decodeHelper;
        this.c = fetcherReadyCallback;
    }

    public boolean a() {
        while (true) {
            boolean z = false;
            if (this.f == null || !c()) {
                this.d++;
                if (this.d >= this.f4856a.size()) {
                    return false;
                }
                Key key = this.f4856a.get(this.d);
                this.i = this.b.b().a(new DataCacheKey(key, this.b.f()));
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
        this.c.a(this.e, obj, this.h.c, DataSource.DATA_DISK_CACHE, this.e);
    }

    public void a(@NonNull Exception exc) {
        this.c.a(this.e, exc, this.h.c, DataSource.DATA_DISK_CACHE);
    }
}
