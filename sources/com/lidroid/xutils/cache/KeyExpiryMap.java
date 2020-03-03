package com.lidroid.xutils.cache;

import java.util.concurrent.ConcurrentHashMap;

public class KeyExpiryMap<K, V> extends ConcurrentHashMap<K, Long> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f6304a = 16;
    private static final long serialVersionUID = 1;

    public KeyExpiryMap(int i, float f, int i2) {
        super(i, f, i2);
    }

    public KeyExpiryMap(int i, float f) {
        super(i, f, 16);
    }

    public KeyExpiryMap(int i) {
        super(i);
    }

    public KeyExpiryMap() {
    }

    public synchronized Long get(Object obj) {
        if (!containsKey(obj)) {
            return null;
        }
        return (Long) super.get(obj);
    }

    public synchronized Long put(K k, Long l) {
        if (containsKey(k)) {
            remove((Object) k);
        }
        return (Long) super.put(k, l);
    }

    public synchronized boolean containsKey(Object obj) {
        boolean z;
        z = false;
        Long l = (Long) super.get(obj);
        if (l == null || System.currentTimeMillis() >= l.longValue()) {
            remove(obj);
        } else {
            z = true;
        }
        return z;
    }

    public synchronized Long remove(Object obj) {
        return (Long) super.remove(obj);
    }

    public synchronized void clear() {
        super.clear();
    }
}
