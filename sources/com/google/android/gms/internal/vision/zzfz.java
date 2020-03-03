package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzfz extends zzef<Integer> implements zzge<Integer>, zzhr, RandomAccess {
    private static final zzfz zzxl;
    private int size;
    private int[] zzxm;

    zzfz() {
        this(new int[10], 0);
    }

    private zzfz(int[] iArr, int i) {
        this.zzxm = iArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzcj();
        if (i2 >= i) {
            System.arraycopy(this.zzxm, i2, this.zzxm, i, this.size - i2);
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
        if (!(obj instanceof zzfz)) {
            return super.equals(obj);
        }
        zzfz zzfz = (zzfz) obj;
        if (this.size != zzfz.size) {
            return false;
        }
        int[] iArr = zzfz.zzxm;
        for (int i = 0; i < this.size; i++) {
            if (this.zzxm[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.zzxm[i2];
        }
        return i;
    }

    public final int getInt(int i) {
        zzaf(i);
        return this.zzxm[i];
    }

    public final int size() {
        return this.size;
    }

    public final void zzbg(int i) {
        zzq(this.size, i);
    }

    private final void zzq(int i, int i2) {
        zzcj();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzag(i));
        }
        if (this.size < this.zzxm.length) {
            System.arraycopy(this.zzxm, i, this.zzxm, i + 1, this.size - i);
        } else {
            int[] iArr = new int[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzxm, 0, iArr, 0, i);
            System.arraycopy(this.zzxm, i, iArr, i + 1, this.size - i);
            this.zzxm = iArr;
        }
        this.zzxm[i] = i2;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        zzcj();
        zzga.checkNotNull(collection);
        if (!(collection instanceof zzfz)) {
            return super.addAll(collection);
        }
        zzfz zzfz = (zzfz) collection;
        if (zzfz.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size >= zzfz.size) {
            int i = this.size + zzfz.size;
            if (i > this.zzxm.length) {
                this.zzxm = Arrays.copyOf(this.zzxm, i);
            }
            System.arraycopy(zzfz.zzxm, 0, this.zzxm, this.size, zzfz.size);
            this.size = i;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzcj();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Integer.valueOf(this.zzxm[i]))) {
                System.arraycopy(this.zzxm, i + 1, this.zzxm, i, this.size - i);
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
        int intValue = ((Integer) obj).intValue();
        zzcj();
        zzaf(i);
        int i2 = this.zzxm[i];
        this.zzxm[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ Object remove(int i) {
        zzcj();
        zzaf(i);
        int i2 = this.zzxm[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzxm, i + 1, this.zzxm, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzq(i, ((Integer) obj).intValue());
    }

    public final /* synthetic */ zzge zzah(int i) {
        if (i >= this.size) {
            return new zzfz(Arrays.copyOf(this.zzxm, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    static {
        zzfz zzfz = new zzfz();
        zzxl = zzfz;
        zzfz.zzci();
    }
}
