package com.xiaomi.mimc.protobuf;

import com.xiaomi.mimc.protobuf.Internal;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class BooleanArrayList extends AbstractProtobufList<Boolean> implements Internal.BooleanList, RandomAccess {
    private static final BooleanArrayList b = new BooleanArrayList();
    private boolean[] c;
    private int d;

    static {
        b.b();
    }

    public static BooleanArrayList d() {
        return b;
    }

    BooleanArrayList() {
        this(new boolean[10], 0);
    }

    private BooleanArrayList(boolean[] zArr, int i) {
        this.c = zArr;
        this.d = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BooleanArrayList)) {
            return super.equals(obj);
        }
        BooleanArrayList booleanArrayList = (BooleanArrayList) obj;
        if (this.d != booleanArrayList.d) {
            return false;
        }
        boolean[] zArr = booleanArrayList.c;
        for (int i = 0; i < this.d; i++) {
            if (this.c[i] != zArr[i]) {
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

    /* renamed from: a */
    public Internal.BooleanList e(int i) {
        if (i >= this.d) {
            return new BooleanArrayList(Arrays.copyOf(this.c, i), this.d);
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: b */
    public Boolean get(int i) {
        return Boolean.valueOf(c(i));
    }

    public boolean c(int i) {
        f(i);
        return this.c[i];
    }

    public int size() {
        return this.d;
    }

    /* renamed from: a */
    public Boolean set(int i, Boolean bool) {
        return Boolean.valueOf(a(i, bool.booleanValue()));
    }

    public boolean a(int i, boolean z) {
        c();
        f(i);
        boolean z2 = this.c[i];
        this.c[i] = z;
        return z2;
    }

    /* renamed from: b */
    public void add(int i, Boolean bool) {
        b(i, bool.booleanValue());
    }

    public void a(boolean z) {
        b(this.d, z);
    }

    private void b(int i, boolean z) {
        c();
        if (i < 0 || i > this.d) {
            throw new IndexOutOfBoundsException(g(i));
        }
        if (this.d < this.c.length) {
            System.arraycopy(this.c, i, this.c, i + 1, this.d - i);
        } else {
            boolean[] zArr = new boolean[(((this.d * 3) / 2) + 1)];
            System.arraycopy(this.c, 0, zArr, 0, i);
            System.arraycopy(this.c, i, zArr, i + 1, this.d - i);
            this.c = zArr;
        }
        this.c[i] = z;
        this.d++;
        this.modCount++;
    }

    public boolean addAll(Collection<? extends Boolean> collection) {
        c();
        if (collection == null) {
            throw new NullPointerException();
        } else if (!(collection instanceof BooleanArrayList)) {
            return super.addAll(collection);
        } else {
            BooleanArrayList booleanArrayList = (BooleanArrayList) collection;
            if (booleanArrayList.d == 0) {
                return false;
            }
            if (Integer.MAX_VALUE - this.d >= booleanArrayList.d) {
                int i = this.d + booleanArrayList.d;
                if (i > this.c.length) {
                    this.c = Arrays.copyOf(this.c, i);
                }
                System.arraycopy(booleanArrayList.c, 0, this.c, this.d, booleanArrayList.d);
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
            if (obj.equals(Boolean.valueOf(this.c[i]))) {
                System.arraycopy(this.c, i + 1, this.c, i, this.d - i);
                this.d--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    public Boolean remove(int i) {
        c();
        f(i);
        boolean z = this.c[i];
        System.arraycopy(this.c, i + 1, this.c, i, this.d - i);
        this.d--;
        this.modCount++;
        return Boolean.valueOf(z);
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
