package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzfv extends zzef<Float> implements zzge<Float>, zzhr, RandomAccess {
    private static final zzfv zzwf;
    private int size;
    private float[] zzwg;

    zzfv() {
        this(new float[10], 0);
    }

    private zzfv(float[] fArr, int i) {
        this.zzwg = fArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzcj();
        if (i2 >= i) {
            System.arraycopy(this.zzwg, i2, this.zzwg, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfv)) {
            return super.equals(obj);
        }
        zzfv zzfv = (zzfv) obj;
        if (this.size != zzfv.size) {
            return false;
        }
        float[] fArr = zzfv.zzwg;
        for (int i = 0; i < this.size; i++) {
            if (Float.floatToIntBits(this.zzwg[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.zzwg[i2]);
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final void zzh(float f) {
        zzc(this.size, f);
    }

    private final void zzc(int i, float f) {
        zzcj();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzag(i));
        }
        if (this.size < this.zzwg.length) {
            System.arraycopy(this.zzwg, i, this.zzwg, i + 1, this.size - i);
        } else {
            float[] fArr = new float[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzwg, 0, fArr, 0, i);
            System.arraycopy(this.zzwg, i, fArr, i + 1, this.size - i);
            this.zzwg = fArr;
        }
        this.zzwg[i] = f;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Float> collection) {
        zzcj();
        zzga.checkNotNull(collection);
        if (!(collection instanceof zzfv)) {
            return super.addAll(collection);
        }
        zzfv zzfv = (zzfv) collection;
        if (zzfv.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size >= zzfv.size) {
            int i = this.size + zzfv.size;
            if (i > this.zzwg.length) {
                this.zzwg = Arrays.copyOf(this.zzwg, i);
            }
            System.arraycopy(zzfv.zzwg, 0, this.zzwg, this.size, zzfv.size);
            this.size = i;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzcj();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Float.valueOf(this.zzwg[i]))) {
                System.arraycopy(this.zzwg, i + 1, this.zzwg, i, this.size - i);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzaf(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzag(i));
        }
    }

    private final String zzag(int i) {
        int i2 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zzcj();
        zzaf(i);
        float f = this.zzwg[i];
        this.zzwg[i] = floatValue;
        return Float.valueOf(f);
    }

    public final /* synthetic */ Object remove(int i) {
        zzcj();
        zzaf(i);
        float f = this.zzwg[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzwg, i + 1, this.zzwg, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Float.valueOf(f);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzc(i, ((Float) obj).floatValue());
    }

    public final /* synthetic */ zzge zzah(int i) {
        if (i >= this.size) {
            return new zzfv(Arrays.copyOf(this.zzwg, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzaf(i);
        return Float.valueOf(this.zzwg[i]);
    }

    static {
        zzfv zzfv = new zzfv();
        zzwf = zzfv;
        zzfv.zzci();
    }
}
