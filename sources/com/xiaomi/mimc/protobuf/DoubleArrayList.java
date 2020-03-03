package com.xiaomi.mimc.protobuf;

import com.xiaomi.mimc.protobuf.Internal;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class DoubleArrayList extends AbstractProtobufList<Double> implements Internal.DoubleList, RandomAccess {
    private static final DoubleArrayList b = new DoubleArrayList();
    private double[] c;
    private int d;

    static {
        b.b();
    }

    public static DoubleArrayList d() {
        return b;
    }

    DoubleArrayList() {
        this(new double[10], 0);
    }

    private DoubleArrayList(double[] dArr, int i) {
        this.c = dArr;
        this.d = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DoubleArrayList)) {
            return super.equals(obj);
        }
        DoubleArrayList doubleArrayList = (DoubleArrayList) obj;
        if (this.d != doubleArrayList.d) {
            return false;
        }
        double[] dArr = doubleArrayList.c;
        for (int i = 0; i < this.d; i++) {
            if (this.c[i] != dArr[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.d; i2++) {
            i = (i * 31) + Internal.a(Double.doubleToLongBits(this.c[i2]));
        }
        return i;
    }

    /* renamed from: a */
    public Internal.DoubleList e(int i) {
        if (i >= this.d) {
            return new DoubleArrayList(Arrays.copyOf(this.c, i), this.d);
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: b */
    public Double get(int i) {
        return Double.valueOf(c(i));
    }

    public double c(int i) {
        f(i);
        return this.c[i];
    }

    public int size() {
        return this.d;
    }

    /* renamed from: a */
    public Double set(int i, Double d2) {
        return Double.valueOf(a(i, d2.doubleValue()));
    }

    public double a(int i, double d2) {
        c();
        f(i);
        double d3 = this.c[i];
        this.c[i] = d2;
        return d3;
    }

    /* renamed from: b */
    public void add(int i, Double d2) {
        b(i, d2.doubleValue());
    }

    public void a(double d2) {
        b(this.d, d2);
    }

    private void b(int i, double d2) {
        c();
        if (i < 0 || i > this.d) {
            throw new IndexOutOfBoundsException(g(i));
        }
        if (this.d < this.c.length) {
            System.arraycopy(this.c, i, this.c, i + 1, this.d - i);
        } else {
            double[] dArr = new double[(((this.d * 3) / 2) + 1)];
            System.arraycopy(this.c, 0, dArr, 0, i);
            System.arraycopy(this.c, i, dArr, i + 1, this.d - i);
            this.c = dArr;
        }
        this.c[i] = d2;
        this.d++;
        this.modCount++;
    }

    public boolean addAll(Collection<? extends Double> collection) {
        c();
        if (collection == null) {
            throw new NullPointerException();
        } else if (!(collection instanceof DoubleArrayList)) {
            return super.addAll(collection);
        } else {
            DoubleArrayList doubleArrayList = (DoubleArrayList) collection;
            if (doubleArrayList.d == 0) {
                return false;
            }
            if (Integer.MAX_VALUE - this.d >= doubleArrayList.d) {
                int i = this.d + doubleArrayList.d;
                if (i > this.c.length) {
                    this.c = Arrays.copyOf(this.c, i);
                }
                System.arraycopy(doubleArrayList.c, 0, this.c, this.d, doubleArrayList.d);
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
            if (obj.equals(Double.valueOf(this.c[i]))) {
                System.arraycopy(this.c, i + 1, this.c, i, this.d - i);
                this.d--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    public Double remove(int i) {
        c();
        f(i);
        double d2 = this.c[i];
        System.arraycopy(this.c, i + 1, this.c, i, this.d - i);
        this.d--;
        this.modCount++;
        return Double.valueOf(d2);
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
