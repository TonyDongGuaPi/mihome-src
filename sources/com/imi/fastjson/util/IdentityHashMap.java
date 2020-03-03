package com.imi.fastjson.util;

public class IdentityHashMap<K, V> {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6191a = 1024;
    private final Entry<K, V>[] b;
    private final int c;

    public IdentityHashMap() {
        this(1024);
    }

    public IdentityHashMap(int i) {
        this.c = i - 1;
        this.b = new Entry[i];
    }

    public final V a(K k) {
        for (Entry<K, V> entry = this.b[System.identityHashCode(k) & this.c]; entry != null; entry = entry.d) {
            if (k == entry.b) {
                return entry.c;
            }
        }
        return null;
    }

    public boolean a(K k, V v) {
        int identityHashCode = System.identityHashCode(k);
        int i = this.c & identityHashCode;
        for (Entry<K, V> entry = this.b[i]; entry != null; entry = entry.d) {
            if (k == entry.b) {
                entry.c = v;
                return true;
            }
        }
        this.b[i] = new Entry<>(k, v, identityHashCode, this.b[i]);
        return false;
    }

    public int b() {
        int i = 0;
        for (Entry<K, V> entry : this.b) {
            while (entry != null) {
                i++;
                entry = entry.d;
            }
        }
        return i;
    }

    protected static final class Entry<K, V> {

        /* renamed from: a  reason: collision with root package name */
        public final int f6192a;
        public final K b;
        public V c;
        public final Entry<K, V> d;

        public Entry(K k, V v, int i, Entry<K, V> entry) {
            this.b = k;
            this.c = v;
            this.d = entry;
            this.f6192a = i;
        }
    }
}
