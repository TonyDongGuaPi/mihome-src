package miuipub.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import miuipub.util.concurrent.ConcurrentRingQueue;

public final class Pools {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final HashMap<Class<?>, InstanceHolder<?>> f3019a = new HashMap<>();
    /* access modifiers changed from: private */
    public static final HashMap<Class<?>, SoftReferenceInstanceHolder<?>> b = new HashMap<>();
    private static final Pool<StringBuilder> c = b(new Manager<StringBuilder>() {
        /* renamed from: a */
        public StringBuilder b() {
            return new StringBuilder();
        }

        public void a(StringBuilder sb) {
            sb.setLength(0);
        }
    }, 4);

    private interface IInstanceHolder<T> {
        Class<T> a();

        void a(int i);

        boolean a(T t);

        int b();

        T c();
    }

    public static abstract class Manager<T> {
        public void a(T t) {
        }

        public abstract T b();

        public void b(T t) {
        }

        public void c(T t) {
        }
    }

    public interface Pool<T> {
        T b();

        void b(T t);

        void c();

        int d();
    }

    private static class InstanceHolder<T> implements IInstanceHolder<T> {

        /* renamed from: a  reason: collision with root package name */
        private final Class<T> f3022a;
        private final ConcurrentRingQueue<T> b;

        InstanceHolder(Class<T> cls, int i) {
            this.f3022a = cls;
            this.b = new ConcurrentRingQueue<>(i, false, true);
        }

        public Class<T> a() {
            return this.f3022a;
        }

