package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcz;
import java.io.IOException;

public final class zzdm extends zzjn<zzdm> {
    private static volatile zzdm[] zzoo;
    public String name = null;
    private String zzop = null;
    private String[] zzoq = zzjw.zzady;
    private zzcz.zzd.zzb zzor;
    public String zzos = null;
    public Long zzot = null;
    public Long zzou = null;
    public zzdt[] zzov = zzdt.zzcd();

    public static zzdm[] zzcb() {
        if (zzoo == null) {
            synchronized (zzjr.zzado) {
                if (zzoo == null) {
                    zzoo = new zzdm[0];
                }
            }
        }
        return zzoo;
    }

    public zzdm() {
        this.zzadp = -1;
    }

    public final void zza(zzjl zzjl) throws IOException {
        if (this.name != null) {
            zzjl.zza(1, this.name);
        }
        if (this.zzop != null) {
            zzjl.zza(2, this.zzop);
        }
        if (this.zzoq != null && this.zzoq.length > 0) {
            for (String str : this.zzoq) {
                if (str != null) {
                    zzjl.zza(3, str);
                }
            }
        }
        if (!(this.zzor == null || this.zzor == null)) {
            zzjl.zze(4, this.zzor.zzr());
        }
        if (this.zzos != null) {
            zzjl.zza(5, this.zzos);
        }
        if (this.zzot != null) {
            zzjl.zzi(6, this.zzot.longValue());
        }
        if (this.zzou != null) {
            zzjl.zzi(7, this.zzou.longValue());
        }
        if (this.zzov != null && this.zzov.length > 0) {
            for (zzdt zzdt : this.zzov) {
                if (zzdt != null) {
                    zzjl.zza(8, (zzjt) zzdt);
                }
            }
        }
        super.zza(zzjl);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.name != null) {
            zzt += zzjl.zzb(1, this.name);
        }
        if (this.zzop != null) {
            zzt += zzjl.zzb(2, this.zzop);
        }
        if (this.zzoq != null && this.zzoq.length > 0) {
            int i = 0;
            int i2 = 0;
            for (String str : this.zzoq) {
                if (str != null) {
                    i2++;
                    i += zzjl.zzn(str);
                }
            }
            zzt = zzt + i + (i2 * 1);
        }
        if (!(this.zzor == null || this.zzor == null)) {
            zzt += zzjl.zzi(4, this.zzor.zzr());
        }
        if (this.zzos != null) {
            zzt += zzjl.zzb(5, this.zzos);
        }
        if (this.zzot != null) {
            zzt += zzjl.zzd(6, this.zzot.longValue());
        }
        if (this.zzou != null) {
            zzt += zzjl.zzd(7, this.zzou.longValue());
        }
        if (this.zzov != null && this.zzov.length > 0) {
            for (zzdt zzdt : this.zzov) {
                if (zzdt != null) {
                    zzt += zzjl.zzb(8, (zzjt) zzdt);
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
                this.name = zzjk.readString();
            } else if (zzdq == 18) {
                this.zzop = zzjk.readString();
            } else if (zzdq != 26) {
                if (zzdq == 32) {
                    int position = zzjk.getPosition();
                    int zzdt = zzjk.zzdt();
                    switch (zzdt) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                            this.zzor = zzcz.zzd.zzb.zzt(zzdt);
                            break;
                        default:
                            zzjk.zzbt(position);
                            zza(zzjk, zzdq);
                            break;
                    }
                } else if (zzdq == 42) {
                    this.zzos = zzjk.readString();
                } else if (zzdq == 48) {
                    this.zzot = Long.valueOf(zzjk.zzdu());
                } else if (zzdq == 56) {
                    this.zzou = Long.valueOf(zzjk.zzdu());
                } else if (zzdq == 66) {
                    int zzb = zzjw.zzb(zzjk, 66);
                    int length = this.zzov == null ? 0 : this.zzov.length;
                    zzdt[] zzdtArr = new zzdt[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzov, 0, zzdtArr, 0, length);
                    }
                    while (length < zzdtArr.length - 1) {
                        zzdtArr[length] = new zzdt();
                        zzjk.zza((zzjt) zzdtArr[length]);
                        zzjk.zzdq();
                        length++;
                    }
                    zzdtArr[length] = new zzdt();
                    zzjk.zza((zzjt) zzdtArr[length]);
                    this.zzov = zzdtArr;
                } else if (!super.zza(zzjk, zzdq)) {
                    return this;
                }
            } else {
                int zzb2 = zzjw.zzb(zzjk, 26);
                int length2 = this.zzoq == null ? 0 : this.zzoq.length;
                String[] strArr = new String[(zzb2 + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zzoq, 0, strArr, 0, length2);
                }
                while (length2 < strArr.length - 1) {
                    strArr[length2] = zzjk.readString();
                    zzjk.zzdq();
                    length2++;
                }
                strArr[length2] = zzjk.readString();
                this.zzoq = strArr;
            }
        }
    }
}
