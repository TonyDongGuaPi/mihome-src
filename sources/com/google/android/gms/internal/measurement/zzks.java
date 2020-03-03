package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzks extends zzaby<zzks> {
    private static volatile zzks[] zzaum;
    public String name = null;
    public String zzajf = null;
    private Float zzarb = null;
    public Double zzarc = null;
    public Long zzate = null;
    public Long zzaun = null;

    public zzks() {
        this.zzbww = null;
        this.zzbxh = -1;
    }

    public static zzks[] zzlo() {
        if (zzaum == null) {
            synchronized (zzacc.zzbxg) {
                if (zzaum == null) {
                    zzaum = new zzks[0];
                }
            }
        }
        return zzaum;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzks)) {
            return false;
        }
        zzks zzks = (zzks) obj;
        if (this.zzaun == null) {
            if (zzks.zzaun != null) {
                return false;
            }
        } else if (!this.zzaun.equals(zzks.zzaun)) {
            return false;
        }
        if (this.name == null) {
            if (zzks.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzks.name)) {
            return false;
        }
        if (this.zzajf == null) {
            if (zzks.zzajf != null) {
                return false;
            }
        } else if (!this.zzajf.equals(zzks.zzajf)) {
            return false;
        }
        if (this.zzate == null) {
            if (zzks.zzate != null) {
                return false;
            }
        } else if (!this.zzate.equals(zzks.zzate)) {
            return false;
        }
        if (this.zzarb == null) {
            if (zzks.zzarb != null) {
                return false;
            }
        } else if (!this.zzarb.equals(zzks.zzarb)) {
            return false;
        }
        if (this.zzarc == null) {
            if (zzks.zzarc != null) {
                return false;
            }
        } else if (!this.zzarc.equals(zzks.zzarc)) {
            return false;
        }
        return (this.zzbww == null || this.zzbww.isEmpty()) ? zzks.zzbww == null || zzks.zzbww.isEmpty() : this.zzbww.equals(zzks.zzbww);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.zzaun == null ? 0 : this.zzaun.hashCode())) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.zzajf == null ? 0 : this.zzajf.hashCode())) * 31) + (this.zzate == null ? 0 : this.zzate.hashCode())) * 31) + (this.zzarb == null ? 0 : this.zzarb.hashCode())) * 31) + (this.zzarc == null ? 0 : this.zzarc.hashCode())) * 31;
        if (this.zzbww != null && !this.zzbww.isEmpty()) {
            i = this.zzbww.hashCode();
        }
        return hashCode + i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza = super.zza();
        if (this.zzaun != null) {
            zza += zzabw.zzc(1, this.zzaun.longValue());
        }
        if (this.name != null) {
            zza += zzabw.zzc(2, this.name);
        }
        if (this.zzajf != null) {
            zza += zzabw.zzc(3, this.zzajf);
        }
        if (this.zzate != null) {
            zza += zzabw.zzc(4, this.zzate.longValue());
        }
        if (this.zzarb != null) {
            this.zzarb.floatValue();
            zza += zzabw.zzaq(5) + 4;
        }
        if (this.zzarc == null) {
            return zza;
        }
        this.zzarc.doubleValue();
        return zza + zzabw.zzaq(6) + 8;
    }

    public final void zza(zzabw zzabw) throws IOException {
        if (this.zzaun != null) {
            zzabw.zzb(1, this.zzaun.longValue());
        }
        if (this.name != null) {
            zzabw.zzb(2, this.name);
        }
        if (this.zzajf != null) {
            zzabw.zzb(3, this.zzajf);
        }
        if (this.zzate != null) {
            zzabw.zzb(4, this.zzate.longValue());
        }
        if (this.zzarb != null) {
            zzabw.zza(5, this.zzarb.floatValue());
        }
        if (this.zzarc != null) {
            zzabw.zza(6, this.zzarc.doubleValue());
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
                this.zzaun = Long.valueOf(zzabv.zzuz());
            } else if (zzuw == 18) {
                this.name = zzabv.readString();
            } else if (zzuw == 26) {
                this.zzajf = zzabv.readString();
            } else if (zzuw == 32) {
                this.zzate = Long.valueOf(zzabv.zzuz());
            } else if (zzuw == 45) {
                this.zzarb = Float.valueOf(Float.intBitsToFloat(zzabv.zzva()));
            } else if (zzuw == 49) {
                this.zzarc = Double.valueOf(Double.longBitsToDouble(zzabv.zzvb()));
            } else if (!super.zza(zzabv, zzuw)) {
                return this;
            }
        }
    }
}
