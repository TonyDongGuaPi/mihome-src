package com.bumptech.glide.util.pool;

import android.support.annotation.NonNull;
import android.support.v4.util.Pools;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class FactoryPools {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5108a = "FactoryPools";
    private static final int b = 20;
    private static final Resetter<Object> c = new Resetter<Object>() {
        public void a(@NonNull Object obj) {
        }
    };

    public interface Factory<T> {
        T b();
    }

    public interface Poolable {
        @NonNull
        StateVerifier d_();
    }

    public interface Resetter<T> {
        void a(@NonNull T t);
    }

    private FactoryPools() {
    }

    @NonNull
    public static <T extends Poolable> Pools.Pool<T> a(int i, @NonNull Factory<T> factory) {
        return a(new Pools.SimplePool(i), factory);
    }

    @NonNull
    public static <T extends Poolable> Pools.Pool<T> b(int i, @NonNull Factory<T> factory) {
        return a(new Pools.SynchronizedPool(i), factory);
    }

    @NonNull
    public static <T> Pools.Pool<List<T>> a() {
        return a(20);
    }

    @NonNull
    public static <T> Pools.Pool<List<T>> a(int i) {
        return a(new Pools.SynchronizedPool(i), new Factory<List<T>>() {
            @NonNull
            /* renamed from: a */
            public List<T> b() {
                return new ArrayList();
            }
        }, new Resetter<List<T>>() {
            public void a(@NonNull List<T> list) {
                list.clear();
            }
        });
    }

    @NonNull
    private static <T extends Poolable> Pools.Pool<T> a(@NonNull Pools.Pool<T> pool, @NonNull Factory<T> factory) {
        return a(pool, factory, b());
    }

    @NonNull
    private static <T> Pools.Pool<T> a(@NonNull Pools.Pool<T> pool, @NonNull Factory<T> factory, @NonNull Resetter<T> resetter) {
        return new FactoryPool(pool, factory, resetter);
    }

    @NonNull
    private static <T> Resetter<T> b() {
        return c;
    }

    private static final class FactoryPool<T> implements Pools.Pool<T> {

        /* renamed from: a  reason: collision with root package name */
        private final Factory<T> f5109a;
        private final Resetter<T> b;
        private final Pools.Pool<T> c;

        FactoryPool(@NonNull Pools.Pool<T> pool, @NonNull Factory<T> factory, @NonNull Resetter<T> resetter) {
            this.c = pool;
            this.f5109a = factory;
            this.b = resetter;
        }

        public T acquire() {
            T acquire = this.c.acquire();
            if (acquire == null) {
                acquire = this.f5109a.b();
                if (Log.isLoggable(FactoryPools.f5108a, 2)) {
                    Log.v(FactoryPools.f5108a, "Created new " + acquire.getClass());
                }
            }
            if (acquire instanceof Poolable) {
                ((Poolable) acquire).d_().a(false);
            }
            return acquire;
        }

        public boolean release(@NonNull T t) {
            if (t instanceof Poolable) {
                ((Poolable) t).d_().a(true);
            }
            this.b.a(t);
            return this.c.release(t);
        }
    }
}
