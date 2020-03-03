package com.bumptech.glide.provider;

import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pools;
import com.bumptech.glide.load.engine.DecodePath;
import com.bumptech.glide.load.engine.LoadPath;
import com.bumptech.glide.load.resource.transcode.UnitTranscoder;
import com.bumptech.glide.util.MultiClassKey;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class LoadPathCache {

    /* renamed from: a  reason: collision with root package name */
    private static final LoadPath<?, ?, ?> f5051a = new LoadPath(Object.class, Object.class, Object.class, Collections.singletonList(new DecodePath(Object.class, Object.class, Object.class, Collections.emptyList(), new UnitTranscoder(), (Pools.Pool<List<Throwable>>) null)), (Pools.Pool<List<Throwable>>) null);
    private final ArrayMap<MultiClassKey, LoadPath<?, ?, ?>> b = new ArrayMap<>();
    private final AtomicReference<MultiClassKey> c = new AtomicReference<>();

    public boolean a(@Nullable LoadPath<?, ?, ?> loadPath) {
        return f5051a.equals(loadPath);
    }

    @Nullable
    public <Data, TResource, Transcode> LoadPath<Data, TResource, Transcode> a(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        LoadPath<Data, TResource, Transcode> loadPath;
        MultiClassKey b2 = b(cls, cls2, cls3);
        synchronized (this.b) {
            loadPath = this.b.get(b2);
        }
        this.c.set(b2);
        return loadPath;
    }

    public void a(Class<?> cls, Class<?> cls2, Class<?> cls3, @Nullable LoadPath<?, ?, ?> loadPath) {
        synchronized (this.b) {
            ArrayMap<MultiClassKey, LoadPath<?, ?, ?>> arrayMap = this.b;
            MultiClassKey multiClassKey = new MultiClassKey(cls, cls2, cls3);
            if (loadPath == null) {
                loadPath = f5051a;
            }
            arrayMap.put(multiClassKey, loadPath);
        }
    }

    private MultiClassKey b(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        MultiClassKey andSet = this.c.getAndSet((Object) null);
        if (andSet == null) {
            andSet = new MultiClassKey();
        }
        andSet.a(cls, cls2, cls3);
        return andSet;
    }
}
