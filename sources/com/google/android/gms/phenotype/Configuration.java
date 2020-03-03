package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

@KeepForSdk
@SafeParcelable.Class(creator = "ConfigurationCreator")
@SafeParcelable.Reserved({1})
public class Configuration extends AbstractSafeParcelable implements Comparable<Configuration> {
    @KeepForSdk
    public static final Parcelable.Creator<Configuration> CREATOR = new zzc();
    @SafeParcelable.Field(id = 2)
    private final int zzc;
    @SafeParcelable.Field(id = 3)
    private final zzi[] zzd;
    @SafeParcelable.Field(id = 4)
    private final String[] zze;
    private final Map<String, zzi> zzf = new TreeMap();

    @SafeParcelable.Constructor
    public Configuration(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) zzi[] zziArr, @SafeParcelable.Param(id = 4) String[] strArr) {
        this.zzc = i;
        this.zzd = zziArr;
        for (zzi zzi : zziArr) {
            this.zzf.put(zzi.name, zzi);
        }
        this.zze = strArr;
        if (this.zze != null) {
            Arrays.sort(this.zze);
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return this.zzc - ((Configuration) obj).zzc;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Configuration) {
            Configuration configuration = (Configuration) obj;
            return this.zzc == configuration.zzc && zzn.equals(this.zzf, configuration.zzf) && Arrays.equals(this.zze, configuration.zze);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Configuration(");
        sb.append(this.zzc);
        sb.append(", ");
        sb.append(Operators.BRACKET_START_STR);
        for (zzi append : this.zzf.values()) {
            sb.append(append);
            sb.append(", ");
        }
        sb.append(Operators.BRACKET_END_STR);
        sb.append(", ");
        sb.append(Operators.BRACKET_START_STR);
        if (this.zze != null) {
            for (String append2 : this.zze) {
                sb.append(append2);
                sb.append(", ");
            }
        } else {
            sb.append("null");
        }
        sb.append(Operators.BRACKET_END_STR);
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzc);
        SafeParcelWriter.writeTypedArray(parcel, 3, this.zzd, i, false);
        SafeParcelWriter.writeStringArray(parcel, 4, this.zze, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
