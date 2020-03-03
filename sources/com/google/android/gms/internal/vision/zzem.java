package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzem extends zzef<Boolean> implements zzge<Boolean>, zzhr, RandomAccess {
    private static final zzem zzrv;
    private int size;
    private boolean[] zzrw;

    zzem() {
        this(new boolean[10], 0);
    }

    private zzem(boolean[] zArr, int i) {
        this.zzrw = zArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzcj();
        if (i2 >= i) {
            System.arraycopy(this.zzrw, i2, this.zzrw, i, this.size - i2);
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
        if (!(obj instanceof zzem)) {
            return super.equals(obj);
        }
        zzem zzem = (zzem) obj;
        if (this.size != zzem.size) {
            return false;
        }
        boolean[] zArr = zzem.zzrw;
        for (int i = 0; i < this.size; i++) {
            if (this.zzrw[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzga.zzj(this.zzrw[i2]);
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final void addBoolean(boolean z) {
        zza(this.size, z);
    }

    private final void zza(int i, boolean z) {
        zzcj();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzag(i));
        }
        if (this.size < this.zzrw.length) {
            System.arraycopy(this.zzrw, i, this.zzrw, i + 1, this.size - i);
        } else {
            boolean[] zArr = new boolean[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzrw, 0, zArr, 0, i);
            System.arraycopy(this.zzrw, i, zArr, i + 1, this.size - i);
            this.zzrw = zArr;
        }
        this.zzrw[i] = z;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzcj();
        zzga.checkNotNull(collection);
        if (!(collection instanceof zzem)) {
            return super.addAll(collection);
        }
        zzem zzem = (zzem) collection;
        if (zzem.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size >= zzem.size) {
            int i = this.size + zzem.size;
            if (i > this.zzrw.length) {
                this.zzrw = Arrays.copyOf(this.zzrw, i);
            }
            System.arraycopy(zzem.zzrw, 0, this.zzrw, this.size, zzem.size);
            this.size = i;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzcj();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zzrw[i]))) {
                System.arraycopy(this.zzrw, i + 1, this.zzrw, i, this.size - i);
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
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzcj();
        zzaf(i);
        boolean z = this.zzrw[i];
        this.zzrw[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ Object remove(int i) {
        zzcj();
        zzaf(i);
        boolean z = this.zzrw[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzrw, i + 1, this.zzrw, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zza(i, ((Boolean) obj).booleanValue());
    }

    public final /* synthetic */ zzge zzah(int i) {
        if (i >= this.size) {
            return new zzem(Arrays.copyOf(this.zzrw, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzaf(i);
        return Boolean.valueOf(this.zzrw[i]);
    }

    static {
        zzem zzem = new zzem();
        zzrv = zzem;
        zzem.zzci();
    }
}
