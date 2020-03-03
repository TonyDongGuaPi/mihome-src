package com.bumptech.glide.provider;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Encoder;
import java.util.ArrayList;
import java.util.List;

public class EncoderRegistry {

    /* renamed from: a  reason: collision with root package name */
    private final List<Entry<?>> f5048a = new ArrayList();

    @Nullable
    public synchronized <T> Encoder<T> a(@NonNull Class<T> cls) {
        for (Entry next : this.f5048a) {
            if (next.a(cls)) {
                return next.f5049a;
            }
        }
        return null;
    }

    public synchronized <T> void a(@NonNull Class<T> cls, @NonNull Encoder<T> encoder) {
        this.f5048a.add(new Entry(cls, encoder));
    }

    public synchronized <T> void b(@NonNull Class<T> cls, @NonNull Encoder<T> encoder) {
        this.f5048a.add(0, new Entry(cls, encoder));
    }

    private static final class Entry<T> {

        /* renamed from: a  reason: collision with root package name */
        final Encoder<T> f5049a;
        private final Class<T> b;

        Entry(@NonNull Class<T> cls, @NonNull Encoder<T> encoder) {
            this.b = cls;
            this.f5049a = encoder;
        }

        /* access modifiers changed from: package-private */
        public boolean a(@NonNull Class<?> cls) {
            return this.b.isAssignableFrom(cls);
        }
    }
}
