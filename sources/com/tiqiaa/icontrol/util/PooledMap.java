package com.tiqiaa.icontrol.util;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class PooledMap<K, V> extends AbstractMap<K, V> {
    private Queue<PooledMap<K, V>.Entry> entries = new LinkedList();
    private int maxSize = 50;

    public PooledMap() {
    }

    public PooledMap(int i) {
        this.maxSize = i;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return new HashSet(this.entries);
    }

    public class Entry implements Map.Entry<K, V> {
        /* access modifiers changed from: private */
        public K key;
        /* access modifiers changed from: private */
        public V value;

        Entry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V v) {
            this.value = v;
            return v;
        }
    }

    public synchronized V put(K k, V v) {
        if (containsKey(k)) {
            remove(k);
        }
        if (this.entries.size() >= this.maxSize) {
            this.entries.remove();
        }
        this.entries.add(new Entry(k, v));
        return v;
    }

    public synchronized V get(Object obj) {
        for (Entry entry : this.entries) {
            if (entry.key.equals(obj)) {
                this.entries.remove(entry);
                this.entries.add(entry);
                return entry.value;
            }
        }
        return null;
    }

    public synchronized V remove(Object obj) {
        for (Entry entry : this.entries) {
            if (entry.key.equals(obj)) {
                this.entries.remove(entry);
                return entry.value;
            }
        }
        return null;
    }

    public boolean containsKey(Object obj) {
        for (Entry access$0 : this.entries) {
            if (access$0.key.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public Collection<V> values() {
        ArrayList arrayList = new ArrayList();
        for (Entry access$1 : this.entries) {
            arrayList.add(access$1.value);
        }
        return arrayList;
    }

    public Set<K> keySet() {
        HashSet hashSet = new HashSet();
        for (Entry access$0 : this.entries) {
            hashSet.add(access$0.key);
        }
        return hashSet;
    }
}
