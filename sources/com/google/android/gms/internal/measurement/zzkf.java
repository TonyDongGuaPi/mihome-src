package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkf extends zzaby<zzkf> {
    private static volatile zzkf[] zzaru;
    public zzki zzarv = null;
    public zzkg zzarw = null;
    public Boolean zzarx = null;
    public String zzary = null;

    public zzkf() {
        this.zzbww = null;
        this.zzbxh = -1;
    }

    public static zzkf[] zzlg() {
        if (zzaru == null) {
            synchronized (zzacc.zzbxg) {
                if (zzaru == null) {
                    zzaru = new zzkf[0];
                }
            }
        }
        return zzaru;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkf)) {
            return false;
        }
        zzkf zzkf = (zzkf) obj;
        if (this.zzarv == null) {
            if (zzkf.zzarv != null) {
                return false;
            }
        } else if (!this.zzarv.equals(zzkf.zzarv)) {
            return false;
        }
        if (this.zzarw == null) {
            if (zzkf.zzarw != null) {
                return false;
            }
        } else if (!this.zzarw.equals(zzkf.zzarw)) {
            return false;
        }
        if (this.zzarx == null) {
            if (zzkf.zzarx != null) {
                return false;
            }
        } else if (!this.zzarx.equals(zzkf.zzarx)) {
            return false;
        }
        if (this.zzary == null) {
            if (zzkf.zzary != null) {
                return false;
            }
        } else if (!this.zzary.equals(zzkf.zzary)) {
            return false;
        }
        return (this.zzbww == null || this.zzbww.isEmpty()) ? zzkf.zzbww == null || zzkf.zzbww.isEmpty() : this.zzbww.equals(zzkf.zzbww);
    }

    public final int hashCode() {
        zzki zzki = this.zzarv;
        int i = 0;
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + (zzki == null ? 0 : zzki.hashCode());
        zzkg zzkg = this.zzarw;
        int hashCode2 = ((((((hashCode * 31) + (zzkg == null ? 0 : zzkg.hashCode())) * 31) + (this.zzarx == null ? 0 : this.zzarx.hashCode())) * 31) + (this.zzary == null ? 0 : this.zzary.hashCode())) * 31;
        if (this.zzbww != null && !this.zzbww.isEmpty()) {
            i = this.zzbww.hashCode();
        }
        return hashCode2 + i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza = super.zza();
        if (this.zzarv != null) {
            zza += zzabw.zzb(1, (zzace) this.zzarv);
        }
        if (this.zzarw != null) {
            zza += zzabw.zzb(2, (zzace) this.zzarw);
        }
        if (this.zzarx != null) {
            this.zzarx.booleanValue();
            zza += zzabw.zzaq(3) + 1;
        }
        return this.zzary != null ? zza + zzabw.zzc(4, this.zzary) : zza;
    }

    public final void zza(zzabw zzabw) throws IOException {
        if (this.zzarv != null) {
            zzabw.zza(1, (zzace) this.zzarv);
        }
        if (this.zzarw != null) {
            zzabw.zza(2, (zzace) this.zzarw);
        }
        if (this.zzarx != null) {
            zzabw.zza(3, this.zzarx.booleanValue());
        }
        if (this.zzary != null) {
            zzabw.zzb(4, this.zzary);
        }
        super.zza(zzabw);
    }

    public final /* synthetic */ zzace zzb(zzabv zzabv) throws IOException {
        zzace zzace;
        while (true) {
            int zzuw = zzabv.zzuw();
            if (zzuw == 0) {
                return this;
            }
            if (zzuw == 10) {
                if (this.zzarv == null) {
                    this.zzarv = new zzki();
                }
                zzace = this.zzarv;
            } else if (zzuw == 18) {
                if (this.zzarw == null) {
                    this.zzarw = new zzkg();
                }
                zzace = this.zzarw;
            } else if (zzuw == 24) {
                this.zzarx = Boolean.valueOf(zzabv.zzux());
            } else if (zzuw == 34) {
                this.zzary = zzabv.readString();
            } else if (!super.zza(zzabv, zzuw)) {
                return this;
            }
            zzabv.zza(zzace);
        }
    }
}
