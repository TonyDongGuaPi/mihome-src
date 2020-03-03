package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkp extends zzaby<zzkp> {
    public zzkq[] zzatf = zzkq.zzln();

    public zzkp() {
        this.zzbww = null;
        this.zzbxh = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkp)) {
            return false;
        }
        zzkp zzkp = (zzkp) obj;
        if (!zzacc.equals((Object[]) this.zzatf, (Object[]) zzkp.zzatf)) {
            return false;
        }
        return (this.zzbww == null || this.zzbww.isEmpty()) ? zzkp.zzbww == null || zzkp.zzbww.isEmpty() : this.zzbww.equals(zzkp.zzbww);
    }

    public final int hashCode() {
        return ((((getClass().getName().hashCode() + 527) * 31) + zzacc.hashCode((Object[]) this.zzatf)) * 31) + ((this.zzbww == null || this.zzbww.isEmpty()) ? 0 : this.zzbww.hashCode());
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza = super.zza();
        if (this.zzatf != null && this.zzatf.length > 0) {
            for (zzkq zzkq : this.zzatf) {
                if (zzkq != null) {
                    zza += zzabw.zzb(1, (zzace) zzkq);
                }
            }
        }
        return zza;
    }

    public final void zza(zzabw zzabw) throws IOException {
        if (this.zzatf != null && this.zzatf.length > 0) {
            for (zzkq zzkq : this.zzatf) {
                if (zzkq != null) {
                    zzabw.zza(1, (zzace) zzkq);
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
            if (zzuw == 10) {
                int zzb = zzach.zzb(zzabv, 10);
                int length = this.zzatf == null ? 0 : this.zzatf.length;
                zzkq[] zzkqArr = new zzkq[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzatf, 0, zzkqArr, 0, length);
                }
                while (length < zzkqArr.length - 1) {
                    zzkqArr[length] = new zzkq();
                    zzabv.zza(zzkqArr[length]);
                    zzabv.zzuw();
                    length++;
                }
                zzkqArr[length] = new zzkq();
                zzabv.zza(zzkqArr[length]);
                this.zzatf = zzkqArr;
            } else if (!super.zza(zzabv, zzuw)) {
                return this;
            }
        }
    }
}
