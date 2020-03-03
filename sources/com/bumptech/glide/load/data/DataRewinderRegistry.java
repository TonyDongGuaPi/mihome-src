package com.bumptech.glide.load.data;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.util.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataRewinderRegistry {
    private static final DataRewinder.Factory<?> b = new DataRewinder.Factory<Object>() {
        @NonNull
        public DataRewinder<Object> a(@NonNull Object obj) {
            return new DefaultRewinder(obj);
        }

        @NonNull
        public Class<Object> a() {
            throw new UnsupportedOperationException("Not implemented");
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final Map<Class<?>, DataRewinder.Factory<?>> f4839a = new HashMap();

    public synchronized void a(@NonNull DataRewinder.Factory<?> factory) {
        this.f4839a.put(factory.a(), factory);
    }

    @NonNull
    public synchronized <T> DataRewinder<T> a(@NonNull T t) {
        DataRewinder.Factory<?> factory;
        Preconditions.a(t);
        factory = this.f4839a.get(t.getClass());
        if (factory == null) {
            Iterator<DataRewinder.Factory<?>> it = this.f4839a.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DataRewinder.Factory<?> next = it.next();
                if (next.a().isAssignableFrom(t.getClass())) {
                    factory = next;
                    break;
                }
            }
        }
        if (factory == null) {
            factory = b;
        }
        return factory.a(t);
    }

    private static final class DefaultRewinder implements DataRewinder<Object> {

        /* renamed from: a  reason: collision with root package name */
        private final Object f4840a;

        public void b() {
        }

        DefaultRewinder(@NonNull Object obj) {
            this.f4840a = obj;
        }

        @NonNull
        public Object a() {
            return this.f4840a;
        }
    }
}
