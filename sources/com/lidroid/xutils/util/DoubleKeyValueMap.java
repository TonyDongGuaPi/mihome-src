package com.lidroid.xutils.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DoubleKeyValueMap<K1, K2, V> {

    /* renamed from: a  reason: collision with root package name */
    private ConcurrentHashMap<K1, ConcurrentHashMap<K2, V>> f6367a = new ConcurrentHashMap<>();

    public void a(K1 k1, K2 k2, V v) {
        if (k1 != null && k2 != null && v != null) {
            if (this.f6367a.containsKey(k1)) {
                ConcurrentHashMap concurrentHashMap = this.f6367a.get(k1);
                if (concurrentHashMap != null) {
                    concurrentHashMap.put(k2, v);
                    return;
                }
                ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();
                concurrentHashMap2.put(k2, v);
                this.f6367a.put(k1, concurrentHashMap2);
                return;
            }
            ConcurrentHashMap concurrentHashMap3 = new ConcurrentHashMap();
            concurrentHashMap3.put(k2, v);
            this.f6367a.put(k1, concurrentHashMap3);
        }
    }

    public Set<K1> a() {
        return this.f6367a.keySet();
    }

    public ConcurrentHashMap<K2, V> a(K1 k1) {
        return this.f6367a.get(k1);
    }

    public V a(K1 k1, K2 k2) {
        ConcurrentHashMap concurrentHashMap = this.f6367a.get(k1);
        if (concurrentHashMap == null) {
            return null;
        }
        return concurrentHashMap.get(k2);
    }

    public Collection<V> b(K1 k1) {
        ConcurrentHashMap concurrentHashMap = this.f6367a.get(k1);
        if (concurrentHashMap == null) {
            return null;
        }
        return concurrentHashMap.values();
    }

    public Collection<V> b() {
        Set<K1> keySet = this.f6367a.keySet();
        if (keySet == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (K1 k1 : keySet) {
            Collection values = this.f6367a.get(k1).values();
            if (values != null) {
                arrayList.addAll(values);
            }
        }
        return arrayList;
    }

    public boolean b(K1 k1, K2 k2) {
        if (this.f6367a.containsKey(k1)) {
            return this.f6367a.get(k1).containsKey(k2);
        }
        return false;
    }

    public boolean c(K1 k1) {
        return this.f6367a.containsKey(k1);
    }

    public int c() {
        int i = 0;
        if (this.f6367a.size() == 0) {
            return 0;
        }
        for (ConcurrentHashMap<K2, V> size : this.f6367a.values()) {
            i += size.size();
        }
        return i;
    }

    public void d(K1 k1) {
        this.f6367a.remove(k1);
    }

    public void c(K1 k1, K2 k2) {
        ConcurrentHashMap concurrentHashMap = this.f6367a.get(k1);
        if (concurrentHashMap != null) {
            concurrentHashMap.remove(k2);
        }
    }

    public void d() {
        if (this.f6367a.size() > 0) {
            for (ConcurrentHashMap<K2, V> clear : this.f6367a.values()) {
                clear.clear();
            }
            this.f6367a.clear();
        }
    }
}
