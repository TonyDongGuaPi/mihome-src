package com.bumptech.glide.provider;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.ResourceEncoder;
import java.util.ArrayList;
import java.util.List;

public class ResourceEncoderRegistry {

    /* renamed from: a  reason: collision with root package name */
    private final List<Entry<?>> f5055a = new ArrayList();

    public synchronized <Z> void a(@NonNull Class<Z> cls, @NonNull ResourceEncoder<Z> resourceEncoder) {
        this.f5055a.add(new Entry(cls, resourceEncoder));
    }

    public synchronized <Z> void b(@NonNull Class<Z> cls, @NonNull ResourceEncoder<Z> resourceEncoder) {
        this.f5055a.add(0, new Entry(cls, resourceEncoder));
    }

    @Nullable
    public synchronized <Z> ResourceEncoder<Z> a(@NonNull Class<Z> cls) {
        int size = this.f5055a.size();
        for (int i = 0; i < size; i++) {
            Entry entry = this.f5055a.get(i);
            if (entry.a(cls)) {
                return entry.f5056a;
            }
        }
        return null;
    }

    private static final class Entry<T> {

        /* renamed from: a  reason: collision with root package name */
        final ResourceEncoder<T> f5056a;
        private final Class<T> b;

        Entry(@NonNull Class<T> cls, @NonNull ResourceEncoder<T> resourceEncoder) {
            this.b = cls;
            this.f5056a = resourceEncoder;
        }

        /* access modifiers changed from: package-private */
        public boolean a(@NonNull Class<?> cls) {
            return this.b.isAssignableFrom(cls);
        }
    }
}
