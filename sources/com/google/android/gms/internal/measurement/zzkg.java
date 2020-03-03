package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkg extends zzaby<zzkg> {
    public Integer zzarz = null;
    public Boolean zzasa = null;
    public String zzasb = null;
    public String zzasc = null;
    public String zzasd = null;

    public zzkg() {
        this.zzbww = null;
        this.zzbxh = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzkg zzb(zzabv zzabv) throws IOException {
        int zzuy;
        while (true) {
            int zzuw = zzabv.zzuw();
            if (zzuw == 0) {
                return this;
            }
            if (zzuw == 8) {
                try {
                    zzuy = zzabv.zzuy();
                    if (zzuy < 0 || zzuy > 4) {
                        StringBuilder sb = new StringBuilder(46);
                        sb.append(zzuy);
                        sb.append(" is not a valid enum ComparisonType");
                    } else {
                        this.zzarz = Integer.valueOf(zzuy);
                    }
                } catch (IllegalArgumentException unused) {
                    zzabv.zzam(zzabv.getPosition());
                    zza(zzabv, zzuw);
                }
            } else if (zzuw == 16) {
                this.zzasa = Boolean.valueOf(zzabv.zzux());
            } else if (zzuw == 26) {
                this.zzasb = zzabv.readString();
            } else if (zzuw == 34) {
                this.zzasc = zzabv.readString();
            } else if (zzuw == 42) {
                this.zzasd = zzabv.readString();
            } else if (!super.zza(zzabv, zzuw)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append(zzuy);
        sb2.append(" is not a valid enum ComparisonType");
        throw new IllegalArgumentException(sb2.toString());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkg)) {
            return false;
        }
        zzkg zzkg = (zzkg) obj;
        if (this.zzarz == null) {
            if (zzkg.zzarz != null) {
                return false;
            }
        } else if (!this.zzarz.equals(zzkg.zzarz)) {
            return false;
        }
        if (this.zzasa == null) {
            if (zzkg.zzasa != null) {
                return false;
            }
        } else if (!this.zzasa.equals(zzkg.zzasa)) {
            return false;
        }
        if (this.zzasb == null) {
            if (zzkg.zzasb != null) {
                return false;
            }
        } else if (!this.zzasb.equals(zzkg.zzasb)) {
            return false;
        }
        if (this.zzasc == null) {
            if (zzkg.zzasc != null) {
                return false;
            }
        } else if (!this.zzasc.equals(zzkg.zzasc)) {
            return false;
        }
        if (this.zzasd == null) {
            if (zzkg.zzasd != null) {
                return false;
            }
        } else if (!this.zzasd.equals(zzkg.zzasd)) {
            return false;
        }
        return (this.zzbww == null || this.zzbww.isEmpty()) ? zzkg.zzbww == null || zzkg.zzbww.isEmpty() : this.zzbww.equals(zzkg.zzbww);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.zzarz == null ? 0 : this.zzarz.intValue())) * 31) + (this.zzasa == null ? 0 : this.zzasa.hashCode())) * 31) + (this.zzasb == null ? 0 : this.zzasb.hashCode())) * 31) + (this.zzasc == null ? 0 : this.zzasc.hashCode())) * 31) + (this.zzasd == null ? 0 : this.zzasd.hashCode())) * 31;
        if (this.zzbww != null && !this.zzbww.isEmpty()) {
            i = this.zzbww.hashCode();
        }
        return hashCode + i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza = super.zza();
        if (this.zzarz != null) {
            zza += zzabw.zzf(1, this.zzarz.intValue());
        }
        if (this.zzasa != null) {
            this.zzasa.booleanValue();
            zza += zzabw.zzaq(2) + 1;
        }
        if (this.zzasb != null) {
            zza += zzabw.zzc(3, this.zzasb);
        }
        if (this.zzasc != null) {
            zza += zzabw.zzc(4, this.zzasc);
        }
        return this.zzasd != null ? zza + zzabw.zzc(5, this.zzasd) : zza;
    }

    public final void zza(zzabw zzabw) throws IOException {
        if (this.zzarz != null) {
            zzabw.zze(1, this.zzarz.intValue());
        }
        if (this.zzasa != null) {
            zzabw.zza(2, this.zzasa.booleanValue());
        }
        if (this.zzasb != null) {
            zzabw.zzb(3, this.zzasb);
        }
        if (this.zzasc != null) {
            zzabw.zzb(4, this.zzasc);
        }
        if (this.zzasd != null) {
            zzabw.zzb(5, this.zzasd);
        }
        super.zza(zzabw);
    }
}
