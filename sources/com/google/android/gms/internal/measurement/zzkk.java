package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkk extends zzaby<zzkk> {
    public String zzadm = null;
    public Long zzasp = null;
    private Integer zzasq = null;
    public zzkl[] zzasr = zzkl.zzlj();
    public zzkj[] zzass = zzkj.zzli();
    public zzkd[] zzast = zzkd.zzle();

    public zzkk() {
        this.zzbww = null;
        this.zzbxh = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkk)) {
            return false;
        }
        zzkk zzkk = (zzkk) obj;
        if (this.zzasp == null) {
            if (zzkk.zzasp != null) {
                return false;
            }
        } else if (!this.zzasp.equals(zzkk.zzasp)) {
            return false;
        }
        if (this.zzadm == null) {
            if (zzkk.zzadm != null) {
                return false;
            }
        } else if (!this.zzadm.equals(zzkk.zzadm)) {
            return false;
        }
        if (this.zzasq == null) {
            if (zzkk.zzasq != null) {
                return false;
            }
        } else if (!this.zzasq.equals(zzkk.zzasq)) {
            return false;
        }
        if (zzacc.equals((Object[]) this.zzasr, (Object[]) zzkk.zzasr) && zzacc.equals((Object[]) this.zzass, (Object[]) zzkk.zzass) && zzacc.equals((Object[]) this.zzast, (Object[]) zzkk.zzast)) {
            return (this.zzbww == null || this.zzbww.isEmpty()) ? zzkk.zzbww == null || zzkk.zzbww.isEmpty() : this.zzbww.equals(zzkk.zzbww);
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.zzasp == null ? 0 : this.zzasp.hashCode())) * 31) + (this.zzadm == null ? 0 : this.zzadm.hashCode())) * 31) + (this.zzasq == null ? 0 : this.zzasq.hashCode())) * 31) + zzacc.hashCode((Object[]) this.zzasr)) * 31) + zzacc.hashCode((Object[]) this.zzass)) * 31) + zzacc.hashCode((Object[]) this.zzast)) * 31;
        if (this.zzbww != null && !this.zzbww.isEmpty()) {
            i = this.zzbww.hashCode();
        }
        return hashCode + i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza = super.zza();
        if (this.zzasp != null) {
            zza += zzabw.zzc(1, this.zzasp.longValue());
        }
        if (this.zzadm != null) {
            zza += zzabw.zzc(2, this.zzadm);
        }
        if (this.zzasq != null) {
            zza += zzabw.zzf(3, this.zzasq.intValue());
        }
        if (this.zzasr != null && this.zzasr.length > 0) {
            int i = zza;
            for (zzkl zzkl : this.zzasr) {
                if (zzkl != null) {
                    i += zzabw.zzb(4, (zzace) zzkl);
                }
            }
            zza = i;
        }
        if (this.zzass != null && this.zzass.length > 0) {
            int i2 = zza;
            for (zzkj zzkj : this.zzass) {
                if (zzkj != null) {
                    i2 += zzabw.zzb(5, (zzace) zzkj);
                }
            }
            zza = i2;
        }
        if (this.zzast != null && this.zzast.length > 0) {
            for (zzkd zzkd : this.zzast) {
                if (zzkd != null) {
                    zza += zzabw.zzb(6, (zzace) zzkd);
                }
            }
        }
        return zza;
    }

    public final void zza(zzabw zzabw) throws IOException {
        if (this.zzasp != null) {
            zzabw.zzb(1, this.zzasp.longValue());
        }
        if (this.zzadm != null) {
            zzabw.zzb(2, this.zzadm);
        }
        if (this.zzasq != null) {
            zzabw.zze(3, this.zzasq.intValue());
        }
        if (this.zzasr != null && this.zzasr.length > 0) {
            for (zzkl zzkl : this.zzasr) {
                if (zzkl != null) {
                    zzabw.zza(4, (zzace) zzkl);
                }
            }
        }
        if (this.zzass != null && this.zzass.length > 0) {
            for (zzkj zzkj : this.zzass) {
                if (zzkj != null) {
                    zzabw.zza(5, (zzace) zzkj);
                }
            }
        }
        if (this.zzast != null && this.zzast.length > 0) {
            for (zzkd zzkd : this.zzast) {
                if (zzkd != null) {
                    zzabw.zza(6, (zzace) zzkd);
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
                this.zzasp = Long.valueOf(zzabv.zzuz());
            } else if (zzuw == 18) {
                this.zzadm = zzabv.readString();
            } else if (zzuw == 24) {
                this.zzasq = Integer.valueOf(zzabv.zzuy());
            } else if (zzuw == 34) {
                int zzb = zzach.zzb(zzabv, 34);
                int length = this.zzasr == null ? 0 : this.zzasr.length;
                zzkl[] zzklArr = new zzkl[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzasr, 0, zzklArr, 0, length);
                }
                while (length < zzklArr.length - 1) {
                    zzklArr[length] = new zzkl();
                    zzabv.zza(zzklArr[length]);
                    zzabv.zzuw();
                    length++;
                }
                zzklArr[length] = new zzkl();
                zzabv.zza(zzklArr[length]);
                this.zzasr = zzklArr;
            } else if (zzuw == 42) {
                int zzb2 = zzach.zzb(zzabv, 42);
                int length2 = this.zzass == null ? 0 : this.zzass.length;
                zzkj[] zzkjArr = new zzkj[(zzb2 + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zzass, 0, zzkjArr, 0, length2);
                }
                while (length2 < zzkjArr.length - 1) {
                    zzkjArr[length2] = new zzkj();
                    zzabv.zza(zzkjArr[length2]);
                    zzabv.zzuw();
                    length2++;
                }
                zzkjArr[length2] = new zzkj();
                zzabv.zza(zzkjArr[length2]);
                this.zzass = zzkjArr;
            } else if (zzuw == 50) {
                int zzb3 = zzach.zzb(zzabv, 50);
                int length3 = this.zzast == null ? 0 : this.zzast.length;
                zzkd[] zzkdArr = new zzkd[(zzb3 + length3)];
                if (length3 != 0) {
                    System.arraycopy(this.zzast, 0, zzkdArr, 0, length3);
                }
                while (length3 < zzkdArr.length - 1) {
                    zzkdArr[length3] = new zzkd();
                    zzabv.zza(zzkdArr[length3]);
                    zzabv.zzuw();
                    length3++;
                }
                zzkdArr[length3] = new zzkd();
                zzabv.zza(zzkdArr[length3]);
                this.zzast = zzkdArr;
            } else if (!super.zza(zzabv, zzuw)) {
                return this;
            }
        }
    }
}
