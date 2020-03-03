package com.bumptech.glide.load.engine.bitmap_recycle;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.bumptech.glide.util.Preconditions;
import com.taobao.weex.el.parse.Operators;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public final class LruArrayPool implements ArrayPool {
    @VisibleForTesting
    static final int b = 8;
    private static final int c = 4194304;
    private static final int d = 2;
    private final GroupedLinkedMap<Key, Object> e;
    private final KeyPool f;
    private final Map<Class<?>, NavigableMap<Integer, Integer>> g;
    private final Map<Class<?>, ArrayAdapterInterface<?>> h;
    private final int i;
    private int j;

    @VisibleForTesting
    public LruArrayPool() {
        this.e = new GroupedLinkedMap<>();
        this.f = new KeyPool();
        this.g = new HashMap();
        this.h = new HashMap();
        this.i = 4194304;
    }

    public LruArrayPool(int i2) {
        this.e = new GroupedLinkedMap<>();
        this.f = new KeyPool();
        this.g = new HashMap();
        this.h = new HashMap();
        this.i = i2;
    }

    @Deprecated
    public <T> void a(T t, Class<T> cls) {
        a(t);
    }

    public synchronized <T> void a(T t) {
        Class<?> cls = t.getClass();
        ArrayAdapterInterface<?> b2 = b(cls);
        int a2 = b2.a(t);
        int b3 = b2.b() * a2;
        if (b(b3)) {
            Key a3 = this.f.a(a2, cls);
            this.e.a(a3, t);
            NavigableMap<Integer, Integer> a4 = a(cls);
            Integer num = (Integer) a4.get(Integer.valueOf(a3.f4895a));
            Integer valueOf = Integer.valueOf(a3.f4895a);
            int i2 = 1;
            if (num != null) {
                i2 = 1 + num.intValue();
            }
            a4.put(valueOf, Integer.valueOf(i2));
            this.j += b3;
            d();
        }
    }

    public synchronized <T> T b(int i2, Class<T> cls) {
        return a(this.f.a(i2, cls), cls);
    }

    public synchronized <T> T a(int i2, Class<T> cls) {
        Key key;
        Integer ceilingKey = a((Class<?>) cls).ceilingKey(Integer.valueOf(i2));
        if (a(i2, ceilingKey)) {
            key = this.f.a(ceilingKey.intValue(), cls);
        } else {
            key = this.f.a(i2, cls);
        }
        return a(key, cls);
    }

    private <T> T a(Key key, Class<T> cls) {
        ArrayAdapterInterface<T> b2 = b(cls);
        T a2 = a(key);
        if (a2 != null) {
            this.j -= b2.a(a2) * b2.b();
            c(b2.a(a2), cls);
        }
        if (a2 != null) {
            return a2;
        }
        if (Log.isLoggable(b2.a(), 2)) {
            Log.v(b2.a(), "Allocated " + key.f4895a + " bytes");
        }
        return b2.a(key.f4895a);
    }

    @Nullable
    private <T> T a(Key key) {
        return this.e.a(key);
    }

    private boolean b(int i2) {
        return i2 <= this.i / 2;
    }

    private boolean a(int i2, Integer num) {
        return num != null && (c() || num.intValue() <= i2 * 8);
    }

    private boolean c() {
        return this.j == 0 || this.i / this.j >= 2;
    }

    public synchronized void a() {
        c(0);
    }

    public synchronized void a(int i2) {
        if (i2 >= 40) {
            try {
                a();
            } catch (Throwable th) {
                throw th;
            }
        } else if (i2 >= 20 || i2 == 15) {
            c(this.i / 2);
        }
    }

    private void d() {
        c(this.i);
    }

    private void c(int i2) {
        while (this.j > i2) {
            Object a2 = this.e.a();
            Preconditions.a(a2);
            ArrayAdapterInterface b2 = b(a2);
            this.j -= b2.a(a2) * b2.b();
            c(b2.a(a2), a2.getClass());
            if (Log.isLoggable(b2.a(), 2)) {
                Log.v(b2.a(), "evicted: " + b2.a(a2));
            }
        }
    }

    private void c(int i2, Class<?> cls) {
        NavigableMap<Integer, Integer> a2 = a(cls);
        Integer num = (Integer) a2.get(Integer.valueOf(i2));
        if (num == null) {
            throw new NullPointerException("Tried to decrement empty size, size: " + i2 + ", this: " + this);
        } else if (num.intValue() == 1) {
            a2.remove(Integer.valueOf(i2));
        } else {
            a2.put(Integer.valueOf(i2), Integer.valueOf(num.intValue() - 1));
        }
    }

    private NavigableMap<Integer, Integer> a(Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMap = this.g.get(cls);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.g.put(cls, treeMap);
        return treeMap;
    }

    private <T> ArrayAdapterInterface<T> b(T t) {
        return b(t.getClass());
    }

    private <T> ArrayAdapterInterface<T> b(Class<T> cls) {
        ArrayAdapterInterface<T> arrayAdapterInterface = this.h.get(cls);
        if (arrayAdapterInterface == null) {
            if (cls.equals(int[].class)) {
                arrayAdapterInterface = new IntegerArrayAdapter();
            } else if (cls.equals(byte[].class)) {
                arrayAdapterInterface = new ByteArrayAdapter();
            } else {
                throw new IllegalArgumentException("No array pool found for: " + cls.getSimpleName());
            }
            this.h.put(cls, arrayAdapterInterface);
        }
        return arrayAdapterInterface;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        int i2 = 0;
        for (Class next : this.g.keySet()) {
            for (Integer num : this.g.get(next).keySet()) {
                i2 += num.intValue() * ((Integer) this.g.get(next).get(num)).intValue() * b(next).b();
            }
        }
        return i2;
    }

    private static final class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        /* access modifiers changed from: package-private */
        public Key a(int i, Class<?> cls) {
            Key key = (Key) c();
            key.a(i, cls);
            return key;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Key b() {
            return new Key(this);
        }
    }

    private static final class Key implements Poolable {

        /* renamed from: a  reason: collision with root package name */
        int f4895a;
        private final KeyPool b;
        private Class<?> c;

        Key(KeyPool keyPool) {
            this.b = keyPool;
        }

        /* access modifiers changed from: package-private */
        public void a(int i, Class<?> cls) {
            this.f4895a = i;
            this.c = cls;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Key)) {
                return false;
            }
            Key key = (Key) obj;
            if (this.f4895a == key.f4895a && this.c == key.c) {
                return true;
            }
            return false;
        }

        public String toString() {
            return "Key{size=" + this.f4895a + "array=" + this.c + Operators.BLOCK_END;
        }

        public void a() {
            this.b.a(this);
        }

        public int hashCode() {
            return (this.f4895a * 31) + (this.c != null ? this.c.hashCode() : 0);
        }
    }
}