        public int b() {
            return this.b.d();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:20:0x002f, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized void a(int r3) {
            /*
                r2 = this;
                monitor-enter(r2)
                miuipub.util.concurrent.ConcurrentRingQueue<T> r0 = r2.b     // Catch:{ all -> 0x0030 }
                int r0 = r0.d()     // Catch:{ all -> 0x0030 }
                int r3 = r3 + r0
                if (r3 > 0) goto L_0x0020
                java.util.HashMap r3 = miuipub.util.Pools.f3019a     // Catch:{ all -> 0x0030 }
                monitor-enter(r3)     // Catch:{ all -> 0x0030 }
                java.util.HashMap r0 = miuipub.util.Pools.f3019a     // Catch:{ all -> 0x001d }
                java.lang.Class r1 = r2.a()     // Catch:{ all -> 0x001d }
                r0.remove(r1)     // Catch:{ all -> 0x001d }
                monitor-exit(r3)     // Catch:{ all -> 0x001d }
                monitor-exit(r2)
                return
            L_0x001d:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x001d }
                throw r0     // Catch:{ all -> 0x0030 }
            L_0x0020:
                if (r3 <= 0) goto L_0x0028
                miuipub.util.concurrent.ConcurrentRingQueue<T> r0 = r2.b     // Catch:{ all -> 0x0030 }
                r0.a((int) r3)     // Catch:{ all -> 0x0030 }
                goto L_0x002e
            L_0x0028:
                miuipub.util.concurrent.ConcurrentRingQueue<T> r0 = r2.b     // Catch:{ all -> 0x0030 }
                int r3 = -r3
                r0.b((int) r3)     // Catch:{ all -> 0x0030 }
            L_0x002e:
                monitor-exit(r2)
                return
            L_0x0030:
                r3 = move-exception
                monitor-exit(r2)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: miuipub.util.Pools.InstanceHolder.a(int):void");
        }

        public T c() {
            return this.b.g();
        }

        public boolean a(T t) {
            return this.b.b(t);
        }
    }

    private static class SoftReferenceInstanceHolder<T> implements IInstanceHolder<T> {

        /* renamed from: a  reason: collision with root package name */
        private final Class<T> f3023a;
        private volatile SoftReference<T>[] b;
        private volatile int c = 0;
        private volatile int d;

        SoftReferenceInstanceHolder(Class<T> cls, int i) {
            this.f3023a = cls;
            this.d = i;
            this.b = new SoftReference[i];
        }

        public Class<T> a() {
            return this.f3023a;
        }

        public int b() {
            return this.d;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:20:0x002e, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized void a(int r4) {
            /*
                r3 = this;
                monitor-enter(r3)
                int r0 = r3.d     // Catch:{ all -> 0x002f }
                int r4 = r4 + r0
                if (r4 > 0) goto L_0x001c
                java.util.HashMap r4 = miuipub.util.Pools.b     // Catch:{ all -> 0x002f }
                monitor-enter(r4)     // Catch:{ all -> 0x002f }
                java.util.HashMap r0 = miuipub.util.Pools.b     // Catch:{ all -> 0x0019 }
                java.lang.Class r1 = r3.a()     // Catch:{ all -> 0x0019 }
                r0.remove(r1)     // Catch:{ all -> 0x0019 }
                monitor-exit(r4)     // Catch:{ all -> 0x0019 }
                monitor-exit(r3)
                return
            L_0x0019:
                r0 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x0019 }
                throw r0     // Catch:{ all -> 0x002f }
            L_0x001c:
                r3.d = r4     // Catch:{ all -> 0x002f }
                java.lang.ref.SoftReference<T>[] r0 = r3.b     // Catch:{ all -> 0x002f }
                int r1 = r3.c     // Catch:{ all -> 0x002f }
                int r2 = r0.length     // Catch:{ all -> 0x002f }
                if (r4 <= r2) goto L_0x002d
                java.lang.ref.SoftReference[] r4 = new java.lang.ref.SoftReference[r4]     // Catch:{ all -> 0x002f }
                r2 = 0
                java.lang.System.arraycopy(r0, r2, r4, r2, r1)     // Catch:{ all -> 0x002f }
                r3.b = r4     // Catch:{ all -> 0x002f }
            L_0x002d:
                monitor-exit(r3)
                return
            L_0x002f:
                r4 = move-exception
                monitor-exit(r3)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: miuipub.util.Pools.SoftReferenceInstanceHolder.a(int):void");
        }

        public synchronized T c() {
            int i = this.c;
            SoftReference<T>[] softReferenceArr = this.b;
            while (i != 0) {
                i--;
                if (softReferenceArr[i] != null) {
                    T t = softReferenceArr[i].get();
                    softReferenceArr[i] = null;
                    if (t != null) {
                        this.c = i;
                        return t;
                    }
                }
            }
            return null;
        }

        public synchronized boolean a(T t) {
            int i = this.c;
            SoftReference<T>[] softReferenceArr = this.b;
            if (i >= this.d) {
                int i2 = 0;
                while (i2 < i) {
                    if (softReferenceArr[i2] != null) {
                        if (softReferenceArr[i2].get() != null) {
                            i2++;
                        }
                    }
                    softReferenceArr[i2] = new SoftReference<>(t);
                    return true;
                }
                return false;
            }
            softReferenceArr[i] = new SoftReference<>(t);
            this.c = i + 1;
            return true;
        }
    }

    public static Pool<StringBuilder> a() {
        return c;
    }

    static <T> InstanceHolder<T> a(Class<T> cls, int i) {
        InstanceHolder<T> instanceHolder;
        synchronized (f3019a) {
            instanceHolder = f3019a.get(cls);
            if (instanceHolder == null) {
                instanceHolder = new InstanceHolder<>(cls, i);
                f3019a.put(cls, instanceHolder);
            } else {
                instanceHolder.a(i);
            }
        }
        return instanceHolder;
    }

    static <T> void a(InstanceHolder<T> instanceHolder, int i) {
        synchronized (f3019a) {
            instanceHolder.a(-i);
        }
    }

    static <T> SoftReferenceInstanceHolder<T> b(Class<T> cls, int i) {
        SoftReferenceInstanceHolder<T> softReferenceInstanceHolder;
        synchronized (b) {
            softReferenceInstanceHolder = b.get(cls);
            if (softReferenceInstanceHolder == null) {
                softReferenceInstanceHolder = new SoftReferenceInstanceHolder<>(cls, i);
                b.put(cls, softReferenceInstanceHolder);
            } else {
                softReferenceInstanceHolder.a(i);
            }
        }
        return softReferenceInstanceHolder;
    }

    static <T> void a(SoftReferenceInstanceHolder<T> softReferenceInstanceHolder, int i) {
        synchronized (b) {
            softReferenceInstanceHolder.a(-i);
        }
    }

    static abstract class BasePool<T> implements Pool<T> {

        /* renamed from: a  reason: collision with root package name */
        private final Manager<T> f3020a;
        private final int b;
        private IInstanceHolder<T> c;
        private final Object d = new Object() {
            /* access modifiers changed from: protected */
            public void finalize() throws Throwable {
                try {
                    BasePool.this.c();
                } finally {
                    super.finalize();
                }
            }
        };

        /* access modifiers changed from: package-private */
        public abstract IInstanceHolder<T> a(Class<T> cls, int i);

        /* access modifiers changed from: package-private */
        public abstract void a(IInstanceHolder<T> iInstanceHolder, int i);

        public BasePool(Manager<T> manager, int i) {
            if (manager == null || i < 1) {
                this.b = this.d.hashCode();
                throw new IllegalArgumentException("manager cannot be null and size cannot less then 1");
            }
            this.f3020a = manager;
            this.b = i;
            T b2 = this.f3020a.b();
            if (b2 != null) {
                this.c = a(b2.getClass(), i);
                a(b2);
                return;
            }
            throw new IllegalStateException("manager create instance cannot return null");
        }

        /* access modifiers changed from: protected */
        public final T a() {
            if (this.c != null) {
                T c2 = this.c.c();
                if (c2 == null && (c2 = this.f3020a.b()) == null) {
                    throw new IllegalStateException("manager create instance cannot return null");
                }
                this.f3020a.b(c2);
                return c2;
            }
            throw new IllegalStateException("Cannot acquire object after close()");
        }

        /* access modifiers changed from: protected */
        public final void a(T t) {
            if (this.c == null) {
                throw new IllegalStateException("Cannot release object after close()");
            } else if (t != null) {
                this.f3020a.a(t);
                if (!this.c.a(t)) {
                    this.f3020a.c(t);
                }
            }
        }

        public T b() {
            return a();
        }

        public void b(T t) {
            a(t);
        }

        public void c() {
            if (this.c != null) {
                a(this.c, this.b);
                this.c = null;
            }
        }

        public int d() {
            if (this.c == null) {
                return 0;
            }
            return this.b;
        }
    }

    public static <T> SimplePool<T> a(Manager<T> manager, int i) {
        return new SimplePool<>(manager, i);
    }

    public static <T> SoftReferencePool<T> b(Manager<T> manager, int i) {
        return new SoftReferencePool<>(manager, i);
    }

    public static class SimplePool<T> extends BasePool<T> {
        public /* bridge */ /* synthetic */ Object b() {
            return super.b();
        }

        public /* bridge */ /* synthetic */ void b(Object obj) {
            super.b(obj);
        }

        public /* bridge */ /* synthetic */ void c() {
            super.c();
        }

        public /* bridge */ /* synthetic */ int d() {
            return super.d();
        }

        SimplePool(Manager<T> manager, int i) {
            super(manager, i);
        }

        /* access modifiers changed from: package-private */
        public final IInstanceHolder<T> a(Class<T> cls, int i) {
            return Pools.a(cls, i);
        }

        /* access modifiers changed from: package-private */
        public final void a(IInstanceHolder<T> iInstanceHolder, int i) {
            Pools.a((InstanceHolder) iInstanceHolder, i);
        }
    }

    public static class SoftReferencePool<T> extends BasePool<T> {
        public /* bridge */ /* synthetic */ Object b() {
            return super.b();
        }

        public /* bridge */ /* synthetic */ void b(Object obj) {
            super.b(obj);
        }

        public /* bridge */ /* synthetic */ void c() {
            super.c();
        }

        public /* bridge */ /* synthetic */ int d() {
            return super.d();
        }

        SoftReferencePool(Manager<T> manager, int i) {
            super(manager, i);
        }

        /* access modifiers changed from: package-private */
        public final IInstanceHolder<T> a(Class<T> cls, int i) {
            return Pools.b(cls, i);
        }

        /* access modifiers changed from: package-private */
        public final void a(IInstanceHolder<T> iInstanceHolder, int i) {
            Pools.a((SoftReferenceInstanceHolder) iInstanceHolder, i);
        }
    }
}
