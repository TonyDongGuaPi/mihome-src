package com.bumptech.glide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import com.bumptech.glide.load.engine.bitmap_recycle.LruArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.DefaultConnectivityMonitorFactory;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class GlideBuilder {

    /* renamed from: a  reason: collision with root package name */
    private final Map<Class<?>, TransitionOptions<?, ?>> f4808a = new ArrayMap();
    private Engine b;
    private BitmapPool c;
    private ArrayPool d;
    private MemoryCache e;
    private GlideExecutor f;
    private GlideExecutor g;
    private DiskCache.Factory h;
    private MemorySizeCalculator i;
    private ConnectivityMonitorFactory j;
    private int k = 4;
    private RequestOptions l = new RequestOptions();
    @Nullable
    private RequestManagerRetriever.RequestManagerFactory m;
    private GlideExecutor n;
    private boolean o;
    @Nullable
    private List<RequestListener<Object>> p;
    private boolean q;

    @NonNull
    public GlideBuilder a(@Nullable BitmapPool bitmapPool) {
        this.c = bitmapPool;
        return this;
    }

    @NonNull
    public GlideBuilder a(@Nullable ArrayPool arrayPool) {
        this.d = arrayPool;
        return this;
    }

    @NonNull
    public GlideBuilder a(@Nullable MemoryCache memoryCache) {
        this.e = memoryCache;
        return this;
    }

    @NonNull
    public GlideBuilder a(@Nullable DiskCache.Factory factory) {
        this.h = factory;
        return this;
    }

    @Deprecated
    public GlideBuilder a(@Nullable GlideExecutor glideExecutor) {
        return b(glideExecutor);
    }

    @NonNull
    public GlideBuilder b(@Nullable GlideExecutor glideExecutor) {
        this.f = glideExecutor;
        return this;
    }

    @NonNull
    public GlideBuilder c(@Nullable GlideExecutor glideExecutor) {
        this.g = glideExecutor;
        return this;
    }

    @NonNull
    public GlideBuilder d(@Nullable GlideExecutor glideExecutor) {
        this.n = glideExecutor;
        return this;
    }

    @NonNull
    public GlideBuilder a(@Nullable RequestOptions requestOptions) {
        this.l = requestOptions;
        return this;
    }

    @NonNull
    public <T> GlideBuilder a(@NonNull Class<T> cls, @Nullable TransitionOptions<?, T> transitionOptions) {
        this.f4808a.put(cls, transitionOptions);
        return this;
    }

    @NonNull
    public GlideBuilder a(@NonNull MemorySizeCalculator.Builder builder) {
        return a(builder.a());
    }

    @NonNull
    public GlideBuilder a(@Nullable MemorySizeCalculator memorySizeCalculator) {
        this.i = memorySizeCalculator;
        return this;
    }

    @NonNull
    public GlideBuilder a(@Nullable ConnectivityMonitorFactory connectivityMonitorFactory) {
        this.j = connectivityMonitorFactory;
        return this;
    }

    @NonNull
    public GlideBuilder a(int i2) {
        if (i2 < 2 || i2 > 6) {
            throw new IllegalArgumentException("Log level must be one of Log.VERBOSE, Log.DEBUG, Log.INFO, Log.WARN, or Log.ERROR");
        }
        this.k = i2;
        return this;
    }

    @NonNull
    public GlideBuilder a(boolean z) {
        this.o = z;
        return this;
    }

    @NonNull
    public GlideBuilder a(@NonNull RequestListener<Object> requestListener) {
        if (this.p == null) {
            this.p = new ArrayList();
        }
        this.p.add(requestListener);
        return this;
    }

    public GlideBuilder b(boolean z) {
        this.q = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    public void a(@Nullable RequestManagerRetriever.RequestManagerFactory requestManagerFactory) {
        this.m = requestManagerFactory;
    }

    /* access modifiers changed from: package-private */
    public GlideBuilder a(Engine engine) {
        this.b = engine;
        return this;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public Glide a(@NonNull Context context) {
        if (this.f == null) {
            this.f = GlideExecutor.b();
        }
        if (this.g == null) {
            this.g = GlideExecutor.a();
        }
        if (this.n == null) {
            this.n = GlideExecutor.d();
        }
        if (this.i == null) {
            this.i = new MemorySizeCalculator.Builder(context).a();
        }
        if (this.j == null) {
            this.j = new DefaultConnectivityMonitorFactory();
        }
        if (this.c == null) {
            int b2 = this.i.b();
            if (b2 > 0) {
                this.c = new LruBitmapPool((long) b2);
            } else {
                this.c = new BitmapPoolAdapter();
            }
        }
        if (this.d == null) {
            this.d = new LruArrayPool(this.i.c());
        }
        if (this.e == null) {
            this.e = new LruResourceCache((long) this.i.a());
        }
        if (this.h == null) {
            this.h = new InternalCacheDiskCacheFactory(context);
        }
        if (this.b == null) {
            this.b = new Engine(this.e, this.h, this.g, this.f, GlideExecutor.c(), GlideExecutor.d(), this.o);
        }
        if (this.p == null) {
            this.p = Collections.emptyList();
        } else {
            this.p = Collections.unmodifiableList(this.p);
        }
        return new Glide(context, this.b, this.e, this.c, this.d, new RequestManagerRetriever(this.m), this.j, this.k, (RequestOptions) this.l.t(), this.f4808a, this.p, this.q);
    }
}
