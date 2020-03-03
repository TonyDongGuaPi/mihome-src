package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelLoaderRegistry {

    /* renamed from: a  reason: collision with root package name */
    private final MultiModelLoaderFactory f4959a;
    private final ModelLoaderCache b;

    public ModelLoaderRegistry(@NonNull Pools.Pool<List<Throwable>> pool) {
        this(new MultiModelLoaderFactory(pool));
    }

    private ModelLoaderRegistry(@NonNull MultiModelLoaderFactory multiModelLoaderFactory) {
        this.b = new ModelLoaderCache();
        this.f4959a = multiModelLoaderFactory;
    }

    public synchronized <Model, Data> void a(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        this.f4959a.a(cls, cls2, modelLoaderFactory);
        this.b.a();
    }

    public synchronized <Model, Data> void b(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        this.f4959a.b(cls, cls2, modelLoaderFactory);
        this.b.a();
    }

    public synchronized <Model, Data> void a(@NonNull Class<Model> cls, @NonNull Class<Data> cls2) {
        a(this.f4959a.a(cls, cls2));
        this.b.a();
    }

    public synchronized <Model, Data> void c(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        a(this.f4959a.c(cls, cls2, modelLoaderFactory));
        this.b.a();
    }

    private <Model, Data> void a(@NonNull List<ModelLoaderFactory<? extends Model, ? extends Data>> list) {
        for (ModelLoaderFactory<? extends Model, ? extends Data> a2 : list) {
            a2.a();
        }
    }

    @NonNull
    public <A> List<ModelLoader<A, ?>> a(@NonNull A a2) {
        List b2 = b(b(a2));
        int size = b2.size();
        List<ModelLoader<A, ?>> emptyList = Collections.emptyList();
        boolean z = true;
        for (int i = 0; i < size; i++) {
            ModelLoader modelLoader = (ModelLoader) b2.get(i);
            if (modelLoader.a(a2)) {
                if (z) {
                    emptyList = new ArrayList<>(size - i);
                    z = false;
                }
                emptyList.add(modelLoader);
            }
        }
        return emptyList;
    }

    public synchronized <Model, Data> ModelLoader<Model, Data> b(@NonNull Class<Model> cls, @NonNull Class<Data> cls2) {
        return this.f4959a.b(cls, cls2);
    }

    @NonNull
    public synchronized List<Class<?>> a(@NonNull Class<?> cls) {
        return this.f4959a.b(cls);
    }

    @NonNull
    private synchronized <A> List<ModelLoader<A, ?>> b(@NonNull Class<A> cls) {
        List<ModelLoader<A, ?>> a2;
        a2 = this.b.a(cls);
        if (a2 == null) {
            a2 = Collections.unmodifiableList(this.f4959a.a(cls));
            this.b.a(cls, a2);
        }
        return a2;
    }

    @NonNull
    private static <A> Class<A> b(@NonNull A a2) {
        return a2.getClass();
    }

    private static class ModelLoaderCache {

        /* renamed from: a  reason: collision with root package name */
        private final Map<Class<?>, Entry<?>> f4960a = new HashMap();

        ModelLoaderCache() {
        }

        public void a() {
            this.f4960a.clear();
        }

        public <Model> void a(Class<Model> cls, List<ModelLoader<Model, ?>> list) {
            if (this.f4960a.put(cls, new Entry(list)) != null) {
                throw new IllegalStateException("Already cached loaders for model: " + cls);
            }
        }

        @Nullable
        public <Model> List<ModelLoader<Model, ?>> a(Class<Model> cls) {
            Entry entry = this.f4960a.get(cls);
            if (entry == null) {
                return null;
            }
            return entry.f4961a;
        }

        private static class Entry<Model> {

            /* renamed from: a  reason: collision with root package name */
            final List<ModelLoader<Model, ?>> f4961a;

            public Entry(List<ModelLoader<Model, ?>> list) {
                this.f4961a = list;
            }
        }
    }
}
