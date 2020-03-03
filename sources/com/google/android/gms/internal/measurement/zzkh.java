package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkh extends zzaby<zzkh> {
    private static volatile zzkh[] zzase;
    public Integer zzarp = null;
    public String zzasf = null;
    public zzkf zzasg = null;

    public zzkh() {
        this.zzbww = null;
        this.zzbxh = -1;
    }

    public static zzkh[] zzlh() {
        if (zzase == null) {
            synchronized (zzacc.zzbxg) {
                if (zzase == null) {
                    zzase = new zzkh[0];
                }
            }
        }
        return zzase;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkh)) {
            return false;
        }
        zzkh zzkh = (zzkh) obj;
        if (this.zzarp == null) {
            if (zzkh.zzarp != null) {
                return false;
            }
        } else if (!this.zzarp.equals(zzkh.zzarp)) {
            return false;
        }
        if (this.zzasf == null) {
            if (zzkh.zzasf != null) {
                return false;
            }
        } else if (!this.zzasf.equals(zzkh.zzasf)) {
            return false;
        }
        if (this.zzasg == null) {
            if (zzkh.zzasg != null) {
                return false;
            }
        } else if (!this.zzasg.equals(zzkh.zzasg)) {
            return false;
        }
        return (this.zzbww == null || this.zzbww.isEmpty()) ? zzkh.zzbww == null || zzkh.zzbww.isEmpty() : this.zzbww.equals(zzkh.zzbww);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((getClass().getName().hashCode() + 527) * 31) + (this.zzarp == null ? 0 : this.zzarp.hashCode())) * 31) + (this.zzasf == null ? 0 : this.zzasf.hashCode());
        zzkf zzkf = this.zzasg;
        int hashCode2 = ((hashCode * 31) + (zzkf == null ? 0 : zzkf.hashCode())) * 31;
        if (this.zzbww != null && !this.zzbww.isEmpty()) {
            i = this.zzbww.hashCode();
        }
        return hashCode2 + i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza = super.zza();
        if (this.zzarp != null) {
            zza += zzabw.zzf(1, this.zzarp.intValue());
        }
        if (this.zzasf != null) {
            zza += zzabw.zzc(2, this.zzasf);
        }
        return this.zzasg != null ? zza + zzabw.zzb(3, (zzace) this.zzasg) : zza;
    }

    public final void zza(zzabw zzabw) throws IOException {
        if (this.zzarp != null) {
            zzabw.zze(1, this.zzarp.intValue());
        }
        if (this.zzasf != null) {
            zzabw.zzb(2, this.zzasf);
        }
        if (this.zzasg != null) {
            zzabw.zza(3, (zzace) this.zzasg);
        }
        super.zza(zzabw);
    }

    public final /* synthetic */ zzace zzb(zzabv zzabv) throws IOException {
        while (true) {
            int zzuw = zzabv.zzuw();
            if (zzuw == 0) {
                return this;
            }
            if (zzuw == 8) {
                this.zzarp = Integer.valueOf(zzabv.zzuy());
            } else if (zzuw == 18) {
                this.zzasf = zzabv.readString();
            } else if (zzuw == 26) {
                if (this.zzasg == null) {
                    this.zzasg = new zzkf();
                }
                zzabv.zza(this.zzasg);
            } else if (!super.zza(zzabv, zzuw)) {
                return this;
            }
        }
    }
}
