package com.bumptech.glide.util;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;

public final class CachedHashCodeArrayMap<K, V> extends ArrayMap<K, V> {

    /* renamed from: a  reason: collision with root package name */
    private int f5095a;

    public void clear() {
        this.f5095a = 0;
        super.clear();
    }

    public V setValueAt(int i, V v) {
        this.f5095a = 0;
        return super.setValueAt(i, v);
    }

    public V put(K k, V v) {
        this.f5095a = 0;
        return super.put(k, v);
    }

    public void putAll(SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        this.f5095a = 0;
        super.putAll(simpleArrayMap);
    }

    public V removeAt(int i) {
        this.f5095a = 0;
        return super.removeAt(i);
    }

    public int hashCode() {
        if (this.f5095a == 0) {
            this.f5095a = super.hashCode();
        }
        return this.f5095a;
    }
}
