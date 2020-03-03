package miuipub.util.cache;

import java.lang.ref.SoftReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache<K, T> implements Cache<K, T> {

    /* renamed from: a  reason: collision with root package name */
    private ConcurrentHashMap<K, SoftReference<T>> f3052a = new ConcurrentHashMap<>();

    public void a(int i) {
    }

    public void a(K k, T t) {
        a(k, t, 0);
    }

    public void a(K k, T t, int i) {
        c();
        this.f3052a.put(k, new SoftReference(t));
    }

    public T a(K k) {
        c();
        SoftReference softReference = this.f3052a.get(k);
        if (softReference == null) {
            return null;
        }
        return softReference.get();
    }

    public void a() {
        for (Map.Entry<K, SoftReference<T>> value : this.f3052a.entrySet()) {
            ((SoftReference) value.getValue()).clear();
        }
        this.f3052a.clear();
    }

    public int b() {
        c();
        return this.f3052a.size();
    }

    private void c() {
        Iterator<Map.Entry<K, SoftReference<T>>> it = this.f3052a.entrySet().iterator();
        while (it.hasNext()) {
            if (((SoftReference) it.next().getValue()).get() == null) {
                it.remove();
            }
        }
    }
}
