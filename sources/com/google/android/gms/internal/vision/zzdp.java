package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcz;
import java.io.IOException;

public final class zzdp extends zzjn<zzdp> {
    public zzdq zzpi = null;
    private zzcz.zzg zzpj;
    public zzdm[] zzpk = zzdm.zzcb();

    public zzdp() {
        this.zzadp = -1;
    }

    public final void zza(zzjl zzjl) throws IOException {
        if (this.zzpi != null) {
            zzjl.zza(1, (zzjt) this.zzpi);
        }
        if (this.zzpj != null) {
            zzjl.zze(2, (zzhf) this.zzpj);
        }
        if (this.zzpk != null && this.zzpk.length > 0) {
            for (zzdm zzdm : this.zzpk) {
                if (zzdm != null) {
                    zzjl.zza(3, (zzjt) zzdm);
                }
            }
        }
        super.zza(zzjl);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzpi != null) {
            zzt += zzjl.zzb(1, (zzjt) this.zzpi);
        }
        if (this.zzpj != null) {
            zzt += zzfe.zzc(2, (zzhf) this.zzpj);
        }
        if (this.zzpk != null && this.zzpk.length > 0) {
            for (zzdm zzdm : this.zzpk) {
                if (zzdm != null) {
                    zzt += zzjl.zzb(3, (zzjt) zzdm);
                }
            }
        }
        return zzt;
    }

    public final /* synthetic */ zzjt zza(zzjk zzjk) throws IOException {
        while (true) {
            int zzdq = zzjk.zzdq();
            if (zzdq == 0) {
                return this;
            }
            if (zzdq == 10) {
                if (this.zzpi == null) {
                    this.zzpi = new zzdq();
                }
                zzjk.zza((zzjt) this.zzpi);
            } else if (zzdq == 18) {
                this.zzpj = (zzcz.zzg) zzjk.zza(zzcz.zzg.zzbx());
            } else if (zzdq == 26) {
                int zzb = zzjw.zzb(zzjk, 26);
                int length = this.zzpk == null ? 0 : this.zzpk.length;
                zzdm[] zzdmArr = new zzdm[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzpk, 0, zzdmArr, 0, length);
                }
                while (length < zzdmArr.length - 1) {
                    zzdmArr[length] = new zzdm();
                    zzjk.zza((zzjt) zzdmArr[length]);
                    zzjk.zzdq();
                    length++;
                }
                zzdmArr[length] = new zzdm();
                zzjk.zza((zzjt) zzdmArr[length]);
                this.zzpk = zzdmArr;
            } else if (!super.zza(zzjk, zzdq)) {
                return this;
            }
        }
    }
}
