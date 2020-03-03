package android.arch.core.internal;

import android.arch.core.internal.SafeIterableMap;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class FastSafeIterableMap<K, V> extends SafeIterableMap<K, V> {

    /* renamed from: a  reason: collision with root package name */
    private HashMap<K, SafeIterableMap.Entry<K, V>> f424a = new HashMap<>();

    /* access modifiers changed from: protected */
    public SafeIterableMap.Entry<K, V> a(K k) {
        return this.f424a.get(k);
    }

    public V a(@NonNull K k, @NonNull V v) {
        SafeIterableMap.Entry a2 = a(k);
        if (a2 != null) {
            return a2.b;
        }
        this.f424a.put(k, b(k, v));
        return null;
    }

    public V b(@NonNull K k) {
        V b = super.b(k);
        this.f424a.remove(k);
        return b;
    }

    public boolean c(K k) {
        return this.f424a.containsKey(k);
    }

    public Map.Entry<K, V> d(K k) {
        if (c(k)) {
            return this.f424a.get(k).d;
        }
        return null;
    }
}
