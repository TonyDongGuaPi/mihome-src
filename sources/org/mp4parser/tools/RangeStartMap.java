package org.mp4parser.tools;

import java.lang.Comparable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RangeStartMap<K extends Comparable, V> implements Map<K, V> {

    /* renamed from: a  reason: collision with root package name */
    TreeMap<K, V> f4111a = new TreeMap<>(new Comparator<K>() {
        /* renamed from: a */
        public int compare(K k, K k2) {
            return -k.compareTo(k2);
        }
    });

    public boolean containsValue(Object obj) {
        return false;
    }

    public RangeStartMap() {
    }

    public RangeStartMap(K k, V v) {
        put(k, v);
    }

    public int size() {
        return this.f4111a.size();
    }

    public boolean isEmpty() {
        return this.f4111a.isEmpty();
    }

    public boolean containsKey(Object obj) {
        return this.f4111a.get(obj) != null;
    }

    public V get(Object obj) {
        if (!(obj instanceof Comparable)) {
            return null;
        }
        Comparable comparable = (Comparable) obj;
        if (isEmpty()) {
            return null;
        }
        Iterator<K> it = this.f4111a.keySet().iterator();
        K next = it.next();
        while (true) {
            Comparable comparable2 = (Comparable) next;
            if (!it.hasNext()) {
                return this.f4111a.get(comparable2);
            }
            if (comparable.compareTo(comparable2) >= 0) {
                return this.f4111a.get(comparable2);
            }
            next = it.next();
        }
    }

    /* renamed from: a */
    public V put(K k, V v) {
        return this.f4111a.put(k, v);
    }

    public V remove(Object obj) {
        if (!(obj instanceof Comparable)) {
            return null;
        }
        Comparable comparable = (Comparable) obj;
        if (isEmpty()) {
            return null;
        }
        Iterator<K> it = this.f4111a.keySet().iterator();
        K next = it.next();
        while (true) {
            Comparable comparable2 = (Comparable) next;
            if (!it.hasNext()) {
                return this.f4111a.remove(comparable2);
            }
            if (comparable.compareTo(comparable2) >= 0) {
                return this.f4111a.remove(comparable2);
            }
            next = it.next();
        }
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        this.f4111a.putAll(map);
    }

    public void clear() {
        this.f4111a.clear();
    }

    public Set<K> keySet() {
        return this.f4111a.keySet();
    }

    public Collection<V> values() {
        return this.f4111a.values();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return this.f4111a.entrySet();
    }
}
