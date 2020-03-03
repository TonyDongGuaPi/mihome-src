package com.xiaomi.mimc.protobuf;

import com.xiaomi.mimc.protobuf.Internal;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class FloatArrayList extends AbstractProtobufList<Float> implements Internal.FloatList, RandomAccess {
    private static final FloatArrayList b = new FloatArrayList();
    private float[] c;
    private int d;

    static {
        b.b();
    }

    public static FloatArrayList d() {
        return b;
    }

    FloatArrayList() {
        this(new float[10], 0);
    }

    private FloatArrayList(float[] fArr, int i) {
        this.c = fArr;
        this.d = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FloatArrayList)) {
            return super.equals(obj);
        }
        FloatArrayList floatArrayList = (FloatArrayList) obj;
        if (this.d != floatArrayList.d) {
            return false;
        }
        float[] fArr = floatArrayList.c;
        for (int i = 0; i < this.d; i++) {
            if (this.c[i] != fArr[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.d; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.c[i2]);
        }
        return i;
    }

    /* renamed from: a */
    public Internal.FloatList e(int i) {
        if (i >= this.d) {
            return new FloatArrayList(Arrays.copyOf(this.c, i), this.d);
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: b */
    public Float get(int i) {
        return Float.valueOf(c(i));
    }

    public float c(int i) {
        f(i);
        return this.c[i];
    }

    public int size() {
        return this.d;
    }

    /* renamed from: a */
    public Float set(int i, Float f) {
        return Float.valueOf(a(i, f.floatValue()));
    }

    public float a(int i, float f) {
        c();
        f(i);
        float f2 = this.c[i];
        this.c[i] = f;
        return f2;
    }

    /* renamed from: b */
    public void add(int i, Float f) {
        b(i, f.floatValue());
    }

    public void a(float f) {
        b(this.d, f);
    }

    private void b(int i, float f) {
        c();
        if (i < 0 || i > this.d) {
            throw new IndexOutOfBoundsException(g(i));
        }
        if (this.d < this.c.length) {
            System.arraycopy(this.c, i, this.c, i + 1, this.d - i);
        } else {
            float[] fArr = new float[(((this.d * 3) / 2) + 1)];
            System.arraycopy(this.c, 0, fArr, 0, i);
            System.arraycopy(this.c, i, fArr, i + 1, this.d - i);
            this.c = fArr;
        }
        this.c[i] = f;
        this.d++;
        this.modCount++;
    }

    public boolean addAll(Collection<? extends Float> collection) {
        c();
        if (collection == null) {
            throw new NullPointerException();
        } else if (!(collection instanceof FloatArrayList)) {
            return super.addAll(collection);
        } else {
            FloatArrayList floatArrayList = (FloatArrayList) collection;
            if (floatArrayList.d == 0) {
                return false;
            }
            if (Integer.MAX_VALUE - this.d >= floatArrayList.d) {
                int i = this.d + floatArrayList.d;
                if (i > this.c.length) {
                    this.c = Arrays.copyOf(this.c, i);
                }
                System.arraycopy(floatArrayList.c, 0, this.c, this.d, floatArrayList.d);
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
            if (obj.equals(Float.valueOf(this.c[i]))) {
                System.arraycopy(this.c, i + 1, this.c, i, this.d - i);
                this.d--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    public Float remove(int i) {
        c();
        f(i);
        float f = this.c[i];
        System.arraycopy(this.c, i + 1, this.c, i, this.d - i);
        this.d--;
        this.modCount++;
        return Float.valueOf(f);
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
