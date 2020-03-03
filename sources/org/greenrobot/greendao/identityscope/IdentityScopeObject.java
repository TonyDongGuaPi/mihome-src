package org.greenrobot.greendao.identityscope;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class IdentityScopeObject<K, T> implements IdentityScope<K, T> {

    /* renamed from: a  reason: collision with root package name */
    private final HashMap<K, Reference<T>> f3526a = new HashMap<>();
    private final ReentrantLock b = new ReentrantLock();

    public void a(int i) {
    }

    public T a(K k) {
        this.b.lock();
        try {
            Reference reference = this.f3526a.get(k);
            if (reference != null) {
                return reference.get();
            }
            return null;
        } finally {
            this.b.unlock();
        }
    }

    public T b(K k) {
        Reference reference = this.f3526a.get(k);
        if (reference != null) {
            return reference.get();
        }
        return null;
    }

    public void a(K k, T t) {
        this.b.lock();
        try {
            this.f3526a.put(k, new WeakReference(t));
        } finally {
            this.b.unlock();
        }
    }

    public void b(K k, T t) {
        this.f3526a.put(k, new WeakReference(t));
    }

    public boolean c(K k, T t) {
        boolean z;
        this.b.lock();
        try {
            if (a(k) != t || t == null) {
                z = false;
            } else {
                c(k);
                z = true;
            }
            return z;
        } finally {
            this.b.unlock();
        }
    }

    public void c(K k) {
        this.b.lock();
        try {
            this.f3526a.remove(k);
        } finally {
            this.b.unlock();
        }
    }

    public void a(Iterable<K> iterable) {
        this.b.lock();
        try {
            for (K remove : iterable) {
                this.f3526a.remove(remove);
            }
        } finally {
            this.b.unlock();
        }
    }

    public void a() {
        this.b.lock();
        try {
            this.f3526a.clear();
        } finally {
            this.b.unlock();
        }
    }

    public void b() {
        this.b.lock();
    }

    public void c() {
        this.b.unlock();
    }
}
