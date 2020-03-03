package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.util.Queue;

public class ModelCache<A, B> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f4955a = 250;
    private final LruCache<ModelKey<A>, B> b;

    public ModelCache() {
        this(250);
    }

    public ModelCache(long j) {
        this.b = new LruCache<ModelKey<A>, B>(j) {
            /* access modifiers changed from: protected */
            public void a(@NonNull ModelKey<A> modelKey, @Nullable B b) {
                modelKey.a();
            }
        };
    }

    @Nullable
    public B a(A a2, int i, int i2) {
        ModelKey a3 = ModelKey.a(a2, i, i2);
        B c = this.b.c(a3);
        a3.a();
        return c;
    }

    public void a(A a2, int i, int i2, B b2) {
        this.b.b(ModelKey.a(a2, i, i2), b2);
    }

    public void a() {
        this.b.c();
    }

    @VisibleForTesting
    static final class ModelKey<A> {

        /* renamed from: a  reason: collision with root package name */
        private static final Queue<ModelKey<?>> f4957a = Util.a(0);
        private int b;
        private int c;
        private A d;

        static <A> ModelKey<A> a(A a2, int i, int i2) {
            ModelKey<A> poll;
            synchronized (f4957a) {
                poll = f4957a.poll();
            }
            if (poll == null) {
                poll = new ModelKey<>();
            }
            poll.b(a2, i, i2);
            return poll;
        }

        private ModelKey() {
        }

        private void b(A a2, int i, int i2) {
            this.d = a2;
            this.c = i;
            this.b = i2;
        }

        public void a() {
            synchronized (f4957a) {
                f4957a.offer(this);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ModelKey)) {
                return false;
            }
            ModelKey modelKey = (ModelKey) obj;
            if (this.c == modelKey.c && this.b == modelKey.b && this.d.equals(modelKey.d)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((this.b * 31) + this.c) * 31) + this.d.hashCode();
        }
    }
}
