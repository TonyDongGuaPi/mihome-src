package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkd extends zzaby<zzkd> {
    private static volatile zzkd[] zzark;
    public Integer zzarl = null;
    public zzkh[] zzarm = zzkh.zzlh();
    public zzke[] zzarn = zzke.zzlf();

    public zzkd() {
        this.zzbww = null;
        this.zzbxh = -1;
    }

    public static zzkd[] zzle() {
        if (zzark == null) {
            synchronized (zzacc.zzbxg) {
                if (zzark == null) {
                    zzark = new zzkd[0];
                }
            }
        }
        return zzark;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkd)) {
            return false;
        }
        zzkd zzkd = (zzkd) obj;
        if (this.zzarl == null) {
            if (zzkd.zzarl != null) {
                return false;
            }
        } else if (!this.zzarl.equals(zzkd.zzarl)) {
            return false;
        }
        if (zzacc.equals((Object[]) this.zzarm, (Object[]) zzkd.zzarm) && zzacc.equals((Object[]) this.zzarn, (Object[]) zzkd.zzarn)) {
            return (this.zzbww == null || this.zzbww.isEmpty()) ? zzkd.zzbww == null || zzkd.zzbww.isEmpty() : this.zzbww.equals(zzkd.zzbww);
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.zzarl == null ? 0 : this.zzarl.hashCode())) * 31) + zzacc.hashCode((Object[]) this.zzarm)) * 31) + zzacc.hashCode((Object[]) this.zzarn)) * 31;
        if (this.zzbww != null && !this.zzbww.isEmpty()) {
            i = this.zzbww.hashCode();
        }
        return hashCode + i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza = super.zza();
        if (this.zzarl != null) {
            zza += zzabw.zzf(1, this.zzarl.intValue());
        }
        if (this.zzarm != null && this.zzarm.length > 0) {
            int i = zza;
            for (zzkh zzkh : this.zzarm) {
                if (zzkh != null) {
                    i += zzabw.zzb(2, (zzace) zzkh);
                }
            }
            zza = i;
        }
        if (this.zzarn != null && this.zzarn.length > 0) {
            for (zzke zzke : this.zzarn) {
                if (zzke != null) {
                    zza += zzabw.zzb(3, (zzace) zzke);
                }
            }
        }
        return zza;
    }

    public final void zza(zzabw zzabw) throws IOException {
        if (this.zzarl != null) {
            zzabw.zze(1, this.zzarl.intValue());
        }
        if (this.zzarm != null && this.zzarm.length > 0) {
            for (zzkh zzkh : this.zzarm) {
                if (zzkh != null) {
                    zzabw.zza(2, (zzace) zzkh);
                }
            }
        }
        if (this.zzarn != null && this.zzarn.length > 0) {
            for (zzke zzke : this.zzarn) {
                if (zzke != null) {
                    zzabw.zza(3, (zzace) zzke);
                }
            }
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
                this.zzarl = Integer.valueOf(zzabv.zzuy());
            } else if (zzuw == 18) {
                int zzb = zzach.zzb(zzabv, 18);
                int length = this.zzarm == null ? 0 : this.zzarm.length;
                zzkh[] zzkhArr = new zzkh[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzarm, 0, zzkhArr, 0, length);
                }
                while (length < zzkhArr.length - 1) {
                    zzkhArr[length] = new zzkh();
                    zzabv.zza(zzkhArr[length]);
                    zzabv.zzuw();
                    length++;
                }
                zzkhArr[length] = new zzkh();
                zzabv.zza(zzkhArr[length]);
                this.zzarm = zzkhArr;
            } else if (zzuw == 26) {
                int zzb2 = zzach.zzb(zzabv, 26);
                int length2 = this.zzarn == null ? 0 : this.zzarn.length;
                zzke[] zzkeArr = new zzke[(zzb2 + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zzarn, 0, zzkeArr, 0, length2);
                }
                while (length2 < zzkeArr.length - 1) {
                    zzkeArr[length2] = new zzke();
                    zzabv.zza(zzkeArr[length2]);
                    zzabv.zzuw();
                    length2++;
                }
                zzkeArr[length2] = new zzke();
                zzabv.zza(zzkeArr[length2]);
                this.zzarn = zzkeArr;
            } else if (!super.zza(zzabv, zzuw)) {
                return this;
            }
        }
    }
}
