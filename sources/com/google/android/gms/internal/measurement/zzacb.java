package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzacb implements Cloneable {
    private Object value;
    private zzabz<?, ?> zzbxe;
    private List<zzacg> zzbxf = new ArrayList();

    zzacb() {
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zza()];
        zza(zzabw.zzj(bArr));
        return bArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzvg */
    public final zzacb clone() {
        Object clone;
        zzacb zzacb = new zzacb();
        try {
            zzacb.zzbxe = this.zzbxe;
            if (this.zzbxf == null) {
                zzacb.zzbxf = null;
            } else {
                zzacb.zzbxf.addAll(this.zzbxf);
            }
            if (this.value != null) {
                if (this.value instanceof zzace) {
                    clone = (zzace) ((zzace) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    clone = ((byte[]) this.value).clone();
                } else {
                    int i = 0;
                    if (this.value instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.value;
                        byte[][] bArr2 = new byte[bArr.length][];
                        zzacb.value = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (this.value instanceof boolean[]) {
                        clone = ((boolean[]) this.value).clone();
                    } else if (this.value instanceof int[]) {
                        clone = ((int[]) this.value).clone();
                    } else if (this.value instanceof long[]) {
                        clone = ((long[]) this.value).clone();
                    } else if (this.value instanceof float[]) {
                        clone = ((float[]) this.value).clone();
                    } else if (this.value instanceof double[]) {
                        clone = ((double[]) this.value).clone();
                    } else if (this.value instanceof zzace[]) {
                        zzace[] zzaceArr = (zzace[]) this.value;
                        zzace[] zzaceArr2 = new zzace[zzaceArr.length];
                        zzacb.value = zzaceArr2;
                        while (i < zzaceArr.length) {
                            zzaceArr2[i] = (zzace) zzaceArr[i].clone();
                            i++;
                        }
                    }
                }
                zzacb.value = clone;
            }
            return zzacb;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzacb)) {
            return false;
        }
        zzacb zzacb = (zzacb) obj;
        if (this.value == null || zzacb.value == null) {
            if (this.zzbxf != null && zzacb.zzbxf != null) {
                return this.zzbxf.equals(zzacb.zzbxf);
            }
            try {
                return Arrays.equals(toByteArray(), zzacb.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else if (this.zzbxe != zzacb.zzbxe) {
            return false;
        } else {
            return !this.zzbxe.zzbwx.isArray() ? this.value.equals(zzacb.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) zzacb.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) zzacb.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) zzacb.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) zzacb.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) zzacb.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) zzacb.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) zzacb.value);
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final int zza() {
        if (this.value != null) {
            zzabz<?, ?> zzabz = this.zzbxe;
            Object obj = this.value;
            if (!zzabz.zzbwy) {
                return zzabz.zzv(obj);
            }
            int length = Array.getLength(obj);
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (Array.get(obj, i2) != null) {
                    i += zzabz.zzv(Array.get(obj, i2));
                }
            }
            return i;
        }
        int i3 = 0;
        for (zzacg next : this.zzbxf) {
            i3 += zzabw.zzas(next.tag) + 0 + next.zzbrc.length;
        }
        return i3;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzabw zzabw) throws IOException {
        if (this.value != null) {
            zzabz<?, ?> zzabz = this.zzbxe;
            Object obj = this.value;
            if (zzabz.zzbwy) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    Object obj2 = Array.get(obj, i);
                    if (obj2 != null) {
                        zzabz.zza(obj2, zzabw);
                    }
                }
                return;
            }
            zzabz.zza(obj, zzabw);
            return;
        }
        for (zzacg next : this.zzbxf) {
            zzabw.zzar(next.tag);
            zzabw.zzk(next.zzbrc);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzacg zzacg) throws IOException {
        Object obj;
        if (this.zzbxf != null) {
            this.zzbxf.add(zzacg);
            return;
        }
        if (this.value instanceof zzace) {
            byte[] bArr = zzacg.zzbrc;
            zzabv zza = zzabv.zza(bArr, 0, bArr.length);
            int zzuy = zza.zzuy();
            if (zzuy == bArr.length - zzabw.zzao(zzuy)) {
                obj = ((zzace) this.value).zzb(zza);
            } else {
                throw zzacd.zzvh();
            }
        } else if (this.value instanceof zzace[]) {
            zzace[] zzaceArr = (zzace[]) this.zzbxe.zzi(Collections.singletonList(zzacg));
            zzace[] zzaceArr2 = (zzace[]) this.value;
            zzace[] zzaceArr3 = (zzace[]) Arrays.copyOf(zzaceArr2, zzaceArr2.length + zzaceArr.length);
            System.arraycopy(zzaceArr, 0, zzaceArr3, zzaceArr2.length, zzaceArr.length);
            obj = zzaceArr3;
        } else {
            obj = this.zzbxe.zzi(Collections.singletonList(zzacg));
        }
        this.zzbxe = this.zzbxe;
        this.value = obj;
        this.zzbxf = null;
    }

    /* access modifiers changed from: package-private */
    public final <T> T zzb(zzabz<?, T> zzabz) {
        if (this.value == null) {
            this.zzbxe = zzabz;
            this.value = zzabz.zzi(this.zzbxf);
            this.zzbxf = null;
        } else if (!this.zzbxe.equals(zzabz)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return this.value;
    }
}
