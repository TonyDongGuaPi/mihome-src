package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkr extends zzaby<zzkr> {
    public long[] zzauk = zzach.zzbxm;
    public long[] zzaul = zzach.zzbxm;

    public zzkr() {
        this.zzbww = null;
        this.zzbxh = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkr)) {
            return false;
        }
        zzkr zzkr = (zzkr) obj;
        if (zzacc.equals(this.zzauk, zzkr.zzauk) && zzacc.equals(this.zzaul, zzkr.zzaul)) {
            return (this.zzbww == null || this.zzbww.isEmpty()) ? zzkr.zzbww == null || zzkr.zzbww.isEmpty() : this.zzbww.equals(zzkr.zzbww);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((getClass().getName().hashCode() + 527) * 31) + zzacc.hashCode(this.zzauk)) * 31) + zzacc.hashCode(this.zzaul)) * 31) + ((this.zzbww == null || this.zzbww.isEmpty()) ? 0 : this.zzbww.hashCode());
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza = super.zza();
        if (this.zzauk != null && this.zzauk.length > 0) {
            int i = 0;
            for (long zzao : this.zzauk) {
                i += zzabw.zzao(zzao);
            }
            zza = zza + i + (this.zzauk.length * 1);
        }
        if (this.zzaul == null || this.zzaul.length <= 0) {
            return zza;
        }
        int i2 = 0;
        for (long zzao2 : this.zzaul) {
            i2 += zzabw.zzao(zzao2);
        }
        return zza + i2 + (this.zzaul.length * 1);
    }

    public final void zza(zzabw zzabw) throws IOException {
        if (this.zzauk != null && this.zzauk.length > 0) {
            for (long zza : this.zzauk) {
                zzabw.zza(1, zza);
            }
        }
        if (this.zzaul != null && this.zzaul.length > 0) {
            for (long zza2 : this.zzaul) {
                zzabw.zza(2, zza2);
            }
        }
        super.zza(zzabw);
    }

    public final /* synthetic */ zzace zzb(zzabv zzabv) throws IOException {
        int i;
        while (true) {
            int zzuw = zzabv.zzuw();
            if (zzuw == 0) {
                return this;
            }
            if (zzuw != 8) {
                if (zzuw == 10) {
                    i = zzabv.zzaf(zzabv.zzuy());
                    int position = zzabv.getPosition();
                    int i2 = 0;
                    while (zzabv.zzvc() > 0) {
                        zzabv.zzuz();
                        i2++;
                    }
                    zzabv.zzam(position);
                    int length = this.zzauk == null ? 0 : this.zzauk.length;
                    long[] jArr = new long[(i2 + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzauk, 0, jArr, 0, length);
                    }
                    while (length < jArr.length) {
                        jArr[length] = zzabv.zzuz();
                        length++;
                    }
                    this.zzauk = jArr;
                } else if (zzuw == 16) {
                    int zzb = zzach.zzb(zzabv, 16);
                    int length2 = this.zzaul == null ? 0 : this.zzaul.length;
                    long[] jArr2 = new long[(zzb + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzaul, 0, jArr2, 0, length2);
                    }
                    while (length2 < jArr2.length - 1) {
                        jArr2[length2] = zzabv.zzuz();
                        zzabv.zzuw();
                        length2++;
                    }
                    jArr2[length2] = zzabv.zzuz();
                    this.zzaul = jArr2;
                } else if (zzuw == 18) {
                    i = zzabv.zzaf(zzabv.zzuy());
                    int position2 = zzabv.getPosition();
                    int i3 = 0;
                    while (zzabv.zzvc() > 0) {
                        zzabv.zzuz();
                        i3++;
                    }
                    zzabv.zzam(position2);
                    int length3 = this.zzaul == null ? 0 : this.zzaul.length;
                    long[] jArr3 = new long[(i3 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzaul, 0, jArr3, 0, length3);
                    }
                    while (length3 < jArr3.length) {
                        jArr3[length3] = zzabv.zzuz();
                        length3++;
                    }
                    this.zzaul = jArr3;
                } else if (!super.zza(zzabv, zzuw)) {
                    return this;
                }
                zzabv.zzal(i);
            } else {
                int zzb2 = zzach.zzb(zzabv, 8);
                int length4 = this.zzauk == null ? 0 : this.zzauk.length;
                long[] jArr4 = new long[(zzb2 + length4)];
                if (length4 != 0) {
                    System.arraycopy(this.zzauk, 0, jArr4, 0, length4);
                }
                while (length4 < jArr4.length - 1) {
                    jArr4[length4] = zzabv.zzuz();
                    zzabv.zzuw();
                    length4++;
                }
                jArr4[length4] = zzabv.zzuz();
                this.zzauk = jArr4;
            }
        }
    }
}
