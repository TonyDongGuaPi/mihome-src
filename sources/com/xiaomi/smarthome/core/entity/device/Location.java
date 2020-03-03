package com.xiaomi.smarthome.core.entity.device;

import android.os.Parcel;
import android.os.Parcelable;

public enum Location implements Parcelable {
    UNKNOWN,
    LOCAL,
    REMOTE;
    
    public static final Parcelable.Creator<Location> CREATOR = null;

    public int describeContents() {
        return 0;
    }

    static {
        CREATOR = new Parcelable.Creator<Location>() {
            /* renamed from: a */
            public Location createFromParcel(Parcel parcel) {
                return Location.values()[parcel.readInt()];
            }

            /* renamed from: a */
            public Location[] newArray(int i) {
                return new Location[i];
            }
        };
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
