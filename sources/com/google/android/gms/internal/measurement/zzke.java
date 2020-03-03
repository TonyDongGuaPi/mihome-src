package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzke extends zzaby<zzke> {
    private static volatile zzke[] zzaro;
    public Integer zzarp = null;
    public String zzarq = null;
    public zzkf[] zzarr = zzkf.zzlg();
    private Boolean zzars = null;
    public zzkg zzart = null;

    public zzke() {
        this.zzbww = null;
        this.zzbxh = -1;
    }

    public static zzke[] zzlf() {
        if (zzaro == null) {
            synchronized (zzacc.zzbxg) {
                if (zzaro == null) {
                    zzaro = new zzke[0];
                }
            }
        }
        return zzaro;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzke)) {
            return false;
        }
        zzke zzke = (zzke) obj;
        if (this.zzarp == null) {
            if (zzke.zzarp != null) {
                return false;
            }
        } else if (!this.zzarp.equals(zzke.zzarp)) {
            return false;
        }
        if (this.zzarq == null) {
            if (zzke.zzarq != null) {
                return false;
            }
        } else if (!this.zzarq.equals(zzke.zzarq)) {
            return false;
        }
        if (!zzacc.equals((Object[]) this.zzarr, (Object[]) zzke.zzarr)) {
            return false;
        }
        if (this.zzars == null) {
            if (zzke.zzars != null) {
                return false;
            }
        } else if (!this.zzars.equals(zzke.zzars)) {
            return false;
        }
        if (this.zzart == null) {
            if (zzke.zzart != null) {
                return false;
            }
        } else if (!this.zzart.equals(zzke.zzart)) {
            return false;
        }
        return (this.zzbww == null || this.zzbww.isEmpty()) ? zzke.zzbww == null || zzke.zzbww.isEmpty() : this.zzbww.equals(zzke.zzbww);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((((getClass().getName().hashCode() + 527) * 31) + (this.zzarp == null ? 0 : this.zzarp.hashCode())) * 31) + (this.zzarq == null ? 0 : this.zzarq.hashCode())) * 31) + zzacc.hashCode((Object[]) this.zzarr)) * 31) + (this.zzars == null ? 0 : this.zzars.hashCode());
        zzkg zzkg = this.zzart;
        int hashCode2 = ((hashCode * 31) + (zzkg == null ? 0 : zzkg.hashCode())) * 31;
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
        if (this.zzarq != null) {
            zza += zzabw.zzc(2, this.zzarq);
        }
        if (this.zzarr != null && this.zzarr.length > 0) {
            for (zzkf zzkf : this.zzarr) {
                if (zzkf != null) {
                    zza += zzabw.zzb(3, (zzace) zzkf);
                }
            }
        }
        if (this.zzars != null) {
            this.zzars.booleanValue();
            zza += zzabw.zzaq(4) + 1;
        }
        return this.zzart != null ? zza + zzabw.zzb(5, (zzace) this.zzart) : zza;
    }

    public final void zza(zzabw zzabw) throws IOException {
        if (this.zzarp != null) {
            zzabw.zze(1, this.zzarp.intValue());
        }
        if (this.zzarq != null) {
            zzabw.zzb(2, this.zzarq);
        }
        if (this.zzarr != null && this.zzarr.length > 0) {
            for (zzkf zzkf : this.zzarr) {
                if (zzkf != null) {
                    zzabw.zza(3, (zzace) zzkf);
                }
            }
        }
        if (this.zzars != null) {
            zzabw.zza(4, this.zzars.booleanValue());
        }
        if (this.zzart != null) {
            zzabw.zza(5, (zzace) this.zzart);
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
                this.zzarq = zzabv.readString();
            } else if (zzuw == 26) {
                int zzb = zzach.zzb(zzabv, 26);
                int length = this.zzarr == null ? 0 : this.zzarr.length;
                zzkf[] zzkfArr = new zzkf[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzarr, 0, zzkfArr, 0, length);
                }
                while (length < zzkfArr.length - 1) {
                    zzkfArr[length] = new zzkf();
                    zzabv.zza(zzkfArr[length]);
                    zzabv.zzuw();
                    length++;
                }
                zzkfArr[length] = new zzkf();
                zzabv.zza(zzkfArr[length]);
                this.zzarr = zzkfArr;
            } else if (zzuw == 32) {
                this.zzars = Boolean.valueOf(zzabv.zzux());
            } else if (zzuw == 42) {
                if (this.zzart == null) {
                    this.zzart = new zzkg();
                }
                zzabv.zza(this.zzart);
            } else if (!super.zza(zzabv, zzuw)) {
                return this;
            }
        }
    }
}
