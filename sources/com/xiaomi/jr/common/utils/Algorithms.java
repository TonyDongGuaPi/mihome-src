package com.xiaomi.jr.common.utils;

import java.lang.ref.WeakReference;
import java.util.Collection;

public class Algorithms {
    public static <T> boolean a(Collection<WeakReference<T>> collection, T t) {
        return c(collection, t) == null && collection.add(new WeakReference(t));
    }

    public static <T> boolean b(Collection<WeakReference<T>> collection, T t) {
        WeakReference<T> c = c(collection, t);
        if (c == null) {
            return false;
        }
        collection.remove(c);
        return true;
    }

    public static <T> WeakReference<T> c(Collection<WeakReference<T>> collection, T t) {
        if (collection == null || t == null) {
            return null;
        }
        for (WeakReference<T> next : collection) {
            if (next.get() == t) {
                return next;
            }
        }
        return null;
    }

    public static <T> void a(Collection<WeakReference<T>> collection) {
        if (collection != null) {
            collection.clear();
        }
    }
}
