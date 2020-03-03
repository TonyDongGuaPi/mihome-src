package com.xiaomi.mimc.protobuf;

import com.xiaomi.mimc.protobuf.Internal;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class LongArrayList extends AbstractProtobufList<Long> implements Internal.LongList, RandomAccess {
    private static final LongArrayList b = new LongArrayList();
    private long[] c;
    private int d;

    static {
        b.b();
    }

    public static LongArrayList d() {
        return b;
    }

    LongArrayList() {
        this(new long[10], 0);
    }

    private LongArrayList(long[] jArr, int i) {
        this.c = jArr;
        this.d = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LongArrayList)) {
            return super.equals(obj);
        }
        LongArrayList longArrayList = (LongArrayList) obj;
        if (this.d != longArrayList.d) {
            return false;
        }
        long[] jArr = longArrayList.c;
        for (int i = 0; i < this.d; i++) {
            if (this.c[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.d; i2++) {
            i = (i * 31) + Internal.a(this.c[i2]);
        }
        return i;
    }

    /* renamed from: b */
    public Internal.LongList e(int i) {
        if (i >= this.d) {
            return new LongArrayList(Arrays.copyOf(this.c, i), this.d);
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: c */
    public Long get(int i) {
        return Long.valueOf(a(i));
    }

    public long a(int i) {
        f(i);
        return this.c[i];
    }

    public int size() {
        return this.d;
    }

    /* renamed from: a */
    public Long set(int i, Long l) {
        return Long.valueOf(a(i, l.longValue()));
    }

    public long a(int i, long j) {
        c();
        f(i);
        long j2 = this.c[i];
        this.c[i] = j;
        return j2;
    }

    /* renamed from: b */
    public void add(int i, Long l) {
        b(i, l.longValue());
    }

    public void a(long j) {
        b(this.d, j);
    }

    private void b(int i, long j) {
        c();
        if (i < 0 || i > this.d) {
            throw new IndexOutOfBoundsException(g(i));
        }
        if (this.d < this.c.length) {
            System.arraycopy(this.c, i, this.c, i + 1, this.d - i);
        } else {
            long[] jArr = new long[(((this.d * 3) / 2) + 1)];
            System.arraycopy(this.c, 0, jArr, 0, i);
            System.arraycopy(this.c, i, jArr, i + 1, this.d - i);
            this.c = jArr;
        }
        this.c[i] = j;
        this.d++;
        this.modCount++;
    }

    public boolean addAll(Collection<? extends Long> collection) {
        c();
        if (collection == null) {
            throw new NullPointerException();
        } else if (!(collection instanceof LongArrayList)) {
            return super.addAll(collection);
        } else {
            LongArrayList longArrayList = (LongArrayList) collection;
            if (longArrayList.d == 0) {
                return false;
            }
            if (Integer.MAX_VALUE - this.d >= longArrayList.d) {
                int i = this.d + longArrayList.d;
                if (i > this.c.length) {
                    this.c = Arrays.copyOf(this.c, i);
                }
                System.arraycopy(longArrayList.c, 0, this.c, this.d, longArrayList.d);
                this.d = i;
                this.modCount++;
                return true;
            }
            throw new OutOfMemoryError();
        }
    }

    public boolean remove(Object obj) {
        c();
        for (int i = 0; i < this.d; i++) {
            if (obj.equals(Long.valueOf(this.c[i]))) {
                System.arraycopy(this.c, i + 1, this.c, i, this.d - i);
                this.d--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    public Long remove(int i) {
        c();
        f(i);
        long j = this.c[i];
        System.arraycopy(this.c, i + 1, this.c, i, this.d - i);
        this.d--;
        this.modCount++;
        return Long.valueOf(j);
    }

    private void f(int i) {
        if (i < 0 || i >= this.d) {
            throw new IndexOutOfBoundsException(g(i));
        }
    }

    private String g(int i) {
        return "Index:" + i + ", Size:" + this.d;
    }
}
