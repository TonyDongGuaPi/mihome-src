package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

final class zzel extends zzej {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private int tag;
    private final boolean zzrs = true;
    private final int zzrt;
    private int zzru;

    public zzel(ByteBuffer byteBuffer, boolean z) {
        super((zzek) null);
        this.buffer = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
        this.pos = arrayOffset;
        this.zzrt = arrayOffset;
        this.limit = byteBuffer.arrayOffset() + byteBuffer.limit();
    }

    private final boolean zzcm() {
        return this.pos == this.limit;
    }

    public final int zzcn() throws IOException {
        if (zzcm()) {
            return Integer.MAX_VALUE;
        }
        this.tag = zzdd();
        if (this.tag == this.zzru) {
            return Integer.MAX_VALUE;
        }
        return this.tag >>> 3;
    }

    public final int getTag() {
        return this.tag;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002b A[LOOP:0: B:10:0x002b->B:13:0x0038, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzco() throws java.io.IOException {
        /*
            r7 = this;
            boolean r0 = r7.zzcm()
            r1 = 0
            if (r0 != 0) goto L_0x0089
            int r0 = r7.tag
            int r2 = r7.zzru
            if (r0 != r2) goto L_0x000f
            goto L_0x0089
        L_0x000f:
            int r0 = r7.tag
            r0 = r0 & 7
            r2 = 5
            r3 = 4
            r4 = 1
            if (r0 == r2) goto L_0x0085
            switch(r0) {
                case 0: goto L_0x0056;
                case 1: goto L_0x0050;
                case 2: goto L_0x0048;
                case 3: goto L_0x0020;
                default: goto L_0x001b;
            }
        L_0x001b:
            com.google.android.gms.internal.vision.zzgg r0 = com.google.android.gms.internal.vision.zzgf.zzfm()
            throw r0
        L_0x0020:
            int r0 = r7.zzru
            int r1 = r7.tag
            int r1 = r1 >>> 3
            int r1 = r1 << 3
            r1 = r1 | r3
            r7.zzru = r1
        L_0x002b:
            int r1 = r7.zzcn()
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r1 == r2) goto L_0x003a
            boolean r1 = r7.zzco()
            if (r1 != 0) goto L_0x002b
        L_0x003a:
            int r1 = r7.tag
            int r2 = r7.zzru
            if (r1 != r2) goto L_0x0043
            r7.zzru = r0
            return r4
        L_0x0043:
            com.google.android.gms.internal.vision.zzgf r0 = com.google.android.gms.internal.vision.zzgf.zzfo()
            throw r0
        L_0x0048:
            int r0 = r7.zzdd()
            r7.zzz(r0)
            return r4
        L_0x0050:
            r0 = 8
            r7.zzz(r0)
            return r4
        L_0x0056:
            int r0 = r7.limit
            int r2 = r7.pos
            int r0 = r0 - r2
            r2 = 10
            if (r0 < r2) goto L_0x0074
            byte[] r0 = r7.buffer
            int r3 = r7.pos
            r5 = r3
            r3 = 0
        L_0x0065:
            if (r3 >= r2) goto L_0x0074
            int r6 = r5 + 1
            byte r5 = r0[r5]
            if (r5 < 0) goto L_0x0070
            r7.pos = r6
            goto L_0x007f
        L_0x0070:
            int r3 = r3 + 1
            r5 = r6
            goto L_0x0065
        L_0x0074:
            if (r1 >= r2) goto L_0x0080
            byte r0 = r7.readByte()
            if (r0 >= 0) goto L_0x007f
            int r1 = r1 + 1
            goto L_0x0074
        L_0x007f:
            return r4
        L_0x0080:
            com.google.android.gms.internal.vision.zzgf r0 = com.google.android.gms.internal.vision.zzgf.zzfj()
            throw r0
        L_0x0085:
            r7.zzz(r3)
            return r4
        L_0x0089:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzel.zzco():boolean");
    }

    public final double readDouble() throws IOException {
        zzab(1);
        return Double.longBitsToDouble(zzdh());
    }

    public final float readFloat() throws IOException {
        zzab(5);
        return Float.intBitsToFloat(zzdg());
    }

    public final long zzcp() throws IOException {
        zzab(0);
        return zzde();
    }

    public final long zzcq() throws IOException {
        zzab(0);
        return zzde();
    }

    public final int zzcr() throws IOException {
        zzab(0);
        return zzdd();
    }

    public final long zzcs() throws IOException {
        zzab(1);
        return zzdh();
    }

    public final int zzct() throws IOException {
        zzab(5);
        return zzdg();
    }

    public final boolean zzcu() throws IOException {
        zzab(0);
        if (zzdd() != 0) {
            return true;
        }
        return false;
    }

    public final String readString() throws IOException {
        return zzg(false);
    }

    public final String zzcv() throws IOException {
        return zzg(true);
    }

    private final String zzg(boolean z) throws IOException {
        zzab(2);
        int zzdd = zzdd();
        if (zzdd == 0) {
            return "";
        }
        zzaa(zzdd);
        if (!z || zziw.zzg(this.buffer, this.pos, this.pos + zzdd)) {
            String str = new String(this.buffer, this.pos, zzdd, zzga.UTF_8);
            this.pos += zzdd;
            return str;
        }
        throw zzgf.zzfp();
    }

    public final <T> T zza(Class<T> cls, zzfk zzfk) throws IOException {
        zzab(2);
        return zzb(zzhs.zzgl().zzf(cls), zzfk);
    }

    public final <T> T zza(zzhw<T> zzhw, zzfk zzfk) throws IOException {
        zzab(2);
        return zzb(zzhw, zzfk);
    }

    private final <T> T zzb(zzhw<T> zzhw, zzfk zzfk) throws IOException {
        int zzdd = zzdd();
        zzaa(zzdd);
        int i = this.limit;
        int i2 = this.pos + zzdd;
        this.limit = i2;
        try {
            T newInstance = zzhw.newInstance();
            zzhw.zza(newInstance, this, zzfk);
            zzhw.zze(newInstance);
            if (this.pos == i2) {
                return newInstance;
            }
            throw zzgf.zzfo();
        } finally {
            this.limit = i;
        }
    }

    public final <T> T zzb(Class<T> cls, zzfk zzfk) throws IOException {
        zzab(3);
        return zzd(zzhs.zzgl().zzf(cls), zzfk);
    }

    public final <T> T zzc(zzhw<T> zzhw, zzfk zzfk) throws IOException {
        zzab(3);
        return zzd(zzhw, zzfk);
    }

    private final <T> T zzd(zzhw<T> zzhw, zzfk zzfk) throws IOException {
        int i = this.zzru;
        this.zzru = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = zzhw.newInstance();
            zzhw.zza(newInstance, this, zzfk);
            zzhw.zze(newInstance);
            if (this.tag == this.zzru) {
                return newInstance;
            }
            throw zzgf.zzfo();
        } finally {
            this.zzru = i;
        }
    }

    public final zzeo zzcw() throws IOException {
        zzeo zzeo;
        zzab(2);
        int zzdd = zzdd();
        if (zzdd == 0) {
            return zzeo.zzrx;
        }
        zzaa(zzdd);
        if (this.zzrs) {
            zzeo = zzeo.zzc(this.buffer, this.pos, zzdd);
        } else {
            zzeo = zzeo.zzb(this.buffer, this.pos, zzdd);
        }
        this.pos += zzdd;
        return zzeo;
    }

    public final int zzcx() throws IOException {
        zzab(0);
        return zzdd();
    }

    public final int zzcy() throws IOException {
        zzab(0);
        return zzdd();
    }

    public final int zzcz() throws IOException {
        zzab(5);
        return zzdg();
    }

    public final long zzda() throws IOException {
        zzab(1);
        return zzdh();
    }

    public final int zzdb() throws IOException {
        zzab(0);
        return zzez.zzaq(zzdd());
    }

    public final long zzdc() throws IOException {
        zzab(0);
        return zzez.zzd(zzde());
    }

    public final void zza(List<Double> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfh) {
            zzfh zzfh = (zzfh) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzdd = zzdd();
                    zzac(zzdd);
                    int i3 = this.pos + zzdd;
                    while (this.pos < i3) {
                        zzfh.zzc(Double.longBitsToDouble(zzdj()));
                    }
                    return;
                default:
                    throw zzgf.zzfm();
            }
            do {
                zzfh.zzc(readDouble());
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzdd2 = zzdd();
                zzac(zzdd2);
                int i4 = this.pos + zzdd2;
                while (this.pos < i4) {
                    list.add(Double.valueOf(Double.longBitsToDouble(zzdj())));
                }
                return;
            default:
                throw zzgf.zzfm();
        }
        do {
            list.add(Double.valueOf(readDouble()));
            if (!zzcm()) {
                i = this.pos;
            } else {
                return;
            }
        } while (zzdd() == this.tag);
        this.pos = i;
    }

    public final void zzb(List<Float> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfv) {
            zzfv zzfv = (zzfv) list;
            int i3 = this.tag & 7;
            if (i3 == 2) {
                int zzdd = zzdd();
                zzad(zzdd);
                int i4 = this.pos + zzdd;
                while (this.pos < i4) {
                    zzfv.zzh(Float.intBitsToFloat(zzdi()));
                }
            } else if (i3 == 5) {
                do {
                    zzfv.zzh(readFloat());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 2) {
                int zzdd2 = zzdd();
                zzad(zzdd2);
                int i6 = this.pos + zzdd2;
                while (this.pos < i6) {
                    list.add(Float.valueOf(Float.intBitsToFloat(zzdi())));
                }
            } else if (i5 == 5) {
                do {
                    list.add(Float.valueOf(readFloat()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzc(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzgt.zzp(zzcp());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzgt.zzp(zzde());
                }
                zzae(zzdd);
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(zzcp()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Long.valueOf(zzde()));
                }
                zzae(zzdd2);
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzd(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzgt.zzp(zzcq());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzgt.zzp(zzde());
                }
                zzae(zzdd);
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(zzcq()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Long.valueOf(zzde()));
                }
                zzae(zzdd2);
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zze(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzfz.zzbg(zzcr());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzfz.zzbg(zzdd());
                }
                zzae(zzdd);
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzcr()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Integer.valueOf(zzdd()));
                }
                zzae(zzdd2);
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzf(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzdd = zzdd();
                    zzac(zzdd);
                    int i3 = this.pos + zzdd;
                    while (this.pos < i3) {
                        zzgt.zzp(zzdj());
                    }
                    return;
                default:
                    throw zzgf.zzfm();
            }
            do {
                zzgt.zzp(zzcs());
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzdd2 = zzdd();
                zzac(zzdd2);
                int i4 = this.pos + zzdd2;
                while (this.pos < i4) {
                    list.add(Long.valueOf(zzdj()));
                }
                return;
            default:
                throw zzgf.zzfm();
        }
        do {
            list.add(Long.valueOf(zzcs()));
            if (!zzcm()) {
                i = this.pos;
            } else {
                return;
            }
        } while (zzdd() == this.tag);
        this.pos = i;
    }

    public final void zzg(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 2) {
                int zzdd = zzdd();
                zzad(zzdd);
                int i4 = this.pos + zzdd;
                while (this.pos < i4) {
                    zzfz.zzbg(zzdi());
                }
            } else if (i3 == 5) {
                do {
                    zzfz.zzbg(zzct());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 2) {
                int zzdd2 = zzdd();
                zzad(zzdd2);
                int i6 = this.pos + zzdd2;
                while (this.pos < i6) {
                    list.add(Integer.valueOf(zzdi()));
                }
            } else if (i5 == 5) {
                do {
                    list.add(Integer.valueOf(zzct()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzh(List<Boolean> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzem) {
            zzem zzem = (zzem) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzem.addBoolean(zzcu());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzem.addBoolean(zzdd() != 0);
                }
                zzae(zzdd);
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Boolean.valueOf(zzcu()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Boolean.valueOf(zzdd() != 0));
                }
                zzae(zzdd2);
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void readStringList(List<String> list) throws IOException {
        zza(list, false);
    }

    public final void zzi(List<String> list) throws IOException {
        zza(list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int i;
        int i2;
        if ((this.tag & 7) != 2) {
            throw zzgf.zzfm();
        } else if (!(list instanceof zzgo) || z) {
            do {
                list.add(zzg(z));
                if (!zzcm()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i;
        } else {
            zzgo zzgo = (zzgo) list;
            do {
                zzgo.zzc(zzcw());
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
        }
    }

    public final <T> void zza(List<T> list, zzhw<T> zzhw, zzfk zzfk) throws IOException {
        int i;
        if ((this.tag & 7) == 2) {
            int i2 = this.tag;
            do {
                list.add(zzb(zzhw, zzfk));
                if (!zzcm()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == i2);
            this.pos = i;
            return;
        }
        throw zzgf.zzfm();
    }

    public final <T> void zzb(List<T> list, zzhw<T> zzhw, zzfk zzfk) throws IOException {
        int i;
        if ((this.tag & 7) == 3) {
            int i2 = this.tag;
            do {
                list.add(zzd(zzhw, zzfk));
                if (!zzcm()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == i2);
            this.pos = i;
            return;
        }
        throw zzgf.zzfm();
    }

    public final void zzj(List<zzeo> list) throws IOException {
        int i;
        if ((this.tag & 7) == 2) {
            do {
                list.add(zzcw());
                if (!zzcm()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i;
            return;
        }
        throw zzgf.zzfm();
    }

    public final void zzk(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzfz.zzbg(zzcx());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzfz.zzbg(zzdd());
                }
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzcx()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Integer.valueOf(zzdd()));
                }
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzl(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzfz.zzbg(zzcy());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzfz.zzbg(zzdd());
                }
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzcy()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Integer.valueOf(zzdd()));
                }
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzm(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 2) {
                int zzdd = zzdd();
                zzad(zzdd);
                int i4 = this.pos + zzdd;
                while (this.pos < i4) {
                    zzfz.zzbg(zzdi());
                }
            } else if (i3 == 5) {
                do {
                    zzfz.zzbg(zzcz());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 2) {
                int zzdd2 = zzdd();
                zzad(zzdd2);
                int i6 = this.pos + zzdd2;
                while (this.pos < i6) {
                    list.add(Integer.valueOf(zzdi()));
                }
            } else if (i5 == 5) {
                do {
                    list.add(Integer.valueOf(zzcz()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzn(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzdd = zzdd();
                    zzac(zzdd);
                    int i3 = this.pos + zzdd;
                    while (this.pos < i3) {
                        zzgt.zzp(zzdj());
                    }
                    return;
                default:
                    throw zzgf.zzfm();
            }
            do {
                zzgt.zzp(zzda());
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzdd2 = zzdd();
                zzac(zzdd2);
                int i4 = this.pos + zzdd2;
                while (this.pos < i4) {
                    list.add(Long.valueOf(zzdj()));
                }
                return;
            default:
                throw zzgf.zzfm();
        }
        do {
            list.add(Long.valueOf(zzda()));
            if (!zzcm()) {
                i = this.pos;
            } else {
                return;
            }
        } while (zzdd() == this.tag);
        this.pos = i;
    }

    public final void zzo(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzfz.zzbg(zzdb());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzfz.zzbg(zzez.zzaq(zzdd()));
                }
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzdb()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Integer.valueOf(zzez.zzaq(zzdd())));
                }
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    public final void zzp(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzgt.zzp(zzdc());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzgt.zzp(zzez.zzd(zzde()));
                }
            } else {
                throw zzgf.zzfm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(zzdc()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Long.valueOf(zzez.zzd(zzde())));
                }
            } else {
                throw zzgf.zzfm();
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:15|16|(2:18|34)(3:28|19|20)) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004d, code lost:
        if (zzco() != false) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0057, code lost:
        throw new com.google.android.gms.internal.vision.zzgf("Unable to parse map entry.");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0049 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void zza(java.util.Map<K, V> r6, com.google.android.gms.internal.vision.zzgy<K, V> r7, com.google.android.gms.internal.vision.zzfk r8) throws java.io.IOException {
        /*
            r5 = this;
            r0 = 2
            r5.zzab(r0)
            int r0 = r5.zzdd()
            r5.zzaa(r0)
            int r1 = r5.limit
            int r2 = r5.pos
            int r2 = r2 + r0
            r5.limit = r2
            K r0 = r7.zzyw     // Catch:{ all -> 0x005e }
            V r2 = r7.zzgq     // Catch:{ all -> 0x005e }
        L_0x0016:
            int r3 = r5.zzcn()     // Catch:{ all -> 0x005e }
            r4 = 2147483647(0x7fffffff, float:NaN)
            if (r3 == r4) goto L_0x0058
            switch(r3) {
                case 1: goto L_0x0035;
                case 2: goto L_0x0027;
                default: goto L_0x0022;
            }
        L_0x0022:
            boolean r3 = r5.zzco()     // Catch:{ zzgg -> 0x0049 }
            goto L_0x003e
        L_0x0027:
            com.google.android.gms.internal.vision.zzjd r3 = r7.zzyx     // Catch:{ zzgg -> 0x0049 }
            V r4 = r7.zzgq     // Catch:{ zzgg -> 0x0049 }
            java.lang.Class r4 = r4.getClass()     // Catch:{ zzgg -> 0x0049 }
            java.lang.Object r3 = r5.zza((com.google.android.gms.internal.vision.zzjd) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.vision.zzfk) r8)     // Catch:{ zzgg -> 0x0049 }
            r2 = r3
            goto L_0x0016
        L_0x0035:
            com.google.android.gms.internal.vision.zzjd r3 = r7.zzyv     // Catch:{ zzgg -> 0x0049 }
            r4 = 0
            java.lang.Object r3 = r5.zza((com.google.android.gms.internal.vision.zzjd) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.vision.zzfk) r4)     // Catch:{ zzgg -> 0x0049 }
            r0 = r3
            goto L_0x0016
        L_0x003e:
            if (r3 == 0) goto L_0x0041
            goto L_0x0016
        L_0x0041:
            com.google.android.gms.internal.vision.zzgf r3 = new com.google.android.gms.internal.vision.zzgf     // Catch:{ zzgg -> 0x0049 }
            java.lang.String r4 = "Unable to parse map entry."
            r3.<init>(r4)     // Catch:{ zzgg -> 0x0049 }
            throw r3     // Catch:{ zzgg -> 0x0049 }
        L_0x0049:
            boolean r3 = r5.zzco()     // Catch:{ all -> 0x005e }
            if (r3 == 0) goto L_0x0050
            goto L_0x0016
        L_0x0050:
            com.google.android.gms.internal.vision.zzgf r6 = new com.google.android.gms.internal.vision.zzgf     // Catch:{ all -> 0x005e }
            java.lang.String r7 = "Unable to parse map entry."
            r6.<init>(r7)     // Catch:{ all -> 0x005e }
            throw r6     // Catch:{ all -> 0x005e }
        L_0x0058:
            r6.put(r0, r2)     // Catch:{ all -> 0x005e }
            r5.limit = r1
            return
        L_0x005e:
            r6 = move-exception
            r5.limit = r1
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzel.zza(java.util.Map, com.google.android.gms.internal.vision.zzgy, com.google.android.gms.internal.vision.zzfk):void");
    }

    private final Object zza(zzjd zzjd, Class<?> cls, zzfk zzfk) throws IOException {
        switch (zzek.zzrr[zzjd.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzcu());
            case 2:
                return zzcw();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzcy());
            case 5:
                return Integer.valueOf(zzct());
            case 6:
                return Long.valueOf(zzcs());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzcr());
            case 9:
                return Long.valueOf(zzcq());
            case 10:
                return zza(cls, zzfk);
            case 11:
                return Integer.valueOf(zzcz());
            case 12:
                return Long.valueOf(zzda());
            case 13:
                return Integer.valueOf(zzdb());
            case 14:
                return Long.valueOf(zzdc());
            case 15:
                return zzg(true);
            case 16:
                return Integer.valueOf(zzcx());
            case 17:
                return Long.valueOf(zzcp());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final int zzdd() throws IOException {
        byte b;
        int i = this.pos;
        if (this.limit != this.pos) {
            int i2 = i + 1;
            byte b2 = this.buffer[i];
            if (b2 >= 0) {
                this.pos = i2;
                return b2;
            } else if (this.limit - i2 < 9) {
                return (int) zzdf();
            } else {
                int i3 = i2 + 1;
                byte b3 = b2 ^ (this.buffer[i2] << 7);
                if (b3 < 0) {
                    b = b3 ^ Byte.MIN_VALUE;
                } else {
                    int i4 = i3 + 1;
                    byte b4 = b3 ^ (this.buffer[i3] << 14);
                    if (b4 >= 0) {
                        b = b4 ^ 16256;
                    } else {
                        i3 = i4 + 1;
                        byte b5 = b4 ^ (this.buffer[i4] << 21);
                        if (b5 < 0) {
                            b = b5 ^ -2080896;
                        } else {
                            i4 = i3 + 1;
                            byte b6 = this.buffer[i3];
                            b = (b5 ^ (b6 << 28)) ^ 266354560;
                            if (b6 < 0) {
                                i3 = i4 + 1;
                                if (this.buffer[i4] < 0) {
                                    i4 = i3 + 1;
                                    if (this.buffer[i3] < 0) {
                                        i3 = i4 + 1;
                                        if (this.buffer[i4] < 0) {
                                            i4 = i3 + 1;
                                            if (this.buffer[i3] < 0) {
                                                i3 = i4 + 1;
                                                if (this.buffer[i4] < 0) {
                                                    throw zzgf.zzfj();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    i3 = i4;
                }
                this.pos = i3;
                return b;
            }
        } else {
            throw zzgf.zzfh();
        }
    }

    private final long zzde() throws IOException {
        long j;
        int i;
        long j2;
        long j3;
        long j4;
        int i2 = this.pos;
        if (this.limit != i2) {
            byte[] bArr = this.buffer;
            int i3 = i2 + 1;
            byte b = bArr[i2];
            if (b >= 0) {
                this.pos = i3;
                return (long) b;
            } else if (this.limit - i3 < 9) {
                return zzdf();
            } else {
                int i4 = i3 + 1;
                byte b2 = b ^ (bArr[i3] << 7);
                if (b2 < 0) {
                    j3 = (long) (b2 ^ Byte.MIN_VALUE);
                } else {
                    int i5 = i4 + 1;
                    byte b3 = b2 ^ (bArr[i4] << 14);
                    if (b3 >= 0) {
                        j4 = (long) (b3 ^ 16256);
                        i = i5;
                        j = j4;
                        this.pos = i;
                        return j;
                    }
                    i4 = i5 + 1;
                    byte b4 = b3 ^ (bArr[i5] << 21);
                    if (b4 < 0) {
                        j3 = (long) (b4 ^ -2080896);
                    } else {
                        long j5 = (long) b4;
                        i = i4 + 1;
                        long j6 = (((long) bArr[i4]) << 28) ^ j5;
                        if (j6 >= 0) {
                            j = j6 ^ 266354560;
                        } else {
                            int i6 = i + 1;
                            long j7 = j6 ^ (((long) bArr[i]) << 35);
                            if (j7 < 0) {
                                j2 = -34093383808L ^ j7;
                            } else {
                                i = i6 + 1;
                                long j8 = j7 ^ (((long) bArr[i6]) << 42);
                                if (j8 >= 0) {
                                    j = j8 ^ 4363953127296L;
                                } else {
                                    i6 = i + 1;
                                    long j9 = j8 ^ (((long) bArr[i]) << 49);
                                    if (j9 < 0) {
                                        j2 = -558586000294016L ^ j9;
                                    } else {
                                        i = i6 + 1;
                                        j = (j9 ^ (((long) bArr[i6]) << 56)) ^ 71499008037633920L;
                                        if (j < 0) {
                                            i6 = i + 1;
                                            if (((long) bArr[i]) < 0) {
                                                throw zzgf.zzfj();
                                            }
                                            i = i6;
                                        }
                                    }
                                }
                            }
                            j = j2;
                            i = i6;
                        }
                        this.pos = i;
                        return j;
                    }
                }
                j4 = j3;
                i = i4;
                j = j4;
                this.pos = i;
                return j;
            }
        } else {
            throw zzgf.zzfh();
        }
    }

    private final long zzdf() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte readByte = readByte();
            j |= ((long) (readByte & Byte.MAX_VALUE)) << i;
            if ((readByte & 128) == 0) {
                return j;
            }
        }
        throw zzgf.zzfj();
    }

    private final byte readByte() throws IOException {
        if (this.pos != this.limit) {
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i + 1;
            return bArr[i];
        }
        throw zzgf.zzfh();
    }

    private final int zzdg() throws IOException {
        zzaa(4);
        return zzdi();
    }

    private final long zzdh() throws IOException {
        zzaa(8);
        return zzdj();
    }

    private final int zzdi() {
        int i = this.pos;
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    private final long zzdj() {
        int i = this.pos;
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    private final void zzz(int i) throws IOException {
        zzaa(i);
        this.pos += i;
    }

    private final void zzaa(int i) throws IOException {
        if (i < 0 || i > this.limit - this.pos) {
            throw zzgf.zzfh();
        }
    }

    private final void zzab(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzgf.zzfm();
        }
    }

    private final void zzac(int i) throws IOException {
        zzaa(i);
        if ((i & 7) != 0) {
            throw zzgf.zzfo();
        }
    }

    private final void zzad(int i) throws IOException {
        zzaa(i);
        if ((i & 3) != 0) {
            throw zzgf.zzfo();
        }
    }

    private final void zzae(int i) throws IOException {
        if (this.pos != i) {
            throw zzgf.zzfh();
        }
    }
}
