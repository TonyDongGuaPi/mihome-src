package com.xiaomi.smarthome.core.entity.device;

import android.os.Parcel;
import android.os.Parcelable;

public enum ScanType implements Parcelable {
    ALL,
    BLUETOOTH;
    
    public static final Parcelable.Creator<ScanType> CREATOR = null;

    public int describeContents() {
        return 0;
    }

    static {
        CREATOR = new Parcelable.Creator<ScanType>() {
            /* renamed from: a */
            public ScanType createFromParcel(Parcel parcel) {
                return ScanType.values()[parcel.readInt()];
            }

            /* renamed from: a */
            public ScanType[] newArray(int i) {
                return new ScanType[i];
            }
        };
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
