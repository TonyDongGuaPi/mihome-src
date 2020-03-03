package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.Comparator;

@SafeParcelable.Class(creator = "FlagCreator")
@SafeParcelable.Reserved({1})
public final class zzi extends AbstractSafeParcelable implements Comparable<zzi> {
    public static final Parcelable.Creator<zzi> CREATOR = new zzk();
    private static final Comparator<zzi> zzai = new zzj();
    @SafeParcelable.Field(id = 2)
    public final String name;
    @SafeParcelable.Field(id = 3)
    private final long zzab;
    @SafeParcelable.Field(id = 4)
    private final boolean zzac;
    @SafeParcelable.Field(id = 5)
    private final double zzad;
    @SafeParcelable.Field(id = 6)
    private final String zzae;
    @SafeParcelable.Field(id = 7)
    private final byte[] zzaf;
    @SafeParcelable.Field(id = 8)
    private final int zzag;
    @SafeParcelable.Field(id = 9)
    public final int zzah;

    @SafeParcelable.Constructor
    public zzi(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 4) boolean z, @SafeParcelable.Param(id = 5) double d, @SafeParcelable.Param(id = 6) String str2, @SafeParcelable.Param(id = 7) byte[] bArr, @SafeParcelable.Param(id = 8) int i, @SafeParcelable.Param(id = 9) int i2) {
        this.name = str;
        this.zzab = j;
        this.zzac = z;
        this.zzad = d;
        this.zzae = str2;
        this.zzaf = bArr;
        this.zzag = i;
        this.zzah = i2;
    }

    private static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public final /* synthetic */ int compareTo(Object obj) {
        zzi zzi = (zzi) obj;
        int compareTo = this.name.compareTo(zzi.name);
        if (compareTo != 0) {
            return compareTo;
        }
        int compare = compare(this.zzag, zzi.zzag);
        if (compare != 0) {
            return compare;
        }
        switch (this.zzag) {
            case 1:
                long j = this.zzab;
                long j2 = zzi.zzab;
                if (j < j2) {
                    return -1;
                }
                return j == j2 ? 0 : 1;
            case 2:
                boolean z = this.zzac;
                if (z == zzi.zzac) {
                    return 0;
                }
                return z ? 1 : -1;
            case 3:
                return Double.compare(this.zzad, zzi.zzad);
            case 4:
                String str = this.zzae;
                String str2 = zzi.zzae;
                if (str == str2) {
                    return 0;
                }
                if (str == null) {
                    return -1;
                }
                if (str2 == null) {
                    return 1;
                }
                return str.compareTo(str2);
            case 5:
                if (this.zzaf == zzi.zzaf) {
                    return 0;
                }
                if (this.zzaf == null) {
                    return -1;
                }
                if (zzi.zzaf == null) {
                    return 1;
                }
                for (int i = 0; i < Math.min(this.zzaf.length, zzi.zzaf.length); i++) {
                    int i2 = this.zzaf[i] - zzi.zzaf[i];
                    if (i2 != 0) {
                        return i2;
                    }
                }
                return compare(this.zzaf.length, zzi.zzaf.length);
            default:
                int i3 = this.zzag;
                StringBuilder sb = new StringBuilder(31);
                sb.append("Invalid enum value: ");
                sb.append(i3);
                throw new AssertionError(sb.toString());
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzi) {
            zzi zzi = (zzi) obj;
            if (zzn.equals(this.name, zzi.name) && this.zzag == zzi.zzag && this.zzah == zzi.zzah) {
                switch (this.zzag) {
                    case 1:
                        if (this.zzab == zzi.zzab) {
                            return true;
                        }
                        break;
                    case 2:
                        return this.zzac == zzi.zzac;
                    case 3:
                        return this.zzad == zzi.zzad;
                    case 4:
                        return zzn.equals(this.zzae, zzi.zzae);
                    case 5:
                        return Arrays.equals(this.zzaf, zzi.zzaf);
                    default:
                        int i = this.zzag;
                        StringBuilder sb = new StringBuilder(31);
                        sb.append("Invalid enum value: ");
                        sb.append(i);
                        throw new AssertionError(sb.toString());
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0066, code lost:
        r0.append(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x007b, code lost:
        r0.append(", ");
        r0.append(r5.zzag);
        r0.append(", ");
        r0.append(r5.zzah);
        r0.append(com.taobao.weex.el.parse.Operators.BRACKET_END_STR);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0098, code lost:
        return r0.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0061, code lost:
        r0.append(r1);
        r1 = "'";
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String toString() {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Flag("
            r0.append(r1)
            java.lang.String r1 = r5.name
            r0.append(r1)
            java.lang.String r1 = ", "
            r0.append(r1)
            int r1 = r5.zzag
            switch(r1) {
                case 1: goto L_0x0076;
                case 2: goto L_0x0070;
                case 3: goto L_0x006a;
                case 4: goto L_0x005a;
                case 5: goto L_0x0046;
                default: goto L_0x0019;
            }
        L_0x0019:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            java.lang.String r1 = r5.name
            int r2 = r5.zzag
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            int r3 = r3 + 27
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Invalid type: "
            r4.append(r3)
            r4.append(r1)
            java.lang.String r1 = ", "
            r4.append(r1)
            r4.append(r2)
            java.lang.String r1 = r4.toString()
            r0.<init>(r1)
            throw r0
        L_0x0046:
            byte[] r1 = r5.zzaf
            if (r1 != 0) goto L_0x004d
            java.lang.String r1 = "null"
            goto L_0x0066
        L_0x004d:
            java.lang.String r1 = "'"
            r0.append(r1)
            byte[] r1 = r5.zzaf
            r2 = 3
            java.lang.String r1 = android.util.Base64.encodeToString(r1, r2)
            goto L_0x0061
        L_0x005a:
            java.lang.String r1 = "'"
            r0.append(r1)
            java.lang.String r1 = r5.zzae
        L_0x0061:
            r0.append(r1)
            java.lang.String r1 = "'"
        L_0x0066:
            r0.append(r1)
            goto L_0x007b
        L_0x006a:
            double r1 = r5.zzad
            r0.append(r1)
            goto L_0x007b
        L_0x0070:
            boolean r1 = r5.zzac
            r0.append(r1)
            goto L_0x007b
        L_0x0076:
            long r1 = r5.zzab
            r0.append(r1)
        L_0x007b:
            java.lang.String r1 = ", "
            r0.append(r1)
            int r1 = r5.zzag
            r0.append(r1)
            java.lang.String r1 = ", "
            r0.append(r1)
            int r1 = r5.zzah
            r0.append(r1)
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.phenotype.zzi.toString():java.lang.String");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.name, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzab);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzac);
        SafeParcelWriter.writeDouble(parcel, 5, this.zzad);
        SafeParcelWriter.writeString(parcel, 6, this.zzae, false);
        SafeParcelWriter.writeByteArray(parcel, 7, this.zzaf, false);
        SafeParcelWriter.writeInt(parcel, 8, this.zzag);
        SafeParcelWriter.writeInt(parcel, 9, this.zzah);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
