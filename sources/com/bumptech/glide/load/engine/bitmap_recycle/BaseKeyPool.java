package com.bumptech.glide.load.engine.bitmap_recycle;

import com.bumptech.glide.load.engine.bitmap_recycle.Poolable;
import com.bumptech.glide.util.Util;
import java.util.Queue;

abstract class BaseKeyPool<T extends Poolable> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f4890a = 20;
    private final Queue<T> b = Util.a(20);

    /* access modifiers changed from: package-private */
    public abstract T b();

    BaseKeyPool() {
    }

    /* access modifiers changed from: package-private */
    public T c() {
        T t = (Poolable) this.b.poll();
        return t == null ? b() : t;
    }

    public void a(T t) {
        if (this.b.size() < 20) {
            this.b.offer(t);
        }
    }
}
