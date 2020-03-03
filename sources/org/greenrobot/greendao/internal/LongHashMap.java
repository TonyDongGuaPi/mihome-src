package org.greenrobot.greendao.internal;

import java.util.Arrays;
import org.greenrobot.greendao.DaoLog;

public final class LongHashMap<T> {

    /* renamed from: a  reason: collision with root package name */
    private Entry<T>[] f3529a;
    private int b;
    private int c;
    private int d;

    static final class Entry<T> {

        /* renamed from: a  reason: collision with root package name */
        final long f3530a;
        T b;
        Entry<T> c;

        Entry(long j, T t, Entry<T> entry) {
            this.f3530a = j;
            this.b = t;
            this.c = entry;
        }
    }

    public LongHashMap() {
        this(16);
    }

    public LongHashMap(int i) {
        this.b = i;
        this.c = (i * 4) / 3;
        this.f3529a = new Entry[i];
    }

    public boolean a(long j) {
        for (Entry<T> entry = this.f3529a[((((int) (j >>> 32)) ^ ((int) j)) & Integer.MAX_VALUE) % this.b]; entry != null; entry = entry.c) {
            if (entry.f3530a == j) {
                return true;
            }
        }
        return false;
    }

    public T b(long j) {
        for (Entry<T> entry = this.f3529a[((((int) (j >>> 32)) ^ ((int) j)) & Integer.MAX_VALUE) % this.b]; entry != null; entry = entry.c) {
            if (entry.f3530a == j) {
                return entry.b;
            }
        }
        return null;
    }

    public T a(long j, T t) {
        int i = ((((int) (j >>> 32)) ^ ((int) j)) & Integer.MAX_VALUE) % this.b;
        Entry<T> entry = this.f3529a[i];
        for (Entry<T> entry2 = entry; entry2 != null; entry2 = entry2.c) {
            if (entry2.f3530a == j) {
                T t2 = entry2.b;
                entry2.b = t;
                return t2;
            }
        }
        this.f3529a[i] = new Entry<>(j, t, entry);
        this.d++;
        if (this.d <= this.c) {
            return null;
        }
        a(this.b * 2);
        return null;
    }

    public T c(long j) {
        int i = ((((int) (j >>> 32)) ^ ((int) j)) & Integer.MAX_VALUE) % this.b;
        Entry<T> entry = this.f3529a[i];
        Entry<T> entry2 = null;
        while (entry != null) {
            Entry<T> entry3 = entry.c;
            if (entry.f3530a == j) {
                if (entry2 == null) {
                    this.f3529a[i] = entry3;
                } else {
                    entry2.c = entry3;
                }
                this.d--;
                return entry.b;
            }
            entry2 = entry;
            entry = entry3;
        }
        return null;
    }

    public void a() {
        this.d = 0;
        Arrays.fill(this.f3529a, (Object) null);
    }

    public int b() {
        return this.d;
    }

    public void a(int i) {
        Entry<T>[] entryArr = new Entry[i];
        for (Entry<T> entry : this.f3529a) {
            while (entry != null) {
                long j = entry.f3530a;
                int i2 = ((((int) j) ^ ((int) (j >>> 32))) & Integer.MAX_VALUE) % i;
                Entry<T> entry2 = entry.c;
                entry.c = entryArr[i2];
                entryArr[i2] = entry;
                entry = entry2;
            }
        }
        this.f3529a = entryArr;
        this.b = i;
        this.c = (i * 4) / 3;
    }

    public void b(int i) {
        a((i * 5) / 3);
    }

    public void c() {
        int i = 0;
        for (Entry<T> entry : this.f3529a) {
            while (entry != null && entry.c != null) {
                i++;
                entry = entry.c;
            }
        }
        DaoLog.b("load: " + (((float) this.d) / ((float) this.b)) + ", size: " + this.d + ", capa: " + this.b + ", collisions: " + i + ", collision ratio: " + (((float) i) / ((float) this.d)));
    }
}
