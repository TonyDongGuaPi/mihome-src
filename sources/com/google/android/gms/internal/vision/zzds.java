package com.google.android.gms.internal.vision;

import java.io.IOException;

public final class zzds extends zzjn<zzds> {
    private static volatile zzds[] zzpu;
    public Integer zzpv = null;
    public Integer zzpw = null;

    public static zzds[] zzcc() {
        if (zzpu == null) {
            synchronized (zzjr.zzado) {
                if (zzpu == null) {
                    zzpu = new zzds[0];
                }
            }
        }
        return zzpu;
    }

    public zzds() {
        this.zzadp = -1;
    }

    public final void zza(zzjl zzjl) throws IOException {
        if (this.zzpv != null) {
            zzjl.zze(1, this.zzpv.intValue());
        }
        if (this.zzpw != null) {
            zzjl.zze(2, this.zzpw.intValue());
        }
        super.zza(zzjl);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzpv != null) {
            zzt += zzjl.zzi(1, this.zzpv.intValue());
        }
        return this.zzpw != null ? zzt + zzjl.zzi(2, this.zzpw.intValue()) : zzt;
    }

    public final /* synthetic */ zzjt zza(zzjk zzjk) throws IOException {
        while (true) {
            int zzdq = zzjk.zzdq();
            if (zzdq == 0) {
                return this;
            }
            if (zzdq == 8) {
                this.zzpv = Integer.valueOf(zzjk.zzdt());
            } else if (zzdq == 16) {
                this.zzpw = Integer.valueOf(zzjk.zzdt());
            } else if (!super.zza(zzjk, zzdq)) {
                return this;
            }
        }
    }
}
