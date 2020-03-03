package org.greenrobot.greendao.identityscope;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.concurrent.locks.ReentrantLock;
import org.greenrobot.greendao.internal.LongHashMap;

public class IdentityScopeLong<T> implements IdentityScope<Long, T> {

    /* renamed from: a  reason: collision with root package name */
    private final LongHashMap<Reference<T>> f3525a = new LongHashMap<>();
    private final ReentrantLock b = new ReentrantLock();

    public T a(Long l) {
        return a(l.longValue());
    }

    public T b(Long l) {
        return b(l.longValue());
    }

    public T a(long j) {
        this.b.lock();
        try {
            Reference b2 = this.f3525a.b(j);
            if (b2 != null) {
                return b2.get();
            }
            return null;
        } finally {
            this.b.unlock();
        }
    }

    public T b(long j) {
        Reference b2 = this.f3525a.b(j);
        if (b2 != null) {
            return b2.get();
        }
        return null;
    }

    public void a(Long l, T t) {
        a(l.longValue(), t);
    }

    public void b(Long l, T t) {
        b(l.longValue(), t);
    }

    public void a(long j, T t) {
        this.b.lock();
        try {
            this.f3525a.a(j, new WeakReference(t));
        } finally {
            this.b.unlock();
        }
    }

    public void b(long j, T t) {
        this.f3525a.a(j, new WeakReference(t));
    }

    public boolean c(Long l, T t) {
        boolean z;
        this.b.lock();
        try {
            if (a(l) != t || t == null) {
                z = false;
            } else {
                c(l);
                z = true;
            }
            return z;
        } finally {
            this.b.unlock();
        }
    }

    public void c(Long l) {
        this.b.lock();
        try {
            this.f3525a.c(l.longValue());
        } finally {
            this.b.unlock();
        }
    }

    public void a(Iterable<Long> iterable) {
        this.b.lock();
        try {
            for (Long longValue : iterable) {
                this.f3525a.c(longValue.longValue());
            }
        } finally {
            this.b.unlock();
        }
    }

    public void a() {
        this.b.lock();
        try {
            this.f3525a.a();
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

    public void a(int i) {
        this.f3525a.b(i);
    }
}
