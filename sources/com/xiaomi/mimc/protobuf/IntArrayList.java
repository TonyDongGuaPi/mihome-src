package com.xiaomi.mimc.protobuf;

import com.xiaomi.mimc.protobuf.Internal;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class IntArrayList extends AbstractProtobufList<Integer> implements Internal.IntList, RandomAccess {
    private static final IntArrayList b = new IntArrayList();
    private int[] c;
    private int d;

    static {
        b.b();
    }

    public static IntArrayList d() {
        return b;
    }

    IntArrayList() {
        this(new int[10], 0);
    }

    private IntArrayList(int[] iArr, int i) {
        this.c = iArr;
        this.d = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IntArrayList)) {
            return super.equals(obj);
        }
        IntArrayList intArrayList = (IntArrayList) obj;
        if (this.d != intArrayList.d) {
            return false;
        }
        int[] iArr = intArrayList.c;
        for (int i = 0; i < this.d; i++) {
            if (this.c[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.d; i2++) {
            i = (i * 31) + this.c[i2];
        }
        return i;
    }

    /* renamed from: a */
    public Internal.IntList e(int i) {
        if (i >= this.d) {
            return new IntArrayList(Arrays.copyOf(this.c, i), this.d);
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: b */
    public Integer get(int i) {
        return Integer.valueOf(c(i));
    }

    public int c(int i) {
        g(i);
        return this.c[i];
    }

    public int size() {
        return this.d;
    }

    /* renamed from: a */
    public Integer set(int i, Integer num) {
        return Integer.valueOf(a(i, num.intValue()));
    }

    public int a(int i, int i2) {
        c();
        g(i);
        int i3 = this.c[i];
        this.c[i] = i2;
        return i3;
    }

    /* renamed from: b */
    public void add(int i, Integer num) {
        b(i, num.intValue());
    }

    public void d(int i) {
        b(this.d, i);
    }

    private void b(int i, int i2) {
        c();
        if (i < 0 || i > this.d) {
            throw new IndexOutOfBoundsException(h(i));
        }
        if (this.d < this.c.length) {
            System.arraycopy(this.c, i, this.c, i + 1, this.d - i);
        } else {
            int[] iArr = new int[(((this.d * 3) / 2) + 1)];
            System.arraycopy(this.c, 0, iArr, 0, i);
            System.arraycopy(this.c, i, iArr, i + 1, this.d - i);
            this.c = iArr;
        }
        this.c[i] = i2;
        this.d++;
        this.modCount++;
    }

    public boolean addAll(Collection<? extends Integer> collection) {
        c();
        if (collection == null) {
            throw new NullPointerException();
        } else if (!(collection instanceof IntArrayList)) {
            return super.addAll(collection);
        } else {
            IntArrayList intArrayList = (IntArrayList) collection;
            if (intArrayList.d == 0) {
                return false;
            }
            if (Integer.MAX_VALUE - this.d >= intArrayList.d) {
                int i = this.d + intArrayList.d;
                if (i > this.c.length) {
                    this.c = Arrays.copyOf(this.c, i);
                }
                System.arraycopy(intArrayList.c, 0, this.c, this.d, intArrayList.d);
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
            if (obj.equals(Integer.valueOf(this.c[i]))) {
                System.arraycopy(this.c, i + 1, this.c, i, this.d - i);
                this.d--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    /* renamed from: f */
    public Integer remove(int i) {
        c();
        g(i);
        int i2 = this.c[i];
        System.arraycopy(this.c, i + 1, this.c, i, this.d - i);
        this.d--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    private void g(int i) {
        if (i < 0 || i >= this.d) {
            throw new IndexOutOfBoundsException(h(i));
        }
    }

    private String h(int i) {
        return "Index:" + i + ", Size:" + this.d;
    }
}
