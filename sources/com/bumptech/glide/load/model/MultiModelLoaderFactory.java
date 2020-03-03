package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.Pools;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.util.Preconditions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MultiModelLoaderFactory {

    /* renamed from: a  reason: collision with root package name */
    private static final Factory f4964a = new Factory();
    private static final ModelLoader<Object, Object> b = new EmptyModelLoader();
    private final List<Entry<?, ?>> c;
    private final Factory d;
    private final Set<Entry<?, ?>> e;
    private final Pools.Pool<List<Throwable>> f;

    public MultiModelLoaderFactory(@NonNull Pools.Pool<List<Throwable>> pool) {
        this(pool, f4964a);
    }

    @VisibleForTesting
    MultiModelLoaderFactory(@NonNull Pools.Pool<List<Throwable>> pool, @NonNull Factory factory) {
        this.c = new ArrayList();
        this.e = new HashSet();
        this.f = pool;
        this.d = factory;
    }

    /* access modifiers changed from: package-private */
    public synchronized <Model, Data> void a(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        a(cls, cls2, modelLoaderFactory, true);
    }

    /* access modifiers changed from: package-private */
    public synchronized <Model, Data> void b(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        a(cls, cls2, modelLoaderFactory, false);
    }

    private <Model, Data> void a(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory, boolean z) {
        this.c.add(z ? this.c.size() : 0, new Entry(cls, cls2, modelLoaderFactory));
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public synchronized <Model, Data> List<ModelLoaderFactory<? extends Model, ? extends Data>> c(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        List<ModelLoaderFactory<? extends Model, ? extends Data>> a2;
        a2 = a(cls, cls2);
        a(cls, cls2, modelLoaderFactory);
        return a2;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public synchronized <Model, Data> List<ModelLoaderFactory<? extends Model, ? extends Data>> a(@NonNull Class<Model> cls, @NonNull Class<Data> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<Entry<?, ?>> it = this.c.iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            if (next.a(cls, cls2)) {
                it.remove();
                arrayList.add(a((Entry<?, ?>) next));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public synchronized <Model> List<ModelLoader<Model, ?>> a(@NonNull Class<Model> cls) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            for (Entry next : this.c) {
                if (!this.e.contains(next)) {
                    if (next.a(cls)) {
                        this.e.add(next);
                        arrayList.add(b((Entry<?, ?>) next));
                        this.e.remove(next);
                    }
                }
            }
        } catch (Throwable th) {
            this.e.clear();
            throw th;
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public synchronized List<Class<?>> b(@NonNull Class<?> cls) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (Entry next : this.c) {
            if (!arrayList.contains(next.f4965a) && next.a(cls)) {
                arrayList.add(next.f4965a);
            }
        }
        return arrayList;
    }

    @NonNull
    public synchronized <Model, Data> ModelLoader<Model, Data> b(@NonNull Class<Model> cls, @NonNull Class<Data> cls2) {
        try {
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (Entry next : this.c) {
                if (this.e.contains(next)) {
                    z = true;
                } else if (next.a(cls, cls2)) {
                    this.e.add(next);
                    arrayList.add(b((Entry<?, ?>) next));
                    this.e.remove(next);
                }
            }
            if (arrayList.size() > 1) {
                return this.d.a(arrayList, this.f);
            } else if (arrayList.size() == 1) {
                return (ModelLoader) arrayList.get(0);
            } else if (z) {
                return a();
            } else {
                throw new Registry.NoModelLoaderAvailableException(cls, cls2);
            }
        } catch (Throwable th) {
            this.e.clear();
            throw th;
        }
    }

    @NonNull
    private <Model, Data> ModelLoaderFactory<Model, Data> a(@NonNull Entry<?, ?> entry) {
        return entry.b;
    }

    @NonNull
    private <Model, Data> ModelLoader<Model, Data> b(@NonNull Entry<?, ?> entry) {
        return (ModelLoader) Preconditions.a(entry.b.a(this));
    }

    @NonNull
    private static <Model, Data> ModelLoader<Model, Data> a() {
        return b;
    }

    private static class Entry<Model, Data> {

        /* renamed from: a  reason: collision with root package name */
        final Class<Data> f4965a;
        final ModelLoaderFactory<? extends Model, ? extends Data> b;
        private final Class<Model> c;

        public Entry(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
            this.c = cls;
            this.f4965a = cls2;
            this.b = modelLoaderFactory;
        }

        public boolean a(@NonNull Class<?> cls, @NonNull Class<?> cls2) {
            return a(cls) && this.f4965a.isAssignableFrom(cls2);
        }

        public boolean a(@NonNull Class<?> cls) {
            return this.c.isAssignableFrom(cls);
        }
    }

    static class Factory {
        Factory() {
        }

        @NonNull
        public <Model, Data> MultiModelLoader<Model, Data> a(@NonNull List<ModelLoader<Model, Data>> list, @NonNull Pools.Pool<List<Throwable>> pool) {
            return new MultiModelLoader<>(list, pool);
        }
    }

    private static class EmptyModelLoader implements ModelLoader<Object, Object> {
        @Nullable
        public ModelLoader.LoadData<Object> a(@NonNull Object obj, int i, int i2, @NonNull Options options) {
            return null;
        }

        public boolean a(@NonNull Object obj) {
            return false;
        }

        EmptyModelLoader() {
        }
    }
}
