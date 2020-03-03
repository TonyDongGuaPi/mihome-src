package com.xiaomi.youpin.common.cache;

import android.text.TextUtils;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class MemoryCache<T> implements ICache<T> {

    /* renamed from: a  reason: collision with root package name */
    private final LinkedHashMap<String, T> f23231a = new LinkedHashMap<>(0, 0.75f, true);
    private int b;

    public MemoryCache(int i) {
        this.b = i;
    }

    public Set<String> a() {
        return new HashSet(this.f23231a.keySet());
    }

    public T d(String str) {
        T t;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        synchronized (this) {
            t = this.f23231a.get(str);
        }
        return t;
    }

    public boolean b(String str) {
        boolean containsKey;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        synchronized (this) {
            containsKey = this.f23231a.containsKey(str);
        }
        return containsKey;
    }

    public boolean a(String str, T t) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        synchronized (this) {
            this.f23231a.put(str, t);
        }
        c();
        return true;
    }

    public boolean c(String str) {
        Object remove;
        synchronized (this) {
            remove = this.f23231a.remove(str);
        }
        return remove != null;
    }

    public void b() {
        synchronized (this) {
            this.f23231a.clear();
        }
    }

    public void c() {
        if (this.b > 0) {
            while (true) {
                synchronized (this) {
                    if (this.f23231a.size() > this.b) {
                        this.f23231a.remove((String) this.f23231a.entrySet().iterator().next().getKey());
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public void d() {
        c();
    }
}
